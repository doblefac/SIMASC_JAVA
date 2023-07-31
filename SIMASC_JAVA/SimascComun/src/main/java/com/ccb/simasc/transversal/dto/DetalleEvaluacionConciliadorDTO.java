package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliadorPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

import java.math.BigDecimal;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad DetalleEvaluacionConciliadorDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class DetalleEvaluacionConciliadorDTO extends IDTO<DetalleEvaluacionConciliador> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private DetalleEvaluacionConciliadorPK detalleEvaluacionConciliadorPK;

	private BigDecimal valor;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public DetalleEvaluacionConciliadorDTO(){
		detalleEvaluacionConciliadorPK = new DetalleEvaluacionConciliadorPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DetalleEvaluacionConciliadorPK getDetalleEvaluacionConciliadorPK(){
		return this.detalleEvaluacionConciliadorPK;
	}
	
	public void setDetalleEvaluacionConciliadorPK(DetalleEvaluacionConciliadorPK detalleEvaluacionConciliadorPK){
		this.detalleEvaluacionConciliadorPK   = detalleEvaluacionConciliadorPK ;
	}  
	
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
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
        
        hash = 37 * hash + Objects.hashCode(this.detalleEvaluacionConciliadorPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetalleEvaluacionConciliadorDTO que se pasa
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
        final DetalleEvaluacionConciliadorDTO other = (DetalleEvaluacionConciliadorDTO) obj;
                
        if (!Objects.equals(this.detalleEvaluacionConciliadorPK, other.detalleEvaluacionConciliadorPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
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
	public DetalleEvaluacionConciliadorDTO transformarSinDependencias(DetalleEvaluacionConciliador obj) {
		DetalleEvaluacionConciliadorDTO detalleEvaluacionConciliadorDTO = new DetalleEvaluacionConciliadorDTO();
		
		detalleEvaluacionConciliadorDTO.setDetalleEvaluacionConciliadorPK(obj.getDetalleEvaluacionConciliadorPK());
		detalleEvaluacionConciliadorDTO.setValor(obj.getValor());
		detalleEvaluacionConciliadorDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detalleEvaluacionConciliadorDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detalleEvaluacionConciliadorDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detalleEvaluacionConciliadorDTO;
	}

	@Override
	public DetalleEvaluacionConciliadorDTO transformarConDependencias(DetalleEvaluacionConciliador obj) {
		DetalleEvaluacionConciliadorDTO detalleEvaluacionConciliadorDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return detalleEvaluacionConciliadorDTO;
	}

	@Override
	public DetalleEvaluacionConciliador transformarEntidadSinDependencias(DetalleEvaluacionConciliador obj) {
		DetalleEvaluacionConciliador detalleEvaluacionConciliador = new DetalleEvaluacionConciliador();
		
		detalleEvaluacionConciliador.setDetalleEvaluacionConciliadorPK(obj.getDetalleEvaluacionConciliadorPK());
		
		detalleEvaluacionConciliador.setValor(obj.getValor());
		detalleEvaluacionConciliador.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		detalleEvaluacionConciliador.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		detalleEvaluacionConciliador.setEstadoRegistro(obj.getEstadoRegistro());
		
		return detalleEvaluacionConciliador;
	}


	@Override
	public DetalleEvaluacionConciliador transformarEntidadConDependencias(DetalleEvaluacionConciliador obj) {
		DetalleEvaluacionConciliador detalleEvaluacionConciliador = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return detalleEvaluacionConciliador;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<DetalleEvaluacionConciliador> coleccion) {
		List<DetalleEvaluacionConciliadorDTO> detalleEvaluacionConciliadorDTOList = new ArrayList<>();
		for(DetalleEvaluacionConciliador c : coleccion)
			detalleEvaluacionConciliadorDTOList.add(transformarConDependencias(c));
		return detalleEvaluacionConciliadorDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DetalleEvaluacionConciliador> coleccion) {
		List<DetalleEvaluacionConciliadorDTO> detalleEvaluacionConciliadorDTOList = new ArrayList<>();
		for(DetalleEvaluacionConciliador c : coleccion)
			detalleEvaluacionConciliadorDTOList.add(transformarSinDependencias(c));
		return detalleEvaluacionConciliadorDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DetalleEvaluacionConciliador> coleccion) {
		List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList = new ArrayList<>();
		for(DetalleEvaluacionConciliador c : coleccion)
			detalleEvaluacionConciliadorList.add(transformarEntidadConDependencias(c));
		return detalleEvaluacionConciliadorList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DetalleEvaluacionConciliador> coleccion) {
		List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList = new ArrayList<>();
		for(DetalleEvaluacionConciliador c : coleccion)
			detalleEvaluacionConciliadorList.add(transformarEntidadSinDependencias(c));
		return detalleEvaluacionConciliadorList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	public Collection transformarColeccionEntidadesActivasSinDependencias(Collection<DetalleEvaluacionConciliador> coleccion) {
		List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList = new ArrayList<>();
		for(DetalleEvaluacionConciliador c : coleccion){
			if( UtilDominios.ESTADO_REGISTRO_ACTIVO.equals( c.getEstadoRegistro() ) )
				detalleEvaluacionConciliadorList.add(transformarEntidadSinDependencias(c));
		}
		return detalleEvaluacionConciliadorList;
	}
	
	// protected region metodos adicionales end

}
