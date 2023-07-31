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

import com.ccb.simasc.transversal.entidades.DetalleReliquidacion;
import com.ccb.simasc.transversal.entidades.DetalleReliquidacionPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DetalleReliquidacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DetalleReliquidacionDTO extends IDTO<DetalleReliquidacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private DetalleReliquidacionPK detalleReliquidacionPK;

	private Long valor;		
	private String servicioCaja;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DetalleReliquidacionDTO(){
		detalleReliquidacionPK = new DetalleReliquidacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DetalleReliquidacionPK getDetalleReliquidacionPK(){
		return this.detalleReliquidacionPK;
	}
	
	public void setDetalleReliquidacionPK(DetalleReliquidacionPK detalleReliquidacionPK){
		this.detalleReliquidacionPK   = detalleReliquidacionPK ;
	}  
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public String getServicioCaja(){
		return this.servicioCaja;
	}
	
	public void setServicioCaja(String servicioCaja){
		this.servicioCaja = servicioCaja;
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
        
        hash = 37 * hash + Objects.hashCode(this.detalleReliquidacionPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.servicioCaja);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetalleReliquidacionDTO que se pasa
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
        final DetalleReliquidacionDTO other = (DetalleReliquidacionDTO) obj;
                
        if (!Objects.equals(this.detalleReliquidacionPK, other.detalleReliquidacionPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.servicioCaja, other.servicioCaja)) {
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
	public DetalleReliquidacionDTO transformarSinDependencias(DetalleReliquidacion obj) {
		DetalleReliquidacionDTO detalleReliquidacionDTO = new DetalleReliquidacionDTO();
		
		detalleReliquidacionDTO.setDetalleReliquidacionPK(obj.getDetalleReliquidacionPK());
		detalleReliquidacionDTO.setValor(obj.getValor());
		detalleReliquidacionDTO.setServicioCaja(obj.getServicioCaja());
		detalleReliquidacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detalleReliquidacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detalleReliquidacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detalleReliquidacionDTO;
	}

	@Override
	public DetalleReliquidacionDTO transformarConDependencias(DetalleReliquidacion obj) {
		DetalleReliquidacionDTO detalleReliquidacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return detalleReliquidacionDTO;
	}

	@Override
	public DetalleReliquidacion transformarEntidadSinDependencias(DetalleReliquidacion obj) {
		DetalleReliquidacion detalleReliquidacion = new DetalleReliquidacion();
		
		detalleReliquidacion.setDetalleReliquidacionPK(obj.getDetalleReliquidacionPK());
		
		detalleReliquidacion.setValor(obj.getValor());
		detalleReliquidacion.setServicioCaja(obj.getServicioCaja());
		detalleReliquidacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detalleReliquidacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detalleReliquidacion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detalleReliquidacion;
	}


	@Override
	public DetalleReliquidacion transformarEntidadConDependencias(DetalleReliquidacion obj) {
		DetalleReliquidacion detalleReliquidacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return detalleReliquidacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DetalleReliquidacion> coleccion) {
		List<DetalleReliquidacionDTO> detalleReliquidacionDTOList = new ArrayList<>();
		for(DetalleReliquidacion c : coleccion)
			detalleReliquidacionDTOList.add(transformarConDependencias(c));
		return detalleReliquidacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DetalleReliquidacion> coleccion) {
		List<DetalleReliquidacionDTO> detalleReliquidacionDTOList = new ArrayList<>();
		for(DetalleReliquidacion c : coleccion)
			detalleReliquidacionDTOList.add(transformarSinDependencias(c));
		return detalleReliquidacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DetalleReliquidacion> coleccion) {
		List<DetalleReliquidacion> detalleReliquidacionList = new ArrayList<>();
		for(DetalleReliquidacion c : coleccion)
			detalleReliquidacionList.add(transformarEntidadConDependencias(c));
		return detalleReliquidacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DetalleReliquidacion> coleccion) {
		List<DetalleReliquidacion> detalleReliquidacionList = new ArrayList<>();
		for(DetalleReliquidacion c : coleccion)
			detalleReliquidacionList.add(transformarEntidadSinDependencias(c));
		return detalleReliquidacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
