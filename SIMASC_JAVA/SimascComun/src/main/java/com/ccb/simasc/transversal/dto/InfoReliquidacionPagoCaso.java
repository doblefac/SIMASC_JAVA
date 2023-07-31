package com.ccb.simasc.transversal.dto;

import java.util.Date;

public class InfoReliquidacionPagoCaso {
	private long 	idReliquidacion;
	private long 	idCaso;
	private Date 	fechaReliquidacion;
	private String 	numeroOrden;
	private double 	valorHonorario;
	private double  ivaHonorario;
	private double  valorGastoAdministrativo;
	private double  ivaGastoAdministrativo;
	private double  totalPagar;
	private String  ordenPagada;
	private Date    fechaPago;
	
	public InfoReliquidacionPagoCaso() {}

	public InfoReliquidacionPagoCaso(long idReliquidacion, long idCaso, Date fechaReliquidacion, String numeroOrden,
			double valorHonorario, double ivaHonorario, double valorGastoAdministrativo, double ivaGastoAdministrativo,
			double totalPagar, String ordenPagada, Date fechaPago) {
		this.idReliquidacion = idReliquidacion;
		this.idCaso = idCaso;
		this.fechaReliquidacion = fechaReliquidacion;
		this.numeroOrden = numeroOrden;
		this.valorHonorario = valorHonorario;
		this.ivaHonorario = ivaHonorario;
		this.valorGastoAdministrativo = valorGastoAdministrativo;
		this.ivaGastoAdministrativo = ivaGastoAdministrativo;
		this.totalPagar = totalPagar;
		this.ordenPagada = ordenPagada;
		this.fechaPago = fechaPago;
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

	public Date getFechaReliquidacion() {
		return fechaReliquidacion;
	}

	public void setFechaReliquidacion(Date fechaReliquidacion) {
		this.fechaReliquidacion = fechaReliquidacion;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public double getValorHonorario() {
		return valorHonorario;
	}

	public void setValorHonorario(double valorHonorario) {
		this.valorHonorario = valorHonorario;
	}

	public double getIvaHonorario() {
		return ivaHonorario;
	}

	public void setIvaHonorario(double ivaHonorario) {
		this.ivaHonorario = ivaHonorario;
	}

	public double getValorGastoAdministrativo() {
		return valorGastoAdministrativo;
	}

	public void setValorGastoAdministrativo(double valorGastoAdministrativo) {
		this.valorGastoAdministrativo = valorGastoAdministrativo;
	}

	public double getIvaGastoAdministrativo() {
		return ivaGastoAdministrativo;
	}

	public void setIvaGastoAdministrativo(double ivaGastoAdministrativo) {
		this.ivaGastoAdministrativo = ivaGastoAdministrativo;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getOrdenPagada() {
		return ordenPagada;
	}

	public void setOrdenPagada(String ordenPagada) {
		this.ordenPagada = ordenPagada;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	
	
	
	
	
	

}
