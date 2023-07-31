package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.TarifaContrato;
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
 * DAO que contiene la informacion de la entidad TarifaContratoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class TarifaContratoDTO extends IDTO<TarifaContrato> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idTarifaContrato;

	private String tipoTarifa;		
	private Integer minimoCasos;		
	private Integer maximoCasos;		
	private Double cuantiaMinima;		
	private Double cuantiaMaxima;		
	private String resultado;		
	private Double porcentaje;		
	private Double valor;		
	private String codigoContrato;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	
    public TarifaContratoDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdTarifaContrato(){
		return this.idTarifaContrato;
	}
	
	public void setIdTarifaContrato(Long idTarifaContrato){
		this.idTarifaContrato = idTarifaContrato;
	}
	
	public String getTipoTarifa(){
		return this.tipoTarifa;
	}
	
	public void setTipoTarifa(String tipoTarifa){
		this.tipoTarifa = tipoTarifa;
	}
		
	public Integer getMinimoCasos(){
		return this.minimoCasos;
	}
	
	public void setMinimoCasos(Integer minimoCasos){
		this.minimoCasos = minimoCasos;
	}
		
	public Integer getMaximoCasos(){
		return this.maximoCasos;
	}
	
	public void setMaximoCasos(Integer maximoCasos){
		this.maximoCasos = maximoCasos;
	}
		
	public Double getCuantiaMinima(){
		return this.cuantiaMinima;
	}
	
	public void setCuantiaMinima(Double cuantiaMinima){
		this.cuantiaMinima = cuantiaMinima;
	}
		
	public Double getCuantiaMaxima(){
		return this.cuantiaMaxima;
	}
	
	public void setCuantiaMaxima(Double cuantiaMaxima){
		this.cuantiaMaxima = cuantiaMaxima;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
		
	public Double getPorcentaje(){
		return this.porcentaje;
	}
	
	public void setPorcentaje(Double porcentaje){
		this.porcentaje = porcentaje;
	}
		
	public Double getValor(){
		return this.valor;
	}
	
	public void setValor(Double valor){
		this.valor = valor;
	}
		
	public String getCodigoContrato(){
		return this.codigoContrato;
	}
	
	public void setCodigoContrato(String codigoContrato){
		this.codigoContrato = codigoContrato;
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
        
        hash = 37 * hash + Objects.hashCode(this.idTarifaContrato);        
        hash = 37 * hash + Objects.hashCode(this.tipoTarifa);
        hash = 37 * hash + Objects.hashCode(this.minimoCasos);
        hash = 37 * hash + Objects.hashCode(this.maximoCasos);
        hash = 37 * hash + Objects.hashCode(this.cuantiaMinima);
        hash = 37 * hash + Objects.hashCode(this.cuantiaMaxima);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.porcentaje);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.codigoContrato);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TarifaContratoDTO que se pasa
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
        final TarifaContratoDTO other = (TarifaContratoDTO) obj;
                
        if (!Objects.equals(this.idTarifaContrato, other.idTarifaContrato)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTarifa, other.tipoTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.minimoCasos, other.minimoCasos)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoCasos, other.maximoCasos)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantiaMinima, other.cuantiaMinima)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantiaMaxima, other.cuantiaMaxima)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoContrato, other.codigoContrato)) {
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
	public TarifaContratoDTO transformarSinDependencias(TarifaContrato obj) {
		TarifaContratoDTO tarifaContratoDTO = new TarifaContratoDTO();
		
		tarifaContratoDTO.setIdTarifaContrato(obj.getIdTarifaContrato());
		tarifaContratoDTO.setTipoTarifa(obj.getTipoTarifa());
		tarifaContratoDTO.setMinimoCasos(obj.getMinimoCasos());
		tarifaContratoDTO.setMaximoCasos(obj.getMaximoCasos());
		tarifaContratoDTO.setCuantiaMinima(obj.getCuantiaMinima());
		tarifaContratoDTO.setCuantiaMaxima(obj.getCuantiaMaxima());
		tarifaContratoDTO.setResultado(obj.getResultado());
		tarifaContratoDTO.setPorcentaje(obj.getPorcentaje());
		tarifaContratoDTO.setValor(obj.getValor());
		tarifaContratoDTO.setCodigoContrato(obj.getCodigoContrato());
		tarifaContratoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tarifaContratoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tarifaContratoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tarifaContratoDTO;
	}

	@Override
	public TarifaContratoDTO transformarConDependencias(TarifaContrato obj) {
		TarifaContratoDTO tarifaContratoDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return tarifaContratoDTO;
	}

	@Override
	public TarifaContrato transformarEntidadSinDependencias(TarifaContrato obj) {
		TarifaContrato tarifaContrato = new TarifaContrato();
		
		tarifaContrato.setIdTarifaContrato(obj.getIdTarifaContrato());
		
		tarifaContrato.setTipoTarifa(obj.getTipoTarifa());
		tarifaContrato.setMinimoCasos(obj.getMinimoCasos());
		tarifaContrato.setMaximoCasos(obj.getMaximoCasos());
		tarifaContrato.setCuantiaMinima(obj.getCuantiaMinima());
		tarifaContrato.setCuantiaMaxima(obj.getCuantiaMaxima());
		tarifaContrato.setResultado(obj.getResultado());
		tarifaContrato.setPorcentaje(obj.getPorcentaje());
		tarifaContrato.setValor(obj.getValor());
		tarifaContrato.setCodigoContrato(obj.getCodigoContrato());
		tarifaContrato.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		tarifaContrato.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		tarifaContrato.setEstadoRegistro(obj.getEstadoRegistro());
		
		return tarifaContrato;
	}


	@Override
	public TarifaContrato transformarEntidadConDependencias(TarifaContrato obj) {
		TarifaContrato tarifaContrato = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return tarifaContrato;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<TarifaContrato> coleccion) {
		List<TarifaContratoDTO> tarifaContratoDTOList = new ArrayList<>();
		for(TarifaContrato c : coleccion)
			tarifaContratoDTOList.add(transformarConDependencias(c));
		return tarifaContratoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<TarifaContrato> coleccion) {
		List<TarifaContratoDTO> tarifaContratoDTOList = new ArrayList<>();
		for(TarifaContrato c : coleccion)
			tarifaContratoDTOList.add(transformarSinDependencias(c));
		return tarifaContratoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<TarifaContrato> coleccion) {
		List<TarifaContrato> tarifaContratoList = new ArrayList<>();
		for(TarifaContrato c : coleccion)
			tarifaContratoList.add(transformarEntidadConDependencias(c));
		return tarifaContratoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<TarifaContrato> coleccion) {
		List<TarifaContrato> tarifaContratoList = new ArrayList<>();
		for(TarifaContrato c : coleccion)
			tarifaContratoList.add(transformarEntidadSinDependencias(c));
		return tarifaContratoList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
