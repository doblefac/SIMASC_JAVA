package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaLey;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSector;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEstadoPersonaTipoServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaServicioMateriaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.FiltosPreseleccionArbitros;
import com.ccb.simasc.transversal.dto.FiltrosPreseleccionArbitrosCCB;
import com.ccb.simasc.transversal.dto.InformacionPrestadorDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaMateriaAsignadaDTO;
import com.ccb.simasc.transversal.dto.PersonaServicioMateriaDTO;
import com.ccb.simasc.transversal.dto.PreferenciasDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDTO;
import com.ccb.simasc.transversal.dto.PreseleccionadoDesignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaRolesActivosDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSeleccionConciliadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicacionSolicitudEnmascaradoDTO;
import com.ccb.simasc.transversal.dto.formularios.RolPersonaCasoDesignacionDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaLey;
import com.ccb.simasc.transversal.entidades.PersonaSector;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST Persona
 * 
 * @author sMartinez
 */
@Path("persona")
@Stateless
public class PersonaRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(PersonaRecurso.class);
	private static final Class<Persona> enClass = Persona.class;

	// protected region atributos on begin
	// Escriba en esta secciÃ³n sus modificaciones

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IEstadoPersonaTipoServicioFacade estadoPersonaTipoServicioFacade;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorPersonaSector manejadorPersonaSector;

	@EJB
	private ManejadorPersonaLey manejadorPersonaLey;

	@EJB
	private ManejadorHistoricoPersonaServicioMateria historicoPersSerMateria;

	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	@EJB
	private IPersonaServicioMateriaFacade personaServicioMateriaFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta secciÃ³n sus modificaciones
	/**
	 * Obtiene todos las personas por el rol que se envia desde el front
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPersonasPorRol/{nombreRol}")
	public Response consultarPersonasPorRol(@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<Persona> personas = personaFacade.consultarPersonasPorRol(nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personas) {
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
	 * Obtiene todos las personas por el rol que se envia desde el front
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaPersonaBasicaPorRol/{nombreRol}")
	public Response consultaPersonaBasicaPorRol(@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = personaFacade.consultarPersonasBasicaPorRol(nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	 * Servicio para obtener la lista de personas con el rol parametrizado para
	 * reparto de abogado por tipo de servicio
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaAbogadosPorServicio/{idServicio}")
	public Response consultaAbogadosPorServicio(@PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = personaFacade.consultarAbogadosPorServicio(idServicio);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	 * Servicio que retorna arbitros para preseleccion dependiendo del tipo de
	 * servicio Arbitraje
	 * 
	 * @param tipoServicio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosPreseleccion/{tipoServicio}")
	public Response consultarArbitrosPreseleccion(@PathParam("tipoServicio") String tipoServicio) {
		Response response = null;
		try {
			List<GenericoDTO> list = personaFacade.consultarArbitrosPreseleccion(tipoServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GenericoDTO>>(list) {
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
	 * Registra una persona como tercero o autoridad Judicial en un caso
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/adicionarTerceroOAutoridad/{idCaso}/{rolPersona}")
	// nombre, direccion telefono correo electronico...id del caso y rol de la
	// perso
	public Response adicionarTercerosOAutoridades(Persona datosPersona, @PathParam("idCaso") Long idCaso,
			@PathParam("rolPersona") String rolPersona) {
		Response response = null;
		try {
			// registra una persona al caso
			personaFacade.adicionarTerceroOAutoridad(datosPersona, idCaso, rolPersona);
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
	 * Obtiene todos las personas por el rol que se envia desde el front y el caso
	 * que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPorRolCaso/{idCaso}/{nombreRol}")
	public Response consultarPorRolCaso(@PathParam("idCaso") Long idCaso, @PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<Persona> personas = (List<Persona>) personaFacade.consultarPorRolCaso(idCaso, nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personas) {
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
	 * Obtiene todos las personas por el rol que se envia desde el front y el caso
	 * que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosPorRolCaso/{idCaso}/{nombreRol}")
	public Response consultarArbitrosPorRolCaso(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<Persona> personas = (List<Persona>) personaFacade.consultarArbitrosPorRolCaso(idCaso, nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personas) {
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
	 * Registra una arbitro al caso
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/adicionarArbitroCaso/{idCaso}/{rolPersona}")
	public Response adicionarArbitroCaso(List<Persona> persona, @PathParam("idCaso") Long idCaso,
			@PathParam("rolPersona") String rolPersona) {
		Response response = null;
		try {
			rolPersonaCasoFacade.eliminarArbitrosCaso(idCaso, rolPersona);
			// registra una persona al caso
			personaFacade.adicionarArbitroCaso(persona, idCaso, rolPersona);

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
	 * Consulta la informacion de una parte por el numero de cedula y el id del Caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartePorCedulaPorCaso/{numeroCedula}/{idCaso}")
	public Response consultarPorCedulaPorRolPorCaso(@PathParam("numeroCedula") String numeroCedula,
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			FormularioParteDTO formularioParteDTO = personaFacade.consultarPartePorCedulaPorCaso(numeroCedula, idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
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
	 * Consulta la informacion de una parte por el numero de cedula y el id del Caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPorIdPersonaPorCaso/{idPersona}/{idCaso}")
	public Response consultarPorIdPersonaPorCaso(@PathParam("idPersona") Long idPersona,
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			FormularioParteDTO formularioParteDTO = personaFacade.consultarPorIdPersonaPorCaso(idPersona, idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Registra una persona como abogado demandante o demandado de n caso
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarInformacionParte")
	public Response guardarInformacionParte(FormularioParteDTO formularioParteDTO) {
		Response response = null;
		try {
			Long respuesta = personaFacade.guardarInformacionParte(formularioParteDTO);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("idPersona", respuesta);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Map<String, Long>>(map) {
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarRolPersonasCaso/{idCaso}/{rolPersona}")
	// nombre, direccion telefono correo electronico...id del caso y rol de la
	// perso
	public Response eliminarRolPersonasCaso(List<Persona> persona, @PathParam("idCaso") Long idCaso,
			@PathParam("rolPersona") String rolPersona) {
		Response response = null;
		try {
			// elimina una lista de personas del caso
			rolPersonaCasoFacade.eliminarRolPersonasCaso(persona, idCaso, rolPersona);

			response = Response.ok().header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	 * Metodo que consulta las personas con roles Demandante, Demandando, Apoderado
	 * Demandante y Apoderado Demandado relacionadas a un CasoÃ‚Â 
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesCaso/{idCaso}")
	public Response consultarPartesCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<FormularioParteDTO> parteDTOs = personaFacade.consultarPartesCaso(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Metodo que consulta las personas con roles Demandante, Demandando, Apoderado
	 * Demandante y Apoderado Demandado relacionadas a un CasoÃ‚Â sin apoderado
	 * asignado
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesCasoApoderado/{idCaso}")
	public Response consultarPartesCasoApoderado(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<FormularioParteDTO> parteDTOs = personaFacade.consultarPartesCaso(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Devuelve los nombres de las personas con los rolesÂ Conciliador, Ã�rbitro, y
	 * Secretario de tribunal para el servicio que se pasa como parametro
	 * 
	 * @param tipoServicio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPrestadoresDelServicio/{tipoServicio}")
	// id del caso
	public Response consultarPrestadoresDelServicio(@PathParam("tipoServicio") String tipoServicio) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> basicaDTOs = personaFacade.consultarPrestadoresDelServicio(tipoServicio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(basicaDTOs) {
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
	 * Consulta la informacion de una parte por el numero de cedula, nombre rol y el
	 * id del Caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartePorIdPorRol/{idPersona}/{nombreRol}/{idCaso}")
	public Response consultarPartePorIdPorRol(@PathParam("idPersona") Long idPersona,
			@PathParam("nombreRol") String nombreRol, @PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			FormularioParteDTO formularioParteDTO = personaFacade.consultarPartePorIdPorRol(idPersona, nombreRol,
					idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
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
	 * Se elimina logicamente el registro de rol persona caso recibiendo como
	 * paramteros el id del caso, id de la persona y el rol.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param rolPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarRolPersonasCasoPorId/{idCaso}/{idPersona}/{rolPersona}")
	public Response eliminarRolPersonasCasoPorId(@PathParam("idCaso") Long idCaso,
			@PathParam("idPersona") Long idPersona, @PathParam("rolPersona") String rolPersona) {
		Response response = null;
		try {
			rolPersonaCasoFacade.eliminarRolPersonasCasoPorId(idCaso, idPersona, rolPersona);
			response = Response.ok().header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	 * Obtiene todos las personas por el rol que se envia desde el front
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/DatosBasicosPersonasPorRol/{nombreRol}")
	public Response consultarDatosBasicosPersonasPorRol(@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = (List<PersonaBasicaDTO>) personaFacade
					.consultarDatosBasicosPersonasPorRol(nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	@Path("/consultarArbitrosPrincipales/{idCaso}")
	public Response consultarArbitrosPrincipales(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = (List<PersonaBasicaDTO>) personaFacade
					.consultarArbitrosPrincipales(idCaso, Arrays.asList(UtilConstantes.ROLES_ARBITROS));

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	 * Obtiene todos las personas por el rol que se envia desde el front y el caso
	 * que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosDesignados/{idCaso}/{nombreRol}")
	public Response consultarArbitrosDesignados(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<RolPersonaCasoDesignacionDTO> personas = (List<RolPersonaCasoDesignacionDTO>) rolPersonaCasoFacade
					.consultarArbitrosDesignados(idCaso, nombreRol);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<RolPersonaCasoDesignacionDTO>>(personas) {
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
	@Path("/validarCompletitudPartes/{idCaso}")
	public Response validarCompletitudPartes(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Boolean respuesta = personaFacade.validarCompletitudPartes(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(respuesta) {
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
	 * Servicio encargado de guardar los arbitros preseleccionados para un caso
	 * 
	 * @param arbitrosPreseleccionados
	 * @param idCaso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarArbitrosPreseleccionados/{idCaso}")
	// nombre, direccion telefono correo electronico...id del caso y rol de la
	// perso
	public Response guardarArbitrosPreseleccionados(List<GenericoDTO> arbitrosPreseleccionados,
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			personaFacade.guardarArbitrosPreseleccionados(arbitrosPreseleccionados, idCaso);
			response = Response.ok().header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	 * Servicio encargado de eliminar los arbitros preseleccionados para un caso
	 * 
	 * @param arbitrosPreseleccionados
	 * @param idCaso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarArbitrosPreseleccionados/{idCaso}")
	// nombre, direccion telefono correo electronico...id del caso y rol de la
	// perso
	public Response eliminarArbitrosPreseleccionados(List<GenericoDTO> arbitrosPreseleccionados,
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			personaFacade.eliminarArbitrosPreseleccionados(arbitrosPreseleccionados, idCaso);
			response = Response.ok().header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	 * Servicio que consulta los arbitros que fueron preseleccionados a un caso en
	 * etapa pre arbitral
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPreseleccionadosCaso/{idCaso}")
	public Response consultarPreseleccionadosCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PreseleccionadoDTO> list = personaFacade.consultarPreseleccionadosCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PreseleccionadoDTO>>(list) {
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
	 * Servicio que consulta la informacion de una parte que tenga registro de
	 * correo electronico fallido en el envio.
	 * 
	 * @param idCaso
	 * @param idPersona
	 * @param idAudiencia
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarParteCorreo/{idCaso}/{idPersona}/{idAudiencia}")
	public Response consultarParteCorreo(@PathParam("idCaso") Long idCaso, @PathParam("idPersona") Long idPersona,
			@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;
		try {
			ParteCasoDTO parteCasoDTO = personaFacade.consultarParteCorreo(idCaso, idPersona, idAudiencia);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<ParteCasoDTO>(parteCasoDTO) {
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
	 * Consulta los datos basicos de una persona en un caso con un rol determinado
	 */

	/**
	 * Consulta el secretario activo del caso
	 * 
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaSecretarioActivoCaso/{idCaso}")
	public Response consultaSecretarioActivoCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = personaFacade.consultaSecretarioActivoCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
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
	 * Consulta el secretario activo del caso
	 * 
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarMateriasAsignadas/{idPersona}")
	public Response consultarMateriasAsignadas(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<PersonaMateriaAsignadaDTO> materias = (List<PersonaMateriaAsignadaDTO>) personaFacade
					.consultarMateriasAsignadas(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaMateriaAsignadaDTO>>(materias) {
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
	 * Consulta el secretario activo del caso
	 * 
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaPersonaSistema")
	public Response consultaPersonaSistema() {
		Response response = null;
		try {
			Persona persona = personaFacade.getPersonaSistema();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Persona>(persona) {
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
	 * Obtiene todas las personas asignadas a un caso segun su rol y en determinados
	 * estados
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estados
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosCasoPorEstados/{idCaso}")
	public Response consultarArbitrosCasoPorEstados(@PathParam("idCaso") Long idCaso, List<String> estados) {
		Response response = null;

		try {
			List<Persona> personas = personaFacade.consultarArbitrosCasoPorEstados(idCaso, estados);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personas) {
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
	@Path("/consultarPartesDelCaso/{idCaso}")
	public Response consultarPartesDelCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			List<Persona> personas = personaFacade.consultarPartesDelCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(personas) {
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
	 * Servicio encargado de consultar la informacion básica de una persona
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/validarIdentificacion/")
	public Response validarIdentificacionPersona(PersonaBasicaDTO personaBasicaDTO) {
		Response response = null;

		try {
			RadicacionSolicitudDTO radicacionSolicitudDTO = personaFacade
					.validarIdentificacionPersona(personaBasicaDTO);

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
	 * Servicio encargado de almacenar las preferencias de una persona
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/almacenarPreferencias/")
	public Response almacenarPreferencias(PreferenciasDTO preferenciasDTO) {
		Response response = null;

		try {
			personaFacade.almacenarPreferencias(preferenciasDTO);
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
	 * Consultar persona por id
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonaId/{idPersona}")
	public Response consultarPersonaId(@PathParam("idPersona") Long idPersona) {
		Response response = null;

		try {
			Persona persona = manejadorPersona.buscar(idPersona);
			PersonaDTO personaDto = new PersonaDTO().transformarSinDependencias(persona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<PersonaDTO>(personaDto) {
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
	 * Servicio encargado de consultar la información de una persona en el sistema
	 * SIREP por tipo y número de identificación, so no hay resultados busca la
	 * información en SIMASC con los mismos parámetros
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/validarIdentificacionPagadorSolicitud/")
	public Response validarIdentificacionPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO) {
		Response response = null;

		try {
			FormularioDatosClienteDTO formularioDatosClienteDTO = personaFacade
					.validarIdentificacionPagadorSolicitud(personaBasicaDTO);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioDatosClienteDTO>(formularioDatosClienteDTO) {
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
	 * Servicio encargado de la creación de la información básica de un cliente en
	 * el sistema SIREP
	 * 
	 * @param formularioDatosClienteDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearDatosBasicosClienteSIREP/")
	public Response crearDatosBasicosClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO) {
		Response response = null;

		try {
			Map<String, String> resultado = personaFacade.crearDatosBasicosClienteSIREP(formularioDatosClienteDTO);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Map<String, String>>(resultado) {
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
	 * Servicio encargado de la creación o actualizacion de la información de
	 * contacto de un cliente en el sistema SIREP
	 * 
	 * @param formularioDatosClienteDTO
	 * @return
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizaDatosBasicosClienteSIREP/")
	public Response actualizacionDatosBasicosClienteSIREP(FormularioDatosClienteDTO formularioDatosClienteDTO) {
		Response response = null;

		try {
			personaFacade.actualizaDatosContactoClienteSIREP(formularioDatosClienteDTO);

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
	 * ADM-F-030 Servicio que asocia una persona natural a una juridica
	 * 
	 * @param idPersonaJuridica
	 * @param idPersonaNatural
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/adicionarPersonaNatural/{idPersonaJuridica}/{idPersonaNatural}")
	public Response adicionarPersonaNatural(@PathParam("idPersonaJuridica") Long idPersonaJuridica,
			@PathParam("idPersonaNatural") Long idPersonaNatural) {
		Response response = null;

		try {
			personaFacade.adicionarPersonaNatural(idPersonaJuridica, idPersonaNatural);
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
	 * Servicio encargado de almacenar las preferencias de una persona
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardaAsignacionMaterias/")
	public Response guardaAsignacionMaterias(PersonaServicioMateria personaServicioMateriaOb) {
		Response response = null;

		try {
			personaServicioMateriaFacade.crearMateriaServicio(personaServicioMateriaOb);
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
	 * Servicio encargado de almacenar las preferencias de una persona
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getNumeroCasosMenorAsignados/{idRol}/{idMateria}")
	public Response getNumeroCasosMenorAsignados(@PathParam("idRol") String idRol,
			@PathParam("idMateria") Long idMateria) {
		Response response = null;

		try {
			Long numCasos = null;
			numCasos = manejadorPersona.validaNumeroCasosAsignados(idRol, idMateria);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(numCasos) {
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

	// Conciliación

	/**
	 * Servicio encargado de retornar los Conciliadores activos. Opcional aplicar
	 * filtros en FiltrosSeleccionConciliadorDTO.
	 * 
	 * CON-F-124
	 * 
	 * @param dto
	 * @return List<PersonaBasicaDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerConciliadores/")
	public Response consultarConciliadores(FiltrosSeleccionConciliadorDTO dto) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> basicaDTOs = personaFacade.consultarConciliadoresFiltros(dto);

			if (basicaDTOs.isEmpty())
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO031.toString()));

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(basicaDTOs) {
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
	 * Servicio encargado de retornar los Conciliadores activos. Opcional aplicar
	 * filtros en FiltrosSeleccionConciliadorDTO.
	 * 
	 * CON-F-124
	 * 
	 * @param dto
	 * @return List<PersonaBasicaDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerConciliadoresPorCentro/")
	public Response obtenerConciliadoresPorCentro(BusquedaRolesActivosDTO filtros) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personaList = personaFacade.consultarConciliadoresPorCentros(filtros);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(personaList) {
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
	 * Servicio encargado de retornar los Mediadores activos. Opcional aplicar
	 * filtros en FiltrosSeleccionConciliadorDTO.
	 * 
	 * CON-F-124
	 * 
	 * @param dto
	 * @return List<PersonaBasicaDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMediadoresPorCentro/")
	public Response obtenerMediadoresPorCentro(BusquedaRolesActivosDTO filtros) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personaList = personaFacade.consultarMediadoresPorCentros(filtros);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(personaList) {
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
	 * Servicio encargado de almacenar las preferencias de una persona
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/finalizaAsignacionMaterias/")
	public Response finalizaAsignacionMaterias(PersonaServicioMateria personaServicioMateriaOb) {
		Response response = null;

		try {
			personaServicioMateriaFacade.actualizar(personaServicioMateriaOb);

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
	 * AMD-F-027 servicio encargado de actualizar el resumen de hoja de vida de una
	 * persona
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarResumenHojaVida/")
	public Response actualizarResumenHojaVida(PersonaDTO persona) {
		Response response = null;

		try {
			personaFacade.actualizarResumenHojaVida(persona);
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
	 * AMD-F-027 servicio encargado de actualizar la tarjeta profesional de una
	 * persona
	 * 
	 * @param persona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarDatosProfesionPregrado/{idPersona}/{idProfesion}/{numeroTarjetaProfesional}")
	public Response actualizarDatosProfesionPregrado(@PathParam("idPersona") Long idPersona,
			@PathParam("idProfesion") Long idProfesion,
			@PathParam("numeroTarjetaProfesional") String numeroTarjetaProfesional) {

		Response response = null;

		try {

			personaFacade.actualizarDatosPersonaPRE(idPersona, idProfesion, numeroTarjetaProfesional);
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
	 * Consulta la informacion de una parte por el numero y tipo de identificacion
	 * 
	 * CON-F-113 - CON-F-125
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getConsultarPartePorCedula/{numeroCedula}/{tipoId}")
	public Response getConsultarPartePorCedula(@PathParam("numeroCedula") String numeroCedula,
			@PathParam("tipoId") String tipoId) {
		Response response = null;
		try {
			FormularioParteDTO formularioParteDTO = personaFacade.getConsultarPartePorCedula(numeroCedula, tipoId);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteDTO>(formularioParteDTO) {
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
	 * Servicio encargado de consultar las partes de una Solicitud de Servicio de
	 * Conciliacion - Tramite Ordinario.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesSolicitudServicio/{idSolicitudServicio}")
	public Response consultarPartesSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			List<FormularioParteDTO> parteDTOs = personaFacade.consultarPartesSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Servicio encargado de validar si ya existen las partes Convocante y Convocado
	 * asociados a la Solicitud de un Servicio de Conciliacion de Tramite Ordinario.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return Boolean
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarCompletitudPartesSolicitudServicio/{idSolicitudServicio}/")
	public Response validarCompletitudPartesSolicitudServicio(
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			Boolean respuesta = personaFacade.validarCompletitudPartesSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(respuesta) {
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
	 * Servicio encargado de validar si ya existen las partes Convocante y Convocado
	 * asociados a la Solicitud de un Servicio de Recuperacion empresarial
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return Boolean
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarCompletitudPartesSolicitudServicioRec/{idSolicitudServicio}/")
	public Response validarCompletitudPartesSolicitudServicioRec(
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			Boolean respuesta = personaFacade.validarCompletitudPartesSolicitudServicioRec(idSolicitudServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(respuesta) {
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
	 * Servicio encargado de eliminar una parte asociada a una Solicitud de Servicio
	 * de Conciliacion.
	 * 
	 * CON-F-125
	 * 
	 * @param formularioParteDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarParteSolicitudServicio/{idSolicitudServicio}/{idPersona}/")
	public Response eliminarParteSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			personaFacade.eliminarParteSolicitudServicio(idSolicitudServicio, idPersona);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * consulta las personas por tipo funcionario
	 * 
	 * @param tiposFuncionario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cosultarPersonaBasicaPorTipoFuncionario/")
	public Response cosultarPersonaBasicaPorTipoFuncionario(List<String> tiposFuncionario) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> personas = personaFacade.cosultarPersonaBasicaPorTipoFuncionario(tiposFuncionario);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(personas) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/conciliadoresCasosPendienteCobro/")
	public Response conciliadoresCasosPendienteCobro() {
		Response response = null;
		try {
			List<Persona> conciliadores = personaFacade.conciliadoresCasosPendienteCobro();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(conciliadores) {
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
	@Path("/consultarPartesConciliacionCaso/{idCaso}/{sinIdentificacion}/{bandera}")
	public Response consultarPartesConciliacionCaso(@PathParam("idCaso") Long idCaso,
			@PathParam("sinIdentificacion") boolean sinIdentificacion, @PathParam("bandera") boolean bandera) {
		Response response = null;
		
		try {
			List<ParteCasoDTO> partes = personaFacade.consultarPartesConciliacionCaso(idCaso, sinIdentificacion, bandera);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ParteCasoDTO>>(partes) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/conciliadoresSeguimientoCasos/")
	public Response conciliadoresSeguimientoCasos(List<CentroDTO> centros) {
		Response response = null;
		try {
			List<Persona> conciliadores = personaFacade.conciliadoresSeguimientoCasos(centros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(conciliadores) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConciliadoresConAudienciasProgramada/{fechaAudiencia}")
	public Response consultarConciliadoresConAudienciasProgramada(@PathParam("fechaAudiencia") Long fechaAudiencia) {
		Response response = null;
		try {
			Date fecha = new Date(fechaAudiencia);
			List<PersonaBasicaDTO> conciliadores = personaFacade.consultarConciliadoresConAudienciasProgramada(fecha);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaBasicaDTO>>(conciliadores) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonaTipoYNumeroDocumento/")
	public Response consultarPersonaTipoYNumeroDocumento(Persona filtros) {
		Response response = null;
		try {
			Persona persona = personaFacade.consultarPersonaTipoYNumeroDocumento(filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Persona>(persona) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * TRANS-F-020 consultar informacion del prestador.
	 * 
	 * @param idPersona
	 * @param idRol
	 * @return InformacionPrestadorDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInformacionPrestador/{idPersona}/{idRol}")
	public Response consultarInformacionPrestador(@PathParam("idPersona") Long idPersona,
			@PathParam("idRol") Long idRol) {
		Response response = null;
		try {
			InformacionPrestadorDTO informacionPrestadorDTO = personaFacade.consultarInformacionPrestador(idPersona,
					idRol);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<InformacionPrestadorDTO>(informacionPrestadorDTO) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Consultar Funcionarios del CAC
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarFuncionariosCAC/")
	public Response consultarFuncionariosCAC() {
		Response response = null;
		try {
			List<Persona> funcionariosCAC = personaFacade.consultarFuncionariosCAC();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(funcionariosCAC) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Consultar persona por id
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPersonaServicioMateria/{idPersona}/{idMateria}/{idServicio}")
	public Response consultarPersonaServicioMateria(@PathParam("idPersona") Long idPersona,
			@PathParam("idMateria") Long idMateria, @PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			PersonaServicioMateria personaServicioMateria = null;
			if (!idMateria.equals(Long.valueOf((long) UtilConstantes.CERO))) {
				personaServicioMateria = manejadorPersonaServicioMateria.consultarPersonaServicioMateria(idServicio,
						idMateria, idPersona);
			} else {
				// ADM-C-022
				List<PersonaServicioMateria> listaPersonaServicioMateria = manejadorPersonaServicioMateria
						.consultarPersonaServicioMateriaInscritaPorServicio(idServicio, null, idPersona);
				if (listaPersonaServicioMateria != null && !listaPersonaServicioMateria.isEmpty()) {
					personaServicioMateria = listaPersonaServicioMateria.get(0);
				} else {
					personaServicioMateria = new PersonaServicioMateria();
				}
			}
			PersonaServicioMateriaDTO personaServicioMateriaDto = new PersonaServicioMateriaDTO()
					.transformarSinDependencias(personaServicioMateria);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<PersonaServicioMateriaDTO>(personaServicioMateriaDto) {
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
	@Path("/activarParaSorteo/")
	public Response activarParaSorteo(PersonaServicioMateriaDTO psm) {
		Response response = null;
		try {

			manejadorPersonaServicioMateria.activarParaSorteo(psm);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Consultar los sectores asociados a una persona.
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerLeyAplicableByUsuario/{idPersona}")
	public Response obtenerLeyAplicableByUsuario(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<PersonaLey> personaSector = manejadorPersonaLey.obtenerLeyAplicableByUsuario(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaLey>>(personaSector) {
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
	@Path("/agregarListaLeyAplicableUsuario/{idPersona}")
	public Response agregarListaLeyAplicableUsuario(List<PersonaLey> listaLeyAplicable,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			manejadorPersonaLey.agregarListaLeyAplicableUsuario(listaLeyAplicable, idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(true) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Consultar los sectores asociados a una persona.
	 * 
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSectoresByUsuario/{idPersona}")
	public Response consultarSectoresByUsuario(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<PersonaSector> personaSector = manejadorPersonaSector.obtenerSectoresByUsuario(idPersona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<PersonaSector>>(personaSector) {
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
	@Path("/agregarListaSectoresUsuario/{idPersona}")
	public Response agregarListaSectoresUsuario(List<PersonaSector> listaSectores,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			manejadorPersonaSector.agregarListaSectoresUsuario(listaSectores, idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(true) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

	/**
	 * Servicio que retorna arbitros para preseleccion dependiendo del tipo de
	 * servicio Arbitraje
	 * 
	 * @param tipoServicio
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarArbitrosInternacionalPreseleccion/{tipoServicio}")
	public Response consultarArbitrosInternacionalPreseleccion(@PathParam("tipoServicio") String tipoServicio,
			FiltosPreseleccionArbitros filtros) {
		Response response = null;
		try {
			List<GenericoDTO> list = personaFacade.consultarArbitrosInternacionalPreseleccion(tipoServicio, filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GenericoDTO>>(list) {
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
	@Path("/consultarArbitrosPreseleccionFiltros/{tipoServicio}")
	public Response consultarArbitrosPreseleccionFiltros(@PathParam("tipoServicio") String tipoServicio,
			FiltosPreseleccionArbitros filtros) {
		Response response = null;
		try {
			List<GenericoDTO> list = personaFacade.consultarArbitrosPreseleccionFiltros(tipoServicio, filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GenericoDTO>>(list) {
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
	@Path("/consultarArbitrosPreseleccionCCBFiltros")
	public Response consultarArbitrosPreseleccionCCBFiltros(FiltrosPreseleccionArbitrosCCB filtros) {
		Response response = null;
		try {
			List<GenericoDTO> list = personaFacade.consultarArbitrosPreseleccionCCBFiltros(filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<GenericoDTO>>(list) {
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

	/**
	 * Servicio encargado de consultar las partes de una Solicitud de Servicio de
	 * Conciliacion - Tramite Ordinario.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesCasoAdicionarSolicitud/{idSolicitudServicio}")
	public Response consultarPartesCasoAdicionarSolicitud(Long idSolicitudServicioCaso,
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			List<FormularioParteDTO> parteDTOs = personaFacade
					.consultarPartesSolicitudServicio(idSolicitudServicioCaso);
			for (FormularioParteDTO parte : parteDTOs) {
				parte.setIdSolicitudServicio(idSolicitudServicio);
				parte.setIdCaso(null);
				if (parte.getRol() != null && parte.getRol().getDominioPK() != null
						&& parte.getRol().getDominioPK().getCodigo() != null) {
					if (parte.getRol().getDominioPK().getCodigo().equals(UtilDominios.ROL_PERSONA_ACREEDOR)) {
						Dominio rol = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE,
								UtilDominios.ROL_PERSONA_ACREEDOR_RECUPERACION);
						parte.setRol(rol);
					} else if (parte.getRol().getDominioPK().getCodigo().equals(UtilDominios.ROL_PERSONA_DEUDOR)) {
						Dominio rol = dominioFacade.getObtenerDominio(UtilDominios.DOMINIO_ROL_PERSONA_PARTE,
								UtilDominios.ROL_PERSONA_DEUDOR_RECUPERACION);
						parte.setRol(rol);
					}
					if (!parte.getRol().getDominioPK().getCodigo().equals(UtilDominios.ROL_PERSONA_SOLICITANTE)) {
						personaFacade.guardarInformacionParte(parte);
					}
				}

				// UbicacionDTO ubicacion =
				// ubicacionRolPersonaCasoFacade.asociarUbicacionRolPersonaCaso(ubicacionPersonaCasoDTO);
			}
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Servicio encargado de consultar las partes de una Solicitud de Servicio de
	 * Conciliacion - Tramite Ordinario.
	 * 
	 * CON-F-125
	 * 
	 * @param idSolicitudServicio
	 * @return List<FormularioParteDTO>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartesSolicitudServicioRec/{idSolicitudServicio}")
	public Response consultarPartesSolicitudServicioRec(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {			
			List<FormularioParteDTO> parteDTOs = personaFacade.consultarPartesSolicitudServicioRec(idSolicitudServicio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<FormularioParteDTO>>(parteDTOs) {
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
	 * Servicio encargado de eliminar una parte asociada a una Solicitud de Servicio
	 * de Conciliacion.
	 * 
	 * CON-F-125
	 * 
	 * @param formularioParteDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarParteRecSolicitudServicio/{idSolicitudServicio}/{idPersona}/")
	public Response eliminarParteRecSolicitudServicio(@PathParam("idSolicitudServicio") Long idSolicitudServicio,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			personaFacade.eliminarDefinitivoParteSolicitudServicio(idSolicitudServicio, idPersona);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	/**
	 * Servicio encargado de consultar la informacion básica de una persona y enmascarar los datos privados
	 * 
	 * @param personaBasicaDTO
	 * @return PersonaBasicaDTO
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/validarIdentificacionEnmascarando/")
	public Response validarIdentificacionPersonaEnmascarando(PersonaBasicaDTO personaBasicaDTO) {
		Response response = null;

		try {
			RadicacionSolicitudEnmascaradoDTO radicacionSolicitudDTO = personaFacade
					.validarIdentificacionPersonaEnmascarando(personaBasicaDTO);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<RadicacionSolicitudEnmascaradoDTO>(radicacionSolicitudDTO) {
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
	 * Consulta la informacion de una parte por el numero de cedula y el id del Caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPartePorCedulaPorCasoEmascarando/{numeroCedula}/{idCaso}")
	public Response consultarPorCedulaPorRolPorCasoEnmascarando(@PathParam("numeroCedula") String numeroCedula,
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			FormularioParteEnmascaradoDTO formularioParteDTO = personaFacade.consultarPartePorCedulaPorCasoEnmascarando(numeroCedula, idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioParteEnmascaradoDTO>(formularioParteDTO) {
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
	@Path("/validarIdentificacionEnmascaradoPagadorSolicitud/")
	public Response validarIdentificacionEnmascaradoPagadorSolicitud(PersonaBasicaDTO personaBasicaDTO) {
		Response response = null;

		try {			
			FormularioDatosClienteDTO formularioDatosClienteDTO = personaFacade
					.validarIdentificacionEnmascaradoPagadorSolicitud(personaBasicaDTO);			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<FormularioDatosClienteDTO>(formularioDatosClienteDTO) {
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
	@Path("/consultarPreseleccionadosDesignadoCaso/{idCaso}")
	public Response consultarPreseleccionadosCasoDesignado(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PreseleccionadoDesignadoDTO> list = personaFacade.consultarPreseleccionadosCasoDesignado(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PreseleccionadoDesignadoDTO>>(list) {
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

}
