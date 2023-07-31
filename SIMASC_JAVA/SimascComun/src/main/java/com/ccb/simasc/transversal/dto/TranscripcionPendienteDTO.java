package com.ccb.simasc.transversal.dto;

import java.util.Date;

/**
 * 
 * @author jcepeda
 *
 */
public class TranscripcionPendienteDTO {
	
	private Long idTranscripcion;
	private Long codigoCaso;
	private String nombreCaso;
	private Date fechaAudiencia;
	private Date fechaPrevistaEntrega;
	private String auxiliarAdministrativoAsignado;
	private Long numeroAudio;
	private String descripcionDocumento;
	private Date minutosPorTranscribir;
	private Date minutosTranscritos;
	private Date minutosPendientesTranscripcion;
	
	public Long getIdTranscripcion() {
		return idTranscripcion;
	}

	public void setIdTranscripcion(Long idTranscripcion) {
		this.idTranscripcion = idTranscripcion;
	}
	
	public Long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}
	
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	
	public Date getFechaPrevistaEntrega() {
		return fechaPrevistaEntrega;
	}
	
	public void setFechaPrevistaEntrega(Date fechaPrevistaEntrega) {
		this.fechaPrevistaEntrega = fechaPrevistaEntrega;
	}
	
	public String getAuxiliarAdministrativoAsignado() {
		return auxiliarAdministrativoAsignado;
	}
	
	public void setAuxiliarAdministrativoAsignado(String auxiliarAdministrativoAsignado) {
		this.auxiliarAdministrativoAsignado = auxiliarAdministrativoAsignado;
	}
	
	public Long getNumeroAudio() {
		return numeroAudio;
	}
	
	public void setNumeroAudio(Long numeroAudio) {
		this.numeroAudio = numeroAudio;
	}
	
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
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
	
	public Date getMinutosPendientesTranscripcion() {
		return minutosPendientesTranscripcion;
	}
	
	public void setMinutosPendientesTranscripcion(Date minutosPendientesTranscripcion) {
		this.minutosPendientesTranscripcion = minutosPendientesTranscripcion;
	}
	
}
