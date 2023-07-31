package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

import java.util.Arrays;

// Escriba en esta sección sus modificaciones

import java.util.Date;
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
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AudienciaDTO;
import com.ccb.simasc.transversal.dto.AudienciaSorteoDTO;
import com.ccb.simasc.transversal.dto.AudienciaTranscripcionesPendientesDTO;
import com.ccb.simasc.transversal.dto.CitacionDTO;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.RealizacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.formularios.AudienciasProgramadasDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosResultadoAudienciaDTO;
import com.ccb.simasc.transversal.dto.formularios.ProgramacionAudienciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSorteadosDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Audiencia
 * 
 * @author sMartinez
 */
@Path("audiencia")
@Stateless
public class AudienciaRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(AudienciaRecurso.class);
	private static final Class<Audiencia> enClass = Audiencia.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IAudienciaFacade audienciaFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;
	

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * consulta las audiencias de una caso
	 * 
	 * @param idCaso
	 *            codigo del caso
	 * @return lista de audiencias
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerAudienciasCaso/{idCaso}")
	public Response obtenerAudienciasCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			AudienciaDTO filtroAudiencia = new AudienciaDTO();
			filtroAudiencia.setIdCaso(idCaso);
			List<AudienciaDTO> audienciasCaso = audienciaFacade.obtenerAudienciasCasoDTO(filtroAudiencia);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<AudienciaDTO>>(audienciasCaso) {
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
	 * actualiza la audiencia enviada
	 * 
	 * @param audiencia
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarAudiencia")
	public Response actualizarAudiencia(Audiencia audiencia) {
		Response response = null;

		try {

			audienciaFacade.actualizarAudiencia(audiencia);

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
	@Path("/obtenerAudienciasFiltros/{contexto}")
	public Response obtenerAudienciasFiltros(@PathParam("contexto") Boolean contexto, AudienciaDTO filtroAudiencia) {
		Response response = null;
		ContextoDeSesion cs = (contexto) ? ContextoDeSesion.obtenerContextoDeSesion(httpHeaders) : null;

		try {

			List<AudienciaDTO> audienciasCaso = audienciaFacade.obtenerAudienciasFiltros(filtroAudiencia, cs);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<AudienciaDTO>>(audienciasCaso) {
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
	 * Consulta las audiencias segun los filtros que se le envien
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerAudienciasFiltros/{idCaso}")
	public Response obtenerAudienciaFiltros(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			AudienciaDTO audienciaCaso = audienciaFacade.obtenerAudienciaFiltros(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<AudienciaDTO>(audienciaCaso) {
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
	@Path("/registrarResultadoAudiencia/")
	public Response registrarResultadoAudiencia(DatosResultadoAudienciaDTO resultadoDTO) {
		Response response = null;

		try {
			audienciaFacade.registrarResultadoAudiencia(resultadoDTO);

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

	/**
	 * Se encarga de programar una audiencia teniendo en cuenta las validaciones
	 * correspondientes
	 * 
	 * @param audiencia
	 * @return 
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/programarAudiencia/{notificar}")
	public Response programarAudiencia(Audiencia audiencia, @PathParam("notificar") Boolean notificar) {
		Response response = null;
		try {
			// si no cumple con la validaciones se genera SIMASCNegocioExcepcion
			audienciaFacade.validacionProgramarAudiencia(audiencia);

			Long idAudiencia = audienciaFacade.programarAudiencia(audiencia, notificar);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idAudiencia) {
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
	 * Se encarga de programar una audiencia teniendo en cuenta las validaciones
	 * correspondientes
	 * 
	 * @param audiencia
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDatosBasicoAudiencia/{idAudiencia}")
	public Response consultarDatosBasicoAudiencia(@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;
		try {
			// si no cumple con la validaciones se genera SIMASCNegocioExcepcion
			Audiencia audienciaCaso = audienciaFacade.consultarDatosBasicoAudiencia(idAudiencia);

			response = Response.ok(Response.Status.OK).entity(new GenericEntity<Audiencia>(audienciaCaso) {
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
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAudienciasTranscripcionesPendientes/{idPersona}")
	public Response consultarAudienciasTranscripcionesPendientes(@PathParam("idPersona") Long idPersona) {
		Response response = null;

		try {
			// 1. Obtiene el contexto de sesión del encabezado de la petición
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			// 2. Obtiene el id del rol de la persona en sesión
			Long idRol = Long.valueOf(sesion.getRolPrincipal());

			List<AudienciaTranscripcionesPendientesDTO> audienciasTranscripcionesPendientes = audienciaFacade
					.consultarAudienciasTranscripcionesPendientes(idPersona, idRol);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<AudienciaTranscripcionesPendientesDTO>>(
							audienciasTranscripcionesPendientes) {
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
	 * Retorna las audicnecias de sorteo publico de designacion pendientes por
	 * ejecutar
	 * 
	 * @param
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAudienciasSorteoPendientes/")
	public Response consultarAudienciasSorteoPendientes() {
		Response response = null;

		try {
			List<AudienciaSorteoDTO> audienciasSorteoPendientes = audienciaFacade
					.consultarAudienciasSorteoPendientes();
			// response =
			// Response.status(Response.Status.OK).entity(audienciasTranscripcionesPendientes)
			// .header("Access-Control-Allow-Headers",
			// UtilConstantes.X_EXTRA_HEADER).build();

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AudienciaSorteoDTO>>(audienciasSorteoPendientes){})
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/notificarResultadoAudiencia/{idCaso}/{idAudiencia}")
	public Response notificarResultadoAudiencia(List<Documento> documento, @PathParam("idCaso") Long idCaso,
			@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;
		try {
			Long idPersona = rolPersonaCasoFacade.consultarSecretarioCaso(idCaso);
			// 1. Obtiene el contexto de sesión del encabezado de la petición
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			// 2. Obtiene el id del rol de la persona en sesión
			Long idRol = Long.valueOf(sesion.getRolPrincipal());
			if (idPersona != null)
				audienciaFacade.enviarNotificacionResultadoAudiencia(idCaso, idAudiencia, idPersona, idRol);
			else
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR502.toString()));

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

	/**
	 * Servicio encargado de obtener los rangos de fecha inicio y fin para la
	 * programacion de una audiencia. Cuando se invoca el servicio se deja por
	 * defecto la fecha de radicacion del caso new Date
	 * 
	 * @return ProgramacionAudienciaDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/rangoProgramacionAudiencia/{idCaso}")
	public Response rangoProgramacionAudiencia(@PathParam("idCaso") String caso) {
		Response response = null;
		Long idCaso = null;
		try {
			if(!UtilConstantes.VALOR_UNDEFINED.equals(caso))
				idCaso = Long.valueOf(caso);
			ProgramacionAudienciaDTO dto = audienciaFacade.fechasProgramacionAudiencia(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<ProgramacionAudienciaDTO>(dto) {
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
	 * Método para obtener el listado de las audiencias que se encuentran
	 * pendientes por realizar que correspondan a una jornada
	 * 
	 * @param jornada
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAudienciasPendientesPorJornada/{jornada}")
	public Response consultarAudienciasPendientesPorJornada(@PathParam("jornada") String jornada) {
		Response response = null;
		Long idJornada = null;
		if (!jornada.equals(UtilConstantes.VALOR_UNDEFINED))
			idJornada = Long.parseLong(jornada);
		try {
			List<AudienciaDTO> audiencias = audienciaFacade.consultarAudienciasPendientesPorJornada(idJornada);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AudienciaDTO>>(audiencias) {
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
	 * Servicio encargado de obtener los nombres correspondiente a las partes
	 * con sus correos electronicos de un caso de conciliacion e información
	 * correspondiente a la audiencia pendiente (fecha, hora y sede)
	 * 
	 * @return ProgramacionAudienciaDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInformacionAudienciaPendiente/{idCaso}")
	public Response consultarInformacionAudienciaPendiente(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			response = Response.status(Response.Status.OK).entity(
					new GenericEntity<CitacionDTO>((CitacionDTO) audienciaFacade.consultarInformacionCitacion(idCaso)) {
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
	@Path("/programarAudienciasJornada/{convenio}")
	public Response programarAudienciasJornada(@PathParam("convenio") String convenio) {
		Response response = null;
		Long idConvenio = null;
		if (!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.valueOf(convenio);
		try {
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Boolean>(audienciaFacade.programarAudiencias(idConvenio)) {
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
	 * 05-02-2018 pRendon Servicio que expone la consulta de la informacion de
	 * una audiencia pendiente de un caso
	 * 
	 * @param idCaso
	 * @return CitacionDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAudienciaPendiente/{idCaso}")
	public Response consultarAudienciaPendiente(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<CitacionDTO> citacionesDTO = audienciaFacade.consultarInformacionAudienciaPendiente(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CitacionDTO>>(citacionesDTO) {
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
	 * 07-02-2018 cagonzalez Retorna el resultado y fecha de la ultima audiencia
	 * en estado realizado
	 * 
	 * @param idCaso
	 * @return AudienciaDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarUltimaAudienciaRealizada/{idCaso}")
	public Response consultarUltimaAudienciaRealizada(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			AudienciaDTO audiencia = audienciaFacade.consultarUltimaAudienciaRealizada(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<AudienciaDTO>(audiencia) {
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
	 * Reversa el resultado de una audiencia
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reversarResultadoAudiencia/")
	public Response reversarResultadoAudiencia(AudienciaDTO audiencia) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			audienciaFacade.revertirResultadoAudiencia(audiencia, idPersonaModificacion);
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

	/**
	 * CON-F-075
	 * 
	 * @param idPersona
	 * @param fechaAudiencia
	 * @param idSede
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAudienciasProgramadasConciliador/{idPersona}/{fechaAudiencia}/{idSede}")
	public Response consultarAudienciasProgramadasConciliador(@PathParam("idPersona") Long idPersona,
			@PathParam("fechaAudiencia") Long fechaAudiencia, @PathParam("idSede") Long idSede) {
		Response response = null;
		try {
			Date fecha = new Date(fechaAudiencia);
			List<AudienciasProgramadasDTO> audiencias = audienciaFacade
					.consultarAudienciasProgramadasConciliador(idSede, fecha, idPersona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<AudienciasProgramadasDTO>>(audiencias) {
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

	/**  CON-F-106 
	 * Servicio que recibe los parametros para programar una audiencia, agendar al conciliador,
	 * agendar la sala, crear registro en el log
	 * @author prendon
	 * @param audienciaPorProgramar
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/programarAudienciaConciliacion/")
	public Response programarAudienciaConciliacion(ConsultaAgendamientoDTO audienciaPorProgramar) {
		Response response = null;
		try {
			audienciaFacade.programarAudienciaConciliacion(audienciaPorProgramar);
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

	/**
	 * Servicio que permite registrar la audiencia realizada (realizacion
	 * audiencia CON-F-110).
	 * 
	 * @author aperez.
	 * 
	 * @param realizacionAudienciaDTO:
	 *            Dto con la informacion de la audiencia.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarAudienciaRealizada/")
	public Response registrarAudienciaRealizada(RealizacionAudienciaDTO realizacionAudienciaDTO) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			audienciaFacade.registrarAudienciaRealizada(realizacionAudienciaDTO, idPersonaModificacion);
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
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenercantidadaudienciasestado/{idCaso}/{estado}")
	public Response obtenerCantidadAudienciasEstado(@PathParam("idCaso") Long idCaso, @PathParam("estado") String estado) {
		Response response = null;
		try {
			Long cantidad = audienciaFacade.obtenerNumeroAudienciasCaso(idCaso, Arrays.asList(estado));
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Long>(cantidad) {
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
	 * Consulta el numero de arbitros principales y suplentes disponibles hay para sorteo
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCantidadArbitros/{idCaso}")
	public Response obtenerCantidadArbitrosDisponibles(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			List<Long> cantidades = audienciaFacade.obtenerCantidadArbitrosDisponibles(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Long>>(cantidades) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerReporteCasosSorteados/{tipoCaso}/{fechaInicio}/{fechaFin}")
	public Response obtenerReporteCasosSorteados(@PathParam("tipoCaso") Long tipoCaso , @PathParam("fechaInicio") Long fechaInicio
	,@PathParam("fechaFin") Long fechaFin){
		Response response = null;

		try {
			Date fechaInicial = new Date(fechaInicio);
			Date fechaFinal = new Date(fechaFin);
			List<ReporteCasosSorteadosDTO> cantidades = audienciaFacade.getReporteCasosSorteados(fechaInicial,fechaFinal,tipoCaso);
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ReporteCasosSorteadosDTO>>(cantidades) {
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
	@Path("/eliminarAudiencia")
	public Response eliminarAudiencia(Audiencia audiencia) {
		Response response = null;

		try {

			audienciaFacade.eliminarAudiencia(audiencia);

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
