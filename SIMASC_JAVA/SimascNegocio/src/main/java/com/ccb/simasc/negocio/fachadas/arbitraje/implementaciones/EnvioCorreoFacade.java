package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.ManejadorAcuse;
import com.ccb.simasc.integracion.manejadores.ManejadorAdjunto;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorInvitado;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlmacenamientoDocumentosFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEnvioCorreoFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;
import com.ccb.simasc.transversal.entidades.Adjunto;
import com.ccb.simasc.transversal.entidades.AdjuntoPK;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class EnvioCorreoFacade implements IEnvioCorreoFacade {
	private static final Logger logger = LogManager.getLogger(EnvioCorreoFacade.class.getName());

	private static final String USUARIO_MODIFICACION = "USUARIO_MODIFICACION";
	
	@Resource
	private UserTransaction userTransaction;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;
	
	@EJB
	private ManejadorAdjunto  manejadorAdjunto;
	
	@EJB
	private ManejadorDocumento manejadorDocumento;
	
	@EJB
	private ManejadorInvitado manejadorInvitado;
	
	@EJB	
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private IAlmacenamientoDocumentosFacade almacenamientoDocumentosFacade; 
	
	@EJB
	private ManejadorAcuse manejadorAcuse;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	
	@Override
	public void registrarFalloEnvioCorreo(CorreoElectronicoDTO correoElectronicoDTO,
			ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO, Long idCaso, String causaFallo) throws Exception {
		
		
		try {
			userTransaction.begin();
			
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
				
				userTransaction.commit();
				
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
						throw new SIMASCNegocioExcepcion(
								MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR215.toString()));
					}
				}
			}
			
			
		}catch (SIMASCNegocioExcepcion e) {
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			try {
				userTransaction.rollback();
			} catch (Exception e1) {
				logger.error("Error: ", e1);
			}
			logger.error("Error: ", e);
		}
		
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
	 * Genera el asunto del caso. Si el correo no es certificado se ignora el parametro
	 * idCaso.
	 * 
	 * @param idCaso
	 * @param asunto
	 * @param isCertificado
	 * @return
	 */
	private String generarAsunto(Long idCaso, String asunto, Boolean isCertificado) {		
		
		StringBuilder sB = new StringBuilder();
		/**
		 * 21/02/2020
		 * Se elimina el prefijo REF-C-XX ,REF-A-XX,REF:idPersonaCarta
		 */
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
		sB.append(asunto);
		return sB.toString();
	}



}
