package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="SOLICITUD_PRESTADOR")
@NamedQuery(name = "SolicitudPrestador.findAll", query = "SELECT p FROM SolicitudPrestador p")
public class SolicitudPrestador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Transient
	private List<Long> idCentros;

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_PK = "idSolicitud";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_SOLICITUD = "estadoSolicitud";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_TIPO = "tipo";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_FECHA_SOLICITUD = "fechaSolicitud";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_REGISTRO_SOLICITUDPRESTADOR = "estadoRegistroSolicitudPrestador";			
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ID_ROL_PERSONA = "idRolPersona";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_SOLICITUD_PRESTADOR_DESCRIPCION_SOLICITUD = "descripcionSolicitud";
    private static final String[] ATRIBUTOS_ENTIDAD_SOLICITUD_PRESTADOR
            = {ENTIDAD_SOLICITUD_PRESTADOR_FECHA_SOLICITUD, ENTIDAD_SOLICITUD_PRESTADOR_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_SOLICITUD, ENTIDAD_SOLICITUD_PRESTADOR_ID_ROL_PERSONA, ENTIDAD_SOLICITUD_PRESTADOR_PK, ENTIDAD_SOLICITUD_PRESTADOR_DESCRIPCION_SOLICITUD, ENTIDAD_SOLICITUD_PRESTADOR_ESTADO_REGISTRO, ENTIDAD_SOLICITUD_PRESTADOR_TIPO, ENTIDAD_SOLICITUD_PRESTADOR_OBSERVACIONES, ENTIDAD_SOLICITUD_PRESTADOR_ID_DOCUMENTO, ENTIDAD_SOLICITUD_PRESTADOR_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_solicitud")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idSolicitud;
    
	@Column(name="estado_solicitud")
	private String estadoSolicitud;		
    
	@Column(name="tipo")
	private String tipo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_solicitud")
	private Date fechaSolicitud;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_rol_persona")
	private Long idRolPersona;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="descripcion_solicitud")
	private String descripcionSolicitud;		

	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_rol_persona", referencedColumnName="id_rol_persona", insertable = false, updatable = false)
    private RolPersona rolPersona;
		
	
	
    public SolicitudPrestador(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSolicitud(){
		return this.idSolicitud;
	}
	
	public void setIdSolicitud(Long idSolicitud){
		this.idSolicitud = idSolicitud;
	}
	
	public String getEstadoSolicitud(){
		return this.estadoSolicitud;
	}
	
	public void setEstadoSolicitud(String estadoSolicitud){
		this.estadoSolicitud = estadoSolicitud;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFechaSolicitud(){
		return this.fechaSolicitud;
	}
	
	public void setFechaSolicitud(Date fechaSolicitud){
		this.fechaSolicitud = fechaSolicitud;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public Long getIdRolPersona(){
		return this.idRolPersona;
	}
	
	public void setIdRolPersona(Long idRolPersona){
		this.idRolPersona = idRolPersona;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	public String getDescripcionSolicitud(){
		return this.descripcionSolicitud;
	}
	
	public void setDescripcionSolicitud(String descripcionSolicitud){
		this.descripcionSolicitud = descripcionSolicitud;
	}
		

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
    public RolPersona getRolPersona(){
		return this.rolPersona; 
	}
	
	public void setRolPersona(RolPersona rolPersona){
		this.rolPersona = rolPersona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SOLICITUD_PRESTADOR) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitud);        
        hash = 37 * hash + Objects.hashCode(this.estadoSolicitud);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRolPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.descripcionSolicitud);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudPrestador que se pasa
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
        final SolicitudPrestador other = (SolicitudPrestador) obj;
        
        if (!Objects.equals(this.idSolicitud, other.idSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoSolicitud, other.estadoSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaSolicitud, other.fechaSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.idRolPersona, other.idRolPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.descripcionSolicitud, other.descripcionSolicitud);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
    
	public List<Long> getIdCentros() {
		return idCentros;
	}


	public void setIdCentros(List<Long> idCentros) {
		this.idCentros = idCentros;
	}

	// protected region metodos adicionales end

} 

