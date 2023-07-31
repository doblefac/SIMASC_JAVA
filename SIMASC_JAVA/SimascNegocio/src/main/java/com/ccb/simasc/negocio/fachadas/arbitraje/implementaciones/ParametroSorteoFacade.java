package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroSorteo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroSorteoFacade;
import com.ccb.simasc.transversal.dto.ParametroSorteoDTO;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParametroSorteo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametroSorteoFacade extends AccesoFacade<ParametroSorteo, Long, ParametroSorteoDTO> implements IParametroSorteoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParametroSorteo manejadorParametroSorteo;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametroSorteo;
	}

	@Override
	public ParametroSorteoDTO transformarSinDependencias(ParametroSorteo obj) {
		ParametroSorteoDTO dto = new ParametroSorteoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParametroSorteoDTO transformarConDependencias(ParametroSorteo obj) {
		ParametroSorteoDTO dto = new ParametroSorteoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParametroSorteo transformarEntidadConDependencias(ParametroSorteo obj) {
		ParametroSorteo dto = new ParametroSorteo();
		dto = new ParametroSorteoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ParametroSorteo transformarEntidadSinDependencias(ParametroSorteo obj) {
		ParametroSorteo dto = new ParametroSorteo();
		dto = new ParametroSorteoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
