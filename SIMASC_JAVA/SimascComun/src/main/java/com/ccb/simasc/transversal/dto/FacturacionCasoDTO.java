package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.FacturacionCaso;
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
 * DAO que contiene la informacion de la entidad FacturacionCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class FacturacionCasoDTO extends IDTO<FacturacionCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idFacturacionCaso;

	private Date fechaDeFacturacion;		
	private BigDecimal valor;		
	private boolean aprobado;		
	private BigDecimal valorCobrosAdicionales;		
	private BigDecimal valorTotal;		
	private Date fechaDeAprobacion;		
	private boolean cobrado;		
	private Date fechaDeCobro;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public FacturacionCasoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdFacturacionCaso(){
		return this.idFacturacionCaso;
	}
	
	public void setIdFacturacionCaso(Long idFacturacionCaso){
		this.idFacturacionCaso = idFacturacionCaso;
	}
	
	public Date getFechaDeFacturacion(){
		return this.fechaDeFacturacion;
	}
	
	public void setFechaDeFacturacion(Date fechaDeFacturacion){
		this.fechaDeFacturacion = fechaDeFacturacion;
	}
		
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
	}
		
	public boolean getAprobado(){
		return this.aprobado;
	}
	
	public void setAprobado(boolean aprobado){
		this.aprobado = aprobado;
	}
		
	public BigDecimal getValorCobrosAdicionales(){
		return this.valorCobrosAdicionales;
	}
	
	public void setValorCobrosAdicionales(BigDecimal valorCobrosAdicionales){
		this.valorCobrosAdicionales = valorCobrosAdicionales;
	}
		
	public BigDecimal getValorTotal(){
		return this.valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal){
		this.valorTotal = valorTotal;
	}
		
	public Date getFechaDeAprobacion(){
		return this.fechaDeAprobacion;
	}
	
	public void setFechaDeAprobacion(Date fechaDeAprobacion){
		this.fechaDeAprobacion = fechaDeAprobacion;
	}
		
	public boolean getCobrado(){
		return this.cobrado;
	}
	
	public void setCobrado(boolean cobrado){
		this.cobrado = cobrado;
	}
		
	public Date getFechaDeCobro(){
		return this.fechaDeCobro;
	}
	
	public void setFechaDeCobro(Date fechaDeCobro){
		this.fechaDeCobro = fechaDeCobro;
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
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idFacturacionCaso);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeFacturacion);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + (this.aprobado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.valorCobrosAdicionales);
        hash = 37 * hash + Objects.hashCode(this.valorTotal);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAprobacion);
        hash = 37 * hash + (this.cobrado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCobro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FacturacionCasoDTO que se pasa
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
        final FacturacionCasoDTO other = (FacturacionCasoDTO) obj;
                
        if (!Objects.equals(this.idFacturacionCaso, other.idFacturacionCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeFacturacion, other.fechaDeFacturacion)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.aprobado, other.aprobado)) {
            return false;
        }
        
        if (!Objects.equals(this.valorCobrosAdicionales, other.valorCobrosAdicionales)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }  
        
        if (!Objects.equals(this.fechaDeAprobacion, other.fechaDeAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.cobrado, other.cobrado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCobro, other.fechaDeCobro)) {
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
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
    
    @Override
	public FacturacionCasoDTO transformarSinDependencias(FacturacionCaso obj) {
		FacturacionCasoDTO facturacionCasoDTO = new FacturacionCasoDTO();
		
		facturacionCasoDTO.setIdFacturacionCaso(obj.getIdFacturacionCaso());
		facturacionCasoDTO.setFechaDeFacturacion(obj.getFechaDeFacturacion());
		facturacionCasoDTO.setValor(obj.getValor());
		facturacionCasoDTO.setAprobado(obj.getAprobado());
		facturacionCasoDTO.setValorCobrosAdicionales(obj.getValorCobrosAdicionales());
		facturacionCasoDTO.setValorTotal(obj.getValorTotal());
		facturacionCasoDTO.setFechaDeAprobacion(obj.getFechaDeAprobacion());
		facturacionCasoDTO.setCobrado(obj.getCobrado());
		facturacionCasoDTO.setFechaDeCobro(obj.getFechaDeCobro());
		facturacionCasoDTO.setIdCaso(obj.getIdCaso());
		facturacionCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		facturacionCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		facturacionCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return facturacionCasoDTO;
	}

	@Override
	public FacturacionCasoDTO transformarConDependencias(FacturacionCaso obj) {
		FacturacionCasoDTO facturacionCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return facturacionCasoDTO;
	}

	@Override
	public FacturacionCaso transformarEntidadSinDependencias(FacturacionCaso obj) {
		FacturacionCaso facturacionCaso = new FacturacionCaso();
		
		facturacionCaso.setIdFacturacionCaso(obj.getIdFacturacionCaso());
		
		facturacionCaso.setFechaDeFacturacion(obj.getFechaDeFacturacion());
		facturacionCaso.setValor(obj.getValor());
		facturacionCaso.setAprobado(obj.getAprobado());
		facturacionCaso.setValorCobrosAdicionales(obj.getValorCobrosAdicionales());
		facturacionCaso.setValorTotal(obj.getValorTotal());
		facturacionCaso.setFechaDeAprobacion(obj.getFechaDeAprobacion());
		facturacionCaso.setCobrado(obj.getCobrado());
		facturacionCaso.setFechaDeCobro(obj.getFechaDeCobro());
		facturacionCaso.setIdCaso(obj.getIdCaso());
		facturacionCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		facturacionCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		facturacionCaso.setEstadoRegistro(obj.getEstadoRegistro());
		
		return facturacionCaso;
	}


	@Override
	public FacturacionCaso transformarEntidadConDependencias(FacturacionCaso obj) {
		FacturacionCaso facturacionCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return facturacionCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<FacturacionCaso> coleccion) {
		List<FacturacionCasoDTO> facturacionCasoDTOList = new ArrayList<>();
		for(FacturacionCaso c : coleccion)
			facturacionCasoDTOList.add(transformarConDependencias(c));
		return facturacionCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<FacturacionCaso> coleccion) {
		List<FacturacionCasoDTO> facturacionCasoDTOList = new ArrayList<>();
		for(FacturacionCaso c : coleccion)
			facturacionCasoDTOList.add(transformarSinDependencias(c));
		return facturacionCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<FacturacionCaso> coleccion) {
		List<FacturacionCaso> facturacionCasoList = new ArrayList<>();
		for(FacturacionCaso c : coleccion)
			facturacionCasoList.add(transformarEntidadConDependencias(c));
		return facturacionCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<FacturacionCaso> coleccion) {
		List<FacturacionCaso> facturacionCasoList = new ArrayList<>();
		for(FacturacionCaso c : coleccion)
			facturacionCasoList.add(transformarEntidadSinDependencias(c));
		return facturacionCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
