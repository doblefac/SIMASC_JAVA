package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones



import java.util.Date;
import java.util.HashMap;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISuspensionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SuspensionDTO;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Suspension
 * @author sMartinez
 */
@Path( "suspension" )
@Stateless
public class SuspensionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SuspensionRecurso.class);
    private static final Class<Suspension> enClass= Suspension.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ISuspensionFacade suspensionFacade; 
	
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/**
	 * Consulta los eventos asociados a un caso de suspension y de suspension
	 * por requerimiento del cliente. Se adiciona el servicio la opcionalidad de
	 * enviar los id de evento
	 * 
	 * @param idCaso
	 * @param idEventos
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSuspensionId/{idCaso}")
	public Response consultarSuspensionRequerimiento(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<Suspension> suspensiones = suspensionFacade.consultarSuspensionId(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Suspension>>(suspensiones) {
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
	
	@POST
	@Produces ({ MediaType.APPLICATION_JSON })
	@Consumes ({ MediaType.APPLICATION_JSON })
	@Path("/crearSuspensionPreArb/")
	public Response crearSuspensionPreArb(Suspension suspension){
		Response response = null;	
		try{
			suspensionFacade.crearSuspensionPreArb(suspension);	
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
	@Produces ({ MediaType.APPLICATION_JSON })
	@Consumes ({ MediaType.APPLICATION_JSON })
	@Path("/ActualizarSuspensionPreArb/")
	public Response actualizarSuspensionPreArb(Suspension suspension){
		Response response = null;	
		try{
			suspensionFacade.actualizarSuspensionPreArb(suspension);	
			} catch (SIMASCNegocioExcepcion e) {
				LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
				throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
				} catch (Exception e) {
					LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
	}
		return response;
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("obtenerSuspensiones/{idCaso}")
	public Response obtenerSuspensiones(@PathParam("idCaso") Long idCaso){
		Response response = null;
		try {
			List<HashMap<String,Object>> respuesta = suspensionFacade.obtenerSuspensiones(idCaso);
			response = Response.status(Response.Status.OK).entity(respuesta)
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
		@Produces({MediaType.APPLICATION_JSON})
		@Consumes({MediaType.APPLICATION_JSON})
		@Path("guardarSuspension/")
		public Response guardarSuspension(SuspensionDTO suspension){
			Response response = null;
			try {
				suspensionFacade.guardarSuspension(suspension);
				response = Response.status(Response.Status.OK).entity("ok")
						.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			} catch (SIMASCNegocioExcepcion e) {
				LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
				throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw SIMASCRecursosRESTExcepcion.transformTopException(e);
			}
			return response;
			
		}

		
		@GET
		@Produces({MediaType.APPLICATION_JSON})
		@Path("obtenerDiasHabiles/{fechaIni}/{fechaFin}")
		public Response obtenerDiasHabiles(@PathParam("fechaIni") Long fechaIni,@PathParam("fechaFin") Long fechaFin){
			Response response = null;
			try {
				Date fechaInicial = new Date(fechaIni);
				Date fechaFinal = new Date(fechaFin);

				response = Response.status(Response.Status.OK).entity(suspensionFacade.obtenerDiasHabiles(fechaInicial, fechaFinal))
						.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			} catch (SIMASCNegocioExcepcion e) {
				LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
				throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw SIMASCRecursosRESTExcepcion.transformTopException(e);
			}
			return response;
			
		}
		
		
		@GET
		@Produces({MediaType.APPLICATION_JSON})
		@Path("adicionarDiasHabilesFecha/{fecha}/{dias}")
		public Response adicionarDiasHabilesFecha(@PathParam("fecha") Long fecha,@PathParam("dias") int dias){
			Response response = null;
			try {
				Date fechaIni = new Date(fecha);
				response = Response.status(Response.Status.OK).entity(suspensionFacade.adicionarDiasHabilesFecha(fechaIni, dias))
						.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
