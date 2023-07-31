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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoPersonaSolicitudFacade;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;

// protected region imports recurso end

/**
 * Recurso REST CorreoElectronicoPersonaSolicitud
 * @author sMartinez
 */
@Path( "correoelectronicopersonasolicitud" )
@Stateless
public class CorreoElectronicoPersonaSolicitudRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CorreoElectronicoPersonaSolicitudRecurso.class);
    private static final Class<CorreoElectronicoPersonaSolicitud> enClass= CorreoElectronicoPersonaSolicitud.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ICorreoElectronicoPersonaSolicitudFacade correoElectronicoPersonaSolicitudFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
