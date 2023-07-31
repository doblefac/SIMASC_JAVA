package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IMateriaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.EspecialidadDTO;
import com.ccb.simasc.transversal.dto.MateriaBasicaDTO;
import com.ccb.simasc.transversal.dto.MateriaDTO;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Materia
 * @author sMartinez
 */
@Path( "materia" )
@Stateless
public class MateriaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(MateriaRecurso.class);
    private static final Class<Materia> enClass= Materia.class;
   
   	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@EJB
	private IMateriaFacade materiaFacade;
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Consultar el listado de materias
	 * ADM-F-026
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMaterias/")
	public Response obtenerMaterias() {
		Response response = null;

		try {
			List<Materia> materias = materiaFacade.consultarMaterias();

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Materia>>(materias) {
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
	@Path("/obtenerMateriasConServicios/")
	public Response obtenerMateriasConServicios() {
		Response response = null;

		try {
			List<Materia> materias = materiaFacade.consultarMateriasConServicios();

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Materia>>(materias) {
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
	@Path("/obtenerMateriasServicio/{idServicio}")
	public Response consultarMateriasPorServicio(@PathParam("idServicio") String idDeServicio) {
		Response response = null;
		Long idServicio = idDeServicio.equals(UtilConstantes.VALOR_UNDEFINED)? null: Long.valueOf(idDeServicio);
		try {
			List<Materia> materias = materiaFacade.consultarMateriasPorServicio(idServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Materia>>(materias) {
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
     * ADM-C-003
     * Consulta las materias del servicio asociado al rol a través de la tabla
     * ParametroServicioSorteo y el sorteo indicador sorteo_con_materia es verdadero.
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMateriasRol/{nombreRol}/")
	public Response obtenerMateriasRol(@PathParam("nombreRol") String nombreRol){
		Response response = null;

		try {
			List<MateriaBasicaDTO> materias = materiaFacade.consultarMateriasRol(nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<MateriaBasicaDTO>>(materias) {
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
     * ADM-C-029
     * Consulta las materias del servicio asociado al rol a través
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarMateriasporRolCONARB/{nombreRol}/")
	public Response consultarMateriasporRolCONARB(@PathParam("nombreRol") String nombreRol){
		Response response = null;

		try {
			List<MateriaBasicaDTO> materias = materiaFacade.consultarMateriasporRolCONARB(nombreRol);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<MateriaBasicaDTO>>(materias) {
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
     * ADM-C-029
     * Consulta las especialidades por materia
     * 
     * @param nombreRol Codigo de dominio del rol
     * @return
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarEspecialidadesMaterias/{idMateria}/")
	public Response consultarEspecialidadesMaterias(@PathParam("idMateria") String idMateria){
		Response response = null;

		try {
			List<EspecialidadDTO> especialidades = materiaFacade.consultarEspecialidadesMaterias(idMateria);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<EspecialidadDTO>>(especialidades) {
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
	
	/** este servicio se puede actualizar para que reciba parametros
     * ADM-C-004
     * Consulta las materias activas del sistema
     * @return List<MateriaDTO> materiasDTO
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarMateriasParametros/")
	public Response consultarMateriasParametros(){
		Response response = null;

		try {
			List<MateriaDTO> materiasDTO = materiaFacade.consultarMateriasParametros();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<MateriaDTO>>(materiasDTO) {
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
