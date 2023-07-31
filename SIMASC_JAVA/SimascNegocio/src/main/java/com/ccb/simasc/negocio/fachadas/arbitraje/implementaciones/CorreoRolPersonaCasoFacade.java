package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

// Escriba en esta seccion sus modificaciones

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.UnresolvedAddressException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorAcuse;
import com.ccb.simasc.integracion.manejadores.ManejadorAdjunto;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCartaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorInvitado;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSede;
import com.ccb.simasc.integracion.manejadores.ManejadorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorValorPlantillaCarta;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICartaPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEnvioCorreoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.transversal.dto.AgendamientoDTO;
import com.ccb.simasc.transversal.dto.CartaPersonaDTO;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.NotificacionEnviadaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.EnvioCorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.formularios.InformacionCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;
import com.ccb.simasc.transversal.entidades.Adjunto;
import com.ccb.simasc.transversal.entidades.AdjuntoPK;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

import co.org.ccb.simasc.comun.correos.AdjuntoDTO;
import co.org.ccb.simasc.comun.correos.ArrayOfAdjuntoDTO;
import co.org.ccb.simasc.comun.correos.ArrayOfString;
import co.org.ccb.simasc.comun.correos.EnvioCorreoInDTO;
import co.org.ccb.simasc.comun.correos.EnvioCorreoServiceSoapProxy;

// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link CorreoRolPersonaCaso}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CorreoRolPersonaCasoFacade extends AccesoFacade<CorreoRolPersonaCaso, Long, CorreoRolPersonaCasoDTO> implements ICorreoRolPersonaCasoFacade {
	
	// protected region atributos on begin
	
	private static final Logger logger = LogManager.getLogger(CorreoRolPersonaCasoFacade.class.getName());// En esta seccion se deben incluir los atributos de la fachada

	private final String asuntoCorreoSecretarioReservas = "NotificaciÃ³n reserva sin sala";
	
	private static final String USUARIO_MODIFICACION = "USUARIO_MODIFICACION";

	private final String asuntoCorreoSolicitudCambioLista = "Solicitud de cambio de lista";
	
	private final String asuntoRespuestaSolicitudCambioLista = "Respuesta a solicitud de cambio de lista";
	
	private final String asuntoRepartoNoExitoso = "No fue posible realizar el reparto";

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorCartaPersona manejadorCartaPersona;

	@EJB
	private ManejadorInvitado manejadorInvitado;

	@EJB
	private ManejadorDocumento manejadorDocumento;	

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorAdjunto manejadorAdjunto;
	
	@EJB
	private ManejadorPlantillaCarta manejadorPlantillaCarta;
	
	@EJB
	private ManejadorValorPlantillaCarta manejadorValorPlantillaCarta; 

	@EJB
	private GestorDocumentalFacade gestorDocumentalFacade;
	
	@EJB
	private DominioFacade dominioFacade;
	
	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private ManejadorAcuse manejadorAcuse;
	
	@EJB
    private ICorreoElectronicoFacade correoElectronicoFacade; 
	
	@EJB
    private IDocumentoFacade documentoFacade; 
	
	@EJB
    private IParametrosGeneralesFacade parametrosGeneralesFacade; 
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;
	
	@EJB
	private EventoFacade eventoFacade;
	
	@EJB
	private ICartaPersonaFacade cartaPersonaFacade;
	
	@Inject
	private ApplicationRequestContext appContext;
	
	@EJB
	private ManejadorPersonaSede manejadorPersonaSede;
	
	@EJB
	private CasoFacade casoFacade;
	
	@EJB
	private IEnvioCorreoFacade envioCorreoFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorreoRolPersonaCaso;
	}

	@Override
	public CorreoRolPersonaCasoDTO transformarSinDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCasoDTO dto = new CorreoRolPersonaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CorreoRolPersonaCasoDTO transformarConDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCasoDTO dto = new CorreoRolPersonaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoRolPersonaCaso transformarEntidadConDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCaso dto = new CorreoRolPersonaCaso();
		dto = new CorreoRolPersonaCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoRolPersonaCaso transformarEntidadSinDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCaso dto = new CorreoRolPersonaCaso();
		dto = new CorreoRolPersonaCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public ParametrosEnvioCorreoDTO convertidorParametros(EnvioCorreoElectronicoDTO correo){
		List<String> cuerpoCorreo = new ArrayList<>();
		cuerpoCorreo.add(correo.getTexto());
		
		List<CorreoElectronicoDTO> destinatarios = new ArrayList<>();
		
		if(correo.getCorreosElectronicos() != null && !correo.getCorreosElectronicos().isEmpty()) {
			destinatarios.addAll(correo.getCorreosElectronicos());
		} else if(correo.getDestinatarios() != null && !correo.getDestinatarios().isEmpty()) {
			for(Long idDest : correo.getDestinatarios()){
				List<CorreoElectronico> correos = correoElectronicoFacade.consultaCorreosPersona(idDest);
				if(!correos.isEmpty()){
					List<CorreoElectronicoDTO> correosDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionConDependencias(correos, new ArrayList<CorreoElectronicoDTO>());
					destinatarios.add(correosDTO.get(0));
				}
			}			
		}
		
		
		List<DocumentoDTO> adjuntos = new ArrayList<DocumentoDTO>();
		 if(correo.getIdDocumentos()!=null)
	         for(Long idDocumento : correo.getIdDocumentos()){
	             Documento adjunto = documentoFacade.consultarPorId(idDocumento);            
	             adjuntos.add(documentoFacade.transformarSinDependencias(adjunto));
         }
		
		//Parametros
		ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
		parametrosCorreo.setAsunto(correo.getAsunto());
		parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
		parametrosCorreo.setRolPersonaCaso(destinatarios);
		parametrosCorreo.setIdAudiencia(correo.getIdAudiencia());
		
		//Remitente
		CorreoElectronicoDTO remitente = obtenerRemitenteCorreo(correo);
		
		parametrosCorreo.setRemitente(remitente);
		parametrosCorreo.setIdCaso(correo.getIdCaso());
		parametrosCorreo.setAdjuntos(adjuntos);
		parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		parametrosCorreo.setCertificado(correo.isCertificado());
		
		if (correo.getIdCarta() != null && correo.getIdPlantillaCarta() != null) {
			CartaPersonaDTO cartaPersonaDTO = new CartaPersonaDTO();
			cartaPersonaDTO.setIdCartaPersona(correo.getIdCarta());
			cartaPersonaDTO.setIdPlantillaCarta(correo.getIdPlantillaCarta());
			parametrosCorreo.setCartaPersona(cartaPersonaDTO);
		}
		
		return parametrosCorreo;
	}

	/**
	 * EnvÃ­a el correo electrÃ³nico a los destinatarios especificados (Personas asignadas a un caso,
	 * invitados o personas registradas en el sistema).
	 * 
	 * Si el correo es certificado se debe definir el id del caso. Para este tipo de correos se genera
	 * acuse en caso de error. Los correos certificados son almacenados en la entidad CorreoRolPersonaCaso, los
	 * no certificados no son almanacenados.
	 * 
	 * Se lanza excepciÃ³n si el correo es certificado y no define un identificador de caso o si 
	 * el no se definen correos receptores.
	 * 
	 * Se devuelve falso si hubo un fallo  en el envÃ­o del correo.
	 * 
	 * @author jcepeda: ModificaciÃ³n de la obtenciÃ³n de archivos adjuntos para
	 *         el envÃ­o de correo electrÃ³nico
	 * @date 31/05/2017
	 */
	@Override
	public boolean enviarCorreo(ParametrosEnvioCorreoDTO parametrosEnvio) throws SIMASCNegocioExcepcion {
				
		validarConsistenciaParametrosEnvioCorreo(parametrosEnvio);
		
		String tipoContenido = null;
		String llave = null;

		boolean falloEnvio = false;
		// Consultar parametros
		ParametrosGenerales parametroTipoCorreoTipoContenido = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.CORREO_TIPO_CONTENIDO);
		ParametrosGenerales parametroTipoCorreoLlave = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.CORREO_LLAVE);
		ParametrosGenerales parametroRutaGestorDocumental = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.GESTOR_DOCUMENTAL_RUTA_GESTOR);

		if (parametroTipoCorreoTipoContenido != null && parametroTipoCorreoTipoContenido.getValorTexto() != null
				&& !parametroTipoCorreoTipoContenido.getValorTexto().isEmpty()) {
			tipoContenido = parametroTipoCorreoTipoContenido.getValorTexto();
		}

		if (parametroTipoCorreoLlave != null && parametroTipoCorreoLlave.getValorTexto() != null
				&& !parametroTipoCorreoLlave.getValorTexto().isEmpty()) {
			llave = parametroTipoCorreoLlave.getValorTexto();
		}

		EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();

		EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
		// Se alimentan los valores parametrizables
		inDTO.setLlave(llave);
		inDTO.setTipoContenido(tipoContenido);

		// Remitente
		inDTO.setDe(parametrosEnvio.getRemitente().getDireccion());

	

		// Adjuntos
		ArrayOfAdjuntoDTO listaAdjuntos = obtenerAdjuntosCorreo(parametrosEnvio, parametroRutaGestorDocumental);
		inDTO.setAdjuntos(listaAdjuntos);

		// Destinos
		List<String> destinos = new ArrayList<>();

		if (parametrosEnvio.getRolPersonaCaso() != null)
			for (CorreoElectronicoDTO correos : parametrosEnvio.getRolPersonaCaso()) {
				if (correos.getDireccion() != null && !correos.getDireccion().isEmpty())
					destinos.add(correos.getDireccion());
			}
		if (parametrosEnvio.getInvitados() != null)
			for (CorreoElectronicoDTO invitado : parametrosEnvio.getInvitados()) {
				if (invitado.getDireccion() != null && !invitado.getDireccion().isEmpty())
					destinos.add(invitado.getDireccion());
			}
		if (parametrosEnvio.getPersonas() != null)
			for (CorreoElectronicoDTO persona : parametrosEnvio.getPersonas()) {
				if (persona.getDireccion() != null && !persona.getDireccion().isEmpty())
					destinos.add(persona.getDireccion());
			}
		
		// Verifica si el correo electrÃ³nico es certificado y si es asÃ­ agrega
		// el sufijo ".rpost.org" a las direcciones de los destinatarios
		Object parametroObject = manejadorParametrosGenerales
				.obtenerValorParametroPorCodigoTipo(UtilDominios.CODIGO_PARAMETRO_GENERAL_ENVIO_CERTIFICADO,
													UtilDominios.TIPO_PARAMETRO_GENERAL_CORREO,
													ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);

		if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado() && parametroObject.equals(UtilConstantes.SI)) {
			
			destinos = adicionarSufijoDireccionesDestinatarios(destinos);
		}
		
		/**
		 * 21/02/2020
		 * Se elimina el prefijo REF:idPersonaCarta a todos los correos
		 */
		try {
			CorreoRolPersonaCaso correo = new CorreoRolPersonaCaso();
			if(destinos.size() != 0) {
				//Envia el cuerpo de correo correspondiente a cada destino
				if (parametrosEnvio.getCuerpoCorreo().size() == destinos.size()) {
					List<String> destinatario = new ArrayList<String>();
					ArrayOfString listaDestinos = new ArrayOfString();
					for (int i = 0; i < destinos.size(); i++) {
						destinatario.clear();
						listaDestinos.getString().clear();
						listaDestinos.getString().add(destinos.get(i));
						inDTO.setPara(listaDestinos);
						inDTO.setContenido(parametrosEnvio.getCuerpoCorreo().get(i));
						inDTO.setAsunto(parametrosEnvio.getAsunto());
						try {
							if(parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado() && parametrosEnvio.getIdCaso() != null  && i < parametrosEnvio.getRolPersonaCaso().size()){
								correo = persistirCorreoRolPersonaCaso(parametrosEnvio,parametrosEnvio.getRolPersonaCaso().get(i) );
								String asunto  = this.generarAsunto(parametrosEnvio, correo.getIdCorreoRolPersonaCaso());
								correo.setAsunto(asunto);
								manejadorCorreoRolPersonaCaso.actualizar(correo);
								inDTO.setAsunto(asunto);
							}
							servicio.envioCorreo(inDTO);
						} catch (SOAPFaultException ex) {
							logger.error(UtilConstantes.ERROR_CORREO,ex);
							
							if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado()) {
								if (parametrosEnvio.getRolPersonaCaso() != null && 
										!parametrosEnvio.getRolPersonaCaso().isEmpty() && parametrosEnvio.getIdCaso() != null){
									falloEnvio = true;
									
									try {
										if(correo != null){
											correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
											manejadorCorreoRolPersonaCaso.actualizar(correo);
										}
										envioCorreoFacade.registrarFalloEnvioCorreo(parametrosEnvio.getRolPersonaCaso().get(i), parametrosEnvio,
												parametrosEnvio.getIdCaso(), ExceptionUtils.getFullStackTrace(ex));
									} catch (Exception e) {
										
										logger.error("Error: ", e);
									}
									/*registrarFalloEnvioCorreo(parametrosEnvio.getRolPersonaCaso().get(i), parametrosEnvio,
											parametrosEnvio.getIdCaso(), ExceptionUtils.getFullStackTrace(ex));*/									
								}									
							}
						}
					}
				} else {
					List<String> destinatario = new ArrayList<String>();
					ArrayOfString listaDestinos = new ArrayOfString();
					for (int i = 0; i < destinos.size(); i++) {
						destinatario.clear();
						listaDestinos.getString().clear();
						listaDestinos.getString().add(destinos.get(i));
						inDTO.setPara(listaDestinos);
						inDTO.setContenido(parametrosEnvio.getCuerpoCorreo().get(0)); 
						inDTO.setAsunto(parametrosEnvio.getAsunto());
						try {
							if(parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado() && parametrosEnvio.getIdCaso() != null && i < parametrosEnvio.getRolPersonaCaso().size()){
								correo = persistirCorreoRolPersonaCaso(parametrosEnvio,parametrosEnvio.getRolPersonaCaso().get(i) );
								String asunto  = this.generarAsunto(parametrosEnvio, correo.getIdCorreoRolPersonaCaso());
								correo.setAsunto(asunto);
								manejadorCorreoRolPersonaCaso.actualizar(correo);
								inDTO.setAsunto(asunto);
							}
							servicio.envioCorreo(inDTO);
						} catch (SOAPFaultException ex) {
							logger.error(UtilConstantes.ERROR_CORREO,ex);
							if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado() && parametrosEnvio.getIdCaso() != null) {

								if (parametrosEnvio.getRolPersonaCaso() != null
										&& !parametrosEnvio.getRolPersonaCaso().isEmpty()
										&& (parametrosEnvio.getInvitados() == null
												|| parametrosEnvio.getInvitados().isEmpty())) {
									falloEnvio = true;
									try {
										if(correo != null){
											correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
											manejadorCorreoRolPersonaCaso.actualizar(correo);
										}
										envioCorreoFacade.registrarFalloEnvioCorreo(parametrosEnvio.getRolPersonaCaso().get(0), parametrosEnvio,
												parametrosEnvio.getIdCaso(), ExceptionUtils.getFullStackTrace(ex));
									} catch (Exception e) {
										logger.error("Error: ", e);
									}				
								}
								if (parametrosEnvio.getInvitados() != null && !parametrosEnvio.getInvitados().isEmpty()) {
									for (CorreoElectronicoDTO invitado : parametrosEnvio.getInvitados()) {
										if (parametrosEnvio.getInvitados() != null
												&& !parametrosEnvio.getInvitados().isEmpty()) {
											falloEnvio = true;
											try {
												envioCorreoFacade.registrarFalloEnvioCorreo(invitado, parametrosEnvio, parametrosEnvio.getIdCaso(),
														ExceptionUtils.getFullStackTrace(ex));
											} catch (Exception e) {
												logger.error("Error: ", e);
											}
										}
									}
								}
							}
						}
					}
					
				}
			}
		} catch (UnresolvedAddressException e) {
			logger.error(UtilConstantes.ERROR_CORREO, e);
		}

		return falloEnvio;
	}
	
	
	
	public void validarConsistenciaParametrosEnvioCorreo(ParametrosEnvioCorreoDTO parametrosEnvio){
		boolean sinPersonas = parametrosEnvio.getPersonas()==null || parametrosEnvio.getPersonas().isEmpty();
		if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado()
				&& parametrosEnvio.getIdCaso() == null && sinPersonas) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR270.toString()));
		}	
		
		boolean sinRolPersonaCaso = parametrosEnvio.getRolPersonaCaso()==null || parametrosEnvio.getRolPersonaCaso().isEmpty();
		boolean sinInvitados = parametrosEnvio.getInvitados()==null || parametrosEnvio.getInvitados().isEmpty();
		
		//Los parametros no definen ningun correo receptor
		if(sinRolPersonaCaso && sinInvitados && sinPersonas){
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
		}
	}
	
	/**
	 * MÃ©todo encargado de obtener los adjuntos de un correo electrÃ³nico
	 * 
	 * @param parametrosEnvio
	 * @param parametroRutaGestorDocumental
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	private ArrayOfAdjuntoDTO obtenerAdjuntosCorreo(ParametrosEnvioCorreoDTO parametrosEnvio,
			ParametrosGenerales parametroRutaGestorDocumental) throws SIMASCNegocioExcepcion {
		ArrayOfAdjuntoDTO listaAdjuntos = new ArrayOfAdjuntoDTO();

		if (parametrosEnvio.getCartaPersona() != null && parametrosEnvio.getCartaPersona().getIdPlantillaCarta() != null
				&& parametrosEnvio.getAdjuntos() != null) {
			parametrosEnvio.getAdjuntos()
					.addAll(obtenerAdjuntosCartaPorDefecto(parametrosEnvio.getCartaPersona().getIdPlantillaCarta()));
		}

		if (parametrosEnvio.getAdjuntos() != null && !parametrosEnvio.getAdjuntos().isEmpty()) {
			List<AdjuntoDTO> adjuntosDTO = new ArrayList<>();
			File comprimidoAdjuntos = null;

			try {
				comprimidoAdjuntos = comprimirAdjuntosCorreo(parametrosEnvio, parametroRutaGestorDocumental);
				
				if (comprimidoAdjuntos != null && comprimidoAdjuntos.exists()) {
					AdjuntoDTO adjuntoDTO = convertirAdjuntosCorreo(comprimidoAdjuntos);
					
					if (adjuntoDTO != null)
						adjuntosDTO.add(adjuntoDTO);
				}

				listaAdjuntos.setAdjuntoDTO(adjuntosDTO);

			} catch (FileNotFoundException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR012.toString()));
			} catch (IOException e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR012.toString()));
			} catch (Exception e) {
				throw new SIMASCNegocioExcepcion(
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR012.toString()));
			} finally {
				if (comprimidoAdjuntos != null && comprimidoAdjuntos.exists() 
						&& parametrosEnvio.getAdjuntos()!=null && parametrosEnvio.getAdjuntos().size()>1)
					comprimidoAdjuntos.delete();
			}
		}

		return listaAdjuntos;
	}
	
	/**
	 * MÃ©todo encargado de comprimir los adjuntos de un correo electrÃ³nico
	 * 
	 * @param parametrosEnvio
	 * @param parametroRutaGestorDocumental
	 * @return
	 * @throws IOException
	 */
	private File comprimirAdjuntosCorreo(ParametrosEnvioCorreoDTO parametrosEnvio,
			ParametrosGenerales parametroRutaGestorDocumental) throws IOException {
		ZipOutputStream zipOutputStream = null;

		String nombreComprimidoAdjuntos = String.format(UtilConstantes.NOMBRE_ARCHIVO_ADJUNTOS,
				(parametrosEnvio.getAsunto().replace(':', '_') + UtilConstantes.CARACTER_ESPACIO + new Date().getTime()
						+ UtilConstantes.CARACTER_PUNTO + UtilConstantes.EXTENSION_ARCHIVO_ZIP));
		String pathComprimidoAdjuntos = parametroRutaGestorDocumental.getValorTexto() + nombreComprimidoAdjuntos;
		File comprimidoAdjuntos = new File(pathComprimidoAdjuntos);

		try {
			if(parametrosEnvio.getAdjuntos().size()>1){
				zipOutputStream = new ZipOutputStream(new FileOutputStream(comprimidoAdjuntos));
				for (DocumentoDTO docDTO : parametrosEnvio.getAdjuntos()) {
	
					File archivo = gestorDocumentalFacade.recuperarDocumento(parametrosEnvio.getIdCaso(),
							docDTO.getIdDocumento());
	
					ZipEntry zipEntry = new ZipEntry(archivo.getName());
					zipOutputStream.putNextEntry(zipEntry);
	
					byte[] bytes = Files.readAllBytes(Paths.get(archivo.getCanonicalPath()));
					zipOutputStream.write(bytes);
					zipOutputStream.closeEntry();
				}
			}else{
				comprimidoAdjuntos = gestorDocumentalFacade.recuperarDocumento(parametrosEnvio.getIdCaso(),
						parametrosEnvio.getAdjuntos().get(0).getIdDocumento());
			}
		} catch (FileNotFoundException e) {
			logger.error("Error comprimirAdjuntosCorreo: " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error("IO Error comprimirAdjuntosCorreo: " + e.getMessage());
			throw e;
		} finally {
			if (zipOutputStream != null) {
				zipOutputStream.close();
			}
		}

		return comprimidoAdjuntos;
	}
	
	/**
	 * MÃ©todo encargado de convertir los adjuntos de un correo electrÃ³nico en un
	 * objeto que entiende el servicio de envÃ­o de correos
	 * 
	 * @param comprimidoAdjuntos
	 * @return
	 * @throws IOException
	 */
	private AdjuntoDTO convertirAdjuntosCorreo(File comprimidoAdjuntos) throws IOException {
		InputStream inputStream = null;
		AdjuntoDTO adjunto = null;

		DataHandler dataHandler = new DataHandler(new FileDataSource(comprimidoAdjuntos));

		String dataAdjuntoDTO = null;
		byte[] data = new byte[(int) comprimidoAdjuntos.length()];
		int offset = 0;
		int numRead = 0;

		try {
			inputStream = dataHandler.getInputStream();
			while (offset < data.length && (numRead = inputStream.read(data, offset, data.length - offset)) >= 0) {
				offset += numRead;
			}
			dataAdjuntoDTO = DatatypeConverter.printBase64Binary(data);

			String[] parametrosAdjuntoDTO = new String[2];
			parametrosAdjuntoDTO[0] = comprimidoAdjuntos.getName();
			parametrosAdjuntoDTO[1] = dataAdjuntoDTO;

			adjunto = new AdjuntoDTO();
			adjunto.setNomAdjunto(parametrosAdjuntoDTO[0]);
			adjunto.setAdjunto(parametrosAdjuntoDTO[1]);

		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null)
				inputStream.close();
		}

		return adjunto;
	}

	/**
	 * 
	 * @param parametrosEnvioCorreoDTO
	 */
	public CorreoRolPersonaCaso persistirCorreoRolPersonaCaso(ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO,CorreoElectronicoDTO correo ) {
			
			CorreoRolPersonaCaso crpc = new CorreoRolPersonaCaso();
			RolPersonaCaso rpc = manejadorRolPersonaCaso.estaPersonaAsignadaCaso(correo.getIdPersona(),
					parametrosEnvioCorreoDTO.getIdCaso());
			if (rpc != null) {
				crpc.setIdPersonaReceptor(correo.getIdPersona());
				crpc.setIdRolReceptor(rpc.getRol().getIdRol());
				crpc.setIdCasoReceptor(parametrosEnvioCorreoDTO.getIdCaso());
			}
				
			CartaPersona cartaPersona = persistirInformacionCorreoEnviado(parametrosEnvioCorreoDTO,
					correo.getDireccion(), crpc);
			if (cartaPersona.getPersona() != null) {
				crpc.setPersona(cartaPersona.getPersona());
				crpc.setRolPersonaCaso(rpc);
			} else if (cartaPersona.getInvitado() != null) {
				crpc.setInvitado(cartaPersona.getInvitado());
			}
			String cuerpoC = "";
			for (String cuerpoCorreo : parametrosEnvioCorreoDTO.getCuerpoCorreo()) {
				cuerpoC = cuerpoC.concat(cuerpoCorreo);
			}
			if(parametrosEnvioCorreoDTO != null){
				crpc.setIdAudiencia(parametrosEnvioCorreoDTO.getIdAudiencia());
			}
			crpc.setCuerpoCorreo(cuerpoC);
			validarEnvioCorreoRolPersonaCaso(crpc);	
			crpc.setCorreoReceptor((correo.getDireccion()==null)?"":correo.getDireccion());
			manejadorCorreoRolPersonaCaso.crear(crpc);
			// se aÃ±adio esta linea para evitar que lanze una excepcion si no
			// tiene adjuntos persistidos.
			if (parametrosEnvioCorreoDTO.getAdjuntos() != null) {
				List<Adjunto> adjuntosPersistidos = persistirAdjuntos(parametrosEnvioCorreoDTO, crpc);
				crpc.setAdjuntoList(adjuntosPersistidos);
				manejadorCorreoRolPersonaCaso.actualizar(crpc);

			}
		return crpc;
	}

	private void validarEnvioCorreoRolPersonaCaso(CorreoRolPersonaCaso crpc) {

		if (crpc.getIdRolReceptor() != null) {
			crpc.setInvitado(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
		} else if (crpc.getIdInvitado() != null || crpc.getInvitado() != null) {
			if (crpc.getInvitado() != null) {
				crpc.setIdInvitado(crpc.getInvitado().getIdInvitado());
				crpc.setCorreoReceptor(crpc.getInvitado().getCorreo());
			}
			crpc.setIdRolReceptor(null);
			crpc.setIdPersonaReceptor(null);
			crpc.setIdCasoReceptor(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
			crpc.setRolPersonaCaso(null);
		} else if (crpc.getIdPersonaQueRecibe() != null || crpc.getPersonaQueRecibe() != null) {
			if (crpc.getPersonaQueRecibe() != null)
				crpc.setIdPersonaQueRecibe(crpc.getPersonaQueRecibe().getIdPersona());
			crpc.setIdRolReceptor(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
			crpc.setIdCasoReceptor(null);
			crpc.setInvitado(null);
			crpc.setRolPersonaCaso(null);
		}
	}

	private List<Adjunto> persistirAdjuntos(ParametrosEnvioCorreoDTO parametrosEnvio, CorreoRolPersonaCaso crpc) {
		List<Adjunto> adjuntosGuardados = new ArrayList<>();
		for (DocumentoDTO doc : parametrosEnvio.getAdjuntos()) {

			Documento documento = manejadorDocumento.buscar(doc.getIdDocumento());
			AdjuntoPK adjuntoPK = new AdjuntoPK();
			adjuntoPK.setIdCorreoRolPersonaCaso(crpc.getIdCorreoRolPersonaCaso());
			adjuntoPK.setIdDocumento(doc.getIdDocumento());
			Adjunto adjunto = new Adjunto();
			adjunto.setAdjuntoPK(adjuntoPK);
			adjunto.setDocumento(documento);
			adjunto.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			adjunto.setFechaUltimaModificacion(new Date());
			adjunto.setIdUsuarioModificacion(USUARIO_MODIFICACION);

			manejadorAdjunto.crear(adjunto);

			adjuntosGuardados.add(adjunto);
		}
		return adjuntosGuardados;
	}

	/**
	 * Persiste la informaciÃ³n del correo enviado en la entidad CorreoRolPersonaCaso.
	 * Solo se persiste informaciÃ³n de correos certificados
	 * @param parametrosEnvio
	 * @param correo
	 * @param crpc
	 * @return
	 */
	private CartaPersona persistirInformacionCorreoEnviado(ParametrosEnvioCorreoDTO parametrosEnvio, String correo,
			CorreoRolPersonaCaso crpc) {
		Audiencia audiencia = manejadorAudiencia.buscar(parametrosEnvio.getIdAudiencia());
		CartaPersona cartaPersona = new CartaPersona();
		if(parametrosEnvio.getCartaPersona() != null){
			crpc.setIdCartaPersona(parametrosEnvio.getCartaPersona().getIdCartaPersona());
		}

		crpc.setAsunto(parametrosEnvio.getAsunto());
		crpc.setCorreoReceptor(correo);
		crpc.setCorreoRemitente(parametrosEnvio.getRemitente().getDireccion());
		crpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		crpc.setFechaEnvio(new Date());
		crpc.setFechaUltimaModificacion(new Date());
		crpc.setIdCasoReceptor(parametrosEnvio.getIdCaso());
		crpc.setIdPersonaEnvio(parametrosEnvio.getRemitente().getIdPersona());
		crpc.setIdUsuarioModificacion(USUARIO_MODIFICACION);

		StringBuilder cuerpo = new StringBuilder();
		for (String cuerpoCorreo : parametrosEnvio.getCuerpoCorreo()) {
			cuerpo.append(cuerpoCorreo);
		}
		if(parametrosEnvio.getInvitados()!=null && !parametrosEnvio.getInvitados().isEmpty())	{
			Invitado invitado = new Invitado();
			invitado = manejadorInvitado.buscar(parametrosEnvio.getInvitados().get(0).getIdPersona());
			cartaPersona.setInvitado(invitado);
			crpc.setInvitado(invitado);
		}
		if(parametrosEnvio.getPersonas()!=null){
			
		}
		crpc.setMensaje(cuerpo.toString());

		if (null != audiencia) {
			crpc.setAudiencia(audiencia);
			crpc.setIdAudiencia(audiencia.getIdAudiencia());
		}
		return cartaPersona;
	}

	/**
	 * Genera el asunto del caso. Si el correo no es certificado se ignora el parametro
	 * idCaso.
	 * 
	 * @param idCaso
	 * @param asunto
	 * @param isCertificado
	 * @return
	 */
	private String generarAsunto(ParametrosEnvioCorreoDTO parametros, Long idCorreoRolPersona) {		

		StringBuilder sB = new StringBuilder();
		if(null != idCorreoRolPersona ){
			sB.append("REF: "+idCorreoRolPersona + " ");
			sB.append(parametros.getAsunto());
		}
//		if(isCertificado != null && isCertificado && idCaso != null){
//			Caso caso = manejadorCaso.buscar(idCaso);
//			sB.append(REF_ASUNTO_CORREO);
//			if (caso.getServicioMateria().getServicio().getTipo().equals(UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA)) {
//				sB.append(REF_TIPO_ARBITRAJE);
//			} else if (caso.getServicioMateria().getServicio().getTipo()
//					.equals(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS)) {
//				sB.append(REF_TIPO_CONCILIACION);
//			}
//			if (caso.getSede() != null){
//				sB.append(caso.getSede().getIdSede());
//				sB.append(REF_GUION);			
//			}
//		}
//		sB.append(asunto);
		return sB.toString();
	}

	@Override
	public List<NotificacionEnviadaDTO> buscarCorreosEnviados(Long idCaso) {
		List<CorreoRolPersonaCaso> correosRolPersonaCaso = manejadorCorreoRolPersonaCaso.obtenerCorreosPorIdCaso(idCaso,true);
		if(correosRolPersonaCaso==null){
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.ERROR013.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		List<NotificacionEnviadaDTO> notificaciones = new ArrayList<>();
		
		for (CorreoRolPersonaCaso correoRolPersonaCaso : correosRolPersonaCaso) {
			NotificacionEnviadaDTO notificacion = new NotificacionEnviadaDTO();
			notificacion.setIdCorreoRolPersonaCaso(correoRolPersonaCaso.getIdCorreoRolPersonaCaso());
			notificacion.setCorreo(correoRolPersonaCaso.getCorreoReceptor());
			notificacion.setFechaEnvio(correoRolPersonaCaso.getFechaEnvio());
			if ( correoRolPersonaCaso.getIdAudiencia() != null ){
				Long numeroAudiencia = (manejadorAudiencia.buscar(correoRolPersonaCaso.getIdAudiencia())).getConsecutivo();
				notificacion.setNumeroAudiencias( numeroAudiencia != null ? numeroAudiencia : 0);
			}
			if (correoRolPersonaCaso.getIdPersonaReceptor() != null) {
				Persona persona = manejadorPersona.buscar(correoRolPersonaCaso.getIdPersonaReceptor());
				notificacion.setNombreParte(persona != null ? persona.getNombreCompleto() : UtilConstantes.CADENA_VACIA);
			} else if (correoRolPersonaCaso.getIdInvitado() != null) {
				Invitado invitado = manejadorInvitado.buscar(correoRolPersonaCaso.getIdInvitado());
				notificacion.setNombreParte(invitado != null ? invitado.getNombre() : UtilConstantes.CADENA_VACIA);
			} else {
				notificacion.setNombreParte(UtilConstantes.CADENA_VACIA);
			}
			
			if(null!= correoRolPersonaCaso.getMensaje()){
				notificacion.setMensaje(true);
			}
			Object[] acuses = manejadorAcuse.obtenerAcusesCorreo(correoRolPersonaCaso.getIdCorreoRolPersonaCaso());
			notificacion.setAcuseEnvio(acuses[0] != null);
			notificacion.setAcuseRecibido(acuses[1] != null);
			notificacion.setAcuseLectura(acuses[2] != null);
			notificacion.setFallaEntrega(acuses[3] != null);

			notificaciones.add(notificacion);
		}

		return notificaciones;
	}

	@Override
	public String obtenerAcuse(Long idCorreoRolPersonaCaso, String tipoAcuse) {
		CorreoRolPersonaCaso crpc = manejadorCorreoRolPersonaCaso.buscar(idCorreoRolPersonaCaso);
		if(crpc==null){
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.ERROR014.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}
		StringBuilder acuseRetorno = new StringBuilder();
		if(tipoAcuse.equals(UtilDominios.TIPO_ACUSE_MENSAJE)){
			if(null!=crpc.getMensaje()){
				return crpc.getMensaje();
			}else{
				String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(
						MensajesEnum.ERROR015.toString());
				throw new SIMASCNegocioExcepcion(mensajeError);
			}
		}
		if (null != crpc.getAcuseList() && !crpc.getAcuseList().isEmpty()) {
			for (Acuse acuse : crpc.getAcuseList()) {
				switch (acuse.getAcusePK().getTipo()) {
				case UtilDominios.TIPO_ACUSE_ENVIO:
					return obtenerHtml(crpc, acuseRetorno, acuse);
				case UtilDominios.TIPO_ACUSE_RECIBIDO:
					return obtenerHtml(crpc, acuseRetorno, acuse);
				case UtilDominios.TIPO_ACUSE_LECTURA:
					return obtenerHtml(crpc, acuseRetorno, acuse);
				case UtilDominios.TIPO_ACUSE_FALLA:
					return obtenerHtml(crpc, acuseRetorno, acuse);
				default:
					break;
				}
			}
		}else{
			String mensajeError = MensajesSimasc.getInstancia().getMensajePorKey(
					MensajesEnum.ERROR016.toString());
			throw new SIMASCNegocioExcepcion(mensajeError);
		}

		return null;
	}

	/**
	 * Obtiene el HTML como String del acuse que se encuentra guardado en el
	 * gestor documental
	 * 
	 * @param crpc
	 *            entidad de la que se va a consultar el acuse
	 * @param acuseRetorno
	 * @param acuse
	 * @return retorna el html en formato String
	 */
	private String obtenerHtml(CorreoRolPersonaCaso crpc, StringBuilder acuseRetorno, Acuse acuse) {
		File acuseArchivo = gestorDocumentalFacade.recuperarDocumento(crpc.getIdCasoReceptor(),
				acuse.getDocumento().getIdDocumento());
		StringBuilder contentBuilder = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(acuseArchivo));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
			logger.error("Error: ", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Error: ", e);
				}
			}
		}
		acuseRetorno.append(contentBuilder.toString());
		return acuseRetorno.toString();
	}

	@Override
	public List<InformacionCorreoDTO> obtenerInformacionCorreosDevueltos() throws SIMASCNegocioExcepcion {
		List<InformacionCorreoDTO> informacionCorreoDTOs = new ArrayList<InformacionCorreoDTO>();
		try {
			List<CorreoRolPersonaCaso> correoRolPersonaCasos = manejadorCorreoRolPersonaCaso.obtenerCorreosFallaEnvio();
			
			for(CorreoRolPersonaCaso it3 : correoRolPersonaCasos){
				if(it3.getLlamadaList().isEmpty()){
					InformacionCorreoDTO informacionCorreoDTO = new InformacionCorreoDTO();
					informacionCorreoDTO.setParteNotificada(false);
					informacionCorreoDTO.setIdCaso(it3.getRolPersonaCaso().getCaso().getIdCaso());
					Long idPersona = it3.getIdPersonaReceptor() != null ? it3.getIdPersonaReceptor()
							: it3.getIdPersonaQueRecibe();
					informacionCorreoDTO.setIdPersona(idPersona);
					informacionCorreoDTO.setIdCorreoRolPersonaCaso(it3.getIdCorreoRolPersonaCaso());
					informacionCorreoDTO.setNombreCaso(it3.getRolPersonaCaso().getCaso().getNombre());
					informacionCorreoDTO.setNombreParte(it3.getRolPersonaCaso().getPersona().getNombreCompleto());
					informacionCorreoDTO.setTipoCaso(it3.getRolPersonaCaso().getCaso().getServicioMateria().getServicio().getNombre());
					informacionCorreoDTO.setIdAudiencia(it3.getIdAudiencia());
					informacionCorreoDTOs.add(informacionCorreoDTO);
				}				
			}
		}catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()));
		}
		return informacionCorreoDTOs;
	}
	
	/**
	 * @throws Exception 
	 * @throws SIMASCNegocioExcepcion 
	 * 
	 */
	private void registrarFalloEnvioCorreo(CorreoElectronicoDTO correoElectronicoDTO,
			ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO, Long idCaso, String causaFallo)
			throws SIMASCNegocioExcepcion {
		CorreoRolPersonaCaso correoRolPersonaCaso = new CorreoRolPersonaCaso();
		InputStream documentoStream = null;

		try {
			// 1. Registra en base de datos el correo que se intento enviar a
			// una persona determinada con un rol especifico asignada a un caso
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso
					.estaPersonaAsignadaCaso(correoElectronicoDTO.getIdPersona(), parametrosEnvioCorreoDTO.getIdCaso());
			if (rolPersonaCaso != null) {
				correoRolPersonaCaso.setIdPersonaReceptor(correoElectronicoDTO.getIdPersona());
				correoRolPersonaCaso.setIdRolReceptor(rolPersonaCaso.getRol().getIdRol());
				correoRolPersonaCaso.setRolPersonaCaso(rolPersonaCaso);
			}
			
			CartaPersona cartaPersona = persistirInformacionCorreoEnviado(parametrosEnvioCorreoDTO,
					correoElectronicoDTO.getDireccion(), correoRolPersonaCaso);
			if (cartaPersona.getPersona() != null) {
				correoRolPersonaCaso.setIdPersonaReceptor(correoElectronicoDTO.getIdPersona());
			} else if (cartaPersona.getInvitado() != null) {
				correoRolPersonaCaso.setIdInvitado(correoElectronicoDTO.getIdPersona());
				correoRolPersonaCaso.setIdCasoReceptor(null);
			}
			
			String textoCuerpoCorreo = UtilConstantes.CADENA_VACIA;
			for (String cuerpoCorreo : parametrosEnvioCorreoDTO.getCuerpoCorreo()) {
				textoCuerpoCorreo = textoCuerpoCorreo.concat(cuerpoCorreo);
			}

			correoRolPersonaCaso.setCuerpoCorreo(textoCuerpoCorreo);

			validarEnvioCorreoRolPersonaCaso(correoRolPersonaCaso);
			
			correoRolPersonaCaso.setCorreoReceptor(correoElectronicoDTO.getDireccion() != null
					? correoElectronicoDTO.getDireccion() : UtilConstantes.CADENA_VACIA);
			
			manejadorCorreoRolPersonaCaso.crear(correoRolPersonaCaso);

			if (parametrosEnvioCorreoDTO.getAdjuntos() != null) {
				List<Adjunto> adjuntosPersistidos = persistirAdjuntos(parametrosEnvioCorreoDTO, correoRolPersonaCaso);
				correoRolPersonaCaso.setAdjuntoList(adjuntosPersistidos);
				manejadorCorreoRolPersonaCaso.actualizar(correoRolPersonaCaso);

			}

			// 2. Genera el documento fisico y su metadata con el detalle del
			// error del envÃ­o del correo
			documentoStream = new ByteArrayInputStream(causaFallo.getBytes());

			String nombreDocumento = UtilConstantes.NOMBRE_DOCUMENTO_FALLO_ENVIO_CORREO + new Date().getTime();

			Documento documentoAcuse = new Documento();
			documentoAcuse.setIdCaso(idCaso);
			documentoAcuse.setNombre(nombreDocumento);
			documentoAcuse.setTipoDocumento(UtilDominios.TIPO_DOCUMENTO_DIG_ACUSE);
			documentoAcuse.setPublicado(false);
			documentoAcuse.setTipoArchivo(UtilDominios.TIPO_ARCHIVO_TXT);
			documentoAcuse.setEstadoDigitalizacion(UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
			documentoAcuse.setIdUsuarioModificacion(correoElectronicoDTO.getIdUsuarioModificacion()!=null?correoElectronicoDTO.getIdUsuarioModificacion():UtilConstantes.USUARIO_DEFECTO_SIMASC);
			documentoAcuse.setFechaUltimaModificacion(new Date());
			documentoAcuse.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

			// 3. Intenta almacenar el documento, con el detalle del error de
			// envÃ­o de correo, fisico y su metadata
			List<Long> idsDocumentos = almacenamientoDocumentosFacade.guardarDocumento(idCaso, nombreDocumento,
					UtilConstantes.EXTENSION_ARCHIVO_TXT, documentoStream, documentoAcuse,
					documentoAcuse.getIdUsuarioModificacion(), null, null);

			// 4. Genera y persiste el acuse de error de envÃ­o del correo desde
			// la aplicaciÃ³n
			AcusePK acusePK = new AcusePK(correoRolPersonaCaso.getIdCorreoRolPersonaCaso(), idsDocumentos.get(0),
					UtilDominios.TIPO_ACUSE_FALLO_SIMASC);
			Acuse acuse = new Acuse();
			acuse.setAcusePK(acusePK);
			acuse.setCorreoRolPersonaCaso(correoRolPersonaCaso);
			acuse.setDocumento(documentoAcuse);
			acuse.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			acuse.setFechaUltimaModificacion(new Date());
			acuse.setIdUsuarioModificacion(correoElectronicoDTO.getIdUsuarioModificacion());

			manejadorAcuse.crear(acuse);
		} catch (Exception e) {
			// 3.1. Si la persistencia del documento fisico y/o su metadata
			// falla se deshace el registro del correo en la base de datos
			if (correoRolPersonaCaso != null && correoRolPersonaCaso.getIdCorreoRolPersonaCaso() != null) {
				CorreoRolPersonaCaso correoRolPersonaCasoEliminar = manejadorCorreoRolPersonaCaso
						.buscar(correoRolPersonaCaso.getIdCorreoRolPersonaCaso());

				if (correoRolPersonaCasoEliminar != null) {
					manejadorCorreoRolPersonaCaso.eliminar(correoRolPersonaCasoEliminar);
				}
			}

			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR215.toString()));
		} finally {
			if (documentoStream != null) {
				try {
					documentoStream.close();
				} catch (IOException e) {
					logger.error("Error: ", e);
				}
			}
		}
	}
	
	/**
	 * MÃ©todo encargado de obtener los archivos adjuntos por defecto que define
	 * la plantilla de una carta
	 * 
	 * @param idPlantillaCarta
	 * @return
	 */
	private List<DocumentoDTO> obtenerAdjuntosCartaPorDefecto(Long idPlantillaCarta) {
		List<DocumentoDTO> adjuntosCartaPorDefecto = new ArrayList<DocumentoDTO>();

		// 1. Obtiene la plantilla de la carta dado su identificador
		PlantillaCarta plantillaCarta = manejadorPlantillaCarta.buscar(idPlantillaCarta);

		// 2. Valida que la plantilla exista
		if (plantillaCarta != null) {

			// 3. Si la plantilla existe obtiene sus valores dinÃ¡micos
			InformacionFiltro informacionFiltro = new InformacionFiltro(TipoFiltro.EXACTO,
					ValorPlantillaCarta.ENTIDAD_VALOR_PLANTILLA_CARTA_ID_PLANTILLA_CARTA, idPlantillaCarta);
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(informacionFiltro);
			List<ValorPlantillaCarta> valoresPlantillaCarta = manejadorValorPlantillaCarta.consultar(filtros, null,
					null);

			// 4. Valida que la plantilla contenga el valor dinÃ¡mico de adjuntos
			// por defecto
			List<Long> idsDocumentosAdjuntos = new ArrayList<Long>();
			for (ValorPlantillaCarta valorPlantillaCarta : valoresPlantillaCarta) {
				if (UtilDominios.DOCUMENTOS_ADJUNTOS.equals(valorPlantillaCarta.getNombreValorDinamico())) {

					// 5. Si la plantilla tiene el valor dinÃ¡mico de adjuntos
					// por defecto intenta obtenerlos de la base de datos
					if (valorPlantillaCarta.getDescripcion() != null
							&& !valorPlantillaCarta.getDescripcion().isEmpty()) {
						if (valorPlantillaCarta.getDescripcion().contains(UtilConstantes.CARACTER_SEPARADOR)) {
							String[] idsDocumentos = valorPlantillaCarta.getDescripcion()
									.split(UtilConstantes.CARACTER_SEPARADOR);
							for (String idDocumento : idsDocumentos) {
								idsDocumentosAdjuntos.add(Long.parseLong(idDocumento));
							}
						} else if (valorPlantillaCarta.getDescripcion().length() >= 1) {
							idsDocumentosAdjuntos.add(Long.parseLong(valorPlantillaCarta.getDescripcion()));
						}
					}
					break;
				}
			}

			// 6. Una vez obtenidos los adjuntos por defecto procede a
			// transformarlos
			DocumentoDTO transformadorDocumento = new DocumentoDTO();
			for (Long idDocumento : idsDocumentosAdjuntos) {
				DocumentoDTO documentoDTO = transformadorDocumento
						.transformarSinDependencias(manejadorDocumento.buscar(idDocumento));
				if (documentoDTO != null)
					adjuntosCartaPorDefecto.add(documentoDTO);
			}

		}

		return adjuntosCartaPorDefecto;
	}
	
	/**
	 * MÃ©todo encargado de obtener el remitente de un correo electrÃ³nico
	 * 
	 * @param correo
	 * @return
	 */
	private CorreoElectronicoDTO obtenerRemitenteCorreo(EnvioCorreoElectronicoDTO correo) {
		CorreoElectronicoDTO remitente = null;
		Caso casoActual = manejadorCaso.buscar(correo.getIdCaso());
		if(casoActual == null || casoActual.getServicioMateria() == null || casoActual.getServicioMateria().getServicio() == null){
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR340.toString()));
		}
		if(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS.equals(casoActual.getServicioMateria().getServicio().getTipo())){
			remitente = this.remitenteConciliacion(correo);
		}else{
			remitente = this.remitentenArbitraje(correo);
		}
	
		return remitente;
	}
	
	/**
	 * consulta el remitenten para los casos de arbitraje
	 * @param correo
	 * @return
	 */
	private CorreoElectronicoDTO remitentenArbitraje(EnvioCorreoElectronicoDTO correo) {
		CorreoElectronicoDTO remitente = new CorreoElectronicoDTO();
		if (correo.isCertificado()) {
			RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultarAbogadoDelCaso(correo.getIdCaso());
			if (rolPersonaCaso != null && rolPersonaCaso.getPersona() != null) {
				remitente.setIdPersona(rolPersonaCaso.getPersona().getIdPersona());
				List<CorreoElectronico> correoElectronicos = correoElectronicoFacade
						.consultaCorreosPersona(rolPersonaCaso.getPersona().getIdPersona());
				if (correoElectronicos != null && !correoElectronicos.isEmpty())
					remitente.setDireccion(correoElectronicos.get(0).getDireccion());
			}
		} else {
			remitente.setIdPersona(correo.getIdUsuario());
			List<CorreoElectronico> correoElectronicos = correoElectronicoFacade
					.consultaCorreosPersona(correo.getIdUsuario());
			if (correoElectronicos != null && !correoElectronicos.isEmpty())
				remitente.setDireccion(correoElectronicos.get(0).getDireccion());
		}

		if (remitente.getDireccion() == null || remitente.getDireccion().isEmpty()) {
			remitente.setIdPersona(correo.getIdUsuario());
			ParametrosGenerales parametrosGenerales = parametrosGeneralesFacade
					.consultarPorCodigo(UtilConstantes.CODIGO_CORREO_ARBITRAJE);
			if (parametrosGenerales != null)
				remitente.setDireccion(parametrosGenerales.getValorTexto());
		}
		return remitente;

	}	
	
	/**
	 * consulta los remitentes de conciliacion
	 * @param correo
	 * @return
	 */
	private CorreoElectronicoDTO remitenteConciliacion(EnvioCorreoElectronicoDTO correo){
		CorreoElectronicoDTO remitente = new CorreoElectronicoDTO();
		if(correo.isCertificado()){
			remitente.setIdPersona(correo.getIdUsuario());
			ParametrosGenerales parametrosGenerales = parametrosGeneralesFacade
					.consultarPorCodigo(UtilConstantes.CODIGO_CORREO_CONCILIACION);
			if (parametrosGenerales != null){
				remitente.setDireccion(parametrosGenerales.getValorTexto());				
			}else{
				throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR341.toString()));
			}
			
		}else{
			Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			List<CorreoElectronico> correoEmisor = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());				
			List<CorreoElectronicoDTO> correosEmisorDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionSinDependencias(correoEmisor, new ArrayList<CorreoElectronicoDTO>());
			if(correosEmisorDTO != null && !correosEmisorDTO.isEmpty()){
				remitente = correosEmisorDTO.get(0);
			}else{
				throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR340.toString()));

			}
			
		}
		return remitente;
		
	}
	
	/**
	 * MÃ©todo encargado de adicionar el sufijo ".rpost.org" a las direcciones de
	 * correo a las cuales serÃ¡ enviado un correo electrÃ³nico certificado
	 * 
	 * @param destinos
	 * @return
	 */
	private List<String> adicionarSufijoDireccionesDestinatarios(List<String> destinos) {
		List<String> destinatarios = new ArrayList<String>();
		Object sufijoObject = manejadorParametrosGenerales
						.obtenerValorParametroPorCodigoTipo(UtilDominios.CODIGO_PARAMETRO_GENERAL_CORREO_SUFIJO,
															UtilDominios.TIPO_PARAMETRO_GENERAL_CORREO,
															ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO);
		if( sufijoObject == null )
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR342.toString()));
		for (String direccionCorreoElectronico : destinos) {
			direccionCorreoElectronico = direccionCorreoElectronico.concat(sufijoObject.toString());
			destinatarios.add(direccionCorreoElectronico);
		}

		return destinatarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoRolPersonaCasoFacade#envioDeCorreo(java.lang.String,
	 * java.util.List, java.util.List, java.util.List, java.util.List,
	 * java.lang.Long, java.util.List, java.lang.Long, java.lang.Boolean)
	 */
	@Override
	public boolean envioDeCorreo(String asunto, List<Persona> personas, List<RolPersonaCaso> receptores,
			List<Invitado> invitados, List<String> textoCorreo, Long idCaso, List<DocumentoDTO> adjuntos, Long idAudiencia, Boolean certificado)
			throws SIMASCNegocioExcepcion {
		
		boolean falloEnvio = false;
		List<String> cuerpoCorreo = textoCorreo;
		List<CorreoElectronico> correosRolPersonaCaso =  new ArrayList<CorreoElectronico>();
		List<CorreoElectronicoDTO> correosInvitados =  new ArrayList<CorreoElectronicoDTO>();
		List<CorreoElectronico> correoPersona =  new ArrayList<CorreoElectronico>();
		List<CorreoElectronico> correoEmisor =  new ArrayList<CorreoElectronico>();
		if (receptores != null)
			for (RolPersonaCaso rolPersonaFor : receptores) {
				correosRolPersonaCaso.addAll(correoElectronicoFacade.consultaCorreosPersona(rolPersonaFor.getPersona().getIdPersona()));
			}
		if (personas != null)
			for (Persona persona : personas) {
				correoPersona.addAll(correoElectronicoFacade.consultaCorreosPersona(persona.getIdPersona()));
			}
		if (invitados != null)
			for (Invitado invitado : invitados) {
				CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
				correo.setIdPersona(invitado.getIdInvitado());
				correo.setDireccion(invitado.getCorreo());
				if(correo.getDireccion()!=null)
				correosInvitados.add(correo);								
			}		
		
		
		Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		correoEmisor = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());
		logger.info("Correo Emisor: "+ correoEmisor.toString());	
		List<CorreoElectronicoDTO> correosRolDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionSinDependencias(correosRolPersonaCaso, new ArrayList<CorreoElectronicoDTO>());
		List<CorreoElectronicoDTO> correosPersonaDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionSinDependencias(correoPersona, new ArrayList<CorreoElectronicoDTO>());
		List<CorreoElectronicoDTO> correosEmisorDTO = (List<CorreoElectronicoDTO>) correoElectronicoFacade.transformarColeccionSinDependencias(correoEmisor, new ArrayList<CorreoElectronicoDTO>());
	
		//Parametros
		ParametrosEnvioCorreoDTO parametrosCorreo = new ParametrosEnvioCorreoDTO();
		parametrosCorreo.setCuerpoCorreo(cuerpoCorreo);
		parametrosCorreo.setRolPersonaCaso(correosRolDTO);				
		parametrosCorreo.setInvitados(correosInvitados);
		parametrosCorreo.setPersonas(correosPersonaDTO);
		parametrosCorreo.setIdAudiencia(idAudiencia);
		parametrosCorreo.setRemitente(correosEmisorDTO.get(0));
		parametrosCorreo.setIdCaso(idCaso);
		parametrosCorreo.setTipo(UtilDominios.TIPO_PLANTILLAS_CORREO_GENERAL);
		parametrosCorreo.setAsunto(asunto);
		
		if(certificado != null){
			parametrosCorreo.setCertificado(certificado);
		}else{
			//Por defecto no es certificado
			parametrosCorreo.setCertificado(false);
		}
		
		if ( adjuntos != null && !adjuntos.isEmpty() )
			parametrosCorreo.setAdjuntos(adjuntos);
		else
			parametrosCorreo.setAdjuntos(new ArrayList<DocumentoDTO>());
		
		try{
			falloEnvio = enviarCorreo(parametrosCorreo);
		}catch(UnresolvedAddressException | javax.xml.ws.soap.SOAPFaultException e){
			logger.error(UtilConstantes.ERROR_CORREO,e);
		}
		
		return falloEnvio;
	}
	

	@Override
	public void enviarCorreoSecretariaSalas(AgendamientoDTO agendamiento) throws SIMASCNegocioExcepcion{		
		
		try {
			// Validación :: Debe existir un correo parametrizado
			ParametrosGenerales correoEnvio = parametrosGeneralesFacade.consultarPorCodigo("CORR_SEC");
			
			if(correoEnvio == null || correoEnvio.getValorTexto().trim().isEmpty()) {
				throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
			}
			
			// Email :: Construir cuerpo
			StringBuilder cadenaCorreo = new StringBuilder();
			cadenaCorreo.append("No se pudo realizar el agendamiento del caso: ").append(agendamiento.getIdCaso()).append(", de  ");
			cadenaCorreo.append(agendamiento.getHoraInicio()).append(" a ").append(agendamiento.getHoraFin());
			
			ParametrosGenerales llaveEnvio = parametrosGeneralesFacade.consultarPorCodigo("LLAVE"); 
			ParametrosGenerales codContenido = parametrosGeneralesFacade.consultarPorCodigo("CONTENID");
			
			Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
			List<CorreoElectronico> correoEmisor  = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());
			
			EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
			EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
			inDTO.setAsunto(asuntoCorreoSecretarioReservas);
			ArrayOfString listaDestinos = new ArrayOfString();
			listaDestinos.getString().add(correoEnvio.getValorTexto());
			inDTO.setPara(listaDestinos);
			inDTO.setContenido(cadenaCorreo.toString());
			inDTO.setDe(correoEmisor.get(0).getDireccion());
			inDTO.setLlave(llaveEnvio.getValorTexto());//SACAR ESTO DE PARAMETROS GENERALES
			inDTO.setTipoContenido(codContenido.getValorTexto());
			servicio.envioCorreo(inDTO);
			
		} catch(Exception e){
			logger.error(UtilConstantes.ERROR_CORREO, e);
		}
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoElectronicoFacade#enviarCorreoSinPersistencia(java.util.List,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void enviarCorreoSinPersistencia(List<String> destinos, String asunto, String contenido) throws SIMASCNegocioExcepcion {
		
		// Validación :: La persona debe tener al menos un correo		
		if(destinos == null || destinos.isEmpty()) {
			throw new SIMASCNegocioExcepcion(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR271.toString()));
		}
		
		ParametrosGenerales llaveEnvio = parametrosGeneralesFacade.consultarPorCodigo("LLAVE");
		ParametrosGenerales codContenido = parametrosGeneralesFacade.consultarPorCodigo("CONTENID");
		Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		List<CorreoElectronico> correoEmisor = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());

		EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
		EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
		inDTO.setAsunto(asunto);
		
		ArrayOfString arrayDestino = new ArrayOfString();
		for (String iterator : destinos) {
			arrayDestino.getString().add(iterator);
		}
		
		inDTO.setPara(arrayDestino);
		inDTO.setContenido(contenido);
		inDTO.setDe(correoEmisor.get(0).getDireccion());
		inDTO.setLlave(llaveEnvio.getValorTexto());
		inDTO.setTipoContenido(codContenido.getValorTexto());
		try {
			servicio.envioCorreo(inDTO);
		} catch (SOAPFaultException ex) {
			logger.error(UtilConstantes.ERROR_CORREO, ex);
		}

	}
	@Override
	public void envioCorreoNombramientoConciliadorEquidad(Long idCaso, Long idPersonaAsignada){
		logger.info("Ingresa a envioCorreoNombramientoConciliadorEquidad");
		Caso casoActual = manejadorCaso.buscar(idCaso);		
				
		String asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO282.toString()));
		String plazoAceptacion = "2";
		ParametrosGenerales parGralPlazo;
		//ParametrosGenerales parGralUrl = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);
		RolPersonaCaso rolPersonaActual = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idPersonaAsignada, idCaso);
		if(UtilDominios.NOMBRAMIENTO_POR_LA_CCB.equals(rolPersonaActual.getTipoNombramiento())){
			parGralPlazo = manejadorParametrosGenerales.
					buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		}else{
			parGralPlazo = manejadorParametrosGenerales.
					buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		}
		if(parGralPlazo != null){
			plazoAceptacion = parGralPlazo.getValorNumerico().toString();			
		}		
				
		List<String> args = new ArrayList<>();
		args.add(casoActual.getIdCaso().toString());
		args.add(casoActual.getNombre());
		args.add(casoActual.getSede().getNombre());
		args.add(plazoAceptacion);
		
		List<String> textoEnvio = new ArrayList<String>();
		textoEnvio.add(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO283.toString()), args.toArray()));
		List<RolPersonaCaso> personasEnvio = new ArrayList<RolPersonaCaso>();
		personasEnvio.add(rolPersonaActual);	
		
		this.envioDeCorreo(asunto, null, personasEnvio, null, textoEnvio, idCaso, null, null, false);
		logger.info("Termino el envio del correo ");
	}
	
	@Override
	public void envioCorreoNombramientoConciliador(Long idCaso, Long idConciliador){
		Caso casoActual = manejadorCaso.buscar(idCaso);		
		List<String> args = new ArrayList<>();
		args.add(casoActual.getSede().getCentro().getNombre());
		String asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO275.toString()), args.toArray());
		String urlCCB = "" ,plazoAceptacion = "";
		ParametrosGenerales parGralPlazo;
		ParametrosGenerales parGralUrl = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);
		RolPersonaCaso rolPersonaActual = manejadorRolPersonaCaso.consultarPersonaAsignadaCaso(idConciliador, idCaso);
		if(UtilDominios.NOMBRAMIENTO_POR_LA_CCB.equals(rolPersonaActual.getTipoNombramiento())){
			parGralPlazo = manejadorParametrosGenerales.
					buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		}else{
			parGralPlazo = manejadorParametrosGenerales.
					buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		}
		if(parGralPlazo != null){
			plazoAceptacion = parGralPlazo.getValorNumerico().toString();			
		}		
		if(parGralUrl != null){
			urlCCB = parGralUrl.getValorTexto();			
		}		
		List<String> args2 = new ArrayList<>();
		args2.add(casoActual.getNombre());
		args2.add(casoActual.getSede().getCentro().getNombre());
		args2.add(plazoAceptacion);
		args2.add(urlCCB);
		List<String> textoEnvio = new ArrayList<String>();
		textoEnvio.add(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO276.toString()), args2.toArray()));
		List<RolPersonaCaso> personasEnvio = new ArrayList<RolPersonaCaso>();
		personasEnvio.add(rolPersonaActual);
	
		this.envioDeCorreo(asunto, null, personasEnvio, null, textoEnvio, idCaso, null, null, false);
		
		
	}
	
	

	@Override
	public void envioCorreoCasoSinConciliador(Long idCaso){
		Caso casoActual = manejadorCaso.buscar(idCaso);		

		List<String> roles = new ArrayList<String>();
		roles.add(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		List<Long> centros = new ArrayList<Long>(Arrays.asList(casoActual.getSede().getIdCentro()));
		List<Persona> analistasConciliacion = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(roles,centros, new Date());
		if(!analistasConciliacion.isEmpty()){
			String asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO278.toString()));
			
			List<String> args = new ArrayList<>();		
			args.add(casoActual.getIdCaso().toString());
			args.add(casoActual.getNombre());
			List<String> textoEnvio = new ArrayList<String>();
			textoEnvio.add(String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO277.toString()), args.toArray()));		
			this.envioDeCorreo(asunto, analistasConciliacion, null, null, textoEnvio, idCaso, null, null, false);
		}
		
		
	}
	
	@Override
	public List<CorreoElectronicoDTO> envioCitacionCorreo( EnvioCorreoElectronicoDTO correo ){
		List<CorreoElectronicoDTO> correosNotificados = new ArrayList<CorreoElectronicoDTO>();
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = convertidorParametros(correo);
		if( !enviarCorreo( parametrosEnvioCorreoDTO )){
			// creacion del evento (ver CON-F-055)
			String nombreUsuario = UtilConstantes.USUARIO_DEFECTO_SIMASC;
			if (appContext.getContextoSesion() != null && appContext.getContextoSesion().getIdUsuario() != null) {
				nombreUsuario = appContext.getContextoSesion().getNombreUsuario();
			}
			List<String> estadosAudienciaCaso = Arrays.asList(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

			List<String> args = new ArrayList<>();
			args.add(manejadorAudiencia.obtenerNumeroAudienciasCaso(parametrosEnvioCorreoDTO.getIdCaso(), estadosAudienciaCaso).toString());
			args.add(manejadorPersona.buscar(correo.getDestinatarios().get(0)).getNombreCompleto());
			args.add(correo.getCorreosElectronicos().get(0).getDireccion());
			String observaciones = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO320.toString()),
					args.toArray()));
			eventoFacade.registrarEvento(parametrosEnvioCorreoDTO.getIdCaso(), UtilDominios.TIPO_EVENTO_NOTIFICACION_CORREO_ELECTRONICO_CERTIFICADO,
										 observaciones, nombreUsuario);
			correosNotificados.addAll(correo.getCorreosElectronicos());
		} 
		return correosNotificados;
	}
	
	
	@Override
	public String enviarCorreoElectronico( EnvioCorreoElectronicoDTO correo ){
		
		ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO = this.convertidorParametros(correo);
		if(correo.getPersonasEnvio() != null && !correo.getPersonasEnvio().isEmpty()){
			for (PersonaBasicaDTO personaFor : correo.getPersonasEnvio() ) {
				List<CorreoElectronicoDTO> personasEnvio = new ArrayList<CorreoElectronicoDTO>();
				CorreoElectronicoDTO correoEnvio = new CorreoElectronicoDTO();
				correoEnvio.setDireccion(personaFor.getCorreoElectronico());
				correoEnvio.setIdPersona(personaFor.getIdPersona());	
				personasEnvio.add(correoEnvio);
				parametrosEnvioCorreoDTO.setRolPersonaCaso(personasEnvio);
				this.enviarCorreo(parametrosEnvioCorreoDTO);				
			}
			
		}else{
			boolean noEnviado = this.enviarCorreo(parametrosEnvioCorreoDTO);	
			if (parametrosEnvioCorreoDTO.getCartaPersona() != null) {
				cartaPersonaFacade.confirmacionNotificacionArbitro(parametrosEnvioCorreoDTO.getCartaPersona(), !noEnviado);
			}
		}

		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoRolPersonaCasoFacade#notificarSecretariaConciliacion(com.ccb.simasc.transversal.entidades.
	 * CorreoRolPersonaCaso)
	 */
	@Override
	public void notificarSecretariaConciliacion( CasoDTO casoDTO, Long idAudiencia ){
		List<String> argsAsunto = new ArrayList<>();
		argsAsunto.add( casoDTO.getIdCaso().toString() );
		String asunto = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO131.toString()),
				argsAsunto.toArray()));

		List<String> argsTexto = new ArrayList<String>();
		argsTexto.add( casoDTO.getIdCaso().toString() );
		argsTexto.add( casoDTO.getNombre() );
		String texto = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO130.toString()),
				argsTexto.toArray()));
		List<String> textoCorreo = new ArrayList<String>();
		textoCorreo.add(texto);
		
		List<String> nombreRoles = new ArrayList<String>();
		nombreRoles.add(UtilDominios.ROL_PERSONA_SECRETARIA_DE_CONCILIACION);
		List<Persona> personas = manejadorPersonaSede.consultarPersonaPorSedeRol(casoDTO.getIdSede(), nombreRoles);
		envioDeCorreo(asunto, personas,	null, null, textoCorreo, casoDTO.getIdCaso(), null, idAudiencia, false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoRolPersonaCasoFacade#notificarJefeAnalistaSolicitudCambioLista(com.ccb.simasc.transversal.entidades.
	 * CorreoRolPersonaCaso)
	 */
	@Override
	public void notificarJefeAnalistaSolicitudCambioLista( SolicitudPrestador solicitudCambioLista, boolean solicitudDeArbitraje, List<Long> idCentros ){
		
		String nombreRol = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA,	solicitudCambioLista.getRolPersona().getRol().getNombre());
		Persona solicitante = manejadorPersona.buscar(solicitudCambioLista.getRolPersona().getIdPersona());
		List<String> argsTexto = new ArrayList<String>();
		argsTexto.add( solicitante.getNombreCompleto() );
		argsTexto.add( nombreRol );
		String texto = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO400.toString()),
				argsTexto.toArray()));
		List<String> textoCorreo = new ArrayList<String>();
		textoCorreo.add(texto);
		
		List<String> nombreRolesNotificar = new ArrayList<String>();
		if ( solicitudDeArbitraje ){			
			nombreRolesNotificar.add(UtilDominios.ROL_PERSONA_ASISTENTE_ARBITRO );
			nombreRolesNotificar.add( UtilDominios.ROL_PERSONA_JEFE_DE_ARBITRAJE );
		} else{
			nombreRolesNotificar.add(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION );
			nombreRolesNotificar.add( UtilDominios.ROL_PERSONA_JEFE_DE_CONCILIACION );
		}
		List<Persona> personasNotificar = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(nombreRolesNotificar, idCentros, new Date() );
		
		List<DocumentoDTO> adjuntos = null;
		if(solicitudCambioLista.getIdDocumento() != null){
			adjuntos = new ArrayList<DocumentoDTO>();
			DocumentoDTO adjunto = new DocumentoDTO();
			adjunto.setIdDocumento(solicitudCambioLista.getIdDocumento());
			adjuntos.add(adjunto);			
		}
				
		envioDeCorreo(asuntoCorreoSolicitudCambioLista, personasNotificar, null, null, textoCorreo, null, adjuntos, null, false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoRolPersonaCasoFacade#notificarJefeAnalistaSolicitudCambioLista(com.ccb.simasc.transversal.entidades.
	 * CorreoRolPersonaCaso)
	 */
	@Override
	public void notificarSolicitanteCambioLista( SolicitudPrestador solicitudPrestador ){
		
		String nombreRol = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, solicitudPrestador.getRolPersona().getRol().getNombre());
		String nombreEstado = dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_SOLICITUD_PRESTADOR, solicitudPrestador.getEstadoSolicitud());
		List<String> argsTexto = new ArrayList<String>();
		argsTexto.add( nombreRol );
		argsTexto.add( nombreEstado );
		argsTexto.add( solicitudPrestador.getObservaciones() );
		String texto = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO401.toString()),
				argsTexto.toArray()));
		List<String> textoCorreo = new ArrayList<String>();
		textoCorreo.add(texto);
		
		List<Persona> personasNotificar = new ArrayList<Persona>();
		personasNotificar.add( manejadorPersona.buscar( solicitudPrestador.getRolPersona().getIdPersona()) );
		
		envioDeCorreo(asuntoRespuestaSolicitudCambioLista, personasNotificar, null, null, textoCorreo, null, null, null, false);
	}
	
	
	@Override
	public boolean enviarCorreoAlerta(ParametrosEnvioCorreoDTO parametrosEnvio) throws SIMASCNegocioExcepcion {
				


		boolean envioCorrecto = false;
		// Consultar parametros
		ParametrosGenerales parametroTipoCorreoTipoContenido = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.CORREO_TIPO_CONTENIDO);
		ParametrosGenerales parametroTipoCorreoLlave = manejadorParametrosGenerales
				.buscar(UtilParamGenerales.CORREO_LLAVE);
		
		if(parametroTipoCorreoLlave == null || parametroTipoCorreoTipoContenido == null){
			return false;
		}

		EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
		EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
		// Se alimentan los valores parametrizables
		inDTO.setLlave(parametroTipoCorreoLlave.getValorTexto());
		inDTO.setTipoContenido(parametroTipoCorreoTipoContenido.getValorTexto());

		// Remitente
		Persona sistema = manejadorPersona.consultarPersonasPorTipoPersona(UtilDominios.TIPO_PERSONA_SISTEMA,
				UtilDominios.ESTADO_PERSONA_ACTIVO);
		List<CorreoElectronico> correoEmisor = correoElectronicoFacade.consultaCorreosPersona(sistema.getIdPersona());
		inDTO.setDe(correoEmisor.get(0).getDireccion());
		//inDTO.setDe(parametrosEnvio.getRemitente().getDireccion());

		// Asunto
		inDTO.setAsunto( parametrosEnvio.getAsunto());

		// Destinos
		String destinatario = null;

		if (!parametrosEnvio.getPersonasEnvio().isEmpty() && parametrosEnvio.getPersonasEnvio().get(0).getCorreoElectronico() != null ){			
					destinatario = parametrosEnvio.getPersonasEnvio().get(0).getCorreoElectronico();
			}

		try {
			if(destinatario != null){
					ArrayOfString listaDestinos = new ArrayOfString();
						listaDestinos.getString().clear();
						listaDestinos.getString().add(destinatario);
						inDTO.setPara(listaDestinos);
						inDTO.setContenido(parametrosEnvio.getCuerpoCorreo().get(0));
						try {
							servicio.envioCorreo(inDTO);
							envioCorrecto = true;
						
						} catch (SOAPFaultException ex) {
							logger.error(UtilConstantes.ERROR_CORREO  + " Alerta",ex);
				
				}
			}
		} catch (UnresolvedAddressException e) {
			logger.error(UtilConstantes.ERROR_CORREO  + " Alerta", e);
		}

		return envioCorrecto;
	}

	/*
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * ICorreoRolPersonaCasoFacade#notificarFalloReparto(com.ccb.simasc.transversal.entidades.
	 * CorreoRolPersonaCaso)
	 */
	@Override
	public void notificarFalloReparto( Long idCaso, String detallesNoReparto ){
		
		Caso caso = casoFacade.buscar( idCaso );
		List<String> argsTexto = new ArrayList<String>();
		argsTexto.add( caso.getIdCaso().toString() );
		argsTexto.add( caso.getNombre() );
		argsTexto.add( detallesNoReparto.toLowerCase() );
		String texto = (String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR484.toString()),
										argsTexto.toArray()));
		List<String> textoCorreo = new ArrayList<String>();
		textoCorreo.add(texto);
		
		List<Persona> personasNotificar = manejadorRolPersona.consultarPesonasPorRolPersonaCentro(Arrays.asList(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION),
																Arrays.asList(caso.getSede().getIdCentro()), new Date());
		
		envioDeCorreo(asuntoRepartoNoExitoso, personasNotificar, null, null, textoCorreo, caso.getIdCaso(), null, null, false);
	}
	
	// protected region metodos adicionales end
	
}
