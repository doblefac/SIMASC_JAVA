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

import com.ccb.simasc.transversal.entidades.ConvenioCentro;
import com.ccb.simasc.transversal.entidades.ConvenioCentroPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ConvenioCentroDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ConvenioCentroDTO extends IDTO<ConvenioCentro> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ConvenioCentroPK convenioCentroPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ConvenioCentroDTO(){
		convenioCentroPK = new ConvenioCentroPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ConvenioCentroPK getConvenioCentroPK(){
		return this.convenioCentroPK;
	}
	
	public void setConvenioCentroPK(ConvenioCentroPK convenioCentroPK){
		this.convenioCentroPK   = convenioCentroPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.convenioCentroPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ConvenioCentroDTO que se pasa
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
        final ConvenioCentroDTO other = (ConvenioCentroDTO) obj;
                
        if (!Objects.equals(this.convenioCentroPK, other.convenioCentroPK)) {
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
	public ConvenioCentroDTO transformarSinDependencias(ConvenioCentro obj) {
		ConvenioCentroDTO convenioCentroDTO = new ConvenioCentroDTO();
		
		convenioCentroDTO.setConvenioCentroPK(obj.getConvenioCentroPK());
		convenioCentroDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		convenioCentroDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		convenioCentroDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return convenioCentroDTO;
	}

	@Override
	public ConvenioCentroDTO transformarConDependencias(ConvenioCentro obj) {
		ConvenioCentroDTO convenioCentroDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return convenioCentroDTO;
	}

	@Override
	public ConvenioCentro transformarEntidadSinDependencias(ConvenioCentro obj) {
		ConvenioCentro convenioCentro = new ConvenioCentro();
		
		convenioCentro.setConvenioCentroPK(obj.getConvenioCentroPK());
		
		convenioCentro.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		convenioCentro.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		convenioCentro.setEstadoRegistro(obj.getEstadoRegistro());
		
		return convenioCentro;
	}


	@Override
	public ConvenioCentro transformarEntidadConDependencias(ConvenioCentro obj) {
		ConvenioCentro convenioCentro = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return convenioCentro;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ConvenioCentro> coleccion) {
		List<ConvenioCentroDTO> convenioCentroDTOList = new ArrayList<>();
		for(ConvenioCentro c : coleccion)
			convenioCentroDTOList.add(transformarConDependencias(c));
		return convenioCentroDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ConvenioCentro> coleccion) {
		List<ConvenioCentroDTO> convenioCentroDTOList = new ArrayList<>();
		for(ConvenioCentro c : coleccion)
			convenioCentroDTOList.add(transformarSinDependencias(c));
		return convenioCentroDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ConvenioCentro> coleccion) {
		List<ConvenioCentro> convenioCentroList = new ArrayList<>();
		for(ConvenioCentro c : coleccion)
			convenioCentroList.add(transformarEntidadConDependencias(c));
		return convenioCentroList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ConvenioCentro> coleccion) {
		List<ConvenioCentro> convenioCentroList = new ArrayList<>();
		for(ConvenioCentro c : coleccion)
			convenioCentroList.add(transformarEntidadSinDependencias(c));
		return convenioCentroList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
