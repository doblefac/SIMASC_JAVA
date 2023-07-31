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

import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DetallePagoCaso;
import com.ccb.simasc.transversal.entidades.PagoCaso;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad PagoCasoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class PagoCasoDTO extends IDTO<PagoCaso> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	private static final long serialVersionUID = -7167762692330801803L;

	private List<DetallePagoCasoDTO> detallePagoCasoList;
	// protected region atributos end
	private String numeroRecibo;

	private Long valor;		
	private Date fechaPago;		
	private String nombrePersona;		
	private String tipoDeDocumento;		
	private String numeroDeDocumento;		
	private String estado;		
	private String descripcion;		
	private String tipoCuantia;		
	private Long valorCuantia;		
	private Long idOrdenDePago;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idSede;		
	private Long idCaso;		
	private Long idServicio;		
	
    public PagoCasoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public String getNumeroRecibo(){
		return this.numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo){
		this.numeroRecibo = numeroRecibo;
	}
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getNombrePersona(){
		return this.nombrePersona;
	}
	
	public void setNombrePersona(String nombrePersona){
		this.nombrePersona = nombrePersona;
	}
		
	public String getTipoDeDocumento(){
		return this.tipoDeDocumento;
	}
	
	public void setTipoDeDocumento(String tipoDeDocumento){
		this.tipoDeDocumento = tipoDeDocumento;
	}
		
	public String getNumeroDeDocumento(){
		return this.numeroDeDocumento;
	}
	
	public void setNumeroDeDocumento(String numeroDeDocumento){
		this.numeroDeDocumento = numeroDeDocumento;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public Long getValorCuantia(){
		return this.valorCuantia;
	}
	
	public void setValorCuantia(Long valorCuantia){
		this.valorCuantia = valorCuantia;
	}
		
	public Long getIdOrdenDePago(){
		return this.idOrdenDePago;
	}
	
	public void setIdOrdenDePago(Long idOrdenDePago){
		this.idOrdenDePago = idOrdenDePago;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.nombrePersona);
        hash = 37 * hash + Objects.hashCode(this.tipoDeDocumento);
        hash = 37 * hash + Objects.hashCode(this.numeroDeDocumento);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + Objects.hashCode(this.valorCuantia);
        hash = 37 * hash + Objects.hashCode(this.idOrdenDePago);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PagoCasoDTO que se pasa
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
        final PagoCasoDTO other = (PagoCasoDTO) obj;
                
        if (!Objects.equals(this.numeroRecibo, other.numeroRecibo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.nombrePersona, other.nombrePersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeDocumento, other.tipoDeDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeDocumento, other.numeroDeDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.valorCuantia, other.valorCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.idOrdenDePago, other.idOrdenDePago)) {
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
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
    
    @Override
	public PagoCasoDTO transformarSinDependencias(PagoCaso obj) {
		PagoCasoDTO pagoCasoDTO = new PagoCasoDTO();
		
		pagoCasoDTO.setNumeroRecibo(obj.getNumeroRecibo());
		pagoCasoDTO.setValor(obj.getValor());
		pagoCasoDTO.setFechaPago(obj.getFechaPago());
		pagoCasoDTO.setNombrePersona(obj.getNombrePersona());
		pagoCasoDTO.setTipoDeDocumento(obj.getTipoDeDocumento());
		pagoCasoDTO.setNumeroDeDocumento(obj.getNumeroDeDocumento());
		pagoCasoDTO.setEstado(obj.getEstado());
		pagoCasoDTO.setDescripcion(obj.getDescripcion());
		pagoCasoDTO.setTipoCuantia(obj.getTipoCuantia());
		pagoCasoDTO.setValorCuantia(obj.getValorCuantia());
		pagoCasoDTO.setIdOrdenDePago(obj.getIdOrdenDePago());
		pagoCasoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pagoCasoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pagoCasoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		pagoCasoDTO.setIdSede(obj.getIdSede());
		pagoCasoDTO.setIdCaso(obj.getIdCaso());
		pagoCasoDTO.setIdServicio(obj.getIdServicio());
		
		return pagoCasoDTO;
	}

	@Override
	public PagoCasoDTO transformarConDependencias(PagoCaso obj) {
		PagoCasoDTO pagoCasoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return pagoCasoDTO;
	}

	@Override
	public PagoCaso transformarEntidadSinDependencias(PagoCaso obj) {
		PagoCaso pagoCaso = new PagoCaso();
		
		pagoCaso.setNumeroRecibo(obj.getNumeroRecibo());
		
		pagoCaso.setValor(obj.getValor());
		pagoCaso.setFechaPago(obj.getFechaPago());
		pagoCaso.setNombrePersona(obj.getNombrePersona());
		pagoCaso.setTipoDeDocumento(obj.getTipoDeDocumento());
		pagoCaso.setNumeroDeDocumento(obj.getNumeroDeDocumento());
		pagoCaso.setEstado(obj.getEstado());
		pagoCaso.setDescripcion(obj.getDescripcion());
		pagoCaso.setTipoCuantia(obj.getTipoCuantia());
		pagoCaso.setValorCuantia(obj.getValorCuantia());
		pagoCaso.setIdOrdenDePago(obj.getIdOrdenDePago());
		pagoCaso.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		pagoCaso.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		pagoCaso.setEstadoRegistro(obj.getEstadoRegistro());
		pagoCaso.setIdSede(obj.getIdSede());
		pagoCaso.setIdCaso(obj.getIdCaso());
		pagoCaso.setIdServicio(obj.getIdServicio());
		
		return pagoCaso;
	}


	@Override
	public PagoCaso transformarEntidadConDependencias(PagoCaso obj) {
		PagoCaso pagoCaso = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		Caso caso = (obj.getCaso() == null)?new CasoDTO().transformarEntidadSinDependencias(new Caso()):new CasoDTO().transformarEntidadSinDependencias(obj.getCaso());
		
		pagoCaso.setSede(new SedeDTO().transformarEntidadSinDependencias(obj.getSede()));
		pagoCaso.setCaso(caso);
		// protected region modificaciones transformarEntidadConDependencias end
		
		return pagoCaso;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<PagoCaso> coleccion) {
		List<PagoCasoDTO> pagoCasoDTOList = new ArrayList<>();
		for(PagoCaso c : coleccion)
			pagoCasoDTOList.add(transformarConDependencias(c));
		return pagoCasoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<PagoCaso> coleccion) {
		List<PagoCasoDTO> pagoCasoDTOList = new ArrayList<>();
		for(PagoCaso c : coleccion)
			pagoCasoDTOList.add(transformarSinDependencias(c));
		return pagoCasoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<PagoCaso> coleccion) {
		List<PagoCaso> pagoCasoList = new ArrayList<>();
		for(PagoCaso c : coleccion)
			pagoCasoList.add(transformarEntidadConDependencias(c));
		return pagoCasoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<PagoCaso> coleccion) {
		List<PagoCaso> pagoCasoList = new ArrayList<>();
		for(PagoCaso c : coleccion)
			pagoCasoList.add(transformarEntidadSinDependencias(c));
		return pagoCasoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	
	public Collection transformarColeccionEntidadesConDependenciaDetalle(Collection<PagoCaso> coleccion){
		List<PagoCaso> pagoCasoList = new ArrayList<>();
		for(PagoCaso c : coleccion)
			pagoCasoList.add(transformarEntidadConDependenciaDetalle(c));
		return pagoCasoList;
	}
	

	public PagoCaso transformarEntidadConDependenciaDetalle(PagoCaso obj) {
		PagoCaso pagoCaso = transformarEntidadSinDependencias(obj);
		if(obj.getDetallePagoCasoList() != null && !obj.getDetallePagoCasoList().isEmpty()){
			pagoCaso.setDetallePagoCasoList((List<DetallePagoCaso>) 
					new DetallePagoCasoDTO().transformarColeccionEntidadesSinDependencias(obj.getDetallePagoCasoList()));	
		}
		
		return pagoCaso;
	}


	public List<DetallePagoCasoDTO> getDetallePagoCasoList() {
		return detallePagoCasoList;
	}



	public void setDetallePagoCasoList(List<DetallePagoCasoDTO> detallePagoCasoList) {
		this.detallePagoCasoList = detallePagoCasoList;
	}
	
	// protected region metodos adicionales end
	
	

}
