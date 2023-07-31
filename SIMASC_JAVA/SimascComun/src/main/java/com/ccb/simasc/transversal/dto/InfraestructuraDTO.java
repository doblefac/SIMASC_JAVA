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

import com.ccb.simasc.transversal.entidades.Infraestructura;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad InfraestructuraDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class InfraestructuraDTO extends IDTO<Infraestructura> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private String codigoRecurso;

	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public InfraestructuraDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getCodigoRecurso(){
		return this.codigoRecurso;
	}
	
	public void setCodigoRecurso(String codigoRecurso){
		this.codigoRecurso = codigoRecurso;
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
        
        hash = 37 * hash + Objects.hashCode(this.codigoRecurso);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InfraestructuraDTO que se pasa
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
        final InfraestructuraDTO other = (InfraestructuraDTO) obj;
                
        if (!Objects.equals(this.codigoRecurso, other.codigoRecurso)) {
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
	public InfraestructuraDTO transformarSinDependencias(Infraestructura obj) {
		InfraestructuraDTO infraestructuraDTO = new InfraestructuraDTO();
		
		infraestructuraDTO.setCodigoRecurso(obj.getCodigoRecurso());
		infraestructuraDTO.setNombre(obj.getNombre());
		infraestructuraDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructuraDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructuraDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructuraDTO;
	}

	@Override
	public InfraestructuraDTO transformarConDependencias(Infraestructura obj) {
		InfraestructuraDTO infraestructuraDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return infraestructuraDTO;
	}

	@Override
	public Infraestructura transformarEntidadSinDependencias(Infraestructura obj) {
		Infraestructura infraestructura = new Infraestructura();
		
		infraestructura.setCodigoRecurso(obj.getCodigoRecurso());
		
		infraestructura.setNombre(obj.getNombre());
		infraestructura.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructura.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructura.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructura;
	}


	@Override
	public Infraestructura transformarEntidadConDependencias(Infraestructura obj) {
		Infraestructura infraestructura = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return infraestructura;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Infraestructura> coleccion) {
		List<InfraestructuraDTO> infraestructuraDTOList = new ArrayList<>();
		for(Infraestructura c : coleccion)
			infraestructuraDTOList.add(transformarConDependencias(c));
		return infraestructuraDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Infraestructura> coleccion) {
		List<InfraestructuraDTO> infraestructuraDTOList = new ArrayList<>();
		for(Infraestructura c : coleccion)
			infraestructuraDTOList.add(transformarSinDependencias(c));
		return infraestructuraDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Infraestructura> coleccion) {
		List<Infraestructura> infraestructuraList = new ArrayList<>();
		for(Infraestructura c : coleccion)
			infraestructuraList.add(transformarEntidadConDependencias(c));
		return infraestructuraList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Infraestructura> coleccion) {
		List<Infraestructura> infraestructuraList = new ArrayList<>();
		for(Infraestructura c : coleccion)
			infraestructuraList.add(transformarEntidadSinDependencias(c));
		return infraestructuraList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
