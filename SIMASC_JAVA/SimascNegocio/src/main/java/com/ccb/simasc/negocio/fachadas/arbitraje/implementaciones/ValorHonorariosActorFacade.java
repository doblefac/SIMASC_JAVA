package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorValorHonorariosActor;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IValorHonorariosActorFacade;
import com.ccb.simasc.transversal.dto.ValorHonorariosActorDTO;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActor;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActorPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ValorHonorariosActor}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ValorHonorariosActorFacade extends AccesoFacade<ValorHonorariosActor, ValorHonorariosActorPK, ValorHonorariosActorDTO> implements IValorHonorariosActorFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorValorHonorariosActor manejadorValorHonorariosActor;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorValorHonorariosActor;
	}

	@Override
	public ValorHonorariosActorDTO transformarSinDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActorDTO dto = new ValorHonorariosActorDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ValorHonorariosActorDTO transformarConDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActorDTO dto = new ValorHonorariosActorDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ValorHonorariosActor transformarEntidadConDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActor dto = new ValorHonorariosActor();
		dto = new ValorHonorariosActorDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ValorHonorariosActor transformarEntidadSinDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActor dto = new ValorHonorariosActor();
		dto = new ValorHonorariosActorDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
