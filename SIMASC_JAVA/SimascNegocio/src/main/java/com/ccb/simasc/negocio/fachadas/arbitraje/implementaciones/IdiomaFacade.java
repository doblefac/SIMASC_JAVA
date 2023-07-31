package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorIdioma;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IIdiomaFacade;
import com.ccb.simasc.transversal.dto.IdiomaDTO;
import com.ccb.simasc.transversal.entidades.Idioma;
import com.ccb.simasc.transversal.entidades.IdiomaPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Idioma}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class IdiomaFacade extends AccesoFacade<Idioma, IdiomaPK, IdiomaDTO> implements IIdiomaFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorIdioma manejadorIdioma;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorIdioma;
	}

	@Override
	public IdiomaDTO transformarSinDependencias(Idioma obj) {
		IdiomaDTO dto = new IdiomaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public IdiomaDTO transformarConDependencias(Idioma obj) {
		IdiomaDTO dto = new IdiomaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Idioma transformarEntidadConDependencias(Idioma obj) {
		Idioma dto = new Idioma();
		dto = new IdiomaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Idioma transformarEntidadSinDependencias(Idioma obj) {
		Idioma dto = new Idioma();
		dto = new IdiomaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
