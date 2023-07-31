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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSedeFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.ParametrosSedesRepartoDTO;
import com.ccb.simasc.transversal.dto.PersonaSedeDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.entidades.PersonaSede;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST PersonaSede
 * @author sMartinez
 */
@Path( "personasede" )
@Stateless
public class PersonaSedeRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(PersonaSedeRecurso.class);
    private static final Class<PersonaSede> enClass= PersonaSede.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IPersonaSedeFacade personaSedeFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          

    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/**
	 * 
	 * @param personaSedeDTOs
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarSedesPreferenciaConciliador")
	public Response registrarSedesPreferenciaConciliador(List<PersonaSedeDTO> personaSedeDTOs) {
		Response response = null;

		try {
			personaSedeFacade.registrarSedesPreferenciaConciliador(personaSedeDTOs);
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
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPersonaSedesPorPersona/{idPersona}")
	public Response obtenerPersonaSedesPorPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<PersonaSedeDTO> personaSedeDTOs = personaSedeFacade.obtenerPersonaSedesPorPersona(idPersona);			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaSedeDTO>>(personaSedeDTOs) {
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
	 * Metodo que que retorna una lista de sedes correspondeintes
	 * a una persona y un tipo de sede
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesPorPersonaYTipoSede/{idPersona}/{tipoSede}")
	public Response obtenerSedesPorPersonaYTipoSede(@PathParam("idPersona")Long idPersona, @PathParam("tipoSede")String tipoSede) {
		Response response = null;
		try {
			List<SedeDTO> sedesDTO = personaSedeFacade.obtenerSedesPorPersonaYTipoSede(idPersona, tipoSede);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedesDTO) {
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
	 * Retorna una lista de sedes de acuerdo a los parametros que se ingresen en el sistema
	 * @param paramSede
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesPorParametros/")
	public Response obtenerSedesPorParametros(ParametrosSedesRepartoDTO parametrosSedesReparto) {
		Response response = null;
		try {
			List<SedeDTO> sedes = personaSedeFacade.obtenerSedesPorParametros(parametrosSedesReparto);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedes) {
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesPorCentros/")
	public Response obtenerSedesPorCentros(List<CentroDTO> centros) {
		Response response = null;
		try {
			List<SedeDTO> sedes = personaSedeFacade.obtenerSedesPorCentros(centros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedes) {
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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesPorPersonaYTipoSedeActivas/{idPersona}/{tipoSede}/{idServicio}")
	public Response obtenerSedesPorPersonaYTipoSedeActivas(@PathParam("idPersona")Long idPersona, @PathParam("tipoSede")String tipoSede
			,@PathParam("idServicio")Long idServicio) {
		Response response = null;
		try {
			List<SedeDTO> sedesDTO = personaSedeFacade.obtenerSedesPorPersonaYTipoSedeActivas(idPersona, tipoSede, idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedesDTO) {
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
