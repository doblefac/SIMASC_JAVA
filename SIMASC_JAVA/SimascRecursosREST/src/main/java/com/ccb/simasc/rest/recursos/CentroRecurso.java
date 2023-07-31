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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICentroFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.entidades.Centro;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Centro
 * @author sMartinez
 */
@Path( "centro" )
@Stateless
public class CentroRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CentroRecurso.class);

    private static final Class<Centro> enClass= Centro.class;

   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ICentroFacade centroFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * Servicio encargado de obtener los Centros activos. Opcional obtener
	 * Centros de acuerdo al idServicio e idMateria.
	 * 
	 * CON-F-124
	 * 
	 * @param dto
	 * @return List<CentroDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultar/")
	public Response consultar(CentroDTO dto) {
		Response response = null;
		try {
			List<CentroDTO> centroDTOs = centroFacade.obtenerCentros(
					dto.getIdServicio() != null ? dto.getIdServicio() : null,
					dto.getIdMateria() != null ? dto.getIdMateria() : null);
			
			if (centroDTOs.isEmpty())
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CentroDTO>>(centroDTOs) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();

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
	 * Servicio que permite actualizar los parámetros de centro
	 * audiencia ADM-C-009).
	 * 
	 * @author cagonzalez.
	 * 
	 * @param parametrosCentro:
	 *            
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarParametrosCentro/")
	public Response actualizarParametrosCentro(List<CentroDTO> parametrosCentro) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			centroFacade.actualizarParametrosCentro(parametrosCentro, contextoDeSesion.getIdUsuario());
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
