package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParteContestacionDTO;
import com.ccb.simasc.transversal.entidades.ParteContestacion;
import com.ccb.simasc.transversal.entidades.ParteContestacionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link ParteContestacion}
 * @author sMartinez
 *
 */
@Local
public interface IParteContestacionFacade extends IAccesoFacade<ParteContestacion, ParteContestacionPK, ParteContestacionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
