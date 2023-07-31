package com.ccb.simasc.transversal.dto.reportes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ReporteOperadoresSuspendidosDTO implements Serializable{	

	private static final long serialVersionUID = 7179764052906356502L;

	private String operador;
	private String rol;
	private String motivo;	
	private String fechaInicial;
	private String fechaFinal;
	private Long codigoCaso;
	private String nombreCaso;
	private String servicio;
	
    public ReporteOperadoresSuspendidosDTO(){}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

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

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	


	
}
