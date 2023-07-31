package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

/**
 * Reporte CON-C-033 casos cerrados de convenio
 */
public class ReporteCasosCerradosConveniosDTO {
	
	private Date fechaRadicacion;
	private Long codigoCaso;
	private String nombreConvenio;
	private String partes;
	private String resultado;
	private Date fechaRegistro;
	private String nombreConciliador;
	private String apoderados;
	private String sedeAudiencia;
	private String hechos;
	private String cuantia;
	private Long valorAcuerdo;
	private Integer numeroAudiencias;
	
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	public String getPartes() {
		return partes;
	}
	public void setPartes(String partes) {
		this.partes = partes;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getApoderados() {
		return apoderados;
	}
	public void setApoderados(String apoderados) {
		this.apoderados = apoderados;
	}
	public String getSedeAudiencia() {
		return sedeAudiencia;
	}
	public void setSedeAudiencia(String sedeAudiencia) {
		this.sedeAudiencia = sedeAudiencia;
	}
	public String getHechos() {
		return hechos;
	}
	public void setHechos(String hechos) {
		this.hechos = hechos;
	}
	public String getCuantia() {
		return cuantia;
	}
	public void setCuantia(String cuantia) {
		this.cuantia = cuantia;
	}
	public Long getValorAcuerdo() {
		return valorAcuerdo;
	}
	public void setValorAcuerdo(Long valorAcuerdo) {
		this.valorAcuerdo = valorAcuerdo;
	}
	public Integer getNumeroAudiencias() {
		return numeroAudiencias;
	}
	public void setNumeroAudiencias(Integer numeroAudiencias) {
		this.numeroAudiencias = numeroAudiencias;
	}
}
