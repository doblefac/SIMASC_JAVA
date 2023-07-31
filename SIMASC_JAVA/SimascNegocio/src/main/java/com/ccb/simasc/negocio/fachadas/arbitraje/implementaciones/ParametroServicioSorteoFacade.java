package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroServicioSorteoFacade;
import com.ccb.simasc.transversal.dto.ParametroServicioSorteoDTO;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ParametroServicioSorteo}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ParametroServicioSorteoFacade extends AccesoFacade<ParametroServicioSorteo, Long, ParametroServicioSorteoDTO> implements IParametroServicioSorteoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorParametroServicioSorteo;
	}

	@Override
	public ParametroServicioSorteoDTO transformarSinDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteoDTO dto = new ParametroServicioSorteoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ParametroServicioSorteoDTO transformarConDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteoDTO dto = new ParametroServicioSorteoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ParametroServicioSorteo transformarEntidadConDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteo dto = new ParametroServicioSorteo();
		dto = new ParametroServicioSorteoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ParametroServicioSorteo transformarEntidadSinDependencias(ParametroServicioSorteo obj) {
		ParametroServicioSorteo dto = new ParametroServicioSorteo();
		dto = new ParametroServicioSorteoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end


}
