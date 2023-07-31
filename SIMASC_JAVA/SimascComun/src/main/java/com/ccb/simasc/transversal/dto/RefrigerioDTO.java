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

import com.ccb.simasc.transversal.entidades.Refrigerio;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RefrigerioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RefrigerioDTO extends IDTO<Refrigerio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Integer idRefrigerio;

	private String refrigerio;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	
    public RefrigerioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Integer getIdRefrigerio(){
		return this.idRefrigerio;
	}
	
	public void setIdRefrigerio(Integer idRefrigerio){
		this.idRefrigerio = idRefrigerio;
	}
	
	public String getRefrigerio(){
		return this.refrigerio;
	}
	
	public void setRefrigerio(String refrigerio){
		this.refrigerio = refrigerio;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idRefrigerio);        
        hash = 37 * hash + Objects.hashCode(this.refrigerio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RefrigerioDTO que se pasa
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
        final RefrigerioDTO other = (RefrigerioDTO) obj;
                
        if (!Objects.equals(this.idRefrigerio, other.idRefrigerio)) {
            return false;
        }
        
        if (!Objects.equals(this.refrigerio, other.refrigerio)) {
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
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public RefrigerioDTO transformarSinDependencias(Refrigerio obj) {
		RefrigerioDTO refrigerioDTO = new RefrigerioDTO();
		
		refrigerioDTO.setIdRefrigerio(obj.getIdRefrigerio());
		refrigerioDTO.setRefrigerio(obj.getRefrigerio());
		refrigerioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		refrigerioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		refrigerioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		refrigerioDTO.setIdPersona(obj.getIdPersona());
		
		return refrigerioDTO;
	}

	@Override
	public RefrigerioDTO transformarConDependencias(Refrigerio obj) {
		RefrigerioDTO refrigerioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return refrigerioDTO;
	}

	@Override
	public Refrigerio transformarEntidadSinDependencias(Refrigerio obj) {
		Refrigerio refrigerio = new Refrigerio();
		
		refrigerio.setIdRefrigerio(obj.getIdRefrigerio());
		
		refrigerio.setRefrigerio(obj.getRefrigerio());
		refrigerio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		refrigerio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		refrigerio.setEstadoRegistro(obj.getEstadoRegistro());
		refrigerio.setIdPersona(obj.getIdPersona());
		
		return refrigerio;
	}


	@Override
	public Refrigerio transformarEntidadConDependencias(Refrigerio obj) {
		Refrigerio refrigerio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return refrigerio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Refrigerio> coleccion) {
		List<RefrigerioDTO> refrigerioDTOList = new ArrayList<>();
		for(Refrigerio c : coleccion)
			refrigerioDTOList.add(transformarConDependencias(c));
		return refrigerioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Refrigerio> coleccion) {
		List<RefrigerioDTO> refrigerioDTOList = new ArrayList<>();
		for(Refrigerio c : coleccion)
			refrigerioDTOList.add(transformarSinDependencias(c));
		return refrigerioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Refrigerio> coleccion) {
		List<Refrigerio> refrigerioList = new ArrayList<>();
		for(Refrigerio c : coleccion)
			refrigerioList.add(transformarEntidadSinDependencias(c));
		return refrigerioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Refrigerio> coleccion) {
		List<Refrigerio> refrigerioList = new ArrayList<>();
		for(Refrigerio c : coleccion)
			refrigerioList.add(transformarEntidadConDependencias(c));
		return refrigerioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
