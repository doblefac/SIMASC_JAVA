package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

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

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.ConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.RolPersonaDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroConsultaPrestadoresDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoPrestadorDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.reportes.FiltroReportesDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST RolPersona
 * @author sMartinez
 */
@Path( "rolpersona" )
@Stateless
public class RolPersonaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = Logger.getLogger(RolPersonaRecurso.class.toString());
    private static final Class<RolPersona> enClass= RolPersona.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
    @EJB
    private IDominioFacade dominioFacade; 
    
	@EJB
    private IRolPersonaFacade rolPersonaFacade; 
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private IUsuarioFacade usuarioFacade;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones

	/**
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerArbitros/")
	public Response obtenerArbitros() {
		Response response = null;

		try {
			List<Dominio> rolesArbitros = dominioFacade.obtenerDominiosHijos(UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA,
					UtilDominios.AGRUPADOR_ROL_PERSONA_ARBITROS);
			List<String> roles = new ArrayList<String>();
			for (Dominio dominio : rolesArbitros) {
				roles.add(dominio.getDominioPK().getCodigo());
			}

			List<RolPersona> arbitros = rolPersonaFacade.obtenerRolPersonasPorRoles(roles);
			Set<HashMap> arb = new HashSet<HashMap>();
			for (RolPersona rp : arbitros) {
				HashMap<String, Object> arbitro = new HashMap<>();
				arbitro.put("nombre", rp.getPersona().getNombreCompleto());
				arbitro.put("id", rp.getPersona().getIdPersona());
				StringBuilder nombreCompleto =  new StringBuilder(rp.getPersona().getNombreCompleto());
				if (rp.getPersona().getTipoDocumento() != null && rp.getPersona().getNumeroDocumento()!= null){
					nombreCompleto.append("    (" +rp.getPersona().getTipoDocumento() + ". ")
					.append(rp.getPersona().getNumeroDocumento() + ") ");
				}
				  
				arbitro.put("identificacion",nombreCompleto.toString());
				if(!arb.contains(arbitro))
					arb.add(arbitro);
			}
			 
			HashMap[] arr = new HashMap[arb.size()];
			arr = arb.toArray(arr);
			List<HashMap> retorno = Arrays.asList(arr);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<HashMap>>(retorno) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio para asignar un nuevo rol persona a a un usuario
	 * @param rolPersona
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	@Path("/asignarRol/{nombreRol}")
	public Response asignarRol(RolPersona rolPersona, @PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			rolPersonaFacade.asignarRol(rolPersona, nombreRol);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio para actualizar los datos de un rol persona
	 * ADM-C-015
	 * @param rolPersona
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/modificarRol")
	public Response modificarRol(RolPersona rolPersona) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			rolPersona.setIdUsuarioModificacion(idPersonaModificacion);
			rolPersona.setFechaUltimaModificacion(new Date());
			rolPersonaFacade.actualizar(rolPersona);
			
			if(rolPersona.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)) {
				//Validar si quedo algun rol para dejar la bandera mauc encendida o apagada
				if( rolPersona.getIdPersona()!= null) {			
				    Persona persona = new Persona();		    
				    persona = manejadorPersona.buscar(rolPersona.getIdPersona());
					usuarioFacade.validarFuncionarioAplicaMauc(persona);	  		
				}
				
			}
			
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio para obtener los objetos de asociados a la persona
	 * ADM-C-015
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/consultarRolesPersona/{idPersona}")
	public Response consultarRolesPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<RolPersona> rolesPersona = rolPersonaFacade.consultarRolPersona(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolPersona>>(rolesPersona) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio para obtener los centros a los cuales se encuentra asociado un usuario
	 * @param codigoUsuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/obtenerCentrosActor/{codigoUsuario}")
	public Response obtenerCentrosActor(@PathParam("codigoUsuario") Long codigoUsuario) {
		Response response = null;
		try {
			List<CentroDTO> rolesAsignados = (List<CentroDTO>) rolPersonaFacade.obtenerCentrosActor(codigoUsuario);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CentroDTO>>(rolesAsignados) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
     * ADM-C-003 COnsulta lista de prestadores
     * Consulta los prestadores que cumplan los criterios de filtrado que se pasan como parámetro
     * @param filtro
     * @return
     */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/consultarListaPrestadores")
	public Response consultarListaPrestadores(FiltroConsultaPrestadoresDTO filtro) {
		Response response = null;
		try {
			List<InfoPrestadorDTO> infoPrestadores = rolPersonaFacade.consultarListaPrestadores(filtro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<InfoPrestadorDTO>>(infoPrestadores) {
				}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio que obtiene el rol persona para el id del rol y el id de la persona
	 * @param idRol, idPersona
	 * @return entidad tipo RolPersona
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerRolPersona/{idRol}/{idPersona}")
	public Response obtenerRolPorId(@PathParam("idRol") Long idRol, @PathParam("idPersona") Long idPersona) {
		Response response = null;        
        try {
        	List<RolPersona> rolPersonaList = rolPersonaFacade.obtenerRolPersona(idRol, idPersona);        	        
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<RolPersona>>(rolPersonaList){})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
            
        } catch (SIMASCNegocioExcepcion e) {
        	LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
        return response;
	}
	
	/**
	 * ADM-F-030
	 * Servicio que devuelve el listado de personas con rol perito que no se encuentren asociadas a  una persona juridica
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPersonasNaturales/{idPersonaJuridica}")
	public Response consultarPersonasNaturales(@PathParam("idPersonaJuridica") String idPersona) {
		Response response = null;
		Long idPersonaJuridica = null;
		if (!idPersona.equals(UtilConstantes.VALOR_UNDEFINED))
			idPersonaJuridica = Long.valueOf(idPersona);
		try {
			List<Persona> personaList = rolPersonaFacade.consultarPeritoSinAsociar(idPersonaJuridica);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personaList) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}		
	
	
	/**
	 * consulta los conciliadores activos, tengiendo en cuenta la tabla tipo servicio y el estado del
	 * rolpersona caso
	 * @param estadoRpc
	 * @param estadoTipoServicio
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/conciliadoresActivosPorEstado/")
	public Response conciliadoresActivosPorEstado(BusquedaRolesActivosDTO datosConsulta) {
		Response response = null;		
		try {
			List<PersonaBasicaDTO> personaList = rolPersonaFacade.conciliadoresActivosPorEstado(datosConsulta);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personaList) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}	
	
	/**
	 * consulta las persona por centro, vigencia del rpc y lista de centros
	 * @param busquedaRolesActivosDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPesonasBasicaPorRolPersonaCentro/")
	public Response consultarPesonasBasicaPorRolPersonaCentro(BusquedaRolesActivosDTO busquedaRolesActivosDTO) {
		Response response = null;		
		try {
			List<PersonaBasicaDTO> personaList = rolPersonaFacade.consultarPesonasBasicaPorRolPersonaCentro(busquedaRolesActivosDTO.getRoles(),
					busquedaRolesActivosDTO.getCentro(), busquedaRolesActivosDTO.getFechaVigenciaRol());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personaList) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}	
	
	/**
	 * CON-F-118
	 * Servicio que retorna los roles persona de tipo prestador de una persona
	 * @return List<RolPersona>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarRolPersonaPrestadorPersona/{idPersona}")
	public Response consultarRolPersonaPrestadorPersona(@PathParam("idPersona") Long idPersona, FiltroReportesDTO filtros) {
		Response response = null;
		try {
			List<RolPersona> rolesPersona = rolPersonaFacade
					.consultarRolPersonaPrestadorPersonaRolLista(idPersona, 
																 null, UtilDominios.TIPO_LISTA_B,
																 Arrays.asList(UtilDominios.ESTADO_CONCILIADOR_ACTIVO),
																 filtros.getCentros());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolPersona>>(rolesPersona) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}	

	/** ADM-C-004
	 * Servicio que retorna los personas que son de tipo prestador
	 * @return List<ConsultaPrestadoresDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonasPorRolMateria/")
	public Response consultarPersonasPorRolMateria( ConsultaPrestadoresDTO filtros ){
		Response response = null;
		try {
			List<ConsultaPrestadoresDTO> prestadores = rolPersonaFacade.consultarPersonasPorRolMateria( filtros );
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConsultaPrestadoresDTO>>(prestadores) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	
	/**
	 * ADM-F-051
	 * Servicio que retorna los idrolpersona y su respectivo nombre de rol
	 * @return List<RolPersona>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarRolPersona/{idPersona}")
	public Response consultarRolPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<RolPersonaDTO> rolesPersona = rolPersonaFacade.consultarRolPersonaVigentes(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolPersonaDTO>>(rolesPersona) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}	

	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/consultarRolPersonaPorServicio/{idPersona}/{idServicio}")
	public Response consultarRolPersonaPorServicio(@PathParam("idPersona") Long idPersona, @PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			List<RolPersonaDTO> rolesPersona = rolPersonaFacade.consultarRolPersonaPorServicio(idPersona,idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolPersonaDTO>>(rolesPersona) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}


}
