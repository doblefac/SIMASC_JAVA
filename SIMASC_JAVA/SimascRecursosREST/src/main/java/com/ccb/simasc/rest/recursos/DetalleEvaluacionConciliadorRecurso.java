package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDetalleEvaluacionConciliadorFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST DetalleEvaluacionConciliador
 * @author sMartinez
 */
@Path( "detalleevaluacionconciliador" )
@Stateless
public class DetalleEvaluacionConciliadorRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(DetalleEvaluacionConciliadorRecurso.class);
    private static final Class<DetalleEvaluacionConciliador> enClass= DetalleEvaluacionConciliador.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IDetalleEvaluacionConciliadorFacade detalleEvaluacionConciliadorFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * CON-C-020
     * Servicio que obtiene los detalles calculador para un criterio
     * @param centros
     * @return
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCriterio/{idPersona}/{periodoDesde}/{periodoHasta}/{codigoCriterio}")
	public Response consultarConciliadoresEvaluar(@PathParam("idPersona") Long idPersona,
			@PathParam("periodoDesde") Long desde, @PathParam("periodoHasta") Long hasta,
			@PathParam("codigoCriterio") String criterio) {
		Response response = null;
		try {
			Date periodoDesde = new Date(desde);
			Date periodoHasta = new Date(hasta);
			List<DetalleEvaluacionConciliador> detalles = detalleEvaluacionConciliadorFacade.calcularCriterio(idPersona, periodoDesde, periodoHasta, criterio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DetalleEvaluacionConciliador>>(detalles) {
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
	 * CON-C-019
	 * Servicio que obtiene los totales de cada criterio de la evaluacion de un conciliador
	 * @param idPersona
	 * @param desde
	 * @param hasta
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarTotales/{idPersona}/{periodoDesde}/{periodoHasta}")
	public Response consultarTotales(@PathParam("idPersona") Long idPersona, @PathParam("periodoDesde") Long desde,
			@PathParam("periodoHasta") Long hasta) {
		Response response = null;
		try {
			Date periodoDesde = new Date(desde);
			Date periodoHasta = new Date(hasta);
			List<DetalleEvaluacionConciliador> detalles = detalleEvaluacionConciliadorFacade.consultarTotales(idPersona,
					periodoDesde, periodoHasta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DetalleEvaluacionConciliador>>(detalles) {
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
