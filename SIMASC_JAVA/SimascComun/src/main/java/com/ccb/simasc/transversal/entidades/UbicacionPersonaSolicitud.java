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
@Table(name="UBICACION_PERSONA_SOLICITUD")
@NamedQuery(name = "UbicacionPersonaSolicitud.findAll", query = "SELECT p FROM UbicacionPersonaSolicitud p")
public class UbicacionPersonaSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_ROL = "ubicacionPersonaSolicitudPK.idRol";
			
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_PERSONA = "ubicacionPersonaSolicitudPK.idPersona";
			
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO = "ubicacionPersonaSolicitudPK.idSolicitudServicio";
			
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_UBICACION = "ubicacionPersonaSolicitudPK.idUbicacion";
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_UBICACION_PERSONA_SOLICITUD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_UBICACION_PERSONA_SOLICITUD
            = {ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_ROL, ENTIDAD_UBICACION_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO, ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_UBICACION, ENTIDAD_UBICACION_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION, ENTIDAD_UBICACION_PERSONA_SOLICITUD_PK_ID_PERSONA, ENTIDAD_UBICACION_PERSONA_SOLICITUD_ESTADO_REGISTRO};

	@EmbeddedId
	private UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;
	
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_solicitud_servicio", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private PersonaSolicitud personaSolicitud;
		
	@ManyToOne
	@JoinColumn(name="id_ubicacion", referencedColumnName="id_ubicacion", insertable = false, updatable = false)
    private Ubicacion ubicacion;
		
	
	
    public UbicacionPersonaSolicitud(){
		ubicacionPersonaSolicitudPK = new UbicacionPersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public UbicacionPersonaSolicitudPK getUbicacionPersonaSolicitudPK(){
		return this.ubicacionPersonaSolicitudPK;
	}
	
	public void setUbicacionPersonaSolicitudPK(UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK){
		this.ubicacionPersonaSolicitudPK   = ubicacionPersonaSolicitudPK ;
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
		

    public PersonaSolicitud getPersonaSolicitud(){
		return this.personaSolicitud; 
	}
	
	public void setPersonaSolicitud(PersonaSolicitud personaSolicitud){
		this.personaSolicitud = personaSolicitud;
	}
    public Ubicacion getUbicacion(){
		return this.ubicacion; 
	}
	
	public void setUbicacion(Ubicacion ubicacion){
		this.ubicacion = ubicacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_UBICACION_PERSONA_SOLICITUD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.ubicacionPersonaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionPersonaSolicitud que se pasa
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
        final UbicacionPersonaSolicitud other = (UbicacionPersonaSolicitud) obj;
        
        if (!Objects.equals(this.ubicacionPersonaSolicitudPK, other.ubicacionPersonaSolicitudPK)) {
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

