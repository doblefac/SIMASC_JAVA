package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * ADM-C-020
 * DAO que contiene los valores a presentar al usuario cuando consulta un usuario del sistema
 * 
 * @author jsoto
 */
@XmlRootElement
public class UsuarioSistemaConsultaDTO implements Serializable {	

	private static final long serialVersionUID = 7564243964723875698L;
	
	private Long idPersona;
	private String nombres;
	private String apellidos;
	//Lista de los roles que tiene la persona separada por comas
	private String roles;
	//Estado del usuario de la persona
	private String estado;
	
    public UsuarioSistemaConsultaDTO(){
	
    }

	public Long getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    

}
