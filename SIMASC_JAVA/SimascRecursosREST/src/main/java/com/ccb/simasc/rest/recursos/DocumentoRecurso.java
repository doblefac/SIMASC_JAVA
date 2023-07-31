package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
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

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.comun.util.Constantes;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.rest.recursos.configuracion.ProveedorGeneralREST;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.DatosVerificacionParteFirmaDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ObjetoCifradoDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.formularios.BuscadorSemanticoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaSemanticaDTO;
import com.ccb.simasc.transversal.dto.formularios.DocumentosDigitalizadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Documento
 * @author sMartinez
 */
@Path( "documento" )
@Stateless
public class DocumentoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(DocumentoRecurso.class);
    private static final Class<Documento> enClass= Documento.class;
    // Variables para consumo de datasourse
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet rs;
   
   	// protected region atributos on begin
	// (03-02-2020 AFG) Creacion WS consultarArchivoOnbase
	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICasoFacade casoFacade;
	
	@EJB
	private IUsuarioFacade usuarioFacade;
	
	@EJB
	private ISeguridadFacade seguridadFacade;
	
	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
	
	@Context
	private HttpServletRequest httpServletRequest;
    
   // protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentos/{casoId}")
	public Response obtenerDocumentosPorCaso(@PathParam("casoId") Long casoId) {
		Response response = null;
		try {
			List<Documento> documentos = documentoFacade.consultarPorCaso(casoId);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Documento>>(documentos) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentos/{casoId}/{tipoServicio}")
	public Response obtenerDocumentosPorCasoTipoServicio(@PathParam("casoId") Long casoId,
			@PathParam("tipoServicio") String tipoServicio) {
		Response response = null;
		try {
			List<Documento> documentos = documentoFacade.consultarPorCasoTipoServicio(casoId, tipoServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Documento>>(documentos) {
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarDocumento")
	public Response eliminarDocumento(Documento documento) {
		Response response = null;
		try {
			documentoFacade.eliminarPorId(documento.getIdDocumento());

			response = Response.status(Response.Status.OK)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
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
	 * Guarda el documento en la tabla de documento
	 * 
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarDocumento")
	public Response guardarDocumento(List<Documento> documento) {
		Response response = null;
		try {
			List<Long> ids= documentoFacade.guardarDocumento(documento);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Long>>(ids){
						
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	 * Consulta un documento por el nombre del documento, por el codigo del caso
	 * y por el tipo de servicio
	 * 
	 * @param idCaso
	 * @param nombreDocumento
	 * @param tipoServicio
	 * @return Documento
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarPorNombreServicioCaso")
	public Response consultarPorNombreServicioCaso(Documento documento) {
		Response response = null;
		try {
			DocumentoDTO documentoDTO = documentoFacade.consultarPorNombreServicioCaso(documento);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<DocumentoDTO>(documentoDTO) {
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
	 * Guarda el documento en la tabla de documento
	 * 
	 * @param documento
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/digitalizarDocumento")
	public Response digitalizarDocumento(Documento documento) {
		Response response = null;
		try {
			documentoFacade.digitalizarDocumento(documento);

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	@Path("/digitalizarDocumento/{idCaso}/{nombreDocumento}/{extension}")
	public Response digitalizarDocumento(@PathParam("idCaso") Long idCaso,
			@PathParam("nombreDocumento") String nombreDocumento, @PathParam("extension") String extension,
			InputStream documento) {
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

			// 3. Descifra la metadata del documento
			String metadataDescifradaDocumento = seguridadFacade.descifrarEncabezadoPeticion(objetoCifrado);

			// 4. Mapea la metadata descifrada del documento a un objeto
			Documento documentoEntity = (Documento) ContextoDeSesion.mapearValorEncabezado(metadataDescifradaDocumento,
					Documento.class);

			// 5. Intenta guardar física y actualizar (gestor documental y base
			// de datos) el documento
			documentoFacade.digitalizarDocumento(idCaso, nombreDocumento, extension, documento, documentoEntity,
					sesion.getNombreUsuario());

			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/publicarDocumento/{idCaso}/{publicacion}")
	public Response publicarDocumento(Documento documento, @PathParam("idCaso") Long idCaso, @PathParam("publicacion") boolean publicacion) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			String idPersonaModificacion = cs.getIdUsuario();
			documentoFacade.publicarReversarDocumento(documento, idCaso, publicacion, idPersonaModificacion);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerActaAudiencia/")
	public Response obtenerActaAudiencia(DocumentoDTO documento) {
		Response response = null;
		try {
			documentoFacade.obtenerActaAudiencia(documento);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<DocumentoDTO>(documento) {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/obtenerAudiosAudiencia/")
	public Response obtenerAudiosAudiencia(DocumentoDTO dto) {
		Response response = null;
		try {
			List<DocumentoDTO> audios = documentoFacade.obtenerAudiosAudiencia(dto);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DocumentoDTO>>(audios) {})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarAudio")
	public Response guardarAudio(DocumentoDTO dto) {
		Response response = null;
		try {
			Long ids= documentoFacade.guardarAudio(dto);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Long>(ids){				
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarActa/{idCaso}/{idAudiencia}/{idPersona}")
	public Response guardarActa(List<Documento> documento, @PathParam("idCaso") Long idCaso, @PathParam("idAudiencia") Long idAudiencia, @PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			List<Long> ids= documentoFacade.guardaActa(documento, idCaso, idAudiencia, idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Long>>(ids){
						
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarRemitentes/{idCaso}/")
	public Response consultarRemitentes(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<Persona> remitentes= documentoFacade.consultarRemitentes(idCaso);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Persona>>(remitentes){
						
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/consultarDocumentosSecretaria")
	public Response consultarDocumentosSecretaria(ParametrosCarpetaDTO carpetaInfo) {
		Response response = null;
		try {
			List<Documento> documentosSecretaria= documentoFacade.consultarDocumentosSecretaria(carpetaInfo);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Documento>>(documentosSecretaria){
						
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}

		return response;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentosBasicosPorCasoTipo/{casoId}/{tipo}")
	public Response obtenerDocumentosBasicosPorCasoTipo(@PathParam("casoId") Long casoId, @PathParam("tipo") String tipo) {
		Response response = null;
		List<DocumentoInfoBasicaDTO> documentosEnvio = new ArrayList<DocumentoInfoBasicaDTO>();
		try {
			List<Documento> documentos = documentoFacade.consultarPorCasoTipo(casoId,tipo);
			for (Documento documento : documentos) {
				DocumentoInfoBasicaDTO documentoDto = new DocumentoInfoBasicaDTO();
				documentoDto.setIdDocumento(documento.getIdDocumento());
				documentoDto.setNombre(documento.getNombre());
				documentoDto.setTipoDocumento(documento.getTipoDocumento());
				documentoDto.setDescripcion(documento.getDescripcion());
				documentoDto.setUrl(documento.getUrl());
				documentoDto.setFechaUltimaModificacion(documento.getFechaUltimaModificacion());
				documentoDto.setEstadoRegistro(documento.getEstadoRegistro());
				documentoDto.setIdCaso(documento.getIdCaso());
				documentoDto.setFechaRadicacion(documento.getFechaRadicacion());
				try{
					Usuario user =   usuarioFacade.buscar(documento.getIdUsuarioModificacion()); 	
					user  = new UsuarioDTO().transformarEntidadConDependencias(user);					
					documentoDto.setNombreUsuarioModificacion(user.getPersona().getNombreCompleto());	
				}catch(Exception e){
					documentoDto.setNombreUsuarioModificacion("");
				}
				
				documentosEnvio.add(documentoDto);
				
			}
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DocumentoInfoBasicaDTO>>(documentosEnvio) {
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
	//DocumentoBasicoDTO
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/registroDocumentoDemanda")
	public Response registroDocumentoDemanda(Documento documento) {
		Response response = null;
		try {
			ContextoDeSesion sesion = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			
			documentoFacade.registroDocumentoDemanda(documento, sesion.getNombreUsuario());
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<String>("ok"){					
					})
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER)
					.build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentoCaso/{idCaso}/{idDocumento}")
	public Response obtenerDocumentoCaso(@PathParam("idCaso") Long idCaso, @PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		try {

			DocumentoDTO documentoDTO = documentoFacade.obtenerDocumentoCaso(idCaso, idDocumento);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<DocumentoDTO>(documentoDTO) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/registrarDocumentoPartes/{idCaso}/{idDocumento}")
	public Response registrarDocumentoPartes(@PathParam("idCaso") Long idCaso, @PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		try {
			documentoFacade.registrarDocumentoPartes(idCaso,idDocumento);
			response = Response.status(Response.Status.OK).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS,
					UtilConstantes.X_EXTRA_HEADER).build();
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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/modificacionDemandaParte/{idCaso}/{idDocumento}/{idPersona}")
	public Response modificacionDemandaParte(@PathParam("idCaso") Long idCaso , @PathParam("idDocumento")Long idDocumento
			, @PathParam("idPersona")Long idPersona) {
		Response response = null;
		try {			
			documentoFacade.modificacionDemandaParte(idCaso, idDocumento , idPersona);
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<String>("ok"){	
					}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	/**
	 * Servicio que obtiene los documentos activos asociados a una solicitud de
	 * servicio
	 * 
	 * @param solicitudId
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentosSolicitud/{idSolicitudServicio}")
	public Response obtenerDocumentosPorSolicitud(@PathParam("idSolicitudServicio") Long idSolicitudServicio) {
		Response response = null;
		try {
			List<Documento> documentos = documentoFacade.consultarPorSolicitudServicio(idSolicitudServicio);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Documento>>(documentos) {
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

	@SuppressWarnings("unchecked")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/documentosPorIdCasoSinDependencias/{casoId}")
	public Response documentosPorIdCasoSinDependencias(@PathParam("casoId") Long casoId) {
		Response response = null;
		try {
			List<Documento> documentos = documentoFacade.consultarPorCaso(casoId);
			documentos = (List<Documento>) new DocumentoDTO().transformarColeccionEntidadesConDependencias(documentos);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Documento>>(documentos) {
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
	 * ARB-F-037
	 * Actualiza la informacion en bd de los documentos
	 * @param documento
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/actualizarInfoDocumento/")
	public Response actualizarInfoDocumento(DocumentoDTO documento) {
		Response response = null;
		try {
			documentoFacade.actualizarInfoDocumento(documento);
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
	 * AMD-F-032
	 * Obtiene el ultimo documento de una persona por tipo
	 * @param persona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerIdDocumentoPersonaPorTipo/{idPersona}/{tipo}")
	public Response obtenerDocumentoPersonaPorTipo(@PathParam("idPersona") Long idPersona, @PathParam("tipo") String tipo) {

		Response response = null;
		try {

			DocumentoDTO documentoDTO = documentoFacade.obtenerDocumentoPersonaPorTipo(idPersona, tipo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<DocumentoDTO>(documentoDTO) {
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
	 * Consulta los documentos que se encuentran en estado por digitalizar con los filtros que
	 * se pasan al servicio.
	 * 
	 * @param filtros
	 *            Filtros seleccionados por el usuario
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/documentospordigitalizar/")
	public Response consultarDocumentosPorDigitalizar(FiltroCasosAsignadosDTO filtros) {

		Response response = null;
		try {
			List<DocumentosDigitalizadorDTO> documentos = documentoFacade.consultarDocumentosPorDigitalizar(filtros);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<DocumentosDigitalizadorDTO>>(documentos) {
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
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDocumentos/{casoId}/{tipoDocumento}/{nombreDocumento}")
	public Response consultarDocumentos(@PathParam("casoId") Long idCaso,
			@PathParam("tipoDocumento") String tipoDocumento, @PathParam("nombreDocumento") String nombreDocumento ) {
		Response response = null;
		String nombreDoc = nombreDocumento;
		if(nombreDocumento.equals("") || nombreDocumento.equals(UtilConstantes.VALOR_UNDEFINED)){
			nombreDoc=null;
		}
		try {
			List<DocumentoDTO> documentos = documentoFacade.consultarDocumentos(idCaso, nombreDoc, tipoDocumento);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DocumentoDTO>>(documentos) {
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
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarUrlObase/{idDocumento}/{idCaso}")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Response consultarArchivoOnbase(@PathParam("idDocumento") Long idDocumento, 
			@PathParam("idCaso") Long idCaso) {
		String  idItem    = null;
		String url = null;
		Response response = null;
		try {
			if(idDocumento != null & idCaso != null) {
				DocumentoDTO documento = documentoFacade.obtenerDocumentoCaso(idCaso, idDocumento);
				
				if(documento != null) {
					ParametrosGenerales urlWsOnBase = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URLWSONBASE);
					String urlOnBase = urlWsOnBase.getValorTexto();
					
					ProveedorGeneralREST proveedorRest = new ProveedorGeneralREST();
					idItem = proveedorRest.obtenerItemOnBase(idCaso, idDocumento, documento.getNombre(), documento.getFechaRadicacion().toString(), urlOnBase);
					
					if(idItem != null){
						
						ParametrosGenerales parametros = parametrosGeneralesFacade.buscar(Constantes.CODIGO_PARAMETRO);
						
						if(parametros != null) {
							url = idItem.equals("") ? parametros.getValorTexto()+idDocumento : parametros.getValorTexto()+idItem;
							
							//Actualizar documento
							documento.setUrl(url);
							documento.setEstado(Constantes.ESTADO_DOC_ONBASE);
							documentoFacade.actualizarInfoDocumento(documento);

							//Respuesta Exitosa WS 200
							response = Response.status(Response.Status.OK)
									.entity(new GenericEntity<String>(url){	
									}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
						}else {
							//Error Parametros Generales							
							LOG.error(Constantes.ERROR_02);						
						}
					}else{
						LOG.error(Constantes.ERROR_02);
					}					
				}else {
					// Error Documento
					LOG.error(Constantes.ERROR_03);
				}
			}else {
				// Respuesta 204
				response = Response.status(Response.Status.NO_CONTENT)
						.entity(new GenericEntity<String>("NO"){	
						}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();				
			}		
		} catch (Exception e) {
			LOG.error("Se presento error en WS consultarArchivoOnbase, Error: "+e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		//salida
		return response;		
	}
	
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/verificarParteFirma")
	public Response verificarParteFirma(DatosVerificacionParteFirmaDTO datosVerificacionParteFirmaDTO) {
		Response response = null;
		String sessionId = null;
		try {

			if (httpServletRequest != null) {
				sessionId = httpServletRequest.getSession().getId();
			}

			documentoFacade.verificarParteFirma(datosVerificacionParteFirmaDTO, sessionId);

			response = Response.ok(sessionId)
					.header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerDocumentosPorCarpeta/{idCarpeta}")
	public Response obtenerDocumentosPorCarpeta(@PathParam("idCarpeta") Long idCarpeta) {
		Response response = null;
		try {
			List<DocumentoDTO> documentos = documentoFacade.consultarDocumentosPorIdCarpeta(idCarpeta);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<DocumentoDTO>>(documentos) {
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
	// protected region metodos adicionales end
		class TrustAnyTrustManager implements X509TrustManager {
			@Override
		    public void checkClientTrusted(X509Certificate[] chain, String authType)
		            throws CertificateException {
		    }
			@Override
		    public void checkServerTrusted(X509Certificate[] chain, String authType)
		            throws CertificateException {
		    }
			@Override
		    public X509Certificate[] getAcceptedIssuers() {
		        return new X509Certificate[] {};
		    }
		}

		@POST
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		@Path("/busqueda")
		public Response busquedaSemantica(BuscadorSemanticoDTO parametros) {
			Response response = null;
			try {
				List<BusquedaSemanticaDTO> resultados = documentoFacade.consultarBusqueda(parametros.getIdCaso(), parametros.getKeyword(), parametros.getTipoDocumento(), parametros.getFechaInicial(), parametros.getFechaFinal());
				response = Response.status(Response.Status.OK).entity(new GenericEntity<List<BusquedaSemanticaDTO>>(resultados) {
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
		
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		@Path("/generarIndiceElectronico/{idCaso}/{firma}")
		public Response generarIndiceElectronico(@PathParam("idCaso") Long idCaso, @PathParam("firma") Boolean firma ) {
			Response response = null;
			try {
				documentoFacade.generarIndiceElectronico(idCaso, firma);
				response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("OK") {
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
