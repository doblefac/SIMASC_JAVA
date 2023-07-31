package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.MotivoDevolucionDTO;
import com.ccb.simasc.transversal.entidades.MotivoDevolucion;
import com.ccb.simasc.transversal.entidades.MotivoDevolucionPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link MotivoDevolucion}
 * @author sMartinez
 *
 */
@Local
public interface IMotivoDevolucionFacade extends IAccesoFacade<MotivoDevolucion, MotivoDevolucionPK, MotivoDevolucionDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
