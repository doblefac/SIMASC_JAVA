package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class DevolucionDeDineroDTO {
	private long idReliquidacion;
	private long idCaso;
	private String nombre;
	private String conciliador;
	private String motivo;
	private double valor;
	private Date fechaGeneracion;
	
	public DevolucionDeDineroDTO() {
		super();
	}

	public long getIdReliquidacion() {
		return idReliquidacion;
	}

	public void setIdReliquidacion(long idReliquidacion) {
		this.idReliquidacion = idReliquidacion;
	}

	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getConciliador() {
		return conciliador;
	}

	public void setConciliador(String conciliador) {
		this.conciliador = conciliador;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

}
