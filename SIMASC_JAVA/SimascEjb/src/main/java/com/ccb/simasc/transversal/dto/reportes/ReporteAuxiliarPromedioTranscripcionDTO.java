package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de 
 * auxiliar promedio transcripcion
 * 
 * @author aalvarez
 */
@XmlRootElement
public class ReporteAuxiliarPromedioTranscripcionDTO {

	private String periodo;
	private String nombreAuxiliar;
	private Long numeroAudienciasAtendidas;	
	private Double promedioDiarioMinutos;
	private Long persona;
	private int acumulado;
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getNombreAuxiliar() {
		return nombreAuxiliar;
	}
	public void setNombreAuxiliar(String nombreAuxiliar) {
		this.nombreAuxiliar = nombreAuxiliar;
	}
	public Double getPromedioDiarioMinutos() {
		return promedioDiarioMinutos;
	}
	public void setPromedioDiarioMinutos(Double promedioDiarioMinutos) {
		this.promedioDiarioMinutos = promedioDiarioMinutos;
	}
	
	public int getAcumulado() {
		return acumulado;
	}
	public void setAcumulado(int acumulado) {
		this.acumulado = acumulado;
	}
	public Long getPersona() {
		return persona;
	}
	public void setPersona(Long persona) {
		this.persona = persona;
	}
	public Long getNumeroAudienciasAtendidas() {
		return numeroAudienciasAtendidas;
	}
	public void setNumeroAudienciasAtendidas(Long numeroAudienciasAtendidas) {
		this.numeroAudienciasAtendidas = numeroAudienciasAtendidas;
	}
	
	
}
