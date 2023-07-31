package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaMascaraDTO;
import com.ccb.simasc.transversal.dto.UsuarioClaveDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.UsuarioSistemaConsultaDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Usuario
 * @author sMartinez
 */
@Path( "usuario" )
@Stateless
public class UsuarioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(UsuarioRecurso.class);
    private static final Class<Usuario> enClass= Usuario.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IUsuarioFacade usuarioFacade; 
	@EJB
	private IPersonaFacade personaFacade;
	// protected region atributos end
	
	@EJB
	private IRolPersonaCasoFacade rolPersonaFace;
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	
	/**
	 * Servicio que retorna todos los usuarios 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerUsuarios")
	public Response obtenerUsuarios() {
		Response response = null;

		try {
			List<UsuarioDTO> allUsers = (List<UsuarioDTO>) usuarioFacade.obtenerTodos(new ArrayList<UsuarioDTO>(), false);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UsuarioDTO>>(allUsers) {
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
	 * Servicio que retorna todos los usuarios 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerUsuarioId/{usuarioId}")
	public Response obtenerUsuario(@PathParam("usuarioId") String usuarioId) {
		Response response = null;

		try {
			Persona persona = new Persona();
			Usuario user =   usuarioFacade.buscar(usuarioId); 
			if(user == null){
				throw new SimascException("No existe ningún usuario registrado en el sistema con el número de identificación ingresado, por favor verifique la información ingresada e intente de nuevo");
			}else{
				persona = new PersonaDTO().transformarEntidadConDependencias(personaFacade.buscar(user.getIdPersona()));
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Persona>(persona) {
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
	 * Servicio que retorna todos los usuarios 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerUsuarioConpersonaPorId/{usuarioId}")
	public Response obtenerUsuarioyPersonaPorId(@PathParam("usuarioId") String usuarioId) {
		Response response = null;

		try {
			
			Usuario user =   usuarioFacade.buscar(usuarioId); 
			if(user == null){
				throw new SimascException("No existe ningún usuario registrado en el sistema con el número de identificación ingresado, por favor verifique la información ingresada e intente de nuevo");
			}else{
				user  = new UsuarioDTO().transformarEntidadConDependencias(user);				
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Usuario>(user) {
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
	 * Servicio que retorna la información de los usuarios del sistema a mostrar en la consulta definida en ADM-C-020
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarUsuariosSistema/{nombreRolUsuario}")
	public Response consultarUsuariosSistemaConsulta(@PathParam("nombreRolUsuario") String nombreRolUsuario, @QueryParam("nombreRol") String nombreRol, 
								@QueryParam("nombres") String nombres, @QueryParam("apellidos") String apellidos) {
		Response response = null;

		try {			
			List<UsuarioSistemaConsultaDTO> usuariosDTO = usuarioFacade.consultarUsuariosSistema(nombres, apellidos, nombreRol, nombreRolUsuario);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UsuarioSistemaConsultaDTO>>(usuariosDTO) {
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
	 * Servcio encargado de crear el usuario de una de las partes
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/crearUsuarioParte/{idPersona}")
	public Response crearUsuarioParte(@PathParam("idPersona") Long idPersona) {
		Response response = null;

		try {			
			usuarioFacade.crearUsuarioParte(idPersona);
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
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/obtenerCentrosActor/{codigoUsuario}")
	public Response obtenerCentrosActor(@PathParam("codigoUsuario") Long codigoUsuario) {
		Response response = null;
		try {
			List<CentroDTO> rolesAsignados = (List<CentroDTO>) usuarioFacade.obtenerCentrosActor(codigoUsuario);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CentroDTO>>(rolesAsignados) {
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
	 * ADM-C-021
	 * Servicio que crea el usuario, a la persona asociada al usuario en caso de que no exista y un rol en caso de que no lo tenga asignado, en cuyo caso
	 * devuelve el id de la persona creada.
	 * Devuelve -2 si el usuario ya existía pero no tenía el rol definido por el usuario administrador.
	 * Lanza excepción Si el usuario ya existía y tenía asignado el rol definido por el usuario.
	 * @param usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearUsuarioSistema/")
	public Response crearUsuarioSistema(FormularioParteDTO usuarioSistemaDTO) {
		Response response = null;
		try {			
			Long codigo = usuarioFacade.crearUsuarioSistema(usuarioSistemaDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(codigo) {
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
	 * ADM-C-021
	 * Consulta la información de usuario del sistema
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarUsuarioSistema/{tipoIdentificacion}/{numeroIdentificacion}")
	public Response consultarUsuarioSistema(@PathParam("tipoIdentificacion") String tipoIdentificacion,
							@PathParam("numeroIdentificacion") String numeroIdentificacion) {
		Response response = null;

		try {			
			FormularioParteDTO usuario = usuarioFacade.consultarUsuarioSistema(tipoIdentificacion, numeroIdentificacion);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<FormularioParteDTO>(usuario) {
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
	 * Servicio encargado de consultar la información básica de un usuario por
	 * el identificador de persona
	 * 
	 * ADM-F-031
	 * 
	 * @param tipoIdentificacion
	 * @param numeroIdentificacion
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarUsuarioPorIdPersona/{idPersona}")
	public Response consultarUsuarioPorIdPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;

		try {
			FormularioParteDTO formularioParteDTO = usuarioFacade.consultarUsuarioPorIdPersona(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEstadoUsuario/{idPersona}")
	public Response consultarEstadoUsuario( @PathParam("idPersona") Long idPersona){		
		Response response = null;
		
		try{
			List<UsuarioClaveDTO>  estadosUsuario = usuarioFacade.consultarEstadoUsuario(idPersona);			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<UsuarioClaveDTO>>(estadosUsuario) {
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
	 * @param usuario
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstadoUsuario")
    public Response cambiarEstadoUsuario(UsuarioClaveDTO usuario) {
        Response response = null;
        try { 
        	 usuarioFacade.cambiarEstadoUsuario(usuario);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	 * Desbloquea la clave del usuario por parametro
	 * @param usuario
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/desbloquearClaveUsuario/")
    public Response desbloquearClaveUsuario(UsuarioClaveDTO usuario) {
        Response response = null;
        try { 
        	 usuarioFacade.desbloquearClaveUsuario(usuario);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	
	/**
	 * Servicio que retorna todos los usuarios 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerUsuarioEnmascaradoById/{usuarioId}")
	public Response obtenerUsuarioEnmascarado(@PathParam("usuarioId") String usuarioId) {
		Response response = null;

		try {
			PersonaMascaraDTO personaMascara = new PersonaMascaraDTO();
			Usuario user =   usuarioFacade.buscar(usuarioId); 
			if(user == null){
				throw new SimascException("No existe ningún usuario registrado en el sistema con el número de identificación ingresado, por favor verifique la información ingresada e intente de nuevo");
			}else{
				personaMascara = new PersonaDTO().transformarEntidadEnmascarandoConDependencias(personaFacade.buscar(user.getIdPersona()));
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<PersonaMascaraDTO>(personaMascara) {
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
