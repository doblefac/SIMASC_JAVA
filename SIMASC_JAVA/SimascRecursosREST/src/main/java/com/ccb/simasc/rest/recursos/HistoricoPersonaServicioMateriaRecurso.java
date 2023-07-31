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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHistoricoPersonaServicioMateriaFacade;
import com.ccb.simasc.transversal.entidades.HistoricoPersonaServicioMateria;

// protected region imports recurso end

/**
 * Recurso REST HistoricoPersonaServicioMateria
 * @author sMartinez
 */
@Path( "historicopersonaserviciomateria" )
@Stateless
public class HistoricoPersonaServicioMateriaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(HistoricoPersonaServicioMateriaRecurso.class);
    private static final Class<HistoricoPersonaServicioMateria> enClass= HistoricoPersonaServicioMateria.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IHistoricoPersonaServicioMateriaFacade historicoPersonaServicioMateriaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

   // protected region metodos adicionales end


}
