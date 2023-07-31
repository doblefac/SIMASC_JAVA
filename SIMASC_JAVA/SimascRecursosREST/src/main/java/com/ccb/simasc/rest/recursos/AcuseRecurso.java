package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAcuseFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AcuseDTO;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Acuse
 * @author sMartinez
 */
@Path( "acuse" )
@Stateless
public class AcuseRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AcuseRecurso.class);
    private static final Class<Acuse> enClass= Acuse.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IAcuseFacade acuseFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Método encargado de obtener el acuse de determinado tipo de un
	 * CorreoRolPersonaCaso especificado
	 * 
	 * @param idCorreoRolPersonaCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerAcuseCorreoRolPersonaCaso/{idCorreoRolPersonaCaso}/{tipo}")
	public Response obtenerAcuseCorreoRolPersonaCaso(@PathParam("idCorreoRolPersonaCaso") Long idCorreoRolPersonaCaso,
			@PathParam("tipo") String tipo) {
		Response response = null;

		try {
			AcuseDTO acuseDTO = acuseFacade.obtenerAcuseCorreoRolPersonaCaso(idCorreoRolPersonaCaso, tipo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<AcuseDTO>(acuseDTO) {
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
