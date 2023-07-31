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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizacionTarifasFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaDTO;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ParametrizacionTarifas
 * @author sMartinez
 */
@Path( "parametrizaciontarifas" )
@Stateless
public class ParametrizacionTarifasRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ParametrizacionTarifasRecurso.class);
    private static final Class<ParametrizacionTarifas> enClass= ParametrizacionTarifas.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IParametrizacionTarifasFacade parametrizacionTarifasFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/calcularTarifaPublico")
	public Response calcularTarifa(ParametrosTarifasDTO parametrosTarifasDTO) {
		Response response = null;
		try {
			ResultadosTarifaDTO resultados = 
					parametrizacionTarifasFacade
					.calcularTarifaPublico(parametrosTarifasDTO);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<ResultadosTarifaDTO>(resultados) {
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarParametrizacionTarifa")
	public Response actualizarParametrizacionTarifa(ParametrizacionTarifas parametrizacionTarifa) {
		Response response = null;
		try {
			parametrizacionTarifasFacade.actualizarParametrizacion(parametrizacionTarifa);
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarParametrizacionTarifa/{idParametrizacion}")
	public Response eliminarParametrizacionTarifa(@PathParam("idParametrizacion") Long idParametrizacion) {
		Response response = null;
		try {
			parametrizacionTarifasFacade.eliminarParametrizacion(idParametrizacion);
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarParametrizacionTarifa/{tipoTarifa}/{cantidadArbitros}/{idServicio}")
	public Response eliminarParametrizacionTarifa(@PathParam("tipoTarifa") String tipoTarifa, 
							@PathParam("cantidadArbitros") String cantidadArbitros,
							@PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			ParametrosTarifasDTO parametros = new ParametrosTarifasDTO();
			parametros.setTipoTarifa(tipoTarifa);
			parametros.setCantidadArbitros(cantidadArbitros);
			parametros.setIdServicio(idServicio);
			List<ParametrizacionTarifasDTO> parametrizacion = parametrizacionTarifasFacade.obtenerRangosTarifa(parametros);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ParametrizacionTarifasDTO>>(parametrizacion) {
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
