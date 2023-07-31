package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de casos suspendidos
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteCasosSuspendidosDTO {

	private Long codigoCaso;
	private String nombreCaso;
	private String secretario;
	private String motivoSuspension;
	private String Servicio;
	private String consumo;
	private String etapa;
	private String fechaInicialSuspension;
	private String fechaFinalSuspension;


	
	public Long getCodigoCaso() {
		return codigoCaso;
	}
	public void setCodigoCaso(Long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getSecretario() {
		return secretario;
	}
	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}
	public String getMotivoSuspension() {
		return motivoSuspension;
	}
	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}
	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return Servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	/**
	 * @return the consumo
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return the etapa
	 */
	public String getEtapa() {
		return etapa;
	}
	/**
	 * @param etapa the etapa to set
	 */
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	/**
	 * @return the fechaInicialSuspension
	 */
	public String getFechaInicialSuspension() {
		return fechaInicialSuspension;
	}
	/**
	 * @param fechaInicialSuspension the fechaInicialSuspension to set
	 */
	public void setFechaInicialSuspension(String fechaInicialSuspension) {
		this.fechaInicialSuspension = fechaInicialSuspension;
	}
	/**
	 * @return the fechaFinalSuspension
	 */
	public String getFechaFinalSuspension() {
		return fechaFinalSuspension;
	}
	/**
	 * @param fechaFinalSuspension the fechaFinalSuspension to set
	 */
	public void setFechaFinalSuspension(String fechaFinalSuspension) {
		this.fechaFinalSuspension = fechaFinalSuspension;
	}
	
		
}
