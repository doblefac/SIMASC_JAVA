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

import com.ccb.simasc.transversal.entidades.TipoServicioSede;
import com.ccb.simasc.transversal.entidades.TipoServicioSedePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TipoServicioSedeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TipoServicioSedeDTO extends IDTO<TipoServicioSede> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private TipoServicioSedePK tipoServicioSedePK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public TipoServicioSedeDTO(){
		tipoServicioSedePK = new TipoServicioSedePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TipoServicioSedePK getTipoServicioSedePK(){
		return this.tipoServicioSedePK;
	}
	
	public void setTipoServicioSedePK(TipoServicioSedePK tipoServicioSedePK){
		this.tipoServicioSedePK   = tipoServicioSedePK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.tipoServicioSedePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoServicioSedeDTO que se pasa
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
        final TipoServicioSedeDTO other = (TipoServicioSedeDTO) obj;
                
        if (!Objects.equals(this.tipoServicioSedePK, other.tipoServicioSedePK)) {
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
	public TipoServicioSedeDTO transformarSinDependencias(TipoServicioSede obj) {
		TipoServicioSedeDTO tipoServicioSedeDTO = new TipoServicioSedeDTO();
		
		tipoServicioSedeDTO.setTipoServicioSedePK(obj.getTipoServicioSedePK());
		tipoServicioSedeDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoServicioSedeDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoServicioSedeDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoServicioSedeDTO;
	}

	@Override
	public TipoServicioSedeDTO transformarConDependencias(TipoServicioSede obj) {
		TipoServicioSedeDTO tipoServicioSedeDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tipoServicioSedeDTO;
	}

	@Override
	public TipoServicioSede transformarEntidadSinDependencias(TipoServicioSede obj) {
		TipoServicioSede tipoServicioSede = new TipoServicioSede();
		
		tipoServicioSede.setTipoServicioSedePK(obj.getTipoServicioSedePK());
		
		tipoServicioSede.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tipoServicioSede.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoServicioSede.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoServicioSede;
	}


	@Override
	public TipoServicioSede transformarEntidadConDependencias(TipoServicioSede obj) {
		TipoServicioSede tipoServicioSede = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tipoServicioSede;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TipoServicioSede> coleccion) {
		List<TipoServicioSedeDTO> tipoServicioSedeDTOList = new ArrayList<>();
		for(TipoServicioSede c : coleccion)
			tipoServicioSedeDTOList.add(transformarConDependencias(c));
		return tipoServicioSedeDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TipoServicioSede> coleccion) {
		List<TipoServicioSedeDTO> tipoServicioSedeDTOList = new ArrayList<>();
		for(TipoServicioSede c : coleccion)
			tipoServicioSedeDTOList.add(transformarSinDependencias(c));
		return tipoServicioSedeDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TipoServicioSede> coleccion) {
		List<TipoServicioSede> tipoServicioSedeList = new ArrayList<>();
		for(TipoServicioSede c : coleccion)
			tipoServicioSedeList.add(transformarEntidadConDependencias(c));
		return tipoServicioSedeList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TipoServicioSede> coleccion) {
		List<TipoServicioSede> tipoServicioSedeList = new ArrayList<>();
		for(TipoServicioSede c : coleccion)
			tipoServicioSedeList.add(transformarEntidadSinDependencias(c));
		return tipoServicioSedeList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
