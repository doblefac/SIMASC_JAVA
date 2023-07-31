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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.EstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoPersonaTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST EstadoPersonaTipoServicio
 * @author sMartinez
 */
@Path( "estadopersonatiposervicio" )
@Stateless
public class EstadoPersonaTipoServicioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EstadoPersonaTipoServicioRecurso.class);
    private static final Class<EstadoPersonaTipoServicio> enClass= EstadoPersonaTipoServicio.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IEstadoPersonaTipoServicioFacade estadoPersonaTipoServicioFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	 
	/**
	 * ADM-C-022
	 * Servicio que consulta el historial de estados del funcionario
	 * @param idPersona
	 * @return List<HistoricoEstadoPersonaTipoServicio>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarHistoricoEstados/{idPersona}")
	public Response consultarHistoricoEstados(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<HistoricoEstadoPersonaTipoServicioDTO> historicoPersona = estadoPersonaTipoServicioFacade.consultarHistoricoEstados(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<HistoricoEstadoPersonaTipoServicioDTO>>(historicoPersona) {
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
	 * ADM-C-022
	 * Servicio que cambia el estado del funcionario actualizando el historial de estados del mismo.
	 * @param estadoPersona
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/cambiarEstadoFuncionario/")
	public Response cambiarEstadoFuncionario(EstadoPersonaTipoServicio estadoPersona) {
		Response response = null;
		try {
			// registra una persona al caso
			estadoPersonaTipoServicioFacade.cambiarEstadoFuncionario(estadoPersona);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.build();

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
	 * ADM-F-051
	 * Servicio que retorna la lista de estado persona tipo servicio
	 * @param idPersona
	 * @return List<HistoricoEstadoPersonaTipoServicio>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEstadoPersonaTipoServicio/{idPersona}/{tipoServicio}/{estado}/{estadoIgual}")
	public Response consultarEstadoPersonaTipoServicio(@PathParam("idPersona") Long idPersona, @PathParam("tipoServicio") String tipoServicio, @PathParam("estado") String estado
			, @PathParam("estadoIgual") Boolean estadoIgual) {
		Response response = null;
		String estadoPersona=estado;
		Boolean estadoIgualP = estadoIgual;
		String tipoServicioPersona = tipoServicio;
		if(UtilConstantes.VALOR_UNDEFINED.equals(estado)){
			estadoPersona=null;
		}
		if(UtilConstantes.VALOR_UNDEFINED.equals(estadoIgual)){
			estadoIgualP=null;
		}
		if(UtilConstantes.VALOR_UNDEFINED.equals(tipoServicio)){
			tipoServicioPersona=null;
		}
		try {
			List<EstadoPersonaTipoServicioDTO> listaEstado = estadoPersonaTipoServicioFacade.consultarEstadoPersonaTipoServicio(idPersona, tipoServicioPersona, estadoPersona, estadoIgualP);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<EstadoPersonaTipoServicioDTO>>(listaEstado) {
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
