package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.List;
import java.util.Map;

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
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.EnvioCorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.ResultadoAudiencia;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports recurso end

/**
 * Recurso REST CorreoElectronico
 * @author sMartinez
 */
@Path( "correoelectronico" )
@Stateless
public class CorreoElectronicoRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(CorreoElectronicoRecurso.class);
    private static final Class<CorreoElectronico> enClass= CorreoElectronico.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
    @EJB
    private IDocumentoFacade documentoFacade; 
    
    @EJB
    private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade; 
    
    @EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 
    
	@EJB
    private ICorreoElectronicoFacade correoElectronicoFacade; 
	
	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private IRepartoSvc repartoFacade;
	
	@EJB
	private ICasoFacade casoFacade;
	
	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	
	/**
	 * Obtiene todos las personas por el rol que se envia desde el front y el caso que esta asociado
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDestinatarios/{idCaso}")
	public Response consultarDestinatarios(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<Map<String, String>> dest = correoElectronicoFacade.consultarDestinatarios(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Map<String, String>>>(dest) {
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
	 * envia un correo electronico con los parametros que se le envie.
	 * 
	 * @param correo
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/enviarCorreoElectronico/")
	public Response enviarCorreoElectronico(EnvioCorreoElectronicoDTO correo) {
		Response response = null;
		try {
			correoRolPersonaCasoFacade.enviarCorreoElectronico(correo);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
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
	 * Obtiene todos las personas por el rol que se envia desde el front y el caso que esta asociado
	 * 
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/enviarCorreoElectronicoObligatorio/")
	public Response enviarCorreoElectronicoObligatorio(EnvioCorreoElectronicoDTO correo) {
		Response response = null;
		try {
			ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = correoRolPersonaCasoFacade
					.convertidorParametros(correo);
			boolean envioFallido = correoRolPersonaCasoFacade.enviarCorreo(parametrosEnvioCorreoDTO);
			
			// Lanzar Excepción si el envío de correo fue fallido
			 if(envioFallido) { throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR011.toString())); }
			
			if (parametrosEnvioCorreoDTO.getCartaPersona() != null) {
				cartaPersonaFacade.confirmacionNotificacionArbitro(parametrosEnvioCorreoDTO.getCartaPersona(), !envioFallido);
			}
			
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
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
	 * Servicio que actualiza el/los correo(s) de una persona
	 * 
	 * @param listCorreos
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/actualizarCorreoElectronico/")
	public Response actualizarCorreoElectronico(List<GenericoDTO> listCorreos) {
		Response response = null;
		try {
			ContextoDeSesion cs = ContextoDeSesion.obtenerContextoDeSesion(httpHeaders);
			correoElectronicoFacade.actualizarCorreosPersona(listCorreos, cs);
			String mensajeInfo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO001.toString());
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>(mensajeInfo) {
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
	 * Servicio que envia citaciones de audiencias por correos certicados
	 * @param EnvioCorreoElectronicoDTO correo
	 * @return List<CorreoElectronicoDTO>
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/enviarCitacionCorreoElectronico/")
	public Response enviarCitacionCorreoElectronico(EnvioCorreoElectronicoDTO correo) {
		Response response = null;
		try {
			
			List<CorreoElectronicoDTO> correosNotificados = correoRolPersonaCasoFacade.envioCitacionCorreo(correo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CorreoElectronicoDTO>>(correosNotificados) {
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
	 * Servicio que envia citaciones de audiencias por correos certicados
	 * @param EnvioCorreoElectronicoDTO correo
	 * @return List<CorreoElectronicoDTO>
	 */
	/*@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/enviarCorreoPartes/{idDocumento}")
	public Response enviarCorreoPartes(@PathParam("idDocumento") Long idDocumento, List<PersonaBasicaDTO> correo) {
		Response response = null;
		try {
			
			repartoFacade.envioCorreoPartes(idDocumento, correo);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
			}).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}*/
	
	/**
	 * Servicio que envia registro por cada uno de los correos de las partes del caos 
	 * @return List<PersonaBasicaDTO>
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarCorreosPartes/{idCaso}")
	public Response consultarCorreosPartes(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			
			List<PersonaBasicaDTO> correos = manejadorPersona.consultarCorreosElectronicosPorPersonaCaso(idCaso);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<PersonaBasicaDTO>>(correos) {
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
	@Path("/traerPrimerCorreoPrimario/{idPersona}")
	public Response traerPrimerCorreoPrimario(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			
			CorreoElectronico correo = correoElectronicoFacade.traerPrimerCorreoPrimario(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<CorreoElectronico>(correo){}
			).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	@Path("/consultarCorreosPersona/{idPersona}")
	public Response consultarCorreosPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {
			
			List<CorreoElectronicoDTO> correos = correoElectronicoFacade.consultarCorreosPersona(idPersona);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<CorreoElectronicoDTO>>(correos){}
			).header(UtilConstantes.ACCESS_CONTROL_ALLOW_HEADERS, UtilConstantes.X_EXTRA_HEADER).build();

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
	 * Servicio que envia resultado caso a las partes de equidad
	 * @param idCaso
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/enviarCorreoPartesEquidad/{idCaso}")
	public Response enviarCorreoPartesEquidad(@PathParam("idCaso") Long idCaso) {
		Response response = null;
		try {
			List<PersonaBasicaDTO> correos = manejadorPersona.consultarCorreosElectronicosPorPersonaCaso(idCaso);
			List<Documento> documentos = documentoFacade.consultarPorCasoTipo(idCaso,UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION);
			
			if(documentos== null || documentos.size() == 0 ) {
				documentos = documentoFacade.consultarPorCasoTipo(idCaso,UtilDominios.TIPO_DOCUMENTO_DIG_CONSTANCIA_RESULTADO_AUDIENCIA);
			}
			
			repartoFacade.envioCorreoPartes(documentos, correos, idCaso);
			casoFacade.cambiarEstadoCaso(idCaso, UtilDominios.ESTADO_CASO_CERRADO, null,
					UtilDominios.TIPO_EVENTO_CASO_CONTROL_CALIDAD, UtilConstantes.CAMBIO_ESTADO_CONTROL_CALIDAD_CERRADO);
			response = Response.status(Response.Status.OK).entity(new GenericEntity<String>("ok") {
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
