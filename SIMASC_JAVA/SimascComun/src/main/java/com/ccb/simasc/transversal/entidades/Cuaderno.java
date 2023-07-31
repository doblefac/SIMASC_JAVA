package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="CUADERNO")
@NamedQuery(name = "Cuaderno.findAll", query = "SELECT p FROM Cuaderno p order by p.orden")
public class Cuaderno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENTIDAD_CUADERNO_PK = "idCuaderno";
	public static final String ENTIDAD_CUADERNO_NOMBRE = "nombre";
	public static final String ENTIDAD_CUADERNO_PUBLICA_EXPEDIENTE= "publicaExpediente";
	public static final String ENTIDAD_CUADERNO_ORDEN= "orden";
	public static final String ENTIDAD_CUADERNO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CUADERNO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CUADERNO_ESTADO_REGISTRO = "estadoRegistro";
	
    private static final String[] ATRIBUTOS_ENTIDAD_CARPETA
    = {ENTIDAD_CUADERNO_PK, ENTIDAD_CUADERNO_NOMBRE, ENTIDAD_CUADERNO_PUBLICA_EXPEDIENTE, 
    	ENTIDAD_CUADERNO_ORDEN, ENTIDAD_CUADERNO_ID_USUARIO_MODIFICACION, ENTIDAD_CUADERNO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CUADERNO_ESTADO_REGISTRO};

	
	@Id
    @Column(name="id_cuaderno")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCuaderno;
	
	@Column(name="nombre")
	private String nombre;		
	
	@Column(name="publica_expediente")
	private Boolean publicaExpediente;
	
	@Column(name="orden")
	private Integer orden;
	
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;

	public Cuaderno() {
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getPublicaExpediente() {
		return publicaExpediente;
	}

	public void setPublicaExpediente(Boolean publicaExpediente) {
		this.publicaExpediente = publicaExpediente;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
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
	
	public Long getIdCuaderno() {
		return idCuaderno;
	}


	public void setIdCuaderno(Long idCuaderno) {
		this.idCuaderno = idCuaderno;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CARPETA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCuaderno);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.orden);
        hash = 37 * hash + Objects.hashCode(this.publicaExpediente);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Carpeta que se pasa
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
        final Cuaderno other = (Cuaderno) obj;
        
        if (!Objects.equals(this.idCuaderno, other.idCuaderno)) {
            return false;
        }
        
        if (!Objects.equals(this.orden, other.orden)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        return Objects.equals(this.publicaExpediente, other.publicaExpediente);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
}
