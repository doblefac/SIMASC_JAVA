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

import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.AgrupamientoRolPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AgrupamientoRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AgrupamientoRolDTO extends IDTO<AgrupamientoRol> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private AgrupamientoRolPK agrupamientoRolPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AgrupamientoRolDTO(){
		agrupamientoRolPK = new AgrupamientoRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public AgrupamientoRolPK getAgrupamientoRolPK(){
		return this.agrupamientoRolPK;
	}
	
	public void setAgrupamientoRolPK(AgrupamientoRolPK agrupamientoRolPK){
		this.agrupamientoRolPK   = agrupamientoRolPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.agrupamientoRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AgrupamientoRolDTO que se pasa
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
        final AgrupamientoRolDTO other = (AgrupamientoRolDTO) obj;
                
        if (!Objects.equals(this.agrupamientoRolPK, other.agrupamientoRolPK)) {
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
	public AgrupamientoRolDTO transformarSinDependencias(AgrupamientoRol obj) {
		AgrupamientoRolDTO agrupamientoRolDTO = new AgrupamientoRolDTO();
		
		agrupamientoRolDTO.setAgrupamientoRolPK(obj.getAgrupamientoRolPK());
		agrupamientoRolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agrupamientoRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agrupamientoRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return agrupamientoRolDTO;
	}

	@Override
	public AgrupamientoRolDTO transformarConDependencias(AgrupamientoRol obj) {
		AgrupamientoRolDTO agrupamientoRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return agrupamientoRolDTO;
	}

	@Override
	public AgrupamientoRol transformarEntidadSinDependencias(AgrupamientoRol obj) {
		AgrupamientoRol agrupamientoRol = new AgrupamientoRol();
		
		agrupamientoRol.setAgrupamientoRolPK(obj.getAgrupamientoRolPK());
		
		agrupamientoRol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agrupamientoRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agrupamientoRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return agrupamientoRol;
	}


	@Override
	public AgrupamientoRol transformarEntidadConDependencias(AgrupamientoRol obj) {
		AgrupamientoRol agrupamientoRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return agrupamientoRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AgrupamientoRol> coleccion) {
		List<AgrupamientoRolDTO> agrupamientoRolDTOList = new ArrayList<>();
		for(AgrupamientoRol c : coleccion)
			agrupamientoRolDTOList.add(transformarConDependencias(c));
		return agrupamientoRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AgrupamientoRol> coleccion) {
		List<AgrupamientoRolDTO> agrupamientoRolDTOList = new ArrayList<>();
		for(AgrupamientoRol c : coleccion)
			agrupamientoRolDTOList.add(transformarSinDependencias(c));
		return agrupamientoRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AgrupamientoRol> coleccion) {
		List<AgrupamientoRol> agrupamientoRolList = new ArrayList<>();
		for(AgrupamientoRol c : coleccion)
			agrupamientoRolList.add(transformarEntidadConDependencias(c));
		return agrupamientoRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AgrupamientoRol> coleccion) {
		List<AgrupamientoRol> agrupamientoRolList = new ArrayList<>();
		for(AgrupamientoRol c : coleccion)
			agrupamientoRolList.add(transformarEntidadSinDependencias(c));
		return agrupamientoRolList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
