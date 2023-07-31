package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorRefrigerio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRefrigerioFacade;
import com.ccb.simasc.transversal.dto.RefrigerioDTO;
import com.ccb.simasc.transversal.entidades.Refrigerio;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Refrigerio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RefrigerioFacade extends AccesoFacade<Refrigerio, Integer, RefrigerioDTO> implements IRefrigerioFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRefrigerio manejadorRefrigerio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRefrigerio;
	}

	@Override
	public RefrigerioDTO transformarSinDependencias(Refrigerio obj) {
		RefrigerioDTO dto = new RefrigerioDTO();
		dto= dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RefrigerioDTO transformarConDependencias(Refrigerio obj) {
		RefrigerioDTO dto = new RefrigerioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Refrigerio transformarEntidadConDependencias(Refrigerio obj) {
		Refrigerio dto =new Refrigerio();
		dto = new RefrigerioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Refrigerio transformarEntidadSinDependencias(Refrigerio obj) {
		Refrigerio dto = new Refrigerio();
		dto = new RefrigerioDTO().transformarEntidadSinDependencias(obj);;
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
