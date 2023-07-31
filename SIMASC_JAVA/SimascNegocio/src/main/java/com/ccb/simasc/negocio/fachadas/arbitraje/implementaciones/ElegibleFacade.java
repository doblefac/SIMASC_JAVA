package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorElegible;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IElegibleFacade;
import com.ccb.simasc.transversal.dto.ElegibleDTO;
import com.ccb.simasc.transversal.entidades.Elegible;
import com.ccb.simasc.transversal.entidades.ElegiblePK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Elegible}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class ElegibleFacade extends AccesoFacade<Elegible, ElegiblePK, ElegibleDTO> implements IElegibleFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorElegible manejadorElegible;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorElegible;
	}

	@Override
	public ElegibleDTO transformarSinDependencias(Elegible obj) {
		ElegibleDTO dto = new ElegibleDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public ElegibleDTO transformarConDependencias(Elegible obj) {
		ElegibleDTO dto = new ElegibleDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Elegible transformarEntidadConDependencias(Elegible obj) {
		Elegible dto = new Elegible();
		dto = new ElegibleDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Elegible transformarEntidadSinDependencias(Elegible obj) {
		Elegible dto = new Elegible();
		dto = new ElegibleDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	
}
