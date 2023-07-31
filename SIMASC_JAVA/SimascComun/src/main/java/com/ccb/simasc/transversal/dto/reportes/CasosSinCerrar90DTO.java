package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class CasosSinCerrar90DTO {
	private long codigoCaso;
	private Date fechaRadicacion;
	private String nombreCaso;
	private String conciliador;
	private int numeroDias;
	private Date fechaProrroga;
	private String observaciones;
	
	public CasosSinCerrar90DTO() {
		super();
	}

	public long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	public String getConciliador() {
		return conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	public Date getFechaProrroga() {
		return fechaProrroga;
	}

	public void setFechaProrroga(Date fechaProrroga) {
		this.fechaProrroga = fechaProrroga;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	

}
