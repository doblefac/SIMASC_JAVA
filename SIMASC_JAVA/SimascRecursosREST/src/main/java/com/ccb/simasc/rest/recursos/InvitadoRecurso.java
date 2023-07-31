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
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IInvitadoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.InvitadoDTO;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Invitado
 * @author sMartinez
 */
@Path( "invitado" )
@Stateless
public class InvitadoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(InvitadoRecurso.class);
    private static final Class<Invitado> enClass= Invitado.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso

	@EJB
	private IInvitadoFacade invitadoFacade;

	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarInvitados/{idAudiencia}")
	public Response consultarInvitados(@PathParam("idAudiencia") Long idAudiencia) {
		Response response = null;

		try {
			InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO, Invitado.ENTIDAD_INVITADO_ID_AUDIENCIA,
					idAudiencia);
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(f);
			List<InvitadoDTO> invitados = (List<InvitadoDTO>) invitadoFacade.obtenerPorFiltro(filtros,
					new ArrayList<InformacionOrdenamiento>(), null, new ArrayList<InvitadoDTO>(), false);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<InvitadoDTO>>(invitados) {
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

	// protected region metodos adicionales end


}
