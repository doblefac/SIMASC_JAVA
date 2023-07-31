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

import com.ccb.simasc.transversal.entidades.ParamEstadoArbitroPreseleccion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParamEstadoArbitroPreseleccionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParamEstadoArbitroPreseleccionDTO extends IDTO<ParamEstadoArbitroPreseleccion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private String codigo;

	private String dominio;		
	private Long idParametrosSorteo;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParamEstadoArbitroPreseleccionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getDominio(){
		return this.dominio;
	}
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}
		
	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
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
        
        hash = 37 * hash + Objects.hashCode(this.codigo);        
        hash = 37 * hash + Objects.hashCode(this.dominio);
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParamEstadoArbitroPreseleccionDTO que se pasa
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
        final ParamEstadoArbitroPreseleccionDTO other = (ParamEstadoArbitroPreseleccionDTO) obj;
                
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.dominio, other.dominio)) {
            return false;
        }
        
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
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
	public ParamEstadoArbitroPreseleccionDTO transformarSinDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccionDTO paramEstadoArbitroPreseleccionDTO = new ParamEstadoArbitroPreseleccionDTO();
		
		paramEstadoArbitroPreseleccionDTO.setCodigo(obj.getCodigo());
		paramEstadoArbitroPreseleccionDTO.setDominio(obj.getDominio());
		paramEstadoArbitroPreseleccionDTO.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		paramEstadoArbitroPreseleccionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		paramEstadoArbitroPreseleccionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		paramEstadoArbitroPreseleccionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return paramEstadoArbitroPreseleccionDTO;
	}

	@Override
	public ParamEstadoArbitroPreseleccionDTO transformarConDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccionDTO paramEstadoArbitroPreseleccionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return paramEstadoArbitroPreseleccionDTO;
	}

	@Override
	public ParamEstadoArbitroPreseleccion transformarEntidadSinDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccion paramEstadoArbitroPreseleccion = new ParamEstadoArbitroPreseleccion();
		
		paramEstadoArbitroPreseleccion.setCodigo(obj.getCodigo());
		
		paramEstadoArbitroPreseleccion.setDominio(obj.getDominio());
		paramEstadoArbitroPreseleccion.setIdParametrosSorteo(obj.getIdParametrosSorteo());
		paramEstadoArbitroPreseleccion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		paramEstadoArbitroPreseleccion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		paramEstadoArbitroPreseleccion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return paramEstadoArbitroPreseleccion;
	}


	@Override
	public ParamEstadoArbitroPreseleccion transformarEntidadConDependencias(ParamEstadoArbitroPreseleccion obj) {
		ParamEstadoArbitroPreseleccion paramEstadoArbitroPreseleccion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return paramEstadoArbitroPreseleccion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParamEstadoArbitroPreseleccion> coleccion) {
		List<ParamEstadoArbitroPreseleccionDTO> paramEstadoArbitroPreseleccionDTOList = new ArrayList<>();
		for(ParamEstadoArbitroPreseleccion c : coleccion)
			paramEstadoArbitroPreseleccionDTOList.add(transformarConDependencias(c));
		return paramEstadoArbitroPreseleccionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParamEstadoArbitroPreseleccion> coleccion) {
		List<ParamEstadoArbitroPreseleccionDTO> paramEstadoArbitroPreseleccionDTOList = new ArrayList<>();
		for(ParamEstadoArbitroPreseleccion c : coleccion)
			paramEstadoArbitroPreseleccionDTOList.add(transformarSinDependencias(c));
		return paramEstadoArbitroPreseleccionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParamEstadoArbitroPreseleccion> coleccion) {
		List<ParamEstadoArbitroPreseleccion> paramEstadoArbitroPreseleccionList = new ArrayList<>();
		for(ParamEstadoArbitroPreseleccion c : coleccion)
			paramEstadoArbitroPreseleccionList.add(transformarEntidadConDependencias(c));
		return paramEstadoArbitroPreseleccionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParamEstadoArbitroPreseleccion> coleccion) {
		List<ParamEstadoArbitroPreseleccion> paramEstadoArbitroPreseleccionList = new ArrayList<>();
		for(ParamEstadoArbitroPreseleccion c : coleccion)
			paramEstadoArbitroPreseleccionList.add(transformarEntidadSinDependencias(c));
		return paramEstadoArbitroPreseleccionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
