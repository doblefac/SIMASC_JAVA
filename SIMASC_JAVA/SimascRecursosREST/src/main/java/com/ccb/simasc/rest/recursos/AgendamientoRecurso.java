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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AgendamientoDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.FechasAgendamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Agendamiento
 * @author sMartinez
 */
@Path( "agendamiento" )
@Stateless
public class AgendamientoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AgendamientoRecurso.class);
    private static final Class<Agendamiento> enClass= Agendamiento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IAgendamientoFacade agendamientoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	
	/**
	 * Permite buscar salas segun los parametros que se le envien
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/buscarAgendamientoFiltros")
    public Response buscarAgendamientoFiltros(FiltrosSalasDTO filtrosSalasDTO) {
        Response response = null;
        try { 
        	List<AgendamientoDTO> agendamientoDTOs = agendamientoFacade.buscarAgendamientoFiltros(filtrosSalasDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AgendamientoDTO>>(agendamientoDTOs) {
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
	 * 
	 * @param AgendamientoDTO
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrarAgendamiento")
    public Response registrarAgendamiento(AgendamientoDTO agendamientoDTO) {
        Response response = null;
        try { 
        	 agendamientoFacade.registrarAgendamiento(agendamientoDTO);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
	 * 
	 * @param AgendamientoDTO
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/actualizarAgendamiento")
    public Response actualizarAgendamiento(AgendamientoDTO agendamientoDTO) {
        Response response = null;
        try { 
        	 agendamientoFacade.actualizarAgendamiento(agendamientoDTO);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/cancelarAgendamiento/{idAgendamiento}")
    public Response cancelarAgendamiento(@PathParam("idAgendamiento")Long idAgendamiento) {
        Response response = null;
        try { 
        	 agendamientoFacade.cancelarAgendamiento(idAgendamiento);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
	 * Obtener el arreglo con las fechas para el agendamiento junto los turnos disponibles para agendamiento 
	 * @param idConciliador
	 * @param idConvenio
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/consultarAgendamiento/")
    public Response consultarAgendamiento(ConsultaAgendamientoDTO consulta) {
        Response response = null;
        try { 
        	List<FechasAgendamientoDTO> agendamientoDisponible = agendamientoFacade.consultarAgendamiento(consulta);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<FechasAgendamientoDTO>>(agendamientoDisponible) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS
					, UtilConstantes.X_EXTRA_HEADER).build();
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
