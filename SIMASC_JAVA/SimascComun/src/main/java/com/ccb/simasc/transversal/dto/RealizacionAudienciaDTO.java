package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.dto.formularios.ParteCasoDTO;

public class RealizacionAudienciaDTO {
	
	private Long idCaso;
	private Long idAudiencia;
	private Long idAsunto;
	private Long idArea;
	private Long idClasificacion;
	
	private String tipoResultadoAudiencia;
	
	private List<ParteCasoDTO> partes;
	private List<ParteCasoDTO> partesIdentificacion;
	private boolean edicionResultado;

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public Long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public Long getIdAsunto() {
		return idAsunto;
	}

	public void setIdAsunto(Long idAsunto) {
		this.idAsunto = idAsunto;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public Long getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public List<ParteCasoDTO> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteCasoDTO> partes) {
		this.partes = partes;
	}

	public String getTipoResultadoAudiencia() {
		return tipoResultadoAudiencia;
	}

	public void setTipoResultadoAudiencia(String tipoResultadoAudiencia) {
		this.tipoResultadoAudiencia = tipoResultadoAudiencia;
	}

	public boolean isEdicionResultado() {
		return edicionResultado;
	}

	public void setEdicionResultado(boolean edicionResultado) {
		this.edicionResultado = edicionResultado;
	}

	public List<ParteCasoDTO> getPartesIdentificacion() {
		return partesIdentificacion;
	}

	public void setPartesIdentificacion(List<ParteCasoDTO> partesIdentificacion) {
		this.partesIdentificacion = partesIdentificacion;
	}
	
	
	

}
