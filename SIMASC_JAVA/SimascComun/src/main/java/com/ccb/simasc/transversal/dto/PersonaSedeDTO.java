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

import com.ccb.simasc.transversal.entidades.PersonaSede;
import com.ccb.simasc.transversal.entidades.PersonaSedePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaSedeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaSedeDTO extends IDTO<PersonaSede> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private PersonaSedePK personaSedePK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PersonaSedeDTO(){
		personaSedePK = new PersonaSedePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaSedePK getPersonaSedePK(){
		return this.personaSedePK;
	}
	
	public void setPersonaSedePK(PersonaSedePK personaSedePK){
		this.personaSedePK   = personaSedePK ;
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
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaSedePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaSedeDTO que se pasa
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
        final PersonaSedeDTO other = (PersonaSedeDTO) obj;
                
        if (!Objects.equals(this.personaSedePK, other.personaSedePK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public PersonaSedeDTO transformarSinDependencias(PersonaSede obj) {
		PersonaSedeDTO personaSedeDTO = new PersonaSedeDTO();
		
		personaSedeDTO.setPersonaSedePK(obj.getPersonaSedePK());
		personaSedeDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSedeDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSedeDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaSedeDTO;
	}

	@Override
	public PersonaSedeDTO transformarConDependencias(PersonaSede obj) {
		PersonaSedeDTO personaSedeDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaSedeDTO;
	}

	@Override
	public PersonaSede transformarEntidadSinDependencias(PersonaSede obj) {
		PersonaSede personaSede = new PersonaSede();
		
		personaSede.setPersonaSedePK(obj.getPersonaSedePK());
		
		personaSede.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSede.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSede.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaSede;
	}


	@Override
	public PersonaSede transformarEntidadConDependencias(PersonaSede obj) {
		PersonaSede personaSede = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaSede;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaSede> coleccion) {
		List<PersonaSedeDTO> personaSedeDTOList = new ArrayList<>();
		for(PersonaSede c : coleccion)
			personaSedeDTOList.add(transformarConDependencias(c));
		return personaSedeDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaSede> coleccion) {
		List<PersonaSedeDTO> personaSedeDTOList = new ArrayList<>();
		for(PersonaSede c : coleccion)
			personaSedeDTOList.add(transformarSinDependencias(c));
		return personaSedeDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaSede> coleccion) {
		List<PersonaSede> personaSedeList = new ArrayList<>();
		for(PersonaSede c : coleccion)
			personaSedeList.add(transformarEntidadConDependencias(c));
		return personaSedeList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaSede> coleccion) {
		List<PersonaSede> personaSedeList = new ArrayList<>();
		for(PersonaSede c : coleccion)
			personaSedeList.add(transformarEntidadSinDependencias(c));
		return personaSedeList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
