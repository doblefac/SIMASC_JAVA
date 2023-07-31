package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.InfraestructuraSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamientoPK;


// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link InfraestructuraSolicitadaAgendamiento}
 * @author sMartinez
 *
 */
@Local
public interface IInfraestructuraSolicitadaAgendamientoFacade extends IAccesoFacade<InfraestructuraSolicitadaAgendamiento, InfraestructuraSolicitadaAgendamientoPK, InfraestructuraSolicitadaAgendamientoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
