package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.dto.CentroDTO;

public class LotesCartasFiltrosDTO {
	private Date fechaInicio;
	private Date fechaFinal;
	private long idSede;
	private long idCaso;
	private long tipoCaso;
	private List<String> tipoPartes;
	private boolean envioCorreo;
	private List<CentroDTO> centros;
	
	public LotesCartasFiltrosDTO() {
		super();
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public long getIdSede() {
		return idSede;
	}

	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}

	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	public long getTipoCaso() {
		return tipoCaso;
	}

	public void setTipoCaso(long tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	public List<String> getTipoPartes() {
		return tipoPartes;
	}

	public void setTipoPartes(List<String> tipoPartes) {
		this.tipoPartes = tipoPartes;
	}

	public boolean isEnvioCorreo() {
		return envioCorreo;
	}

	public void setEnvioCorreo(boolean envioCorreo) {
		this.envioCorreo = envioCorreo;
	}

	public List<CentroDTO> getCentros() {
		return centros;
	}

	public void setCentros(List<CentroDTO> centros) {
		this.centros = centros;
	}
	
}
