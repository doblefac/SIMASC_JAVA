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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IObligacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SeguimientoObligacionesDTO;
import com.ccb.simasc.transversal.entidades.Obligacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Obligacion
 * @author sMartinez
 */
@Path( "obligacion" )
@Stateless
public class ObligacionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ObligacionRecurso.class);
    private static final Class<Obligacion> enClass= Obligacion.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IObligacionFacade obligacionFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarObligacionesCaso/{idCaso}")
	public Response consultarObligacionesCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<SeguimientoObligacionesDTO> obligaciones = obligacionFacade
					.consultarObligacionesCaso(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<SeguimientoObligacionesDTO>>(obligaciones) {
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
