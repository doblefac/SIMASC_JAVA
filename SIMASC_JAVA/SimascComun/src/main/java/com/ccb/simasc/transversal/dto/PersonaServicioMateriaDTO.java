package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PersonaServicioMateriaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PersonaServicioMateriaDTO extends IDTO<PersonaServicioMateria> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idPersonaServicioMateria;

	private String estadoParaSorteo;		
	private Date fechaInicioVigencia;		
	private Date fechaFinDeVigencia;		
	private Long cantidadCasosAsignados;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	private Long idEspecialidad;		
	private Long idServicio;		
	private Long idMateria;		
	// RQ17 06/02/2019
	private String observacion; 
	
    public PersonaServicioMateriaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPersonaServicioMateria(){
		return this.idPersonaServicioMateria;
	}
	
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria){
		this.idPersonaServicioMateria = idPersonaServicioMateria;
	}
	
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
		
	public Date getFechaInicioVigencia(){
		return this.fechaInicioVigencia;
	}
	
	public void setFechaInicioVigencia(Date fechaInicioVigencia){
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
		
	public Date getFechaFinDeVigencia(){
		return this.fechaFinDeVigencia;
	}
	
	public void setFechaFinDeVigencia(Date fechaFinDeVigencia){
		this.fechaFinDeVigencia = fechaFinDeVigencia;
	}
		
	public Long getCantidadCasosAsignados(){
		return this.cantidadCasosAsignados;
	}
	
	public void setCantidadCasosAsignados(Long cantidadCasosAsignados){
		this.cantidadCasosAsignados = cantidadCasosAsignados;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdEspecialidad(){
		return this.idEspecialidad;
	}
	
	public void setIdEspecialidad(Long idEspecialidad){
		this.idEspecialidad = idEspecialidad;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPersonaServicioMateria);        
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinDeVigencia);
        hash = 37 * hash + Objects.hashCode(this.cantidadCasosAsignados);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idEspecialidad);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaServicioMateriaDTO que se pasa
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
        final PersonaServicioMateriaDTO other = (PersonaServicioMateriaDTO) obj;
                
        if (!Objects.equals(this.idPersonaServicioMateria, other.idPersonaServicioMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinDeVigencia, other.fechaFinDeVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadCasosAsignados, other.cantidadCasosAsignados)) {
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
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.idMateria, other.idMateria);
                
    }
    
    @Override
	public PersonaServicioMateriaDTO transformarSinDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateriaDTO personaServicioMateriaDTO = new PersonaServicioMateriaDTO();
		
		personaServicioMateriaDTO.setIdPersonaServicioMateria(obj.getIdPersonaServicioMateria());
		personaServicioMateriaDTO.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		personaServicioMateriaDTO.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		personaServicioMateriaDTO.setFechaFinDeVigencia(obj.getFechaFinDeVigencia());
		personaServicioMateriaDTO.setCantidadCasosAsignados(obj.getCantidadCasosAsignados());
		personaServicioMateriaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaServicioMateriaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaServicioMateriaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		personaServicioMateriaDTO.setIdPersona(obj.getIdPersona());
		personaServicioMateriaDTO.setIdEspecialidad(obj.getIdEspecialidad());
		personaServicioMateriaDTO.setIdServicio(obj.getIdServicio());
		personaServicioMateriaDTO.setIdMateria(obj.getIdMateria());
		
		return personaServicioMateriaDTO;
	}

	@Override
	public PersonaServicioMateriaDTO transformarConDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateriaDTO personaServicioMateriaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return personaServicioMateriaDTO;
	}

	@Override
	public PersonaServicioMateria transformarEntidadSinDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateria personaServicioMateria = new PersonaServicioMateria();
		
		personaServicioMateria.setIdPersonaServicioMateria(obj.getIdPersonaServicioMateria());
		
		personaServicioMateria.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		personaServicioMateria.setFechaInicioVigencia(obj.getFechaInicioVigencia());
		personaServicioMateria.setFechaFinDeVigencia(obj.getFechaFinDeVigencia());
		personaServicioMateria.setCantidadCasosAsignados(obj.getCantidadCasosAsignados());
		personaServicioMateria.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		personaServicioMateria.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		personaServicioMateria.setEstadoRegistro(obj.getEstadoRegistro());
		personaServicioMateria.setIdPersona(obj.getIdPersona());
		personaServicioMateria.setIdEspecialidad(obj.getIdEspecialidad());
		personaServicioMateria.setIdServicio(obj.getIdServicio());
		personaServicioMateria.setIdMateria(obj.getIdMateria());
		
		return personaServicioMateria;
	}


	@Override
	public PersonaServicioMateria transformarEntidadConDependencias(PersonaServicioMateria obj) {
		PersonaServicioMateria personaServicioMateria = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return personaServicioMateria;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PersonaServicioMateria> coleccion) {
		List<PersonaServicioMateriaDTO> personaServicioMateriaDTOList = new ArrayList<>();
		for(PersonaServicioMateria c : coleccion)
			personaServicioMateriaDTOList.add(transformarConDependencias(c));
		return personaServicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PersonaServicioMateria> coleccion) {
		List<PersonaServicioMateriaDTO> personaServicioMateriaDTOList = new ArrayList<>();
		for(PersonaServicioMateria c : coleccion)
			personaServicioMateriaDTOList.add(transformarSinDependencias(c));
		return personaServicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PersonaServicioMateria> coleccion) {
		List<PersonaServicioMateria> personaServicioMateriaList = new ArrayList<>();
		for(PersonaServicioMateria c : coleccion)
			personaServicioMateriaList.add(transformarEntidadConDependencias(c));
		return personaServicioMateriaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PersonaServicioMateria> coleccion) {
		List<PersonaServicioMateria> personaServicioMateriaList = new ArrayList<>();
		for(PersonaServicioMateria c : coleccion)
			personaServicioMateriaList.add(transformarEntidadSinDependencias(c));
		return personaServicioMateriaList;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
