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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAsesoriaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.formularios.NuevaAsesoriaDTO;
import com.ccb.simasc.transversal.entidades.Asesoria;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Asesoria
 * 
 * @author sMartinez
 */
@Path("asesoria")
@Stateless
public class AsesoriaRecurso {
	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(AsesoriaRecurso.class);
	private static final Class<Asesoria> enClass = Asesoria.class;

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IAsesoriaFacade asesoriaFacade;

	// protected region atributos end

	@Context
	private HttpHeaders httpHeaders;

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/asesoriaPorId/{idAsesoria}")
	public Response asesoriaPorId(@PathParam("idAsesoria") Long idAsesoria) {
		Response response = null;
		try {
			Asesoria asesoria = asesoriaFacade.buscar(idAsesoria);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Asesoria>(asesoria) {
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
	@Path("/consultaAsesorias")
	public Response consultarAsesorias(AsesoriaDTO filtros) {
		Response response = null;
		try {
			List<AsesoriaDTO> asesorias = asesoriaFacade.consultaAsesorias(filtros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AsesoriaDTO>>(asesorias) {
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
	@Path("/listadoAsesores")
	public Response listadoAsesores(List<CentroDTO> centros) {
		Response response = null;
		try {
			List<Persona> asesores = asesoriaFacade.listadoAsesores(centros);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Persona>>(asesores) {
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
	@Path("/nuevaAsesoria")
	public Response nuevaAsesoria(NuevaAsesoriaDTO asesoria) {
		Response response = null;
		try {
			asesoriaFacade.nuevaAsesoria(asesoria);
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

}
