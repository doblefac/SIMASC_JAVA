package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class SeguimientoCasosDTO {
	private long idLlamada;
	private long codigoCaso;
	private String tipoCaso;
	private String nombreCaso;
	private String sede;
	private String conciliador;
	private String materia;
	private String acuerdos;
	private String personaSeguimiento;
	private Date fechaSeguimiento;
	private String respondio;
	private String dioInformacion;
	private String cumplio;
	private String observaciones;
	
	public long getIdLlamada() {
		return idLlamada;
	}

	public void setIdLlamada(long idLlamada) {
		this.idLlamada = idLlamada;
	}
	
	public long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public String getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getConciliador() {
		return conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getAcuerdos() {
		return acuerdos;
	}

	public void setAcuerdos(String acuerdos) {
		this.acuerdos = acuerdos;
	}

	public String getPersonaSeguimiento() {
		return personaSeguimiento;
	}

	public void setPersonaSeguimiento(String personaSeguimiento) {
		this.personaSeguimiento = personaSeguimiento;
	}

	public Date getFechaSeguimiento() {
		return fechaSeguimiento;
	}

	public void setFechaSeguimiento(Date fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}

	public String getRespondio() {
		return respondio;
	}

	public void setRespondio(String respondio) {
		this.respondio = respondio;
	}

	public String getDioInformacion() {
		return dioInformacion;
	}

	public void setDioInformacion(String dioInformacion) {
		this.dioInformacion = dioInformacion;
	}

	public String getCumplio() {
		return cumplio;
	}

	public void setCumplio(String cumplio) {
		this.cumplio = cumplio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public SeguimientoCasosDTO() {
		super();
	}
	
	
}
