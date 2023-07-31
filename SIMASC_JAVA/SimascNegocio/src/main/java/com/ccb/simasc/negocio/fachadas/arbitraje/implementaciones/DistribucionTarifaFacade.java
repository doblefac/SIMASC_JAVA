package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorDistribucionTarifa;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDistribucionTarifaFacade;
import com.ccb.simasc.transversal.dto.DistribucionTarifaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.entidades.DistribucionTarifa;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DistribucionTarifa}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DistribucionTarifaFacade extends AccesoFacade<DistribucionTarifa, Long, DistribucionTarifaDTO> implements IDistribucionTarifaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	/**
	 * Manejador utilizado para la consulta de la distribucion
	 */
	@EJB
	private ManejadorDistribucionTarifa manejadorDistribucionTarifa;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDistribucionTarifa;
	}

	@Override
	public DistribucionTarifaDTO transformarSinDependencias(DistribucionTarifa obj) {
		DistribucionTarifaDTO dto = new DistribucionTarifaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DistribucionTarifaDTO transformarConDependencias(DistribucionTarifa obj) {
		DistribucionTarifaDTO dto = new DistribucionTarifaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DistribucionTarifa transformarEntidadConDependencias(DistribucionTarifa obj) {
		DistribucionTarifa dto = new DistribucionTarifa();
		dto = new DistribucionTarifaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DistribucionTarifa transformarEntidadSinDependencias(DistribucionTarifa obj) {
		DistribucionTarifa dto = new DistribucionTarifa();
		dto = new DistribucionTarifaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDistribucionTarifaFacade#consultarDistribucion(com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO)
	 */
	@Override
	public List<DistribucionTarifaDTO> consultarDistribucion(ParametrosTarifasDTO parametrosTarifasDTO) {
		
		Collection<InformacionFiltro> filtros = new ArrayList<>();
		InformacionFiltro informacionFiltroCantidadArbitros = new InformacionFiltro(TipoFiltro.EXACTO,
				DistribucionTarifa.ENTIDAD_DISTRIBUCION_TARIFA_CANTIDAD_ARBITROS,
				parametrosTarifasDTO.getCantidadArbitros());		
		InformacionFiltro informacionFiltroEstado = new InformacionFiltro(TipoFiltro.EXACTO,
				ParametrizacionTarifas.ENTIDAD_PARAMETRIZACION_TARIFAS_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		InformacionFiltro informacionFiltroServicio = new InformacionFiltro(TipoFiltro.EXACTO,
				DistribucionTarifa.ENTIDAD_DISTRIBUCION_TARIFA_ID_SERVICIO,
				parametrosTarifasDTO.getIdServicio());
		
		filtros.add(informacionFiltroCantidadArbitros);
		filtros.add(informacionFiltroEstado);
		filtros.add(informacionFiltroServicio);
		List<DistribucionTarifaDTO> parametrizacion = (List<DistribucionTarifaDTO>) transformarColeccionConDependencias(
				new ArrayList<DistribucionTarifa>(),
				obtenerPorFiltro(filtros, null, null, new ArrayList<DistribucionTarifaDTO>(), true));
		
		return parametrizacion;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDistribucionTarifaFacade#actualizarDisitribucionTarifas(java.util.List)
	 */
	@Override
	public void actualizarDisitribucionTarifas(List<DistribucionTarifa> distribuciones) {
		for (DistribucionTarifa distribucionTarifa : distribuciones) {
			manejadorDistribucionTarifa.actualizar(distribucionTarifa);
		}
	}
	
	
	// protected region metodos adicionales end
	
}
