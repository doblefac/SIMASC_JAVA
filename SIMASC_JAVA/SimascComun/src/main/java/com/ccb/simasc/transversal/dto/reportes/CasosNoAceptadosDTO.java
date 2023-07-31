/**
 * 
 */
package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * Dto de los reportes no aceptados no por el conciliador
 * @author cbenavides
 *
 */
public class CasosNoAceptadosDTO {

	private Long idCaso;
	private Long idServicio;
	private String servicio;
	private String materia;
	private String convenio;
	private String conciliador;
	private Long idPersona;
	private String pronunciamiento;
	private Date fechaPronunciamiento;
	private String codMotivoDeclinacion;
	private String motivoDeclinacion;
	private String observaciones;
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
	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return the materia
	 */
	public String getMateria() {
		return materia;
	}
	/**
	 * @param materia the materia to set
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}
	/**
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
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
	/**
	 * @return the pronunciamiento
	 */
	public String getPronunciamiento() {
		return pronunciamiento;
	}
	/**
	 * @param pronunciamiento the pronunciamiento to set
	 */
	public void setPronunciamiento(String pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	/**
	 * @return the fechaPronunciamiento
	 */
	public Date getFechaPronunciamiento() {
		return fechaPronunciamiento;
	}
	/**
	 * @param fechaPronunciamiento the fechaPronunciamiento to set
	 */
	public void setFechaPronunciamiento(Date fechaPronunciamiento) {
		this.fechaPronunciamiento = fechaPronunciamiento;
	}
	/**
	 * @return the codMotivoDeclinacion
	 */
	public String getCodMotivoDeclinacion() {
		return codMotivoDeclinacion;
	}
	/**
	 * @param codMotivoDeclinacion the codMotivoDeclinacion to set
	 */
	public void setCodMotivoDeclinacion(String codMotivoDeclinacion) {
		this.codMotivoDeclinacion = codMotivoDeclinacion;
	}
	/**
	 * @return the motivoDeclinacion
	 */
	public String getMotivoDeclinacion() {
		return motivoDeclinacion;
	}
	/**
	 * @param motivoDeclinacion the motivoDeclinacion to set
	 */
	public void setMotivoDeclinacion(String motivoDeclinacion) {
		this.motivoDeclinacion = motivoDeclinacion;
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
	
	
	
	
}
