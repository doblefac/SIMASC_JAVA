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

import com.ccb.simasc.transversal.entidades.ZonaGeografica;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ZonaGeograficaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ZonaGeograficaDTO extends IDTO<ZonaGeografica> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private String idZonaGeografica;

	private String nombre;		
	private String idZonaGeograficaPadre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Integer idTipoZonaGeografica;		
	
    public ZonaGeograficaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getIdZonaGeograficaPadre(){
		return this.idZonaGeograficaPadre;
	}
	
	public void setIdZonaGeograficaPadre(String idZonaGeograficaPadre){
		this.idZonaGeograficaPadre = idZonaGeograficaPadre;
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
		
	public Integer getIdTipoZonaGeografica(){
		return this.idTipoZonaGeografica;
	}
	
	public void setIdTipoZonaGeografica(Integer idTipoZonaGeografica){
		this.idTipoZonaGeografica = idTipoZonaGeografica;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeograficaPadre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeografica);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ZonaGeograficaDTO que se pasa
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
        final ZonaGeograficaDTO other = (ZonaGeograficaDTO) obj;
                
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.idZonaGeograficaPadre, other.idZonaGeograficaPadre)) {
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
        
        return Objects.equals(this.idTipoZonaGeografica, other.idTipoZonaGeografica);
                
    }
    
    @Override
	public ZonaGeograficaDTO transformarSinDependencias(ZonaGeografica obj) {
		ZonaGeograficaDTO zonaGeograficaDTO = new ZonaGeograficaDTO();
		
		zonaGeograficaDTO.setIdZonaGeografica(obj.getIdZonaGeografica());
		zonaGeograficaDTO.setNombre(obj.getNombre());
		zonaGeograficaDTO.setIdZonaGeograficaPadre(obj.getIdZonaGeograficaPadre());
		zonaGeograficaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		zonaGeograficaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		zonaGeograficaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		zonaGeograficaDTO.setIdTipoZonaGeografica(obj.getIdTipoZonaGeografica());
		
		return zonaGeograficaDTO;
	}

	@Override
	public ZonaGeograficaDTO transformarConDependencias(ZonaGeografica obj) {
		ZonaGeograficaDTO zonaGeograficaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return zonaGeograficaDTO;
	}

	@Override
	public ZonaGeografica transformarEntidadSinDependencias(ZonaGeografica obj) {
		ZonaGeografica zonaGeografica = new ZonaGeografica();
		
		zonaGeografica.setIdZonaGeografica(obj.getIdZonaGeografica());
		
		zonaGeografica.setNombre(obj.getNombre());
		zonaGeografica.setIdZonaGeograficaPadre(obj.getIdZonaGeograficaPadre());
		zonaGeografica.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		zonaGeografica.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		zonaGeografica.setEstadoRegistro(obj.getEstadoRegistro());
		zonaGeografica.setIdTipoZonaGeografica(obj.getIdTipoZonaGeografica());
		
		return zonaGeografica;
	}


	@Override
	public ZonaGeografica transformarEntidadConDependencias(ZonaGeografica obj) {
		ZonaGeografica zonaGeografica = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return zonaGeografica;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ZonaGeografica> coleccion) {
		List<ZonaGeograficaDTO> zonaGeograficaDTOList = new ArrayList<>();
		for(ZonaGeografica c : coleccion)
			zonaGeograficaDTOList.add(transformarConDependencias(c));
		return zonaGeograficaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ZonaGeografica> coleccion) {
		List<ZonaGeograficaDTO> zonaGeograficaDTOList = new ArrayList<>();
		for(ZonaGeografica c : coleccion)
			zonaGeograficaDTOList.add(transformarSinDependencias(c));
		return zonaGeograficaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ZonaGeografica> coleccion) {
		List<ZonaGeografica> zonaGeograficaList = new ArrayList<>();
		for(ZonaGeografica c : coleccion)
			zonaGeograficaList.add(transformarEntidadConDependencias(c));
		return zonaGeograficaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ZonaGeografica> coleccion) {
		List<ZonaGeografica> zonaGeograficaList = new ArrayList<>();
		for(ZonaGeografica c : coleccion)
			zonaGeograficaList.add(transformarEntidadSinDependencias(c));
		return zonaGeograficaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
