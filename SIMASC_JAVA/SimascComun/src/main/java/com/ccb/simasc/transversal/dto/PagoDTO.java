package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DAO que contiene la información de la entidad PagoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PagoDTO implements Serializable{	

	private Long idPago;

	private Long valorFijado;		
	private java.util.Date fechaFijacion;		
	private java.util.Date fechaLimitePago;		
	private Long valorPagado;		
	private java.util.Date fechaPago;		
	private String tipoPago;		
	private String idUsuarioModificacion;		
	private java.util.Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	private Long idRol;		
	private Long idPersona;		

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
    public PagoDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	public Long getIdPago(){
		return this.idPago;
	}
	
	public void setIdPago(Long idPago){
		this.idPago = idPago;
	}
	
	public Long getValorFijado(){
		return this.valorFijado;
	}
	
	public void setValorFijado(Long valorFijado){
		this.valorFijado = valorFijado;
	}
		
	public java.util.Date getFechaFijacion(){
		return this.fechaFijacion;
	}
	
	public void setFechaFijacion(java.util.Date fechaFijacion){
		this.fechaFijacion = fechaFijacion;
	}
		
	public java.util.Date getFechaLimitePago(){
		return this.fechaLimitePago;
	}
	
	public void setFechaLimitePago(java.util.Date fechaLimitePago){
		this.fechaLimitePago = fechaLimitePago;
	}
		
	public Long getValorPagado(){
		return this.valorPagado;
	}
	
	public void setValorPagado(Long valorPagado){
		this.valorPagado = valorPagado;
	}
		
	public java.util.Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(java.util.Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getTipoPago(){
		return this.tipoPago;
	}
	
	public void setTipoPago(String tipoPago){
		this.tipoPago = tipoPago;
	}
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public java.util.Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(java.util.Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPago);        
        hash = 37 * hash + Objects.hashCode(this.valorFijado);
        hash = 37 * hash + Objects.hashCode(this.fechaFijacion);
        hash = 37 * hash + Objects.hashCode(this.fechaLimitePago);
        hash = 37 * hash + Objects.hashCode(this.valorPagado);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.tipoPago);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PagoDTO que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PagoDTO other = (PagoDTO) obj;
                
        if (!Objects.equals(this.idPago, other.idPago)) {
            return false;
        }
        
        if (!Objects.equals(this.valorFijado, other.valorFijado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFijacion, other.fechaFijacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaLimitePago, other.fechaLimitePago)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPagado, other.valorPagado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPago, other.tipoPago)) {
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
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

