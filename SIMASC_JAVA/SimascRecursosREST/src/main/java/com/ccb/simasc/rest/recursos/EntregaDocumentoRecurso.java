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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEntregaDocumentoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DocumentosEntregadosDTO;
import com.ccb.simasc.transversal.entidades.EntregaDocumento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST EntregaDocumento
 * @author sMartinez
 */
@Path( "entregadocumento" )
@Stateless
public class EntregaDocumentoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(EntregaDocumentoRecurso.class);
    private static final Class<EntregaDocumento> enClass= EntregaDocumento.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private IEntregaDocumentoFacade entregaDocumentoFacade; 
        
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones
	
	/** CON-F-85
	 * servicio que expone la consulta de la entrega de un documento a las partes de un caso
	 * @param idCaso codigo del caso
	 * @return lista de entrega de documentos con dependencias
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDocumentosEntregadosPartesCaso/{idCaso}")
	public Response consultarDocumentosEntregadosPartesCaso(@PathParam("idCaso") Long idCaso ) {
		Response response = null;

		try {
			List<DocumentosEntregadosDTO> documentosEntregados = entregaDocumentoFacade.consultarDocumentosEntregadosPartesCaso(idCaso);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DocumentosEntregadosDTO>>(documentosEntregados) {
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
	 * CON-F-085
	 * Servicio que crea los registro de entrega de los documentos a las partes
	 * @return 
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/entregarDocumentos/{idCaso}/")
	public Response entregarDocumentos(@PathParam("idCaso") Long idCaso, List<DocumentosEntregadosDTO> documentosAEntregar ) {
		Response response = null;
		
		try {
			entregaDocumentoFacade.entregarDocumentos( documentosAEntregar, idCaso );
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
