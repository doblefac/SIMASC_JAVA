package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;

// protected region imports dto end


/**
 * DAO que contiene la informacion básica que representa a un Rol
 * 
 * @author jsoto
 */
@XmlRootElement
public class RolBasicoDTO implements Serializable, Comparator<RolBasicoDTO>, Comparable<RolBasicoDTO>{
	
	private static final long serialVersionUID = 9150862631166327467L;
	
	private String codigoDominio;
	private String nombre;
	private boolean tieneLista; 
	private Long idRol;
	private String tipoServicio;
	
    public RolBasicoDTO(){
    	
    }
    
	public RolBasicoDTO(String codigoDominio, String nombre) {
		super();
		this.codigoDominio = codigoDominio;
		this.nombre = nombre;
	}

	public RolBasicoDTO(String codigoDominio, String nombre, boolean tieneLista) {
		super();
		this.codigoDominio = codigoDominio;
		this.nombre = nombre;
		this.tieneLista = tieneLista;
	}

	public String getCodigoDominio() {
		return codigoDominio;
	}

	public void setCodigoDominio(String codigoDominio) {
		this.codigoDominio = codigoDominio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	

	public boolean isTieneLista() {
		return tieneLista;
	}

	public void setTieneLista(boolean tieneLista) {
		this.tieneLista = tieneLista;
	}
	
	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
	/**
	 * @return the tipoServicio
	 */
	public String getTipoServicio() {
		return tipoServicio;
	}

	/**
	 * @param tipoServicio the tipoServicio to set
	 */
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}	

	@Override
	public int compare(RolBasicoDTO o1, RolBasicoDTO o2) {
		return o1.getNombre().compareTo(o2.getNombre());
	}

	@Override
	public int compareTo(RolBasicoDTO o) {
		return this.getNombre().compareTo(o.getNombre());
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((codigoDominio == null) ? 0 : codigoDominio.hashCode());
		result = PRIME * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolBasicoDTO other = (RolBasicoDTO) obj;
		if (codigoDominio == null) {
			if (other.codigoDominio != null)
				return false;
		} else if (!codigoDominio.equals(other.codigoDominio))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	
	
}
