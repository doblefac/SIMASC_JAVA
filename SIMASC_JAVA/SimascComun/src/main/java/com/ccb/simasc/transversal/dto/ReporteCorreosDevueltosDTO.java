package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ReporteCorreosDevueltosDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long casoReceptor;
	private String nombreCaso;
	private String tipoCaso;
	private String parte;
	private Date fecha;
	private String telefono;
	private Boolean contactado;
	private String observaciones;
	private Boolean confirmacionAsistencia;
	public Long getCasoReceptor() {
		return casoReceptor;
	}
	public void setCasoReceptor(Long casoReceptor) {
		this.casoReceptor = casoReceptor;
	}
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
	public String getParte() {
		return parte;
	}
	public void setParte(String parte) {
		this.parte = parte;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getContactado() {
		return contactado;
	}
	public void setContactado(Boolean contactado) {
		this.contactado = contactado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getConfirmacionAsistencia() {
		return confirmacionAsistencia;
	}
	public void setConfirmacionAsistencia(Boolean confirmacionAsistencia) {
		this.confirmacionAsistencia = confirmacionAsistencia;
	}
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
