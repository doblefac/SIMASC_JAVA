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

import com.ccb.simasc.transversal.entidades.ContestacionDemanda;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ContestacionDemandaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ContestacionDemandaDTO extends IDTO<ContestacionDemanda> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idContestacionDemanda;

	private Long idDocumentoContestacion;		
	private Long idDocumentoExcepciones;		
	private Long idDocumentoDemReconvencion;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idContestacionDemReconvencion;		
	
    public ContestacionDemandaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdContestacionDemanda(){
		return this.idContestacionDemanda;
	}
	
	public void setIdContestacionDemanda(Long idContestacionDemanda){
		this.idContestacionDemanda = idContestacionDemanda;
	}
	
	public Long getIdDocumentoContestacion(){
		return this.idDocumentoContestacion;
	}
	
	public void setIdDocumentoContestacion(Long idDocumentoContestacion){
		this.idDocumentoContestacion = idDocumentoContestacion;
	}
		
	public Long getIdDocumentoExcepciones(){
		return this.idDocumentoExcepciones;
	}
	
	public void setIdDocumentoExcepciones(Long idDocumentoExcepciones){
		this.idDocumentoExcepciones = idDocumentoExcepciones;
	}
		
	public Long getIdDocumentoDemReconvencion(){
		return this.idDocumentoDemReconvencion;
	}
	
	public void setIdDocumentoDemReconvencion(Long idDocumentoDemReconvencion){
		this.idDocumentoDemReconvencion = idDocumentoDemReconvencion;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
		
	public Long getIdContestacionDemReconvencion(){
		return this.idContestacionDemReconvencion;
	}
	
	public void setIdContestacionDemReconvencion(Long idContestacionDemReconvencion){
		this.idContestacionDemReconvencion = idContestacionDemReconvencion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idContestacionDemanda);        
        hash = 37 * hash + Objects.hashCode(this.idDocumentoContestacion);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoExcepciones);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoDemReconvencion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idContestacionDemReconvencion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ContestacionDemandaDTO que se pasa
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
        final ContestacionDemandaDTO other = (ContestacionDemandaDTO) obj;
                
        if (!Objects.equals(this.idContestacionDemanda, other.idContestacionDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoContestacion, other.idDocumentoContestacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoExcepciones, other.idDocumentoExcepciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoDemReconvencion, other.idDocumentoDemReconvencion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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
        
        return Objects.equals(this.idContestacionDemReconvencion, other.idContestacionDemReconvencion);
                
    }
    
    @Override
	public ContestacionDemandaDTO transformarSinDependencias(ContestacionDemanda obj) {
		ContestacionDemandaDTO contestacionDemandaDTO = new ContestacionDemandaDTO();
		
		contestacionDemandaDTO.setIdContestacionDemanda(obj.getIdContestacionDemanda());
		contestacionDemandaDTO.setIdDocumentoContestacion(obj.getIdDocumentoContestacion());
		contestacionDemandaDTO.setIdDocumentoExcepciones(obj.getIdDocumentoExcepciones());
		contestacionDemandaDTO.setIdDocumentoDemReconvencion(obj.getIdDocumentoDemReconvencion());
		contestacionDemandaDTO.setIdCaso(obj.getIdCaso());
		contestacionDemandaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		contestacionDemandaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		contestacionDemandaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		contestacionDemandaDTO.setIdContestacionDemReconvencion(obj.getIdContestacionDemReconvencion());
		
		return contestacionDemandaDTO;
	}

	@Override
	public ContestacionDemandaDTO transformarConDependencias(ContestacionDemanda obj) {
		ContestacionDemandaDTO contestacionDemandaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return contestacionDemandaDTO;
	}

	@Override
	public ContestacionDemanda transformarEntidadSinDependencias(ContestacionDemanda obj) {
		ContestacionDemanda contestacionDemanda = new ContestacionDemanda();
		
		contestacionDemanda.setIdContestacionDemanda(obj.getIdContestacionDemanda());
		
		contestacionDemanda.setIdDocumentoContestacion(obj.getIdDocumentoContestacion());
		contestacionDemanda.setIdDocumentoExcepciones(obj.getIdDocumentoExcepciones());
		contestacionDemanda.setIdDocumentoDemReconvencion(obj.getIdDocumentoDemReconvencion());
		contestacionDemanda.setIdCaso(obj.getIdCaso());
		contestacionDemanda.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		contestacionDemanda.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		contestacionDemanda.setEstadoRegistro(obj.getEstadoRegistro());
		contestacionDemanda.setIdContestacionDemReconvencion(obj.getIdContestacionDemReconvencion());
		
		return contestacionDemanda;
	}


	@Override
	public ContestacionDemanda transformarEntidadConDependencias(ContestacionDemanda obj) {
		ContestacionDemanda contestacionDemanda = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return contestacionDemanda;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ContestacionDemanda> coleccion) {
		List<ContestacionDemandaDTO> contestacionDemandaDTOList = new ArrayList<>();
		for(ContestacionDemanda c : coleccion)
			contestacionDemandaDTOList.add(transformarConDependencias(c));
		return contestacionDemandaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ContestacionDemanda> coleccion) {
		List<ContestacionDemandaDTO> contestacionDemandaDTOList = new ArrayList<>();
		for(ContestacionDemanda c : coleccion)
			contestacionDemandaDTOList.add(transformarSinDependencias(c));
		return contestacionDemandaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ContestacionDemanda> coleccion) {
		List<ContestacionDemanda> contestacionDemandaList = new ArrayList<>();
		for(ContestacionDemanda c : coleccion)
			contestacionDemandaList.add(transformarEntidadConDependencias(c));
		return contestacionDemandaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ContestacionDemanda> coleccion) {
		List<ContestacionDemanda> contestacionDemandaList = new ArrayList<>();
		for(ContestacionDemanda c : coleccion)
			contestacionDemandaList.add(transformarEntidadSinDependencias(c));
		return contestacionDemandaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
