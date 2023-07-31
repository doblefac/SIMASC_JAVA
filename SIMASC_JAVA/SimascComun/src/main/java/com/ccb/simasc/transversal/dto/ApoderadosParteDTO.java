package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

/**
 * Se utiliza para almacenar la informacion de los apoderados de la parte
 * @author Aospinag
 *
 */
public class ApoderadosParteDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String nombreApoderado;
	private String parteRepresenta;
	
	public String getNombreApoderado() {
		return nombreApoderado;
	}
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}
	public String getParteRepresenta() {
		return parteRepresenta;
	}
	public void setParteRepresenta(String parteRepresenta) {
		this.parteRepresenta = parteRepresenta;
	}

}
