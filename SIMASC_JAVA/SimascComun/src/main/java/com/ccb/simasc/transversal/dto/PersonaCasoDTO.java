package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

public class PersonaCasoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Long idPersona;
	Integer cantidadCasos;
	
	public PersonaCasoDTO(Long idPersona, int cantidadCasos) {
		super();
		this.idPersona = idPersona;
		this.cantidadCasos = cantidadCasos;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Integer getCantidadCasos() {
		return cantidadCasos;
	}
	public void setCantidadCasos(Integer cantidadCasos) {
		this.cantidadCasos = cantidadCasos;
	}
}
