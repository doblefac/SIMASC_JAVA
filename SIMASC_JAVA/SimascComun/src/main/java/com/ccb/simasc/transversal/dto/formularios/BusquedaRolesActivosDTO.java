package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;
import java.util.List;

public class BusquedaRolesActivosDTO {
	private List<String> roles;
	private List<Long> centro;
	private String estadoRolPersonaCaso;
	private String estadoTipoServicio;
	private Date fechaVigenciaRol;
	private Long idMateria;
	
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	/**
	 * @return the centro
	 */
	public List<Long> getCentro() {
		return centro;
	}
	/**
	 * @param centro the centro to set
	 */
	public void setCentro(List<Long> centro) {
		this.centro = centro;
	}
	
	/**
	 * @return the estadoRolPersonaCaso
	 */
	public String getEstadoRolPersonaCaso() {
		return estadoRolPersonaCaso;
	}
	/**
	 * @param estadoRolPersonaCaso the estadoRolPersonaCaso to set
	 */
	public void setEstadoRolPersonaCaso(String estadoRolPersonaCaso) {
		this.estadoRolPersonaCaso = estadoRolPersonaCaso;
	}
	/**
	 * @return the estadoTipoServicio
	 */
	public String getEstadoTipoServicio() {
		return estadoTipoServicio;
	}
	/**
	 * @param estadoTipoServicio the estadoTipoServicio to set
	 */
	public void setEstadoTipoServicio(String estadoTipoServicio) {
		this.estadoTipoServicio = estadoTipoServicio;
	}
	/**
	 * @return the fechaVigenciaRol
	 */
	public Date getFechaVigenciaRol() {
		return fechaVigenciaRol;
	}
	/**
	 * @param fechaVigenciaRol the fechaVigenciaRol to set
	 */
	public void setFechaVigenciaRol(Date fechaVigenciaRol) {
		this.fechaVigenciaRol = fechaVigenciaRol;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	

}
