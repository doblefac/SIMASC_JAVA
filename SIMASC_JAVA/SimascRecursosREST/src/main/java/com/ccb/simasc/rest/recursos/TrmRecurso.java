package com.ccb.simasc.rest.recursos;

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITrmFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.TrmDTO;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Path( "trm" )
@Stateless
public class TrmRecurso {

    private static final Logger LOG = LogManager.getLogger(TrmRecurso.class);
    private static final Class<Rol> enClass= Rol.class;
   
	@EJB
    private ITrmFacade trmFacade; 
	
	@Context
    private HttpHeaders httpHeaders;
      
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/obtenerTodos/")
    public Response obtenerTodos() {
        Response response = null;        
        try {
        	List<TrmDTO> trms = trmFacade.obtenerTodosTRM();           
            response = Response.status(Response.Status.OK)        		
                    .entity(new GenericEntity<List<TrmDTO>>(trms) {})
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
	@Path("/consultarTRMPorId/{idTrm}")
	public Response consultarTRMPorId(@PathParam("idTrm") Long idTrm) {
		Response response = null;
		try {
			TrmDTO trmDTO = trmFacade.consultarTRMPorId(idTrm);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<TrmDTO>(trmDTO) {
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
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/crearTRM/")
	public Response crearTRM(TrmDTO trmDTO) {
		Response response = null;
		try {
        	trmFacade.crearTRM(trmDTO); 	
        	response = Response.status(Response.Status.OK)     
					  .entity(new GenericEntity<String>("OK") {})
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
    @Consumes({MediaType.APPLICATION_JSON})
	@Path("/actualizarTRM")
	public Response actualizarTRM(TrmDTO trmDTO) {
		Response response = null;
		try {
			trmFacade.actualizarTRM(trmDTO);
			response = Response.status(Response.Status.OK)     
					  .entity(new GenericEntity<String>("OK") {})
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
	@Path("/consultarTRMActual/")
	public Response consultarTRMActual() {
		Response response = null;
		try {
			TrmDTO trmDTO = trmFacade.consultarTRMActual();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<TrmDTO>(trmDTO) {
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
