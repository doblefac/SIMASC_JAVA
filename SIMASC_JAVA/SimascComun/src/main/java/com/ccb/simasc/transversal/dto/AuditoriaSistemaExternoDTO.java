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

import com.ccb.simasc.transversal.entidades.AuditoriaSistemaExterno;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AuditoriaSistemaExternoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AuditoriaSistemaExternoDTO extends IDTO<AuditoriaSistemaExterno> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idAuditoriaSistemaExterno;

	private String servicio;		
	private Long idCaso;		
	private String operacionSistemaExterno;		
	private Date fechaOperacion;		
	private String usuarioOperacion;		
	private String idCasoMinjusticia;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AuditoriaSistemaExternoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAuditoriaSistemaExterno(){
		return this.idAuditoriaSistemaExterno;
	}
	
	public void setIdAuditoriaSistemaExterno(Long idAuditoriaSistemaExterno){
		this.idAuditoriaSistemaExterno = idAuditoriaSistemaExterno;
	}
	
	public String getServicio(){
		return this.servicio;
	}
	
	public void setServicio(String servicio){
		this.servicio = servicio;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getOperacionSistemaExterno(){
		return this.operacionSistemaExterno;
	}
	
	public void setOperacionSistemaExterno(String operacionSistemaExterno){
		this.operacionSistemaExterno = operacionSistemaExterno;
	}
		
	public Date getFechaOperacion(){
		return this.fechaOperacion;
	}
	
	public void setFechaOperacion(Date fechaOperacion){
		this.fechaOperacion = fechaOperacion;
	}
		
	public String getUsuarioOperacion(){
		return this.usuarioOperacion;
	}
	
	public void setUsuarioOperacion(String usuarioOperacion){
		this.usuarioOperacion = usuarioOperacion;
	}
		
	public String getIdCasoMinjusticia(){
		return this.idCasoMinjusticia;
	}
	
	public void setIdCasoMinjusticia(String idCasoMinjusticia){
		this.idCasoMinjusticia = idCasoMinjusticia;
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
        
        hash = 37 * hash + Objects.hashCode(this.idAuditoriaSistemaExterno);        
        hash = 37 * hash + Objects.hashCode(this.servicio);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.operacionSistemaExterno);
        hash = 37 * hash + Objects.hashCode(this.fechaOperacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioOperacion);
        hash = 37 * hash + Objects.hashCode(this.idCasoMinjusticia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AuditoriaSistemaExternoDTO que se pasa
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
        final AuditoriaSistemaExternoDTO other = (AuditoriaSistemaExternoDTO) obj;
                
        if (!Objects.equals(this.idAuditoriaSistemaExterno, other.idAuditoriaSistemaExterno)) {
            return false;
        }
        
        if (!Objects.equals(this.servicio, other.servicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.operacionSistemaExterno, other.operacionSistemaExterno)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaOperacion, other.fechaOperacion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioOperacion, other.usuarioOperacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoMinjusticia, other.idCasoMinjusticia)) {
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
	public AuditoriaSistemaExternoDTO transformarSinDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExternoDTO auditoriaSistemaExternoDTO = new AuditoriaSistemaExternoDTO();
		
		auditoriaSistemaExternoDTO.setIdAuditoriaSistemaExterno(obj.getIdAuditoriaSistemaExterno());
		auditoriaSistemaExternoDTO.setServicio(obj.getServicio());
		auditoriaSistemaExternoDTO.setIdCaso(obj.getIdCaso());
		auditoriaSistemaExternoDTO.setOperacionSistemaExterno(obj.getOperacionSistemaExterno());
		auditoriaSistemaExternoDTO.setFechaOperacion(obj.getFechaOperacion());
		auditoriaSistemaExternoDTO.setUsuarioOperacion(obj.getUsuarioOperacion());
		auditoriaSistemaExternoDTO.setIdCasoMinjusticia(obj.getIdCasoMinjusticia());
		auditoriaSistemaExternoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		auditoriaSistemaExternoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		auditoriaSistemaExternoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return auditoriaSistemaExternoDTO;
	}

	@Override
	public AuditoriaSistemaExternoDTO transformarConDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExternoDTO auditoriaSistemaExternoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		// protected region modificaciones transformarConDependencias end
		
		return auditoriaSistemaExternoDTO;
	}

	@Override
	public AuditoriaSistemaExterno transformarEntidadSinDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExterno auditoriaSistemaExterno = new AuditoriaSistemaExterno();
		
		auditoriaSistemaExterno.setIdAuditoriaSistemaExterno(obj.getIdAuditoriaSistemaExterno());
		
		auditoriaSistemaExterno.setServicio(obj.getServicio());
		auditoriaSistemaExterno.setIdCaso(obj.getIdCaso());
		auditoriaSistemaExterno.setOperacionSistemaExterno(obj.getOperacionSistemaExterno());
		auditoriaSistemaExterno.setFechaOperacion(obj.getFechaOperacion());
		auditoriaSistemaExterno.setUsuarioOperacion(obj.getUsuarioOperacion());
		auditoriaSistemaExterno.setIdCasoMinjusticia(obj.getIdCasoMinjusticia());
		auditoriaSistemaExterno.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		auditoriaSistemaExterno.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		auditoriaSistemaExterno.setEstadoRegistro(obj.getEstadoRegistro());
		
		return auditoriaSistemaExterno;
	}


	@Override
	public AuditoriaSistemaExterno transformarEntidadConDependencias(AuditoriaSistemaExterno obj) {
		AuditoriaSistemaExterno auditoriaSistemaExterno = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return auditoriaSistemaExterno;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AuditoriaSistemaExterno> coleccion) {
		List<AuditoriaSistemaExternoDTO> auditoriaSistemaExternoDTOList = new ArrayList<>();
		for(AuditoriaSistemaExterno c : coleccion)
			auditoriaSistemaExternoDTOList.add(transformarConDependencias(c));
		return auditoriaSistemaExternoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AuditoriaSistemaExterno> coleccion) {
		List<AuditoriaSistemaExternoDTO> auditoriaSistemaExternoDTOList = new ArrayList<>();
		for(AuditoriaSistemaExterno c : coleccion)
			auditoriaSistemaExternoDTOList.add(transformarSinDependencias(c));
		return auditoriaSistemaExternoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AuditoriaSistemaExterno> coleccion) {
		List<AuditoriaSistemaExterno> auditoriaSistemaExternoList = new ArrayList<>();
		for(AuditoriaSistemaExterno c : coleccion)
			auditoriaSistemaExternoList.add(transformarEntidadConDependencias(c));
		return auditoriaSistemaExternoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AuditoriaSistemaExterno> coleccion) {
		List<AuditoriaSistemaExterno> auditoriaSistemaExternoList = new ArrayList<>();
		for(AuditoriaSistemaExterno c : coleccion)
			auditoriaSistemaExternoList.add(transformarEntidadSinDependencias(c));
		return auditoriaSistemaExternoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
