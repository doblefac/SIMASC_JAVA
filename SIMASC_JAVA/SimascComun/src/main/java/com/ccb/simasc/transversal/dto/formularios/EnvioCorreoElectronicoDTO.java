package com.ccb.simasc.transversal.dto.formularios;

import java.util.List;

import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;

/**
 * DAO que contene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpacho
 */
public class EnvioCorreoElectronicoDTO {
	
	private Long idUsuario;
	private Long idCaso;
	private List<Long> destinatarios;
	private String asunto;
	private String texto;
	private List<Long> idDocumentos;
	private boolean certificado;
	private Long idPlantillaCarta;
	private Long idCarta;
	private List<CorreoElectronicoDTO> correosElectronicos;
	private Long idAudiencia;
	private List<PersonaBasicaDTO> personasEnvio;
	
	public List<Long> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(List<Long> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public List<Long> getIdDocumentos() {
		return idDocumentos;
	}
	public void setIdDocumentos(List<Long> idDocumentos) {
		this.idDocumentos = idDocumentos;
	}
	public boolean isCertificado() {
		return certificado;
	}
	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPlantillaCarta() {
		return idPlantillaCarta;
	}
	public void setIdPlantillaCarta(Long idPlantillaCarta) {
		this.idPlantillaCarta = idPlantillaCarta;
	}
	public Long getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(Long idCarta) {
		this.idCarta = idCarta;
	}
	public List<CorreoElectronicoDTO> getCorreosElectronicos() {
		return correosElectronicos;
	}
	public void setCorreosElectronicos(List<CorreoElectronicoDTO> correosElectronicos) {
		this.correosElectronicos = correosElectronicos;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
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
