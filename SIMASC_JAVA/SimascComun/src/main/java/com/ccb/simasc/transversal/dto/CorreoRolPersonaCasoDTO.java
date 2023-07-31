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

import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CorreoRolPersonaCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CorreoRolPersonaCasoDTO extends IDTO<CorreoRolPersonaCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idCorreoRolPersonaCaso;

	private String asunto;		
	private String cuerpoCorreo;		
	private Date fechaEnvio;		
	private String mensaje;		
	private boolean gestionado;		
	private Long idRolReceptor;		
	private Long idPersonaReceptor;		
	private Long idCasoReceptor;		
	private String correoRemitente;		
	private String correoReceptor;		
	private Long idCartaPersona;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idInvitado;		
	private Long idPersonaEnvio;		
	private Long idAudiencia;		
	private Long idPersonaQueRecibe;		
	
    public CorreoRolPersonaCasoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
	
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getCuerpoCorreo(){
		return this.cuerpoCorreo;
	}
	
	public void setCuerpoCorreo(String cuerpoCorreo){
		this.cuerpoCorreo = cuerpoCorreo;
	}
		
	public Date getFechaEnvio(){
		return this.fechaEnvio;
	}
	
	public void setFechaEnvio(Date fechaEnvio){
		this.fechaEnvio = fechaEnvio;
	}
		
	public String getMensaje(){
		return this.mensaje;
	}
	
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}
		
	public boolean getGestionado(){
		return this.gestionado;
	}
	
	public void setGestionado(boolean gestionado){
		this.gestionado = gestionado;
	}
		
	public Long getIdRolReceptor(){
		return this.idRolReceptor;
	}
	
	public void setIdRolReceptor(Long idRolReceptor){
		this.idRolReceptor = idRolReceptor;
	}
		
	public Long getIdPersonaReceptor(){
		return this.idPersonaReceptor;
	}
	
	public void setIdPersonaReceptor(Long idPersonaReceptor){
		this.idPersonaReceptor = idPersonaReceptor;
	}
		
	public Long getIdCasoReceptor(){
		return this.idCasoReceptor;
	}
	
	public void setIdCasoReceptor(Long idCasoReceptor){
		this.idCasoReceptor = idCasoReceptor;
	}
		
	public String getCorreoRemitente(){
		return this.correoRemitente;
	}
	
	public void setCorreoRemitente(String correoRemitente){
		this.correoRemitente = correoRemitente;
	}
		
	public String getCorreoReceptor(){
		return this.correoReceptor;
	}
	
	public void setCorreoReceptor(String correoReceptor){
		this.correoReceptor = correoReceptor;
	}
		
	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
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
		
	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
		
	public Long getIdPersonaEnvio(){
		return this.idPersonaEnvio;
	}
	
	public void setIdPersonaEnvio(Long idPersonaEnvio){
		this.idPersonaEnvio = idPersonaEnvio;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdPersonaQueRecibe(){
		return this.idPersonaQueRecibe;
	}
	
	public void setIdPersonaQueRecibe(Long idPersonaQueRecibe){
		this.idPersonaQueRecibe = idPersonaQueRecibe;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);        
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.cuerpoCorreo);
        hash = 37 * hash + Objects.hashCode(this.fechaEnvio);
        hash = 37 * hash + Objects.hashCode(this.mensaje);
        hash = 37 * hash + (this.gestionado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idRolReceptor);
        hash = 37 * hash + Objects.hashCode(this.idPersonaReceptor);
        hash = 37 * hash + Objects.hashCode(this.idCasoReceptor);
        hash = 37 * hash + Objects.hashCode(this.correoRemitente);
        hash = 37 * hash + Objects.hashCode(this.correoReceptor);
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idInvitado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaEnvio);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idPersonaQueRecibe);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoRolPersonaCasoDTO que se pasa
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
        final CorreoRolPersonaCasoDTO other = (CorreoRolPersonaCasoDTO) obj;
                
        if (!Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.cuerpoCorreo, other.cuerpoCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEnvio, other.fechaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            return false;
        }
        
        if (!Objects.equals(this.gestionado, other.gestionado)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolReceptor, other.idRolReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaReceptor, other.idPersonaReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoReceptor, other.idCasoReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.correoRemitente, other.correoRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.correoReceptor, other.correoReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idCartaPersona, other.idCartaPersona)) {
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
        
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaEnvio, other.idPersonaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        return Objects.equals(this.idPersonaQueRecibe, other.idPersonaQueRecibe);
                
    }
    
    @Override
	public CorreoRolPersonaCasoDTO transformarSinDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCasoDTO correoRolPersonaCasoDTO = new CorreoRolPersonaCasoDTO();
		
		correoRolPersonaCasoDTO.setIdCorreoRolPersonaCaso(obj.getIdCorreoRolPersonaCaso());
		correoRolPersonaCasoDTO.setAsunto(obj.getAsunto());
		correoRolPersonaCasoDTO.setCuerpoCorreo(obj.getCuerpoCorreo());
		correoRolPersonaCasoDTO.setFechaEnvio(obj.getFechaEnvio());
		correoRolPersonaCasoDTO.setMensaje(obj.getMensaje());
		correoRolPersonaCasoDTO.setGestionado(obj.getGestionado());
		correoRolPersonaCasoDTO.setIdRolReceptor(obj.getIdRolReceptor());
		correoRolPersonaCasoDTO.setIdPersonaReceptor(obj.getIdPersonaReceptor());
		correoRolPersonaCasoDTO.setIdCasoReceptor(obj.getIdCasoReceptor());
		correoRolPersonaCasoDTO.setCorreoRemitente(obj.getCorreoRemitente());
		correoRolPersonaCasoDTO.setCorreoReceptor(obj.getCorreoReceptor());
		correoRolPersonaCasoDTO.setIdCartaPersona(obj.getIdCartaPersona());
		correoRolPersonaCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoRolPersonaCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoRolPersonaCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		correoRolPersonaCasoDTO.setIdInvitado(obj.getIdInvitado());
		correoRolPersonaCasoDTO.setIdPersonaEnvio(obj.getIdPersonaEnvio());
		correoRolPersonaCasoDTO.setIdAudiencia(obj.getIdAudiencia());
		correoRolPersonaCasoDTO.setIdPersonaQueRecibe(obj.getIdPersonaQueRecibe());
		
		return correoRolPersonaCasoDTO;
	}

	@Override
	public CorreoRolPersonaCasoDTO transformarConDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCasoDTO correoRolPersonaCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones
		
		// protected region modificaciones transformarConDependencias end
		
		return correoRolPersonaCasoDTO;
	}

	@Override
	public CorreoRolPersonaCaso transformarEntidadSinDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCaso correoRolPersonaCaso = new CorreoRolPersonaCaso();
		
		correoRolPersonaCaso.setIdCorreoRolPersonaCaso(obj.getIdCorreoRolPersonaCaso());
		
		correoRolPersonaCaso.setAsunto(obj.getAsunto());
		correoRolPersonaCaso.setCuerpoCorreo(obj.getCuerpoCorreo());
		correoRolPersonaCaso.setFechaEnvio(obj.getFechaEnvio());
		correoRolPersonaCaso.setMensaje(obj.getMensaje());
		correoRolPersonaCaso.setGestionado(obj.getGestionado());
		correoRolPersonaCaso.setIdRolReceptor(obj.getIdRolReceptor());
		correoRolPersonaCaso.setIdPersonaReceptor(obj.getIdPersonaReceptor());
		correoRolPersonaCaso.setIdCasoReceptor(obj.getIdCasoReceptor());
		correoRolPersonaCaso.setCorreoRemitente(obj.getCorreoRemitente());
		correoRolPersonaCaso.setCorreoReceptor(obj.getCorreoReceptor());
		correoRolPersonaCaso.setIdCartaPersona(obj.getIdCartaPersona());
		correoRolPersonaCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoRolPersonaCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoRolPersonaCaso.setEstadoRegistro(obj.getEstadoRegistro());
		correoRolPersonaCaso.setIdInvitado(obj.getIdInvitado());
		correoRolPersonaCaso.setIdPersonaEnvio(obj.getIdPersonaEnvio());
		correoRolPersonaCaso.setIdAudiencia(obj.getIdAudiencia());
		correoRolPersonaCaso.setIdPersonaQueRecibe(obj.getIdPersonaQueRecibe());
		
		return correoRolPersonaCaso;
	}


	@Override
	public CorreoRolPersonaCaso transformarEntidadConDependencias(CorreoRolPersonaCaso obj) {
		CorreoRolPersonaCaso correoRolPersonaCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if(obj.getRolPersonaCaso()!=null){
			RolPersonaCaso rpc = new RolPersonaCasoDTO().transformarEntidadSinDependencias(obj.getRolPersonaCaso());
			correoRolPersonaCaso.setRolPersonaCaso(rpc);
		}
		if(obj.getInvitado()!=null){
			Invitado inv = new InvitadoDTO().transformarEntidadSinDependencias(obj.getInvitado());
			correoRolPersonaCaso.setInvitado(inv);
		}
		if(obj.getPersonaQueRecibe()!=null){
			Persona pQrcb = new PersonaDTO().transformarEntidadSinDependencias(obj.getPersonaQueRecibe());
			correoRolPersonaCaso.setPersonaQueRecibe(pQrcb);
		}
		// protected region modificaciones transformarEntidadConDependencias end
		
		return correoRolPersonaCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CorreoRolPersonaCaso> coleccion) {
		List<CorreoRolPersonaCasoDTO> correoRolPersonaCasoDTOList = new ArrayList<>();
		for(CorreoRolPersonaCaso c : coleccion)
			correoRolPersonaCasoDTOList.add(transformarConDependencias(c));
		return correoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CorreoRolPersonaCaso> coleccion) {
		List<CorreoRolPersonaCasoDTO> correoRolPersonaCasoDTOList = new ArrayList<>();
		for(CorreoRolPersonaCaso c : coleccion)
			correoRolPersonaCasoDTOList.add(transformarSinDependencias(c));
		return correoRolPersonaCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CorreoRolPersonaCaso> coleccion) {
		List<CorreoRolPersonaCaso> correoRolPersonaCasoList = new ArrayList<>();
		for(CorreoRolPersonaCaso c : coleccion)
			correoRolPersonaCasoList.add(transformarEntidadConDependencias(c));
		return correoRolPersonaCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CorreoRolPersonaCaso> coleccion) {
		List<CorreoRolPersonaCaso> correoRolPersonaCasoList = new ArrayList<>();
		for(CorreoRolPersonaCaso c : coleccion)
			correoRolPersonaCasoList.add(transformarEntidadSinDependencias(c));
		return correoRolPersonaCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
