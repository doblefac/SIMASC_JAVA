package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParamEstadoArbitroPreseleccionDTO;
import com.ccb.simasc.transversal.entidades.ParamEstadoArbitroPreseleccion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParamEstadoArbitroPreseleccion}
 * @author sMartinez
 *
 */
@Local
public interface IParamEstadoArbitroPreseleccionFacade extends IAccesoFacade<ParamEstadoArbitroPreseleccion, String, ParamEstadoArbitroPreseleccionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
