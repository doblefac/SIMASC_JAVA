package com.ccb.simasc.transversal.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DocumentoRadicado;

@XmlRootElement
public class DocumentoRadicadoDTO extends IDTO<DocumentoRadicado> implements Serializable{	

	private Long idDocumentoRadicado;
	private Long idDocumento;
	private Date fechaRadicacion;	
	private String idUsuarioModificacion;	
	private Date fechaUltimaModificacion;	
	private String estadoRegistro;
	
    public DocumentoRadicadoDTO(){
    }

    public Long getIdDocumentoRadicado() {
		return idDocumentoRadicado;
	}

	public void setIdDocumentoRadicado(Long idDocumentoRadicado) {
		this.idDocumentoRadicado = idDocumentoRadicado;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
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
		result = prime * result + ((fechaRadicacion == null) ? 0 : fechaRadicacion.hashCode());
		result = prime * result + ((fechaUltimaModificacion == null) ? 0 : fechaUltimaModificacion.hashCode());
		result = prime * result + ((idDocumento == null) ? 0 : idDocumento.hashCode());
		result = prime * result + ((idDocumentoRadicado == null) ? 0 : idDocumentoRadicado.hashCode());
		result = prime * result + ((idUsuarioModificacion == null) ? 0 : idUsuarioModificacion.hashCode());
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
		DocumentoRadicadoDTO other = (DocumentoRadicadoDTO) obj;
		if (estadoRegistro == null) {
			if (other.estadoRegistro != null)
				return false;
		} else if (!estadoRegistro.equals(other.estadoRegistro))
			return false;
		if (fechaRadicacion == null) {
			if (other.fechaRadicacion != null)
				return false;
		} else if (!fechaRadicacion.equals(other.fechaRadicacion))
			return false;
		if (fechaUltimaModificacion == null) {
			if (other.fechaUltimaModificacion != null)
				return false;
		} else if (!fechaUltimaModificacion.equals(other.fechaUltimaModificacion))
			return false;
		if (idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!idDocumento.equals(other.idDocumento))
			return false;
		if (idDocumentoRadicado == null) {
			if (other.idDocumentoRadicado != null)
				return false;
		} else if (!idDocumentoRadicado.equals(other.idDocumentoRadicado))
			return false;
		if (idUsuarioModificacion == null) {
			if (other.idUsuarioModificacion != null)
				return false;
		} else if (!idUsuarioModificacion.equals(other.idUsuarioModificacion))
			return false;
		return true;
	}

	@Override
	public DocumentoRadicadoDTO transformarSinDependencias(DocumentoRadicado obj) {
		DocumentoRadicadoDTO documentoRadicadoDTO = new DocumentoRadicadoDTO();
		
		documentoRadicadoDTO.setIdDocumentoRadicado(obj.getIdDocumentoRadicado());
		documentoRadicadoDTO.setIdDocumento(obj.getIdDocumento());
		documentoRadicadoDTO.setFechaRadicacion(obj.getFechaRadicacion());
		documentoRadicadoDTO.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documentoRadicadoDTO.setFechaUltimaModificacion(obj.getFechaRadicacion());
		documentoRadicadoDTO.setEstadoRegistro(obj.getEstadoRegistro());
		
		return documentoRadicadoDTO;
	}

	@Override
	public DocumentoRadicadoDTO transformarConDependencias(DocumentoRadicado obj) {
		DocumentoRadicadoDTO documentoRadicadoDTO = transformarSinDependencias(obj);
		return documentoRadicadoDTO;
	}

	@Override
	public DocumentoRadicado transformarEntidadSinDependencias(DocumentoRadicado obj) {
		DocumentoRadicado documentoRadicado = new DocumentoRadicado();
		
		documentoRadicado.setIdDocumentoRadicado(obj.getIdDocumentoRadicado());
		documentoRadicado.setIdDocumento(obj.getIdDocumento());
		documentoRadicado.setFechaRadicacion(obj.getFechaRadicacion());
		documentoRadicado.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documentoRadicado.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documentoRadicado.setEstadoRegistro(obj.getEstadoRegistro());
		
		return documentoRadicado;
	}

	@Override
	public DocumentoRadicado transformarEntidadConDependencias(DocumentoRadicado obj) {
		DocumentoRadicado documentoRadicado = transformarEntidadSinDependencias(obj);		
		return documentoRadicado;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<DocumentoRadicado> coleccion) {
		List<DocumentoRadicadoDTO> documentoDTOList = new ArrayList<>();
		for(DocumentoRadicado c : coleccion)
			documentoDTOList.add(transformarConDependencias(c));
		return documentoDTOList;
	}



	@Override
	public Collection transformarColeccionSinDependencias(Collection<DocumentoRadicado> coleccion) {
		List<DocumentoRadicadoDTO> documentoDTOList = new ArrayList<>();
		for(DocumentoRadicado c : coleccion)
			documentoDTOList.add(transformarSinDependencias(c));
		return documentoDTOList;
	}



	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DocumentoRadicado> coleccion) {
		List<DocumentoRadicado> documentoList = new ArrayList<>();
		for(DocumentoRadicado c : coleccion)
			documentoList.add(transformarEntidadConDependencias(c));
		return documentoList;
	}



	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DocumentoRadicado> coleccion) {
		List<DocumentoRadicado> documentoList = new ArrayList<>();
		for(DocumentoRadicado c : coleccion)
			documentoList.add(transformarEntidadSinDependencias(c));
		return documentoList;
	}
	
	public DocumentoRadicado transformarEntidadSinDependencias(DocumentoRadicadoDTO obj) {
		DocumentoRadicado documentoRadicado = new DocumentoRadicado();
		
		documentoRadicado.setIdDocumentoRadicado(obj.getIdDocumentoRadicado());
		documentoRadicado.setIdDocumento(obj.getIdDocumento());
		documentoRadicado.setFechaRadicacion(obj.getFechaRadicacion());
		documentoRadicado.setIdUsuarioModificacion(obj.getIdUsuarioModificacion());
		documentoRadicado.setFechaUltimaModificacion(obj.getFechaUltimaModificacion());
		documentoRadicado.setEstadoRegistro(obj.getEstadoRegistro());
		
		return documentoRadicado;
	}
}
