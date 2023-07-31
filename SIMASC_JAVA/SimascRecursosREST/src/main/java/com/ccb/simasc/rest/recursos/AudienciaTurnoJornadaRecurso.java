package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaTurnoJornadaFacade;
import com.ccb.simasc.transversal.entidades.AudienciaTurnoJornada;

// protected region imports recurso end

/**
 * Recurso REST AudienciaTurnoJornada
 * @author sMartinez
 */
@Path( "audienciaturnojornada" )
@Stateless
public class AudienciaTurnoJornadaRecurso {
	/**
    * REST resource logging 
    */
    private static final Class<AudienciaTurnoJornada> enClass= AudienciaTurnoJornada.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IAudienciaTurnoJornadaFacade audienciaTurnoJornadaFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
