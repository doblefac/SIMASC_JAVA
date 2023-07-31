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

import com.ccb.simasc.transversal.entidades.Elegible;
import com.ccb.simasc.transversal.entidades.ElegiblePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ElegibleDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ElegibleDTO extends IDTO<Elegible> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	private ElegiblePK elegiblePK;

	private Date fechaDeSeleccion;		
	private boolean elegidoPorLiberacionLista;		
	private Long aleatorioAsignado;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ElegibleDTO(){
		elegiblePK = new ElegiblePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ElegiblePK getElegiblePK(){
		return this.elegiblePK;
	}
	
	public void setElegiblePK(ElegiblePK elegiblePK){
		this.elegiblePK   = elegiblePK ;
	}  
	
	public Date getFechaDeSeleccion(){
		return this.fechaDeSeleccion;
	}
	
	public void setFechaDeSeleccion(Date fechaDeSeleccion){
		this.fechaDeSeleccion = fechaDeSeleccion;
	}
		
	public boolean getElegidoPorLiberacionLista(){
		return this.elegidoPorLiberacionLista;
	}
	
	public void setElegidoPorLiberacionLista(boolean elegidoPorLiberacionLista){
		this.elegidoPorLiberacionLista = elegidoPorLiberacionLista;
	}
		
	public Long getAleatorioAsignado(){
		return this.aleatorioAsignado;
	}
	
	public void setAleatorioAsignado(Long aleatorioAsignado){
		this.aleatorioAsignado = aleatorioAsignado;
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
        
        hash = 37 * hash + Objects.hashCode(this.elegiblePK);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeSeleccion);
        hash = 37 * hash + (this.elegidoPorLiberacionLista ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.aleatorioAsignado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ElegibleDTO que se pasa
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
        final ElegibleDTO other = (ElegibleDTO) obj;
                
        if (!Objects.equals(this.elegiblePK, other.elegiblePK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeSeleccion, other.fechaDeSeleccion)) {
            return false;
        }
        
        if (!Objects.equals(this.elegidoPorLiberacionLista, other.elegidoPorLiberacionLista)) {
            return false;
        }
        
        if (!Objects.equals(this.aleatorioAsignado, other.aleatorioAsignado)) {
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
	public ElegibleDTO transformarSinDependencias(Elegible obj) {
		ElegibleDTO elegibleDTO = new ElegibleDTO();
		
		elegibleDTO.setElegiblePK(obj.getElegiblePK());
		elegibleDTO.setFechaDeSeleccion(obj.getFechaDeSeleccion());
		elegibleDTO.setElegidoPorLiberacionLista(obj.getElegidoPorLiberacionLista());
		elegibleDTO.setAleatorioAsignado(obj.getAleatorioAsignado());
		elegibleDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		elegibleDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		elegibleDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return elegibleDTO;
	}

	@Override
	public ElegibleDTO transformarConDependencias(Elegible obj) {
		ElegibleDTO elegibleDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return elegibleDTO;
	}

	@Override
	public Elegible transformarEntidadSinDependencias(Elegible obj) {
		Elegible elegible = new Elegible();
		
		elegible.setElegiblePK(obj.getElegiblePK());
		
		elegible.setFechaDeSeleccion(obj.getFechaDeSeleccion());
		elegible.setElegidoPorLiberacionLista(obj.getElegidoPorLiberacionLista());
		elegible.setAleatorioAsignado(obj.getAleatorioAsignado());
		elegible.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		elegible.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		elegible.setEstadoRegistro(obj.getEstadoRegistro());
		
		return elegible;
	}


	@Override
	public Elegible transformarEntidadConDependencias(Elegible obj) {
		Elegible elegible = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return elegible;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Elegible> coleccion) {
		List<ElegibleDTO> elegibleDTOList = new ArrayList<>();
		for(Elegible c : coleccion)
			elegibleDTOList.add(transformarConDependencias(c));
		return elegibleDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Elegible> coleccion) {
		List<ElegibleDTO> elegibleDTOList = new ArrayList<>();
		for(Elegible c : coleccion)
			elegibleDTOList.add(transformarSinDependencias(c));
		return elegibleDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Elegible> coleccion) {
		List<Elegible> elegibleList = new ArrayList<>();
		for(Elegible c : coleccion)
			elegibleList.add(transformarEntidadConDependencias(c));
		return elegibleList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Elegible> coleccion) {
		List<Elegible> elegibleList = new ArrayList<>();
		for(Elegible c : coleccion)
			elegibleList.add(transformarEntidadSinDependencias(c));
		return elegibleList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
