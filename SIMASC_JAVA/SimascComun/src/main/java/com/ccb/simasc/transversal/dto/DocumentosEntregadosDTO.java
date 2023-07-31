package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class DocumentosEntregadosDTO {
	
	private Long idPersona;
	private String nombreParte;
	private String nombreRolParte;
	private Date fechaAudiencia;
	private String resultadoAudiencia;
	private Long idDocumento;
	private String tipoDocumento;
	private Date fechaEntrega;
	
	public String getNombreRolParte() {
		return nombreRolParte;
	}
	public void setNombreRolParte(String nombreRolParte) {
		this.nombreRolParte = nombreRolParte;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public String getResultadoAudiencia() {
		return resultadoAudiencia;
	}
	public void setResultadoAudiencia(String resultadoAudiencia) {
		this.resultadoAudiencia = resultadoAudiencia;
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
	public String getNombreParte() {
		return nombreParte;
	}
	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
	
}
