package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ClasificadorDominioDTO;
import com.ccb.simasc.transversal.entidades.ClasificadorDominio;
import com.ccb.simasc.transversal.entidades.ClasificadorDominioPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ClasificadorDominio}
 * @author sMartinez
 *
 */
@Local
public interface IClasificadorDominioFacade extends IAccesoFacade<ClasificadorDominio, ClasificadorDominioPK, ClasificadorDominioDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
