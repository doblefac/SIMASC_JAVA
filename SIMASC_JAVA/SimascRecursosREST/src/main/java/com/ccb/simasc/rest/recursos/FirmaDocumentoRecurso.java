package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFirmaDocumentoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CasosPendientesFirmaDTO;
import com.ccb.simasc.transversal.dto.DatosFirmaActaConstantciaDTO;
import com.ccb.simasc.transversal.dto.PartesEstadoFirmaDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosTokenDTO;
import com.ccb.simasc.transversal.entidades.FirmaDocumento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports recurso end

/**
 * Recurso REST FirmaDocumento
 * @author sMartinez
 */
@Path( "firmadocumento" )
@Stateless
public class FirmaDocumentoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(FirmaDocumentoRecurso.class);
    private static final Class<FirmaDocumento> enClass= FirmaDocumento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IFirmaDocumentoFacade firmaDocumentoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
	
	@Context
	private HttpServletRequest httpServletRequest;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/solicitarTokenClaveSegura/")
	public Response solicitarTokenClaveSegura(DatosTokenDTO datosToken) {
		Response response = null;
		try {
			
			datosToken.setIp(UtilOperaciones.obtenerDireccionIPOrigen(httpServletRequest)); 
			String token = firmaDocumentoFacade.solicitarTokenClaveSegura(datosToken);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.entity(new GenericEntity<String>(token) {}).build();

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
	@Path("/consultarFirmaPartes/{idCaso}/{idDocumento}")
	public Response consultarFirmaPartes(@PathParam("idCaso") Long idCaso, @PathParam("idDocumento")  Long idDocumento) {
		Response response = null;
		try {
			List<PartesEstadoFirmaDTO> partesFirma = firmaDocumentoFacade.consultarFirmaPartes(idCaso, idDocumento);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PartesEstadoFirmaDTO>>(partesFirma) {
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
	@Path("/casosPendientesFirmaConciliador/{idPersona}")
	public Response casosPendientesFirmaConciliador(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<CasosPendientesFirmaDTO> casosPendientes = firmaDocumentoFacade.casosPendientesFirmaConciliador(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosPendientesFirmaDTO>>(casosPendientes) {
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/habilitarpartes/{idDocumento}")
	public Response solicitarTokenClaveSegura(List<PartesEstadoFirmaDTO> partesHabilitadas, @PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		try {
			firmaDocumentoFacade.habilitarPartes(partesHabilitadas, idDocumento);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/firmarActaConstancia")
	public Response firmarActaConstancia(DatosFirmaActaConstantciaDTO datosFirmaActaConstantciaDTO) {
		Response response = null;
		try {
			firmaDocumentoFacade.firmarActaConstancia(datosFirmaActaConstantciaDTO);

			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Path("/validarpartefirma/{idPersona}/{idCaso}")
	public Response validarParteParaFirma(@PathParam("idPersona") Long idPersona, @PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<FirmaDocumento> firma = firmaDocumentoFacade.obtenerParteFirmante(idPersona, idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<FirmaDocumento>>(firma) {
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
