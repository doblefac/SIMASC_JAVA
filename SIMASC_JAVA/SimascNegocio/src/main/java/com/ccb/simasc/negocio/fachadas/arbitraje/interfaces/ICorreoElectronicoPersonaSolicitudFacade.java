package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoPersonaSolicitudDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link CorreoElectronicoPersonaSolicitud}
 * @author sMartinez
 *
 */
@Local
public interface ICorreoElectronicoPersonaSolicitudFacade extends IAccesoFacade<CorreoElectronicoPersonaSolicitud, CorreoElectronicoPersonaSolicitudPK, CorreoElectronicoPersonaSolicitudDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los Correos asociados a un Persona Solicitud
	 * @param personaSolicitudPk
	 * @return
	 */
	public List<CorreoElectronico> consultarCorreosPorPersonaSolicitud(PersonaSolicitudPK personaSolicitudPk);
	
	/**
	 * Consulta las Personas Solicitud asociadas a un Correo
	 * @param idCorreo
	 * @return
	 */
	public List<PersonaSolicitud> consultarPersonasSolicitudPorCorreo(Long idCorreo);
	
	/**
	 * Crea un nuevo registro para CorreoElectronicoPersonaSolicitud a partir de las PKs de ambas entidades
	 * @param personaSolicitudPk
	 * @param idCorreo
	 */
	public CorreoElectronicoPersonaSolicitud crearCorreoElectronicoPersonaSolicitud(PersonaSolicitud personaSolicitudPk, CorreoElectronico correoElectronico);
	
	/**
	 * Crea un nuevo registro para CorreoElectronicoPersonaSolicitud a partir de la PK de PersonaSolicitud y una dirección de correo electronico,
	 * si el correo esta asociado a la Persona se toma el id asociado al mismo para el registro, en caso contrario se crea un nuevo registro 
	 * para CorreoElectronico, y se toma el id resultante para la creación de CorreoElectronicoPersonaSolicitud
	 * @param personaSolicitudPk
	 * @param direccionCorreoElectronico
	 */
	public CorreoElectronicoPersonaSolicitud crearCorreoElectronicoPersonaSolicitud(PersonaSolicitud personaSolicitudPk, String direccionCorreoElectronico, String tipoCorreo);
	
	/**
	 * Asocia una lista de Correos Electronicos a una Persona Solicitud
	 * @param correos
	 * @param personaSolicitud
	 */
	public void asociarCorreosElectronicosPersonaSolicitud(List<CorreoElectronicoDTO> correosElectronicos, PersonaSolicitud personaSolicitud);

	// protected region metodos adicionales end
	
}
