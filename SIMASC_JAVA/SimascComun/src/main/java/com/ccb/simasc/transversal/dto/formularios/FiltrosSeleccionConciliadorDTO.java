package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;

/**
 * DTO encargado de especificar los criterios de busqueda de Conciliadores
 * 
 * @author dpachon
 *
 */
public class FiltrosSeleccionConciliadorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombreRol;
	private Long idServicio;
	private Long idMateria;
	private Long idSede;
	
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
}
