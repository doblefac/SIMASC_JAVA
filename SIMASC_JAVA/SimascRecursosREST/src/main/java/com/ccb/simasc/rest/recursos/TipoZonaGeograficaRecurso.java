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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoZonaGeograficaFacade;
import com.ccb.simasc.transversal.entidades.TipoZonaGeografica;

// protected region imports recurso end

/**
 * Recurso REST TipoZonaGeografica
 * @author sMartinez
 */
@Path( "tipozonageografica" )
@Stateless
public class TipoZonaGeograficaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TipoZonaGeograficaRecurso.class);
    private static final Class<TipoZonaGeografica> enClass= TipoZonaGeografica.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ITipoZonaGeograficaFacade tipoZonaGeograficaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

   // protected region metodos adicionales end


}
