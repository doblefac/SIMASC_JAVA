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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IResultadoAudienciaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.ResultadoCasoDTO;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ResultadoAudiencia
 * 
 * @author sMartinez
 */
@Path("resultadoaudiencia")
@Stateless
public class ResultadoAudienciaRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(ResultadoAudienciaRecurso.class);
	private static final Class<ResultadoAudiencia> enClass = ResultadoAudiencia.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IResultadoAudienciaFacade resultadoAudienciaFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarResultadoAudiencia/")
	public Response registrarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO) {
		Response response = null;

		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			resultadoAudienciaFacade.registrarResultadoAudiencia(resultadoDTO, idPersonaModificacion);

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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarResultadoAudiencia/")
	public Response modificarResultadoAudiencia(ResultadoAudienciaDTO resultadoDTO) {
		Response response = null;

		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			resultadoAudienciaFacade.modificarResultadoAudiencia(resultadoDTO, idPersonaModificacion);

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

	/**
	 * consulta las resultados de una audiencia.
	 * 
	 * @param idAudiencia:
	 *            Identificador de la audiencia.
	 * @return lista de audiencias.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarResultadosAudiencia/{idAudiencia}")
	public Response consultarResultadosAudiencia(@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;
		try {
			List<ResultadoAudienciaDTO> resultadosAudiencia = resultadoAudienciaFacade
					.consultarResultadosAudiencia(idAudiencia);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ResultadoAudienciaDTO>>(resultadosAudiencia) {
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

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarDocumentoResultadoAudiencia/{idDocumento}/{idResultadoAudiencia}/{idServicio}")
	public Response actualizarDocumentoResultadoAudiencia(@PathParam("idDocumento") Long idDocumento,
			@PathParam("idResultadoAudiencia") Long idResultadoAudiencia, @PathParam("idServicio") Long idServicio) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			resultadoAudienciaFacade.actualizarDocumentoResultadoAudiencia(idDocumento, idResultadoAudiencia,
					idPersonaModificacion, idServicio);
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

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarResultadosAudienciaCaso/{idCaso}")
	public Response consultarResultadosAudienciaCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<ResultadoCasoDTO> resultadosCaso = resultadoAudienciaFacade.consultarResultadosAudienciasCaso(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ResultadoCasoDTO>>(resultadosCaso) {
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarEstadoResultadoAudiencia/{idResultadoAudiencia}/{estadoResultado}")
	public Response actualizarEstadoResultadoAudiencia(@PathParam("idResultadoAudiencia") Long idResultadoAudiencia,
			@PathParam("estadoResultado") String estadoResultado) {
		Response response = null;

		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			resultadoAudienciaFacade.actualizarEstadoResultado(estadoResultado, idResultadoAudiencia, idPersonaModificacion);
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
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarResultadoAudiencia/{idResultadoAudiencia}")
	public Response eliminarResultadoAudiencia(@PathParam("idResultadoAudiencia") Long idResultadoAudiencia ) {
		Response response = null;
		try {
			resultadoAudienciaFacade.eliminarResultadoAudiencia( idResultadoAudiencia);
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
