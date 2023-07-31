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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDiaFestivoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Suspension;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ParametrosGenerales
 * @author sMartinez
 */
@Path( "parametrosgenerales" )
@Stateless
public class ParametrosGeneralesRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ParametrosGeneralesRecurso.class);
    private static final Class<ParametrosGenerales> enClass= ParametrosGenerales.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 
	
	@EJB
	private IDiaFestivoFacade diaFestivoFacade;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaParametrosPorCodigo/{codigo}")
	public Response consultaParametrosPorCodigo(@PathParam("codigo") String codigo) {
		Response response = null;

		try {
			ParametrosGenerales parametro = parametrosGeneralesFacade.consultarPorCodigo(codigo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<ParametrosGenerales>(parametro) {
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
	@Path("/consultaParametrosPorTipo/{tipo}")
	public Response consultarParametrosPorTipo(@PathParam("tipo") String tipo) {
		Response response = null;
		String tipoParametro = tipo.equals(UtilConstantes.VALOR_UNDEFINED)? null: tipo;
		try {
			List<ParametrosGenerales> parametro = parametrosGeneralesFacade.consultarPorTipo(tipoParametro);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ParametrosGenerales>>(parametro) {
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaDiasHabiles/")
	public Response consultaDiasHabiles( Suspension suspension ){		
		Response response = null;
		Integer diasHabiles = 0;
		
		
		try {
			if (suspension.getFechaFinal() != null && suspension.getFechaInicial() != null){
				diasHabiles = diaFestivoFacade.obtenerDiasHabilesEntreFechas(suspension.getFechaInicial(), suspension.getFechaFinal());

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
	
	/**
	 * Servicio que permite actualizar los parámetros generales
	 * audiencia ADM-C-009).
	 * 
	 * @author cagonzalez.
	 * 
	 * @param ParametrosGenerales:
	 *            
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarParametrosGenerales/")
	public Response actualizarParametrosGenerales(List<ParametrosGenerales> parametrosGenerales) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			parametrosGeneralesFacade.actualizarParametrosGenerales(parametrosGenerales, contextoDeSesion.getIdUsuario());
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
