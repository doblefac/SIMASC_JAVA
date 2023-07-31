package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.EventoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link EventoRolPersonaCaso}
 * @author sMartinez
 *
 */
@Local
public interface IEventoRolPersonaCasoFacade extends IAccesoFacade<EventoRolPersonaCaso, Long, EventoRolPersonaCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Metodo para crear un evento rol persona caso recibiendo los diferentes
	 * parametros
	 * 
	 * @param estadoAsignado
	 * @param motivoInactivacion
	 * @param fechaDeAsignacion
	 * @param estadoRegistro
	 * @param idRol
	 * @param idPersona
	 * @param idCaso
	 * @return EventoRolPersonaCaso
	 * @throws SIMASCNegocioExcepcion
	 */
	public EventoRolPersonaCaso crearEventoRolPersonaCaso(String estadoAsignado, String motivoInactivacion,
			Date fechaDeAsignacion, String estadoRegistro, Long idRol, Long idPersona, Long idCaso)
			throws SIMASCNegocioExcepcion;

	/**
	 * metodo encargado de traer loe eventos del una persona, filtrados por
	 * fecha y por un estado especifico
	 * 
	 * @param casosAsignadosDTO
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<CasosAsignadosDTO> eventoRpcAsignado(CasosAsignadosDTO casosAsignadosDTO) throws SIMASCNegocioExcepcion;

	/**
	 * Consulta el evento de comunicación de designación de un árbitro
	 * 
	 * @param idRol
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public EventoRolPersonaCaso consultarEventoComunicacionDesignacionArbitro(Long idRol, Long idPersona, Long idCaso);
		
	// protected region metodos adicionales end
	
}
