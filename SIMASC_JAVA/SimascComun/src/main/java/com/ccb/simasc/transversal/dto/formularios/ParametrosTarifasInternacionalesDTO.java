package com.ccb.simasc.transversal.dto.formularios;

import java.math.BigDecimal;

public class ParametrosTarifasInternacionalesDTO {
	
	private String cantidadArbitros;
	private	BigDecimal montoDisputa;
	private String modeloTarifa;
	private String moneda;
	private Long idServicio;
	
	public String getCantidadArbitros() {
		return cantidadArbitros;
	}
	public void setCantidadArbitros(String cantidadArbitros) {
		this.cantidadArbitros = cantidadArbitros;
	}
	public BigDecimal getMontoDisputa() {
		return montoDisputa;
	}
	public void setMontoDisputa(BigDecimal montoDisputa) {
		this.montoDisputa = montoDisputa;
	}
	public String getModeloTarifa() {
		return modeloTarifa;
	}
	public void setModeloTarifa(String modeloTarifa) {
		this.modeloTarifa = modeloTarifa;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
}
