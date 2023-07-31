package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="APODERADOS_SOLICITUD")
@NamedQuery(name = "ApoderadosSolicitud.findAll", query = "SELECT p FROM ApoderadosSolicitud p")
public class ApoderadosSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_PERSONA_APODERADO = "apoderadosSolicitudPK.idPersonaApoderado";
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_SOLICITUD_SERVICIO_APODERADO = "apoderadosSolicitudPK.idSolicitudServicioApoderado";
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_ROL_APODERADO = "apoderadosSolicitudPK.idRolApoderado";
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_PERSONA_REPRESENTADO = "apoderadosSolicitudPK.idPersonaRepresentado";
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_SOLICITUD_SERVICIO_REPRESENTADO = "apoderadosSolicitudPK.idSolicitudServicioRepresentado";
			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_PK_ID_ROL_REPRESENTADO = "apoderadosSolicitudPK.idRolRepresentado";
	public static final String ENTIDAD_APODERADOS_SOLICITUD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_APODERADOS_SOLICITUD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_APODERADOS_SOLICITUD_ESTADO_REGISTRO_APODERADOSSOLICITUD = "estadoRegistroApoderadosSolicitud";			
	public static final String ENTIDAD_APODERADOS_SOLICITUD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_APODERADOS_SOLICITUD
            = {ENTIDAD_APODERADOS_SOLICITUD_ID_USUARIO_MODIFICACION, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_SOLICITUD_SERVICIO_REPRESENTADO, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_PERSONA_REPRESENTADO, ENTIDAD_APODERADOS_SOLICITUD_ESTADO_REGISTRO, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_PERSONA_APODERADO, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_SOLICITUD_SERVICIO_APODERADO, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_ROL_REPRESENTADO, ENTIDAD_APODERADOS_SOLICITUD_PK_ID_ROL_APODERADO, ENTIDAD_APODERADOS_SOLICITUD_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private ApoderadosSolicitudPK apoderadosSolicitudPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona_apoderado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_solicitud_servicio_apoderado", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol_apoderado", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private PersonaSolicitud apoderadosSolicitudList;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona_representado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_solicitud_servicio_representado", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol_representado", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private PersonaSolicitud representadosSolicitudList;
		
	
	
    public ApoderadosSolicitud(){
		apoderadosSolicitudPK = new ApoderadosSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ApoderadosSolicitudPK getApoderadosSolicitudPK(){
		return this.apoderadosSolicitudPK;
	}
	
	public void setApoderadosSolicitudPK(ApoderadosSolicitudPK apoderadosSolicitudPK){
		this.apoderadosSolicitudPK   = apoderadosSolicitudPK ;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_APODERADOS_SOLICITUD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.apoderadosSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ApoderadosSolicitud que se pasa
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
        final ApoderadosSolicitud other = (ApoderadosSolicitud) obj;
        
        if (!Objects.equals(this.apoderadosSolicitudPK, other.apoderadosSolicitudPK)) {
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


	/**
	 * @return the apoderadosSolicitudList
	 */
	public PersonaSolicitud getApoderadosSolicitudList() {
		return apoderadosSolicitudList;
	}


	/**
	 * @param apoderadosSolicitudList the apoderadosSolicitudList to set
	 */
	public void setApoderadosSolicitudList(PersonaSolicitud apoderadosSolicitudList) {
		this.apoderadosSolicitudList = apoderadosSolicitudList;
	}


	/**
	 * @return the representadosSolicitudList
	 */
	public PersonaSolicitud getRepresentadosSolicitudList() {
		return representadosSolicitudList;
	}


	/**
	 * @param representadosSolicitudList the representadosSolicitudList to set
	 */
	public void setRepresentadosSolicitudList(PersonaSolicitud representadosSolicitudList) {
		this.representadosSolicitudList = representadosSolicitudList;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

