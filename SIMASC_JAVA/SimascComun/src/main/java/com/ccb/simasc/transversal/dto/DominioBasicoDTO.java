package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * ADM-C-020
 * DTO que contiene información básica de un dominio.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DominioBasicoDTO implements Serializable {	

	private static final long serialVersionUID = 4023568011331160796L;
	
	private String codigo;
	private String nombre;
	
    public DominioBasicoDTO(){
	
    }    

	public DominioBasicoDTO(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
