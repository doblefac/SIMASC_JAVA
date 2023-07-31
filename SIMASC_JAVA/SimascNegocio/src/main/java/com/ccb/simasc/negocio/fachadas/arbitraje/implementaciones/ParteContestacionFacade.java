package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParteContestacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParteContestacionFacade;
import com.ccb.simasc.transversal.dto.ParteContestacionDTO;
import com.ccb.simasc.transversal.entidades.ParteContestacion;
import com.ccb.simasc.transversal.entidades.ParteContestacionPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParteContestacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParteContestacionFacade extends AccesoFacade<ParteContestacion, ParteContestacionPK, ParteContestacionDTO> implements IParteContestacionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParteContestacion manejadorParteContestacion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParteContestacion;
	}

	@Override
	public ParteContestacionDTO transformarSinDependencias(ParteContestacion obj) {
		ParteContestacionDTO dto = new ParteContestacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParteContestacionDTO transformarConDependencias(ParteContestacion obj) {
		ParteContestacionDTO dto = new ParteContestacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParteContestacion transformarEntidadConDependencias(ParteContestacion obj) {
		ParteContestacion dto = new ParteContestacion();
		dto = new ParteContestacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ParteContestacion transformarEntidadSinDependencias(ParteContestacion obj) {
		ParteContestacion dto = new ParteContestacion();
		dto = new ParteContestacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
