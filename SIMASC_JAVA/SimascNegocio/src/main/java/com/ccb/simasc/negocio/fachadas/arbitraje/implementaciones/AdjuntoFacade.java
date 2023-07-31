package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAdjunto;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAdjuntoFacade;
import com.ccb.simasc.transversal.dto.AdjuntoDTO;
import com.ccb.simasc.transversal.entidades.Adjunto;
import com.ccb.simasc.transversal.entidades.AdjuntoPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Adjunto}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AdjuntoFacade extends AccesoFacade<Adjunto, AdjuntoPK, AdjuntoDTO> implements IAdjuntoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorAdjunto manejadorAdjunto;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAdjunto;
	}

	@Override
	public AdjuntoDTO transformarSinDependencias(Adjunto obj) {
		AdjuntoDTO dto = new AdjuntoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AdjuntoDTO transformarConDependencias(Adjunto obj) {
		AdjuntoDTO dto = new AdjuntoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Adjunto transformarEntidadConDependencias(Adjunto obj) {
		Adjunto dto = new Adjunto();
		dto = new AdjuntoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Adjunto transformarEntidadSinDependencias(Adjunto obj) {
		Adjunto dto = new Adjunto();
		dto = new AdjuntoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
