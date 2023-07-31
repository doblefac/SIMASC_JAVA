package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de casos cerrados
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteCasosCerradosDTO {

	private Long codigoCaso;
	private int consecutivo;
	private String nombreCaso;
	private String fechaRadicacion;
	private String valorPretensiones;
	private String materia;
	private String apoderadoDemandante;
	private String apoderadoDemandado;
	private String arbitros;
	private String secretario;
	private String fechaCierre;
	private String motivoCierre;
	private String observacionesCierre;
	private String servicioCaso;
	private String consumo;
	
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
	public String getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(String fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public String getValorPretensiones() {
		return valorPretensiones;
	}
	public void setValorPretensiones(String valorPretensiones) {
		this.valorPretensiones = valorPretensiones;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getApoderadoDemandante() {
		return apoderadoDemandante;
	}
	public void setApoderadoDemandante(String apoderadoDemandante) {
		this.apoderadoDemandante = apoderadoDemandante;
	}
	public String getApoderadoDemandado() {
		return apoderadoDemandado;
	}
	public void setApoderadoDemandado(String apoderadoDemandado) {
		this.apoderadoDemandado = apoderadoDemandado;
	}
	public String getArbitros() {
		return arbitros;
	}
	public void setArbitros(String arbitros) {
		this.arbitros = arbitros;
	}
	public String getSecretario() {
		return secretario;
	}
	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getMotivoCierre() {
		return motivoCierre;
	}
	public void setMotivoCierre(String motivoCierre) {
		this.motivoCierre = motivoCierre;
	}
	public String getObservacionesCierre() {
		return observacionesCierre;
	}
	public void setObservacionesCierre(String observacionesCierre) {
		this.observacionesCierre = observacionesCierre;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	/**
	 * @return the servicioCaso
	 */
	public String getServicioCaso() {
		return servicioCaso;
	}
	/**
	 * @param servicioCaso the servicioCaso to set
	 */
	public void setServicioCaso(String servicioCaso) {
		this.servicioCaso = servicioCaso;
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
}
