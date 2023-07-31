package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class ReporteGeneralCasosConciliacionDTO {
	private String tipoCaso;
	private Long idCaso;
	private String sedeCaso;
	private String nombreCaso;
	private String hechos;
	private String pretensiones;
	private Date fechaPagoCaso;
	private Date fechaRadicacionCaso;
	private Long numeroDiasRadicacion;
	private Date fechaReparto;
	private Long numeroDiasReparto;
	private Date fechaCitacion;
	private Date fechaPrimeraAudiencia;
	private Long numeroDiasAudiencia;
	private Long totalDias;
	private Long numeroAudienciasRealizadas;
	private Date fechaActa;
	private Date fechaConstancia;
	private String ultimoEstadoCaso;
	private String resultadoCaso;
	private String nombreConciliador;
	private String materiaCaso;
	private String cuantiaCaso;
	private String observacionesCaso;
	private Date fechaSICAAC;
	private String codigoSICAAC;
	private String apoderado;
	private Double valorTotalAcuerdo;
	private String area;
	private String asunto;
	private String clasificacion;
	
	public Date getFechaSICAAC() {
		return fechaSICAAC;
	}
	public void setFechaSICAAC(Date fechaSICAAC) {
		this.fechaSICAAC = fechaSICAAC;
	}
	public String getCodigoSICAAC() {
		return codigoSICAAC;
	}
	public void setCodigoSICAAC(String codigoSICAAC) {
		this.codigoSICAAC = codigoSICAAC;
	}
	public Date getFechaCitacion() {
		return fechaCitacion;
	}
	public void setFechaCitacion(Date fechaCitacion) {
		this.fechaCitacion = fechaCitacion;
	}
	public Date getFechaActa() {
		return fechaActa;
	}
	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}
	public Date getFechaConstancia() {
		return fechaConstancia;
	}
	public void setFechaConstancia(Date fechaConstancia) {
		this.fechaConstancia = fechaConstancia;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getSedeCaso() {
		return sedeCaso;
	}
	public void setSedeCaso(String sedeCaso) {
		this.sedeCaso = sedeCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFechaPagoCaso() {
		return fechaPagoCaso;
	}
	public void setFechaPagoCaso(Date fechaPagoCaso) {
		this.fechaPagoCaso = fechaPagoCaso;
	}
	public Date getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}
	public void setFechaRadicacionCaso(Date fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}
	public Long getNumeroDiasRadicacion() {
		return numeroDiasRadicacion;
	}
	public void setNumeroDiasRadicacion(Long numeroDiasRadicacion) {
		this.numeroDiasRadicacion = numeroDiasRadicacion;
	}
	public Date getFechaReparto() {
		return fechaReparto;
	}
	public void setFechaReparto(Date fechaReparto) {
		this.fechaReparto = fechaReparto;
	}
	public Long getNumeroDiasReparto() {
		return numeroDiasReparto;
	}
	public void setNumeroDiasReparto(Long numeroDiasReparto) {
		this.numeroDiasReparto = numeroDiasReparto;
	}
	public Date getFechaPrimeraAudiencia() {
		return fechaPrimeraAudiencia;
	}
	public void setFechaPrimeraAudiencia(Date fechaPrimeraAudiencia) {
		this.fechaPrimeraAudiencia = fechaPrimeraAudiencia;
	}
	public Long getNumeroDiasAudiencia() {
		return numeroDiasAudiencia;
	}
	public void setNumeroDiasAudiencia(Long numeroDiasAudiencia) {
		this.numeroDiasAudiencia = numeroDiasAudiencia;
	}
	public Long getTotalDias() {
		return totalDias;
	}
	public void setTotalDias(Long totalDias) {
		this.totalDias = totalDias;
	}
	public Long getNumeroAudienciasRealizadas() {
		return numeroAudienciasRealizadas;
	}
	public void setNumeroAudienciasRealizadas(Long numeroAudienciasRealizadas) {
		this.numeroAudienciasRealizadas = numeroAudienciasRealizadas;
	}
	public String getUltimoEstadoCaso() {
		return ultimoEstadoCaso;
	}
	public void setUltimoEstadoCaso(String ultimoEstadoCaso) {
		this.ultimoEstadoCaso = ultimoEstadoCaso;
	}
	public String getResultadoCaso() {
		return resultadoCaso;
	}
	public void setResultadoCaso(String resultadoCaso) {
		this.resultadoCaso = resultadoCaso;
	}
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getMateriaCaso() {
		return materiaCaso;
	}
	public void setMateriaCaso(String materiaCaso) {
		this.materiaCaso = materiaCaso;
	}
	public String getCuantiaCaso() {
		return cuantiaCaso;
	}
	public void setCuantiaCaso(String cuantiaCaso) {
		this.cuantiaCaso = cuantiaCaso;
	}
	public String getObservacionesCaso() {
		return observacionesCaso;
	}
	public void setObservacionesCaso(String observacionesCaso) {
		this.observacionesCaso = observacionesCaso;
	}
	public String getHechos() {
		return hechos;
	}
	public void setHechos(String hechos) {
		this.hechos = hechos;
	}
	public String getPretensiones() {
		return pretensiones;
	}
	public void setPretensiones(String pretensiones) {
		this.pretensiones = pretensiones;
	}
	public String getApoderado() {
		return apoderado;
	}
	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Double getValorTotalAcuerdo() {
		return valorTotalAcuerdo;
	}
	public void setValorTotalAcuerdo(Double valorTotalAcuerdo) {
		this.valorTotalAcuerdo = valorTotalAcuerdo;
	}
	
}
