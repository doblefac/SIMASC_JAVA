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

import com.ccb.simasc.transversal.entidades.Membresia;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad MembresiaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class MembresiaDTO extends IDTO<Membresia> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idMembresia;

	private Date fechaInicio;		
	private Date fechaFin;		
	private String numeroReciboPago;		
	private Long idPersona;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public MembresiaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdMembresia(){
		return this.idMembresia;
	}
	
	public void setIdMembresia(Long idMembresia){
		this.idMembresia = idMembresia;
	}
	
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public String getNumeroReciboPago(){
		return this.numeroReciboPago;
	}
	
	public void setNumeroReciboPago(String numeroReciboPago){
		this.numeroReciboPago = numeroReciboPago;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
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
        
        hash = 37 * hash + Objects.hashCode(this.idMembresia);        
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.numeroReciboPago);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MembresiaDTO que se pasa
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
        final MembresiaDTO other = (MembresiaDTO) obj;
                
        if (!Objects.equals(this.idMembresia, other.idMembresia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroReciboPago, other.numeroReciboPago)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
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
	public MembresiaDTO transformarSinDependencias(Membresia obj) {
		MembresiaDTO membresiaDTO = new MembresiaDTO();
		
		membresiaDTO.setIdMembresia(obj.getIdMembresia());
		membresiaDTO.setFechaInicio(obj.getFechaInicio());
		membresiaDTO.setFechaFin(obj.getFechaFin());
		membresiaDTO.setNumeroReciboPago(obj.getNumeroReciboPago());
		membresiaDTO.setIdPersona(obj.getIdPersona());
		membresiaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		membresiaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		membresiaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return membresiaDTO;
	}

	@Override
	public MembresiaDTO transformarConDependencias(Membresia obj) {
		MembresiaDTO membresiaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return membresiaDTO;
	}

	@Override
	public Membresia transformarEntidadSinDependencias(Membresia obj) {
		Membresia membresia = new Membresia();
		
		membresia.setIdMembresia(obj.getIdMembresia());
		
		membresia.setFechaInicio(obj.getFechaInicio());
		membresia.setFechaFin(obj.getFechaFin());
		membresia.setNumeroReciboPago(obj.getNumeroReciboPago());
		membresia.setIdPersona(obj.getIdPersona());
		membresia.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		membresia.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		membresia.setEstadoRegistro(obj.getEstadoRegistro());
		
		return membresia;
	}


	@Override
	public Membresia transformarEntidadConDependencias(Membresia obj) {
		Membresia membresia = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return membresia;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Membresia> coleccion) {
		List<MembresiaDTO> membresiaDTOList = new ArrayList<>();
		for(Membresia c : coleccion)
			membresiaDTOList.add(transformarConDependencias(c));
		return membresiaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Membresia> coleccion) {
		List<MembresiaDTO> membresiaDTOList = new ArrayList<>();
		for(Membresia c : coleccion)
			membresiaDTOList.add(transformarSinDependencias(c));
		return membresiaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Membresia> coleccion) {
		List<Membresia> membresiaList = new ArrayList<>();
		for(Membresia c : coleccion)
			membresiaList.add(transformarEntidadConDependencias(c));
		return membresiaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Membresia> coleccion) {
		List<Membresia> membresiaList = new ArrayList<>();
		for(Membresia c : coleccion)
			membresiaList.add(transformarEntidadSinDependencias(c));
		return membresiaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
