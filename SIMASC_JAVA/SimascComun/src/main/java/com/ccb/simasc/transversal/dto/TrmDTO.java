package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Trm;


@XmlRootElement
public class TrmDTO extends IDTO<Trm> implements Serializable{	

	private static final long serialVersionUID = 1L;

	private Long idTrm;
	private Date fecha;	
	private String valor;		
	private String idUsuarioModificacion;	
	private Date fechaUltimaModificacion;	
	private String estadoRegistro;
	
    public TrmDTO(){
    }

	public Long getIdTrm() {
		return idTrm;
	}
	
	public void setIdTrm(Long idTrm) {
		this.idTrm = idTrm;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estadoRegistro == null) ? 0 : estadoRegistro.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + ((idTrm == null) ? 0 : idTrm.hashCode());
		result = prime * result + ((idUsuarioModificacion == null) ? 0 : idUsuarioModificacion.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrmDTO other = (TrmDTO) obj;
		if (estadoRegistro == null) {
			if (other.estadoRegistro != null)
				return false;
		} else if (!estadoRegistro.equals(other.estadoRegistro))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (fechaUltimaModificacion == null) {
			if (other.fechaUltimaModificacion != null)
				return false;
		} else if (!fechaUltimaModificacion.equals(other.fechaUltimaModificacion))
			return false;
		if (idTrm == null) {
			if (other.idTrm != null)
				return false;
		} else if (!idTrm.equals(other.idTrm))
			return false;
		if (idUsuarioModificacion == null) {
			if (other.idUsuarioModificacion != null)
				return false;
		} else if (!idUsuarioModificacion.equals(other.idUsuarioModificacion))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public TrmDTO transformarSinDependencias(Trm obj) {
		
		TrmDTO trmDTO = new TrmDTO();
		
		trmDTO.setIdTrm(obj.getIdTrm());
		trmDTO.setFecha(obj.getFecha());
		trmDTO.setValor(obj.getValor());
		trmDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		trmDTO.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		trmDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return trmDTO;
	}

	@Override
	public TrmDTO transformarConDependencias(Trm obj) {
		TrmDTO trmDTO = transformarSinDependencias(obj);
		return trmDTO;
	}

	@Override
	public Trm transformarEntidadSinDependencias(Trm obj) {
		Trm trm = new Trm();
		
		trm.setIdTrm(obj.getIdTrm());
		trm.setFecha(obj.getFecha());
		trm.setValor(obj.getValor());
		trm.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		trm.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		trm.setEstadoRegistro(obj.getEstadoRegistro());
		return trm;
	}

	@Override
	public Trm transformarEntidadConDependencias(Trm obj) {
		Trm trm = transformarEntidadSinDependencias(obj);		
		return trm;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<Trm> coleccion) {
		List<TrmDTO> trmDTOList = new ArrayList<>();
		for(Trm c : coleccion)
			trmDTOList.add(transformarConDependencias(c));
		return trmDTOList;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<Trm> coleccion) {
		List<TrmDTO> trmDTOList = new ArrayList<>();
		for(Trm c : coleccion)
			trmDTOList.add(transformarSinDependencias(c));
		return trmDTOList;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<Trm> coleccion) {
		List<Trm> trmList = new ArrayList<>();
		for(Trm c : coleccion)
			trmList.add(transformarEntidadConDependencias(c));
		return trmList;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<Trm> coleccion) {
		List<Trm> trmList = new ArrayList<>();
		for(Trm c : coleccion)
			trmList.add(transformarEntidadSinDependencias(c));
		return trmList;
	}
}
