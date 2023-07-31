package com.ccb.simasc.transversal.dto;

public class CambioConciliadorDTO {

	
	private Long idCaso;
    private Long idConciliador;
    private Long idRol;
    private String observaciones;
    private String nombreCompleto;
    
	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	/**
	 * @return the idConciliador
	 */
	public Long getIdConciliador() {
		return idConciliador;
	}
	/**
	 * @param idConciliador the idConciliador to set
	 */
	public void setIdConciliador(Long idConciliador) {
		this.idConciliador = idConciliador;
	}
	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
    
   
    
}
