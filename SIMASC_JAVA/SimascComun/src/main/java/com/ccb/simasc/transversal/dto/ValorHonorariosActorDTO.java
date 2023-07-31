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

import com.ccb.simasc.transversal.entidades.ValorHonorariosActor;
import com.ccb.simasc.transversal.entidades.ValorHonorariosActorPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ValorHonorariosActorDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ValorHonorariosActorDTO extends IDTO<ValorHonorariosActor> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ValorHonorariosActorPK valorHonorariosActorPK;

	private BigDecimal valor;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ValorHonorariosActorDTO(){
		valorHonorariosActorPK = new ValorHonorariosActorPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ValorHonorariosActorPK getValorHonorariosActorPK(){
		return this.valorHonorariosActorPK;
	}
	
	public void setValorHonorariosActorPK(ValorHonorariosActorPK valorHonorariosActorPK){
		this.valorHonorariosActorPK   = valorHonorariosActorPK ;
	}  
	
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
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
        
        hash = 37 * hash + Objects.hashCode(this.valorHonorariosActorPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorHonorariosActorDTO que se pasa
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
        final ValorHonorariosActorDTO other = (ValorHonorariosActorDTO) obj;
                
        if (!Objects.equals(this.valorHonorariosActorPK, other.valorHonorariosActorPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
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
	public ValorHonorariosActorDTO transformarSinDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActorDTO valorHonorariosActorDTO = new ValorHonorariosActorDTO();
		
		valorHonorariosActorDTO.setValorHonorariosActorPK(obj.getValorHonorariosActorPK());
		valorHonorariosActorDTO.setValor(obj.getValor());
		valorHonorariosActorDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		valorHonorariosActorDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		valorHonorariosActorDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return valorHonorariosActorDTO;
	}

	@Override
	public ValorHonorariosActorDTO transformarConDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActorDTO valorHonorariosActorDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return valorHonorariosActorDTO;
	}

	@Override
	public ValorHonorariosActor transformarEntidadSinDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActor valorHonorariosActor = new ValorHonorariosActor();
		
		valorHonorariosActor.setValorHonorariosActorPK(obj.getValorHonorariosActorPK());
		
		valorHonorariosActor.setValor(obj.getValor());
		valorHonorariosActor.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		valorHonorariosActor.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		valorHonorariosActor.setEstadoRegistro(obj.getEstadoRegistro());
		
		return valorHonorariosActor;
	}


	@Override
	public ValorHonorariosActor transformarEntidadConDependencias(ValorHonorariosActor obj) {
		ValorHonorariosActor valorHonorariosActor = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return valorHonorariosActor;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ValorHonorariosActor> coleccion) {
		List<ValorHonorariosActorDTO> valorHonorariosActorDTOList = new ArrayList<>();
		for(ValorHonorariosActor c : coleccion)
			valorHonorariosActorDTOList.add(transformarConDependencias(c));
		return valorHonorariosActorDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ValorHonorariosActor> coleccion) {
		List<ValorHonorariosActorDTO> valorHonorariosActorDTOList = new ArrayList<>();
		for(ValorHonorariosActor c : coleccion)
			valorHonorariosActorDTOList.add(transformarSinDependencias(c));
		return valorHonorariosActorDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ValorHonorariosActor> coleccion) {
		List<ValorHonorariosActor> valorHonorariosActorList = new ArrayList<>();
		for(ValorHonorariosActor c : coleccion)
			valorHonorariosActorList.add(transformarEntidadConDependencias(c));
		return valorHonorariosActorList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ValorHonorariosActor> coleccion) {
		List<ValorHonorariosActor> valorHonorariosActorList = new ArrayList<>();
		for(ValorHonorariosActor c : coleccion)
			valorHonorariosActorList.add(transformarEntidadSinDependencias(c));
		return valorHonorariosActorList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
