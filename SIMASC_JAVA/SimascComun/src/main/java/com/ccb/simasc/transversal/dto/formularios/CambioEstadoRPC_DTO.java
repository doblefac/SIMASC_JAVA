package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class CambioEstadoRPC_DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCaso;
	private List<Long> idPersonas;
	private Date fechaCambio;
	private String estado;
	private String motivo;
	private String observaciones;
	private Long idUsuario;
	
	public List<Long> getIdPersonas() {
		return idPersonas;
	}
	public void setIdPersonas(List<Long> listaRolPersonaCaso) {
		this.idPersonas = listaRolPersonaCaso;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Date getFechaCambio() {
		return fechaCambio;
	}
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
			
}
