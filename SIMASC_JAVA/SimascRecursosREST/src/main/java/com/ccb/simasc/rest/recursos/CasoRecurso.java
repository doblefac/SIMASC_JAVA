package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

// Escriba en esta sección sus modificaciones

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import com.ccb.simasc.comun.fachada.interfaces.IIntegracionSWFacade;
import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INombramientoPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosControlLegalidadDTO;
import com.ccb.simasc.transversal.dto.CasosNoAsignadosDTO;
import com.ccb.simasc.transversal.dto.CasosSinCerrarDTO;
import com.ccb.simasc.transversal.dto.CierreConciliacionDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.InformacionCasoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.CapturaResultadoJornadaDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoCerradoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoIncompletoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosBasicosCasoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosReversarResultadoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicarCasoConvenioDTO;
import com.ccb.simasc.transversal.dto.formularios.SuspenderDTO;
import com.ccb.simasc.transversal.dto.reportes.CasosCobradosDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST Caso
 * 
 * @author sMartinez
 */
@Path("caso")
@Stateless
public class CasoRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(CasoRecurso.class);
	private static final Class<Caso> enClass = Caso.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private INombramientoPersonaFacade nombramientoPersonasFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ICarpetaFacade carpetaFacade;

	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB 
	private ManejadorPagoCaso manejadorPagoCaso;
	
	@EJB
	private IPagoCasoFacade pagoCasoFacade;

	@EJB
	private IIntegracionSWFacade integracionSWFacade;

	@EJB
	private ISeguridadFacade seguridadFacade;

	@EJB
	private IRepartoSvc repartoFacade;
	
	@EJB
	private ICasoTramiteOrdinarioFacade casoTramiteOrdinarioFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Obtiene todos los casos sin dependencias de tipo {@link CasoDTO} que
	 * existen en el contexto de persistencia
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasos/")
	public Response obtenerCasos(@HeaderParam(UtilConstantes.HEADER_SESSION_CONTEXT) String context) {
		Response response = null;
		try {
			List<CasoDTO> casos = (List<CasoDTO>) casoFacade.obtenerTodos(new ArrayList<CasoDTO>(), false);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasoDTO>>(casos) {
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
	 * Obtiene todos los casos con dependencias de tipo {@link CasoDTO} que
	 * existen en el contexto de persistencia
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasosEntidades/")
	public Response obtenerCasosConDependencias() {
		Response response = null;

		try {
			List<Caso> casos = (List<Caso>) casoFacade.obtenerEntidadesTodos(new ArrayList<Caso>(), true);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Caso>>(casos) {
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
	 * Obtiene todos los casos con dependencias de tipo {@link CasoDTO} que
	 * existen en el contexto de persistencia
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasosPatron/{patron}")
	public Response obtenerCasosPorPatron(@PathParam("patron") String patron) {
		Response response = null;

		try {
			List<Caso> casos = (List<Caso>) casoFacade.obtenerEntidadesPatron(patron, new ArrayList<Caso>(), true);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Caso>>(casos) {
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
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCaso/{id}")
	public Response consultarCaso(@PathParam("id") Long id) {
		Response response = null;
		try {
			Caso caso = casoFacade.transformarEntidadSinDependencias(casoFacade.buscar(id));

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
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
	 * Crea un caso con sus datos basicos
	 * 
	 * @param caso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearCaso")
	public Response crearCaso(Caso caso) {
		Response response = null;
		try {
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			caso.setFechaRadicacion(date);
			caso.setFechaUltimaModificacion(date);
			caso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			caso.setEstadoCaso(UtilConstantes.ESTADO_CASO_EN_CREACION);
			Caso casoCreado = manejadorCaso.actualizarCaso(caso);
			Long idCaso = casoCreado.getIdCaso();
			carpetaFacade.generarCuadernos(idCaso);
			PagoCaso pago = pagoCasoFacade.obtenerPagoCasoPorCaso(idCaso);
			if(pago != null && pago.getEstado() != null && !pago.getEstado().equals(UtilDominios.ESTADOS_PAGO_CASO_CASO_ASOCIADO)){
				pago.setEstado(UtilDominios.ESTADOS_PAGO_CASO_CASO_ASOCIADO);
				manejadorPagoCaso.actualizar(pago);
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(casoCreado) {
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
	 * 
	 * @param caso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearCasoPago/{numeroRecibo}")
	public Response crearCasoConPagoCaso(Caso caso, @PathParam("numeroRecibo") String numRecibo) {
		Response response = null;
		try {
			PagoCaso pagoCaso = pagoCasoFacade.obtenerPagosPorNumeroRecibo(numRecibo);
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			caso.setFechaRadicacion(date);
			caso.setFechaUltimaModificacion(date);
			caso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			caso.setEstadoCaso(UtilConstantes.ESTADO_CASO_EN_CREACION);
			pagoCaso = casoFacade.crearCaso(caso, pagoCaso);
			pagoCasoFacade.actualizar(pagoCaso);
			caso.setIdCaso(pagoCaso.getIdCaso());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
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
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDatosBasicosCaso/{id}")
	public Response consultarDatosBasicosCaso(@PathParam("id") Long id) {
		Response response = null;
		try {
			DatosBasicosCasoDTO caso = casoFacade.obtenerDatosBasicosCaso(id);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<DatosBasicosCasoDTO>(caso) {
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
	@Path("/editarDatosBasicosCaso/")
	public Response editarDatosBasicosCaso(DatosBasicosCasoDTO dto) {
		Response response = null;
		try {
			casoFacade.editarDatosBasicos(dto);
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
	@Path("/validarValorPretenciones/{valor}/{tipoCuantia}")
	public Response validarValorPretenciones(@PathParam("valor") BigDecimal valor,
			@PathParam("tipoCuantia") String tipoCuantia) {
		Response response = null;
		try {
			Boolean valido = casoFacade.validarValorPretenciones(valor, tipoCuantia);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(valido) {
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
	 * Obtiene un caso dado su id
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/pactoExistente/{id}")
	public Response validarPactoRegistrado(@PathParam("id") Long id) {
		Response response = null;
		try {
			Object existePacto = casoFacade.validarPactoRegistrado(id);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Object>(existePacto) {
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
	 * Servicio que pone al caso seleccionado en NoAcreditado
	 * 
	 * @param idcaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/pactoNoAcreditado/{idCaso}")
	public Response pactoNoAcreditado(@PathParam("idCaso") Long idcaso) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			casoFacade.pactoNoAcreditado(idcaso, cs.getIdUsuario());
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCaso")
	public Response actualizarCaso(Caso caso) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			Caso nuevoCaso = casoFacade.actualizarCaso(caso, cs.getIdUsuario());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(nuevoCaso) {
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
	@Path("/actualizarEtapaCaso/{plantillaCarta}")
	public Response actualizarEtapaCaso(@PathParam("plantillaCarta") Long plantillaCarta,Caso caso) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			CasoDTO nuevoCaso = casoFacade.actualizarEtapaCaso(caso.getIdCaso(), caso.getEtapa(), plantillaCarta, cs.getIdUsuario());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<CasoDTO>(nuevoCaso) {
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
	@Path("/inactivarCasoRadicacion")
	public Response inactivarCasoRadicacion(Caso caso) {
		Response response = null;
		try {
			casoFacade.inactivarCasoRadicacion(caso);
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCasoDocumentos")
	public Response actualizarCasoDocumentos(Caso caso) {
		Response response = null;
		try {
			casoFacade.actualizarCasoDocumentos(caso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
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
	 * Consulta los casos asignados al funcionario y aplicando los filtros que
	 * se pasan al servicio. Los registros recuperados se deben presentar
	 * ordenados por código del caso de mayor a menor
	 * 
	 * @param filtros
	 *            Filtros seleccionados por el usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/casosAsignados/")
	public Response consultarCasosAsignados(FiltroCasosAsignadosDTO filtros) {
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		Response response = null;
		try {
			List<CasoAsignadoDTO> casosAsignados = casoFacade.consultarCasosAsignados(filtros, cs.getRolPrincipal());

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasoAsignadoDTO>>(casosAsignados) {
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
	 * Consulta los casos asignados al funcionario y aplicando los filtros que
	 * se pasan al servicio. Los registros recuperados se deben presentar
	 * ordenados por código del caso de mayor a menor Este metodo debe ser
	 * llamado cuando la persona tiene el rol de digitalizador.
	 * 
	 * @param filtros
	 *            Filtros seleccionados por el usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/casosDigitalizador/")
	public Response consultarCasosAsignadosDigitalizador(FiltroCasosAsignadosDTO filtros) {

		Response response = null;
		try {
			List<CasoAsignadoDTO> casosAsignados = casoFacade.consultarCasosAsignadosDigitalizador(filtros);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasoAsignadoDTO>>(casosAsignados) {
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

	/*
	 * Obtiene casos en una etapa definida
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasosEtapa/{etapa}")
	public Response obtenerCasosEtapa(@PathParam("etapa") String etapa) {
		Response response = null;

		try {
			List<CasoDTO> casos = casoFacade.obtenerEntidadesPorEtapa(etapa);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasoDTO>>(casos) {
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
	 * Suspende el tramite en la etapa pre-arbitral
	 * 
	 * @param suspenderDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarSuspension")
	public Response adicionarSuspension(SuspenderDTO suspenderDTO) {
		Response response = null;
		try {
			casoFacade.adicionarSuspension(suspenderDTO);
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
	 * Modifica el tramite en la etapa pre-arbitral
	 * 
	 * @param suspenderDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/modificarSuspension")
	public Response modificarSuspension(SuspenderDTO suspenderDTO) {
		Response response = null;
		try {
			casoFacade.modificarSuspension(suspenderDTO);
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
	 * Permite realizar el cierre de un caso
	 * 
	 * @param casoCerradoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cerrar")
	public Response cerrarCaso(CasoCerradoDTO casoCerradoDTO) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			casoFacade.cerrarCaso(casoCerradoDTO, cs);
			String mensajeInfo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO001.toString());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(mensajeInfo) {
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
	 * Cnsulta el nombre de un caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerNombreCaso/{idCaso}")
	public Response obtenerNombreCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		HashMap<String, String> datos = new HashMap<>();
		try {
			datos.put("nombre", casoFacade.obtenerNombreCaso(idCaso));
			datos.put("id", idCaso.toString());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<HashMap<String, String>>(datos) {
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
	 * Cnsulta el nombre de un caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/generarNombreCaso/{idCaso}")
	public Response generarNombreCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		HashMap<String, String> datos = new HashMap<>();
		try {
			datos.put("nombre", casoFacade.generarNombreCaso(idCaso));
			datos.put("id", idCaso.toString());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<HashMap<String, String>>(datos) {
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
	 * Permite realizar el cierre de un caso
	 * 
	 * @param casoCerradoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/actualizarTerminos")
	public Response actualizarTerminos(DatosBasicosCasoDTO datosBasicos) {
		Response response = null;
		try {
			casoFacade.actualizarTerminos(datosBasicos);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
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
	 * Servicio de prueba para habilitar un arbitro
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/habilitar/{idCaso}/{idPersona}/{motivo}")
	public Response habilitar(@PathParam("idCaso") Long idCaso, @PathParam("idPersona") Long idPersona,
			@PathParam("motivo") String motivo) {
		Response response = null;
		try {
			String respuesta = rolPersonaCasoFacade.habilitarArbitro(idCaso, idPersona, motivo);
			response = Response.status(Response.Status.OK).entity(respuesta)
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
	@Path("/documentosNoRecibidos/")
	public Response habilitar(CasoDTO caso) {
		Response response = null;
		try {

			String respuesta = casoFacade.agregarDocumentosNoRecibidos(caso);
			response = Response.status(Response.Status.OK).entity(respuesta)
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
	 * busca si el caso tiene supensiones y le modifica el estado dependiendo si
	 * la supension esta o no activa y si la fecha la hace vigente.
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarEstdoSuspension/{idCaso}")
	public Response actualizarEstdoSuspension(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			boolean respuesta = casoFacade.actualizarEstdoSuspension(idCaso);
			response = Response.status(Response.Status.OK).entity(respuesta)
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
	 * Envia el caso de arbitraje al ministerio
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/enviarCasoArbitrajeMinisterio/{idCaso}")
	public Response enviarCasoArbitrajeMinisterio(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			String respuesta = integracionSWFacade.agregarCasoArbitrajeMinisterio(idCaso);
			response = Response.status(Response.Status.OK).entity(respuesta)
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
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/enviarCasoAmigableComposicionMinisterio/{idCaso}")
	public Response enviarCasoAmigableComposicionMinisterio(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			String respuesta = integracionSWFacade.agregarCasoAmigableComposicionMinisterio(idCaso);
			response = Response.status(Response.Status.OK).entity(respuesta)
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
	 * buseca todos los casos que tiene suspensiones y le modifica el estado
	 * dependiendo si la supension esta o no activa y si la fecha la hace
	 * vigent.
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCasosEstdoSuspension/")
	public Response actualizarCasosEstdoSuspension() {
		Response response = null;
		casoFacade.actualizarCasosEstdoSuspension();

		return response;

	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/activarCaso/{idCaso}")
	public Response activarCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			casoFacade.modificarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_CREADO);
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

	// PagoCasoDTO
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/creacionCasoTramiteOrdinario/{idSolicitudServicio}")
	public Response creacionCasoTramiteOrdinario(PagoCasoDTO pagoCasoDTO,
			@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

		try {
			casoFacade.creacionCasoTramiteOrdinario(idSolicitudServicio, contextoDeSesion.getNombreUsuario(),
					pagoCasoDTO, null);
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
	 * Consulta los casos que se encuentra en creacion o incompletos 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/casosIncompletos")
	public Response consultarCasosIncompletos() {
		Response response = null;
		try {
			List<CasoIncompletoDTO> casosIncompletos = casoFacade.consultarCasosIncompletos();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasoIncompletoDTO>>(casosIncompletos) {
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
	 * CON-F-070 Servicio que recibe el documento con los datos a cargar
	 * 
	 * @param servicio
	 * @param convenio
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/cargueCasosDesdeArchivo/{idServicio}/{idConvenio}/{numeroCasos}")
	public Response cargueCasoDesdeArchivo(@PathParam("idServicio") String servicio,
			@PathParam("idConvenio") String convenio, @PathParam("numeroCasos") String numeroCasos,
			InputStream documento) {
		Response response = null;
		Long idServicio = null;
		Long idConvenio = null;
		Long casos = null;
		if (!servicio.equals(UtilConstantes.VALOR_UNDEFINED))
			idServicio = Long.parseLong(servicio);
		if (!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.parseLong(convenio);
		if (!numeroCasos.equals(UtilConstantes.VALOR_UNDEFINED))
			casos = Long.parseLong(numeroCasos);
		try {
			String nombre = httpHeaders.getRequestHeaders().getFirst(UtilConstantes.HEADER_FILENAME);
			LOG.info("Inicio proceso cargue archivo");
			casoFacade.cargueCasosDesdeArchivo(documento, idConvenio, idServicio, casos, nombre);
			LOG.info("Finalizo proceso cargue archivo");
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
	 * Radiaca un caso de comvenio CON-F-107 CON-F-113 CON-F-096
	 * 
	 * @param radicarCasoConvenio
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearCasoConvenio")
	public Response crearCasoConvenio(RadicarCasoConvenioDTO radicarCasoConvenio) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		try {
			Caso caso = casoFacade.crearCasoConvenio(radicarCasoConvenio, cs.getIdUsuario(), cs.getRolPrincipal(),
					cs.getNombreUsuario());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/cerrarCasoConciliacion/")
	public Response cerrarCasoConciliacion(CierreConciliacionDTO cierreConciliacion) {
		Response response = null;
		try {
			casoFacade.cerrarCasoConciliacion(cierreConciliacion);
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
	 * Obtiene el caso por su id y el tipo de servicio
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasoPorIdYTipoServicio/{idCaso}/{tipoServicio}")
	public Response obtenerCasoPorIdYTipoServicio(@PathParam("idCaso") Long idCaso,
			@PathParam("tipoServicio") String tipoServicio) {
		Response response = null;

		try {
			Caso caso = casoFacade.obtenerCasoPorIdTipoServicio(idCaso, tipoServicio);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
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
	@Path("/enviarCasoConciliacionMinisterio/{idCaso}")
	public Response enviarCasoConciliacionMinisterio(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String mensajeMinisterio = casoFacade.agregarCasoMinisterioConciliacion(idCaso, cs.getNombreUsuario());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(mensajeMinisterio) {
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
	 * Obtiene los casos no asignados para reparto al conciliador.
	 * 
	 * @author aperez.
	 * @param idCentros:
	 *            Lista de centros que tiene asociado el usuario.
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosPendientesPorReparto/")
	public Response consultarCasosPendientesPorReparto(List<Long> idCentros) {
		Response response = null;
		try {
			List<CasosNoAsignadosDTO> casosNoAsignados = casoFacade.consultarCasosPendientesPorReparto(idCentros);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasosNoAsignadosDTO>>(casosNoAsignados) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Obtiene la informacion del caso para la modificacion de parametros para
	 * reparto del caso (CONF-103).
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @return Response.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCasoPorIdCaso/{idCaso}")
	public Response obtenerCasoPorIdCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			InformacionCasoDTO caso = casoFacade.obtenerInformacionCasoPorIdCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<InformacionCasoDTO>(caso) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio que permite modificar un reparto.
	 * 
	 * @author aperez.
	 * 
	 * @param datosEntradaRepartoDTO:
	 *            Datos de entrada para la modificacion del reparto.
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarReparto")
	public Response modificarReparto(DatosEntradaRepartoDTO datosEntradaRepartoDTO) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			datosEntradaRepartoDTO.setUsuario(contextoDeSesion.getNombreUsuario());
			repartoFacade.reparto(datosEntradaRepartoDTO);
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
	 * Servicio encargado de marcar los casos que ya fueron cobrados por un
	 * conciliador
	 * 
	 * CON-C-028
	 * 
	 * @param fechaCobro
	 * @param casosAMarcar
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/marcarCasosCobrados/{fechaCobro}")
	public Response marcarCasosCobrados(@PathParam("fechaCobro") String fechaCobro,
			List<CasosCobradosDTO> casosAMarcar) {
		Response response = null;

		try {

			casoFacade.marcarCasosCobrados(fechaCobro, casosAMarcar,
					Long.valueOf(ContextoDeSesion.obtenerContextoDeSesion(httpHeaders).getNombreUsuario()));
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
	 * Método que obtiene los casos pendientes de audiencia asociados a un
	 * convenio
	 * 
	 * @param convenio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosConvenioPendienteAudiencia/{idConvenio}")
	public Response consultarCasosConvenioPendienteAudiencia(@PathParam("idConvenio") String convenio) {
		Response response = null;
		Long idConvenio = null;
		if (!convenio.equals(UtilConstantes.VALOR_UNDEFINED))
			idConvenio = Long.valueOf(convenio);
		try {
			List<CasoDTO> casos = casoFacade.consultarCasosConvenioAudienciaPendiente(idConvenio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasoDTO>>(casos) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio que realiza el registro del resultado del caso de jornada
	 * CON-F-067
	 * 
	 * @param resultado
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registrarResultadoJornada/")
	public Response registrarResultadoJornada(CapturaResultadoJornadaDTO resultado) {
		Response response = null;
		try {
			casoFacade.registrarResultadoJornada(resultado);
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
	 * CON-F-086
	 * Consulta los casos con las dependias de facturaicon y de PAGO
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosConPagos/{idCaso}")
	public Response consultarCasosConPagos(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Caso caso = casoFacade.consultarCasosConPagos(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * CON-F-116
	 * Servicio para obtener los datos del caso para el cual se reversara el resultado del caso 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDatoCasoReversarJornada/{idCaso}")
	public Response consultarDatoCasoReversarJornada(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<DatosReversarResultadoDTO> caso = casoFacade.consultarDatoCasoReversarJornada(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DatosReversarResultadoDTO>>(caso) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio para realizar el proceso de reversar el resultado de caso de
	 * jornada CON-F-116
	 * 
	 * @param idCaso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/reversarResultadoCaso/{idCaso}")
	public Response reversarResultadoCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			casoFacade.reversarResultadoCasoJornada(idCaso);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * 07-02-2018 pRendon Servicio que expone la consulta de un caso que no
	 * tiene audiencia programada
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasoActivo/{idCaso}")
	public Response consultarCasoActivo(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			CasoDTO casoDTO = casoFacade.consultarCasoActivo(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<CasoDTO>(casoDTO) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * metodo encargado de actualizar el caso desde los datos basicos del caso
	 * de conciliacion
	 * 
	 * @param factura
	 *            parametro que determina si se debe actulizar o crear la
	 *            facturacion
	 * @param caso
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCasoConciliacion/{factura}")
	public Response actualizarCasoConciliacion(@PathParam("factura") Boolean factura, Caso caso) {
		Response response = null;
		try {
			casoFacade.actualizarCasoConciliacion(caso, factura);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio para consultar casos sin cerrar asignados al conciliador
	 * CON-F-102 CON-F-102
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosSinCerrarConciliador/{idConciliador}")
	public Response consultarCasosSinCerrarConciliador(@PathParam("idConciliador") Long idConciliador) {
		Response response = null;
		try {
			List<CasosSinCerrarDTO> caso = casoFacade.consultarCasosSinCerrarConciliador(idConciliador);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CasosSinCerrarDTO>>(caso) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasoConConvenio/{idCaso}")
	public Response consultarCasoConConvenio(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Caso caso = casoFacade.consultarCasoConConvenio(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Caso>(caso) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
			
		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * metodo encargado de reabrir el caso
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param observaciones:
	 *            observaciones del cambio.
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/reabrirCaso/{idCaso}")
	public Response reabrirCaso(@PathParam("idCaso") Long idCaso, String observaciones) {
		Response response = null;
		try {
			casoFacade.reabrirCaso(idCaso, observaciones);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCasosParaControlLegalidad/{reasignarAnalista}/{rol}")
	public Response consultarCasosParaControlLegalidad(@PathParam("reasignarAnalista") boolean reasignarAnalista, @PathParam("rol") String rol) {
		Response response = null;
		try {
			Long idPersonaSesion = Long.valueOf(ContextoDeSesion.obtenerContextoDeSesion(httpHeaders).getIdUsuario());
			List<CasosControlLegalidadDTO> casos = casoFacade.consultarCasosParaControlLegalidad(reasignarAnalista, rol, idPersonaSesion);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CasosControlLegalidadDTO>>(casos) {
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * @param idCaso:
	 *            Digitar informacion para constancia de no competencia
	 * @param 
	 * 		  ParametrosGenerarCartaDTO 
	 * 		  idCaso
	 * 		  motivo
	 * 	      observaciones
	 */
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/digitarInformacionConstanciaNoCompetencia/{idCaso}/{observaciones}/{idPersona}")
	public Response digitarInformacionConstanciaNoCompetencia(@PathParam("idCaso") Long idCaso, @PathParam("observaciones") String observaciones,
			@PathParam("idPersona") Long idPersona) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			
			casoFacade.digitarInformacionConstanciaNoCompetencia(idCaso, observaciones, idPersonaModificacion, idPersona);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio que actualiza el resultado del caso.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarResultadoCaso/{idCaso}/{resultadoCaso}")
	public Response actualizarResultadoCaso(@PathParam("idCaso") Long idCaso,
			@PathParam("resultadoCaso") String resultadoCaso) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			casoFacade.actualizarResultadoCaso(idCaso, resultadoCaso, idPersonaModificacion);
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Valida el nombramiento de los arbitros de un caso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarNombramientoArbitros/{idCaso}")
	public Response validarNombramientoArbitros(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Boolean nombramientoArbitros = casoFacade.validarNombramientoArbitros(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Boolean>(nombramientoArbitros) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerPagoPorIdCaso/{idCaso}")
	public Response obtenerPagoPorIdCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			PagoCaso pago = pagoCasoFacade.obtenerPagoCasoPorCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<PagoCaso>(pago) {
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
	@Path("/migracionSolicitudCaso")
	public Response migracionSolicitudCaso(Long idSolicitudServicio) {
		Response response = null;
		try {
			Long idCaso = casoTramiteOrdinarioFacade.crearPagoCasoRecuperacionEmpresarial(idSolicitudServicio);
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idCaso) {
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
	@Path("/habilitaCambioEtapa/{idCaso}")
	public Response habilitaCambioEtapa(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			Boolean habilitar = casoFacade.habilitaCambioEtapa(idCaso);
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(habilitar) {
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
