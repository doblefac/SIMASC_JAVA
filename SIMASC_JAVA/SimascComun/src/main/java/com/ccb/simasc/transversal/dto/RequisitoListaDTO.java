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

import com.ccb.simasc.transversal.entidades.RequisitoLista;
import com.ccb.simasc.transversal.entidades.RequisitoListaPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RequisitoListaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RequisitoListaDTO extends IDTO<RequisitoLista> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private RequisitoListaPK requisitoListaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public RequisitoListaDTO(){
		requisitoListaPK = new RequisitoListaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RequisitoListaPK getRequisitoListaPK(){
		return this.requisitoListaPK;
	}
	
	public void setRequisitoListaPK(RequisitoListaPK requisitoListaPK){
		this.requisitoListaPK   = requisitoListaPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.requisitoListaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RequisitoListaDTO que se pasa
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
        final RequisitoListaDTO other = (RequisitoListaDTO) obj;
                
        if (!Objects.equals(this.requisitoListaPK, other.requisitoListaPK)) {
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
	public RequisitoListaDTO transformarSinDependencias(RequisitoLista obj) {
		RequisitoListaDTO requisitoListaDTO = new RequisitoListaDTO();
		
		requisitoListaDTO.setRequisitoListaPK(obj.getRequisitoListaPK());
		requisitoListaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisitoListaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisitoListaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisitoListaDTO;
	}

	@Override
	public RequisitoListaDTO transformarConDependencias(RequisitoLista obj) {
		RequisitoListaDTO requisitoListaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return requisitoListaDTO;
	}

	@Override
	public RequisitoLista transformarEntidadSinDependencias(RequisitoLista obj) {
		RequisitoLista requisitoLista = new RequisitoLista();
		
		requisitoLista.setRequisitoListaPK(obj.getRequisitoListaPK());
		
		requisitoLista.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisitoLista.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisitoLista.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisitoLista;
	}


	@Override
	public RequisitoLista transformarEntidadConDependencias(RequisitoLista obj) {
		RequisitoLista requisitoLista = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return requisitoLista;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RequisitoLista> coleccion) {
		List<RequisitoListaDTO> requisitoListaDTOList = new ArrayList<>();
		for(RequisitoLista c : coleccion)
			requisitoListaDTOList.add(transformarConDependencias(c));
		return requisitoListaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RequisitoLista> coleccion) {
		List<RequisitoListaDTO> requisitoListaDTOList = new ArrayList<>();
		for(RequisitoLista c : coleccion)
			requisitoListaDTOList.add(transformarSinDependencias(c));
		return requisitoListaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RequisitoLista> coleccion) {
		List<RequisitoLista> requisitoListaList = new ArrayList<>();
		for(RequisitoLista c : coleccion)
			requisitoListaList.add(transformarEntidadConDependencias(c));
		return requisitoListaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RequisitoLista> coleccion) {
		List<RequisitoLista> requisitoListaList = new ArrayList<>();
		for(RequisitoLista c : coleccion)
			requisitoListaList.add(transformarEntidadSinDependencias(c));
		return requisitoListaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
