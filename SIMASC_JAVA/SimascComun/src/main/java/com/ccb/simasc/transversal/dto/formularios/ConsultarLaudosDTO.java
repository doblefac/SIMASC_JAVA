package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class ConsultarLaudosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fechaIni;
	private Date fechaFin;
	private String arbitros;
	private String partes;
	private String docParte;
	private Long idCaso;
	private Long materia;
	private String estado;
	
	
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getArbitros() {
		return arbitros;
	}
	public void setArbitros(String arbitros) {
		this.arbitros = arbitros;
	}
	public String getPartes() {
		return partes;
	}
	public void setPartes(String partes) {
		this.partes = partes;
	}
	public String getDocParte() {
		return docParte;
	}
	public void setDocParte(String idParte) {
		this.docParte = idParte;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public Long getMateria() {
		return materia;
	}
	public void setMateria(Long materia) {
		this.materia = materia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
			
}
