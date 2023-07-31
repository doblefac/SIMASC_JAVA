package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Notificacion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad NotificacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class NotificacionDTO extends IDTO<Notificacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idNotificacion;

	private String textoAlerta;		
	private String estado;		
	private Date fechaNotificacion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idAlerta;		
	private Long idPersona;		
	
    public NotificacionDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdNotificacion(){
		return this.idNotificacion;
	}
	
	public void setIdNotificacion(Long idNotificacion){
		this.idNotificacion = idNotificacion;
	}
	
	public String getTextoAlerta(){
		return this.textoAlerta;
	}
	
	public void setTextoAlerta(String textoAlerta){
		this.textoAlerta = textoAlerta;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaNotificacion(){
		return this.fechaNotificacion;
	}
	
	public void setFechaNotificacion(Date fechaNotificacion){
		this.fechaNotificacion = fechaNotificacion;
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
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
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
        
        hash = 37 * hash + Objects.hashCode(this.idNotificacion);        
        hash = 37 * hash + Objects.hashCode(this.textoAlerta);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaNotificacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NotificacionDTO que se pasa
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
        final NotificacionDTO other = (NotificacionDTO) obj;
                
        if (!Objects.equals(this.idNotificacion, other.idNotificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.textoAlerta, other.textoAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaNotificacion, other.fechaNotificacion)) {
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
        
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public NotificacionDTO transformarSinDependencias(Notificacion obj) {
		NotificacionDTO notificacionDTO = new NotificacionDTO();
		
		notificacionDTO.setIdNotificacion(obj.getIdNotificacion());
		notificacionDTO.setTextoAlerta(obj.getTextoAlerta());
		notificacionDTO.setEstado(obj.getEstado());
		notificacionDTO.setFechaNotificacion(obj.getFechaNotificacion());
		notificacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		notificacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		notificacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		notificacionDTO.setIdAlerta(obj.getIdAlerta());
		notificacionDTO.setIdPersona(obj.getIdPersona());
		
		return notificacionDTO;
	}

	@Override
	public NotificacionDTO transformarConDependencias(Notificacion obj) {
		NotificacionDTO notificacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return notificacionDTO;
	}

	@Override
	public Notificacion transformarEntidadSinDependencias(Notificacion obj) {
		Notificacion notificacion = new Notificacion();
		
		notificacion.setIdNotificacion(obj.getIdNotificacion());
		
		notificacion.setTextoAlerta(obj.getTextoAlerta());
		notificacion.setEstado(obj.getEstado());
		notificacion.setFechaNotificacion(obj.getFechaNotificacion());
		notificacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		notificacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		notificacion.setEstadoRegistro(obj.getEstadoRegistro());
		notificacion.setIdAlerta(obj.getIdAlerta());
		notificacion.setIdPersona(obj.getIdPersona());
		
		return notificacion;
	}


	@Override
	public Notificacion transformarEntidadConDependencias(Notificacion obj) {
		Notificacion notificacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return notificacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Notificacion> coleccion) {
		List<NotificacionDTO> notificacionDTOList = new ArrayList<>();
		for(Notificacion c : coleccion)
			notificacionDTOList.add(transformarConDependencias(c));
		return notificacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Notificacion> coleccion) {
		List<NotificacionDTO> notificacionDTOList = new ArrayList<>();
		for(Notificacion c : coleccion)
			notificacionDTOList.add(transformarSinDependencias(c));
		return notificacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Notificacion> coleccion) {
		List<Notificacion> notificacionList = new ArrayList<>();
		for(Notificacion c : coleccion)
			notificacionList.add(transformarEntidadConDependencias(c));
		return notificacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Notificacion> coleccion) {
		List<Notificacion> notificacionList = new ArrayList<>();
		for(Notificacion c : coleccion)
			notificacionList.add(transformarEntidadSinDependencias(c));
		return notificacionList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}

