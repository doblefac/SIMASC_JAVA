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

import com.ccb.simasc.transversal.entidades.FechaCaso;
import com.ccb.simasc.transversal.entidades.FechaCasoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad FechaCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FechaCasoDTO extends IDTO<FechaCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private FechaCasoPK fechaCasoPK;

	private Date fecha;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FechaCasoDTO(){
		fechaCasoPK = new FechaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public FechaCasoPK getFechaCasoPK(){
		return this.fechaCasoPK;
	}
	
	public void setFechaCasoPK(FechaCasoPK fechaCasoPK){
		this.fechaCasoPK   = fechaCasoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.fechaCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FechaCasoDTO que se pasa
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
        final FechaCasoDTO other = (FechaCasoDTO) obj;
                
        if (!Objects.equals(this.fechaCasoPK, other.fechaCasoPK)) {
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
	public FechaCasoDTO transformarSinDependencias(FechaCaso obj) {
		FechaCasoDTO fechaCasoDTO = new FechaCasoDTO();
		
		fechaCasoDTO.setFechaCasoPK(obj.getFechaCasoPK());
		fechaCasoDTO.setFecha(obj.getFecha());
		fechaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		fechaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		fechaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return fechaCasoDTO;
	}

	@Override
	public FechaCasoDTO transformarConDependencias(FechaCaso obj) {
		FechaCasoDTO fechaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return fechaCasoDTO;
	}

	@Override
	public FechaCaso transformarEntidadSinDependencias(FechaCaso obj) {
		FechaCaso fechaCaso = new FechaCaso();
		
		fechaCaso.setFechaCasoPK(obj.getFechaCasoPK());
		
		fechaCaso.setFecha(obj.getFecha());
		fechaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		fechaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		fechaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return fechaCaso;
	}


	@Override
	public FechaCaso transformarEntidadConDependencias(FechaCaso obj) {
		FechaCaso fechaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return fechaCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FechaCaso> coleccion) {
		List<FechaCasoDTO> fechaCasoDTOList = new ArrayList<>();
		for(FechaCaso c : coleccion)
			fechaCasoDTOList.add(transformarConDependencias(c));
		return fechaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FechaCaso> coleccion) {
		List<FechaCasoDTO> fechaCasoDTOList = new ArrayList<>();
		for(FechaCaso c : coleccion)
			fechaCasoDTOList.add(transformarSinDependencias(c));
		return fechaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FechaCaso> coleccion) {
		List<FechaCaso> fechaCasoList = new ArrayList<>();
		for(FechaCaso c : coleccion)
			fechaCasoList.add(transformarEntidadConDependencias(c));
		return fechaCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FechaCaso> coleccion) {
		List<FechaCaso> fechaCasoList = new ArrayList<>();
		for(FechaCaso c : coleccion)
			fechaCasoList.add(transformarEntidadSinDependencias(c));
		return fechaCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
