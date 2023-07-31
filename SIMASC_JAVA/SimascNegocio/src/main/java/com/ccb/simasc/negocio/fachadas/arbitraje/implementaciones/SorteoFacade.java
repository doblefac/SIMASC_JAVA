package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISorteoFacade;
import com.ccb.simasc.transversal.dto.SorteoDTO;
import com.ccb.simasc.transversal.entidades.Sorteo;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Sorteo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SorteoFacade extends AccesoFacade<Sorteo, Long, SorteoDTO> implements ISorteoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorSorteo manejadorSorteo;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSorteo;
	}

	@Override
	public SorteoDTO transformarSinDependencias(Sorteo obj) {
		SorteoDTO dto = new SorteoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SorteoDTO transformarConDependencias(Sorteo obj) {
		SorteoDTO dto = new SorteoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Sorteo transformarEntidadConDependencias(Sorteo obj) {
		Sorteo dto = new Sorteo();
		dto = new SorteoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Sorteo transformarEntidadSinDependencias(Sorteo obj) {
		Sorteo dto = new Sorteo();
		dto = new SorteoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
