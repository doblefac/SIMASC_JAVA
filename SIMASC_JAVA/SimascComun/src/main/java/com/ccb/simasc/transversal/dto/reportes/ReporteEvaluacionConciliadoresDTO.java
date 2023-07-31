package com.ccb.simasc.transversal.dto.reportes;

import java.math.BigDecimal;

public class ReporteEvaluacionConciliadoresDTO {

	public String numeroDocumento;
	public String nombreConciliador;
	public String lista;
	public BigDecimal totalCriterioCalidad;
	public BigDecimal totalCriterioEducacionContinua;
	public BigDecimal totalCriterioParticipacion;
	public BigDecimal totalCriterioProcedimientos;
	public BigDecimal notaFinal;
	public int anio;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getLista() {
		return lista;
	}
	public void setLista(String lista) {
		this.lista = lista;
	}
	public BigDecimal getTotalCriterioCalidad() {
		return totalCriterioCalidad;
	}
	public void setTotalCriterioCalidad(BigDecimal totalCriterioCalidad) {
		this.totalCriterioCalidad = totalCriterioCalidad;
	}
	public BigDecimal getTotalCriterioEducacionContinua() {
		return totalCriterioEducacionContinua;
	}
	public void setTotalCriterioEducacionContinua(BigDecimal totalCriterioEducacionContinua) {
		this.totalCriterioEducacionContinua = totalCriterioEducacionContinua;
	}
	public BigDecimal getTotalCriterioParticipacion() {
		return totalCriterioParticipacion;
	}
	public void setTotalCriterioParticipacion(BigDecimal totalCriterioParticipacion) {
		this.totalCriterioParticipacion = totalCriterioParticipacion;
	}
	public BigDecimal getTotalCriterioProcedimientos() {
		return totalCriterioProcedimientos;
	}
	public void setTotalCriterioProcedimientos(BigDecimal totalCriterioProcedimientos) {
		this.totalCriterioProcedimientos = totalCriterioProcedimientos;
	}
	public BigDecimal getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(BigDecimal notaFinal) {
		this.notaFinal = notaFinal;
	}

	
	
	
	
}
