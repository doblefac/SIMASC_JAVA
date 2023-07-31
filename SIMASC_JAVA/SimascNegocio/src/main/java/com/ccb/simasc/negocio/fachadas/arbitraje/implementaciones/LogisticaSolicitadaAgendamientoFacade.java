package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorLogisticaSolicitadaAgendamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILogisticaSolicitadaAgendamientoFacade;
import com.ccb.simasc.transversal.dto.LogisticaSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamientoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link LogisticaSolicitadaAgendamiento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class LogisticaSolicitadaAgendamientoFacade extends AccesoFacade<LogisticaSolicitadaAgendamiento, LogisticaSolicitadaAgendamientoPK, LogisticaSolicitadaAgendamientoDTO> implements ILogisticaSolicitadaAgendamientoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorLogisticaSolicitadaAgendamiento manejadorLogisticaSolicitadaAgendamiento;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLogisticaSolicitadaAgendamiento;
	}

	@Override
	public LogisticaSolicitadaAgendamientoDTO transformarSinDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamientoDTO dto = new LogisticaSolicitadaAgendamientoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public LogisticaSolicitadaAgendamientoDTO transformarConDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamientoDTO dto = new LogisticaSolicitadaAgendamientoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public LogisticaSolicitadaAgendamiento transformarEntidadConDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamiento dto = new LogisticaSolicitadaAgendamiento();
		dto = new LogisticaSolicitadaAgendamientoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public LogisticaSolicitadaAgendamiento transformarEntidadSinDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamiento dto = new LogisticaSolicitadaAgendamiento();
		dto = new LogisticaSolicitadaAgendamientoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
