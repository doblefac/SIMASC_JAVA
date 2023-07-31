package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoZonaGeografica;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoZonaGeograficaFacade;
import com.ccb.simasc.transversal.dto.TipoZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.TipoZonaGeografica;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link TipoZonaGeografica}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TipoZonaGeograficaFacade extends AccesoFacade<TipoZonaGeografica, Integer, TipoZonaGeograficaDTO> implements ITipoZonaGeograficaFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTipoZonaGeografica manejadorTipoZonaGeografica;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTipoZonaGeografica;
	}

	@Override
	public TipoZonaGeograficaDTO transformarSinDependencias(TipoZonaGeografica obj) {
		TipoZonaGeograficaDTO dto = new TipoZonaGeograficaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TipoZonaGeograficaDTO transformarConDependencias(TipoZonaGeografica obj) {
		TipoZonaGeograficaDTO dto = new TipoZonaGeograficaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public TipoZonaGeografica transformarEntidadConDependencias(TipoZonaGeografica obj) {
		TipoZonaGeografica dto = new TipoZonaGeografica();
		dto = new TipoZonaGeograficaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public TipoZonaGeografica transformarEntidadSinDependencias(TipoZonaGeografica obj) {
		TipoZonaGeografica dto = new TipoZonaGeografica();
		dto = new TipoZonaGeograficaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
