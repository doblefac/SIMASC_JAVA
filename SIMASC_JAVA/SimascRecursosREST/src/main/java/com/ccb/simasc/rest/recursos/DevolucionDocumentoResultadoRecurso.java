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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDevolucionDocumentoResultadoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DevolucionDocumentoDTO;
import com.ccb.simasc.transversal.dto.ModificarActasConstanciasDevueltasDTO;
import com.ccb.simasc.transversal.entidades.DevolucionDocumentoResultado;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST DevolucionDocumentoResultado
 * @author sMartinez
 */
@Path( "devoluciondocumentoresultado" )
@Stateless
public class DevolucionDocumentoResultadoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(DevolucionDocumentoResultadoRecurso.class);
    private static final Class<DevolucionDocumentoResultado> enClass= DevolucionDocumentoResultado.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IDevolucionDocumentoResultadoFacade devolucionDocumentoResultadoFacade; 

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/** CON-F-084
	 * servicio que expone la consulta de la informacion necesaria 
	 * para responder los documentos devueltos en control de legalidad
	 * @param idCaso: codigo del caso
	 * @return lista de audiencias
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarActasConstanciasDevueltasCaso/{idCaso}")
	public Response consultarActasConstanciasDevueltasCaso( @PathParam("idCaso") Long idCaso ) {
		Response response = null;

		try {
			List<ModificarActasConstanciasDevueltasDTO> actasConstanciasDevueltasCaso = devolucionDocumentoResultadoFacade.consultarActasConstanciasDevueltasCaso( idCaso );

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ModificarActasConstanciasDevueltasDTO>>(actasConstanciasDevueltasCaso) {
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
	 * CON-F-084
	 * Servicio que expone el metodo para modificar actas y constancias devueltas 
	 * @return ok
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarActasConstanciasDevueltas/")
	public Response modificarActasConstanciasDevueltas( ModificarActasConstanciasDevueltasDTO modificarActasConstanciasDevueltasDTO ) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idUsuarioModificacion = cs.getIdUsuario();
		try {
			devolucionDocumentoResultadoFacade.modificarActasConstanciasDevueltas( modificarActasConstanciasDevueltasDTO, idUsuarioModificacion );
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarDevolucionDocumento/")
	public Response guardarDevolucionDocumento(DevolucionDocumentoDTO devolucionDocumentoDTO) {
		Response response = null;

		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			devolucionDocumentoResultadoFacade.guardarDevolucionDocumento(devolucionDocumentoDTO, idPersonaModificacion);

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
