package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
import java.util.List;

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IContestacionDemandaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.ContestacionDemanda;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ContestacionDemanda
 * @author sMartinez
 */
@Path( "contestaciondemanda" )
@Stateless
public class ContestacionDemandaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ContestacionDemandaRecurso.class);
    private static final Class<ContestacionDemanda> enClass= ContestacionDemanda.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IContestacionDemandaFacade contestacionDemandaFacade; 
	

	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * metodo encargado de registrar la contestacion de una demanda
	 * @param contestacionDemanda
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/registrarContestacionDemanda/")
	public Response registrarContestacionDemanda(ContestacionDemanda contestacionDemanda) {
		Response response = null;
		try {
			
			contestacionDemandaFacade.registrarContestacion(contestacionDemanda);

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * metodo encargado de registrar la contestacion de una reconvencion
	 * @param contestacionDemanda
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/registrarReconvencion/")
	public Response registrarReconvencion(ContestacionDemanda contestacionDemanda) {
		Response response = null;
		try {
			
			contestacionDemandaFacade.registrarReconvencion(contestacionDemanda);

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS,  UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * metodo que retorna las contestaciones de demanda de un caso 
	 * @param contestacionDemanda
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarContestacionDemandaPorCaso/{idCaso}")
	public Response consultarContestacionDemandaPorCaso( @PathParam("idCaso") Long idCaso ) {
		Response response = null;
		
		try {
			List<ContestacionDemanda> contestaciones = new ArrayList<ContestacionDemanda>();
			
			contestaciones = contestacionDemandaFacade.consultarContestacionDemandaPorCaso(idCaso);

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS,  UtilConstantes.X_EXTRA_HEADER).entity(new GenericEntity<List<ContestacionDemanda>>(contestaciones){
			}).build();


		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * metodo que retorna si la fecha de contestacion de la demanda es vigente 
	 * @param validarFechaContestacion
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarFechaContestacion/{idCaso}/{nombreRol}")
	public Response validarFechaContestacion( @PathParam("idCaso") Long idCaso, @PathParam("nombreRol") String nombreRol ) {
		Response response = null;
		
		try {
			Boolean fechaValida = contestacionDemandaFacade.validarFechaContestacion(idCaso, nombreRol);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS,  UtilConstantes.X_EXTRA_HEADER).entity(new GenericEntity<Boolean>(fechaValida){
			}).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * metodo que retorna si ya se realizo la contestacion de la demanda de reconvencion 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarContestacionReconvencion/{idCaso}")
	public Response validarContestacionReconvencion( @PathParam("idCaso") Long idCaso) {
		Response response = null;
		
		try {
			Boolean contestacionRealizada = contestacionDemandaFacade.validarContestacionReconvencion(idCaso);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS,  UtilConstantes.X_EXTRA_HEADER).entity(new GenericEntity<Boolean>(contestacionRealizada){
			}).build();

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
