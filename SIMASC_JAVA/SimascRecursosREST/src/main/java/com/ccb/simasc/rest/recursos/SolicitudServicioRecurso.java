package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
import java.util.List;

// Escriba en esta seccion sus modificaciones

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudServicioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.SolicitudServicioDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionDTO;
import com.ccb.simasc.transversal.dto.formularios.SolicitudConciliacionEnmascaradoDTO;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST SolicitudServicio
 * @author sMartinez
 */
@Path( "solicitudservicio" )
@Stateless
public class SolicitudServicioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(SolicitudServicioRecurso.class);
    private static final Class<SolicitudServicio> enClass= SolicitudServicio.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private ISolicitudServicioFacade solicitudServicioFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	// Escriba en esta seccion sus modificaciones
	/**
	 * Servicio encargado de recibir la informacion para crear una solicitud de
	 * servicio de Conciliacion..
	 * 
	 * CON-F-124
	 * 
	 * @param SolicitudConciliacionDTO
	 * @return idSolicitudServicio
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crear/")
	public Response crearSolicitudConciliacion(SolicitudConciliacionDTO dto) {
		Response response = null;
		try {
			Long idSolicitudServicio = solicitudServicioFacade.crearSolicitudConciliacion(dto);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idSolicitudServicio) {
			}).build();

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
	 * Servicio encargado de recibir la informacion para crear una solicitud de
	 * servicio de Arbitraje.
	 * 
	 * ARB-F-108
	 * 
	 * @param SolicitudConciliacionDTO
	 * @return idSolicitudServicio
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearSolicitudServicio/")
	public Response crearSolicitudServicio(RadicacionSolicitudDTO radicacionSolicitudDTO) {
		Response response = null;
		try {

			Long idSolicitudServicio = solicitudServicioFacade.crearSolicitudServicio(radicacionSolicitudDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idSolicitudServicio) {
			}).build();

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
	 * Servicio encargado de consultar una solicitud del servicio
	 * 
	 * ARB-F-110
	 * 
	 * @param idSolicitud
	 * @return SolicitudConciliacionDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudServicio/{idSolicitudServicio}")
	public Response consultarSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			RadicacionSolicitudDTO radicacionSolicitudDTO = solicitudServicioFacade
					.consultarSolicitudServicio(idSolicitudServicio);

			if (radicacionSolicitudDTO == null)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<RadicacionSolicitudDTO>(radicacionSolicitudDTO) {
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

	/**
	 * Servicio encargado de validar que el número y roles mínimos de las partes
	 * requeridas para el registro de una solicitud esta cubierto
	 * 
	 * @param idSolicitudServicio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarPartesSolicitudServicio/{idSolicitudServicio}")
	public Response validarPartesSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {

			Boolean resultado = solicitudServicioFacade.validarPartesSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(resultado) {
			}).build();

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
	 * Servicio encargado de obtener las solicitudes de servicio vigentes (menos
	 * de 5 días hábiles) por solicitante
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSolicitudesVigentesPorSolicitante/{idPersona}")
	public Response consultarSolicitudesVigentesPorSolicitante(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<SolicitudServicioDTO> solicitudesServicioDTO = solicitudServicioFacade
					.consultarSolicitudesVigentesPorSolicitante(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<SolicitudServicioDTO>>(solicitudesServicioDTO) {
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

	/**
	 * Servicio encargado de realizar la liquidación de una solicitud de
	 * servicio en el sistema SIREP
	 * 
	 * @param idSolicitudServicio
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/liquidarreliquidar/")
	public Response liquidarSolicitudServicio(ProcesoReliquidacionDTO dto) {
		Response response = null;

		try {
			FormularioGenerarLiquidacionDTO formularioGenerarLiquidacionDTO = solicitudServicioFacade
					.liquidar(dto);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioGenerarLiquidacionDTO>(formularioGenerarLiquidacionDTO) {
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
	
	/**
	 * Servicio encargado de consultar una solicitud del servicio de
	 * conciliacion
	 * 
	 * CON-F-124
	 * 
	 * @param idSolicitud
	 * @return SolicitudConciliacionDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultar/{idSolicitud}")
	public Response consultarSolicitudConciliacion(@PathParam("idSolicitud") Long idSolicitud) {
		Response response = null;
		try {
			SolicitudConciliacionDTO dto = solicitudServicioFacade.consultarSolicitudConciliacionServicio(idSolicitud);

			if (dto == null)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));			

			response = Response.status(Response.Status.OK).entity(new GenericEntity<SolicitudConciliacionDTO>(dto) {
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


	// protected region metodos adicionales end
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEnmascarando/{idSolicitud}")
	public Response consultarSolicitudConciliacionEnmascarandoCorreo(@PathParam("idSolicitud") Long idSolicitud) {
		Response response = null;
		try {
			SolicitudConciliacionEnmascaradoDTO dto = solicitudServicioFacade.consultarSolicitudConciliacionEnmascarando(idSolicitud);

			if (dto == null)
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));			

			response = Response.status(Response.Status.OK).entity(new GenericEntity<SolicitudConciliacionEnmascaradoDTO>(dto) {
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


}
