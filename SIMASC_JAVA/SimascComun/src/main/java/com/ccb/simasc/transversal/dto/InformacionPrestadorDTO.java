package com.ccb.simasc.transversal.dto;

import java.util.List;

/** 
 * DTO que contiene la informacion que se debe presentar en el caso de uso TRANS-F-020
 * @author prendon
 *
 */
public class InformacionPrestadorDTO {
	private String nombreRol;
	private String nombresPrestador;
	private String apellidosPrestador;
	private String nacionalidad;
	private String hojaDeVida;
	private String materias;
	private String idiomas;
	private String direccion;
	private String numeroTelefono;
	private String numeroCelular;
	private List<String> correosElectronicos;
	
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getNombresPrestador() {
		return nombresPrestador;
	}
	public void setNombresPrestador(String nombresPrestador) {
		this.nombresPrestador = nombresPrestador;
	}
	public String getApellidosPrestador() {
		return apellidosPrestador;
	}
	public void setApellidosPrestador(String apellidosPrestador) {
		this.apellidosPrestador = apellidosPrestador;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getMaterias() {
		return materias;
	}
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	public String getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public List<String> getCorreosElectronicos() {
		return correosElectronicos;
	}
	public void setCorreosElectronicos(List<String> correosElectronicos) {
		this.correosElectronicos = correosElectronicos;
	}
	public String getHojaDeVida() {
		return hojaDeVida;
	}
	public void setHojaDeVida(String hojaDeVida) {
		this.hojaDeVida = hojaDeVida;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

}
