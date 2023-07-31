package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechasCasoFacade;
import com.ccb.simasc.transversal.entidades.FechasCaso;

// protected region imports recurso end

/**
 * Recurso REST FechasCaso
 * @author sMartinez
 */
@Path( "fechascaso" )
@Stateless
public class FechasCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(FechasCasoRecurso.class);
    private static final Class<FechasCaso> enClass= FechasCaso.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IFechasCasoFacade fechasCasoFacade; 
	
	// protected region atributos end
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

   // protected region metodos adicionales end


}
