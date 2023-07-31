package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PERSONA_SOLICITUD")
@NamedQuery(name = "PersonaSolicitud.findAll", query = "SELECT p FROM PersonaSolicitud p")
public class PersonaSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	
	@OneToMany(mappedBy="apoderadosSolicitudList")
    private List<ApoderadosSolicitud> apoderadosSolicitudList;
	@OneToMany(mappedBy="representadosSolicitudList")
    private List<ApoderadosSolicitud> representadosSolicitudList;


	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PERSONA_SOLICITUD_PK_ID_ROL = "personaSolicitudPK.idRol";
			
	public static final String ENTIDAD_PERSONA_SOLICITUD_PK_ID_PERSONA = "personaSolicitudPK.idPersona";
			
	public static final String ENTIDAD_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO = "personaSolicitudPK.idSolicitudServicio";
	public static final String ENTIDAD_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO_PERSONASOLICITUD = "estadoRegistroPersonaSolicitud";			
	public static final String ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PERSONA_SOLICITUD_ID_PERSONA_APODERADO = "idPersonaApoderado";
	public static final String ENTIDAD_PERSONA_SOLICITUD_ID_SOLICITUD_SERVICIO_APODERADO = "idSolicitudServicioApoderado";
	public static final String ENTIDAD_PERSONA_SOLICITUD_ID_ROL_APODERADO = "idRolApoderado";
    private static final String[] ATRIBUTOS_ENTIDAD_PERSONA_SOLICITUD
            = {ENTIDAD_PERSONA_SOLICITUD_PK_ID_PERSONA, ENTIDAD_PERSONA_SOLICITUD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PERSONA_SOLICITUD_ID_USUARIO_MODIFICACION, ENTIDAD_PERSONA_SOLICITUD_ID_PERSONA_APODERADO, ENTIDAD_PERSONA_SOLICITUD_ESTADO_REGISTRO, ENTIDAD_PERSONA_SOLICITUD_ID_SOLICITUD_SERVICIO_APODERADO, ENTIDAD_PERSONA_SOLICITUD_ID_ROL_APODERADO, ENTIDAD_PERSONA_SOLICITUD_PK_ID_ROL, ENTIDAD_PERSONA_SOLICITUD_PK_ID_SOLICITUD_SERVICIO};

	@EmbeddedId
	private PersonaSolicitudPK personaSolicitudPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona_apoderado")
	private Long idPersonaApoderado;		
    
	@Column(name="id_solicitud_servicio_apoderado")
	private Long idSolicitudServicioApoderado;		
    
	@Column(name="id_rol_apoderado")
	private Long idRolApoderado;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona_apoderado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_solicitud_servicio_apoderado", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol_apoderado", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private PersonaSolicitud personaSolicitud;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	@ManyToOne
	@JoinColumn(name="id_solicitud_servicio", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;
		
	@OneToMany(mappedBy="personaSolicitud")
    private List<PersonaSolicitud> personaSolicitudList;
	
	@OneToMany(mappedBy="personaSolicitud")
    private List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList;
	
	@OneToMany(mappedBy="personaSolicitud")
    private List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList;
	
    public PersonaSolicitud(){
		personaSolicitudPK = new PersonaSolicitudPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaSolicitudPK getPersonaSolicitudPK(){
		return this.personaSolicitudPK;
	}
	
	public void setPersonaSolicitudPK(PersonaSolicitudPK personaSolicitudPK){
		this.personaSolicitudPK   = personaSolicitudPK ;
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
		
	public Long getIdPersonaApoderado(){
		return this.idPersonaApoderado;
	}
	
	public void setIdPersonaApoderado(Long idPersonaApoderado){
		this.idPersonaApoderado = idPersonaApoderado;
	}
		
	public Long getIdSolicitudServicioApoderado(){
		return this.idSolicitudServicioApoderado;
	}
	
	public void setIdSolicitudServicioApoderado(Long idSolicitudServicioApoderado){
		this.idSolicitudServicioApoderado = idSolicitudServicioApoderado;
	}
		
	public Long getIdRolApoderado(){
		return this.idRolApoderado;
	}
	
	public void setIdRolApoderado(Long idRolApoderado){
		this.idRolApoderado = idRolApoderado;
	}
		

    public List<PersonaSolicitud> getPersonaSolicitudList(){
		return this.personaSolicitudList;
	}
	
	public void setPersonaSolicitudList(List<PersonaSolicitud> personaSolicitudList){
		this.personaSolicitudList = personaSolicitudList;
	}
			
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public PersonaSolicitud getPersonaSolicitud(){
		return this.personaSolicitud; 
	}
	
	public void setPersonaSolicitud(PersonaSolicitud personaSolicitud){
		this.personaSolicitud = personaSolicitud;
	}
    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}
    public SolicitudServicio getSolicitudServicio(){
		return this.solicitudServicio; 
	}
	
	public void setSolicitudServicio(SolicitudServicio solicitudServicio){
		this.solicitudServicio = solicitudServicio;
	}
	
	public List<CorreoElectronicoPersonaSolicitud> getCorreoElectronicoPersonaSolicitudList(){
		return this.correoElectronicoPersonaSolicitudList;
	}
		 
	public void setCorreoElectronicoPersonaSolicitudList(List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList){
		this.correoElectronicoPersonaSolicitudList = correoElectronicoPersonaSolicitudList;
	}
	
	public List<UbicacionPersonaSolicitud> getUbicacionPersonaSolicitudList(){
		return this.ubicacionPersonaSolicitudList;
	}
	
	public void setUbicacionPersonaSolicitudList(List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList){
		this.ubicacionPersonaSolicitudList = ubicacionPersonaSolicitudList;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PERSONA_SOLICITUD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.personaSolicitudPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicioApoderado);
        hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaSolicitud que se pasa
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
        final PersonaSolicitud other = (PersonaSolicitud) obj;
        
        if (!Objects.equals(this.personaSolicitudPK, other.personaSolicitudPK)) {
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
        
        if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicioApoderado, other.idSolicitudServicioApoderado)) {
            return false;
        }
        
        return Objects.equals(this.idRolApoderado, other.idRolApoderado);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
    
    public List<ApoderadosSolicitud> getApoderadosSolicitudList(){
    	return this.apoderadosSolicitudList;
   	}
    	public void setApoderadosSolicitudList(List<ApoderadosSolicitud> apoderadosSolicitudList){
    	this.apoderadosSolicitudList = apoderadosSolicitudList;
   	}
    public List<ApoderadosSolicitud> getRepresentadosSolicitudList(){
    	return this.representadosSolicitudList;
   	}
   	public void setRepresentadosSolicitudList(List<ApoderadosSolicitud> representadosSolicitudList){
    	this.representadosSolicitudList = representadosSolicitudList;
   	}


	// protected region metodos adicionales end

} 

