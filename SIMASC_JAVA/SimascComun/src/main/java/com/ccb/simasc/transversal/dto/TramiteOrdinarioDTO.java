package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class TramiteOrdinarioDTO {
	private Long idCaso;
	private Long idConciliador;
	private Long idFacturacionCaso;
	private String nombreCaso;
	private Date fechaCierre;
	private String resultado;
	private String conciliador;
	private Double valorCobrar;
	private Boolean aprobado;
	private String observaciones;
	private Date fechaNoAprobacion;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdConciliador() {
		return idConciliador;
	}
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}
	public Long getIdFacturacionCaso() {
		return idFacturacionCaso;
	}
	public void setIdFacturacionCaso(Long idFacturacionCaso) {
		this.idFacturacionCaso = idFacturacionCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getConciliador() {
		return conciliador;
	}
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	public Double getValorCobrar() {
		return valorCobrar;
	}
	public void setValorCobrar(Double valorCobrar) {
		this.valorCobrar = valorCobrar;
	}
	public Boolean getAprobado() {
		return aprobado;
	}
	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFechaNoAprobacion() {
		return fechaNoAprobacion;
	}
	public void setFechaNoAprobacion(Date fechaNoAprobacion) {
		this.fechaNoAprobacion = fechaNoAprobacion;
	}
	
}
