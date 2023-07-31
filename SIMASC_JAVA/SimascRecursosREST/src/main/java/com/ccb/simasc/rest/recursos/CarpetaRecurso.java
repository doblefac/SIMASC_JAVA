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

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CarpetaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Carpeta
 * @author sMartinez
 */
@Path( "carpeta" )
@Stateless
public class CarpetaRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CarpetaRecurso.class);
    private static final Class<Carpeta> enClass= Carpeta.class;
   
   	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos del recurso
	
	@EJB
    private ICarpetaFacade carpetaFacade; 
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta seccion sus modificaciones

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCarpetaCuaderno/{casoId}")
	public Response obtenerCarpetaCuadernoPorCaso(@PathParam("casoId") Long casoId) {
		Response response = null;
		try {
			List<Carpeta> carpetas = carpetaFacade.obtenerCarpetaCuadernoPorCaso(casoId);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Carpeta>>(carpetas) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/guardarDocumento")
	public Response guardarInformacionDocumento(ParametrosCarpetaDTO carpetaInfo) {
		Response response = null;
		try {
			carpetaFacade.guardarInformacionDocumento(carpetaInfo);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/enviarAlExpediente/{idCarpeta}")
	public Response enviarAlExpediente(List<Documento> listDocumento, @PathParam("idCarpeta") Long idCarpeta) {
		Response response = null;
		try {
			carpetaFacade.enviarAlExpediente(listDocumento, idCarpeta);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	@Path("/obtenerDocumentoCaso/{casoId}/{codigoDoc}")
	public Response obtenerDocumentoCaso(@PathParam("casoId") Long casoId,@PathParam("codigoDoc") Long codigoDoc) {
		Response response = null;
		try {
			ParametrosCarpetaDTO carpetaInfo = carpetaFacade.obtenerDocumentoCaso(casoId,codigoDoc);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<ParametrosCarpetaDTO>(carpetaInfo) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
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
	@Path("/obtenerDocumentoCarpeta/{casoId}")
	public Response obtenerDocumentoCarpeta(@PathParam("casoId") Long casoId) {
		Response response = null;
		try {
			List<Documento> documentosList = carpetaFacade.obtenerDocumentoCarpeta(casoId);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Documento>>(documentosList) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/crearCarpeta")
	public Response crearCarpeta(CarpetaDTO carpetaDTO) {
		Response response = null;
		try {
			Carpeta carpeta = carpetaFacade.crearCarpeta(carpetaDTO.getIdCarpetaContenedora(), carpetaDTO.getNombre());

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Carpeta>(carpeta) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/cambiarDestinoDocumento/{idCarpetaActual}/{idCarpetaDestino}/{idDocumento}")
	public Response cambiarDestinoDocumento(@PathParam("idCarpetaActual") Long idCarpetaActual,
			@PathParam("idCarpetaDestino") Long idCarpetaDestino, @PathParam("idDocumento") Long idDocumento) {
		Response response = null;
		try {
			
			Documento documento = carpetaFacade.cambiarDestinoDocumento(idCarpetaActual,idCarpetaDestino,idDocumento);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<Documento>(documento) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarCarpeta/{idCarpeta}")
	public Response eliminarCarpeta(@PathParam("idCarpeta") Long idCarpeta) {
		Response response = null;
		try {
			
			carpetaFacade.eliminarCarpeta(idCarpeta);

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	   // protected region metodos adicionales end
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/obtenerCarpetasPorIdContenedor/{idContenedor}")
	public Response obtenerCarpetasPorIdContenedor(@PathParam("idContenedor") Long idContenedor) {
		Response response = null;
		try {
			List<Carpeta> carpetas = carpetaFacade.obtenerCarpetasPorIdContenedor(idContenedor);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Carpeta>>(carpetas) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarCarpeta")
	public Response ModificarCarpeta(CarpetaDTO carpetaDTO) {
		Response response = null;
		try {
			Carpeta carpeta = carpetaFacade.buscar(carpetaDTO.getIdCarpeta());
			
			if(carpeta != null && !carpeta.getEsCuaderno()) {
				carpeta.setNombre(carpetaDTO.getNombre());
				carpetaFacade.actualizar(carpeta);
				response = Response.status(Response.Status.OK).entity(new GenericEntity<Carpeta>(carpeta) {
				}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			}else {
				response = Response.status(Response.Status.BAD_REQUEST).entity(new GenericEntity<String>("ERROR") {
				}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
			}


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
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarUbicacionDocuemnto/{idCarpeta}")
	public Response modificarUbicacionDocuemnto(List<Long> documentos ,
			@PathParam("idCarpeta")  long idCarpeta) {
		Response response = null;
		try {
			
			if(!documentos.isEmpty()) {
				for(Long documento : documentos) {
					carpetaFacade.modificarUbicacionDocuemnto(documento, idCarpeta);
				}
			}

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("OK") {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/modificarUbicacionCarpeta/{idCarpeta}")
	public Response modificarUbicacionCarpeta(List<Long> carpetas ,
			@PathParam("idCarpeta")  long idCarpetaContenedora) {
		Response response = null;
		try {
			
			if(!carpetas.isEmpty()) {
				for(Long carpeta : carpetas) {
					carpetaFacade.modificarUbicacionCarpeta(carpeta, idCarpetaContenedora);
				}
			}

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("OK") {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
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
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/eliminarCarpetas")
	public Response eliminarCarpetas(List<Long> idCarpetas) {
		Response response = null;
		try {
			
			for(Long carpeta : idCarpetas) {
				carpetaFacade.eliminarCarpeta(carpeta);
			}

			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER)
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
	@Path("/obtenerCantidadCarpetasYDocumentos/{idContenedor}")
	public Response obtenerCantidadCarpetasYDocumentosPorIdContenedor(@PathParam("idContenedor") Long idContenedor) {
		Response response = null;
		try {
			String carpetas = carpetaFacade.obtenerCantidadCarpetasIDocumentos(idContenedor);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(carpetas) {
			}).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCRecursosRESTExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			SIMASCRecursosRESTExcepcion.transformTopException(e);
			throw e;
		}
		return response;
	}

}
