package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorArea;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAreaFacade;
import com.ccb.simasc.transversal.dto.AreaDTO;
import com.ccb.simasc.transversal.entidades.Area;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Area}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AreaFacade extends AccesoFacade<Area, Long, AreaDTO> implements IAreaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorArea manejadorArea;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorArea;
	}

	@Override
	public AreaDTO transformarSinDependencias(Area obj) {
		AreaDTO dto = new AreaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AreaDTO transformarConDependencias(Area obj) {
		AreaDTO dto = new AreaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Area transformarEntidadConDependencias(Area obj) {
		Area dto = new Area();
		dto = new AreaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Area transformarEntidadSinDependencias(Area obj) {
		Area dto = new Area();
		dto = new AreaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
