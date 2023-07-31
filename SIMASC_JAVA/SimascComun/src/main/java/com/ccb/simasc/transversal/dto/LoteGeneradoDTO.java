package com.ccb.simasc.transversal.dto;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ccb.simasc.transversal.entidades.LoteGenerado;

// protected region imports entidad end



public class LoteGeneradoDTO extends IDTO<LoteGenerado> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)

	private Long idLote;
    
	private Long idPersona;		
    
	private String nombreDocumento;		
    
	private String estadoGeneracion;		
	
	private Date fechaGeneracion;		
	
	
    public LoteGeneradoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdLote(){
		return this.idLote;
	}
	
	public void setIdLote(Long idLote){
		this.idLote = idLote;
	}
	
	
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}


	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}


	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}


	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}


	/**
	 * @return the estadoGeneracion
	 */
	public String getEstadoGeneracion() {
		return estadoGeneracion;
	}


	/**
	 * @param estadoGeneracion the estadoGeneracion to set
	 */
	public void setEstadoGeneracion(String estadoGeneracion) {
		this.estadoGeneracion = estadoGeneracion;
	}


	/**
	 * @return the fechaGeneracion
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}


	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}


    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idLote);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.nombreDocumento);
        hash = 37 * hash + Objects.hashCode(this.fechaGeneracion);
        hash = 37 * hash + Objects.hashCode(this.estadoGeneracion);
       
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Agendamiento que se pasa
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
        final LoteGeneradoDTO other = (LoteGeneradoDTO) obj;
        
        if (!Objects.equals(this.idLote, other.idLote)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreDocumento, other.nombreDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoGeneracion, other.estadoGeneracion)) {
            return false;
        }
        
        return Objects.equals(this.fechaGeneracion, other.fechaGeneracion);
                
    }


	@Override
	public LoteGeneradoDTO transformarSinDependencias(LoteGenerado obj) {
		// TODO Auto-generated method stub
		LoteGeneradoDTO loteGeneradoDTO = new LoteGeneradoDTO();
		loteGeneradoDTO.setIdPersona(obj.getIdPersona());
		loteGeneradoDTO.setNombreDocumento(obj.getNombreDocumento());
		loteGeneradoDTO.setEstadoGeneracion(obj.getEstadoGeneracion());
		loteGeneradoDTO.setFechaGeneracion(obj.getFechaGeneracion());
		return loteGeneradoDTO;
	}


	@Override
	public LoteGeneradoDTO transformarConDependencias(LoteGenerado obj) {
		// TODO Auto-generated method stub
		LoteGeneradoDTO loteGeneradoDTO = this.transformarSinDependencias(obj);
		return loteGeneradoDTO;
	}


	@Override
	public LoteGenerado transformarEntidadSinDependencias(LoteGenerado obj) {
		// TODO Auto-generated method stub
		LoteGenerado loteGenerado = new LoteGenerado();
		loteGenerado.setIdPersona(obj.getIdPersona());
		loteGenerado.setNombreDocumento(obj.getNombreDocumento());
		loteGenerado.setEstadoGeneracion(obj.getEstadoGeneracion());
		loteGenerado.setFechaGeneracion(obj.getFechaGeneracion());
		return loteGenerado;
	}
	


	@Override
	public LoteGenerado transformarEntidadConDependencias(LoteGenerado obj) {
		// TODO Auto-generated method stub
		return this.transformarEntidadSinDependencias(obj);
	}


	@Override
	public Collection<LoteGeneradoDTO> transformarColeccionConDependencias(Collection<LoteGenerado> coleccion) {
		// TODO Auto-generated method stub
		List<LoteGeneradoDTO> lista = new ArrayList<>();
		for(LoteGenerado lg : coleccion){
			lista.add(this.transformarConDependencias(lg));
		}
		return lista;
	}


	@Override
	public Collection<LoteGeneradoDTO> transformarColeccionSinDependencias(Collection<LoteGenerado> coleccion) {
		List<LoteGeneradoDTO> lista = new ArrayList<>();
		for(LoteGenerado lg : coleccion){
			lista.add(this.transformarSinDependencias(lg));
		}
		return lista;
	}


	@Override
	public Collection<LoteGenerado> transformarColeccionEntidadesConDependencias(Collection<LoteGenerado> coleccion) {
		List<LoteGenerado> lista = new ArrayList<>();
		for(LoteGenerado lg : coleccion){
			lista.add(this.transformarEntidadConDependencias(lg));
		}
		return lista;
	}


	@Override
	public Collection<LoteGenerado> transformarColeccionEntidadesSinDependencias(Collection<LoteGenerado> coleccion) {
		List<LoteGenerado> lista = new ArrayList<>();
		for(LoteGenerado lg : coleccion){
			lista.add(this.transformarEntidadSinDependencias(lg));
		}
		return lista;
	}


	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

