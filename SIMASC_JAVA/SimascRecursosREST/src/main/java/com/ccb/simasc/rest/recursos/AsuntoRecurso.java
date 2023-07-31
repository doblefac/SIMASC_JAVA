package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAsuntoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AsuntoDTO;
import com.ccb.simasc.transversal.entidades.Asunto;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Asunto
 * @author sMartinez
 */
@Path( "asunto" )
@Stateless
public class AsuntoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AsuntoRecurso.class);
    private static final Class<Asunto> enClass= Asunto.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IAsuntoFacade asuntoFacade;
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerAsuntos/")
	public Response obtenerAsuntos() {
		Response response = null;

		try {
			List<AsuntoDTO> asuntos = (List<AsuntoDTO>) asuntoFacade.obtenerTodos(new ArrayList<AsuntoDTO>(), false);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AsuntoDTO>>(asuntos) {
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
	@Path("/obtenerAsuntosPorArea/{idArea}")
	public Response obtenerAsuntosPorArea(@PathParam("idArea") Long idArea) {
		Response response = null;

		try {
			InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Asunto.ENTIDAD_ASUNTO_ID_AREA, idArea);
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(f);
			List<AsuntoDTO> asuntos = (List<AsuntoDTO>) asuntoFacade.obtenerPorFiltro(filtros,
					new ArrayList<InformacionOrdenamiento>(), null, new ArrayList<AsuntoDTO>(), false);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AsuntoDTO>>(asuntos) {
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
