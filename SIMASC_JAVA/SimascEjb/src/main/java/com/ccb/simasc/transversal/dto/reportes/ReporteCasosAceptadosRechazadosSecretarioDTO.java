package com.ccb.simasc.transversal.dto.reportes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO que contiene la información del reporte de los
 * casos aceptados y rechazados por secretario
 * 
 * @author aalvarez
 */

@XmlRootElement
public class ReporteCasosAceptadosRechazadosSecretarioDTO {
	
	private Long codigoCaso;
	private String nombreCaso;
	private String fechaRadicacionCaso;
	private String tipoCaso;
	private String tipoCuantia;
	private String materia;
	private String nombreSecretario;
	private String arbitrosLista;	
	private String pronunciamiento;
	private String fechaNotificacionNombramiento;
	private String fechaAceptacionEncargo;
	private String consumo;
	private String solicitaAmparo;
	private String concedeAmparo;
	
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
	public String getFechaRadicacionCaso() {
		return fechaRadicacionCaso;
	}
	public void setFechaRadicacionCaso(String fechaRadicacionCaso) {
		this.fechaRadicacionCaso = fechaRadicacionCaso;
	}
	public String getTipoCaso() {
		return tipoCaso;
	}
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	public String getTipoCuantia() {
		return tipoCuantia;
	}
	public void setTipoCuantia(String tipoCuantia) {
		this.tipoCuantia = tipoCuantia;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getNombreSecretario() {
		return nombreSecretario;
	}
	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}

	public String getArbitrosLista() {
		return arbitrosLista;
	}
	public void setArbitrosLista(String arbitrosLista) {
		this.arbitrosLista = arbitrosLista;
	}
	public String getPronunciamiento() {
		return pronunciamiento;
	}
	public void setPronunciamiento(String pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	public String getFechaNotificacionNombramiento() {
		return fechaNotificacionNombramiento;
	}
	public void setFechaNotificacionNombramiento(String fechaNotificacionNombramiento) {
		this.fechaNotificacionNombramiento = fechaNotificacionNombramiento;
	}
	public String getFechaAceptacionEncargo() {
		return fechaAceptacionEncargo;
	}
	public void setFechaAceptacionEncargo(String fechaAceptacionEncargo) {
		this.fechaAceptacionEncargo = fechaAceptacionEncargo;
	}
	public String getConsumo() {
		return consumo;
	}
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	public String getSolicitaAmparo() {
		return solicitaAmparo;
	}
	public void setSolicitaAmparo(String solicitaAmparo) {
		this.solicitaAmparo = solicitaAmparo;
	}
	public String getConcedeAmparo() {
		return concedeAmparo;
	}
	public void setConcedeAmparo(String concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}
	
}
