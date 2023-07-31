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

import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Documento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CarpetaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CarpetaDTO extends IDTO<Carpeta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idCarpeta;

	private boolean esCuaderno;		
	private String path;		
	private String nombre;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCarpetaContenedora;		
	private Long idCaso;		
	
    public CarpetaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdCarpeta(){
		return this.idCarpeta;
	}
	
	public void setIdCarpeta(Long idCarpeta){
		this.idCarpeta = idCarpeta;
	}
	
	public boolean getEsCuaderno(){
		return this.esCuaderno;
	}
	
	public void setEsCuaderno(boolean esCuaderno){
		this.esCuaderno = esCuaderno;
	}
		
	public String getPath(){
		return this.path;
	}
	
	public void setPath(String path){
		this.path = path;
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
		
	public Long getIdCarpetaContenedora(){
		return this.idCarpetaContenedora;
	}
	
	public void setIdCarpetaContenedora(Long idCarpetaContenedora){
		this.idCarpetaContenedora = idCarpetaContenedora;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCarpeta);        
        hash = 37 * hash + (this.esCuaderno ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.path);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCarpetaContenedora);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CarpetaDTO que se pasa
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
        final CarpetaDTO other = (CarpetaDTO) obj;
                
        if (!Objects.equals(this.idCarpeta, other.idCarpeta)) {
            return false;
        }
        
        if (!Objects.equals(this.esCuaderno, other.esCuaderno)) {
            return false;
        }
        
        if (!Objects.equals(this.path, other.path)) {
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
        
        if (!Objects.equals(this.idCarpetaContenedora, other.idCarpetaContenedora)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    @Override
	public CarpetaDTO transformarSinDependencias(Carpeta obj) {
		CarpetaDTO carpetaDTO = new CarpetaDTO();
		
		carpetaDTO.setIdCarpeta(obj.getIdCarpeta());
		carpetaDTO.setEsCuaderno(obj.getEsCuaderno());
		carpetaDTO.setPath(obj.getPath());
		carpetaDTO.setNombre(obj.getNombre());
		carpetaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		carpetaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		carpetaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		carpetaDTO.setIdCarpetaContenedora(obj.getIdCarpetaContenedora());
		carpetaDTO.setIdCaso(obj.getIdCaso());
		
		return carpetaDTO;
	}

	@Override
	public CarpetaDTO transformarConDependencias(Carpeta obj) {
		CarpetaDTO carpetaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return carpetaDTO;
	}

	@Override
	public Carpeta transformarEntidadSinDependencias(Carpeta obj) {
		Carpeta carpeta = new Carpeta();
		
		carpeta.setIdCarpeta(obj.getIdCarpeta());
		carpeta.setIdCuaderno(obj.getIdCuaderno());
		carpeta.setEsCuaderno(obj.getEsCuaderno());
		carpeta.setPath(obj.getPath());
		carpeta.setNombre(obj.getNombre());
		carpeta.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		carpeta.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		carpeta.setEstadoRegistro(obj.getEstadoRegistro());
		carpeta.setIdCarpetaContenedora(obj.getIdCarpetaContenedora());
		carpeta.setIdCaso(obj.getIdCaso());
		
		return carpeta;
	}


	@Override
	public Carpeta transformarEntidadConDependencias(Carpeta obj) {
		Carpeta carpeta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		
		if (obj.getCarpetaList() != null && !obj.getCarpetaList().isEmpty())
			carpeta.setCarpetaList((List<Carpeta>) transformarColeccionEntidadesConDependencias(obj.getCarpetaList()));
		if(obj.getCuaderno() != null){
			carpeta.setCuaderno(obj.getCuaderno());
		}
		if (obj.getDocumentoList() != null && !obj.getDocumentoList().isEmpty()) {
			DocumentoDTO transformadorDocumento = new DocumentoDTO();
			carpeta.setDocumentoList((List<Documento>) transformadorDocumento.transformarColeccionEntidadesSinDependencias(obj.getDocumentoList()));
		}

		// protected region modificaciones transformarEntidadConDependencias end
		
		return carpeta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Carpeta> coleccion) {
		List<CarpetaDTO> carpetaDTOList = new ArrayList<>();
		for(Carpeta c : coleccion)
			carpetaDTOList.add(transformarConDependencias(c));
		return carpetaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Carpeta> coleccion) {
		List<CarpetaDTO> carpetaDTOList = new ArrayList<>();
		for(Carpeta c : coleccion)
			carpetaDTOList.add(transformarSinDependencias(c));
		return carpetaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Carpeta> coleccion) {
		List<Carpeta> carpetaList = new ArrayList<>();
		for(Carpeta c : coleccion)
			carpetaList.add(transformarEntidadConDependencias(c));
		return carpetaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Carpeta> coleccion) {
		List<Carpeta> carpetaList = new ArrayList<>();
		for(Carpeta c : coleccion)
			carpetaList.add(transformarEntidadSinDependencias(c));
		return carpetaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
