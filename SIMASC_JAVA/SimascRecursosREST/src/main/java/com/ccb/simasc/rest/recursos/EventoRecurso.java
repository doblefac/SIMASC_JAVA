package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.EventoDTO;
import com.ccb.simasc.transversal.dto.formularios.DevolucionExpedienteDTO;
import com.ccb.simasc.transversal.dto.formularios.RutaDelCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST Evento
 * @author sMartinez
 */
@Path( "evento" )
@Stateless
public class EventoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EventoRecurso.class);
    private static final Class<Evento> enClass= Evento.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private CorreoElectronicoRecurso correo;
	
	@EJB
    private ICasoFacade casoFacade;
	
	@EJB
    private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IRepartoSvc repartoSvc;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crear")
	public Response crearEvento(Evento evento) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			evento.setFechaEvento(new Date());
			evento.setFechaUltimaModificacion(new Date());
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			evento.setIdUsuarioModificacion(cs.getIdUsuario());
			eventoFacade.crear(evento);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Evento>(evento) {
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/RegistrarDevolucionDeExpediente")
	public Response registrarDevolucionDeExpediente(DevolucionExpedienteDTO mapa) {
		Response response = null;
		try {
			Evento evento = mapa.getEvento();
			
			Caso caso = evento.getCaso() == null ? casoFacade.buscar(evento.getIdCaso()) : evento.getCaso();
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			evento.setFechaEvento(new Date());
			evento.setFechaUltimaModificacion(new Date());
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			evento.setIdUsuarioModificacion(cs.getIdUsuario());
			eventoFacade.crear(evento);
			caso.setEtapa(UtilDominios.ETAPA_CASO_PREARBITRAL);
			casoFacade.actualizar(caso);
			correoRolPersonaCasoFacade.enviarCorreo(correoRolPersonaCasoFacade.convertidorParametros(mapa.getCorreo()));
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Evento>(evento) {
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
	@Path("/consultarEventosCaso/{idCaso}")
	public Response consultarEventosCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<RutaDelCasoDTO> eventos = eventoFacade.consultarEventosDeCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RutaDelCasoDTO>>(eventos) {
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
	@Path("/consultarEventosCasoTipo/{idCaso}")
	public Response consultarEventosCasoTipo(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<RutaDelCasoDTO> eventos = eventoFacade.consultarEventosCasoTipo(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RutaDelCasoDTO>>(eventos) {
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
	@Path("/consultarEntidadesEventosCaso/{idCaso}")
	public Response consultarEntidadesEventosCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<RutaDelCasoDTO> eventos = eventoFacade.consultarEntidadesEventosDeCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RutaDelCasoDTO>>(eventos) {
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
	
	/** Recurso que expone el guardado del evento de fallo del reparto
	 * @param mapa
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarFalloReparto/{idCaso}")
	public Response guardarFalloReparto( @PathParam("idCaso") Long idCaso, String detallesNoReparto ) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			repartoSvc.fallaReparto(idCaso, detallesNoReparto, contextoDeSesion.getNombreUsuario());
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
	
	
	/** Recurso que expone el guardado del evento de fallo del reparto
	 * @param mapa
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarUltimoEventoPorTipos/{idCaso}")
	public Response consultarUltimoEventoPorTipos( @PathParam("idCaso") Long idCaso, List<String> tipoEventos ) {
		Response response = null;
		try {
			
			List<EventoDTO> eventos = eventoFacade.consultarUltimoEventoPorTipos(tipoEventos, idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<EventoDTO>>(eventos) {
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
