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

import com.ccb.simasc.transversal.entidades.Profesion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ProfesionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ProfesionDTO extends IDTO<Profesion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idProfesion;

	private String nombre;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ProfesionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdProfesion(){
		return this.idProfesion;
	}
	
	public void setIdProfesion(Long idProfesion){
		this.idProfesion = idProfesion;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
        
        hash = 37 * hash + Objects.hashCode(this.idProfesion);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ProfesionDTO que se pasa
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
        final ProfesionDTO other = (ProfesionDTO) obj;
                
        if (!Objects.equals(this.idProfesion, other.idProfesion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
	public ProfesionDTO transformarSinDependencias(Profesion obj) {
		ProfesionDTO profesionDTO = new ProfesionDTO();
		
		profesionDTO.setIdProfesion(obj.getIdProfesion());
		profesionDTO.setNombre(obj.getNombre());
		profesionDTO.setObservaciones(obj.getObservaciones());
		profesionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		profesionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		profesionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return profesionDTO;
	}

	@Override
	public ProfesionDTO transformarConDependencias(Profesion obj) {
		ProfesionDTO profesionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return profesionDTO;
	}

	@Override
	public Profesion transformarEntidadSinDependencias(Profesion obj) {
		Profesion profesion = new Profesion();
		
		profesion.setIdProfesion(obj.getIdProfesion());
		
		profesion.setNombre(obj.getNombre());
		profesion.setObservaciones(obj.getObservaciones());
		profesion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		profesion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		profesion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return profesion;
	}


	@Override
	public Profesion transformarEntidadConDependencias(Profesion obj) {
		Profesion profesion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return profesion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Profesion> coleccion) {
		List<ProfesionDTO> profesionDTOList = new ArrayList<>();
		for(Profesion c : coleccion)
			profesionDTOList.add(transformarConDependencias(c));
		return profesionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Profesion> coleccion) {
		List<ProfesionDTO> profesionDTOList = new ArrayList<>();
		for(Profesion c : coleccion)
			profesionDTOList.add(transformarSinDependencias(c));
		return profesionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Profesion> coleccion) {
		List<Profesion> profesionList = new ArrayList<>();
		for(Profesion c : coleccion)
			profesionList.add(transformarEntidadConDependencias(c));
		return profesionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Profesion> coleccion) {
		List<Profesion> profesionList = new ArrayList<>();
		for(Profesion c : coleccion)
			profesionList.add(transformarEntidadSinDependencias(c));
		return profesionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
