package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoRolPersonaCasoFacade;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;

// protected region imports recurso end

/**
 * Recurso REST CorreoElectronicoRolPersonaCaso
 * @author sMartinez
 */
@Path( "correoelectronicorolpersonacaso" )
@Stateless
public class CorreoElectronicoRolPersonaCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CorreoElectronicoRolPersonaCasoRecurso.class);
    private static final Class<CorreoElectronicoRolPersonaCaso> enClass= CorreoElectronicoRolPersonaCaso.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ICorreoElectronicoRolPersonaCasoFacade correoElectronicoRolPersonaCasoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
