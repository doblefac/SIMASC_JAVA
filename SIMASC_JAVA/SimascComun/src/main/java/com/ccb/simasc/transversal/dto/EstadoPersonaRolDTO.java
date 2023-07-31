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

import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRolPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EstadoPersonaRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EstadoPersonaRolDTO extends IDTO<EstadoPersonaRol> implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Date fechaAsignacion;
	// protected region atributos end
	private EstadoPersonaRolPK estadoPersonaRolPK;

	private String Motivo;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public EstadoPersonaRolDTO(){
		estadoPersonaRolPK = new EstadoPersonaRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EstadoPersonaRolPK getEstadoPersonaRolPK(){
		return this.estadoPersonaRolPK;
	}
	
	public void setEstadoPersonaRolPK(EstadoPersonaRolPK estadoPersonaRolPK){
		this.estadoPersonaRolPK   = estadoPersonaRolPK ;
	}  
	
	public String getMotivo(){
		return this.Motivo;
	}
	
	public void setIdMotivo(String Motivo){
		this.Motivo = Motivo;
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
        
        hash = 37 * hash + Objects.hashCode(this.estadoPersonaRolPK);        
        hash = 37 * hash + Objects.hashCode(this.Motivo);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoPersonaRolDTO que se pasa
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
        final EstadoPersonaRolDTO other = (EstadoPersonaRolDTO) obj;
                
        if (!Objects.equals(this.estadoPersonaRolPK, other.estadoPersonaRolPK)) {
            return false;
        }
        
        if (!Objects.equals(this.Motivo, other.Motivo)) {
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
	public EstadoPersonaRolDTO transformarSinDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRolDTO estadoPersonaRolDTO = new EstadoPersonaRolDTO();
		
		estadoPersonaRolDTO.setEstadoPersonaRolPK(obj.getEstadoPersonaRolPK());
		estadoPersonaRolDTO.setIdMotivo(obj.getIdMotivo());
		estadoPersonaRolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		estadoPersonaRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		estadoPersonaRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return estadoPersonaRolDTO;
	}

	@Override
	public EstadoPersonaRolDTO transformarConDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRolDTO estadoPersonaRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return estadoPersonaRolDTO;
	}

	@Override
	public EstadoPersonaRol transformarEntidadSinDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRol estadoPersonaRol = new EstadoPersonaRol();
		
		estadoPersonaRol.setEstadoPersonaRolPK(obj.getEstadoPersonaRolPK());
		
		estadoPersonaRol.setIdMotivo(obj.getIdMotivo());
		estadoPersonaRol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		estadoPersonaRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		estadoPersonaRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return estadoPersonaRol;
	}


	@Override
	public EstadoPersonaRol transformarEntidadConDependencias(EstadoPersonaRol obj) {
		EstadoPersonaRol estadoPersonaRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return estadoPersonaRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EstadoPersonaRol> coleccion) {
		List<EstadoPersonaRolDTO> estadoPersonaRolDTOList = new ArrayList<>();
		for(EstadoPersonaRol c : coleccion)
			estadoPersonaRolDTOList.add(transformarConDependencias(c));
		return estadoPersonaRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EstadoPersonaRol> coleccion) {
		List<EstadoPersonaRolDTO> estadoPersonaRolDTOList = new ArrayList<>();
		for(EstadoPersonaRol c : coleccion)
			estadoPersonaRolDTOList.add(transformarSinDependencias(c));
		return estadoPersonaRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EstadoPersonaRol> coleccion) {
		List<EstadoPersonaRol> estadoPersonaRolList = new ArrayList<>();
		for(EstadoPersonaRol c : coleccion)
			estadoPersonaRolList.add(transformarEntidadConDependencias(c));
		return estadoPersonaRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EstadoPersonaRol> coleccion) {
		List<EstadoPersonaRol> estadoPersonaRolList = new ArrayList<>();
		for(EstadoPersonaRol c : coleccion)
			estadoPersonaRolList.add(transformarEntidadSinDependencias(c));
		return estadoPersonaRolList;
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
