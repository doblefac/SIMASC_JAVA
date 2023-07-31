package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO que contiene la informacion asociada a correos
 * @author dpachon
 *
 */
public class InformacionCorreoDTO {	
	
	private Long idCaso;
	private Long idCorreoRolPersonaCaso;
	private Long idPersona;
	private String tipoCaso;
	private String nombreCaso;
	private String nombreParte;
	private Boolean parteNotificada;
	private Long idAudiencia;
	private Date fechaInicial;
	private Date fechaFinal;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdCorreoRolPersonaCaso() {
		return idCorreoRolPersonaCaso;
	}
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso) {
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public Boolean getParteNotificada() {
		return parteNotificada;
	}
	public void setParteNotificada(Boolean parteNotificada) {
		this.parteNotificada = parteNotificada;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
}
