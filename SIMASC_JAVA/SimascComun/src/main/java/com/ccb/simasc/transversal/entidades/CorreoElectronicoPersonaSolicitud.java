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
@Table(name="CORREO_ELECTRONICO_PERSONA_SOLICITUD")
@NamedQuery(name = "CorreoElectronicoPersonaSolicitud.findAll", query = "SELECT p FROM CorreoElectronicoPersonaSolicitud p")
public class CorreoElectronicoPersonaSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_ROL = "correoElectronicoPersonaSolicitudPK.idRol";
			
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_PERSONA = "correoElectronicoPersonaSolicitudPK.idPersona";
			
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO = "correoElectronicoPersonaSolicitudPK.idSolicitudServicio";
			
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_CORREO = "correoElectronicoPersonaSolicitudPK.idCorreo";
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD
            = {ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_PERSONA, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_CORREO, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_PK_ID_ROL, ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD_ESTADO_REGISTRO};

	@EmbeddedId
	private CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
	
	@Column(name="estado_registro")
	private String estadoRegistro;

	@ManyToOne
	@JoinColumn(name="id_correo", referencedColumnName="id_correo", insertable = false, updatable = false)
    private CorreoElectronico correoElectronico;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_solicitud_servicio", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private PersonaSolicitud personaSolicitud;
		
	
	
    public CorreoElectronicoPersonaSolicitud(){
		correoElectronicoPersonaSolicitudPK = new CorreoElectronicoPersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CorreoElectronicoPersonaSolicitudPK getCorreoElectronicoPersonaSolicitudPK(){
		return this.correoElectronicoPersonaSolicitudPK;
	}
	
	public void setCorreoElectronicoPersonaSolicitudPK(CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK){
		this.correoElectronicoPersonaSolicitudPK   = correoElectronicoPersonaSolicitudPK ;
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
		

    public CorreoElectronico getCorreoElectronico(){
		return this.correoElectronico; 
	}
	
	public void setCorreoElectronico(CorreoElectronico correoElectronico){
		this.correoElectronico = correoElectronico;
	}
    public PersonaSolicitud getPersonaSolicitud(){
		return this.personaSolicitud; 
	}
	
	public void setPersonaSolicitud(PersonaSolicitud personaSolicitud){
		this.personaSolicitud = personaSolicitud;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CORREO_ELECTRONICO_PERSONA_SOLICITUD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.correoElectronicoPersonaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoElectronicoPersonaSolicitud que se pasa
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
        final CorreoElectronicoPersonaSolicitud other = (CorreoElectronicoPersonaSolicitud) obj;
        
        if (!Objects.equals(this.correoElectronicoPersonaSolicitudPK, other.correoElectronicoPersonaSolicitudPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

