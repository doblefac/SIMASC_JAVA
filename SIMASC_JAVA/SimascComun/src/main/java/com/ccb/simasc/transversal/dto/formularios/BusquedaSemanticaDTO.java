package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

public class BusquedaSemanticaDTO {

	private Long idDocumento;
	private String nombreDocumento;
	private String descripcionDocumento;
	private String tipoArchivo;
	private String url;
	private String estado;
	private Date fechaCargue;
	private Date fechaRadicacion;
	private String nombreCuaderno;
	private String nombreCarpeta;
	
	
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaCargue() {
		return fechaCargue;
	}
	public void setFechaCargue(Date fechaCargue) {
		this.fechaCargue = fechaCargue;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public String getNombreCuaderno() {
		return nombreCuaderno;
	}
	public void setNombreCuaderno(String nombreCuaderno) {
		this.nombreCuaderno = nombreCuaderno;
	}
	public String getNombreCarpeta() {
		return nombreCarpeta;
	}
	public void setNombreCarpeta(String nombreCarpeta) {
		this.nombreCarpeta = nombreCarpeta;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	
	
}
