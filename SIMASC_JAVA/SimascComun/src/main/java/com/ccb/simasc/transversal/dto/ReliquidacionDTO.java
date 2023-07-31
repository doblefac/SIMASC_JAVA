package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Reliquidacion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ReliquidacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ReliquidacionDTO extends IDTO<Reliquidacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idReliquidacion;

	private String tipo;		
	private Date fecha;		
	private String motivo;		
	private BigDecimal valor;		
	private String ordenDePago;		
	private boolean pagada;		
	private Long nuevaCuantia;		
	private Long idCaso;
	private Long porcentajeDevolucion;
	private Date fechaPago;		
	private String numeroRecibo;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ReliquidacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdReliquidacion(){
		return this.idReliquidacion;
	}
	
	public void setIdReliquidacion(Long idReliquidacion){
		this.idReliquidacion = idReliquidacion;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
		
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
	}
		
	public String getOrdenDePago(){
		return this.ordenDePago;
	}
	
	public void setOrdenDePago(String ordenDePago){
		this.ordenDePago = ordenDePago;
	}
		
	public boolean getPagada(){
		return this.pagada;
	}
	
	public void setPagada(boolean pagada){
		this.pagada = pagada;
	}
		
	public Long getNuevaCuantia(){
		return this.nuevaCuantia;
	}
	
	public void setNuevaCuantia(Long nuevaCuantia){
		this.nuevaCuantia = nuevaCuantia;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getNumeroRecibo(){
		return this.numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo){
		this.numeroRecibo = numeroRecibo;
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
	
	public Long getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	public void setPorcentajeDevolucion(Long porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idReliquidacion);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.ordenDePago);
        hash = 37 * hash + (this.pagada ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.nuevaCuantia);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.porcentajeDevolucion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ReliquidacionDTO que se pasa
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
        final ReliquidacionDTO other = (ReliquidacionDTO) obj;
                
        if (!Objects.equals(this.idReliquidacion, other.idReliquidacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.ordenDePago, other.ordenDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.pagada, other.pagada)) {
            return false;
        }
        
        if (!Objects.equals(this.nuevaCuantia, other.nuevaCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroRecibo, other.numeroRecibo)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.porcentajeDevolucion, other.porcentajeDevolucion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public ReliquidacionDTO transformarSinDependencias(Reliquidacion obj) {
		ReliquidacionDTO reliquidacionDTO = new ReliquidacionDTO();
		
		reliquidacionDTO.setIdReliquidacion(obj.getIdReliquidacion());
		reliquidacionDTO.setTipo(obj.getTipo());
		reliquidacionDTO.setFecha(obj.getFecha());
		reliquidacionDTO.setMotivo(obj.getMotivo());
		reliquidacionDTO.setValor(obj.getValor());
		reliquidacionDTO.setOrdenDePago(obj.getOrdenDePago());
		reliquidacionDTO.setPagada(obj.getPagada());
		reliquidacionDTO.setNuevaCuantia(obj.getNuevaCuantia());
		reliquidacionDTO.setIdCaso(obj.getIdCaso());
		reliquidacionDTO.setPorcentajeDevolucion(obj.getPorcentajeDevolucion());
		reliquidacionDTO.setFechaPago(obj.getFechaPago());
		reliquidacionDTO.setNumeroRecibo(obj.getNumeroRecibo());
		reliquidacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		reliquidacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		reliquidacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return reliquidacionDTO;
	}

	@Override
	public ReliquidacionDTO transformarConDependencias(Reliquidacion obj) {
		ReliquidacionDTO reliquidacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return reliquidacionDTO;
	}

	@Override
	public Reliquidacion transformarEntidadSinDependencias(Reliquidacion obj) {
		Reliquidacion reliquidacion = new Reliquidacion();
		
		reliquidacion.setIdReliquidacion(obj.getIdReliquidacion());
		
		reliquidacion.setTipo(obj.getTipo());
		reliquidacion.setFecha(obj.getFecha());
		reliquidacion.setMotivo(obj.getMotivo());
		reliquidacion.setValor(obj.getValor());
		reliquidacion.setOrdenDePago(obj.getOrdenDePago());
		reliquidacion.setPagada(obj.getPagada());
		reliquidacion.setNuevaCuantia(obj.getNuevaCuantia());
		reliquidacion.setIdCaso(obj.getIdCaso());
		reliquidacion.setPorcentajeDevolucion(obj.getPorcentajeDevolucion());
		reliquidacion.setFechaPago(obj.getFechaPago());
		reliquidacion.setNumeroRecibo(obj.getNumeroRecibo());
		reliquidacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		reliquidacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		reliquidacion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return reliquidacion;
	}


	@Override
	public Reliquidacion transformarEntidadConDependencias(Reliquidacion obj) {
		Reliquidacion reliquidacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return reliquidacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Reliquidacion> coleccion) {
		List<ReliquidacionDTO> reliquidacionDTOList = new ArrayList<>();
		for(Reliquidacion c : coleccion)
			reliquidacionDTOList.add(transformarConDependencias(c));
		return reliquidacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Reliquidacion> coleccion) {
		List<ReliquidacionDTO> reliquidacionDTOList = new ArrayList<>();
		for(Reliquidacion c : coleccion)
			reliquidacionDTOList.add(transformarSinDependencias(c));
		return reliquidacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Reliquidacion> coleccion) {
		List<Reliquidacion> reliquidacionList = new ArrayList<>();
		for(Reliquidacion c : coleccion)
			reliquidacionList.add(transformarEntidadConDependencias(c));
		return reliquidacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Reliquidacion> coleccion) {
		List<Reliquidacion> reliquidacionList = new ArrayList<>();
		for(Reliquidacion c : coleccion)
			reliquidacionList.add(transformarEntidadSinDependencias(c));
		return reliquidacionList;
	}


	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
