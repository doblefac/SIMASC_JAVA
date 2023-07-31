package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Funcionalidad;
import com.ccb.simasc.transversal.entidades.FuncionalidadRol;



// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FuncionalidadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FuncionalidadDTO extends IDTO<Funcionalidad> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private String nombre;

	private String titulo;		
	private String url;		
	private String nombreTipoFuncionalidad;		
	private String aplicacionTipoFuncionalidad;		
	private String nombreFuncionalidadPadre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FuncionalidadDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
		
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
		
	public String getNombreTipoFuncionalidad(){
		return this.nombreTipoFuncionalidad;
	}
	
	public void setNombreTipoFuncionalidad(String nombreTipoFuncionalidad){
		this.nombreTipoFuncionalidad = nombreTipoFuncionalidad;
	}
		
	public String getAplicacionTipoFuncionalidad(){
		return this.aplicacionTipoFuncionalidad;
	}
	
	public void setAplicacionTipoFuncionalidad(String aplicacionTipoFuncionalidad){
		this.aplicacionTipoFuncionalidad = aplicacionTipoFuncionalidad;
	}
		
	public String getNombreFuncionalidadPadre(){
		return this.nombreFuncionalidadPadre;
	}
	
	public void setNombreFuncionalidadPadre(String nombreFuncionalidadPadre){
		this.nombreFuncionalidadPadre = nombreFuncionalidadPadre;
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
        
        hash = 37 * hash + Objects.hashCode(this.nombre);        
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.nombreTipoFuncionalidad);
        hash = 37 * hash + Objects.hashCode(this.aplicacionTipoFuncionalidad);
        hash = 37 * hash + Objects.hashCode(this.nombreFuncionalidadPadre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FuncionalidadDTO que se pasa
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
        final FuncionalidadDTO other = (FuncionalidadDTO) obj;
                
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreTipoFuncionalidad, other.nombreTipoFuncionalidad)) {
            return false;
        }
        
        if (!Objects.equals(this.aplicacionTipoFuncionalidad, other.aplicacionTipoFuncionalidad)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreFuncionalidadPadre, other.nombreFuncionalidadPadre)) {
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
	public FuncionalidadDTO transformarSinDependencias(Funcionalidad obj) {
		FuncionalidadDTO funcionalidadDTO = new FuncionalidadDTO();
		
		funcionalidadDTO.setNombre(obj.getNombre());
		funcionalidadDTO.setTitulo(obj.getTitulo());
		funcionalidadDTO.setUrl(obj.getUrl());
		funcionalidadDTO.setNombreTipoFuncionalidad(obj.getNombreTipoFuncionalidad());
		funcionalidadDTO.setAplicacionTipoFuncionalidad(obj.getAplicacionTipoFuncionalidad());
		funcionalidadDTO.setNombreFuncionalidadPadre(obj.getNombreFuncionalidadPadre());
		funcionalidadDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		funcionalidadDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		funcionalidadDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return funcionalidadDTO;
	}

	@Override
	public FuncionalidadDTO transformarConDependencias(Funcionalidad obj) {
		FuncionalidadDTO funcionalidadDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones
		
		// protected region modificaciones transformarConDependencias end
		
		return funcionalidadDTO;
	}

	@Override
	public Funcionalidad transformarEntidadSinDependencias(Funcionalidad obj) {
		Funcionalidad funcionalidad = new Funcionalidad();
		
		funcionalidad.setNombre(obj.getNombre());
		
		funcionalidad.setTitulo(obj.getTitulo());
		funcionalidad.setUrl(obj.getUrl());
		funcionalidad.setNombreTipoFuncionalidad(obj.getNombreTipoFuncionalidad());
		funcionalidad.setAplicacionTipoFuncionalidad(obj.getAplicacionTipoFuncionalidad());
		funcionalidad.setNombreFuncionalidadPadre(obj.getNombreFuncionalidadPadre());
		funcionalidad.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		funcionalidad.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		funcionalidad.setEstadoRegistro(obj.getEstadoRegistro());
		
		return funcionalidad;
	}


	@Override
	public Funcionalidad transformarEntidadConDependencias(Funcionalidad obj) {
		Funcionalidad funcionalidad = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		// protected region modificaciones transformarEntidadConDependencias end
		
		return funcionalidad;
	}

	@SuppressWarnings("unchecked")
	public Funcionalidad transformarEntidadConDependenciasRecursivo(Funcionalidad obj, Long idRol, boolean obtenerHijos, boolean obtenerPermisos) {
		Funcionalidad funcionalidad = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		// Escriba en esta seccion sus modificaciones
		if (obtenerHijos && obj.getFuncionalidadList() != null && !obj.getFuncionalidadList().isEmpty())
			funcionalidad.setFuncionalidadList((List<Funcionalidad>) transformarEntidadesConDependenciasRecursivo(
					obj.getFuncionalidadList(), idRol, obtenerHijos, obtenerPermisos));
		if (obtenerPermisos && obj.getFuncionalidadRolList() != null && !obj.getFuncionalidadRolList().isEmpty()) {
			List<FuncionalidadRol> permisos = obj.getFuncionalidadRolList();
			Collections.sort(permisos, new Comparator<FuncionalidadRol>() {
				@Override
				public int compare(FuncionalidadRol o1, FuncionalidadRol o2) {
					return o1.getFuncionalidadRolPK().getIdRol().compareTo(o2.getFuncionalidadRolPK().getIdRol());
				}
			});
			funcionalidad.setFuncionalidadRolList((List<FuncionalidadRol>) new FuncionalidadRolDTO()
					.transformarColeccionEntidadesSinDependencias(permisos));
		}
		else if (obj.getFuncionalidadRolList() != null && !obj.getFuncionalidadRolList().isEmpty())
			funcionalidad.setAcceso(!encontrarRol(obj.getFuncionalidadRolList(), idRol));
		else
			funcionalidad.setAcceso(true);
		// protected region modificaciones transformarEntidadConDependencias end
		
		return funcionalidad;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Funcionalidad> coleccion) {
		List<FuncionalidadDTO> funcionalidadDTOList = new ArrayList<>();
		for(Funcionalidad c : coleccion)
			funcionalidadDTOList.add(transformarConDependencias(c));
		return funcionalidadDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Funcionalidad> coleccion) {
		List<FuncionalidadDTO> funcionalidadDTOList = new ArrayList<>();
		for(Funcionalidad c : coleccion)
			funcionalidadDTOList.add(transformarSinDependencias(c));
		return funcionalidadDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Funcionalidad> coleccion) {
		List<Funcionalidad> funcionalidadList = new ArrayList<>();
		for(Funcionalidad c : coleccion)
			funcionalidadList.add(transformarEntidadConDependencias(c));
		return funcionalidadList;
	}

	public Collection<Funcionalidad> transformarEntidadesConDependenciasRecursivo(Collection<Funcionalidad> coleccion, Long idRol
			, boolean obtenerHijos, boolean obtenerPermisos) {
		List<Funcionalidad> funcionalidadList = new ArrayList<>();
		for(Funcionalidad c : coleccion)
			funcionalidadList.add(transformarEntidadConDependenciasRecursivo(c, idRol, obtenerHijos, obtenerPermisos));
		return funcionalidadList;
	}


	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Funcionalidad> coleccion) {
		List<Funcionalidad> funcionalidadList = new ArrayList<>();
		for(Funcionalidad c : coleccion)
			funcionalidadList.add(transformarEntidadSinDependencias(c));
		return funcionalidadList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	private boolean encontrarRol(List<FuncionalidadRol> roles, Long idRol) {
		boolean encontrado = false;
		for (FuncionalidadRol rol : roles) {
			if (rol.getFuncionalidadRolPK().getIdRol().equals(idRol)) {
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}
	
//	public Collection transformarEntidadesConDependenciasRecursivo(Collection<Funcionalidad> coleccion, Long idRol, boolean obtenerHijos) {
//		List<Funcionalidad> funcionalidadList = new ArrayList<>();
//		for(Funcionalidad c : coleccion)
//			funcionalidadList.add(transformarEntidadConDependenciasRecursivo(c, idRol, obtenerHijos));
//		return funcionalidadList;
//	}

	// protected region metodos adicionales end

}
