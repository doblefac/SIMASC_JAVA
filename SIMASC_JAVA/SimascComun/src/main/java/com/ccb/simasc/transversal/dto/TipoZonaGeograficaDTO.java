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

import com.ccb.simasc.transversal.entidades.TipoZonaGeografica;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TipoZonaGeograficaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TipoZonaGeograficaDTO extends IDTO<TipoZonaGeografica> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Integer idTipoZonaGeografica;

	private String nombre;		
	private boolean opcional;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Integer idTipoZonaGeograficaPadre;		
	
    public TipoZonaGeograficaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Integer getIdTipoZonaGeografica(){
		return this.idTipoZonaGeografica;
	}
	
	public void setIdTipoZonaGeografica(Integer idTipoZonaGeografica){
		this.idTipoZonaGeografica = idTipoZonaGeografica;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getOpcional(){
		return this.opcional;
	}
	
	public void setOpcional(boolean opcional){
		this.opcional = opcional;
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
		
	public Integer getIdTipoZonaGeograficaPadre(){
		return this.idTipoZonaGeograficaPadre;
	}
	
	public void setIdTipoZonaGeograficaPadre(Integer idTipoZonaGeograficaPadre){
		this.idTipoZonaGeograficaPadre = idTipoZonaGeograficaPadre;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeografica);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.opcional ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeograficaPadre);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoZonaGeograficaDTO que se pasa
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
        final TipoZonaGeograficaDTO other = (TipoZonaGeograficaDTO) obj;
                
        if (!Objects.equals(this.idTipoZonaGeografica, other.idTipoZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.opcional, other.opcional)) {
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
        
        return Objects.equals(this.idTipoZonaGeograficaPadre, other.idTipoZonaGeograficaPadre);
                
    }
    
    @Override
	public TipoZonaGeograficaDTO transformarSinDependencias(TipoZonaGeografica obj) {
		TipoZonaGeograficaDTO tipoZonaGeograficaDTO = new TipoZonaGeograficaDTO();
		
		tipoZonaGeograficaDTO.setIdTipoZonaGeografica(obj.getIdTipoZonaGeografica());
		tipoZonaGeograficaDTO.setNombre(obj.getNombre());
		tipoZonaGeograficaDTO.setOpcional(obj.getOpcional());
		tipoZonaGeograficaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoZonaGeograficaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoZonaGeograficaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		tipoZonaGeograficaDTO.setIdTipoZonaGeograficaPadre(obj.getIdTipoZonaGeograficaPadre());
		
		return tipoZonaGeograficaDTO;
	}

	@Override
	public TipoZonaGeograficaDTO transformarConDependencias(TipoZonaGeografica obj) {
		TipoZonaGeograficaDTO tipoZonaGeograficaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tipoZonaGeograficaDTO;
	}

	@Override
	public TipoZonaGeografica transformarEntidadSinDependencias(TipoZonaGeografica obj) {
		TipoZonaGeografica tipoZonaGeografica = new TipoZonaGeografica();
		
		tipoZonaGeografica.setIdTipoZonaGeografica(obj.getIdTipoZonaGeografica());
		
		tipoZonaGeografica.setNombre(obj.getNombre());
		tipoZonaGeografica.setOpcional(obj.getOpcional());
		tipoZonaGeografica.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoZonaGeografica.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoZonaGeografica.setEstadoRegistro(obj.getEstadoRegistro());
		tipoZonaGeografica.setIdTipoZonaGeograficaPadre(obj.getIdTipoZonaGeograficaPadre());
		
		return tipoZonaGeografica;
	}


	@Override
	public TipoZonaGeografica transformarEntidadConDependencias(TipoZonaGeografica obj) {
		TipoZonaGeografica tipoZonaGeografica = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tipoZonaGeografica;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TipoZonaGeografica> coleccion) {
		List<TipoZonaGeograficaDTO> tipoZonaGeograficaDTOList = new ArrayList<>();
		for(TipoZonaGeografica c : coleccion)
			tipoZonaGeograficaDTOList.add(transformarConDependencias(c));
		return tipoZonaGeograficaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TipoZonaGeografica> coleccion) {
		List<TipoZonaGeograficaDTO> tipoZonaGeograficaDTOList = new ArrayList<>();
		for(TipoZonaGeografica c : coleccion)
			tipoZonaGeograficaDTOList.add(transformarSinDependencias(c));
		return tipoZonaGeograficaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TipoZonaGeografica> coleccion) {
		List<TipoZonaGeografica> tipoZonaGeograficaList = new ArrayList<>();
		for(TipoZonaGeografica c : coleccion)
			tipoZonaGeograficaList.add(transformarEntidadConDependencias(c));
		return tipoZonaGeograficaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TipoZonaGeografica> coleccion) {
		List<TipoZonaGeografica> tipoZonaGeograficaList = new ArrayList<>();
		for(TipoZonaGeografica c : coleccion)
			tipoZonaGeograficaList.add(transformarEntidadSinDependencias(c));
		return tipoZonaGeograficaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
