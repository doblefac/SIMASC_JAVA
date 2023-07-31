package com.ccb.simasc.transversal.dto;

import java.util.List;

public class DevolucionDocumentoDTO {

	private List<MotivoDevolucionDTO> motivosDevolucion;
	private String observacionDevolucion;
	private Long idDocumento;
	private Long idResultadoAudiencia;
	private Long idCaso;
	private Long idServicio;
	
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public List<MotivoDevolucionDTO> getMotivosDevolucion() {
		return motivosDevolucion;
	}
	public void setMotivosDevolucion(List<MotivoDevolucionDTO> motivosDevolucion) {
		this.motivosDevolucion = motivosDevolucion;
	}
	public String getObservacionDevolucion() {
		return observacionDevolucion;
	}
	public void setObservacionDevolucion(String observacionDevolucion) {
		this.observacionDevolucion = observacionDevolucion;
	}
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
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	
	
	
}
