package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST EventoRolPersonaCaso
 * @author sMartinez
 */
@Path( "eventorolpersonacaso" )
@Stateless
public class EventoRolPersonaCasoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EventoRolPersonaCasoRecurso.class);
    private static final Class<EventoRolPersonaCaso> enClass= EventoRolPersonaCaso.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IEventoRolPersonaCasoFacade eventoRolPersonaCasoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	/**
	 * metodo que consulta los EventoRolPersona caso activos de un caso seleccionado, filtrado por etapa del caso y estado de la 
	 * persona dentro del caso.
	 * @param casosAsignadosDTO
	 * @return eventoRolPersonaCaso
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eventoRpcAsignado/")
	public Response eventoRpcAsignado(CasosAsignadosDTO casosAsignadosDTO) {
		Response response = null;
		try {

			List<CasosAsignadosDTO> eventosAsignados =	eventoRolPersonaCasoFacade.eventoRpcAsignado(casosAsignadosDTO);
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasosAsignadosDTO>>(eventosAsignados){})
							.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
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
