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

import com.ccb.simasc.transversal.entidades.PlantillaCarta;
import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PlantillaCartaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PlantillaCartaDTO extends IDTO<PlantillaCarta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idPlantillaCarta;

	private String nombre;		
	private String tipoServicio;		
	private String plantillaHtml;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idServicio;		
	private Long idConvenio;		
	
    public PlantillaCartaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getPlantillaHtml(){
		return this.plantillaHtml;
	}
	
	public void setPlantillaHtml(String plantillaHtml){
		this.plantillaHtml = plantillaHtml;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.plantillaHtml);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PlantillaCartaDTO que se pasa
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
        final PlantillaCartaDTO other = (PlantillaCartaDTO) obj;
                
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.plantillaHtml, other.plantillaHtml)) {
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
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.idConvenio, other.idConvenio);
                
    }
    
    @Override
	public PlantillaCartaDTO transformarSinDependencias(PlantillaCarta obj) {
		PlantillaCartaDTO plantillaCartaDTO = new PlantillaCartaDTO();
		
		plantillaCartaDTO.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		plantillaCartaDTO.setNombre(obj.getNombre());
		plantillaCartaDTO.setTipoServicio(obj.getTipoServicio());
		plantillaCartaDTO.setPlantillaHtml(obj.getPlantillaHtml());
		plantillaCartaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		plantillaCartaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		plantillaCartaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		plantillaCartaDTO.setIdServicio(obj.getIdServicio());
		plantillaCartaDTO.setIdConvenio(obj.getIdConvenio());
		
		return plantillaCartaDTO;
	}

	@Override
	public PlantillaCartaDTO transformarConDependencias(PlantillaCarta obj) {
		PlantillaCartaDTO plantillaCartaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return plantillaCartaDTO;
	}

	@Override
	public PlantillaCarta transformarEntidadSinDependencias(PlantillaCarta obj) {
		PlantillaCarta plantillaCarta = new PlantillaCarta();
		
		plantillaCarta.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		
		plantillaCarta.setNombre(obj.getNombre());
		plantillaCarta.setTipoServicio(obj.getTipoServicio());
		plantillaCarta.setPlantillaHtml(obj.getPlantillaHtml());
		plantillaCarta.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		plantillaCarta.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		plantillaCarta.setEstadoRegistro(obj.getEstadoRegistro());
		plantillaCarta.setIdServicio(obj.getIdServicio());
		plantillaCarta.setIdConvenio(obj.getIdConvenio());
		
		return plantillaCarta;
	}


	@Override
	public PlantillaCarta transformarEntidadConDependencias(PlantillaCarta obj) {
		PlantillaCarta plantillaCarta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		plantillaCarta.setValorPlantillaCartaList((List<ValorPlantillaCarta>) new ValorPlantillaCartaDTO()
				.transformarColeccionEntidadesSinDependencias(obj.getValorPlantillaCartaList()));
		// protected region modificaciones transformarEntidadConDependencias end
		
		return plantillaCarta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PlantillaCarta> coleccion) {
		List<PlantillaCartaDTO> plantillaCartaDTOList = new ArrayList<>();
		for(PlantillaCarta c : coleccion)
			plantillaCartaDTOList.add(transformarConDependencias(c));
		return plantillaCartaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PlantillaCarta> coleccion) {
		List<PlantillaCartaDTO> plantillaCartaDTOList = new ArrayList<>();
		for(PlantillaCarta c : coleccion)
			plantillaCartaDTOList.add(transformarSinDependencias(c));
		return plantillaCartaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PlantillaCarta> coleccion) {
		List<PlantillaCarta> plantillaCartaList = new ArrayList<>();
		for(PlantillaCarta c : coleccion)
			plantillaCartaList.add(transformarEntidadConDependencias(c));
		return plantillaCartaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PlantillaCarta> coleccion) {
		List<PlantillaCarta> plantillaCartaList = new ArrayList<>();
		for(PlantillaCarta c : coleccion)
			plantillaCartaList.add(transformarEntidadSinDependencias(c));
		return plantillaCartaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
