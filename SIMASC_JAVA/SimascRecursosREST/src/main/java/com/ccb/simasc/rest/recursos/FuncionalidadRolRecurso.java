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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadRolFacade;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;

// protected region imports recurso end

/**
 * Recurso REST FuncionalidadRol
 * @author sMartinez
 */
@Path( "funcionalidadrol" )
@Stateless
public class FuncionalidadRolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(FuncionalidadRolRecurso.class);
    private static final Class<FuncionalidadRol> enClass= FuncionalidadRol.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IFuncionalidadRolFacade funcionalidadRolFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
