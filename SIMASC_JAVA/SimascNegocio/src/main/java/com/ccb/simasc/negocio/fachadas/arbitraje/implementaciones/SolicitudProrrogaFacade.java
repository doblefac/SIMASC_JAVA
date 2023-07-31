package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudProrroga;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudProrrogaFacade;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaCierreDTO;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaDTO;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.SolicitudProrroga;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link SolicitudProrroga}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SolicitudProrrogaFacade extends AccesoFacade<SolicitudProrroga, Long, SolicitudProrrogaDTO> implements ISolicitudProrrogaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	private static final Logger logger = LogManager.getLogger(SolicitudProrrogaFacade.class.getName());
	
	@EJB
	private ManejadorSolicitudProrroga manejadorSolicitudProrroga;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private EventoFacade eventoFacade;
	
	@EJB
	private ManejadorDocumento manejadorDocumento;
	
	@EJB
	private AlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSolicitudProrroga;
	}

	@Override
	public SolicitudProrrogaDTO transformarSinDependencias(SolicitudProrroga obj) {
		SolicitudProrrogaDTO dto = new SolicitudProrrogaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudProrrogaDTO transformarConDependencias(SolicitudProrroga obj) {
		SolicitudProrrogaDTO dto = new SolicitudProrrogaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudProrroga transformarEntidadConDependencias(SolicitudProrroga obj) {
		SolicitudProrroga dto = new SolicitudProrroga();
		dto = new SolicitudProrrogaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public SolicitudProrroga transformarEntidadSinDependencias(SolicitudProrroga obj) {
		SolicitudProrroga dto = new SolicitudProrroga();
		dto = new SolicitudProrrogaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 *  Método para consultar las solicitudes de prorroga de un caso 
	 * 
	 * @param idCaso:
	 *            Id del caso.
	
	 * @return List<SolicitudProrrogaCierreDTO>
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public SolicitudProrrogaCierreDTO consultarSolicitudesProrroga(Long idCaso, String idUsuarioModificacion) {
		
		
		Collection<SolicitudProrroga> listaSolicitudes = manejadorSolicitudProrroga.consultarSolicitudesProrroga(idCaso);	
		List<SolicitudProrrogaDTO> listaSolicitudesDTO = (List<SolicitudProrrogaDTO>) new SolicitudProrrogaDTO().transformarColeccionSinDependencias(listaSolicitudes);
		
		SolicitudProrrogaCierreDTO solicitudProrrogasCierre = new SolicitudProrrogaCierreDTO();
		solicitudProrrogasCierre.setListaSolicitudProrroga(listaSolicitudesDTO);
		solicitudProrrogasCierre.setDiasCaso(manejadorCaso.consultarDiferenciaFechaCaso(idCaso));
		return solicitudProrrogasCierre;
	}
	
	@Override
	public void modificarSolicitudeProrroga(SolicitudProrrogaDTO solicitud, String idUsuarioModificacion){
		
		SolicitudProrroga solicitudEntidad = new SolicitudProrroga();
		solicitudEntidad.setIdCaso(solicitud.getIdCaso());
		solicitudEntidad.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		solicitudEntidad.setFechaProrroga(solicitud.getFechaProrroga());
		solicitudEntidad.setFechaSolicitud(solicitud.getFechaSolicitud());
		solicitudEntidad.setFechaUltimaModificacion(solicitud.getFechaUltimaModificacion());
		if(solicitud.getIdSolicitud()!=null){
			solicitudEntidad.setIdDocumento(solicitud.getIdDocumento());	
		}
		solicitudEntidad.setIdUsuarioModificacion(idUsuarioModificacion);
		solicitudEntidad.setObservaciones(solicitud.getObservaciones());
		Date fecha = new Date();
		if(solicitud.getIdSolicitud()!=null){
			
			solicitudEntidad.setIdSolicitud(solicitud.getIdSolicitud());
		
			if(solicitud.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)){
				manejadorSolicitudProrroga.actualizar(solicitudEntidad);
				String mensaje = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO371.toString()));
				eventoFacade.registrarEvento(solicitud.getIdCaso(), UtilDominios.TIPO_EVENTO_SOLICITUD_PRORROGA,mensaje,
					idUsuarioModificacion, fecha, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			}else{
				solicitudEntidad.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
				manejadorSolicitudProrroga.actualizar(solicitudEntidad);
				List<String> tiposDocumento = new ArrayList<String>();
				tiposDocumento.add(UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_SOLICITUD_PRORROGA);
				List<Documento> documentosProrroga = manejadorDocumento.consultarDocumentosCasoTipo(solicitudEntidad.getIdCaso(), tiposDocumento); 
				if(documentosProrroga.size()>0){
					almacenamientoDocumentosFacade.eliminarDocumento(documentosProrroga.get(0).getIdDocumento(), idUsuarioModificacion);
				}
				
				String mensaje = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO372.toString()));
				eventoFacade.registrarEvento(solicitud.getIdCaso(), UtilDominios.TIPO_EVENTO_SOLICITUD_PRORROGA,mensaje,
						idUsuarioModificacion, fecha, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			}
			
		}
		else{
			String mensaje = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO373.toString()));
			eventoFacade.registrarEvento(solicitud.getIdCaso(), UtilDominios.TIPO_EVENTO_SOLICITUD_PRORROGA,mensaje,
					idUsuarioModificacion, fecha, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorSolicitudProrroga.crear(solicitudEntidad);
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ISolicitudProrrogaFacade#validarProrrogaVigentePorFecha(com.ccb.simasc.transversal.entidades.
	 * SolicitudProrroga)
	 */
	@Override
	public boolean validarProrrogaCasoVigentePorFecha( Long idCaso, Date fecha ){
		boolean fechaVigente;
				List<SolicitudProrroga> solicitudProrrogas = manejadorSolicitudProrroga
						.consultarSolicitudesProrroga(idCaso);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String fechaAudiencia = sdf.format(fecha).toString();
		try {
			fecha = sdf.parse(fechaAudiencia);
		} catch (ParseException e) {
			logger.error(e);
		}
		SolicitudProrroga solicitudProrroga = (!solicitudProrrogas.isEmpty())? solicitudProrrogas.get(0) : null;	
		if ( solicitudProrroga != null && solicitudProrroga.getFechaProrroga().compareTo( fecha ) > 0 ){
			fechaVigente = true;
		} else{
			fechaVigente = false;
		}
		return fechaVigente;
	}
	
	// protected region metodos adicionales end
	
}
