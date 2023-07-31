package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.SolicitudProrroga;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SolicitudProrrogaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SolicitudProrrogaDTO extends IDTO<SolicitudProrroga> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idSolicitud;

	private Long idCaso;		
	private Date fechaSolicitud;		
	private Date fechaProrroga;		
	private String observaciones;		
	private Long idDocumento;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public SolicitudProrrogaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSolicitud(){
		return this.idSolicitud;
	}
	
	public void setIdSolicitud(Long idSolicitud){
		this.idSolicitud = idSolicitud;
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Date getFechaSolicitud(){
		return this.fechaSolicitud;
	}
	
	public void setFechaSolicitud(Date fechaSolicitud){
		this.fechaSolicitud = fechaSolicitud;
	}
		
	public Date getFechaProrroga(){
		return this.fechaProrroga;
	}
	
	public void setFechaProrroga(Date fechaProrroga){
		this.fechaProrroga = fechaProrroga;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
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
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitud);        
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 37 * hash + Objects.hashCode(this.fechaProrroga);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudProrrogaDTO que se pasa
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
        final SolicitudProrrogaDTO other = (SolicitudProrrogaDTO) obj;
                
        if (!Objects.equals(this.idSolicitud, other.idSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaSolicitud, other.fechaSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaProrroga, other.fechaProrroga)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
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
	public SolicitudProrrogaDTO transformarSinDependencias(SolicitudProrroga obj) {
		SolicitudProrrogaDTO solicitudProrrogaDTO = new SolicitudProrrogaDTO();
		
		solicitudProrrogaDTO.setIdSolicitud(obj.getIdSolicitud());
		solicitudProrrogaDTO.setIdCaso(obj.getIdCaso());
		solicitudProrrogaDTO.setFechaSolicitud(obj.getFechaSolicitud());
		solicitudProrrogaDTO.setFechaProrroga(obj.getFechaProrroga());
		solicitudProrrogaDTO.setObservaciones(obj.getObservaciones());
		solicitudProrrogaDTO.setIdDocumento(obj.getIdDocumento());
		solicitudProrrogaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudProrrogaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudProrrogaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return solicitudProrrogaDTO;
	}

	@Override
	public SolicitudProrrogaDTO transformarConDependencias(SolicitudProrroga obj) {
		SolicitudProrrogaDTO solicitudProrrogaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return solicitudProrrogaDTO;
	}

	@Override
	public SolicitudProrroga transformarEntidadSinDependencias(SolicitudProrroga obj) {
		SolicitudProrroga solicitudProrroga = new SolicitudProrroga();
		
		solicitudProrroga.setIdSolicitud(obj.getIdSolicitud());
		
		solicitudProrroga.setIdCaso(obj.getIdCaso());
		solicitudProrroga.setFechaSolicitud(obj.getFechaSolicitud());
		solicitudProrroga.setFechaProrroga(obj.getFechaProrroga());
		solicitudProrroga.setObservaciones(obj.getObservaciones());
		solicitudProrroga.setIdDocumento(obj.getIdDocumento());
		solicitudProrroga.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		solicitudProrroga.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		solicitudProrroga.setEstadoRegistro(obj.getEstadoRegistro());
		
		return solicitudProrroga;
	}


	@Override
	public SolicitudProrroga transformarEntidadConDependencias(SolicitudProrroga obj) {
		SolicitudProrroga solicitudProrroga = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return solicitudProrroga;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<SolicitudProrroga> coleccion) {
		List<SolicitudProrrogaDTO> solicitudProrrogaDTOList = new ArrayList<>();
		for(SolicitudProrroga c : coleccion)
			solicitudProrrogaDTOList.add(transformarConDependencias(c));
		return solicitudProrrogaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<SolicitudProrroga> coleccion) {
		List<SolicitudProrrogaDTO> solicitudProrrogaDTOList = new ArrayList<>();
		for(SolicitudProrroga c : coleccion)
			solicitudProrrogaDTOList.add(transformarSinDependencias(c));
		return solicitudProrrogaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<SolicitudProrroga> coleccion) {
		List<SolicitudProrroga> solicitudProrrogaList = new ArrayList<>();
		for(SolicitudProrroga c : coleccion)
			solicitudProrrogaList.add(transformarEntidadConDependencias(c));
		return solicitudProrrogaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<SolicitudProrroga> coleccion) {
		List<SolicitudProrroga> solicitudProrrogaList = new ArrayList<>();
		for(SolicitudProrroga c : coleccion)
			solicitudProrrogaList.add(transformarEntidadSinDependencias(c));
		return solicitudProrrogaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
