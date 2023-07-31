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
import javax.persistence.JoinColumns;
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
@Table(name="LLAMADA")
@NamedQuery(name = "Llamada.findAll", query = "SELECT p FROM Llamada p")
public class Llamada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_LLAMADA_PK = "idLlamada";
	public static final String ENTIDAD_LLAMADA_TIPO_LLAMADA = "tipoLlamada";
	public static final String ENTIDAD_LLAMADA_FECHA = "fecha";
	public static final String ENTIDAD_LLAMADA_CONTACTADO = "contactado";
	public static final String ENTIDAD_LLAMADA_PERSONA_QUE_CONTESTA = "personaQueContesta";
	public static final String ENTIDAD_LLAMADA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_LLAMADA_CONFIRMACION = "confirmacion";
	public static final String ENTIDAD_LLAMADA_REENVIO_CORREO = "reenvioCorreo";
	public static final String ENTIDAD_LLAMADA_ID_CORREO_ROL_PERSONA_CASO = "idCorreoRolPersonaCaso";
	public static final String ENTIDAD_LLAMADA_CONFIRMACION_ASISTENCIA = "confirmacionAsistencia";
	public static final String ENTIDAD_LLAMADA_DIO_INFORMACION = "dioInformacion";
	public static final String ENTIDAD_LLAMADA_CUMPLIO = "cumplio";
	public static final String ENTIDAD_LLAMADA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_LLAMADA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_LLAMADA_ESTADO_REGISTRO_LLAMADA = "estadoRegistroLlamada";			
	public static final String ENTIDAD_LLAMADA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_LLAMADA_USUARIO_LLAMADA = "usuarioLlamada";
	public static final String ENTIDAD_LLAMADA_ID_ROL = "idRol";
	public static final String ENTIDAD_LLAMADA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_LLAMADA_ID_CASO = "idCaso";
	public static final String ENTIDAD_LLAMADA_ID_TELEFONO = "idTelefono";
	public static final String ENTIDAD_LLAMADA_ID_CARTA_PERSONA = "idCartaPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_LLAMADA
            = {ENTIDAD_LLAMADA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_LLAMADA_ID_PERSONA, ENTIDAD_LLAMADA_ID_CARTA_PERSONA, ENTIDAD_LLAMADA_DIO_INFORMACION, ENTIDAD_LLAMADA_ID_CASO, ENTIDAD_LLAMADA_CONFIRMACION, ENTIDAD_LLAMADA_FECHA, ENTIDAD_LLAMADA_REENVIO_CORREO, ENTIDAD_LLAMADA_CONTACTADO, ENTIDAD_LLAMADA_PK, ENTIDAD_LLAMADA_ID_CORREO_ROL_PERSONA_CASO, ENTIDAD_LLAMADA_ID_USUARIO_MODIFICACION, ENTIDAD_LLAMADA_PERSONA_QUE_CONTESTA, ENTIDAD_LLAMADA_OBSERVACIONES, ENTIDAD_LLAMADA_TIPO_LLAMADA, ENTIDAD_LLAMADA_CUMPLIO, ENTIDAD_LLAMADA_ID_TELEFONO, ENTIDAD_LLAMADA_CONFIRMACION_ASISTENCIA, ENTIDAD_LLAMADA_USUARIO_LLAMADA, ENTIDAD_LLAMADA_ESTADO_REGISTRO, ENTIDAD_LLAMADA_ID_ROL};

	@Id
    @Column(name="id_llamada")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idLlamada;
    
	@Column(name="tipo_llamada")
	private String tipoLlamada;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="contactado")
	private boolean contactado;		
    
	@Column(name="persona_que_contesta")
	private String personaQueContesta;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="confirmacion")
	private boolean confirmacion;		
    
	@Column(name="reenvio_correo")
	private boolean reenvioCorreo;		
    
	@Column(name="id_correo_rol_persona_caso")
	private Long idCorreoRolPersonaCaso;		
    
	@Column(name="confirmacion_asistencia")
	private boolean confirmacionAsistencia;		
    
	@Column(name="dio_informacion")
	private boolean dioInformacion;		
    
	@Column(name="cumplio")
	private String cumplio;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="usuario_llamada")
	private String usuarioLlamada;		
    
	@Column(name="id_rol")
	private Long idRol;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_telefono")
	private Long idTelefono;		
    
	@Column(name="id_carta_persona")
	private Long idCartaPersona;		

	@ManyToOne
	@JoinColumn(name="id_carta_persona", referencedColumnName="id_carta_persona", insertable = false, updatable = false)
    private CartaPersona cartaPersona;
		
	@ManyToOne
	@JoinColumn(name="id_correo_rol_persona_caso", referencedColumnName="id_correo_rol_persona_caso", insertable = false, updatable = false)
    private CorreoRolPersonaCaso correoRolPersonaCaso;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@ManyToOne
	@JoinColumn(name="id_telefono", referencedColumnName="id_telefono", insertable = false, updatable = false)
    private Telefono telefono;
		
	@ManyToOne
	@JoinColumn(name="usuario_llamada", referencedColumnName="usuario_login", insertable = false, updatable = false)
    private Usuario usuario;
		
	
	
    public Llamada(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdLlamada(){
		return this.idLlamada;
	}
	
	public void setIdLlamada(Long idLlamada){
		this.idLlamada = idLlamada;
	}
	
	public String getTipoLlamada(){
		return this.tipoLlamada;
	}
	
	public void setTipoLlamada(String tipoLlamada){
		this.tipoLlamada = tipoLlamada;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getContactado(){
		return this.contactado;
	}
	
	public void setContactado(boolean contactado){
		this.contactado = contactado;
	}
		
	public String getPersonaQueContesta(){
		return this.personaQueContesta;
	}
	
	public void setPersonaQueContesta(String personaQueContesta){
		this.personaQueContesta = personaQueContesta;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public boolean getConfirmacion(){
		return this.confirmacion;
	}
	
	public void setConfirmacion(boolean confirmacion){
		this.confirmacion = confirmacion;
	}
		
	public boolean getReenvioCorreo(){
		return this.reenvioCorreo;
	}
	
	public void setReenvioCorreo(boolean reenvioCorreo){
		this.reenvioCorreo = reenvioCorreo;
	}
		
	public Long getIdCorreoRolPersonaCaso(){
		return this.idCorreoRolPersonaCaso;
	}
	
	public void setIdCorreoRolPersonaCaso(Long idCorreoRolPersonaCaso){
		this.idCorreoRolPersonaCaso = idCorreoRolPersonaCaso;
	}
		
	public boolean getConfirmacionAsistencia(){
		return this.confirmacionAsistencia;
	}
	
	public void setConfirmacionAsistencia(boolean confirmacionAsistencia){
		this.confirmacionAsistencia = confirmacionAsistencia;
	}
		
	public boolean getDioInformacion(){
		return this.dioInformacion;
	}
	
	public void setDioInformacion(boolean dioInformacion){
		this.dioInformacion = dioInformacion;
	}
		
	public String getCumplio(){
		return this.cumplio;
	}
	
	public void setCumplio(String cumplio){
		this.cumplio = cumplio;
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
		
	public String getUsuarioLlamada(){
		return this.usuarioLlamada;
	}
	
	public void setUsuarioLlamada(String usuarioLlamada){
		this.usuarioLlamada = usuarioLlamada;
	}
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdTelefono(){
		return this.idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono){
		this.idTelefono = idTelefono;
	}
		
	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
	}
		

    public CartaPersona getCartaPersona(){
		return this.cartaPersona; 
	}
	
	public void setCartaPersona(CartaPersona cartaPersona){
		this.cartaPersona = cartaPersona;
	}
    public CorreoRolPersonaCaso getCorreoRolPersonaCaso(){
		return this.correoRolPersonaCaso; 
	}
	
	public void setCorreoRolPersonaCaso(CorreoRolPersonaCaso correoRolPersonaCaso){
		this.correoRolPersonaCaso = correoRolPersonaCaso;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}
    public Telefono getTelefono(){
		return this.telefono; 
	}
	
	public void setTelefono(Telefono telefono){
		this.telefono = telefono;
	}
    public Usuario getUsuario(){
		return this.usuario; 
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_LLAMADA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idLlamada);        
        hash = 37 * hash + Objects.hashCode(this.tipoLlamada);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.contactado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.personaQueContesta);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + (this.confirmacion ? 0 : 1);
        hash = 37 * hash + (this.reenvioCorreo ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idCorreoRolPersonaCaso);
        hash = 37 * hash + (this.confirmacionAsistencia ? 0 : 1);
        hash = 37 * hash + (this.dioInformacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.cumplio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.usuarioLlamada);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idTelefono);
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Llamada que se pasa
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
        final Llamada other = (Llamada) obj;
        
        if (!Objects.equals(this.idLlamada, other.idLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoLlamada, other.tipoLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.contactado, other.contactado)) {
            return false;
        }
        
        if (!Objects.equals(this.personaQueContesta, other.personaQueContesta)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacion, other.confirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.reenvioCorreo, other.reenvioCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.idCorreoRolPersonaCaso, other.idCorreoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacionAsistencia, other.confirmacionAsistencia)) {
            return false;
        }
        
        if (!Objects.equals(this.dioInformacion, other.dioInformacion)) {
            return false;
        }
        
        if (!Objects.equals(this.cumplio, other.cumplio)) {
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
        
        if (!Objects.equals(this.usuarioLlamada, other.usuarioLlamada)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idTelefono, other.idTelefono)) {
            return false;
        }
        
        return Objects.equals(this.idCartaPersona, other.idCartaPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

