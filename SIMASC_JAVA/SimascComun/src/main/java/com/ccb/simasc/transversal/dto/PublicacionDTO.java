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

import com.ccb.simasc.transversal.entidades.Publicacion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PublicacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PublicacionDTO extends IDTO<Publicacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idPublicacion;

	private String tipoPublicacion;		
	private Long idPersona;		
	private String titulo;		
	private String editorial;		
	private String ciudad;		
	private Integer anio;		
	private Integer numeroPaginas;		
	private String nombreRevista;		
	private String registroIsbn;		
	private Integer numeroRevista;		
	private String numeroPagina;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public PublicacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPublicacion(){
		return this.idPublicacion;
	}
	
	public void setIdPublicacion(Long idPublicacion){
		this.idPublicacion = idPublicacion;
	}
	
	public String getTipoPublicacion(){
		return this.tipoPublicacion;
	}
	
	public void setTipoPublicacion(String tipoPublicacion){
		this.tipoPublicacion = tipoPublicacion;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
		
	public String getEditorial(){
		return this.editorial;
	}
	
	public void setEditorial(String editorial){
		this.editorial = editorial;
	}
		
	public String getCiudad(){
		return this.ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}
		
	public Integer getAnio(){
		return this.anio;
	}
	
	public void setAnio(Integer anio){
		this.anio = anio;
	}
		
	public Integer getNumeroPaginas(){
		return this.numeroPaginas;
	}
	
	public void setNumeroPaginas(Integer numeroPaginas){
		this.numeroPaginas = numeroPaginas;
	}
		
	public String getNombreRevista(){
		return this.nombreRevista;
	}
	
	public void setNombreRevista(String nombreRevista){
		this.nombreRevista = nombreRevista;
	}
		
	public String getRegistroIsbn(){
		return this.registroIsbn;
	}
	
	public void setRegistroIsbn(String registroIsbn){
		this.registroIsbn = registroIsbn;
	}
		
	public Integer getNumeroRevista(){
		return this.numeroRevista;
	}
	
	public void setNumeroRevista(Integer numeroRevista){
		this.numeroRevista = numeroRevista;
	}
		
	public String getNumeroPagina(){
		return this.numeroPagina;
	}
	
	public void setNumeroPagina(String numeroPagina){
		this.numeroPagina = numeroPagina;
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
        
        hash = 37 * hash + Objects.hashCode(this.idPublicacion);        
        hash = 37 * hash + Objects.hashCode(this.tipoPublicacion);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.editorial);
        hash = 37 * hash + Objects.hashCode(this.ciudad);
        hash = 37 * hash + Objects.hashCode(this.anio);
        hash = 37 * hash + Objects.hashCode(this.numeroPaginas);
        hash = 37 * hash + Objects.hashCode(this.nombreRevista);
        hash = 37 * hash + Objects.hashCode(this.registroIsbn);
        hash = 37 * hash + Objects.hashCode(this.numeroRevista);
        hash = 37 * hash + Objects.hashCode(this.numeroPagina);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PublicacionDTO que se pasa
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
        final PublicacionDTO other = (PublicacionDTO) obj;
                
        if (!Objects.equals(this.idPublicacion, other.idPublicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPublicacion, other.tipoPublicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroPaginas, other.numeroPaginas)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreRevista, other.nombreRevista)) {
            return false;
        }
        
        if (!Objects.equals(this.registroIsbn, other.registroIsbn)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroRevista, other.numeroRevista)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroPagina, other.numeroPagina)) {
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
	public PublicacionDTO transformarSinDependencias(Publicacion obj) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();
		
		publicacionDTO.setIdPublicacion(obj.getIdPublicacion());
		publicacionDTO.setTipoPublicacion(obj.getTipoPublicacion());
		publicacionDTO.setIdPersona(obj.getIdPersona());
		publicacionDTO.setTitulo(obj.getTitulo());
		publicacionDTO.setEditorial(obj.getEditorial());
		publicacionDTO.setCiudad(obj.getCiudad());
		publicacionDTO.setAnio(obj.getAnio());
		publicacionDTO.setNumeroPaginas(obj.getNumeroPaginas());
		publicacionDTO.setNombreRevista(obj.getNombreRevista());
		publicacionDTO.setRegistroIsbn(obj.getRegistroIsbn());
		publicacionDTO.setNumeroRevista(obj.getNumeroRevista());
		publicacionDTO.setNumeroPagina(obj.getNumeroPagina());
		publicacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		publicacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		publicacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return publicacionDTO;
	}

	@Override
	public PublicacionDTO transformarConDependencias(Publicacion obj) {
		PublicacionDTO publicacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return publicacionDTO;
	}

	@Override
	public Publicacion transformarEntidadSinDependencias(Publicacion obj) {
		Publicacion publicacion = new Publicacion();
		
		publicacion.setIdPublicacion(obj.getIdPublicacion());
		
		publicacion.setTipoPublicacion(obj.getTipoPublicacion());
		publicacion.setIdPersona(obj.getIdPersona());
		publicacion.setTitulo(obj.getTitulo());
		publicacion.setEditorial(obj.getEditorial());
		publicacion.setCiudad(obj.getCiudad());
		publicacion.setAnio(obj.getAnio());
		publicacion.setNumeroPaginas(obj.getNumeroPaginas());
		publicacion.setNombreRevista(obj.getNombreRevista());
		publicacion.setRegistroIsbn(obj.getRegistroIsbn());
		publicacion.setNumeroRevista(obj.getNumeroRevista());
		publicacion.setNumeroPagina(obj.getNumeroPagina());
		publicacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		publicacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		publicacion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return publicacion;
	}


	@Override
	public Publicacion transformarEntidadConDependencias(Publicacion obj) {
		Publicacion publicacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return publicacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Publicacion> coleccion) {
		List<PublicacionDTO> publicacionDTOList = new ArrayList<>();
		for(Publicacion c : coleccion)
			publicacionDTOList.add(transformarConDependencias(c));
		return publicacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Publicacion> coleccion) {
		List<PublicacionDTO> publicacionDTOList = new ArrayList<>();
		for(Publicacion c : coleccion)
			publicacionDTOList.add(transformarSinDependencias(c));
		return publicacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Publicacion> coleccion) {
		List<Publicacion> publicacionList = new ArrayList<>();
		for(Publicacion c : coleccion)
			publicacionList.add(transformarEntidadConDependencias(c));
		return publicacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Publicacion> coleccion) {
		List<Publicacion> publicacionList = new ArrayList<>();
		for(Publicacion c : coleccion)
			publicacionList.add(transformarEntidadSinDependencias(c));
		return publicacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
