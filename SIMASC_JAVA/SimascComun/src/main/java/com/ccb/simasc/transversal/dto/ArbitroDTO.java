package com.ccb.simasc.transversal.dto;

/**
 * DTO utilizado en el reporte 23 de Estados Arbitro
 * Contiene información básica de la persona
 * @author jsoto
 *
 */
public class ArbitroDTO {
	
	private Long idPersona;
	//Concatena primerNombre, segundoNombre, primerApellido, segundoApellido
	private String nombreCompleto;
	
	public Long getIdPersona() {
		return idPersona;
	}
	
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}	
	
}
