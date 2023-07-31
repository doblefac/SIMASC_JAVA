package com.ccb.simasc.rest.recursos;

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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoObligatorioFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DocumentoObligatorioDTO;
import com.ccb.simasc.transversal.entidades.DocumentoObligatorio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


@Path("documentoobligatorio")
@Stateless
public class DocumentoObligatorioRecurso {
	
	private static final Logger LOG = LogManager.getLogger(DocumentoObligatorioRecurso.class);
	private static final Class<DocumentoObligatorio> enClass = DocumentoObligatorio.class;

	
	@EJB
	private IDocumentoObligatorioFacade documentoObligatorioFacade;
	
	@Context
	private HttpHeaders httpHeaders;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocObligServicio/{idServicio}")
	public Response consultarDocumentoObligatorioPorServicio(@PathParam("idServicio") Long idServicio) {
		Response response = null;
		try {
			List<DocumentoObligatorioDTO> docOblig = documentoObligatorioFacade.consultarDocumentoObligatorioPorServicio(idServicio);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DocumentoObligatorioDTO>>(docOblig) {
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
