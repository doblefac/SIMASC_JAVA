package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFechaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.formularios.HitosCasoDTO;
import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST FechaCaso
 * @author sMartinez
 */
@Path( "fechacaso" )
@Stateless
public class FechaCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(FechaCasoRecurso.class);
    private static final Class<FechaCaso> enClass= FechaCaso.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IFechaCasoFacade fechaCasoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	
	/**
	 *Obtiene los hitos de un caso
	 * @param id
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerHitosCaso/{idCaso}")
	public Response obtenerHitosCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {				
			HitosCasoDTO hitos = fechaCasoFacade.obtenerHitosCaso(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<HitosCasoDTO>(hitos) {
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
	 *Obtiene los hitos de un caso
	 * @param id
	 * @return
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarHitosCaso/")
	public Response actualizarHitosCaso(HitosCasoDTO hitos) {
		Response response = null;
		try {				
			fechaCasoFacade.actualizarHitosCaso(hitos);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<String>("ok") {
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
