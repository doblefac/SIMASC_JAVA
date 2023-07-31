package com.ccb.simasc.rest.recursos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorrerTrasladoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CorrerTrasladoDocumentoDTO;
import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Path( "correrTraslado" )
@Stateless
public class CorrerTrasladoRecurso {
	
	/**
	* REST resource logging 
	*/
	private static final Logger LOG = LogManager.getLogger(ServicioSedeRecurso.class);
	private static final Class<CorrerTraslado> enClass= CorrerTraslado.class;
	
	@EJB
    private ICorrerTrasladoFacade correrTrasladoFacade;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerTrasladosPorCaso/{idCaso}")
	public Response consultarArbitrosPorRolCaso(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<CorrerTrasladoDocumentoDTO> trasladosDTO = correrTrasladoFacade.obtenerDocumentosTrasladoPorCaso(idCaso);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CorrerTrasladoDocumentoDTO>>(trasladosDTO) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

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
