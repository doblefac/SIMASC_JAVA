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

import com.ccb.simasc.transversal.entidades.DiaFestivo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DiaFestivoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DiaFestivoDTO extends IDTO<DiaFestivo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idFestivo;

	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DiaFestivoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdFestivo(){
		return this.idFestivo;
	}
	
	public void setIdFestivo(Long idFestivo){
		this.idFestivo = idFestivo;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
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
        
        hash = 37 * hash + Objects.hashCode(this.idFestivo);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DiaFestivoDTO que se pasa
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
        final DiaFestivoDTO other = (DiaFestivoDTO) obj;
                
        if (!Objects.equals(this.idFestivo, other.idFestivo)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
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
	public DiaFestivoDTO transformarSinDependencias(DiaFestivo obj) {
		DiaFestivoDTO diaFestivoDTO = new DiaFestivoDTO();
		
		diaFestivoDTO.setIdFestivo(obj.getIdFestivo());
		diaFestivoDTO.setFecha(obj.getFecha());
		diaFestivoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		diaFestivoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		diaFestivoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return diaFestivoDTO;
	}

	@Override
	public DiaFestivoDTO transformarConDependencias(DiaFestivo obj) {
		DiaFestivoDTO diaFestivoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return diaFestivoDTO;
	}

	@Override
	public DiaFestivo transformarEntidadSinDependencias(DiaFestivo obj) {
		DiaFestivo diaFestivo = new DiaFestivo();
		
		diaFestivo.setIdFestivo(obj.getIdFestivo());
		
		diaFestivo.setFecha(obj.getFecha());
		diaFestivo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		diaFestivo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		diaFestivo.setEstadoRegistro(obj.getEstadoRegistro());
		
		return diaFestivo;
	}


	@Override
	public DiaFestivo transformarEntidadConDependencias(DiaFestivo obj) {
		DiaFestivo diaFestivo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return diaFestivo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DiaFestivo> coleccion) {
		List<DiaFestivoDTO> diaFestivoDTOList = new ArrayList<>();
		for(DiaFestivo c : coleccion)
			diaFestivoDTOList.add(transformarConDependencias(c));
		return diaFestivoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DiaFestivo> coleccion) {
		List<DiaFestivoDTO> diaFestivoDTOList = new ArrayList<>();
		for(DiaFestivo c : coleccion)
			diaFestivoDTOList.add(transformarSinDependencias(c));
		return diaFestivoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DiaFestivo> coleccion) {
		List<DiaFestivo> diaFestivoList = new ArrayList<>();
		for(DiaFestivo c : coleccion)
			diaFestivoList.add(transformarEntidadConDependencias(c));
		return diaFestivoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DiaFestivo> coleccion) {
		List<DiaFestivo> diaFestivoList = new ArrayList<>();
		for(DiaFestivo c : coleccion)
			diaFestivoList.add(transformarEntidadSinDependencias(c));
		return diaFestivoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
