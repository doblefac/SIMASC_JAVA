package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;



/**
 * DTO que contiene la información del reporte de casos pendientes
 * para sorteo publico de designacion
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteCasosPendientesSorteoPublicoDesignacionDTO {

	private Long codigoCaso;
	private String nombreCaso;
	private String tipoCuantia;
	private String valorPretensiones;
	private String tipoServicio;
	private String materia;
	private String fechaSorteo;	
	private int arbitrosPrincipales;
	private int arbitrosSuplentes;	
	private String preseleccion;
	private boolean preseleccionData;
	private String consumo;
	private String tipoPreseleccion;
	
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
	
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
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
	public String getFechaSorteo() {
		return fechaSorteo;
	}
	public void setFechaSorteo(String fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	public int getArbitrosPrincipales() {
		return arbitrosPrincipales;
	}
	public void setArbitrosPrincipales(int arbitrosPrincipales) {
		this.arbitrosPrincipales = arbitrosPrincipales;
	}
	public int getArbitrosSuplentes() {
		return arbitrosSuplentes;
	}
	public void setArbitrosSuplentes(int arbitrosSuplentes) {
		this.arbitrosSuplentes = arbitrosSuplentes;
	}
	
	public String getPreseleccion() {
		preseleccion = UtilConstantes.NO;
		if (preseleccionData) {
			preseleccion = UtilConstantes.SI;
		}		
		return preseleccion;
	}
	
	public void setPreseleccion(String preseleccion) {
		this.preseleccion = preseleccion;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	/**
	 * @return the preseleccionData
	 */
	public boolean isPreseleccionData() {
		return preseleccionData;
	}
	/**
	 * @param preseleccionData the preseleccionData to set
	 */
	public void setPreseleccionData(boolean preseleccionData) {
		this.preseleccionData = preseleccionData;
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
	 * @return the tipoPreseleccion
	 */
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	/**
	 * @param tipoPreseleccion the tipoPreseleccion to set
	 */
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}
	
}
