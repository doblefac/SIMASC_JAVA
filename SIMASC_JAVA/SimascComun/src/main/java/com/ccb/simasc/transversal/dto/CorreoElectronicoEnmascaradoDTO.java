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

import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad CorreoElectronicoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class CorreoElectronicoEnmascaradoDTO extends IDTO<CorreoElectronico> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private String tipoAcuse;
	// protected region atributos end
	private Long idCorreo;

	private String direccionEnmascarada;
	private String direccionAnterior;
	private String tipo;		
	private Long idUbicacion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	
    public CorreoElectronicoEnmascaradoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }
	
	public Long getIdCorreo(){
		return this.idCorreo;
	}
	
	public void setIdCorreo(Long idCorreo){
		this.idCorreo = idCorreo;
	}
	
	public String getDireccionEnmascarada(){
		return this.direccionEnmascarada;
	}
	
	public void setDireccionEnmascarada(String direccionEnmascarada){
		this.direccionEnmascarada = direccionEnmascarada;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
    public String getDireccionAnterior() {
		return direccionAnterior;
	}

	public void setDireccionAnterior(String direccionAnterior) {
		this.direccionAnterior = direccionAnterior;
	}
	

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCorreo);        
        hash = 37 * hash + Objects.hashCode(this.direccionEnmascarada);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoElectronicoDTO que se pasa
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
        final CorreoElectronicoEnmascaradoDTO other = (CorreoElectronicoEnmascaradoDTO) obj;
                
        if (!Objects.equals(this.idCorreo, other.idCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.direccionEnmascarada, other.direccionEnmascarada)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
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
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public CorreoElectronicoEnmascaradoDTO transformarSinDependencias(CorreoElectronico obj) {
		CorreoElectronicoEnmascaradoDTO correoElectronicoDTO = new CorreoElectronicoEnmascaradoDTO();
		
		correoElectronicoDTO.setIdCorreo(obj.getIdCorreo());
		correoElectronicoDTO.setDireccionEnmascarada(obj.getDireccion());
		correoElectronicoDTO.setDireccionAnterior(obj.getDireccion());
		correoElectronicoDTO.setTipo(obj.getTipo());
		correoElectronicoDTO.setIdUbicacion(obj.getIdUbicacion());
		correoElectronicoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronicoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		correoElectronicoDTO.setIdPersona(obj.getIdPersona());
		
		return correoElectronicoDTO;
	}

	@Override
	public CorreoElectronicoEnmascaradoDTO transformarConDependencias(CorreoElectronico obj) {
		CorreoElectronicoEnmascaradoDTO correoElectronicoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return correoElectronicoDTO;
	}
	
	
	@Override
	public CorreoElectronico transformarEntidadSinDependencias(CorreoElectronico obj) {
		CorreoElectronico correoElectronico = new CorreoElectronico();
		
		correoElectronico.setIdCorreo(obj.getIdCorreo());
		
		correoElectronico.setDireccion(obj.getDireccion());		
		correoElectronico.setTipo(obj.getTipo());
		correoElectronico.setIdUbicacion(obj.getIdUbicacion());
		correoElectronico.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronico.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronico.setEstadoRegistro(obj.getEstadoRegistro());
		correoElectronico.setIdPersona(obj.getIdPersona());
		
		return correoElectronico;
	}


	@Override
	public CorreoElectronico transformarEntidadConDependencias(CorreoElectronico obj) {
		CorreoElectronico correoElectronico = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return correoElectronico;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<CorreoElectronico> coleccion) {
		List<CorreoElectronicoEnmascaradoDTO> correoElectronicoDTOList = new ArrayList<>();
		for(CorreoElectronico c : coleccion)
			correoElectronicoDTOList.add(transformarConDependencias(c));
		return correoElectronicoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<CorreoElectronico> coleccion) {
		List<CorreoElectronicoEnmascaradoDTO> correoElectronicoDTOList = new ArrayList<>();
		for(CorreoElectronico c : coleccion)
			correoElectronicoDTOList.add(transformarSinDependencias(c));
		return correoElectronicoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<CorreoElectronico> coleccion) {
		List<CorreoElectronico> correoElectronicoList = new ArrayList<>();
		for(CorreoElectronico c : coleccion)
			correoElectronicoList.add(transformarEntidadConDependencias(c));
		return correoElectronicoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<CorreoElectronico> coleccion) {
		List<CorreoElectronico> correoElectronicoList = new ArrayList<>();
		for(CorreoElectronico c : coleccion)
			correoElectronicoList.add(transformarEntidadSinDependencias(c));
		return correoElectronicoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	public String getTipoAcuse() {
		return tipoAcuse;
	}
	public void setTipoAcuse(String tipoAcuse) {
		this.tipoAcuse = tipoAcuse;
	}
	
	public List<CorreoElectronico> transformarColeccionDTOAColeccionEntidades(Collection<CorreoElectronicoEnmascaradoDTO> coleccion) {
		List<CorreoElectronico> correoElectronicoList = new ArrayList<>();
		if(coleccion!=null){
			for(CorreoElectronicoEnmascaradoDTO c : coleccion)
				correoElectronicoList.add(transformarDTOAEntidad(c));
		}		
		return correoElectronicoList;
	}
	
	public CorreoElectronico transformarDTOAEntidad(CorreoElectronicoEnmascaradoDTO obj) {
		CorreoElectronico correoElectronico = new CorreoElectronico();
		
		correoElectronico.setIdCorreo(obj.getIdCorreo());
		
		correoElectronico.setDireccion(obj.getDireccionEnmascarada());
		correoElectronico.setTipo(obj.getTipo());
		correoElectronico.setIdUbicacion(obj.getIdUbicacion());
		correoElectronico.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronico.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronico.setEstadoRegistro(obj.getEstadoRegistro());
		correoElectronico.setIdPersona(obj.getIdPersona());
		
		return correoElectronico;
	}
	// protected region metodos adicionales end
	
	
	public List<CorreoElectronicoEnmascaradoDTO> transformarColeccionEnmascarando(Collection<CorreoElectronico> coleccion) {
		List<CorreoElectronicoEnmascaradoDTO> correoElectronicoDTOList = new ArrayList<>();
		for(CorreoElectronico c : coleccion)
			correoElectronicoDTOList.add(transformarEnmascarandoDireccion(c));
		return correoElectronicoDTOList;
	}
	
	public CorreoElectronicoEnmascaradoDTO transformarEnmascarandoDireccion(CorreoElectronico obj) {
		CorreoElectronicoEnmascaradoDTO correoElectronicoDTO = new CorreoElectronicoEnmascaradoDTO();
		
		correoElectronicoDTO.setIdCorreo(obj.getIdCorreo());
		correoElectronicoDTO.setDireccionEnmascarada(UtilMascaraTexto.replaceEmailCharactersByDot(obj.getDireccion()));
		correoElectronicoDTO.setDireccionAnterior(obj.getDireccion());
		correoElectronicoDTO.setTipo(obj.getTipo());
		correoElectronicoDTO.setIdUbicacion(obj.getIdUbicacion());
		correoElectronicoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		correoElectronicoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		correoElectronicoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		correoElectronicoDTO.setIdPersona(obj.getIdPersona());
		
		return correoElectronicoDTO;
	}
}
