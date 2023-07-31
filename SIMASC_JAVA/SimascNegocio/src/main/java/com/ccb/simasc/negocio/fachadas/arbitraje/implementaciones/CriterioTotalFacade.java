package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCriterioTotal;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICriterioTotalFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.CriterioTotalDTO;
import com.ccb.simasc.transversal.entidades.CriterioTotal;
import com.ccb.simasc.transversal.entidades.CriterioTotalPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link CriterioTotal}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CriterioTotalFacade extends AccesoFacade<CriterioTotal, CriterioTotalPK, CriterioTotalDTO> implements ICriterioTotalFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorCriterioTotal manejadorCriterioTotal;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCriterioTotal;
	}

	@Override
	public CriterioTotalDTO transformarSinDependencias(CriterioTotal obj) {
		CriterioTotalDTO dto = new CriterioTotalDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CriterioTotalDTO transformarConDependencias(CriterioTotal obj) {
		CriterioTotalDTO dto = new CriterioTotalDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CriterioTotal transformarEntidadConDependencias(CriterioTotal obj) {
		CriterioTotal dto = new CriterioTotal();
		dto = new CriterioTotalDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public CriterioTotal transformarEntidadSinDependencias(CriterioTotal obj) {
		CriterioTotal dto = new CriterioTotal();
		dto = new CriterioTotalDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
