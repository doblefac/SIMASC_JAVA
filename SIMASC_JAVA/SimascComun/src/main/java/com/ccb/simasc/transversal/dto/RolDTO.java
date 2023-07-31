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

import com.ccb.simasc.transversal.entidades.Rol;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RolDTO extends IDTO<Rol> implements Serializable{	

	private static final long serialVersionUID = 1L;

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idRol;

	private Date fechaCreacion;		
	private String nombre;		
	private boolean preseleccion;
	private String tipoServicio;
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;
	private String nombreCompleto;
	private boolean aplicaMauc;
	
    public RolDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
	
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getPreseleccion(){
		return this.preseleccion;
	}
	
	public void setPreseleccion(boolean preseleccion){
		this.preseleccion = preseleccion;
	}
		
	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
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
	
    public boolean isAplicaMauc() {
		return aplicaMauc;
	}

	public void setAplicaMauc(boolean aplicaMauc) {
		this.aplicaMauc = aplicaMauc;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idRol);        
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.preseleccion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + (this.aplicaMauc ? 0 : 1);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RolDTO que se pasa
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
        final RolDTO other = (RolDTO) obj;
                
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.preseleccion, other.preseleccion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
        	return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aplicaMauc, other.aplicaMauc)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public RolDTO transformarSinDependencias(Rol obj) {
		RolDTO rolDTO = new RolDTO();
		
		rolDTO.setIdRol(obj.getIdRol());
		rolDTO.setFechaCreacion(obj.getFechaCreacion());
		rolDTO.setNombre(obj.getNombre());
		rolDTO.setPreseleccion(obj.getPreseleccion());
		rolDTO.setTipoServicio(obj.getTipoServicio());
		rolDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		rolDTO.setAplicaMauc(obj.getAplicaMauc());
		
		return rolDTO;
	}

	@Override
	public RolDTO transformarConDependencias(Rol obj) {
		RolDTO rolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return rolDTO;
	}

	@Override
	public Rol transformarEntidadSinDependencias(Rol obj) {
		Rol rol = new Rol();
		
		rol.setIdRol(obj.getIdRol());
		
		rol.setFechaCreacion(obj.getFechaCreacion());
		rol.setNombre(obj.getNombre());
		rol.setPreseleccion(obj.getPreseleccion());
		rol.setTipoServicio(obj.getTipoServicio());
		rol.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		rol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		rol.setEstadoRegistro(obj.getEstadoRegistro());
		rol.setAplicaMauc(obj.getAplicaMauc());
		
		return rol;
	}


	@Override
	public Rol transformarEntidadConDependencias(Rol obj) {
		Rol rol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return rol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Rol> coleccion) {
		List<RolDTO> rolDTOList = new ArrayList<>();
		for(Rol c : coleccion)
			rolDTOList.add(transformarConDependencias(c));
		return rolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Rol> coleccion) {
		List<RolDTO> rolDTOList = new ArrayList<>();
		for(Rol c : coleccion)
			rolDTOList.add(transformarSinDependencias(c));
		return rolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Rol> coleccion) {
		List<Rol> rolList = new ArrayList<>();
		for(Rol c : coleccion)
			rolList.add(transformarEntidadConDependencias(c));
		return rolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Rol> coleccion) {
		List<Rol> rolList = new ArrayList<>();
		for(Rol c : coleccion)
			rolList.add(transformarEntidadSinDependencias(c));
		return rolList;
	}



	public String getNombreCompleto() {
		return nombreCompleto;
	}



	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
