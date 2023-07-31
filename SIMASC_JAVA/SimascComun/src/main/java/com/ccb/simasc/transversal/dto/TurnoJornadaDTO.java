package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.TurnoJornada;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TurnoJornadaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TurnoJornadaDTO extends IDTO<TurnoJornada> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private String intervalo;
	private Long consecutivo;

	// protected region atributos end
	private Long idTurnoJornada;

	private Date horaInicio;		
	private Date horaFin;		
	private Integer limiteAudiencias;		
	private Long idConvenio;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;
	
    public TurnoJornadaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdTurnoJornada(){
		return this.idTurnoJornada;
	}
	
	public void setIdTurnoJornada(Long idTurnoJornada){
		this.idTurnoJornada = idTurnoJornada;
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
		
	public Integer getLimiteAudiencias(){
		return this.limiteAudiencias;
	}
	
	public void setLimiteAudiencias(Integer limiteAudiencias){
		this.limiteAudiencias = limiteAudiencias;
	}
		
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
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
        
        hash = 37 * hash + Objects.hashCode(this.idTurnoJornada);        
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.limiteAudiencias);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TurnoJornadaDTO que se pasa
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
        final TurnoJornadaDTO other = (TurnoJornadaDTO) obj;
                
        if (!Objects.equals(this.idTurnoJornada, other.idTurnoJornada)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteAudiencias, other.limiteAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
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
	public TurnoJornadaDTO transformarSinDependencias(TurnoJornada obj) {
		TurnoJornadaDTO turnoJornadaDTO = new TurnoJornadaDTO();
		
		turnoJornadaDTO.setIdTurnoJornada(obj.getIdTurnoJornada());
		turnoJornadaDTO.setHoraInicio(obj.getHoraInicio());
		turnoJornadaDTO.setHoraFin(obj.getHoraFin());
		turnoJornadaDTO.setLimiteAudiencias(obj.getLimiteAudiencias());
		turnoJornadaDTO.setIdConvenio(obj.getIdConvenio());
		turnoJornadaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		turnoJornadaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		turnoJornadaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return turnoJornadaDTO;
	}

	@Override
	public TurnoJornadaDTO transformarConDependencias(TurnoJornada obj) {
		TurnoJornadaDTO turnoJornadaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return turnoJornadaDTO;
	}

	@Override
	public TurnoJornada transformarEntidadSinDependencias(TurnoJornada obj) {
		TurnoJornada turnoJornada = new TurnoJornada();
		
		turnoJornada.setIdTurnoJornada(obj.getIdTurnoJornada());
		
		turnoJornada.setHoraInicio(obj.getHoraInicio());
		turnoJornada.setHoraFin(obj.getHoraFin());
		turnoJornada.setLimiteAudiencias(obj.getLimiteAudiencias());
		turnoJornada.setIdConvenio(obj.getIdConvenio());
		turnoJornada.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		turnoJornada.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		turnoJornada.setEstadoRegistro(obj.getEstadoRegistro());
		
		return turnoJornada;
	}


	@Override
	public TurnoJornada transformarEntidadConDependencias(TurnoJornada obj) {
		TurnoJornada turnoJornada = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return turnoJornada;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TurnoJornada> coleccion) {
		List<TurnoJornadaDTO> turnoJornadaDTOList = new ArrayList<>();
		for(TurnoJornada c : coleccion)
			turnoJornadaDTOList.add(transformarConDependencias(c));
		return turnoJornadaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TurnoJornada> coleccion) {
		List<TurnoJornadaDTO> turnoJornadaDTOList = new ArrayList<>();
		for(TurnoJornada c : coleccion)
			turnoJornadaDTOList.add(transformarSinDependencias(c));
		return turnoJornadaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TurnoJornada> coleccion) {
		List<TurnoJornada> turnoJornadaList = new ArrayList<>();
		for(TurnoJornada c : coleccion)
			turnoJornadaList.add(transformarEntidadConDependencias(c));
		return turnoJornadaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TurnoJornada> coleccion) {
		List<TurnoJornada> turnoJornadaList = new ArrayList<>();
		for(TurnoJornada c : coleccion)
			turnoJornadaList.add(transformarEntidadSinDependencias(c));
		return turnoJornadaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * @return the intervalo
	 */
	public String getIntervalo() {
		return intervalo;
	}
	
	
	
	/**
	 * @param intervalo the intervalo to set
	 */
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}



	/**
	 * @return the consecutivo
	 */
	public Long getConsecutivo() {
		return consecutivo;
	}



	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
	// protected region metodos adicionales end

}
