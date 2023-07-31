package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorObligacionParte;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IObligacionParteFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.ObligacionParteDTO;
import com.ccb.simasc.transversal.entidades.ObligacionParte;
import com.ccb.simasc.transversal.entidades.ObligacionPartePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ObligacionParte}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ObligacionParteFacade extends AccesoFacade<ObligacionParte, ObligacionPartePK, ObligacionParteDTO> implements IObligacionParteFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorObligacionParte manejadorObligacionParte;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorObligacionParte;
	}

	@Override
	public ObligacionParteDTO transformarSinDependencias(ObligacionParte obj) {
		ObligacionParteDTO dto = new ObligacionParteDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ObligacionParteDTO transformarConDependencias(ObligacionParte obj) {
		ObligacionParteDTO dto = new ObligacionParteDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ObligacionParte transformarEntidadConDependencias(ObligacionParte obj) {
		ObligacionParte dto = new ObligacionParte();
		dto = new ObligacionParteDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ObligacionParte transformarEntidadSinDependencias(ObligacionParte obj) {
		ObligacionParte dto = new ObligacionParte();
		dto = new ObligacionParteDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
