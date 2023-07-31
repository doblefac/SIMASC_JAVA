package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CriterioTotalDTO;
import com.ccb.simasc.transversal.entidades.CriterioTotal;
import com.ccb.simasc.transversal.entidades.CriterioTotalPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link CriterioTotal}
 * @author sMartinez
 *
 */
@Local
public interface ICriterioTotalFacade extends IAccesoFacade<CriterioTotal, CriterioTotalPK, CriterioTotalDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
