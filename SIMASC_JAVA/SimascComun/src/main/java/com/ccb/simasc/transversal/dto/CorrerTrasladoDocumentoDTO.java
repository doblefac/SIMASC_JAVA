package com.ccb.simasc.transversal.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CorrerTrasladoDocumentoDTO {
	
	private String nombreDocumento;
	private String partes;
	private Date fechaTraslado;
	
	public CorrerTrasladoDocumentoDTO (){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getPartes() {
		return partes;
	}
	public void setPartes(String partes) {
		this.partes = partes;
	}	
	public Date getFechaTraslado() {
		return fechaTraslado;
	}
	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}

}
