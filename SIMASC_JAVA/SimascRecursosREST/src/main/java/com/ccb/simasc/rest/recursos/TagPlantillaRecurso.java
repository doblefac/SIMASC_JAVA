package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITagPlantillaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.TagPlantilla;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST TagPlantilla
 * @author sMartinez
 */
@Path( "tagplantilla" )
@Stateless
public class TagPlantillaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TagPlantillaRecurso.class);
    private static final Class<TagPlantilla> enClass= TagPlantilla.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ITagPlantillaFacade tagPlantillaFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	   /**
  * Obtiener tags Plantillas por tipoServicio
  * con las dependencias correspondientes.
  * @return
  */
	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/obtieneTagplantilla/{tipoServicio}")
	    public Response obtenerPlantillasFiltros(@PathParam("tipoServicio") String tipoServicio,@PathParam("idPlantilla") Long idPlantilla) {
	        Response response = null;
	        try {

	        	List<TagPlantilla> tagsPorTipoServicio =  tagPlantillaFacade.obtieneTagPorTipoServicio(tipoServicio);
	        	
	        	response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<List<TagPlantilla>>(tagsPorTipoServicio) {
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

   // protected region metodos adicionales end


}
