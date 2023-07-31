package com.ccb.simasc.negocio.transversal;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.Date;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.integracion.manejadores.ManejadorNotificacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.NotificacionDTO;

import com.ccb.simasc.transversal.entidades.Notificacion;

import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Notificacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class NotificacionFacade extends AccesoFacade<Notificacion, Long, NotificacionDTO>{
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorNotificacion manejadorNotificacion;
	
	@Inject
	private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorNotificacion;
	}

	@Override
	public NotificacionDTO transformarSinDependencias(Notificacion obj) {
		NotificacionDTO dto = new NotificacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public NotificacionDTO transformarConDependencias(Notificacion obj) {
		NotificacionDTO dto = new NotificacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Notificacion transformarEntidadConDependencias(Notificacion obj) {
		Notificacion dto = new Notificacion();
		dto = new NotificacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Notificacion transformarEntidadSinDependencias(Notificacion obj) {
		Notificacion dto = new Notificacion();
		dto = new NotificacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	
	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	

	public void generarLogAlerta(String texto, String estado, Long idAlerta, Long idPersona, Date fechaNotificacion){
		String idUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		
		Notificacion notificacion = new Notificacion();
		notificacion.setIdAlerta(idAlerta);
		notificacion.setIdPersona(idPersona);
		notificacion.setEstado(estado);
	

		notificacion.setTextoAlerta(texto);
		if(fechaNotificacion != null){
			notificacion.setFechaNotificacion(fechaNotificacion);
		}else{
			notificacion.setFechaNotificacion(new Date());	
		}
		notificacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		notificacion.setFechaUltimaModificacion(new Date());
		notificacion.setIdUsuarioModificacion(idUsuario);		
		manejadorNotificacion.crearSinAtributosAuditoria(notificacion);

	}
	
	
	// protected region metodos adicionales end
	
}
