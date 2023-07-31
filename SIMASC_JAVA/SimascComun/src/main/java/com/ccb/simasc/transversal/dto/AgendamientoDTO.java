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

import com.ccb.simasc.transversal.entidades.Agendamiento;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AgendamientoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AgendamientoDTO extends IDTO<Agendamiento> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private List<InfraestructuraSolicitadaAgendamientoDTO> lstInfraestructuraSolicitada;
	private List<LogisticaSolicitadaAgendamientoDTO> lstLogisticaSolicitada;	
	private String numeroSala;
	private Long idSede;
	private String nombretipoReunion;
	// protected region atributos end
	private Long idAgendamiento;

	private Long idAudiencia;		
	private Long idSala;		
	private Date horaInicio;		
	private Date horaFin;		
	private String tipoReunion;		
	private String responsable;		
	private Integer cantidadDeAsistentes;		
	private String descripcion;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public AgendamientoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAgendamiento(){
		return this.idAgendamiento;
	}
	
	public void setIdAgendamiento(Long idAgendamiento){
		this.idAgendamiento = idAgendamiento;
	}
	
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdSala(){
		return this.idSala;
	}
	
	public void setIdSala(Long idSala){
		this.idSala = idSala;
	}
		
	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Date getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Date horaFin){
		this.horaFin = horaFin;
	}
		
	public String getTipoReunion(){
		return this.tipoReunion;
	}
	
	public void setTipoReunion(String tipoReunion){
		this.tipoReunion = tipoReunion;
	}
		
	public String getResponsable(){
		return this.responsable;
	}
	
	public void setResponsable(String responsable){
		this.responsable = responsable;
	}
		
	public Integer getCantidadDeAsistentes(){
		return this.cantidadDeAsistentes;
	}
	
	public void setCantidadDeAsistentes(Integer cantidadDeAsistentes){
		this.cantidadDeAsistentes = cantidadDeAsistentes;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
        
        hash = 37 * hash + Objects.hashCode(this.idAgendamiento);        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idSala);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoReunion);
        hash = 37 * hash + Objects.hashCode(this.responsable);
        hash = 37 * hash + Objects.hashCode(this.cantidadDeAsistentes);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AgendamientoDTO que se pasa
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
        final AgendamientoDTO other = (AgendamientoDTO) obj;
                
        if (!Objects.equals(this.idAgendamiento, other.idAgendamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoReunion, other.tipoReunion)) {
            return false;
        }
        
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadDeAsistentes, other.cantidadDeAsistentes)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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
	public AgendamientoDTO transformarSinDependencias(Agendamiento obj) {
		AgendamientoDTO agendamientoDTO = new AgendamientoDTO();
		
		agendamientoDTO.setIdAgendamiento(obj.getIdAgendamiento());
		agendamientoDTO.setIdAudiencia(obj.getIdAudiencia());
		agendamientoDTO.setIdSala(obj.getIdSala());
		agendamientoDTO.setHoraInicio(obj.getHoraInicio());
		agendamientoDTO.setHoraFin(obj.getHoraFin());
		agendamientoDTO.setTipoReunion(obj.getTipoReunion());
		agendamientoDTO.setResponsable(obj.getResponsable());
		agendamientoDTO.setCantidadDeAsistentes(obj.getCantidadDeAsistentes());
		agendamientoDTO.setDescripcion(obj.getDescripcion());
		agendamientoDTO.setIdCaso(obj.getIdCaso());
		agendamientoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agendamientoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agendamientoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return agendamientoDTO;
	}

	@Override
	public AgendamientoDTO transformarConDependencias(Agendamiento obj) {
		AgendamientoDTO agendamientoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return agendamientoDTO;
	}

	@Override
	public Agendamiento transformarEntidadSinDependencias(Agendamiento obj) {
		Agendamiento agendamiento = new Agendamiento();
		
		agendamiento.setIdAgendamiento(obj.getIdAgendamiento());
		
		agendamiento.setIdAudiencia(obj.getIdAudiencia());
		agendamiento.setIdSala(obj.getIdSala());
		agendamiento.setHoraInicio(obj.getHoraInicio());
		agendamiento.setHoraFin(obj.getHoraFin());
		agendamiento.setTipoReunion(obj.getTipoReunion());
		agendamiento.setResponsable(obj.getResponsable());
		agendamiento.setCantidadDeAsistentes(obj.getCantidadDeAsistentes());
		agendamiento.setDescripcion(obj.getDescripcion());
		agendamiento.setIdCaso(obj.getIdCaso());
		agendamiento.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		agendamiento.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		agendamiento.setEstadoRegistro(obj.getEstadoRegistro());
		
		return agendamiento;
	}


	@Override
	public Agendamiento transformarEntidadConDependencias(Agendamiento obj) {
		Agendamiento agendamiento = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return agendamiento;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Agendamiento> coleccion) {
		List<AgendamientoDTO> agendamientoDTOList = new ArrayList<>();
		for(Agendamiento c : coleccion)
			agendamientoDTOList.add(transformarConDependencias(c));
		return agendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Agendamiento> coleccion) {
		List<AgendamientoDTO> agendamientoDTOList = new ArrayList<>();
		for(Agendamiento c : coleccion)
			agendamientoDTOList.add(transformarSinDependencias(c));
		return agendamientoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Agendamiento> coleccion) {
		List<Agendamiento> agendamientoList = new ArrayList<>();
		for(Agendamiento c : coleccion)
			agendamientoList.add(transformarEntidadConDependencias(c));
		return agendamientoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Agendamiento> coleccion) {
		List<Agendamiento> agendamientoList = new ArrayList<>();
		for(Agendamiento c : coleccion)
			agendamientoList.add(transformarEntidadSinDependencias(c));
		return agendamientoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<InfraestructuraSolicitadaAgendamientoDTO> getLstInfraestructuraSolicitada() {
		return lstInfraestructuraSolicitada;
	}



	public void setLstInfraestructuraSolicitada(
			List<InfraestructuraSolicitadaAgendamientoDTO> lstInfraestructuraSolicitada) {
		this.lstInfraestructuraSolicitada = lstInfraestructuraSolicitada;
	}



	public List<LogisticaSolicitadaAgendamientoDTO> getLstLogisticaSolicitada() {
		return lstLogisticaSolicitada;
	}



	public void setLstLogisticaSolicitada(List<LogisticaSolicitadaAgendamientoDTO> lstLogisticaSolicitada) {
		this.lstLogisticaSolicitada = lstLogisticaSolicitada;
	}

	public String getNumeroSala() {
		return numeroSala;
	}



	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}



	public Long getIdSede() {
		return idSede;
	}



	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}



	public String getNombretipoReunion() {
		return nombretipoReunion;
	}



	public void setNombretipoReunion(String nombretipoReunion) {
		this.nombretipoReunion = nombretipoReunion;
	}

	// protected region metodos adicionales end

}
