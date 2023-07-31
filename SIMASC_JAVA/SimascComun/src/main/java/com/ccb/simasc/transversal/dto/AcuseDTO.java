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

import com.ccb.simasc.transversal.entidades.Acuse;
import com.ccb.simasc.transversal.entidades.AcusePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AcuseDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AcuseDTO extends IDTO<Acuse> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private AcusePK acusePK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AcuseDTO(){
		acusePK = new AcusePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public AcusePK getAcusePK(){
		return this.acusePK;
	}
	
	public void setAcusePK(AcusePK acusePK){
		this.acusePK   = acusePK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.acusePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AcuseDTO que se pasa
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
        final AcuseDTO other = (AcuseDTO) obj;
                
        if (!Objects.equals(this.acusePK, other.acusePK)) {
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
	public AcuseDTO transformarSinDependencias(Acuse obj) {
		AcuseDTO acuseDTO = new AcuseDTO();
		
		acuseDTO.setAcusePK(obj.getAcusePK());
		acuseDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		acuseDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		acuseDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return acuseDTO;
	}

	@Override
	public AcuseDTO transformarConDependencias(Acuse obj) {
		AcuseDTO acuseDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return acuseDTO;
	}

	@Override
	public Acuse transformarEntidadSinDependencias(Acuse obj) {
		Acuse acuse = new Acuse();
		
		acuse.setAcusePK(obj.getAcusePK());
		
		acuse.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		acuse.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		acuse.setEstadoRegistro(obj.getEstadoRegistro());
		
		return acuse;
	}


	@Override
	public Acuse transformarEntidadConDependencias(Acuse obj) {
		Acuse acuse = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return acuse;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Acuse> coleccion) {
		List<AcuseDTO> acuseDTOList = new ArrayList<>();
		for(Acuse c : coleccion)
			acuseDTOList.add(transformarConDependencias(c));
		return acuseDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Acuse> coleccion) {
		List<AcuseDTO> acuseDTOList = new ArrayList<>();
		for(Acuse c : coleccion)
			acuseDTOList.add(transformarSinDependencias(c));
		return acuseDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Acuse> coleccion) {
		List<Acuse> acuseList = new ArrayList<>();
		for(Acuse c : coleccion)
			acuseList.add(transformarEntidadConDependencias(c));
		return acuseList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Acuse> coleccion) {
		List<Acuse> acuseList = new ArrayList<>();
		for(Acuse c : coleccion)
			acuseList.add(transformarEntidadSinDependencias(c));
		return acuseList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
