package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DAO que contiene la información del rango de fechas para la programacion de
 * una audiencia. Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class ProgramacionAudienciaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date fechaInicioProgramarAudiencia;
	private Date fechaFinProgramarAudiencia;
	
	public Date getFechaInicioProgramarAudiencia() {
		return fechaInicioProgramarAudiencia;
	}
	public void setFechaInicioProgramarAudiencia(Date fechaInicioProgramarAudiencia) {
		this.fechaInicioProgramarAudiencia = fechaInicioProgramarAudiencia;
	}
	public Date getFechaFinProgramarAudiencia() {
		return fechaFinProgramarAudiencia;
	}
	public void setFechaFinProgramarAudiencia(Date fechaFinProgramarAudiencia) {
		this.fechaFinProgramarAudiencia = fechaFinProgramarAudiencia;
	}
}
