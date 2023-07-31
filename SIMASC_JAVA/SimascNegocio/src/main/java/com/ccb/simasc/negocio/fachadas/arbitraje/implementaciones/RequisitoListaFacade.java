package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorRequisitoLista;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRequisitoListaFacade;
import com.ccb.simasc.transversal.dto.RequisitoListaDTO;
import com.ccb.simasc.transversal.entidades.RequisitoLista;
import com.ccb.simasc.transversal.entidades.RequisitoListaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link RequisitoLista}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RequisitoListaFacade extends AccesoFacade<RequisitoLista, RequisitoListaPK, RequisitoListaDTO> implements IRequisitoListaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRequisitoLista manejadorRequisitoLista;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRequisitoLista;
	}

	@Override
	public RequisitoListaDTO transformarSinDependencias(RequisitoLista obj) {
		RequisitoListaDTO dto = new RequisitoListaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoListaDTO transformarConDependencias(RequisitoLista obj) {
		RequisitoListaDTO dto = new RequisitoListaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoLista transformarEntidadConDependencias(RequisitoLista obj) {
		RequisitoLista dto = new RequisitoLista();
		dto = new RequisitoListaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoLista transformarEntidadSinDependencias(RequisitoLista obj) {
		RequisitoLista dto = new RequisitoLista();
		dto = new RequisitoListaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
