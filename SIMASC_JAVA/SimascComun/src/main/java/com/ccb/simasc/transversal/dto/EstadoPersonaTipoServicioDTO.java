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

import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicio;
import com.ccb.simasc.transversal.entidades.EstadoPersonaTipoServicioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EstadoPersonaTipoServicioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EstadoPersonaTipoServicioDTO extends IDTO<EstadoPersonaTipoServicio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Date fechaAsignacion;
	// protected region atributos end
	private EstadoPersonaTipoServicioPK estadoPersonaTipoServicioPK;

	private String estado;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public EstadoPersonaTipoServicioDTO(){
		estadoPersonaTipoServicioPK = new EstadoPersonaTipoServicioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EstadoPersonaTipoServicioPK getEstadoPersonaTipoServicioPK(){
		return this.estadoPersonaTipoServicioPK;
	}
	
	public void setEstadoPersonaTipoServicioPK(EstadoPersonaTipoServicioPK estadoPersonaTipoServicioPK){
		this.estadoPersonaTipoServicioPK   = estadoPersonaTipoServicioPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
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
        
        hash = 37 * hash + Objects.hashCode(this.estadoPersonaTipoServicioPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoPersonaTipoServicioDTO que se pasa
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
        final EstadoPersonaTipoServicioDTO other = (EstadoPersonaTipoServicioDTO) obj;
                
        if (!Objects.equals(this.estadoPersonaTipoServicioPK, other.estadoPersonaTipoServicioPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
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
	public EstadoPersonaTipoServicioDTO transformarSinDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicioDTO estadoPersonaTipoServicioDTO = new EstadoPersonaTipoServicioDTO();
		
		estadoPersonaTipoServicioDTO.setEstadoPersonaTipoServicioPK(obj.getEstadoPersonaTipoServicioPK());
		estadoPersonaTipoServicioDTO.setEstado(obj.getEstado());
		estadoPersonaTipoServicioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		estadoPersonaTipoServicioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		estadoPersonaTipoServicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return estadoPersonaTipoServicioDTO;
	}

	@Override
	public EstadoPersonaTipoServicioDTO transformarConDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicioDTO estadoPersonaTipoServicioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return estadoPersonaTipoServicioDTO;
	}

	@Override
	public EstadoPersonaTipoServicio transformarEntidadSinDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicio estadoPersonaTipoServicio = new EstadoPersonaTipoServicio();
		
		estadoPersonaTipoServicio.setEstadoPersonaTipoServicioPK(obj.getEstadoPersonaTipoServicioPK());
		
		estadoPersonaTipoServicio.setEstado(obj.getEstado());
		estadoPersonaTipoServicio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		estadoPersonaTipoServicio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		estadoPersonaTipoServicio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return estadoPersonaTipoServicio;
	}


	@Override
	public EstadoPersonaTipoServicio transformarEntidadConDependencias(EstadoPersonaTipoServicio obj) {
		EstadoPersonaTipoServicio estadoPersonaTipoServicio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return estadoPersonaTipoServicio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EstadoPersonaTipoServicio> coleccion) {
		List<EstadoPersonaTipoServicioDTO> estadoPersonaTipoServicioDTOList = new ArrayList<>();
		for(EstadoPersonaTipoServicio c : coleccion)
			estadoPersonaTipoServicioDTOList.add(transformarConDependencias(c));
		return estadoPersonaTipoServicioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EstadoPersonaTipoServicio> coleccion) {
		List<EstadoPersonaTipoServicioDTO> estadoPersonaTipoServicioDTOList = new ArrayList<>();
		for(EstadoPersonaTipoServicio c : coleccion)
			estadoPersonaTipoServicioDTOList.add(transformarSinDependencias(c));
		return estadoPersonaTipoServicioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EstadoPersonaTipoServicio> coleccion) {
		List<EstadoPersonaTipoServicio> estadoPersonaTipoServicioList = new ArrayList<>();
		for(EstadoPersonaTipoServicio c : coleccion)
			estadoPersonaTipoServicioList.add(transformarEntidadConDependencias(c));
		return estadoPersonaTipoServicioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EstadoPersonaTipoServicio> coleccion) {
		List<EstadoPersonaTipoServicio> estadoPersonaTipoServicioList = new ArrayList<>();
		for(EstadoPersonaTipoServicio c : coleccion)
			estadoPersonaTipoServicioList.add(transformarEntidadSinDependencias(c));
		return estadoPersonaTipoServicioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}


	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	
	
	// protected region metodos adicionales end

}
