package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.LogisticaSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamientoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link LogisticaSolicitadaAgendamiento}
 * @author sMartinez
 *
 */
@Local
public interface ILogisticaSolicitadaAgendamientoFacade extends IAccesoFacade<LogisticaSolicitadaAgendamiento, LogisticaSolicitadaAgendamientoPK, LogisticaSolicitadaAgendamientoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
