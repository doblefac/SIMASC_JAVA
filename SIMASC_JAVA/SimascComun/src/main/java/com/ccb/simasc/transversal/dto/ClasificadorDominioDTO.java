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

import com.ccb.simasc.transversal.entidades.ClasificadorDominio;
import com.ccb.simasc.transversal.entidades.ClasificadorDominioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ClasificadorDominioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ClasificadorDominioDTO extends IDTO<ClasificadorDominio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ClasificadorDominioPK clasificadorDominioPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ClasificadorDominioDTO(){
		clasificadorDominioPK = new ClasificadorDominioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ClasificadorDominioPK getClasificadorDominioPK(){
		return this.clasificadorDominioPK;
	}
	
	public void setClasificadorDominioPK(ClasificadorDominioPK clasificadorDominioPK){
		this.clasificadorDominioPK   = clasificadorDominioPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.clasificadorDominioPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClasificadorDominioDTO que se pasa
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
        final ClasificadorDominioDTO other = (ClasificadorDominioDTO) obj;
                
        if (!Objects.equals(this.clasificadorDominioPK, other.clasificadorDominioPK)) {
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
	public ClasificadorDominioDTO transformarSinDependencias(ClasificadorDominio obj) {
		ClasificadorDominioDTO clasificadorDominioDTO = new ClasificadorDominioDTO();
		
		clasificadorDominioDTO.setClasificadorDominioPK(obj.getClasificadorDominioPK());
		clasificadorDominioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		clasificadorDominioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		clasificadorDominioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return clasificadorDominioDTO;
	}

	@Override
	public ClasificadorDominioDTO transformarConDependencias(ClasificadorDominio obj) {
		ClasificadorDominioDTO clasificadorDominioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return clasificadorDominioDTO;
	}

	@Override
	public ClasificadorDominio transformarEntidadSinDependencias(ClasificadorDominio obj) {
		ClasificadorDominio clasificadorDominio = new ClasificadorDominio();
		
		clasificadorDominio.setClasificadorDominioPK(obj.getClasificadorDominioPK());
		
		clasificadorDominio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		clasificadorDominio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		clasificadorDominio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return clasificadorDominio;
	}


	@Override
	public ClasificadorDominio transformarEntidadConDependencias(ClasificadorDominio obj) {
		ClasificadorDominio clasificadorDominio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return clasificadorDominio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ClasificadorDominio> coleccion) {
		List<ClasificadorDominioDTO> clasificadorDominioDTOList = new ArrayList<>();
		for(ClasificadorDominio c : coleccion)
			clasificadorDominioDTOList.add(transformarConDependencias(c));
		return clasificadorDominioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ClasificadorDominio> coleccion) {
		List<ClasificadorDominioDTO> clasificadorDominioDTOList = new ArrayList<>();
		for(ClasificadorDominio c : coleccion)
			clasificadorDominioDTOList.add(transformarSinDependencias(c));
		return clasificadorDominioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ClasificadorDominio> coleccion) {
		List<ClasificadorDominio> clasificadorDominioList = new ArrayList<>();
		for(ClasificadorDominio c : coleccion)
			clasificadorDominioList.add(transformarEntidadConDependencias(c));
		return clasificadorDominioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ClasificadorDominio> coleccion) {
		List<ClasificadorDominio> clasificadorDominioList = new ArrayList<>();
		for(ClasificadorDominio c : coleccion)
			clasificadorDominioList.add(transformarEntidadSinDependencias(c));
		return clasificadorDominioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
