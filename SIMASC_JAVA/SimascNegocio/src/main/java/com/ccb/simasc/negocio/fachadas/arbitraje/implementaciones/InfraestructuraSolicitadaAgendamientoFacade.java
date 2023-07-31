package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInfraestructuraSolicitadaAgendamientoFacade;
import com.ccb.simasc.transversal.dto.InfraestructuraSolicitadaAgendamientoDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamientoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link InfraestructuraSolicitadaAgendamiento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class InfraestructuraSolicitadaAgendamientoFacade extends AccesoFacade<InfraestructuraSolicitadaAgendamiento, InfraestructuraSolicitadaAgendamientoPK, InfraestructuraSolicitadaAgendamientoDTO> implements IInfraestructuraSolicitadaAgendamientoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorInfraestructuraSolicitadaAgendamiento manejadorInfraestructuraSolicitadaAgendamiento;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorInfraestructuraSolicitadaAgendamiento;
	}

	@Override
	public InfraestructuraSolicitadaAgendamientoDTO transformarSinDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamientoDTO dto = new InfraestructuraSolicitadaAgendamientoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSolicitadaAgendamientoDTO transformarConDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamientoDTO dto = new InfraestructuraSolicitadaAgendamientoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSolicitadaAgendamiento transformarEntidadConDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamiento dto = new InfraestructuraSolicitadaAgendamiento();
		dto = new InfraestructuraSolicitadaAgendamientoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSolicitadaAgendamiento transformarEntidadSinDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamiento dto = new InfraestructuraSolicitadaAgendamiento();
		dto = new InfraestructuraSolicitadaAgendamientoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
