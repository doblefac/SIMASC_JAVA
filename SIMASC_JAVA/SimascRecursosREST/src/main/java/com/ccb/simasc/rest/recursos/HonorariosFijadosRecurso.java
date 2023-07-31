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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IHonorariosFijadosFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.HonorariosFijadosDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasDTO;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST HonorariosFijados
 * @author sMartinez
 */
@Path( "honorariosfijados" )
@Stateless
public class HonorariosFijadosRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(HonorariosFijadosRecurso.class);
    private static final Class<HonorariosFijados> enClass= HonorariosFijados.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	/**
	 * Fachada utilizada para gestionar los datos de los honorarios fijados
	 */
	@EJB
	private IHonorariosFijadosFacade honorariosFijadosFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Servicio que consulta los honorarios fijados para el caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarHonorariosFijados/{idCaso}")
	public Response consultarHonorariosFijados(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<HonorariosFijadosDTO> honorariosFijadosDTO = honorariosFijadosFacade
					.consultarHonorariosFijados(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<HonorariosFijadosDTO>>(honorariosFijadosDTO) {
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
	 * calcula las tarifas para los arbitros
	 * 
	 * @return response
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/calcularTarifas")
	public Response calcularTarifas(ParametrosTarifasDTO parametrosTarifasDTO) {
		Response response = null;
		try {
			List<HonorariosFijadosDTO> honorariosFijadosDTO = honorariosFijadosFacade
					.calcularTarifas(parametrosTarifasDTO);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<HonorariosFijadosDTO>>(honorariosFijadosDTO) {
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
	 * registra los honorarios para las partes
	 * 
	 * @return response
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/guardarTarifas")
	public Response guardarCalculoTarifas(HonorariosFijadosDTO honorariosFijadosDTO) {
		Response response = null;
		try {
			honorariosFijadosFacade.guardarCalculoTarifas(honorariosFijadosDTO);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
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
