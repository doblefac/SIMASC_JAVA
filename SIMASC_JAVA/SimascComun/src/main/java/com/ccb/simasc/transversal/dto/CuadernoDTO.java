package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Cuaderno;

@XmlRootElement
public class CuadernoDTO extends  IDTO<Cuaderno> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCuaderno;
	
	private String nombre;		
	private boolean publicaExpediente;
	private int orden;
	private String idUsuarioModificacion;		
	private Date fechaUltimaModificacion;		
	private String estadoRegistro;

	public CuadernoDTO transformarSinDependencias(Cuaderno obj) {
		CuadernoDTO cuadernoDTO = new CuadernoDTO();
		cuadernoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		cuadernoDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cuadernoDTO.setIdCuaderno(obj.getIdCuaderno());
		cuadernoDTO.setPublicaExpediente(obj.getPublicaExpediente());
		cuadernoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cuadernoDTO.setNombre(obj.getNombre());
		cuadernoDTO.setOrden(obj.getOrden());
		return cuadernoDTO;
	}

	@Override
	public CuadernoDTO transformarConDependencias(Cuaderno obj) {
		CuadernoDTO cuadernoDTO = transformarSinDependencias(obj);
		return cuadernoDTO;
	}

	@Override
	public Cuaderno transformarEntidadSinDependencias(Cuaderno obj) {
		Cuaderno cuaderno = new Cuaderno();
		cuaderno.setEstadoRegistro(obj.getEstadoRegistro());
		cuaderno.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		cuaderno.setIdCuaderno(obj.getIdCuaderno());
		cuaderno.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		cuaderno.setNombre(obj.getNombre());
		cuaderno.setOrden(obj.getOrden());
		return cuaderno;
	}

	@Override
	public Cuaderno transformarEntidadConDependencias(Cuaderno obj) {
		Cuaderno cuaderno = this.transformarEntidadSinDependencias(obj);
		return cuaderno;
	}

	@Override
	public Collection transformarColeccionConDependencias(
			Collection<Cuaderno> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionSinDependencias(
			Collection<Cuaderno> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(
			Collection<Cuaderno> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(
			Collection<Cuaderno> coleccion) {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getIdCuaderno() {
		return idCuaderno;
	}

	public void setIdCuaderno(Long idCuaderno) {
		this.idCuaderno = idCuaderno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPublicaExpediente() {
		return publicaExpediente;
	}

	public void setPublicaExpediente(boolean publicaExpediente) {
		this.publicaExpediente = publicaExpediente;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
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

	
}
