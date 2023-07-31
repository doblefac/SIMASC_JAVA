package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITipoDeServicioRolFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.transversal.dto.RolDTO;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST TipoDeServicioRol
 * @author sMartinez
 */
@Path( "tipodeserviciorol" )
@Stateless
public class TipoDeServicioRolRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TipoDeServicioRolRecurso.class);
    private static final Class<TipoDeServicioRol> enClass= TipoDeServicioRol.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ITipoDeServicioRolFacade tipoDeServicioRolFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	
	/** ADM-C-004: Servicio que expone la consulta de los roles de tipo prestador
	 * @param tipoServicio
	 * @return List<RolDTO> rolesPrestadores
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarRolesPrestador/{tipoServicio}")
	public Response consultarRolesPrestador( @PathParam("tipoServicio") String tipoDeServicio ){
		Response response = null;
		String tipoServicio = tipoDeServicio.equals(UtilConstantes.VALOR_UNDEFINED)? null: tipoDeServicio;
		try {
			List<RolDTO> rolesPrestadores = tipoDeServicioRolFacade.consultarRolesPrestador(tipoServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolDTO>>(rolesPrestadores) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
   // protected region metodos adicionales end

}
