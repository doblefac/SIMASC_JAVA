package com.ccb.simasc.transversal.dto.reportes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO con campos definidos para el reporte caso asignado secretario
 * 
 * @author dpachon
 */
public class ReporteCasoSecretarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private BigDecimal codigoCaso;
	private String nombreCaso;
	private Date fechaCaso;
	private String etapa;
	private BigDecimal diasTranscurridos;
	private String diasFaltantes;
	private BigDecimal diasSuspension;
	private BigDecimal diasInterrupcion;
	private Date fechaLaudo;
	private Date fechaCierre;
	private String nombreAuxiliar;
	private String estado;
	private BigDecimal diasProferirLaudo;
	
	/**
	 * @return the codigoCaso
	 */
	public BigDecimal getCodigoCaso() {
		return codigoCaso;
	}
	/**
	 * @param codigoCaso the codigoCaso to set
	 */
	public void setCodigoCaso(BigDecimal codigoCaso) {
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
	 * @return the fechaCaso
	 */
	public Date getFechaCaso() {
		return fechaCaso;
	}
	/**
	 * @param fechaCaso the fechaCaso to set
	 */
	public void setFechaCaso(Date fechaCaso) {
		this.fechaCaso = fechaCaso;
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
	 * @return the diasTranscurridos
	 */
	public BigDecimal getDiasTranscurridos() {
		return diasTranscurridos;
	}
	/**
	 * @param diasTranscurridos the diasTranscurridos to set
	 */
	public void setDiasTranscurridos(BigDecimal diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}
	/**
	 * @return the diasFaltantes
	 */
	public String getDiasFaltantes() {
		return diasFaltantes;
	}
	/**
	 * @param diasFaltantes the diasFaltantes to set
	 */
	public void setDiasFaltantes(String diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}
	/**
	 * @return the diasSuspension
	 */
	public BigDecimal getDiasSuspension() {
		return diasSuspension;
	}
	/**
	 * @param diasSuspension the diasSuspension to set
	 */
	public void setDiasSuspension(BigDecimal diasSuspension) {
		this.diasSuspension = diasSuspension;
	}
	/**
	 * @return the diasInterrupcion
	 */
	public BigDecimal getDiasInterrupcion() {
		return diasInterrupcion;
	}
	/**
	 * @param diasInterrupcion the diasInterrupcion to set
	 */
	public void setDiasInterrupcion(BigDecimal diasInterrupcion) {
		this.diasInterrupcion = diasInterrupcion;
	}
	/**
	 * @return the fechaLaudo
	 */
	public Date getFechaLaudo() {
		return fechaLaudo;
	}
	/**
	 * @param fechaLaudo the fechaLaudo to set
	 */
	public void setFechaLaudo(Date fechaLaudo) {
		this.fechaLaudo = fechaLaudo;
	}
	/**
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}
	/**
	 * @param fechaCierre the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	/**
	 * @return the nombreAuxiliar
	 */
	public String getNombreAuxiliar() {
		return nombreAuxiliar;
	}
	/**
	 * @param nombreAuxiliar the nombreAuxiliar to set
	 */
	public void setNombreAuxiliar(String nombreAuxiliar) {
		this.nombreAuxiliar = nombreAuxiliar;
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
	/**
	 * @return the diasProferirLaudo
	 */
	public BigDecimal getDiasProferirLaudo() {
		return diasProferirLaudo;
	}
	/**
	 * @param diasProferirLaudo the diasProferirLaudo to set
	 */
	public void setDiasProferirLaudo(BigDecimal diasProferirLaudo) {
		this.diasProferirLaudo = diasProferirLaudo;
	}
}
