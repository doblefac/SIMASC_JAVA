package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWFirmaDigital;
import com.ccb.simasc.comun.webservice.integracion.interfaces.IClienteSWToken;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorFirmaDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFirmaDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IGestorDocumentalFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.transversal.dto.CasosPendientesFirmaDTO;
import com.ccb.simasc.transversal.dto.DatosFirmaActaConstantciaDTO;
import com.ccb.simasc.transversal.dto.FirmaDocumentoDTO;
import com.ccb.simasc.transversal.dto.PartesEstadoFirmaDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosTokenDTO;
import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.FirmaDocumento;
import com.ccb.simasc.transversal.entidades.FirmaDocumentoPK;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link FirmaDocumento}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class FirmaDocumentoFacade extends AccesoFacade<FirmaDocumento, FirmaDocumentoPK, FirmaDocumentoDTO> implements IFirmaDocumentoFacade {
	private static final Logger logger = LogManager.getLogger(FirmaDocumentoFacade.class.getName());
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;
	
	@EJB
	private IPersonaFacade personaFacade;
	
	@EJB
	private ManejadorFirmaDocumento manejadorFirmaDocumento;
	
	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@EJB
	private ManejadorDocumento manejadorDocumento;
	
	@EJB
	private ManejadorRol manejadorRol;
	
	@EJB
	private ParametrosGeneralesFacade parametrosGeneralesFacade;
	
	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade;
	
	@EJB
	private IGestorDocumentalFacade gestorDocumentalFacade;
	
	@EJB 
	private IClienteSWToken clienteSWToken;
	
	@EJB
	private IClienteSWFirmaDigital clienteFirmaDigital;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorFirmaDocumento;
	}

	@Override
	public FirmaDocumentoDTO transformarSinDependencias(FirmaDocumento obj) {
		FirmaDocumentoDTO dto = new FirmaDocumentoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public FirmaDocumentoDTO transformarConDependencias(FirmaDocumento obj) {
		FirmaDocumentoDTO dto = new FirmaDocumentoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public FirmaDocumento transformarEntidadConDependencias(FirmaDocumento obj) {
		FirmaDocumento dto = new FirmaDocumento();
		dto = new FirmaDocumentoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public FirmaDocumento transformarEntidadSinDependencias(FirmaDocumento obj) {
		FirmaDocumento dto = new FirmaDocumento();
		dto = new FirmaDocumentoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public String solicitarTokenClaveSegura(DatosTokenDTO datosToken){
		String idAplicacion = parametrosGeneralesFacade.consultarPorCodigo(UtilParamGenerales.CODIGO_ID_APLICACION_TOKEN).getValorTexto();
		String idSede = parametrosGeneralesFacade.consultarPorCodigo(UtilParamGenerales.CODIGO_ID_SEDE_TOKEN).getValorTexto();
		datosToken.setIdAplicacion(idAplicacion);
		datosToken.setSede(idSede);
		return clienteSWToken.solicitarTokenClaveSegura(datosToken);
		
	}
	
	@Override
	public List<PartesEstadoFirmaDTO> consultarFirmaPartes(Long idCaso, Long idDocumento){
		return manejadorFirmaDocumento.consultarFirmaPartes(idCaso, idDocumento);
	}

	@Override
    public List<CasosPendientesFirmaDTO> casosPendientesFirmaConciliador(Long idPersona){
    	return manejadorFirmaDocumento.casosPendientesFirmaConciliador(idPersona);
    }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IFirmaDocumentoFacade#habilitarPartes(java.util.List, java.lang.Long)
	 */
	@Override
	public void habilitarPartes(List<PartesEstadoFirmaDTO> partesHabilitadas, Long idDocumento) {
		for (PartesEstadoFirmaDTO parteEstadoFirmaDTO : partesHabilitadas) {
			FirmaDocumentoPK pk = new FirmaDocumentoPK(idDocumento, parteEstadoFirmaDTO.getIdRol(),
					parteEstadoFirmaDTO.getIdPersona(), parteEstadoFirmaDTO.getIdCaso());
			FirmaDocumento firma = buscar(pk);
			if(firma != null && !UtilDominios.ESTADO_DOCUMENTO_FIRMADO.equals(firma.getEstado())) {
				firma.setEstado(parteEstadoFirmaDTO.getSolicitarCertificado() ? UtilDominios.ESTADO_DOCUMENTO_SOLICITADO : UtilDominios.ESTADO_DOCUMENTO_SIN_SOLICITUD);
				actualizar(firma);					
			} else {
				firma = new FirmaDocumento();
				firma.setEstado(parteEstadoFirmaDTO.getSolicitarCertificado() ? UtilDominios.ESTADO_DOCUMENTO_SOLICITADO
						: UtilDominios.ESTADO_DOCUMENTO_SIN_SOLICITUD);
				firma.setFirmaDocumentoPK(pk);
				firma.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				firma.setFechaEmision(new Date());
				crear(firma);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFirmaDocumentoFacade#obtenerParteFirmante(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<FirmaDocumento> obtenerParteFirmante(Long idPersona, Long idCaso) {
		return manejadorFirmaDocumento.obtenerParteFirmante(idPersona, idCaso);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IFirmaDocumentoFacade#firmarActaConstancia(com.ccb.simasc.transversal.dto
	 * .DatosFirmaActaConstantciaDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public void firmarActaConstancia(DatosFirmaActaConstantciaDTO datosFirmaActaConstantciaDTO) {		
		String direccionIP = null;
		try {
			direccionIP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			logger.error("Error: ", e1);
			throw new SIMASCNegocioExcepcion(e1.getMessage());		
		}
		datosFirmaActaConstantciaDTO.setDireccionIPOrigen(direccionIP);
		ParametrosGenerales MAC = parametrosGeneralesFacade.consultarPorNombre(datosFirmaActaConstantciaDTO.getDireccionIPOrigen());
		datosFirmaActaConstantciaDTO.setDireccionMACOrigen(MAC.getValorTexto());
		// Búsqueda de la persona que firma por su identificador
		Persona persona = manejadorPersona.buscar(datosFirmaActaConstantciaDTO.getIdPersona());
		InputStream is = null;
		if (persona != null) {
			Documento documento = manejadorDocumento.buscar(datosFirmaActaConstantciaDTO.getIdDocumento());
			String nombreDocumento = documento.getUrl().substring(documento.getUrl().indexOf(documento.getNombre()));
			String ruta =  documento.getUrl().substring(0, documento.getUrl().indexOf(documento.getNombre()));	
			try {
				byte[] bytes = clienteFirmaDigital.firmarPDF(ruta, nombreDocumento, persona, datosFirmaActaConstantciaDTO, datosFirmaActaConstantciaDTO.getIdRol() != null);
				is = new ByteArrayInputStream(bytes);
				guardarDocumento(documento.getUrl(), is);
			} catch (Exception e) {
				logger.error("Error: ", e);
				throw new SIMASCNegocioExcepcion(e.getMessage());					
			} finally {
				try{
					if(is != null) is.close();
				} catch (Exception ex){
					logger.error("Error: ", ex);
				}
			}
			String observacionesEvento = null;
			// Si el rol se envía dentro de los datos para la firma se asume que
			// es una parte quien esta firmando de lo contrario se asume que es
			// el conciliador
			if (datosFirmaActaConstantciaDTO.getIdRol() != null) {
				FirmaDocumento firma = manejadorFirmaDocumento.buscar(new FirmaDocumentoPK(
						datosFirmaActaConstantciaDTO.getIdDocumento(), datosFirmaActaConstantciaDTO.getIdRol(),
						datosFirmaActaConstantciaDTO.getIdPersona(), datosFirmaActaConstantciaDTO.getIdCaso()));
				firma.setEstado(UtilDominios.ESTADO_DOCUMENTO_FIRMADO);
				firma.setFechaFirma(new Date());
				actualizar(firma);
				observacionesEvento = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO600.toString(),
						persona.getNombreCompleto(), persona.getNumeroDocumento(),
						new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_DIA_MES_ANIO_HORA).format(new Date()));

				// Registro del evento de la firma en la ruta del caso
				eventoFacade.registrarEvento(datosFirmaActaConstantciaDTO.getIdCaso(),
						UtilDominios.TIPO_EVENTO_FIRMA_ELECTRONICA_ACTA, observacionesEvento, null);

				// Notificación del evento de firma electrónica al conciliador
				// del caso
				notificarConciliadorCasoFirmaActaConstancia(datosFirmaActaConstantciaDTO.getIdCaso(), persona);
			} else {
				Rol conciliador = manejadorRol.consultarRolPorNombre(UtilDominios.ROL_PERSONA_CONCILIADOR);
				FirmaDocumentoPK pk = new FirmaDocumentoPK(
						datosFirmaActaConstantciaDTO.getIdDocumento(), conciliador.getIdRol(),
						datosFirmaActaConstantciaDTO.getIdPersona(), datosFirmaActaConstantciaDTO.getIdCaso()); 
				FirmaDocumento firma = new FirmaDocumento();
				firma.setFirmaDocumentoPK(pk);
				firma.setEstado(UtilDominios.ESTADO_DOCUMENTO_FIRMADO);
				firma.setFechaFirma(new Date());
				firma.setFechaEmision(new Date());
				firma.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				crear(firma);
				
				observacionesEvento = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO603.toString(),
						persona.getNombreCompleto(), persona.getNumeroDocumento(),
						new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_DIA_MES_ANIO_HORA).format(new Date()));

				// Registro del evento de la firma en la ruta del caso
				eventoFacade.registrarEvento(datosFirmaActaConstantciaDTO.getIdCaso(),
						UtilDominios.TIPO_EVENTO_FIRMA_ELECTRONICA_ACTA_CONCILIADOR, observacionesEvento, null);
				
				// Notificación del evento de firma electrónica a las partes del
				// caso
				notificarPartesFirmaActaConstancia(datosFirmaActaConstantciaDTO.getIdCaso());
			}

		}

	}
	
	private void guardarDocumento(String ruta, InputStream documento) {
		File nuevoDocumento = new File(ruta);
		gestorDocumentalFacade.escribirDocumentoDisco(documento, nuevoDocumento, ruta);
	}
	
	/**
	 * Método encargado de notificar al conciliador del caso de la firma
	 * electrónica del acta o constancia resultado de la audiencia virtual
	 * 
	 * @param idCaso
	 * @param persona
	 */
	private void notificarConciliadorCasoFirmaActaConstancia(Long idCaso, Persona persona) {
		// Consulta del caso
		Caso caso = manejadorCaso.buscar(idCaso);

		if (caso != null) {
			// Consulta del conciliador asignado al caso
			List<RolPersonaCaso> conciliadores = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
					caso.getIdCaso(), Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO),
					UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);

			if (conciliadores != null && !conciliadores.isEmpty() && conciliadores.size() == 1) {
				// Construcción de los parámetros de la notificación
				String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO601.toString());
				String textoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO602.toString(),
						persona.getNombreCompleto(), persona.getNumeroDocumento(), caso.getIdCaso().toString(),
						caso.getNombre());

				// Envío de la notificación
				correoRolPersonaCasoFacade.envioDeCorreo(asunto, Arrays.asList(persona), null, null,
						Arrays.asList(textoCorreo), caso.getIdCaso(), null, null, false);
			} else {
				throw new SIMASCNegocioExcepcion(
						String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO350.toString())));
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR700.toString())));
		}
	}
	
	/**
	 * Método encargado de notificar a las partes del caso de la firma
	 * electrónica del acta o constancia resultado de la audiencia virtual
	 * 
	 * @param idCaso
	 * @param persona
	 */
	private void notificarPartesFirmaActaConstancia(Long idCaso) {
		// Consulta del caso
		Caso caso = manejadorCaso.buscar(idCaso);

		if (caso != null) {
			// Consulta del conciliador asignado al caso
			List<RolPersonaCaso> conciliadores = manejadorRolPersonaCaso.consultaConciliadoresCasoEstadoNombramiento(
					caso.getIdCaso(), Arrays.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO),
					UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);

			// Consulta de las partes del caso
			List<ParteCasoDTO> partesCaso = personaFacade.consultarPartesConciliacionCaso(idCaso, false, false);

			if (partesCaso != null && !partesCaso.isEmpty() && conciliadores != null && !conciliadores.isEmpty()
					&& conciliadores.size() == 1) {
				Persona personaConciliador = conciliadores.get(0).getPersona();
				
				// Construcción de los parámetros de la notificación
				String asunto = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO604.toString());
				List<Persona> personas = new ArrayList<Persona>();

				for (ParteCasoDTO parteCasoDTO : partesCaso) {
					Persona persona = manejadorPersona.buscar(parteCasoDTO.getIdPersona());
					personas.add(persona);
				}

				if (personaConciliador != null) {
					String textoCorreo = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO605.toString(),
							personaConciliador.getNombreCompleto(), personaConciliador.getNumeroDocumento(),
							caso.getIdCaso().toString(), caso.getNombre());
					
					// Envío de la notificación
					correoRolPersonaCasoFacade.envioDeCorreo(asunto, personas, null, null,
							Arrays.asList(textoCorreo), caso.getIdCaso(), null, null, false);
				}

			} else {
				throw new SIMASCNegocioExcepcion(String
						.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR701.toString())));
			}
		} else {
			throw new SIMASCNegocioExcepcion(
					String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR701.toString())));
		}
	}
	
	// protected region metodos adicionales end
	
}
