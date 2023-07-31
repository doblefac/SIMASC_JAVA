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

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaRolFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.EstadoPersonaRolDTO;
import com.ccb.simasc.transversal.dto.HistoricoEstadoMotivoPersonaDTO;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST EstadoPersonaRol
 * @author sMartinez
 */
@Path( "estadopersonarol" )
@Stateless
public class EstadoPersonaRolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EstadoPersonaRolRecurso.class);
    private static final Class<EstadoPersonaRol> enClass= EstadoPersonaRol.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IEstadoPersonaRolFacade estadoPersonaRolFacade; 
	
	@EJB
	IDominioFacade dominioFacade;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	 
	/**
	 * ADM-C-022
	 * Servicio que consulta el historial de estados del funcionario
	 * @param idPersona
	 * @return List<HistoricoEstadoPersonaRol>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarHistoricoEstados/{idPersona}")
	public Response consultarHistoricoEstados(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<HistoricoEstadoMotivoPersonaDTO> historicoPersona = estadoPersonaRolFacade.consultarHistoricoEstados(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<HistoricoEstadoMotivoPersonaDTO>>(historicoPersona) {
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
	public Response cambiarEstadoFuncionario(EstadoPersonaRol estadoPersona) {
		Response response = null;
		try {
			// registra una persona al caso
			estadoPersonaRolFacade.cambiarEstadoFuncionario(estadoPersona);
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
	 * @return List<HistoricoEstadoPersonaRol>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarMotivosEstadoArbitro/")
	public Response consultarMotivosEstadoArbitro() {
		Response response = null;
		try {
			List<DominioDTO> listaEstado = dominioFacade.getDominiosDTO(UtilDominios.DOMINIO_MOTIVO_ESTADO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DominioDTO>>(listaEstado) {
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
	 * ADM-F-051
	 * Servicio que retorna la lista de estado persona tipo servicio
	 * @param idPersona
	 * @return List<HistoricoEstadoPersonaRol>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEstadoPersonaRolPorIdPersona/{idPersona}")
	public Response consultarEstadoPersonaRolPorIdPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<EstadoPersonaRolDTO> listaEstado = estadoPersonaRolFacade.consultarEstadoPersonaRol(idPersona, null, null, null);
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<EstadoPersonaRolDTO>>(listaEstado) {
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
	 * ADM-F-051
	 * Servicio que retorna la lista de estado persona tipo servicio
	 * @param idPersona
	 * @return List<HistoricoEstadoPersonaRol>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEstadoPersonaRol/{idPersona}/{idRol}/{estado}/{estadoIgual}")
	public Response consultarEstadoPersonaRol(@PathParam("idPersona") Long idPersona, @PathParam("idRol") Long idRol, @PathParam("estado") String estado
			, @PathParam("estadoIgual") Boolean estadoIgual) {
		Response response = null;
		String estadoPersona=estado;
		Boolean estadoIgualP = estadoIgual;
		Long RolPersona = idRol;
		if(UtilConstantes.VALOR_UNDEFINED.equals(estado)){
			estadoPersona=null;
		}
		if(UtilConstantes.VALOR_UNDEFINED.equals(estadoIgual)){
			estadoIgualP=null;
		}
		if(UtilConstantes.VALOR_UNDEFINED.equals(idRol)){
			RolPersona=null;
		}
		try {
			List<EstadoPersonaRolDTO> listaEstado = estadoPersonaRolFacade.consultarEstadoPersonaRol(idPersona, RolPersona, estadoPersona, estadoIgualP);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<EstadoPersonaRolDTO>>(listaEstado) {
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


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarMotivosEstadoPorRol/{idRol}")
	public Response consultarMotivosEstadoPorRol(@PathParam("idRol") Long idRol) {
		Response response = null;
		try {
			List<DominioDTO> listaMotivos = estadoPersonaRolFacade.consultarMotivosEstadoPersonaRol(idRol);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DominioDTO>>(listaMotivos) {
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
}
