package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaTipoServicio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHistoricoEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.transversal.dto.HistoricoEstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaTipoServicio;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link HistoricoEstadoPersonaTipoServicio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class HistoricoEstadoPersonaTipoServicioFacade extends AccesoFacade<HistoricoEstadoPersonaTipoServicio, Long, HistoricoEstadoPersonaTipoServicioDTO> implements IHistoricoEstadoPersonaTipoServicioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorHistoricoEstadoPersonaTipoServicio manejadorHistoricoEstadoPersonaTipoServicio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorHistoricoEstadoPersonaTipoServicio;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicioDTO transformarSinDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicioDTO dto = new HistoricoEstadoPersonaTipoServicioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicioDTO transformarConDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicioDTO dto = new HistoricoEstadoPersonaTipoServicioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicio transformarEntidadConDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicio dto = new HistoricoEstadoPersonaTipoServicio();
		dto = new HistoricoEstadoPersonaTipoServicioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public HistoricoEstadoPersonaTipoServicio transformarEntidadSinDependencias(HistoricoEstadoPersonaTipoServicio obj) {
		HistoricoEstadoPersonaTipoServicio dto = new HistoricoEstadoPersonaTipoServicio();
		dto = new HistoricoEstadoPersonaTipoServicioDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
