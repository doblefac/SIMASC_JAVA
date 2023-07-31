package com.ccb.simasc.transversal.dto.cartas;

public class LotesCartasDTO {
	private long codigoCaso;
	private long idPersona;
	private String nombreParte;
	private String rol;
	private String correo;
	private boolean selected;
	private boolean enviarCorreo;
	private long idAudiencia;
	
	public LotesCartasDTO() {
		super();
	}

	public long getCodigoCaso() {
		return codigoCaso;
	}

	public void setCodigoCaso(long codigoCaso) {
		this.codigoCaso = codigoCaso;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombreParte() {
		return nombreParte;
	}

	public void setNombreParte(String nombreParte) {
		this.nombreParte = nombreParte;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEnviarCorreo() {
		return enviarCorreo;
	}

	public void setEnviarCorreo(boolean enviarCorreo) {
		this.enviarCorreo = enviarCorreo;
	}

	public long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	
	

}
