package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroSorteoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISorteoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Sorteo
 * @author sMartinez
 */
@Path( "sorteo" )
@Stateless
public class SorteoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SorteoRecurso.class);
    private static final Class<Sorteo> enClass= Sorteo.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
    /**
     * Fachada de negocio donde se tienen todos los 
     * servicios correspondientes al sorteo
     */
	@EJB
    private ISorteoFacade sorteoFacade; 
	
	/**
	 * Fachada de negocio donde se parametrizan los
	 * parametros para realizar sorteo
	 */
	@EJB
	private IParametroSorteoFacade paramSorteoFacade;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	
	/**
	 * Actualiza la informacion de parametros para
	 * realizar un sorteo
	 * @param paramSorteo
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/actualizarParamSorteo")
	public Response actualizarParamSorteo(ParametroSorteo paramSorteo) {
		Response response = null;
		try {
			paramSorteoFacade.actualizar(paramSorteo);
			response = Response.status(Response.Status.OK).header(
					"Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			
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
	 * Consulta los parametros para realizar un sorteo
	 * @return ParametrosSorteo informacion de los parametros
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarParametrosSorteo/")
	public Response consultarParametrosSorteo() {
		Response response = null;

		try {
			List<ParametroSorteo> listParam = (List<ParametroSorteo>) paramSorteoFacade
					.obtenerEntidadesTodos(new ArrayList<ParametroSorteo>(), true);

			if (listParam != null && listParam.size() > 0) {
				response = Response.status(Response.Status.OK)
						.entity(new GenericEntity<ParametroSorteo>(listParam.get(0)) {
						}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			} else {
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR120.toString());
				throw new SIMASCRecursosRESTExcepcion(mensajeError);
			}
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
