package com.ccb.simasc.transversal.dto.formularios;

public class ProcesoReliquidacionDTO {

	private Long idSolicitud;
	private Double porcentajeAdicional;
	private String cuantia;
	private Boolean isSolicitud;
	private Long idCentro;
	private Long idServicio;
	private Boolean isReliquidacion;
	/**
	 * @return the idSolicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	/**
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	/**
	 * @return the porcentajeAdicional
	 */
	public Double getPorcentajeAdicional() {
		return porcentajeAdicional;
	}
	/**
	 * @param porcentajeAdicional the porcentajeAdicional to set
	 */
	public void setPorcentajeAdicional(Double porcentajeAdicional) {
		this.porcentajeAdicional = porcentajeAdicional;
	}
	/**
	 * @return the cuantia
	 */
	public String getCuantia() {
		return cuantia;
	}
	/**
	 * @param cuantia the cuantia to set
	 */
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	/**
	 * @return the isSolicitud
	 */
	public Boolean getIsSolicitud() {
		return isSolicitud;
	}
	/**
	 * @param isSolicitud the isSolicitud to set
	 */
	public void setIsSolicitud(Boolean isSolicitud) {
		this.isSolicitud = isSolicitud;
	}
	/**
	 * @return the idCentro
	 */
	public Long getIdCentro() {
		return idCentro;
	}
	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public Boolean getIsReliquidacion() {
		return isReliquidacion;
	}
	public void setIsReliquidacion(Boolean isReliquidacion) {
		this.isReliquidacion = isReliquidacion;
	}
	
	
}
