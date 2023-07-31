package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
import java.util.List;
import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AgendamientoDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.FechasAgendamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Agendamiento}
 * @author sMartinez
 *
 */
@Local
public interface IAgendamientoFacade extends IAccesoFacade<Agendamiento, Long, AgendamientoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	 /**
	  * Registra la reserva de la sala
	  * @param agendamientoDTO
	  * @return
	  */
	 public void registrarAgendamiento(AgendamientoDTO agendamientoDTO);
	 
	 /**
	  * 
	  * @param filtrosSalasDTO
	  * @return
	  * @throws SIMASCNegocioExcepcion
	  */
	public List<AgendamientoDTO> buscarAgendamientoFiltros(FiltrosSalasDTO filtrosSalasDTO) throws SIMASCNegocioExcepcion;
	/**
	 * Elimina el agendamiento con sus respectivos recursos y logistica solicitados 
	 * @param idAgendamiento
	 */
	public void cancelarAgendamiento(Long idAgendamiento);
	
	/**
	 * Actualiza el agendamiento de la reunion cambiando la sala
	 * @param agendamientoDTO
	 */
	public void actualizarAgendamiento(AgendamientoDTO agendamientoDTO) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método para consultar fechas y horas disponibles para el agendamiento
	 * @param consulta
	 * @return
	 */
	public List<FechasAgendamientoDTO> consultarAgendamiento(ConsultaAgendamientoDTO consulta);
	
	/**
	 * 
	 * @param idConciliador
	 * @param idCaso
	 * @param fecha
	 * @param horaInicio
	 * @param horaFin
	 * @param idSede
	 * @return
	 */
	public boolean validarDisponibilidadConciliador(Long idConciliador, Date fecha, Date horaInicio, Date horaFin, Long idSede);
	
	
	/**
	 * Metodo que valida la disponibilidad de la agenda para el conciliador.
	 * 
	 * @param idConciliador:
	 *            Identificador del conciliador.
	 * @param fecha:Dia
	 *            de la semana para verificar la disponibilidad del conciliador.
	 * @param horaInicio:
	 *            Hora inicial de la fecha.
	 * @param horaFin:
	 *            Hora final de la fecha.
	 * @return boolean: true si la persona esta disponible, de lo contrario
	 *         false.
	 */
	public boolean validarDisponibilidadAgenda(Long idConciliador, Date fecha, Date horaInicio, Date horaFin,
			Long idSede);
	
	/**
	 * Metodo que crea un agendamiento para una audiencia de conciliacion 
	 * el agendamiento lo hace en cualquier sala que se encuentre dispobible. 
	 * @author prendon
	 * @param idCaso
	 * @param idAudiencia
	 * @param idSede
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public Agendamiento crearAgendamientoAudienciaConciliacion( Long idCaso, Long idAudiencia, Long idSede, Date fechaInicio, Date fechaFin, String descipcion );
	
	// protected region metodos adicionales end
	
	
	
}
