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

import com.ccb.simasc.transversal.entidades.NotificacionDocumento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad NotificacionDocumentoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class NotificacionDocumentoDTO extends IDTO<NotificacionDocumento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private String palabraClave;
	
	// protected region atributos end
	private Long idNotificacionDocumento;

	private String tipoNotificacion;		
	private Date fechaFijacion;		
	private String norma;		
	private String asunto;		
	private String termino;		
	private Date fechaInicio;		
	private Date fechaFin;		
	private String providencia;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	private Long idCaso;		
	
    public NotificacionDocumentoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdNotificacionDocumento(){
		return this.idNotificacionDocumento;
	}
	
	public void setIdNotificacionDocumento(Long idNotificacionDocumento){
		this.idNotificacionDocumento = idNotificacionDocumento;
	}
	
	public String getTipoNotificacion(){
		return this.tipoNotificacion;
	}
	
	public void setTipoNotificacion(String tipoNotificacion){
		this.tipoNotificacion = tipoNotificacion;
	}
		
	public Date getFechaFijacion(){
		return this.fechaFijacion;
	}
	
	public void setFechaFijacion(Date fechaFijacion){
		this.fechaFijacion = fechaFijacion;
	}
		
	public String getNorma(){
		return this.norma;
	}
	
	public void setNorma(String norma){
		this.norma = norma;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getTermino(){
		return this.termino;
	}
	
	public void setTermino(String termino){
		this.termino = termino;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public String getProvidencia(){
		return this.providencia;
	}
	
	public void setProvidencia(String providencia){
		this.providencia = providencia;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idNotificacionDocumento);        
        hash = 37 * hash + Objects.hashCode(this.tipoNotificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaFijacion);
        hash = 37 * hash + Objects.hashCode(this.norma);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.termino);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.providencia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NotificacionDocumentoDTO que se pasa
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
        final NotificacionDocumentoDTO other = (NotificacionDocumentoDTO) obj;
                
        if (!Objects.equals(this.idNotificacionDocumento, other.idNotificacionDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoNotificacion, other.tipoNotificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFijacion, other.fechaFijacion)) {
            return false;
        }
        
        if (!Objects.equals(this.norma, other.norma)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.termino, other.termino)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.providencia, other.providencia)) {
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
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
    
    @Override
	public NotificacionDocumentoDTO transformarSinDependencias(NotificacionDocumento obj) {
		NotificacionDocumentoDTO notificacionDocumentoDTO = new NotificacionDocumentoDTO();
		
		notificacionDocumentoDTO.setIdNotificacionDocumento(obj.getIdNotificacionDocumento());
		notificacionDocumentoDTO.setTipoNotificacion(obj.getTipoNotificacion());
		notificacionDocumentoDTO.setFechaFijacion(obj.getFechaFijacion());
		notificacionDocumentoDTO.setNorma(obj.getNorma());
		notificacionDocumentoDTO.setAsunto(obj.getAsunto());
		notificacionDocumentoDTO.setTermino(obj.getTermino());
		notificacionDocumentoDTO.setFechaInicio(obj.getFechaInicio());
		notificacionDocumentoDTO.setFechaFin(obj.getFechaFin());
		notificacionDocumentoDTO.setProvidencia(obj.getProvidencia());
		notificacionDocumentoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		notificacionDocumentoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		notificacionDocumentoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		notificacionDocumentoDTO.setIdDocumento(obj.getIdDocumento());
		notificacionDocumentoDTO.setIdCaso(obj.getIdCaso());
		
		return notificacionDocumentoDTO;
	}

	@Override
	public NotificacionDocumentoDTO transformarConDependencias(NotificacionDocumento obj) {
		NotificacionDocumentoDTO notificacionDocumentoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return notificacionDocumentoDTO;
	}

	@Override
	public NotificacionDocumento transformarEntidadSinDependencias(NotificacionDocumento obj) {
		NotificacionDocumento notificacionDocumento = new NotificacionDocumento();
		
		notificacionDocumento.setIdNotificacionDocumento(obj.getIdNotificacionDocumento());
		
		notificacionDocumento.setTipoNotificacion(obj.getTipoNotificacion());
		notificacionDocumento.setFechaFijacion(obj.getFechaFijacion());
		notificacionDocumento.setNorma(obj.getNorma());
		notificacionDocumento.setAsunto(obj.getAsunto());
		notificacionDocumento.setTermino(obj.getTermino());
		notificacionDocumento.setFechaInicio(obj.getFechaInicio());
		notificacionDocumento.setFechaFin(obj.getFechaFin());
		notificacionDocumento.setProvidencia(obj.getProvidencia());
		notificacionDocumento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		notificacionDocumento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		notificacionDocumento.setEstadoRegistro(obj.getEstadoRegistro());
		notificacionDocumento.setIdDocumento(obj.getIdDocumento());
		notificacionDocumento.setIdCaso(obj.getIdCaso());
		
		return notificacionDocumento;
	}


	@Override
	public NotificacionDocumento transformarEntidadConDependencias(NotificacionDocumento obj) {
		NotificacionDocumento notificacionDocumento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return notificacionDocumento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<NotificacionDocumento> coleccion) {
		List<NotificacionDocumentoDTO> notificacionDocumentoDTOList = new ArrayList<>();
		for(NotificacionDocumento c : coleccion)
			notificacionDocumentoDTOList.add(transformarConDependencias(c));
		return notificacionDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<NotificacionDocumento> coleccion) {
		List<NotificacionDocumentoDTO> notificacionDocumentoDTOList = new ArrayList<>();
		for(NotificacionDocumento c : coleccion)
			notificacionDocumentoDTOList.add(transformarSinDependencias(c));
		return notificacionDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<NotificacionDocumento> coleccion) {
		List<NotificacionDocumento> notificacionDocumentoList = new ArrayList<>();
		for(NotificacionDocumento c : coleccion)
			notificacionDocumentoList.add(transformarEntidadConDependencias(c));
		return notificacionDocumentoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<NotificacionDocumento> coleccion) {
		List<NotificacionDocumento> notificacionDocumentoList = new ArrayList<>();
		for(NotificacionDocumento c : coleccion)
			notificacionDocumentoList.add(transformarEntidadSinDependencias(c));
		return notificacionDocumentoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public String getPalabraClave() {
		return palabraClave;
	}
		
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}



	public Long getIdCaso() {
		return idCaso;
	}



	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	
	
	// protected region metodos adicionales end

}
