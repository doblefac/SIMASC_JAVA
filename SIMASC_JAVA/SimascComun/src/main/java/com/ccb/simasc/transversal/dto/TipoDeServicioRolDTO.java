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

import com.ccb.simasc.transversal.entidades.TipoDeServicioRol;
import com.ccb.simasc.transversal.entidades.TipoDeServicioRolPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TipoDeServicioRolDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TipoDeServicioRolDTO extends IDTO<TipoDeServicioRol> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private TipoDeServicioRolPK tipoDeServicioRolPK;

	private String idUsuarioModificaicon;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public TipoDeServicioRolDTO(){
		tipoDeServicioRolPK = new TipoDeServicioRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TipoDeServicioRolPK getTipoDeServicioRolPK(){
		return this.tipoDeServicioRolPK;
	}
	
	public void setTipoDeServicioRolPK(TipoDeServicioRolPK tipoDeServicioRolPK){
		this.tipoDeServicioRolPK   = tipoDeServicioRolPK ;
	}  
	
	public String getIdUsuarioModificaicon(){
		return this.idUsuarioModificaicon;
	}
	
	public void setIdUsuarioModificaicon(String idUsuarioModificaicon){
		this.idUsuarioModificaicon = idUsuarioModificaicon;
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
        
        hash = 37 * hash + Objects.hashCode(this.tipoDeServicioRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificaicon);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoDeServicioRolDTO que se pasa
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
        final TipoDeServicioRolDTO other = (TipoDeServicioRolDTO) obj;
                
        if (!Objects.equals(this.tipoDeServicioRolPK, other.tipoDeServicioRolPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificaicon, other.idUsuarioModificaicon)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public TipoDeServicioRolDTO transformarSinDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRolDTO tipoDeServicioRolDTO = new TipoDeServicioRolDTO();
		
		tipoDeServicioRolDTO.setTipoDeServicioRolPK(obj.getTipoDeServicioRolPK());
		tipoDeServicioRolDTO.setIdUsuarioModificaicon(obj.getIdUsuarioModificaicon());
		tipoDeServicioRolDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoDeServicioRolDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoDeServicioRolDTO;
	}

	@Override
	public TipoDeServicioRolDTO transformarConDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRolDTO tipoDeServicioRolDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tipoDeServicioRolDTO;
	}

	@Override
	public TipoDeServicioRol transformarEntidadSinDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRol tipoDeServicioRol = new TipoDeServicioRol();
		
		tipoDeServicioRol.setTipoDeServicioRolPK(obj.getTipoDeServicioRolPK());
		
		tipoDeServicioRol.setIdUsuarioModificaicon(obj.getIdUsuarioModificaicon());
		tipoDeServicioRol.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tipoDeServicioRol.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tipoDeServicioRol;
	}


	@Override
	public TipoDeServicioRol transformarEntidadConDependencias(TipoDeServicioRol obj) {
		TipoDeServicioRol tipoDeServicioRol = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tipoDeServicioRol;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TipoDeServicioRol> coleccion) {
		List<TipoDeServicioRolDTO> tipoDeServicioRolDTOList = new ArrayList<>();
		for(TipoDeServicioRol c : coleccion)
			tipoDeServicioRolDTOList.add(transformarConDependencias(c));
		return tipoDeServicioRolDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TipoDeServicioRol> coleccion) {
		List<TipoDeServicioRolDTO> tipoDeServicioRolDTOList = new ArrayList<>();
		for(TipoDeServicioRol c : coleccion)
			tipoDeServicioRolDTOList.add(transformarSinDependencias(c));
		return tipoDeServicioRolDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TipoDeServicioRol> coleccion) {
		List<TipoDeServicioRol> tipoDeServicioRolList = new ArrayList<>();
		for(TipoDeServicioRol c : coleccion)
			tipoDeServicioRolList.add(transformarEntidadConDependencias(c));
		return tipoDeServicioRolList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TipoDeServicioRol> coleccion) {
		List<TipoDeServicioRol> tipoDeServicioRolList = new ArrayList<>();
		for(TipoDeServicioRol c : coleccion)
			tipoDeServicioRolList.add(transformarEntidadSinDependencias(c));
		return tipoDeServicioRolList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
