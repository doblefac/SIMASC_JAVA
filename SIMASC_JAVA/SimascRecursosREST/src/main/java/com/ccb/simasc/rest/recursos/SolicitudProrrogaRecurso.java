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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudProrrogaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaCierreDTO;
import com.ccb.simasc.transversal.dto.SolicitudProrrogaDTO;
import com.ccb.simasc.transversal.entidades.SolicitudProrroga;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST SolicitudProrroga
 * @author sMartinez
 */
@Path( "solicitudprorroga" )
@Stateless
public class SolicitudProrrogaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SolicitudProrrogaRecurso.class);
    private static final Class<SolicitudProrroga> enClass= SolicitudProrroga.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ISolicitudProrrogaFacade solicitudProrrogaFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/**	
	 * Consulta las solicitudes de prorroga de un caso 
	 * 
	 * @return Response.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudesProrroga/{idCaso}/")
	public Response consultarSolicitudesProrroga(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			SolicitudProrrogaCierreDTO listaSolicitud = solicitudProrrogaFacade.consultarSolicitudesProrroga(idCaso,idPersonaModificacion);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<SolicitudProrrogaCierreDTO>(listaSolicitud) {
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
	 * Crea/Modifica una solicitud de prorroga de un caso 
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarSolicitudProrroga/")
	public Response modificarSolicitudProrroga(SolicitudProrrogaDTO solicitud) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			solicitudProrrogaFacade.modificarSolicitudeProrroga(solicitud,idPersonaModificacion);
			response = Response.status(Response.Status.OK)
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
