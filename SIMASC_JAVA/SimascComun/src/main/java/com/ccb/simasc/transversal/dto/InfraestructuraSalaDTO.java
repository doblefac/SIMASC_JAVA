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

import com.ccb.simasc.transversal.entidades.InfraestructuraSala;
import com.ccb.simasc.transversal.entidades.InfraestructuraSalaPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad InfraestructuraSalaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class InfraestructuraSalaDTO extends IDTO<InfraestructuraSala> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private InfraestructuraSalaPK infraestructuraSalaPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public InfraestructuraSalaDTO(){
		infraestructuraSalaPK = new InfraestructuraSalaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public InfraestructuraSalaPK getInfraestructuraSalaPK(){
		return this.infraestructuraSalaPK;
	}
	
	public void setInfraestructuraSalaPK(InfraestructuraSalaPK infraestructuraSalaPK){
		this.infraestructuraSalaPK   = infraestructuraSalaPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.infraestructuraSalaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InfraestructuraSalaDTO que se pasa
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
        final InfraestructuraSalaDTO other = (InfraestructuraSalaDTO) obj;
                
        if (!Objects.equals(this.infraestructuraSalaPK, other.infraestructuraSalaPK)) {
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
	public InfraestructuraSalaDTO transformarSinDependencias(InfraestructuraSala obj) {
		InfraestructuraSalaDTO infraestructuraSalaDTO = new InfraestructuraSalaDTO();
		
		infraestructuraSalaDTO.setInfraestructuraSalaPK(obj.getInfraestructuraSalaPK());
		infraestructuraSalaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructuraSalaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructuraSalaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructuraSalaDTO;
	}

	@Override
	public InfraestructuraSalaDTO transformarConDependencias(InfraestructuraSala obj) {
		InfraestructuraSalaDTO infraestructuraSalaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return infraestructuraSalaDTO;
	}

	@Override
	public InfraestructuraSala transformarEntidadSinDependencias(InfraestructuraSala obj) {
		InfraestructuraSala infraestructuraSala = new InfraestructuraSala();
		
		infraestructuraSala.setInfraestructuraSalaPK(obj.getInfraestructuraSalaPK());
		
		infraestructuraSala.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		infraestructuraSala.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		infraestructuraSala.setEstadoRegistro(obj.getEstadoRegistro());
		
		return infraestructuraSala;
	}


	@Override
	public InfraestructuraSala transformarEntidadConDependencias(InfraestructuraSala obj) {
		InfraestructuraSala infraestructuraSala = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return infraestructuraSala;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<InfraestructuraSala> coleccion) {
		List<InfraestructuraSalaDTO> infraestructuraSalaDTOList = new ArrayList<>();
		for(InfraestructuraSala c : coleccion)
			infraestructuraSalaDTOList.add(transformarConDependencias(c));
		return infraestructuraSalaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<InfraestructuraSala> coleccion) {
		List<InfraestructuraSalaDTO> infraestructuraSalaDTOList = new ArrayList<>();
		for(InfraestructuraSala c : coleccion)
			infraestructuraSalaDTOList.add(transformarSinDependencias(c));
		return infraestructuraSalaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<InfraestructuraSala> coleccion) {
		List<InfraestructuraSala> infraestructuraSalaList = new ArrayList<>();
		for(InfraestructuraSala c : coleccion)
			infraestructuraSalaList.add(transformarEntidadConDependencias(c));
		return infraestructuraSalaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<InfraestructuraSala> coleccion) {
		List<InfraestructuraSala> infraestructuraSalaList = new ArrayList<>();
		for(InfraestructuraSala c : coleccion)
			infraestructuraSalaList.add(transformarEntidadSinDependencias(c));
		return infraestructuraSalaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
