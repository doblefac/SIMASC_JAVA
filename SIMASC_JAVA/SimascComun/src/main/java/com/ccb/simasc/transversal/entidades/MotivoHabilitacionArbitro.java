package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="MOTIVOS_HABILITACION_ARBITRO")
@NamedQuery(name = "MotivoHabilitacionArbitro.findAll", query = "SELECT m FROM MotivoHabilitacionArbitro m")
public class MotivoHabilitacionArbitro  implements Serializable{

	@Id
    @Column(name="mha_codigo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long codigo;
    
	@ManyToOne
	@JoinColumn(name = "codigo_inactivacion", referencedColumnName = "codigo")
	private Dominio codigoInactivacion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Dominio getCodigoInactivacion() {
		return codigoInactivacion;
	}

	public void setCodigoInactivacion(Dominio codigoInactivacion) {
		this.codigoInactivacion = codigoInactivacion;
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
	public String toString() {
		return "MotivoHabilitacionArbitro [codigo=" + codigo + ", codigoInactivacion=" + codigoInactivacion
				+ ", idUsuarioModificacion=" + idUsuarioModificacion + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoRegistro=" + estadoRegistro + "]";
	}





	private static final long serialVersionUID = 1L;

}
