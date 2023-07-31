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

import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.ServicioMateria;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad MateriaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class MateriaDTO extends IDTO<Materia> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idMateria;

	private String nombre;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public MateriaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
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
        
        hash = 37 * hash + Objects.hashCode(this.idMateria);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MateriaDTO que se pasa
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
        final MateriaDTO other = (MateriaDTO) obj;
                
        if (!Objects.equals(this.idMateria, other.idMateria)) {
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
	public MateriaDTO transformarSinDependencias(Materia obj) {
		MateriaDTO materiaDTO = new MateriaDTO();
		
		materiaDTO.setIdMateria(obj.getIdMateria());
		materiaDTO.setNombre(obj.getNombre());
		materiaDTO.setObservaciones(obj.getObservaciones());
		materiaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		materiaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		materiaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return materiaDTO;
	}

	@Override
	public MateriaDTO transformarConDependencias(Materia obj) {
		MateriaDTO materiaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return materiaDTO;
	}

	@Override
	public Materia transformarEntidadSinDependencias(Materia obj) {
		Materia materia = new Materia();
		
		materia.setIdMateria(obj.getIdMateria());
		
		materia.setNombre(obj.getNombre());
		materia.setObservaciones(obj.getObservaciones());
		materia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		materia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		materia.setEstadoRegistro(obj.getEstadoRegistro());
		
		return materia;
	}


	@Override
	public Materia transformarEntidadConDependencias(Materia obj) {
		Materia materia = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		ServicioMateriaDTO dto = new ServicioMateriaDTO();
		List<ServicioMateria> servicioMateria = (List<ServicioMateria>)dto.transformarColeccionEntidadesSinDependencias(obj.getServicioMateriaList());
		materia.setServicioMateriaList(servicioMateria);
		// protected region modificaciones transformarEntidadConDependencias end
		
		return materia;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Materia> coleccion) {
		List<MateriaDTO> materiaDTOList = new ArrayList<>();
		for(Materia c : coleccion)
			materiaDTOList.add(transformarConDependencias(c));
		return materiaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Materia> coleccion) {
		List<MateriaDTO> materiaDTOList = new ArrayList<>();
		for(Materia c : coleccion)
			materiaDTOList.add(transformarSinDependencias(c));
		return materiaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Materia> coleccion) {
		List<Materia> materiaList = new ArrayList<>();
		for(Materia c : coleccion)
			materiaList.add(transformarEntidadConDependencias(c));
		return materiaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Materia> coleccion) {
		List<Materia> materiaList = new ArrayList<>();
		for(Materia c : coleccion)
			materiaList.add(transformarEntidadSinDependencias(c));
		return materiaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
