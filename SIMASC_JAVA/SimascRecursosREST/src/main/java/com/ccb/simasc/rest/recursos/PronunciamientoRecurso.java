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

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPronunciamientoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CambioEstadoSuplenteDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoCasoParaPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoRegistroPronunciamientoDTO;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Pronunciamiento
 * @author sMartinez
 */
@Path( "pronunciamiento" )
@Stateless
public class PronunciamientoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(PronunciamientoRecurso.class);
    private static final Class<Pronunciamiento> enClass= Pronunciamiento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IPronunciamientoFacade pronunciamientoFacade; 
	
	@EJB
    private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	/**
	 * ARB-F-052 Pronunciamiento del árbitro a la designación
	 * Consulta el caso al cual ha sido asignado el operador y que está pendiente de pronunciamiento. 
	 *  
	 * En caso que haya más de un caso asignado se consultan los casos en los que ha sido asignado 
	 * el operador que se pasa como parámetro y en caso de 
	 * que sea más de uno se devuelve el caso en el que se asigno de primeras (la fecha más vieja).
	 * 
	 * @return El código del caso pendiente de pronunciamieto. Si no se encuentra ningún caso se devuelve el número -1.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/casoPendienteDePronunciamiento/")
	public Response consultarCasoPendienteDePronunciamiento(){		
		
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);	
		Response response = null;

		try {
			Long idCasoAsignado = pronunciamientoFacade.consultarCasoPendienteDePronunciamiento(Long.valueOf(cs.getIdUsuario()));
			response = Response.status(Response.Status.OK).entity(new GenericEntity<Long>(idCasoAsignado) {
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
     * ARB-F-072 Crea o actualiza la información del pronunciamiento del  Arbitro
     * 
     * @param pronunciamiento
     * @param idPersona
     * @param idCaso
     * @param idUsuario
     * @return
     */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/guardarPronunciamiento/{idPersona}/{idCaso}/{idUsuario}")
	public Response guardarPronunciamiento(InfoRegistroPronunciamientoDTO infoPronunciamiento, @PathParam("idPersona") Long idPersona, 
			@PathParam("idCaso") Long idCaso, @PathParam("idUsuario") String idUsuario) {
		Response response = null;
		try {
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Long>(pronunciamientoFacade.guardarPronunciamiento(infoPronunciamiento, idPersona, idCaso)){})
							.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(e.getMessage(), enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			//Por manejo de transaccionalidad se elimina el documento asociado al pronunciamiento
			//Si el id de documento anterior es igual al id de documento del pronunciamiento es porque se 
			//estaba realizandso una actualización del documento y no se requiere borrarlo
			if(infoPronunciamiento!=null && infoPronunciamiento.getPronunciamiento()!=null){
				if(infoPronunciamiento.getPronunciamiento().getIdDocumento()!=null &&
							( infoPronunciamiento.getIdDocumentoAnterior()==null || 
									infoPronunciamiento.getIdDocumentoAnterior()!=infoPronunciamiento.getPronunciamiento().getIdDocumento())){ 					
					almacenamientoDocumentosFacade.eliminarDocumento(infoPronunciamiento.getPronunciamiento().getIdDocumento(), idUsuario);
				}
			}						
			LOG.error(e.getMessage());			
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
     * ARB-F-072 Consulta la información del pronunciamiento del árbitro
     * 
     * @param pronunciamiento
     * @param idPersona
     * @param idCaso
     * @return
     */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarPronunciamiento/{idPersona}/{idCaso}")
	public Response consultarPronunciamiento(@PathParam("idPersona") Long idPersona, 
			@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<InfoRegistroPronunciamientoDTO>(pronunciamientoFacade.consultarPronunciamiento(idPersona, idCaso)){})
							.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	

	/**
	 * ARB-F-052 Pronunciamiento del arbitro a la designación
	 * @param idCaso
	 * @param idPersona
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/casosAsignadosArbitro/{idCaso}/{idPersona}")
    public Response consultarInfoCasoAsignadoArbitro(@PathParam("idCaso") Long idCaso, @PathParam("idPersona") Long idPersona){    
		
    	Response response = null;
    	
    	try {
	    	response = Response.status(Response.Status.OK)
	                .entity(new GenericEntity<InfoCasoParaPronunciamientoDTO>(pronunciamientoFacade.consultarInfoCasoAsignadoArbitro(idCaso, idPersona)) {
	                 })
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
     * ARB-F-052 Pronunciamiento del arbitro a la designación
     * 
     * @param pronunciamiento
     * @param idPersona
     * @param idCaso
     * @param idUsuario 
     * @return
     */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/registrarPronunciamiento/{idPersona}/{idCaso}/{idUsuario}")
	public Response registrarPronunciamiento(Pronunciamiento pronunciamiento,
			@PathParam("idPersona") Long idPersona, @PathParam("idCaso") Long idCaso,
			@PathParam("idUsuario") String idUsuario) {
		Response response = null;
		try {
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Long>(pronunciamientoFacade.registrarPronunciamiento(false,pronunciamiento, idPersona, idCaso, idUsuario)){})
							.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			//Por manejo de transaccionalidad se elimina el documento asociado al pronunciamiento
			if(pronunciamiento.getIdDocumento()!=null){
				almacenamientoDocumentosFacade.eliminarDocumento(pronunciamiento.getIdDocumento(), idUsuario);
			}			
			LOG.error(e.getMessage());			
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}	
	

	/**
	 * Servicio que permite reversar un pronunciamiento de un arbitro principal que 
	 * declino o no se pronuncio
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/reversarPronunciamiento/")
	public Response reversarPronunciamiento(CambioEstadoSuplenteDTO cambioEstadoSuplenteDTO) {
		Response response = null;
		try {
			List<String> mensajes = pronunciamientoFacade.reversarPronunciamiento(cambioEstadoSuplenteDTO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<String>>(mensajes) {
							}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	
	/**
	 * Guarda el pronunciamiento de los conciliadores
	 * @param pendientePronunciamientoDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/pronunciamientoConciliador/")
	public Response pronunciamientoConciliador(List<PendientePronunciamientoDTO> casosPendientes) {
		Response response = null;
		ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);	
		try {
			pronunciamientoFacade.pronunciamientoConciliador(casosPendientes,cs.getNombreUsuario());
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
	
	
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/generarDocumento/{idPersona}/{idCaso}/{idPronunciamiento}")
	public Response generarDocumentoPronunciamiento(@PathParam("idPersona") Long idPersona, @PathParam("idCaso") Long idCaso,
			 @PathParam("idPronunciamiento") Long idPronunciamiento) {
		Response response = null;
	
		try {
			
			pronunciamientoFacade.generarDocumentoPronunciamiento(idPersona,idCaso, idPronunciamiento);
			
		    	response = Response.status(Response.Status.OK)
		                .entity(new GenericEntity<String>("OK") {
		                 })
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
