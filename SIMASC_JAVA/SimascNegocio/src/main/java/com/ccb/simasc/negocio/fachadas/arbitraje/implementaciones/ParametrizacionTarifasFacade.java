package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrizacionTarifas;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade;
import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link ParametrizacionTarifas}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametrizacionTarifasFacade extends AccesoFacade<ParametrizacionTarifas, Long, ParametrizacionTarifasDTO>
		implements IParametrizacionTarifasFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorParametrizacionTarifas manejadorParametrizacionTarifas;

	@EJB
	private ParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private HonorariosFijadosFacade honorariosFijadosFacade;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametrizacionTarifas;
	}

	@Override
	public ParametrizacionTarifasDTO transformarSinDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarSinDependencias(obj);
	}

	@Override
	public ParametrizacionTarifasDTO transformarConDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarConDependencias(obj);
	}

	@Override
	public ParametrizacionTarifas transformarEntidadConDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public ParametrizacionTarifas transformarEntidadSinDependencias(ParametrizacionTarifas obj) {
		return new ParametrizacionTarifasDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * consulta la parametrizacion de las tarifas
	 * 
	 * @param ParametrosTarifasDTO
	 *            parametrosTarifasDTO
	 */
	@Override
	public ParametrizacionTarifasDTO consultarParametrizacion(ParametrosTarifasDTO parametrosTarifasDTO) {		
		try {
			List<ParametrizacionTarifasDTO> parametrizacionTarifas = manejadorParametrizacionTarifas.consultarRangoTarifa(parametrosTarifasDTO, true);
			if(parametrizacionTarifas != null && !parametrizacionTarifas.isEmpty())
				return parametrizacionTarifas.get(0);
			else
				throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR273.toString()));
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade#calcularTarifaPublico(com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO)
	 */
	@Override
	public ResultadosTarifaDTO calcularTarifaPublico(ParametrosTarifasDTO parametrosTarifasDTO) {
		
		if(parametrosTarifasDTO.getIdCaso() != null) {
			Caso caso = manejadorCaso.buscar(parametrosTarifasDTO.getIdCaso());
			parametrosTarifasDTO.setIdServicio(caso.getIdServicio());
		}
		
		List<HonorariosFijadosDTO> honorarios = honorariosFijadosFacade.calcularTarifas(parametrosTarifasDTO);
		List<ParametrizacionTarifasDTO> rangos = manejadorParametrizacionTarifas.consultarRangoTarifa(parametrosTarifasDTO, false);
		
		
		ResultadosTarifaDTO tarifas = new ResultadosTarifaDTO();
		tarifas.setRangos(rangos);
		tarifas.setResultados(honorarios);
		
		return tarifas;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade#obtenerRangosTarifa(com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO)
	 */
	@Override
	public List<ParametrizacionTarifasDTO> obtenerRangosTarifa(ParametrosTarifasDTO parametrosTarifasDTO) {
		Collection<InformacionFiltro> filtros = new ArrayList<>();
		InformacionFiltro informacionFiltroCantidadArbitros = new InformacionFiltro(TipoFiltro.EXACTO,
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_CANTIDAD_ARBITROS,
				parametrosTarifasDTO.getCantidadArbitros());
		InformacionFiltro informacionFiltroTipoTarifa = new InformacionFiltro(TipoFiltro.EXACTO,
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_TARIFA,
				parametrosTarifasDTO.getTipoTarifa());
		InformacionFiltro informacionFiltroEstado = new InformacionFiltro(TipoFiltro.EXACTO,
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (parametrosTarifasDTO.getIdServicio() == null) {
			parametrosTarifasDTO.setIdServicio((long) 2);
		}
		InformacionFiltro informacionFiltroIdServicio = new InformacionFiltro(TipoFiltro.EXACTO, 
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_ID_SERVICIO,
				parametrosTarifasDTO.getIdServicio());
		filtros.add(informacionFiltroIdServicio);	
		filtros.add(informacionFiltroCantidadArbitros);
		filtros.add(informacionFiltroTipoTarifa);
		filtros.add(informacionFiltroEstado);
		Collection<InformacionOrdenamiento> ordenamientos = new ArrayList<>();
		InformacionOrdenamiento ordenamientoRangoInferior = new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE,
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_RANGO_INFERIOR);
		ordenamientos.add(ordenamientoRangoInferior);
		
		try {
    		return (List<ParametrizacionTarifasDTO>) transformarColeccionConDependencias(
    				new ArrayList<ParametrizacionTarifas>(),
    				obtenerPorFiltro(filtros, ordenamientos, null, new ArrayList<ParametrizacionTarifasDTO>(), true));
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade#actualizarParametrizacion(com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO) 
	 */
	@Override
	public void actualizarParametrizacion(ParametrizacionTarifas tarifa) {
		ParametrosTarifasDTO parametros = new ParametrosTarifasDTO();
		parametros.setCantidadArbitros(tarifa.getCantidadArbitros());
		parametros.setTipoTarifa(tarifa.getTipoTarifa());
		parametros.setIdServicio(tarifa.getIdServicio());
		List<ParametrizacionTarifasDTO> tarifas = obtenerRangosTarifa(parametros);
		for (ParametrizacionTarifasDTO parametrizacionTarifasDTO : tarifas) {
			if (tarifa.getIdParametrizacionTarifas() != null && tarifa.getIdParametrizacionTarifas().equals(parametrizacionTarifasDTO.getIdParametrizacionTarifas()))
				continue;
			if ((tarifa.getRangoInferior().compareTo(parametrizacionTarifasDTO.getRangoInferior()) >= 0
					&& tarifa.getRangoInferior().compareTo(parametrizacionTarifasDTO.getRangoSuperior()) < 0)
					|| (tarifa.getRangoSuperior().compareTo(parametrizacionTarifasDTO.getRangoInferior()) > 0
							&& tarifa.getRangoSuperior().compareTo(parametrizacionTarifasDTO.getRangoSuperior()) <= 0)
					|| (tarifa.getRangoInferior().compareTo(parametrizacionTarifasDTO.getRangoInferior()) < 0
							&& tarifa.getRangoSuperior().compareTo(parametrizacionTarifasDTO.getRangoSuperior()) > 0)) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR504.toString()));
			}
		}
		tarifa.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		tarifa.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		tarifa.setTipoValorRango(UtilDominios.TIPO_VALOR_TARIFA_SMM);
		tarifa.setMoneda(UtilDominios.TIPO_MONEDA_COP);
		manejadorParametrizacionTarifas.actualizar(tarifa);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade#eliminarParametrizacion(java.lang.Long)
	 */
	@Override
	public void eliminarParametrizacion(Long idParametrizacion) {
		ParametrizacionTarifas tarifa = manejadorParametrizacionTarifas.buscar(idParametrizacion);
		tarifa.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		tarifa.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
		tarifa.setFechaUltimaModificacion(new Date());
	}
	
	
	// protected region metodos adicionales end


}
