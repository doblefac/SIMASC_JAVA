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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPeticionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.PartePeticionDTO;
import com.ccb.simasc.transversal.dto.PeticionBasicaDTO;
import com.ccb.simasc.transversal.dto.PeticionDTO;
import com.ccb.simasc.transversal.entidades.Peticion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Peticion
 * @author sMartinez
 */
@Path( "peticion" )
@Stateless
public class PeticionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(PeticionRecurso.class);
    private static final Class<Peticion> enClass= Peticion.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IPeticionFacade peticionFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	

	/**
	 * consulta las peticiones activas por caso
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/peticionesPorCaso/{idCaso}")
	public Response peticionesPorCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PeticionBasicaDTO> peticiones = peticionFacade.peticionesPorCaso(idCaso, null);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PeticionBasicaDTO>>(peticiones) {
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
	 * realiza la contestacion de una peticion especial
	 * @param peticionBasicaDTO
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/contestarPeticion/")
	public Response contestarPeticion(PeticionBasicaDTO peticionBasicaDTO) {
		Response response = null;
		try {
			peticionFacade.contestarPeticion(peticionBasicaDTO);
			response = Response.status(Response.Status.OK)
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
	 * Realiza una peticion especial
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/realizarPeticionEspecial/{idCaso}/{idPersona}/{idRol}")
	public Response realizarPeticionEspecial(@PathParam("idCaso") Long idCaso,@PathParam("idPersona") Long idPersona,
			@PathParam("idRol") Long idRol, PeticionDTO peticionEspecial) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			PartePeticionDTO peticionRealizada = peticionFacade.realizarPeticionEspecial(idCaso, idPersona, idRol, peticionEspecial, idPersonaModificacion);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<PartePeticionDTO>(peticionRealizada) {
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
	 * Elimina una peticion especial cuando el cargue del documento falla
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarPeticionEspecial/")
	public Response eliminarPeticionEspecial(PartePeticionDTO peticionEspecial) {
		Response response = null;
	
		try {
			peticionFacade.eliminarPeticionEspecial(peticionEspecial);
			response = Response.status(Response.Status.OK)
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
