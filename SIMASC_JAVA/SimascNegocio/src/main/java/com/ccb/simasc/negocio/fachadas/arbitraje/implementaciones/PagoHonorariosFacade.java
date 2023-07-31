package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHonorariosFijados;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoHonorarios;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoHonorariosFacade;
import com.ccb.simasc.transversal.dto.PagoHonorariosDTO;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.entidades.PagoHonorarios;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PagoHonorarios}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PagoHonorariosFacade extends AccesoFacade<PagoHonorarios, Long, PagoHonorariosDTO>
		implements IPagoHonorariosFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	@EJB
	private ManejadorPagoHonorarios manejadorPagoHonorarios;

	/**
	 * 
	 */
	@EJB
	private ManejadorHonorariosFijados manejadorHonorariosFijados;
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPagoHonorarios;
	}

	@Override
	public PagoHonorariosDTO transformarSinDependencias(PagoHonorarios obj) {
		PagoHonorariosDTO dto = new PagoHonorariosDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PagoHonorariosDTO transformarConDependencias(PagoHonorarios obj) {
		PagoHonorariosDTO dto = new PagoHonorariosDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PagoHonorarios transformarEntidadConDependencias(PagoHonorarios obj) {
		PagoHonorarios dto = new PagoHonorarios();
		dto = new PagoHonorariosDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public PagoHonorarios transformarEntidadSinDependencias(PagoHonorarios obj) {
		PagoHonorarios dto = new PagoHonorarios();
		dto = new PagoHonorariosDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Se registra el pago de honorarios de una parte
	 * 
	 * @param PagoHonorariosDTO
	 *            pagoHonorariosDTO
	 */
	@Override
	public void registrarPagoHonorarios(PagoHonorariosDTO pagoHonorariosDTO) {
		PagoHonorarios pagoHonorarios = null;
		pagoHonorarios = manejadorPagoHonorarios.consultarPorIdCasoYPartePaga(pagoHonorariosDTO.getIdCaso(),
				pagoHonorariosDTO.getParteQuePaga());
		List<PagoHonorarios> pagosHonorarios = manejadorPagoHonorarios.consultarPorIdCaso(pagoHonorariosDTO.getIdCaso());
		if (pagoHonorarios != null) {
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR184.toString());
			throw new SimascException(mensaje);
		}
		// consulta la fecha de fijacion de honorarios
		HonorariosFijados honorariosFijados = manejadorHonorariosFijados
				.consultarPorIdCaso(pagoHonorariosDTO.getIdCaso());
		if (honorariosFijados != null) {
			Date fechaFijacion = honorariosFijados.getFechaFijacion();
			Date fechaLimite = honorariosFijados.getFechaLimiteDePago();
			int diasHabiles = diaFestivoFacade.obtenerDiasHabilesEntreFechas(fechaLimite,
					pagoHonorariosDTO.getFechaPago());

			int diasProrroga = diaFestivoFacade.obtenerDiasHabilesEntreFechas(fechaFijacion, fechaLimite);
			
			if (((diasHabiles>0&&diasHabiles<= 10) || (diasHabiles >= 10&&pagosHonorarios.size() == 1))&&diasProrroga!=6) {
				Date fechaFijacionLimite = diaFestivoFacade.adicionarDiasHabilesFecha(fechaFijacion, 5);
				honorariosFijados.setFechaLimiteDePago(fechaFijacionLimite);
				manejadorHonorariosFijados.actualizar(honorariosFijados);
			}
			if(diasProrroga==6&&diasHabiles >= 10){
				String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR235.toString());
				throw new SimascException(mensaje);
			}
		}else{
			String mensaje = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR236.toString());
			throw new SimascException(mensaje);
		}
		pagoHonorarios = new PagoHonorarios();
		pagoHonorarios.setIdCaso(pagoHonorariosDTO.getIdCaso());
		pagoHonorarios.setFechaPago(pagoHonorariosDTO.getFechaPago());
		pagoHonorarios.setParteQuePaga(pagoHonorariosDTO.getParteQuePaga());
		pagoHonorarios.setValorPagado(pagoHonorariosDTO.getValorPagado());
		pagoHonorarios.setFechaUltimaModificacion(new Date());
		pagoHonorarios.setIdUsuarioModificacion(pagoHonorariosDTO.getIdUsuarioModificacion() != null
				? pagoHonorariosDTO.getIdUsuarioModificacion() : UtilConstantes.USUARIO_DEFECTO_SIMASC);
		pagoHonorarios.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		manejadorPagoHonorarios.actualizar(pagoHonorarios);

	}

	// protected region metodos adicionales end

}
