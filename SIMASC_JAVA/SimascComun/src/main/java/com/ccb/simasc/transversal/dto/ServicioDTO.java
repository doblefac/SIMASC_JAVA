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

import com.ccb.simasc.transversal.entidades.Servicio;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ServicioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ServicioDTO extends IDTO<Servicio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idServicio;

	private String nombre;		
	private String tipo;		
	private String observaciones;		
	private boolean requiereSuplente;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;
	private boolean liquidable;
	private boolean aplicaMauc;
	private String urlRetoma;	
	
    public ServicioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public boolean getRequiereSuplente(){
		return this.requiereSuplente;
	}
	
	public void setRequiereSuplente(boolean requiereSuplente){
		this.requiereSuplente = requiereSuplente;
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
	
	
    public boolean getLiquidable() {
		return liquidable;
	}

	public void setLiquidable(boolean liquidable) {
		this.liquidable = liquidable;
	}
	
	public boolean isAplicaMauc() {
		return aplicaMauc;
	}

	public void setAplicaMauc(boolean aplicaMauc) {
		this.aplicaMauc = aplicaMauc;
	}


	public String getUrlRetoma() {
		return urlRetoma;
	}

	public void setUrlRetoma(String urlRetoma) {
		this.urlRetoma = urlRetoma;
	}


	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idServicio);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + (this.requiereSuplente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.liquidable);
        hash = 37 * hash + (this.aplicaMauc ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.urlRetoma);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioDTO que se pasa
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
        final ServicioDTO other = (ServicioDTO) obj;
                
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.requiereSuplente, other.requiereSuplente)) {
        	return false;
        }
        
        if (!Objects.equals(this.liquidable, other.liquidable)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.urlRetoma, other.urlRetoma);
                
    }
    
    @Override
	public ServicioDTO transformarSinDependencias(Servicio obj) {
		ServicioDTO servicioDTO = new ServicioDTO();
		
		servicioDTO.setIdServicio(obj.getIdServicio());
		servicioDTO.setNombre(obj.getNombre());
		servicioDTO.setTipo(obj.getTipo());
		servicioDTO.setObservaciones(obj.getObservaciones());
		servicioDTO.setRequiereSuplente(obj.getRequiereSuplente());
		servicioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		servicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		servicioDTO.setLiquidable(obj.getLiquidable());
		servicioDTO.setAplicaMauc(obj.getAplicaMauc());
		servicioDTO.setUrlRetoma(obj.getUrlRetoma());
		
		return servicioDTO;
	}

	@Override
	public ServicioDTO transformarConDependencias(Servicio obj) {
		ServicioDTO servicioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return servicioDTO;
	}

	@Override
	public Servicio transformarEntidadSinDependencias(Servicio obj) {
		Servicio servicio = new Servicio();
		
		servicio.setIdServicio(obj.getIdServicio());
		
		servicio.setNombre(obj.getNombre());
		servicio.setTipo(obj.getTipo());
		servicio.setObservaciones(obj.getObservaciones());
		servicio.setRequiereSuplente(obj.getRequiereSuplente());
		servicio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		servicio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		servicio.setEstadoRegistro(obj.getEstadoRegistro());
		servicio.setLiquidable(obj.getLiquidable());
		servicio.setRequierePago(obj.isRequierePago());
		servicio.setAplicaMauc(obj.getAplicaMauc());
		servicio.setUrlRetoma(obj.getUrlRetoma());
		return servicio;
	}


	@Override
	public Servicio transformarEntidadConDependencias(Servicio obj) {
		Servicio servicio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return servicio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Servicio> coleccion) {
		List<ServicioDTO> servicioDTOList = new ArrayList<>();
		for(Servicio c : coleccion)
			servicioDTOList.add(transformarConDependencias(c));
		return servicioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Servicio> coleccion) {
		List<ServicioDTO> servicioDTOList = new ArrayList<>();
		for(Servicio c : coleccion)
			servicioDTOList.add(transformarSinDependencias(c));
		return servicioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Servicio> coleccion) {
		List<Servicio> servicioList = new ArrayList<>();
		for(Servicio c : coleccion)
			servicioList.add(transformarEntidadConDependencias(c));
		return servicioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Servicio> coleccion) {
		List<Servicio> servicioList = new ArrayList<>();
		for(Servicio c : coleccion)
			servicioList.add(transformarEntidadSinDependencias(c));
		return servicioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
