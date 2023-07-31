package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta sección sus modificaciones

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWFirmaDigital;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorCarpeta;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorNotificacionDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorTranscripcion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAgrupamientoRolFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICuadernoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoRadicadoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DatosVerificacionParteFirmaDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.DocumentoIndiceElectronicoDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.TipoDocumentoFoliadoDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaSemanticaDTO;
import com.ccb.simasc.transversal.dto.formularios.DocumentosDigitalizadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoCasoParaPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosGenerarCartaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.NotificacionDocumento;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.Pronunciamiento;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.Transcripcion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.simasc.clientes.ministerio.ArrayOfbase64Binary;

import co.org.ccb.simasc.comun.pdf.PlantillaIndicePDF;
import co.org.ccb.simasc.comun.pdf.RadicacionSolicitudPDF;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Documento}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class DocumentoFacade extends AccesoFacade<Documento, Long, DocumentoDTO> implements IDocumentoFacade {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private static final Logger LOGGER = LogManager.getLogger(ManejadorCrud.class.getName());

	private static final String NOMBRE_DOCUMENTO = "Respuesta ministerio";
	private static final String DESCRIPCION_DOCUMENTO = "Documento generado por servicio de ministerio";

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ManejadorTranscripcion manejadorTranscripcion;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreoElectronico;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private IAudienciaFacade audienciaFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;

	@EJB
	private IDominioFacade dominioFacade;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ICarpetaFacade carpetaFacade;

	@EJB
	private ManejadorNotificacionDocumento manejadorNotificacionDocumento;

	@EJB
	private INotificacionDocumentoFacade notificacionDocumentoFacade;

	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;

	@EJB
	private IAgrupamientoRolFacade agrupamientoRolFacade;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;

	@EJB
	private ManejadorCarpeta manejadorCarpeta;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private IClienteSWFirmaDigital clienteFirmaDigital;
	
	@EJB
	private ICuadernoFacade cuadernoFacade;
	
	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;
	
	@EJB
	private IAlertaFacade alertaFacade;
	
	@EJB
	private IDocumentoRadicadoFacade documentoRadicadoFacade;


	@Context
	private ServletContext contexto;

	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorDocumento;
	}

	@Override
	public DocumentoDTO transformarSinDependencias(Documento obj) {
		return new DocumentoDTO().transformarSinDependencias(obj);
	}

	@Override
	public DocumentoDTO transformarConDependencias(Documento obj) {
		return new DocumentoDTO().transformarConDependencias(obj);
	}

	@Override
	public Documento transformarEntidadConDependencias(Documento obj) {
		return new DocumentoDTO().transformarEntidadConDependencias(obj);
	}

	@Override
	public Documento transformarEntidadSinDependencias(Documento obj) {
		return new DocumentoDTO().transformarEntidadSinDependencias(obj);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	public List<Documento> consultarPorCaso(Long casoId) {
		try {
			return manejadorDocumento.consultarDocumentosActivos(casoId);
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#consultarPorCasoTipoServicio(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public List<Documento> consultarPorCasoTipoServicio(Long casoId, String tipoServicio) {
		return manejadorDocumento.consultarDocumentosActivos(casoId, tipoServicio);
	}

	public void eliminarDocumento(Long idDocumento, String idUsuario) {
		Documento documento = manejadorDocumento.buscar(idDocumento);
		if (documento != null && UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(documento.getEstadoRegistro())) {
			documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
			documento.setFechaUltimaModificacion(new Date());
			documento.setIdUsuarioModificacion(idUsuario);
			manejadorDocumento.actualizar(documento);
		}
	}

	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#
	 * actualizarCasoDocumentos(com.ccb.simasc.transversal.entidades.Documento)
	 */
	@Override
	public void crearEvento(Documento documento) {
		String nombreUsuario;
		if (appContext != null && appContext.getContextoSesion() != null)
			nombreUsuario = appContext.getContextoSesion().getNombreUsuario();
		else
			nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		Evento evento = new Evento();
		evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		evento.setFechaEvento(new Date());
		evento.setIdCaso(documento.getIdCaso());
		evento.setIdUsuarioModificacion(nombreUsuario);
		evento.setFechaUltimaModificacion(new Date());
		evento.setTipoEvento(UtilDominios.TIPO_EVENTO_RADICACION_DOCUMENTO);

		StringBuilder observaciones = new StringBuilder();
		observaciones.append(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO104.toString()));
		observaciones.append(documento.getNombre());

		if (documento.getDescripcion() != null) {
			observaciones.append(", Descripción: ");
			observaciones.append(documento.getDescripcion());
		}
		if (documento.getObservaciones() != null) {
			observaciones.append(", con ");
			observaciones.append((documento.getNumeroFolios() != null ? documento.getNumeroFolios() : 0)
					+ UtilConstantes.CARACTER_ESPACIO);
			observaciones.append(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO105.toString()));
			observaciones.append(documento.getObservaciones());
		}
		evento.setObservaciones(observaciones.toString());
		manejadorEvento.crear(evento);
	}

	@Override
	public List<Long> guardarDocumento(List<Documento> lstDocumento) {
		ArrayList<Long> idDocumentos = new ArrayList<Long>();
		Caso caso = null;
		Servicio servicio = null;

		for (Documento documento : lstDocumento) {
			if (documento.getIdCaso() != null) {
				caso = manejadorCaso.buscar(documento.getIdCaso());
				servicio = manejadorServicio.buscar(caso.getIdServicio());
			}

			documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
			documento.setFechaUltimaModificacion(new Date());
			documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (documento.getIdCaso() != null) {				
				documento.setFechaDigitalizacion(null);
				documento.setFechaAsignacion(new Date());
				documento.setFechaRadicacion(
				documento.getFechaRadicacion() == null ? new Date() : documento.getFechaRadicacion());				
			}
			if (documento.getDescripcion() == null) {
				documento.setDescripcion(documento.getNombre());
			}
			if (documento.getTipoArchivo() == null) {
				documento.setTipoArchivo("");
			}
			
			if (documento.getIdCaso() != null && documento.getIdCarpeta() == null) {
				Carpeta carpeta = null;
				if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equalsIgnoreCase(servicio.getTipo())
				|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equalsIgnoreCase(servicio.getTipo())) {
					if(documento.getTipoDocumento().equals(UtilDominios.TIPO_DOCUMENTO_DIG_CARTA)){
						carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(documento.getIdCaso(), UtilDominios.ID_CUADERNO_COMUNICACIONES_SISTEMA);
					}else{
						carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(documento.getIdCaso(), UtilDominios.ID_CUADERNO_PRINCIPAL);
						
					}
					if (carpeta != null) {
						documento.setIdCarpeta(carpeta.getIdCarpeta());
					}
				}				
			}
			manejadorDocumento.crear(documento);
			
			if (documento.getIdCaso() != null) {
				if (UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equalsIgnoreCase(servicio.getTipo())
						|| UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equalsIgnoreCase(servicio.getTipo())) {
					crearEvento(documento);
				}
				this.notificarRadicacionDocumento(documento,caso);				
			}
			
			idDocumentos.add(documento.getIdDocumento());
		}
		return idDocumentos;
	}

	@Override
	public void actualizarDocumentos(List<Documento> lstDocumento) {

		ArrayList<Long> idDocumentos = new ArrayList<Long>();
		for (Documento documento : lstDocumento) {
			if (documento.getIdDocumento() == null) {
				throw new SIMASCNegocioExcepcion(0,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR217.toString()));
			}
			if (documento.getIdCaso() != null) {
				Caso caso = manejadorCaso.buscar(documento.getIdCaso());
				if (caso.getIdCaso() != null) {
					documento.setFechaDigitalizacion(null);
					documento.setFechaUltimaModificacion(new Date());
					documento.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
					documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					documento.setFechaAsignacion(new Date());
					manejadorDocumento.actualizar(documento);
					idDocumentos.add(documento.getIdDocumento());

				} else {
					throw new SIMASCNegocioExcepcion(0,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()));

				}
			} else {
				throw new SIMASCNegocioExcepcion(0,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()));
			}
		}
		return;
	}

	@Override
	public List<Long> guardaActa(List<Documento> lstDocumentos, Long idCaso, Long idAudiencia, Long idPersona) {
		DocumentoDTO dto = new DocumentoDTO();
		dto.setIdCaso(idCaso);
		dto.setIdAudiencia(idAudiencia);
		ArrayList<Long> idDocumentos = (ArrayList<Long>) guardarDocumento(lstDocumentos);

		CasosAsignadosDTO casos = new CasosAsignadosDTO();
		casos.setIdPersona(idPersona);
		casos.setIdCaso(idCaso);
		RolPersonaCaso rolActual = manejadorRolPersonaCaso.obtenerRolDePersona(casos);
		// zona envio de correo
		if (!rolActual.getRol().getNombre().equalsIgnoreCase(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL)) {
			ArrayList<String> roles = new ArrayList<String>();
			roles.add(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
			List<RolPersonaCaso> rpc = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(dto.getIdCaso(),
					roles);
			RolPersonaCaso secretario = rpc.get(0);
			List<CorreoElectronicoDTO> correoEnviarDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
					.transformarColeccionConDependencias(
							correoElectronicoFacade.consultaCorreosPersona(secretario.getPersona().getIdPersona()),
							new ArrayList<CorreoElectronicoDTO>());
			ParametrosEnvioCorreoDTO correoDTO = new ParametrosEnvioCorreoDTO();
			correoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());
			correoDTO.setAsunto(UtilConstantes.ASUNTO_CORREO_RESULTADO_AUDIENCIA);
			String cuerpoCorreo = UtilConstantes.CUERPO_CORREO_RESULTADO_AUDIENCIA_ACTA;
			Audiencia audiencia = audienciaFacade.consultarDatosBasicoAudiencia(dto.getIdAudiencia());
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
			if (!manejadorDocumento.obtenerAudiosAudiencia(dto).isEmpty())
				cuerpoCorreo += UtilConstantes.CUERPO_CORREO_RESULTADO_AUDIENCIA_AUDIOS;

			cuerpoCorreo += " "
					+ dominioFacade.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA, audiencia.getTipoAudiencia())
					+ " " + simpleDate.format(audiencia.getHoraInicio()) + " del caso "
					+ audiencia.getCaso().getIdCaso() + "-" + audiencia.getCaso().getNombre();
			correoDTO.setIdCaso(dto.getIdCaso());
			ArrayList<String> cuerpos = new ArrayList<String>();
			cuerpos.add(cuerpoCorreo);
			correoDTO.setCuerpoCorreo(cuerpos);
			correoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
			correoDTO.setRolPersonaCaso(correoEnviarDTO);
			CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
			Persona remitente = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
					UtilDominios.ESTADO_PERSONA_ACTIVO);
			List<CorreoElectronico> correosR = manejadorCorreoElectronico
					.consultaCorreosPersona(remitente.getIdPersona());
			correo = correo.transformarConDependencias(correosR.get(0));
			correoDTO.setRemitente(correo);
			correoRolPersonaCasoFacade.enviarCorreo(correoDTO);
		}
		// fin zona envio de correo

		return idDocumentos;
	}

	@Override
	public DocumentoDTO consultarPorNombreServicioCaso(Documento documento) {
		DocumentoDTO dto = new DocumentoDTO();
		dto.setIdCaso(documento.getIdCaso());
		Caso caso = manejadorCaso.buscar(documento.getIdCaso());
		dto.setIdDocumento(documento.getIdCaso());
		dto.setDescripcion(documento.getDescripcion());
		dto.setIdDigitalizador(documento.getIdDigitalizador());
		dto.setTipoDocumento(documento.getTipoDocumento());
		dto.setNombreCaso(caso.getNombre());
		dto.setNombre(documento.getNombre());
		dto.setNumeroFolios(documento.getNumeroFolios());
		dto.setObservaciones(documento.getObservaciones());
		return dto;
	}

	@Override
	public void digitalizarDocumento(Documento documento) {
		documento.setFechaDigitalizacion(new Date());
		documento.setFechaUltimaModificacion(new Date());
		documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
		documento.setFechaCargue(new Date());
		if (documento.getTipoDocumento().equalsIgnoreCase(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)
				|| documento.getTipoDocumento().equalsIgnoreCase(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA)) {
			crearEvento(documento);
		}
		manejadorDocumento.actualizarDocumentoDigitalizado(documento);

	}

	@Override
	public void digitalizarDocumento(Long idCaso, String nombreDocumento, String extension, InputStream documentoStream,
			Documento documento, String idUsuarioModificacion) {
		String pathDocumento = UtilConstantes.CADENA_VACIA;
		nombreDocumento = nombreDocumento.replace(",", "");
		try {
			// 1. Intenta guardar fisicamente el documento en el gestor
			// documental
			pathDocumento = gestorDocumentalFacade.subirDocumento(idCaso, documento.getIdCarpeta(), documentoStream,
					nombreDocumento, extension, null);
			// 2. Intenta actualizar la metadata del documento en base de datos
			// (digitalizar)
			if (!pathDocumento.isEmpty()) {
				documento.setUrl(pathDocumento);
				digitalizarDocumento(documento);
			}
		} catch (EJBTransactionRolledbackException e) {
			// 2.1. Si hay algún error al tratar de actualizar la metadata del
			// documento y el documento fisico fue almacenado en el gestor
			// documental, este último será eliminado para deshacer lacción
			// completa
			if (!pathDocumento.isEmpty()) {
				gestorDocumentalFacade.eliminarDocumento(pathDocumento);
			}
		}
	}

	public DocumentoDTO obtenerActaAudiencia(DocumentoDTO documentoDTO) {
		DocumentoDTO dto = new DocumentoDTO();
		try {
			Documento documento = manejadorDocumento.obtenerActaAudiencia(documentoDTO);
			dto.setIdDocumento(documento.getIdDocumento());
			dto.setIdCaso(documento.getIdCaso());
			dto.setIdAudiencia(documento.getIdAudiencia());
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
		return dto;
	}

	@Override
	public List<DocumentoDTO> obtenerAudiosAudiencia(DocumentoDTO dto) {
		List<DocumentoDTO> audios = new ArrayList<>();
		List<Documento> lista = manejadorDocumento.obtenerAudiosAudiencia(dto);
		for (Documento documento : lista) {
			DocumentoDTO documentoDTO = new DocumentoDTO();
			documentoDTO.setIdDocumento(documento.getIdDocumento());
			documentoDTO.setNumeroDePista(documento.getNumeroDePista());
			documentoDTO.setDuracion(documento.getDuracion() == null ? null : documento.getDuracion() / 60);
			documentoDTO.setDescripcion(documento.getDescripcion());
			if (documento.getTranscripcionDocFuenteList() != null
					&& !documento.getTranscripcionDocFuenteList().isEmpty()) {
				boolean esDocumentoPendienteTranscribir = false;
				for (Transcripcion transcripcion : documento.getTranscripcionDocFuenteList()) {
					if (UtilDominios.ESTADO_TRANSCRIPCION_PENDIENTE.equals(transcripcion.getEstado())) {
						esDocumentoPendienteTranscribir = true;
						break;
					}
				}
				documentoDTO.setEstadoDigitalizacion(
						esDocumentoPendienteTranscribir ? UtilConstantes.ESTADO_TRANSCRIPCION_POR_TRANSCRIBIR
								: UtilConstantes.ESTADO_TRANSCRIPCION_TRANSCRIPCION_FINALIZADA);
			} else {
				documento.setEstadoDigitalizacion(UtilConstantes.ESTADO_TRANSCRIPCION_TRANSCRIPCION_FINALIZADA);
			}
			audios.add(documentoDTO);
		}
		return audios;
	}

	@Override
	public Long guardarAudio(DocumentoDTO documentoDTO) {
		Long idDocumento = 0L;
		try {
			if (documentoDTO.getIdCaso() != null) {
				Caso caso = manejadorCaso.buscar(documentoDTO.getIdCaso());
				Servicio servicio = manejadorServicio.buscar(caso.getIdServicio());
				if (caso.getIdCaso() != null) {
					if (UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equalsIgnoreCase(servicio.getTipo())
							|| UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equalsIgnoreCase(servicio.getTipo())) {
						Evento evento = new Evento();
						evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						evento.setFechaEvento(new Date());
						evento.setIdCaso(documentoDTO.getIdCaso());
						evento.setIdUsuarioModificacion(appContext.getContextoSesion().getNombreUsuario());
						evento.setFechaUltimaModificacion(new Date());
						evento.setTipoEvento(UtilDominios.TIPO_EVENTO_RADICACION_DOCUMENTO);
						evento.setObservaciones(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO183.toString())
										+ documentoDTO.getNombre() + ", " + MensajesSimasc.getInstancia()
												.getMensajePorKey(MensajesEnum.INFO184.toString())
										+ documentoDTO.getDescripcion());
						manejadorEvento.crear(evento);
					}
					if (documentoDTO.getIdCarpeta() == null) {
						Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(documentoDTO.getIdCaso(),
								new Long(1));
						documentoDTO.setIdCarpeta(carpeta.getIdCarpeta());
					}
					Documento documento = crearDocumentoDesdeDTO(documentoDTO);

					Date fechaTranscripcion = documento.getFechaDeGrabacion();
					documento.setFechaDeGrabacion(null);
					documento.setFechaDigitalizacion(fechaTranscripcion);
					manejadorDocumento.crear(documento);
					Transcripcion transcripcion = new Transcripcion();
					transcripcion.setFechaPrevistaDeEntrega(fechaTranscripcion);
					transcripcion.setIdDocAudioFuente(documento.getIdDocumento());
					transcripcion.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
					transcripcion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					transcripcion.setFechaUltimaModificacion(new Date());
					transcripcion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
					transcripcion.setIdPersona(documentoDTO.getIdDigitalizador());
					transcripcion.setTiempoInicial(0L);
					transcripcion.setTiempoFinal(documento.getDuracion().longValue());
					transcripcion.setTiempoTranscrito(0L);
					manejadorTranscripcion.crear(transcripcion);
					idDocumento = documento.getIdDocumento();
				} else {
					throw new SIMASCNegocioExcepcion(0,
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()));
				}
			} else {
				throw new SIMASCNegocioExcepcion(0,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR088.toString()));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return idDocumento;
	}

	private Documento crearDocumentoDesdeDTO(DocumentoDTO documentoDTO) {
		Documento documento = new Documento();
		documento.setEstadoDigitalizacion(null);
		documento.setIdCaso(documentoDTO.getIdCaso());
		documento.setIdAudiencia(documentoDTO.getIdAudiencia());
		documento.setDescripcion(documentoDTO.getDescripcion());
		documento.setNumeroDePista(documentoDTO.getNumeroDePista());
		documento.setNombre(documentoDTO.getNombre());
		documento.setTipoDocumento(documentoDTO.getTipoDocumento());
		documento.setPublicado(documentoDTO.getPublicado());
		documento.setTipoArchivo(documentoDTO.getTipoArchivo());
		documento.setUrl(documentoDTO.getUrl());
		documento.setEstadoDigitalizacion(documentoDTO.getEstadoDigitalizacion());
		documento.setDuracion(documentoDTO.getDuracion());
		documento.setFechaDigitalizacion(null);
		documento.setFechaUltimaModificacion(new Date());
		documento.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documento.setFechaAsignacion(new Date());
		return documento;
	}

	public Transcripcion crearTranscripcion(Date fechaTranscripcion, Documento documento, Long idDigitalizador) {
		Transcripcion transcripcion = new Transcripcion();
		transcripcion.setFechaPrevistaDeEntrega(fechaTranscripcion);
		transcripcion.setIdDocAudioFuente(documento.getIdDocumento());
		transcripcion.setEstado(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		transcripcion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		transcripcion.setFechaUltimaModificacion(new Date());
		transcripcion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		transcripcion.setIdPersona(idDigitalizador);
		transcripcion.setTiempoInicial(0L);
		transcripcion.setTiempoFinal(documento.getDuracion().longValue());
		transcripcion.setTiempoTranscrito(0L);
		return transcripcion;
	}

	@Override
	public Documento consultarPorId(Long id) {
		Documento doc = null;
		try {
			doc = manejadorDocumento.buscar(id);
		} catch (NoResultException e) {
			return doc;
		}
		return doc;
	}

	@Override
	public List<Persona> consultarRemitentes(Long idCaso) {
		List<Documento> listDocumentos = manejadorDocumento.consultarDocumentosActivos(idCaso);
		List<Persona> listRemitentes = new ArrayList<Persona>();
		for (Documento documento : listDocumentos) {
			Persona persona = manejadorPersona.buscar(documento.getIdPersonaRemitente());
			listRemitentes.add(persona);
		}
		return listRemitentes;
	}

	@Override
	public List<Documento> consultarDocumentosSecretaria(ParametrosCarpetaDTO carpetaInfo) {
		List<Documento> listDocumentos = manejadorDocumento.consultarDocumentosSecretaria(carpetaInfo);
		List<Documento> listDocumentosSecretaria = new ArrayList<>();
		for (Documento documento : listDocumentos) {

			Persona remitente = personaFacade.buscar(documento.getIdPersonaRemitente());
			Carpeta cuaderno = new Carpeta();
			if (documento.getIdCarpeta() != null) {
				cuaderno = carpetaFacade.buscar(documento.getIdCarpeta());
				if (cuaderno.getIdCarpetaContenedora() != null) {
					cuaderno = carpetaFacade.buscar(cuaderno.getIdCarpetaContenedora());
				}
			}
			InformacionFiltro f = new InformacionFiltro(TipoFiltro.EXACTO,
					NotificacionDocumento.ENTIDAD_NOTIFICACION_DOCUMENTO_ID_DOCUMENTO, documento.getIdDocumento());
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(f);
			List<NotificacionDocumento> notificacion = manejadorNotificacionDocumento.consultar(filtros, null, null);
			if (!notificacion.isEmpty()) {
				documento.setNotificacionDocumentoList((List<NotificacionDocumento>) notificacionDocumentoFacade
						.transformarEntidadesColeccionSinDependencias(notificacion,
								new ArrayList<NotificacionDocumento>()));
			}
			documento.setPersona(personaFacade.transformarEntidadSinDependencias(remitente));
			if (documento.getIdCarpeta() != null)
				documento.setCarpeta(carpetaFacade.transformarEntidadSinDependencias(cuaderno));
			listDocumentosSecretaria.add(documento);

		}
		return listDocumentosSecretaria;
	}

	@Override
	public List<Documento> consultarPorCasoTipo(Long casoId, String tipo) {
		return consultarPorCasoTipo(casoId, Arrays.asList(tipo));
	}

	@Override
	public List<Documento> consultarPorCasoTipo(Long casoId, List<String> tipos) {
		return manejadorDocumento.consultarDocumentosCasoTipo(casoId, tipos);
	}

	@Override
	public void registroDocumentoDemanda(Documento documento, String usuario) {

		// Actualiza la ruta del caso

		String observaciones = "La parte demandante ha modificao la demanda";
		eventoFacade.registrarEvento(documento.getIdCaso(), UtilDominios.TIPO_EVENTO_MODIFICACION_DEMANDA,
				observaciones, usuario);

		// envio de correo
		/*
		 * Caso caso = manejadorCaso.buscar(documento.getIdCaso()); List<String>
		 * cuerpoCorreo = new ArrayList<>();
		 * cuerpoCorreo.add("La parte demandante en el caso" + " (" +
		 * documento.getIdCaso() + "  " + caso.getNombre() +
		 * ") ha modificado la demanda."); List<String> estadosArbitros = new
		 * ArrayList<String>();
		 * estadosArbitros.add(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		 * 
		 * // CORREOS DE LOS ARBITROS List<RolPersonaCaso> arbitros =
		 * manejadorRolPersonaCaso.consultarArbitros(caso.getIdCaso(),
		 * UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, null, estadosArbitros);
		 * List<CorreoElectronico> correosArbitros = new ArrayList<>(); for
		 * (RolPersonaCaso arbitro : arbitros) { CorreoElectronico correoArbitro =
		 * correoElectronicoFacade
		 * .consultaCorreosPersona(arbitro.getRolPersonaCasoPK().getIdPersona()).get(0);
		 * correosArbitros.add(correoArbitro); } List<CorreoElectronicoDTO>
		 * correosArbitrosDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
		 * .transformarColeccionConDependencias(correosArbitros, new
		 * ArrayList<CorreoElectronicoDTO>());
		 * 
		 * // CORREO DE ABOGADO List<Persona> abogados = (List<Persona>)
		 * personaFacade.consultarPorRolCaso(caso.getIdCaso(),
		 * UtilDominios.ROL_PERSONA_ABOGADO); if (abogados.isEmpty()) { throw new
		 * SIMASCNegocioExcepcion(
		 * MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR151.toString
		 * ())); }
		 * 
		 * List<CorreoElectronico> correosAbogado = correoElectronicoFacade
		 * .consultaCorreosPersona(abogados.get(0).getIdPersona()); if
		 * (correosAbogado.isEmpty()) { throw new SIMASCNegocioExcepcion(
		 * MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR152.toString
		 * ())); } List<CorreoElectronicoDTO> correosAbogadoDTO =
		 * (List<CorreoElectronicoDTO>) correoElectronicoFacade
		 * .transformarColeccionConDependencias(correosAbogado, new
		 * ArrayList<CorreoElectronicoDTO>());
		 * 
		 * List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();
		 * 
		 * // Parametros ParametrosEnvioCorreoDTO parametrosCorreo = new
		 * ParametrosEnvioCorreoDTO();
		 * parametrosCorreo.setAsunto("Modificación de la demanda");
		 * parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
		 * parametrosCorreo.setRolPersonaCaso(correosArbitrosDTO);
		 * parametrosCorreo.setRemitente(correosAbogadoDTO.get(0));
		 * parametrosCorreo.setIdCaso(documento.getIdCaso());
		 * parametrosCorreo.setAdjuntos(adjuntos);
		 * parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		 * 
		 * correoRolPersonaCasoFacade.enviarCorreo(parametrosCorreo);
		 */
	}

	/**
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#obtenerDocumentoCaso(Long,
	 *      Long)
	 */
	@Override
	public DocumentoDTO obtenerDocumentoCaso(Long idCaso, Long idDocumento) {
		DocumentoDTO documentoDTO = null;

		Documento documento = manejadorDocumento.obtenerDocumentoCaso(idCaso, idDocumento);
		if (documento != null) {
			documentoDTO = new DocumentoDTO().transformarSinDependencias(documento);
		}

		return documentoDTO;
	}

	@Override
	public void registrarDocumentoPartes(Long idCaso, Long idDocumento) {
		Long idPersona = null;
		String idUsuarioModifica = null;
		Persona personaUsuario = null;

		List<String> textoCorreo = new ArrayList<String>();
		try {
			idUsuarioModifica = appContext.getContextoSesion().getNombreUsuario();
			idPersona = Long.parseLong(appContext.getContextoSesion().getIdUsuario());
		} catch (NullPointerException | NumberFormatException ne) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR530.toString()));
		}

		Caso caso = manejadorCaso.buscar(idCaso);
		personaUsuario = manejadorPersona.buscar(idPersona);
		Documento documento = manejadorDocumento.buscar(idDocumento);
		if (caso == null || personaUsuario == null || documento == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR531.toString()));
		}
		List<String> args = new ArrayList<>();
		args.add(documento.getNombre());
		args.add(String.valueOf(documento.getNumeroFolios()));
		args.add(personaUsuario.getNombreCompleto());
		args.add(documento.getObservaciones());
		String eventoDocumento = (String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO220.toString()), args.toArray()));
		eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_DOCUMENTO_ADICIONADO_PARTE, eventoDocumento,
				idUsuarioModifica);

		List<RolPersonaCaso> personasCorreo = obtenerRolPersonaEnvioDocumento(caso);
		if (!personasCorreo.isEmpty()) {
			List<String> args3 = new ArrayList<>();
			args3.add(String.valueOf(caso.getIdCaso()));
			String asunto = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO222.toString()), args3.toArray());

			List<String> args2 = new ArrayList<>();
			args2.add(personaUsuario.getNombreCompleto());
			args2.add(documento.getNombre());
			args2.add(String.valueOf(documento.getNumeroFolios()));
			args2.add(String.valueOf(caso.getIdCaso()));
			args2.add(caso.getNombre());
			textoCorreo.add(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO221.toString()), args2.toArray()));
			correoRolPersonaCasoFacade.envioDeCorreo(asunto, null, personasCorreo, null, textoCorreo, idCaso, null,
					null, false);

		}

	}

	/**
	 * ontiene la lista de personas a las cuales se les enviara el correo de
	 * notificacion de radicacion de un docuemtno subido por las partes
	 * 
	 * @param caso
	 * @return
	 */
	private List<RolPersonaCaso> obtenerRolPersonaEnvioDocumento(Caso caso) {
		List<RolPersonaCaso> personaDelCaso = new ArrayList<RolPersonaCaso>();

		if (this.esPlanJusticia(caso) && UtilDominios.ETAPA_CASO_PREARBITRAL.equals(caso.getEtapa())) {
			RolPersonaCaso abogadoCaso = manejadorRolPersonaCaso.consultarAbogadoDelCaso(caso.getIdCaso());

			if (abogadoCaso != null) {
				personaDelCaso.add(abogadoCaso);
			}

		} else if (this.esPlanJusticia(caso) && UtilDominios.ETAPA_CASO_ARBITRAL.equals(caso.getEtapa())) {
			RolPersonaCaso secretarioDelTribunal = manejadorRolPersonaCaso.consultarSecretarioDelCaso(caso.getIdCaso());
			if (secretarioDelTribunal != null) {
				personaDelCaso.add(secretarioDelTribunal);
			}

		} else if (this.esPlanDialogo(caso)) {
			List<Rol> rolPorServicio = agrupamientoRolFacade.obtenerRolesPorServicioYTipoAgrupador(caso.getIdServicio(),
					UtilDominios.TIPO_AGRUPAMIENTO_ROL_PRESTADOR_SERVICIO);
			if (!rolPorServicio.isEmpty()) {
				List<RolPersonaCaso> rolPersonaCaso = manejadorRolPersonaCaso
						.consultarPersonasAsignadasCasoPorRol(caso.getIdCaso(), rolPorServicio.get(0).getNombre());
				if (!rolPersonaCaso.isEmpty()) {
					personaDelCaso.addAll(rolPersonaCaso);
				}
			}
		}

		return personaDelCaso;
	}

	@Override
	public void modificacionDemandaParte(Long idCaso, Long idDocumento, Long idPersona) {
		// Actualiza la ruta del caso
		List<RolPersonaCaso> personasEnvio = new ArrayList<RolPersonaCaso>();
		String usuarioModificacion = UtilConstantes.USUARIO_DEFECTO_SIMASC;
		if (appContext != null && appContext.getContextoSesion() != null
				&& appContext.getContextoSesion().getNombreUsuario() != null) {
			usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
		}
		List<String> textoCorreo = new ArrayList<String>();

		Caso caso = manejadorCaso.buscar(idCaso);
		Persona personaUsuario = manejadorPersona.buscar(idPersona);
		Documento documento = manejadorDocumento.buscar(idDocumento);
		if (caso == null || personaUsuario == null || documento == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR531.toString()));
		}
		// registro evento
		List<String> args1 = new ArrayList<>();
		args1.add(personaUsuario.getNombreCompleto());
		// registro envento
		String observaciones = String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO225.toString()), args1.toArray());
		eventoFacade.registrarEvento(idCaso, UtilDominios.TIPO_EVENTO_MODIFICACION_DEMANDA, observaciones,
				usuarioModificacion);
		List<String> rolesEnvio = Arrays.asList(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		personasEnvio.addAll(manejadorRolPersonaCaso.consultarPartesDelCasoRPC(idCaso, rolesEnvio));

		List<String> estados = Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		personasEnvio.addAll(manejadorRolPersonaCaso.consultarArbitrosCasoPorEstadosRPC(idCaso, estados));

		// envio de correo
		if (!personasEnvio.isEmpty()) {
			String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO226.toString());
			List<String> args2 = new ArrayList<>();
			args2.add(personaUsuario.getNombreCompleto());
			args2.add(String.valueOf(caso.getIdCaso()));
			args2.add(caso.getNombre());
			textoCorreo.add(String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO227.toString()), args2.toArray()));
			correoRolPersonaCasoFacade.envioDeCorreo(asunto, null, personasEnvio, null, textoCorreo, idCaso, null, null,
					true);

		}

	}

	/**
	 * Metodo encargado de validar si el caso es plan justicia o no
	 * 
	 * @param caso
	 * @return true si es plan justicia false de lo contrario
	 */
	private boolean esPlanJusticia(Caso caso) {
		return caso != null && caso.getServicioMateria() != null && caso.getServicioMateria().getServicio() != null
				&& (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(caso.getServicioMateria().getServicio().getTipo())
				|| UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(caso.getServicioMateria().getServicio().getTipo()));

	}

	/**
	 * Metodo encargado de validar si el caso es plan dialogos o no
	 * 
	 * @param caso
	 * @return
	 */
	private boolean esPlanDialogo(Caso caso) {
		return caso != null && caso.getServicioMateria() != null && caso.getServicioMateria().getServicio() != null
				&& UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equals(caso.getServicioMateria().getServicio().getTipo());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#
	 * consultarPorSolicitudServicio(java.lang.Long)
	 */
	@Override
	public List<Documento> consultarPorSolicitudServicio(Long idSolicitudServicio) {
		try {
			return manejadorDocumento.consultarDocumentosActivosPorSolicitud(idSolicitudServicio);
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#
	 * guardarDocumentoSolicitudServicio(Documento)
	 */
	@Override
	public Long guardarDocumentoSolicitudServicio(Documento documento) {
		Long idDocumento = null;
		if (documento.getIdSolicitudServicio() != null) {
			//

			if (documento.getDescripcion() == null) {
				documento.setDescripcion(documento.getNombre());
			}
			documento.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_DIGITALIZADO);
			documento.setFechaDigitalizacion(new Date());

			manejadorDocumento.crear(documento);
			idDocumento = documento.getIdDocumento();
		} else {
			lanzarSIMASCNegocioExcepcion(MensajesEnum.ERROR086.toString());
		}
		return idDocumento;
	}

	@Override
	public void actualizarInfoDocumento(DocumentoDTO documento) {
		String usuarioModificacion = null;
		if (documento != null && documento.getIdDocumento() != null) {
			Documento docActualizar = manejadorDocumento.buscar(documento.getIdDocumento());
			docActualizar.setDescripcion(documento.getDescripcion());
			docActualizar.setFechaUltimaModificacion(new Date());
			docActualizar.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			docActualizar.setUrl(documento.getUrl());
			docActualizar.setTipoArchivo(documento.getTipoArchivo());
			docActualizar.setEstado(documento.getEstado());
			if (appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null) {
				usuarioModificacion = appContext.getContextoSesion().getNombreUsuario();
			} else {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR530.toString()));
			}
			docActualizar.setIdUsuarioModificacion(usuarioModificacion);

			manejadorDocumento.actualizar(docActualizar);
		} else {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR531.toString()));

		}

	}

	@Override
	public DocumentoDTO obtenerDocumentoPersonaPorTipo(Long idPersona, String tipo) {
		DocumentoDTO documentoDTO = null;

		Documento documento = manejadorDocumento.obtenerDocumentoPersonaPorTipo(idPersona, tipo);
		if (documento != null) {
			documentoDTO = new DocumentoDTO().transformarSinDependencias(documento);
		}

		return documentoDTO;
	}

	@Override
	public void guardarDocumentosMinisterio(ArrayOfbase64Binary listaDocumentos, Long idCaso) throws Exception {
		for (byte[] datosDocumento : listaDocumentos.getBase64Binary()) {
			InputStream is = new ByteArrayInputStream(datosDocumento);
			Documento documento = crearDocumentoRespuesta(idCaso);
			almacenamientoDocumentosFacade.guardarDocumento(idCaso, documento.getNombre(),
					UtilDominios.TIPO_ARCHIVO_PDF, is, documento, null, null, null);
		}
	}

	/**
	 * Método que crea un objeto documento para persistir
	 * 
	 * @param idCaso
	 * @return
	 */
	private Documento crearDocumentoRespuesta(Long idCaso) {
		Documento documento = new Documento();
		documento.setIdCaso(idCaso);
		documento.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
		documento.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_RESPUESTA_MINISTERIO);
		documento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documento.setDescripcion(DocumentoFacade.DESCRIPCION_DOCUMENTO);
		documento.setNuevo(true);
		documento.setNombre(DocumentoFacade.NOMBRE_DOCUMENTO + new Date().getTime());
		documento.setPublicado(false);

		return documento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#
	 * eliminarDocumentosCasoJornada(java.lang.Long, java.lang.String)
	 */
	@Override
	public void eliminarDocumentosCasoJornada(Long idCaso, String usuarioModificacion) {
		List<Documento> documentos = consultarPorCasoTipo(idCaso,
				Arrays.asList(UtilDominios.TIPO_DOCUMENTO_DIG_ACTA_CONCILIACION,
						UtilDominios.TIPO_DOCUMENTO_DIG_RESPUESTA_MINISTERIO));
		for (Documento documento : documentos) {
			almacenamientoDocumentosFacade.eliminarDocumento(documento.getIdDocumento(), usuarioModificacion);
		}
	}

	@Override
	public List<DocumentosDigitalizadorDTO> consultarDocumentosPorDigitalizar(FiltroCasosAsignadosDTO filtros) {
		return manejadorDocumento.consultarDocumentosDigitalizar(filtros);
	}

	@Override
	public void publicarReversarDocumento(Documento documento, Long idCaso, boolean publicar,
			String idPersonaModificacion) {

		if (publicar) {
			this.publicarDocumento(documento, idCaso, idPersonaModificacion);
		} else {
			this.reversarPublicacion(documento, idCaso, idPersonaModificacion);
		}
	}

	@Override
	public List<DocumentoDTO> consultarDocumentos(Long idCaso, String nombreDocumento, String tipoDocumento) {
		return manejadorDocumento.consultarDocumento(idCaso, nombreDocumento, tipoDocumento);
	}

	private void publicarDocumento(Documento documento, Long idCaso, String idPersonaModificacion) {

		documento.setPublicado(true);
		this.actualizar(documento);
		Caso caso = casoFacade.buscar(idCaso);
		String observacionesevento = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO003.toString());
		observacionesevento = observacionesevento.replace("denominacion", documento.getNombre());
		eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_PUBLICACION_DOCUMENTO, observacionesevento,
				idPersonaModificacion, new Timestamp(new Date().getTime()), UtilDominios.ESTADO_REGISTRO_ACTIVO);
	}

	private void reversarPublicacion(Documento documento, Long idCaso, String idPersonaModificacion) {
		documento.setPublicado(false);
		this.actualizar(documento);
		Caso caso = casoFacade.buscar(idCaso);
		String observacionesevento = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO004.toString());
		observacionesevento = observacionesevento.replace("nombreDocumento", documento.getNombre());
		eventoFacade.registrarEvento(caso, UtilDominios.TIPO_EVENTO_REVERSAR_DOCUMENTO, observacionesevento,
				idPersonaModificacion, new Timestamp(new Date().getTime()), UtilDominios.ESTADO_REGISTRO_ACTIVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade#
	 * verificarParteFirma(com.ccb.simasc.transversal.dto.
	 * DatosVerificacionParteFirmaDTO, java.lang.String)
	 */
	@Override
	public void verificarParteFirma(DatosVerificacionParteFirmaDTO datosVerificacionParteFirmaDTO, String sessionId) {
		BufferedReader bufferedReader = null;
		String respuesta = UtilConstantes.CADENA_VACIA;

		try {
			// Obtención de los parámetros generales de consumo del servicio
			String urlServicio = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_PARAMETRO_URL_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETRO_URL_SERVICIOS,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
			String usuario = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_USUARIO_SERVICIO_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETROS_CERTICAMARA,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
			String contrasena = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_CLAVE_SERVICIO_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETROS_CERTICAMARA,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
			String aplicacionId = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_ID_APLICACION_CCB_SERVICIO_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETROS_CERTICAMARA,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO); // se debe verificar con CCB
			String paginas = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_PARAMETRO_PAGINAS_SERVICIO_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETROS_CERTICAMARA,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
			String validacion = (String) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
					UtilParamGenerales.CODIGO_PARAMETRO_VALIDACION_SERVICIO_CERTICAMARA,
					UtilParamGenerales.TIPO_PARAMETROS_CERTICAMARA,
					ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);

			// Construcción del valor del encabezzado HTTP de autorización
			String credencialesUsuario = usuario + UtilConstantes.DOS_PUNTOS + contrasena;
			String autorizacionBasica = new String(Base64.encodeBase64(credencialesUsuario.getBytes()));

			// Validación del tipo de documento de la persona
			String tipoDocumento = homologacionTipoDocumentoPersona(datosVerificacionParteFirmaDTO.getTipoDocumento()); // Tipo de documento de la persona que firma
																														// (Se debe realizar homologación para tipo de
																														// documento CC (8) y CE (3))
			if (tipoDocumento == null || tipoDocumento.isEmpty())
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR650.toString()));

			StringBuilder parametros = new StringBuilder();
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_TIPO_DOCUMENTO).append(tipoDocumento);
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_NUMERO_DOCUMENTO)
					.append(datosVerificacionParteFirmaDTO.getNumeroDocumento()); // Número de documento de la persona
																					// que firma
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_NOMBRES)
					.append(datosVerificacionParteFirmaDTO.getNombres()); // Nombres (primer nombre y segundo nombre) de
																			// la persona que firma
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_PRIMER_APELLIDO)
					.append(datosVerificacionParteFirmaDTO.getPrimerApellido()); // Primer apellido de la persona que
																					// firma
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_SEGUNDO_APELLIDO)
					.append(datosVerificacionParteFirmaDTO.getSegundoApellido()); // Segundo apellido de la persona que
																					// firma
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_TOKEN_ENTRADA)
					.append(datosVerificacionParteFirmaDTO.getTokenEntrada()); // Token generado del servicio de Cámara
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_APLICACION_ID).append(aplicacionId);
			parametros.append(UtilConstantes.LLAVE_VALOR_PARAMETRO_OLVIDO);
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_TELEFONO_MOVIL).append(
					UtilConstantes.CODIGO_INDICATIVO_COLOMBIA + datosVerificacionParteFirmaDTO.getTelefonoMovil()); // Telefono movil (tipo telefono: CEL) de la
																													// persona que firma precedido por el indicativo
																													// de COLOMBIA (57)
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_PAGINAS).append(paginas);
			parametros.append(UtilConstantes.LLAVE_PARAMETRO_VALIDACION).append(validacion);

			if (sessionId != null && !sessionId.isEmpty()) {
				parametros.append(UtilConstantes.LLAVE_PARAMETRO_SESSION_ID).append(sessionId);
			}

			// Construcción de la petición del servicio
			URL url = new URL(urlServicio);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod(UtilConstantes.METODO_HTTP_POST);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestProperty(UtilConstantes.LLAVE_ENCABEZADO_HTTP_AUTORIZACION, autorizacionBasica);
			httpURLConnection.setRequestProperty(UtilConstantes.LLAVE_ENCABEZADO_HTTP_TIPO_CONTENIDO,
					UtilConstantes.VALOR_ENCABEZADO_HTTP_TIPO_CONTENIDO);
			httpURLConnection.setRequestProperty(UtilConstantes.LLAVE_ENCABEZADO_HTTP_LONGITUD_CONTENIDO,
					UtilConstantes.CADENA_VACIA + parametros.toString().getBytes().length);
			httpURLConnection.setRequestProperty(UtilConstantes.LLAVE_ENCABEZADO_HTTP_CONTENIDO_PROCESAR,
					UtilConstantes.VALOR_ENCABEZADO_HTTP_CONTENIDO_PROCESAR);
			httpURLConnection.setRequestProperty(UtilConstantes.LLAVE_ENCABEZADO_HTTP_AGENTE_USUARIO,
					UtilConstantes.VALOR_ENCABEZADO_HTTP_AGENTE_USUARIO);
			httpURLConnection.getOutputStream().write(parametros.toString().getBytes());

			httpURLConnection.connect();

			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			respuesta = bufferedReader.readLine();

			if (!UtilConstantes.TIPO_SESION_VALOR_OK.equals(respuesta))
				throw new SIMASCNegocioExcepcion(respuesta);

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR651.toString()));
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * Método encargado de la homologación del tipo de documento para el consumo del
	 * servicio de Certicamara
	 * 
	 * @param tipoDocumento
	 * @return
	 */
	private String homologacionTipoDocumentoPersona(String tipoDocumento) {
		String tipoDocumentoHomologacion = null;

		switch (tipoDocumento) {
		case UtilDominios.TIPO_DOCUMENTO_PERSONA_CEDULA_DE_CIUDADANIA:
			tipoDocumentoHomologacion = "8";
			break;
		case UtilDominios.TIPO_DOCUMENTO_PERSONA_CEDULA_DE_EXTRANJERIA:
			tipoDocumentoHomologacion = "3";
			break;
		default:
			tipoDocumentoHomologacion = UtilConstantes.CADENA_VACIA;
			break;
		}

		return tipoDocumentoHomologacion;
	}

	@Override
	public void actualizaCarpetaDocumento(Long idCaso) {
		List<Carpeta> carpetas = manejadorCarpeta.obtenerCudernosPorCaso(idCaso);

		List<Documento> documentoList = manejadorDocumento.consultarDocumentosActivos(idCaso);

		// Actualizacion carpeta
		if (documentoList != null && !documentoList.isEmpty()) {
			for (Documento documento : documentoList) {
				if(documento.getTipoDocumento().equals(UtilDominios.TIPO_DOCUMENTO_DIG_DOCUMENTO_DIGITALIZADO)) {
					for(Carpeta carpeta:carpetas) {
						if(carpeta.getIdCuaderno().equals(UtilDominios.ID_CUADERNO_PRUEBAS)) {
							documento.setIdCarpeta(carpeta.getIdCarpeta());
						}
					}
				}else if(documento.getTipoDocumento().equals(UtilDominios.TIPO_DOCUMENTO_DIG_MEDIDAS_CAUTELARES)) {
					for(Carpeta carpeta:carpetas) {
						if(carpeta.getIdCuaderno().equals(UtilDominios.ID_CUADERNO_MEDIDAS_CAUTELARES)) {
							documento.setIdCarpeta(carpeta.getIdCarpeta());
						}
					}
				}else {
					for(Carpeta carpeta:carpetas) {
						if(carpeta.getIdCuaderno().equals(UtilDominios.ID_CUADERNO_PRINCIPAL)) {
							documento.setIdCarpeta(carpeta.getIdCarpeta());
						}
					}
				}
				
			}
			manejadorDocumento.actualizarLista(documentoList);
		}

	}

	@Override
	public void generaDocumentosNotificacion(Caso caso, List<RolPersonaCaso> rolPersonaCasoList,
			String realPath) {

		ParametrosGenerarCartaDTO parametrosGenerarCartaDTO = new ParametrosGenerarCartaDTO();
		List<Long> listaNotificados = new ArrayList<Long>();

		parametrosGenerarCartaDTO.setIdCaso(caso.getIdCaso());
		parametrosGenerarCartaDTO.setIdPlantilla(UtilConstantes.ID_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS);
		parametrosGenerarCartaDTO.setIndicadorNotificacion(UtilConstantes.SI);

		List<String> nombreParametros = new ArrayList<String>();
		
		if(caso.isMedidasCautelares()) {
			nombreParametros.add(UtilConstantes.NOTIFICACION_ROL_CARTA_MED_CAUTELARES);
		}else {
			nombreParametros.add(UtilConstantes.NOTIFICACION_ROL_CARTA_PCDRA);	
		}
		

		List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade.consultarParametroDeServicio(
				nombreParametros, caso.getIdServicio(), UtilConstantes.TIPO_PARAMETRO_NOTIFICACION_ROL_CARTA);			

		if (parametroDeServicioList != null && !parametroDeServicioList.isEmpty()) {
			
			List<String> roles = Arrays.asList(parametroDeServicioList.get(0).getValor().split(","));

			for (RolPersonaCaso rolPersonaCaso : rolPersonaCasoList) {

				if (roles.contains(rolPersonaCaso.getRol().getIdRol().toString())) {
					listaNotificados.add(rolPersonaCaso.getPersona().getIdPersona());
				}
			}

			parametrosGenerarCartaDTO.setListaIdNotificados(listaNotificados);

			List<CartaPersona> cartasGeneradasList = cartaPersonaFacade.generarCarta(parametrosGenerarCartaDTO, null);

			boolean insertoDocumento = false;
			
			
			for (CartaPersona cartapersona : cartasGeneradasList) {
				RadicacionSolicitudPDF radicacionSolicitudPDF = new RadicacionSolicitudPDF();								

				String tempPathHtmlFile =  RadicacionSolicitudPDF.RESOURCE + "page1.html";
				String tempPathPdfFile =  RadicacionSolicitudPDF.RESOURCE + "page1.pdf";
				radicacionSolicitudPDF.createHtmlToexport(cartapersona.getContenido(), tempPathHtmlFile);
				radicacionSolicitudPDF.generatePDFFromHtml(tempPathHtmlFile, tempPathPdfFile);

				byte[] bytes;
				try {
					bytes = clienteFirmaDigital.firmarPDF(tempPathPdfFile, "", null, null, false);
					Documento documentoEntity = new Documento();
					documentoEntity.setIdCaso(caso.getIdCaso());
					documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
					documentoEntity.setTipoDocumento(UtilDominios.NOMBRE_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS);
					documentoEntity.setNombre(UtilConstantes.NOMBRE_TEMPLATE_CARTAS_DOCUMENTOS_RADICADOS);
					documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
					documentoEntity.setFechaUltimaModificacion(new Date());
					documentoEntity.setDescripcion(UtilConstantes.NOMBRE_TEMPLATE_CARTAS_DOCUMENTOS_RADICADOS);
					documentoEntity.setNuevo(true);

					InputStream is = new ByteArrayInputStream(bytes);
					
					if(!insertoDocumento) {
												
						almacenamientoDocumentosFacade.guardarDocumento(caso.getIdCaso(),
								UtilConstantes.NOMBRE_PLANTILLA_CARTA_DOCUMENTOS_RADICADOS, UtilDominios.TIPO_ARCHIVO_PDF,
								is, documentoEntity, UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null);
						insertoDocumento = true;

					}									
					cartaPersonaFacade.enviarCartaCorreo(cartapersona);

				} catch (

				Exception e) {
					LOGGER.error(e.getMessage());
				}
			}

		}
	}

	@Override
	public void migraDocumentosSolicitudCaso(Long idSolicitudServicio, Long idCaso) {
		List<Documento> documentosSolicitud = new ArrayList<>();
		documentosSolicitud = manejadorDocumento.consultarDocumentosActivosPorSolicitud(idSolicitudServicio);

		// Migración documentos de la solicitud al caso
		if (documentosSolicitud != null && !documentosSolicitud.isEmpty()) {
			for (Documento documento : documentosSolicitud) {
				documento.setIdCaso(idCaso);
				// manejadorDocumento.actualizar(documento);
			}
			manejadorDocumento.actualizarLista(documentosSolicitud);
		}

	}

	@Override
	public void generarDocumentoPronunciamiento(InfoCasoParaPronunciamientoDTO infoCasoParaPronunciamientoDTO,
			Pronunciamiento pronunciamiento, Long idCaso, PlantillaCarta plantillaCarta, Persona persona)
			throws SIMASCNegocioExcepcion {

		try {

			Caso caso = manejadorCaso.buscar(idCaso);

			String contenidoDocumento = reemplazarContenidoPlantilla(infoCasoParaPronunciamientoDTO, pronunciamiento,
					plantillaCarta, persona, caso.getNombre());

			RadicacionSolicitudPDF radicacionSolicitudPDF = new RadicacionSolicitudPDF();

			String tempPathHtmlFile = contexto.getRealPath("/") + RadicacionSolicitudPDF.RESOURCE + "page1.html";
			String tempPathPdfFile = contexto.getRealPath("/") + RadicacionSolicitudPDF.RESOURCE + "page1.pdf";
			radicacionSolicitudPDF.createHtmlToexport(contenidoDocumento, tempPathHtmlFile);
			radicacionSolicitudPDF.generatePDFFromHtml(tempPathHtmlFile, tempPathPdfFile);

			byte[] bytes;

			bytes = clienteFirmaDigital.firmarPDF(tempPathPdfFile, "", null, null, false);
			Documento documentoEntity = new Documento();

			Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(idCaso, 1L);
			documentoEntity.setIdCarpeta(carpeta.getIdCarpeta());
			documentoEntity.setIdCaso(idCaso);
			documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
			documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_PRONUNCIAMIENTO);
			documentoEntity.setNombre(UtilConstantes.NOMBRE_DOCUMENTO_PRONUNCIAMIENTO + persona.getNombreCompleto());
			documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			documentoEntity.setFechaUltimaModificacion(new Date());
			documentoEntity.setDescripcion("");
			documentoEntity.setNuevo(true);

			InputStream is = new ByteArrayInputStream(bytes);
			almacenamientoDocumentosFacade.guardarDocumento(idCaso,
					UtilConstantes.NOMBRE_DOCUMENTO_PRONUNCIAMIENTO + persona.getNombreCompleto(),
					UtilDominios.TIPO_ARCHIVO_PDF, is, documentoEntity, UtilConstantes.USUARIO_DEFECTO_SIMASC,
					persona.getIdPersona(), null);

		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}
	}

	private String reemplazarContenidoPlantilla(InfoCasoParaPronunciamientoDTO infoCasoParaPronunciamientoDTO,
			Pronunciamiento pronunciamiento, PlantillaCarta plantillaCarta, Persona persona, String nombreCaso) {
		Map<String, String> parametros = new HashMap<String, String>();

		parametros.put("fechaP", UtilOperaciones.formatearFechaReporte(pronunciamiento.getFecha()));
		parametros.put("nombreP", (persona.getNombreCompleto() != null ? persona.getNombreCompleto() : ""));
		parametros.put("documentoP", (persona.getNumeroDocumento() != null ? persona.getNumeroDocumento() : ""));
		parametros.put("tramiteP", (nombreCaso != null ? nombreCaso : ""));
		parametros.put("arbitroSip", ("ACP".equals(pronunciamiento.getPronunciamiento()) ? "X" : ""));
		parametros.put("arbitronop", ("DEC".equals(pronunciamiento.getPronunciamiento()) ? "X" : ""));
		parametros.put("motivoP",
				(pronunciamiento.getMotivoDeclinacion() != null
						? consultarMotivo(pronunciamiento.getMotivoDeclinacion())
						: ""));
		parametros.put("observacionesP",
				(pronunciamiento.getObservaciones() != null ? pronunciamiento.getObservaciones() : ""));
		parametros.put("procesosSiP", (pronunciamiento.isTramitesAdministrativos() ? "X" : ""));
		parametros.put("procesosNoP", (pronunciamiento.isTramitesAdministrativos() ? "" : "X"));
		parametros.put("familiarSiP", (pronunciamiento.getTieneRelacionConPartes() ? "X" : ""));
		parametros.put("familiarNoP", (pronunciamiento.getTieneRelacionConPartes() ? "" : "X"));
		parametros.put("manifestacionP",
				(pronunciamiento.getRevelaciones() != null ? pronunciamiento.getRevelaciones() : ""));
		parametros.put("tribunalesSiP", (pronunciamiento.getMaximoDeTribunalesEntidadPublica() ? "X" : ""));
		parametros.put("tribunalesNoP", (pronunciamiento.getMaximoDeTribunalesEntidadPublica() ? "" : "X"));
		parametros.put("tribunales3SiP", (pronunciamiento.getMaximoDeTribunalesEntidadPublicaLey1682() ? "X" : ""));
		parametros.put("tribunales3NoP", (pronunciamiento.getMaximoDeTribunalesEntidadPublicaLey1682() ? "" : "X"));
		parametros.put("mediadorSiP", (pronunciamiento.isProcedimientoRecuperacionEmpresarial() ? "X" : ""));
		parametros.put("mediadorNoP", (pronunciamiento.isProcedimientoRecuperacionEmpresarial() ? "" : "X"));
		parametros.put("obtenerArticuloTipoServicioP",
				"De conformidad con lo previsto en el artículo 14 de la Ley 1563 de 2012,");

		return reemplazarContenido(plantillaCarta.getPlantillaHtml(), parametros);

	}

	private String generarContenidoLista(List<ParteCasoDTO> partes) {
		String resultado = "";
		int i = 0;
		for (ParteCasoDTO parte : partes) {
			if (i == 0) {
				resultado = resultado + parte.getNombre();
			} else {
				resultado = resultado + ", " + parte.getNombre();
			}
		}

		return resultado;
	}

	private String reemplazarContenido(String plantilla, Map<String, String> parametros) {

		String result = plantilla;

		for (Map.Entry<String, String> entry : parametros.entrySet()) {
			if (entry.getValue() != null) {
				result = result.replaceAll(entry.getKey(), entry.getValue());
			} else {
				result = result.replaceAll(entry.getKey(), "");
			}

		}

		return result;

	}

	private String obtenerNombrePacto(InfoCasoParaPronunciamientoDTO infoCasoParaPronunciamientoDTO) {
		String tipoServicio = "pacto arbitral";
		if ("PDL".equals(infoCasoParaPronunciamientoDTO.getTipoServicio())) {
			tipoServicio = "pacto de amigable composición";
		}

		return tipoServicio;
	}

	private String consultarMotivo(String codigo) {
		DominioPK dominioPK = new DominioPK();
		dominioPK.setDominio(UtilDominios.NOMBRE_DOMINIO_MOTIVOS_DE_RECHAZO_DE_DESIGNACION);
		dominioPK.setCodigo(codigo);

		Dominio dominio = manejadorDominio.buscar(dominioPK);

		return (dominio.getNombre() != null ? dominio.getNombre() : "");
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosPorIdCarpeta(Long idCarpetaContenedora) {		
		return manejadorDocumento.consultarDocumentosPorIdCarpeta(idCarpetaContenedora);
	}

	@Override
	public List<BusquedaSemanticaDTO> consultarBusqueda(Long idCaso, String keyword, String tipoDocumento, String fechaInicial,
			String fechaFinal) {
		return manejadorDocumento.consultarBusqueda(idCaso, keyword, tipoDocumento, fechaInicial, fechaFinal);
	}
	
	@Override
	public int consultarCantidadDocumentosIguales(Long idDocumento) {		
		return manejadorDocumento.consultarCantidadDocumentosIguales(idDocumento);
	}


// protected region metodos adicionales end
	
	//metodo para notificar al abagogado o al secretario por la radicación de un docuemento en el CAC
	private void notificarRadicacionDocumento(Documento documento , Caso caso) {

		if(documento!= null && documento.getIdCarpeta()!= null) {
			Carpeta carpeta = manejadorCarpeta.buscar(documento.getIdCarpeta());

			if(carpeta.getCuaderno() != null && carpeta.getEsCuaderno()) {

				if(carpeta.getCuaderno().getIdCuaderno() == UtilDominios.ID_CUADERNO_RADICACION_DE_DOCUMENTOS_CAC){
					if(caso.getEtapa().equals(UtilDominios.ETAPA_CASO_PREARBITRAL)) {
						RolPersonaCaso abogado = manejadorRolPersonaCaso.consultarAbogadoDelCaso(caso.getIdCaso());
						this.enviarCorreoNotificacion(abogado,documento,caso);
					}else if(caso.getEtapa().equals(UtilDominios.ETAPA_CASO_ARBITRAL)) {
						RolPersonaCaso secretario = manejadorRolPersonaCaso.consultarSecretarioDelCaso(caso.getIdCaso());
						this.enviarCorreoNotificacion(secretario,documento,caso);
					}
					documentoRadicadoFacade.guardarDocumentoRadicado(documento.getIdDocumento());
				}
			}
		}
		
	}
	
	private void enviarCorreoNotificacion(RolPersonaCaso persona, Documento documento, Caso caso ) {
		List<CorreoElectronicoDTO> correoEnviarDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade
				.transformarColeccionConDependencias(
						correoElectronicoFacade.consultaCorreosPersona(persona.getPersona().getIdPersona()),
						new ArrayList<CorreoElectronicoDTO>());
		
		ParametrosEnvioCorreoDTO correoDTO = new ParametrosEnvioCorreoDTO();
		correoDTO.setAdjuntos(new ArrayList<DocumentoDTO>());
		correoDTO.setAsunto(UtilConstantes.ASUNTO_CORREO_RADICACION_DOCUMENTOS);
		
		List<PlantillaCarta> plantillaCartaList = plantillaCartaFacade.consultarPlantillaNombre(
				UtilConstantes.NOMBRE_PLANTILLA_RADICAR_DOCUMENTO_CAC,
				UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put(UtilConstantes.NOMBRE_PERSONA, persona.getPersona().getNombreCompleto());
		filtros.put(UtilConstantes.ID_CASO, ""+caso.getIdCaso());
		filtros.put(UtilConstantes.NOMBRE_CASO, caso.getNombre());
		filtros.put(UtilConstantes.NOMBRE_DOCUMENTO, documento.getNombre());
		
		String cuerpoCorreo = alertaFacade
				.reemplazarTextoAlertas(plantillaCartaList.get(0).getPlantillaHtml(), filtros);
		
		correoDTO.setIdCaso(documento.getIdCaso());
		ArrayList<String> cuerpos = new ArrayList<String>();
		cuerpos.add(cuerpoCorreo);
		correoDTO.setCuerpoCorreo(cuerpos);
		correoDTO.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		correoDTO.setRolPersonaCaso(correoEnviarDTO);
		CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
		Persona remitente = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
				UtilDominios.ESTADO_PERSONA_ACTIVO);
		List<CorreoElectronico> correosR = manejadorCorreoElectronico
				.consultaCorreosPersona(remitente.getIdPersona());
		correo = correo.transformarConDependencias(correosR.get(0));
		correoDTO.setRemitente(correo);
		correoRolPersonaCasoFacade.enviarCorreo(correoDTO);
	}

	@Override
	public void generarIndiceElectronico(Long idCaso, Boolean firma) {
		TipoDocumentoFoliadoDTO documentoFolio = new TipoDocumentoFoliadoDTO();
		List<DocumentoIndiceElectronicoDTO> indiceElectronico =
		manejadorDocumento.generarIndiceElectronico(idCaso);	
		
		int orden = 0;
		int paginaInicial = 0;
		int paginaFinal = 0;
		for(DocumentoIndiceElectronicoDTO doc : indiceElectronico) {
			orden++;
			paginaInicial = paginaFinal + 1;
			paginaFinal = paginaInicial + doc.getCantidadPaginas() - 1;
						
			doc.setOrden(orden);
			doc.setPaginaInicial(paginaInicial);
			doc.setPaginaFinal(paginaFinal);
			doc.setSerie("1");
		}			
		documentoFolio.setDocumentoIndizado(indiceElectronico);
		generarPDFIndiceElectronico(idCaso, indiceElectronico,firma);
		String xml =convetirObjetoAXml(documentoFolio);
				
		Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(idCaso, 1L);
		Documento documentoEntity = new Documento();
		documentoEntity.setIdCarpeta(carpeta.getIdCarpeta());
		documentoEntity.setIdCaso(idCaso);
		documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_XML);
		documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_INDICE_ELECTRONICO);
		documentoEntity.setNombre("IndiceElectronico");
		documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoEntity.setFechaUltimaModificacion(new Date()); 
		documentoEntity.setDescripcion("IndiceElectronicoXML");
		documentoEntity.setNuevo(true);		
		InputStream is = null;
		byte[] bytes;
		bytes = xml.getBytes();
		if(firma) {		
			try {
				bytes = clienteFirmaDigital.firmarPDF(xml, "", null, null, false);
			} catch (SimascNegocioPruebaException e1) {
				LOGGER.error(e1.getMessage());
			}
		}		
		is = new ByteArrayInputStream(bytes);		
		try {
			almacenamientoDocumentosFacade.guardarDocumento(idCaso, "IndiceElectronicoXML",
					UtilDominios.TIPO_ARCHIVO_XML, is, documentoEntity, UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null);
		} catch (Exception e) {			
			LOGGER.error(e.getMessage());
		}
		//
		String xsd =convetirObjetoAXSD(documentoFolio);

		bytes = xsd.getBytes();
		is = new ByteArrayInputStream(bytes);
		documentoEntity = new Documento();
		documentoEntity.setIdCaso(idCaso);
		documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_XSD);
		documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_INDICE_ELECTRONICO);
		documentoEntity.setNombre("IndiceElectronicoXSD");
		documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoEntity.setFechaUltimaModificacion(new Date());
		documentoEntity.setDescripcion("IndiceElectronicoXSD");
		documentoEntity.setIdCarpeta(carpeta.getIdCarpeta());
		documentoEntity.setNuevo(true);
		try {
			almacenamientoDocumentosFacade.guardarDocumento(idCaso, "IndiceElectronicoXSD",
			UtilDominios.TIPO_ARCHIVO_XSD, is, documentoEntity, UtilConstantes.USUARIO_DEFECTO_SIMASC, null, null);
			} catch (Exception e) {			
			LOGGER.error(e.getMessage());
		}
	}
	
	private void generarPDFIndiceElectronico(Long idCaso,List<DocumentoIndiceElectronicoDTO> indiceElectronico, Boolean firma) {
		PlantillaIndicePDF indicePDF = new PlantillaIndicePDF();
		String contenidoDocumento = indicePDF.getPlantillaPDF(indiceElectronico);
		
		RadicacionSolicitudPDF radicacionSolicitudPDF = new RadicacionSolicitudPDF();
		
		String tempPathHtmlFile = contexto.getRealPath("/") + RadicacionSolicitudPDF.RESOURCE + "page1.html";
		String tempPathPdfFile = contexto.getRealPath("/") + RadicacionSolicitudPDF.RESOURCE + "page1.pdf";
		radicacionSolicitudPDF.createHtmlToexport(contenidoDocumento, tempPathHtmlFile);
		radicacionSolicitudPDF.generatePDFFromHtml(tempPathHtmlFile, tempPathPdfFile);
	
		try {		
			byte[] bytes;	
			if(firma) {
				bytes = clienteFirmaDigital.firmarPDF(tempPathPdfFile, "", null, null, false);
			}else {
				File file = new File(tempPathPdfFile);
				bytes = Files.readAllBytes(file.toPath());
			}
			InputStream is = new ByteArrayInputStream(bytes);
			Documento documentoEntity = new Documento();
			Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(idCaso, 1L);
			documentoEntity.setIdCarpeta(carpeta.getIdCarpeta());
			documentoEntity.setIdCaso(idCaso);
			documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
			documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_INDICE_ELECTRONICO);
			documentoEntity.setNombre(UtilConstantes.NOMBRE_DOCUMENTO_INDICE_ELECTRONICO + idCaso);
			documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			documentoEntity.setFechaUltimaModificacion(new Date());
			documentoEntity.setDescripcion("");
			documentoEntity.setNuevo(true);	
			almacenamientoDocumentosFacade.guardarDocumento(idCaso,
					UtilConstantes.NOMBRE_DOCUMENTO_INDICE_ELECTRONICO + idCaso,
					UtilDominios.TIPO_ARCHIVO_PDF, is, documentoEntity, UtilConstantes.USUARIO_DEFECTO_SIMASC,
					null, null);
		} catch (SimascNegocioPruebaException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	private static String convetirObjetoAXml(TipoDocumentoFoliadoDTO tipoDocumentoFoliado) {
	    String xmlString = "";
	    try {
	        JAXBContext context = JAXBContext.newInstance(TipoDocumentoFoliadoDTO.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
	        m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");

	        StringWriter sw = new StringWriter();
	        m.marshal(tipoDocumentoFoliado, sw);
	        
	        xmlString = sw.toString();

	    } catch (JAXBException e) {
	        LOGGER.error(e.getMessage());
	    }

	    return xmlString;
	}
	
	
	private static String convetirObjetoAXSD(TipoDocumentoFoliadoDTO tipoDocumentoFoliado) {
		final File xsdFile = new File("example.xsd");
		String xsd = "";
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(TipoDocumentoFoliadoDTO.class);
		try {
		jaxbContext.generateSchema(new SchemaOutputResolver() {

		@Override
		public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
		StreamResult result = new StreamResult(new FileOutputStream(xsdFile));
		result.setSystemId(xsdFile.getAbsolutePath());
		return result;
		}
		});
		xsd = printFile(xsdFile);
		} catch (IOException e) {		
			LOGGER.error(e.getMessage());
		}
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage());
		}
		return xsd;
	}

	public static String printFile(File file) throws IOException {
		String xsd = "";
		BufferedReader reader = null; 
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = null;			
			while ((line = reader.readLine()) != null) {
			xsd = xsd + line + "\n";
			}
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if(reader != null){
				reader.close();
			}			
		}
		return xsd;
	}
	
}
 