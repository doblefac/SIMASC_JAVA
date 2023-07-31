/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
//protected region imports fachada on begin

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaLote;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaLoteFacade;
import com.ccb.simasc.transversal.dto.PersonaLoteDTO;
import com.ccb.simasc.transversal.entidades.PersonaLote;
import com.ccb.simasc.transversal.entidades.PersonaLotePK;

//Escriba en esta seccion sus modificaciones

//protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link PersonaLote}
 * @author jnmartinez
 *
 */
@Stateless
@LocalBean
public class PersonaLoteFacade extends AccesoFacade<PersonaLote, PersonaLotePK, PersonaLoteDTO> implements IPersonaLoteFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada	
	private ManejadorPersonaLote manejadorPersonaLote;
	
	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorPersonaLote;
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade#transformarSinDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLoteDTO transformarSinDependencias(PersonaLote obj) {
		return new PersonaLoteDTO().transformarSinDependencias(obj);
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade#transformarEntidadConDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLote transformarEntidadConDependencias(PersonaLote obj) {
		return new PersonaLoteDTO().transformarEntidadConDependencias(obj);
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade#transformarEntidadSinDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLote transformarEntidadSinDependencias(PersonaLote obj) {
		return new PersonaLoteDTO().transformarEntidadSinDependencias(obj);
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade#transformarConDependencias(java.lang.Object)
	 */
	@Override
	public PersonaLoteDTO transformarConDependencias(PersonaLote obj) {
		return new PersonaLoteDTO().transformarConDependencias(obj);
	}
}
