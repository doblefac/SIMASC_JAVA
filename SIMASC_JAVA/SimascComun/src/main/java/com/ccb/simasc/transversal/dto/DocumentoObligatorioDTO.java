package com.ccb.simasc.transversal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.DocumentoObligatorio;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Servicio;

@XmlRootElement
public class DocumentoObligatorioDTO extends IDTO<DocumentoObligatorio> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long idDocumentoObligatorio;
	
	private String codigo;	
	
	private String descripcion;	
	
	private String mensaje;	
	
	private boolean estado;	
	
	private Servicio servicio;

	public Long getIdDocumentoObligatorio() {
		return idDocumentoObligatorio;
	}

	public void setIdDocumentoObligatorio(Long idDocumentoObligatorio) {
		this.idDocumentoObligatorio = idDocumentoObligatorio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Override
	public DocumentoObligatorioDTO transformarSinDependencias(DocumentoObligatorio obj) {
		DocumentoObligatorioDTO dto = new DocumentoObligatorioDTO();
		dto.setIdDocumentoObligatorio(obj.getIdDocumentoObligatorio());
		dto.setCodigo(obj.getCodigo());
		dto.setDescripcion(obj.getDescripcion());
		dto.setMensaje(obj.getMensaje());
		
		return dto;
	}

	@Override
	public DocumentoObligatorioDTO transformarConDependencias(DocumentoObligatorio obj) {
		return null;
	}

	@Override
	public DocumentoObligatorio transformarEntidadSinDependencias(DocumentoObligatorio obj) {
		return null;
	}

	@Override
	public DocumentoObligatorio transformarEntidadConDependencias(DocumentoObligatorio obj) {
		return null;
	}

	@Override
	public Collection transformarColeccionConDependencias(Collection<DocumentoObligatorio> coleccion) {
		return null;
	}

	@Override
	public Collection transformarColeccionSinDependencias(Collection<DocumentoObligatorio> coleccion) {
		List<DocumentoObligatorioDTO> DocumentoObligatorioDTOList = new ArrayList<>();
		for(DocumentoObligatorio c : coleccion)
			DocumentoObligatorioDTOList.add(transformarSinDependencias(c));
		return DocumentoObligatorioDTOList;
	}

	@Override
	public Collection transformarColeccionEntidadesConDependencias(Collection<DocumentoObligatorio> coleccion) {
		return null;
	}

	@Override
	public Collection transformarColeccionEntidadesSinDependencias(Collection<DocumentoObligatorio> coleccion) {
		return null;
	}
	
	
}
