package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="MEMBRESIA")
@NamedQuery(name = "Membresia.findAll", query = "SELECT p FROM Membresia p")
public class Membresia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_MEMBRESIA_PK = "idMembresia";
	public static final String ENTIDAD_MEMBRESIA_FECHA_INICIO = "fechaInicio";
	public static final String ENTIDAD_MEMBRESIA_FECHA_FIN = "fechaFin";
	public static final String ENTIDAD_MEMBRESIA_NUMERO_RECIBO_PAGO = "numeroReciboPago";
	public static final String ENTIDAD_MEMBRESIA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_MEMBRESIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_MEMBRESIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_MEMBRESIA_ESTADO_REGISTRO_MEMBRESIA = "estadoRegistroMembresia";			
	public static final String ENTIDAD_MEMBRESIA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_MEMBRESIA
            = {ENTIDAD_MEMBRESIA_ID_USUARIO_MODIFICACION, ENTIDAD_MEMBRESIA_PK, ENTIDAD_MEMBRESIA_FECHA_FIN, ENTIDAD_MEMBRESIA_ESTADO_REGISTRO, ENTIDAD_MEMBRESIA_ID_PERSONA, ENTIDAD_MEMBRESIA_NUMERO_RECIBO_PAGO, ENTIDAD_MEMBRESIA_FECHA_INICIO, ENTIDAD_MEMBRESIA_FECHA_ULTIMA_MODIFICACION};

	@Id
    @Column(name="id_membresia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idMembresia;
    
	@Column(name="fecha_inicio")
	private Date fechaInicio;		
    
	@Column(name="fecha_fin")
	private Date fechaFin;		
    
	@Column(name="numero_recibo_pago")
	private String numeroReciboPago;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public Membresia(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdMembresia(){
		return this.idMembresia;
	}
	
	public void setIdMembresia(Long idMembresia){
		this.idMembresia = idMembresia;
	}
	
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public String getNumeroReciboPago(){
		return this.numeroReciboPago;
	}
	
	public void setNumeroReciboPago(String numeroReciboPago){
		this.numeroReciboPago = numeroReciboPago;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
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
		

    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_MEMBRESIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idMembresia);        
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.numeroReciboPago);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Membresia que se pasa
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
        final Membresia other = (Membresia) obj;
        
        if (!Objects.equals(this.idMembresia, other.idMembresia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroReciboPago, other.numeroReciboPago)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
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

	// protected region metodos adicionales end

} 

