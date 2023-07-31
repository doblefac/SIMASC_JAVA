package com.ccb.simasc.transversal.dto.formularios;

public class TipoDocumentalDTO {

	private String codigo;
	private String dominio;
	private String nombre;
	private String descripcion;
	private Boolean es_virtual;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEs_virtual() {
		return es_virtual;
	}
	public void setEs_virtual(Boolean es_virtual) {
		this.es_virtual = es_virtual;
	}
	
}
