package com.ccb.simasc.transversal.dto;

public class ResultadoCasoDTO {
	
	private Long idDocumento;
	private Long idResultadoAudiencia;
	private String resultadoAudiencia;
	private String estadoResultado;
	private String respuestaConciliador;
	private Long consecutivo;
	
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Long getIdResultadoAudiencia() {
		return idResultadoAudiencia;
	}
	public void setIdResultadoAudiencia(Long idResultadoAudiencia) {
		this.idResultadoAudiencia = idResultadoAudiencia;
	}
	public String getResultadoAudiencia() {
		return resultadoAudiencia;
	}
	public void setResultadoAudiencia(String resultadoAudiencia) {
		this.resultadoAudiencia = resultadoAudiencia;
	}
	public String getEstadoResultado() {
		return estadoResultado;
	}
	public void setEstadoResultado(String estadoResultado) {
		this.estadoResultado = estadoResultado;
	}
	public String getRespuestaConciliador() {
		return respuestaConciliador;
	}
	public void setRespuestaConciliador(String respuestaConciliador) {
		this.respuestaConciliador = respuestaConciliador;
	}
	public Long getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
	

}
