package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.PersonaEventoCcbPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaEventoCcbDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaEventoCcbDTO extends IDTO<PersonaEventoCcb> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private PersonaEventoCcbPK personaEventoCcbPK;

	private String estado;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PersonaEventoCcbDTO(){
		personaEventoCcbPK = new PersonaEventoCcbPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaEventoCcbPK getPersonaEventoCcbPK(){
		return this.personaEventoCcbPK;
	}
	
	public void setPersonaEventoCcbPK(PersonaEventoCcbPK personaEventoCcbPK){
		this.personaEventoCcbPK   = personaEventoCcbPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
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
        
        hash = 37 * hash + Objects.hashCode(this.personaEventoCcbPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaEventoCcbDTO que se pasa
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
        final PersonaEventoCcbDTO other = (PersonaEventoCcbDTO) obj;
                
        if (!Objects.equals(this.personaEventoCcbPK, other.personaEventoCcbPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
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
	public PersonaEventoCcbDTO transformarSinDependencias(PersonaEventoCcb obj) {
		PersonaEventoCcbDTO personaEventoCcbDTO = new PersonaEventoCcbDTO();
		
		personaEventoCcbDTO.setPersonaEventoCcbPK(obj.getPersonaEventoCcbPK());
		personaEventoCcbDTO.setEstado(obj.getEstado());
		personaEventoCcbDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaEventoCcbDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaEventoCcbDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaEventoCcbDTO;
	}

	@Override
	public PersonaEventoCcbDTO transformarConDependencias(PersonaEventoCcb obj) {
		PersonaEventoCcbDTO personaEventoCcbDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaEventoCcbDTO;
	}

	@Override
	public PersonaEventoCcb transformarEntidadSinDependencias(PersonaEventoCcb obj) {
		PersonaEventoCcb personaEventoCcb = new PersonaEventoCcb();
		
		personaEventoCcb.setPersonaEventoCcbPK(obj.getPersonaEventoCcbPK());
		
		personaEventoCcb.setEstado(obj.getEstado());
		personaEventoCcb.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaEventoCcb.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaEventoCcb.setEstadoRegistro(obj.getEstadoRegistro());
		
		return personaEventoCcb;
	}


	@Override
	public PersonaEventoCcb transformarEntidadConDependencias(PersonaEventoCcb obj) {
		PersonaEventoCcb personaEventoCcb = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaEventoCcb;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaEventoCcb> coleccion) {
		List<PersonaEventoCcbDTO> personaEventoCcbDTOList = new ArrayList<>();
		for(PersonaEventoCcb c : coleccion)
			personaEventoCcbDTOList.add(transformarConDependencias(c));
		return personaEventoCcbDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaEventoCcb> coleccion) {
		List<PersonaEventoCcbDTO> personaEventoCcbDTOList = new ArrayList<>();
		for(PersonaEventoCcb c : coleccion)
			personaEventoCcbDTOList.add(transformarSinDependencias(c));
		return personaEventoCcbDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaEventoCcb> coleccion) {
		List<PersonaEventoCcb> personaEventoCcbList = new ArrayList<>();
		for(PersonaEventoCcb c : coleccion)
			personaEventoCcbList.add(transformarEntidadConDependencias(c));
		return personaEventoCcbList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaEventoCcb> coleccion) {
		List<PersonaEventoCcb> personaEventoCcbList = new ArrayList<>();
		for(PersonaEventoCcb c : coleccion)
			personaEventoCcbList.add(transformarEntidadSinDependencias(c));
		return personaEventoCcbList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
