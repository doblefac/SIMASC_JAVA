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

import com.ccb.simasc.transversal.entidades.Norma;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad NormaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class NormaDTO extends IDTO<Norma> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idNorma;

	private String nombre;		
	private String url;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public NormaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdNorma(){
		return this.idNorma;
	}
	
	public void setIdNorma(Long idNorma){
		this.idNorma = idNorma;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
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
        
        hash = 37 * hash + Objects.hashCode(this.idNorma);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NormaDTO que se pasa
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
        final NormaDTO other = (NormaDTO) obj;
                
        if (!Objects.equals(this.idNorma, other.idNorma)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.url, other.url)) {
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
	public NormaDTO transformarSinDependencias(Norma obj) {
		NormaDTO normaDTO = new NormaDTO();
		
		normaDTO.setIdNorma(obj.getIdNorma());
		normaDTO.setNombre(obj.getNombre());
		normaDTO.setUrl(obj.getUrl());
		normaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		normaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		normaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return normaDTO;
	}

	@Override
	public NormaDTO transformarConDependencias(Norma obj) {
		NormaDTO normaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return normaDTO;
	}

	@Override
	public Norma transformarEntidadSinDependencias(Norma obj) {
		Norma norma = new Norma();
		
		norma.setIdNorma(obj.getIdNorma());
		
		norma.setNombre(obj.getNombre());
		norma.setUrl(obj.getUrl());
		norma.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		norma.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		norma.setEstadoRegistro(obj.getEstadoRegistro());
		
		return norma;
	}


	@Override
	public Norma transformarEntidadConDependencias(Norma obj) {
		Norma norma = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return norma;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Norma> coleccion) {
		List<NormaDTO> normaDTOList = new ArrayList<>();
		for(Norma c : coleccion)
			normaDTOList.add(transformarConDependencias(c));
		return normaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Norma> coleccion) {
		List<NormaDTO> normaDTOList = new ArrayList<>();
		for(Norma c : coleccion)
			normaDTOList.add(transformarSinDependencias(c));
		return normaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Norma> coleccion) {
		List<Norma> normaList = new ArrayList<>();
		for(Norma c : coleccion)
			normaList.add(transformarEntidadConDependencias(c));
		return normaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Norma> coleccion) {
		List<Norma> normaList = new ArrayList<>();
		for(Norma c : coleccion)
			normaList.add(transformarEntidadSinDependencias(c));
		return normaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
