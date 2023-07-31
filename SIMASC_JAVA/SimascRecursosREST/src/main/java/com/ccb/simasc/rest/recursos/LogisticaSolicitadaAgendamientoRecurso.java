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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILogisticaSolicitadaAgendamientoFacade;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;

// protected region imports recurso end

/**
 * Recurso REST LogisticaSolicitadaAgendamiento
 * @author sMartinez
 */
@Path( "logisticasolicitadaagendamiento" )
@Stateless
public class LogisticaSolicitadaAgendamientoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(LogisticaSolicitadaAgendamientoRecurso.class);
    private static final Class<LogisticaSolicitadaAgendamiento> enClass= LogisticaSolicitadaAgendamiento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ILogisticaSolicitadaAgendamientoFacade logisticaSolicitadaAgendamientoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
