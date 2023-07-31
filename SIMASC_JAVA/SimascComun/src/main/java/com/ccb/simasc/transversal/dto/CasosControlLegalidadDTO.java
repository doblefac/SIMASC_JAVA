package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class CasosControlLegalidadDTO {

	private Long idCaso;
	private String caso;
	private String conciliador;
	private String analistaConciliador;
	private Long idAnalistaConciliador;
	private String resultadoCaso;
	private Long idServicio; 
	private Date fechaUltimaAudiencia;
	private Date fechaLimiteEstudioCaso;
	private Date fechaRadicacionCaso;
	private String resultadoUltimaAudiencia;

	
	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getConciliador() {
		return conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public String getAnalistaConciliador() {
		return analistaConciliador;
	}

	public void setAnalistaConciliador(String analistaConciliador) {
		this.analistaConciliador = analistaConciliador;
	}

	public String getResultadoCaso() {
		return resultadoCaso;
	}

	public void setResultadoCaso(String resultadoCaso) {
		this.resultadoCaso = resultadoCaso;
	}
	
	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	} 

	public Date getFechaUltimaAudiencia() {
		return fechaUltimaAudiencia;
	}

	public void setFechaUltimaAudiencia(Date fechaUltimaAudiencia) {
		this.fechaUltimaAudiencia = fechaUltimaAudiencia;
	}

	public Date getFechaLimiteEstudioCaso() {
		return fechaLimiteEstudioCaso;
	}

	public void setFechaLimiteEstudioCaso(Date fechaLimiteEstudioCaso) {
		this.fechaLimiteEstudioCaso = fechaLimiteEstudioCaso;
	}

	public Long getIdAnalistaConciliador() {
		return idAnalistaConciliador;
	}

	public void setIdAnalistaConciliador(Long idAnalistaConciliador) {
		this.idAnalistaConciliador = idAnalistaConciliador;
	}
	
	public Date getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}

	public void setFechaRadicacionCaso(Date fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}

	public String getResultadoUltimaAudiencia() {
		return resultadoUltimaAudiencia;
	}

	public void setResultadoUltimaAudiencia(String resultadoUltimaAudiencia) {
		this.resultadoUltimaAudiencia = resultadoUltimaAudiencia;
	}


}
