package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorServicioMateria;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioMateriaFacade;
import com.ccb.simasc.transversal.dto.ServicioMateriaDTO;
import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.entidades.ServicioMateriaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ServicioMateria}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ServicioMateriaFacade extends AccesoFacade<ServicioMateria, ServicioMateriaPK, ServicioMateriaDTO> implements IServicioMateriaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorServicioMateria manejadorServicioMateria;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorServicioMateria;
	}

	@Override
	public ServicioMateriaDTO transformarSinDependencias(ServicioMateria obj) {
		ServicioMateriaDTO dto = new ServicioMateriaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateriaDTO transformarConDependencias(ServicioMateria obj) {
		ServicioMateriaDTO dto = new ServicioMateriaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateria transformarEntidadConDependencias(ServicioMateria obj) {
		ServicioMateria dto = new ServicioMateria();
		dto = new ServicioMateriaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ServicioMateria transformarEntidadSinDependencias(ServicioMateria obj) {
		ServicioMateria dto = new ServicioMateria();
		dto = new ServicioMateriaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
