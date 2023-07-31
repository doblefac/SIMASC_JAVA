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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITarifaContratoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.TarifaContratoDTO;
import com.ccb.simasc.transversal.entidades.TarifaContrato;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST TarifaContrato
 * @author sMartinez
 */
@Path( "tarifacontrato" )
@Stateless
public class TarifaContratoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TarifaContratoRecurso.class);
    private static final Class<TarifaContrato> enClass= TarifaContrato.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ITarifaContratoFacade tarifaContratoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	
	/**
	 * Consulta tarifas de contrato
	 * 
	 * @param tarifaContrato
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarTarifasContrato")
	public Response consultarTarifasContrato(TarifaContratoDTO tarifaContrato) {
		Response response = null;
		try {
			List<TarifaContratoDTO> tarifas = tarifaContratoFacade.consultarTarifasContrato(tarifaContrato);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<TarifaContratoDTO>>(tarifas){
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
	 * Crear Tarifas de contrato
	 * 
	 * @param tarifaContrato
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearTarifasContrato")
	public Response crearTarifasContrato(List<TarifaContrato> tarifasContrato) {
		Response response = null;
		try {
			tarifaContratoFacade.crearTarifasContrato(tarifasContrato);
			response = Response.status(Response.Status.OK).
					header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
	 * Actualiza tarifas del contrato
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarTarifasContrato/")
	public Response actualizarTarifasContrato(List<TarifaContratoDTO> tarifasContrato) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			tarifaContratoFacade.actualizarTarifasContrato(tarifasContrato, idPersonaModificacion);
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
	
   // protected region metodos adicionales end
	

}
