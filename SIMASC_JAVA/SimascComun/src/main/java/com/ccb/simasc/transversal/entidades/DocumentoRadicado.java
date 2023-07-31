package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="DOCUMENTO_RADICADO")
@NamedQuery(name = "DocumentoRadicado.findAll", query = "SELECT p FROM DocumentoRadicado p")
public class DocumentoRadicado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public static final String ENTIDAD_DOCUMENTO_RADICADO_PK = "idDocumentoRadicado";
	public static final String ENTIDAD_DOCUMENTO_RADICADO_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_DOCUMENTO_RADICADO_FECHA_RADICACION = "fechaRadicacion";
	public static final String ENTIDAD_DOCUMENTO_RADICADO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DOCUMENTO_RADICADO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DOCUMENTO_RADICADO_ESTADO_REGISTRO = "estadoRegistro";
	private static final String[] ATRIBUTOS_ENTIDAD_DOCUMENTO_RADICADO
     	= {ENTIDAD_DOCUMENTO_RADICADO_PK, ENTIDAD_DOCUMENTO_RADICADO_ID_DOCUMENTO, ENTIDAD_DOCUMENTO_RADICADO_FECHA_RADICACION, ENTIDAD_DOCUMENTO_RADICADO_ID_USUARIO_MODIFICACION, ENTIDAD_DOCUMENTO_RADICADO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DOCUMENTO_RADICADO_ESTADO_REGISTRO};

	@Id
    @Column(name="id_documento_radicado")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDocumentoRadicado;
	
	@Column(name="id_documento")
	private Long idDocumento;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_radicacion")
	private Date fechaRadicacion;	
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;	
	
	@Column(name="estado_registro")
	private String estadoRegistro;
	
    public DocumentoRadicado(){
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

    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DOCUMENTO_RADICADO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
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
		DocumentoRadicado other = (DocumentoRadicado) obj;
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
} 

