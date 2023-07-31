package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * ADM-C-020
 * DAO que contiene los valores por lo que se puede filtrar la consulta de usuarios del sistema.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FiltroBusquedaUsuarioSistemaDTO implements Serializable {	

	private static final long serialVersionUID = 4023568011331160796L;
	
	private String nombres;
	private String apellidos;
	//Rol de los usuarios a buscar
	private String rol;
	//Lista de los nombres de los roles que puede consultar el usuario
	private String grupoUsuarioConsulta;
	
    public FiltroBusquedaUsuarioSistemaDTO(){
	
    }

	public FiltroBusquedaUsuarioSistemaDTO(String nombres, String apellidos, String rol, String grupoUsuarioConsulta) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rol = rol;
		this.grupoUsuarioConsulta = grupoUsuarioConsulta;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}	
	
	public boolean definenRol(){
		return this.rol!=null && !this.rol.isEmpty();			
	}
	
	public boolean definenApellidos(){
		return this.apellidos!=null && !this.apellidos.isEmpty();
	}
	
	public boolean definenNombres(){
		return this.nombres!=null && !this.nombres.isEmpty();
	}


	public String getGrupoUsuarioConsulta() {
		return this.grupoUsuarioConsulta;
	}


	public void setGrupoUsuarioConsulta(String grupoUsuarioConsulta) {
		this.grupoUsuarioConsulta = grupoUsuarioConsulta;
	}

	
	
}
