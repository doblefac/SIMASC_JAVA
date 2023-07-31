package com.ccb.simasc.transversal.dto.reportes;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

/**
 * DTO que contiene la información del reporte de casos sorteados
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteCasosSorteadosDTO {

	private Long idCaso;
	private String nombreCaso;
	private String tipoCaso;
	private String tipoCuantia;
	private String valorPretensiones;
	private String materia;
	private boolean preseleccion;
	private String preseleccionLabel;
	private String fechaSorteo;
//	private String arbitros;
	private String nombramiento;
	private String consumo;
	private String tipoPreseleccion;
	private String tipoSorteo;
	
	private List<RolPersonaCaso> arbitros;
	
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	
	public String getTipoCuantia() {
		String tipoCuantiaDescripcion = "";
		switch (tipoCuantia) {
		case UtilDominios.TIPO_CUANTIA_MAYOR:
			tipoCuantiaDescripcion = UtilConstantes.TIPO_CUANTIA_MAYOR;
			break;
		case UtilDominios.TIPO_CUANTIA_MENOR:
			tipoCuantiaDescripcion = UtilConstantes.TIPO_CUANTIA_MENOR;
			break;
		case UtilDominios.TIPO_CUANTIA_INDETERMINADO:
			tipoCuantiaDescripcion = UtilConstantes.TIPO_CUANTIA_INDERTERMINADA;
			break;
		default:
			tipoCuantiaDescripcion = UtilConstantes.TIPO_CUANTIA_MAYOR;
			break;
		}
		return tipoCuantiaDescripcion;
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
	public boolean getPreseleccion() {
		return preseleccion;
	}
	public void setPreseleccion(boolean preseleccion) {
		this.preseleccion = preseleccion;
	}
	
//	public String getArbitros() {
//		return arbitros;
//	}
//	public void setArbitros(String arbitros) {
//		this.arbitros = arbitros;
//	}
	public String getNombramiento() {
		return nombramiento;
	}
	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}
	/**
	 * @return the fechaSorteo
	 */
	public String getFechaSorteo() {
		return fechaSorteo;
	}
	/**
	 * @param fechaSorteo the fechaSorteo to set
	 */
	public void setFechaSorteo(String fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}
	/**
	 * @return the arbitros
	 */
	public List<RolPersonaCaso> getArbitros() {
		return arbitros;
	}
	/**
	 * @param arbitros the arbitros to set
	 */
	public void setArbitros(List<RolPersonaCaso> arbitros) {
		this.arbitros = arbitros;
	}
	/**
	 * @return the preseleccionString
	 */
	public String getPreseleccionLabel() {
		preseleccionLabel = UtilConstantes.NO;
		if (this.preseleccion) {
			preseleccionLabel = UtilConstantes.SI;
		}
		return preseleccionLabel;
	}
	/**
	 * @param preseleccionString the preseleccionString to set
	 */
	public void setPreseleccionString(String preseleccionString) {
		this.preseleccionLabel = preseleccionString;
	}
	/**
	 * @return the idCaso
	 */
	public Long getIdCaso() {
		return idCaso;
	}
	/**
	 * @param idCaso the idCaso to set
	 */
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}
	public String getTipoSorteo() {
		return tipoSorteo;
	}
	public void setTipoSorteo(String tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}	
}
