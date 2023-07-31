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

import com.ccb.simasc.transversal.entidades.HomologacionSistemaExterno;
import com.ccb.simasc.transversal.entidades.HomologacionSistemaExternoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HomologacionSistemaExternoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HomologacionSistemaExternoDTO extends IDTO<HomologacionSistemaExterno> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private HomologacionSistemaExternoPK homologacionSistemaExternoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public HomologacionSistemaExternoDTO(){
		homologacionSistemaExternoPK = new HomologacionSistemaExternoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public HomologacionSistemaExternoPK getHomologacionSistemaExternoPK(){
		return this.homologacionSistemaExternoPK;
	}
	
	public void setHomologacionSistemaExternoPK(HomologacionSistemaExternoPK homologacionSistemaExternoPK){
		this.homologacionSistemaExternoPK   = homologacionSistemaExternoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.homologacionSistemaExternoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HomologacionSistemaExternoDTO que se pasa
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
        final HomologacionSistemaExternoDTO other = (HomologacionSistemaExternoDTO) obj;
                
        if (!Objects.equals(this.homologacionSistemaExternoPK, other.homologacionSistemaExternoPK)) {
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
	public HomologacionSistemaExternoDTO transformarSinDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExternoDTO homologacionSistemaExternoDTO = new HomologacionSistemaExternoDTO();
		
		homologacionSistemaExternoDTO.setHomologacionSistemaExternoPK(obj.getHomologacionSistemaExternoPK());
		homologacionSistemaExternoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		homologacionSistemaExternoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		homologacionSistemaExternoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return homologacionSistemaExternoDTO;
	}

	@Override
	public HomologacionSistemaExternoDTO transformarConDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExternoDTO homologacionSistemaExternoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return homologacionSistemaExternoDTO;
	}

	@Override
	public HomologacionSistemaExterno transformarEntidadSinDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExterno homologacionSistemaExterno = new HomologacionSistemaExterno();
		
		homologacionSistemaExterno.setHomologacionSistemaExternoPK(obj.getHomologacionSistemaExternoPK());
		
		homologacionSistemaExterno.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		homologacionSistemaExterno.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		homologacionSistemaExterno.setEstadoRegistro(obj.getEstadoRegistro());
		
		return homologacionSistemaExterno;
	}


	@Override
	public HomologacionSistemaExterno transformarEntidadConDependencias(HomologacionSistemaExterno obj) {
		HomologacionSistemaExterno homologacionSistemaExterno = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return homologacionSistemaExterno;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HomologacionSistemaExterno> coleccion) {
		List<HomologacionSistemaExternoDTO> homologacionSistemaExternoDTOList = new ArrayList<>();
		for(HomologacionSistemaExterno c : coleccion)
			homologacionSistemaExternoDTOList.add(transformarConDependencias(c));
		return homologacionSistemaExternoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HomologacionSistemaExterno> coleccion) {
		List<HomologacionSistemaExternoDTO> homologacionSistemaExternoDTOList = new ArrayList<>();
		for(HomologacionSistemaExterno c : coleccion)
			homologacionSistemaExternoDTOList.add(transformarSinDependencias(c));
		return homologacionSistemaExternoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HomologacionSistemaExterno> coleccion) {
		List<HomologacionSistemaExterno> homologacionSistemaExternoList = new ArrayList<>();
		for(HomologacionSistemaExterno c : coleccion)
			homologacionSistemaExternoList.add(transformarEntidadConDependencias(c));
		return homologacionSistemaExternoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HomologacionSistemaExterno> coleccion) {
		List<HomologacionSistemaExterno> homologacionSistemaExternoList = new ArrayList<>();
		for(HomologacionSistemaExterno c : coleccion)
			homologacionSistemaExternoList.add(transformarEntidadSinDependencias(c));
		return homologacionSistemaExternoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
