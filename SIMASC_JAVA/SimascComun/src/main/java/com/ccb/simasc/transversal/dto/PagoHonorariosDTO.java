package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.PagoHonorarios;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PagoHonorariosDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PagoHonorariosDTO extends IDTO<PagoHonorarios> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idPagoHonorarios;

	private BigDecimal valorPagado;		
	private Date fechaPago;		
	private String parteQuePaga;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	
    public PagoHonorariosDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPagoHonorarios(){
		return this.idPagoHonorarios;
	}
	
	public void setIdPagoHonorarios(Long idPagoHonorarios){
		this.idPagoHonorarios = idPagoHonorarios;
	}
	
	public BigDecimal getValorPagado(){
		return this.valorPagado;
	}
	
	public void setValorPagado(BigDecimal valorPagado){
		this.valorPagado = valorPagado;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getParteQuePaga(){
		return this.parteQuePaga;
	}
	
	public void setParteQuePaga(String parteQuePaga){
		this.parteQuePaga = parteQuePaga;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPagoHonorarios);        
        hash = 37 * hash + Objects.hashCode(this.valorPagado);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.parteQuePaga);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PagoHonorariosDTO que se pasa
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
        final PagoHonorariosDTO other = (PagoHonorariosDTO) obj;
                
        if (!Objects.equals(this.idPagoHonorarios, other.idPagoHonorarios)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPagado, other.valorPagado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQuePaga, other.parteQuePaga)) {
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
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    @Override
	public PagoHonorariosDTO transformarSinDependencias(PagoHonorarios obj) {
		PagoHonorariosDTO pagoHonorariosDTO = new PagoHonorariosDTO();
		
		pagoHonorariosDTO.setIdPagoHonorarios(obj.getIdPagoHonorarios());
		pagoHonorariosDTO.setValorPagado(obj.getValorPagado());
		pagoHonorariosDTO.setFechaPago(obj.getFechaPago());
		pagoHonorariosDTO.setParteQuePaga(obj.getParteQuePaga());
		pagoHonorariosDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pagoHonorariosDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pagoHonorariosDTO.setEstadoRegistro(obj.getEstadoRegistro());
		pagoHonorariosDTO.setIdCaso(obj.getIdCaso());
		
		return pagoHonorariosDTO;
	}

	@Override
	public PagoHonorariosDTO transformarConDependencias(PagoHonorarios obj) {
		PagoHonorariosDTO pagoHonorariosDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return pagoHonorariosDTO;
	}

	@Override
	public PagoHonorarios transformarEntidadSinDependencias(PagoHonorarios obj) {
		PagoHonorarios pagoHonorarios = new PagoHonorarios();
		
		pagoHonorarios.setIdPagoHonorarios(obj.getIdPagoHonorarios());
		
		pagoHonorarios.setValorPagado(obj.getValorPagado());
		pagoHonorarios.setFechaPago(obj.getFechaPago());
		pagoHonorarios.setParteQuePaga(obj.getParteQuePaga());
		pagoHonorarios.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pagoHonorarios.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pagoHonorarios.setEstadoRegistro(obj.getEstadoRegistro());
		pagoHonorarios.setIdCaso(obj.getIdCaso());
		
		return pagoHonorarios;
	}


	@Override
	public PagoHonorarios transformarEntidadConDependencias(PagoHonorarios obj) {
		PagoHonorarios pagoHonorarios = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return pagoHonorarios;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PagoHonorarios> coleccion) {
		List<PagoHonorariosDTO> pagoHonorariosDTOList = new ArrayList<>();
		for(PagoHonorarios c : coleccion)
			pagoHonorariosDTOList.add(transformarConDependencias(c));
		return pagoHonorariosDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PagoHonorarios> coleccion) {
		List<PagoHonorariosDTO> pagoHonorariosDTOList = new ArrayList<>();
		for(PagoHonorarios c : coleccion)
			pagoHonorariosDTOList.add(transformarSinDependencias(c));
		return pagoHonorariosDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PagoHonorarios> coleccion) {
		List<PagoHonorarios> pagoHonorariosList = new ArrayList<>();
		for(PagoHonorarios c : coleccion)
			pagoHonorariosList.add(transformarEntidadConDependencias(c));
		return pagoHonorariosList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PagoHonorarios> coleccion) {
		List<PagoHonorarios> pagoHonorariosList = new ArrayList<>();
		for(PagoHonorarios c : coleccion)
			pagoHonorariosList.add(transformarEntidadSinDependencias(c));
		return pagoHonorariosList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
