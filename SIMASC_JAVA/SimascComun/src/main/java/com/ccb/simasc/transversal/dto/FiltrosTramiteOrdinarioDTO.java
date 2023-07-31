package com.ccb.simasc.transversal.dto;

import java.util.Date;
import java.util.List;

public class FiltrosTramiteOrdinarioDTO {
	private Date fechaDesde;
	private Date fechaHasta;
	private  long idConciliador;
	private List<CentroDTO> centros;
	
	public FiltrosTramiteOrdinarioDTO() {
		super();
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public long getIdConciliador() {
		return idConciliador;
	}

	public void setIdConciliador(long idConciliador) {
		this.idConciliador = idConciliador;
	}

	public List<CentroDTO> getCentros() {
		return centros;
	}

	public void setCentros(List<CentroDTO> centros) {
		this.centros = centros;
	}

}
