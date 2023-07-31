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

import com.ccb.simasc.transversal.entidades.Sorteo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad SorteoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class SorteoDTO extends IDTO<Sorteo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private Long idSorteo;

	private Date fechaRealizacion;		
	private String estado;		
	private Long materiaCaso;		
	private Long servicioCaso;		
	private String tipoCuantia;		
	private boolean liberoLista;		
	private Date horaLiberacion;		
	private String aleatoriosSeleccionados;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;

	private String tipoPreseleccion;		
	
    public SorteoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdSorteo(){
		return this.idSorteo;
	}
	
	public void setIdSorteo(Long idSorteo){
		this.idSorteo = idSorteo;
	}
	
	public Date getFechaRealizacion(){
		return this.fechaRealizacion;
	}
	
	public void setFechaRealizacion(Date fechaRealizacion){
		this.fechaRealizacion = fechaRealizacion;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getMateriaCaso(){
		return this.materiaCaso;
	}
	
	public void setMateriaCaso(Long materiaCaso){
		this.materiaCaso = materiaCaso;
	}
		
	public Long getServicioCaso(){
		return this.servicioCaso;
	}
	
	public void setServicioCaso(Long servicioCaso){
		this.servicioCaso = servicioCaso;
	}
		
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public boolean getLiberoLista(){
		return this.liberoLista;
	}
	
	public void setLiberoLista(boolean liberoLista){
		this.liberoLista = liberoLista;
	}
		
	public Date getHoraLiberacion(){
		return this.horaLiberacion;
	}
	
	public void setHoraLiberacion(Date horaLiberacion){
		this.horaLiberacion = horaLiberacion;
	}
		
	public String getAleatoriosSeleccionados(){
		return this.aleatoriosSeleccionados;
	}
	
	public void setAleatoriosSeleccionados(String aleatoriosSeleccionados){
		this.aleatoriosSeleccionados = aleatoriosSeleccionados;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
	
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSorteo);        
        hash = 37 * hash + Objects.hashCode(this.fechaRealizacion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.materiaCaso);
        hash = 37 * hash + Objects.hashCode(this.servicioCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + (this.liberoLista ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.horaLiberacion);
        hash = 37 * hash + Objects.hashCode(this.aleatoriosSeleccionados);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SorteoDTO que se pasa
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
        final SorteoDTO other = (SorteoDTO) obj;
                
        if (!Objects.equals(this.idSorteo, other.idSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRealizacion, other.fechaRealizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCaso, other.materiaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.servicioCaso, other.servicioCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.liberoLista, other.liberoLista)) {
            return false;
        }
        
        if (!Objects.equals(this.horaLiberacion, other.horaLiberacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aleatoriosSeleccionados, other.aleatoriosSeleccionados)) {
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
        
        if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    @Override
	public SorteoDTO transformarSinDependencias(Sorteo obj) {
		SorteoDTO sorteoDTO = new SorteoDTO();
		
		sorteoDTO.setIdSorteo(obj.getIdSorteo());
		sorteoDTO.setFechaRealizacion(obj.getFechaRealizacion());
		sorteoDTO.setEstado(obj.getEstado());
		sorteoDTO.setMateriaCaso(obj.getMateriaCaso());
		sorteoDTO.setServicioCaso(obj.getServicioCaso());
		sorteoDTO.setTipoCuantia(obj.getTipoCuantia());
		sorteoDTO.setLiberoLista(obj.getLiberoLista());
		sorteoDTO.setHoraLiberacion(obj.getHoraLiberacion());
		sorteoDTO.setAleatoriosSeleccionados(obj.getAleatoriosSeleccionados());
		sorteoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sorteoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sorteoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		sorteoDTO.setIdCaso(obj.getIdCaso());
		sorteoDTO.setTipoPreseleccion(obj.getTipoPreseleccion());
		
		return sorteoDTO;
	}

	@Override
	public SorteoDTO transformarConDependencias(Sorteo obj) {
		SorteoDTO sorteoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return sorteoDTO;
	}

	@Override
	public Sorteo transformarEntidadSinDependencias(Sorteo obj) {
		Sorteo sorteo = new Sorteo();
		
		sorteo.setIdSorteo(obj.getIdSorteo());
		
		sorteo.setFechaRealizacion(obj.getFechaRealizacion());
		sorteo.setEstado(obj.getEstado());
		sorteo.setMateriaCaso(obj.getMateriaCaso());
		sorteo.setServicioCaso(obj.getServicioCaso());
		sorteo.setTipoCuantia(obj.getTipoCuantia());
		sorteo.setLiberoLista(obj.getLiberoLista());
		sorteo.setHoraLiberacion(obj.getHoraLiberacion());
		sorteo.setAleatoriosSeleccionados(obj.getAleatoriosSeleccionados());
		sorteo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		sorteo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		sorteo.setEstadoRegistro(obj.getEstadoRegistro());
		sorteo.setIdCaso(obj.getIdCaso());
		sorteo.setTipoPreseleccion(obj.getTipoPreseleccion());
		return sorteo;
	}


	@Override
	public Sorteo transformarEntidadConDependencias(Sorteo obj) {
		Sorteo sorteo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return sorteo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Sorteo> coleccion) {
		List<SorteoDTO> sorteoDTOList = new ArrayList<>();
		for(Sorteo c : coleccion)
			sorteoDTOList.add(transformarConDependencias(c));
		return sorteoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Sorteo> coleccion) {
		List<SorteoDTO> sorteoDTOList = new ArrayList<>();
		for(Sorteo c : coleccion)
			sorteoDTOList.add(transformarSinDependencias(c));
		return sorteoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Sorteo> coleccion) {
		List<Sorteo> sorteoList = new ArrayList<>();
		for(Sorteo c : coleccion)
			sorteoList.add(transformarEntidadConDependencias(c));
		return sorteoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Sorteo> coleccion) {
		List<Sorteo> sorteoList = new ArrayList<>();
		for(Sorteo c : coleccion)
			sorteoList.add(transformarEntidadSinDependencias(c));
		return sorteoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
