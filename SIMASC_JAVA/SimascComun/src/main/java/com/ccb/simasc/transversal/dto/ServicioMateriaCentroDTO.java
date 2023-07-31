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

import com.ccb.simasc.transversal.entidades.ServicioMateriaCentro;
import com.ccb.simasc.transversal.entidades.ServicioMateriaCentroPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ServicioMateriaCentroDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ServicioMateriaCentroDTO extends IDTO<ServicioMateriaCentro> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ServicioMateriaCentroPK servicioMateriaCentroPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ServicioMateriaCentroDTO(){
		servicioMateriaCentroPK = new ServicioMateriaCentroPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ServicioMateriaCentroPK getServicioMateriaCentroPK(){
		return this.servicioMateriaCentroPK;
	}
	
	public void setServicioMateriaCentroPK(ServicioMateriaCentroPK servicioMateriaCentroPK){
		this.servicioMateriaCentroPK   = servicioMateriaCentroPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.servicioMateriaCentroPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioMateriaCentroDTO que se pasa
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
        final ServicioMateriaCentroDTO other = (ServicioMateriaCentroDTO) obj;
                
        if (!Objects.equals(this.servicioMateriaCentroPK, other.servicioMateriaCentroPK)) {
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
	public ServicioMateriaCentroDTO transformarSinDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentroDTO servicioMateriaCentroDTO = new ServicioMateriaCentroDTO();
		
		servicioMateriaCentroDTO.setServicioMateriaCentroPK(obj.getServicioMateriaCentroPK());
		servicioMateriaCentroDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioMateriaCentroDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioMateriaCentroDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioMateriaCentroDTO;
	}

	@Override
	public ServicioMateriaCentroDTO transformarConDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentroDTO servicioMateriaCentroDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return servicioMateriaCentroDTO;
	}

	@Override
	public ServicioMateriaCentro transformarEntidadSinDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentro servicioMateriaCentro = new ServicioMateriaCentro();
		
		servicioMateriaCentro.setServicioMateriaCentroPK(obj.getServicioMateriaCentroPK());
		
		servicioMateriaCentro.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioMateriaCentro.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioMateriaCentro.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioMateriaCentro;
	}


	@Override
	public ServicioMateriaCentro transformarEntidadConDependencias(ServicioMateriaCentro obj) {
		ServicioMateriaCentro servicioMateriaCentro = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return servicioMateriaCentro;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ServicioMateriaCentro> coleccion) {
		List<ServicioMateriaCentroDTO> servicioMateriaCentroDTOList = new ArrayList<>();
		for(ServicioMateriaCentro c : coleccion)
			servicioMateriaCentroDTOList.add(transformarConDependencias(c));
		return servicioMateriaCentroDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ServicioMateriaCentro> coleccion) {
		List<ServicioMateriaCentroDTO> servicioMateriaCentroDTOList = new ArrayList<>();
		for(ServicioMateriaCentro c : coleccion)
			servicioMateriaCentroDTOList.add(transformarSinDependencias(c));
		return servicioMateriaCentroDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ServicioMateriaCentro> coleccion) {
		List<ServicioMateriaCentro> servicioMateriaCentroList = new ArrayList<>();
		for(ServicioMateriaCentro c : coleccion)
			servicioMateriaCentroList.add(transformarEntidadConDependencias(c));
		return servicioMateriaCentroList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ServicioMateriaCentro> coleccion) {
		List<ServicioMateriaCentro> servicioMateriaCentroList = new ArrayList<>();
		for(ServicioMateriaCentro c : coleccion)
			servicioMateriaCentroList.add(transformarEntidadSinDependencias(c));
		return servicioMateriaCentroList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
