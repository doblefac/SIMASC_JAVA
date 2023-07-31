package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaEventoCcbDTO;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcbPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaEventoCcb}
 * @author sMartinez
 *
 */
@Local
public interface IPersonaEventoCcbFacade extends IAccesoFacade<PersonaEventoCcb, PersonaEventoCcbPK, PersonaEventoCcbDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Método que realiza la actualizacion de la asociacion de una persona a un evento programado por la ccb
	 * @param idEventoCcb
	 * @param idPersona
	 * @param estado
	 * @param estadoRegistro
	 */
	public void actualizarAsociacion(Long idEventoCcb, Long idPersona, String estado, String estadoRegistro);
	// protected region metodos adicionales end
	
}
