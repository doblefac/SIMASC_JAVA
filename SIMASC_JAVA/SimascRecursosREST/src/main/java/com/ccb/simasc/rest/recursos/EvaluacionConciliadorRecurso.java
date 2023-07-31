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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEvaluacionConciliadorFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST EvaluacionConciliador
 * 
 * @author sMartinez
 */
@Path("evaluacionconciliador")
@Stateless
public class EvaluacionConciliadorRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(EvaluacionConciliadorRecurso.class);
	private static final Class<EvaluacionConciliador> enClass = EvaluacionConciliador.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IEvaluacionConciliadorFacade evaluacionConciliadorFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * CON-C-018 Servicio que obtiene el listado de conciliadores que se
	 * encuentran habilitados para la evaluciación
	 * 
	 * @param centros
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConciliadoresEvaluar")
	public Response consultarConciliadoresEvaluar(List<Long> centros) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> conciliadores = evaluacionConciliadorFacade.consultaConciliadoresEvaluar(centros);
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
	 * CON-C-019 Servicio que realiza el registro del resultado de la evaluación
	 * del conciliador
	 * 
	 * @param idEvaluacion
	 * @param resultado
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarEvaluacion/{idEvaluacion}")
	public Response registrarEvaluacionConciliador(@PathParam("idEvaluacion") Long idEvaluacion, Double resultado) {
		Response response = null;
		try {
			evaluacionConciliadorFacade.registrarResultadoEvaluacion(idEvaluacion, resultado);
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
	 * Servicio que permite consultar los anios en los que se han registrado
	 * evaluaciones.
	 * 
	 * @return List<String>: Lista de anios.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAniosRegistroEvaluacion/")
	public Response consultarAniosRegistroEvaluacion() {
		Response response = null;
		try {
			List<String> anios = evaluacionConciliadorFacade.consultarAniosRegistroEvaluaciones();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<String>>(anios) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	// protected region metodos adicionales end

}
