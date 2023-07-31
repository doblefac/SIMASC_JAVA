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

import com.ccb.simasc.transversal.entidades.Adjunto;
import com.ccb.simasc.transversal.entidades.AdjuntoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AdjuntoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AdjuntoDTO extends IDTO<Adjunto> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private AdjuntoPK adjuntoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AdjuntoDTO(){
		adjuntoPK = new AdjuntoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public AdjuntoPK getAdjuntoPK(){
		return this.adjuntoPK;
	}
	
	public void setAdjuntoPK(AdjuntoPK adjuntoPK){
		this.adjuntoPK   = adjuntoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.adjuntoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AdjuntoDTO que se pasa
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
        final AdjuntoDTO other = (AdjuntoDTO) obj;
                
        if (!Objects.equals(this.adjuntoPK, other.adjuntoPK)) {
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
	public AdjuntoDTO transformarSinDependencias(Adjunto obj) {
		AdjuntoDTO adjuntoDTO = new AdjuntoDTO();
		
		adjuntoDTO.setAdjuntoPK(obj.getAdjuntoPK());
		adjuntoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		adjuntoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		adjuntoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return adjuntoDTO;
	}

	@Override
	public AdjuntoDTO transformarConDependencias(Adjunto obj) {
		AdjuntoDTO adjuntoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return adjuntoDTO;
	}

	@Override
	public Adjunto transformarEntidadSinDependencias(Adjunto obj) {
		Adjunto adjunto = new Adjunto();
		
		adjunto.setAdjuntoPK(obj.getAdjuntoPK());
		
		adjunto.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		adjunto.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		adjunto.setEstadoRegistro(obj.getEstadoRegistro());
		
		return adjunto;
	}


	@Override
	public Adjunto transformarEntidadConDependencias(Adjunto obj) {
		Adjunto adjunto = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return adjunto;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Adjunto> coleccion) {
		List<AdjuntoDTO> adjuntoDTOList = new ArrayList<>();
		for(Adjunto c : coleccion)
			adjuntoDTOList.add(transformarConDependencias(c));
		return adjuntoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Adjunto> coleccion) {
		List<AdjuntoDTO> adjuntoDTOList = new ArrayList<>();
		for(Adjunto c : coleccion)
			adjuntoDTOList.add(transformarSinDependencias(c));
		return adjuntoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Adjunto> coleccion) {
		List<Adjunto> adjuntoList = new ArrayList<>();
		for(Adjunto c : coleccion)
			adjuntoList.add(transformarEntidadConDependencias(c));
		return adjuntoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Adjunto> coleccion) {
		List<Adjunto> adjuntoList = new ArrayList<>();
		for(Adjunto c : coleccion)
			adjuntoList.add(transformarEntidadSinDependencias(c));
		return adjuntoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
