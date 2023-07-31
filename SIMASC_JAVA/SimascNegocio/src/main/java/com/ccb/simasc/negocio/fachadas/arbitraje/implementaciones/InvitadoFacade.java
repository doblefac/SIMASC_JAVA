package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorInvitado;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInvitadoFacade;
import com.ccb.simasc.transversal.dto.InvitadoDTO;
import com.ccb.simasc.transversal.entidades.Invitado;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Invitado}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class InvitadoFacade extends AccesoFacade<Invitado, Long, InvitadoDTO> implements IInvitadoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorInvitado manejadorInvitado;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorInvitado;
	}

	@Override
	public InvitadoDTO transformarSinDependencias(Invitado obj) {
		return new InvitadoDTO().transformarSinDependencias(obj);
	}

	@Override
	public InvitadoDTO transformarConDependencias(Invitado obj) {
		return new InvitadoDTO().transformarConDependencias(obj);
	}

	@Override
	public Invitado transformarEntidadConDependencias(Invitado obj) {
		return new InvitadoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Invitado transformarEntidadSinDependencias(Invitado obj) {
		return new InvitadoDTO().transformarEntidadSinDependencias(obj);
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
