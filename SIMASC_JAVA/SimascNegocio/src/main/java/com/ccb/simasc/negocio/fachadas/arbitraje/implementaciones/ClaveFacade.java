package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorClave;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IClaveFacade;
import com.ccb.simasc.transversal.dto.ClaveDTO;
import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.ClavePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Clave}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ClaveFacade extends AccesoFacade<Clave, ClavePK, ClaveDTO> implements IClaveFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorClave manejadorClave;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorClave;
	}

	@Override
	public ClaveDTO transformarSinDependencias(Clave obj) {
		ClaveDTO dto = new ClaveDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ClaveDTO transformarConDependencias(Clave obj) {
		ClaveDTO dto = new ClaveDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Clave transformarEntidadConDependencias(Clave obj) {
		Clave dto = new Clave();
		dto = new ClaveDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Clave transformarEntidadSinDependencias(Clave obj) {
		Clave dto = new Clave();
		dto = new ClaveDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
