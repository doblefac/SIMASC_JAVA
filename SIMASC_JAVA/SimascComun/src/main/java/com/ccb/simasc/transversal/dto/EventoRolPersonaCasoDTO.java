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

import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EventoRolPersonaCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EventoRolPersonaCasoDTO extends IDTO<EventoRolPersonaCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idEventoRolPersonaCaso;

	private String estadoAsignado;		
	private Date fechaDeAsignacion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idRol;		
	private Long idPersona;		
	private Long idCaso;		
	private String motivoInactivacion;		
	
    public EventoRolPersonaCasoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdEventoRolPersonaCaso(){
		return this.idEventoRolPersonaCaso;
	}
	
	public void setIdEventoRolPersonaCaso(Long idEventoRolPersonaCaso){
		this.idEventoRolPersonaCaso = idEventoRolPersonaCaso;
	}
	
	public String getEstadoAsignado(){
		return this.estadoAsignado;
	}
	
	public void setEstadoAsignado(String estadoAsignado){
		this.estadoAsignado = estadoAsignado;
	}
		
	public Date getFechaDeAsignacion(){
		return this.fechaDeAsignacion;
	}
	
	public void setFechaDeAsignacion(Date fechaDeAsignacion){
		this.fechaDeAsignacion = fechaDeAsignacion;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getMotivoInactivacion(){
		return this.motivoInactivacion;
	}
	
	public void setMotivoInactivacion(String motivoInactivacion){
		this.motivoInactivacion = motivoInactivacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idEventoRolPersonaCaso);        
        hash = 37 * hash + Objects.hashCode(this.estadoAsignado);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAsignacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.motivoInactivacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EventoRolPersonaCasoDTO que se pasa
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
        final EventoRolPersonaCasoDTO other = (EventoRolPersonaCasoDTO) obj;
                
        if (!Objects.equals(this.idEventoRolPersonaCaso, other.idEventoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoAsignado, other.estadoAsignado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAsignacion, other.fechaDeAsignacion)) {
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
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        return Objects.equals(this.motivoInactivacion, other.motivoInactivacion);
                
    }
    
    @Override
	public EventoRolPersonaCasoDTO transformarSinDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCasoDTO eventoRolPersonaCasoDTO = new EventoRolPersonaCasoDTO();
		
		eventoRolPersonaCasoDTO.setIdEventoRolPersonaCaso(obj.getIdEventoRolPersonaCaso());
		eventoRolPersonaCasoDTO.setEstadoAsignado(obj.getEstadoAsignado());
		eventoRolPersonaCasoDTO.setFechaDeAsignacion(obj.getFechaDeAsignacion());
		eventoRolPersonaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		eventoRolPersonaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		eventoRolPersonaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		eventoRolPersonaCasoDTO.setIdRol(obj.getIdRol());
		eventoRolPersonaCasoDTO.setIdPersona(obj.getIdPersona());
		eventoRolPersonaCasoDTO.setIdCaso(obj.getIdCaso());
		eventoRolPersonaCasoDTO.setMotivoInactivacion(obj.getMotivoInactivacion());
		
		return eventoRolPersonaCasoDTO;
	}

	@Override
	public EventoRolPersonaCasoDTO transformarConDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCasoDTO eventoRolPersonaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return eventoRolPersonaCasoDTO;
	}

	@Override
	public EventoRolPersonaCaso transformarEntidadSinDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
		
		eventoRolPersonaCaso.setIdEventoRolPersonaCaso(obj.getIdEventoRolPersonaCaso());
		
		eventoRolPersonaCaso.setEstadoAsignado(obj.getEstadoAsignado());
		eventoRolPersonaCaso.setFechaDeAsignacion(obj.getFechaDeAsignacion());
		eventoRolPersonaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		eventoRolPersonaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		eventoRolPersonaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		eventoRolPersonaCaso.setIdRol(obj.getIdRol());
		eventoRolPersonaCaso.setIdPersona(obj.getIdPersona());
		eventoRolPersonaCaso.setIdCaso(obj.getIdCaso());
		eventoRolPersonaCaso.setMotivoInactivacion(obj.getMotivoInactivacion());
		
		return eventoRolPersonaCaso;
	}


	@Override
	public EventoRolPersonaCaso transformarEntidadConDependencias(EventoRolPersonaCaso obj) {
		EventoRolPersonaCaso eventoRolPersonaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return eventoRolPersonaCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EventoRolPersonaCaso> coleccion) {
		List<EventoRolPersonaCasoDTO> eventoRolPersonaCasoDTOList = new ArrayList<>();
		for(EventoRolPersonaCaso c : coleccion)
			eventoRolPersonaCasoDTOList.add(transformarConDependencias(c));
		return eventoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EventoRolPersonaCaso> coleccion) {
		List<EventoRolPersonaCasoDTO> eventoRolPersonaCasoDTOList = new ArrayList<>();
		for(EventoRolPersonaCaso c : coleccion)
			eventoRolPersonaCasoDTOList.add(transformarSinDependencias(c));
		return eventoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EventoRolPersonaCaso> coleccion) {
		List<EventoRolPersonaCaso> eventoRolPersonaCasoList = new ArrayList<>();
		for(EventoRolPersonaCaso c : coleccion)
			eventoRolPersonaCasoList.add(transformarEntidadConDependencias(c));
		return eventoRolPersonaCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EventoRolPersonaCaso> coleccion) {
		List<EventoRolPersonaCaso> eventoRolPersonaCasoList = new ArrayList<>();
		for(EventoRolPersonaCaso c : coleccion)
			eventoRolPersonaCasoList.add(transformarEntidadSinDependencias(c));
		return eventoRolPersonaCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
