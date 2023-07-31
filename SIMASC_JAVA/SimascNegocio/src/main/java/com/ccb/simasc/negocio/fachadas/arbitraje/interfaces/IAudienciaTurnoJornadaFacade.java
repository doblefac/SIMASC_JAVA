package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.AudienciaTurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornadaPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link AudienciaTurnoJornada}
 * @author sMartinez
 *
 */
@Local
public interface IAudienciaTurnoJornadaFacade extends IAccesoFacade<AudienciaTurnoJornada, AudienciaTurnoJornadaPK, AudienciaTurnoJornadaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * método para eliminar los turnos que tenga la audiencia para la jornada
	 * @param turnos
	 */
	public void liberarTurnos(List<AudienciaTurnoJornada> turnos);
	
	/**
	 * Método para crear el turno ocupado por la audiencia
	 * @param idAudiencia
	 * @param idTurnoJornada
	 * @return
	 */
	public AudienciaTurnoJornada ocuparTurno(Long idAudiencia, Long idTurnoJornada);
	
	/**
	 * Método para actualizar el estado del registro y los campos de auditoria del turno
	 * @param turno
	 * @param estado
	 */
	public void actualizarEstadoTurno(AudienciaTurnoJornada turno, String estado);
	// protected region metodos adicionales end
	
}
