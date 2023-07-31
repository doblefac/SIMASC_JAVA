package com.ccb.simasc.transversal.dto;

import java.util.Date;

/**
 * DTO que contiene la informacion para modificar actas y constancias devueltas 
 * @author prendon
 *
 */
public class DevolucionActaConstanciaDTO {
	/**
	 * nombre del caso
	 */
	private String nombreCaso;
	
	/**
	 * fecha de la ultima audiencia 
	 */
	private Date fechaAudiencia;
	
	/**
	 * Resultado del caso
	 */
	private String resultadoCaso;
	
	/**
	 * Motivos de devolucion
	 */
	private String motivosDevolucion;
	
	/**
	 * Observaciones de la devolucion
	 */
	private String observaciones;

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}

	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	public String getResultadoCaso() {
		return resultadoCaso;
	}

	public void setResultadoCaso(String resultadoCaso) {
		this.resultadoCaso = resultadoCaso;
	}

	public String getMotivosDevolucion() {
		return motivosDevolucion;
	}

	public void setMotivosDevolucion(String motivosDevolucion) {
		this.motivosDevolucion = motivosDevolucion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
