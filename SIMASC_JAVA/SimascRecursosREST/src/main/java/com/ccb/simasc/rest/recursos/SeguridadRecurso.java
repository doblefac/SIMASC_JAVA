/**
 * 
 */
package com.ccb.simasc.rest.recursos;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AutenticacionDTO;
import com.ccb.simasc.transversal.dto.AutenticacionUsuarioDTO;
import com.ccb.simasc.transversal.dto.BodyTokenMaucDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


/**
 * @author dbarrera
 *
 */
@Path("seguridad")
@Stateless
public class SeguridadRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(SeguridadRecurso.class.getName());
	private static final Class enClass = SeguridadRecurso.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	/**
	 * Recurso que provee de los servicios de seguridad
	 */
	@EJB
	private ISeguridadFacade seguridadFacade;
	/**
	 * Recurso que provee los servicios de parametros generales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	/**
	 * Contexto de la secion en front
	 */
	@Context
	HttpHeaders httpHeaders;
	// protected region atributos end

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Servicio de versión
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/version")
	public Response obtenerVersion() {
		Response response = null;
		try {
			response = Response.ok(UtilConstantes.VERSION).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servivio de autenticacion
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/login")
	public Response autenticacion(AutenticacionDTO usuario) {
		Response response = null;
		
		try {
			List<String> metodosAutenticacion = new ArrayList<String>();
			metodosAutenticacion.add(UtilConstantes.METODO_AUTENTICACION_INTRANET);
			metodosAutenticacion.add(UtilConstantes.METODO_AUTENTICACION_LDAP);

			String jwt = seguridadFacade.autenticarUsuario(usuario.getNombreUsuario(), usuario.getParametros(),
					metodosAutenticacion);

			response = Response.ok(jwt).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio que permite bloquear a un usuario
	 * 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/bloquearUsuario/{nombreUsuario}/{pwd}")
	public Response userBlock(@PathParam("nombreUsuario") String usuario, @PathParam("clave") String pwd) {
		Response response = null;
		try {
			seguridadFacade.bloquearUsuario(usuario, pwd);

			response = Response.ok().build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servivio de autenticacion
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/logout")
	public Response logout(AutenticacionDTO usuario) {
		Response response = null;
		try {
			seguridadFacade.cerrarSesionUsuario(usuario.getNombreUsuario());
			response = Response.ok().build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servivio de cambio de contraseña
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cambioClave")
	public Response cambioClave(UsuarioDTO usuario) {
		Response response = null;
		try {
			ContextoDeSesion ctx = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			seguridadFacade.cambioClave(usuario, ctx.getIdUsuario());
			response = Response.ok().build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servivio de generacion de contraseña
	 * 
	 * @param usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/generarClave")
	public Response generarClave(UsuarioDTO usuario) {
		Response response = null;
		try {
			ContextoDeSesion ctx = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			seguridadFacade.generarClave(usuario, ctx.getIdUsuario());
			response = Response.ok().build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servivio de recuperación de contraseña
	 * 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/recuperarClave/{usuario}/{correo}")
	public Response recuperarClave(@PathParam("usuario") String usuario, @PathParam("correo") String correo) {
		Response response = null;
		try {
			seguridadFacade.recuperarClave(usuario, correo);
			response = Response.ok().build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio para determinar disponibilidad del servidor de aplicaciones
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public Response ping() {
		return Response.ok().build();
	}

	/**
	 * Servicio encargado de dar acceso a la aplicación a una persona en el sistema
	 * por número de documento y dirección de correco electrónico
	 * 
	 * ARB-F-111
	 * 
	 * @param numeroDocumento
	 * @param correoElectronico
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/autenticarPersonaRetomaSolicitud/{numeroDocumento}/{correoElectronico}")
	public Response autenticarPersonaRetomaSolicitud(@PathParam("numeroDocumento") String numeroDocumento,
			@PathParam("correoElectronico") String correoElectronico) {
		Response response = null;

		try {
			String respuesta = seguridadFacade.autenticarPersonaRetomaSolicitud(numeroDocumento, correoElectronico);

			response = Response.ok(respuesta).build();
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
	@Path("/aplicaMauc/{numeroDocumento}")
	public Response usuarioAplicalogoutMauc(@PathParam("numeroDocumento") String numeroDocumento) {
		Response response = null;
		try {
			boolean aplica = seguridadFacade.usuarioAplicaMauc(numeroDocumento);
			response = Response.ok(aplica).build();
		}catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/validaMetodoAutenticacionUsuario")
	public Response validametodoAutenticacionUsuario(AutenticacionUsuarioDTO autenticacionUsuarioDTO) {
		Response response = null;
		try {
			String respuesta = seguridadFacade.validaMetodoAutenticacionUsuario(autenticacionUsuarioDTO);
			response = Response.ok(respuesta).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}


	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/loginMauc")
	public Response autenticarUsuarioMauc(BodyTokenMaucDTO tokenMauc) {
		Response response = null;
		try {
			String jwt = seguridadFacade.autenticarUsuarioMauc(tokenMauc.getAccessToken());
			response = Response.ok(jwt).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/urlClaveMauc")
	public Response urlClaveMauc() { 
		Response response = null;
		try {				
			ParametrosGenerales url = manejadorParametrosGenerales.obtenerParametroPorNombre(UtilConstantes.URL_CLAVE_MAUC);  
			response = Response.ok(url.getValorTexto()).build();
		}catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	
	/**
	 * Servicio de contextoSesion
	 * 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/contextoSesion")
	public Response autenticacionContextoSesion() {
		Response response = null;		
		try {
			ContextoDeSesion ctx = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			response = Response.ok(ctx).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/loginC")
	public Response autenticacionC(AutenticacionDTO usuario) {
		Response response = null;
		
		try {
			List<String> metodosAutenticacion = new ArrayList<String>();
			metodosAutenticacion.add(UtilConstantes.METODO_AUTENTICACION_INTRANET);
			metodosAutenticacion.add(UtilConstantes.METODO_AUTENTICACION_LDAP);

			String jwt = seguridadFacade.autenticarUsuario(usuario.getNombreUsuario(), usuario.getParametros(),
					metodosAutenticacion);

			response = Response.ok(jwt).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
}
