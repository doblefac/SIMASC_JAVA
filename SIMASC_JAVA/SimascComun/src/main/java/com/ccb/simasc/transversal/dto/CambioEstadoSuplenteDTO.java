package com.ccb.simasc.transversal.dto;

public class CambioEstadoSuplenteDTO {
	
	private Long idCaso;
    private Long idPersona;
    private String idUsuario;
    private String motivoReversamiento;
   
    
    public CambioEstadoSuplenteDTO(){}
    
	public CambioEstadoSuplenteDTO(Long idCaso, Long idPersona, String idUsuario) {
		super();
		this.idCaso = idCaso;
		this.idPersona = idPersona;
		this.idUsuario = idUsuario;
	}
	
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
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMotivoReversamiento() {
		return motivoReversamiento;
	}

	public void setMotivoReversamiento(String motivoReversamiento) {
		this.motivoReversamiento = motivoReversamiento;
	}

}
