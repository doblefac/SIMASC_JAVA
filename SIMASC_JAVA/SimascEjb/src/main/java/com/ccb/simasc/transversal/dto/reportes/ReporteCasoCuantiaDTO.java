package com.ccb.simasc.transversal.dto.reportes;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO con campos definidos para el reporte cuantia pretension
 * 
 * @author dpachon
 */
public class ReporteCasoCuantiaDTO implements Serializable{

	private static final long serialVersionUID = 1L;	
	
	private String nombreCaso;
	private Date fechaCaso;
	private String tipoCaso;
	private String materia;
	private String tipoCuantia;
	private String valorPretension;
	private String consumo;
	private String amparoDePobreza;
	private String concedeAmparo;
	
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
	 * @return the tipoCaso
	 */
	public String getTipoCaso() {
		return tipoCaso;
	}
	/**
	 * @param tipoCaso the tipoCaso to set
	 */
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	/**
	 * @return the materia
	 */
	public String getMateria() {
		return materia;
	}
	/**
	 * @param materia the materia to set
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}
	/**
	 * @return the tipoCuantia
	 */
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	/**
	 * @param tipoCuantia the tipoCuantia to set
	 */
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	/**
	 * @return the valorPretension
	 */
	public String getValorPretension() {
		return valorPretension;
	}
	/**
	 * @param valorPretension the valorPretension to set
	 */
	public void setValorPretension(String valorPretension) {
		this.valorPretension = valorPretension;
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
	 * @return the amparoDePobreza
	 */
	public String getAmparoDePobreza() {
		return amparoDePobreza;
	}
	/**
	 * @param amparoDePobreza the amparoDePobreza to set
	 */
	public void setAmparoDePobreza(String amparoDePobreza) {
		this.amparoDePobreza = amparoDePobreza;
	}
	/**
	 * @return the concedeAmparo
	 */
	public String getConcedeAmparo() {
		return concedeAmparo;
	}
	/**
	 * @param concedeAmparo the concedeAmparo to set
	 */
	public void setConcedeAmparo(String concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}	
	
}
