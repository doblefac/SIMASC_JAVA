package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * DTO con el cual se manejaran los datos a presentar en la planilla de correspondencia
 * @author jnmartinez
 *
 */
public class PlanillaCorrespondenciaDTO {

	private Long idCartaPersona;
	private Date fecha;
	private Long codigoCaso;
	private String nombreParte;
	private String rol;
	private String direccion;
	private String ciudad;
	private String telefono;
	private Date fechaAudiencia;
	private String sede;
	private String conciliador;
	
	public PlanillaCorrespondenciaDTO() {
		
	}
	
	/**
	 * @return the idCartaPersona
	 */
	public Long getIdCartaPersona() {
		return idCartaPersona;
	}
	/**
	 * @param idCartaPersona the idCartaPersona to set
	 */
	public void setIdCartaPersona(Long idCartaPersona) {
		this.idCartaPersona = idCartaPersona;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the codigoCaso
	 */
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	/**
	 * @param codigoCaso the codigoCaso to set
	 */
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	/**
	 * @return the nombreParte
	 */
	public String getNombreParte() {
		return nombreParte;
	}
	/**
	 * @param nombreParte the nombreParte to set
	 */
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}
	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the fechaAudiencia
	 */
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	/**
	 * @param fechaAudiencia the fechaAudiencia to set
	 */
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	/**
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * @return the conciliador
	 */
	public String getConciliador() {
		return conciliador;
	}
	/**
	 * @param conciliador the conciliador to set
	 */
	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}
	
	
}
