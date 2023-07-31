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

import com.ccb.simasc.transversal.entidades.ParametrosGenerales;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametrosGeneralesDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametrosGeneralesDTO extends IDTO<ParametrosGenerales> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private String codigo;

	private boolean editable;		
	private String tipo;		
	private String nombre;		
	private Long valorNumerico;		
	private String valorTexto;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParametrosGeneralesDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public boolean getEditable(){
		return this.editable;
	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Long getValorNumerico(){
		return this.valorNumerico;
	}
	
	public void setValorNumerico(Long valorNumerico){
		this.valorNumerico = valorNumerico;
	}
		
	public String getValorTexto(){
		return this.valorTexto;
	}
	
	public void setValorTexto(String valorTexto){
		this.valorTexto = valorTexto;
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
        
        hash = 37 * hash + Objects.hashCode(this.codigo);        
        hash = 37 * hash + (this.editable ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.valorNumerico);
        hash = 37 * hash + Objects.hashCode(this.valorTexto);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrosGeneralesDTO que se pasa
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
        final ParametrosGeneralesDTO other = (ParametrosGeneralesDTO) obj;
                
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.editable, other.editable)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.valorNumerico, other.valorNumerico)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTexto, other.valorTexto)) {
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
	public ParametrosGeneralesDTO transformarSinDependencias(ParametrosGenerales obj) {
		ParametrosGeneralesDTO parametrosGeneralesDTO = new ParametrosGeneralesDTO();
		
		parametrosGeneralesDTO.setCodigo(obj.getCodigo());
		parametrosGeneralesDTO.setEditable(obj.getEditable());
		parametrosGeneralesDTO.setTipo(obj.getTipo());
		parametrosGeneralesDTO.setNombre(obj.getNombre());
		parametrosGeneralesDTO.setValorNumerico(obj.getValorNumerico());
		parametrosGeneralesDTO.setValorTexto(obj.getValorTexto());
		parametrosGeneralesDTO.setDescripcion(obj.getDescripcion());
		parametrosGeneralesDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrosGeneralesDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrosGeneralesDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametrosGeneralesDTO;
	}

	@Override
	public ParametrosGeneralesDTO transformarConDependencias(ParametrosGenerales obj) {
		ParametrosGeneralesDTO parametrosGeneralesDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametrosGeneralesDTO;
	}

	@Override
	public ParametrosGenerales transformarEntidadSinDependencias(ParametrosGenerales obj) {
		ParametrosGenerales parametrosGenerales = new ParametrosGenerales();
		
		parametrosGenerales.setCodigo(obj.getCodigo());
		
		parametrosGenerales.setEditable(obj.getEditable());
		parametrosGenerales.setTipo(obj.getTipo());
		parametrosGenerales.setNombre(obj.getNombre());
		parametrosGenerales.setValorNumerico(obj.getValorNumerico());
		parametrosGenerales.setValorTexto(obj.getValorTexto());
		parametrosGenerales.setDescripcion(obj.getDescripcion());
		parametrosGenerales.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrosGenerales.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrosGenerales.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametrosGenerales;
	}


	@Override
	public ParametrosGenerales transformarEntidadConDependencias(ParametrosGenerales obj) {
		ParametrosGenerales parametrosGenerales = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametrosGenerales;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametrosGenerales> coleccion) {
		List<ParametrosGeneralesDTO> parametrosGeneralesDTOList = new ArrayList<>();
		for(ParametrosGenerales c : coleccion)
			parametrosGeneralesDTOList.add(transformarConDependencias(c));
		return parametrosGeneralesDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametrosGenerales> coleccion) {
		List<ParametrosGeneralesDTO> parametrosGeneralesDTOList = new ArrayList<>();
		for(ParametrosGenerales c : coleccion)
			parametrosGeneralesDTOList.add(transformarSinDependencias(c));
		return parametrosGeneralesDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametrosGenerales> coleccion) {
		List<ParametrosGenerales> parametrosGeneralesList = new ArrayList<>();
		for(ParametrosGenerales c : coleccion)
			parametrosGeneralesList.add(transformarEntidadConDependencias(c));
		return parametrosGeneralesList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametrosGenerales> coleccion) {
		List<ParametrosGenerales> parametrosGeneralesList = new ArrayList<>();
		for(ParametrosGenerales c : coleccion)
			parametrosGeneralesList.add(transformarEntidadSinDependencias(c));
		return parametrosGeneralesList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
