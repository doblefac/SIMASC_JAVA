package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class AudienciasPorFechasDTO {
	//resultado
	private long codigoCaso;
	private long idAudiencia;
	private String descripcionCaso;
	private long audiencia;
	private Date fechaAudiencia;
	private String sede;
	private String conciliador;
	private String estadoAudiencia;
	//fin resultado
	public AudienciasPorFechasDTO() {
		super();
	}

	public long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public String getDescripcionCaso() {
		return descripcionCaso;
	}

	public void setDescripcionCaso(String descripcionCaso) {
		this.descripcionCaso = descripcionCaso;
	}

	public long getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(long audiencia) {
		this.audiencia = audiencia;
	}

	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}

	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getConciliador() {
		return conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public String getEstadoAudiencia() {
		return estadoAudiencia;
	}

	public void setEstadoAudiencia(String estadoAudiencia) {
		this.estadoAudiencia = estadoAudiencia;
	}
	

}



