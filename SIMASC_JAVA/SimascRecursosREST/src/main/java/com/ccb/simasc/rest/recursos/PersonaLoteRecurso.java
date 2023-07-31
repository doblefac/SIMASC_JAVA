/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.rest.recursos;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaLoteFacade;
import com.ccb.simasc.transversal.entidades.PersonaLote;

/**
 * @author jnmartinez
 *
 */
@Path("personalote")
@Stateless
public class PersonaLoteRecurso {

	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(PersonaLoteRecurso.class);
	private static final Class<PersonaLote> enClass = PersonaLote.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IPersonaLoteFacade personaLoteFacade;
	// protected region atributos end
	
	// protected region metodos on begin
	// Escriba en esta sección sus modificaciones
	
	// protected region metodos on end
}
