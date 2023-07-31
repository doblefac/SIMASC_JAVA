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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITranscripcionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AudienciaPendienteTranscripcionDTO;
import com.ccb.simasc.transversal.dto.DatosReasignacionTranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionDTO;
import com.ccb.simasc.transversal.dto.TranscripcionPendienteDTO;
import com.ccb.simasc.transversal.entidades.Transcripcion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Transcripcion
 * @author sMartinez
 */
@Path( "transcripcion" )
@Stateless
public class TranscripcionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(TranscripcionRecurso.class);
    private static final Class<Transcripcion> enClass= Transcripcion.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private ITranscripcionFacade transcripcionFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param idAudiencia
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarTranscripcionesPendientes/{idCaso}/{idAudiencia}")
	public Response consultarTranscripcionesPendientes(@PathParam("idCaso") Long idCaso,
			@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;
		
		try {
			List<TranscripcionPendienteDTO> transcripcionesPendientes = transcripcionFacade
					.consultarTranscripcionesPendientes(idCaso, idAudiencia);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<TranscripcionPendienteDTO>>(transcripcionesPendientes) {
					}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cargarTranscripcion")
	public Response cargarTranscripcion(ArrayList<Transcripcion> transcripciones){
		Response response = null;
		ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		transcripcionFacade.cargarTranscripciones(transcripciones,sesion);
		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<String>("ok"){			
				})
				.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
				.build();
		return response;
	}
	
	/**
	 * 
	 * 
	 * @param idDocumento
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/crearTranscripcionDocumento")
	public Response crearTranscripcionDocumento(Long idDocumento) {
		Response response = null;

		try {
			transcripcionFacade.crearTranscripcionDocumento(idDocumento);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
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
    @Produces({MediaType.APPLICATION_JSON})
	@Path("/casosAudienciasPendientesTranscripciones/")
	public Response casosAudienciasPendientesTranscripciones() {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {	
			List<AudienciaPendienteTranscripcionDTO> listAudienciaPendienteTranscripcionDTO=transcripcionFacade.casosAudienciasPendientesTranscripciones(cs.getIdUsuario());
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AudienciaPendienteTranscripcionDTO>>(listAudienciaPendienteTranscripcionDTO) {
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
    
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCasosAudienciasPendientesTranscripciones/")
	public Response actualizarCasosAudienciasPendientesTranscripciones(List<AudienciaPendienteTranscripcionDTO> listAudienciaPendienteTranscripcionDTO) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			List<AudienciaPendienteTranscripcionDTO> list = transcripcionFacade.actualizarCasosAudienciasPendientesTranscripciones(listAudienciaPendienteTranscripcionDTO, cs.getIdUsuario());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AudienciaPendienteTranscripcionDTO>>(list) {
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
	 * 
	 * 
	 * @param idPersona
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reasignarTranscripcionCompleta/")
	public Response reasignarTranscripcionCompleta(TranscripcionDTO transcripcionDTO) {
		Response response = null;

		try {
			transcripcionFacade.reasignarTranscripcionCompleta(transcripcionDTO);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
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
	 * 
	 * 
	 * @param idTranscripcion
	 * @param datosTranscripcionesNuevas
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reasignarTranscripcionParcial/{idTranscripcion}")
	public Response reasignarTranscripcionParcial(@PathParam("idTranscripcion") Long idTranscripcion,
			List<DatosReasignacionTranscripcionDTO> datosTranscripcionesNuevas) {
		Response response = null;

		try {
			transcripcionFacade.reasignarTranscripcionParcial(idTranscripcion, datosTranscripcionesNuevas);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
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
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/cargarTranscripcionesPorAudio/{idAudio}")
	public Response cargarTranscripcionesPorAudio(@PathParam("idAudio") Long idDocumento){
		Response response = null;
		List<Transcripcion> t = (List<Transcripcion>) new TranscripcionDTO().transformarColeccionSinDependencias(transcripcionFacade.consultarTranscripcionesPorDocumento(idDocumento));
		response = Response.status(Response.Status.OK)
				.entity(new GenericEntity<List<Transcripcion>>(t){			
				})
				.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
				.build();
		return response;
	}
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarTranscripcion/")
	public Response actualizarTranscripcion(Transcripcion transcripcion) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			transcripcionFacade.actualizarTranscripcion(transcripcion, cs.getIdUsuario());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
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
