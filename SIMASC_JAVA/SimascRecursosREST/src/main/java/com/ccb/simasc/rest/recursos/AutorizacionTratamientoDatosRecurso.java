package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

// Escriba en esta seccion sus modificaciones

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAutorizacionTratamientoDatosFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.AutorizacionTratamientoDatos;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST AutorizacionTratamientoDatos
 * 
 * @author sMartinez
 */
@Path("autorizaciontratamientodatos")
@Stateless
public class AutorizacionTratamientoDatosRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(AutorizacionTratamientoDatosRecurso.class);
	private static final Class<AutorizacionTratamientoDatos> enClass = AutorizacionTratamientoDatos.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IAutorizacionTratamientoDatosFacade autorizacionTratamientoDatosFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearAutorizacionDatos")
	public Response crearAutorizacionDatos(AutorizacionTratamientoDatos autorizacion) {
		Response response = null;
		try {
			Long idAutorizacion = autorizacionTratamientoDatosFacade.crearAutorizacionDatos(autorizacion);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idAutorizacion) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
