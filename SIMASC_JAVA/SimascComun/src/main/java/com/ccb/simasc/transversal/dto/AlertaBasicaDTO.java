package com.ccb.simasc.transversal.dto;

import java.util.List;

import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;

/**
 * dto de alertas con la lista de persona y roles a notificar
 * @author cbenavides
 *
 */
public class AlertaBasicaDTO {
	private Alerta alerta;
	private List<PersonaBasicaDTO> personas;
	private List<RolBasicoDTO> roles;
	private List<String> diaEjecucion;
	private String textoPersonas;
    private String textoRoles;
	/**
	 * @return the alerta
	 */
	public Alerta getAlerta() {
		return alerta;
	}
	/**
	 * @param alerta the alerta to set
	 */
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	/**
	 * @return the personas
	 */
	public List<PersonaBasicaDTO> getPersonas() {
		return personas;
	}
	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(List<PersonaBasicaDTO> personas) {
		this.personas = personas;
	}
	/**
	 * @return the roles
	 */
	public List<RolBasicoDTO> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RolBasicoDTO> roles) {
		this.roles = roles;
	}
	/**
	 * @return the diaEjecucion
	 */
	public List<String> getDiaEjecucion() {
		return diaEjecucion;
	}
	/**
	 * @param diaEjecucion the diaEjecucion to set
	 */
	public void setDiaEjecucion(List<String> diaEjecucion) {
		this.diaEjecucion = diaEjecucion;
	}
	/**
	 * @return the textoPersonas
	 */
	public String getTextoPersonas() {
		return textoPersonas;
	}
	/**
	 * @param textoPersonas the textoPersonas to set
	 */
	public void setTextoPersonas(String textoPersonas) {
		this.textoPersonas = textoPersonas;
	}
	/**
	 * @return the textoRoles
	 */
	public String getTextoRoles() {
		return textoRoles;
	}
	/**
	 * @param textoRoles the textoRoles to set
	 */
	public void setTextoRoles(String textoRoles) {
		this.textoRoles = textoRoles;
	}
	

}
