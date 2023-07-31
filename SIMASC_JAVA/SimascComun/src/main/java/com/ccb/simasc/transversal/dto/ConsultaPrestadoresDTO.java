package com.ccb.simasc.transversal.dto;

/**
 * DTO que contiene la informacion de la consulta de prestadores de servicio del caso de uso ADM-C-004
 * @author prendon
 *
 */
public class ConsultaPrestadoresDTO {
	private Long idPersona;
	private Long idRol;
	private String nombreRol;
	private Long idMateria;
	private String nombrePrestador;
	private String apellidoPrestador;
	private String numeroDocumento;
	private String nombreLista;
	private String nombrePersonaJuridica;
	private String materias;
	
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public String getNombrePrestador() {
		return nombrePrestador;
	}
	public void setNombrePrestador(String nombrePrestador) {
		this.nombrePrestador = nombrePrestador;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombreLista() {
		return nombreLista;
	}
	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	public String getNombrePersonaJuridica() {
		return nombrePersonaJuridica;
	}
	public void setNombrePersonaJuridica(String nombrePersonaJuridica) {
		this.nombrePersonaJuridica = nombrePersonaJuridica;
	}
	public String getMaterias() {
		return materias;
	}
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getApellidoPrestador() {
		return apellidoPrestador;
	}
	public void setApellidoPrestador(String apellidoPrestador) {
		this.apellidoPrestador = apellidoPrestador;
	}
	
}
