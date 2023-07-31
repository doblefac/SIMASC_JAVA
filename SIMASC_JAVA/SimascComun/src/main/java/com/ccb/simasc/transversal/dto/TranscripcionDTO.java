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

import com.ccb.simasc.transversal.entidades.Transcripcion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TranscripcionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TranscripcionDTO extends IDTO<Transcripcion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idTranscripcion;

	private String estado;		
	private Long tiempoTranscrito;		
	private Long tiempoInicial;		
	private Long tiempoFinal;		
	private Date fechaInicioTranscripcion;		
	private Date fechaEntregaTranscripcion;		
	private Date fechaPrevistaDeEntrega;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocGenerado;		
	private Long idDocAudioFuente;		
	private Long idPersona;		
	
    public TranscripcionDTO(){
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
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getTiempoTranscrito(){
		return this.tiempoTranscrito;
	}
	
	public void setTiempoTranscrito(Long tiempoTranscrito){
		this.tiempoTranscrito = tiempoTranscrito;
	}
		
	public Long getTiempoInicial(){
		return this.tiempoInicial;
	}
	
	public void setTiempoInicial(Long tiempoInicial){
		this.tiempoInicial = tiempoInicial;
	}
		
	public Long getTiempoFinal(){
		return this.tiempoFinal;
	}
	
	public void setTiempoFinal(Long tiempoFinal){
		this.tiempoFinal = tiempoFinal;
	}
		
	public Date getFechaInicioTranscripcion(){
		return this.fechaInicioTranscripcion;
	}
	
	public void setFechaInicioTranscripcion(Date fechaInicioTranscripcion){
		this.fechaInicioTranscripcion = fechaInicioTranscripcion;
	}
		
	public Date getFechaEntregaTranscripcion(){
		return this.fechaEntregaTranscripcion;
	}
	
	public void setFechaEntregaTranscripcion(Date fechaEntregaTranscripcion){
		this.fechaEntregaTranscripcion = fechaEntregaTranscripcion;
	}
		
	public Date getFechaPrevistaDeEntrega(){
		return this.fechaPrevistaDeEntrega;
	}
	
	public void setFechaPrevistaDeEntrega(Date fechaPrevistaDeEntrega){
		this.fechaPrevistaDeEntrega = fechaPrevistaDeEntrega;
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
		
	public Long getIdDocGenerado(){
		return this.idDocGenerado;
	}
	
	public void setIdDocGenerado(Long idDocGenerado){
		this.idDocGenerado = idDocGenerado;
	}
		
	public Long getIdDocAudioFuente(){
		return this.idDocAudioFuente;
	}
	
	public void setIdDocAudioFuente(Long idDocAudioFuente){
		this.idDocAudioFuente = idDocAudioFuente;
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
        
        hash = 37 * hash + Objects.hashCode(this.idTranscripcion);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.tiempoTranscrito);
        hash = 37 * hash + Objects.hashCode(this.tiempoInicial);
        hash = 37 * hash + Objects.hashCode(this.tiempoFinal);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaEntregaTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaPrevistaDeEntrega);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocGenerado);
        hash = 37 * hash + Objects.hashCode(this.idDocAudioFuente);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TranscripcionDTO que se pasa
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
        final TranscripcionDTO other = (TranscripcionDTO) obj;
                
        if (!Objects.equals(this.idTranscripcion, other.idTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoTranscrito, other.tiempoTranscrito)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoInicial, other.tiempoInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoFinal, other.tiempoFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioTranscripcion, other.fechaInicioTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEntregaTranscripcion, other.fechaEntregaTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPrevistaDeEntrega, other.fechaPrevistaDeEntrega)) {
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
        
        if (!Objects.equals(this.idDocGenerado, other.idDocGenerado)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocAudioFuente, other.idDocAudioFuente)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public TranscripcionDTO transformarSinDependencias(Transcripcion obj) {
		TranscripcionDTO transcripcionDTO = new TranscripcionDTO();
		
		transcripcionDTO.setIdTranscripcion(obj.getIdTranscripcion());
		transcripcionDTO.setEstado(obj.getEstado());
		transcripcionDTO.setTiempoTranscrito(obj.getTiempoTranscrito());
		transcripcionDTO.setTiempoInicial(obj.getTiempoInicial());
		transcripcionDTO.setTiempoFinal(obj.getTiempoFinal());
		transcripcionDTO.setFechaInicioTranscripcion(obj.getFechaInicioTranscripcion());
		transcripcionDTO.setFechaEntregaTranscripcion(obj.getFechaEntregaTranscripcion());
		transcripcionDTO.setFechaPrevistaDeEntrega(obj.getFechaPrevistaDeEntrega());
		transcripcionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		transcripcionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		transcripcionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		transcripcionDTO.setIdDocGenerado(obj.getIdDocGenerado());
		transcripcionDTO.setIdDocAudioFuente(obj.getIdDocAudioFuente());
		transcripcionDTO.setIdPersona(obj.getIdPersona());
		
		return transcripcionDTO;
	}

	@Override
	public TranscripcionDTO transformarConDependencias(Transcripcion obj) {
		TranscripcionDTO transcripcionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return transcripcionDTO;
	}

	@Override
	public Transcripcion transformarEntidadSinDependencias(Transcripcion obj) {
		Transcripcion transcripcion = new Transcripcion();
		
		transcripcion.setIdTranscripcion(obj.getIdTranscripcion());
		
		transcripcion.setEstado(obj.getEstado());
		transcripcion.setTiempoTranscrito(obj.getTiempoTranscrito());
		transcripcion.setTiempoInicial(obj.getTiempoInicial());
		transcripcion.setTiempoFinal(obj.getTiempoFinal());
		transcripcion.setFechaInicioTranscripcion(obj.getFechaInicioTranscripcion());
		transcripcion.setFechaEntregaTranscripcion(obj.getFechaEntregaTranscripcion());
		transcripcion.setFechaPrevistaDeEntrega(obj.getFechaPrevistaDeEntrega());
		transcripcion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		transcripcion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		transcripcion.setEstadoRegistro(obj.getEstadoRegistro());
		transcripcion.setIdDocGenerado(obj.getIdDocGenerado());
		transcripcion.setIdDocAudioFuente(obj.getIdDocAudioFuente());
		transcripcion.setIdPersona(obj.getIdPersona());
		
		return transcripcion;
	}


	@Override
	public Transcripcion transformarEntidadConDependencias(Transcripcion obj) {
		Transcripcion transcripcion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return transcripcion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Transcripcion> coleccion) {
		List<TranscripcionDTO> transcripcionDTOList = new ArrayList<>();
		for(Transcripcion c : coleccion)
			transcripcionDTOList.add(transformarConDependencias(c));
		return transcripcionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Transcripcion> coleccion) {
		List<TranscripcionDTO> transcripcionDTOList = new ArrayList<>();
		for(Transcripcion c : coleccion)
			transcripcionDTOList.add(transformarSinDependencias(c));
		return transcripcionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Transcripcion> coleccion) {
		List<Transcripcion> transcripcionList = new ArrayList<>();
		for(Transcripcion c : coleccion)
			transcripcionList.add(transformarEntidadConDependencias(c));
		return transcripcionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Transcripcion> coleccion) {
		List<Transcripcion> transcripcionList = new ArrayList<>();
		for(Transcripcion c : coleccion)
			transcripcionList.add(transformarEntidadSinDependencias(c));
		return transcripcionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
