package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

public class ResultadosTarifaInternacionalDTO {

	private String honorariosMinimo;
	private String honorariosMaximo;
	private String gastosAdministrativos;
	private Date fechaTRM;
	private String valorTRM;
	
	public String getHonorariosMinimo() {
		return honorariosMinimo;
	}
	public void setHonorariosMinimo(String honorariosMinimo) {
		this.honorariosMinimo = honorariosMinimo;
	}
	public String getHonorariosMaximo() {
		return honorariosMaximo;
	}
	public void setHonorariosMaximo(String honorariosMaximo) {
		this.honorariosMaximo = honorariosMaximo;
	}
	public String getGastosAdministrativos() {
		return gastosAdministrativos;
	}
	public void setGastosAdministrativos(String gastosAdministrativos) {
		this.gastosAdministrativos = gastosAdministrativos;
	}
	public Date getFechaTRM() {
		return fechaTRM;
	}
	public void setFechaTRM(Date fechaTRM) {
		this.fechaTRM = fechaTRM;
	}
	public String getValorTRM() {
		return valorTRM;
	}
	public void setValorTRM(String valorTRM) {
		this.valorTRM = valorTRM;
	}
}
