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

import com.ccb.simasc.transversal.entidades.Recusacion;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RecusacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RecusacionDTO extends IDTO<Recusacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idRecusacion;

	private Long idRolArbitro;		
	private Long idPersonaArbitro;		
	private Long idCasoArbitro;		
	private String estado;		
	private Date fechaRecusacion;		
	private String motivoRecusacion;		
	private boolean aceptaRecusacion;		
	private Date fechaRespuestaArbitro;		
	private String tipoDeConfirmacion;		
	private Date fechaConfirmacion;		
	private String juzgadoConfirmacion;		
	private boolean confirmacionNombramiento;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocRecusacion;		
	private Long idDocRespuesta;		
	private Long idDocConfirmacion;		
	
    public RecusacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdRecusacion(){
		return this.idRecusacion;
	}
	
	public void setIdRecusacion(Long idRecusacion){
		this.idRecusacion = idRecusacion;
	}
	
	public Long getIdRolArbitro(){
		return this.idRolArbitro;
	}
	
	public void setIdRolArbitro(Long idRolArbitro){
		this.idRolArbitro = idRolArbitro;
	}
		
	public Long getIdPersonaArbitro(){
		return this.idPersonaArbitro;
	}
	
	public void setIdPersonaArbitro(Long idPersonaArbitro){
		this.idPersonaArbitro = idPersonaArbitro;
	}
		
	public Long getIdCasoArbitro(){
		return this.idCasoArbitro;
	}
	
	public void setIdCasoArbitro(Long idCasoArbitro){
		this.idCasoArbitro = idCasoArbitro;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaRecusacion(){
		return this.fechaRecusacion;
	}
	
	public void setFechaRecusacion(Date fechaRecusacion){
		this.fechaRecusacion = fechaRecusacion;
	}
		
	public String getMotivoRecusacion(){
		return this.motivoRecusacion;
	}
	
	public void setMotivoRecusacion(String motivoRecusacion){
		this.motivoRecusacion = motivoRecusacion;
	}
		
	public boolean getAceptaRecusacion(){
		return this.aceptaRecusacion;
	}
	
	public void setAceptaRecusacion(boolean aceptaRecusacion){
		this.aceptaRecusacion = aceptaRecusacion;
	}
		
	public Date getFechaRespuestaArbitro(){
		return this.fechaRespuestaArbitro;
	}
	
	public void setFechaRespuestaArbitro(Date fechaRespuestaArbitro){
		this.fechaRespuestaArbitro = fechaRespuestaArbitro;
	}
		
	public String getTipoDeConfirmacion(){
		return this.tipoDeConfirmacion;
	}
	
	public void setTipoDeConfirmacion(String tipoDeConfirmacion){
		this.tipoDeConfirmacion = tipoDeConfirmacion;
	}
		
	public Date getFechaConfirmacion(){
		return this.fechaConfirmacion;
	}
	
	public void setFechaConfirmacion(Date fechaConfirmacion){
		this.fechaConfirmacion = fechaConfirmacion;
	}
		
	public String getJuzgadoConfirmacion(){
		return this.juzgadoConfirmacion;
	}
	
	public void setJuzgadoConfirmacion(String juzgadoConfirmacion){
		this.juzgadoConfirmacion = juzgadoConfirmacion;
	}
		
	public boolean getConfirmacionNombramiento(){
		return this.confirmacionNombramiento;
	}
	
	public void setConfirmacionNombramiento(boolean confirmacionNombramiento){
		this.confirmacionNombramiento = confirmacionNombramiento;
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
		
	public Long getIdDocRecusacion(){
		return this.idDocRecusacion;
	}
	
	public void setIdDocRecusacion(Long idDocRecusacion){
		this.idDocRecusacion = idDocRecusacion;
	}
		
	public Long getIdDocRespuesta(){
		return this.idDocRespuesta;
	}
	
	public void setIdDocRespuesta(Long idDocRespuesta){
		this.idDocRespuesta = idDocRespuesta;
	}
		
	public Long getIdDocConfirmacion(){
		return this.idDocConfirmacion;
	}
	
	public void setIdDocConfirmacion(Long idDocConfirmacion){
		this.idDocConfirmacion = idDocConfirmacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idRecusacion);        
        hash = 37 * hash + Objects.hashCode(this.idRolArbitro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaArbitro);
        hash = 37 * hash + Objects.hashCode(this.idCasoArbitro);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaRecusacion);
        hash = 37 * hash + Objects.hashCode(this.motivoRecusacion);
        hash = 37 * hash + (this.aceptaRecusacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaRespuestaArbitro);
        hash = 37 * hash + Objects.hashCode(this.tipoDeConfirmacion);
        hash = 37 * hash + Objects.hashCode(this.fechaConfirmacion);
        hash = 37 * hash + Objects.hashCode(this.juzgadoConfirmacion);
        hash = 37 * hash + (this.confirmacionNombramiento ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocRecusacion);
        hash = 37 * hash + Objects.hashCode(this.idDocRespuesta);
        hash = 37 * hash + Objects.hashCode(this.idDocConfirmacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RecusacionDTO que se pasa
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
        final RecusacionDTO other = (RecusacionDTO) obj;
                
        if (!Objects.equals(this.idRecusacion, other.idRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolArbitro, other.idRolArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaArbitro, other.idPersonaArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoArbitro, other.idCasoArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRecusacion, other.fechaRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoRecusacion, other.motivoRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aceptaRecusacion, other.aceptaRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRespuestaArbitro, other.fechaRespuestaArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeConfirmacion, other.tipoDeConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaConfirmacion, other.fechaConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.juzgadoConfirmacion, other.juzgadoConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacionNombramiento, other.confirmacionNombramiento)) {
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
        
        if (!Objects.equals(this.idDocRecusacion, other.idDocRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocRespuesta, other.idDocRespuesta)) {
            return false;
        }
        
        return Objects.equals(this.idDocConfirmacion, other.idDocConfirmacion);
                
    }
    
    @Override
	public RecusacionDTO transformarSinDependencias(Recusacion obj) {
		RecusacionDTO recusacionDTO = new RecusacionDTO();
		
		recusacionDTO.setIdRecusacion(obj.getIdRecusacion());
		recusacionDTO.setIdRolArbitro(obj.getIdRolArbitro());
		recusacionDTO.setIdPersonaArbitro(obj.getIdPersonaArbitro());
		recusacionDTO.setIdCasoArbitro(obj.getIdCasoArbitro());
		recusacionDTO.setEstado(obj.getEstado());
		recusacionDTO.setFechaRecusacion(obj.getFechaRecusacion());
		recusacionDTO.setMotivoRecusacion(obj.getMotivoRecusacion());
		recusacionDTO.setAceptaRecusacion(obj.getAceptaRecusacion());
		recusacionDTO.setFechaRespuestaArbitro(obj.getFechaRespuestaArbitro());
		recusacionDTO.setTipoDeConfirmacion(obj.getTipoDeConfirmacion());
		recusacionDTO.setFechaConfirmacion(obj.getFechaConfirmacion());
		recusacionDTO.setJuzgadoConfirmacion(obj.getJuzgadoConfirmacion());
		recusacionDTO.setConfirmacionNombramiento(obj.getConfirmacionNombramiento());
		recusacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		recusacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		recusacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		recusacionDTO.setIdDocRecusacion(obj.getIdDocRecusacion());
		recusacionDTO.setIdDocRespuesta(obj.getIdDocRespuesta());
		recusacionDTO.setIdDocConfirmacion(obj.getIdDocConfirmacion());
		
		return recusacionDTO;
	}

	@Override
	public RecusacionDTO transformarConDependencias(Recusacion obj) {
		RecusacionDTO recusacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return recusacionDTO;
	}

	@Override
	public Recusacion transformarEntidadSinDependencias(Recusacion obj) {
		Recusacion recusacion = new Recusacion();
		
		recusacion.setIdRecusacion(obj.getIdRecusacion());
		
		recusacion.setIdRolArbitro(obj.getIdRolArbitro());
		recusacion.setIdPersonaArbitro(obj.getIdPersonaArbitro());
		recusacion.setIdCasoArbitro(obj.getIdCasoArbitro());
		recusacion.setEstado(obj.getEstado());
		recusacion.setFechaRecusacion(obj.getFechaRecusacion());
		recusacion.setMotivoRecusacion(obj.getMotivoRecusacion());
		recusacion.setAceptaRecusacion(obj.getAceptaRecusacion());
		recusacion.setFechaRespuestaArbitro(obj.getFechaRespuestaArbitro());
		recusacion.setTipoDeConfirmacion(obj.getTipoDeConfirmacion());
		recusacion.setFechaConfirmacion(obj.getFechaConfirmacion());
		recusacion.setJuzgadoConfirmacion(obj.getJuzgadoConfirmacion());
		recusacion.setConfirmacionNombramiento(obj.getConfirmacionNombramiento());
		recusacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		recusacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		recusacion.setEstadoRegistro(obj.getEstadoRegistro());
		recusacion.setIdDocRecusacion(obj.getIdDocRecusacion());
		recusacion.setIdDocRespuesta(obj.getIdDocRespuesta());
		recusacion.setIdDocConfirmacion(obj.getIdDocConfirmacion());
		
		return recusacion;
	}


	@Override
	public Recusacion transformarEntidadConDependencias(Recusacion obj) {
		Recusacion recusacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return recusacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Recusacion> coleccion) {
		List<RecusacionDTO> recusacionDTOList = new ArrayList<>();
		for(Recusacion c : coleccion)
			recusacionDTOList.add(transformarConDependencias(c));
		return recusacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Recusacion> coleccion) {
		List<RecusacionDTO> recusacionDTOList = new ArrayList<>();
		for(Recusacion c : coleccion)
			recusacionDTOList.add(transformarSinDependencias(c));
		return recusacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Recusacion> coleccion) {
		List<Recusacion> recusacionList = new ArrayList<>();
		for(Recusacion c : coleccion)
			recusacionList.add(transformarEntidadConDependencias(c));
		return recusacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Recusacion> coleccion) {
		List<Recusacion> recusacionList = new ArrayList<>();
		for(Recusacion c : coleccion)
			recusacionList.add(transformarEntidadSinDependencias(c));
		return recusacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
