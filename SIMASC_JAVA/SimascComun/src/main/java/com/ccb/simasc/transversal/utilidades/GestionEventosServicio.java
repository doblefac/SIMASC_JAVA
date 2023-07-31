package com.ccb.simasc.transversal.utilidades;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;

/***
 * Clase responsable de la gestión de los diferentes eventos que ocurren para los casos
 * y sus acciones
 * 
 * @author fsandoval
 *
 */
@Stateless
@LocalBean
public class GestionEventosServicio {

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;
	
	public void registrarEvento(Caso caso, String tipoEvento, String mensaje, String userName){
		
		Evento evento = new Evento();
		
		evento.setCaso(caso);
		evento.setIdCaso(caso.getIdCaso());
		evento.setTipoEvento(tipoEvento);
		evento.setObservaciones(mensaje);
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setIdUsuarioModificacion(userName);
		evento.setFechaEvento(Calendar.getInstance().getTime());
		evento.setFechaUltimaModificacion(Calendar.getInstance().getTime());
		
		manejadorEvento.crear(evento);
	}

	public void registrarEventoRolPersonaCaso(String userName, String estadoAsignado, Date fechaDeAsignacion, Long idPersona, Long idRol, Long idCaso, RolPersonaCaso rolPersonaCaso){
		
		EventoRolPersonaCaso evento = new EventoRolPersonaCaso();
		
		evento.setEstadoAsignado(estadoAsignado);
		evento.setFechaDeAsignacion(fechaDeAsignacion);
		evento.setIdPersona(idPersona);
		evento.setIdCaso(idCaso);
		evento.setIdRol(idRol);
		evento.setRolPersonaCaso(rolPersonaCaso);
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setIdUsuarioModificacion(userName);
		evento.setFechaUltimaModificacion(Calendar.getInstance().getTime());
		
		manejadorEventoRolPersonaCaso.crear(evento);
	}
}
