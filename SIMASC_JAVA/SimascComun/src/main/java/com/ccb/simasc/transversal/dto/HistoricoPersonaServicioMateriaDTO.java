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

import com.ccb.simasc.transversal.entidades.HistoricoPersonaServicioMateria;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HistoricoPersonaServicioMateriaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HistoricoPersonaServicioMateriaDTO extends IDTO<HistoricoPersonaServicioMateria> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idHistoricoPersonaServicioMateria;

	private String estadoParaSorteo;		
	private Date fechaAsignacionEstado;		
	private String observaciones;		
	private Long idPersonaServicioMateria;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public HistoricoPersonaServicioMateriaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHistoricoPersonaServicioMateria(){
		return this.idHistoricoPersonaServicioMateria;
	}
	
	public void setIdHistoricoPersonaServicioMateria(Long idHistoricoPersonaServicioMateria){
		this.idHistoricoPersonaServicioMateria = idHistoricoPersonaServicioMateria;
	}
	
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
		
	public Date getFechaAsignacionEstado(){
		return this.fechaAsignacionEstado;
	}
	
	public void setFechaAsignacionEstado(Date fechaAsignacionEstado){
		this.fechaAsignacionEstado = fechaAsignacionEstado;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdPersonaServicioMateria(){
		return this.idPersonaServicioMateria;
	}
	
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria){
		this.idPersonaServicioMateria = idPersonaServicioMateria;
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoPersonaServicioMateria);        
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.fechaAsignacionEstado);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idPersonaServicioMateria);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoPersonaServicioMateriaDTO que se pasa
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
        final HistoricoPersonaServicioMateriaDTO other = (HistoricoPersonaServicioMateriaDTO) obj;
                
        if (!Objects.equals(this.idHistoricoPersonaServicioMateria, other.idHistoricoPersonaServicioMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsignacionEstado, other.fechaAsignacionEstado)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaServicioMateria, other.idPersonaServicioMateria)) {
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
	public HistoricoPersonaServicioMateriaDTO transformarSinDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateriaDTO historicoPersonaServicioMateriaDTO = new HistoricoPersonaServicioMateriaDTO();
		
		historicoPersonaServicioMateriaDTO.setIdHistoricoPersonaServicioMateria(obj.getIdHistoricoPersonaServicioMateria());
		historicoPersonaServicioMateriaDTO.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		historicoPersonaServicioMateriaDTO.setFechaAsignacionEstado(obj.getFechaAsignacionEstado());
		historicoPersonaServicioMateriaDTO.setObservaciones(obj.getObservaciones());
		historicoPersonaServicioMateriaDTO.setIdPersonaServicioMateria(obj.getIdPersonaServicioMateria());
		historicoPersonaServicioMateriaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoPersonaServicioMateriaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoPersonaServicioMateriaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoPersonaServicioMateriaDTO;
	}

	@Override
	public HistoricoPersonaServicioMateriaDTO transformarConDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateriaDTO historicoPersonaServicioMateriaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return historicoPersonaServicioMateriaDTO;
	}

	@Override
	public HistoricoPersonaServicioMateria transformarEntidadSinDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateria historicoPersonaServicioMateria = new HistoricoPersonaServicioMateria();
		
		historicoPersonaServicioMateria.setIdHistoricoPersonaServicioMateria(obj.getIdHistoricoPersonaServicioMateria());
		
		historicoPersonaServicioMateria.setEstadoParaSorteo(obj.getEstadoParaSorteo());
		historicoPersonaServicioMateria.setFechaAsignacionEstado(obj.getFechaAsignacionEstado());
		historicoPersonaServicioMateria.setObservaciones(obj.getObservaciones());
		historicoPersonaServicioMateria.setIdPersonaServicioMateria(obj.getIdPersonaServicioMateria());
		historicoPersonaServicioMateria.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		historicoPersonaServicioMateria.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		historicoPersonaServicioMateria.setEstadoRegistro(obj.getEstadoRegistro());
		
		return historicoPersonaServicioMateria;
	}


	@Override
	public HistoricoPersonaServicioMateria transformarEntidadConDependencias(HistoricoPersonaServicioMateria obj) {
		HistoricoPersonaServicioMateria historicoPersonaServicioMateria = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return historicoPersonaServicioMateria;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HistoricoPersonaServicioMateria> coleccion) {
		List<HistoricoPersonaServicioMateriaDTO> historicoPersonaServicioMateriaDTOList = new ArrayList<>();
		for(HistoricoPersonaServicioMateria c : coleccion)
			historicoPersonaServicioMateriaDTOList.add(transformarConDependencias(c));
		return historicoPersonaServicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HistoricoPersonaServicioMateria> coleccion) {
		List<HistoricoPersonaServicioMateriaDTO> historicoPersonaServicioMateriaDTOList = new ArrayList<>();
		for(HistoricoPersonaServicioMateria c : coleccion)
			historicoPersonaServicioMateriaDTOList.add(transformarSinDependencias(c));
		return historicoPersonaServicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HistoricoPersonaServicioMateria> coleccion) {
		List<HistoricoPersonaServicioMateria> historicoPersonaServicioMateriaList = new ArrayList<>();
		for(HistoricoPersonaServicioMateria c : coleccion)
			historicoPersonaServicioMateriaList.add(transformarEntidadConDependencias(c));
		return historicoPersonaServicioMateriaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HistoricoPersonaServicioMateria> coleccion) {
		List<HistoricoPersonaServicioMateria> historicoPersonaServicioMateriaList = new ArrayList<>();
		for(HistoricoPersonaServicioMateria c : coleccion)
			historicoPersonaServicioMateriaList.add(transformarEntidadSinDependencias(c));
		return historicoPersonaServicioMateriaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
