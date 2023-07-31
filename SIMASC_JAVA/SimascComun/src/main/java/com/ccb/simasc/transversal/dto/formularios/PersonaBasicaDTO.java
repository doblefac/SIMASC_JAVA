package com.ccb.simasc.transversal.dto.formularios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class PersonaBasicaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idPersona;
	private String nombreCompleto;
	private String tipoDocumento;
	private String numeroDocumento;
	private String primerNombreORazonSocial;		
	private String segundoNombre;		
	private String primerApellido;		
	private String segundoApellido;
	private String correoElectronico;
	private String lista;
	private Date fechaEvaluacion;
	private String telefono;
	private String direccion;
	
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the primerNombreORazonSocial
	 */
	public String getPrimerNombreORazonSocial() {
		return primerNombreORazonSocial;
	}
	/**
	 * @param primerNombreORazonSocial the primerNombreORazonSocial to set
	 */
	public void setPrimerNombreORazonSocial(String primerNombreORazonSocial) {
		this.primerNombreORazonSocial = primerNombreORazonSocial;
	}
	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}
	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	/**
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	/**
	 * @return the lista
	 */
	public String getLista() {
		return lista;
	}
	/**
	 * @param lista the lista to set
	 */
	public void setLista(String lista) {
		this.lista = lista;
	}
	
	
	/**
	 * @return the fechaEvaluacion
	 */
	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}
	/**
	 * @param fechaEvaluacion the fechaEvaluacion to set
	 */
	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	/**
	 * Genera el DTO a partir de la entidad de persona
	 * @param persona
	 * @return
	 */
	public static PersonaBasicaDTO newPersonaBasicaDTO(Persona persona){
		PersonaBasicaDTO dto = new PersonaBasicaDTO();
		dto.setIdPersona(persona.getIdPersona());
		dto.setNombreCompleto(persona.getNombreCompleto());
		if(persona.getNumeroDocumento() != null){
			dto.setNumeroDocumento(persona.getNumeroDocumento());	
		}
		if(persona.getTipoDocumento()!= null){
			dto.setTipoDocumento(persona.getTipoDocumento());	
		}
		
		return dto;
	}
	
	/**
	 * Convierte una lista de personas (Entidad) en una lista de PersonaBasicaDTO
	 * @param persona
	 * @return
	 */
	public static Collection<PersonaBasicaDTO> newListaPersonaBasicaDTO(Collection<Persona> personas){
		List<PersonaBasicaDTO> dtos = new ArrayList<>();
		for(Persona persona : personas){
			dtos.add(PersonaBasicaDTO.newPersonaBasicaDTO(persona));
		}
		
		return dtos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correoElectronico == null) ? 0 : correoElectronico.hashCode());
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
		result = prime * result + ((primerApellido == null) ? 0 : primerApellido.hashCode());
		result = prime * result + ((primerNombreORazonSocial == null) ? 0 : primerNombreORazonSocial.hashCode());
		result = prime * result + ((segundoApellido == null) ? 0 : segundoApellido.hashCode());
		result = prime * result + ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaBasicaDTO other = (PersonaBasicaDTO) obj;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (numeroDocumento == null) {
			if (other.numeroDocumento != null)
				return false;
		} else if (!numeroDocumento.equals(other.numeroDocumento))
			return false;
		if (primerApellido == null) {
			if (other.primerApellido != null)
				return false;
		} else if (!primerApellido.equals(other.primerApellido))
			return false;
		if (primerNombreORazonSocial == null) {
			if (other.primerNombreORazonSocial != null)
				return false;
		} else if (!primerNombreORazonSocial.equals(other.primerNombreORazonSocial))
			return false;
		if (segundoApellido == null) {
			if (other.segundoApellido != null)
				return false;
		} else if (!segundoApellido.equals(other.segundoApellido))
			return false;
		if (segundoNombre == null) {
			if (other.segundoNombre != null)
				return false;
		} else if (!segundoNombre.equals(other.segundoNombre))
			return false;
		if (tipoDocumento == null) {
			if (other.tipoDocumento != null)
				return false;
		} else if (!tipoDocumento.equals(other.tipoDocumento))
			return false;
		return true;
	}
	
	
	
}
