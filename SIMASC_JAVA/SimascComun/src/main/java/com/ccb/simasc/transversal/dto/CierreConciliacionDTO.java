/**
 * 
 */
package com.ccb.simasc.transversal.dto;

import java.util.Date;

/**
 * @author cbenavides
 *
 */
public class CierreConciliacionDTO {
	private String motivo;
	private Date fechaSolicitud;
	private String observaciones;
	private Long idCaso;
	private Long idArea;
	private Long idAsunto;
	private Long idClasificacion;
	
	
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * @return the idArea
	 */
	public Long getIdArea() {
		return idArea;
	}
	/**
	 * @param idArea the idArea to set
	 */
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}
	/**
	 * @return the idAsunto
	 */
	public Long getIdAsunto() {
		return idAsunto;
	}
	/**
	 * @param idAsunto the idAsunto to set
	 */
	public void setIdAsunto(Long idAsunto) {
		this.idAsunto = idAsunto;
	}
	/**
	 * @return the idClasificacion
	 */
	public Long getIdClasificacion() {
		return idClasificacion;
	}
	/**
	 * @param idClasificacion the idClasificacion to set
	 */
	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}
	
	
	
}
