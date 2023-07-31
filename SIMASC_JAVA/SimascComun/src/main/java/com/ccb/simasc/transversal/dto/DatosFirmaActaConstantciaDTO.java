package com.ccb.simasc.transversal.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene los datos necearios para la firma de un acta o constancia
 * resultado de una audiencia virtual
 * 
 * @author Asesoftware - Juan Diego Cepeda Mosquera
 */
@XmlRootElement
public class DatosFirmaActaConstantciaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCaso;
	private Long idPersona;
	private Long idRol;
	private Long idDocumento;
	private String claveSegura;
	private String direccionIPOrigen;
	private String direccionMACOrigen;

	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}

	/**
	 * @param idCaso
	 *            the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona
	 *            the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the idRol
	 */
	public Long getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            the idRol to set
	 */
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the idDocumento
	 */
	public Long getIdDocumento() {
		return idDocumento;
	}

	/**
	 * @param idDocumento
	 *            the idDocumento to set
	 */
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @return the claveSegura
	 */
	public String getClaveSegura() {
		return claveSegura;
	}

	/**
	 * @param claveSegura
	 *            the claveSegura to set
	 */
	public void setClaveSegura(String claveSegura) {
		this.claveSegura = claveSegura;
	}

	/**
	 * @return the direccionIPOrigen
	 */
	public String getDireccionIPOrigen() {
		return direccionIPOrigen;
	}

	/**
	 * @param direccionIPOrigen the direccionIPOrigen to set
	 */
	public void setDireccionIPOrigen(String direccionIPOrigen) {
		this.direccionIPOrigen = direccionIPOrigen;
	}

	/**
	 * @return the direccionMACOrigen
	 */
	public String getDireccionMACOrigen() {
		return direccionMACOrigen;
	}

	/**
	 * @param direccionMACOrigen the direccionMACOrigen to set
	 */
	public void setDireccionMACOrigen(String direccionMACOrigen) {
		this.direccionMACOrigen = direccionMACOrigen;
	}

}
