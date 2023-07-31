package com.ccb.simasc.transversal.dto;

import java.util.Date;
import java.util.List;

import com.ccb.simasc.transversal.entidades.Cuota;

public class SeguimientoObligacionesDTO {
	//atributos busquedas
	private String tipoObligacion;
	private double valorTotalAcuerdo;
	private String compromiso;
	private Date fecha;
	private String direccion;
	private String observaciones;
	private Long idResultadoAudiencia;
	
	//agregados al final
	private String quienPagaAQuien;
	private List<Cuota> cuotas;
	private String formaPago;
	private String quienSeObliga;
	private String queDebeHacer;
	public SeguimientoObligacionesDTO() {
		super();
	}

	public SeguimientoObligacionesDTO(String tipoObligacion, double valorTotalAcuerdo, String compromiso, Date fecha,
			String direccion, String observaciones, Long idResultadoAudiencia) {
		super();
		this.tipoObligacion = tipoObligacion;
		this.valorTotalAcuerdo = valorTotalAcuerdo;
		this.compromiso = compromiso;
		this.fecha = fecha;
		this.direccion = direccion;
		this.observaciones = observaciones;
		this.idResultadoAudiencia = idResultadoAudiencia;
	}

	public String getTipoObligacion() {
		return tipoObligacion;
	}

	public void setTipoObligacion(String tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
	}

	public double getValorTotalAcuerdo() {
		return valorTotalAcuerdo;
	}

	public void setValorTotalAcuerdo(double valorTotalAcuerdo) {
		this.valorTotalAcuerdo = valorTotalAcuerdo;
	}

	public String getCompromiso() {
		return compromiso;
	}

	public void setCompromiso(String compromiso) {
		this.compromiso = compromiso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIdResultadoAudiencia() {
		return idResultadoAudiencia;
	}

	public void setIdResultadoAudiencia(Long idResultadoAudiencia) {
		this.idResultadoAudiencia = idResultadoAudiencia;
	}

	public String getQuienPagaAQuien() {
		return quienPagaAQuien;
	}

	public void setQuienPagaAQuien(String quienPagaAQuien) {
		this.quienPagaAQuien = quienPagaAQuien;
	}

	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getQuienSeObliga() {
		return quienSeObliga;
	}

	public void setQuienSeObliga(String quienSeObliga) {
		this.quienSeObliga = quienSeObliga;
	}

	public String getQueDebeHacer() {
		return queDebeHacer;
	}

	public void setQueDebeHacer(String queDebeHacer) {
		this.queDebeHacer = queDebeHacer;
	}
	
	

}
