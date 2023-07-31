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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorSala;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones.InfraestructuraSalaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgendamientoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISalaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SalaDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Sala
 * @author sMartinez
 */
@Path( "sala" )
@Stateless
public class SalaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SalaRecurso.class);
    private static final Class<Sala> enClass= Sala.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ISalaFacade salaFacade; 
	
	@EJB
    private IAgendamientoFacade agendamientoFacade;
	
	@EJB
	private ManejadorSala manejadorSala;
	
	@EJB
	private InfraestructuraSalaFacade infraestructuraSalaFacade;
 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * Se obtienen todas las salas activas con id y nombre de la sala
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerSalas")
    public Response ejemplo() {
        Response response = null;
        try {
        	List<GenericoDTO> genericoDTOs = salaFacade.obtenerSalas();
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<GenericoDTO>>(genericoDTOs) {
                     })
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
	 * Permite buscar salas segun los parametros que se le envien
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
    @Path("/buscarSalaDisponibles")
    public Response buscarSalaDisponibles(FiltrosSalasDTO filtrosSalasDTO) {
        Response response = null;
        try { 
        	List<SalaDTO> salaDTOs = salaFacade.buscarSalaDisponibles(filtrosSalasDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SalaDTO>>(salaDTOs) {
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
	 * Permite buscar salas segun los parametros que se le envien
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/buscarSalaPorSede/{idSede}")
    public Response buscarSalaPorSede(@PathParam("idSede") Long idSede) {
        Response response = null;
        try {
        	   		
    		InformacionFiltro filtroTipoServicio = new InformacionFiltro(TipoFiltro.EXACTO,
    				Sala.ENTIDAD_SALA_ID_SEDE,
    				idSede);
        	List<InformacionFiltro> filtrosSalasConsulta = new ArrayList<>();
        	filtrosSalasConsulta.add(filtroTipoServicio);
        	 
        	List<Sala> sala = manejadorSala.consultar(filtrosSalasConsulta, null, null);
        	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<Sala>>(sala) {
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
	 * Guarda la información de una nueva sede
	 * @param paramSede
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/crearSala")
	public Response nuevaSala(Sala sala) {
		Response response = null;
		try {
			salaFacade.crear(sala);
			response = Response.status(Response.Status.OK).entity(
					new GenericEntity<Long>(sala.getIdSala()) {
            }).header(
					"Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			
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
	 * Guarda la información de una nueva sede
	 * @param paramSede
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/crearSalaInfraestructura")
	public Response crearSalaInfraestructura(List<InfraestructuraSala> infaSala) {
		Response response = null;
		try {
			for (InfraestructuraSala element : infaSala) {
				infraestructuraSalaFacade.crear(element);
			}
			response = Response.status(Response.Status.OK).header(
					"Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			
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
	 * Guarda la información de una nueva sala
	 * @param paramSede
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/modificarSala")
	public Response modificarSala(Sala sala) {
		Response response = null;
		try {
			salaFacade.actualizar(sala);
			response = Response.status(Response.Status.OK).header(
					"Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			
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
	 * Guarda la información de una nueva sede
	 * @param paramSede
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/modificarSalaInfraestructura")
	public Response modificarSalaInfraestructura(List<InfraestructuraSala> infaSala) {
		Response response = null;
		try {
			
			salaFacade.modificarInfraestructuraSala(infaSala);
			response = Response.status(Response.Status.OK).header(
					"Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
//			}
			
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
     * Retorna la lista de sedes para la pantalla de Administracion consultadas por id
     * con las dependencias correspondientes.
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getSalaInfo/{idSala}")
    public Response getSalaInfo(@PathParam("idSala") Long idSala) {
        Response response = null;
        try {
        	   		
    		InformacionFiltro filtrossala = new InformacionFiltro(TipoFiltro.EXACTO,
    				Sala.ENTIDAD_SALA_PK,
    				idSala);
        	List<InformacionFiltro> filtrosSalaConsulta = new ArrayList<>();
        	filtrosSalaConsulta.add(filtrossala);
        	
        	Sala sala = salaFacade.buscar(idSala);
        	sala = new SalaDTO().transformarEntidadConDependencias(sala);
        	
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<Sala>(sala) {
                     })
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
