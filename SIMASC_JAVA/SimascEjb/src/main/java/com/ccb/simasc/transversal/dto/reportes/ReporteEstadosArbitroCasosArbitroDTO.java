package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * DTO para reporte 23 Estados Arbitro
 * Contiene información relacionada al caso en el que se encuentra asignado
 * un arbitro específido
 * @author jsoto
 *
 */
public class ReporteEstadosArbitroCasosArbitroDTO {
	
	private Long codigoCaso; 
	private String tipoCaso;
	private String nombreCaso;
	private Date fechaRadicacionCaso;
	//Nombre de la materia del caso
	private String materiaCaso;
	private String cuantia;
	private String metodoNombramiento;
	private String tipoNombramiento;
	private Date fechaNombramiento;
	private String preseleccion;
	private String tipoPreseleccion;
	private String quienPreseleccion;
	private String consumo;
	private String rol;
	
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
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
	public Date getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}
	public void setFechaRadicacionCaso(Date fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}
	public String getMateriaCaso() {
		return materiaCaso;
	}
	public void setMateriaCaso(String materiaCaso) {
		this.materiaCaso = materiaCaso;
	}	
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	/**
	 * @return the metodoNombramiento
	 */
	public String getMetodoNombramiento() {
		return metodoNombramiento;
	}
	/**
	 * @param metodoNombramiento the metodoNombramiento to set
	 */
	public void setMetodoNombramiento(String metodoNombramiento) {
		this.metodoNombramiento = metodoNombramiento;
	}
	public String getTipoNombramiento() {
		return tipoNombramiento;
	}
	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}
	/**
	 * @return the fechaNombramiento
	 */
	public Date getFechaNombramiento() {
		return fechaNombramiento;
	}
	/**
	 * @param fechaNombramiento the fechaNombramiento to set
	 */
	public void setFechaNombramiento(Date fechaNombramiento) {
		this.fechaNombramiento = fechaNombramiento;
	}
	public String getPreseleccion() {
		return preseleccion;
	}
	public void setPreseleccion(String preseleccion) {
		this.preseleccion = preseleccion;
	}
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}
	public String getQuienPreseleccion() {
		return quienPreseleccion;
	}
	public void setQuienPreseleccion(String quienPreseleccion) {
		this.quienPreseleccion = quienPreseleccion;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
