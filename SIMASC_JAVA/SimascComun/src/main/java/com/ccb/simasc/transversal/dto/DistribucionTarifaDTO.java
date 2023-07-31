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

import com.ccb.simasc.transversal.entidades.DistribucionTarifa;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DistribucionTarifaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DistribucionTarifaDTO extends IDTO<DistribucionTarifa> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idDistribucionTarifa;

	private String cantidadArbitros;		
	private String tipoActor;		
	private BigDecimal valorPorcentual;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DistribucionTarifaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdDistribucionTarifa(){
		return this.idDistribucionTarifa;
	}
	
	public void setIdDistribucionTarifa(Long idDistribucionTarifa){
		this.idDistribucionTarifa = idDistribucionTarifa;
	}
	
	public String getCantidadArbitros(){
		return this.cantidadArbitros;
	}
	
	public void setCantidadArbitros(String cantidadArbitros){
		this.cantidadArbitros = cantidadArbitros;
	}
		
	public String getTipoActor(){
		return this.tipoActor;
	}
	
	public void setTipoActor(String tipoActor){
		this.tipoActor = tipoActor;
	}
		
	public BigDecimal getValorPorcentual(){
		return this.valorPorcentual;
	}
	
	public void setValorPorcentual(BigDecimal valorPorcentual){
		this.valorPorcentual = valorPorcentual;
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
        
        hash = 37 * hash + Objects.hashCode(this.idDistribucionTarifa);        
        hash = 37 * hash + Objects.hashCode(this.cantidadArbitros);
        hash = 37 * hash + Objects.hashCode(this.tipoActor);
        hash = 37 * hash + Objects.hashCode(this.valorPorcentual);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DistribucionTarifaDTO que se pasa
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
        final DistribucionTarifaDTO other = (DistribucionTarifaDTO) obj;
                
        if (!Objects.equals(this.idDistribucionTarifa, other.idDistribucionTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadArbitros, other.cantidadArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActor, other.tipoActor)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPorcentual, other.valorPorcentual)) {
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
	public DistribucionTarifaDTO transformarSinDependencias(DistribucionTarifa obj) {
		DistribucionTarifaDTO distribucionTarifaDTO = new DistribucionTarifaDTO();
		
		distribucionTarifaDTO.setIdDistribucionTarifa(obj.getIdDistribucionTarifa());
		distribucionTarifaDTO.setCantidadArbitros(obj.getCantidadArbitros());
		distribucionTarifaDTO.setTipoActor(obj.getTipoActor());
		distribucionTarifaDTO.setValorPorcentual(obj.getValorPorcentual());
		distribucionTarifaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		distribucionTarifaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		distribucionTarifaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return distribucionTarifaDTO;
	}

	@Override
	public DistribucionTarifaDTO transformarConDependencias(DistribucionTarifa obj) {
		DistribucionTarifaDTO distribucionTarifaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return distribucionTarifaDTO;
	}

	@Override
	public DistribucionTarifa transformarEntidadSinDependencias(DistribucionTarifa obj) {
		DistribucionTarifa distribucionTarifa = new DistribucionTarifa();
		
		distribucionTarifa.setIdDistribucionTarifa(obj.getIdDistribucionTarifa());
		
		distribucionTarifa.setCantidadArbitros(obj.getCantidadArbitros());
		distribucionTarifa.setTipoActor(obj.getTipoActor());
		distribucionTarifa.setValorPorcentual(obj.getValorPorcentual());
		distribucionTarifa.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		distribucionTarifa.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		distribucionTarifa.setEstadoRegistro(obj.getEstadoRegistro());
		
		return distribucionTarifa;
	}


	@Override
	public DistribucionTarifa transformarEntidadConDependencias(DistribucionTarifa obj) {
		DistribucionTarifa distribucionTarifa = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return distribucionTarifa;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DistribucionTarifa> coleccion) {
		List<DistribucionTarifaDTO> distribucionTarifaDTOList = new ArrayList<>();
		for(DistribucionTarifa c : coleccion)
			distribucionTarifaDTOList.add(transformarConDependencias(c));
		return distribucionTarifaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DistribucionTarifa> coleccion) {
		List<DistribucionTarifaDTO> distribucionTarifaDTOList = new ArrayList<>();
		for(DistribucionTarifa c : coleccion)
			distribucionTarifaDTOList.add(transformarSinDependencias(c));
		return distribucionTarifaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DistribucionTarifa> coleccion) {
		List<DistribucionTarifa> distribucionTarifaList = new ArrayList<>();
		for(DistribucionTarifa c : coleccion)
			distribucionTarifaList.add(transformarEntidadConDependencias(c));
		return distribucionTarifaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DistribucionTarifa> coleccion) {
		List<DistribucionTarifa> distribucionTarifaList = new ArrayList<>();
		for(DistribucionTarifa c : coleccion)
			distribucionTarifaList.add(transformarEntidadSinDependencias(c));
		return distribucionTarifaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
