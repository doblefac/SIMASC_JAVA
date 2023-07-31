package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
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

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorInfraestructuraSala;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInfraestructuraFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.InfraestructuraDTO;
import com.ccb.simasc.transversal.entidades.Infraestructura;
import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Infraestructura
 * @author sMartinez
 */
@Path( "infraestructura" )
@Stateless
public class InfraestructuraRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(InfraestructuraRecurso.class);
    private static final Class<Infraestructura> enClass= Infraestructura.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IInfraestructuraFacade infraestructuraFacade;
	
	@EJB
	private ManejadorInfraestructuraSala infraestructuraSalaManejador;
	

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	/**
	 * Consulta todas las logisticas
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInfraestructuras")
	public Response consultarInfraestructuras() {
		Response response = null;
		try {
			// si no cumple con la validaciones se genera SIMASCNegocioExcepcion
			List<InfraestructuraDTO> infraestructuras = (List<InfraestructuraDTO>) infraestructuraFacade
					.obtenerTodos(new ArrayList<InfraestructuraDTO>(), false);

			response = Response.ok(Response.Status.OK)
					.entity(new GenericEntity<List<InfraestructuraDTO>>(infraestructuras) {
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
	 * Consulta todas las logisticas
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInfraestructurasBySala/{idSala}")
	public Response consultarInfraestructurasBySala(@PathParam("idSala") Long idSala) {
		Response response = null;
		try {
			
			InformacionFiltro filtroInfraestructura = new InformacionFiltro(TipoFiltro.EXACTO,
    				InfraestructuraSala.ENTIDAD_INFRAESTRUCTURA_SALA_PK_ID_SALA,
    				idSala);
        	List<InformacionFiltro> filtrosInfraestructuraSala = new ArrayList<>();
        	filtrosInfraestructuraSala.add(filtroInfraestructura);
        	
        	List<InfraestructuraSala> InSala = infraestructuraSalaManejador.consultar(filtrosInfraestructuraSala,null, null);
        	
			response = Response.ok(Response.Status.OK)
					.entity(new GenericEntity<List<InfraestructuraSala>>(InSala) {
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
