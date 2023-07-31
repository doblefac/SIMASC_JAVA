package com.ccb.simasc.transversal.dto.formularios;

import java.util.List;

/**
 * DAO que contene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
public class GenericoDTO {
	
	private String id;
	private String nombre;
	private String tipo;
	private String idioma;
	private String idPais;
	private String  idLeyAplicable;
	private String idMateria;
	private String estadoSorteo;
	private String rol;
	private String lista;
	private String tipoPreseleccion;
	private List<Integer> materiasPreseleccion;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getIdPais() {
		return idPais;
	}
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}
	public String getIdLeyAplicable() {
		return idLeyAplicable;
	}
	public void setIdLeyAplicable(String idLeyAplicable) {
		this.idLeyAplicable = idLeyAplicable;
	}
	public String getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}
	public String getEstadoSorteo() {
		return estadoSorteo;
	}
	public void setEstadoSorteo(String estadoSorteo) {
		this.estadoSorteo = estadoSorteo;
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
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}
	public List<Integer> getMateriasPreseleccion() {
		return materiasPreseleccion;
	}
	public void setMateriasPreseleccion(List<Integer> materiasPreseleccion) {
		this.materiasPreseleccion = materiasPreseleccion;
	}	
	
	
}
