package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGastoTribunalFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.GastoTribunalDTO;
import com.ccb.simasc.transversal.entidades.GastoTribunal;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST GastoTribunal
 * @author sMartinez
 */
@Path( "gastotribunal" )
@Stateless
public class GastoTribunalRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(GastoTribunalRecurso.class);
    private static final Class<GastoTribunal> enClass= GastoTribunal.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IGastoTribunalFacade gastoTribunalFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	/**
	 * registra un gasto de tribunal
	 * 
	 * @return response
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/crearGastoTribunal")
	public Response crearGastoTribunal(GastoTribunalDTO gastoTribunalDTO) {
		Response response = null;
		try {
			gastoTribunalFacade.crearGastoTribunal(gastoTribunalDTO,false);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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

	/**
	 * consulta los gastros de tribunal
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarGastosTribunalCaso/{idCaso}")
	public Response consultarGastosTribunalCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			List<GastoTribunalDTO> honorariosFijadosDTO = gastoTribunalFacade.consultarGastosTribunalCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GastoTribunalDTO>>(honorariosFijadosDTO) {
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
	
	/**
	 * actualiza los gastos de tribunal
	 * 
	 * @return response
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarGastoTribunal")
	public Response actualizarGastoTribunal(GastoTribunalDTO gastoTribunalDTO) {
		Response response = null;
		try {
			gastoTribunalFacade.crearGastoTribunal(gastoTribunalDTO,true);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	
	/**
	 * Elimina un gasto de tribunal
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarGastoTribunal/{idCaso}/{idGasto}")
	public Response eliminarGastoTribunal(@PathParam("idCaso") Long idCaso,@PathParam("idGasto") Long idGasto) {
		Response response = null;

		try {
			gastoTribunalFacade.eliminarGastoTribunal(idCaso,idGasto);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
	 * Consulta el total de gastos de tribunal
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarTotales/{idCaso}")
	public Response consultarTotales(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			GastoTribunalDTO gastosTotales = gastoTribunalFacade.consultarTotales(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<GastoTribunalDTO>(gastosTotales) {
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
