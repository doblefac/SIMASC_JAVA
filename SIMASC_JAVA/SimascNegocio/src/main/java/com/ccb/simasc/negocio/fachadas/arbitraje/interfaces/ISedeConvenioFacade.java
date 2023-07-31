package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.SedeConvenioDTO;
import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.entidades.SedeConvenioPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link SedeConvenio}
 * @author sMartinez
 *
 */
@Local
public interface ISedeConvenioFacade extends IAccesoFacade<SedeConvenio, SedeConvenioPK, SedeConvenioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
