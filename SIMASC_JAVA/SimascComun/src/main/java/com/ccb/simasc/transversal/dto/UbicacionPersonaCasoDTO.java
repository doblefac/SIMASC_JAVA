package com.ccb.simasc.transversal.dto;

public class UbicacionPersonaCasoDTO {
	
	private UbicacionDTO ubicacionDTO;
	private Long idPersona;
	private String nombreRol;
	private Long idCasoOSolicitud;
	private boolean esSolicitud;
	
	/**
	 * @return the ubicacionDTO
	 */
	public UbicacionDTO getUbicacionDTO() {
		return ubicacionDTO;
	}
	/**
	 * @param ubicacionDTO the ubicacionDTO to set
	 */
	public void setUbicacionDTO(UbicacionDTO ubicacionDTO) {
		this.ubicacionDTO = ubicacionDTO;
	}
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	/**
	 * @param nombreRol the nombreRol to set
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	/**
	 * @return the idCasoOSolicitud
	 */
	public Long getIdCasoOSolicitud() {
		return idCasoOSolicitud;
	}
	/**
	 * @param idCasoOSolicitud the idCasoOSolicitud to set
	 */
	public void setIdCasoOSolicitud(Long idCasoOSolicitud) {
		this.idCasoOSolicitud = idCasoOSolicitud;
	}
	/**
	 * @return the esSolicitud
	 */
	public boolean getEsSolicitud() {
		return esSolicitud;
	}
	/**
	 * @param esSolicitud the esSolicitud to set
	 */
	public void setEsSolicitud(boolean esSolicitud) {
		this.esSolicitud = esSolicitud;
	}
	

}
