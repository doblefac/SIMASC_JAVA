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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DominioBasicoDTO;
import com.ccb.simasc.transversal.dto.RolBasicoDTO;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


// protected region imports recurso end

/**
 * Recurso REST Rol
 * @author sMartinez
 */
@Path( "rol" )
@Stateless
public class RolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(RolRecurso.class);
    private static final Class<Rol> enClass= Rol.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IRolFacade rolFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerRoles/")
    public Response obtenerRoles() {
        Response response = null;        
        try {
        	List<Rol> roles = (List<Rol>)rolFacade.obtenerEntidadesTodos(new ArrayList<Rol>(), false);            
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<Rol>>(roles) {})
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
	 * Obtiene el rango de funcionalidades y perfiles de la aplicación
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerMapaFucionalidades/")
    public Response consultarMapaFucionalidades() {
        Response response = null;

        try {
        	
        	List<Rol> roles = rolFacade.obtenerMapaFuncionalidades();     	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<Rol>>(roles) {
                     })
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
	 * Obtiene los perfiles asociados a un usuario
	 * @param persona
	 * @param idCaso
	 * @param rolPersona
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/obtenerRolesPorPersona")
	public Response consultarPerfilesPorUsusario(Long idPersona) {
		Response response = null;
		try {
			List<Rol> roles = rolFacade.obtenerRolesPorPersona(idPersona);
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<Rol>>(roles) {
                     })
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
	 * Servicio encargado de obtener el rol de una persona que esta retomando
	 * una solicitud
	 * 
	 * ARB-F-111
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerRolPersonaSolicitante/{idPersona}")
	public Response consultarRolPersonaSolicitante(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			Rol rol = rolFacade.obtenerRolPersonaSolicitante(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Rol>(rol) {
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
	 * ADM-C-020
	 * Consulta la lista de roles por los que puede filtrar el usuario en la funcionalidad de consuta de usuarios dependiendo del rol que tenga
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerRolesConsultaUsuarios/{nombreRolUsuario}")
    public Response consultarListaRolesParaConsultaUsuarios(@PathParam("nombreRolUsuario") String nombreRolUsuario) {
        Response response = null;

        try {
        	
        	List<RolBasicoDTO> roles = rolFacade.consultarListaRolesParaConsultaUsuarios(nombreRolUsuario);     	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<RolBasicoDTO>>(roles) {
                     })
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
	 * ADM-C-020
	 * Consulta los roles del agrupador que se pasa como parámetro
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerRolesPorAgrupador/{codigoAgrupador}")
    public Response consultarRolesPorAgrupador(@PathParam("codigoAgrupador") String codigoAgrupador) {
        Response response = null;

        try {        	
        	List<DominioBasicoDTO> roles = rolFacade.consultarRolesPorClasificador(codigoAgrupador);     	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<DominioBasicoDTO>>(roles) {
                     })
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
	 * ADM-C-013
	 * Consultar los roles que se encuentran asociados a un servicio
	 * @param idServicio
	 * @return listado de roles
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarRolesPorServicio/{tipoServicio}")
	public Response consultarRolesPorServicio(@PathParam("tipoServicio") String tipoServicio) {
		Response response = null;
		try {
        	List<RolDTO> roles = rolFacade.consultarRolesPorServicio(tipoServicio);     	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<RolDTO>>(roles) {
                     })
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
	 * Servicio para crear un nuevo rol asociado a un tipo de servicio
	 * @param rol
	 * @param tipoServicio
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/crearRol/{tipoServicio}")
	public Response crearRol (RolDTO rol, @PathParam("tipoServicio") String tipoServicio) {
		Response response = null;
		try {
        	Long idRol = rolFacade.crearRol(rol, tipoServicio);    	
        	response = Response.status(Response.Status.OK)
        			.entity(new GenericEntity<Long>(idRol) {
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
	 * ADM-C-003
	 * Consulta los roles que definen lista, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_lista en verdadero.
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerRolesDefinenLista/")
    public Response obtenerRolesQueDefinenLista() {
        Response response = null;        
        try {
        	List<String> roles = rolFacade.consultarNombreRolesQueManejanLista();         
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<String>>(roles){})
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
	 * ADM-C-003
	 * Consulta los roles que definen materia, es decir, aquellos definidos en la tabla
	 * ParametroServicioSorteo con el indicador sorteo_con_materia en verdadero.
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerRolesDefinenMateria/")
    public Response obtenerRolesQueDefinenMateria() {
        Response response = null;        
        try {
        	List<String> roles = rolFacade.consultarNombreRolesQueManejanMateria();         
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<String>>(roles){})
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
	 * servicio para obtener un rol por el id
	 * @param idRol
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerRolPorId/{idRol}")
	public Response obtenerRolPorId(@PathParam("idRol") Long idRol) {
		Response response = null;        
        try {
        	Rol rol = rolFacade.buscar(idRol);         
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<Rol>(rol){})
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
	
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/consutarRolesBasicosPorListaTipoServicio/{transversales}")
	public Response consutarRolesBasicosPorListaTipoServicio(List<String> tipoServicio, @PathParam("transversales") Boolean transversales) {
		Response response = null;
		try {
        	List<RolBasicoDTO> roles = rolFacade.consutarRolesBasicosPorListaTipoServicio(tipoServicio, transversales);  	
        	response = Response.status(Response.Status.OK)
        			.entity(new GenericEntity<List<RolBasicoDTO>>(roles) {
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
	 * método que obtiene los servicios asociados a un rol dado en la tabla Parametro_servicio_sorteo
	 *  @param codigoRol
	 */

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerServiciosPorRolSorteo/{codigoRol}")
	public Response obtenerServiciosPorRolSorteo(@PathParam("codigoRol") Long codigoRol) {
		Response response = null;        
		try {
			List<Long> listaServicios = rolFacade.obtenerServiciosPorRolSorteo(codigoRol);         
			response = Response.status(Response.Status.OK)        		
					.entity(new GenericEntity<List<Long>>(listaServicios){})
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
	 * servicio para obtener los roles por idpersona
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarRolesPorPersona/{idPersona}")
	public Response consultarRolesPorPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;        
        try {
        	List<RolDTO> roles = rolFacade.consultarRolesPorPersona(idPersona);         
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<RolDTO>>(roles){})
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
	 * servicio para obtener los roles por idpersona
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/consultarRolesSorteo/")
	public Response consultarRolesSorteo() {
		Response response = null;        
        try {
        	List<RolDTO> roles = rolFacade.consultarRolesSorteo();         
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<RolDTO>>(roles){})
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
   // protected region metodos adicionales end
	
	/**
	 * Marca los roles que aplican para ser creados en MAUC
	 * @param List Rol
	 * @return 
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
	@Path("/modificarRol")
	public Response modificarRolSiAplicaMauc(List<Rol> roles) {
		Response response = null;
		try {
			
			for (Rol rol : roles) {
				Rol rolUpdate = new Rol();
				rolUpdate =	new RolDTO().transformarEntidadSinDependencias(rolFacade.buscar(rol.getIdRol()));
				
				if(rolUpdate.getIdRol() != null) {
					rolUpdate.setAplicaMauc(rol.getAplicaMauc());
					rolFacade.actualizarRol(rolUpdate);
				}
			}

			  response = Response.status(Response.Status.OK)     
					  .entity(new GenericEntity<String>("OK") {})
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
	

}
