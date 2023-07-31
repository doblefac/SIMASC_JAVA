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

import com.ccb.simasc.transversal.entidades.Telefono;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad TelefonoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TelefonoDTO extends IDTO<Telefono> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idTelefono;

	private String numero;
	private String numeroEnmascarado;
	private String tipoTelefono;		
	private String extension;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idUbicacion;		
	private Long idPersona;		
	
    public TelefonoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdTelefono(){
		return this.idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono){
		this.idTelefono = idTelefono;
	}
	
	public String getNumero(){
		return this.numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
		
	public String getTipoTelefono(){
		return this.tipoTelefono;
	}
	
	public void setTipoTelefono(String tipoTelefono){
		this.tipoTelefono = tipoTelefono;
	}
		
	public String getExtension(){
		return this.extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
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
		
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
    public String getNumeroEnmascarado() {
		return numeroEnmascarado;
	}

	public void setNumeroEnmascarado(String numeroEnmascarado) {
		this.numeroEnmascarado = numeroEnmascarado;
	}


	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idTelefono);        
        hash = 37 * hash + Objects.hashCode(this.numero);
        hash = 37 * hash + Objects.hashCode(this.tipoTelefono);
        hash = 37 * hash + Objects.hashCode(this.extension);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TelefonoDTO que se pasa
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
        final TelefonoDTO other = (TelefonoDTO) obj;
                
        if (!Objects.equals(this.idTelefono, other.idTelefono)) {
            return false;
        }
        
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTelefono, other.tipoTelefono)) {
            return false;
        }
        
        if (!Objects.equals(this.extension, other.extension)) {
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
        
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public TelefonoDTO transformarSinDependencias(Telefono obj) {
		TelefonoDTO telefonoDTO = new TelefonoDTO();
		
		telefonoDTO.setIdTelefono(obj.getIdTelefono());
		telefonoDTO.setNumero(obj.getNumero());
		telefonoDTO.setTipoTelefono(obj.getTipoTelefono());
		telefonoDTO.setExtension(obj.getExtension());
		telefonoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		telefonoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		telefonoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		telefonoDTO.setIdUbicacion(obj.getIdUbicacion());
		telefonoDTO.setIdPersona(obj.getIdPersona());
		
		return telefonoDTO;
	}

	@Override
	public TelefonoDTO transformarConDependencias(Telefono obj) {
		TelefonoDTO telefonoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return telefonoDTO;
	}

	@Override
	public Telefono transformarEntidadSinDependencias(Telefono obj) {
		Telefono telefono = new Telefono();
		
		telefono.setIdTelefono(obj.getIdTelefono());
		
		telefono.setNumero(obj.getNumero());
		telefono.setTipoTelefono(obj.getTipoTelefono());
		telefono.setExtension(obj.getExtension());
		telefono.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		telefono.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		telefono.setEstadoRegistro(obj.getEstadoRegistro());
		telefono.setIdUbicacion(obj.getIdUbicacion());
		telefono.setIdPersona(obj.getIdPersona());
		
		return telefono;
	}


	@Override
	public Telefono transformarEntidadConDependencias(Telefono obj) {
		Telefono telefono = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return telefono;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Telefono> coleccion) {
		List<TelefonoDTO> telefonoDTOList = new ArrayList<>();
		for(Telefono c : coleccion)
			telefonoDTOList.add(transformarConDependencias(c));
		return telefonoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Telefono> coleccion) {
		List<TelefonoDTO> telefonoDTOList = new ArrayList<>();
		for(Telefono c : coleccion)
			telefonoDTOList.add(transformarSinDependencias(c));
		return telefonoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Telefono> coleccion) {
		List<Telefono> telefonoList = new ArrayList<>();
		for(Telefono c : coleccion)
			telefonoList.add(transformarEntidadConDependencias(c));
		return telefonoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Telefono> coleccion) {
		List<Telefono> telefonoList = new ArrayList<>();
		for(Telefono c : coleccion)
			telefonoList.add(transformarEntidadSinDependencias(c));
		return telefonoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public List<Telefono> transformarColeccionDTOAColeccionEntidades(Collection<TelefonoDTO> coleccion) {
		List<Telefono> telefonoList = new ArrayList<>();
		if(coleccion!=null){
			for(TelefonoDTO c : coleccion)
				telefonoList.add(transformarDTOAEntidad(c));
		}
		
		return telefonoList;
	}
	
	public Telefono transformarDTOAEntidad(TelefonoDTO obj) {
		Telefono telefono = new Telefono();
		
		telefono.setIdTelefono(obj.getIdTelefono());
		
		telefono.setNumero(obj.getNumero());
		telefono.setTipoTelefono(obj.getTipoTelefono());
		telefono.setIdUbicacion(obj.getIdUbicacion());
		telefono.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		telefono.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		telefono.setEstadoRegistro(obj.getEstadoRegistro());
		telefono.setIdPersona(obj.getIdPersona());
		telefono.setExtension(obj.getExtension());
		
		return telefono;
	}

	// protected region metodos adicionales end

}
