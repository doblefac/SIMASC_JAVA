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

import com.ccb.simasc.transversal.entidades.Asunto;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AsuntoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AsuntoDTO extends IDTO<Asunto> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idAsunto;

	private String nombre;		
	private Long idArea;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String descripcion;		
	
    public AsuntoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Long getIdArea(){
		return this.idArea;
	}
	
	public void setIdArea(Long idArea){
		this.idArea = idArea;
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
        
        hash = 37 * hash + Objects.hashCode(this.idAsunto);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idArea);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AsuntoDTO que se pasa
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
        final AsuntoDTO other = (AsuntoDTO) obj;
                
        if (!Objects.equals(this.idAsunto, other.idAsunto)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.idArea, other.idArea)) {
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
	public AsuntoDTO transformarSinDependencias(Asunto obj) {
		AsuntoDTO asuntoDTO = new AsuntoDTO();
		
		asuntoDTO.setIdAsunto(obj.getIdAsunto());
		asuntoDTO.setNombre(obj.getNombre());
		asuntoDTO.setIdArea(obj.getIdArea());
		asuntoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		asuntoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		asuntoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		asuntoDTO.setDescripcion(obj.getDescripcion());
		
		return asuntoDTO;
	}

	@Override
	public AsuntoDTO transformarConDependencias(Asunto obj) {
		AsuntoDTO asuntoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return asuntoDTO;
	}

	@Override
	public Asunto transformarEntidadSinDependencias(Asunto obj) {
		Asunto asunto = new Asunto();
		
		asunto.setIdAsunto(obj.getIdAsunto());
		
		asunto.setNombre(obj.getNombre());
		asunto.setIdArea(obj.getIdArea());
		asunto.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		asunto.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		asunto.setEstadoRegistro(obj.getEstadoRegistro());
		asunto.setDescripcion(obj.getDescripcion());
		
		return asunto;
	}


	@Override
	public Asunto transformarEntidadConDependencias(Asunto obj) {
		Asunto asunto = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return asunto;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Asunto> coleccion) {
		List<AsuntoDTO> asuntoDTOList = new ArrayList<>();
		for(Asunto c : coleccion)
			asuntoDTOList.add(transformarConDependencias(c));
		return asuntoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Asunto> coleccion) {
		List<AsuntoDTO> asuntoDTOList = new ArrayList<>();
		for(Asunto c : coleccion)
			asuntoDTOList.add(transformarSinDependencias(c));
		return asuntoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Asunto> coleccion) {
		List<Asunto> asuntoList = new ArrayList<>();
		for(Asunto c : coleccion)
			asuntoList.add(transformarEntidadConDependencias(c));
		return asuntoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Asunto> coleccion) {
		List<Asunto> asuntoList = new ArrayList<>();
		for(Asunto c : coleccion)
			asuntoList.add(transformarEntidadSinDependencias(c));
		return asuntoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
