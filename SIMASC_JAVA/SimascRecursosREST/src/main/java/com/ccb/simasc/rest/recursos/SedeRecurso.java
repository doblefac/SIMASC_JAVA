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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISedeFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AgendaPersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaSedeDTO;
import com.ccb.simasc.transversal.dto.SedeDTO;
import com.ccb.simasc.transversal.dto.SedeTipoServicioDTO;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Sede
 * 
 * @author sMartinez
 */
@Path("sede")
@Stateless
public class SedeRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(SedeRecurso.class);
	private static final Class<Sede> enClass = Sede.class;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private ISedeFacade sedeFacade;
	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedes/")
	public Response obtenerSedes() {
		Response response = null;

		try {
			List<Sede> sedes =  sedeFacade.consultarSedes();

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Sede>>(sedes) {
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
	 * Retorna la lista de sedes para la pantalla de Administracion con las
	 * dependencias correspondientes.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesAdmin/")
	public Response obtenerSedesAdmin() {
		Response response = null;
		try {
			List<Sede> sedes = (List<Sede>) sedeFacade.obtenerEntidadesTodos(new ArrayList<Sede>(), true);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Sede>>(sedes) {
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
	 * Retorna la lista de sedes para la pantalla de Administracion consultadas
	 * por id con las dependencias correspondientes.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesAdminByid/{idSede}")
	public Response obtenerSedesAdminByid(@PathParam("idSede") Long idSede) {
		Response response = null;
		try {

			InformacionFiltro filtroTipoServicio = new InformacionFiltro(TipoFiltro.EXACTO,
					TipoServicioSede.ENTIDAD_TIPO_SERVICIO_SEDE_PK_ID_SEDE, idSede);
			List<InformacionFiltro> filtrosTipoServicioConsulta = new ArrayList<>();
			filtrosTipoServicioConsulta.add(filtroTipoServicio);

			Sede sede = sedeFacade.buscar(idSede);
			sede = new SedeDTO().transformarEntidadConDependencias(sede);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Sede>(sede) {
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
	 * Guarda la información de una nueva sede
	 * 
	 * @param paramSede
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearSede")
	public Response nuevaSede(SedeTipoServicioDTO servicioSede) {
		Response response = null;
		try {
			sedeFacade.crearSede(servicioSede);
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
	 * Guarda la información de una nueva sede
	 * 
	 * @param paramSede
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarSede")
	public Response actualizarSede(SedeTipoServicioDTO servicioSede) {
		Response response = null;
		try {
			sedeFacade.actualizarSede(servicioSede);
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
	 * Retorna la lista de sedes para la pantalla de Administracion consultadas
	 * por id con las dependencias correspondientes.
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerSedesByPersonaSede")
	public Response obtenerSedesByPersonaSede(List<PersonaSedeDTO> personaSede) {
		Response response = null;
		List<Sede> sedes = new ArrayList<>();
		try {
			for (PersonaSedeDTO ps : personaSede) {
				InformacionFiltro filtroTipoServicio = new InformacionFiltro(TipoFiltro.EXACTO,
						TipoServicioSede.ENTIDAD_TIPO_SERVICIO_SEDE_PK_ID_SEDE, ps.getPersonaSedePK().getIdSede());
				List<InformacionFiltro> filtrosTipoServicioConsulta = new ArrayList<>();
				filtrosTipoServicioConsulta.add(filtroTipoServicio);
				Sede sede = sedeFacade.buscar(ps.getPersonaSedePK().getIdSede());
				sede = new SedeDTO().transformarEntidadConDependencias(sede);
				sedes.add(sede);
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Sede>>(sedes) {
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
	@Path("/consultarSedesPorCentro/")
	public Response consultarSedesPorCentro(List<Long> idCentro) {
		Response response = null;
		try {
			List<SedeDTO> sedes = 	sedeFacade.consultarSedesPorCentro(idCentro);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedes) {
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
	 * CON-F-075
	 * Servicio que obtiene el listado de las sedes en los cuales el conciliador tiene audiencias asignadas 
	 * @param consulta
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarSedesAudienciaConciliador")
	public Response consultarSedesAudienciaPorConciliador(AgendaPersonaDTO consulta) {
		Response response = null;
		
		try {
			List<SedeDTO> sedes = sedeFacade.consultarSedesAudienciaConciliador(consulta.getFechaInicio(), consulta.getIdPersona());
		
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedes) {
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

	
	/** pRendon 6-02-2018 
	 * CON-F-106
	 * Retorna la lista de sedes donde se le podria hacer una nueva audiencia a un caso, 
	 * filtrado por caso y del caso se saca el servicio y el centro. 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSedesPorCasoServicio/{idCaso}/{idPersona}/")
	public Response consultarSedesPorCasoServicio(@PathParam("idCaso") Long idCaso, @PathParam("idPersona") String idPersona) {
		Response response = null;
		Long persona = null;
		if(!UtilConstantes.VALOR_UNDEFINED.equals(idPersona))
			persona=Long.parseLong(idPersona);
		
		try {
			List<SedeDTO> sedesDTO = sedeFacade.consultarSedesPorCasoServicio(idCaso, persona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedesDTO) {
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
	 * consultar sedes por servicio.
	 * @param idServicio
	 * @return sedes
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarSedesPorServicio/{idServicio}/")
	public Response consultarSedesPorServicio(@PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			List<SedeDTO> sedesDTO = sedeFacade.consultarSedesPorServicio(idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<SedeDTO>>(sedesDTO) {
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
