package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.math.BigDecimal;
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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;

// protected region imports recurso end

/**
 * Recurso REST ParametroDeServicio
 * @author sMartinez
 */
@Path( "parametrodeservicio" )
@Stateless
public class ParametroDeServicioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ParametroDeServicioRecurso.class);
    private static final Class<ParametroDeServicio> enClass= ParametroDeServicio.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IParametroDeServicioFacade parametroDeServicioFacade; 
	
	@EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	/**
	 * Metodo que consulta los convenios dada una lista de filtros 
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarParametroDeServicioFiltros")
	public Response consultarParametroDeServicioFiltros(List<InformacionFiltroDTO> filtrosParametro) {
		Response response = null;
		try {
			List<ParametroDeServicioDTO> convenioDTOs = parametroDeServicioFacade.consultarParametroDeServicioFiltros(filtrosParametro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ParametroDeServicioDTO>>(convenioDTOs) {
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
	 * Metodo que consulta los convenios dada una lista de nombres 
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarParametroDeServicio/{idServicio}/{tipoParametro}")
	public Response consultarParametroDeServicio(List<String> nombres, @PathParam("idServicio") String servicio, @PathParam("tipoParametro") String tipoParametro) {
		Response response = null;
		Long idServicio = null;
		if(!servicio.equals(UtilConstantes.VALOR_UNDEFINED))
			idServicio = Long.parseLong(servicio);
		try {
			List<ParametroDeServicioDTO> parametrosDTO = parametroDeServicioFacade.consultarParametroDeServicio(nombres, idServicio, tipoParametro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ParametroDeServicioDTO>>(parametrosDTO) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaParametrosServicioPorTipo/{tipo}")
	public Response consultarParametrosPorTipo(@PathParam("tipo") String tipo) {
		Response response = null;
		String tipoParametro = tipo.equals(UtilConstantes.VALOR_UNDEFINED)? null: tipo;
		try {
			List<ParametroDeServicioDTO> parametro = parametroDeServicioFacade.consultarParametroDeServicioTipo(tipoParametro);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ParametroDeServicioDTO>>(parametro) {
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
	 * Servicio que permite actualizar los parámetros de servicio
	 * audiencia ADM-C-009).
	 * 
	 * @author cagonzalez.
	 * 
	 * @param ParametrosDeServicio:
	 *            
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarParametrosDeServicio/")
	public Response actualizarParametrosDeServicio(List<ParametroDeServicioDTO> parametrosDeServicio) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			parametroDeServicioFacade.actualizarParametrosDeServicio(parametrosDeServicio, contextoDeSesion.getIdUsuario());
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
	 * Metodo que consulta los parametros MontoDeudaInsolvencia
	 * 
	 * @return el valor del monto minimo para solicitar una insolvencia
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaParametroMontoDeudaInsolvencia")
	public Response consultarParametroMontoDeudaInsolvencia() {
		Response response = null;
		
		try {
			ParametroDeServicioDTO parametroServicio = parametroDeServicioFacade.
					consultarParametroDeServicioPorNombre(UtilParamServicio.MONTO_DEUDA_INSOLVENCIA_SMLMV);
			
			ParametrosGenerales pametroGeneral = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.UVT);
			
			BigDecimal monto = new BigDecimal(parametroServicio.getValor()).multiply(new BigDecimal(pametroGeneral.getValorNumerico()));
			response = Response.status(Response.Status.OK).entity(new GenericEntity<BigDecimal>(monto) {
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

}
