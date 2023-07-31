package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.ContratoConvenio;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;
import java.math.BigDecimal;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ContratoConvenioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ContratoConvenioDTO extends IDTO<ContratoConvenio> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private String codigoContrato;

	private Long idConvenio;		
	private Date fechaInicio;		
	private Date fechaFin;		
	private Integer maximoAudiencias;		
	private Integer diasCancelacion;		
	private BigDecimal incrementoAudienciaAdicional;		
	private Long idDocumento;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String tipoCombinacion;
	
    public ContratoConvenioDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getCodigoContrato(){
		return this.codigoContrato;
	}
	
	public void setCodigoContrato(String codigoContrato){
		this.codigoContrato = codigoContrato;
	}
	
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public Integer getMaximoAudiencias(){
		return this.maximoAudiencias;
	}
	
	public void setMaximoAudiencias(Integer maximoAudiencias){
		this.maximoAudiencias = maximoAudiencias;
	}
		
	public Integer getDiasCancelacion(){
		return this.diasCancelacion;
	}
	
	public void setDiasCancelacion(Integer diasCancelacion){
		this.diasCancelacion = diasCancelacion;
	}
		
	public BigDecimal getIncrementoAudienciaAdicional(){
		return this.incrementoAudienciaAdicional;
	}
	
	public void setIncrementoAudienciaAdicional(BigDecimal incrementoAudienciaAdicional){
		this.incrementoAudienciaAdicional = incrementoAudienciaAdicional;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
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
	
	public String getTipoCombinacion() {
		return tipoCombinacion;
	}

	public void setTipoCombinacion(String tipoCombinacion) {
		this.tipoCombinacion = tipoCombinacion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoContrato);        
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.maximoAudiencias);
        hash = 37 * hash + Objects.hashCode(this.diasCancelacion);
        hash = 37 * hash + Objects.hashCode(this.incrementoAudienciaAdicional);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipoCombinacion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ContratoConvenioDTO que se pasa
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
        final ContratoConvenioDTO other = (ContratoConvenioDTO) obj;
                
        if (!Objects.equals(this.codigoContrato, other.codigoContrato)) {
            return false;
        }
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoAudiencias, other.maximoAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.diasCancelacion, other.diasCancelacion)) {
            return false;
        }
        
        if (!Objects.equals(this.incrementoAudienciaAdicional, other.incrementoAudienciaAdicional)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCombinacion, other.tipoCombinacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public ContratoConvenioDTO transformarSinDependencias(ContratoConvenio obj) {
		ContratoConvenioDTO contratoConvenioDTO = new ContratoConvenioDTO();
		
		contratoConvenioDTO.setCodigoContrato(obj.getCodigoContrato());
		contratoConvenioDTO.setIdConvenio(obj.getIdConvenio());
		contratoConvenioDTO.setFechaInicio(obj.getFechaInicio());
		contratoConvenioDTO.setFechaFin(obj.getFechaFin());
		contratoConvenioDTO.setMaximoAudiencias(obj.getMaximoAudiencias());
		contratoConvenioDTO.setDiasCancelacion(obj.getDiasCancelacion());
		contratoConvenioDTO.setIncrementoAudienciaAdicional(obj.getIncrementoAudienciaAdicional());
		contratoConvenioDTO.setIdDocumento(obj.getIdDocumento());
		contratoConvenioDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		contratoConvenioDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		contratoConvenioDTO.setEstadoRegistro(obj.getEstadoRegistro());
		contratoConvenioDTO.setTipoCombinacion(obj.getTipoCombinacion());
		
		return contratoConvenioDTO;
	}

	@Override
	public ContratoConvenioDTO transformarConDependencias(ContratoConvenio obj) {
		ContratoConvenioDTO contratoConvenioDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		
		// protected region modificaciones transformarConDependencias end
		
		return contratoConvenioDTO;
	}

	@Override
	public ContratoConvenio transformarEntidadSinDependencias(ContratoConvenio obj) {
		ContratoConvenio contratoConvenio = new ContratoConvenio();
		
		contratoConvenio.setCodigoContrato(obj.getCodigoContrato());
		
		contratoConvenio.setIdConvenio(obj.getIdConvenio());
		contratoConvenio.setFechaInicio(obj.getFechaInicio());
		contratoConvenio.setFechaFin(obj.getFechaFin());
		contratoConvenio.setMaximoAudiencias(obj.getMaximoAudiencias());
		contratoConvenio.setDiasCancelacion(obj.getDiasCancelacion());
		contratoConvenio.setIncrementoAudienciaAdicional(obj.getIncrementoAudienciaAdicional());
		contratoConvenio.setIdDocumento(obj.getIdDocumento());
		contratoConvenio.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		contratoConvenio.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		contratoConvenio.setEstadoRegistro(obj.getEstadoRegistro());
		contratoConvenio.setTipoCombinacion(obj.getTipoCombinacion());
		
		return contratoConvenio;
	}


	@Override
	public ContratoConvenio transformarEntidadConDependencias(ContratoConvenio obj) {
		ContratoConvenio contratoConvenio = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return contratoConvenio;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<ContratoConvenio> coleccion) {
		List<ContratoConvenioDTO> contratoConvenioDTOList = new ArrayList<>();
		for(ContratoConvenio c : coleccion)
			contratoConvenioDTOList.add(transformarConDependencias(c));
		return contratoConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<ContratoConvenio> coleccion) {
		List<ContratoConvenioDTO> contratoConvenioDTOList = new ArrayList<>();
		for(ContratoConvenio c : coleccion)
			contratoConvenioDTOList.add(transformarSinDependencias(c));
		return contratoConvenioDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ContratoConvenio> coleccion) {
		List<ContratoConvenio> contratoConvenioList = new ArrayList<>();
		for(ContratoConvenio c : coleccion)
			contratoConvenioList.add(transformarEntidadConDependencias(c));
		return contratoConvenioList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ContratoConvenio> coleccion) {
		List<ContratoConvenio> contratoConvenioList = new ArrayList<>();
		for(ContratoConvenio c : coleccion)
			contratoConvenioList.add(transformarEntidadSinDependencias(c));
		return contratoConvenioList;
	}


	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
