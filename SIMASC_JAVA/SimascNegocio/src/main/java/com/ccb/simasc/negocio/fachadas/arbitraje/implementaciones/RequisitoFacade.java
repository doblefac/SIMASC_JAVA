package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorRequisito;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRequisitoFacade;
import com.ccb.simasc.transversal.dto.RequisitoDTO;
import com.ccb.simasc.transversal.entidades.Requisito;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Requisito}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class RequisitoFacade extends AccesoFacade<Requisito, Long, RequisitoDTO> implements IRequisitoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorRequisito manejadorRequisito;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorRequisito;
	}

	@Override
	public RequisitoDTO transformarSinDependencias(Requisito obj) {
		RequisitoDTO dto = new RequisitoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public RequisitoDTO transformarConDependencias(Requisito obj) {
		RequisitoDTO dto = new RequisitoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Requisito transformarEntidadConDependencias(Requisito obj) {
		Requisito dto = new Requisito();
		dto = new RequisitoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Requisito transformarEntidadSinDependencias(Requisito obj) {
		Requisito dto = new Requisito();
		dto = new RequisitoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
