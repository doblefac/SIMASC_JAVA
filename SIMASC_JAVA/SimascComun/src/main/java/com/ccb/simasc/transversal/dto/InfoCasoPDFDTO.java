package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class InfoCasoPDFDTO {
	
	private long idCaso;
	private String hechos;
	private String pretensiones;
	private String tipoCuantia;
	private String valorPretensiones;
	private String inicioConflicto;
	private String asuntoConciliacion;
	private String sede;
	private String centro;
	private Date fechaRadicacion;
	
	public InfoCasoPDFDTO() {
		
	}

	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	public String getHechos() {
		return hechos;
	}

	public void setHechos(String hechos) {
		this.hechos = hechos;
	}

	public String getPretensiones() {
		return pretensiones;
	}

	public void setPretensiones(String pretensiones) {
		this.pretensiones = pretensiones;
	}

	public String getTipoCuantia() {
		return tipoCuantia;
	}

	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}

	public String getValorPretensiones() {
		return valorPretensiones;
	}

	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}

	public String getInicioConflicto() {
		return inicioConflicto;
	}

	public void setInicioConflicto(String inicioConflicto) {
		this.inicioConflicto = inicioConflicto;
	}

	public String getAsuntoConciliacion() {
		return asuntoConciliacion;
	}

	public void setAsuntoConciliacion(String asuntoConciliacion) {
		this.asuntoConciliacion = asuntoConciliacion;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}


	 
	
}
