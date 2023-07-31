package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.ParametrosSedesRepartoDTO;
import com.ccb.simasc.transversal.dto.PersonaSedeDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.PersonaSede;
import com.ccb.simasc.transversal.entidades.PersonaSedePK;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaSede}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IPersonaSedeFacade extends IAccesoFacade<PersonaSede, PersonaSedePK, PersonaSedeDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Registra en el sistema la información de preferencia de sedes de una
	 * persona con rol Conciliador
	 * 
	 * @param personaSedeDTOs
	 */
	public void registrarSedesPreferenciaConciliador(List<PersonaSedeDTO> personaSedeDTOs);

	/**
	 * Obtiene todas las PersonaSede de una persona
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<PersonaSedeDTO> obtenerPersonaSedesPorPersona(Long idPersona);

	/**
	 * Metodo que retorna una lista de sedes correspondeintes a una persona y un
	 * tipo de sede
	 * 
	 * @param idPersona
	 * @param tipoSede
	 * @return
	 */
	public List<SedeDTO> obtenerSedesPorPersonaYTipoSede(Long idPersona, String tipoSede);

	/**
	 * Metodo que permite consultar las sedes correspondientes a los parametros
	 * enviados en la modificacion de parametros para reparto CONF103
	 * (idConciliador, fecha u horas).
	 * 
	 * @author aperez.
	 * 
	 * @param parametrosSedesReparto:
	 *            DTO con los filtros para consulta de las sedes.
	 * @return List<SedeDTO>. Lista de sedes de tipo SedeDTO.
	 */
	public List<SedeDTO> obtenerSedesPorParametros(ParametrosSedesRepartoDTO parametrosSedesReparto);
	
	/**
	 * Obtiene las sedes por centros
	 * @param centros
	 * @return
	 */
	public List<SedeDTO> obtenerSedesPorCentros(List<CentroDTO> centros);

	public List<SedeDTO> obtenerSedesPorPersonaYTipoSedeActivas(Long idPersona,
			String tipoSede, Long idServicio);

	// protected region metodos adicionales end

}
