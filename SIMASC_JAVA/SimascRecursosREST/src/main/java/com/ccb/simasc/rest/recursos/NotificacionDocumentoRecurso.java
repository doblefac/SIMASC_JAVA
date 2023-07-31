package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionDocumentoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.NotificacionDocumentoDTO;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST NotificacionDocumento
 * @author sMartinez
 */
@Path( "notificaciondocumento" )
@Stateless
public class NotificacionDocumentoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(NotificacionDocumentoRecurso.class);
    private static final Class<NotificacionDocumento> enClass= NotificacionDocumento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private INotificacionDocumentoFacade notificacionDocumentoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerEstadosOFijacionListas/")
	public Response obtenerEstadosOFijacionListas(NotificacionDocumentoDTO notificacion){
		Response response = null;
		try{
			List<HashMap<String,Object>> responseDTO = notificacionDocumentoFacade.obtenerEstadosOFijacionListas(notificacion.getTipoNotificacion(),notificacion.getPalabraClave(),notificacion.getFechaInicio(),notificacion.getFechaFin());
			response = Response.status(Response.Status.OK).entity(responseDTO).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	 * Obtiene lista de noficiacion_documento dado un idCaso
	 * @param idAgenda
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerNotificacionDocumentoPorCaso/{idCaso}/")
	public Response obtenerNotificacionDocumentoPorCaso(@PathParam("idCaso")Long idCaso){
		Response response = null;
		try {		
			
			List<NotificacionDocumentoDTO> lista = notificacionDocumentoFacade.obtenerNofitificacionesPorCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<NotificacionDocumentoDTO>>(lista) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	 * Obtiene lista de noficiacion_documento dado un idNotificacionDocumento
	 * @param idAgenda
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerNotificacionDocumentoPorId/{idNotificacionDocumento}/")
	public Response obtenerNotificacionDocumentoPorId(@PathParam("idNotificacionDocumento")Long idNotificacionDocumento){
		Response response = null;
		try {		
			
			List<NotificacionDocumentoDTO> lista = notificacionDocumentoFacade.obtenerNofitificacionesPorId(idNotificacionDocumento);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<NotificacionDocumentoDTO>>(lista) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Path("/crearModificarNotificacionDocumento/")
	public Response crearModificarNotificacionDocumento(NotificacionDocumentoDTO notificacion){
		Response response = null;
		try {
			notificacionDocumentoFacade.crearOactualizarNotificacionDocumento(notificacion);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
