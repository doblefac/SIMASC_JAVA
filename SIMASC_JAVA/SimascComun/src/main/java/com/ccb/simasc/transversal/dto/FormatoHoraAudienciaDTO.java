package com.ccb.simasc.transversal.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormatoHoraAudienciaDTO {

	private Date fechaInicio;
	private Date fechaFin;
	private String formatoFechas;
	
	public FormatoHoraAudienciaDTO() {
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFormatoFechas() {
		return formatoFechas;
	}
	public void setFormatoFechas(String formatoFechas) {
		this.formatoFechas = formatoFechas;
	}
	
	
	
}
