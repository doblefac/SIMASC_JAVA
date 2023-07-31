package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class ReporteCasosDevueltosControlLegalidadDTO {
	private Long idCaso;
	private String tipoCaso;
	private String nombreCaso;
	private String nombreConciliador;
	private String nombreAnalista;
	private Date fechaDevolucion;
	private String causalDevolucion;
	private Date fechaCorrecion;
	private String corrige;
	private String observaciones;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
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
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getCausalDevolucion() {
		return causalDevolucion;
	}
	public void setCausalDevolucion(String causalDevolucion) {
		this.causalDevolucion = causalDevolucion;
	}
	public String getCorrige() {
		return corrige;
	}
	public void setCorrige(String corrige) {
		this.corrige = corrige;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the fechaCorrecion
	 */
	public Date getFechaCorrecion() {
		return fechaCorrecion;
	}
	/**
	 * @param fechaCorrecion the fechaCorrecion to set
	 */
	public void setFechaCorrecion(Date fechaCorrecion) {
		this.fechaCorrecion = fechaCorrecion;
	}
	
}
