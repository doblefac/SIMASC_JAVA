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

import com.ccb.simasc.transversal.entidades.ServicioSede;
import com.ccb.simasc.transversal.entidades.ServicioSedePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ServicioSedeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ServicioSedeDTO extends IDTO<ServicioSede> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ServicioSedePK servicioSedePK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ServicioSedeDTO(){
		servicioSedePK = new ServicioSedePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ServicioSedePK getServicioSedePK(){
		return this.servicioSedePK;
	}
	
	public void setServicioSedePK(ServicioSedePK servicioSedePK){
		this.servicioSedePK   = servicioSedePK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.servicioSedePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioSedeDTO que se pasa
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
        final ServicioSedeDTO other = (ServicioSedeDTO) obj;
                
        if (!Objects.equals(this.servicioSedePK, other.servicioSedePK)) {
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
	public ServicioSedeDTO transformarSinDependencias(ServicioSede obj) {
		ServicioSedeDTO servicioSedeDTO = new ServicioSedeDTO();
		
		servicioSedeDTO.setServicioSedePK(obj.getServicioSedePK());
		servicioSedeDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioSedeDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioSedeDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioSedeDTO;
	}

	@Override
	public ServicioSedeDTO transformarConDependencias(ServicioSede obj) {
		ServicioSedeDTO servicioSedeDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return servicioSedeDTO;
	}

	@Override
	public ServicioSede transformarEntidadSinDependencias(ServicioSede obj) {
		ServicioSede servicioSede = new ServicioSede();
		
		servicioSede.setServicioSedePK(obj.getServicioSedePK());
		
		servicioSede.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioSede.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioSede.setEstadoRegistro(obj.getEstadoRegistro());
		
		return servicioSede;
	}


	@Override
	public ServicioSede transformarEntidadConDependencias(ServicioSede obj) {
		ServicioSede servicioSede = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return servicioSede;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ServicioSede> coleccion) {
		List<ServicioSedeDTO> servicioSedeDTOList = new ArrayList<>();
		for(ServicioSede c : coleccion)
			servicioSedeDTOList.add(transformarConDependencias(c));
		return servicioSedeDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ServicioSede> coleccion) {
		List<ServicioSedeDTO> servicioSedeDTOList = new ArrayList<>();
		for(ServicioSede c : coleccion)
			servicioSedeDTOList.add(transformarSinDependencias(c));
		return servicioSedeDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ServicioSede> coleccion) {
		List<ServicioSede> servicioSedeList = new ArrayList<>();
		for(ServicioSede c : coleccion)
			servicioSedeList.add(transformarEntidadConDependencias(c));
		return servicioSedeList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ServicioSede> coleccion) {
		List<ServicioSede> servicioSedeList = new ArrayList<>();
		for(ServicioSede c : coleccion)
			servicioSedeList.add(transformarEntidadSinDependencias(c));
		return servicioSedeList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
