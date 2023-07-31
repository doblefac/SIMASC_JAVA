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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ServicioDTO;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Servicio
 * @author sMartinez
 */
@Path( "servicio" )
@Stateless
public class ServicioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ServicioRecurso.class);
    private static final Class<Servicio> enClass= Servicio.class;
   
   	// protected region atributos on begin
   // Escriba en esta sección sus modificaciones
    @EJB
	private IServicioFacade servicioFacade;
   // protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerServicio/{id}")
    public Response consultarServicio(@PathParam("id")Long id)  {
        Response response = null;

        try {
        	Servicio servicio = new ServicioDTO().transformarEntidadSinDependencias(servicioFacade.buscar(id));
            
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<Servicio>(servicio) {
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
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerTodos/")
    public Response consultarTodosLosServicios()  {
        Response response = null;

        try {
        	List<Servicio> servicios = (List<Servicio>)servicioFacade.obtenerEntidadesTodos(new ArrayList<Servicio>(),false);
            
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<Servicio>>(servicios) {
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
	 * Metodo que consulta los servicios dada una lista de filtros 
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarServiciosPorFiltros")
	public Response consultarServiciosPorFiltros(List<InformacionFiltroDTO> filtrosParametro) {
		Response response = null;
		try {
			List<Servicio> servicios = servicioFacade.consultarServiciosFiltros(filtrosParametro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Servicio>>(servicios) {
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
	
	/** CON-C-050
	 * servicio que expone la consulta de los servicios por tipo
	 * @param String tipo: tipo de servicio (PDL o PJT)
	 * @return lista de servicioDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarServiciosPorTipo/{tipo}")
	public Response consultarServiciosPorTipo(@PathParam("tipo") String tipo) {
		Response response = null;
		try {
			List<ServicioDTO> serviciosDTO = servicioFacade.consultarServiciosPorTipo( tipo );

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ServicioDTO>>(serviciosDTO) {
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

	/** CON-F-087
	 * servicio que expone la consulta del servicio de un caso
	 * @param Long idCaso: identificador del caso
	 * @return ServicioDTO: servicio del caso
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarServicioDelCaso/{idCaso}")
	public Response consultarServicioDelCaso( @PathParam("idCaso") Long idCaso ) {
		Response response = null;
		try {
			ServicioDTO servicioDTO = servicioFacade.consultarServicioDelCaso( idCaso );

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<ServicioDTO>(servicioDTO) {
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
	
	
	/** CON-C-050
	 * servicio que expone la consulta de los servicios por los roles que tiene una persona
	 * @param long : id de la persona 
	 * @return lista de servicioDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarServiciosPorPersona/{id_persona}")
	public Response consultarServiciosPorPersona(@PathParam("id_persona") long idPersona) {
		Response response = null;
		try {
			List<ServicioDTO> serviciosDTO = servicioFacade.consultarServiciosPorPersona(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ServicioDTO>>(serviciosDTO) {
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
	 * Marca los roles que aplican para ser creados en MAUC
	 * @param List Rol
	 * @return 
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
	@Path("/modificarServicios")
	public Response modificarRolSiAplicaMauc(List<Servicio> servicios) {
		Response response = null;
		try {
			for (Servicio servicio : servicios) {
				Servicio servicioUpdate = new ServicioDTO().transformarEntidadSinDependencias(
						servicioFacade.buscar(servicio.getIdServicio()));
				
				if(servicioUpdate.getIdServicio() != null) {
					servicioUpdate.setAplicaMauc(servicio.getAplicaMauc());
					servicioFacade.actualizar(servicioUpdate);
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarServiciosParaTarifador")
	public Response consultarServiciosParaTarifador() {
		Response response = null;
		try {
			List<ServicioDTO> serviciosDTO = servicioFacade.consultarServiciosParaTarifador();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ServicioDTO>>(serviciosDTO) {
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
	@Path("/consultarServiciosPorPersonaMateria/{id_persona}")
	public Response consultarServiciosPorPersonaMateria(@PathParam("id_persona") long idPersona) {
		Response response = null;
		try {
			List<ServicioDTO> serviciosDTO = servicioFacade.consultarServiciosPorPersonaMateria(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ServicioDTO>>(serviciosDTO) {
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

}
