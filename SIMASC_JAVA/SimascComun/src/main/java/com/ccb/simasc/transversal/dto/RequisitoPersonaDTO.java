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

import com.ccb.simasc.transversal.entidades.RequisitoPersona;
import com.ccb.simasc.transversal.entidades.RequisitoPersonaPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RequisitoPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RequisitoPersonaDTO extends IDTO<RequisitoPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private RequisitoPersonaPK requisitoPersonaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public RequisitoPersonaDTO(){
		requisitoPersonaPK = new RequisitoPersonaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RequisitoPersonaPK getRequisitoPersonaPK(){
		return this.requisitoPersonaPK;
	}
	
	public void setRequisitoPersonaPK(RequisitoPersonaPK requisitoPersonaPK){
		this.requisitoPersonaPK   = requisitoPersonaPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.requisitoPersonaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RequisitoPersonaDTO que se pasa
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
        final RequisitoPersonaDTO other = (RequisitoPersonaDTO) obj;
                
        if (!Objects.equals(this.requisitoPersonaPK, other.requisitoPersonaPK)) {
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
	public RequisitoPersonaDTO transformarSinDependencias(RequisitoPersona obj) {
		RequisitoPersonaDTO requisitoPersonaDTO = new RequisitoPersonaDTO();
		
		requisitoPersonaDTO.setRequisitoPersonaPK(obj.getRequisitoPersonaPK());
		requisitoPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisitoPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisitoPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisitoPersonaDTO;
	}

	@Override
	public RequisitoPersonaDTO transformarConDependencias(RequisitoPersona obj) {
		RequisitoPersonaDTO requisitoPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return requisitoPersonaDTO;
	}

	@Override
	public RequisitoPersona transformarEntidadSinDependencias(RequisitoPersona obj) {
		RequisitoPersona requisitoPersona = new RequisitoPersona();
		
		requisitoPersona.setRequisitoPersonaPK(obj.getRequisitoPersonaPK());
		
		requisitoPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		requisitoPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		requisitoPersona.setEstadoRegistro(obj.getEstadoRegistro());
		
		return requisitoPersona;
	}


	@Override
	public RequisitoPersona transformarEntidadConDependencias(RequisitoPersona obj) {
		RequisitoPersona requisitoPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return requisitoPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RequisitoPersona> coleccion) {
		List<RequisitoPersonaDTO> requisitoPersonaDTOList = new ArrayList<>();
		for(RequisitoPersona c : coleccion)
			requisitoPersonaDTOList.add(transformarConDependencias(c));
		return requisitoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RequisitoPersona> coleccion) {
		List<RequisitoPersonaDTO> requisitoPersonaDTOList = new ArrayList<>();
		for(RequisitoPersona c : coleccion)
			requisitoPersonaDTOList.add(transformarSinDependencias(c));
		return requisitoPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RequisitoPersona> coleccion) {
		List<RequisitoPersona> requisitoPersonaList = new ArrayList<>();
		for(RequisitoPersona c : coleccion)
			requisitoPersonaList.add(transformarEntidadConDependencias(c));
		return requisitoPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RequisitoPersona> coleccion) {
		List<RequisitoPersona> requisitoPersonaList = new ArrayList<>();
		for(RequisitoPersona c : coleccion)
			requisitoPersonaList.add(transformarEntidadSinDependencias(c));
		return requisitoPersonaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
