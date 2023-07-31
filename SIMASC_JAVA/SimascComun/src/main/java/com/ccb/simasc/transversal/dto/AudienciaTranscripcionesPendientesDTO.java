package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class AudienciaTranscripcionesPendientesDTO {
	
	private Long idCaso;
	private Long idAudiencia;
	private String nombreCaso;
	private Date fechaPrevistaEntrega;
	private Date fechaAudiencia;
	private Date minutosPorTranscribir;
	private Date minutosTranscritos;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFechaPrevistaEntrega() {
		return fechaPrevistaEntrega;
	}
	public void setFechaPrevistaEntrega(Date fechaPrevistaEntrega) {
		this.fechaPrevistaEntrega = fechaPrevistaEntrega;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public Date getMinutosPorTranscribir() {
		return minutosPorTranscribir;
	}
	public void setMinutosPorTranscribir(Date minutosPorTranscribir) {
		this.minutosPorTranscribir = minutosPorTranscribir;
	}
	public Date getMinutosTranscritos() {
		return minutosTranscritos;
	}
	public void setMinutosTranscritos(Date minutosTranscritos) {
		this.minutosTranscritos = minutosTranscritos;
	}

}
