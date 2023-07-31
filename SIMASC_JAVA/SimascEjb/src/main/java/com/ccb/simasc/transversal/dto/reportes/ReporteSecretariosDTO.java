package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de secretarios
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteSecretariosDTO {
	
	private String nombreSecretario;
	private String direccion;
	private String telefono;
	private String correoElectronico;
	private String estado;
	
	
	public String getNombreSecretario() {
		return nombreSecretario;
	}
	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
