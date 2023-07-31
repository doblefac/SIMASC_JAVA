package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorClasificadorDominio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IClasificadorDominioFacade;
import com.ccb.simasc.transversal.dto.ClasificadorDominioDTO;
import com.ccb.simasc.transversal.entidades.ClasificadorDominio;
import com.ccb.simasc.transversal.entidades.ClasificadorDominioPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link ClasificadorDominio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ClasificadorDominioFacade extends AccesoFacade<ClasificadorDominio, ClasificadorDominioPK, ClasificadorDominioDTO> implements IClasificadorDominioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorClasificadorDominio manejadorClasificadorDominio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorClasificadorDominio;
	}

	@Override
	public ClasificadorDominioDTO transformarSinDependencias(ClasificadorDominio obj) {
		ClasificadorDominioDTO dto = new ClasificadorDominioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ClasificadorDominioDTO transformarConDependencias(ClasificadorDominio obj) {
		ClasificadorDominioDTO dto = new ClasificadorDominioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public ClasificadorDominio transformarEntidadConDependencias(ClasificadorDominio obj) {
		ClasificadorDominio dto = new ClasificadorDominio();
		dto = new ClasificadorDominioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public ClasificadorDominio transformarEntidadSinDependencias(ClasificadorDominio obj) {
		ClasificadorDominio dto = new ClasificadorDominio();
		dto = new ClasificadorDominioDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
