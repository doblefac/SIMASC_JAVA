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

import com.ccb.simasc.transversal.entidades.Invitado;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad InvitadoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class InvitadoDTO extends IDTO<Invitado> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idInvitado;

	private Long idAudiencia;		
	private String nombre;		
	private String correo;		
	private String ciudad;		
	private String direccion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public InvitadoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
	
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getCorreo(){
		return this.correo;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}
		
	public String getCiudad(){
		return this.ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}
		
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
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
        
        hash = 37 * hash + Objects.hashCode(this.idInvitado);        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.correo);
        hash = 37 * hash + Objects.hashCode(this.ciudad);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad InvitadoDTO que se pasa
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
        final InvitadoDTO other = (InvitadoDTO) obj;
                
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
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
	public InvitadoDTO transformarSinDependencias(Invitado obj) {
		InvitadoDTO invitadoDTO = new InvitadoDTO();
		
		invitadoDTO.setIdInvitado(obj.getIdInvitado());
		invitadoDTO.setIdAudiencia(obj.getIdAudiencia());
		invitadoDTO.setNombre(obj.getNombre());
		invitadoDTO.setCorreo(obj.getCorreo());
		invitadoDTO.setCiudad(obj.getCiudad());
		invitadoDTO.setDireccion(obj.getDireccion());
		invitadoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		invitadoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		invitadoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return invitadoDTO;
	}

	@Override
	public InvitadoDTO transformarConDependencias(Invitado obj) {
		InvitadoDTO invitadoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return invitadoDTO;
	}

	@Override
	public Invitado transformarEntidadSinDependencias(Invitado obj) {
		Invitado invitado = new Invitado();
		
		invitado.setIdInvitado(obj.getIdInvitado());
		
		invitado.setIdAudiencia(obj.getIdAudiencia());
		invitado.setNombre(obj.getNombre());
		invitado.setCorreo(obj.getCorreo());
		invitado.setCiudad(obj.getCiudad());
		invitado.setDireccion(obj.getDireccion());
		invitado.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		invitado.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		invitado.setEstadoRegistro(obj.getEstadoRegistro());
		
		return invitado;
	}


	@Override
	public Invitado transformarEntidadConDependencias(Invitado obj) {
		Invitado invitado = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return invitado;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Invitado> coleccion) {
		List<InvitadoDTO> invitadoDTOList = new ArrayList<>();
		for(Invitado c : coleccion)
			invitadoDTOList.add(transformarConDependencias(c));
		return invitadoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Invitado> coleccion) {
		List<InvitadoDTO> invitadoDTOList = new ArrayList<>();
		for(Invitado c : coleccion)
			invitadoDTOList.add(transformarSinDependencias(c));
		return invitadoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Invitado> coleccion) {
		List<Invitado> invitadoList = new ArrayList<>();
		for(Invitado c : coleccion)
			invitadoList.add(transformarEntidadConDependencias(c));
		return invitadoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Invitado> coleccion) {
		List<Invitado> invitadoList = new ArrayList<>();
		for(Invitado c : coleccion)
			invitadoList.add(transformarEntidadSinDependencias(c));
		return invitadoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
