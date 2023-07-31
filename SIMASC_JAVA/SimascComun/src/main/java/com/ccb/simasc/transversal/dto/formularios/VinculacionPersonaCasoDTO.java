/**
 * 
 */
package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

import com.ccb.simasc.transversal.entidades.Caso;

/**
 * TRANS-F-022	SL-CU-Consultar-vinculaci¢n-de-persona-a-un-caso
 * @author lvalbuena
 *
 */
public class VinculacionPersonaCasoDTO {
	
	private String servicio;
	private Long codigoCaso;
	private Caso caso;
	private String nombreCaso;
	private Date fechaRadicacion;
	private String estado;



	/**
	 * @param servicio
	 * @param codigoCaso
	 * @param nombreCaso
	 * @param fechaRadicacion
	 * @param estado
	 */
	public VinculacionPersonaCasoDTO(String servicio, Long codigoCaso, String nombreCaso, Date fechaRadicacion,
			String estado) {
		this.servicio = servicio;
		this.codigoCaso = codigoCaso;
		this.nombreCaso = nombreCaso;
		this.fechaRadicacion = fechaRadicacion;
		this.estado = estado;
	}


	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}


	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}


	/**
	 * @return the codigoCaso
	 */
	public Long getCodigoCaso() {
		return codigoCaso;
	}


	/**
	 * @param codigoCaso the codigoCaso to set
	 */
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}


	/**
	 * @return the nombreCaso
	 */
	public String getNombreCaso() {
		return nombreCaso;
	}


	/**
	 * @param nombreCaso the nombreCaso to set
	 */
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}


	/**
	 * @return the fechaRadicacion
	 */
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}


	/**
	 * @param fechaRadicacion the fechaRadicacion to set
	 */
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}


	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	public Caso getCaso() {
		return caso;
	}


	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	
	
	

}
