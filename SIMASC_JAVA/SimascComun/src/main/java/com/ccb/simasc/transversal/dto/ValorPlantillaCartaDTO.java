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

import com.ccb.simasc.transversal.entidades.ValorPlantillaCarta;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ValorPlantillaCartaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ValorPlantillaCartaDTO extends IDTO<ValorPlantillaCarta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idValorPlantillaCarta;

	private String nombreValorDinamico;		
	private Long idPlantillaCarta;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String descripcion;		
	private String consulta;		
	private boolean llenadoPorConsulta;		
	private Long idTagPlantilla;		
	
    public ValorPlantillaCartaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdValorPlantillaCarta(){
		return this.idValorPlantillaCarta;
	}
	
	public void setIdValorPlantillaCarta(Long idValorPlantillaCarta){
		this.idValorPlantillaCarta = idValorPlantillaCarta;
	}
	
	public String getNombreValorDinamico(){
		return this.nombreValorDinamico;
	}
	
	public void setNombreValorDinamico(String nombreValorDinamico){
		this.nombreValorDinamico = nombreValorDinamico;
	}
		
	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
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
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getConsulta(){
		return this.consulta;
	}
	
	public void setConsulta(String consulta){
		this.consulta = consulta;
	}
		
	public boolean getLlenadoPorConsulta(){
		return this.llenadoPorConsulta;
	}
	
	public void setLlenadoPorConsulta(boolean llenadoPorConsulta){
		this.llenadoPorConsulta = llenadoPorConsulta;
	}
		
	public Long getIdTagPlantilla(){
		return this.idTagPlantilla;
	}
	
	public void setIdTagPlantilla(Long idTagPlantilla){
		this.idTagPlantilla = idTagPlantilla;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idValorPlantillaCarta);        
        hash = 37 * hash + Objects.hashCode(this.nombreValorDinamico);
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.consulta);
        hash = 37 * hash + (this.llenadoPorConsulta ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idTagPlantilla);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorPlantillaCartaDTO que se pasa
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
        final ValorPlantillaCartaDTO other = (ValorPlantillaCartaDTO) obj;
                
        if (!Objects.equals(this.idValorPlantillaCarta, other.idValorPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreValorDinamico, other.nombreValorDinamico)) {
            return false;
        }
        
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
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
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        
        if (!Objects.equals(this.llenadoPorConsulta, other.llenadoPorConsulta)) {
            return false;
        }
        
        return Objects.equals(this.idTagPlantilla, other.idTagPlantilla);
                
    }
    
    @Override
	public ValorPlantillaCartaDTO transformarSinDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCartaDTO valorPlantillaCartaDTO = new ValorPlantillaCartaDTO();
		
		valorPlantillaCartaDTO.setIdValorPlantillaCarta(obj.getIdValorPlantillaCarta());
		valorPlantillaCartaDTO.setNombreValorDinamico(obj.getNombreValorDinamico());
		valorPlantillaCartaDTO.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		valorPlantillaCartaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		valorPlantillaCartaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		valorPlantillaCartaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		valorPlantillaCartaDTO.setDescripcion(obj.getDescripcion());
		valorPlantillaCartaDTO.setConsulta(obj.getConsulta());
		valorPlantillaCartaDTO.setLlenadoPorConsulta(obj.getLlenadoPorConsulta());
		valorPlantillaCartaDTO.setIdTagPlantilla(obj.getIdTagPlantilla());
		
		return valorPlantillaCartaDTO;
	}

	@Override
	public ValorPlantillaCartaDTO transformarConDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCartaDTO valorPlantillaCartaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return valorPlantillaCartaDTO;
	}

	@Override
	public ValorPlantillaCarta transformarEntidadSinDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCarta valorPlantillaCarta = new ValorPlantillaCarta();
		
		valorPlantillaCarta.setIdValorPlantillaCarta(obj.getIdValorPlantillaCarta());
		
		valorPlantillaCarta.setNombreValorDinamico(obj.getNombreValorDinamico());
		valorPlantillaCarta.setIdPlantillaCarta(obj.getIdPlantillaCarta());
		valorPlantillaCarta.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		valorPlantillaCarta.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		valorPlantillaCarta.setEstadoRegistro(obj.getEstadoRegistro());
		valorPlantillaCarta.setDescripcion(obj.getDescripcion());
		valorPlantillaCarta.setConsulta(obj.getConsulta());
		valorPlantillaCarta.setLlenadoPorConsulta(obj.getLlenadoPorConsulta());
		valorPlantillaCarta.setIdTagPlantilla(obj.getIdTagPlantilla());
		
		return valorPlantillaCarta;
	}


	@Override
	public ValorPlantillaCarta transformarEntidadConDependencias(ValorPlantillaCarta obj) {
		ValorPlantillaCarta valorPlantillaCarta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return valorPlantillaCarta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ValorPlantillaCarta> coleccion) {
		List<ValorPlantillaCartaDTO> valorPlantillaCartaDTOList = new ArrayList<>();
		for(ValorPlantillaCarta c : coleccion)
			valorPlantillaCartaDTOList.add(transformarConDependencias(c));
		return valorPlantillaCartaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ValorPlantillaCarta> coleccion) {
		List<ValorPlantillaCartaDTO> valorPlantillaCartaDTOList = new ArrayList<>();
		for(ValorPlantillaCarta c : coleccion)
			valorPlantillaCartaDTOList.add(transformarSinDependencias(c));
		return valorPlantillaCartaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ValorPlantillaCarta> coleccion) {
		List<ValorPlantillaCarta> valorPlantillaCartaList = new ArrayList<>();
		for(ValorPlantillaCarta c : coleccion)
			valorPlantillaCartaList.add(transformarEntidadConDependencias(c));
		return valorPlantillaCartaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ValorPlantillaCarta> coleccion) {
		List<ValorPlantillaCarta> valorPlantillaCartaList = new ArrayList<>();
		for(ValorPlantillaCarta c : coleccion)
			valorPlantillaCartaList.add(transformarEntidadSinDependencias(c));
		return valorPlantillaCartaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
