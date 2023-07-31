package com.ccb.simasc.negocio.transversal;
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


import com.ccb.simasc.integracion.manejadores.ManejadorAcuse;
import com.ccb.simasc.integracion.manejadores.ManejadorAdjunto;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorInvitado;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.entidades.Adjunto;
import com.ccb.simasc.transversal.entidades.AdjuntoPK;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class EnvioCorreoFacade {

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
	private ManejadorAcuse manejadorAcuse;
	
	@EJB
	private ManejadorCaso manejadorCaso;
	

	
	
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
