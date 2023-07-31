package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.NotificacionDTO;
import com.ccb.simasc.transversal.entidades.Notificacion;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Notificacion}
 * @author sMartinez
 *
 */
@Local
public interface INotificacionFacade extends IAccesoFacade<Notificacion, Long, NotificacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Genera la notificacion de la alerta dependiendo del estado 
	 * @param texto
	 * @param estado
	 * @param idAlerta
	 * @param idPersona
	 * @param fechaNotificacion
	 */
	public void generarLogAlerta(String texto, String estado, Long idAlerta, Long idPersona, Date fechaNotificacion);
	
	public void notificaVencimientoPronunciamientoarbitro(Long idCaso, Long idPersona);
	
	public void notificaUsuarioPlantilla(String nombrePlantilla, RolPersonaCaso rolPersonaCaso, String asunto);
	// protected region metodos adicionales end
	
}
