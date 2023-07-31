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

import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametrizacionTarifasDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametrizacionTarifasDTO extends IDTO<ParametrizacionTarifas> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idParametrizacionTarifas;

	private String cantidadArbitros;		
	private String tipoTarifa;		
	private String moneda;		
	private String tipoValorRango;		
	private BigDecimal rangoInferior;		
	private BigDecimal rangoSuperior;		
	private String tipoValorMinimo;		
	private BigDecimal valorMinimo;		
	private String tipoValorMaximo;		
	private BigDecimal valorMaximo;		
	private String tipoActor;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idServicio;		
	
    public ParametrizacionTarifasDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdParametrizacionTarifas(){
		return this.idParametrizacionTarifas;
	}
	
	public void setIdParametrizacionTarifas(Long idParametrizacionTarifas){
		this.idParametrizacionTarifas = idParametrizacionTarifas;
	}
	
	public String getCantidadArbitros(){
		return this.cantidadArbitros;
	}
	
	public void setCantidadArbitros(String cantidadArbitros){
		this.cantidadArbitros = cantidadArbitros;
	}
		
	public String getTipoTarifa(){
		return this.tipoTarifa;
	}
	
	public void setTipoTarifa(String tipoTarifa){
		this.tipoTarifa = tipoTarifa;
	}
		
	public String getMoneda(){
		return this.moneda;
	}
	
	public void setMoneda(String moneda){
		this.moneda = moneda;
	}
		
	public String getTipoValorRango(){
		return this.tipoValorRango;
	}
	
	public void setTipoValorRango(String tipoValorRango){
		this.tipoValorRango = tipoValorRango;
	}
		
	public BigDecimal getRangoInferior(){
		return this.rangoInferior;
	}
	
	public void setRangoInferior(BigDecimal rangoInferior){
		this.rangoInferior = rangoInferior;
	}
		
	public BigDecimal getRangoSuperior(){
		return this.rangoSuperior;
	}
	
	public void setRangoSuperior(BigDecimal rangoSuperior){
		this.rangoSuperior = rangoSuperior;
	}
		
	public String getTipoValorMinimo(){
		return this.tipoValorMinimo;
	}
	
	public void setTipoValorMinimo(String tipoValorMinimo){
		this.tipoValorMinimo = tipoValorMinimo;
	}
		
	public BigDecimal getValorMinimo(){
		return this.valorMinimo;
	}
	
	public void setValorMinimo(BigDecimal valorMinimo){
		this.valorMinimo = valorMinimo;
	}
		
	public String getTipoValorMaximo(){
		return this.tipoValorMaximo;
	}
	
	public void setTipoValorMaximo(String tipoValorMaximo){
		this.tipoValorMaximo = tipoValorMaximo;
	}
		
	public BigDecimal getValorMaximo(){
		return this.valorMaximo;
	}
	
	public void setValorMaximo(BigDecimal valorMaximo){
		this.valorMaximo = valorMaximo;
	}
		
	public String getTipoActor(){
		return this.tipoActor;
	}
	
	public void setTipoActor(String tipoActor){
		this.tipoActor = tipoActor;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idParametrizacionTarifas);        
        hash = 37 * hash + Objects.hashCode(this.cantidadArbitros);
        hash = 37 * hash + Objects.hashCode(this.tipoTarifa);
        hash = 37 * hash + Objects.hashCode(this.moneda);
        hash = 37 * hash + Objects.hashCode(this.tipoValorRango);
        hash = 37 * hash + Objects.hashCode(this.rangoInferior);
        hash = 37 * hash + Objects.hashCode(this.rangoSuperior);
        hash = 37 * hash + Objects.hashCode(this.tipoValorMinimo);
        hash = 37 * hash + Objects.hashCode(this.valorMinimo);
        hash = 37 * hash + Objects.hashCode(this.tipoValorMaximo);
        hash = 37 * hash + Objects.hashCode(this.valorMaximo);
        hash = 37 * hash + Objects.hashCode(this.tipoActor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrizacionTarifasDTO que se pasa
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
        final ParametrizacionTarifasDTO other = (ParametrizacionTarifasDTO) obj;
                
        if (!Objects.equals(this.idParametrizacionTarifas, other.idParametrizacionTarifas)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadArbitros, other.cantidadArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTarifa, other.tipoTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.moneda, other.moneda)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorRango, other.tipoValorRango)) {
            return false;
        }
        
        if (!Objects.equals(this.rangoInferior, other.rangoInferior)) {
            return false;
        }
        
        if (!Objects.equals(this.rangoSuperior, other.rangoSuperior)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorMinimo, other.tipoValorMinimo)) {
            return false;
        }
        
        if (!Objects.equals(this.valorMinimo, other.valorMinimo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorMaximo, other.tipoValorMaximo)) {
            return false;
        }
        
        if (!Objects.equals(this.valorMaximo, other.valorMaximo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActor, other.tipoActor)) {
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
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
    
    @Override
	public ParametrizacionTarifasDTO transformarSinDependencias(ParametrizacionTarifas obj) {
		ParametrizacionTarifasDTO parametrizacionTarifasDTO = new ParametrizacionTarifasDTO();
		
		parametrizacionTarifasDTO.setIdParametrizacionTarifas(obj.getIdParametrizacionTarifas());
		parametrizacionTarifasDTO.setCantidadArbitros(obj.getCantidadArbitros());
		parametrizacionTarifasDTO.setTipoTarifa(obj.getTipoTarifa());
		parametrizacionTarifasDTO.setMoneda(obj.getMoneda());
		parametrizacionTarifasDTO.setTipoValorRango(obj.getTipoValorRango());
		parametrizacionTarifasDTO.setRangoInferior(obj.getRangoInferior());
		parametrizacionTarifasDTO.setRangoSuperior(obj.getRangoSuperior());
		parametrizacionTarifasDTO.setTipoValorMinimo(obj.getTipoValorMinimo());
		parametrizacionTarifasDTO.setValorMinimo(obj.getValorMinimo());
		parametrizacionTarifasDTO.setTipoValorMaximo(obj.getTipoValorMaximo());
		parametrizacionTarifasDTO.setValorMaximo(obj.getValorMaximo());
		parametrizacionTarifasDTO.setTipoActor(obj.getTipoActor());
		parametrizacionTarifasDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrizacionTarifasDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrizacionTarifasDTO.setEstadoRegistro(obj.getEstadoRegistro());
		parametrizacionTarifasDTO.setIdServicio(obj.getIdServicio());
		
		return parametrizacionTarifasDTO;
	}

	@Override
	public ParametrizacionTarifasDTO transformarConDependencias(ParametrizacionTarifas obj) {
		ParametrizacionTarifasDTO parametrizacionTarifasDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametrizacionTarifasDTO;
	}

	@Override
	public ParametrizacionTarifas transformarEntidadSinDependencias(ParametrizacionTarifas obj) {
		ParametrizacionTarifas parametrizacionTarifas = new ParametrizacionTarifas();
		
		parametrizacionTarifas.setIdParametrizacionTarifas(obj.getIdParametrizacionTarifas());
		
		parametrizacionTarifas.setCantidadArbitros(obj.getCantidadArbitros());
		parametrizacionTarifas.setTipoTarifa(obj.getTipoTarifa());
		parametrizacionTarifas.setMoneda(obj.getMoneda());
		parametrizacionTarifas.setTipoValorRango(obj.getTipoValorRango());
		parametrizacionTarifas.setRangoInferior(obj.getRangoInferior());
		parametrizacionTarifas.setRangoSuperior(obj.getRangoSuperior());
		parametrizacionTarifas.setTipoValorMinimo(obj.getTipoValorMinimo());
		parametrizacionTarifas.setValorMinimo(obj.getValorMinimo());
		parametrizacionTarifas.setTipoValorMaximo(obj.getTipoValorMaximo());
		parametrizacionTarifas.setValorMaximo(obj.getValorMaximo());
		parametrizacionTarifas.setTipoActor(obj.getTipoActor());
		parametrizacionTarifas.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametrizacionTarifas.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametrizacionTarifas.setEstadoRegistro(obj.getEstadoRegistro());
		parametrizacionTarifas.setIdServicio(obj.getIdServicio());
		
		return parametrizacionTarifas;
	}


	@Override
	public ParametrizacionTarifas transformarEntidadConDependencias(ParametrizacionTarifas obj) {
		ParametrizacionTarifas parametrizacionTarifas = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametrizacionTarifas;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametrizacionTarifas> coleccion) {
		List<ParametrizacionTarifasDTO> parametrizacionTarifasDTOList = new ArrayList<>();
		for(ParametrizacionTarifas c : coleccion)
			parametrizacionTarifasDTOList.add(transformarConDependencias(c));
		return parametrizacionTarifasDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametrizacionTarifas> coleccion) {
		List<ParametrizacionTarifasDTO> parametrizacionTarifasDTOList = new ArrayList<>();
		for(ParametrizacionTarifas c : coleccion)
			parametrizacionTarifasDTOList.add(transformarSinDependencias(c));
		return parametrizacionTarifasDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametrizacionTarifas> coleccion) {
		List<ParametrizacionTarifas> parametrizacionTarifasList = new ArrayList<>();
		for(ParametrizacionTarifas c : coleccion)
			parametrizacionTarifasList.add(transformarEntidadConDependencias(c));
		return parametrizacionTarifasList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametrizacionTarifas> coleccion) {
		List<ParametrizacionTarifas> parametrizacionTarifasList = new ArrayList<>();
		for(ParametrizacionTarifas c : coleccion)
			parametrizacionTarifasList.add(transformarEntidadSinDependencias(c));
		return parametrizacionTarifasList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
