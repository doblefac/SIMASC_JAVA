package com.ccb.simasc.rest.recursos;

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICaptchaVerificaSitioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;

@Path( "captcha" )
@Stateless
public class CaptchaVerificaSitioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CaptchaVerificaSitioRecurso.class);
	
	@EJB
    private ICaptchaVerificaSitioFacade captchaVerificaSitioFacade;        
	
	@Context
    private HttpHeaders httpHeaders;
          
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/verificarSitio/{keyResponse}")
	public Response verificarSitio(@PathParam("keyResponse") String keyResponse) {
		Response response = null;
		try {			
			String respuesta = captchaVerificaSitioFacade.consumirServicioVerificacionDeSitio(keyResponse);									
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(respuesta) {}).build();			
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, "CaptchaFacade", ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
}
