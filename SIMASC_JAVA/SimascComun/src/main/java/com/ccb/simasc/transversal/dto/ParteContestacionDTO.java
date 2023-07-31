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

import com.ccb.simasc.transversal.entidades.ParteContestacion;
import com.ccb.simasc.transversal.entidades.ParteContestacionPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParteContestacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParteContestacionDTO extends IDTO<ParteContestacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ParteContestacionPK parteContestacionPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParteContestacionDTO(){
		parteContestacionPK = new ParteContestacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParteContestacionPK getParteContestacionPK(){
		return this.parteContestacionPK;
	}
	
	public void setParteContestacionPK(ParteContestacionPK parteContestacionPK){
		this.parteContestacionPK   = parteContestacionPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.parteContestacionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteContestacionDTO que se pasa
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
        final ParteContestacionDTO other = (ParteContestacionDTO) obj;
                
        if (!Objects.equals(this.parteContestacionPK, other.parteContestacionPK)) {
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
	public ParteContestacionDTO transformarSinDependencias(ParteContestacion obj) {
		ParteContestacionDTO parteContestacionDTO = new ParteContestacionDTO();
		
		parteContestacionDTO.setParteContestacionPK(obj.getParteContestacionPK());
		parteContestacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parteContestacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parteContestacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parteContestacionDTO;
	}

	@Override
	public ParteContestacionDTO transformarConDependencias(ParteContestacion obj) {
		ParteContestacionDTO parteContestacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parteContestacionDTO;
	}

	@Override
	public ParteContestacion transformarEntidadSinDependencias(ParteContestacion obj) {
		ParteContestacion parteContestacion = new ParteContestacion();
		
		parteContestacion.setParteContestacionPK(obj.getParteContestacionPK());
		
		parteContestacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parteContestacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parteContestacion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parteContestacion;
	}


	@Override
	public ParteContestacion transformarEntidadConDependencias(ParteContestacion obj) {
		ParteContestacion parteContestacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parteContestacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParteContestacion> coleccion) {
		List<ParteContestacionDTO> parteContestacionDTOList = new ArrayList<>();
		for(ParteContestacion c : coleccion)
			parteContestacionDTOList.add(transformarConDependencias(c));
		return parteContestacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParteContestacion> coleccion) {
		List<ParteContestacionDTO> parteContestacionDTOList = new ArrayList<>();
		for(ParteContestacion c : coleccion)
			parteContestacionDTOList.add(transformarSinDependencias(c));
		return parteContestacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParteContestacion> coleccion) {
		List<ParteContestacion> parteContestacionList = new ArrayList<>();
		for(ParteContestacion c : coleccion)
			parteContestacionList.add(transformarEntidadConDependencias(c));
		return parteContestacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParteContestacion> coleccion) {
		List<ParteContestacion> parteContestacionList = new ArrayList<>();
		for(ParteContestacion c : coleccion)
			parteContestacionList.add(transformarEntidadSinDependencias(c));
		return parteContestacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
