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

import com.ccb.simasc.transversal.entidades.AgendaPersona;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AgendaPersonaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AgendaPersonaDTO extends IDTO<AgendaPersona> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idAgendaPersona;

	private String estado;		
	private String tipoActividadAgenda;		
	private Long idPersona;		
	private Date fechaInicio;		
	private Date fechaFin;		
	private Long idAudiencia;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Boolean cumplioHorario;
	private Boolean justificacionValida;
	
    public AgendaPersonaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAgendaPersona(){
		return this.idAgendaPersona;
	}
	
	public void setIdAgendaPersona(Long idAgendaPersona){
		this.idAgendaPersona = idAgendaPersona;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getTipoActividadAgenda(){
		return this.tipoActividadAgenda;
	}
	
	public void setTipoActividadAgenda(String tipoActividadAgenda){
		this.tipoActividadAgenda = tipoActividadAgenda;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
	
	public Boolean getCumplioHorario() {
		return cumplioHorario;
	}

	public void setCumplioHorario(Boolean cumpliohorario) {
		this.cumplioHorario = cumpliohorario;
	}
	
	public Boolean getJustificacionValida() {
		return justificacionValida;
	}

	public void setJustificacionValida(Boolean justificacionValida) {
		this.justificacionValida = justificacionValida;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAgendaPersona);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.tipoActividadAgenda);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.cumplioHorario);
        hash = 37 * hash + Objects.hashCode(this.justificacionValida);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AgendaPersonaDTO que se pasa
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
        final AgendaPersonaDTO other = (AgendaPersonaDTO) obj;
                
        if (!Objects.equals(this.idAgendaPersona, other.idAgendaPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActividadAgenda, other.tipoActividadAgenda)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.cumplioHorario, other.cumplioHorario)) {
            return false;
        }
        
        if (!Objects.equals(this.justificacionValida, other.justificacionValida)) {
            return false;
        }     
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public AgendaPersonaDTO transformarSinDependencias(AgendaPersona obj) {
		AgendaPersonaDTO agendaPersonaDTO = new AgendaPersonaDTO();
		
		agendaPersonaDTO.setIdAgendaPersona(obj.getIdAgendaPersona());
		agendaPersonaDTO.setEstado(obj.getEstado());
		agendaPersonaDTO.setTipoActividadAgenda(obj.getTipoActividadAgenda());
		agendaPersonaDTO.setIdPersona(obj.getIdPersona());
		agendaPersonaDTO.setFechaInicio(obj.getFechaInicio());
		agendaPersonaDTO.setFechaFin(obj.getFechaFin());
		agendaPersonaDTO.setIdAudiencia(obj.getIdAudiencia());
		agendaPersonaDTO.setObservaciones(obj.getObservaciones());
		agendaPersonaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agendaPersonaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agendaPersonaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		agendaPersonaDTO.setCumplioHorario(obj.getCumplioHorario());
		agendaPersonaDTO.setJustificacionValida(obj.getJustificacionValida());
		
		return agendaPersonaDTO;
	}

	@Override
	public AgendaPersonaDTO transformarConDependencias(AgendaPersona obj) {
		AgendaPersonaDTO agendaPersonaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return agendaPersonaDTO;
	}

	@Override
	public AgendaPersona transformarEntidadSinDependencias(AgendaPersona obj) {
		AgendaPersona agendaPersona = new AgendaPersona();
		
		agendaPersona.setIdAgendaPersona(obj.getIdAgendaPersona());		
		agendaPersona.setEstado(obj.getEstado());
		agendaPersona.setTipoActividadAgenda(obj.getTipoActividadAgenda());
		agendaPersona.setIdPersona(obj.getIdPersona());
		agendaPersona.setFechaInicio(obj.getFechaInicio());
		agendaPersona.setFechaFin(obj.getFechaFin());
		agendaPersona.setIdAudiencia(obj.getIdAudiencia());
		agendaPersona.setObservaciones(obj.getObservaciones());
		agendaPersona.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agendaPersona.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agendaPersona.setEstadoRegistro(obj.getEstadoRegistro());
		agendaPersona.setCumplioHorario(obj.getCumplioHorario());
		agendaPersona.setJustificacionValida(obj.getJustificacionValida());
		
		return agendaPersona;
	}


	@Override
	public AgendaPersona transformarEntidadConDependencias(AgendaPersona obj) {
		AgendaPersona agendaPersona = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return agendaPersona;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<AgendaPersona> coleccion) {
		List<AgendaPersonaDTO> agendaPersonaDTOList = new ArrayList<>();
		for(AgendaPersona c : coleccion)
			agendaPersonaDTOList.add(transformarConDependencias(c));
		return agendaPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<AgendaPersona> coleccion) {
		List<AgendaPersonaDTO> agendaPersonaDTOList = new ArrayList<>();
		for(AgendaPersona c : coleccion)
			agendaPersonaDTOList.add(transformarSinDependencias(c));
		return agendaPersonaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<AgendaPersona> coleccion) {
		List<AgendaPersona> agendaPersonaList = new ArrayList<>();
		for(AgendaPersona c : coleccion)
			agendaPersonaList.add(transformarEntidadConDependencias(c));
		return agendaPersonaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<AgendaPersona> coleccion) {
		List<AgendaPersona> agendaPersonaList = new ArrayList<>();
		for(AgendaPersona c : coleccion)
			agendaPersonaList.add(transformarEntidadSinDependencias(c));
		return agendaPersonaList;
	}


	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
