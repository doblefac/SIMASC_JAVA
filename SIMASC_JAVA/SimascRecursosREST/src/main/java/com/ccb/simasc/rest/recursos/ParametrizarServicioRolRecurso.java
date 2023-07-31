package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrizarServicioRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IServicioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ParametrizarServicioRolDTO;
import com.ccb.simasc.transversal.entidades.ParametrizarServicioRol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST ParametrizarServicioRol
 * @author sMartinez
 */
@Path( "parametrizarserviciorol" )
@Stateless
public class ParametrizarServicioRolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ParametrizarServicioRolRecurso.class);
    private static final Class<ParametrizarServicioRol> enClass= ParametrizarServicioRol.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IParametrizarServicioRolFacade parametrizarServicioRolFacade; 
	
	@EJB
	private IRolFacade rolFacade;
	
	@EJB
	private IServicioFacade servicioFacade;
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * Actualiza la informacion de parametros el reparto de abogados a un caso
	 * @param paramServicioRol
	 * @return
	 */
	@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/actualizarParamRepartoServicioRol")
	public Response actualizarParamRepartoServicioRol(List<ParametrizarServicioRolDTO> paramsServicioRol) {
		Response response = null;
		try {
			List<ParametrizarServicioRol> listParam = (List<ParametrizarServicioRol>) parametrizarServicioRolFacade
					.obtenerEntidadesTodos(new ArrayList<ParametrizarServicioRol>(), true);
			
			if (!listParam.isEmpty()) {
				parametrizarServicioRolFacade.eliminarParametrizarServicioRol(listParam);
			}
			
			for (ParametrizarServicioRolDTO paramServicioRol : paramsServicioRol) {
				parametrizarServicioRolFacade.actualizarParametrizarServicioRol(paramServicioRol);
			}
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
	@Path("/consultarParamServicioRol/")
	public List<Response> consultarParamRepartoServicioRol() {
		List<Response> response = new ArrayList<>();

		try {
			List<ParametrizarServicioRol> listParam = (List<ParametrizarServicioRol>) parametrizarServicioRolFacade
					.obtenerEntidadesTodos(new ArrayList<ParametrizarServicioRol>(), true);

			if (listParam != null && listParam.size() > 0) {
				for (ParametrizarServicioRol param : listParam) {
					response.add(Response.status(Response.Status.OK)
							.entity(new GenericEntity<ParametrizarServicioRol>(param) {
							}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build());
				}
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
