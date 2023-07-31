package com.ccb.simasc.transversal.dto.formularios;

/**
 * DTO para almacenar los documentos de un digitalizador asociados a un caso
 * Utilizado en el requerimiento TRANS-F-009
 * @author jsoto
 *
 */
public class DocumentosDigitalizadorDTO {
		
	private Long idCaso;
	private String nombreCaso;
	private Long idDocumento;
	private String tipoDocumento;
	private String nombreDocumento;
	private Integer numeroFolios;
	private String cuaderno;
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}
	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * @return the numeroFolios
	 */
	public Integer getNumeroFolios() {
		return numeroFolios;
	}
	/**
	 * @param numeroFolios the numeroFolios to set
	 */
	public void setNumeroFolios(Integer numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	/**
	 * @return the cuaderno
	 */
	public String getCuaderno() {
		return cuaderno;
	}
	/**
	 * @param cuaderno the cuaderno to set
	 */
	public void setCuaderno(String cuaderno) {
		this.cuaderno = cuaderno;
	}
	
	
	
}
