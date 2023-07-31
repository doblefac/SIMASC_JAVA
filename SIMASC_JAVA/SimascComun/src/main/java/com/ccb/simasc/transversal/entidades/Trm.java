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
@Table(name="TRM")
@NamedQuery(name = "TRM.findAll", query = "SELECT t FROM TRM t")
public class Trm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String ENTIDAD_TRM_PK = "idTrm";
	public static final String ENTIDAD_TRM_FECHA = "fecha";
	public static final String ENTIDAD_TRM_VALOR = "valor";
	public static final String ENTIDAD_TRM_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TRM_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";			
	public static final String ENTIDAD_TRM_ESTADO_REGISTRO = "estadoRegistro";
	private static final String[] ATRIBUTOS_ENTIDAD_TRM = { ENTIDAD_TRM_PK, ENTIDAD_TRM_FECHA, ENTIDAD_TRM_VALOR,
			ENTIDAD_TRM_ID_USUARIO_MODIFICACION, ENTIDAD_TRM_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TRM_ESTADO_REGISTRO};

	@Id
    @Column(name="id_trm")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTrm;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="valor")
	private String valor;		
        
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;
	
    public Trm(){
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

    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TRM) {
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
		Trm other = (Trm) obj;
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
} 

