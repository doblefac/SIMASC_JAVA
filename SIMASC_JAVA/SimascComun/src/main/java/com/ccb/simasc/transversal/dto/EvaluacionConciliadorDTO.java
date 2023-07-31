package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.DetalleEvaluacionConciliador;
import com.ccb.simasc.transversal.entidades.EvaluacionConciliador;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.math.BigDecimal;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad EvaluacionConciliadorDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class EvaluacionConciliadorDTO extends IDTO<EvaluacionConciliador> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idEvaluacionConciliador;

	private BigDecimal totalEvaluacion;		
	private Date periodoDesde;	
	private Date periodoHasta;	
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idPersona;		
	
    public EvaluacionConciliadorDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdEvaluacionConciliador(){
		return this.idEvaluacionConciliador;
	}
	
	public void setIdEvaluacionConciliador(Long idEvaluacionConciliador){
		this.idEvaluacionConciliador = idEvaluacionConciliador;
	}
	
	public BigDecimal getTotalEvaluacion(){
		return this.totalEvaluacion;
	}
	
	public void setTotalEvaluacion(BigDecimal totalEvaluacion){
		this.totalEvaluacion = totalEvaluacion;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
	public Date getPeriodoDesde() {
		return periodoDesde;
	}
	
	public void setPeriodoDesde(Date periodoDesde) {
		this.periodoDesde = periodoDesde;
	}
	
	public Date getPeriodoHasta() {
		return periodoHasta;
	}
	
	public void setPeriodoHasta(Date periodoHasta) {
		this.periodoHasta = periodoHasta;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idEvaluacionConciliador);        
        hash = 37 * hash + Objects.hashCode(this.totalEvaluacion);
        hash = 37 * hash + Objects.hashCode(this.periodoDesde);
        hash = 37 * hash + Objects.hashCode(this.periodoHasta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EvaluacionConciliadorDTO que se pasa
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
        final EvaluacionConciliadorDTO other = (EvaluacionConciliadorDTO) obj;
                
        if (!Objects.equals(this.idEvaluacionConciliador, other.idEvaluacionConciliador)) {
            return false;
        }
        
        if (!Objects.equals(this.totalEvaluacion, other.totalEvaluacion)) {
            return false;
        }
        
        if (!Objects.equals(this.periodoDesde, other.periodoDesde)) {
            return false;
        }
        
        if (!Objects.equals(this.periodoHasta, other.periodoHasta)) {
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
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
    
    @Override
	public EvaluacionConciliadorDTO transformarSinDependencias(EvaluacionConciliador obj) {
		EvaluacionConciliadorDTO evaluacionConciliadorDTO = new EvaluacionConciliadorDTO();
		
		evaluacionConciliadorDTO.setIdEvaluacionConciliador(obj.getIdEvaluacionConciliador());
		evaluacionConciliadorDTO.setTotalEvaluacion(obj.getTotalEvaluacion());
		evaluacionConciliadorDTO.setPeriodoDesde(obj.getPeriodoDesde());
		evaluacionConciliadorDTO.setPeriodoHasta(obj.getPeriodoHasta());
		evaluacionConciliadorDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		evaluacionConciliadorDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		evaluacionConciliadorDTO.setEstadoRegistro(obj.getEstadoRegistro());
		evaluacionConciliadorDTO.setIdPersona(obj.getIdPersona());
		
		return evaluacionConciliadorDTO;
	}

	@Override
	public EvaluacionConciliadorDTO transformarConDependencias(EvaluacionConciliador obj) {
		EvaluacionConciliadorDTO evaluacionConciliadorDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return evaluacionConciliadorDTO;
	}

	@Override
	public EvaluacionConciliador transformarEntidadSinDependencias(EvaluacionConciliador obj) {
		EvaluacionConciliador evaluacionConciliador = new EvaluacionConciliador();
		
		evaluacionConciliador.setIdEvaluacionConciliador(obj.getIdEvaluacionConciliador());
		
		evaluacionConciliador.setTotalEvaluacion(obj.getTotalEvaluacion());
		evaluacionConciliador.setPeriodoDesde(obj.getPeriodoDesde());
		evaluacionConciliador.setPeriodoHasta(obj.getPeriodoHasta());
		evaluacionConciliador.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		evaluacionConciliador.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		evaluacionConciliador.setEstadoRegistro(obj.getEstadoRegistro());
		evaluacionConciliador.setIdPersona(obj.getIdPersona());
		
		return evaluacionConciliador;
	}


	@Override
	public EvaluacionConciliador transformarEntidadConDependencias(EvaluacionConciliador obj) {
		EvaluacionConciliador evaluacionConciliador = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if( obj.getDetalleEvaluacionConciliadorList() != null && !obj.getDetalleEvaluacionConciliadorList().isEmpty() )
			evaluacionConciliador.setDetalleEvaluacionConciliadorList( (List<DetalleEvaluacionConciliador>) new DetalleEvaluacionConciliadorDTO()
				.transformarColeccionEntidadesActivasSinDependencias( obj.getDetalleEvaluacionConciliadorList() ));
		// protected region modificaciones transformarEntidadConDependencias end
		
		return evaluacionConciliador;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<EvaluacionConciliador> coleccion) {
		List<EvaluacionConciliadorDTO> evaluacionConciliadorDTOList = new ArrayList<>();
		for(EvaluacionConciliador c : coleccion)
			evaluacionConciliadorDTOList.add(transformarConDependencias(c));
		return evaluacionConciliadorDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<EvaluacionConciliador> coleccion) {
		List<EvaluacionConciliadorDTO> evaluacionConciliadorDTOList = new ArrayList<>();
		for(EvaluacionConciliador c : coleccion)
			evaluacionConciliadorDTOList.add(transformarSinDependencias(c));
		return evaluacionConciliadorDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<EvaluacionConciliador> coleccion) {
		List<EvaluacionConciliador> evaluacionConciliadorList = new ArrayList<>();
		for(EvaluacionConciliador c : coleccion)
			evaluacionConciliadorList.add(transformarEntidadConDependencias(c));
		return evaluacionConciliadorList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<EvaluacionConciliador> coleccion) {
		List<EvaluacionConciliador> evaluacionConciliadorList = new ArrayList<>();
		for(EvaluacionConciliador c : coleccion)
			evaluacionConciliadorList.add(transformarEntidadSinDependencias(c));
		return evaluacionConciliadorList;
	}




	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
