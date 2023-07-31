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
@Table(name="CORREO_ROL_PERSONA_CASO")
@NamedQuery(name = "CorreoRolPersonaCaso.findAll", query = "SELECT p FROM CorreoRolPersonaCaso p")
public class CorreoRolPersonaCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@ManyToOne
	@JoinColumn(name="id_persona_que_recibe", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona personaQueRecibe;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_PK = "idCorreoRolPersonaCaso";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ASUNTO = "asunto";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_CUERPO_CORREO = "cuerpoCorreo";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_FECHA_ENVIO = "fechaEnvio";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_MENSAJE = "mensaje";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_GESTIONADO = "gestionado";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_ROL_RECEPTOR = "idRolReceptor";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_RECEPTOR = "idPersonaReceptor";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CASO_RECEPTOR = "idCasoReceptor";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_CORREO_REMITENTE = "correoRemitente";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_CORREO_RECEPTOR = "correoReceptor";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CARTA_PERSONA = "idCartaPersona";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ESTADO_REGISTRO_CORREOROLPERSONACASO = "estadoRegistroCorreoRolPersonaCaso";			
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_INVITADO = "idInvitado";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_ENVIO = "idPersonaEnvio";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_QUE_RECIBE = "idPersonaQueRecibe";
    private static final String[] ATRIBUTOS_ENTIDAD_CORREO_ROL_PERSONA_CASO
            = {ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CARTA_PERSONA, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_INVITADO, ENTIDAD_CORREO_ROL_PERSONA_CASO_MENSAJE, ENTIDAD_CORREO_ROL_PERSONA_CASO_GESTIONADO, ENTIDAD_CORREO_ROL_PERSONA_CASO_ASUNTO, ENTIDAD_CORREO_ROL_PERSONA_CASO_CORREO_REMITENTE, ENTIDAD_CORREO_ROL_PERSONA_CASO_CORREO_RECEPTOR, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_ROL_RECEPTOR, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_RECEPTOR, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_CASO_RECEPTOR, ENTIDAD_CORREO_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CORREO_ROL_PERSONA_CASO_ESTADO_REGISTRO, ENTIDAD_CORREO_ROL_PERSONA_CASO_FECHA_ENVIO, ENTIDAD_CORREO_ROL_PERSONA_CASO_PK, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_ENVIO, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_CORREO_ROL_PERSONA_CASO_CUERPO_CORREO, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_AUDIENCIA, ENTIDAD_CORREO_ROL_PERSONA_CASO_ID_PERSONA_QUE_RECIBE};

	@Id
    @Column(name="id_correo_rol_persona_caso")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCorreoRolPersonaCaso;
    
	@Column(name="asunto")
	private String asunto;		
    
	@Column(name="cuerpo_correo")
	private String cuerpoCorreo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_envio")
	private Date fechaEnvio;		
    
	@Column(name="mensaje")
	private String mensaje;		
    
	@Column(name="gestionado")
	private boolean gestionado;		
    
	@Column(name="id_rol_receptor")
	private Long idRolReceptor;		
    
	@Column(name="id_persona_receptor")
	private Long idPersonaReceptor;		
    
	@Column(name="id_caso_receptor")
	private Long idCasoReceptor;		
    
	@Column(name="correo_remitente")
	private String correoRemitente;		
    
	@Column(name="correo_receptor")
	private String correoReceptor;		
    
	@Column(name="id_carta_persona")
	private Long idCartaPersona;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_invitado")
	private Long idInvitado;		
    
	@Column(name="id_persona_envio")
	private Long idPersonaEnvio;		
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="id_persona_que_recibe")
	private Long idPersonaQueRecibe;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_carta_persona", referencedColumnName="id_carta_persona", insertable = false, updatable = false)
    private CartaPersona cartaPersona;
		
	@ManyToOne
	@JoinColumn(name="id_invitado", referencedColumnName="id_invitado", insertable = false, updatable = false)
    private Invitado invitado;		
		
	@ManyToOne
	@JoinColumn(name="id_persona_envio", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_receptor", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_receptor", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_receptor", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@OneToMany(mappedBy="correoRolPersonaCaso")
    private List<Acuse> acuseList;
	@OneToMany(mappedBy="correoRolPersonaCaso")
    private List<Adjunto> adjuntoList;
	@OneToMany(mappedBy="correoRolPersonaCaso")
    private List<Llamada> llamadaList;
	
	
    public CorreoRolPersonaCaso(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
	
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getCuerpoCorreo(){
		return this.cuerpoCorreo;
	}
	
	public void setCuerpoCorreo(String cuerpoCorreo){
		this.cuerpoCorreo = cuerpoCorreo;
	}
		
	public Date getFechaEnvio(){
		return this.fechaEnvio;
	}
	
	public void setFechaEnvio(Date fechaEnvio){
		this.fechaEnvio = fechaEnvio;
	}
		
	public String getMensaje(){
		return this.mensaje;
	}
	
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}
		
	public boolean getGestionado(){
		return this.gestionado;
	}
	
	public void setGestionado(boolean gestionado){
		this.gestionado = gestionado;
	}
		
	public Long getIdRolReceptor(){
		return this.idRolReceptor;
	}
	
	public void setIdRolReceptor(Long idRolReceptor){
		this.idRolReceptor = idRolReceptor;
	}
		
	public Long getIdPersonaReceptor(){
		return this.idPersonaReceptor;
	}
	
	public void setIdPersonaReceptor(Long idPersonaReceptor){
		this.idPersonaReceptor = idPersonaReceptor;
	}
		
	public Long getIdCasoReceptor(){
		return this.idCasoReceptor;
	}
	
	public void setIdCasoReceptor(Long idCasoReceptor){
		this.idCasoReceptor = idCasoReceptor;
	}
		
	public String getCorreoRemitente(){
		return this.correoRemitente;
	}
	
	public void setCorreoRemitente(String correoRemitente){
		this.correoRemitente = correoRemitente;
	}
		
	public String getCorreoReceptor(){
		return this.correoReceptor;
	}
	
	public void setCorreoReceptor(String correoReceptor){
		this.correoReceptor = correoReceptor;
	}
		
	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
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
		
	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
		
	public Long getIdPersonaEnvio(){
		return this.idPersonaEnvio;
	}
	
	public void setIdPersonaEnvio(Long idPersonaEnvio){
		this.idPersonaEnvio = idPersonaEnvio;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdPersonaQueRecibe(){
		return this.idPersonaQueRecibe;
	}
	
	public void setIdPersonaQueRecibe(Long idPersonaQueRecibe){
		this.idPersonaQueRecibe = idPersonaQueRecibe;
	}
		

    public List<Acuse> getAcuseList(){
		return this.acuseList;
	}
	
	public void setAcuseList(List<Acuse> acuseList){
		this.acuseList = acuseList;
	}
			
    public List<Adjunto> getAdjuntoList(){
		return this.adjuntoList;
	}
	
	public void setAdjuntoList(List<Adjunto> adjuntoList){
		this.adjuntoList = adjuntoList;
	}
			
    public List<Llamada> getLlamadaList(){
		return this.llamadaList;
	}
	
	public void setLlamadaList(List<Llamada> llamadaList){
		this.llamadaList = llamadaList;
	}
			
    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public CartaPersona getCartaPersona(){
		return this.cartaPersona; 
	}
	
	public void setCartaPersona(CartaPersona cartaPersona){
		this.cartaPersona = cartaPersona;
	}
    public Invitado getInvitado(){
		return this.invitado; 
	}
	
	public void setInvitado(Invitado invitado){
		this.invitado = invitado;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CORREO_ROL_PERSONA_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);        
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.cuerpoCorreo);
        hash = 37 * hash + Objects.hashCode(this.fechaEnvio);
        hash = 37 * hash + Objects.hashCode(this.mensaje);
        hash = 37 * hash + (this.gestionado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idRolReceptor);
        hash = 37 * hash + Objects.hashCode(this.idPersonaReceptor);
        hash = 37 * hash + Objects.hashCode(this.idCasoReceptor);
        hash = 37 * hash + Objects.hashCode(this.correoRemitente);
        hash = 37 * hash + Objects.hashCode(this.correoReceptor);
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idInvitado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaEnvio);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idPersonaQueRecibe);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoRolPersonaCaso que se pasa
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
        final CorreoRolPersonaCaso other = (CorreoRolPersonaCaso) obj;
        
        if (!Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.cuerpoCorreo, other.cuerpoCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEnvio, other.fechaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            return false;
        }
        
        if (!Objects.equals(this.gestionado, other.gestionado)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolReceptor, other.idRolReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaReceptor, other.idPersonaReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoReceptor, other.idCasoReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.correoRemitente, other.correoRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.correoReceptor, other.correoReceptor)) {
            return false;
        }
        
        if (!Objects.equals(this.idCartaPersona, other.idCartaPersona)) {
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
        
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaEnvio, other.idPersonaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        return Objects.equals(this.idPersonaQueRecibe, other.idPersonaQueRecibe);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
    public Persona getPersonaQueRecibe(){
		return this.personaQueRecibe; 
	}
	
	public void setPersonaQueRecibe(Persona personaQueRecibe){
		this.personaQueRecibe = personaQueRecibe;
	}
	// protected region metodos adicionales end

} 

