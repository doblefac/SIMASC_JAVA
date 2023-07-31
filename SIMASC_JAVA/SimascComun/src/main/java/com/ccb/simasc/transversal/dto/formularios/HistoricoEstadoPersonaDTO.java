package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

public class HistoricoEstadoPersonaDTO {
	/**
	 * DAO que contene la información que se despliega para el caso de uso ADM-C-022
	 * Este formulario esta construido para los servicios REST
	 * 
	 * @author john.reyes
	 */
	private long idRol;
	private String rol;
	private String estado;
	private String motivo;
	private Date fecha;
	 
	public HistoricoEstadoPersonaDTO() {
	}
	
	public HistoricoEstadoPersonaDTO(long idRol, String nombreRol,
			String estado, String motivo, Date fecha) {
		this.idRol = idRol;
		this.rol = nombreRol;
		this.estado = estado;
		this.motivo = motivo;
		this.fecha = fecha;
	}



	public long getIdRol() {
		return idRol;
	}
	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
