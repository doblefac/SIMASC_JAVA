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

import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Usuario;



// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad UsuarioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class UsuarioDTO extends IDTO<Usuario> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String nuevaClave;
	private String antiguaClave;
	// protected region atributos end
	private String usuarioLogin;

	private String estado;		
	private Long idPersona;		
	private String observaciones;		
	private Date ultimoAcceso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;
	private boolean aplicaMauc;
	
    public UsuarioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getUsuarioLogin(){
		return this.usuarioLogin;
	}
	
	public void setUsuarioLogin(String usuarioLogin){
		this.usuarioLogin = usuarioLogin;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Date getUltimoAcceso(){
		return this.ultimoAcceso;
	}
	
	public void setUltimoAcceso(Date ultimoAcceso){
		this.ultimoAcceso = ultimoAcceso;
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

    public boolean getAplicaMauc() {
		return aplicaMauc;
	}

	public void setAplicaMauc(boolean aplicaMauc) {
		this.aplicaMauc = aplicaMauc;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.usuarioLogin);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.ultimoAcceso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + (this.aplicaMauc ? 0 : 1);

        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UsuarioDTO que se pasa
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
        final UsuarioDTO other = (UsuarioDTO) obj;
                
        if (!Objects.equals(this.usuarioLogin, other.usuarioLogin)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.ultimoAcceso, other.ultimoAcceso)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion);
                
    }
    
    @Override
	public UsuarioDTO transformarSinDependencias(Usuario obj) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setUsuarioLogin(obj.getUsuarioLogin());
		usuarioDTO.setEstado(obj.getEstado());
		usuarioDTO.setIdPersona(obj.getIdPersona());
		usuarioDTO.setObservaciones(obj.getObservaciones());
		usuarioDTO.setUltimoAcceso(obj.getUltimoAcceso());
		usuarioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		usuarioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		usuarioDTO.setAplicaMauc(obj.getAplicaMauc());
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO transformarConDependencias(Usuario obj) {
		UsuarioDTO usuarioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return usuarioDTO;
	}

	@Override
	public Usuario transformarEntidadSinDependencias(Usuario obj) {
		Usuario usuario = new Usuario();
		
		usuario.setUsuarioLogin(obj.getUsuarioLogin());
		
		usuario.setEstado(obj.getEstado());
		usuario.setIdPersona(obj.getIdPersona());
		usuario.setObservaciones(obj.getObservaciones());
		usuario.setUltimoAcceso(obj.getUltimoAcceso());
		usuario.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		usuario.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		usuario.setAplicaMauc(obj.getAplicaMauc());
		return usuario;
	}


	@Override
	public Usuario transformarEntidadConDependencias(Usuario obj) {
		Usuario usuario = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		PersonaDTO tranformadorPersona = new PersonaDTO();
		usuario.setPersona((Persona) tranformadorPersona.transformarEntidadSinDependencias(obj.getPersona()));
		
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return usuario;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Usuario> coleccion) {
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		for(Usuario c : coleccion)
			usuarioDTOList.add(transformarConDependencias(c));
		return usuarioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Usuario> coleccion) {
		List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
		for(Usuario c : coleccion)
			usuarioDTOList.add(transformarSinDependencias(c));
		return usuarioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Usuario> coleccion) {
		List<Usuario> usuarioList = new ArrayList<>();
		for(Usuario c : coleccion)
			usuarioList.add(transformarEntidadConDependencias(c));
		return usuarioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Usuario> coleccion) {
		List<Usuario> usuarioList = new ArrayList<>();
		for(Usuario c : coleccion)
			usuarioList.add(transformarEntidadSinDependencias(c));
		return usuarioList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public String getNuevaClave() {
		return nuevaClave;
	}



	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}



	public String getAntiguaClave() {
		return antiguaClave;
	}



	public void setAntiguaClave(String antiguaClave) {
		this.antiguaClave = antiguaClave;
	}
	// protected region metodos adicionales end

}
