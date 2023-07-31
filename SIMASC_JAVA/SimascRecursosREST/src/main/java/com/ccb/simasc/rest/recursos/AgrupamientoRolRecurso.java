package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgrupamientoRolFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST AgrupamientoRol
 * @author sMartinez
 */
@Path( "agrupamientorol" )
@Stateless
public class AgrupamientoRolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AgrupamientoRolRecurso.class);
    private static final Class<AgrupamientoRol> enClass= AgrupamientoRol.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IAgrupamientoRolFacade agrupamientoRolFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/**
	 * Obtiene los roles dependiendo del servicio y el agrupador.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerRolesPorServicioYTipoAgrupador/{idServicio}/{tipoAgrupador}")
	public Response obtenerRolesPorServicioYTipoAgrupador(@PathParam("idServicio")Long idServicio, @PathParam("tipoAgrupador")String tipoAgrupador) {
		Response response = null;
		try {		
			List<Rol> roles = agrupamientoRolFacade.obtenerRolesPorServicioYTipoAgrupador(idServicio, tipoAgrupador);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Rol>>(roles) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

   // protected region metodos adicionales end


}
