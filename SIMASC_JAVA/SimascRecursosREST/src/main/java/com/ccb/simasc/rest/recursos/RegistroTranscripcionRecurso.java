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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRegistroTranscripcionFacade;
import com.ccb.simasc.transversal.entidades.RegistroTranscripcion;

// protected region imports recurso end

/**
 * Recurso REST RegistroTranscripcion
 * @author sMartinez
 */
@Path( "registrotranscripcion" )
@Stateless
public class RegistroTranscripcionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(RegistroTranscripcionRecurso.class);
    private static final Class<RegistroTranscripcion> enClass= RegistroTranscripcion.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IRegistroTranscripcionFacade registroTranscripcionFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
