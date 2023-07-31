package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorGastoTribunal;
import com.ccb.simasc.integracion.manejadores.ManejadorHonorariosFijados;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGastoTribunalFacade;
import com.ccb.simasc.transversal.dto.GastoTribunalDTO;
import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.entidades.GastoTribunal;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link GastoTribunal}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class GastoTribunalFacade extends AccesoFacade<GastoTribunal, Long, GastoTribunalDTO>
		implements IGastoTribunalFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorGastoTribunal manejadorGastoTribunal;

	@EJB
	private ManejadorHonorariosFijados manejadorHonorariosFijados;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorGastoTribunal;
	}

	@Override
	public GastoTribunalDTO transformarSinDependencias(GastoTribunal obj) {
		GastoTribunalDTO dto = new GastoTribunalDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public GastoTribunalDTO transformarConDependencias(GastoTribunal obj) {
		GastoTribunalDTO dto = new GastoTribunalDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public GastoTribunal transformarEntidadConDependencias(GastoTribunal obj) {
		GastoTribunal dto = new GastoTribunal();
		dto = new GastoTribunalDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public GastoTribunal transformarEntidadSinDependencias(GastoTribunal obj) {
		GastoTribunal dto = new GastoTribunal();
		dto = new GastoTribunalDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * 
	 * @param gastoTribunalDTO
	 * @param actualizar
	 */
	@Override
	public void crearGastoTribunal(GastoTribunalDTO gastoTribunalDTO, boolean actualizar) {
		GastoTribunal gastoTribunal = new GastoTribunal();
		Date fecha = gastoTribunalDTO.getFecha() != null ? gastoTribunalDTO.getFecha() : new Date();
		gastoTribunal.setFecha(fecha);
		gastoTribunal.setIdCaso(gastoTribunalDTO.getIdCaso());
		gastoTribunal.setValor(gastoTribunalDTO.getValor());
		gastoTribunal.setIdDocumento(gastoTribunalDTO.getIdDocumento());
		gastoTribunal.setDescripcion(gastoTribunalDTO.getDescripcion());
		gastoTribunal.setIdUsuarioModificacion(gastoTribunalDTO.getIdUsuarioModificacion());
		gastoTribunal.setFechaUltimaModificacion(new Date());
		gastoTribunal.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (actualizar)
			gastoTribunal.setIdGastoTribunal(gastoTribunalDTO.getIdGastoTribunal());
		manejadorGastoTribunal.actualizar(gastoTribunal);
	}

	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	@Override
	public List<GastoTribunalDTO> consultarGastosTribunalCaso(Long idCaso) {
		Collection<InformacionFiltro> filtros = new ArrayList<>();
		InformacionFiltro informacionFiltroIdCaso = new InformacionFiltro(TipoFiltro.EXACTO,
				GastoTribunal.ENTIDAD_GASTO_TRIBUNAL_ID_CASO, idCaso);
		InformacionFiltro informacionFiltroEstado = new InformacionFiltro(TipoFiltro.EXACTO,
				GastoTribunal.ENTIDAD_GASTO_TRIBUNAL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		filtros.add(informacionFiltroIdCaso);
		filtros.add(informacionFiltroEstado);
		List<GastoTribunalDTO> lstGastosTribunal = (List<GastoTribunalDTO>) transformarColeccionConDependencias(
				new ArrayList<GastoTribunal>(),
				obtenerPorFiltro(filtros, null, null, new ArrayList<GastoTribunalDTO>(), true));
		return lstGastosTribunal;
	}

	/**
	 * 
	 * @param idCaso
	 * @param idGasto
	 */
	@Override
	public void eliminarGastoTribunal(Long idCaso, Long idGasto) {
		GastoTribunal gastoTribunal = new GastoTribunal();
		gastoTribunal.setFechaUltimaModificacion(new Date());
		gastoTribunal.setIdGastoTribunal(idGasto);
		gastoTribunal.setIdCaso(idCaso);
		gastoTribunal.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
		manejadorGastoTribunal.actualizar(gastoTribunal);

	}

	/**
	 * 
	 * @param idCaso
	 * @return
	 */
	@Override
	public GastoTribunalDTO consultarTotales(Long idCaso) {

		GastoTribunalDTO gastosDTO = new GastoTribunalDTO();
		List<GastoTribunalDTO> lstGastosTribunal = consultarGastosTribunalCaso(idCaso);
		HonorariosFijados honorariosFijados = new HonorariosFijados();
		Long totalGastos = 0L;
		for (GastoTribunalDTO gastoTribunalDTO : lstGastosTribunal) {
			totalGastos += gastoTribunalDTO.getValor();
		}
		HonorariosFijadosDTO honorariosFijadosDTO = new HonorariosFijadosDTO();
		if (manejadorHonorariosFijados.consultarPorIdCaso(idCaso) != null) {
			honorariosFijados = honorariosFijadosDTO
					.transformarEntidadSinDependencias(manejadorHonorariosFijados.consultarPorIdCaso(idCaso));
			gastosDTO.setGastosPresupuestados(honorariosFijados.getValorFijadoPretensiones());
			gastosDTO.setTotalGastos(totalGastos);
			gastosDTO.setSaldoGastos(gastosDTO.getGastosPresupuestados().subtract(new BigDecimal(totalGastos)));
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR183.toString()));
		}

		return gastosDTO;
	}

	// protected region metodos adicionales end

}
