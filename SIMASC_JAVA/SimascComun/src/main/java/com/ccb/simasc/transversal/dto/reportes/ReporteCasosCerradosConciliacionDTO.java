package com.ccb.simasc.transversal.dto.reportes;

import java.util.Date;

public class ReporteCasosCerradosConciliacionDTO {
	
	private Date fechaRadicacion;
	private Integer mesResultado;
	private Long codigoCaso;
	private String nombreCaso;
	private String tipoCaso;	
	private Date fechaRegistro;
	private String estadoCaso;
	private String resultado;
	private String estadoResultado;
	private String nombreConciliador;
	private String apoderado;
	private String hechos;
	private String pretensiones;
	private Long valorAcuerdo;
	private String sedeAudiencia;
	private Integer numeroAudiencias;
	private Date fechaUltimaAudiencia;
	
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public Integer getMesResultado() {
		return mesResultado;
	}
	public void setMesResultado(Integer mesResultado) {
		this.mesResultado = mesResultado;
	}
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getNombreConciliador() {
		return nombreConciliador;
	}
	public void setNombreConciliador(String nombreConciliador) {
		this.nombreConciliador = nombreConciliador;
	}
	public String getApoderado() {
		return apoderado;
	}
	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
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
	public Long getValorAcuerdo() {
		return valorAcuerdo;
	}
	public void setValorAcuerdo(Long valorAcuerdo) {
		this.valorAcuerdo = valorAcuerdo;
	}
	public String getSedeAudiencia() {
		return sedeAudiencia;
	}
	public void setSedeAudiencia(String sedeAudiencia) {
		this.sedeAudiencia = sedeAudiencia;
	}
	public Integer getNumeroAudiencias() {
		return numeroAudiencias;
	}
	public void setNumeroAudiencias(Integer numeroAudiencias) {
		this.numeroAudiencias = numeroAudiencias;
	}
	public Date getFechaUltimaAudiencia() {
		return fechaUltimaAudiencia;
	}
	public void setFechaUltimaAudiencia(Date fechaUltimaAudiencia) {
		this.fechaUltimaAudiencia = fechaUltimaAudiencia;
	}
	public String getEstadoCaso() {
		return estadoCaso;
	}
	public void setEstadoCaso(String estadoCaso) {
		this.estadoCaso = estadoCaso;
	}
	public String getEstadoResultado() {
		return estadoResultado;
	}
	public void setEstadoResultado(String estadoResultado) {
		this.estadoResultado = estadoResultado;
	}
}
