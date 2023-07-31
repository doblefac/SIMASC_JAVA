package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaSolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaSolicitudDTO extends IDTO<PersonaSolicitud> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private PersonaSolicitudPK personaSolicitudPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersonaApoderado;		
	private Long idSolicitudServicioApoderado;		
	private Long idRolApoderado;		
	
    public PersonaSolicitudDTO(){
		personaSolicitudPK = new PersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaSolicitudPK getPersonaSolicitudPK(){
		return this.personaSolicitudPK;
	}
	
	public void setPersonaSolicitudPK(PersonaSolicitudPK personaSolicitudPK){
		this.personaSolicitudPK   = personaSolicitudPK ;
	}  
	
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		
	public Long getIdPersonaApoderado(){
		return this.idPersonaApoderado;
	}
	
	public void setIdPersonaApoderado(Long idPersonaApoderado){
		this.idPersonaApoderado = idPersonaApoderado;
	}
		
	public Long getIdSolicitudServicioApoderado(){
		return this.idSolicitudServicioApoderado;
	}
	
	public void setIdSolicitudServicioApoderado(Long idSolicitudServicioApoderado){
		this.idSolicitudServicioApoderado = idSolicitudServicioApoderado;
	}
		
	public Long getIdRolApoderado(){
		return this.idRolApoderado;
	}
	
	public void setIdRolApoderado(Long idRolApoderado){
		this.idRolApoderado = idRolApoderado;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicioApoderado);
        hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaSolicitudDTO que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonaSolicitudDTO other = (PersonaSolicitudDTO) obj;
                
        if (!Objects.equals(this.personaSolicitudPK, other.personaSolicitudPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicioApoderado, other.idSolicitudServicioApoderado)) {
            return false;
        }
        
        return Objects.equals(this.idRolApoderado, other.idRolApoderado);
                
    }
    
    @Override
	public PersonaSolicitudDTO transformarSinDependencias(PersonaSolicitud obj) {
		PersonaSolicitudDTO personaSolicitudDTO = new PersonaSolicitudDTO();
		
		personaSolicitudDTO.setPersonaSolicitudPK(obj.getPersonaSolicitudPK());
		personaSolicitudDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSolicitudDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSolicitudDTO.setEstadoRegistro(obj.getEstadoRegistro());
		personaSolicitudDTO.setIdPersonaApoderado(obj.getIdPersonaApoderado());
		personaSolicitudDTO.setIdSolicitudServicioApoderado(obj.getIdSolicitudServicioApoderado());
		personaSolicitudDTO.setIdRolApoderado(obj.getIdRolApoderado());
		
		return personaSolicitudDTO;
	}

	@Override
	public PersonaSolicitudDTO transformarConDependencias(PersonaSolicitud obj) {
		PersonaSolicitudDTO personaSolicitudDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaSolicitudDTO;
	}

	@Override
	public PersonaSolicitud transformarEntidadSinDependencias(PersonaSolicitud obj) {
		PersonaSolicitud personaSolicitud = new PersonaSolicitud();
		
		personaSolicitud.setPersonaSolicitudPK(obj.getPersonaSolicitudPK());
		
		personaSolicitud.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSolicitud.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSolicitud.setEstadoRegistro(obj.getEstadoRegistro());
		personaSolicitud.setIdPersonaApoderado(obj.getIdPersonaApoderado());
		personaSolicitud.setIdSolicitudServicioApoderado(obj.getIdSolicitudServicioApoderado());
		personaSolicitud.setIdRolApoderado(obj.getIdRolApoderado());
		
		return personaSolicitud;
	}


	@Override
	public PersonaSolicitud transformarEntidadConDependencias(PersonaSolicitud obj) {
		PersonaSolicitud personaSolicitud = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		personaSolicitud.setCorreoElectronicoPersonaSolicitudList(obj.getCorreoElectronicoPersonaSolicitudList());
		personaSolicitud.setUbicacionPersonaSolicitudList(obj.getUbicacionPersonaSolicitudList());
		personaSolicitud.setApoderadosSolicitudList(obj.getApoderadosSolicitudList());
		personaSolicitud.setRepresentadosSolicitudList(obj.getRepresentadosSolicitudList());
		personaSolicitud.setPersonaSolicitudList(obj.getPersonaSolicitudList());
		personaSolicitud.setRol(obj.getRol());
		personaSolicitud.setSolicitudServicio(obj.getSolicitudServicio());
		personaSolicitud.setPersona(obj.getPersona());
		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaSolicitud;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaSolicitud> coleccion) {
		List<PersonaSolicitudDTO> personaSolicitudDTOList = new ArrayList<>();
		for(PersonaSolicitud c : coleccion)
			personaSolicitudDTOList.add(transformarConDependencias(c));
		return personaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaSolicitud> coleccion) {
		List<PersonaSolicitudDTO> personaSolicitudDTOList = new ArrayList<>();
		for(PersonaSolicitud c : coleccion)
			personaSolicitudDTOList.add(transformarSinDependencias(c));
		return personaSolicitudDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaSolicitud> coleccion) {
		List<PersonaSolicitud> personaSolicitudList = new ArrayList<>();
		for(PersonaSolicitud c : coleccion)
			personaSolicitudList.add(transformarEntidadConDependencias(c));
		return personaSolicitudList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaSolicitud> coleccion) {
		List<PersonaSolicitud> personaSolicitudList = new ArrayList<>();
		for(PersonaSolicitud c : coleccion)
			personaSolicitudList.add(transformarEntidadSinDependencias(c));
		return personaSolicitudList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
