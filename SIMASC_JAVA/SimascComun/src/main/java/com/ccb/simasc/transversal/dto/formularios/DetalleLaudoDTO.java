package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.RecursoLaudoDTO;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class DetalleLaudoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idLaudo;
	private String nombreCaso;
	private Long idCaso;
	private String arbitros;
	private Date fecha;
	private String estado;
	private ArrayList<RecursoLaudoDTO> recursos;
	private Long idDocumento;
		
	
	public String getArbitros() {
		return arbitros;
	}
	public void setArbitros(String arbitros) {
		this.arbitros = arbitros;
	}
	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
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
	public Long getIdLaudo() {
		return idLaudo;
	}
	public void setIdLaudo(Long idLaudo) {
		this.idLaudo = idLaudo;
	}
	public String getNombreCaso() {
		return nombreCaso;
	}
	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ArrayList<RecursoLaudoDTO> getRecursos() {
		return recursos;
	}
	public void setRecursos(ArrayList<RecursoLaudoDTO> recursos) {
		this.recursos = recursos;
	}
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}	
			
}
