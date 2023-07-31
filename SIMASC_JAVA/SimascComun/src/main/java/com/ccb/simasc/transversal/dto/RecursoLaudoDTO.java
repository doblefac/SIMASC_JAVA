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

import com.ccb.simasc.transversal.entidades.RecursoLaudo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad RecursoLaudoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class RecursoLaudoDTO extends IDTO<RecursoLaudo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idRecursoLaudo;

	private Long idLaudo;		
	private String tipo;		
	private String parteQueInterpone;		
	private Date fecha;		
	private boolean sePresentoEnTermino;		
	private String causalDeRecursoDeAnulacion;		
	private Date fechaRemision;		
	private String entidadJudicial;		
	private String despachoAsignado;		
	private String nombreMagistrado;		
	private String resultado;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	
    public RecursoLaudoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdRecursoLaudo(){
		return this.idRecursoLaudo;
	}
	
	public void setIdRecursoLaudo(Long idRecursoLaudo){
		this.idRecursoLaudo = idRecursoLaudo;
	}
	
	public Long getIdLaudo(){
		return this.idLaudo;
	}
	
	public void setIdLaudo(Long idLaudo){
		this.idLaudo = idLaudo;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getParteQueInterpone(){
		return this.parteQueInterpone;
	}
	
	public void setParteQueInterpone(String parteQueInterpone){
		this.parteQueInterpone = parteQueInterpone;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getSePresentoEnTermino(){
		return this.sePresentoEnTermino;
	}
	
	public void setSePresentoEnTermino(boolean sePresentoEnTermino){
		this.sePresentoEnTermino = sePresentoEnTermino;
	}
		
	public String getCausalDeRecursoDeAnulacion(){
		return this.causalDeRecursoDeAnulacion;
	}
	
	public void setCausalDeRecursoDeAnulacion(String causalDeRecursoDeAnulacion){
		this.causalDeRecursoDeAnulacion = causalDeRecursoDeAnulacion;
	}
		
	public Date getFechaRemision(){
		return this.fechaRemision;
	}
	
	public void setFechaRemision(Date fechaRemision){
		this.fechaRemision = fechaRemision;
	}
		
	public String getEntidadJudicial(){
		return this.entidadJudicial;
	}
	
	public void setEntidadJudicial(String entidadJudicial){
		this.entidadJudicial = entidadJudicial;
	}
		
	public String getDespachoAsignado(){
		return this.despachoAsignado;
	}
	
	public void setDespachoAsignado(String despachoAsignado){
		this.despachoAsignado = despachoAsignado;
	}
		
	public String getNombreMagistrado(){
		return this.nombreMagistrado;
	}
	
	public void setNombreMagistrado(String nombreMagistrado){
		this.nombreMagistrado = nombreMagistrado;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idRecursoLaudo);        
        hash = 37 * hash + Objects.hashCode(this.idLaudo);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.parteQueInterpone);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.sePresentoEnTermino ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.causalDeRecursoDeAnulacion);
        hash = 37 * hash + Objects.hashCode(this.fechaRemision);
        hash = 37 * hash + Objects.hashCode(this.entidadJudicial);
        hash = 37 * hash + Objects.hashCode(this.despachoAsignado);
        hash = 37 * hash + Objects.hashCode(this.nombreMagistrado);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RecursoLaudoDTO que se pasa
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
        final RecursoLaudoDTO other = (RecursoLaudoDTO) obj;
                
        if (!Objects.equals(this.idRecursoLaudo, other.idRecursoLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.idLaudo, other.idLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQueInterpone, other.parteQueInterpone)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.sePresentoEnTermino, other.sePresentoEnTermino)) {
            return false;
        }
        
        if (!Objects.equals(this.causalDeRecursoDeAnulacion, other.causalDeRecursoDeAnulacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRemision, other.fechaRemision)) {
            return false;
        }
        
        if (!Objects.equals(this.entidadJudicial, other.entidadJudicial)) {
            return false;
        }
        
        if (!Objects.equals(this.despachoAsignado, other.despachoAsignado)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreMagistrado, other.nombreMagistrado)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
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
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
    
    @Override
	public RecursoLaudoDTO transformarSinDependencias(RecursoLaudo obj) {
		RecursoLaudoDTO recursoLaudoDTO = new RecursoLaudoDTO();
		
		recursoLaudoDTO.setIdRecursoLaudo(obj.getIdRecursoLaudo());
		recursoLaudoDTO.setIdLaudo(obj.getIdLaudo());
		recursoLaudoDTO.setTipo(obj.getTipo());
		recursoLaudoDTO.setParteQueInterpone(obj.getParteQueInterpone());
		recursoLaudoDTO.setFecha(obj.getFecha());
		recursoLaudoDTO.setSePresentoEnTermino(obj.getSePresentoEnTermino());
		recursoLaudoDTO.setCausalDeRecursoDeAnulacion(obj.getCausalDeRecursoDeAnulacion());
		recursoLaudoDTO.setFechaRemision(obj.getFechaRemision());
		recursoLaudoDTO.setEntidadJudicial(obj.getEntidadJudicial());
		recursoLaudoDTO.setDespachoAsignado(obj.getDespachoAsignado());
		recursoLaudoDTO.setNombreMagistrado(obj.getNombreMagistrado());
		recursoLaudoDTO.setResultado(obj.getResultado());
		recursoLaudoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		recursoLaudoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		recursoLaudoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		recursoLaudoDTO.setIdDocumento(obj.getIdDocumento());
		
		return recursoLaudoDTO;
	}

	@Override
	public RecursoLaudoDTO transformarConDependencias(RecursoLaudo obj) {
		RecursoLaudoDTO recursoLaudoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return recursoLaudoDTO;
	}

	@Override
	public RecursoLaudo transformarEntidadSinDependencias(RecursoLaudo obj) {
		RecursoLaudo recursoLaudo = new RecursoLaudo();
		
		recursoLaudo.setIdRecursoLaudo(obj.getIdRecursoLaudo());
		
		recursoLaudo.setIdLaudo(obj.getIdLaudo());
		recursoLaudo.setTipo(obj.getTipo());
		recursoLaudo.setParteQueInterpone(obj.getParteQueInterpone());
		recursoLaudo.setFecha(obj.getFecha());
		recursoLaudo.setSePresentoEnTermino(obj.getSePresentoEnTermino());
		recursoLaudo.setCausalDeRecursoDeAnulacion(obj.getCausalDeRecursoDeAnulacion());
		recursoLaudo.setFechaRemision(obj.getFechaRemision());
		recursoLaudo.setEntidadJudicial(obj.getEntidadJudicial());
		recursoLaudo.setDespachoAsignado(obj.getDespachoAsignado());
		recursoLaudo.setNombreMagistrado(obj.getNombreMagistrado());
		recursoLaudo.setResultado(obj.getResultado());
		recursoLaudo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		recursoLaudo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		recursoLaudo.setEstadoRegistro(obj.getEstadoRegistro());
		recursoLaudo.setIdDocumento(obj.getIdDocumento());
		
		return recursoLaudo;
	}


	@Override
	public RecursoLaudo transformarEntidadConDependencias(RecursoLaudo obj) {
		RecursoLaudo recursoLaudo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return recursoLaudo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<RecursoLaudo> coleccion) {
		List<RecursoLaudoDTO> recursoLaudoDTOList = new ArrayList<>();
		for(RecursoLaudo c : coleccion)
			recursoLaudoDTOList.add(transformarConDependencias(c));
		return recursoLaudoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<RecursoLaudo> coleccion) {
		List<RecursoLaudoDTO> recursoLaudoDTOList = new ArrayList<>();
		for(RecursoLaudo c : coleccion)
			recursoLaudoDTOList.add(transformarSinDependencias(c));
		return recursoLaudoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<RecursoLaudo> coleccion) {
		List<RecursoLaudo> recursoLaudoList = new ArrayList<>();
		for(RecursoLaudo c : coleccion)
			recursoLaudoList.add(transformarEntidadConDependencias(c));
		return recursoLaudoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<RecursoLaudo> coleccion) {
		List<RecursoLaudo> recursoLaudoList = new ArrayList<>();
		for(RecursoLaudo c : coleccion)
			recursoLaudoList.add(transformarEntidadSinDependencias(c));
		return recursoLaudoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
