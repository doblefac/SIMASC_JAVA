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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITurnoJornadaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.TurnoJornadaDTO;
import com.ccb.simasc.transversal.entidades.TurnoJornada;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST TurnoJornada
 * @author sMartinez
 */
@Path( "turnojornada" )
@Stateless
public class TurnoJornadaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TurnoJornadaRecurso.class);
    private static final Class<TurnoJornada> enClass= TurnoJornada.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ITurnoJornadaFacade turnoJornadaFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	/**
	 * CON-C-029
	 * CON-C-030
	 * Obtiene el listado de turnos disponibles para el convenio de tipo jornada
	 * @param convenio
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarTurnosDisponibles/{idConvenio}")
	public Response consultarTurnosDisponibles(@PathParam("idConvenio") String convenio) {
		Response response = null;
		Long idConvenio = null;
		if(!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.parseLong(convenio);
		try {
			List<TurnoJornadaDTO> turnos = turnoJornadaFacade.consultarTurnosDisplonibles(idConvenio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<TurnoJornadaDTO>>(turnos) {
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
	 * CON-C-029
	 * Método para obtener los turnos consecutivos con los que cuenta la jornada
	 * @param convenio
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarTurnosConsecutivos/{idConvenio}")
	public Response consultarTurnosConsecutivos(@PathParam("idConvenio") String convenio) {
		Response response = null;
		Long idConvenio = null;
		if(!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.parseLong(convenio);
		try {
			List<TurnoJornadaDTO> turnos = turnoJornadaFacade.consultarTurnosConsecutivos(idConvenio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<TurnoJornadaDTO>>(turnos) {
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
	 * CON-C-029
	 * Método para actualizar el turno de la audiencia para la jornada
	 * @param audiencia
	 * @param turno
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/actualizarTurnoAudiencia/{audiencia}/{turno}")
	public Response actualizarTurnoAudiencia(@PathParam("audiencia") String audiencia, @PathParam("turno") String turno) {
		Response response = null;
		Long idAudiencia = null;
		Long idNuevoTurno = null;
		
		if(!audiencia.equals(UtilConstantes.VALOR_UNDEFINED))
			idAudiencia = Long.parseLong(audiencia);
		if(!turno.equals(UtilConstantes.VALOR_UNDEFINED))
			idNuevoTurno = Long.parseLong(turno);
		
		try {
			turnoJornadaFacade.actualizarTurnoAudiencia(idAudiencia, idNuevoTurno);
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
	 * CON-C-029
	 * Método para actualizar una audiencia para que ocupe dos turnos consecutivos
	 * @param audiencia
	 * @param turno
	 * @param consecutivo
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/aumentarTurnoAudiencia/{audiencia}/{turno}/{consecutivo}")
	public Response aumentarTurnoAudiencia(@PathParam("audiencia") String audiencia, @PathParam("turno") String turno, @PathParam("consecutivo") String consecutivo) {
		Response response = null;
		Long idAudiencia = null;
		Long idNuevoTurno = null;
		Long idConsecutivo = null;
		
		if(!audiencia.equals(UtilConstantes.VALOR_UNDEFINED))
			idAudiencia = Long.parseLong(audiencia);
		if(!turno.equals(UtilConstantes.VALOR_UNDEFINED))
			idNuevoTurno = Long.parseLong(turno);
		if(!consecutivo.equals(UtilConstantes.VALOR_UNDEFINED))
			idConsecutivo = Long.parseLong(consecutivo);
		
		try {
			turnoJornadaFacade.ampliarTurno(idAudiencia, idNuevoTurno, idConsecutivo);
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
	 * CON-C-030
	 * Método para actualizar o crear un turno de jornada
	 * @param turno
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/actualizarTurno")
	public Response actualizarTurno(TurnoJornada turno) {
		Response response = null;
		
		try {
			turnoJornadaFacade.actualizarTurnoJornada(turno);
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
	 * CON-C-030
	 * Método para obtener el listado de horas para la creacion de turnos para una jornada
	 * @param convenio
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerListaHorasJornada/{idConvenio}")
	public Response obtenerListaHorasJornada(@PathParam("idConvenio") String convenio) {
		Response response = null;
		Long idConvenio = null;
		if(!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.parseLong(convenio);
		try {
			List<FormatoHoraAudienciaDTO> horas = turnoJornadaFacade.obtenerRangoHoras(idConvenio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<FormatoHoraAudienciaDTO>>(horas) {
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
