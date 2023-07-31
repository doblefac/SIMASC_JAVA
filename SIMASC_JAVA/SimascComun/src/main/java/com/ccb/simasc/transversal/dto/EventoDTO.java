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

import com.ccb.simasc.transversal.entidades.Evento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EventoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EventoDTO extends IDTO<Evento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idEvento;

	private String tipoEvento;		
	private Date fechaEvento;		
	private String observaciones;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public EventoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdEvento(){
		return this.idEvento;
	}
	
	public void setIdEvento(Long idEvento){
		this.idEvento = idEvento;
	}
	
	public String getTipoEvento(){
		return this.tipoEvento;
	}
	
	public void setTipoEvento(String tipoEvento){
		this.tipoEvento = tipoEvento;
	}
		
	public Date getFechaEvento(){
		return this.fechaEvento;
	}
	
	public void setFechaEvento(Date fechaEvento){
		this.fechaEvento = fechaEvento;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
        
        hash = 37 * hash + Objects.hashCode(this.idEvento);        
        hash = 37 * hash + Objects.hashCode(this.tipoEvento);
        hash = 37 * hash + Objects.hashCode(this.fechaEvento);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EventoDTO que se pasa
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
        final EventoDTO other = (EventoDTO) obj;
                
        if (!Objects.equals(this.idEvento, other.idEvento)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoEvento, other.tipoEvento)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEvento, other.fechaEvento)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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
	public EventoDTO transformarSinDependencias(Evento obj) {
		EventoDTO eventoDTO = new EventoDTO();
		
		eventoDTO.setIdEvento(obj.getIdEvento());
		eventoDTO.setTipoEvento(obj.getTipoEvento());
		eventoDTO.setFechaEvento(obj.getFechaEvento());
		eventoDTO.setObservaciones(obj.getObservaciones());
		eventoDTO.setIdCaso(obj.getIdCaso());
		eventoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		eventoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		eventoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return eventoDTO;
	}

	@Override
	public EventoDTO transformarConDependencias(Evento obj) {
		EventoDTO eventoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return eventoDTO;
	}

	@Override
	public Evento transformarEntidadSinDependencias(Evento obj) {
		Evento evento = new Evento();
		
		evento.setIdEvento(obj.getIdEvento());
		
		evento.setTipoEvento(obj.getTipoEvento());
		evento.setFechaEvento(obj.getFechaEvento());
		evento.setObservaciones(obj.getObservaciones());
		evento.setIdCaso(obj.getIdCaso());
		evento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		evento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		evento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return evento;
	}


	@Override
	public Evento transformarEntidadConDependencias(Evento obj) {
		Evento evento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return evento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Evento> coleccion) {
		List<EventoDTO> eventoDTOList = new ArrayList<>();
		for(Evento c : coleccion)
			eventoDTOList.add(transformarConDependencias(c));
		return eventoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Evento> coleccion) {
		List<EventoDTO> eventoDTOList = new ArrayList<>();
		for(Evento c : coleccion)
			eventoDTOList.add(transformarSinDependencias(c));
		return eventoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Evento> coleccion) {
		List<Evento> eventoList = new ArrayList<>();
		for(Evento c : coleccion)
			eventoList.add(transformarEntidadConDependencias(c));
		return eventoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Evento> coleccion) {
		List<Evento> eventoList = new ArrayList<>();
		for(Evento c : coleccion)
			eventoList.add(transformarEntidadSinDependencias(c));
		return eventoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
