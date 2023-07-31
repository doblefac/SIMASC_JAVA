package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.FirmaDocumento;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.FirmaDocumentoPK;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FirmaDocumentoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FirmaDocumentoDTO extends IDTO<FirmaDocumento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private FirmaDocumentoPK firmaDocumentoPK;

	private String estado;		
	private Date fechaEmision;		
	private Date fechaFirma;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FirmaDocumentoDTO(){
		firmaDocumentoPK = new FirmaDocumentoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public FirmaDocumentoPK getFirmaDocumentoPK(){
		return this.firmaDocumentoPK;
	}
	
	public void setFirmaDocumentoPK(FirmaDocumentoPK firmaDocumentoPK){
		this.firmaDocumentoPK   = firmaDocumentoPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaEmision(){
		return this.fechaEmision;
	}
	
	public void setFechaEmision(Date fechaEmision){
		this.fechaEmision = fechaEmision;
	}
		
	public Date getFechaFirma(){
		return this.fechaFirma;
	}
	
	public void setFechaFirma(Date fechaFirma){
		this.fechaFirma = fechaFirma;
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
        
        hash = 37 * hash + Objects.hashCode(this.firmaDocumentoPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaEmision);
        hash = 37 * hash + Objects.hashCode(this.fechaFirma);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FirmaDocumentoDTO que se pasa
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
        final FirmaDocumentoDTO other = (FirmaDocumentoDTO) obj;
                
        if (!Objects.equals(this.firmaDocumentoPK, other.firmaDocumentoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEmision, other.fechaEmision)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFirma, other.fechaFirma)) {
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
	public FirmaDocumentoDTO transformarSinDependencias(FirmaDocumento obj) {
		FirmaDocumentoDTO firmaDocumentoDTO = new FirmaDocumentoDTO();
		
		firmaDocumentoDTO.setFirmaDocumentoPK(obj.getFirmaDocumentoPK());
		firmaDocumentoDTO.setEstado(obj.getEstado());
		firmaDocumentoDTO.setFechaEmision(obj.getFechaEmision());
		firmaDocumentoDTO.setFechaFirma(obj.getFechaFirma());
		firmaDocumentoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		firmaDocumentoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		firmaDocumentoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return firmaDocumentoDTO;
	}

	@Override
	public FirmaDocumentoDTO transformarConDependencias(FirmaDocumento obj) {
		FirmaDocumentoDTO firmaDocumentoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return firmaDocumentoDTO;
	}

	@Override
	public FirmaDocumento transformarEntidadSinDependencias(FirmaDocumento obj) {
		FirmaDocumento firmaDocumento = new FirmaDocumento();
		
		firmaDocumento.setFirmaDocumentoPK(obj.getFirmaDocumentoPK());
		
		firmaDocumento.setEstado(obj.getEstado());
		firmaDocumento.setFechaEmision(obj.getFechaEmision());
		firmaDocumento.setFechaFirma(obj.getFechaFirma());
		firmaDocumento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		firmaDocumento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		firmaDocumento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return firmaDocumento;
	}


	@Override
	public FirmaDocumento transformarEntidadConDependencias(FirmaDocumento obj) {
		FirmaDocumento firmaDocumento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return firmaDocumento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FirmaDocumento> coleccion) {
		List<FirmaDocumentoDTO> firmaDocumentoDTOList = new ArrayList<>();
		for(FirmaDocumento c : coleccion)
			firmaDocumentoDTOList.add(transformarConDependencias(c));
		return firmaDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FirmaDocumento> coleccion) {
		List<FirmaDocumentoDTO> firmaDocumentoDTOList = new ArrayList<>();
		for(FirmaDocumento c : coleccion)
			firmaDocumentoDTOList.add(transformarSinDependencias(c));
		return firmaDocumentoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FirmaDocumento> coleccion) {
		List<FirmaDocumento> firmaDocumentoList = new ArrayList<>();
		for(FirmaDocumento c : coleccion)
			firmaDocumentoList.add(transformarEntidadConDependencias(c));
		return firmaDocumentoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FirmaDocumento> coleccion) {
		List<FirmaDocumento> firmaDocumentoList = new ArrayList<>();
		for(FirmaDocumento c : coleccion)
			firmaDocumentoList.add(transformarEntidadSinDependencias(c));
		return firmaDocumentoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
