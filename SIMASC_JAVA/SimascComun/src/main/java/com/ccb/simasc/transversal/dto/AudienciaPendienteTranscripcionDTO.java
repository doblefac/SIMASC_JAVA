package com.ccb.simasc.transversal.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AudienciaPendienteTranscripcionDTO {
	
	BigDecimal idCaso;
	BigDecimal idAudiencia;
	BigDecimal idTranscricpion;
	BigDecimal idDocumento;
	String nombreCaso;
	String fechaAudiencia;
	String fechaPrevistaEntrega;
	Date fechaEntregaTranscripcion;
	BigDecimal numeroAudio;
	String descripcion;
	Integer minutosTranscribir;
	BigDecimal minutosTranscritos;
	BigDecimal minutosPendientesTranscripcion;
	private Date fechaInicioTranscripcion;
	
	
	
	public Date getFechaInicioTranscripcion() {
		return fechaInicioTranscripcion;
	}
	public void setFechaInicioTranscripcion(Date fechaInicioTranscripcion) {
		this.fechaInicioTranscripcion = fechaInicioTranscripcion;
	}
	public BigDecimal getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(BigDecimal idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(String fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public String getFechaPrevistaEntrega() {
		return fechaPrevistaEntrega;
	}
	public void setFechaPrevistaEntrega(String fechaPrevistaEntrega) {
		this.fechaPrevistaEntrega = fechaPrevistaEntrega;
	}
	public BigDecimal getNumeroAudio() {
		return numeroAudio;
	}
	public void setNumeroAudio(BigDecimal numeroAudio) {
		this.numeroAudio = numeroAudio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getMinutosTranscritos() {
		return minutosTranscritos;
	}
	public void setMinutosTranscritos(BigDecimal minutosTranscritos) {
		this.minutosTranscritos = minutosTranscritos;
	}
	public Integer getMinutosTranscribir() {
		return minutosTranscribir;
	}
	public void setMinutosTranscribir(Integer minutosTranscribir) {
		this.minutosTranscribir = minutosTranscribir;
	}
	public BigDecimal getMinutosPendientesTranscripcion() {
		return minutosPendientesTranscripcion;
	}
	public void setMinutosPendientesTranscripcion(BigDecimal minutosPendientesTranscripcion) {
		this.minutosPendientesTranscripcion = minutosPendientesTranscripcion;
	}
	public BigDecimal getIdAudiencia() {
		return idAudiencia;
	}
	public void setIdAudiencia(BigDecimal idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	public BigDecimal getIdTranscricpion() {
		return idTranscricpion;
	}
	public void setIdTranscricpion(BigDecimal idTranscricpion) {
		this.idTranscricpion = idTranscricpion;
	}
	public BigDecimal getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(BigDecimal idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Date getFechaEntregaTranscripcion() {
		return fechaEntregaTranscripcion;
	}
	public void setFechaEntregaTranscripcion(Date fechaEntregaTranscripcion) {
		this.fechaEntregaTranscripcion = fechaEntregaTranscripcion;
	}

	
	
}
