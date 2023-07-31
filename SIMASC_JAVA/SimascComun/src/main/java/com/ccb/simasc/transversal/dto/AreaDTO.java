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

import com.ccb.simasc.transversal.entidades.Area;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AreaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AreaDTO extends IDTO<Area> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idArea;

	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String descripcion;		
	
    public AreaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdArea(){
		return this.idArea;
	}
	
	public void setIdArea(Long idArea){
		this.idArea = idArea;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idArea);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AreaDTO que se pasa
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
        final AreaDTO other = (AreaDTO) obj;
                
        if (!Objects.equals(this.idArea, other.idArea)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        
        return Objects.equals(this.descripcion, other.descripcion);
                
    }
    
    @Override
	public AreaDTO transformarSinDependencias(Area obj) {
		AreaDTO areaDTO = new AreaDTO();
		
		areaDTO.setIdArea(obj.getIdArea());
		areaDTO.setNombre(obj.getNombre());
		areaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		areaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		areaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		areaDTO.setDescripcion(obj.getDescripcion());
		
		return areaDTO;
	}

	@Override
	public AreaDTO transformarConDependencias(Area obj) {
		AreaDTO areaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return areaDTO;
	}

	@Override
	public Area transformarEntidadSinDependencias(Area obj) {
		Area area = new Area();
		
		area.setIdArea(obj.getIdArea());
		
		area.setNombre(obj.getNombre());
		area.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		area.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		area.setEstadoRegistro(obj.getEstadoRegistro());
		area.setDescripcion(obj.getDescripcion());
		
		return area;
	}


	@Override
	public Area transformarEntidadConDependencias(Area obj) {
		Area area = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return area;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Area> coleccion) {
		List<AreaDTO> areaDTOList = new ArrayList<>();
		for(Area c : coleccion)
			areaDTOList.add(transformarConDependencias(c));
		return areaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Area> coleccion) {
		List<AreaDTO> areaDTOList = new ArrayList<>();
		for(Area c : coleccion)
			areaDTOList.add(transformarSinDependencias(c));
		return areaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Area> coleccion) {
		List<Area> areaList = new ArrayList<>();
		for(Area c : coleccion)
			areaList.add(transformarEntidadConDependencias(c));
		return areaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Area> coleccion) {
		List<Area> areaList = new ArrayList<>();
		for(Area c : coleccion)
			areaList.add(transformarEntidadSinDependencias(c));
		return areaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
