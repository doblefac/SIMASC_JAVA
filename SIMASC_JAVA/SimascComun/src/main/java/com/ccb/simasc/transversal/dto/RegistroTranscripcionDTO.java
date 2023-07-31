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

import com.ccb.simasc.transversal.entidades.RegistroTranscripcion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RegistroTranscripcionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RegistroTranscripcionDTO extends IDTO<RegistroTranscripcion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idTranscripcion;

	private Long idRegistroTranscripcion;		
	private Long tiempoTranscrito;		
	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public RegistroTranscripcionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdTranscripcion(){
		return this.idTranscripcion;
	}
	
	public void setIdTranscripcion(Long idTranscripcion){
		this.idTranscripcion = idTranscripcion;
	}
	
	public Long getIdRegistroTranscripcion(){
		return this.idRegistroTranscripcion;
	}
	
	public void setIdRegistroTranscripcion(Long idRegistroTranscripcion){
		this.idRegistroTranscripcion = idRegistroTranscripcion;
	}
		
	public Long getTiempoTranscrito(){
		return this.tiempoTranscrito;
	}
	
	public void setTiempoTranscrito(Long tiempoTranscrito){
		this.tiempoTranscrito = tiempoTranscrito;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
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
        
        hash = 37 * hash + Objects.hashCode(this.idTranscripcion);        
        hash = 37 * hash + Objects.hashCode(this.idRegistroTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.tiempoTranscrito);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RegistroTranscripcionDTO que se pasa
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
        final RegistroTranscripcionDTO other = (RegistroTranscripcionDTO) obj;
                
        if (!Objects.equals(this.idTranscripcion, other.idTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idRegistroTranscripcion, other.idRegistroTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoTranscrito, other.tiempoTranscrito)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
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
	public RegistroTranscripcionDTO transformarSinDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcionDTO registroTranscripcionDTO = new RegistroTranscripcionDTO();
		
		registroTranscripcionDTO.setIdTranscripcion(obj.getIdTranscripcion());
		registroTranscripcionDTO.setIdRegistroTranscripcion(obj.getIdRegistroTranscripcion());
		registroTranscripcionDTO.setTiempoTranscrito(obj.getTiempoTranscrito());
		registroTranscripcionDTO.setFecha(obj.getFecha());
		registroTranscripcionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		registroTranscripcionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		registroTranscripcionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return registroTranscripcionDTO;
	}

	@Override
	public RegistroTranscripcionDTO transformarConDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcionDTO registroTranscripcionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return registroTranscripcionDTO;
	}

	@Override
	public RegistroTranscripcion transformarEntidadSinDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcion registroTranscripcion = new RegistroTranscripcion();
		
		registroTranscripcion.setIdTranscripcion(obj.getIdTranscripcion());
		
		registroTranscripcion.setIdRegistroTranscripcion(obj.getIdRegistroTranscripcion());
		registroTranscripcion.setTiempoTranscrito(obj.getTiempoTranscrito());
		registroTranscripcion.setFecha(obj.getFecha());
		registroTranscripcion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		registroTranscripcion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		registroTranscripcion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return registroTranscripcion;
	}


	@Override
	public RegistroTranscripcion transformarEntidadConDependencias(RegistroTranscripcion obj) {
		RegistroTranscripcion registroTranscripcion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return registroTranscripcion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RegistroTranscripcion> coleccion) {
		List<RegistroTranscripcionDTO> registroTranscripcionDTOList = new ArrayList<>();
		for(RegistroTranscripcion c : coleccion)
			registroTranscripcionDTOList.add(transformarConDependencias(c));
		return registroTranscripcionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RegistroTranscripcion> coleccion) {
		List<RegistroTranscripcionDTO> registroTranscripcionDTOList = new ArrayList<>();
		for(RegistroTranscripcion c : coleccion)
			registroTranscripcionDTOList.add(transformarSinDependencias(c));
		return registroTranscripcionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RegistroTranscripcion> coleccion) {
		List<RegistroTranscripcion> registroTranscripcionList = new ArrayList<>();
		for(RegistroTranscripcion c : coleccion)
			registroTranscripcionList.add(transformarEntidadConDependencias(c));
		return registroTranscripcionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RegistroTranscripcion> coleccion) {
		List<RegistroTranscripcion> registroTranscripcionList = new ArrayList<>();
		for(RegistroTranscripcion c : coleccion)
			registroTranscripcionList.add(transformarEntidadSinDependencias(c));
		return registroTranscripcionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
