package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFuncionalidadFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Funcionalidad
 * @author sMartinez
 */
@Path( "funcionalidad" )
@Stateless
public class FuncionalidadRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(FuncionalidadRecurso.class);
    private static final Class<Funcionalidad> enClass= Funcionalidad.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IFuncionalidadFacade funcionalidadFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	/**
	 * Servicio para obtener las funcionalidades de acuerdo al tipo de servicio
	 * @param tipoServicio
	 * @param idRol
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/consultarFuncionalidadesRol/{tipoServicio}/{idRol}")
    public Response consultarFuncionalidadesRol(@PathParam("tipoServicio") String tipoServicio, @PathParam("idRol") Long idRol) {
        Response response = null;        
        try {
        	List<Funcionalidad> funcionalidades = funcionalidadFacade.consultarFuncionalidadesRol(idRol, tipoServicio, false, false);            
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<Funcionalidad>>(funcionalidades) {})
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
	 * Método que obtiene la totalidad de funcionalidades sin padre del sistema 
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("consultarFuncionalidades")
    public Response consultarFuncionalidades() {
        Response response = null;        
        try {
        	List<Funcionalidad> funcionalidades = funcionalidadFacade.consultarFuncionalidadesRol(null, null, false, true);            
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<Funcionalidad>>(funcionalidades) {})
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
	 * Método que obtiene las funcionalidades por padre y con las funcionalidades hijas de cada una
	 * @param funcionalidadPadre
	 * @param idRol
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/consultarFuncionalidadesPorPadre/{funcionalidadPadre}/{idRol}")
    public Response consultarFuncionalidadesPorPadre(@PathParam("funcionalidadPadre") String funcionalidadPadre, @PathParam("idRol") Long idRol) {
        Response response = null;
        try {
        	List<Funcionalidad> funcionalidades = funcionalidadFacade.consultarFuncionalidadesPorPadre(funcionalidadPadre, idRol, true, false);            
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<Funcionalidad>>(funcionalidades) {})
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
	 * Método que obtiene las funcionalidades por padre y con las funcionalidades hijas de cada una
	 * @param funcionalidadPadre
	 * @param idRol
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/consultarFuncionalidadesPorPadre/{funcionalidadPadre}")
    public Response consultarFuncionalidadesPorPadre(@PathParam("funcionalidadPadre") String funcionalidadPadre) {
        Response response = null;
        try {
        	List<Funcionalidad> funcionalidades = funcionalidadFacade.consultarFuncionalidadesPorPadre(funcionalidadPadre, null, true, true);            
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<Funcionalidad>>(funcionalidades) {})
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
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/actualizarFuncionalidadesRol/{idRol}")
    public Response actualizarFuncionalidadesRol(List<Funcionalidad> funcionalidades, @PathParam("idRol") Long idRol) {
        Response response = null;        
        try {
        	funcionalidadFacade.actualizarFuncionalidadesRol(funcionalidades, idRol);            
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
