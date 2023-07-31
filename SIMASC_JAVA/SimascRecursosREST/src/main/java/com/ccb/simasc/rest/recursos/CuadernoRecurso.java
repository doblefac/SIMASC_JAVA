package com.ccb.simasc.rest.recursos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICuadernoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CuadernoDTO;
import com.ccb.simasc.transversal.entidades.Cuaderno;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Path( "cuaderno" )
@Stateless
public class CuadernoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CuadernoRecurso.class);
    private static final Class<Cuaderno> enClass= Cuaderno.class;
	@EJB
    private ICuadernoFacade cuadernoFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCuadernos")
	public Response obtenerCuadernos() {
		Response response = null;
		try {
			List<CuadernoDTO> cuadernos = cuadernoFacade.obtenerCuadernos();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CuadernoDTO>>(cuadernos) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
}

