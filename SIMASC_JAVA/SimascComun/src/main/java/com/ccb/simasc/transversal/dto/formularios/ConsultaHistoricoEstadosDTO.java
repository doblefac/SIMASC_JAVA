package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * ADM-C-022
 * DAO que contiene la información a mostrar en la tabla de consulta de estados del funcionario
 * 
 * @author jsoto
 */
@XmlRootElement
public class ConsultaHistoricoEstadosDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 307770462082443098L;
	
	private String tipoServicio;
	private Date fechaAsignacionEstado;
	private String estado;
	
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Date getFechaAsignacionEstado() {
		return fechaAsignacionEstado;
	}
	public void setFechaAsignacionEstado(Date fechaAsignacionEstado) {
		this.fechaAsignacionEstado = fechaAsignacionEstado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

	
	
}
