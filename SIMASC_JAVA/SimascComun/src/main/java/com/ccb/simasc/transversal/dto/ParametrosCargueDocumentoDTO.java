package com.ccb.simasc.transversal.dto;

public class ParametrosCargueDocumentoDTO {
	
	private Long idPersona;
	private Long idEvento;
	private Long idConvenio;
	private Long idCasoOSolicitud;
	//private InputStream documento;
	private String nombreDocumento;
	private String extension;
	
	
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
	 * @return the idEvento
	 */
	public Long getIdEvento() {
		return idEvento;
	}
	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
	/**
	 * @return the idConvenio
	 */
	public Long getIdConvenio() {
		return idConvenio;
	}
	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
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
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	

}
