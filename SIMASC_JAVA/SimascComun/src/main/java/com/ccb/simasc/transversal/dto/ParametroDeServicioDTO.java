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

import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroDeServicioPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParametroDeServicioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParametroDeServicioDTO extends IDTO<ParametroDeServicio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ParametroDeServicioPK parametroDeServicioPK;

	private String valor;		
	private String descripcion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParametroDeServicioDTO(){
		parametroDeServicioPK = new ParametroDeServicioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParametroDeServicioPK getParametroDeServicioPK(){
		return this.parametroDeServicioPK;
	}
	
	public void setParametroDeServicioPK(ParametroDeServicioPK parametroDeServicioPK){
		this.parametroDeServicioPK   = parametroDeServicioPK ;
	}  
	
	public String getValor(){
		return this.valor;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
        
        hash = 37 * hash + Objects.hashCode(this.parametroDeServicioPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroDeServicioDTO que se pasa
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
        final ParametroDeServicioDTO other = (ParametroDeServicioDTO) obj;
                
        if (!Objects.equals(this.parametroDeServicioPK, other.parametroDeServicioPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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
	public ParametroDeServicioDTO transformarSinDependencias(ParametroDeServicio obj) {
		ParametroDeServicioDTO parametroDeServicioDTO = new ParametroDeServicioDTO();
		
		parametroDeServicioDTO.setParametroDeServicioPK(obj.getParametroDeServicioPK());
		parametroDeServicioDTO.setValor(obj.getValor());
		parametroDeServicioDTO.setDescripcion(obj.getDescripcion());
		parametroDeServicioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroDeServicioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroDeServicioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametroDeServicioDTO;
	}

	@Override
	public ParametroDeServicioDTO transformarConDependencias(ParametroDeServicio obj) {
		ParametroDeServicioDTO parametroDeServicioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parametroDeServicioDTO;
	}

	@Override
	public ParametroDeServicio transformarEntidadSinDependencias(ParametroDeServicio obj) {
		ParametroDeServicio parametroDeServicio = new ParametroDeServicio();
		
		parametroDeServicio.setParametroDeServicioPK(obj.getParametroDeServicioPK());
		
		parametroDeServicio.setValor(obj.getValor());
		parametroDeServicio.setDescripcion(obj.getDescripcion());
		parametroDeServicio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parametroDeServicio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parametroDeServicio.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parametroDeServicio;
	}


	@Override
	public ParametroDeServicio transformarEntidadConDependencias(ParametroDeServicio obj) {
		ParametroDeServicio parametroDeServicio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parametroDeServicio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParametroDeServicio> coleccion) {
		List<ParametroDeServicioDTO> parametroDeServicioDTOList = new ArrayList<>();
		for(ParametroDeServicio c : coleccion)
			parametroDeServicioDTOList.add(transformarConDependencias(c));
		return parametroDeServicioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParametroDeServicio> coleccion) {
		List<ParametroDeServicioDTO> parametroDeServicioDTOList = new ArrayList<>();
		for(ParametroDeServicio c : coleccion)
			parametroDeServicioDTOList.add(transformarSinDependencias(c));
		return parametroDeServicioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParametroDeServicio> coleccion) {
		List<ParametroDeServicio> parametroDeServicioList = new ArrayList<>();
		for(ParametroDeServicio c : coleccion)
			parametroDeServicioList.add(transformarEntidadConDependencias(c));
		return parametroDeServicioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParametroDeServicio> coleccion) {
		List<ParametroDeServicio> parametroDeServicioList = new ArrayList<>();
		for(ParametroDeServicio c : coleccion)
			parametroDeServicioList.add(transformarEntidadSinDependencias(c));
		return parametroDeServicioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
