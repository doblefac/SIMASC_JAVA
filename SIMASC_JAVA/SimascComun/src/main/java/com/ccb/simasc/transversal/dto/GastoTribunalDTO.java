package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.GastoTribunal;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad GastoTribunalDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class GastoTribunalDTO extends IDTO<GastoTribunal> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private DocumentoDTO documento;
	private Long totalGastos;
	private BigDecimal gastosPresupuestados;
	private BigDecimal saldoGastos;
	
	// protected region atributos end
	private Long idGastoTribunal;

	private Long valor;		
	private Date fecha;		
	private String descripcion;		
	private Long idCaso;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idDocumento;		
	
    public GastoTribunalDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdGastoTribunal(){
		return this.idGastoTribunal;
	}
	
	public void setIdGastoTribunal(Long idGastoTribunal){
		this.idGastoTribunal = idGastoTribunal;
	}
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
        
        hash = 37 * hash + Objects.hashCode(this.idGastoTribunal);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad GastoTribunalDTO que se pasa
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
        final GastoTribunalDTO other = (GastoTribunalDTO) obj;
                
        if (!Objects.equals(this.idGastoTribunal, other.idGastoTribunal)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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
	public GastoTribunalDTO transformarSinDependencias(GastoTribunal obj) {
		GastoTribunalDTO gastoTribunalDTO = new GastoTribunalDTO();
		
		gastoTribunalDTO.setIdGastoTribunal(obj.getIdGastoTribunal());
		gastoTribunalDTO.setValor(obj.getValor());
		gastoTribunalDTO.setFecha(obj.getFecha());
		gastoTribunalDTO.setDescripcion(obj.getDescripcion());
		gastoTribunalDTO.setIdCaso(obj.getIdCaso());
		gastoTribunalDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		gastoTribunalDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		gastoTribunalDTO.setEstadoRegistro(obj.getEstadoRegistro());
		gastoTribunalDTO.setIdDocumento(obj.getIdDocumento());
		
		return gastoTribunalDTO;
	}

	@Override
	public GastoTribunalDTO transformarConDependencias(GastoTribunal obj) {
		GastoTribunalDTO gastoTribunalDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones
		DocumentoDTO transformadorDocumento = new DocumentoDTO();
		gastoTribunalDTO.setDocumento(transformadorDocumento.transformarSinDependencias(obj.getDocumento()));
		
		// protected region modificaciones transformarConDependencias end
		
		return gastoTribunalDTO;
	}

	@Override
	public GastoTribunal transformarEntidadSinDependencias(GastoTribunal obj) {
		GastoTribunal gastoTribunal = new GastoTribunal();
		
		gastoTribunal.setIdGastoTribunal(obj.getIdGastoTribunal());
		
		gastoTribunal.setValor(obj.getValor());
		gastoTribunal.setFecha(obj.getFecha());
		gastoTribunal.setDescripcion(obj.getDescripcion());
		gastoTribunal.setIdCaso(obj.getIdCaso());
		gastoTribunal.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		gastoTribunal.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		gastoTribunal.setEstadoRegistro(obj.getEstadoRegistro());
		gastoTribunal.setIdDocumento(obj.getIdDocumento());
		
		return gastoTribunal;
	}


	@Override
	public GastoTribunal transformarEntidadConDependencias(GastoTribunal obj) {
		GastoTribunal gastoTribunal = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return gastoTribunal;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<GastoTribunal> coleccion) {
		List<GastoTribunalDTO> gastoTribunalDTOList = new ArrayList<>();
		for(GastoTribunal c : coleccion)
			gastoTribunalDTOList.add(transformarConDependencias(c));
		return gastoTribunalDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<GastoTribunal> coleccion) {
		List<GastoTribunalDTO> gastoTribunalDTOList = new ArrayList<>();
		for(GastoTribunal c : coleccion)
			gastoTribunalDTOList.add(transformarSinDependencias(c));
		return gastoTribunalDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<GastoTribunal> coleccion) {
		List<GastoTribunal> gastoTribunalList = new ArrayList<>();
		for(GastoTribunal c : coleccion)
			gastoTribunalList.add(transformarEntidadConDependencias(c));
		return gastoTribunalList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<GastoTribunal> coleccion) {
		List<GastoTribunal> gastoTribunalList = new ArrayList<>();
		for(GastoTribunal c : coleccion)
			gastoTribunalList.add(transformarEntidadSinDependencias(c));
		return gastoTribunalList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	public DocumentoDTO getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}



	public Long getTotalGastos() {
		return totalGastos;
	}



	public void setTotalGastos(Long totalGastos) {
		this.totalGastos = totalGastos;
	}



	public BigDecimal getGastosPresupuestados() {
		return gastosPresupuestados;
	}



	public void setGastosPresupuestados(BigDecimal gastosPresupuestados) {
		this.gastosPresupuestados = gastosPresupuestados;
	}



	public BigDecimal getSaldoGastos() {
		return saldoGastos;
	}



	public void setSaldoGastos(BigDecimal saldoGastos) {
		this.saldoGastos = saldoGastos;
	}
	
	// protected region metodos adicionales end

}
