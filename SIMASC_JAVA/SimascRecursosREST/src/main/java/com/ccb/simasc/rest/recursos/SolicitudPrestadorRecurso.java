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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudPrestadorFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SolicitudPrestadorDTO;
import com.ccb.simasc.transversal.dto.VerificarSolicitudCambioListaDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
// protected region imports recurso end

/**
 * Recurso REST SolicitudPrestador
 * @author sMartinez
 */
@Path( "solicitudprestador" )
@Stateless
public class SolicitudPrestadorRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SolicitudPrestadorRecurso.class);
    private static final Class<SolicitudPrestador> enClass= SolicitudPrestador.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ISolicitudPrestadorFacade solicitudPrestadorFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/**  CON-F-118
	 * Servicio que crea una solicitud de cambio de lista
	 * @author prendon
	 * @param SolicitudCambioListaDTO
	 * @return OK
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/solicitarCambioLista/")
	public Response solicitarCambioLista( SolicitudPrestador solicitudPrestador ) {
		Response response = null;
		try {
			solicitudPrestadorFacade.solicitarCambioLista(solicitudPrestador);
			response = Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	 * CON-C-002
	 * Servicio que retorna las solicitudes de cambio de lista por nombre de la persona o por estado de solicitud
	 * @return List<SolicitudCambioLista>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudesCambioLista/{estadoSolicitud}")
	public Response consultarSolicitudesCambioLista( @PathParam("estadoSolicitud") String estadoSolicitud, FiltroReportesDTO filtros ) {
		Response response = null;
		String estadoDeSolicitud = !estadoSolicitud.equals(UtilConstantes.VALOR_UNDEFINED)? estadoSolicitud: null;
		try {
			List<SolicitudPrestador> solicitudesCambioLista = solicitudPrestadorFacade.consultarSolicitudesCambioLista( estadoDeSolicitud, filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SolicitudPrestador>>(solicitudesCambioLista) {
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
	
	/** CON-C-004
	 * servicio que expone la consulta de la informacion necesaria 
	 * para hacer la verificacion del cambio de lista
	 * 
	 * @param idCaso
	 *            codigo del caso
	 * @return lista de audiencias
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudVerificacion/{idSolicitud}")
	public Response consultarSolicitudVerificacion(@PathParam("idSolicitud") Long idSolicitud) {
		Response response = null;

		try {
			VerificarSolicitudCambioListaDTO verificarSolicitudCambioListaDTO = solicitudPrestadorFacade.consultarSolicitudVerificacion( idSolicitud );

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<VerificarSolicitudCambioListaDTO>(verificarSolicitudCambioListaDTO) {
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

	/**
	 * CON-C-004
	 * Servicio que responde la solicitud de cambio de lista 
	 * @return List<SolicitudCambioLista>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/verificarSolicitudCambioLista/")
	public Response verificarSolicitudCambioLista( SolicitudPrestador solicitudCambioLista ) {
		Response response = null;
		
		try {
			solicitudPrestadorFacade.verificarSolicitudCambioLista( solicitudCambioLista );
			response = Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	 * ADM-C-006
	 * Servicio que retorna las solicitudes realizadas 
	 * @return List<SolicitudCambioLista>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudesRealizadas/{idRol}/{tipoSolicitud}")
	public Response consultarSolicitudesRealizadas( @PathParam("idRol") String idRol, @PathParam("tipoSolicitud") String tipoSolicitud) {
		Response response = null;
		try {
			List<SolicitudPrestadorDTO> solicitudesRealizadas = solicitudPrestadorFacade.consultarSolicitudesRealizadas( idRol, tipoSolicitud);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SolicitudPrestadorDTO>>(solicitudesRealizadas) {
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
	 * ADM-F.051
	 * Servicio que retorna las solicitudes realizadas 
	 * @return List<SolicitudPrestadorDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudesTipoRolPersona/{tipoSolicitud}")
	public Response consultarSolicitudesTipoRolPersona( @PathParam("tipoSolicitud") String tipoSolicitud, List<Long> idRolPersona) {
		Response response = null;
		try {
			List<SolicitudPrestadorDTO> solicitudesRealizadas = solicitudPrestadorFacade.consultarSolicitudesTipoRolPersona(idRolPersona, tipoSolicitud);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SolicitudPrestadorDTO>>(solicitudesRealizadas) {
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
	 * Crea una nueva solicitud de prestador según su tipo 
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearSolicitudPrestador/{tipoServicio}/{idPersona}/{nombreUsuario}/{nombreRol}")
	public Response crearSolicitudPrestador(@PathParam("tipoServicio") String tipoServicio, @PathParam("idPersona") Long idPersona,
			@PathParam("nombreUsuario") String nombreUsuario, @PathParam("nombreRol") String nombreRol,
			SolicitudPrestadorDTO solicitud) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			solicitudPrestadorFacade.crearSolicitudPrestador(solicitud, idPersonaModificacion, tipoServicio, idPersona, nombreUsuario, nombreRol);
			response = Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
