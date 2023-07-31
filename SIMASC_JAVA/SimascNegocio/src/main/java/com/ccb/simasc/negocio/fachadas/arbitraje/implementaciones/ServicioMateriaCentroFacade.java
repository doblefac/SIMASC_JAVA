package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioMateriaCentro;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioMateriaCentroFacade;
import com.ccb.simasc.transversal.dto.ServicioMateriaCentroDTO;
import com.ccb.simasc.transversal.entidades.ServicioMateriaCentro;
import com.ccb.simasc.transversal.entidades.ServicioMateriaCentroPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ServicioMateriaCentro}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ServicioMateriaCentroFacade extends AccesoFacade<ServicioMateriaCentro, ServicioMateriaCentroPK, ServicioMateriaCentroDTO> implements IServicioMateriaCentroFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorServicioMateriaCentro manejadorServicioMateriaCentro;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorServicioMateriaCentro;
	}

	@Override
	public ServicioMateriaCentroDTO transformarSinDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentroDTO dto = new ServicioMateriaCentroDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateriaCentroDTO transformarConDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentroDTO dto = new ServicioMateriaCentroDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateriaCentro transformarEntidadConDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentro dto = new ServicioMateriaCentro();
		dto = new ServicioMateriaCentroDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateriaCentro transformarEntidadSinDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentro dto = new ServicioMateriaCentro();
		dto = new ServicioMateriaCentroDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
