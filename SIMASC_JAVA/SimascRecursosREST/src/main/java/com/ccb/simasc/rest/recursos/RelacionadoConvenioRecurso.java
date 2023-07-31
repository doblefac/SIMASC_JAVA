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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRelacionadoConvenioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.RelacionadoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.RelacionadoConvenio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST RelacionadoConvenio
 * 
 * @author sMartinez
 */
@Path("relacionadoconvenio")
@Stateless
public class RelacionadoConvenioRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(RelacionadoConvenioRecurso.class);
	private static final Class<RelacionadoConvenio> enClass = RelacionadoConvenio.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IRelacionadoConvenioFacade relacionadoConvenioFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarRelacionadoConvenio/{idConvenio}/{nombreRol}")
	public Response consultarRelacionadoConvenioFiltros(@PathParam("idConvenio") String convenio,
			@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		Long idConvenio = null;
		if (!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.parseLong(convenio);
		try {
			List<RelacionadoConvenioDTO> convenioDTOs = relacionadoConvenioFacade
					.consultarRelacionadoConvenio(idConvenio, nombreRol);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<RelacionadoConvenioDTO>>(convenioDTOs) {
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
	 * Servicio que permite consultar los conciliadores relacionados por
	 * convenio.
	 * 
	 * @author aperez.
	 * @param idConvenio:
	 *            Identificador del convenio.
	 * @return List<PersonaBasicaDTO>: Lista de conciliadores.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConciliadoresRelacionadoConvenio/{idConvenio}")
	public Response consultarConciliadoresRelacionadoConvenio(@PathParam("idConvenio") Long idConvenio) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> conciliadores = relacionadoConvenioFacade
					.consultarConciliadoresRelacionadoConvenio(idConvenio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(conciliadores) {
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
	 * CON-C-010/CON-C-008 Método para realizar la asignacion de personas al
	 * convenio
	 * 
	 * @param personas
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/asignarPersonasConvenio/{idConvenio}/{rol}")
	public Response asignarPersonasConvenio(@PathParam("idConvenio") Long idConvenio, @PathParam("rol") String rol,
			List<RelacionadoConvenio> personas) {
		Response response = null;
		try {
			relacionadoConvenioFacade.asignarPersonasConvenio(personas, rol, idConvenio);
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
	
	/**
	 * CON-C-010/CON-C-008
	 * Servicio que inactiva la relación al convenio de una persona
	 * @param idConvenio
	 * @param idPersona
	 * @param rol
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/inactivarPersonaConvenio/{idConvenio}/{idPersona}/{rol}")
	public Response inactivarPersonaConvenio(@PathParam("idConvenio") Long idConvenio, @PathParam("idPersona") Long idPersona, 
			@PathParam("rol") String rol) {
		Response response = null;
		try {
			relacionadoConvenioFacade.cambiarEstadoPersona(idPersona, idConvenio, UtilDominios.ESTADO_REGISTRO_INACTIVO, rol);
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
	
	/**
	 * CON-C-015/CON-C-008
	 * Servicio que consulta las personas relacionadas de un convenio por filtro  
	 * @param List<String> nombreRoles
	 * @param idConvenio 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonasRelacionadoConvenio/{idConvenio}")
	public Response consultarPersonasRelacionadoConvenio(@PathParam("idConvenio") Long idConvenio, List<String> nombreRoles) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = relacionadoConvenioFacade.consultarPersonasRelacionadoConvenio(nombreRoles, idConvenio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	

	
	// protected region metodos adicionales end

}
