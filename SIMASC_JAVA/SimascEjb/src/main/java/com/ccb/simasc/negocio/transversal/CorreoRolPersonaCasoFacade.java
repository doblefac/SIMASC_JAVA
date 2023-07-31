package com.ccb.simasc.negocio.transversal;

import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.xml.ws.soap.SOAPFaultException;


import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.comun.fachada.implementacion.DominioFacade;
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
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

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
public class CorreoRolPersonaCasoFacade extends AccesoFacade<CorreoRolPersonaCaso, Long, CorreoRolPersonaCasoDTO> {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	private static final Logger logger = LogManager.getLogger(CorreoRolPersonaCasoFacade.class.getName());

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
	private DominioFacade dominioFacade;

	@EJB
	private ManejadorAcuse manejadorAcuse;
	
	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	
	@EJB
	private ManejadorPersonaSede manejadorPersonaSede;
	
	@EJB
	private CorreoElectronicoFacade correoElectronicoFacade;
	

	
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
			logger.error(UtilConstantes.ERROR_CORREO, e);
		}
		
		return falloEnvio;
	}
	
	public boolean enviarCorreo(ParametrosEnvioCorreoDTO parametrosEnvio) throws SIMASCNegocioExcepcion {
				
		
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

		// Destinos
		List<String> destinos = new ArrayList<>();

		if (parametrosEnvio.getPersonas() != null)
			for (CorreoElectronicoDTO persona : parametrosEnvio.getPersonas()) {
				if (persona.getDireccion() != null && !persona.getDireccion().isEmpty())
					destinos.add(persona.getDireccion());
			}
		
		// Verifica si el correo electrÃ³nico es certificado y si es asÃ­ agrega
		// el sufijo ".rpost.org" a las direcciones de los destinatarios
		if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado()) {
			
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
//							if(parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado() && parametrosEnvio.getIdCaso() != null  && i < parametrosEnvio.getRolPersonaCaso().size()){
//								correo = persistirCorreoRolPersonaCaso(parametrosEnvio,parametrosEnvio.getRolPersonaCaso().get(i) );
//								String asunto  = this.generarAsunto(parametrosEnvio, correo.getIdCorreoRolPersonaCaso());
//								correo.setAsunto(asunto);
//								manejadorCorreoRolPersonaCaso.actualizar(correo);
//								inDTO.setAsunto(asunto);
//							}
							servicio.envioCorreo(inDTO);
						} catch (SOAPFaultException ex) {
							logger.error(UtilConstantes.ERROR_CORREO, ex);
							if (parametrosEnvio.getCertificado() != null && parametrosEnvio.getCertificado()) {
								if (parametrosEnvio.getRolPersonaCaso() != null && 
										!parametrosEnvio.getRolPersonaCaso().isEmpty() && parametrosEnvio.getIdCaso() != null){
									falloEnvio = true;
									
									try {
										if(correo != null){
											correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_INACTIVO);
											manejadorCorreoRolPersonaCaso.actualizar(correo);
										}
//										envioCorreoFacade.registrarFalloEnvioCorreo(parametrosEnvio.getRolPersonaCaso().get(i), parametrosEnvio,
//												parametrosEnvio.getIdCaso(), ExceptionUtils.getFullStackTrace(ex));
									} catch (Exception e) {
										
										logger.error("Error: ", e);
									}
									/*registrarFalloEnvioCorreo(parametrosEnvio.getRolPersonaCaso().get(i), parametrosEnvio,
											parametrosEnvio.getIdCaso(), ExceptionUtils.getFullStackTrace(ex));*/									
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
							logger.error(UtilConstantes.ERROR_CORREO  + " Alerta", ex);
				
				}
			}
		} catch (UnresolvedAddressException e) {
			logger.error(UtilConstantes.ERROR_CORREO  + " Alerta", e);
		}

		return envioCorrecto;
	}

		
}
