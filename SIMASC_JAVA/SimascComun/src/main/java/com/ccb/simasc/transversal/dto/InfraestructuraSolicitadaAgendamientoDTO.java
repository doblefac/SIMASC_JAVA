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

import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamiento;
import com.ccb.simasc.transversal.entidades.InfraestructuraSolicitadaAgendamientoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad InfraestructuraSolicitadaAgendamientoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class InfraestructuraSolicitadaAgendamientoDTO extends IDTO<InfraestructuraSolicitadaAgendamiento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private String nombreInfraestructura;
	private String codigoInfraestructura;
	// protected region atributos end
	private InfraestructuraSolicitadaAgendamientoPK infraestructuraSolicitadaAgendamientoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public InfraestructuraSolicitadaAgendamientoDTO(){
		infraestructuraSolicitadaAgendamientoPK = new InfraestructuraSolicitadaAgendamientoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public InfraestructuraSolicitadaAgendamientoPK getInfraestructuraSolicitadaAgendamientoPK(){
		return this.infraestructuraSolicitadaAgendamientoPK;
	}
	
	public void setInfraestructuraSolicitadaAgendamientoPK(InfraestructuraSolicitadaAgendamientoPK infraestructuraSolicitadaAgendamientoPK){
		this.infraestructuraSolicitadaAgendamientoPK   = infraestructuraSolicitadaAgendamientoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.infraestructuraSolicitadaAgendamientoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InfraestructuraSolicitadaAgendamientoDTO que se pasa
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
        final InfraestructuraSolicitadaAgendamientoDTO other = (InfraestructuraSolicitadaAgendamientoDTO) obj;
                
        if (!Objects.equals(this.infraestructuraSolicitadaAgendamientoPK, other.infraestructuraSolicitadaAgendamientoPK)) {
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
	public InfraestructuraSolicitadaAgendamientoDTO transformarSinDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamientoDTO infraestructuraSolicitadaAgendamientoDTO = new InfraestructuraSolicitadaAgendamientoDTO();
		
		infraestructuraSolicitadaAgendamientoDTO.setInfraestructuraSolicitadaAgendamientoPK(obj.getInfraestructuraSolicitadaAgendamientoPK());
		infraestructuraSolicitadaAgendamientoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructuraSolicitadaAgendamientoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructuraSolicitadaAgendamientoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructuraSolicitadaAgendamientoDTO;
	}

	@Override
	public InfraestructuraSolicitadaAgendamientoDTO transformarConDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamientoDTO infraestructuraSolicitadaAgendamientoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return infraestructuraSolicitadaAgendamientoDTO;
	}

	@Override
	public InfraestructuraSolicitadaAgendamiento transformarEntidadSinDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamiento infraestructuraSolicitadaAgendamiento = new InfraestructuraSolicitadaAgendamiento();
		
		infraestructuraSolicitadaAgendamiento.setInfraestructuraSolicitadaAgendamientoPK(obj.getInfraestructuraSolicitadaAgendamientoPK());
		
		infraestructuraSolicitadaAgendamiento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructuraSolicitadaAgendamiento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructuraSolicitadaAgendamiento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructuraSolicitadaAgendamiento;
	}


	@Override
	public InfraestructuraSolicitadaAgendamiento transformarEntidadConDependencias(InfraestructuraSolicitadaAgendamiento obj) {
		InfraestructuraSolicitadaAgendamiento infraestructuraSolicitadaAgendamiento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return infraestructuraSolicitadaAgendamiento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<InfraestructuraSolicitadaAgendamiento> coleccion) {
		List<InfraestructuraSolicitadaAgendamientoDTO> infraestructuraSolicitadaAgendamientoDTOList = new ArrayList<>();
		for(InfraestructuraSolicitadaAgendamiento c : coleccion)
			infraestructuraSolicitadaAgendamientoDTOList.add(transformarConDependencias(c));
		return infraestructuraSolicitadaAgendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<InfraestructuraSolicitadaAgendamiento> coleccion) {
		List<InfraestructuraSolicitadaAgendamientoDTO> infraestructuraSolicitadaAgendamientoDTOList = new ArrayList<>();
		for(InfraestructuraSolicitadaAgendamiento c : coleccion)
			infraestructuraSolicitadaAgendamientoDTOList.add(transformarSinDependencias(c));
		return infraestructuraSolicitadaAgendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<InfraestructuraSolicitadaAgendamiento> coleccion) {
		List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList = new ArrayList<>();
		for(InfraestructuraSolicitadaAgendamiento c : coleccion)
			infraestructuraSolicitadaAgendamientoList.add(transformarEntidadConDependencias(c));
		return infraestructuraSolicitadaAgendamientoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<InfraestructuraSolicitadaAgendamiento> coleccion) {
		List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList = new ArrayList<>();
		for(InfraestructuraSolicitadaAgendamiento c : coleccion)
			infraestructuraSolicitadaAgendamientoList.add(transformarEntidadSinDependencias(c));
		return infraestructuraSolicitadaAgendamientoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	public String getNombreInfraestructura() {
		return nombreInfraestructura;
	}


	public void setNombreInfraestructura(String nombreInfraestructura) {
		this.nombreInfraestructura = nombreInfraestructura;
	}


	public String getCodigoInfraestructura() {
		return codigoInfraestructura;
	}


	public void setCodigoInfraestructura(String codigoInfraestructura) {
		this.codigoInfraestructura = codigoInfraestructura;
	}
	

	// protected region metodos adicionales end

}
