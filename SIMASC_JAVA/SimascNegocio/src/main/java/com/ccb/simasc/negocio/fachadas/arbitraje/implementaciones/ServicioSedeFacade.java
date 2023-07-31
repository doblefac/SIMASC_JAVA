package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioSede;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioSedeFacade;
import com.ccb.simasc.transversal.dto.ServicioSedeDTO;
import com.ccb.simasc.transversal.entidades.ServicioSede;
import com.ccb.simasc.transversal.entidades.ServicioSedePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ServicioSede}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ServicioSedeFacade extends AccesoFacade<ServicioSede, ServicioSedePK, ServicioSedeDTO> implements IServicioSedeFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorServicioSede manejadorServicioSede;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorServicioSede;
	}

	@Override
	public ServicioSedeDTO transformarSinDependencias(ServicioSede obj) {
		ServicioSedeDTO dto = new ServicioSedeDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ServicioSedeDTO transformarConDependencias(ServicioSede obj) {
		ServicioSedeDTO dto = new ServicioSedeDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioSede transformarEntidadConDependencias(ServicioSede obj) {
		ServicioSede dto = new ServicioSede();
		dto = new ServicioSedeDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioSede transformarEntidadSinDependencias(ServicioSede obj) {
		ServicioSede dto = new ServicioSede();
		dto = new ServicioSedeDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
