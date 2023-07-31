package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;

/**
 * DTO que contiene lista de personaBasicaDTO (partes de la audiencia pendiente) y la información 
 * correspondiente a la audiencia pendiente por realizar
 * 
 * @author cagonzalez
 */

@XmlRootElement
public class CitacionDTO implements Serializable{
	
	
	private List<PersonaBasicaDTO> listaPersonaBasica;
	private Long idAudiencia;
	private Long numeroAudiencia;
	private Date horaInicio;
	private Date horaFin;
	private String sedeAudiencia;
	
	
	
	/**
	 * @return the idAudiencia
	 */
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	/**
	 * @param idAudiencia the idAudiencia to set
	 */
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	/**
	 * @return the listaPersonaBasica
	 */
	public List<PersonaBasicaDTO> getListaPersonaBasica() {
		return listaPersonaBasica;
	}
	/**
	 * @param listaPersonaBasica the listaPersonaBasica to set
	 */
	public void setListaPersonaBasica(List<PersonaBasicaDTO> listaPersonaBasica) {
		this.listaPersonaBasica = listaPersonaBasica;
	}
	/**
	 * @return the numeroAudiencia
	 */
	public Long getNumeroAudiencia() {
		return numeroAudiencia;
	}
	/**
	 * @param numeroAudiencia the numeroAudiencia to set
	 */
	public void setNumeroAudiencia(Long numeroAudiencia) {
		this.numeroAudiencia = numeroAudiencia;
	}
	/**
	 * @return the horaInicio
	 */
	public Date getHoraInicio() {
		return horaInicio;
	}
	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	/**
	 * @return the horaFin
	 */
	public Date getHoraFin() {
		return horaFin;
	}
	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	/**
	 * @return the sedeAudiencia
	 */
	public String getSedeAudiencia() {
		return sedeAudiencia;
	}
	/**
	 * @param sedeAudiencia the sedeAudiencia to set
	 */
	public void setSedeAudiencia(String sedeAudiencia) {
		this.sedeAudiencia = sedeAudiencia;
	}
	
	
	

}
