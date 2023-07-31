package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Collection;

import com.ccb.simasc.transversal.entidades.ArbitroCasoLiberacion;

public class ArbitroCasoLiberacionDTO extends IDTO<ArbitroCasoLiberacion> implements Serializable {
	
	private static final long serialVersionUID = 9086166505971042104L;
	
	private long idPersona;
	private long idCaso;
	
	
	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(long idCaso) {
		this.idCaso = idCaso;
	}

	@Override
	public IDTO transformarSinDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public IDTO transformarConDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public ArbitroCasoLiberacion transformarEntidadSinDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public ArbitroCasoLiberacion transformarEntidadConDependencias(ArbitroCasoLiberacion obj) {
		return null;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<ArbitroCasoLiberacion> coleccion) {
		return null;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<ArbitroCasoLiberacion> coleccion) {
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<ArbitroCasoLiberacion> coleccion) {
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<ArbitroCasoLiberacion> coleccion) {
		return null;
	}

}
