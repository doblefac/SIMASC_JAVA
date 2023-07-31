package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoSedeFacade;
import com.ccb.simasc.transversal.entidades.TelefonoSede;

// protected region imports recurso end

/**
 * Recurso REST TelefonoSede
 * @author sMartinez
 */
@Path( "telefonosede" )
@Stateless
public class TelefonoSedeRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TelefonoSedeRecurso.class);
    private static final Class<TelefonoSede> enClass= TelefonoSede.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ITelefonoSedeFacade telefonoSedeFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

   // protected region metodos adicionales end


}
