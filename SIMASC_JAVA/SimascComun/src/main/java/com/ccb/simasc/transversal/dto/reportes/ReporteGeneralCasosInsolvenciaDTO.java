package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class ReporteGeneralCasosInsolvenciaDTO {
	
	private Long idCaso;
	private String nombreSolicitante;
	private String tipoDocumentoSolicitante;
	private String numeroDocumentoSolicitante;
	private String telefonoSolicitante;
	private String emailSolicitante;
	private String direccionSolicitante;
	//private List<PersonaBasicaDTO> acreedores;
	private String nombreAcreedores;
	private String tipoDocumentoAcreedores;
	private String numeroDocumentoAcreedores;
	private String emailAcreedores;
	private String telefonoAcreedores;
	private String direccionAcreedores;
	private String conciliadorAsignado;
	private Date fechaRadicacionCaso;
	private Date fechaAceptacionCaso;
	private Date fechaAdmisibilidadCaso;
	private Date fechaPrimeraAudiencia ;
	private Date fechaAudienciasRealizadas;
	private String valorCreditos;
	private String	valorAcuerdo;
	private Date fechaCierreCaso;
	private String	resultadoCaso;
	private String	ultimoEstadoCaso;
	private String	observaciones;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getTipoDocumentoSolicitante() {
		return tipoDocumentoSolicitante;
	}
	public void setTipoDocumentoSolicitante(String tipoDocumentoSolicitante) {
		this.tipoDocumentoSolicitante = tipoDocumentoSolicitante;
	}
	public String getNumeroDocumentoSolicitante() {
		return numeroDocumentoSolicitante;
	}
	public void setNumeroDocumentoSolicitante(String numeroDocumentoSolicitante) {
		this.numeroDocumentoSolicitante = numeroDocumentoSolicitante;
	}
	public String getTelefonoSolicitante() {
		return telefonoSolicitante;
	}
	public void setTelefonoSolicitante(String telefonoSolicitante) {
		this.telefonoSolicitante = telefonoSolicitante;
	}
	public String getEmailSolicitante() {
		return emailSolicitante;
	}
	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}
	public String getDireccionSolicitante() {
		return direccionSolicitante;
	}
	public void setDireccionSolicitante(String direccionSolicitante) {
		this.direccionSolicitante = direccionSolicitante;
	}
	public String getConciliadorAsignado() {
		return conciliadorAsignado;
	}
	public void setConciliadorAsignado(String conciliadorAsignado) {
		this.conciliadorAsignado = conciliadorAsignado;
	}
	public Date getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}
	public void setFechaRadicacionCaso(Date fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}
	public Date getFechaAceptacionCaso() {
		return fechaAceptacionCaso;
	}
	public void setFechaAceptacionCaso(Date fechaAceptacionCaso) {
		this.fechaAceptacionCaso = fechaAceptacionCaso;
	}
	public Date getFechaAdmisibilidadCaso() {
		return fechaAdmisibilidadCaso;
	}
	public void setFechaAdmisibilidadCaso(Date fechaAdmisibilidadCaso) {
		this.fechaAdmisibilidadCaso = fechaAdmisibilidadCaso;
	}
	public Date getFechaPrimeraAudiencia() {
		return fechaPrimeraAudiencia;
	}
	public void setFechaPrimeraAudiencia(Date fechaPrimeraAudiencia) {
		this.fechaPrimeraAudiencia = fechaPrimeraAudiencia;
	}
	public Date getFechaAudienciasRealizadas() {
		return fechaAudienciasRealizadas;
	}
	public void setFechaAudienciasRealizadas(Date fechaAudienciasRealizadas) {
		this.fechaAudienciasRealizadas = fechaAudienciasRealizadas;
	}
	public String getValorCreditos() {
		return valorCreditos;
	}
	public void setValorCreditos(String valorCreditos) {
		this.valorCreditos = valorCreditos;
	}
	public String getValorAcuerdo() {
		return valorAcuerdo;
	}
	public void setValorAcuerdo(String valorAcuerdo) {
		this.valorAcuerdo = valorAcuerdo;
	}
	public Date getFechaCierreCaso() {
		return fechaCierreCaso;
	}
	public void setFechaCierreCaso(Date fechaCierreCaso) {
		this.fechaCierreCaso = fechaCierreCaso;
	}
	public String getResultadoCaso() {
		return resultadoCaso;
	}
	public void setResultadoCaso(String resultadoCaso) {
		this.resultadoCaso = resultadoCaso;
	}
	public String getUltimoEstadoCaso() {
		return ultimoEstadoCaso;
	}
	public void setUltimoEstadoCaso(String ultimoEstadoCaso) {
		this.ultimoEstadoCaso = ultimoEstadoCaso;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getNombreAcreedores() {
		return nombreAcreedores;
	}
	public void setNombreAcreedores(String nombreAcreedores) {
		this.nombreAcreedores = nombreAcreedores;
	}
	public String getTipoDocumentoAcreedores() {
		return tipoDocumentoAcreedores;
	}
	public void setTipoDocumentoAcreedores(String tipoDocumentoAcreedores) {
		this.tipoDocumentoAcreedores = tipoDocumentoAcreedores;
	}
	public String getNumeroDocumentoAcreedores() {
		return numeroDocumentoAcreedores;
	}
	public void setNumeroDocumentoAcreedores(String numeroDocumentoAcreedores) {
		this.numeroDocumentoAcreedores = numeroDocumentoAcreedores;
	}
	public String getEmailAcreedores() {
		return emailAcreedores;
	}
	public void setEmailAcreedores(String emailAcreedores) {
		this.emailAcreedores = emailAcreedores;
	}
	public String getTelefonoAcreedores() {
		return telefonoAcreedores;
	}
	public void setTelefonoAcreedores(String telefonoAcreedores) {
		this.telefonoAcreedores = telefonoAcreedores;
	}
	public String getDireccionAcreedores() {
		return direccionAcreedores;
	}
	public void setDireccionAcreedores(String direccionAcreedores) {
		this.direccionAcreedores = direccionAcreedores;
	}
	

}
