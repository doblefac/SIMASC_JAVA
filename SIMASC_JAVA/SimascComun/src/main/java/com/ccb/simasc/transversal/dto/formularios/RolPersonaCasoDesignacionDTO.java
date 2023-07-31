package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Persona;

/**
 * DAO que contiene la información del formulario para la creacion de parte representante sea
 * abogado demandante o abogado demandado.
 * Este formulario esta construido para los servicios REST
 * 
 * @author dpachon
 */
@XmlRootElement
public class RolPersonaCasoDesignacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idPersona;
	private String nombreCompleto;
	private String metodoNombramiento;
	private String tipoNombramiento;
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Genera el DTO a partir de la entidad de persona
	 * @param persona
	 * @return
	 */
	public static RolPersonaCasoDesignacionDTO newPersonaBasicaDTO(Persona persona){
		RolPersonaCasoDesignacionDTO dto = new RolPersonaCasoDesignacionDTO();
		dto.setIdPersona(persona.getIdPersona());
		dto.setNombreCompleto(persona.getNombreCompleto());
		
		return dto;
	}
	
	/**
	 * Convierte una lista de personas (Entidad) en una lista de PersonaBasicaDTO
	 * @param persona
	 * @return
	 */
	public static Collection<RolPersonaCasoDesignacionDTO> newListaPersonaBasicaDTO(Collection<Persona> personas){
		List<RolPersonaCasoDesignacionDTO> dtos = new ArrayList<>();
		for(Persona persona : personas){
			dtos.add(RolPersonaCasoDesignacionDTO.newPersonaBasicaDTO(persona));
		}
		
		return dtos;
	}
	public String getMetodoNombramiento() {
		return metodoNombramiento;
	}
	public void setMetodoNombramiento(String metodoNombramiento) {
		this.metodoNombramiento = metodoNombramiento;
	}
	public String getTipoNombramiento() {
		return tipoNombramiento;
	}
	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}
}
