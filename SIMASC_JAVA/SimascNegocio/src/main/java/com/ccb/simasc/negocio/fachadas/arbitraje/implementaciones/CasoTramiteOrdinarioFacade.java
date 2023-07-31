package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWFirmaDigital;
import com.ccb.simasc.integracion.manejadores.ManejadorCarpeta;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDetallePagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICarpetaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoTramiteOrdinarioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILoteGeneradoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPlantillaCartaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUsuarioFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.NotificacionPagoDTO;
import com.ccb.simasc.transversal.dto.PagoCasoDTO;
import com.ccb.simasc.transversal.dto.PersonaSolicitudDTO;
import com.ccb.simasc.transversal.dto.formularios.CondicionesGeneralesDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioParteDTO;
import com.ccb.simasc.transversal.dto.formularios.RadicarCasoConvenioDTO;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.LoteGenerado;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.HttpGetWithEntity;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import co.org.ccb.simasc.comun.pdf.RadicacionSolicitudPDF;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class CasoTramiteOrdinarioFacade implements ICasoTramiteOrdinarioFacade {

	private static final Logger logger = LogManager.getLogger(CasoTramiteOrdinarioFacade.class.getName());

	@Resource
	private UserTransaction userTransaction;

	// Fachadas de negocio

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private ICarpetaFacade carpetaFacade;

	@EJB
	private IEventoFacade eventoFacade;

	@EJB
	private IPersonaSolicitudFacade personaSolicitudFacade;

	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;

	@EJB
	private IRepartoSvc repartoSvc;

	@EJB
	private ILoteGeneradoFacade loteGeneradoFacade;

	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private IPlantillaCartaFacade plantillaCartaFacade;

	@EJB
	private IAlertaFacade alertaFacade;

	@EJB
	private IUsuarioFacade usuarioFacade;

	@EJB
	private ISeguridadFacade seguridadFacade;

	@EJB
	private IPersonaFacade personaFacade;

	// Manejadores

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorPagoCaso manejadorPagoCaso;

	@EJB
	private ManejadorDetallePagoCaso manejadorDetallePagoCaso;

	@EJB
	private ManejadorServicio manejadorServicio;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	// Utilidades

	@EJB
	private RadicacionSolicitudPDF radicacionSolicitudPDF;

	@EJB
	private IClienteSWFirmaDigital clienteFirmaDigital;
	
	@EJB
	private IPagoCasoFacade pagoCasoFacade;
	
	@EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 
	
	@EJB
	private ManejadorCarpeta manejadorCarpeta;
	
	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;

	private List<Documento> documentosSolicitud;

	@Context
	private ServletContext contexto;

	@Override
	public NotificacionPagoDTO crearPagoCasoTramiteOrdinario(PagoCasoDTO pagoCasoDTO, String realPath) {
		Caso caso = null;
		NotificacionPagoDTO notificacionPagoDTO = null;
		SolicitudServicio solicitudActual;
		List<SolicitudServicio> solicitudes = manejadorSolicitudServicio
				.consultarSolicitudPorOrden(pagoCasoDTO.getIdOrdenDePago());

		if (solicitudes.isEmpty()) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR585.toString()));
		} else {
			solicitudActual = solicitudes.get(0);
		}

		try {
			userTransaction.begin();

			PagoCaso pagoCaso = new PagoCaso();
			pagoCaso.setNumeroRecibo(pagoCasoDTO.getNumeroRecibo());
			pagoCaso.setValor(pagoCasoDTO.getValor());
			pagoCaso.setFechaPago(new Date());
			pagoCaso.setNombrePersona(pagoCasoDTO.getNombrePersona());
			pagoCaso.setTipoDeDocumento(pagoCasoDTO.getTipoDeDocumento());
			pagoCaso.setNumeroDeDocumento(pagoCasoDTO.getNumeroDeDocumento());
			pagoCaso.setIdServicio(solicitudActual.getIdServicio());
			pagoCaso.setIdSede(pagoCasoDTO.getIdSede());
			pagoCaso.setIdUsuarioModificacion(pagoCasoDTO.getIdUsuarioModificacion());
			pagoCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			pagoCaso.setFechaUltimaModificacion(new Date());
			pagoCaso.setEstado(pagoCasoDTO.getEstado());
			pagoCaso.setDescripcion(pagoCasoDTO.getDescripcion());
			manejadorPagoCaso.crear(pagoCaso);

			pagoCasoFacade.creaDetallePagoCaso(pagoCaso, pagoCasoDTO);

			caso = crearCasoTramiteOrdinario(pagoCasoDTO.getIdUsuarioModificacion(), pagoCasoDTO, realPath,
					solicitudActual);
			if (solicitudActual.getIdCasoAnterior() != null
					&& caso.getIdServicio() == UtilConstantes.ID_SERVICIO_RECUPERACION_EMPRESARIAL) {

				List<RolPersonaCaso> lista = manejadorRolPersonaCaso
						.consultarPorIdCasoyIdRol(solicitudActual.getIdCasoAnterior(), UtilDominios.ROL_MEDIADOR);
				if (lista != null && !lista.isEmpty()) {
					for (RolPersonaCaso obj : lista) {

						RolPersonaCaso rolPersonaCasoNuevo = new RolPersonaCaso();
						rolPersonaCasoNuevo.setEstado(obj.getEstado() != null ? obj.getEstado() : "");
						rolPersonaCasoNuevo.setIdUsuarioModificacion(
								obj.getIdUsuarioModificacion() != null ? obj.getIdUsuarioModificacion() : "");
						rolPersonaCasoNuevo.setFechaUltimaModificacion(
								obj.getFechaUltimaModificacion() != null ? obj.getFechaUltimaModificacion()
										: new Date());
						rolPersonaCasoNuevo
								.setEstadoRegistro(obj.getEstadoRegistro() != null ? obj.getEstadoRegistro() : "");
						RolPersonaCasoPK pK = new RolPersonaCasoPK();
						pK.setIdCaso(caso.getIdCaso());
						pK.setIdPersona(obj.getRolPersonaCasoPK().getIdPersona());
						pK.setIdRol(obj.getRolPersonaCasoPK().getIdRol());
						rolPersonaCasoNuevo.setRolPersonaCasoPK(pK);
						rolPersonaCasoFacade.crear(rolPersonaCasoNuevo);
					}
				}
			}

			pagoCaso.setIdCaso(caso.getIdCaso());
			manejadorPagoCaso.actualizar(pagoCaso);

			notificacionPagoDTO = new NotificacionPagoDTO();
			notificacionPagoDTO.setCaso(caso);
			notificacionPagoDTO.setPagoCaso(pagoCaso);
		} catch (Exception e) {
			logger.error("Error: ", e);
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				logger.error("Error: ", e1);
			}
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}

		return notificacionPagoDTO;
	}

	/**
	 * Crea el tramite de un caso en conciliacion
	 * 
	 * @param idOrdenPago
	 * @param idUsuario
	 * @return
	 * @throws Exception
	 */
	private Caso crearCasoTramiteOrdinario(String idUsuario, PagoCasoDTO pagoCaso, String realPath,
			SolicitudServicio solicitudActual) throws Exception {
		Caso caso = null;

		try {
			caso = migracionSolicitudServicioACaso(idUsuario, solicitudActual);			
			casoFacade.generarNombreCaso(caso.getIdCaso());
			carpetaFacade.generarCuadernos((caso.getIdCaso()));

			Servicio servicioDelCaso = manejadorServicio.buscar(caso.getIdServicio());			
			if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(servicioDelCaso.getTipo()) ||
			UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(servicioDelCaso.getTipo())) {
				
				

				Rol rolReparto = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ABOGADO);
				rolPersonaCasoFacade.reparto(caso, rolReparto);

				documentoFacade.migraDocumentosSolicitudCaso(solicitudActual.getIdSolicitudServicio(),
						caso.getIdCaso());


				userTransaction.commit();

				List<RolPersonaCaso> rolPersonaCasoList = rolPersonaCasoFacade.consultarPartesCaso(caso.getIdCaso());

				usuarioFacade.crearUsuarioParteConNotificacion(caso, rolPersonaCasoList);				
				documentoFacade.actualizaCarpetaDocumento(caso.getIdCaso());
				
				asociarReciboPagoConCaso(pagoCaso.getNumeroRecibo(),caso.getIdCaso());							
				
				documentoFacade.generaDocumentosNotificacion(caso, rolPersonaCasoList, realPath);

			} else {

				String nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;

				documentoFacade.migraDocumentosSolicitudCaso(solicitudActual.getIdSolicitudServicio(),
						caso.getIdCaso());

				RadicarCasoConvenioDTO radicacion = crearObjetoRadicacion(caso);

				// guardado documento radicacion de caso
				guardarDocumentoRadicacion(radicacion, caso, nombreUsuario, realPath);

				documentoFacade.actualizaCarpetaDocumento(caso.getIdCaso());

				userTransaction.commit();

				if (!caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)) {

					try {
						userTransaction.begin();

						llamarReparto(radicacion, nombreUsuario);

						userTransaction.commit();
					} catch (SIMASCNegocioExcepcion e) {
						userTransaction.rollback();
						caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);
						manejadorCaso.actualizar(caso);
						repartoSvc.fallaReparto(caso.getIdCaso(), e.getMessage(), nombreUsuario);
					} catch (Exception e) {
						userTransaction.rollback();
						caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);
						manejadorCaso.actualizar(caso);
						logger.error("Error: ", e);
						Throwable causa = ExceptionUtils.getRootCause(e);
						repartoSvc.fallaReparto(caso.getIdCaso(),
								causa != null ? causa.getMessage() : ExceptionUtils.getRootCauseMessage(e),
								nombreUsuario);
					}
				}
			}
			
		} catch (SIMASCNegocioExcepcion e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				logger.error("Error: ", e1);
			}
			logger.error("Error: ", e);
			throw new Exception(e.getMessage());
		}
		return caso;

	}

	/**
	 * Radica un caso a partir de una solicitud
	 * 
	 * @param idOrdenPago
	 * @param idUsuario
	 * @return
	 */
	private Caso migracionSolicitudServicioACaso(String idUsuario, SolicitudServicio solicitudActual) {

		Servicio servicio = manejadorServicio.buscar(solicitudActual.getIdServicio());
		Caso casoActual = new Caso();
		casoActual.setIdSolicitudServicio(solicitudActual.getIdSolicitudServicio());
		casoActual.setTipoCuantia(solicitudActual.getTipoCuantia());
		if (!UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(solicitudActual.getTipoCuantia()))
			casoActual.setValorPretensiones(solicitudActual.getCuantia().trim());
		casoActual.setIdServicio(solicitudActual.getIdServicio());
		casoActual.setIdMateria(solicitudActual.getIdMateria());
		casoActual.setFechaUltimaModificacion(new Date());
		casoActual.setIdUsuarioModificacion(idUsuario);
		casoActual.setFechaRadicacion(new Date());
		casoActual
				.setEstadoCaso(solicitudActual.getIdServicio().equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
						? UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION
						: UtilDominios.ESTADO_CASO_CREADO);
		casoActual.setEtapa(UtilDominios.ETAPAS_CONCILIACION);
		casoActual.setFechaEstado(new Date());
		casoActual.setAmparoDePobreza(false);
		casoActual.setPendienteDePago(false);
		casoActual.setTipoRadicacion(UtilDominios.TIPO_RADICACION_VIRTUAL);
		casoActual.setIdSede(solicitudActual.getIdSede());
		casoActual.setInicioDeConflicto(solicitudActual.getInicioDeConflicto());
		casoActual.setParteQueSolicitaServicio(solicitudActual.getParteQueSolicitaServicio());
		casoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		casoActual.setIdLugarDelConflicto(solicitudActual.getIdLugarConflicto());

		casoActual.setMedidasCautelares(solicitudActual.isMedidasCautelares());
		casoActual.setPagoMediacion(solicitudActual.getPagoMediacion());
		casoActual.setIdCasoAnterior(solicitudActual.getIdCasoAnterior());
		casoActual.setTipoTramite(solicitudActual.getTipoTramite());
		casoActual.setArbitrajeConsumo(solicitudActual.isArbitrajeConsumo());
		casoActual.setTipoConflicto(solicitudActual.getTipoConflicto());
		casoActual.setEnteroServicio(solicitudActual.getEnteroServicio());

		String rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE;
		if (UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA.equals(servicio.getTipo()) || 
			UtilDominios.TIPO_SERVICIO_INTERNACIONAL.equals(servicio.getTipo())) {
			casoActual.setEtapa(UtilDominios.ETAPA_CASO_PREARBITRAL);
		} else if (UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equals(servicio.getTipo()) ||
		UtilDominios.TIPO_SERVICIO_CONVIVENCIA.equals(servicio.getTipo())) {
			casoActual.setHechos(solicitudActual.getHechos());
			casoActual.setPretensiones(solicitudActual.getPretensiones());
			rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE_CON;
			if(solicitudActual.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {
				logger.info("Asigna consulta rol partes equidad");
				rolParte = UtilDominios.DOMINIO_ROL_PERSONA_PARTE_EQUIDAD;
			}
		}

		manejadorCaso.crear(casoActual);
		List<String> args = new ArrayList<>();
		args.add(casoActual.getIdCaso().toString());
		
		String eventoRadicacion = (String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO250.toString()), args.toArray()));
		eventoFacade.registrarEvento(casoActual.getIdCaso(), UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO,
				eventoRadicacion, idUsuario);

		List<String> roles = manejadorDominio.consultarCodigosDominio(rolParte);
		
		List<PersonaSolicitud> personasSolicitud = (List<PersonaSolicitud>) new PersonaSolicitudDTO()
				.transformarColeccionEntidadesConDependencias(manejadorPersonaSolicitud
						.consultarPersonasSolicitud(solicitudActual.getIdSolicitudServicio(), roles, true));

		// migracion de las persona solicitud de un caso
		if (!personasSolicitud.isEmpty()) {
			personaSolicitudFacade.migracionPersonaSolicitudARolPersonaCaso(personasSolicitud, idUsuario,
					casoActual.getIdCaso());
		}

		return casoActual;

	}

	/**
	 * 
	 * 
	 * @param caso
	 * @return
	 */
	private RadicarCasoConvenioDTO crearObjetoRadicacion(Caso caso) {
		SolicitudServicio solicitud = manejadorSolicitudServicio.buscar(caso.getIdSolicitudServicio());
		List<FormularioParteDTO> partes = personaSolicitudFacade
				.consultarPartesSolicitudServicio(caso.getIdSolicitudServicio());
		RadicarCasoConvenioDTO radicacion = new RadicarCasoConvenioDTO();
		radicacion.setCaso(new Caso());
		radicacion.setParteInfo(partes);

		radicacion.getCaso().setIdCaso(caso.getIdCaso());
		radicacion.getCaso().setHechos(caso.getHechos());
		radicacion.getCaso().setPretensiones(caso.getPretensiones());
		radicacion.getCaso().setInicioDeConflicto(caso.getInicioDeConflicto());
		radicacion.getCaso().setIdLugarDelConflicto(caso.getIdLugarDelConflicto());
		radicacion.getCaso().setParteQueSolicitaServicio(caso.getParteQueSolicitaServicio());
		radicacion.getCaso().setIdMateria(caso.getIdMateria());
		radicacion.getCaso().setIdSede(caso.getIdSede());
		radicacion.getCaso().setEstadoCaso(caso.getEstadoCaso());
		radicacion.getCaso().setTipoCuantia(caso.getTipoCuantia());

		if (!UtilDominios.TIPO_CUANTIA_CONCILIACION_INDETERMINADO.equals(caso.getTipoCuantia()))
			radicacion.setCuantia(Long.valueOf(caso.getValorPretensiones().trim()));
		List<PersonaSolicitud> personaSolicitudList = manejadorPersonaSolicitud
				.consultarPersonaSolicitud(caso.getIdSolicitudServicio(), null, UtilDominios.ROL_PERSONA_CONCILIADOR);

		PersonaSolicitud personaSolicitud = null;
		if (!personaSolicitudList.isEmpty()) {
			personaSolicitud = personaSolicitudList.get(0);
		}

		if (personaSolicitud != null)
			radicacion.setConciliador(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
		radicacion.setTipoAudiencia(solicitud.getTipoDeAudiencia());
		radicacion.setFechaAudiencia(solicitud.getFechaInicioAudiencia());		

		if (solicitud.getFechaInicioAudiencia() != null) {
			Calendar horas = Calendar.getInstance();
			horas.setTime(solicitud.getFechaInicioAudiencia());

			radicacion.setFechaHoraInicio(
					horas.get(Calendar.HOUR) != UtilConstantes.CERO ? solicitud.getFechaInicioAudiencia() : null);
			radicacion.setFechaHoraFin(
					horas.get(Calendar.HOUR) != UtilConstantes.CERO ? solicitud.getFechaFinAudiencia() : null);
		}
		
		CondicionesGeneralesDTO condiciones = new CondicionesGeneralesDTO();		
		condiciones.setCantAcreedor(solicitud.getCantAcreedor()!= null ? solicitud.getCantAcreedor() : "");
		condiciones.setCantDeuda(solicitud.getCantDeuda()!= null ? solicitud.getCantDeuda() : "");
		condiciones.setDomicilio(solicitud.getDomicilio()!= null ? solicitud.getDomicilio() : "");
		condiciones.setSaldoCapital(solicitud.getSaldoCapital()!= null ? solicitud.getSaldoCapital() : "");
		condiciones.setTipoPersona(solicitud.getTipoPersona()!= null ? solicitud.getTipoPersona() : "");
		condiciones.setValorMora(solicitud.getValorMora()!= null ? solicitud.getValorMora() : "");		
		radicacion.setCondicionesGeneralesDTO(condiciones);
		

		return radicacion;
	}

	/**
	 * Método que realiza el proceso de creacion y almacenamiento del documento de
	 * radicación del caso
	 * 
	 * @param radicarCasoConvenio
	 * @param casoRetorno
	 */
	private List<Long> guardarDocumentoRadicacion(RadicarCasoConvenioDTO radicarCasoConvenio, Caso casoRetorno,
		String nombreUsuario, String realPath) {
		int intentos = 3;
		List<Long> documentos = new ArrayList<Long>();
		InputStream is = null;

		List<Documento> documentoList = documentoFacade.consultarPorCaso(casoRetorno.getIdCaso());

		casoRetorno.setDocumentoList(documentoList);
		
		
		String nombreDocumento = UtilConstantes.NOMBRE_DOCUMENTO_TRAMITE_ORDINARIO;
		if(casoRetorno !=null && casoRetorno.getIdServicio()!= null && casoRetorno.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
			nombreDocumento = UtilConstantes.NOMBRE_DOCUMENTO_TRAMITE_INSOLVENCIA;
		}

		radicarCasoConvenio.setCaso(casoRetorno);
		Documento documentoEntity = new Documento();
		documentoEntity.setIdCaso(casoRetorno.getIdCaso());
		documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
		documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_RADICACION_CASO);
		documentoEntity.setNombre(nombreDocumento);
		documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoEntity.setFechaUltimaModificacion(new Date());
		documentoEntity.setDescripcion(nombreDocumento);
		documentoEntity.setNuevo(true);

		String[] datosDocumentos = (String[]) radicacionSolicitudPDF.generarPDFRadicacion(radicarCasoConvenio, realPath)
				.toArray();
		while (intentos > 0) {
			try {
				byte[] bytes = clienteFirmaDigital.firmarPDF(datosDocumentos[0], datosDocumentos[1], null, null, false);
				is = new ByteArrayInputStream(bytes);
				documentos = almacenamientoDocumentosFacade.guardarDocumento(casoRetorno.getIdCaso(),
						nombreDocumento, UtilDominios.TIPO_ARCHIVO_PDF, is,
						documentoEntity, nombreUsuario, null, null);
				intentos = 0;
			} catch (Exception e) {
				if (intentos == 1) {
					logger.error(e.getMessage());
					throw new SIMASCNegocioExcepcion(
							MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR086.toString()));
				} else {
					intentos -= 1;
				}
			} finally {
				try {
					if (is != null)
						is.close();
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}

		}

		return documentos;
	}

	/**
	 * Método que realiza el proceso de reparto para la radicacion del caso de
	 * convenio
	 * 
	 * @param radicarCasoConvenio
	 * @param nombreUsuario
	 * @throws Exception
	 */
	public void llamarReparto(RadicarCasoConvenioDTO radicarCasoConvenio, String nombreUsuario) throws Exception {
		DatosEntradaRepartoDTO datos = new DatosEntradaRepartoDTO();
		datos.setIdCaso(radicarCasoConvenio.getCaso().getIdCaso());
		datos.setIdSede(radicarCasoConvenio.getCaso().getIdSede());
		datos.setIdConciliador(radicarCasoConvenio.getConciliador());
		datos.setFechaAudiencia(radicarCasoConvenio.getFechaAudiencia());
		datos.setHoraAudiencia(radicarCasoConvenio.getFechaHoraInicio());
		datos.setUsuario(nombreUsuario);
		Rol rolConciliador = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR);
		datos.setIdRol(rolConciliador.getIdRol());
				
		repartoSvc.reparto(datos);
	}
	/**
	 * Método que realiza el proceso de reparto para la radicacion del caso de
	 * convenio
	 * 
	 * @param radicarCasoConvenio
	 * @param nombreUsuario
	 * @throws Exception
	 */
	@Override
	public void llamarRepartoEquidad(RadicarCasoConvenioDTO radicarCasoConvenio, String nombreUsuario) throws Exception {
		
		DatosEntradaRepartoDTO datos = new DatosEntradaRepartoDTO();
		datos.setIdCaso(radicarCasoConvenio.getCaso().getIdCaso());
		datos.setIdSede(radicarCasoConvenio.getCaso().getIdSede());
		datos.setIdConciliador(radicarCasoConvenio.getConciliador());
		datos.setFechaAudiencia(radicarCasoConvenio.getFechaAudiencia());
		datos.setHoraAudiencia(radicarCasoConvenio.getFechaHoraInicio());
		datos.setUsuario(nombreUsuario);
		Rol rolConciliador = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD);
		datos.setIdRol(rolConciliador.getIdRol());
		logger.info(" Entra a  llamarRepartoEquidad");		
		repartoSvc.repartoEquidad(datos);
	}

	@Override
	public void actualizarFalloLotes(Long idLote) {
		try {
			userTransaction.begin();
			LoteGenerado lote = loteGeneradoFacade.buscar(idLote);
			lote.setEstadoGeneracion(UtilDominios.ESTADO_LOTE_FINALIZADO_ERROR);
			loteGeneradoFacade.actualizarSinAtributosDeAuditoria(lote);
			userTransaction.commit();
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				logger.error("Error: ", e1);
			}
			logger.error("Error: ", e);
		}
	}

	@Override
	public void eliminarRegistroGeneracion(Long idLote) {
		try {
			userTransaction.begin();
			loteGeneradoFacade.borrarLoteGenerado(idLote);
			userTransaction.commit();
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				logger.error("Error: ", e1);
			}
			logger.error("Error: ", e);
		}
	}

	@Override
	public Long crearPagoCasoRecuperacionEmpresarial(Long idSolicitud) {
		Caso caso = null;
		String realPath = null;

		SolicitudServicio solicitudActual = manejadorSolicitudServicio.buscar(idSolicitud);
		
		if (solicitudActual.getIdSolicitudServicio() == null) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR585.toString()));
		}

		try {
			userTransaction.begin();
			caso = crearCasoTramiteOrdinario(UtilConstantes.USUARIO_DEFECTO_SIMASC, realPath, solicitudActual);
			if (solicitudActual.getIdCasoAnterior() != null) {

				List<RolPersonaCaso> lista = manejadorRolPersonaCaso
						.consultarPorIdCasoyIdRol(solicitudActual.getIdCasoAnterior(), UtilDominios.ROL_MEDIADOR);
				if (lista != null && !lista.isEmpty()) {
					for (RolPersonaCaso obj : lista) {
						RolPersonaCaso rolPersonaCasoNuevo = new RolPersonaCaso();
						rolPersonaCasoNuevo.setEstado(obj.getEstado() != null ? obj.getEstado() : "");
						rolPersonaCasoNuevo.setIdUsuarioModificacion(
								obj.getIdUsuarioModificacion() != null ? obj.getIdUsuarioModificacion() : "");
						rolPersonaCasoNuevo.setFechaUltimaModificacion(
								obj.getFechaUltimaModificacion() != null ? obj.getFechaUltimaModificacion()
										: new Date());
						rolPersonaCasoNuevo
								.setEstadoRegistro(obj.getEstadoRegistro() != null ? obj.getEstadoRegistro() : "");
						RolPersonaCasoPK pK = new RolPersonaCasoPK();
						pK.setIdCaso(caso.getIdCaso());
						pK.setIdPersona(obj.getRolPersonaCasoPK().getIdPersona());
						pK.setIdRol(obj.getRolPersonaCasoPK().getIdRol());
						rolPersonaCasoNuevo.setRolPersonaCasoPK(pK);
						rolPersonaCasoFacade.crear(rolPersonaCasoNuevo);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			}
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}

		return caso.getIdCaso();
	}

	private Caso crearCasoTramiteOrdinario(String idUsuario, String realPath, SolicitudServicio solicitudActual)
			throws Exception {
		Caso caso = null;

		try {
			caso = migracionSolicitudServicioACaso(idUsuario, solicitudActual);
			casoFacade.generarNombreCaso(caso.getIdCaso());
			carpetaFacade.generarCuadernos(caso.getIdCaso());

			Rol rolReparto = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_ABOGADO);

			if(caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
				rolPersonaCasoFacade.repartoInsolvencia(caso);
			}else if(caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)) {
				logger.info("empieza llamar reparto equidad");
				String nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;

				RadicarCasoConvenioDTO radicacion = crearObjetoRadicacion(caso);
				

				// guardado documento radicacion de caso
				guardarDocumentoRadicacion(radicacion, caso, nombreUsuario, realPath);

				documentoFacade.actualizaCarpetaDocumento(caso.getIdCaso());

				userTransaction.commit();

				
					try {
						userTransaction.begin();
						
						llamarRepartoEquidad(radicacion, nombreUsuario);

						//userTransaction.commit();
					} catch (SIMASCNegocioExcepcion e) {
						userTransaction.rollback();
						caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);
						manejadorCaso.actualizar(caso);
						repartoSvc.fallaReparto(caso.getIdCaso(), e.getMessage(), nombreUsuario);
					} catch (Exception e) {
						userTransaction.rollback();
						caso.setEstadoCaso(UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);
						manejadorCaso.actualizar(caso);
						logger.error(e.getMessage());
						Throwable causa = ExceptionUtils.getRootCause(e);
						repartoSvc.fallaReparto(caso.getIdCaso(),
								causa != null ? causa.getMessage() : ExceptionUtils.getRootCauseMessage(e),
								nombreUsuario);
					}
				
			}else {
				rolPersonaCasoFacade.reparto(caso, rolReparto);
			}
			
			logger.info("termino proceso  llamar reparto equidad");
			documentoFacade.migraDocumentosSolicitudCaso(solicitudActual.getIdSolicitudServicio(), caso.getIdCaso());
			logger.info("termino proceso  migraDocumentosSolicitudCaso");
			documentoFacade.actualizaCarpetaDocumento(caso.getIdCaso());
			logger.info("termino proceso  actualizaCarpetaDocumento");
			userTransaction.commit();
			if(!caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_EQUIDAD)){
				
				List<RolPersonaCaso> rolPersonaCasoList = rolPersonaCasoFacade.consultarPartesCaso(caso.getIdCaso());
	
				documentoFacade.generaDocumentosNotificacion(caso, rolPersonaCasoList, contexto.getRealPath("/"));
				
				if(caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_INSOLVENCIA)) {
					String nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
	
					RadicarCasoConvenioDTO radicacion = crearObjetoRadicacion(caso);
	
					// guardado documento radicacion de caso
					guardarDocumentoRadicacion(radicacion, caso, nombreUsuario, realPath);
	
				}
				
					usuarioFacade.crearUsuarioParteConNotificacion(caso, rolPersonaCasoList);
			}

		} catch (SIMASCNegocioExcepcion e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				throw new Exception(e.getMessage());
			}
			logger.error("Error: ", e);
			throw new Exception(e.getMessage());
		}
		return caso;

	}
	
	public void asociarReciboPagoConCaso(String numeroRecibo ,Long idCaso) {
		
		try {
			obtenerReciboPago(numeroRecibo, idCaso);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void obtenerReciboPago(String numeroRecibo ,Long idCaso) throws IOException {
		ParametrosGenerales parametro = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_RECIBO_PAGO);

		String url = parametro.getValorTexto() +"/"+numeroRecibo+"/servicioNegocio/0513";        

        try {

        	CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGetWithEntity httpGet = new HttpGetWithEntity(url);
			
			String json = "{\"numeroRecibo\":\"" + numeroRecibo + "\",\"tipoDocumento\":\""+"0513" +"\"}";
			
			StringEntity params = new StringEntity(json ,"UTF-8");
			params.setContentEncoding("Accept-Encoding: gzip, deflate, br");
			params.setContentType("application/json; charset=UTF-8");
					
			httpGet.setEntity(params); 
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");

        	HttpResponse httpresponse = httpClient.execute(httpGet);
        	int codigoRespuesta = httpresponse.getStatusLine().getStatusCode();
        	String resul = new BasicResponseHandler().handleResponse(httpresponse);
        	
        	if(codigoRespuesta == 200) {
            	
            	Gson gson = new Gson();
            	JsonElement element = gson.fromJson(resul, JsonElement.class);
            	JsonObject jsonObj = element.getAsJsonObject();
            	JsonObject recibo = jsonObj.get("recibo").getAsJsonObject();
            	String archivoBase64 = recibo.get("archivo").getAsString();
            	

                byte[] decoder = Base64.getDecoder().decode(archivoBase64);
                InputStream is = null;
	         	 is = new ByteArrayInputStream(decoder);	
	         	 
	         	
	         	guardarDocumento(idCaso ,is);
        	}else {
        		logger.info("NO HAY DOCUMENTO CON ESE NUMERO DE RECIBO: "+numeroRecibo);
        	}

    	} catch (Exception e) {
			logger.error("Exception in NetClientGet:- " + e.getMessage());
    	}

	}
	
	public void guardarDocumento( Long idCaso ,InputStream is ) throws KeyManagementException, NoSuchAlgorithmException {
		Documento documentoEntity = new Documento();
		Carpeta carpeta = manejadorCarpeta.obtenerCarpetaCuadernoCaso(idCaso, 1L);
		documentoEntity.setIdCarpeta(carpeta.getIdCarpeta());
		documentoEntity.setIdCaso(idCaso);
		documentoEntity.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_PDF);
		documentoEntity.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_INDICE_ELECTRONICO);
		documentoEntity.setNombre("reciboPago-" + idCaso);
		documentoEntity.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		documentoEntity.setFechaUltimaModificacion(new Date());
		documentoEntity.setDescripcion("");
		documentoEntity.setNuevo(true);	
		documentoEntity.setFechaRadicacion(new Date());
		documentoEntity.setFechaCargue(new Date());
		String pathDocumento = gestorDocumentalFacade.subirDocumento(idCaso, documentoEntity.getIdCarpeta(), is,
				documentoEntity.getNombre(), UtilDominios.TIPO_ARCHIVO_PDF, null);
		
		File file = new File(pathDocumento);
		
		String url = null;
		try {
			url = guardarDocumentoEnS3(file,idCaso);
			documentoEntity.setUrl(url);
			manejadorDocumento.crear(documentoEntity);
			gestorDocumentalFacade.eliminarDocumento(pathDocumento);
		} catch (IOException e) {
			
			gestorDocumentalFacade.eliminarDocumento(pathDocumento);			
			logger.error(e.getMessage());
		}		
	}
	

	public String guardarDocumentoEnS3(File file , Long idCaso) throws IOException, NoSuchAlgorithmException, KeyManagementException {

		String token = almacenamientoDocumentosFacade.getClientCredentials();
		ParametrosGenerales parametro = parametrosGeneralesFacade.consultarPorCodigo(UtilConstantes.PARAMETRO_CODIGO_URL_SERV_SAVE);
		
		SSLContext sc = SSLContext.getInstance("TLSv1.2");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		
		try (CloseableHttpClient httpclient = HttpClients.custom().setSSLContext(sc).build();) {
		
			HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
		                .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName())
		                .addTextBody("filecase", idCaso.toString(), ContentType.DEFAULT_BINARY).build();
		       

            // build http request and assign multipart upload data
            HttpUriRequest request = RequestBuilder.post(parametro.getValorTexto()).setEntity(data).build();
            request.addHeader("Authorization", "Bearer " + token);
		
            // Create a custom response handler
            ResponseHandler < String > responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				    int status = response.getStatusLine().getStatusCode();
				    if (status >= 200 && status < 300) {
				    	String result = new BasicResponseHandler().handleResponse(response);
				    
				    	 return result != null ? result : null;
				    } else {
				        throw new ClientProtocolException("Unexpected response status: " + status);
				    }
				}
			};
			return httpclient.execute(request, responseHandler);
		 }
	}
}
