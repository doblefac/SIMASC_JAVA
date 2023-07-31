package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class ModificarActasConstanciasDevueltasDTO {
	private Long idResultadoAudiencia;
	private Long idDocumento;
	private Long idDevolucionDocumentoResultado;
	private Date fechaAudiencia;
	private String resultado;
	private String motivosDevolucion;
	private String observaciones;
	
	/*
	 * Atributos para el guardado
	 */
	private Long idCaso;
	private String estadoResultadoAudiencia;
	private Boolean corrige; 
	private Date fechaCorrecion;
	private String observacionesRespuesta;
	
	/*
	 * Atributos adiciones del documento
	 */
	private String tipoDocumento;
	private String descripcionDocumento;
	private Long numeroFolios;
	private Long idAudienciaDocumento;
	
	public String getEstadoResultadoAudiencia() {
		return estadoResultadoAudiencia;
	}
	public void setEstadoResultadoAudiencia(String estadoResultadoAudiencia) {
		this.estadoResultadoAudiencia = estadoResultadoAudiencia;
	}
	public Boolean getCorrige() {
		return corrige;
	}
	public void setCorrige(Boolean corrige) {
		this.corrige = corrige;
	}
	public Date getFechaCorrecion() {
		return fechaCorrecion;
	}
	public void setFechaCorrecion(Date fechaCorrecion) {
		this.fechaCorrecion = fechaCorrecion;
	}
	public String getObservacionesRespuesta() {
		return observacionesRespuesta;
	}
	public void setObservacionesRespuesta(String observacionesRespuesta) {
		this.observacionesRespuesta = observacionesRespuesta;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	public Long getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(Long numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public Long getIdAudienciaDocumento() {
		return idAudienciaDocumento;
	}
	public void setIdAudienciaDocumento(Long idAudienciaDocumento) {
		this.idAudienciaDocumento = idAudienciaDocumento;
	}
	public Long getIdResultadoAudiencia() {
		return idResultadoAudiencia;
	}
	public void setIdResultadoAudiencia(Long idResultadoAudiencia) {
		this.idResultadoAudiencia = idResultadoAudiencia;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Long getIdDevolucionDocumentoResultado() {
		return idDevolucionDocumentoResultado;
	}
	public void setIdDevolucionDocumentoResultado(Long idDevolucionDocumentoResultado) {
		this.idDevolucionDocumentoResultado = idDevolucionDocumentoResultado;
	}
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getMotivosDevolucion() {
		return motivosDevolucion;
	}
	public void setMotivosDevolucion(String motivosDevolucion) {
		this.motivosDevolucion = motivosDevolucion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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

}
