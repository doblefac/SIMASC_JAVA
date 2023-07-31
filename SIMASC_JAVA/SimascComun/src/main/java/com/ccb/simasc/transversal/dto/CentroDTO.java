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

import com.ccb.simasc.transversal.entidades.Centro;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CentroDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CentroDTO extends IDTO<Centro> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Long idServicio;
	private Long idMateria;
	// protected region atributos end
	private Long idCentro;
	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String idCiudad;	
	private String director;
	private String firmaDirector;
	private String direccion;
	private String resolucion;
    
	
    public CentroDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
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
		
	public String getIdCiudad(){
		return this.idCiudad;
	}
	
	public void setIdCiudad(String idCiudad){
		this.idCiudad = idCiudad;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getFirmaDirector() {
		return firmaDirector;
	}

	public void setFirmaDirector(String firmaDirector) {
		this.firmaDirector = firmaDirector;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCentro);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCiudad);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.director);
        hash = 37 * hash + Objects.hashCode(this.firmaDirector);
        hash = 37 * hash + Objects.hashCode(this.resolucion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CentroDTO que se pasa
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
        final CentroDTO other = (CentroDTO) obj;
                
        if (!Objects.equals(this.idCentro, other.idCentro)) {
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
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
      
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        
        if (!Objects.equals(this.firmaDirector, other.firmaDirector)) {
            return false;
        }
        
        if (!Objects.equals(this.resolucion, other.resolucion)) {
            return false;
        }
        
        return Objects.equals(this.idCiudad, other.idCiudad);
                
    }
    
    @Override
	public CentroDTO transformarSinDependencias(Centro obj) {
		CentroDTO centroDTO = new CentroDTO();
		
		centroDTO.setIdCentro(obj.getIdCentro());
		centroDTO.setNombre(obj.getNombre());
		centroDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		centroDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		centroDTO.setEstadoRegistro(obj.getEstadoRegistro());
		centroDTO.setIdCiudad(obj.getIdCiudad());
		centroDTO.setDireccion(obj.getDireccion());
		centroDTO.setDirector(obj.getDirector());
		centroDTO.setFirmaDirector(obj.getFirmaDirector());		
		centroDTO.setResolucion(obj.getResolucion());	
		
		return centroDTO;
	}

	@Override
	public CentroDTO transformarConDependencias(Centro obj) {
		CentroDTO centroDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return centroDTO;
	}

	@Override
	public Centro transformarEntidadSinDependencias(Centro obj) {
		Centro centro = new Centro();
		
		centro.setIdCentro(obj.getIdCentro());
		
		centro.setNombre(obj.getNombre());
		centro.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		centro.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		centro.setEstadoRegistro(obj.getEstadoRegistro());
		centro.setIdCiudad(obj.getIdCiudad());
		centro.setDireccion(obj.getDireccion());
		centro.setDirector(obj.getDirector());
		centro.setFirmaDirector(obj.getFirmaDirector());
		centro.setResolucion(obj.getResolucion());
		
		return centro;
	}


	@Override
	public Centro transformarEntidadConDependencias(Centro obj) {
		Centro centro = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return centro;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Centro> coleccion) {
		List<CentroDTO> centroDTOList = new ArrayList<>();
		for(Centro c : coleccion)
			centroDTOList.add(transformarConDependencias(c));
		return centroDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Centro> coleccion) {
		List<CentroDTO> centroDTOList = new ArrayList<>();
		for(Centro c : coleccion)
			centroDTOList.add(transformarSinDependencias(c));
		return centroDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Centro> coleccion) {
		List<Centro> centroList = new ArrayList<>();
		for(Centro c : coleccion)
			centroList.add(transformarEntidadConDependencias(c));
		return centroList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Centro> coleccion) {
		List<Centro> centroList = new ArrayList<>();
		for(Centro c : coleccion)
			centroList.add(transformarEntidadSinDependencias(c));
		return centroList;
	}
	
	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}



	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}



	/**
	 * @return the idMateria
	 */
	public Long getIdMateria() {
		return idMateria;
	}



	/**
	 * @param idMateria the idMateria to set
	 */
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}






	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
