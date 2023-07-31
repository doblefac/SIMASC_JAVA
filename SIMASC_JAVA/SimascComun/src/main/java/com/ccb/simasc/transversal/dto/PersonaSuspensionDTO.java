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

import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.PersonaSuspensionPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaSuspensionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaSuspensionDTO extends IDTO<PersonaSuspension> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private PersonaSuspensionPK personaSuspensionPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PersonaSuspensionDTO(){
		personaSuspensionPK = new PersonaSuspensionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaSuspensionPK getPersonaSuspensionPK(){
		return this.personaSuspensionPK;
	}
	
	public void setPersonaSuspensionPK(PersonaSuspensionPK personaSuspensionPK){
		this.personaSuspensionPK   = personaSuspensionPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.personaSuspensionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaSuspensionDTO que se pasa
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
        final PersonaSuspensionDTO other = (PersonaSuspensionDTO) obj;
                
        if (!Objects.equals(this.personaSuspensionPK, other.personaSuspensionPK)) {
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
	public PersonaSuspensionDTO transformarSinDependencias(PersonaSuspension obj) {
		PersonaSuspensionDTO personaSuspensionDTO = new PersonaSuspensionDTO();
		
		personaSuspensionDTO.setPersonaSuspensionPK(obj.getPersonaSuspensionPK());
		personaSuspensionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSuspensionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSuspensionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaSuspensionDTO;
	}

	@Override
	public PersonaSuspensionDTO transformarConDependencias(PersonaSuspension obj) {
		PersonaSuspensionDTO personaSuspensionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaSuspensionDTO;
	}

	@Override
	public PersonaSuspension transformarEntidadSinDependencias(PersonaSuspension obj) {
		PersonaSuspension personaSuspension = new PersonaSuspension();
		
		personaSuspension.setPersonaSuspensionPK(obj.getPersonaSuspensionPK());
		
		personaSuspension.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaSuspension.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaSuspension.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaSuspension;
	}


	@Override
	public PersonaSuspension transformarEntidadConDependencias(PersonaSuspension obj) {
		PersonaSuspension personaSuspension = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaSuspension;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaSuspension> coleccion) {
		List<PersonaSuspensionDTO> personaSuspensionDTOList = new ArrayList<>();
		for(PersonaSuspension c : coleccion)
			personaSuspensionDTOList.add(transformarConDependencias(c));
		return personaSuspensionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaSuspension> coleccion) {
		List<PersonaSuspensionDTO> personaSuspensionDTOList = new ArrayList<>();
		for(PersonaSuspension c : coleccion)
			personaSuspensionDTOList.add(transformarSinDependencias(c));
		return personaSuspensionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaSuspension> coleccion) {
		List<PersonaSuspension> personaSuspensionList = new ArrayList<>();
		for(PersonaSuspension c : coleccion)
			personaSuspensionList.add(transformarEntidadConDependencias(c));
		return personaSuspensionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaSuspension> coleccion) {
		List<PersonaSuspension> personaSuspensionList = new ArrayList<>();
		for(PersonaSuspension c : coleccion)
			personaSuspensionList.add(transformarEntidadSinDependencias(c));
		return personaSuspensionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
