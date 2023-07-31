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

import com.ccb.simasc.integracion.manejadores.ManejadorFalloAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.AlertaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Alerta
 * @author sMartinez
 */
@Path( "alerta" )
@Stateless
public class AlertaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(AlertaRecurso.class);
    private static final Class<Alerta> enClass= Alerta.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IAlertaFacade alertaFacade; 
	
	@EJB
	private IOrquestadorAlertasFacade orquestadorAlertasFacade;
	
	@EJB
	private ManejadorFalloAlerta manejadorFalloAlerta;
	
	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	

	/**
	 * consulta las alertas por tipo de servicio y estado
	 * @param tipoServicio
	 * @param estado
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarAlertasPorServicioEstado/{tipoServicio}/{estado}")
	public Response consultarAlertasPorServicioEstado(@PathParam("tipoServicio") String tipoServicio, @PathParam("estado") String estado) {
		Response response = null;
		try {
			List<AlertaBasicaDTO> alertas =  alertaFacade.consultarAlertasPorServicioEstado(estado, tipoServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<AlertaBasicaDTO>>(alertas) {
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
	

	/**
	 * actualiza las alertas por alerta BAsicas DTO
	 * @param alertaBasica
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON }) 
	@Path("/actualizarAlerta/")
	public Response actualizarAlerta(AlertaBasicaDTO alertaBasica) {
		Response response = null;
		try {
			alertaFacade.actualizarAlerta(alertaBasica);
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
	 * revision de alertas ejecucion de pruenbsa
	 * @param alertaBasica
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ejecutarAlertasProgramadas/")
	public Response ejecutarAlertasProgramadas() {
		Response response = null;
		try {
			manejadorFalloAlerta.actualizarFallosAlerta();
			//alertaFacade.actualizarEjecucionDiaria();
			orquestadorAlertasFacade.ejecutarAlertasParametrizadas();
			orquestadorAlertasFacade.ejecutarAlertasProgramadasNegocio();
			//manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(17202l, 1l, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
			//manejadorProgramacionAlerta.crearProgramacionAlerta(2000l, 10L, "CSINCON");
			
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
	
	

   // protected region metodos adicionales end


}
