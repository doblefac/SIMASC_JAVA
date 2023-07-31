package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.log4j.Logger;

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ConsultaDominioDTO;
import com.ccb.simasc.transversal.dto.DominioDTO;
import com.ccb.simasc.transversal.dto.formularios.TipoDocumentalDTO;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Dominio
 * 
 * @author sMartinez
 */
@Path("dominio")
@Stateless
public class DominioRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = Logger.getLogger(DominioRecurso.class);
	private static final Class<Dominio> enClass = Dominio.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private IPersonaFacade personaFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * consulta el dominio por su codigo
	 * 
	 * @param codigo
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDominio/{nomDominio}/{codigo}")
	public Response obtenerDominio(@PathParam("nomDominio") String nomDominio, @PathParam("codigo") String codigo) {
		Response response = null;

		try {
			DominioDTO dominioDTO = dominioFacade.consultarDominioDTO(nomDominio, codigo);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<DominioDTO>(dominioDTO) {
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
	 * Obtiene los dominios
	 * 
	 * @param List<String> nombreDominios
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarDominios")
	public Response consultarDominios(List<String> nombreDominios) {
		Response response = null;
		try {
			List<DominioDTO> dominiosDTO = dominioFacade.consultarDominiosPorNombre(nombreDominios);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DominioDTO>>(dominiosDTO) {
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
	 * Obtiene los dominios definidos en los parametros de consulta. Si viene
	 * definido el clasificador se obtienen los dominios que agrupa el clasificador,
	 * de lo contrario, todos los valores de dominio identificados por el nombre de
	 * dominio.
	 * 
	 * @param List<ConsultaDominioDTO> parametrosBusqueda
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarDominiosPorClasificadores")
	public Response consultarDominiosPorClasificadores(List<ConsultaDominioDTO> parametrosBusqueda) {
		Response response = null;
		try {
			List<DominioDTO> dominiosDTO = dominioFacade.consultarDominiosPorClasificadores(parametrosBusqueda);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DominioDTO>>(dominiosDTO) {
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
	 * Devuelve los hijos de un agrupamiento
	 * 
	 * @param dominio : Dominio padre
	 * @param codigo  : Codigo de dominio padre
	 * @return Lista de dominios hijos
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDominiosHijos/{nomDominioPadre}/{codigoPadre}")
	public Response obtenerDominiosHijos(@PathParam("nomDominioPadre") String nombreDominioPadre,
			@PathParam("codigoPadre") String codigoPadre) {

		Response response = null;
		try {
			List<DominioDTO> dominiosDTO = new DominioDTO().transformarColeccionSinDependencias(
					dominioFacade.obtenerDominiosHijos(nombreDominioPadre, codigoPadre));
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DominioDTO>>(dominiosDTO) {
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
	 * Obtiene los dominios
	 * 
	 * @param List<String> nombreDominios
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerListasParaAsignarArbitro/{idCaso}")
	public Response obtenerListasParaAsignarArbitro(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		Map<String, Object> listas = new HashMap<>();
		try {
			listas = personaFacade.obtenerListasParaAsignarArbitro(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<HashMap<String, Object>>((HashMap<String, Object>) listas) {
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
	 * Obtiene los dominios
	 * 
	 * @param List<String> nombreDominios
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMotivosInactivacionArbitros/")
	public Response obtenerMotivosInactivacionArbitros() {
		Response response = null;

		try {
			List<DominioDTO> motivosInactivacion = dominioFacade.obtenerMotivosInactivacionArbitros();

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DominioDTO>>(motivosInactivacion) {
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
	 * Obtiene los dominios definidos en los parametros de consulta. Si viene
	 * definido el clasificador se obtienen los dominios que agrupa el clasificador,
	 * de lo contrario, todos los valores de dominio identificados por el nombre de
	 * dominio.
	 * 
	 * @param List<ConsultaDominioDTO> parametrosBusqueda
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearDominio")
	public Response crearDominio(Dominio dominio) {
		Response response = null;
		try {
			dominioFacade.actualizarSinAtributosDeAuditoria(dominio);
			dominioFacade.recargarDominios();
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

	// protected region metodos adicionales end
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/dominioMotivosInactivacionArbitro")
	public Response obtenerDominioMotivosInactivacionArbitro() {

		Response response = null;
		try {
			List<Dominio> dominiosDTO = dominioFacade.obtenerDominioMotivosInactivacionArbitro();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Dominio>>(dominiosDTO) {
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
	@Path("/obtenerDominioPorClasificador/{dominioClasificador}")
	public Response obtenerDominioPorClasificador(@PathParam("dominioClasificador") String dominioClasificador) {
		Response response = null;

		try {
			List<Dominio> dominios = dominioFacade.obtenerDominioPorClasificador(dominioClasificador);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Dominio>>(dominios) {
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
	 * consulta el dominio por su codigo
	 * 
	 * @param codigo
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDominioPorServicio/{idServicio}/{nomDominio}")
	public Response obtenerDominioPorServicio(@PathParam("idServicio") Long idServicio,
			@PathParam("nomDominio") String nomDominio) {
		Response response = null;

		try {
			List<Dominio> dominios = dominioFacade.obtenerDominioPorServicio(idServicio, nomDominio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Dominio>>(dominios) {
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
	 * consulta el dominio de los tipos docunebtales por el caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/tiposDocumentales/{idCaso}")
	public Response obtenerTipoDocumentalDominioCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;

		try {
			List<TipoDocumentalDTO> tiposDocumentales = dominioFacade.consultarTipoDocumentalCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<TipoDocumentalDTO>>(tiposDocumentales) {
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
	@Path("/obtenerTipoSorteoPorServicio/{idServicio}")
	public Response obtenerTipoSorteoPorServicio(@PathParam("idServicio") Long idServicio) {
		Response response = null;

		try {
			List<Dominio> dominios = dominioFacade.obtenerTipoSorteoPorServicio(idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Dominio>>(dominios) {
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
