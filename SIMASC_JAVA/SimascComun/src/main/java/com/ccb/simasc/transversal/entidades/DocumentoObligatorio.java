package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENTO_OBLIGATORIO")
@NamedQuery(name = "DocumentoObligatorio.findAll", query = "SELECT do FROM DocumentoObligatorio do")
public class DocumentoObligatorio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id_documento_obligatorio")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDocumentoObligatorio;
	
	
	@Column(name="codigo")
	private String codigo;	
	
	@Column(name="descripcion")
	private String descripcion;	
	
	@Column(name="mensaje")
	private String mensaje;	
	
	@Column(name="estado")
	private boolean estado;	
	
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
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
	
	
}
