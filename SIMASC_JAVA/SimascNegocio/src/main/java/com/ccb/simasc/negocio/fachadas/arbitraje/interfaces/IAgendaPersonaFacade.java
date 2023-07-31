package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;
import java.util.Date;
import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AgendaPersonaDTO;
import com.ccb.simasc.transversal.entidades.AgendaPersona;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link AgendaPersona}
 * @author sMartinez
 *
 */
@Local
public interface IAgendaPersonaFacade extends IAccesoFacade<AgendaPersona, Long, AgendaPersonaDTO> {

	List<Persona> consultaFuncionariosAgenda(Long idCentro);

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	

	public void liberarAgendamientoPersona(Long idPersona, Long idAudiencia);
	
	/**
	 * bloquea el agendamiento de una persona
	 * @param idPersona
	 * @param estado
	 * @param tipoActividad
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idAudiencia
	 * @param observaciones
	 */
	public void bloquearAgendaPersona(Long idPersona, String estado, String tipoActividad, Date fechaInicio, Date fechaFin,
			Long idAudiencia,String observaciones);

	public void eliminarAgendaFechaPersona(Long idAgenda);

	/**
	 * Método para actualizar la agenda de un conciliador
	 * pasar el registro a validado y actualizar las observaciones
	 * @param agendaPersonaDTO
	 */
	public void actualizarAgendaPersona(AgendaPersonaDTO agendaPersonaDTO);
	
	/**
	 * Método para liberar el agendamiento de las personas asociadas al convenio
	 * @param idEventoCcb convenio para el cual se liberara la agenda
	 * @param personas lista de personas para las cuales se quiere liberar la agenda (opcional)
	 */
	public void liberarAgendaEvento(Long idEventoCcb, List<Long> personas);
	
	/**
	 * Método que realiza la que creación del evento para la agenda del conciliador 
	 * si no se presenta cruce con las fechas del evento y la agenda del conciliador
	 * @param agenda agenda a crear
	 */
	public void crearAgendaPersona(AgendaPersona agenda);
	
	/**
	 * Método para validar si existen conciliadores que no tengan disponibilidad para las fechas 
	 * @param personas
	 * @param fechaInicio
	 * @param fechaFin
	 * @return nombre de las personas que presentaron cruce en la agenda
	 */
	public String validarDisponibilidadPersonas(List<PersonaEventoCcb> personas, Date fechaInicio, Date fechaFin, Date fechaInicioExistente, Date fechaFinExistente);
	// protected region metodos adicionales end
	
}
