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

import com.ccb.simasc.transversal.entidades.PersonaSuspension;
import com.ccb.simasc.transversal.entidades.Suspension;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SuspensionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SuspensionDTO extends IDTO<Suspension> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	private String motivoDevolucion;
	private String descripcionDevolucion;
	private Long numeroDias;

	// protected region atributos end
	private Long idSuspension;

	private String tipo;		
	private Date fechaInicial;		
	private Date fechaFinal;		
	private String motivo;		
	private boolean devolucionExpedienteACac;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String quienSuspende;
	
    public SuspensionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSuspension(){
		return this.idSuspension;
	}
	
	public void setIdSuspension(Long idSuspension){
		this.idSuspension = idSuspension;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFechaInicial(){
		return this.fechaInicial;
	}
	
	public void setFechaInicial(Date fechaInicial){
		this.fechaInicial = fechaInicial;
	}
		
	public Date getFechaFinal(){
		return this.fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal){
		this.fechaFinal = fechaFinal;
	}
		
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
		
	public boolean getDevolucionExpedienteACac(){
		return this.devolucionExpedienteACac;
	}
	
	public void setDevolucionExpedienteACac(boolean devolucionExpedienteACac){
		this.devolucionExpedienteACac = devolucionExpedienteACac;
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
        
        hash = 37 * hash + Objects.hashCode(this.idSuspension);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fechaInicial);
        hash = 37 * hash + Objects.hashCode(this.fechaFinal);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + (this.devolucionExpedienteACac ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SuspensionDTO que se pasa
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
        final SuspensionDTO other = (SuspensionDTO) obj;
                
        if (!Objects.equals(this.idSuspension, other.idSuspension)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        
        if (!Objects.equals(this.devolucionExpedienteACac, other.devolucionExpedienteACac)) {
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
	public SuspensionDTO transformarSinDependencias(Suspension obj) {
		SuspensionDTO suspensionDTO = new SuspensionDTO();
		
		suspensionDTO.setIdSuspension(obj.getIdSuspension());
		suspensionDTO.setTipo(obj.getTipo());
		suspensionDTO.setFechaInicial(obj.getFechaInicial());
		suspensionDTO.setFechaFinal(obj.getFechaFinal());
		suspensionDTO.setMotivo(obj.getMotivo());
		suspensionDTO.setDevolucionExpedienteACac(obj.getDevolucionExpedienteACac());
		suspensionDTO.setIdCaso(obj.getIdCaso());
		suspensionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		suspensionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		suspensionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		suspensionDTO.setQuienSuspende(obj.getQuienSuspende());
		
		return suspensionDTO;
	}

	@Override
	public SuspensionDTO transformarConDependencias(Suspension obj) {
		SuspensionDTO suspensionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return suspensionDTO;
	}

	@Override
	public Suspension transformarEntidadSinDependencias(Suspension obj) {
		Suspension suspension = new Suspension();
		
		suspension.setIdSuspension(obj.getIdSuspension());
		
		suspension.setTipo(obj.getTipo());
		suspension.setFechaInicial(obj.getFechaInicial());
		suspension.setFechaFinal(obj.getFechaFinal());
		suspension.setMotivo(obj.getMotivo());
		suspension.setDevolucionExpedienteACac(obj.getDevolucionExpedienteACac());
		suspension.setIdCaso(obj.getIdCaso());
		suspension.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		suspension.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		suspension.setEstadoRegistro(obj.getEstadoRegistro());
		suspension.setQuienSuspende(obj.getQuienSuspende());
		
		return suspension;
	}


	@Override
	public Suspension transformarEntidadConDependencias(Suspension obj) {
		Suspension suspension = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		PersonaSuspensionDTO transformadorPersonasSuspensiones = new PersonaSuspensionDTO();
		suspension.setPersonaSuspensionList((List<PersonaSuspension>)
				transformadorPersonasSuspensiones.transformarColeccionEntidadesSinDependencias(obj.getPersonaSuspensionList()));
		// protected region modificaciones transformarEntidadConDependencias end
		
		return suspension;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Suspension> coleccion) {
		List<SuspensionDTO> suspensionDTOList = new ArrayList<>();
		for(Suspension c : coleccion)
			suspensionDTOList.add(transformarConDependencias(c));
		return suspensionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Suspension> coleccion) {
		List<SuspensionDTO> suspensionDTOList = new ArrayList<>();
		for(Suspension c : coleccion)
			suspensionDTOList.add(transformarSinDependencias(c));
		return suspensionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Suspension> coleccion) {
		List<Suspension> suspensionList = new ArrayList<>();
		for(Suspension c : coleccion)
			suspensionList.add(transformarEntidadConDependencias(c));
		return suspensionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Suspension> coleccion) {
		List<Suspension> suspensionList = new ArrayList<>();
		for(Suspension c : coleccion)
			suspensionList.add(transformarEntidadSinDependencias(c));
		return suspensionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	

	public Suspension transformarEntidadConDependencias(SuspensionDTO obj) {
		Suspension suspension = transformarEntidadSinDependencias(obj);
		
		return suspension;
	}
	
	public Suspension transformarEntidadSinDependencias(SuspensionDTO obj) {
		Suspension suspension = new Suspension();
		
		suspension.setIdSuspension(obj.getIdSuspension());
		
		suspension.setTipo(obj.getTipo());
		suspension.setFechaInicial(obj.getFechaInicial());
		suspension.setFechaFinal(obj.getFechaFinal());
		suspension.setMotivo(obj.getMotivo());
		suspension.setDevolucionExpedienteACac(obj.getDevolucionExpedienteACac());
		suspension.setIdCaso(obj.getIdCaso());
		suspension.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		suspension.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		suspension.setEstadoRegistro(obj.getEstadoRegistro());
		suspension.setQuienSuspende(obj.getQuienSuspende());
		
		return suspension;
	}
	
	public String getMotivoDevolucion() {
		return motivoDevolucion;
	}
	
	public void setMotivoDevolucion(String motivoDevolucion) {
		this.motivoDevolucion = motivoDevolucion;
	}	
	
	public String getDescripcionDevolucion() {
		return descripcionDevolucion;
	}	
	
	public void setDescripcionDevolucion(String descripcionDevolucion) {
		this.descripcionDevolucion = descripcionDevolucion;
	}

	public Long getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(Long numeroDias) {
		this.numeroDias = numeroDias;
	}



	public String getQuienSuspende() {
		return quienSuspende;
	}



	public void setQuienSuspende(String quienSuspende) {
		this.quienSuspende = quienSuspende;
	}	
	
	// protected region metodos adicionales end

}
