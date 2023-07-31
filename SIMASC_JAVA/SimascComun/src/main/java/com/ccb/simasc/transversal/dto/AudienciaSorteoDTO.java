package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class AudienciaSorteoDTO {
	private Date fecha;
	private String nombreCaso;
	private Long idCaso;
	private String tipoCaso;
	private String materia;
	private String tipoCuantia;
	private String preseleccion;
	private int verElegible;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public int getVerElegible() {
		return verElegible;
	}
	public void setVerElegible(int verElegible) {
		this.verElegible = verElegible;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public String getPreseleccion() {
		return preseleccion;
	}
	public void setPreseleccion(String preseleccion) {
		this.preseleccion = preseleccion;
	}
}
