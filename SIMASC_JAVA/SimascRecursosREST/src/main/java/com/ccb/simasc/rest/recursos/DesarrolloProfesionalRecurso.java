package com.ccb.simasc.rest.recursos;
import java.util.Date;
// protected region imports recurso on begin
import java.util.List;

// Escriba en esta seccion sus modificaciones

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDesarrolloProfesionalFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DesarrolloProfesionalDTO;
import com.ccb.simasc.transversal.entidades.DesarrolloProfesional;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST DesarrolloProfesional
 * @author sMartinez
 */
@Path( "desarrolloprofesional" )
@Stateless
public class DesarrolloProfesionalRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(DesarrolloProfesionalRecurso.class);
    private static final Class<DesarrolloProfesional> enClass= DesarrolloProfesional.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IDesarrolloProfesionalFacade desarrolloProfesionalFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Servicio para obtener los datos del desarrollo profesional de un usuario
	 * ADM-F-026
	 * ADM-F-033
	 * ADM-F-032
	 * 
	 * @param nombreUsuario
	 * @param tipoDesarrollo
	 *            tipo de desarrollo que se busca
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDesarrolloProfesional/{idPersona}/{tipoDesarrollo}/{fechaDesde}/{fechaHasta}")
	public Response obtenerDesarrolloProfesional(@PathParam("idPersona") Long idPersona,
			@PathParam("tipoDesarrollo") String tipoDesarrollo, @PathParam("fechaDesde") String fechaDesde, @PathParam("fechaHasta") String fechaHasta) {
		Response response = null;
		Date periodoDesde = null;
		Date periodoHasta = null;
		try {
			if(!UtilConstantes.VALOR_UNDEFINED.equals(fechaDesde))
				periodoDesde = new Date(Long.parseLong(fechaDesde));
			if(!UtilConstantes.VALOR_UNDEFINED.equals(fechaHasta))
				periodoHasta = new Date(Long.parseLong(fechaHasta));
			List<DesarrolloProfesional> desarrolloList = desarrolloProfesionalFacade
					.consultarDesarrolloProfesional(idPersona, tipoDesarrollo, periodoDesde, periodoHasta);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DesarrolloProfesional>>(desarrolloList) {
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
	 * Metodo para actualizar un desarrollo profesional
	 * ADM-F-026
	 * ADM-F-033
	 * ADM-F-032
	 * @param desarrollo
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarDesarrolloProfesional")
	public Response actualizarDesarrollo(DesarrolloProfesional desarrollo) {
		Response response = null;
		try {
			desarrolloProfesionalFacade.actualizarDesarrolloProfesional(desarrollo);
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
	 * Metodo para consultar el desarrollo profesional 
	 * @param desarrollo
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarDesarrolloProfesional")
	public Response consultarDesarrolloProfesional(DesarrolloProfesionalDTO desarrollo) {
		Response response = null;
		try {
			List<DesarrolloProfesionalDTO> listaDesarrollo = desarrolloProfesionalFacade.consultarDesarrolloProfesional(desarrollo);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DesarrolloProfesionalDTO>>(listaDesarrollo) {
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
