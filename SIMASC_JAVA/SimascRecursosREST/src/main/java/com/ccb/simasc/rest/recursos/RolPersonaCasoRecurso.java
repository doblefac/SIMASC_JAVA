package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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

import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CambioConciliadorDTO;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.PartesSeguimientoDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PersonaCasoListDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.CambioEstadoRPC_DTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.DetalleArbitroDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.LotesCartasFiltrosDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RepartoDTO;
import com.ccb.simasc.transversal.dto.formularios.VinculacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST RolPersonaCaso
 * 
 * @author sMartinez
 */
@Path("rolpersonacaso")
@Stateless
public class RolPersonaCasoRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(RolPersonaCasoRecurso.class);
	private static final Class<RolPersonaCaso> enClass = RolPersonaCaso.class;

	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ICasoFacade casoFacade;
	
	
	@EJB
	private IRealizacionSorteoFacade realizacionSorteoFacade;


	@EJB
	private INombramientoPersonaFacade nombramientoPersonaFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorCaso manejadorCaso;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Obtiene todos las personas por el rol que se envia desde el front y el
	 * caso que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosPorRolCaso/{idCaso}")
	public Response consultarArbitrosPorRolCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<RolPersonaCaso> personas = (List<RolPersonaCaso>) rolPersonaCasoFacade
					.consultarArbitrosPorRolCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<RolPersonaCaso>>(personas) {
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
	 * Obtiene todos las personas por el rol que se envia desde el front y el
	 * caso que esta asociado
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarRolPersonacaso/{idCaso}/{nombreRol}/{idPersona}")
	public Response eliminarRolPersonaCaso(@PathParam("idCaso") Long idCaso, @PathParam("nombreRol") String nombreRol,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			rolPersonaCasoFacade.eliminarRolPersonaCaso(idCaso, idPersona, nombreRol);

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
	 * Obtiene todos las personas por el rol que se envia desde el front y el
	 * caso que esta asociado
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarPactoTerceroOAutoridadJudicial/{idCaso}/{nombreRol}/{idPersona}")
	public Response eliminarPactoTerceroOAutoridadJudicial(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreRol") String nombreRol, @PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			rolPersonaCasoFacade.eliminarPactoTerceroOAutoridadJudicial(idCaso, idPersona, nombreRol);

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
	 * Obtiene todos las personas por el rol que se envia desde el front y el
	 * caso que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarArbitroConEvento/{idCaso}/{idPersona}")
	public Response eliminarRolPersonaCaso(@PathParam("idCaso") Long idCaso, @PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			Persona arbitro = personaFacade.buscar(idPersona);
			String obsevaciones = "Se ha eliminado el árbitro " + arbitro.getNombreCompleto();
			rolPersonaCasoFacade.eliminarRolPersonaCasoConEvento(idCaso, idPersona, UtilDominios.ROL_PERSONA_ARBITRO,
					UtilDominios.TIPO_EVENTO_ELIMINACION_DE_ARBITRO, obsevaciones);
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
	 * Adiciona un arbitro al caso
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/designarArbitroCaso/")
	public Response designarArbitroCaso(DetalleArbitroDTO detalleArbitro) {
		Response response = null;
		try {
			rolPersonaCasoFacade.designarArbitroCaso(detalleArbitro);
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
	 * 
	 * @param cambioEstadoSuplenteDto
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/nombrarSuplentePrincipal/")
	public Response nombrarSuplentePrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto) {
		Response response = null;
		try {
			rolPersonaCasoFacade.nombrarSuplentePrincipal(cambioEstadoSuplenteDto);
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
	 * Obtiene los detalles propios de los arbitros de un caso
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDetallesArbitros/{idCaso}")
	public Response obtenerDetallesArbitros(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {

			List<DetalleArbitroDTO> detalles = rolPersonaCasoFacade.consultarDetallesArbitros(idCaso, false, false);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DetalleArbitroDTO>>(detalles) {
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
	 * Servicio que permite realizar el reparto para el tipo de funcionario
	 * indicado
	 * 
	 * @param repartoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarReparto/")
	public Response registrarReparto(RepartoDTO repartoDTO) {
		Response response = null;
		try {

			boolean rpc = rolPersonaCasoFacade.reparto(repartoDTO);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(rpc) {
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
	 * Elimina logicamente un arbitro de la base de datos y genera el evento de
	 * eliminacion
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cambiarEstadoArbitros/")
	public Response cambiarEstadoArbitros(CambioEstadoRPC_DTO cambioEstadoDTO) {
		Response response = null;
		try {

			rolPersonaCasoFacade.cambiarEstadoArbitros(cambioEstadoDTO);

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

	/**
	 * Obtiene los detalles propios de los arbitros de un caso
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerInformacionArbitros/{idCaso}")
	public Response obtenerInformacionArbitros(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		HashMap<String, Object> info = new HashMap<>();
		try {
			// Integer principalesPorNombrarPartes =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES,
			// UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,null);
			// Integer principalesPorNombrarJudicial =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_LA_AUTORIDAD_JUDICIAL,
			// UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,null);
			// Integer principalesPorNombrarTercero =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_UN_TERCERO,
			// UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,null);
			// Integer suplPorNombrarPartes =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES,
			// UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,null);
			// Integer suplPorNombrarJudicial =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_LA_AUTORIDAD_JUDICIAL,
			// UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,null);
			// Integer suplPorNombrarTercero =
			// nombramientoPersonaFacade.obtenerNumeroArbitrosPorNombrar(idCaso,
			// UtilDominios.NOMBRAMIENTO_POR_UN_TERCERO,
			// UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,null);
			//
			// Integer arbitrosPrincipalesPorNombrar =
			// principalesPorNombrarPartes + principalesPorNombrarJudicial +
			// principalesPorNombrarTercero;
			// Integer arbitrosPorNombrar = suplPorNombrarPartes +
			// suplPorNombrarJudicial + suplPorNombrarTercero +
			// arbitrosPrincipalesPorNombrar;

			List<DetalleArbitroDTO> detalles = rolPersonaCasoFacade.consultarDetallesArbitros(idCaso, true, false);

			info.put("arbitrosPrincipalesPorNombrar", null);
			info.put("arbitrosPorNombrar", null);
			info.put("arbitros", detalles);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<HashMap<String, Object>>(info) {
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
	 * Obtiene la lista de arbitros y partes de un caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPartesYArbitrosCaso/{idCaso}")
	public Response obtenerPartesYArbitrosCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			HashMap<String, Object> personas = new HashMap<>();

			List<FormularioParteDTO> parteDTOs = personaFacade.consultarPartesCaso(idCaso);
			List<DetalleArbitroDTO> arbitrosDTOs = rolPersonaCasoFacade.consultarDetalleArbitrosCaso(idCaso);

			personas.put("partes", parteDTOs);
			personas.put("arbitros", arbitrosDTOs);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<HashMap<String, Object>>(personas) {
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
	@Path("/obtenerRolDePersonaCaso/")
	public Response obtenerRolDePersonaCaso(CasosAsignadosDTO dto) {
		Response response = null;
		try {
			CasosAsignadosDTO caso = rolPersonaCasoFacade.obtenerRolDeCaso(dto);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<CasosAsignadosDTO>(caso) {
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
	 * registra el pronunciamiento de un secretario
	 * 
	 * @param pronunciamiento
	 *            (entidad)
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/pronunciamientoSecretario/{idPersona}/{idCaso}")
	public Response pronunciamientoSecretario(Pronunciamiento pronunciamiento, @PathParam("idPersona") Long idPersona,
			@PathParam("idCaso") Long idCaso) {
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		Response response = null;
		try {
			rolPersonaCasoFacade.pronunciamientoSecretario(pronunciamiento, idPersonaModificacion, idCaso);

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
	 * TRANS-F-022 SL-CU-Consultar-vinculaci¢n-de-persona-a-un-caso Permite a
	 * una persona natural o jurídica consultar si se encuentra vinculado a un
	 * caso de arbitraje o conciliación
	 * 
	 * @param tipoDoc
	 * @param identificacion
	 * @param nombre
	 * @return VinculacionPersonaCasoDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaVinculacionPersonaCaso/{tipoDoc}/{identificacion}/{nombre}")
	public Response consultaVinculacionPersonaCaso(@PathParam("tipoDoc") String tipoDoc,
			@PathParam("identificacion") String identificacion, @PathParam("nombre") String nombre) {
		Response response = null;
		try {
			tipoDoc = (UtilConstantes.VALOR_UNDEFINED.equals(tipoDoc)) ? null : tipoDoc;
			identificacion = "null".equals(identificacion) ? null : identificacion;
			nombre = "null".equals(nombre) ? null : nombre;
			List<VinculacionPersonaCasoDTO> vinculacionPersonaList = rolPersonaCasoFacade
					.consultaVinculacionPersonaCaso(tipoDoc, identificacion, nombre);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<VinculacionPersonaCasoDTO>>(vinculacionPersonaList) {
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
	 * Obtiene los detalles propios de los arbitros de un caso que han aceptado
	 * la designación
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDetallesArbitrosAceptados/{idCaso}")
	public Response obtenerDetallesArbitrosAceptados(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<DetalleArbitroDTO> detallesAceptados = new ArrayList<DetalleArbitroDTO>();
			List<DetalleArbitroDTO> detalles = rolPersonaCasoFacade.consultarDetallesArbitros(idCaso, false, false);
			for (DetalleArbitroDTO detalle : detalles) {
				if (detalle.getRolPersonaCaso() != null && detalle.getRolPersonaCaso().getEstado() != null
						&& detalle.getRolPersonaCaso().getEstado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO)
						&& detalle.getRolPersonaCaso().getEstadoRegistro()
								.equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
					detallesAceptados.add(detalle);
				}
			}

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DetalleArbitroDTO>>(detallesAceptados) {
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
	 * Obtiene la lista de rolPersoanaCaso asociado a un caso por una lista de
	 * roles y estados.
	 * 
	 * @param idCaso
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerRolPersonaCasoPorRolCasoEstado/{idCaso}/{estado}/")
	public Response obtenerRolPersonaCasoPorRolCasoEstado(List<String> roles, @PathParam("idCaso") Long idCaso,
			@PathParam("estado") String estado) {
		Response response = null;
		// List<String> roles = new ArrayList<String>();
		try {
			List<RolPersonaCaso> rolPersonas = new ArrayList<RolPersonaCaso>();
			rolPersonas = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(idCaso, roles);
			List<RolPersonaCaso> rolPersonasPorEstado = new ArrayList<RolPersonaCaso>();

			for (RolPersonaCaso rolPersonaFor : rolPersonas) {
				if (estado.equals(rolPersonaFor.getEstado())) {
					rolPersonaFor = new RolPersonaCasoDTO().transformarEntidadConDependenciaDePersona(rolPersonaFor);
					rolPersonasPorEstado.add(rolPersonaFor);
				}
			}
			if (rolPersonasPorEstado.size() >= 0) {
				Collections.sort(rolPersonasPorEstado, new Comparator<RolPersonaCaso>() {
					@Override
					public int compare(RolPersonaCaso R1, RolPersonaCaso R2) {
						return R1.getPersona().getNombreCompleto().compareTo(R2.getPersona().getNombreCompleto());
					}
				});

			}

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<RolPersonaCaso>>(rolPersonasPorEstado) {
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
	 * @param cambioEstadoSuplenteDto
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/nombrarSuplenteEspecificoPrincipal/")
	public Response nombrarSuplenteEspecificoPrincipal(CambioEstadoSuplenteDTO cambioEstadoSuplenteDto) {
		Response response = null;
		try {
			rolPersonaCasoFacade.nombrarSuplenteEspecificoPrincipal(cambioEstadoSuplenteDto);
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
	 * retorna los datos de los casos pendientes de pronunciamiento
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/casosPendientesPronunciamentoConciliador/{idPersona}/{rol}")
	public Response casosPendientesPronunciamentoConciliador(@PathParam("idPersona") Long idPersona, @PathParam("rol") String rol) {
		Response response = null;
		try {

			List<PendientePronunciamientoDTO> casosPendientes = rolPersonaCasoFacade
					.casosPendientesPronunciamentoConciliador(idPersona, rol);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PendientePronunciamientoDTO>>(casosPendientes) {
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
	 * CONF104
	 * 
	 * @param Nombrar
	 *            suplente como principal conciliacion
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/nombrarSuplenteConciliacion/")
	public Response nombrarSuplenteConciliacion(PendientePronunciamientoDTO pendientePronunciamiento) {
		Response response = null;
		try {
			rolPersonaCasoFacade.nombrarSuplenteConciliacion(pendientePronunciamiento, null);
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
	@Path("/lotesCartas/")
	public Response lotesCartasByFiltros(LotesCartasFiltrosDTO filtros) {
		Response response = null;
		List<LotesCartasDTO> lotesCartas = new ArrayList<>();
		try {
			lotesCartas = rolPersonaCasoFacade.lotesCartasByFiltros(filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<LotesCartasDTO>>(lotesCartas) {
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
	 * Consultar personas asociadas al caso cuyo rol este habilitado para envio
	 * de cartas (caso CONF094).
	 * 
	 * @author aperez.
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonasAsignadasCaso/{idCaso}/")
	public Response consultarPersonasAsignadasCaso(List<Long> idServicios, @PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PersonaCasoListDTO> personas = rolPersonaCasoFacade.consultarPersonasAsignadasCaso(idCaso,
					idServicios);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaCasoListDTO>>(personas) {
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
	 * Cambia el conciliador, eliminando el designado, liberando la agenda del conciliador para
     * la fecha y hora de la audiencia y generando un log en la ruta del caso
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cambiarConciliador/")
	public Response cambiarConciliador(CambioConciliadorDTO informacionConciliador) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			rolPersonaCasoFacade.cambiarConciliador(informacionConciliador,idPersonaModificacion);
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
	 * Consulta los conciliadores para cambio de conciliador manual segun regla de negocio RN_01 CON-F-066
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerConciliadoresCambio/{idCaso}/")
	public Response obtenerConciliadoresCambio(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<CambioConciliadorDTO> listaConciliadoresCambio = rolPersonaCasoFacade.obtenerConciliadoresCambio(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CambioConciliadorDTO>>(listaConciliadoresCambio) {
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
	 * Servicio que expone la consulta de una PersonaBasicaDTO a partir de la entidad ROL_PERSONA_CASO
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPersonaBasicaConciliadorCasoEstadoNombramiento/{idCaso}/{tipoNombramiento}/")
	public Response obtenerPersonaBasicaConciliadorCasoEstadoNombramiento(@PathParam("idCaso") Long idCaso,
			@PathParam("tipoNombramiento") String tipoNombramiento, List<String> estados) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> conciliadoresPersonaBasicaDTO = rolPersonaCasoFacade.obtenerPersonaBasicaConciliadorCasoEstadoNombramiento(idCaso, estados, tipoNombramiento);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(conciliadoresPersonaBasicaDTO) {
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
	 * Expone el servicio para consultar personas que tienen el rol de apoderado convocante, filtrado por el centro
	 * donde se encuentre la seccion
	 * 
	 * @author pRendon.
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonaCasoPorRolCentro/")
	public Response consultarPersonaCasoPorRolCentro( List<String> centros ) {
		Response response = null;
		try {
			List<String> nombreRoles = new ArrayList<String>();
			nombreRoles.add(UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
			List<PersonaBasicaDTO> personasBasicasDTO = rolPersonaCasoFacade.consultarPersonaCasoPorRolCentro(centros, nombreRoles );
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(personasBasicasDTO) {
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
	@Path("/consultarPartesSeguimiento/{idCaso}")
	public Response consultarPartesSeguimiento(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {

			List<PartesSeguimientoDTO> partes = rolPersonaCasoFacade
					.consultarPartesSeguimiento(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PartesSeguimientoDTO>>(partes) {
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
	 * Servicio que permite reasignar el analista a un caso para control de
	 * legalidad.
	 * 
	 * @author aperez.
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reasignarAnalistaConciliacion/{idCaso}/{idPersona}")
	public Response reasignarAnalistaConciliacion(@PathParam("idCaso") Long idCaso,
			@PathParam("idPersona") Long idPersona, Date fechaLimiteEstudioCaso) {
		Response response = null;
		try {
			rolPersonaCasoFacade.reasignarAnalistaControlLegalidad(idCaso, idPersona, fechaLimiteEstudioCaso);
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
	 * Consulta la vinculacion de una persona (cliente) a un caso
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarVinculacionClienteCaso/")
	public Response consultarVinculacionClienteCaso(PersonaDTO cliente) {
		Response response = null;
		try {
			List<CasoAsignadoDTO> vinculaciones = rolPersonaCasoFacade.consultarVinculacionClienteCaso(cliente);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasoAsignadoDTO>>(vinculaciones){
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
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerArbitrosDisponibles/{idCaso}")
	public Response consultarArbitrosDisponiblesSorteo(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			
			Caso caso = (Caso) casoFacade.getManejadorCrud().buscar(idCaso);
			ParametroSorteo parametrosSort = realizacionSorteoFacade.consultarParametrosSorteo();
			for (ParametroServicioSorteo paramServicio : parametrosSort.getParametroServicioSorteoList()) {
				if (paramServicio.getIdServicio() == caso.getIdServicio()) {
					caso.setParametroServSorteo(paramServicio);			
					break;
				}
			}
			List<Audiencia> listaAudiencias = new ArrayList<>();
			String tipoSorteo = "";
			listaAudiencias = realizacionSorteoFacade.consultarAudienciasSorteoDelDia(new Date ());
			if(!listaAudiencias.isEmpty() && listaAudiencias != null) {
				for(Audiencia audiencia: listaAudiencias) {
					if(audiencia.getIdCaso().equals(caso.getIdCaso())){
						tipoSorteo = audiencia.getTipoSorteo();
						break;
					}
				}
			}
			List<Persona> arbitros = realizacionSorteoFacade.getConformarListaFuncionarioSorteo(caso, false, tipoSorteo);
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Persona>>(arbitros){})
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	 *  verifica la existencia de una persona en un caso dada su idPersona y el idCaso
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/personaExisteEnCaso/{idPersona}/{idCaso}")
	public Response personaExisteEnCaso(@PathParam("idPersona") Long idPersona, @PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Boolean existe = 
					rolPersonaCasoFacade.personaExisteEnCaso(idPersona, idCaso);
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Boolean>(existe){})
							.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
    @Path("/liberarSuplentes/{idCaso}")
	public Response liberarSuplentes(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			rolPersonaCasoFacade.liberarSuplentes(idCaso);
			
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerInformacionOperadores/{idCaso}")
	public Response obtenerInformacionOperadores(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		HashMap<String, Object> info = new HashMap<>();
		try {

			List<DetalleArbitroDTO> detalles = rolPersonaCasoFacade.consultarDetallesArbitros(idCaso, true, true);

			info.put("operadoresPrincipalesPorNombrar", null);
			info.put("operadoresPorNombrar", null);
			info.put("operadores", detalles);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<HashMap<String, Object>>(info) {
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
