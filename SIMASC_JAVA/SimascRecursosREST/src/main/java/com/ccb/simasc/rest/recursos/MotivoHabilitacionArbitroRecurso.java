package com.ccb.simasc.rest.recursos;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.ManejadorMotivoHabilitacionArbitro;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.MotivoHabilitacionArbitro;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Path( "motivoHabilitacionArbitro" )
@Stateless
public class MotivoHabilitacionArbitroRecurso {
	
	 private static final Logger LOG = LogManager.getLogger(MotivoHabilitacionArbitroRecurso.class);
	  private static final Class<MotivoHabilitacionArbitroRecurso> enClass= MotivoHabilitacionArbitroRecurso.class;
	
	@EJB
	private ManejadorMotivoHabilitacionArbitro  motivoHabilitacionArbitro;
	
    @Inject
    private ApplicationRequestContext appContext;
	
	/**
	 * Servicio que retorna todos los usuarios 
	 * @param usuario
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerMotivosHabilitacionArbitro")
	public Response obtenerMotivosHabilitacionArbitro() {
		Response response = null;

		try {
			List<MotivoHabilitacionArbitro> motivos = motivoHabilitacionArbitro.findAll();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<MotivoHabilitacionArbitro>>(motivos) {
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
	@Path("/obtenerMotivosHabilitacionArbitro/{idMotivo}")
	public Response EliminarMotivosHabilitacionArbitro(@PathParam("idMotivo") Long codigo) {
		Response response = null;

		try {
			 MotivoHabilitacionArbitro motivo = motivoHabilitacionArbitro.buscar(codigo);
			 
			 if(motivo!=null) {
				 motivo.setEstadoRegistro(UtilDominios.ESTADO_MOTIVO_INACTIVO);
				 motivo.setFechaUltimaModificacion(new Date());
				 String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
					if (appContext != null && appContext.getContextoSesion() != null
							&& appContext.getContextoSesion().getNombreUsuario() != null)
						usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
				motivo.setIdUsuarioModificacion(usuarioModificacion);
				 motivoHabilitacionArbitro.actualizar(motivo);
			 }
			response = Response.status(Response.Status.OK).entity((String) "OK").header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/crearMotivosHabilitacionArbitro")
	public Response crearMotivosHabilitacionArbitro(MotivoHabilitacionArbitro motivo) {
		Response response = null;
		try {
			motivo.setFechaUltimaModificacion(new Date());
			String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
				if (appContext != null && appContext.getContextoSesion() != null
						&& appContext.getContextoSesion().getNombreUsuario() != null)
					usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
			motivo.setIdUsuarioModificacion(usuarioModificacion);
			motivo.setEstadoRegistro(UtilDominios.ESTADO_MOTIVO_ACTIVO);
			MotivoHabilitacionArbitro motivoexistente = null;
			if(motivoHabilitacionArbitro.validarSiExiste(motivo.getCodigoInactivacion())) {
				motivoexistente = motivoHabilitacionArbitro.
						findbyCodigoInactivacion(motivo.getCodigoInactivacion());
			}
			
			if(motivoexistente == null) {
				motivoHabilitacionArbitro.crearMotivoHabilitacionArbitro(motivo);  
			}else {
				motivoexistente.setFechaUltimaModificacion(new Date());
				motivoexistente.setIdUsuarioModificacion(usuarioModificacion);
				motivoexistente.setEstadoRegistro(UtilDominios.ESTADO_MOTIVO_ACTIVO);
				motivoHabilitacionArbitro.actualizar(motivoexistente);
			}
	
        	response = Response.status(Response.Status.OK).entity((String) "OK").header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();             
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
