package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorClasificacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IClasificacionFacade;
import com.ccb.simasc.transversal.dto.ClasificacionDTO;
import com.ccb.simasc.transversal.entidades.Clasificacion;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Clasificacion}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ClasificacionFacade extends AccesoFacade<Clasificacion, Long, ClasificacionDTO> implements IClasificacionFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorClasificacion manejadorClasificacion;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorClasificacion;
	}

	@Override
	public ClasificacionDTO transformarSinDependencias(Clasificacion obj) {
		ClasificacionDTO dto = new ClasificacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ClasificacionDTO transformarConDependencias(Clasificacion obj) {
		ClasificacionDTO dto = new ClasificacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Clasificacion transformarEntidadConDependencias(Clasificacion obj) {
		Clasificacion dto = new Clasificacion();
		dto = new ClasificacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Clasificacion transformarEntidadSinDependencias(Clasificacion obj) {
		Clasificacion dto = new Clasificacion();
		dto = new ClasificacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
