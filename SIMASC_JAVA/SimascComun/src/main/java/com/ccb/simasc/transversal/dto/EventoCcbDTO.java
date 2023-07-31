package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.EventoCcb;
import com.ccb.simasc.transversal.entidades.PersonaEventoCcb;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EventoCcbDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EventoCcbDTO extends IDTO<EventoCcb> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idEventoCcb;

	private String estado;		
	private Date fechaInicio;		
	private Date fechaFin;		
	private String tipoEventoCcb;		
	private String lugar;		
	private String descripcion;		
	private String temasTratados;		
	private String resumen;		
	private String personaActa;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String capacitador;	
	private String tipoCapacitacion;
	private String materia;	
	private String materiaOtros;	
	
	
    public EventoCcbDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdEventoCcb(){
		return this.idEventoCcb;
	}
	
	public void setIdEventoCcb(Long idEventoCcb){
		this.idEventoCcb = idEventoCcb;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
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
		
	public String getTipoEventoCcb(){
		return this.tipoEventoCcb;
	}
	
	public void setTipoEventoCcb(String tipoEventoCcb){
		this.tipoEventoCcb = tipoEventoCcb;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getTemasTratados(){
		return this.temasTratados;
	}
	
	public void setTemasTratados(String temasTratados){
		this.temasTratados = temasTratados;
	}
		
	public String getResumen(){
		return this.resumen;
	}
	
	public void setResumen(String resumen){
		this.resumen = resumen;
	}
		
	public String getPersonaActa(){
		return this.personaActa;
	}
	
	public void setPersonaActa(String personaActa){
		this.personaActa = personaActa;
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
	 * @return the capacitador
	 */
	public String getCapacitador() {
		return capacitador;
	}

	/**
	 * @param capacitador the capacitador to set
	 */
	public void setCapacitador(String capacitador) {
		this.capacitador = capacitador;
	}

	/**
	 * @return the tipoCapacitacion
	 */
	public String getTipoCapacitacion() {
		return tipoCapacitacion;
	}

	/**
	 * @param tipoCapacitacion the tipoCapacitacion to set
	 */
	public void setTipoCapacitacion(String tipoCapacitacion) {
		this.tipoCapacitacion = tipoCapacitacion;
	}

	/**
	 * @return the materia
	 */
	public String getMateria() {
		return materia;
	}

	/**
	 * @param materia the materia to set
	 */
	public void setMateria(String materia) {
		this.materia = materia;
	}

	/**
	 * @return the materiaOtros
	 */
	public String getMateriaOtros() {
		return materiaOtros;
	}

	/**
	 * @param materiaOtros the materiaOtros to set
	 */
	public void setMateriaOtros(String materiaOtros) {
		this.materiaOtros = materiaOtros;
	}


		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idEventoCcb);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoEventoCcb);
        hash = 37 * hash + Objects.hashCode(this.lugar);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.temasTratados);
        hash = 37 * hash + Objects.hashCode(this.resumen);
        hash = 37 * hash + Objects.hashCode(this.personaActa);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);        
        hash = 37 * hash + Objects.hashCode(this.capacitador);
        hash = 37 * hash + Objects.hashCode(this.tipoCapacitacion);
        hash = 37 * hash + Objects.hashCode(this.materia);
        hash = 37 * hash + Objects.hashCode(this.materiaOtros);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EventoCcbDTO que se pasa
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
        final EventoCcbDTO other = (EventoCcbDTO) obj;
                
        if (!Objects.equals(this.idEventoCcb, other.idEventoCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoEventoCcb, other.tipoEventoCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.temasTratados, other.temasTratados)) {
            return false;
        }
        
        if (!Objects.equals(this.resumen, other.resumen)) {
            return false;
        }
        
        if (!Objects.equals(this.personaActa, other.personaActa)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }          
        
        if (!Objects.equals(this.capacitador, other.capacitador)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCapacitacion, other.tipoCapacitacion)) {
            return false;
        }
        
        if (!Objects.equals(this.materia, other.materia)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaOtros, other.materiaOtros)) {
            return false;
        }        

        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public EventoCcbDTO transformarSinDependencias(EventoCcb obj) {
		EventoCcbDTO eventoCcbDTO = new EventoCcbDTO();
		
		eventoCcbDTO.setIdEventoCcb(obj.getIdEventoCcb());
		eventoCcbDTO.setEstado(obj.getEstado());
		eventoCcbDTO.setFechaInicio(obj.getFechaInicio());
		eventoCcbDTO.setFechaFin(obj.getFechaFin());
		eventoCcbDTO.setTipoEventoCcb(obj.getTipoEventoCcb());
		eventoCcbDTO.setLugar(obj.getLugar());
		eventoCcbDTO.setDescripcion(obj.getDescripcion());
		eventoCcbDTO.setTemasTratados(obj.getTemasTratados());
		eventoCcbDTO.setResumen(obj.getResumen());
		eventoCcbDTO.setPersonaActa(obj.getPersonaActa());
		eventoCcbDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		eventoCcbDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		eventoCcbDTO.setEstadoRegistro(obj.getEstadoRegistro());
		eventoCcbDTO.setCapacitador(obj.getCapacitador());
		eventoCcbDTO.setTipoCapacitacion(obj.getTipoCapacitacion());
		eventoCcbDTO.setMateria(obj.getMateria());
		eventoCcbDTO.setMateriaOtros(obj.getMateriaOtros());
		
		return eventoCcbDTO;
	}

	@Override
	public EventoCcbDTO transformarConDependencias(EventoCcb obj) {
		EventoCcbDTO eventoCcbDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return eventoCcbDTO;
	}

	@Override
	public EventoCcb transformarEntidadSinDependencias(EventoCcb obj) {
		EventoCcb eventoCcb = new EventoCcb();
		
		eventoCcb.setIdEventoCcb(obj.getIdEventoCcb());
		
		eventoCcb.setEstado(obj.getEstado());
		eventoCcb.setFechaInicio(obj.getFechaInicio());
		eventoCcb.setFechaFin(obj.getFechaFin());
		eventoCcb.setTipoEventoCcb(obj.getTipoEventoCcb());
		eventoCcb.setLugar(obj.getLugar());
		eventoCcb.setDescripcion(obj.getDescripcion());
		eventoCcb.setTemasTratados(obj.getTemasTratados());
		eventoCcb.setResumen(obj.getResumen());
		eventoCcb.setPersonaActa(obj.getPersonaActa());
		eventoCcb.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		eventoCcb.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		eventoCcb.setEstadoRegistro(obj.getEstadoRegistro());		
		eventoCcb.setCapacitador(obj.getCapacitador());
		eventoCcb.setTipoCapacitacion(obj.getTipoCapacitacion());
		eventoCcb.setMateria(obj.getMateria());
		eventoCcb.setMateriaOtros(obj.getMateriaOtros());
		
		return eventoCcb;
	}


	@Override
	public EventoCcb transformarEntidadConDependencias(EventoCcb obj) {
		EventoCcb eventoCcb = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		eventoCcb.setPersonaEventoCcbList((List<PersonaEventoCcb>) new PersonaEventoCcbDTO()
				.transformarColeccionEntidadesSinDependencias(obj.getPersonaEventoCcbList()));
		// protected region modificaciones transformarEntidadConDependencias end
		
		return eventoCcb;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EventoCcb> coleccion) {
		List<EventoCcbDTO> eventoCcbDTOList = new ArrayList<>();
		for(EventoCcb c : coleccion)
			eventoCcbDTOList.add(transformarConDependencias(c));
		return eventoCcbDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EventoCcb> coleccion) {
		List<EventoCcbDTO> eventoCcbDTOList = new ArrayList<>();
		for(EventoCcb c : coleccion)
			eventoCcbDTOList.add(transformarSinDependencias(c));
		return eventoCcbDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EventoCcb> coleccion) {
		List<EventoCcb> eventoCcbList = new ArrayList<>();
		for(EventoCcb c : coleccion)
			eventoCcbList.add(transformarEntidadConDependencias(c));
		return eventoCcbList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EventoCcb> coleccion) {
		List<EventoCcb> eventoCcbList = new ArrayList<>();
		for(EventoCcb c : coleccion)
			eventoCcbList.add(transformarEntidadSinDependencias(c));
		return eventoCcbList;
	}




	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
