package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

public class PersonaMateriaAsignadaDTO implements Serializable{

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idRol;		
	private String codigoRol;
	private String nombreRol;	
	private Long idMateria;		
	private String nombreMateria;
	private Date fechaInicioVigencia;
	private Date fechaFinDeVigencia;
	private Long cantidadDeMaterias; 
	private Long idPersonaServicioMateria;
	private Long idServicio;
	private String nombreServicio;
	private String tipoServicio;
	
	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	/**
	 * @return the nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	/**
	 * @param nombreRol the nombreRol to set
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	/**
	 * @return the idMateria
	 */
	public Long getIdMateria() {
		return idMateria;
	}
	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	/**
	 * @return the nombreMateria
	 */
	public String getNombreMateria() {
		return nombreMateria;
	}
	/**
	 * @param nombreMateria the nombreMateria to set
	 */
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	/**
	 * @return the fechaInicioVigencia
	 */
	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	/**
	 * @param fechaInicioVigencia the fechaInicioVigencia to set
	 */
	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	/**
	 * @return the codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}
	/**
	 * @param codigoRol the codigoRol to set
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	/**
	 * @return the cantidadDeMaterias
	 */
	public Long getCantidadDeMaterias() {
		return cantidadDeMaterias;
	}
	/**
	 * @param cantidadDeMaterias the cantidadDeMaterias to set
	 */
	public void setCantidadDeMaterias(Long cantidadDeMaterias) {
		this.cantidadDeMaterias = cantidadDeMaterias;
	}
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public Long getIdPersonaServicioMateria() {
		return idPersonaServicioMateria;
	}
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria) {
		this.idPersonaServicioMateria = idPersonaServicioMateria;
	}
	public Date getFechaFinDeVigencia() {
		return fechaFinDeVigencia;
	}
	public void setFechaFinDeVigencia(Date fechaFinDeVigencia) {
		this.fechaFinDeVigencia = fechaFinDeVigencia;
	}
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
}
