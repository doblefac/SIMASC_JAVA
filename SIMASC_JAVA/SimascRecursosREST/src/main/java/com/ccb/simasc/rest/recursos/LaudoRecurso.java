package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILaudoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.LaudoDTO;
import com.ccb.simasc.transversal.dto.formularios.ConsultarLaudosDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleLaudoDTO;
import com.ccb.simasc.transversal.entidades.Laudo;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Laudo
 * @author sMartinez
 */
@Path( "laudo" )
@Stateless
public class LaudoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(LaudoRecurso.class);
    private static final Class<Laudo> enClass= Laudo.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ILaudoFacade laudoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarExistenciaLaudo/{idCaso}")
	public Response consultarExistenciaLaudo(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Boolean existeLaudo = laudoFacade.consultarExistenciaLaudo(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Boolean>(existeLaudo){
						
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
	@Path("/guardarLaudo/{idCaso}")
	public Response guardarLaudo(@PathParam("idCaso") Long idCaso, Laudo laudo) {
		Response response = null;
		try {
			
			LaudoDTO laudoCreado = laudoFacade.guardarLaudo(idCaso, laudo);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<LaudoDTO>(laudoCreado){						
					})
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	
	/**
	 * Servivio que retorna un laudo dado el laudo de un acaso dado el id de este
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarLaudo/{idCaso}")
	public Response consultarLaudo(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			LaudoDTO laudo = new LaudoDTO().transformarConDependencias(laudoFacade.consultarLaudo(idCaso));

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<LaudoDTO>(laudo){
						
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
	
	/**
	 * Servivio que retorna los laudos de un caso dados los filtros del DTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarLaudos/")
	public Response consultarLaudos(ConsultarLaudosDTO datos) {
		Response response = null;
		try {			
			List<DetalleLaudoDTO> laudos = laudoFacade.obtenerLaudos(datos.getFechaIni(), datos.getFechaFin(), datos.getArbitros(),
					datos.getPartes(), datos.getDocParte(), datos.getIdCaso(), datos.getMateria(), datos.getEstado());
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DetalleLaudoDTO>>(laudos){						
					})
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
