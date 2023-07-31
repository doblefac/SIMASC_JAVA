package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

/**
 * DAO que contene la información del formulario: Tabla resumen de suspensiones
 * o requerimientos al cliente
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
public class CasoCerradoDTO {
	
	private Long idCaso;
	private String motivoCierre;
	private Date fechaCierre;
	private String observaciones;
	private Date fechaEntrega;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
}
