package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PersonaSuspensionDTO;
import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.PersonaSuspensionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link PersonaSuspension}
 * @author sMartinez
 *
 */
@Local
public interface IPersonaSuspensionFacade extends IAccesoFacade<PersonaSuspension, PersonaSuspensionPK, PersonaSuspensionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
