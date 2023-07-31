package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Obligacion;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.ccb.simasc.transversal.entidades.ObligacionPK;
import java.math.BigDecimal;
import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad ObligacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class ObligacionDTO extends IDTO<Obligacion> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private ObligacionPK obligacionPK;

	private BigDecimal valorTotalAcuerdo;		
	private String formaDePago;		
	private String nombreBanco;		
	private String numeroCuenta;		
	private String compromiso;		
	private Date fechaCompromiso;		
	private String observaciones;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	private String direccion;
	
	 private List<ObligacionParteDTO> obligacionParteList;
	 private List<CuotaDTO> cuotaList;
	
    public ObligacionDTO(){
		obligacionPK = new ObligacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ObligacionPK getObligacionPK(){
		return this.obligacionPK;
	}
	
	public void setObligacionPK(ObligacionPK obligacionPK){
		this.obligacionPK   = obligacionPK ;
	}  
	
	public BigDecimal getValorTotalAcuerdo(){
		return this.valorTotalAcuerdo;
	}
	
	public void setValorTotalAcuerdo(BigDecimal valorTotalAcuerdo){
		this.valorTotalAcuerdo = valorTotalAcuerdo;
	}
		
	public String getFormaDePago(){
		return this.formaDePago;
	}
	
	public void setFormaDePago(String formaDePago){
		this.formaDePago = formaDePago;
	}
		
	public String getNombreBanco(){
		return this.nombreBanco;
	}
	
	public void setNombreBanco(String nombreBanco){
		this.nombreBanco = nombreBanco;
	}
		
	public String getNumeroCuenta(){
		return this.numeroCuenta;
	}
	
	public void setNumeroCuenta(String numeroCuenta){
		this.numeroCuenta = numeroCuenta;
	}
		
	public String getCompromiso(){
		return this.compromiso;
	}
	
	public void setCompromiso(String compromiso){
		this.compromiso = compromiso;
	}
		
	public Date getFechaCompromiso(){
		return this.fechaCompromiso;
	}
	
	public void setFechaCompromiso(Date fechaCompromiso){
		this.fechaCompromiso = fechaCompromiso;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.obligacionPK);        
        hash = 37 * hash + Objects.hashCode(this.valorTotalAcuerdo);
        hash = 37 * hash + Objects.hashCode(this.formaDePago);
        hash = 37 * hash + Objects.hashCode(this.nombreBanco);
        hash = 37 * hash + Objects.hashCode(this.numeroCuenta);
        hash = 37 * hash + Objects.hashCode(this.compromiso);
        hash = 37 * hash + Objects.hashCode(this.fechaCompromiso);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.direccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ObligacionDTO que se pasa
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
        final ObligacionDTO other = (ObligacionDTO) obj;
                
        if (!Objects.equals(this.obligacionPK, other.obligacionPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotalAcuerdo, other.valorTotalAcuerdo)) {
            return false;
        }
        
        if (!Objects.equals(this.formaDePago, other.formaDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreBanco, other.nombreBanco)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        
        if (!Objects.equals(this.compromiso, other.compromiso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCompromiso, other.fechaCompromiso)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.direccion, other.direccion);
                
    }
    
    @Override
	public ObligacionDTO transformarSinDependencias(Obligacion obj) {
		ObligacionDTO obligacionDTO = new ObligacionDTO();
		
		obligacionDTO.setObligacionPK(obj.getObligacionPK());
		obligacionDTO.setValorTotalAcuerdo(obj.getValorTotalAcuerdo());
		obligacionDTO.setFormaDePago(obj.getFormaDePago());
		obligacionDTO.setNombreBanco(obj.getNombreBanco());
		obligacionDTO.setNumeroCuenta(obj.getNumeroCuenta());
		obligacionDTO.setCompromiso(obj.getCompromiso());
		obligacionDTO.setFechaCompromiso(obj.getFechaCompromiso());
		obligacionDTO.setObservaciones(obj.getObservaciones());
		obligacionDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		obligacionDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		obligacionDTO.setEstadoRegistro(obj.getEstadoRegistro());
		obligacionDTO.setIdDocumento(obj.getIdDocumento());
		obligacionDTO.setDireccion(obj.getDireccion());
		
		return obligacionDTO;
	}

	@Override
	public ObligacionDTO transformarConDependencias(Obligacion obj) {
		ObligacionDTO obligacionDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		// TODO Escriba en esta seccion sus modificaciones
		if (obj.getObligacionParteList() != null) {
			List<ObligacionParteDTO> obligacionParteListDTO = (List<ObligacionParteDTO>) new ObligacionParteDTO()
					.transformarColeccionSinDependencias(obj.getObligacionParteList());
			obligacionDTO.setObligacionParteList(obligacionParteListDTO);
		}
		if (obj.getCuotaList() != null) {
			List<CuotaDTO> cuotaListDTO = (List<CuotaDTO>) new CuotaDTO()
					.transformarColeccionSinDependencias(obj.getCuotaList());
			obligacionDTO.setCuotaList(cuotaListDTO);
		}
		// protected region modificaciones transformarConDependencias end

		return obligacionDTO;
	}

	@Override
	public Obligacion transformarEntidadSinDependencias(Obligacion obj) {
		Obligacion obligacion = new Obligacion();
		
		obligacion.setObligacionPK(obj.getObligacionPK());
		
		obligacion.setValorTotalAcuerdo(obj.getValorTotalAcuerdo());
		obligacion.setFormaDePago(obj.getFormaDePago());
		obligacion.setNombreBanco(obj.getNombreBanco());
		obligacion.setNumeroCuenta(obj.getNumeroCuenta());
		obligacion.setCompromiso(obj.getCompromiso());
		obligacion.setFechaCompromiso(obj.getFechaCompromiso());
		obligacion.setObservaciones(obj.getObservaciones());
		obligacion.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		obligacion.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		obligacion.setEstadoRegistro(obj.getEstadoRegistro());
		obligacion.setIdDocumento(obj.getIdDocumento());
		obligacion.setDireccion(obj.getDireccion());
		
		return obligacion;
	}


	@Override
	public Obligacion transformarEntidadConDependencias(Obligacion obj) {
		Obligacion obligacion = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return obligacion;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Obligacion> coleccion) {
		List<ObligacionDTO> obligacionDTOList = new ArrayList<>();
		for(Obligacion c : coleccion)
			obligacionDTOList.add(transformarConDependencias(c));
		return obligacionDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Obligacion> coleccion) {
		List<ObligacionDTO> obligacionDTOList = new ArrayList<>();
		for(Obligacion c : coleccion)
			obligacionDTOList.add(transformarSinDependencias(c));
		return obligacionDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Obligacion> coleccion) {
		List<Obligacion> obligacionList = new ArrayList<>();
		for(Obligacion c : coleccion)
			obligacionList.add(transformarEntidadConDependencias(c));
		return obligacionList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Obligacion> coleccion) {
		List<Obligacion> obligacionList = new ArrayList<>();
		for(Obligacion c : coleccion)
			obligacionList.add(transformarEntidadSinDependencias(c));
		return obligacionList;
	}


	public List<ObligacionParteDTO> getObligacionParteList() {
		return obligacionParteList;
	}


	public void setObligacionParteList(List<ObligacionParteDTO> obligacionParteList) {
		this.obligacionParteList = obligacionParteList;
	}


	public List<CuotaDTO> getCuotaList() {
		return cuotaList;
	}


	public void setCuotaList(List<CuotaDTO> cuotaList) {
		this.cuotaList = cuotaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
