package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlanillaCorrespondenciaCartaDTO implements Serializable{
	
	private Date fechaEnvio;
	private Long idPersona;
	private Long idSede;
	private String caso;
	private Long idCaso;
	private String destinatario;
	private String numeroRadicado;
	private Long idConvenio;
	private List<Long> idCentros;
	private List<LlamadaPlanillaCorrespondenciaDTO> correspondencia;
	
	
	/**
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	/**
	 * @return the idSede
	 */
	public Long getIdSede() {
		return idSede;
	}
	/**
	 * @param idSede the idSede to set
	 */
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	/**
	 * @return the caso
	 */
	public String getCaso() {
		return caso;
	}
	/**
	 * @param caso the caso to set
	 */
	public void setCaso(String caso) {
		this.caso = caso;
	}
	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}
	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	/**
	 * @return the numeroRadicado
	 */
	public String getNumeroRadicado() {
		return numeroRadicado;
	}
	/**
	 * @param numeroRadicado the numeroRadicado to set
	 */
	public void setNumeroRadicado(String numeroRadicado) {
		this.numeroRadicado = numeroRadicado;
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
	 * @return the idCentros
	 */
	public List<Long> getIdCentros() {
		return idCentros;
	}
	/**
	 * @param idCentros the idCentros to set
	 */
	public void setIdCentros(List<Long> idCentros) {
		this.idCentros = idCentros;
	}
	/**
	 * @return the correspondencia
	 */
	public List<LlamadaPlanillaCorrespondenciaDTO> getCorrespondencia() {
		return correspondencia;
	}
	/**
	 * @param correspondencia the correspondencia to set
	 */
	public void setCorrespondencia(List<LlamadaPlanillaCorrespondenciaDTO> correspondencia) {
		this.correspondencia = correspondencia;
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
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	
}
