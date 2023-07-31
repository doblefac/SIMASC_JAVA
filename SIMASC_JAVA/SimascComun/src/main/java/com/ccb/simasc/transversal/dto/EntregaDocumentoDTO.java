package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.EntregaDocumento;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.EntregaDocumentoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EntregaDocumentoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EntregaDocumentoDTO extends IDTO<EntregaDocumento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private EntregaDocumentoPK entregaDocumentoPK;

	private Date fechaEntrega;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public EntregaDocumentoDTO(){
		entregaDocumentoPK = new EntregaDocumentoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EntregaDocumentoPK getEntregaDocumentoPK(){
		return this.entregaDocumentoPK;
	}
	
	public void setEntregaDocumentoPK(EntregaDocumentoPK entregaDocumentoPK){
		this.entregaDocumentoPK   = entregaDocumentoPK ;
	}  
	
	public Date getFechaEntrega(){
		return this.fechaEntrega;
	}
	
	public void setFechaEntrega(Date fechaEntrega){
		this.fechaEntrega = fechaEntrega;
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
        
        hash = 37 * hash + Objects.hashCode(this.entregaDocumentoPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaEntrega);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EntregaDocumentoDTO que se pasa
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
        final EntregaDocumentoDTO other = (EntregaDocumentoDTO) obj;
                
        if (!Objects.equals(this.entregaDocumentoPK, other.entregaDocumentoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEntrega, other.fechaEntrega)) {
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
	public EntregaDocumentoDTO transformarSinDependencias(EntregaDocumento obj) {
		EntregaDocumentoDTO entregaDocumentoDTO = new EntregaDocumentoDTO();
		
		entregaDocumentoDTO.setEntregaDocumentoPK(obj.getEntregaDocumentoPK());
		entregaDocumentoDTO.setFechaEntrega(obj.getFechaEntrega());
		entregaDocumentoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		entregaDocumentoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		entregaDocumentoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return entregaDocumentoDTO;
	}

	@Override
	public EntregaDocumentoDTO transformarConDependencias(EntregaDocumento obj) {
		EntregaDocumentoDTO entregaDocumentoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return entregaDocumentoDTO;
	}

	@Override
	public EntregaDocumento transformarEntidadSinDependencias(EntregaDocumento obj) {
		EntregaDocumento entregaDocumento = new EntregaDocumento();
		
		entregaDocumento.setEntregaDocumentoPK(obj.getEntregaDocumentoPK());
		
		entregaDocumento.setFechaEntrega(obj.getFechaEntrega());
		entregaDocumento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		entregaDocumento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		entregaDocumento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return entregaDocumento;
	}


	@Override
	public EntregaDocumento transformarEntidadConDependencias(EntregaDocumento obj) {
		EntregaDocumento entregaDocumento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return entregaDocumento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EntregaDocumento> coleccion) {
		List<EntregaDocumentoDTO> entregaDocumentoDTOList = new ArrayList<>();
		for(EntregaDocumento c : coleccion)
			entregaDocumentoDTOList.add(transformarConDependencias(c));
		return entregaDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EntregaDocumento> coleccion) {
		List<EntregaDocumentoDTO> entregaDocumentoDTOList = new ArrayList<>();
		for(EntregaDocumento c : coleccion)
			entregaDocumentoDTOList.add(transformarSinDependencias(c));
		return entregaDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EntregaDocumento> coleccion) {
		List<EntregaDocumento> entregaDocumentoList = new ArrayList<>();
		for(EntregaDocumento c : coleccion)
			entregaDocumentoList.add(transformarEntidadConDependencias(c));
		return entregaDocumentoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EntregaDocumento> coleccion) {
		List<EntregaDocumento> entregaDocumentoList = new ArrayList<>();
		for(EntregaDocumento c : coleccion)
			entregaDocumentoList.add(transformarEntidadSinDependencias(c));
		return entregaDocumentoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public Collection transformarColeccionEntidadesConDependenciasActivas(Collection<EntregaDocumento> coleccion) {
		List<EntregaDocumento> entregaDocumentoList = new ArrayList<>();
		for(EntregaDocumento c : coleccion)
			entregaDocumentoList.add(transformarEntidadConDependenciasActivas(c));
		return entregaDocumentoList;
	}
	
	public EntregaDocumento transformarEntidadConDependenciasActivas(EntregaDocumento obj) {
		EntregaDocumento entregaDocumento = transformarEntidadSinDependencias(obj);
		if(obj.getPersona() != null && UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(obj.getPersona().getEstadoRegistro() ) )
			entregaDocumento.setPersona( new PersonaDTO().transformarEntidadSinDependencias(obj.getPersona()) );
		
		return entregaDocumento;
	}
	
	// protected region metodos adicionales end

}
