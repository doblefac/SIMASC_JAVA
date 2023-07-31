package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

import java.util.HashMap;
import java.util.Map;

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST NombramientoPersona
 * @author sMartinez
 */
@Path( "nombramientopersona" )
@Stateless
public class NombramientoPersonaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(NombramientoPersonaRecurso.class);
    private static final Class<NombramientoPersona> enClass= NombramientoPersona.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso

	@EJB
	private INombramientoPersonaFacade nombramientoPersonasFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMensajeArbitrosPorNombrar/{idCaso}/{rolNombra}")
	public Response obtenerMensajeArbitrosPorNombrar(@PathParam("idCaso") Long idCaso,
			@PathParam("rolNombra") String metodoNombramiento) {
		Response response = null;
		try {
			Integer principalesPorNombrar = nombramientoPersonasFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
					metodoNombramiento, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,null);
			Integer suplentesPorNombrar = nombramientoPersonasFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
					metodoNombramiento, UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,null);
			String mensaje = "Se debe nombrar (" + principalesPorNombrar + ") árbitro(s) Principal(es) y/o ("
					+ suplentesPorNombrar + ") árbitro(s) Suplente(s)";

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(mensaje) {
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
	@Path("/obtenerMensajePacto/{idCaso}/{rolNombra}/{idTercero}")
	public Response obtenerMensajePacto(@PathParam("idCaso") Long idCaso,
			@PathParam("rolNombra") String metodoNombramiento,@PathParam("idTercero") Long idTerceroAutoridad ) {
		Response response = null;
		Map<String, Object> arbitro = new HashMap<>();

		try {
			arbitro = nombramientoPersonasFacade.obtenerMensajePacto(idCaso,metodoNombramiento, idTerceroAutoridad ); 
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Map>(arbitro) {
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

	// protected region metodos adicionales end


}
