package com.ccb.simasc.transversal.dto.formularios;

import java.util.Date;

import com.ccb.simasc.transversal.dto.PronunciamientoDTO;

/**
 * DAO que contiene la informaciÃ³n del formulario para el registro de un
 * pronunciamiento de un Ã¡rbitro. Este formulario esta construido para los
 * servicios REST
 * ARB-F-072
 * 
 * @author
 */
public class InfoRegistroPronunciamientoDTO {
	
	private String nombreArbitro;
	private Date fechaDesignacion;
	private Date fechaComunicacion; 
	//No es actualizable, solo de consulta
	private String metodoNombramiento;
	private PronunciamientoDTO pronunciamiento;
	private String usuario;	
	private Long idDocumentoAnterior;
		
	public String getNombreArbitro() {
		return nombreArbitro;
	}

	public void setNombreArbitro(String nombreArbitro) {
		this.nombreArbitro = nombreArbitro;
	}

	public Date getFechaDesignacion() {
		return fechaDesignacion;
	}

	public void setFechaDesignacion(Date fechaDesignacion) {
		this.fechaDesignacion = fechaDesignacion;
	}

	public Date getFechaComunicacion() {
		return fechaComunicacion;
	}

	public void setFechaComunicacion(Date fechaComunicacion) {
		this.fechaComunicacion = fechaComunicacion;
	}	

	public String getMetodoNombramiento() {
		return metodoNombramiento;
	}

	public void setMetodoNombramiento(String metodoNombramiento) {
		this.metodoNombramiento = metodoNombramiento;
	}

	public PronunciamientoDTO getPronunciamiento() {
		return pronunciamiento;
	}
	
	public void setPronunciamiento(PronunciamientoDTO pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getIdDocumentoAnterior() {
		return idDocumentoAnterior;
	}

	public void setIdDocumentoAnterior(Long idDocumentoAnterior) {
		this.idDocumentoAnterior = idDocumentoAnterior;
	}

}
