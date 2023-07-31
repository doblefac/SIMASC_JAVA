package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link CorreoElectronico}
 * @author sMartinez
 *
 */
@Local
public interface ICorreoElectronicoFacade extends IAccesoFacade<CorreoElectronico, Long, CorreoElectronicoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Consulta los Correos Electronicos ACTIVOS asociados a una Persona
	 * @param idPersona
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona);
	
	/**
	 * Consulta los Correos Electronicos ACTIVOS o INACTIVOS asociados a una Persona
	 * @param idPersona
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos);
	
	/**
	 * Consulta los Correos Electronicos ACTIVOS o INACTIVOS asociados a una Persona,
	 * y que pertenezcan a un tipo determinado
	 * @param idPersona
	 * @param incluirInactivos
	 * @param tipo
	 * @return
	 */
	public List<CorreoElectronico> consultaCorreosPersona(Long idPersona, Boolean incluirInactivos, String tipo);

	/**
	 * Consulta los destinatarios asociados a un Caso
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<Map<String, String>> consultarDestinatarios(Long idCaso);

	/**
	 * Se actualizan los correos electronicos asociados a una persona
	 * 
	 * @param genericoDTOs
	 * @param contextoDeSesion
	 * @throws SIMASCNegocioExcepcion
	 */
	public void actualizarCorreosPersona(List<GenericoDTO> genericoDTOs, ContextoDeSesion contextoDeSesion);

	/**
	 * elimina logicamente los correos electronicos de una persona
	 * 
	 * @param idPersona
	 * @throws SIMASCNegocioExcepcion
	 */
	public void eliminarCorreosElectronicos(Long idPersona);

	/**
	 * Metodo encargado de crear los correos electronicos de una persona. Es
	 * opcional el envio de los parametros idPersona y idUbicacion ya que pueden
	 * estar contenidos dentro del list DTO corresosPersona.
	 * 
	 * ARB-F-108
	 * 
	 * @param correosElectronicosPersona
	 * @param idPersona
	 */
	public void crearCorreoElectronicoPersona(List<CorreoElectronicoDTO> correosElectronicosPersona, Long idPersona);

	void enviarCorreoSinPersistencia(List<String> destinos, String asunto, String contenido);
	
	/**
	 * Trae el primer correo principal de la persona.
	 * CON-C-006
	 * @param id_persona
	 * @return
	 */
	public CorreoElectronico traerPrimerCorreoPrimario(long idPersona);
	 
	/**
	 * Consulta los correos electronicos de una persona
	 * CON-C-013
	 * @param id_persona
	 * @return
	 */
	public List<CorreoElectronicoDTO> consultarCorreosPersona(Long idPersona);
	
	/**
	 * Método que realiza la creacion de un correo electronico
	 * @param correoDTO
	 * @return
	 */
	public CorreoElectronico crearCorreoElectronico(CorreoElectronicoDTO correoDTO);
	 // protected region metodos adicionales end
	
}
