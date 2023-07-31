package com.ccb.simasc.transversal.dto;

import java.util.Date;

/**
 * DTO que contiene informacion relacionada con el estado de envio de la
 * correspondencia fisica de un caso.
 * 
 * @author aperez.
 *
 */
public class CorrespondenciaDTO {

	/**
	 * fecha envio de carta
	 */
	private Date fechaEnvio;
	/**
	 * fecha inicio de la audiencia
	 */
	private Date fechaAudiencia;

	private Date fechaLlamada;

	/**
	 * persona que va dirigida la carta
	 */
	private String destinatario;
	/**
	 * sede de la audiencia
	 */
	private String sedeAudiencia;
	/**
	 * estado de la carta
	 */
	private String estado;
	/**
	 * observaciones de la llamada
	 */
	private String observaciones;

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}

	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	public Date getFechaLlamada() {
		return fechaLlamada;
	}

	public void setFechaLlamada(Date fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getSedeAudiencia() {
		return sedeAudiencia;
	}

	public void setSedeAudiencia(String sedeAudiencia) {
		this.sedeAudiencia = sedeAudiencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
