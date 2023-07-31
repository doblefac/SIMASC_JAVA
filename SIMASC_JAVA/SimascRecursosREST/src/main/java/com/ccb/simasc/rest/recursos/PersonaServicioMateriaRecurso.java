package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaServicioMateriaFacade;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;

// protected region imports recurso end

/**
 * Recurso REST PersonaServicioMateria
 * @author sMartinez
 */
@Path( "personaserviciomateria" )
@Stateless
public class PersonaServicioMateriaRecurso {
	/**
    * REST resource logging 
    */
    private static final Class<PersonaServicioMateria> enClass= PersonaServicioMateria.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IPersonaServicioMateriaFacade personaServicioMateriaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

   // protected region metodos adicionales end


}
