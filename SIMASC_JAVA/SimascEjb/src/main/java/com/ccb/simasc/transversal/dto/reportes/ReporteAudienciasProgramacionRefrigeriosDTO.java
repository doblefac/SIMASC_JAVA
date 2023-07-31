package com.ccb.simasc.transversal.dto.reportes;

import java.io.Serializable;

public class ReporteAudienciasProgramacionRefrigeriosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nombreCaso;	
	private Long codigoCaso;
	private String fechaHoraAudiencia;
	private String tipoAudiencia;
	private Integer cantidadAsistentes;				
	private String nombreSecretarios;
	private String nombreArbitros;
	private String observaciones;
	private String consumo;
	private String servicio;
	
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getFechaHoraAudiencia() {
		return fechaHoraAudiencia;
	}
	public void setFechaHoraAudiencia(String fechaHoraAudiencia) {
		this.fechaHoraAudiencia = fechaHoraAudiencia;
	}
	public Integer getCantidadAsistentes() {
		if (cantidadAsistentes == null) {
			cantidadAsistentes = 0;
		}
		return cantidadAsistentes;
	}
	public void setCantidadAsistentes(Integer cantidadAsistentes) {
		this.cantidadAsistentes = cantidadAsistentes;
	}
	public String getNombreSecretarios() {
		return nombreSecretarios;
	}
	public void setNombreSecretarios(String nombreSecretarios) {
		this.nombreSecretarios = nombreSecretarios;
	}
	public String getNombreArbitros() {
		return nombreArbitros;
	}
	public void setNombreArbitros(String nombreArbitros) {
		this.nombreArbitros = nombreArbitros;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the codigoCaso
	 */
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	/**
	 * @param codigoCaso the codigoCaso to set
	 */
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	/**
	 * @return the tipoAudiencia
	 */
	public String getTipoAudiencia() {
		return tipoAudiencia;
	}
	/**
	 * @param tipoAudiencia the tipoAudiencia to set
	 */
	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	

}
