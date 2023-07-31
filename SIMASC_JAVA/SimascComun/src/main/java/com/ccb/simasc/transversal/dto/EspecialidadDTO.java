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

import com.ccb.simasc.transversal.entidades.Especialidad;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EspecialidadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EspecialidadDTO extends IDTO<Especialidad> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idEspecialidad;

	private Long idMateria;		
	private String nombre;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public EspecialidadDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdEspecialidad(){
		return this.idEspecialidad;
	}
	
	public void setIdEspecialidad(Long idEspecialidad){
		this.idEspecialidad = idEspecialidad;
	}
	
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
        
        hash = 37 * hash + Objects.hashCode(this.idEspecialidad);        
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EspecialidadDTO que se pasa
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
        final EspecialidadDTO other = (EspecialidadDTO) obj;
                
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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
	public EspecialidadDTO transformarSinDependencias(Especialidad obj) {
		EspecialidadDTO especialidadDTO = new EspecialidadDTO();
		
		especialidadDTO.setIdEspecialidad(obj.getIdEspecialidad());
		especialidadDTO.setIdMateria(obj.getIdMateria());
		especialidadDTO.setNombre(obj.getNombre());
		especialidadDTO.setDescripcion(obj.getDescripcion());
		especialidadDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		especialidadDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		especialidadDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return especialidadDTO;
	}

	@Override
	public EspecialidadDTO transformarConDependencias(Especialidad obj) {
		EspecialidadDTO especialidadDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return especialidadDTO;
	}

	@Override
	public Especialidad transformarEntidadSinDependencias(Especialidad obj) {
		Especialidad especialidad = new Especialidad();
		
		especialidad.setIdEspecialidad(obj.getIdEspecialidad());
		
		especialidad.setIdMateria(obj.getIdMateria());
		especialidad.setNombre(obj.getNombre());
		especialidad.setDescripcion(obj.getDescripcion());
		especialidad.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		especialidad.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		especialidad.setEstadoRegistro(obj.getEstadoRegistro());
		
		return especialidad;
	}


	@Override
	public Especialidad transformarEntidadConDependencias(Especialidad obj) {
		Especialidad especialidad = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return especialidad;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Especialidad> coleccion) {
		List<EspecialidadDTO> especialidadDTOList = new ArrayList<>();
		for(Especialidad c : coleccion)
			especialidadDTOList.add(transformarConDependencias(c));
		return especialidadDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Especialidad> coleccion) {
		List<EspecialidadDTO> especialidadDTOList = new ArrayList<>();
		for(Especialidad c : coleccion)
			especialidadDTOList.add(transformarSinDependencias(c));
		return especialidadDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Especialidad> coleccion) {
		List<Especialidad> especialidadList = new ArrayList<>();
		for(Especialidad c : coleccion)
			especialidadList.add(transformarEntidadConDependencias(c));
		return especialidadList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Especialidad> coleccion) {
		List<Especialidad> especialidadList = new ArrayList<>();
		for(Especialidad c : coleccion)
			especialidadList.add(transformarEntidadSinDependencias(c));
		return especialidadList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
