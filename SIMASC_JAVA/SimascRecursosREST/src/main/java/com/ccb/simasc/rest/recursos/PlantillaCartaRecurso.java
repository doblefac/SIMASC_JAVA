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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.PlantillaCartaDTO;
import com.ccb.simasc.transversal.dto.PlantillaVariablesCartaEditorDTO;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST PlantillaCarta
 * @author sMartinez
 */
@Path( "plantillacarta" )
@Stateless
public class PlantillaCartaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(PlantillaCartaRecurso.class);
    private static final Class<PlantillaCarta> enClass= PlantillaCarta.class;
   
   	// protected region atributos on begin
	   // Escriba en esta sección sus modificaciones

	    
	    @EJB
	    private IPlantillaCartaFacade plantillaCartaFacade;
    
    
	   // protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPlantillas/")
	public Response consultarPlantillas() {
		Response response = null;

		try {

			List<PlantillaCarta> plantillaCartaList = plantillaCartaFacade.obtenerPlantillasCartas();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PlantillaCarta>>(plantillaCartaList) {
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
     * Obtiene Plantillas por filtro
     * con las dependencias correspondientes.
     * @return
     */
	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/obtenerPlantillasFiltros/{tipoServicio}/{idPlantilla}")
	    public Response obtenerPlantillasFiltros(@PathParam("tipoServicio") String tipoServicio,@PathParam("idPlantilla") Long idPlantilla) {
	        Response response = null;
	        try {

	        	List<PlantillaCarta> plantillasFiltradas = plantillaCartaFacade.obtenerPlantillasFiltros(tipoServicio, idPlantilla);
	        	
	        	response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<List<PlantillaCarta>>(plantillasFiltradas) {
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
		 * Guarda la información de una nueva plantilla
		 * @param paramSede
		 * @return
		 */
		@POST
	    @Produces({MediaType.APPLICATION_JSON})
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Path("/crearPlantillaCarta")
		public Response crearPlantillaCarta(PlantillaVariablesCartaEditorDTO plantilla) {
			Response response = null;
			try {
				Long idPlantillaCreada = plantillaCartaFacade.crearPlantillaCarta(plantilla);
				response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<Long>(idPlantillaCreada) {
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
	     * Obtiene una PlantillaCarta por caso y nombre de plantilla
	     * @return
	     */
	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/consultarPlantillaServicioCaso/{idCaso}/{nombrePlantilla}")
	    public Response consultarPlantillaServicioCaso( @PathParam("idCaso") Long idCaso, @PathParam("nombrePlantilla") String nombrePlantilla) {
	        Response response = null;
	        try {

	        	PlantillaCartaDTO plantillaCartaDTO = plantillaCartaFacade.consultarPlantillaServicioCaso(idCaso, nombrePlantilla);
	        	
	        	response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<PlantillaCartaDTO>(plantillaCartaDTO) {
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
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/consultarPlantillaServicioNombre/{idServicio}/{idCaso}/{nombrePlantilla}")
	    public Response consultarPlantillaServicioNombre( @PathParam("idServicio") Long idServicio,@PathParam("idCaso") Long idCaso ,@PathParam("nombrePlantilla") String nombrePlantilla) {
	        Response response = null;
	        try {

	        	PlantillaCartaDTO plantillaCartaDTO = plantillaCartaFacade.consultarPlantillaServicioNombre(idServicio, nombrePlantilla, idCaso);
	        	
	        	response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<PlantillaCartaDTO>(plantillaCartaDTO) {
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

	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/obtenerPlantillasEquidad")
	    public Response obtenerPlantillasEquidad() {
	        Response response = null;
	        try {

	        	List<PlantillaCarta> plantillasFiltradas = plantillaCartaFacade.obtenerPlantillasEquidad();
	        	
	        	response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<List<PlantillaCarta>>(plantillasFiltradas) {
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
