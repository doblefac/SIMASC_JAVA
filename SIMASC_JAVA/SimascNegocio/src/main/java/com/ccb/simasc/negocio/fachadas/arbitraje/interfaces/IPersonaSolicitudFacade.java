package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaSolicitud}
 * @author sMartinez
 *
 */
@Local
public interface IPersonaSolicitudFacade extends IAccesoFacade<PersonaSolicitud, PersonaSolicitudPK, PersonaSolicitudDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Método encargado de obtener las partes asociadas a una Solicitud de
	 * Servicio
	 *
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<FormularioParteDTO> consultarPartesSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de realizar una eliminacion logica de los registros
	 * asociados a una parte en una Solicitud de Servicio.
	 * 
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona) throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de obtener la informacion de una parte asociada a la
	 * Solcitud de un Servicio
	 * 
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteDTO consultarParteSolicitudServicio(Long idSolicitudServicio, Long idPersona)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Genera el rol persona caso a partir de una lista de persona solicitud
	 * @param personasSolicitud
	 * @param idUsuario
	 * @param idCaso
	 */

	public void migracionPersonaSolicitudARolPersonaCaso(List<PersonaSolicitud> personasSolicitud, String idUsuario, Long idCaso)
			throws SIMASCNegocioExcepcion;


	// protected region metodos adicionales end
	
	
	/**
	 * Método encargado de obtener las partes asociadas a una Solicitud de
	 * Servicio enmascarando los datos sensibles de la persona
	 *
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 * @throws SIMASCNegocioExcepcion
	 */
	public List<FormularioParteEnmascaradoDTO> consultarPartesEnmascarandoSolicitudServicio(Long idSolicitudServicio)
			throws SIMASCNegocioExcepcion;
	
	/**
	 * Método encargado de obtener la informacion de una parte asociada a la
	 * Solcitud de un Servicio enmascarando los datos sensibles de la persona
	 * 
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @return FormularioParteDTO
	 * @throws SIMASCNegocioExcepcion
	 */
	public FormularioParteEnmascaradoDTO consultarParteEnmascaradaSolicitudServicio(Long idSolicitudServicio, Long idPersona)
			throws SIMASCNegocioExcepcion;
}
