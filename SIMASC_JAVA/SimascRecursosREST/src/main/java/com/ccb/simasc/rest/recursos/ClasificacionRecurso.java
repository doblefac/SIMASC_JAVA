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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IClasificacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ClasificacionDTO;
import com.ccb.simasc.transversal.entidades.Clasificacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Clasificacion
 * @author sMartinez
 */
@Path( "clasificacion" )
@Stateless
public class ClasificacionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ClasificacionRecurso.class);
    private static final Class<Clasificacion> enClass= Clasificacion.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IClasificacionFacade clasificacionFacade;
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerClasificaciones/")
	public Response obtenerClasificaciones() {
		Response response = null;

		try {
			List<ClasificacionDTO> clasificaciones = (List<ClasificacionDTO>) clasificacionFacade
					.obtenerTodos(new ArrayList<ClasificacionDTO>(), false);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ClasificacionDTO>>(clasificaciones) {
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
	@Path("/obtenerClasificacionesPorAsunto/{idAsunto}")
	public Response obtenerAsuntosPorArea(@PathParam("idAsunto") Long idAsunto) {
		Response response = null;

		try {
			InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO,
					Clasificacion.ENTIDAD_CLASIFICACION_ID_ASUNTO, idAsunto);
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(f);
			List<ClasificacionDTO> asuntos = (List<ClasificacionDTO>) clasificacionFacade.obtenerPorFiltro(filtros,
					new ArrayList<InformacionOrdenamiento>(), null, new ArrayList<ClasificacionDTO>(), false);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ClasificacionDTO>>(asuntos) {
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
