package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class CasosSinCerrarDTO {
	
	private long idCaso;
	private String nombre;
	private Date fechaRadicacion;
	private Date fechaUltimaAudiencia;
	private String resultadoUltimaAudiencia;
	/**
	 * @return the idCaso
	 */
	public long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(long idCaso) {
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
	 * @return the fechaRadicacion
	 */
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	/**
	 * @param fechaRadicacion the fechaRadicacion to set
	 */
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	/**
	 * @return the fechaUltimaAudiencia
	 */
	public Date getFechaUltimaAudiencia() {
		return fechaUltimaAudiencia;
	}
	/**
	 * @param fechaUltimaAudiencia the fechaUltimaAudiencia to set
	 */
	public void setFechaUltimaAudiencia(Date fechaUltimaAudiencia) {
		this.fechaUltimaAudiencia = fechaUltimaAudiencia;
	}
	/**
	 * @return the resultadoUltimaAudiencia
	 */
	public String getResultadoUltimaAudiencia() {
		return resultadoUltimaAudiencia;
	}
	/**
	 * @param resultadoUltimaAudiencia the resultadoUltimaAudiencia to set
	 */
	public void setResultadoUltimaAudiencia(String resultadoUltimaAudiencia) {
		this.resultadoUltimaAudiencia = resultadoUltimaAudiencia;
	}
	

}
