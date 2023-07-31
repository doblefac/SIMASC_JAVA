package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ConvenioCentroDTO;
import com.ccb.simasc.transversal.entidades.ConvenioCentro;
import com.ccb.simasc.transversal.entidades.ConvenioCentroPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ConvenioCentro}
 * @author sMartinez
 *
 */
@Local
public interface IConvenioCentroFacade extends IAccesoFacade<ConvenioCentro, ConvenioCentroPK, ConvenioCentroDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
