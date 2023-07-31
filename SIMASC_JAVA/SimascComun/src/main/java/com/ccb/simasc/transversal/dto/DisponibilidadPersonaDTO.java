package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DisponibilidadPersona;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DisponibilidadPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DisponibilidadPersonaDTO extends IDTO<DisponibilidadPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idDisponibildadPersona;

	private String codigo;		
	private Time horaInicio;		
	private Time horaFin;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	
    public DisponibilidadPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdDisponibildadPersona(){
		return this.idDisponibildadPersona;
	}
	
	public void setIdDisponibildadPersona(Long idDisponibildadPersona){
		this.idDisponibildadPersona = idDisponibildadPersona;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
		
	public Time getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Time horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Time getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Time horaFin){
		this.horaFin = horaFin;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idDisponibildadPersona);        
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DisponibilidadPersonaDTO que se pasa
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
        final DisponibilidadPersonaDTO other = (DisponibilidadPersonaDTO) obj;
                
        if (!Objects.equals(this.idDisponibildadPersona, other.idDisponibildadPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public DisponibilidadPersonaDTO transformarSinDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersonaDTO disponibilidadPersonaDTO = new DisponibilidadPersonaDTO();
		
		disponibilidadPersonaDTO.setIdDisponibildadPersona(obj.getIdDisponibildadPersona());
		disponibilidadPersonaDTO.setCodigo(obj.getCodigo());
		disponibilidadPersonaDTO.setHoraInicio(obj.getHoraInicio());
		disponibilidadPersonaDTO.setHoraFin(obj.getHoraFin());
		disponibilidadPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		disponibilidadPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		disponibilidadPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		disponibilidadPersonaDTO.setIdPersona(obj.getIdPersona());
		
		return disponibilidadPersonaDTO;
	}

	@Override
	public DisponibilidadPersonaDTO transformarConDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersonaDTO disponibilidadPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return disponibilidadPersonaDTO;
	}

	@Override
	public DisponibilidadPersona transformarEntidadSinDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersona disponibilidadPersona = new DisponibilidadPersona();
		
		disponibilidadPersona.setIdDisponibildadPersona(obj.getIdDisponibildadPersona());
		
		disponibilidadPersona.setCodigo(obj.getCodigo());
		disponibilidadPersona.setHoraInicio(obj.getHoraInicio());
		disponibilidadPersona.setHoraFin(obj.getHoraFin());
		disponibilidadPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		disponibilidadPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		disponibilidadPersona.setEstadoRegistro(obj.getEstadoRegistro());
		disponibilidadPersona.setIdPersona(obj.getIdPersona());
		
		return disponibilidadPersona;
	}


	@Override
	public DisponibilidadPersona transformarEntidadConDependencias(DisponibilidadPersona obj) {
		DisponibilidadPersona disponibilidadPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return disponibilidadPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DisponibilidadPersona> coleccion) {
		List<DisponibilidadPersonaDTO> disponibilidadPersonaDTOList = new ArrayList<>();
		for(DisponibilidadPersona c : coleccion)
			disponibilidadPersonaDTOList.add(transformarConDependencias(c));
		return disponibilidadPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DisponibilidadPersona> coleccion) {
		List<DisponibilidadPersonaDTO> disponibilidadPersonaDTOList = new ArrayList<>();
		for(DisponibilidadPersona c : coleccion)
			disponibilidadPersonaDTOList.add(transformarSinDependencias(c));
		return disponibilidadPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DisponibilidadPersona> coleccion) {
		List<DisponibilidadPersona> disponibilidadPersonaList = new ArrayList<>();
		for(DisponibilidadPersona c : coleccion)
			disponibilidadPersonaList.add(transformarEntidadConDependencias(c));
		return disponibilidadPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DisponibilidadPersona> coleccion) {
		List<DisponibilidadPersona> disponibilidadPersonaList = new ArrayList<>();
		for(DisponibilidadPersona c : coleccion)
			disponibilidadPersonaList.add(transformarEntidadSinDependencias(c));
		return disponibilidadPersonaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
