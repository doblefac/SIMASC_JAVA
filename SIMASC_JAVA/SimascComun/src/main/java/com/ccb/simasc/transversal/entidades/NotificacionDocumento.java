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
@Table(name="NOTIFICACION_DOCUMENTO")
@NamedQuery(name = "NotificacionDocumento.findAll", query = "SELECT p FROM NotificacionDocumento p")
public class NotificacionDocumento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_PK = "idNotificacionDocumento";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_TIPO_NOTIFICACION = "tipoNotificacion";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_FIJACION = "fechaFijacion";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_NORMA = "norma";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ASUNTO = "asunto";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_TERMINO = "termino";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_INICIO = "fechaInicio";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_FIN = "fechaFin";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_PROVIDENCIA = "providencia";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ESTADO_REGISTRO_NOTIFICACIONDOCUMENTO = "estadoRegistroNotificacionDocumento";			
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_NOTIFICACION_DOCUMENTO_ID_CASO = "idCaso";
    private static final String[] ATRIBUTOS_ENTIDAD_NOTIFICACION_DOCUMENTO
            = {ENTIDAD_NOTIFICACION_DOCUMENTO_PROVIDENCIA, ENTIDAD_NOTIFICACION_DOCUMENTO_ASUNTO, ENTIDAD_NOTIFICACION_DOCUMENTO_NORMA, ENTIDAD_NOTIFICACION_DOCUMENTO_PK, ENTIDAD_NOTIFICACION_DOCUMENTO_ID_USUARIO_MODIFICACION, ENTIDAD_NOTIFICACION_DOCUMENTO_ID_DOCUMENTO, ENTIDAD_NOTIFICACION_DOCUMENTO_TERMINO, ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_INICIO, ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_FIN, ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_NOTIFICACION_DOCUMENTO_TIPO_NOTIFICACION, ENTIDAD_NOTIFICACION_DOCUMENTO_FECHA_FIJACION, ENTIDAD_NOTIFICACION_DOCUMENTO_ESTADO_REGISTRO,ENTIDAD_NOTIFICACION_DOCUMENTO_ID_CASO};

	@Id
    @Column(name="id_notificacion_documento")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idNotificacionDocumento;
    
	@Column(name="tipo_notificacion")
	private String tipoNotificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fijacion")
	private Date fechaFijacion;		
    
	@Column(name="norma")
	private String norma;		
    
	@Column(name="asunto")
	private String asunto;		
    
	@Column(name="termino")
	private String termino;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;		
    
	@Column(name="providencia")
	private String providencia;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
	
	@Column(name="id_caso")
	private Long idCaso;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	
	
    public NotificacionDocumento(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdNotificacionDocumento(){
		return this.idNotificacionDocumento;
	}
	
	public void setIdNotificacionDocumento(Long idNotificacionDocumento){
		this.idNotificacionDocumento = idNotificacionDocumento;
	}
	
	public String getTipoNotificacion(){
		return this.tipoNotificacion;
	}
	
	public void setTipoNotificacion(String tipoNotificacion){
		this.tipoNotificacion = tipoNotificacion;
	}
		
	public Date getFechaFijacion(){
		return this.fechaFijacion;
	}
	
	public void setFechaFijacion(Date fechaFijacion){
		this.fechaFijacion = fechaFijacion;
	}
		
	public String getNorma(){
		return this.norma;
	}
	
	public void setNorma(String norma){
		this.norma = norma;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getTermino(){
		return this.termino;
	}
	
	public void setTermino(String termino){
		this.termino = termino;
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
		
	public String getProvidencia(){
		return this.providencia;
	}
	
	public void setProvidencia(String providencia){
		this.providencia = providencia;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}

	public Long getIdCaso() {
		return idCaso;
	}


	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}


	public Caso getCaso() {
		return caso;
	}


	public void setCaso(Caso caso) {
		this.caso = caso;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_NOTIFICACION_DOCUMENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idNotificacionDocumento);        
        hash = 37 * hash + Objects.hashCode(this.tipoNotificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaFijacion);
        hash = 37 * hash + Objects.hashCode(this.norma);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.termino);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.providencia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NotificacionDocumento que se pasa
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
        final NotificacionDocumento other = (NotificacionDocumento) obj;
        
        if (!Objects.equals(this.idNotificacionDocumento, other.idNotificacionDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoNotificacion, other.tipoNotificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFijacion, other.fechaFijacion)) {
            return false;
        }
        
        if (!Objects.equals(this.norma, other.norma)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.termino, other.termino)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.providencia, other.providencia)) {
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
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

