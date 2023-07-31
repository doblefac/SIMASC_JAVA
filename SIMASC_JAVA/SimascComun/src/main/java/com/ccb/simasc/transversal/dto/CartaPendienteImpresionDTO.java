package com.ccb.simasc.transversal.dto;

public class CartaPendienteImpresionDTO {
	
	Long idCarta;
	Long numeroCaso;
	String nombreParte;
	String conciliador;
	String indicadorCorreoDevuelto;
	
	public Long getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(Long idCarta) {
		this.idCarta = idCarta;
	}
	public Long getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(Long numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public String getConciliador() {
		return conciliador;
	}
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	public String getIndicadorCorreoDevuelto() {
		return indicadorCorreoDevuelto;
	}
	public void setIndicadorCorreoDevuelto(String indicadorCorreoDevuelto) {
		this.indicadorCorreoDevuelto = indicadorCorreoDevuelto;
	}
	
	
}
