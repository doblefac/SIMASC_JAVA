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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.NotificacionEnviadaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.InformacionCorreoDTO;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST CorreoRolPersonaCaso
 * @author sMartinez
 */
@Path( "correorolpersonacaso" )
@Stateless
public class CorreoRolPersonaCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CorreoRolPersonaCasoRecurso.class);
    private static final Class<CorreoRolPersonaCaso> enClass= CorreoRolPersonaCaso.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * envía correos
	 * 
	 * @param ParametrosEnvioCorreoDTO
	 * @return true si fue enviado
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/enviarCorreo")
	public Response enviarCorreo(ParametrosEnvioCorreoDTO parametrosEnvio) {
		Response response = null;
		try {
			boolean enviado = correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(enviado) {
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
	 * Consulta correos enviados
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaNotificaciones/{id}")
	public Response consultarNotificaciones(@PathParam("id") Long idCaso) {
		Response response = null;
		try {
			List<NotificacionEnviadaDTO> notificaciones = correoRolPersonaCasoFacade.buscarCorreosEnviados(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<NotificacionEnviadaDTO>>(notificaciones) {
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
	 * Consulta los acuse de un correo que se envio
	 * @param idCorreoRolPersonaCaso
	 * @param tipoAcuse
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaAcuse/{id}/{tipo}")
	public Response consultarAcuse(@PathParam("id") Long idCorreoRolPersonaCaso, @PathParam("tipo") String tipoAcuse) {
		Response response = null;
		try {
			String notificacion = correoRolPersonaCasoFacade.obtenerAcuse(idCorreoRolPersonaCaso, tipoAcuse);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(notificacion) {
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
	 * Servicio que obtiene la informacion de Personas que registran falla de
	 * envio en los correos electronicos
	 * 
	 * @param tipoAcuse
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInformacionCorreos/devueltos/")
	public Response consultarInformacionCorreosDevueltos() {
		Response response = null;
		try {
			List<InformacionCorreoDTO> informacionCorreoDTOs = correoRolPersonaCasoFacade
					.obtenerInformacionCorreosDevueltos();
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<InformacionCorreoDTO>>(informacionCorreoDTOs) {
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
