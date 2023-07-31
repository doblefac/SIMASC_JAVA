package com.ccb.simasc.rest.recursos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILlamadaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.LlamadaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.ReporteCorreosDevueltosDTO;
import com.ccb.simasc.transversal.dto.formularios.InformacionCorreoDTO;
import com.ccb.simasc.transversal.entidades.Llamada;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Llamada
 * @author sMartinez
 */
@Path( "llamada" )
@Stateless
public class LlamadaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(LlamadaRecurso.class);
    private static final Class<Llamada> enClass= Llamada.class;
    private static final String ACCESS_CONTROL_ALLOW_HEADERS="Access-Control-Allow-Headers";
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ILlamadaFacade llamadaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * Servicio que registra las llamadas para los correos electronicos
	 * fallidos.
	 * 
	 * @param llamadaDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/registrar/")
	public Response registrarLlamada(LlamadaDTO llamadaDTO) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			LlamadaDTO llamadaDTO2 = llamadaFacade.registrarLlamada(llamadaDTO, cs);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<LlamadaDTO>(llamadaDTO2) {
			}).header(ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();	
		}  catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio que obtiene el registro de gestion de llamadas realizadas de correos devueltos.
	 * @param informacionCorreoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/gestionCorreosDevueltos/")
	public Response reporteCorreosDevueltosGestion(InformacionCorreoDTO informacionCorreoDTO) {
		Response response = null;
		try {
			List<ReporteCorreosDevueltosDTO> llamadaDTOs = llamadaFacade.reporteCorreosDevueltos(informacionCorreoDTO.getIdCaso(),
					informacionCorreoDTO.getFechaInicial(), informacionCorreoDTO.getFechaFinal());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteCorreosDevueltosDTO>>(llamadaDTOs) {
			}).header(ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/registrarLlamadaSeguimiento/")
	public Response registrarLlamadaSeguimiento(Llamada llamada) {
		Response response = null;
		try {
			String idUsuario = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders).getNombreUsuario();
			llamadaFacade.registrarLlamadaSeguimiento(llamada, idUsuario);
			response = Response.status(Response.Status.OK)
					.header(ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();	
		}  catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/registrarLlamadaCorreo/")
	public Response registrarLlamadaCorreo(LlamadaPlanillaCorrespondenciaDTO llamada) {
		Response response = null;
		try {
			String idUsuario = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders).getNombreUsuario();
			llamadaFacade.registrarLlamadaCorreo(llamada, idUsuario);
			response = Response.status(Response.Status.OK)
					.header(ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();	
		}  catch (SIMASCNegocioExcepcion e) {
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
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/consultarLlamadas/")
	public Response consultarLlamadas(InformacionCorreoDTO informacionCorreoDTO) {
		Response response = null;
		try {
			List<LlamadaDTO> llamadaDTOs = llamadaFacade.gestionCorreosDevueltos(informacionCorreoDTO.getIdCaso(),
					informacionCorreoDTO.getFechaInicial(), informacionCorreoDTO.getFechaFinal());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<LlamadaDTO>>(llamadaDTOs) {
			}).header(ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
