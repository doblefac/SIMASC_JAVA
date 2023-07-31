package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorFechasCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechasCasoFacade;
import com.ccb.simasc.transversal.dto.FechasCasoDTO;
import com.ccb.simasc.transversal.entidades.FechasCaso;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link FechasCaso}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FechasCasoFacade extends AccesoFacade<FechasCaso, Long, FechasCasoDTO> implements IFechasCasoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorFechasCaso manejadorFechasCaso;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFechasCaso;
	}

	@Override
	public FechasCasoDTO transformarSinDependencias(FechasCaso obj) {
		FechasCasoDTO dto = new FechasCasoDTO();
		dto= dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FechasCasoDTO transformarConDependencias(FechasCaso obj) {
		FechasCasoDTO dto = new FechasCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FechasCaso transformarEntidadConDependencias(FechasCaso obj) {
		FechasCaso dto =new FechasCaso();
		dto = new FechasCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public FechasCaso transformarEntidadSinDependencias(FechasCaso obj) {
		FechasCaso dto = new FechasCaso();
		dto = new FechasCasoDTO().transformarEntidadSinDependencias(obj);;
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
