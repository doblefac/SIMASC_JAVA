package com.ccb.simasc.transversal.dto;

import java.util.Date;

/*
 * DTO que contiene la informacion de la liquidacion de los casos de convenio para el caso de uso CON-C-014
 */
public class LiquidacionCasosConvenioDTO {
	private Long idCaso;
	private String nombreCaso;
	private Date fechaCierre;
	private String resultado;
	private Double valorCaso;
	private Double valorCobrosAdicionales;
	
	
	public Double getValorCaso() {
		return valorCaso;
	}
	public void setValorCaso(Double valorCaso) {
		this.valorCaso = valorCaso;
	}
	public Double getValorCobrosAdicionales() {
		return valorCobrosAdicionales;
	}
	public void setValorCobrosAdicionales(Double valorCobrosAdicionales) {
		this.valorCobrosAdicionales = valorCobrosAdicionales;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
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
	
}