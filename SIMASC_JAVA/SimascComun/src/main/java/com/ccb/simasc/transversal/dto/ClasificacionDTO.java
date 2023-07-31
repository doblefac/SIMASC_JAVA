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

import com.ccb.simasc.transversal.entidades.Clasificacion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ClasificacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ClasificacionDTO extends IDTO<Clasificacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idClasificacion;

	private String nombre;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idAsunto;		
	
    public ClasificacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdClasificacion(){
		return this.idClasificacion;
	}
	
	public void setIdClasificacion(Long idClasificacion){
		this.idClasificacion = idClasificacion;
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
		
	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idClasificacion);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAsunto);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClasificacionDTO que se pasa
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
        final ClasificacionDTO other = (ClasificacionDTO) obj;
                
        if (!Objects.equals(this.idClasificacion, other.idClasificacion)) {
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
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idAsunto, other.idAsunto);
                
    }
    
    @Override
	public ClasificacionDTO transformarSinDependencias(Clasificacion obj) {
		ClasificacionDTO clasificacionDTO = new ClasificacionDTO();
		
		clasificacionDTO.setIdClasificacion(obj.getIdClasificacion());
		clasificacionDTO.setNombre(obj.getNombre());
		clasificacionDTO.setDescripcion(obj.getDescripcion());
		clasificacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		clasificacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		clasificacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		clasificacionDTO.setIdAsunto(obj.getIdAsunto());
		
		return clasificacionDTO;
	}

	@Override
	public ClasificacionDTO transformarConDependencias(Clasificacion obj) {
		ClasificacionDTO clasificacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return clasificacionDTO;
	}

	@Override
	public Clasificacion transformarEntidadSinDependencias(Clasificacion obj) {
		Clasificacion clasificacion = new Clasificacion();
		
		clasificacion.setIdClasificacion(obj.getIdClasificacion());
		
		clasificacion.setNombre(obj.getNombre());
		clasificacion.setDescripcion(obj.getDescripcion());
		clasificacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		clasificacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		clasificacion.setEstadoRegistro(obj.getEstadoRegistro());
		clasificacion.setIdAsunto(obj.getIdAsunto());
		
		return clasificacion;
	}


	@Override
	public Clasificacion transformarEntidadConDependencias(Clasificacion obj) {
		Clasificacion clasificacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return clasificacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Clasificacion> coleccion) {
		List<ClasificacionDTO> clasificacionDTOList = new ArrayList<>();
		for(Clasificacion c : coleccion)
			clasificacionDTOList.add(transformarConDependencias(c));
		return clasificacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Clasificacion> coleccion) {
		List<ClasificacionDTO> clasificacionDTOList = new ArrayList<>();
		for(Clasificacion c : coleccion)
			clasificacionDTOList.add(transformarSinDependencias(c));
		return clasificacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Clasificacion> coleccion) {
		List<Clasificacion> clasificacionList = new ArrayList<>();
		for(Clasificacion c : coleccion)
			clasificacionList.add(transformarEntidadConDependencias(c));
		return clasificacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Clasificacion> coleccion) {
		List<Clasificacion> clasificacionList = new ArrayList<>();
		for(Clasificacion c : coleccion)
			clasificacionList.add(transformarEntidadSinDependencias(c));
		return clasificacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
