package com.ccb.simasc.transversal.dto;

public class PartesSeguimientoDTO {
	private String nombreParte;
	private String celular;
	private String telefono;
	private String tipoParte;
	private Long idPersona;
	private Long idTelefono;
	private Long idCelular;
	private Long idRol;
	
	public PartesSeguimientoDTO() {
		super();
	}	

	public String getNombreParte() {
		return nombreParte;
	}

	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoParte() {
		return tipoParte;
	}

	public void setTipoParte(String tipoParte) {
		this.tipoParte = tipoParte;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Long getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Long idTelefono) {
		this.idTelefono = idTelefono;
	}

	public Long getIdCelular() {
		return idCelular;
	}

	public void setIdCelular(Long idCelular) {
		this.idCelular = idCelular;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}	
	
}
