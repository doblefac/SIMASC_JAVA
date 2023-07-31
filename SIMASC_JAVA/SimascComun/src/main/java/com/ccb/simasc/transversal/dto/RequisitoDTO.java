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

import com.ccb.simasc.transversal.entidades.Requisito;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RequisitoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RequisitoDTO extends IDTO<Requisito> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idRequisito;

	private String nombre;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public RequisitoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdRequisito(){
		return this.idRequisito;
	}
	
	public void setIdRequisito(Long idRequisito){
		this.idRequisito = idRequisito;
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
        
        hash = 37 * hash + Objects.hashCode(this.idRequisito);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RequisitoDTO que se pasa
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
        final RequisitoDTO other = (RequisitoDTO) obj;
                
        if (!Objects.equals(this.idRequisito, other.idRequisito)) {
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
	public RequisitoDTO transformarSinDependencias(Requisito obj) {
		RequisitoDTO requisitoDTO = new RequisitoDTO();
		
		requisitoDTO.setIdRequisito(obj.getIdRequisito());
		requisitoDTO.setNombre(obj.getNombre());
		requisitoDTO.setDescripcion(obj.getDescripcion());
		requisitoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisitoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisitoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisitoDTO;
	}

	@Override
	public RequisitoDTO transformarConDependencias(Requisito obj) {
		RequisitoDTO requisitoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return requisitoDTO;
	}

	@Override
	public Requisito transformarEntidadSinDependencias(Requisito obj) {
		Requisito requisito = new Requisito();
		
		requisito.setIdRequisito(obj.getIdRequisito());
		
		requisito.setNombre(obj.getNombre());
		requisito.setDescripcion(obj.getDescripcion());
		requisito.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisito.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisito.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisito;
	}


	@Override
	public Requisito transformarEntidadConDependencias(Requisito obj) {
		Requisito requisito = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return requisito;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Requisito> coleccion) {
		List<RequisitoDTO> requisitoDTOList = new ArrayList<>();
		for(Requisito c : coleccion)
			requisitoDTOList.add(transformarConDependencias(c));
		return requisitoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Requisito> coleccion) {
		List<RequisitoDTO> requisitoDTOList = new ArrayList<>();
		for(Requisito c : coleccion)
			requisitoDTOList.add(transformarSinDependencias(c));
		return requisitoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Requisito> coleccion) {
		List<Requisito> requisitoList = new ArrayList<>();
		for(Requisito c : coleccion)
			requisitoList.add(transformarEntidadConDependencias(c));
		return requisitoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Requisito> coleccion) {
		List<Requisito> requisitoList = new ArrayList<>();
		for(Requisito c : coleccion)
			requisitoList.add(transformarEntidadSinDependencias(c));
		return requisitoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
