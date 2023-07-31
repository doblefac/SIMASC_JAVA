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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSolicitudFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST PersonaSolicitud
 * @author sMartinez
 */
@Path( "personasolicitud" )
@Stateless
public class PersonaSolicitudRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(PersonaSolicitudRecurso.class);
    private static final Class<PersonaSolicitud> enClass= PersonaSolicitud.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IPersonaSolicitudFacade personaSolicitudFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * Servicio encargado de consultar las partes de una Solicitud de Servicio.
	 * 
	 * ARB-F-109
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesSolicitudServicio/{idSolicitudServicio}")
	public Response consultarPartesSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			List<FormularioParteDTO> parteDTOs = personaSolicitudFacade
					.consultarPartesSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Servicio encargado de eliminar una parte asociada a una Solicitud de
	 * Servicio.
	 * 
	 * ARB-F-109
	 * 
	 * @param formularioParteDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarParteSolicitudServicio/{idSolicitudServicio}/{idPersona}/")
	public Response eliminarParteSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			personaSolicitudFacade.eliminarParteSolicitudServicio(idSolicitudServicio, idPersona);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	/**
	 * Servicio encargado de consultar una parte activa asociada a una Solicitud
	 * de Servicio.
	 * 
	 * ARB-F-109
	 * 
	 * @param idPersona
	 * @param idSolicitudServicio
	 * @return FormularioParteDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarParteSolicitud/{idPersona}/{idSolicitudServicio}/")
	public Response consultarParteSolicitud(@PathParam("idPersona") Long idPersona,
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			FormularioParteDTO formularioParteDTO = personaSolicitudFacade
					.consultarParteSolicitudServicio(idSolicitudServicio, idPersona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	// protected region metodos adicionales end
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesEnmascaradoSolicitudServicio/{idSolicitudServicio}")
	public Response consultarPartesEnmascaradoSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			List<FormularioParteEnmascaradoDTO> parteDTOs = personaSolicitudFacade
					.consultarPartesEnmascarandoSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteEnmascaradoDTO>>(parteDTOs) {
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
	 * Servicio encargado de consultar una parte activa asociada a una Solicitud
	 * de Servicio enmascarando datos sensibles de la persona
	 * 
	 * ARB-F-109
	 * 
	 * @param idPersona
	 * @param idSolicitudServicio
	 * @return FormularioParteDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarParteEnmascaradaSolicitud/{idPersona}/{idSolicitudServicio}/")
	public Response consultarParteEnmascaradaSolicitud(@PathParam("idPersona") Long idPersona,
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			FormularioParteEnmascaradoDTO formularioParteDTO = personaSolicitudFacade
					.consultarParteEnmascaradaSolicitudServicio(idSolicitudServicio, idPersona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteEnmascaradoDTO>(formularioParteDTO) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

}
