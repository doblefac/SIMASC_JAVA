package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * DAO que contiene los parametros para realizar un reparto
 * 
 * @author jnmartinez
 *
 */

@XmlRootElement
public class RepartoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoServicio;
	private Long idCaso;
	private int indicadorReparto;
	private String rol;
	private int cantidadFuncionariosPrincipales;
	private int cantidadFuncionariosSuplentes;
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public int getIndicadorReparto() {
		return indicadorReparto;
	}
	public void setIndicadorReparto(int indicadorReparto) {
		this.indicadorReparto = indicadorReparto;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public int getCantidadFuncionariosPrincipales() {
		return cantidadFuncionariosPrincipales;
	}
	public void setCantidadFuncionariosPrincipales(int cantidadFuncionariosPrincipales) {
		this.cantidadFuncionariosPrincipales = cantidadFuncionariosPrincipales;
	}
	public int getCantidadFuncionariosSuplentes() {
		return cantidadFuncionariosSuplentes;
	}
	public void setCantidadFuncionariosSuplentes(int cantidadFuncionariosSuplentes) {
		this.cantidadFuncionariosSuplentes = cantidadFuncionariosSuplentes;
	}
	
	
	
}
