package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRecursoLaudoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.RecursoLaudo;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST RecursoLaudo
 * @author sMartinez
 */
@Path( "recursolaudo" )
@Stateless
public class RecursoLaudoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(RecursoLaudoRecurso.class);
    private static final Class<RecursoLaudo> enClass= RecursoLaudo.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IRecursoLaudoFacade recursoLaudoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarRecurso/{idCaso}/{idRecurso}")
	public Response consultarRecurso(@PathParam("idCaso") Long idCaso, @PathParam("idRecurso") Long idRecurso) {
		Response response = null;
		try {
			RecursoLaudo recursoLaudo = recursoLaudoFacade.consultarRecurso(idCaso,idRecurso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<RecursoLaudo>(recursoLaudo){
						
					})
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarRecurso")
	public Response actualizarRecurso(RecursoLaudo recursoLaudo) {
		Response response = null;
		try {
			ContextoDeSesion ctx = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			recursoLaudoFacade.actualizarRecurso(recursoLaudo, ctx.getIdUsuario());
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}

		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearRecurso/{idCaso}")
	public Response crearRecurso(@PathParam("idCaso") Long idCaso, RecursoLaudo recursoLaudo) {
		Response response = null;
		try {
			
			recursoLaudoFacade.crearRecurso(idCaso, recursoLaudo);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}

		return response;
	}
   // protected region metodos adicionales end


}
