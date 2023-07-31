package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMembresiaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.MembresiaDTO;
import com.ccb.simasc.transversal.entidades.Membresia;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Membresia
 * @author sMartinez
 */
@Path( "membresia" )
@Stateless
public class MembresiaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(MembresiaRecurso.class);
    private static final Class<Membresia> enClass= Membresia.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IMembresiaFacade membresiaFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	
	/**
	 * Crea un nueva membresia
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearMembresia/")
	public Response crearMembresia(MembresiaDTO membresia) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			membresiaFacade.crearMembresia(membresia, idPersonaModificacion);
			response = Response.status(Response.Status.OK)
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
	 * Consulta las membresias según el id de la persona
	 * 
	 * @param membresia
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarMembresias")
	public Response consultarMembresias(MembresiaDTO membresia) {
		Response response = null;
		try {
			List<MembresiaDTO> membresias = membresiaFacade.consultarMembresiasPersona(membresia.getIdPersona());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<MembresiaDTO>>(membresias){
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
