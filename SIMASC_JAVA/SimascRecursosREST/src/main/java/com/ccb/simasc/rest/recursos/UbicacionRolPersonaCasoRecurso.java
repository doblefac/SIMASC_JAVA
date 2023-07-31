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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST UbicacionRolPersonaCaso
 * @author sMartinez
 */
@Path( "ubicacionrolpersonacaso" )
@Stateless
public class UbicacionRolPersonaCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(UbicacionRolPersonaCasoRecurso.class);
    private static final Class<UbicacionRolPersonaCaso> enClass= UbicacionRolPersonaCaso.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IUbicacionRolPersonaCasoFacade ubicacionRolPersonaCasoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	/**
	 * 
	 * 
	 * @param ubicacionRolPersonaCasoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarUbicacionRolPersonaCaso/")
	public Response adicionarUbicacionRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		Response response = null;
		try {
			UbicacionDTO ubicacion = ubicacionRolPersonaCasoFacade.asociarUbicacionRolPersonaCaso(ubicacionPersonaCasoDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<UbicacionDTO>(ubicacion){
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ubicacionescasopersona/")
	public Response consultarUbicacioneCasoRolPersona(UbicacionPersonaCasoDTO rolPersonaCaso) {
		Response response = null;
		try {
			List<UbicacionDTO> ubicacion = ubicacionRolPersonaCasoFacade.consultarUbicacionesRolPersonaCaso(rolPersonaCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UbicacionDTO>>(ubicacion){
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
