package com.ccb.simasc.transversal.dto.formularios;

/**
 * DAO que contene la información del formulario para registrar el resultado de la audiencia.
 * Este formulario esta construido para los servicios REST
 * 
 * @author fguzman
 */
public class DatosResultadoAudienciaDTO {	


	private Long idAudiencia;
	private Long idCaso;
	private String estadoResultado;
	private String tipoAudiencia;
	private String descripcionEstadoResultado;
	private String descripcionTipoAudiencia;
	private String observaciones;
	private String resultado;
	private String descripcionResultado;
	private Long nombramientoPresidente;
	private Long nombramientoSecretario;
	
    public DatosResultadoAudienciaDTO(){

    }

	public Long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public String getTipoAudiencia() {
		return tipoAudiencia;
	}

	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getEstadoResultado() {
		return estadoResultado;
	}

	public void setEstadoResultado(String estadoResultado) {
		this.estadoResultado = estadoResultado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Long getNombramientoPresidente() {
		return nombramientoPresidente;
	}

	public void setNombramientoPresidente(Long nombramientoPresidente) {
		this.nombramientoPresidente = nombramientoPresidente;
	}

	public Long getNombramientoSecretario() {
		return nombramientoSecretario;
	}

	public void setNombramientoSecretario(Long nombramientoSecretario) {
		this.nombramientoSecretario = nombramientoSecretario;
	}

	public String getDescripcionEstadoResultado() {
		return descripcionEstadoResultado;
	}

	public void setDescripcionEstadoResultado(String descripcionEstadoResultado) {
		this.descripcionEstadoResultado = descripcionEstadoResultado;
	}

	public String getDescripcionTipoAudiencia() {
		return descripcionTipoAudiencia;
	}

	public void setDescripcionTipoAudiencia(String descripcionTipoAudiencia) {
		this.descripcionTipoAudiencia = descripcionTipoAudiencia;
	}

	public String getDescripcionResultado() {
		return descripcionResultado;
	}

	public void setDescripcionResultado(String descripcionResultado) {
		this.descripcionResultado = descripcionResultado;
	}


	

}
