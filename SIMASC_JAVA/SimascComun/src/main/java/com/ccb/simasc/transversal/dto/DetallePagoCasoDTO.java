package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.DetallePagoCasoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DetallePagoCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DetallePagoCasoDTO extends IDTO<DetallePagoCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private DetallePagoCasoPK detallePagoCasoPK;

	private Long valor;		
	private String servicioCaja;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DetallePagoCasoDTO(){
		detallePagoCasoPK = new DetallePagoCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DetallePagoCasoPK getDetallePagoCasoPK(){
		return this.detallePagoCasoPK;
	}
	
	public void setDetallePagoCasoPK(DetallePagoCasoPK detallePagoCasoPK){
		this.detallePagoCasoPK   = detallePagoCasoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.detallePagoCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.servicioCaja);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetallePagoCasoDTO que se pasa
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
        final DetallePagoCasoDTO other = (DetallePagoCasoDTO) obj;
                
        if (!Objects.equals(this.detallePagoCasoPK, other.detallePagoCasoPK)) {
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
	public DetallePagoCasoDTO transformarSinDependencias(DetallePagoCaso obj) {
		DetallePagoCasoDTO detallePagoCasoDTO = new DetallePagoCasoDTO();
		
		detallePagoCasoDTO.setDetallePagoCasoPK(obj.getDetallePagoCasoPK());
		detallePagoCasoDTO.setValor(obj.getValor());
		detallePagoCasoDTO.setServicioCaja(obj.getServicioCaja());
		detallePagoCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detallePagoCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detallePagoCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detallePagoCasoDTO;
	}

	@Override
	public DetallePagoCasoDTO transformarConDependencias(DetallePagoCaso obj) {
		DetallePagoCasoDTO detallePagoCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return detallePagoCasoDTO;
	}

	@Override
	public DetallePagoCaso transformarEntidadSinDependencias(DetallePagoCaso obj) {
		DetallePagoCaso detallePagoCaso = new DetallePagoCaso();
		
		detallePagoCaso.setDetallePagoCasoPK(obj.getDetallePagoCasoPK());
		
		detallePagoCaso.setValor(obj.getValor());
		detallePagoCaso.setServicioCaja(obj.getServicioCaja());
		detallePagoCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detallePagoCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detallePagoCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detallePagoCaso;
	}


	@Override
	public DetallePagoCaso transformarEntidadConDependencias(DetallePagoCaso obj) {
		DetallePagoCaso detallePagoCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return detallePagoCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DetallePagoCaso> coleccion) {
		List<DetallePagoCasoDTO> detallePagoCasoDTOList = new ArrayList<>();
		for(DetallePagoCaso c : coleccion)
			detallePagoCasoDTOList.add(transformarConDependencias(c));
		return detallePagoCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DetallePagoCaso> coleccion) {
		List<DetallePagoCasoDTO> detallePagoCasoDTOList = new ArrayList<>();
		for(DetallePagoCaso c : coleccion)
			detallePagoCasoDTOList.add(transformarSinDependencias(c));
		return detallePagoCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DetallePagoCaso> coleccion) {
		List<DetallePagoCaso> detallePagoCasoList = new ArrayList<>();
		for(DetallePagoCaso c : coleccion)
			detallePagoCasoList.add(transformarEntidadConDependencias(c));
		return detallePagoCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DetallePagoCaso> coleccion) {
		List<DetallePagoCaso> detallePagoCasoList = new ArrayList<>();
		for(DetallePagoCaso c : coleccion)
			detallePagoCasoList.add(transformarEntidadSinDependencias(c));
		return detallePagoCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
