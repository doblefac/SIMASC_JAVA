package com.ccb.simasc.transversal.dto;

public class PersonaAleatoriaDTO {

	private Long idPersona;
	private Long idRol;
	private String nombrePersona;
	private Float aleatorio;
	
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public Float getAleatorio() {
		return aleatorio;
	}
	public void setAleatorio(Float aleatorio) {
		this.aleatorio = aleatorio;
	}

	
}
