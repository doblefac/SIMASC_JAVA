package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroServicioSorteoFacade;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;

// protected region imports recurso end

/**
 * Recurso REST ParametroServicioSorteo
 * @author sMartinez
 */
@Path( "parametroserviciosorteo" )
@Stateless
public class ParametroServicioSorteoRecurso {
	/**
    * REST resource logging 
    */
    private static final Class<ParametroServicioSorteo> enClass= ParametroServicioSorteo.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IParametroServicioSorteoFacade parametroServicioSorteoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
