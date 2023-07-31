package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;

/**
 * DTO con los parámetros de llamado al método de envío de correo
 * @author ymoreno
 *
 */
public class ParametrosEnvioCorreoDTO {

	//Obligatorio
	CorreoElectronicoDTO remitente;
	List<CorreoElectronicoDTO> rolPersonaCaso;
	List<CorreoElectronicoDTO> invitados;
	List<CorreoElectronicoDTO> personas;
	String tipo; 
	List<DocumentoDTO> adjuntos;
	//Obligatorio si el correo es certificado
	Long idCaso; 
	//Obligatorio
	String asunto; 
	Long idAudiencia; 
	CartaPersonaDTO cartaPersona;
	//Obligatorio
	List<String> cuerpoCorreo;
	Boolean certificado;
	List<PersonaBasicaDTO> personasEnvio;
	
	public CorreoElectronicoDTO getRemitente() {
		return remitente;
	}
	public void setRemitente(CorreoElectronicoDTO remitente) {
		this.remitente = remitente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<DocumentoDTO> getAdjuntos() {
		return adjuntos;
	}
	public void setAdjuntos(List<DocumentoDTO> adjuntos) {
		this.adjuntos = adjuntos;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public CartaPersonaDTO getCartaPersona() {
		return cartaPersona;
	}
	public void setCartaPersona(CartaPersonaDTO cartaPersona) {
		this.cartaPersona = cartaPersona;
	}
	public List<String> getCuerpoCorreo() {
		return cuerpoCorreo;
	}
	public void setCuerpoCorreo(List<String> cuerpoCorreo) {
		this.cuerpoCorreo = cuerpoCorreo;
	}
	public List<CorreoElectronicoDTO> getRolPersonaCaso() {
		return rolPersonaCaso;
	}
	public void setRolPersonaCaso(List<CorreoElectronicoDTO> rolPersonaCaso) {
		this.rolPersonaCaso = rolPersonaCaso;
	}
	public List<CorreoElectronicoDTO> getInvitados() {
		return invitados;
	}
	public void setInvitados(List<CorreoElectronicoDTO> invitados) {
		this.invitados = invitados;
	}
	public List<CorreoElectronicoDTO> getPersonas() {
		return personas;
	}
	public void setPersonas(List<CorreoElectronicoDTO> personas) {
		this.personas = personas;
	}
	/**
	 * @return the certificado
	 */
	public Boolean getCertificado() {
		return certificado;
	}
	/**
	 * @param certificado the certificado to set
	 */
	public void setCertificado(Boolean certificado) {
		this.certificado = certificado;
	}
	/**
	 * @return the personasEnvio
	 */
	public List<PersonaBasicaDTO> getPersonasEnvio() {
		return personasEnvio;
	}
	/**
	 * @param personasEnvio the personasEnvio to set
	 */
	public void setPersonasEnvio(List<PersonaBasicaDTO> personasEnvio) {
		this.personasEnvio = personasEnvio;
	}

	
	
	
}
