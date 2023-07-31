package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorLogistica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILogisticaFacade;
import com.ccb.simasc.transversal.dto.LogisticaDTO;
import com.ccb.simasc.transversal.entidades.Logistica;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Logistica}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class LogisticaFacade extends AccesoFacade<Logistica, String, LogisticaDTO> implements ILogisticaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorLogistica manejadorLogistica;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorLogistica;
	}

	@Override
	public LogisticaDTO transformarSinDependencias(Logistica obj) {
		LogisticaDTO dto = new LogisticaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public LogisticaDTO transformarConDependencias(Logistica obj) {
		LogisticaDTO dto = new LogisticaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Logistica transformarEntidadConDependencias(Logistica obj) {
		Logistica dto = new Logistica();
		dto = new LogisticaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Logistica transformarEntidadSinDependencias(Logistica obj) {
		Logistica dto = new Logistica();
		dto = new LogisticaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
