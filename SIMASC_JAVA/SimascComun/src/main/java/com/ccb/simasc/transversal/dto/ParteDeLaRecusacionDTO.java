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

import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacion;
import com.ccb.simasc.transversal.entidades.ParteDeLaRecusacionPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ParteDeLaRecusacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ParteDeLaRecusacionDTO extends IDTO<ParteDeLaRecusacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ParteDeLaRecusacionPK parteDeLaRecusacionPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ParteDeLaRecusacionDTO(){
		parteDeLaRecusacionPK = new ParteDeLaRecusacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParteDeLaRecusacionPK getParteDeLaRecusacionPK(){
		return this.parteDeLaRecusacionPK;
	}
	
	public void setParteDeLaRecusacionPK(ParteDeLaRecusacionPK parteDeLaRecusacionPK){
		this.parteDeLaRecusacionPK   = parteDeLaRecusacionPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.parteDeLaRecusacionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteDeLaRecusacionDTO que se pasa
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
        final ParteDeLaRecusacionDTO other = (ParteDeLaRecusacionDTO) obj;
                
        if (!Objects.equals(this.parteDeLaRecusacionPK, other.parteDeLaRecusacionPK)) {
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
	public ParteDeLaRecusacionDTO transformarSinDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacionDTO parteDeLaRecusacionDTO = new ParteDeLaRecusacionDTO();
		
		parteDeLaRecusacionDTO.setParteDeLaRecusacionPK(obj.getParteDeLaRecusacionPK());
		parteDeLaRecusacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parteDeLaRecusacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parteDeLaRecusacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parteDeLaRecusacionDTO;
	}

	@Override
	public ParteDeLaRecusacionDTO transformarConDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacionDTO parteDeLaRecusacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return parteDeLaRecusacionDTO;
	}

	@Override
	public ParteDeLaRecusacion transformarEntidadSinDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacion parteDeLaRecusacion = new ParteDeLaRecusacion();
		
		parteDeLaRecusacion.setParteDeLaRecusacionPK(obj.getParteDeLaRecusacionPK());
		
		parteDeLaRecusacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		parteDeLaRecusacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		parteDeLaRecusacion.setEstadoRegistro(obj.getEstadoRegistro());
		
		return parteDeLaRecusacion;
	}


	@Override
	public ParteDeLaRecusacion transformarEntidadConDependencias(ParteDeLaRecusacion obj) {
		ParteDeLaRecusacion parteDeLaRecusacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return parteDeLaRecusacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ParteDeLaRecusacion> coleccion) {
		List<ParteDeLaRecusacionDTO> parteDeLaRecusacionDTOList = new ArrayList<>();
		for(ParteDeLaRecusacion c : coleccion)
			parteDeLaRecusacionDTOList.add(transformarConDependencias(c));
		return parteDeLaRecusacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ParteDeLaRecusacion> coleccion) {
		List<ParteDeLaRecusacionDTO> parteDeLaRecusacionDTOList = new ArrayList<>();
		for(ParteDeLaRecusacion c : coleccion)
			parteDeLaRecusacionDTOList.add(transformarSinDependencias(c));
		return parteDeLaRecusacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ParteDeLaRecusacion> coleccion) {
		List<ParteDeLaRecusacion> parteDeLaRecusacionList = new ArrayList<>();
		for(ParteDeLaRecusacion c : coleccion)
			parteDeLaRecusacionList.add(transformarEntidadConDependencias(c));
		return parteDeLaRecusacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ParteDeLaRecusacion> coleccion) {
		List<ParteDeLaRecusacion> parteDeLaRecusacionList = new ArrayList<>();
		for(ParteDeLaRecusacion c : coleccion)
			parteDeLaRecusacionList.add(transformarEntidadSinDependencias(c));
		return parteDeLaRecusacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
