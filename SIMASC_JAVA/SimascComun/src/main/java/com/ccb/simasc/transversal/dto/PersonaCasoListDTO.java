package com.ccb.simasc.transversal.dto;

/**
 * DTO que contiene la informacion de las personas asociadas al caso cuyo rol
 * esta habilitado para el envio de cartas (CONF094 Seleccion manual del tipo de
 * carta a generar).
 * 
 * @author aperez.
 *
 */
public class PersonaCasoListDTO {

	private Long idPersona;
	/**
	 * Nombre de la persona asociada al caso.
	 */
	private String nombrePersona;
	/**
	 * Nombre del rol de la persona.
	 */
	private String nombreRol;
	/**
	 * indica si la persona tiene registrado un correo electronico
	 */
	private boolean tieneCorreo;
	
	
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

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public boolean isTieneCorreo() {
		return tieneCorreo;
	}

	public void setTieneCorreo(boolean tieneCorreo) {
		this.tieneCorreo = tieneCorreo;
	}

	

}
