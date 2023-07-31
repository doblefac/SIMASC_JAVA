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

import com.ccb.simasc.transversal.entidades.Lista;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ListaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ListaDTO extends IDTO<Lista> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idLista;

	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ListaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdLista(){
		return this.idLista;
	}
	
	public void setIdLista(Long idLista){
		this.idLista = idLista;
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
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idLista);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ListaDTO que se pasa
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
        final ListaDTO other = (ListaDTO) obj;
                
        if (!Objects.equals(this.idLista, other.idLista)) {
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
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public ListaDTO transformarSinDependencias(Lista obj) {
		ListaDTO listaDTO = new ListaDTO();
		
		listaDTO.setIdLista(obj.getIdLista());
		listaDTO.setNombre(obj.getNombre());
		listaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		listaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		listaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return listaDTO;
	}

	@Override
	public ListaDTO transformarConDependencias(Lista obj) {
		ListaDTO listaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return listaDTO;
	}

	@Override
	public Lista transformarEntidadSinDependencias(Lista obj) {
		Lista lista = new Lista();
		
		lista.setIdLista(obj.getIdLista());
		
		lista.setNombre(obj.getNombre());
		lista.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		lista.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		lista.setEstadoRegistro(obj.getEstadoRegistro());
		
		return lista;
	}


	@Override
	public Lista transformarEntidadConDependencias(Lista obj) {
		Lista lista = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return lista;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Lista> coleccion) {
		List<ListaDTO> listaDTOList = new ArrayList<>();
		for(Lista c : coleccion)
			listaDTOList.add(transformarConDependencias(c));
		return listaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Lista> coleccion) {
		List<ListaDTO> listaDTOList = new ArrayList<>();
		for(Lista c : coleccion)
			listaDTOList.add(transformarSinDependencias(c));
		return listaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Lista> coleccion) {
		List<Lista> listaList = new ArrayList<>();
		for(Lista c : coleccion)
			listaList.add(transformarEntidadConDependencias(c));
		return listaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Lista> coleccion) {
		List<Lista> listaList = new ArrayList<>();
		for(Lista c : coleccion)
			listaList.add(transformarEntidadSinDependencias(c));
		return listaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
