package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructuraSala;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInfraestructuraSalaFacade;
import com.ccb.simasc.transversal.dto.InfraestructuraSalaDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.InfraestructuraSalaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link InfraestructuraSala}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class InfraestructuraSalaFacade extends AccesoFacade<InfraestructuraSala, InfraestructuraSalaPK, InfraestructuraSalaDTO> implements IInfraestructuraSalaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorInfraestructuraSala manejadorInfraestructuraSala;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorInfraestructuraSala;
	}

	@Override
	public InfraestructuraSalaDTO transformarSinDependencias(InfraestructuraSala obj) {
		InfraestructuraSalaDTO dto = new InfraestructuraSalaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSalaDTO transformarConDependencias(InfraestructuraSala obj) {
		InfraestructuraSalaDTO dto = new InfraestructuraSalaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSala transformarEntidadConDependencias(InfraestructuraSala obj) {
		InfraestructuraSala dto = new InfraestructuraSala();
		dto = new InfraestructuraSalaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public InfraestructuraSala transformarEntidadSinDependencias(InfraestructuraSala obj) {
		InfraestructuraSala dto = new InfraestructuraSala();
		dto = new InfraestructuraSalaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
