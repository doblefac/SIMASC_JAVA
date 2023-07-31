package com.ccb.simasc.transversal.dto.formularios;

import com.ccb.simasc.transversal.entidades.Pronunciamiento;

/**
 * DAO que contiene la información del formulario para el registro de un
 * pronunciamiento de un árbitro. Este formulario esta construido para los
 * servicios REST
 * 
 * @author
 */
public class InfoPronunciamientoDTO {
	
	private Pronunciamiento pronunciamiento;
	private String usuario;
	
	public Pronunciamiento getPronunciamiento() {
		return pronunciamiento;
	}
	
	public void setPronunciamiento(Pronunciamiento pronunciamiento) {
		this.pronunciamiento = pronunciamiento;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
