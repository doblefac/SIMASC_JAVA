package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
//protected region imports interfaz fachada on begin
//Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;

//protected region imports interfaz fachada end

/**
 * Contrato que define las operaciones asociadas al tratamiento de la entidad {@link CorreoElectronicoRolPersonaCaso}
 * 
 * @author ftovar
 *
 */
@Local
public interface ICorreoElectronicoRolPersonaCasoFacade extends IAccesoFacade<CorreoElectronicoRolPersonaCaso, CorreoElectronicoRolPersonaCasoPK, CorreoElectronicoRolPersonaCasoDTO> {
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los Correos asociados a un Rol Presona Caso
	 * @param rolPersonaCaso
	 * @return
	 */
	public List<CorreoElectronico> consultarCorreosPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPk);
	
	/**
	 * Consulta los Roles Persona Caso asociados a un Correo
	 * @param idCorreo
	 * @return
	 */
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorCorreo(Long idCorreo);
	
	/**
	 * Crea un nuevo registro para CorreoElectronicoRolPersonaCaso a partir de las PKs de ambas entidades
	 * @param rolPersonaCasoPk
	 * @param idCorreo
	 */
	public CorreoElectronicoRolPersonaCaso crearCorreoElectronicoRolPersonaCaso(RolPersonaCaso rolPersonaCasoPk, CorreoElectronico correoElectronico);
	
	/**
	 * Crea un nuevo registro para CorreoElectronicoRolPersonaCaso a partir de la PK de RolPersonaCaso y una dirección de correo electronico,
	 * si el correo esta asociado a la Persona se toma el id asociado al mismo para el registro, en caso contrario se crea un nuevo registro 
	 * para CorreoElectronico, y se toma el id resultante para la creación de CorreoElectronicoRolPersonaCaso
	 * @param rolPersonaCasoPk
	 * @param direccionCorreoElectronico
	 * @param tipoCorreo
	 */
	public CorreoElectronicoRolPersonaCaso crearCorreoElectronicoRolPersonaCaso(RolPersonaCaso rolPersonaCasoPk, String direccionCorreoElectronico, String tipoCorreo);
	
	/**
	 * Asocia una lista de Correos Electronicos a un Rol Persona Caso 
	 * @param correos
	 * @param rolPersonaCaso
	 */
	public void asociarCorreosElectronicosRolPersonaCaso(List<CorreoElectronicoDTO> correosElectronicos, RolPersonaCaso rolPersonaCaso);
	
	// protected region metodos adicionales end
}
