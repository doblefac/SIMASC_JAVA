package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.TurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.TurnoJornada;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link TurnoJornada}
 * @author sMartinez
 *
 */
@Local
public interface ITurnoJornadaFacade extends IAccesoFacade<TurnoJornada, Long, TurnoJornadaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método que actualiza el turno de la audiencia para la jornada
	 * y libera el anterior
	 * @param idAudiencia
	 * @param idNuevoTurno
	 */
	public void actualizarTurnoAudiencia(Long idAudiencia, Long idNuevoTurno);
	
	/**
	 * Método que obtiene los turnos disponibles para una jornada
	 * @param idConvenio
	 * @return
	 */
	public List<TurnoJornadaDTO> consultarTurnosDisplonibles(Long idConvenio);
	
	/**
	 * Método para actualizar los turnos utilizados por una audiencia
	 * @param idAudiencia
	 * @param idTurno
	 * @param idTurnoConsecutivo
	 */
	public void ampliarTurno(Long idAudiencia, Long idTurno, Long idTurnoConsecutivo);
	
	/**
	 * Método para obtener los turnos disponibles y que sean consecutivos de una jornada
	 * @param idConvenio
	 * @return
	 */
	public List<TurnoJornadaDTO> consultarTurnosConsecutivos(Long idConvenio);
	
	/**
	 * Método para crear o actualizar un turno a un convenio de jornada
	 * @param turnos
	 */
	public void actualizarTurnoJornada(TurnoJornada turno);
	
	/**
	 * Método para obtener el listado de horas para los turnos de la jornada
	 * @param idConvenio
	 * @return
	 */
	public List<FormatoHoraAudienciaDTO> obtenerRangoHoras(Long idConvenio);
	// protected region metodos adicionales end
	
}
