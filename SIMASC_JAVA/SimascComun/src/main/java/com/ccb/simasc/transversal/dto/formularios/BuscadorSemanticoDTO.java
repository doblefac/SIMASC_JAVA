package com.ccb.simasc.transversal.dto.formularios;


public class BuscadorSemanticoDTO {
	
	private Long idCaso;
	private String keyword;
	private String tipoDocumento;
	private String fechaInicial;
	private String fechaFinal;
	
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
}
