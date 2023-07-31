package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="NOTIFICACION")
@NamedQuery(name = "Notificacion.findAll", query = "SELECT p FROM Notificacion p")
public class Notificacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_NOTIFICACION_PK = "idNotificacion";
	public static final String ENTIDAD_NOTIFICACION_TEXTO_ALERTA = "textoAlerta";
	public static final String ENTIDAD_NOTIFICACION_ESTADO = "estado";
	public static final String ENTIDAD_NOTIFICACION_FECHA_NOTIFICACION = "fechaNotificacion";
	public static final String ENTIDAD_NOTIFICACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_NOTIFICACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_NOTIFICACION_ESTADO_REGISTRO_NOTIFICACION = "estadoRegistroNotificacion";			
	public static final String ENTIDAD_NOTIFICACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_NOTIFICACION_ID_ALERTA = "idAlerta";
	public static final String ENTIDAD_NOTIFICACION_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_NOTIFICACION
            = {ENTIDAD_NOTIFICACION_ID_USUARIO_MODIFICACION, ENTIDAD_NOTIFICACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_NOTIFICACION_ID_PERSONA, ENTIDAD_NOTIFICACION_ESTADO, ENTIDAD_NOTIFICACION_TEXTO_ALERTA, ENTIDAD_NOTIFICACION_PK, ENTIDAD_NOTIFICACION_FECHA_NOTIFICACION, ENTIDAD_NOTIFICACION_ESTADO_REGISTRO, ENTIDAD_NOTIFICACION_ID_ALERTA};

	@Id
    @Column(name="id_notificacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idNotificacion;
    
	@Column(name="texto_alerta")
	private String textoAlerta;		
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_notificacion")
	private Date fechaNotificacion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_alerta")
	private Long idAlerta;		
    
	@Column(name="id_persona")
	private Long idPersona;		

	@ManyToOne
	@JoinColumn(name="id_alerta", referencedColumnName="id_alerta", insertable = false, updatable = false)
    private Alerta alerta;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public Notificacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdNotificacion(){
		return this.idNotificacion;
	}
	
	public void setIdNotificacion(Long idNotificacion){
		this.idNotificacion = idNotificacion;
	}
	
	public String getTextoAlerta(){
		return this.textoAlerta;
	}
	
	public void setTextoAlerta(String textoAlerta){
		this.textoAlerta = textoAlerta;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaNotificacion(){
		return this.fechaNotificacion;
	}
	
	public void setFechaNotificacion(Date fechaNotificacion){
		this.fechaNotificacion = fechaNotificacion;
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
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		

    public Alerta getAlerta(){
		return this.alerta; 
	}
	
	public void setAlerta(Alerta alerta){
		this.alerta = alerta;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_NOTIFICACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idNotificacion);        
        hash = 37 * hash + Objects.hashCode(this.textoAlerta);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaNotificacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Notificacion que se pasa
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
        final Notificacion other = (Notificacion) obj;
        
        if (!Objects.equals(this.idNotificacion, other.idNotificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.textoAlerta, other.textoAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaNotificacion, other.fechaNotificacion)) {
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
        
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

