package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPreseleccionado;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPreseleccionadoFacade;
import com.ccb.simasc.transversal.dto.PreseleccionadoDTO;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Preseleccionado}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PreseleccionadoFacade extends AccesoFacade<Preseleccionado, PreseleccionadoPK, PreseleccionadoDTO> implements IPreseleccionadoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPreseleccionado manejadorPreseleccionado;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPreseleccionado;
	}

	@Override
	public PreseleccionadoDTO transformarSinDependencias(Preseleccionado obj) {
		PreseleccionadoDTO dto = new PreseleccionadoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PreseleccionadoDTO transformarConDependencias(Preseleccionado obj) {
		PreseleccionadoDTO dto = new PreseleccionadoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Preseleccionado transformarEntidadConDependencias(Preseleccionado obj) {
		Preseleccionado dto = new Preseleccionado();
		dto = new PreseleccionadoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Preseleccionado transformarEntidadSinDependencias(Preseleccionado obj) {
		Preseleccionado dto = new Preseleccionado();
		dto = new PreseleccionadoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
