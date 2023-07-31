package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.ArrayList;
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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaFestivo;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendaPersonaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AgendaPersonaDTO;
import com.ccb.simasc.transversal.entidades.AgendaPersona;
import com.ccb.simasc.transversal.entidades.DiaFestivo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST AgendaPersona
 * @author sMartinez
 */
@Path( "agendapersona" )
@Stateless
public class AgendaPersonaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AgendaPersonaRecurso.class);
    private static final Class<AgendaPersona> enClass= AgendaPersona.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IAgendaPersonaFacade agendaPersonaFacade; 
	
	@EJB
    private ManejadorAgendaPersona manejadorAgendaPersona; 


	@EJB
    private ManejadorDiaFestivo manejadorDiaFestivo; 

	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	
	/**
	 * Obtiene la agenda de una persona.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaAgendaPersona/{idPersona}")
	public Response consultaAgendaPersona(@PathParam("idPersona")Long idPersona) {
		Response response = null;
		try {		
			List<AgendaPersona> agendaPersona = manejadorAgendaPersona.consultaAgendaPersona(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AgendaPersona>>(agendaPersona) {
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
	 * Obtiene la agenda de una persona.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarAgendaFechaPersona/{idAgenda}")
	public Response eliminarAgendaFechaPersona(@PathParam("idAgenda")Long idAgenda) {
		Response response = null;
		try {		
			
			agendaPersonaFacade.eliminarAgendaFechaPersona(idAgenda);
			response = Response.status(Response.Status.OK).header(
					UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	 * Obtiene la agenda de una persona.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaFuncionariosAgenda/{idCentro}")
	public Response consultaFuncionariosAgenda(@PathParam("idCentro")Long idCentro) {
		Response response = null;
		try {		
			
			List<Persona> personasFuncionarios = agendaPersonaFacade.consultaFuncionariosAgenda(idCentro);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personasFuncionarios) {
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
	 * Obtiene la agenda de una persona.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtieneDiasFestivos/")
	public Response obtieneDiasFestivos() {
		Response response = null;
		try {		
			
			List<DiaFestivo> diasFestivos =  manejadorDiaFestivo.consultar(null, null, null);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DiaFestivo>>(diasFestivos) {
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
	 * Guarda la información de una nueva sede
	 * @param paramSede
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/crearAgendaPersona")
	public Response crearAgendaPersona(AgendaPersona agendaP) {
		Response response = null;
		try {
			agendaPersonaFacade.crearAgendaPersona(agendaP);
			response = Response.status(Response.Status.OK).header(
					UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
			
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
	 * CON-F-075
	 * Servicio para actualizar la asistencia de la audiencia
	 * @param agendaPersonaDTO
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/validarAgendaConciliador")
	public Response validarAgendaConciliador(AgendaPersonaDTO agendaPersonaDTO) {
		Response response = null;
		try {
			agendaPersonaFacade.actualizarAgendaPersona(agendaPersonaDTO);
			response = Response.status(Response.Status.OK).header(
					UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
