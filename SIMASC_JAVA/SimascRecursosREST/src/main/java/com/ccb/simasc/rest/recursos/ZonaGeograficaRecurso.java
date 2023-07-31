package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IZonaGeograficaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ZonaGeograficaDTO;
import com.ccb.simasc.transversal.entidades.ZonaGeografica;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ZonaGeografica
 * @author sMartinez
 */
@Path( "zonageografica" )
@Stateless
public class ZonaGeograficaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ZonaGeograficaRecurso.class);
    private static final Class<ZonaGeografica> enClass= ZonaGeografica.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IZonaGeograficaFacade zonaGeograficaFacade;
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Obtiene todos los paises
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPaises")
	public Response consultarPaises() {
		Response response = null;
		try {
			List<ZonaGeografica> zonaGeograficas = zonaGeograficaFacade.getBusquedaPaises();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ZonaGeografica>>(zonaGeograficas) {
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
	 * Obtiene las ciudades de acuerdo al pais
	 * 
	 * @param
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCiudades/{idPais}")
	public Response consultarCiudades(@PathParam("idPais") String idPais) {
		Response response = null;
		try {			
			List<ZonaGeografica> zonaGeograficas = zonaGeograficaFacade.getBusquedaCiudadesXPais(idPais);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ZonaGeografica>>(zonaGeograficas) {
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
	@Path("/consultarCiudadesNacionalidad/{idZona}")
	public Response consultarCiudadesNacionalidad(@PathParam("idZona") String idZona) {
		Response response = null;
		try {			
			List<ZonaGeografica> zonaGeograficas = zonaGeograficaFacade.getBusquedaCiudadesXNacionalidad(idZona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ZonaGeografica>>(zonaGeograficas) {
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
	 * Obtiene todos los paises de los departamentos
	 * 
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarZonaGeograficaPadre")
	public Response consultarZonaGeograficaPadre(List<String> idZonasGeograficas) {
		Response response = null;
		try {
			List<ZonaGeograficaDTO> zonaGeograficas = zonaGeograficaFacade.consultarPaisesPorDepartamento(idZonasGeograficas);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ZonaGeograficaDTO>>(zonaGeograficas) {
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
