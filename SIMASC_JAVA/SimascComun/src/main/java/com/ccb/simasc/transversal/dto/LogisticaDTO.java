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

import com.ccb.simasc.transversal.entidades.Logistica;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad LogisticaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class LogisticaDTO extends IDTO<Logistica> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Integer cantidad;
	// protected region atributos end
	private String codigoLogistica;

	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public LogisticaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getCodigoLogistica(){
		return this.codigoLogistica;
	}
	
	public void setCodigoLogistica(String codigoLogistica){
		this.codigoLogistica = codigoLogistica;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
        
        hash = 37 * hash + Objects.hashCode(this.codigoLogistica);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LogisticaDTO que se pasa
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
        final LogisticaDTO other = (LogisticaDTO) obj;
                
        if (!Objects.equals(this.codigoLogistica, other.codigoLogistica)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
	public LogisticaDTO transformarSinDependencias(Logistica obj) {
		LogisticaDTO logisticaDTO = new LogisticaDTO();
		
		logisticaDTO.setCodigoLogistica(obj.getCodigoLogistica());
		logisticaDTO.setNombre(obj.getNombre());
		logisticaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		logisticaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		logisticaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return logisticaDTO;
	}

	@Override
	public LogisticaDTO transformarConDependencias(Logistica obj) {
		LogisticaDTO logisticaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return logisticaDTO;
	}

	@Override
	public Logistica transformarEntidadSinDependencias(Logistica obj) {
		Logistica logistica = new Logistica();
		
		logistica.setCodigoLogistica(obj.getCodigoLogistica());
		
		logistica.setNombre(obj.getNombre());
		logistica.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		logistica.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		logistica.setEstadoRegistro(obj.getEstadoRegistro());
		
		return logistica;
	}


	@Override
	public Logistica transformarEntidadConDependencias(Logistica obj) {
		Logistica logistica = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return logistica;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Logistica> coleccion) {
		List<LogisticaDTO> logisticaDTOList = new ArrayList<>();
		for(Logistica c : coleccion)
			logisticaDTOList.add(transformarConDependencias(c));
		return logisticaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Logistica> coleccion) {
		List<LogisticaDTO> logisticaDTOList = new ArrayList<>();
		for(Logistica c : coleccion)
			logisticaDTOList.add(transformarSinDependencias(c));
		return logisticaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Logistica> coleccion) {
		List<Logistica> logisticaList = new ArrayList<>();
		for(Logistica c : coleccion)
			logisticaList.add(transformarEntidadConDependencias(c));
		return logisticaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Logistica> coleccion) {
		List<Logistica> logisticaList = new ArrayList<>();
		for(Logistica c : coleccion)
			logisticaList.add(transformarEntidadSinDependencias(c));
		return logisticaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	// protected region metodos adicionales end

}
