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

import com.ccb.simasc.transversal.dto.formularios.HonorariosActorDTO;
import com.ccb.simasc.transversal.entidades.HonorariosFijados;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad HonorariosFijadosDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class HonorariosFijadosDTO extends IDTO<HonorariosFijados> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	private List<HonorariosActorDTO> honorariosArbitros;
	private HonorariosActorDTO honorariosSecretario;
	private HonorariosActorDTO honorariosCAC;
	private BigDecimal otrosGastos;
	private String rolHonorarios;
	private Date fechaPago;
	private BigDecimal total;
	private BigDecimal totalIva;
	private BigDecimal valorArbitros;
	private String tipoTarifa;
	
	// protected region atributos end
	private Long idHonorariosFijados;

	private BigDecimal valorFijadoPretensiones;		
	private Date fechaLimiteDePago;		
	private Date fechaFijacion;		
	private String moneda;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private Long idCaso;		
	
    public HonorariosFijadosDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdHonorariosFijados(){
		return this.idHonorariosFijados;
	}
	
	public void setIdHonorariosFijados(Long idHonorariosFijados){
		this.idHonorariosFijados = idHonorariosFijados;
	}
	
	public BigDecimal getValorFijadoPretensiones(){
		return this.valorFijadoPretensiones;
	}
	
	public void setValorFijadoPretensiones(BigDecimal valorFijadoPretensiones){
		this.valorFijadoPretensiones = valorFijadoPretensiones;
	}
		
	public Date getFechaLimiteDePago(){
		return this.fechaLimiteDePago;
	}
	
	public void setFechaLimiteDePago(Date fechaLimiteDePago){
		this.fechaLimiteDePago = fechaLimiteDePago;
	}
		
	public Date getFechaFijacion(){
		return this.fechaFijacion;
	}
	
	public void setFechaFijacion(Date fechaFijacion){
		this.fechaFijacion = fechaFijacion;
	}
		
	public String getMoneda(){
		return this.moneda;
	}
	
	public void setMoneda(String moneda){
		this.moneda = moneda;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idHonorariosFijados);        
        hash = 37 * hash + Objects.hashCode(this.valorFijadoPretensiones);
        hash = 37 * hash + Objects.hashCode(this.fechaLimiteDePago);
        hash = 37 * hash + Objects.hashCode(this.fechaFijacion);
        hash = 37 * hash + Objects.hashCode(this.moneda);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HonorariosFijadosDTO que se pasa
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
        final HonorariosFijadosDTO other = (HonorariosFijadosDTO) obj;
                
        if (!Objects.equals(this.idHonorariosFijados, other.idHonorariosFijados)) {
            return false;
        }
        
        if (!Objects.equals(this.valorFijadoPretensiones, other.valorFijadoPretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaLimiteDePago, other.fechaLimiteDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFijacion, other.fechaFijacion)) {
            return false;
        }
        
        if (!Objects.equals(this.moneda, other.moneda)) {
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
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
    
    @Override
	public HonorariosFijadosDTO transformarSinDependencias(HonorariosFijados obj) {
		HonorariosFijadosDTO honorariosFijadosDTO = new HonorariosFijadosDTO();
		
		honorariosFijadosDTO.setIdHonorariosFijados(obj.getIdHonorariosFijados());
		honorariosFijadosDTO.setValorFijadoPretensiones(obj.getValorFijadoPretensiones());
		honorariosFijadosDTO.setFechaLimiteDePago(obj.getFechaLimiteDePago());
		honorariosFijadosDTO.setFechaFijacion(obj.getFechaFijacion());
		honorariosFijadosDTO.setMoneda(obj.getMoneda());
		honorariosFijadosDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		honorariosFijadosDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		honorariosFijadosDTO.setEstadoRegistro(obj.getEstadoRegistro());
		honorariosFijadosDTO.setIdCaso(obj.getIdCaso());
		
		return honorariosFijadosDTO;
	}

	@Override
	public HonorariosFijadosDTO transformarConDependencias(HonorariosFijados obj) {
		HonorariosFijadosDTO honorariosFijadosDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		// TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return honorariosFijadosDTO;
	}

	@Override
	public HonorariosFijados transformarEntidadSinDependencias(HonorariosFijados obj) {
		HonorariosFijados honorariosFijados = new HonorariosFijados();
		
		honorariosFijados.setIdHonorariosFijados(obj.getIdHonorariosFijados());
		
		honorariosFijados.setValorFijadoPretensiones(obj.getValorFijadoPretensiones());
		honorariosFijados.setFechaLimiteDePago(obj.getFechaLimiteDePago());
		honorariosFijados.setFechaFijacion(obj.getFechaFijacion());
		honorariosFijados.setMoneda(obj.getMoneda());
		honorariosFijados.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		honorariosFijados.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		honorariosFijados.setEstadoRegistro(obj.getEstadoRegistro());
		honorariosFijados.setIdCaso(obj.getIdCaso());
		
		return honorariosFijados;
	}


	@Override
	public HonorariosFijados transformarEntidadConDependencias(HonorariosFijados obj) {
		HonorariosFijados honorariosFijados = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarEntidadConDependencias end
		
		return honorariosFijados;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<HonorariosFijados> coleccion) {
		List<HonorariosFijadosDTO> honorariosFijadosDTOList = new ArrayList<>();
		for(HonorariosFijados c : coleccion)
			honorariosFijadosDTOList.add(transformarConDependencias(c));
		return honorariosFijadosDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<HonorariosFijados> coleccion) {
		List<HonorariosFijadosDTO> honorariosFijadosDTOList = new ArrayList<>();
		for(HonorariosFijados c : coleccion)
			honorariosFijadosDTOList.add(transformarSinDependencias(c));
		return honorariosFijadosDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<HonorariosFijados> coleccion) {
		List<HonorariosFijados> honorariosFijadosList = new ArrayList<>();
		for(HonorariosFijados c : coleccion)
			honorariosFijadosList.add(transformarEntidadConDependencias(c));
		return honorariosFijadosList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<HonorariosFijados> coleccion) {
		List<HonorariosFijados> honorariosFijadosList = new ArrayList<>();
		for(HonorariosFijados c : coleccion)
			honorariosFijadosList.add(transformarEntidadSinDependencias(c));
		return honorariosFijadosList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public List<HonorariosActorDTO> getHonorariosArbitros() {
		return honorariosArbitros;
	}

	public void setHonorariosArbitros(List<HonorariosActorDTO> honorariosArbitros) {
		this.honorariosArbitros = honorariosArbitros;
	}

	public HonorariosActorDTO getHonorariosSecretario() {
		return honorariosSecretario;
	}

	public void setHonorariosSecretario(HonorariosActorDTO honorariosSecretario) {
		this.honorariosSecretario = honorariosSecretario;
	}

	public HonorariosActorDTO getHonorariosCAC() {
		return honorariosCAC;
	}

	public void setHonorariosCAC(HonorariosActorDTO honorariosCAC) {
		this.honorariosCAC = honorariosCAC;
	}

	public BigDecimal getOtrosGastos() {
		return otrosGastos;
	}

	public void setOtrosGastos(BigDecimal otrosGastos) {
		this.otrosGastos = otrosGastos;
	}

	public String getRolHonorarios() {
		return rolHonorarios;
	}

	public void setRolHonorarios(String rolHonorarios) {
		this.rolHonorarios = rolHonorarios;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public BigDecimal getValorArbitros() {
		return valorArbitros;
	}
	
	public void setValorArbitros(BigDecimal valorArbitros) {
		this.valorArbitros = valorArbitros;
	}

	public String getTipoTarifa() {
		return tipoTarifa;
	}



	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}



	public void setHonorariosFijados(HonorariosFijadosDTO honorariosFijados) {
		this.honorariosArbitros = honorariosFijados.getHonorariosArbitros();
		this.honorariosSecretario = honorariosFijados.getHonorariosSecretario();
		this.honorariosCAC = honorariosFijados.getHonorariosCAC();
		this.otrosGastos = honorariosFijados.getOtrosGastos();
		this.rolHonorarios = honorariosFijados.getRolHonorarios();
		this.fechaPago = honorariosFijados.getFechaPago();
		this.total = honorariosFijados.getTotal();
		this.totalIva = honorariosFijados.getTotalIva();
		this.idHonorariosFijados = honorariosFijados.getIdHonorariosFijados();
		this.valorFijadoPretensiones = honorariosFijados.getValorFijadoPretensiones();
		this.fechaLimiteDePago = honorariosFijados.getFechaLimiteDePago();
		this.fechaFijacion = honorariosFijados.getFechaFijacion();
		this.moneda = honorariosFijados.getMoneda();
		this.idCaso = honorariosFijados.getIdCaso();
	}
	// protected region metodos adicionales end

}
