package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorSedeConvenio;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeConvenioFacade;
import com.ccb.simasc.transversal.dto.SedeConvenioDTO;
import com.ccb.simasc.transversal.entidades.SedeConvenio;
import com.ccb.simasc.transversal.entidades.SedeConvenioPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link SedeConvenio}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class SedeConvenioFacade extends AccesoFacade<SedeConvenio, SedeConvenioPK, SedeConvenioDTO> implements ISedeConvenioFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorSedeConvenio manejadorSedeConvenio;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorSedeConvenio;
	}

	@Override
	public SedeConvenioDTO transformarSinDependencias(SedeConvenio obj) {
		SedeConvenioDTO dto = new SedeConvenioDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public SedeConvenioDTO transformarConDependencias(SedeConvenio obj) {
		SedeConvenioDTO dto = new SedeConvenioDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public SedeConvenio transformarEntidadConDependencias(SedeConvenio obj) {
		SedeConvenio dto = new SedeConvenio();
		dto = new SedeConvenioDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public SedeConvenio transformarEntidadSinDependencias(SedeConvenio obj) {
		SedeConvenio dto = new SedeConvenio();
		dto = new SedeConvenioDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
