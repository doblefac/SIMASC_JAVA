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
@Table(name="REGISTRO_TRANSCRIPCION")
@NamedQuery(name = "RegistroTranscripcion.findAll", query = "SELECT p FROM RegistroTranscripcion p")
public class RegistroTranscripcion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_PK = "idTranscripcion";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_ID_REGISTRO_TRANSCRIPCION = "idRegistroTranscripcion";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_TIEMPO_TRANSCRITO = "tiempoTranscrito";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_FECHA = "fecha";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_ESTADO_REGISTRO_REGISTROTRANSCRIPCION = "estadoRegistroRegistroTranscripcion";			
	public static final String ENTIDAD_REGISTRO_TRANSCRIPCION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_REGISTRO_TRANSCRIPCION
            = {ENTIDAD_REGISTRO_TRANSCRIPCION_ID_REGISTRO_TRANSCRIPCION, ENTIDAD_REGISTRO_TRANSCRIPCION_PK, ENTIDAD_REGISTRO_TRANSCRIPCION_ESTADO_REGISTRO, ENTIDAD_REGISTRO_TRANSCRIPCION_FECHA, ENTIDAD_REGISTRO_TRANSCRIPCION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_REGISTRO_TRANSCRIPCION_TIEMPO_TRANSCRITO, ENTIDAD_REGISTRO_TRANSCRIPCION_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_transcripcion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTranscripcion;
    
	@Column(name="id_registro_transcripcion")
	private Long idRegistroTranscripcion;		
    
	@Column(name="tiempo_transcrito")
	private Long tiempoTranscrito;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_transcripcion", referencedColumnName="id_transcripcion", insertable = false, updatable = false)
    private Transcripcion transcripcion;
		
	
	
    public RegistroTranscripcion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTranscripcion(){
		return this.idTranscripcion;
	}
	
	public void setIdTranscripcion(Long idTranscripcion){
		this.idTranscripcion = idTranscripcion;
	}
	
	public Long getIdRegistroTranscripcion(){
		return this.idRegistroTranscripcion;
	}
	
	public void setIdRegistroTranscripcion(Long idRegistroTranscripcion){
		this.idRegistroTranscripcion = idRegistroTranscripcion;
	}
		
	public Long getTiempoTranscrito(){
		return this.tiempoTranscrito;
	}
	
	public void setTiempoTranscrito(Long tiempoTranscrito){
		this.tiempoTranscrito = tiempoTranscrito;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
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
		

    public Transcripcion getTranscripcion(){
		return this.transcripcion; 
	}
	
	public void setTranscripcion(Transcripcion transcripcion){
		this.transcripcion = transcripcion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_REGISTRO_TRANSCRIPCION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTranscripcion);        
        hash = 37 * hash + Objects.hashCode(this.idRegistroTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.tiempoTranscrito);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RegistroTranscripcion que se pasa
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
        final RegistroTranscripcion other = (RegistroTranscripcion) obj;
        
        if (!Objects.equals(this.idTranscripcion, other.idTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idRegistroTranscripcion, other.idRegistroTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoTranscrito, other.tiempoTranscrito)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
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

