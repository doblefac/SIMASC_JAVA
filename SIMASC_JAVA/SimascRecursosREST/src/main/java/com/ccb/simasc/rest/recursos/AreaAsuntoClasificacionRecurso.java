package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

// Escriba en esta sección sus modificaciones

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAreaAsuntoClasificacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.AreaAsuntoClasificacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST AreaAsuntoClasificacion
 * 
 * @author sMartinez
 */
@Path("areaasuntoclasificacion")
@Stateless
public class AreaAsuntoClasificacionRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(AreaAsuntoClasificacionRecurso.class);
	private static final Class<AreaAsuntoClasificacion> enClass = AreaAsuntoClasificacion.class;

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso

	@EJB
	private IAreaAsuntoClasificacionFacade areaAsuntoClasificacionFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Obtiene un areaAsuntoClasificacion de un caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @return AreaAsuntoClasificacion.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAreaAsuntoClasificacionPorCaso/{idCaso}")
	public Response consultarAreaAsuntoClasificacionPorCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			AreaAsuntoClasificacion areaAsuntoClasificacion = areaAsuntoClasificacionFacade
					.consultarAreaAsuntoClasificacionPorCaso(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<AreaAsuntoClasificacion>(areaAsuntoClasificacion) {
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
