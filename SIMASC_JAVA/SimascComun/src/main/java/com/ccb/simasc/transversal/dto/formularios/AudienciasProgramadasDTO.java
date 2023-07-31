package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DTO para el control de asistencia a las audiencias por parte de los conciliadores
 * @author jnmartinez
 *
 */
public class AudienciasProgramadasDTO {

	private String sede;
	private Long idCaso;
	private String nombre;
	private String nombrePersona;
	private Long idAgendaPersona;
	private Date fechaInicio;
	private String observaciones;
	private Boolean cumplioHorario;
	private Boolean valido;
	private String estado;
	
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
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nombrePersona
	 */
	public String getNombrePersona() {
		return nombrePersona;
	}
	/**
	 * @param nombrePersona the nombrePersona to set
	 */
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	/**
	 * @return the idAgendaPersona
	 */
	public Long getIdAgendaPersona() {
		return idAgendaPersona;
	}
	/**
	 * @param idAgendaPersona the idAgendaPersona to set
	 */
	public void setIdAgendaPersona(Long idAgendaPersona) {
		this.idAgendaPersona = idAgendaPersona;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the cumplioHorario
	 */
	public Boolean getCumplioHorario() {
		return cumplioHorario;
	}
	/**
	 * @param cumplioHorario the cumplioHorario to set
	 */
	public void setCumplioHorario(Boolean cumplioHorario) {
		this.cumplioHorario = cumplioHorario;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the valido
	 */
	public Boolean getValido() {
		return valido;
	}
	/**
	 * @param valido the valido to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}
	
	
	
}
