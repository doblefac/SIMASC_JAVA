package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta sección sus modificaciones


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Sala;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SalaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SalaDTO extends IDTO<Sala> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String codigoReunion;
	private String nombretipoReunion;
	private Long idCaso;
	private Date horaInicio;
	private Date horaFin;
	private List<LogisticaDTO> logisticaSolicitada;
	private GenericoDTO infraestructuraSolicitada;
	// protected region atributos end
	private Long idSala;

	private String numeroSala;		
	private Integer capacidadDeAsistentes;		
	private String tipoServicio;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idSede;		
	
    public SalaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSala(){
		return this.idSala;
	}
	
	public void setIdSala(Long idSala){
		this.idSala = idSala;
	}
	
	public String getNumeroSala(){
		return this.numeroSala;
	}
	
	public void setNumeroSala(String numeroSala){
		this.numeroSala = numeroSala;
	}
		
	public Integer getCapacidadDeAsistentes(){
		return this.capacidadDeAsistentes;
	}
	
	public void setCapacidadDeAsistentes(Integer capacidadDeAsistentes){
		this.capacidadDeAsistentes = capacidadDeAsistentes;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSala);        
        hash = 37 * hash + Objects.hashCode(this.numeroSala);
        hash = 37 * hash + Objects.hashCode(this.capacidadDeAsistentes);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SalaDTO que se pasa
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
        final SalaDTO other = (SalaDTO) obj;
                
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroSala, other.numeroSala)) {
            return false;
        }
        
        if (!Objects.equals(this.capacidadDeAsistentes, other.capacidadDeAsistentes)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
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
        
        return Objects.equals(this.idSede, other.idSede);
                
    }
    
    @Override
	public SalaDTO transformarSinDependencias(Sala obj) {
		SalaDTO salaDTO = new SalaDTO();
		
		salaDTO.setIdSala(obj.getIdSala());
		salaDTO.setNumeroSala(obj.getNumeroSala());
		salaDTO.setCapacidadDeAsistentes(obj.getCapacidadDeAsistentes());
		salaDTO.setTipoServicio(obj.getTipoServicio());
		salaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		salaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		salaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		salaDTO.setIdSede(obj.getIdSede());
		
		return salaDTO;
	}

	@Override
	public SalaDTO transformarConDependencias(Sala obj) {
		SalaDTO salaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return salaDTO;
	}

	@Override
	public Sala transformarEntidadSinDependencias(Sala obj) {
		Sala sala = new Sala();
		
		sala.setIdSala(obj.getIdSala());
		
		sala.setNumeroSala(obj.getNumeroSala());
		sala.setCapacidadDeAsistentes(obj.getCapacidadDeAsistentes());
		sala.setTipoServicio(obj.getTipoServicio());
		sala.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sala.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sala.setEstadoRegistro(obj.getEstadoRegistro());
		sala.setIdSede(obj.getIdSede());
		
		return sala;
	}


	@Override
	public Sala transformarEntidadConDependencias(Sala obj) {
		Sala sala = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return sala;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Sala> coleccion) {
		List<SalaDTO> salaDTOList = new ArrayList<>();
		for(Sala c : coleccion)
			salaDTOList.add(transformarConDependencias(c));
		return salaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Sala> coleccion) {
		List<SalaDTO> salaDTOList = new ArrayList<>();
		for(Sala c : coleccion)
			salaDTOList.add(transformarSinDependencias(c));
		return salaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Sala> coleccion) {
		List<Sala> salaList = new ArrayList<>();
		for(Sala c : coleccion)
			salaList.add(transformarEntidadConDependencias(c));
		return salaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Sala> coleccion) {
		List<Sala> salaList = new ArrayList<>();
		for(Sala c : coleccion)
			salaList.add(transformarEntidadSinDependencias(c));
		return salaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones	
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getCodigoReunion() {
		return codigoReunion;
	}
	public void setCodigoReunion(String codigoReunion) {
		this.codigoReunion = codigoReunion;
	}
	public String getNombretipoReunion() {
		return nombretipoReunion;
	}
	public void setNombretipoReunion(String nombretipoReunion) {
		this.nombretipoReunion = nombretipoReunion;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public List<LogisticaDTO> getLogisticaSolicitada() {
		return logisticaSolicitada;
	}
	public void setLogisticaSolicitada(List<LogisticaDTO> logisticaSolicitada) {
		this.logisticaSolicitada = logisticaSolicitada;
	}
	public GenericoDTO getInfraestructuraSolicitada() {
		return infraestructuraSolicitada;
	}
	public void setInfraestructuraSolicitada(GenericoDTO infraestructuraSolicitada) {
		this.infraestructuraSolicitada = infraestructuraSolicitada;
	}
	// protected region metodos adicionales end

}
