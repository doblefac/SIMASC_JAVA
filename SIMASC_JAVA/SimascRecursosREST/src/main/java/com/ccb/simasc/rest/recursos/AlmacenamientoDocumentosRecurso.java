package com.ccb.simasc.rest.recursos;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.ObjetoCifradoDTO;
import com.ccb.simasc.transversal.dto.ParametrosCargueDocumentoDTO;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

/**
 * 
 * 
 * @author jcepeda
 *
 */
@Path("almacenamientoDocumentosRecurso")
@Stateless
public class AlmacenamientoDocumentosRecurso {

	/**
	 * REST resource logging
	 */
	private static final Logger logger = LogManager.getLogger(AlmacenamientoDocumentosRecurso.class);
	private static final Class<AlmacenamientoDocumentosRecurso> enClass = AlmacenamientoDocumentosRecurso.class;

	@Context
	private HttpHeaders httpHeaders;

	@EJB
	private ISeguridadFacade seguridadFacade;

	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private IDocumentoFacade documentoFacade;

	/**
	 * 
	 * 
	 * @param idCasoOSolicitud
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/guardarDocumento")
	public Response guardarDocumento(InputStream documento){
		
		Response response = null;

		try {
			// 1. Obtiene el contexto de sesión del encabezado de la petición
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			// 2. Extrae la metadata del documento del encabezado de la
			// petición encriptada
			String metadataCifradaDocumento = httpHeaders.getRequestHeaders()
					.getFirst(UtilConstantes.HEADER_DOCUMENT_KEY);
			ObjetoCifradoDTO objetoCifrado = (ObjetoCifradoDTO) ContextoDeSesion
					.mapearValorEncabezado(metadataCifradaDocumento, ObjetoCifradoDTO.class);

			// *  Extrae el DTO del encabezado
			String metadataCifradaParametros = httpHeaders.getRequestHeaders()
					.getFirst("parametroCargueDocumento");
			ParametrosCargueDocumentoDTO parametrosCargueDocumento= (ParametrosCargueDocumentoDTO) ContextoDeSesion
					.mapearValorEncabezado(metadataCifradaParametros, ParametrosCargueDocumentoDTO.class);		
			
			// 3. Descifra la metadata del documento
			String metadataDescifradaDocumento = seguridadFacade.descifrarEncabezadoPeticion(objetoCifrado);

			// 4. Mapea la metadata descifrada del documento a un objeto
			Documento documentoEntity = (Documento) ContextoDeSesion.mapearValorEncabezado(metadataDescifradaDocumento,
					Documento.class);

			// 5. Intenta guardar física y lógicamente (gestor documental y base
			// de datos) el documento
			List<Long> idsDocumentos;
			if (documentoEntity.getIdSolicitudServicio() != null ) {
				idsDocumentos = almacenamientoDocumentosFacade.guardarDocumentoSolicitudServicio(parametrosCargueDocumento, //idCaso
						 documentoEntity, sesion.getNombreUsuario(),documento);
			} else if(parametrosCargueDocumento.getIdCasoOSolicitud()!=null || parametrosCargueDocumento.getIdPersona()!=null)  {
				idsDocumentos = almacenamientoDocumentosFacade.guardarDocumento(parametrosCargueDocumento.getIdCasoOSolicitud(), parametrosCargueDocumento.getNombreDocumento(),
						parametrosCargueDocumento.getExtension(), documento, documentoEntity, sesion.getNombreUsuario(), parametrosCargueDocumento.getIdPersona(), sesion); //idCaso, idPersona
			}else{
				idsDocumentos = almacenamientoDocumentosFacade.guardarDocumentoOtros(parametrosCargueDocumento, documentoEntity, sesion.getNombreUsuario(), documento);
			}
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Long>>(idsDocumentos) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param extension
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/actualizarDocumento/{idCaso}/{nombreDocumento}/{extension}/{idPersona}")
	public Response actualizarDocumento(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreDocumento") String nombreDocumento, @PathParam("extension") String extension,
			@PathParam("idPersona") String idPersona, InputStream documento) {
		Response response = null;
		Long idPersonaDesarrollo = null;
		if (!idPersona.equals(UtilConstantes.VALOR_UNDEFINED))
			idPersonaDesarrollo = Long.parseLong(idPersona);
		try {
			// 1. Obtiene el contexto de sesión del encabezado de la petición
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			// 2. Extrae la metadata del documento del encabezado de la
			// petición encriptada
			String metadataCifradaDocumento = httpHeaders.getRequestHeaders()
					.getFirst(UtilConstantes.HEADER_DOCUMENT_KEY);
			ObjetoCifradoDTO objetoCifrado = (ObjetoCifradoDTO) ContextoDeSesion
					.mapearValorEncabezado(metadataCifradaDocumento, ObjetoCifradoDTO.class);

			// 3. Descifra la metadata del documento
			String metadataDescifradaDocumento = seguridadFacade.descifrarEncabezadoPeticion(objetoCifrado);

			// 4. Mapea la metadata descifrada del documento a un objeto
			Documento documentoEntity = (Documento) ContextoDeSesion.mapearValorEncabezado(metadataDescifradaDocumento,
					Documento.class);

			// 5. Intenta guardar física y lógicamente (gestor documental y base
			// de datos) el documento
			List<Long> idsDocumentos = almacenamientoDocumentosFacade.actualizarDocumento(idCaso, nombreDocumento,
					extension, documento, documentoEntity, sesion.getIdUsuario(), idPersonaDesarrollo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Long>>(idsDocumentos) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}

	/**
	 * 
	 * 
	 * @param idDocumento
	 * @return
	 */
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarDocumento/{idDocumento}")
	public Response eliminarDocumento(@PathParam("idDocumento") Long idDocumento) {
		Response response = null;

		try {
			// 1. Obtiene el contexto de sesión del encabezado de la petición
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			// 2. Elimina el documento
			almacenamientoDocumentosFacade.eliminarDocumento(idDocumento, sesion.getIdUsuario());

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	/**
	 * 
	 * 
	 * @param idDocumento
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/validarDocumentoExiste/{idCaso}/{tipoDocumento}")
	public Response validarDocumentoExiste(@PathParam("idCaso") Long idCaso,
			@PathParam("tipoDocumento") String tipoDocumento) {
		Response response = null;

		try {
			boolean existe = almacenamientoDocumentosFacade.validarDocumentoExiste(idCaso, tipoDocumento);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Boolean>(existe) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/obtenerToken/")
	public Response obtenerToken() {
		Response response = null;
		try {
			String token = almacenamientoDocumentosFacade.getClientCredentials();
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(token) {}).build();
		} catch (UnsupportedEncodingException e) {
			logger.error("Error: ", e);
		}
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarListaDocumentos/")
	public Response eliminarListaDocumentos(ArrayList<Long> idDocumentos) {
		Response response = null;

		try {
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);

			almacenamientoDocumentosFacade.eliminarListadoDocumentos(idDocumentos, sesion.getIdUsuario());

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			logger.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}

		return response;
	}
}
