package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
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
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ConvenioDTO;
import com.ccb.simasc.transversal.dto.CrearConvenioDTO;
import com.ccb.simasc.transversal.dto.InformacionFiltroDTO;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Convenio
 * @author sMartinez
 */
@Path( "convenio" )
@Stateless
public class ConvenioRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(ConvenioRecurso.class);
    private static final Class<Convenio> enClass= Convenio.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso

	@EJB
	private IConvenioFacade convenioFacade;
	
	@EJB
	private ManejadorConvenio manejadorConvenio;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Consulta los convenios disponibles en la base de datos
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConvenios")
	public Response consultarArbitrosPorRolCaso(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreRol") String nombreRol) {
		Response response = null;
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarConvenios();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	 * Metodo que consulta los convenios dada una lista de filtros 
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarConveniosPorFiltros")
	public Response consultarConveniosPorFiltros(List<InformacionFiltroDTO> filtrosParametro) {
		Response response = null;
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarConveniosFiltros(filtrosParametro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	 * Metodo que consulta los convenios dada una lista de filtros 
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConvenioPorRelacionado/{idPersona}/{idRol}/{tipoConvenio}")
	public Response consultarConvenioPorRelacionado(@PathParam("idPersona") Long idPersona , @PathParam("idRol") Long idRol
			, @PathParam("tipoConvenio") String tipoConvenio ) {	

		Response response = null;
		try {
			List<Convenio> convenioDTOs = convenioFacade.consultarConvenioPorRelacionado(idPersona, idRol, new Date(), tipoConvenio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Convenio>>(convenioDTOs) {
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
	 * Método encargado de consultar los convenios vigentes por tipo de convenio
	 * 
	 * @param tipoConvenio
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConveniosVigentes/{tipoConvenio}/{servicio}")
	public Response consultarConveniosVigentesPorTipo(@PathParam("tipoConvenio") String tipoConvenio, @PathParam("servicio") String servicio) {
		Response response = null;
		Long idServicio = null;
		if(!servicio.equals(UtilConstantes.VALOR_UNDEFINED))
			idServicio = Long.parseLong(servicio);
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarConveniosVigentes(tipoConvenio, idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	 * Método para traer las jornadas que aun no se han ejecutado de acuerdo a la fecha de inicio
	 * y los centros del usuario en sesion
	 * @param centros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarJornadasSinEjecutar")
	public Response consultarJornadasSinEjecutar(List<Long> centros) {
		Response response = null;
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarJornadas(centros, false);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	 * Método para traer las jornadas que aun no se han ejecutado de acuerdo a la fecha de inicio
	 * y los centros del usuario en sesion
	 * @param centros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarJornadasEjecutadas")
	public Response consultarJornadasEjecutadas(List<Long> centros) {
		Response response = null;
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarJornadas(centros, true);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	 * Método para traer las jornadas que aun no se han ejecutado de acuerdo a la fecha de inicio
	 * y los centros del usuario en sesion
	 * @param centros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarConvenio")
	public Response actualizarConvenio(Convenio convenio) {
		Response response = null;
		try {
			Convenio modificado = convenioFacade.actualizarConvenio(convenio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Convenio>(convenioFacade.transformarEntidadSinDependencias(modificado)) {
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
	 * Servicio que consulta los convenios que sean de tipo convenio por centro
	 * @param centros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarConveniosPorCentro")
	public Response consultarConveniosPorCentro(List<String> centros) {
		Response response = null;
		try {
			List<ConvenioDTO> conveniosDTO = convenioFacade.consultarConveniosPorCentro(centros,null);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<ConvenioDTO>>(conveniosDTO) {
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
	 * Método encargado de consultar los convenios por nombre o codigo de convenio
	 * 
	 * @param ListaConvenios
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConveniosPorNombreCodigo/{nombreConvenio}/{codigoConvenio}")
	public Response consultarConveniosPorNombreCodigo(@PathParam("nombreConvenio") String nombreConvenio, @PathParam("codigoConvenio") Long codigoConvenio, List<Long> idCentros) {
		Response response = null;
		
		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarConveniosPorNombreCodigo(nombreConvenio, codigoConvenio, idCentros );
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
	
	/**  CON-C-013
	 * Crea un convenio creando/actualizando la informacion asociada a la empresa
	 * crea relacionado convenio y sede convenio, actualiza plantilla carta id convenio
	 * @author cagonzalez
	 * @param CrearConvenioDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearConvenio")
	public Response crearConvenio(CrearConvenioDTO informacionConvenio) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			ConvenioDTO  convenio = convenioFacade.crearConvenio(informacionConvenio, contextoDeSesion.getIdUsuario());
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<ConvenioDTO>(convenio) {
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
	
	
	/**  CON-C-015
	 * Consulta la informacion del convenio y la relacionada con éste
	 * @author cagonzalez
	 * @param idConvenio
	 * @return CrearConvenioDTO
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInformacionConvenio/{idConvenio}")
	public Response consultarInformacionConvenio(@PathParam("idConvenio") Long idConvenio) {
		Response response = null;
		try {
			
			CrearConvenioDTO informacionConvenio = convenioFacade.consultarInformacionConvenio(idConvenio);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<CrearConvenioDTO>(informacionConvenio) {
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
	 * Servicio que permite actualizar la informacion relacionada a un convenio
	 * audiencia CON-C-015).
	 * 
	 * @author cagonzalez.
	 * 
	 * @param crearConvenioDTO:
	 *            Dto con la informacion del convenio.
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarInformacionConvenio/")
	public Response actualizarInformacionConvenio(CrearConvenioDTO informacionConvenio) {
		Response response = null;
		try {
			ContextoDeSesion contextoDeSesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			convenioFacade.actualizarInformacionConvenio(informacionConvenio, contextoDeSesion.getIdUsuario());
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
	 * Método encargado de consultar los convenios vigentes por centro
	 * 
	 * @param centros
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarConveniosVigentesPorCentro")
	public Response consultarConveniosVigentesPorTipo(List<String> centros) {
		Response response = null;

		try {
			List<ConvenioDTO> convenioDTOs = convenioFacade.consultarConveniosVigentesPorCentro(centros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<ConvenioDTO>>(convenioDTOs) {
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
