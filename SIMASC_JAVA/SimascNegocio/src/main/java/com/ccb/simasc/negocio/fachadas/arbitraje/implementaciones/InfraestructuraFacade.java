package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructura;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInfraestructuraFacade;
import com.ccb.simasc.transversal.dto.InfraestructuraDTO;
import com.ccb.simasc.transversal.entidades.Infraestructura;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Infraestructura}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class InfraestructuraFacade extends AccesoFacade<Infraestructura, String, InfraestructuraDTO> implements IInfraestructuraFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorInfraestructura manejadorInfraestructura;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorInfraestructura;
	}

	@Override
	public InfraestructuraDTO transformarSinDependencias(Infraestructura obj) {
		InfraestructuraDTO dto = new InfraestructuraDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraDTO transformarConDependencias(Infraestructura obj) {
		InfraestructuraDTO dto = new InfraestructuraDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Infraestructura transformarEntidadConDependencias(Infraestructura obj) {
		Infraestructura dto = new Infraestructura();
		dto = new InfraestructuraDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Infraestructura transformarEntidadSinDependencias(Infraestructura obj) {
		Infraestructura dto = new Infraestructura();
		dto = new InfraestructuraDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
