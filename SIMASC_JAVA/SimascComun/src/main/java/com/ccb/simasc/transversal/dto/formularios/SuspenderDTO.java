package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DAO que contene la información del formulario: Tabla resumen de suspensiones
 * o requerimientos al cliente
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
public class SuspenderDTO {
	
	private Long idCaso;
	private Long idEventoInicial;
	private Long idEventoFinal;
	private String codigoSuspension;
	private String tipoSuspension;
	private Date fechaInicial;
	private Date fechaFinal;
	private Long numeroDias;
	private String observacion;
	private String nombreParte;
	private String estadoRegistro;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getIdEventoInicial() {
		return idEventoInicial;
	}
	public void setIdEventoInicial(Long idEventoInicial) {
		this.idEventoInicial = idEventoInicial;
	}
	public Long getIdEventoFinal() {
		return idEventoFinal;
	}
	public void setIdEventoFinal(Long idEventoFinal) {
		this.idEventoFinal = idEventoFinal;
	}
	public String getCodigoSuspension() {
		return codigoSuspension;
	}
	public void setCodigoSuspension(String codigoSuspension) {
		this.codigoSuspension = codigoSuspension;
	}
	public String getTipoSuspension() {
		return tipoSuspension;
	}
	public void setTipoSuspension(String tipoSuspension) {
		this.tipoSuspension = tipoSuspension;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Long getNumeroDias() {
		return numeroDias;
	}
	public void setNumeroDias(Long numeroDias) {
		this.numeroDias = numeroDias;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
