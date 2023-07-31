package com.ccb.simasc.rest.recursos;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;


@Path("gestorDocumental")
@Stateless
public class GestorDocumentalRecurso {

	/**
	 * REST resource logging
	 */
	private static final Logger LOG = LogManager.getLogger(GestorDocumentalRecurso.class);
	private static final Class<GestorDocumentalRecurso> enClass = GestorDocumentalRecurso.class;
	
	/**
	 * REST resource headers parameters
	 */
	private static final String ACCESS_CONTROL_ALLOW_HEADERS = UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS;
	private static final String X_EXTRA_HEADER = UtilConstantes.X_EXTRA_HEADER;


	@EJB
	IGestorDocumentalFacade gestorDocumentalFacade;

	/**
	 * Registra un documento en el sistema de archivos según el caso
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/subirDocumento/{idCaso}/{idDocumento}/{nombreDocumento}/{extension}")
	public Response subirDocumentoCaso(@PathParam("idCaso") Long idCaso, @PathParam("idDocumento") Long idDocumento,
			@PathParam("nombreDocumento") String nombreDocumento, @PathParam("extension") String extension,
			InputStream documento) {
		Response response = null;

		try {
			if (documento != null) {
				// String pathDocumento = gestorDocumentalFacade.subirDocumento(idCaso, idDocumento, documento,
				//		nombreDocumento, extension);
				response = Response.status(Response.Status.OK)
						// .entity(new GenericEntity<String>(pathDocumento) {})
						.header(ACCESS_CONTROL_ALLOW_HEADERS, X_EXTRA_HEADER).build();
			}
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
	 * 
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 * @return
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/actualizarDocumento/{idCaso}/{idDocumento}/{nombreDocumento}/{extension}")
	public Response actualizarDocumentoCaso(@PathParam("idCaso") Long idCaso,
			@PathParam("idDocumento") Long idDocumento, @PathParam("nombreDocumento") String nombreDocumento,
			@PathParam("extension") String extension, InputStream documento) {
		Response response = null;

		try {
			if (documento != null) {
				// gestorDocumentalFacade.actualizarDocumento(idCaso, idDocumento, nombreDocumento, extension, documento);
				response = Response.status(Response.Status.OK).header(ACCESS_CONTROL_ALLOW_HEADERS, X_EXTRA_HEADER)
						.build();
			}
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
	 * 
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/recuperarDocumento/{idCaso}/{idDocumento}")
	public Response recuperarDocumentoCaso(@PathParam("idCaso") String idCaso,
			@PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		Long idCasoBuscado = null;
		if(!idCaso.equals("null"))
			idCasoBuscado = Long.valueOf(idCaso);
		try {
			File documento = gestorDocumentalFacade.recuperarDocumento(idCasoBuscado, idDocumento);
			if (documento != null) {
				response = Response.ok((Object) documento)
						.header("Content-Disposition", "attachment; filename=" + documento.getName().replace(",", "")).build();
			}
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
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @return
	 */
	@GET
	@Path("/recuperarDocumentoGestorDocumental/{idCaso}/{idDocumento}")
	public Response recuperarDocumentoCasoGestorDocumental(@PathParam("idCaso") String idCaso,
			@PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		Long idCasoBuscado = null;
		if(!idCaso.equals("null"))
			idCasoBuscado = Long.valueOf(idCaso);
		try {
			Object documento = gestorDocumentalFacade.recuperarDocumentoGestorDocumental(idCasoBuscado, idDocumento);
			if (documento != null && documento instanceof File) {
				response = Response.ok((Object) documento)
						.header("Content-Disposition", "attachment; filename=" + ((File) documento).getName().replace(",", "")).build();
			} else if (documento != null && documento instanceof String) {
				response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(((String) documento)) {
				}).header("Access-Control-Allow-Headers", "X-extra-header").build();
			}
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
	 * 
	 * 
	 * @param idCaso
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/crearCarpetasCaso/{idCaso}")
	public Response crearCarpetasCaso(@PathParam("idCaso") Long idCaso, List<String> nombresCarpetas) {
		Response response = null;

		try {
			// Map<String, String> pathsCarpetasCaso = gestorDocumentalFacade.crearCarpetasCaso(nombresCarpetas, idCaso);

			response = Response.status(Response.Status.OK)
					// .entity(new GenericEntity<Map<String, String>>(pathsCarpetasCaso) {})
					.header(ACCESS_CONTROL_ALLOW_HEADERS, X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	/**
	 * Método encargado de eliminar un documento fisico dada su URL
	 * 
	 * @param pathDocumento
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarDocumento")
	public Response eliminarDocumento(String pathDocumento) {
		Response response = null;

		try {
			boolean resultado = gestorDocumentalFacade.eliminarDocumento(pathDocumento);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(resultado) {
			}).header(ACCESS_CONTROL_ALLOW_HEADERS, X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	/**
	 * Servicio encargado de obtener el documento especificado por el nombre en
	 * el Gestor Documental con sub carpeta Transversales
	 * 
	 * @param nombreDocumento
	 * @return File
	 */
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/recuperarDocumentoTransversal/{nombreDocumento}/")
	public Response recuperarDocumentoTransversal(@PathParam("nombreDocumento") String nombreDocumento) {
		Response response = null;
		try {
			File documento = gestorDocumentalFacade.recuperarDocumentoTransversal(nombreDocumento);
			if (documento.exists()) {
				response = Response.ok((Object) documento)
						.header("Content-Disposition", "attachment; filename=" + documento.getName()).build();
			}
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
