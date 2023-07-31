package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILoteGeneradoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.LoteGeneradoDTO;
import com.ccb.simasc.transversal.dto.cartas.GeneracionLoteDTO;
import com.ccb.simasc.transversal.entidades.LoteGenerado;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST LoteGenerado
 * @author sMartinez
 */
@Path( "loteGenerado" )
@Stateless
public class LoteGeneradoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(LoteGeneradoRecurso.class);
    private static final Class<LoteGenerado> enClass= LoteGenerado.class;
   
   	// protected region atributos on begin
   // Escriba en esta sección sus modificaciones
    @EJB
	private ILoteGeneradoFacade loteGeneradoFacade;
   // protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerLoteGenerado/{id}")
    public Response consultarLoteGenerado(@PathParam("id")Long id)  {
        Response response = null;

        try {
        	LoteGenerado loteGenerado = new LoteGeneradoDTO().transformarEntidadSinDependencias(loteGeneradoFacade.buscar(id));
            
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<LoteGenerado>(loteGenerado) {
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
    public Response consultarTodosLosLoteGenerados()  {
        Response response = null;

        try {
        	List<LoteGenerado> loteGenerados = (List<LoteGenerado>)loteGeneradoFacade.obtenerEntidadesTodos(new ArrayList<LoteGenerado>(),false);
            
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<List<LoteGenerado>>(loteGenerados) {
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
    @Path("/consultarLoteGenerado/{idPersona}")
    public Response obtenerLoteGenerado(@PathParam("idPersona") Long id)  {
        Response response = null;
        try {
        	GeneracionLoteDTO lote = loteGeneradoFacade.consultarLote(id);
        	response = Response.status(Response.Status.OK)
                    .entity(new GenericEntity<GeneracionLoteDTO>(lote) {
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
    

	// protected region metodos adicionales end

}
