package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ESTADO_MOTIVO_DISPONIBILIDAD")
@NamedQuery(name = "EstadoMotivoDisponibilidad.findAll", query = "SELECT p FROM EstadoMotivoDisponibilidad p")
public class EstadoMotivoDisponibilidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Transient
	private Date fechaAsignacion;

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_PK_ID_MOTIVO_ESTADO = "EstadoMotivoDisponibilidadPK.idMotivoEstado";
			
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_PK_ESTADO_PARA_SORTEO = "EstadoMotivoDisponibilidadPK.estadoParaSorteo";
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ESTADO = "estado";
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD
            = {ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_PK_ID_MOTIVO_ESTADO, ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_PK_ESTADO_PARA_SORTEO
    	, ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ESTADO, ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ID_USUARIO_MODIFICACION
    	,ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD_ESTADO_REGISTRO};

	@EmbeddedId
	private EstadoMotivoDisponibilidadPK estadoMotivoDisponibilidadPK;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;				
    
	@Column(name="estado_registro")
	private String estadoRegistro;			
	
    public EstadoMotivoDisponibilidad(){
		estadoMotivoDisponibilidadPK = new EstadoMotivoDisponibilidadPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EstadoMotivoDisponibilidadPK getEstadoMotivoDisponibilidadPK(){
		return this.estadoMotivoDisponibilidadPK;
	}
	
	public void setEstadoMotivoDisponibilidadPK(EstadoMotivoDisponibilidadPK estadoMotivoDisponibilidadPK){
		this.estadoMotivoDisponibilidadPK   = estadoMotivoDisponibilidadPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ESTADO_MOTIVO_DISPONIBILIDAD) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.estadoMotivoDisponibilidadPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoMotivoDisponibilidad que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadoMotivoDisponibilidad other = (EstadoMotivoDisponibilidad) obj;
        
        if (!Objects.equals(this.estadoMotivoDisponibilidadPK, other.estadoMotivoDisponibilidadPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}


	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
    
	// protected region metodos adicionales end

} 

