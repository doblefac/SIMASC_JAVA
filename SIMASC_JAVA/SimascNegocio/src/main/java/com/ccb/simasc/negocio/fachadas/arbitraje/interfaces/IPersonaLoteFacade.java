/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaLoteDTO;
import com.ccb.simasc.transversal.entidades.PersonaLote;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;

/**
 * @author jnmartinez
 *
 */
@Local
public interface IPersonaLoteFacade extends IAccesoFacade<PersonaLote, PersonaLotePK, PersonaLoteDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
}
