package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Telefono
 * @author sMartinez
 */
@Path( "telefono" )
@Stateless
public class TelefonoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TelefonoRecurso.class);
    private static final Class<Telefono> enClass= Telefono.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ITelefonoFacade telefonoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	/**
	 * Se consultan los telefonos registrados de una persona
	 * @param idCaso
	 * @param idPersona
	 * @param idCorreoRolPersonaCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultar/telefonos/{idPersona}")
	public Response consultarTelefonosPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<GenericoDTO> dtos = telefonoFacade.consultarTelefonosPersona(idPersona); 
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GenericoDTO>>(dtos) {
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


}
