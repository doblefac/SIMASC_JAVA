package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDetallePagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetallePagoCasoFacade;
import com.ccb.simasc.transversal.dto.DetallePagoCasoDTO;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link DetallePagoCaso}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DetallePagoCasoFacade extends AccesoFacade<DetallePagoCaso, DetallePagoCasoPK, DetallePagoCasoDTO> implements IDetallePagoCasoFacade {
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@EJB
	private ManejadorDominio manejadorDominio;
	
	@EJB
	private ManejadorDetallePagoCaso manejadorDetallePagoCaso;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDetallePagoCaso;
	}

	@Override
	public DetallePagoCasoDTO transformarSinDependencias(DetallePagoCaso obj) {
		DetallePagoCasoDTO dto = new DetallePagoCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public DetallePagoCasoDTO transformarConDependencias(DetallePagoCaso obj) {
		DetallePagoCasoDTO dto = new DetallePagoCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public DetallePagoCaso transformarEntidadConDependencias(DetallePagoCaso obj) {
		DetallePagoCaso dto = new DetallePagoCaso();
		dto = new DetallePagoCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public DetallePagoCaso transformarEntidadSinDependencias(DetallePagoCaso obj) {
		DetallePagoCaso dto = new DetallePagoCaso();
		dto = new DetallePagoCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public List<DetallePagoCaso> obtenerDetallesPago(String numeroRecibo) {
		return (List<DetallePagoCaso>) transformarEntidadesColeccionSinDependencias(
				manejadorDetallePagoCaso.obtenerDetalles(numeroRecibo), new ArrayList<DetallePagoCaso>());
	}	
	// protected region metodos adicionales end
	
}
