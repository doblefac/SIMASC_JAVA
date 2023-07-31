package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAreaAsuntoClasificacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAreaAsuntoClasificacionFacade;
import com.ccb.simasc.transversal.dto.AreaAsuntoClasificacionDTO;
import com.ccb.simasc.transversal.entidades.AreaAsuntoClasificacion;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link AreaAsuntoClasificacion}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AreaAsuntoClasificacionFacade
		extends AccesoFacade<AreaAsuntoClasificacion, Long, AreaAsuntoClasificacionDTO>
		implements IAreaAsuntoClasificacionFacade {

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada

	@EJB
	private ManejadorAreaAsuntoClasificacion manejadorAreaAsuntoClasificacion;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAreaAsuntoClasificacion;
	}

	@Override
	public AreaAsuntoClasificacionDTO transformarSinDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacionDTO dto = new AreaAsuntoClasificacionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AreaAsuntoClasificacionDTO transformarConDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacionDTO dto = new AreaAsuntoClasificacionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public AreaAsuntoClasificacion transformarEntidadConDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacion dto = new AreaAsuntoClasificacion();
		dto = new AreaAsuntoClasificacionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public AreaAsuntoClasificacion transformarEntidadSinDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacion dto = new AreaAsuntoClasificacion();
		dto = new AreaAsuntoClasificacionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	@Override
	public AreaAsuntoClasificacion consultarAreaAsuntoClasificacionPorCaso(Long idCaso) {
		return manejadorAreaAsuntoClasificacion.obtenerAreAsuntoClasificacionPorCaso(idCaso);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
