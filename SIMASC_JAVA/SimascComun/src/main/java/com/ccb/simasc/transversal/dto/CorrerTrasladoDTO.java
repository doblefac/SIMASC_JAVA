package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.CorrerTraslado;
import com.ccb.simasc.transversal.entidades.CorrerTrasladoPK;

@XmlRootElement
public class CorrerTrasladoDTO extends IDTO<CorrerTraslado> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CorrerTrasladoPK correrTrasladoPK;
	
	private Date fechaTraslado;
	
	private String idUsuarioModificacion;		
    
	private Date fechaUltimaModificacion;		
    
	private String estadoRegistro;
	
	

	public CorrerTrasladoPK getCorrerTrasladoPK() {
		return correrTrasladoPK;
	}

	public void setCorrerTrasladoPK(CorrerTrasladoPK correrTrasladoPK) {
		this.correrTrasladoPK = correrTrasladoPK;
	}

	public Date getFechaTraslado() {
		return fechaTraslado;
	}

	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}

	public String getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	@Override
	public IDTO transformarSinDependencias(CorrerTraslado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDTO transformarConDependencias(CorrerTraslado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorrerTraslado transformarEntidadSinDependencias(CorrerTraslado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorrerTraslado transformarEntidadConDependencias(CorrerTraslado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionConDependencias(
			Collection<CorrerTraslado> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionSinDependencias(
			Collection<CorrerTraslado> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(
			Collection<CorrerTraslado> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(
			Collection<CorrerTraslado> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

}
