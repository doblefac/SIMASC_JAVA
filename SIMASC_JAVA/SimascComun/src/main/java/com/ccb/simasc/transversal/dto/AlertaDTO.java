package com.ccb.simasc.transversal.dto;
// protected region imports dto on begin
// Escriba en esta seccion sus modificaciones


import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.DiaEjecucion;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import java.util.Date;

// protected region imports dto end


/**
 * DAO que contiene la informacion de la entidad AlertaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author jsoto
 */
@XmlRootElement
public class AlertaDTO extends IDTO<Alerta> implements Serializable{	

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	private Long idAlerta;

	private String nombre;		
	private String tipoServicio;		
	private String tipoAlerta;		
	private String codigo;		
	private Long valor;		
	private String asunto;		
	private String descripcionDeAlerta;		
	private String texto;		
	private String periodicidad;		
	private String tipoPeriodicidad;		
	private String estado;		
	private Date horaEjecucion;		
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;		
	private String estadoEjecucion;		
	
    public AlertaDTO(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getTipoAlerta(){
		return this.tipoAlerta;
	}
	
	public void setTipoAlerta(String tipoAlerta){
		this.tipoAlerta = tipoAlerta;
	}
		
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
		
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getDescripcionDeAlerta(){
		return this.descripcionDeAlerta;
	}
	
	public void setDescripcionDeAlerta(String descripcionDeAlerta){
		this.descripcionDeAlerta = descripcionDeAlerta;
	}
		
	public String getTexto(){
		return this.texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
		
	public String getPeriodicidad(){
		return this.periodicidad;
	}
	
	public void setPeriodicidad(String periodicidad){
		this.periodicidad = periodicidad;
	}
		
	public String getTipoPeriodicidad(){
		return this.tipoPeriodicidad;
	}
	
	public void setTipoPeriodicidad(String tipoPeriodicidad){
		this.tipoPeriodicidad = tipoPeriodicidad;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getHoraEjecucion(){
		return this.horaEjecucion;
	}
	
	public void setHoraEjecucion(Date horaEjecucion){
		this.horaEjecucion = horaEjecucion;
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
		
	public String getEstadoEjecucion(){
		return this.estadoEjecucion;
	}
	
	public void setEstadoEjecucion(String estadoEjecucion){
		this.estadoEjecucion = estadoEjecucion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAlerta);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.tipoAlerta);
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.descripcionDeAlerta);
        hash = 37 * hash + Objects.hashCode(this.texto);
        hash = 37 * hash + Objects.hashCode(this.periodicidad);
        hash = 37 * hash + Objects.hashCode(this.tipoPeriodicidad);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.horaEjecucion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.estadoEjecucion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AlertaDTO que se pasa
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
        final AlertaDTO other = (AlertaDTO) obj;
                
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAlerta, other.tipoAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcionDeAlerta, other.descripcionDeAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        
        if (!Objects.equals(this.periodicidad, other.periodicidad)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPeriodicidad, other.tipoPeriodicidad)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.horaEjecucion, other.horaEjecucion)) {
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
        
        return Objects.equals(this.estadoEjecucion, other.estadoEjecucion);
                
    }
    
    @Override
	public AlertaDTO transformarSinDependencias(Alerta obj) {
		AlertaDTO alertaDTO = new AlertaDTO();
		
		alertaDTO.setIdAlerta(obj.getIdAlerta());
		alertaDTO.setNombre(obj.getNombre());
		alertaDTO.setTipoServicio(obj.getTipoServicio());
		alertaDTO.setTipoAlerta(obj.getTipoAlerta());
		alertaDTO.setCodigo(obj.getCodigo());
		alertaDTO.setValor(obj.getValor());
		alertaDTO.setAsunto(obj.getAsunto());
		alertaDTO.setDescripcionDeAlerta(obj.getDescripcionDeAlerta());
		alertaDTO.setTexto(obj.getTexto());
		alertaDTO.setPeriodicidad(obj.getPeriodicidad());
		alertaDTO.setTipoPeriodicidad(obj.getTipoPeriodicidad());
		alertaDTO.setEstado(obj.getEstado());
		alertaDTO.setHoraEjecucion(obj.getHoraEjecucion());
		alertaDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		alertaDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		alertaDTO.setEstadoRegistro(obj.getEstadoRegistro());
		alertaDTO.setEstadoEjecucion(obj.getEstadoEjecucion());
		
		return alertaDTO;
	}

	@Override
	public AlertaDTO transformarConDependencias(Alerta obj) {
		AlertaDTO alertaDTO = transformarSinDependencias(obj);
		// protected region modificaciones transformarConDependencias on begin
		//TODO Escriba en esta seccion sus modificaciones

		// protected region modificaciones transformarConDependencias end
		
		return alertaDTO;
	}

	@Override
	public Alerta transformarEntidadSinDependencias(Alerta obj) {
		Alerta alerta = new Alerta();
		
		alerta.setIdAlerta(obj.getIdAlerta());
		
		alerta.setNombre(obj.getNombre());
		alerta.setTipoServicio(obj.getTipoServicio());
		alerta.setTipoAlerta(obj.getTipoAlerta());
		alerta.setCodigo(obj.getCodigo());
		alerta.setValor(obj.getValor());
		alerta.setAsunto(obj.getAsunto());
		alerta.setDescripcionDeAlerta(obj.getDescripcionDeAlerta());
		alerta.setTexto(obj.getTexto());
		alerta.setPeriodicidad(obj.getPeriodicidad());
		alerta.setTipoPeriodicidad(obj.getTipoPeriodicidad());
		alerta.setEstado(obj.getEstado());
		alerta.setHoraEjecucion(obj.getHoraEjecucion());
		alerta.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		alerta.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		alerta.setEstadoRegistro(obj.getEstadoRegistro());
		alerta.setEstadoEjecucion(obj.getEstadoEjecucion());
		
		return alerta;
	}


	@Override
	public Alerta transformarEntidadConDependencias(Alerta obj) {
		Alerta alerta = transformarEntidadSinDependencias(obj);
		// protected region modificaciones transformarEntidadConDependencias on begin
		//Escriba en esta seccion sus modificaciones
		if( obj.getDiaEjecucionList() != null){
			alerta.setDiaEjecucionList((List<DiaEjecucion>) new DiaEjecucionDTO().transformarColeccionEntidadesSinDependencias( obj.getDiaEjecucionList()));			
		}

		// protected region modificaciones transformarEntidadConDependencias end
		
		return alerta;
	}



	@Override
	public Collection transformarColeccionConDependencias(Collection<Alerta> coleccion) {
		List<AlertaDTO> alertaDTOList = new ArrayList<>();
		for(Alerta c : coleccion)
			alertaDTOList.add(transformarConDependencias(c));
		return alertaDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<Alerta> coleccion) {
		List<AlertaDTO> alertaDTOList = new ArrayList<>();
		for(Alerta c : coleccion)
			alertaDTOList.add(transformarSinDependencias(c));
		return alertaDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Alerta> coleccion) {
		List<Alerta> alertaList = new ArrayList<>();
		for(Alerta c : coleccion)
			alertaList.add(transformarEntidadConDependencias(c));
		return alertaList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Alerta> coleccion) {
		List<Alerta> alertaList = new ArrayList<>();
		for(Alerta c : coleccion)
			alertaList.add(transformarEntidadSinDependencias(c));
		return alertaList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

}
