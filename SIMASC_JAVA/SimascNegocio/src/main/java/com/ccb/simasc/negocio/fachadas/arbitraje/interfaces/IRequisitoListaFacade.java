package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.RequisitoListaDTO;
import com.ccb.simasc.transversal.entidades.RequisitoLista;
import com.ccb.simasc.transversal.entidades.RequisitoListaPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link RequisitoLista}
 * @author sMartinez
 *
 */
@Local
public interface IRequisitoListaFacade extends IAccesoFacade<RequisitoLista, RequisitoListaPK, RequisitoListaDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
