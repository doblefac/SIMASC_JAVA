package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.Date;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DiaFestivoDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.entidades.DiaFestivo;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST DiaFestivo
 * @author sMartinez
 */
@Path( "diafestivo" )
@Stateless
public class DiaFestivoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(DiaFestivoRecurso.class);
    private static final Class<DiaFestivo> enClass= DiaFestivo.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IDiaFestivoFacade diaFestivoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/calcularDiasEntreDosFechas/{fechaInicio}/{fechaFin}")
	public Response calcularDiasEntreDosFechas( @PathParam("fechaInicio") String fechaInicio, @PathParam("fechaFin") String fechaFin ){		
		Response response = null;
		Integer diasHabiles = 0;
		
		try {
			if (fechaInicio != null && fechaFin != null){
				Date fechaI = new Date(Long.valueOf(fechaInicio));
				Date fechaF = new Date(Long.valueOf(fechaFin));
				diasHabiles = diaFestivoFacade.calcularDiasEntreDosFechas(fechaI, fechaF);
			}			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Integer>(diasHabiles) {
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
	@Path("/consultarDiasFestivos")
	public Response consultarDiasFestivos(){		
		Response response = null;
		List<DiaFestivoDTO> diasFestivo;
		try {
		
			diasFestivo = diaFestivoFacade.consultarDiasFestivos();					
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DiaFestivoDTO>>(diasFestivo) {
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
	@Path("/obtenerDiasFestivosEntreFechas")
	public Response consultarFestivosEntreFechas(ProgramacionAudienciaDTO programacion) {
		Response response = null;
		try {
			
			List<Date> diasFestivo = diaFestivoFacade.consultarFestivosEntreFechas(programacion);					
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Date>>(diasFestivo) {
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
