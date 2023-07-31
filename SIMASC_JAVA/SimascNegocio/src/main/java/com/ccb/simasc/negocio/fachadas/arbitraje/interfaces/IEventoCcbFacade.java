package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.EventoCcbDTO;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link EventoCcb}
 * @author sMartinez
 *
 */
@Local
public interface IEventoCcbFacade extends IAccesoFacade<EventoCcb, Long, EventoCcbDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Método para obtener los eventos de la CCB que se encuentren entre dos fechas
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	public List<EventoCcbDTO> consultarEventos(Date fechaDesde, Date fechaHasta);
	
	/**
	 * Método que realiza el proceso de cancelar un evento de la CCB
	 * @param idEventoCcb
	 */
	public void cancelarEvento(Long idEventoCcb);
	
	/**
	 * Método para realizar la liberacion de la agenda de los conciliadores asociados al evento
	 * @param eventoCcb
	 * @param conciliadores lista de conciliadores a los que se liberara la agenda
	 * @param centros centros del usuario en sesión
	 * @param desasociar indicador de si se debe desasociar el conciliador del evento
	 * @return
	 */
	public boolean liberarAgendaEventoConciliadores(EventoCcb eventoCcb, List<PersonaEventoCcb> conciliadores, List<Long> centros, boolean desasociar);
	
	/**
	 * Método que obtiene el evento por el id
	 * y adiciona la lista de las personas que se encuentran asociadas al evento
	 * @param idEventoCcb
	 * @return
	 */
	public EventoCcb consultarEvento(Long idEventoCcb);
	
	/**
	 * Método para realizar la creacion o actualización de un evento programado por la ccb.
	 * Tambien se encarga de realizar las actualizaciones de los conciliadores asociados al evento
	 * y la notificacion de los cambios a los mismos
	 * @param evento
	 * @return arreglo en el que la primera posicion es el identificador del evento creado 
	 * 		y la siguiente false si el envio de las notificaciones se realizo correctamente
	 */
	public boolean actualizarEvento(EventoCcb evento, List<Long> centros);
	
	/**
	 * Método que realiza el proceso de registro del resultado de un evento programado por la CCB
	 * @param eventoCcb evento del cual se regitrara el resultado
	 */
	public void registrarResultadoEvento(EventoCcb eventoCcb);
	// protected region metodos adicionales end
	
}
