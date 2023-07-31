package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.PreseleccionadoDTO;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Preseleccionado}
 * @author sMartinez
 *
 */
@Local
public interface IPreseleccionadoFacade extends IAccesoFacade<Preseleccionado, PreseleccionadoPK, PreseleccionadoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
