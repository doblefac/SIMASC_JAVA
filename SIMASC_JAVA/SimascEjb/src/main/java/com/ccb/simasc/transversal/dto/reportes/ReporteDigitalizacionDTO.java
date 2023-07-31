package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de Digitalziacion
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteDigitalizacionDTO {

	private Long codigoCaso;		
	private String nombreCaso;
	private String documento;
	private String tipoDocumento;	
	private String fechaAsignacion;		
	private String fechaDigitalizacion;
	private String estadoDigitalizacion;
	private String descripcionDocumento;
	private Long numeroFolios;
	private String nombreFuncionario;
	private String consumo;
	private String servicio;
	
	
	public String getNombreFuncionario() {
		return nombreFuncionario;
	}
	public void setNombreFuncionario(String nombreFuncionario) {
		this.nombreFuncionario = nombreFuncionario;
	}
	public String getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public String getFechaDigitalizacion() {
		return fechaDigitalizacion;
	}
	public void setFechaDigitalizacion(String fechaDigitalizacion) {
		this.fechaDigitalizacion = fechaDigitalizacion;
	}
	public String getEstadoDigitalizacion() {
		return estadoDigitalizacion;
	}
	public void setEstadoDigitalizacion(String estadoDigitalizacion) {
		this.estadoDigitalizacion = estadoDigitalizacion;
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
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	public Long getNumeroFolios() {
		return numeroFolios;
	}
	public void setNumeroFolios(Long numeroFolios) {
		this.numeroFolios = numeroFolios;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	
}
