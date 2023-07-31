package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSuspension;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSuspensionFacade;
import com.ccb.simasc.transversal.dto.PersonaSuspensionDTO;
import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.PersonaSuspensionPK;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link PersonaSuspension}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class PersonaSuspensionFacade extends AccesoFacade<PersonaSuspension, PersonaSuspensionPK, PersonaSuspensionDTO> implements IPersonaSuspensionFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorPersonaSuspension manejadorPersonaSuspension;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaSuspension;
	}

	@Override
	public PersonaSuspensionDTO transformarSinDependencias(PersonaSuspension obj) {
		PersonaSuspensionDTO dto = new PersonaSuspensionDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSuspensionDTO transformarConDependencias(PersonaSuspension obj) {
		PersonaSuspensionDTO dto = new PersonaSuspensionDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSuspension transformarEntidadConDependencias(PersonaSuspension obj) {
		PersonaSuspension dto = new PersonaSuspension();
		dto = new PersonaSuspensionDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public PersonaSuspension transformarEntidadSinDependencias(PersonaSuspension obj) {
		PersonaSuspension dto = new PersonaSuspension();
		dto = new PersonaSuspensionDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
	
}
