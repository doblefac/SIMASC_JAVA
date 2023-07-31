package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenioCentro;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioCentroFacade;
import com.ccb.simasc.transversal.dto.ConvenioCentroDTO;
import com.ccb.simasc.transversal.entidades.ConvenioCentro;
import com.ccb.simasc.transversal.entidades.ConvenioCentroPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ConvenioCentro}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ConvenioCentroFacade extends AccesoFacade<ConvenioCentro, ConvenioCentroPK, ConvenioCentroDTO> implements IConvenioCentroFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorConvenioCentro manejadorConvenioCentro;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorConvenioCentro;
	}

	@Override
	public ConvenioCentroDTO transformarSinDependencias(ConvenioCentro obj) {
		ConvenioCentroDTO dto = new ConvenioCentroDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ConvenioCentroDTO transformarConDependencias(ConvenioCentro obj) {
		ConvenioCentroDTO dto = new ConvenioCentroDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ConvenioCentro transformarEntidadConDependencias(ConvenioCentro obj) {
		ConvenioCentro dto = new ConvenioCentro();
		dto = new ConvenioCentroDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ConvenioCentro transformarEntidadSinDependencias(ConvenioCentro obj) {
		ConvenioCentro dto = new ConvenioCentro();
		dto = new ConvenioCentroDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
