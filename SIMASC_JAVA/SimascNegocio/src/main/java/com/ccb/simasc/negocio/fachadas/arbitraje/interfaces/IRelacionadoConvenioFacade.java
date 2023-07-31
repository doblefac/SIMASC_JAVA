package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.RelacionadoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenioPK;

// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de
 * {@link RelacionadoConvenio}
 * 
 * @author sMartinez
 *
 */
@Local
public interface IRelacionadoConvenioFacade
		extends IAccesoFacade<RelacionadoConvenio, RelacionadoConvenioPK, RelacionadoConvenioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Metodo que trae los RelacionadoConvenio dado el id del Convenio y nombre
	 * del rol
	 * 
	 * @param idConvenio
	 * @param nombreRol
	 * @return
	 */
	public List<RelacionadoConvenioDTO> consultarRelacionadoConvenio(Long idConvenio, String nombreRol);

	/**
	 * Metodo que consulta los conciliadores relacionados por convennio.
	 * 
	 * @author aperez.
	 * 
	 * @param idConvenio:
	 *            Identificador del convenio.
	 * @return List<PersonaBasicaDTO>: Lista de conciliadores.
	 */
	public List<PersonaBasicaDTO> consultarConciliadoresRelacionadoConvenio(Long idConvenio);
	
	/**
	 * Método para realizar la asignacion de las personas a un convenio o jornada 
	 * @param personas: Lista de personas a asignar al convenio.
	 * @param idConvenio: Identificador del convenio
	 * @param codigoRol: tipo de rol, APOCON/CON.
	 */
	public void asignarPersonasConvenio(List<RelacionadoConvenio> personas,  String codigoRol, Long idConvenio);
	
	/**
	 * Método que inactiva la relacion de una persona a un convenio
	 * @param idPersona
	 * @param idConvenio
	 * @param estadoRegistro
	 * @param rol
	 */
	public void cambiarEstadoPersona(Long idPersona, Long idConvenio, String estadoRegistro, String rol);
	
	/**
	 * Método que consulta las personas relacionadas al convenio
	 * @param nombreRoles
	 * @param idConvenio
	 */
	public List<PersonaBasicaDTO> consultarPersonasRelacionadoConvenio(List<String> nombreRoles,Long idConvenio);
	// protected region metodos adicionales end

}
