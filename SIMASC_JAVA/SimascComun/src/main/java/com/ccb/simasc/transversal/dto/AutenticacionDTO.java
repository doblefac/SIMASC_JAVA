/**
 * 
 */
package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dbarrera
 *
 */
@XmlRootElement
public class AutenticacionDTO implements Serializable{
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String nombreUsuario;
	private List<Object> parametros;
	// protected region atributos end


	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public List<Object> getParametros() {
		return parametros;
	}
	public void setParametros(List<Object> parametros) {
		this.parametros = parametros;
	}
	// protected region metodos adicionales end
}
