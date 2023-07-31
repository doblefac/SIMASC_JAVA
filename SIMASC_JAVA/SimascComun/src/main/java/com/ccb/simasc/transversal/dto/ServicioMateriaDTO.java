package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.ServicioMateria;
import com.ccb.simasc.transversal.entidades.ServicioMateriaPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ServicioMateriaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ServicioMateriaDTO extends IDTO<ServicioMateria> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private ServicioMateriaPK servicioMateriaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ServicioMateriaDTO(){
		servicioMateriaPK = new ServicioMateriaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ServicioMateriaPK getServicioMateriaPK(){
		return this.servicioMateriaPK;
	}
	
	public void setServicioMateriaPK(ServicioMateriaPK servicioMateriaPK){
		this.servicioMateriaPK   = servicioMateriaPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.servicioMateriaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioMateriaDTO que se pasa
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
        final ServicioMateriaDTO other = (ServicioMateriaDTO) obj;
                
        if (!Objects.equals(this.servicioMateriaPK, other.servicioMateriaPK)) {
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
	public ServicioMateriaDTO transformarSinDependencias(ServicioMateria obj) {
		ServicioMateriaDTO servicioMateriaDTO = new ServicioMateriaDTO();
		
		servicioMateriaDTO.setServicioMateriaPK(obj.getServicioMateriaPK());
		servicioMateriaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioMateriaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioMateriaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioMateriaDTO;
	}

	@Override
	public ServicioMateriaDTO transformarConDependencias(ServicioMateria obj) {
		ServicioMateriaDTO servicioMateriaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return servicioMateriaDTO;
	}

	@Override
	public ServicioMateria transformarEntidadSinDependencias(ServicioMateria obj) {
		ServicioMateria servicioMateria = new ServicioMateria();
		
		servicioMateria.setServicioMateriaPK(obj.getServicioMateriaPK());
		
		servicioMateria.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioMateria.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioMateria.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioMateria;
	}


	@Override
	public ServicioMateria transformarEntidadConDependencias(ServicioMateria obj) {
		ServicioMateria servicioMateria = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return servicioMateria;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ServicioMateria> coleccion) {
		List<ServicioMateriaDTO> servicioMateriaDTOList = new ArrayList<>();
		for(ServicioMateria c : coleccion)
			servicioMateriaDTOList.add(transformarConDependencias(c));
		return servicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ServicioMateria> coleccion) {
		List<ServicioMateriaDTO> servicioMateriaDTOList = new ArrayList<>();
		for(ServicioMateria c : coleccion)
			servicioMateriaDTOList.add(transformarSinDependencias(c));
		return servicioMateriaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ServicioMateria> coleccion) {
		List<ServicioMateria> servicioMateriaList = new ArrayList<>();
		for(ServicioMateria c : coleccion)
			servicioMateriaList.add(transformarEntidadConDependencias(c));
		return servicioMateriaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ServicioMateria> coleccion) {
		List<ServicioMateria> servicioMateriaList = new ArrayList<>();
		for(ServicioMateria c : coleccion)
			servicioMateriaList.add(transformarEntidadSinDependencias(c));
		return servicioMateriaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
