package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorNotificacionDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionDocumentoFacade;
import com.ccb.simasc.transversal.dto.NotificacionDocumentoDTO;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link NotificacionDocumento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class NotificacionDocumentoFacade extends AccesoFacade<NotificacionDocumento, Long, NotificacionDocumentoDTO> implements INotificacionDocumentoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorNotificacionDocumento manejadorNotificacionDocumento;
	@Inject
	private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorNotificacionDocumento;
	}

	@Override
	public NotificacionDocumentoDTO transformarSinDependencias(NotificacionDocumento obj) {
		NotificacionDocumentoDTO dto = new NotificacionDocumentoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public NotificacionDocumentoDTO transformarConDependencias(NotificacionDocumento obj) {
		NotificacionDocumentoDTO dto = new NotificacionDocumentoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public NotificacionDocumento transformarEntidadConDependencias(NotificacionDocumento obj) {
		NotificacionDocumento dto = new NotificacionDocumento();
		dto = new NotificacionDocumentoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public NotificacionDocumento transformarEntidadSinDependencias(NotificacionDocumento obj) {
		NotificacionDocumento dto = new NotificacionDocumento();
		dto = new NotificacionDocumentoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<HashMap<String,Object>> obtenerEstadosOFijacionListas(String tipo,String palabraClave,Date fechaInicio,Date fechaFinal){
		List<NotificacionDocumento> notificaciones = manejadorNotificacionDocumento.obtenerEstadosOFijacionListas(tipo, palabraClave, fechaInicio, fechaFinal);
		List<HashMap<String,Object>> listaReturn = new ArrayList<HashMap<String,Object>>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//horas utc 0
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE,0);		
		Date HoraInicioPublicaciones = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		Date HoraFinPublicaciones = calendar.getTime();
		
		if(fechaInicio == null && fechaFinal==null){
			if(new Date().before(HoraInicioPublicaciones)){
				if(UtilDominios.TIPO_LIBRO_NOTIFICACION_LISTA.equals(tipo)){
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR158.toString()));
				}else{
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR160.toString()));
				}
			}
			
			if(new Date().after(HoraFinPublicaciones)){
				if(UtilDominios.TIPO_LIBRO_NOTIFICACION_LISTA.equals(tipo)){
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR159.toString()));
				}else{
					throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR161.toString()));
				}
				
			}
			
		}

				
		for(NotificacionDocumento notificacion : notificaciones){
			HashMap<String,Object> campo = new HashMap<String,Object>();
			campo.put("idCaso", notificacion.getCaso().getIdCaso());
			campo.put("nombreCaso", notificacion.getCaso().getNombre());
			campo.put("claseProceso", notificacion.getCaso().getServicioMateria().getServicio().getNombre());
			
			List<Persona> demandantes = (List<Persona>) manejadorRolPersonaCaso.consultarPersonasPorRolCaso(notificacion.getIdCaso(), UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
			String convocante = "";
			if(demandantes.size() > 1){
				for (int i = 0; i < demandantes.size(); i++) {
					convocante += demandantes.get(i).getNombreCompleto();
					if(i+2 == demandantes.size()){
						convocante += " y ";
					}else if(i !=  demandantes.size()-1)
						convocante += ", ";
				}
			}else if(demandantes.size() == 1){
				convocante = demandantes.get(0).getNombreCompleto();
			}
			
			campo.put("convocante", convocante);
			
			List<Persona> demandados =	(List<Persona>) manejadorRolPersonaCaso.consultarPersonasPorRolCaso(notificacion.getIdCaso(), UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
			String convocadado = "";
			if(demandados.size() > 1){
				for (int i = 0; i < demandados.size(); i++) {
					convocadado += demandados.get(i).getNombreCompleto();
					if(i+2 == demandados.size()){
						convocadado += " y ";
					}else if(i !=  demandados.size()-1)
						convocadado += ", ";
				}
			}else if(demandados.size() == 1){
				convocadado = demandados.get(0).getNombreCompleto();
			}
			
			campo.put("convocadado", convocadado);
			campo.put("asunto", notificacion.getAsunto());
			campo.put("termino", notificacion.getTermino());
			campo.put("norma", notificacion.getNorma());
			campo.put("providencia", notificacion.getProvidencia());
			campo.put("fechaInicio", notificacion.getFechaInicio());
			campo.put("fechaFin", notificacion.getFechaFin());
			campo.put("fechaNotificacion",notificacion.getFechaFijacion());
			campo.put("tipoNotificacion", notificacion.getTipoNotificacion());
			campo.put("fechaFijacion", (new SimpleDateFormat("d MMMM yyyy",new Locale("es"))).format(notificacion.getFechaFijacion()));
			List<Persona> secretarios = (List<Persona>) manejadorRolPersonaCaso.consultarPersonasPorRolCaso(notificacion.getIdCaso(), UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);

			if(!secretarios.isEmpty()){
				campo.put("secretario", secretarios.get(0).getNombreCompleto());				
			}
			campo.put("idProvidencia", notificacion.getIdDocumento());
			listaReturn.add(campo);
		}
		
		return listaReturn;
	}
	
	public void crearOactualizarNotificacionDocumento(NotificacionDocumentoDTO notificacion){
		NotificacionDocumento notificacionDocumento = new NotificacionDocumento();
		//Se obtiene el usuario que hizo la modificación
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
//
//		if(appContext!= null && appContext.getContextoSesion() != null && appContext.getContextoSesion().getNombreUsuario() != null ){
//		usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
//		}
		if(notificacion != null){
			notificacionDocumento.setIdNotificacionDocumento(notificacion.getIdNotificacionDocumento());
			notificacionDocumento.setTipoNotificacion(notificacion.getTipoNotificacion());
			notificacionDocumento.setFechaFijacion(notificacion.getFechaFijacion());
			notificacionDocumento.setNorma(notificacion.getNorma());
			notificacionDocumento.setAsunto(notificacion.getAsunto());
			notificacionDocumento.setTermino(notificacion.getTermino());
			notificacionDocumento.setFechaInicio(notificacion.getFechaInicio());
			notificacionDocumento.setFechaFin(notificacion.getFechaFin());
			notificacionDocumento.setProvidencia(notificacion.getProvidencia());
			notificacionDocumento.setIdUsuarioModificacion(usuarioModificacion);
			notificacionDocumento.setFechaUltimaModificacion(new Date());
			notificacionDocumento.setEstadoRegistro(notificacion.getEstadoRegistro());
			notificacionDocumento.setIdDocumento(notificacion.getIdDocumento());
			notificacionDocumento.setIdCaso(notificacion.getIdCaso());
		}
		
		if(notificacion != null){
			if(this.buscar(notificacion.getIdNotificacionDocumento()) != null){
				actualizar(notificacionDocumento);
			}else{
				this.crear(notificacionDocumento);
			}
		}
	}
	
	public List<NotificacionDocumentoDTO> obtenerNofitificacionesPorCaso(Long idCaso){
		List<NotificacionDocumento> entidades =manejadorNotificacionDocumento.obtenerNofitificaciones(idCaso, null);
		List<NotificacionDocumentoDTO> listaDTO = new ArrayList<NotificacionDocumentoDTO>();
		for(NotificacionDocumento obj: entidades){
			listaDTO.add(this.transformarSinDependencias(obj));
		}
		return listaDTO;
	}
	public List<NotificacionDocumentoDTO> obtenerNofitificacionesPorId(Long idNotificacionDocumento){
		List<NotificacionDocumento> entidades =manejadorNotificacionDocumento.obtenerNofitificaciones(null,idNotificacionDocumento);
		List<NotificacionDocumentoDTO> listaDTO = new ArrayList<NotificacionDocumentoDTO>();
		for(NotificacionDocumento obj: entidades){
			listaDTO.add(this.transformarSinDependencias(obj));
		}
		return listaDTO;
	}
	
	// protected region metodos adicionales end
	
}
