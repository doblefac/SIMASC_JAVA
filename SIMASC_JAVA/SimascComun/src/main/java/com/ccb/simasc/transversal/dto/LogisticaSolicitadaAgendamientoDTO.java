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

import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.LogisticaSolicitadaAgendamientoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad LogisticaSolicitadaAgendamientoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class LogisticaSolicitadaAgendamientoDTO extends IDTO<LogisticaSolicitadaAgendamiento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private String nombreLogistica;
	private String codigoLogistica;
	
	
	// protected region atributos end
	private LogisticaSolicitadaAgendamientoPK logisticaSolicitadaAgendamientoPK;

	private Integer cantidad;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public LogisticaSolicitadaAgendamientoDTO(){
		logisticaSolicitadaAgendamientoPK = new LogisticaSolicitadaAgendamientoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public LogisticaSolicitadaAgendamientoPK getLogisticaSolicitadaAgendamientoPK(){
		return this.logisticaSolicitadaAgendamientoPK;
	}
	
	public void setLogisticaSolicitadaAgendamientoPK(LogisticaSolicitadaAgendamientoPK logisticaSolicitadaAgendamientoPK){
		this.logisticaSolicitadaAgendamientoPK   = logisticaSolicitadaAgendamientoPK ;
	}  
	
	public Integer getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
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
        
        hash = 37 * hash + Objects.hashCode(this.logisticaSolicitadaAgendamientoPK);        
        hash = 37 * hash + Objects.hashCode(this.cantidad);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LogisticaSolicitadaAgendamientoDTO que se pasa
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
        final LogisticaSolicitadaAgendamientoDTO other = (LogisticaSolicitadaAgendamientoDTO) obj;
                
        if (!Objects.equals(this.logisticaSolicitadaAgendamientoPK, other.logisticaSolicitadaAgendamientoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidad, other.cantidad)) {
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
	public LogisticaSolicitadaAgendamientoDTO transformarSinDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamientoDTO logisticaSolicitadaAgendamientoDTO = new LogisticaSolicitadaAgendamientoDTO();
		
		logisticaSolicitadaAgendamientoDTO.setLogisticaSolicitadaAgendamientoPK(obj.getLogisticaSolicitadaAgendamientoPK());
		logisticaSolicitadaAgendamientoDTO.setCantidad(obj.getCantidad());
		logisticaSolicitadaAgendamientoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		logisticaSolicitadaAgendamientoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		logisticaSolicitadaAgendamientoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return logisticaSolicitadaAgendamientoDTO;
	}

	@Override
	public LogisticaSolicitadaAgendamientoDTO transformarConDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamientoDTO logisticaSolicitadaAgendamientoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return logisticaSolicitadaAgendamientoDTO;
	}

	@Override
	public LogisticaSolicitadaAgendamiento transformarEntidadSinDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamiento logisticaSolicitadaAgendamiento = new LogisticaSolicitadaAgendamiento();
		
		logisticaSolicitadaAgendamiento.setLogisticaSolicitadaAgendamientoPK(obj.getLogisticaSolicitadaAgendamientoPK());
		
		logisticaSolicitadaAgendamiento.setCantidad(obj.getCantidad());
		logisticaSolicitadaAgendamiento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		logisticaSolicitadaAgendamiento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		logisticaSolicitadaAgendamiento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return logisticaSolicitadaAgendamiento;
	}


	@Override
	public LogisticaSolicitadaAgendamiento transformarEntidadConDependencias(LogisticaSolicitadaAgendamiento obj) {
		LogisticaSolicitadaAgendamiento logisticaSolicitadaAgendamiento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return logisticaSolicitadaAgendamiento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<LogisticaSolicitadaAgendamiento> coleccion) {
		List<LogisticaSolicitadaAgendamientoDTO> logisticaSolicitadaAgendamientoDTOList = new ArrayList<>();
		for(LogisticaSolicitadaAgendamiento c : coleccion)
			logisticaSolicitadaAgendamientoDTOList.add(transformarConDependencias(c));
		return logisticaSolicitadaAgendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<LogisticaSolicitadaAgendamiento> coleccion) {
		List<LogisticaSolicitadaAgendamientoDTO> logisticaSolicitadaAgendamientoDTOList = new ArrayList<>();
		for(LogisticaSolicitadaAgendamiento c : coleccion)
			logisticaSolicitadaAgendamientoDTOList.add(transformarSinDependencias(c));
		return logisticaSolicitadaAgendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<LogisticaSolicitadaAgendamiento> coleccion) {
		List<LogisticaSolicitadaAgendamiento> logisticaSolicitadaAgendamientoList = new ArrayList<>();
		for(LogisticaSolicitadaAgendamiento c : coleccion)
			logisticaSolicitadaAgendamientoList.add(transformarEntidadConDependencias(c));
		return logisticaSolicitadaAgendamientoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<LogisticaSolicitadaAgendamiento> coleccion) {
		List<LogisticaSolicitadaAgendamiento> logisticaSolicitadaAgendamientoList = new ArrayList<>();
		for(LogisticaSolicitadaAgendamiento c : coleccion)
			logisticaSolicitadaAgendamientoList.add(transformarEntidadSinDependencias(c));
		return logisticaSolicitadaAgendamientoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones



	public String getNombreLogistica() {
		return nombreLogistica;
	}


	public void setNombreLogistica(String nombreLogistica) {
		this.nombreLogistica = nombreLogistica;
	}


	public String getCodigoLogistica() {
		return codigoLogistica;
	}


	public void setCodigoLogistica(String codigoLogistica) {
		this.codigoLogistica = codigoLogistica;
	}
	
	
	// protected region metodos adicionales end

}
