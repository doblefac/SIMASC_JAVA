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

import com.ccb.simasc.transversal.entidades.TipoFuncionalidad;
import com.ccb.simasc.transversal.entidades.TipoFuncionalidadPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TipoFuncionalidadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TipoFuncionalidadDTO extends IDTO<TipoFuncionalidad> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private TipoFuncionalidadPK tipoFuncionalidadPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public TipoFuncionalidadDTO(){
		tipoFuncionalidadPK = new TipoFuncionalidadPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TipoFuncionalidadPK getTipoFuncionalidadPK(){
		return this.tipoFuncionalidadPK;
	}
	
	public void setTipoFuncionalidadPK(TipoFuncionalidadPK tipoFuncionalidadPK){
		this.tipoFuncionalidadPK   = tipoFuncionalidadPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.tipoFuncionalidadPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoFuncionalidadDTO que se pasa
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
        final TipoFuncionalidadDTO other = (TipoFuncionalidadDTO) obj;
                
        if (!Objects.equals(this.tipoFuncionalidadPK, other.tipoFuncionalidadPK)) {
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
	public TipoFuncionalidadDTO transformarSinDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidadDTO tipoFuncionalidadDTO = new TipoFuncionalidadDTO();
		
		tipoFuncionalidadDTO.setTipoFuncionalidadPK(obj.getTipoFuncionalidadPK());
		tipoFuncionalidadDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoFuncionalidadDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoFuncionalidadDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoFuncionalidadDTO;
	}

	@Override
	public TipoFuncionalidadDTO transformarConDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidadDTO tipoFuncionalidadDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tipoFuncionalidadDTO;
	}

	@Override
	public TipoFuncionalidad transformarEntidadSinDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidad tipoFuncionalidad = new TipoFuncionalidad();
		
		tipoFuncionalidad.setTipoFuncionalidadPK(obj.getTipoFuncionalidadPK());
		
		tipoFuncionalidad.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoFuncionalidad.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoFuncionalidad.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoFuncionalidad;
	}


	@Override
	public TipoFuncionalidad transformarEntidadConDependencias(TipoFuncionalidad obj) {
		TipoFuncionalidad tipoFuncionalidad = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tipoFuncionalidad;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TipoFuncionalidad> coleccion) {
		List<TipoFuncionalidadDTO> tipoFuncionalidadDTOList = new ArrayList<>();
		for(TipoFuncionalidad c : coleccion)
			tipoFuncionalidadDTOList.add(transformarConDependencias(c));
		return tipoFuncionalidadDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TipoFuncionalidad> coleccion) {
		List<TipoFuncionalidadDTO> tipoFuncionalidadDTOList = new ArrayList<>();
		for(TipoFuncionalidad c : coleccion)
			tipoFuncionalidadDTOList.add(transformarSinDependencias(c));
		return tipoFuncionalidadDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TipoFuncionalidad> coleccion) {
		List<TipoFuncionalidad> tipoFuncionalidadList = new ArrayList<>();
		for(TipoFuncionalidad c : coleccion)
			tipoFuncionalidadList.add(transformarEntidadConDependencias(c));
		return tipoFuncionalidadList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TipoFuncionalidad> coleccion) {
		List<TipoFuncionalidad> tipoFuncionalidadList = new ArrayList<>();
		for(TipoFuncionalidad c : coleccion)
			tipoFuncionalidadList.add(transformarEntidadSinDependencias(c));
		return tipoFuncionalidadList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
