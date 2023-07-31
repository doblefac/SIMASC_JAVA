package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAuditoriaSistemaExterno;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAuditoriaSistemaExternoFacade;
import com.ccb.simasc.transversal.dto.AuditoriaSistemaExternoDTO;
import com.ccb.simasc.transversal.entidades.AuditoriaSistemaExterno;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link AuditoriaSistemaExterno}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AuditoriaSistemaExternoFacade extends AccesoFacade<AuditoriaSistemaExterno, Long, AuditoriaSistemaExternoDTO> implements IAuditoriaSistemaExternoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAuditoriaSistemaExterno manejadorAuditoriaSistemaExterno;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAuditoriaSistemaExterno;
	}

	@Override
	public AuditoriaSistemaExternoDTO transformarSinDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExternoDTO dto = new AuditoriaSistemaExternoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AuditoriaSistemaExternoDTO transformarConDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExternoDTO dto = new AuditoriaSistemaExternoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public AuditoriaSistemaExterno transformarEntidadConDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExterno dto = new AuditoriaSistemaExterno();
		dto = new AuditoriaSistemaExternoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public AuditoriaSistemaExterno transformarEntidadSinDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExterno dto = new AuditoriaSistemaExterno();
		dto = new AuditoriaSistemaExternoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
