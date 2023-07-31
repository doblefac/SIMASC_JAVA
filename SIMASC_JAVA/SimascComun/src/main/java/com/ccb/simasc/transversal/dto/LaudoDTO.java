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

import com.ccb.simasc.transversal.entidades.Laudo;
import com.ccb.simasc.transversal.entidades.RecursoLaudo;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad LaudoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class LaudoDTO extends IDTO<Laudo> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	private List<RecursoLaudo> recursoLaudoList;
	// protected region atributos end
	private Long idLaudo;

	private Date fecha;		
	private String resultado;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	
    public LaudoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdLaudo(){
		return this.idLaudo;
	}
	
	public void setIdLaudo(Long idLaudo){
		this.idLaudo = idLaudo;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
        
        hash = 37 * hash + Objects.hashCode(this.idLaudo);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LaudoDTO que se pasa
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
        final LaudoDTO other = (LaudoDTO) obj;
                
        if (!Objects.equals(this.idLaudo, other.idLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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
	public LaudoDTO transformarSinDependencias(Laudo obj) {
		LaudoDTO laudoDTO = new LaudoDTO();
		
		laudoDTO.setIdLaudo(obj.getIdLaudo());
		laudoDTO.setFecha(obj.getFecha());
		laudoDTO.setResultado(obj.getResultado());
		laudoDTO.setIdCaso(obj.getIdCaso());
		laudoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		laudoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		laudoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		laudoDTO.setIdDocumento(obj.getIdDocumento());
		
		return laudoDTO;
	}

	@Override
	public LaudoDTO transformarConDependencias(Laudo obj) {
		LaudoDTO laudoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones
		ArrayList<RecursoLaudo> recursos = new ArrayList<RecursoLaudo>();
		for (RecursoLaudo recurso : obj.getRecursoLaudoList()) {
			recursos.add(new RecursoLaudoDTO().transformarEntidadSinDependencias(recurso));
		}
		laudoDTO.setRecursoLaudoList(recursos);
		// protected region modificaciones transformarConDependencias end
		
		return laudoDTO;
	}

	@Override
	public Laudo transformarEntidadSinDependencias(Laudo obj) {
		Laudo laudo = new Laudo();
		
		laudo.setIdLaudo(obj.getIdLaudo());
		
		laudo.setFecha(obj.getFecha());
		laudo.setResultado(obj.getResultado());
		laudo.setIdCaso(obj.getIdCaso());
		laudo.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		laudo.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		laudo.setEstadoRegistro(obj.getEstadoRegistro());
		laudo.setIdDocumento(obj.getIdDocumento());
		
		return laudo;
	}


	@Override
	public Laudo transformarEntidadConDependencias(Laudo obj) {
		Laudo laudo = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return laudo;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Laudo> coleccion) {
		List<LaudoDTO> laudoDTOList = new ArrayList<>();
		for(Laudo c : coleccion)
			laudoDTOList.add(transformarConDependencias(c));
		return laudoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Laudo> coleccion) {
		List<LaudoDTO> laudoDTOList = new ArrayList<>();
		for(Laudo c : coleccion)
			laudoDTOList.add(transformarSinDependencias(c));
		return laudoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Laudo> coleccion) {
		List<Laudo> laudoList = new ArrayList<>();
		for(Laudo c : coleccion)
			laudoList.add(transformarEntidadConDependencias(c));
		return laudoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Laudo> coleccion) {
		List<Laudo> laudoList = new ArrayList<>();
		for(Laudo c : coleccion)
			laudoList.add(transformarEntidadSinDependencias(c));
		return laudoList;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<RecursoLaudo> getRecursoLaudoList() {
		return recursoLaudoList;
	}



	public void setRecursoLaudoList(List<RecursoLaudo> recursoLaudoList) {
		this.recursoLaudoList = recursoLaudoList;
	}
		
	
	// protected region metodos adicionales end

}
