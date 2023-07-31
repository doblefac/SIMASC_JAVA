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

import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.PreseleccionadoPK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PreseleccionadoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PreseleccionadoDTO extends IDTO<Preseleccionado> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private Long idPersona;
	private Long idCaso;
	private String nombreCompleto;
	// protected region atributos end
	private PreseleccionadoPK preseleccionadoPK;

	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;
	private String tipoPreseleccion;	
	private String materiasPreseleccionadasCaso;
	
    public PreseleccionadoDTO(){
		preseleccionadoPK = new PreseleccionadoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PreseleccionadoPK getPreseleccionadoPK(){
		return this.preseleccionadoPK;
	}
	
	public void setPreseleccionadoPK(PreseleccionadoPK preseleccionadoPK){
		this.preseleccionadoPK   = preseleccionadoPK ;
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
        
        hash = 37 * hash + Objects.hashCode(this.preseleccionadoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PreseleccionadoDTO que se pasa
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
        final PreseleccionadoDTO other = (PreseleccionadoDTO) obj;
                
        if (!Objects.equals(this.preseleccionadoPK, other.preseleccionadoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
        	return false;
        }
        if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public PreseleccionadoDTO transformarSinDependencias(Preseleccionado obj) {
		PreseleccionadoDTO preseleccionadoDTO = new PreseleccionadoDTO();
		
		preseleccionadoDTO.setPreseleccionadoPK(obj.getPreseleccionadoPK());
		preseleccionadoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		preseleccionadoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		preseleccionadoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		preseleccionadoDTO.setTipoPreseleccion(obj.getTipoPreseleccion());
		
		return preseleccionadoDTO;
	}

	@Override
	public PreseleccionadoDTO transformarConDependencias(Preseleccionado obj) {
		PreseleccionadoDTO preseleccionadoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return preseleccionadoDTO;
	}

	@Override
	public Preseleccionado transformarEntidadSinDependencias(Preseleccionado obj) {
		Preseleccionado preseleccionado = new Preseleccionado();
		
		preseleccionado.setPreseleccionadoPK(obj.getPreseleccionadoPK());
		
		preseleccionado.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		preseleccionado.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		preseleccionado.setEstadoRegistro(obj.getEstadoRegistro());
		preseleccionado.setTipoPreseleccion(obj.getTipoPreseleccion());
		
		return preseleccionado;
	}


	@Override
	public Preseleccionado transformarEntidadConDependencias(Preseleccionado obj) {
		Preseleccionado preseleccionado = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return preseleccionado;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Preseleccionado> coleccion) {
		List<PreseleccionadoDTO> preseleccionadoDTOList = new ArrayList<>();
		for(Preseleccionado c : coleccion)
			preseleccionadoDTOList.add(transformarConDependencias(c));
		return preseleccionadoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Preseleccionado> coleccion) {
		List<PreseleccionadoDTO> preseleccionadoDTOList = new ArrayList<>();
		for(Preseleccionado c : coleccion)
			preseleccionadoDTOList.add(transformarSinDependencias(c));
		return preseleccionadoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Preseleccionado> coleccion) {
		List<Preseleccionado> preseleccionadoList = new ArrayList<>();
		for(Preseleccionado c : coleccion)
			preseleccionadoList.add(transformarEntidadConDependencias(c));
		return preseleccionadoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Preseleccionado> coleccion) {
		List<Preseleccionado> preseleccionadoList = new ArrayList<>();
		for(Preseleccionado c : coleccion)
			preseleccionadoList.add(transformarEntidadSinDependencias(c));
		return preseleccionadoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Long getIdCaso() {
		return idCaso;
	}
	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}

	public String getMateriasPreseleccionadasCaso() {
		return materiasPreseleccionadasCaso;
	}


	public void setMateriasPreseleccionadasCaso(String materiasPreseleccionadasCaso) {
		this.materiasPreseleccionadasCaso = materiasPreseleccionadasCaso;
	}
	
	
	
	// protected region metodos adicionales end

}
