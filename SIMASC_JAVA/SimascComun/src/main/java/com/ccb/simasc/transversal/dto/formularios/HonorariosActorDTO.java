package com.ccb.simasc.transversal.dto.formularios;

import java.math.BigDecimal;

/**
 * DTO para manejar los actores en los honorarios
 * @author fguzman
 *
 */
public class HonorariosActorDTO {
	
	private String actor;
	private String rol;
	private BigDecimal valor;
	private String porcentaje;
	
	
	
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	
	
}
