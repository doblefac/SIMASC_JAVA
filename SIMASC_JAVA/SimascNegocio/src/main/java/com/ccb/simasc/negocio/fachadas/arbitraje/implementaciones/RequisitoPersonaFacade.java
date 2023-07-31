package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorRequisitoPersona;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRequisitoPersonaFacade;
import com.ccb.simasc.transversal.dto.RequisitoPersonaDTO;
import com.ccb.simasc.transversal.entidades.RequisitoPersona;
import com.ccb.simasc.transversal.entidades.RequisitoPersonaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link RequisitoPersona}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RequisitoPersonaFacade extends AccesoFacade<RequisitoPersona, RequisitoPersonaPK, RequisitoPersonaDTO> implements IRequisitoPersonaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRequisitoPersona manejadorRequisitoPersona;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRequisitoPersona;
	}

	@Override
	public RequisitoPersonaDTO transformarSinDependencias(RequisitoPersona obj) {
		RequisitoPersonaDTO dto = new RequisitoPersonaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoPersonaDTO transformarConDependencias(RequisitoPersona obj) {
		RequisitoPersonaDTO dto = new RequisitoPersonaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoPersona transformarEntidadConDependencias(RequisitoPersona obj) {
		RequisitoPersona dto = new RequisitoPersona();
		dto = new RequisitoPersonaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoPersona transformarEntidadSinDependencias(RequisitoPersona obj) {
		RequisitoPersona dto = new RequisitoPersona();
		dto = new RequisitoPersonaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
