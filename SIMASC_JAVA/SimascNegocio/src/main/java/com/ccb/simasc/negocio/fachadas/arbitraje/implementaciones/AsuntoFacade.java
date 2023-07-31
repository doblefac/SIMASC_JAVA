package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAsunto;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAsuntoFacade;
import com.ccb.simasc.transversal.dto.AsuntoDTO;
import com.ccb.simasc.transversal.entidades.Asunto;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Asunto}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AsuntoFacade extends AccesoFacade<Asunto, Long, AsuntoDTO> implements IAsuntoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAsunto manejadorAsunto;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAsunto;
	}

	@Override
	public AsuntoDTO transformarSinDependencias(Asunto obj) {
		AsuntoDTO dto = new AsuntoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AsuntoDTO transformarConDependencias(Asunto obj) {
		AsuntoDTO dto = new AsuntoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Asunto transformarEntidadConDependencias(Asunto obj) {
		Asunto dto = new Asunto();
		dto = new AsuntoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Asunto transformarEntidadSinDependencias(Asunto obj) {
		Asunto dto = new Asunto();
		dto = new AsuntoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
