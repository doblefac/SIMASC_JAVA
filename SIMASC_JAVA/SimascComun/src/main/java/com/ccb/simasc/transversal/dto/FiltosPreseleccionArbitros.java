package com.ccb.simasc.transversal.dto;

public class FiltosPreseleccionArbitros {

	private String idioma;
	private String leyAplicable;
	private String nacionalidad;
	private String idMateria;
	private String rol;
	private String lista;
	// Quien Preselecciona si partes o ccb
	private String quienPreselecciona;
	
	public FiltosPreseleccionArbitros() {
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getLeyAplicable() {
		return leyAplicable;
	}

	public void setLeyAplicable(String leyAplicable) {
		this.leyAplicable = leyAplicable;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}

	public String getQuienPreselecciona() {
		return quienPreselecciona;
	}

	public void setQuienPreselecciona(String quienPreselecciona) {
		this.quienPreselecciona = quienPreselecciona;
	}
	
}
