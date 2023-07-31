package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoCcbFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.EventoCcbDTO;
import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST EventoCcb
 * @author sMartinez
 */
@Path( "eventoccb" )
@Stateless
public class EventoCcbRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EventoCcbRecurso.class);
    private static final Class<EventoCcb> enClass= EventoCcb.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IEventoCcbFacade eventoCcbFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	/**
	 * CON-F-072
	 * Servicio para cancelar un evento de la ccb
	 * @param idEventoCcb
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cancelarEvento/{idEventoCcb}")
	public Response cancelarEvento(@PathParam("idEventoCcb") Long idEventoCcb) {
		Response response = null;
		try {
			eventoCcbFacade.cancelarEvento(idEventoCcb);
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
	 * Servicio que consulta los eventos que se encuentren entre las fechas recibidas por parametro
	 * CON-F-072
	 * @param desde
	 * @param hasta
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEventos/{fechaDesde}/{fechaHasta}")
	public Response consultarEventosPorFecha(@PathParam("fechaDesde") Long desde,
			@PathParam("fechaHasta") Long hasta) {
		Response response = null;
		try {
			Date fechaDesde = new Date(desde);
			Date fechaHasta = new Date(hasta);
			List<EventoCcbDTO> eventos = eventoCcbFacade.consultarEventos(fechaDesde, fechaHasta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<EventoCcbDTO>>(eventos) {
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
	 * CON-C-025
	 * Servicio para consultar un evento de la ccb por el identificador
	 * @param idEventoCcb
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEvento/{idEventoCcb}")
	public Response consultarEvento(@PathParam("idEventoCcb") Long idEventoCcb) {
		Response response = null;
		try {
			EventoCcb evento = eventoCcbFacade.consultarEvento(idEventoCcb);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<EventoCcb>(evento) {
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
	 * CON-C-025
	 * Servicio que realiza la actualizacion o creacion del evento programado por la CCB
	 * y se encarga de realizar las notificaciones a los conciliadores asociados
	 * @param eventoCcb
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarEvento/")
	public Response cancelarEvento(EventoCcb evento) {
		Response response = null;
		try {
			List<Long> centros = evento.getCentros();
			Boolean envio = eventoCcbFacade.actualizarEvento(evento, centros);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Boolean>(envio) {
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
	 * CON-C-026
	 * Servicio que realiza el proceso de registrar el resultado de un evento
	 * programado por la CCB
	 * @param evento
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/registrarResultadoEvento/")
	public Response registrarResultadoEvento(EventoCcb evento) {
		Response response = null;
		try {
			eventoCcbFacade.registrarResultadoEvento(evento);
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
