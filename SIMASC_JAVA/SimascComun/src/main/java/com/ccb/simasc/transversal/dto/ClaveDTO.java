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

import com.ccb.simasc.transversal.entidades.Clave;
import com.ccb.simasc.transversal.entidades.ClavePK;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ClaveDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ClaveDTO extends IDTO<Clave> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ClavePK clavePK;

	private boolean claveBloqueada;		
	private Date fechaVencimiento;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public ClaveDTO(){
		clavePK = new ClavePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ClavePK getClavePK(){
		return this.clavePK;
	}
	
	public void setClavePK(ClavePK clavePK){
		this.clavePK   = clavePK ;
	}  
	
	public boolean getClaveBloqueada(){
		return this.claveBloqueada;
	}
	
	public void setClaveBloqueada(boolean claveBloqueada){
		this.claveBloqueada = claveBloqueada;
	}
		
	public Date getFechaVencimiento(){
		return this.fechaVencimiento;
	}
	
	public void setFechaVencimiento(Date fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento;
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
        
        hash = 37 * hash + Objects.hashCode(this.clavePK);        
        hash = 37 * hash + (this.claveBloqueada ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClaveDTO que se pasa
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
        final ClaveDTO other = (ClaveDTO) obj;
                
        if (!Objects.equals(this.clavePK, other.clavePK)) {
            return false;
        }
        
        if (!Objects.equals(this.claveBloqueada, other.claveBloqueada)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaVencimiento, other.fechaVencimiento)) {
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
	public ClaveDTO transformarSinDependencias(Clave obj) {
		ClaveDTO claveDTO = new ClaveDTO();
		
		claveDTO.setClavePK(obj.getClavePK());
		claveDTO.setClaveBloqueada(obj.getClaveBloqueada());
		claveDTO.setFechaVencimiento(obj.getFechaVencimiento());
		claveDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		claveDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		claveDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return claveDTO;
	}

	@Override
	public ClaveDTO transformarConDependencias(Clave obj) {
		ClaveDTO claveDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return claveDTO;
	}

	@Override
	public Clave transformarEntidadSinDependencias(Clave obj) {
		Clave clave = new Clave();
		
		clave.setClavePK(obj.getClavePK());
		
		clave.setClaveBloqueada(obj.getClaveBloqueada());
		clave.setFechaVencimiento(obj.getFechaVencimiento());
		clave.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		clave.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		clave.setEstadoRegistro(obj.getEstadoRegistro());
		
		return clave;
	}


	@Override
	public Clave transformarEntidadConDependencias(Clave obj) {
		Clave clave = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return clave;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Clave> coleccion) {
		List<ClaveDTO> claveDTOList = new ArrayList<>();
		for(Clave c : coleccion)
			claveDTOList.add(transformarConDependencias(c));
		return claveDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Clave> coleccion) {
		List<ClaveDTO> claveDTOList = new ArrayList<>();
		for(Clave c : coleccion)
			claveDTOList.add(transformarSinDependencias(c));
		return claveDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Clave> coleccion) {
		List<Clave> claveList = new ArrayList<>();
		for(Clave c : coleccion)
			claveList.add(transformarEntidadConDependencias(c));
		return claveList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Clave> coleccion) {
		List<Clave> claveList = new ArrayList<>();
		for(Clave c : coleccion)
			claveList.add(transformarEntidadSinDependencias(c));
		return claveList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
