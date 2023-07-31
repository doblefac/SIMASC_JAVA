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

import com.ccb.simasc.transversal.entidades.AreaAsuntoClasificacion;



// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AreaAsuntoClasificacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AreaAsuntoClasificacionDTO extends IDTO<AreaAsuntoClasificacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idAreaAsuntoClasificacion;

	private Long idAsunto;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idClasificacion;		
	
    public AreaAsuntoClasificacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAreaAsuntoClasificacion(){
		return this.idAreaAsuntoClasificacion;
	}
	
	public void setIdAreaAsuntoClasificacion(Long idAreaAsuntoClasificacion){
		this.idAreaAsuntoClasificacion = idAreaAsuntoClasificacion;
	}
	
	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
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
		
	public Long getIdClasificacion(){
		return this.idClasificacion;
	}
	
	public void setIdClasificacion(Long idClasificacion){
		this.idClasificacion = idClasificacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAreaAsuntoClasificacion);        
        hash = 37 * hash + Objects.hashCode(this.idAsunto);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idClasificacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AreaAsuntoClasificacionDTO que se pasa
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
        final AreaAsuntoClasificacionDTO other = (AreaAsuntoClasificacionDTO) obj;
                
        if (!Objects.equals(this.idAreaAsuntoClasificacion, other.idAreaAsuntoClasificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idAsunto, other.idAsunto)) {
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
        
        return Objects.equals(this.idClasificacion, other.idClasificacion);
                
    }
    
    @Override
	public AreaAsuntoClasificacionDTO transformarSinDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacionDTO areaAsuntoClasificacionDTO = new AreaAsuntoClasificacionDTO();
		
		areaAsuntoClasificacionDTO.setIdAreaAsuntoClasificacion(obj.getIdAreaAsuntoClasificacion());
		areaAsuntoClasificacionDTO.setIdAsunto(obj.getIdAsunto());
		areaAsuntoClasificacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		areaAsuntoClasificacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		areaAsuntoClasificacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		areaAsuntoClasificacionDTO.setIdClasificacion(obj.getIdClasificacion());
		
		return areaAsuntoClasificacionDTO;
	}

	@Override
	public AreaAsuntoClasificacionDTO transformarConDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacionDTO areaAsuntoClasificacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return areaAsuntoClasificacionDTO;
	}

	@Override
	public AreaAsuntoClasificacion transformarEntidadSinDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacion areaAsuntoClasificacion = new AreaAsuntoClasificacion();
		
		areaAsuntoClasificacion.setIdAreaAsuntoClasificacion(obj.getIdAreaAsuntoClasificacion());
		
		areaAsuntoClasificacion.setIdAsunto(obj.getIdAsunto());
		areaAsuntoClasificacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		areaAsuntoClasificacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		areaAsuntoClasificacion.setEstadoRegistro(obj.getEstadoRegistro());
		areaAsuntoClasificacion.setIdClasificacion(obj.getIdClasificacion());
		
		return areaAsuntoClasificacion;
	}


	@Override
	public AreaAsuntoClasificacion transformarEntidadConDependencias(AreaAsuntoClasificacion obj) {
		AreaAsuntoClasificacion areaAsuntoClasificacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return areaAsuntoClasificacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AreaAsuntoClasificacion> coleccion) {
		List<AreaAsuntoClasificacionDTO> areaAsuntoClasificacionDTOList = new ArrayList<>();
		for(AreaAsuntoClasificacion c : coleccion)
			areaAsuntoClasificacionDTOList.add(transformarConDependencias(c));
		return areaAsuntoClasificacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AreaAsuntoClasificacion> coleccion) {
		List<AreaAsuntoClasificacionDTO> areaAsuntoClasificacionDTOList = new ArrayList<>();
		for(AreaAsuntoClasificacion c : coleccion)
			areaAsuntoClasificacionDTOList.add(transformarSinDependencias(c));
		return areaAsuntoClasificacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AreaAsuntoClasificacion> coleccion) {
		List<AreaAsuntoClasificacion> areaAsuntoClasificacionList = new ArrayList<>();
		for(AreaAsuntoClasificacion c : coleccion)
			areaAsuntoClasificacionList.add(transformarEntidadConDependencias(c));
		return areaAsuntoClasificacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AreaAsuntoClasificacion> coleccion) {
		List<AreaAsuntoClasificacion> areaAsuntoClasificacionList = new ArrayList<>();
		for(AreaAsuntoClasificacion c : coleccion)
			areaAsuntoClasificacionList.add(transformarEntidadSinDependencias(c));
		return areaAsuntoClasificacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
