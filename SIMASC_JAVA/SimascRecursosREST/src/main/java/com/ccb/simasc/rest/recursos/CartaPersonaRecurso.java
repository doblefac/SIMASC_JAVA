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

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInvitadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CartaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CartaPersonaDTO;
import com.ccb.simasc.transversal.dto.CorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.PlanillaCorrespondenciaCartaDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.reportes.PlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.GeneradorDocumentos;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST CartaPersona
 * 
 * @author sMartinez
 */
@Path("cartapersona")
@Stateless
public class CartaPersonaRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(CartaPersonaRecurso.class);
	private static final Class<CartaPersona> enClass = CartaPersona.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	private GeneradorDocumentos generadorDocumentos;

	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private IInvitadoFacade invitadoFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/generarCarta/")
	public Response generarCarta(ParametrosGenerarCartaDTO filtros) {
		Response response = null;
		try {

			List<CartaPersona> cartasGeneradasListDTO = cartaPersonaFacade.generarCarta(filtros,null);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CartaPersona>>(cartasGeneradasListDTO) {
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
	@Path("/actualizarCarta/{idCaso}")
	public Response actualizarCarta(CartaPersona carta, @PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			CartaPersonaDTO cartaPersonaDTO = cartaPersonaFacade.actualizarCarta(carta, idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<CartaPersonaDTO>(cartaPersonaDTO) {
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
	@Path("/enviarNotificaciones/{indicadorImpresion}/{indicadorNotificacion}")
	public Response enviarCarta(CartaPersona carta, @PathParam("indicadorImpresion") boolean indicadorImpresion,
			@PathParam("indicadorNotificacion") boolean indicadorNotificacion) {
		Response response = null;
		try {
			cartaPersonaFacade.enviarCarta(carta, indicadorImpresion, indicadorNotificacion);
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
	@Path("/consultarCartas/{idCaso}")
	public Response consultarCartasCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<CartaPersona> cartasGeneradasListDTO = cartaPersonaFacade.consultarCartas(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CartaPersona>>(cartasGeneradasListDTO) {
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
	@Path("/consultarCarta/{idCarta}")
	public Response consultarCarta(@PathParam("idCarta") Long idCarta) {
		Response response = null;
		try {
			CartaPersona cartaDTO = cartaPersonaFacade.consultarCarta(idCarta);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<CartaPersona>(cartaDTO) {
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
	@Path("/consultarCartasPendientesImpresion/")
	public Response consultarCartasPendientesImpresion() {
		Response response = null;
		try {
			List<CartaPendienteImpresionDTO> cartaPendienteImpresionListDTO = cartaPersonaFacade
					.consultarCartasPendientesImpresion();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CartaPendienteImpresionDTO>>(cartaPendienteImpresionListDTO) {
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
	@Path("/actualizarEstadoCartaImpresa/{idCarta}")
	public Response actualizarEstadoCartaImpresa(@PathParam("idCarta") Long idCarta) {
		Response response = null;
		try {
			cartaPersonaFacade.actualizarEstadoCartaImpresa(idCarta);
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
	 * Método para actualizar el estado de las cartas incluidas en la planilla
	 * 
	 * @param planilla
	 * @returnid
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCartasPlanilla")
	public Response actualizarEstadosCartasPlanilla(List<PlanillaCorrespondenciaDTO> planilla) {
		Response response = null;
		try {
			cartaPersonaFacade.actualizarEstadoPlanilla(planilla);
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
	 * Metodo para consultar el estado de las correspondencias.
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param estadosCarta:
	 *            Estados de la carta de envio fisico.
	 * @return List<CorrespondenciaDTO>: Lista de correspondencias.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarCorrespondencias/{idCaso}")
	public Response consultarCorrespondencias(@PathParam("idCaso") Long idCaso, List<String> estadosCarta) {
		Response response = null;
		try {
			List<CorrespondenciaDTO> correspondecias = cartaPersonaFacade.consultarCorrespondencia(idCaso,
					estadosCarta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<CorrespondenciaDTO>>(correspondecias) {
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
	@Path("/generarLotesCartas/")
	public Response generarlotesCartas(List<LotesCartasDTO> loteCartas) {
		
		Response response = null;
		try {
			
			cartaPersonaFacade.generarLotesCartas(loteCartas, ContextoDeSesion.obtenerContextoDeSesion(httpHeaders));
			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error("Error: ", e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
				
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarInformacionEstadoCorrespondencia/")
	public Response consultarInformacionEstadoCorrespondencia(PlanillaCorrespondenciaCartaDTO filtroBusqueda) {
		Response response = null;
		try {
			
			PlanillaCorrespondenciaCartaDTO informacionEstadoCorrespondencia = cartaPersonaFacade.consultarInformacionEstadoCorrespondencia(filtroBusqueda);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<PlanillaCorrespondenciaCartaDTO>(informacionEstadoCorrespondencia) {
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
	 * Actualiza el estado y número guia de las cartas 
	 * 
	 * @return Response.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarEstadoCorrespondencia/")
	public Response actualizarEstadoCorrespondencia(List<LlamadaPlanillaCorrespondenciaDTO> cartas) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
		String idPersonaModificacion = cs.getIdUsuario();
		try {
			cartaPersonaFacade.actualizarEstadoCorrespondencia(cartas, idPersonaModificacion);
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
