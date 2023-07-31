package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.PersonaRolAlerta;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaRolAlertaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaRolAlertaDTO extends IDTO<PersonaRolAlerta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idPersonaRolAlerta;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idAlerta;		
	private Long idPersona;		
	private Long idRol;		
	
    public PersonaRolAlertaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPersonaRolAlerta(){
		return this.idPersonaRolAlerta;
	}
	
	public void setIdPersonaRolAlerta(Long idPersonaRolAlerta){
		this.idPersonaRolAlerta = idPersonaRolAlerta;
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
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPersonaRolAlerta);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idRol);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaRolAlertaDTO que se pasa
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
        final PersonaRolAlertaDTO other = (PersonaRolAlertaDTO) obj;
                
        if (!Objects.equals(this.idPersonaRolAlerta, other.idPersonaRolAlerta)) {
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
        
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
    
    @Override
	public PersonaRolAlertaDTO transformarSinDependencias(PersonaRolAlerta obj) {
		PersonaRolAlertaDTO personaRolAlertaDTO = new PersonaRolAlertaDTO();
		
		personaRolAlertaDTO.setIdPersonaRolAlerta(obj.getIdPersonaRolAlerta());
		personaRolAlertaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaRolAlertaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaRolAlertaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		personaRolAlertaDTO.setIdAlerta(obj.getIdAlerta());
		personaRolAlertaDTO.setIdPersona(obj.getIdPersona());
		personaRolAlertaDTO.setIdRol(obj.getIdRol());
		
		return personaRolAlertaDTO;
	}

	@Override
	public PersonaRolAlertaDTO transformarConDependencias(PersonaRolAlerta obj) {
		PersonaRolAlertaDTO personaRolAlertaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaRolAlertaDTO;
	}

	@Override
	public PersonaRolAlerta transformarEntidadSinDependencias(PersonaRolAlerta obj) {
		PersonaRolAlerta personaRolAlerta = new PersonaRolAlerta();
		
		personaRolAlerta.setIdPersonaRolAlerta(obj.getIdPersonaRolAlerta());
		
		personaRolAlerta.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaRolAlerta.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaRolAlerta.setEstadoRegistro(obj.getEstadoRegistro());
		personaRolAlerta.setIdAlerta(obj.getIdAlerta());
		personaRolAlerta.setIdPersona(obj.getIdPersona());
		personaRolAlerta.setIdRol(obj.getIdRol());
		
		return personaRolAlerta;
	}


	@Override
	public PersonaRolAlerta transformarEntidadConDependencias(PersonaRolAlerta obj) {
		PersonaRolAlerta personaRolAlerta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaRolAlerta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaRolAlerta> coleccion) {
		List<PersonaRolAlertaDTO> personaRolAlertaDTOList = new ArrayList<>();
		for(PersonaRolAlerta c : coleccion)
			personaRolAlertaDTOList.add(transformarConDependencias(c));
		return personaRolAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaRolAlerta> coleccion) {
		List<PersonaRolAlertaDTO> personaRolAlertaDTOList = new ArrayList<>();
		for(PersonaRolAlerta c : coleccion)
			personaRolAlertaDTOList.add(transformarSinDependencias(c));
		return personaRolAlertaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaRolAlerta> coleccion) {
		List<PersonaRolAlerta> personaRolAlertaList = new ArrayList<>();
		for(PersonaRolAlerta c : coleccion)
			personaRolAlertaList.add(transformarEntidadConDependencias(c));
		return personaRolAlertaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaRolAlerta> coleccion) {
		List<PersonaRolAlerta> personaRolAlertaList = new ArrayList<>();
		for(PersonaRolAlerta c : coleccion)
			personaRolAlertaList.add(transformarEntidadSinDependencias(c));
		return personaRolAlertaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
