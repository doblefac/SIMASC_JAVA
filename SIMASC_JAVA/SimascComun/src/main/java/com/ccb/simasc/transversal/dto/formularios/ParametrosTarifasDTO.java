package com.ccb.simasc.transversal.dto.formularios;

import java.math.BigDecimal;

/**
 * DAO que contene la información para el calculo de tarifas
 * 
 * @author dpachon
 */
public class ParametrosTarifasDTO {
	
	private Long idCaso;
	private boolean indeterminado;
	private String cantidadArbitros;
	private	BigDecimal cuantia;
	private String tipoTarifa;
	private Long idServicio;
	private boolean arbitrajeConsumo;
	
	public boolean isIndeterminado() {
		return indeterminado;
	}
	public void setIndeterminado(boolean indeterminado) {
		this.indeterminado = indeterminado;
	}
	public String getCantidadArbitros() {
		return cantidadArbitros;
	}
	public void setCantidadArbitros(String cantidadArbitros) {
		this.cantidadArbitros = cantidadArbitros;
	}
	public BigDecimal getCuantia() {
		return cuantia;
	}
	public void setCuantia(BigDecimal cuantia) {
		this.cuantia = cuantia;
	}
	public String getTipoTarifa() {
		return tipoTarifa;
	}
	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}
	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}
}
