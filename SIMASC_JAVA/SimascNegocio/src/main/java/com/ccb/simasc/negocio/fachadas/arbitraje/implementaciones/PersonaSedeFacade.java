package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSedeFacade;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.ParametrosSedesRepartoDTO;
import com.ccb.simasc.transversal.dto.PersonaSedeDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.PersonaSede;
import com.ccb.simasc.transversal.entidades.PersonaSedePK;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PersonaSede}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaSedeFacade extends AccesoFacade<PersonaSede, PersonaSedePK, PersonaSedeDTO>
		implements IPersonaSedeFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorPersonaSede manejadorPersonaSede;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaSede;
	}

	@Override
	public PersonaSedeDTO transformarSinDependencias(PersonaSede obj) {
		PersonaSedeDTO dto = new PersonaSedeDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSedeDTO transformarConDependencias(PersonaSede obj) {
		PersonaSedeDTO dto = new PersonaSedeDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSede transformarEntidadConDependencias(PersonaSede obj) {
		PersonaSede dto = new PersonaSede();
		dto = new PersonaSedeDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSede transformarEntidadSinDependencias(PersonaSede obj) {
		PersonaSede dto = new PersonaSede();
		dto = new PersonaSedeDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSedeFacade#
	 * registrarSedesPreferenciaConciliador(java.util.List)
	 */
	@Override
	public void registrarSedesPreferenciaConciliador(List<PersonaSedeDTO> personaSedeDTOs) {
		Long idPersona = personaSedeDTOs.get(0).getPersonaSedePK().getIdPersona();

		// 1. Consulta todas las persona sede de la persona (conciliador)
		List<PersonaSede> personaSedes = manejadorPersonaSede.obtenerPersonaSedesPorPersona(idPersona);

		// 2. Elimina toda la infromación actual de PersonaSede asociada a la
		// persona (conciliador)
		for (PersonaSede personaSede : personaSedes) {
			manejadorPersonaSede.eliminar(personaSede);
		}

		// 3. Crea la nueva información de preferencia de sedes de la persona
		// (conciliador)
		for (PersonaSedeDTO personaSedeDTO : personaSedeDTOs) {
			PersonaSede personaSede = new PersonaSede();
			PersonaSedePK personaSedePK = new PersonaSedePK();
			personaSedePK.setIdPersona(personaSedeDTO.getPersonaSedePK().getIdPersona());
			personaSedePK.setIdSede(personaSedeDTO.getPersonaSedePK().getIdSede());
			personaSede.setPersonaSedePK(personaSedePK);
			personaSede.setEstadoRegistro(personaSedeDTO.getEstadoRegistro());

			manejadorPersonaSede.crear(personaSede);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSedeFacade#
	 * obtenerPersonaSedesPorPersona(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaSedeDTO> obtenerPersonaSedesPorPersona(Long idPersona) {
		return (List<PersonaSedeDTO>) new PersonaSedeDTO().transformarColeccionEntidadesSinDependencias(
				manejadorPersonaSede.obtenerPersonaSedesPorPersona(idPersona));
	}

	@Override
	public List<SedeDTO> obtenerSedesPorPersonaYTipoSede(Long idPersona, String tipoSede) {
		return (List<SedeDTO>) new SedeDTO().transformarColeccionSinDependencias(
				manejadorPersonaSede.obtenerSedesPorPersonaYTipoSede(idPersona, tipoSede));
	}
	
	@Override
	public List<SedeDTO> obtenerSedesPorPersonaYTipoSedeActivas(Long idPersona, String tipoSede,Long idServicio) {
		return (List<SedeDTO>) new SedeDTO().transformarColeccionSinDependencias(
				manejadorPersonaSede.obtenerSedesPorPersonaYTipoSedeActivas(idPersona, tipoSede, idServicio));
	}
	

	/**
	 * Metodo que permite filtrar las sedes disponibles tanto para una lista de
	 * horas de audiencia o para una hora de audiencia especifica.
	 * 
	 * @author aperez
	 * @param parametrosSedesReparto:
	 *            DTO que contiene los filtros para la consulta de las sedes.
	 * @return List<SedeDto>: Lista de sedes disponibles.
	 */
	@Override
	public List<SedeDTO> obtenerSedesPorParametros(ParametrosSedesRepartoDTO parametrosSedesReparto) {
		List<FormatoHoraAudienciaDTO> turnos = parametrosSedesReparto.getListaHoras();
		List<SedeDTO> listaSedes = new ArrayList<SedeDTO>();
		Set<SedeDTO> tempSedes = new LinkedHashSet<SedeDTO>();
		if (turnos != null && !turnos.isEmpty()) {
			for (FormatoHoraAudienciaDTO turno : turnos) {
				parametrosSedesReparto.setFormatoHora(turno);
				List<SedeDTO> sedesDisponibles = manejadorPersonaSede.obtenerSedesPorParametros(parametrosSedesReparto);
				tempSedes.addAll(sedesDisponibles);
			}
			listaSedes.addAll(tempSedes);	
		} else {
			listaSedes = manejadorPersonaSede.obtenerSedesPorParametros(parametrosSedesReparto);
		}
		return listaSedes;
	}

	@Override
	public List<SedeDTO> obtenerSedesPorCentros(List<CentroDTO> centros) {
		List<Long> idCentros = new ArrayList<>();
		for (CentroDTO centro: centros){
			idCentros.add(centro.getIdCentro());
		}
		return (List<SedeDTO>) new SedeDTO().transformarColeccionEntidadesSinDependencias(manejadorPersonaSede.obtenerSedesPorCentros(idCentros));
	}
	
	
	// protected region metodos adicionales end

}
