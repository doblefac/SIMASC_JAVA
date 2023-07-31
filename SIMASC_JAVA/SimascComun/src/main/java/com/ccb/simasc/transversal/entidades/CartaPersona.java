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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CARTA_PERSONA")
@NamedQuery(name = "CartaPersona.findAll", query = "SELECT p FROM CartaPersona p")
public class CartaPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CARTA_PERSONA_PK = "idCartaPersona";
	public static final String ENTIDAD_CARTA_PERSONA_ID_PLANTILLA_CARTA = "idPlantillaCarta";
	public static final String ENTIDAD_CARTA_PERSONA_ENVIO_CERTIMAIL = "envioCertimail";
	public static final String ENTIDAD_CARTA_PERSONA_CONTENIDO = "contenido";
	public static final String ENTIDAD_CARTA_PERSONA_ESTADO_CARTA = "estadoCarta";
	public static final String ENTIDAD_CARTA_PERSONA_ID_CASO = "idCaso";
	public static final String ENTIDAD_CARTA_PERSONA_ASUNTO = "asunto";
	public static final String ENTIDAD_CARTA_PERSONA_CORREO_ELECTRONICO = "correoElectronico";
	public static final String ENTIDAD_CARTA_PERSONA_DIRECCION_CORRESPONDENCIA = "direccionCorrespondencia";
	public static final String ENTIDAD_CARTA_PERSONA_CIUDAD_CORRESPONDENCIA = "ciudadCorrespondencia";
	public static final String ENTIDAD_CARTA_PERSONA_TELEFONOS_CONTACTO = "telefonosContacto";
	public static final String ENTIDAD_CARTA_PERSONA_FECHA_ENVIO = "fechaEnvio";
	public static final String ENTIDAD_CARTA_PERSONA_FECHA_GENERACION = "fechaGeneracion";
	public static final String ENTIDAD_CARTA_PERSONA_NUMERO_DE_RADICADO = "numeroDeRadicado";
	public static final String ENTIDAD_CARTA_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CARTA_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CARTA_PERSONA_ESTADO_REGISTRO_CARTAPERSONA = "estadoRegistroCartaPersona";			
	public static final String ENTIDAD_CARTA_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CARTA_PERSONA_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_CARTA_PERSONA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_CARTA_PERSONA_ID_INVITADO = "idInvitado";
	public static final String ENTIDAD_CARTA_PERSONA_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_CARTA_PERSONA_ID_SEDE = "idSede";
	public static final String ENTIDAD_CARTA_PERSONA_NUMERO_GUIA = "numeroGuia";
	public static final String ENTIDAD_CARTA_PERSONA_FECHA_DEVOLUCION = "fechaDevolucion";
	

    private static final String[] ATRIBUTOS_ENTIDAD_CARTA_PERSONA
            = {ENTIDAD_CARTA_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CARTA_PERSONA_ID_INVITADO, ENTIDAD_CARTA_PERSONA_FECHA_GENERACION, ENTIDAD_CARTA_PERSONA_NUMERO_DE_RADICADO, ENTIDAD_CARTA_PERSONA_NUMERO_GUIA, ENTIDAD_CARTA_PERSONA_ESTADO_CARTA, ENTIDAD_CARTA_PERSONA_CIUDAD_CORRESPONDENCIA, ENTIDAD_CARTA_PERSONA_ID_DOCUMENTO, ENTIDAD_CARTA_PERSONA_ID_AUDIENCIA, ENTIDAD_CARTA_PERSONA_ID_CASO, ENTIDAD_CARTA_PERSONA_ASUNTO, ENTIDAD_CARTA_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_CARTA_PERSONA_DIRECCION_CORRESPONDENCIA, ENTIDAD_CARTA_PERSONA_ENVIO_CERTIMAIL, ENTIDAD_CARTA_PERSONA_ID_SEDE, ENTIDAD_CARTA_PERSONA_CONTENIDO, ENTIDAD_CARTA_PERSONA_ID_PLANTILLA_CARTA, ENTIDAD_CARTA_PERSONA_TELEFONOS_CONTACTO, ENTIDAD_CARTA_PERSONA_ESTADO_REGISTRO, ENTIDAD_CARTA_PERSONA_ID_PERSONA, ENTIDAD_CARTA_PERSONA_PK, ENTIDAD_CARTA_PERSONA_CORREO_ELECTRONICO, ENTIDAD_CARTA_PERSONA_FECHA_DEVOLUCION, ENTIDAD_CARTA_PERSONA_FECHA_ENVIO};

	@Id
    @Column(name="id_carta_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCartaPersona;
    
	@Column(name="id_plantilla_carta")
	private Long idPlantillaCarta;		
    
	@Column(name="envio_certimail")
	private boolean envioCertimail;		
    
	@Column(name="contenido")
	private String contenido;		
    
	@Column(name="estado_carta")
	private String estadoCarta;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="asunto")
	private String asunto;		
    
	@Column(name="correo_electronico")
	private String correoElectronico;		
    
	@Column(name="direccion_correspondencia")
	private String direccionCorrespondencia;		
    
	@Column(name="ciudad_correspondencia")
	private String ciudadCorrespondencia;		
    
	@Column(name="telefonos_contacto")
	private String telefonosContacto;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_envio")
	private Date fechaEnvio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_generacion")
	private Date fechaGeneracion;		
    
	@Column(name="numero_de_radicado")
	private Long numeroDeRadicado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_invitado")
	private Long idInvitado;		
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="id_sede")
	private Long idSede;		
    
	@Column(name="numero_guia")
	private String numeroGuia;	
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_devolucion")
	private Date fechaDevolucion;		
	
	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_invitado", referencedColumnName="id_invitado", insertable = false, updatable = false)
    private Invitado invitado;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_plantilla_carta", referencedColumnName="id_plantilla_carta", insertable = false, updatable = false)
    private PlantillaCarta plantillaCarta;
		
	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	@OneToMany(mappedBy="cartaPersona")
    private List<CorreoRolPersonaCaso> correoRolPersonaCasoList;
	@OneToMany(mappedBy="cartaPersona")
    private List<Llamada> llamadaList;
	
	
    public CartaPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCartaPersona(){
		return this.idCartaPersona;
	}
	
	public void setIdCartaPersona(Long idCartaPersona){
		this.idCartaPersona = idCartaPersona;
	}
	
	public Long getIdPlantillaCarta(){
		return this.idPlantillaCarta;
	}
	
	public void setIdPlantillaCarta(Long idPlantillaCarta){
		this.idPlantillaCarta = idPlantillaCarta;
	}
		
	public boolean getEnvioCertimail(){
		return this.envioCertimail;
	}
	
	public void setEnvioCertimail(boolean envioCertimail){
		this.envioCertimail = envioCertimail;
	}
		
	public String getContenido(){
		return this.contenido;
	}
	
	public void setContenido(String contenido){
		this.contenido = contenido;
	}
		
	public String getEstadoCarta(){
		return this.estadoCarta;
	}
	
	public void setEstadoCarta(String estadoCarta){
		this.estadoCarta = estadoCarta;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getAsunto(){
		return this.asunto;
	}
	
	public void setAsunto(String asunto){
		this.asunto = asunto;
	}
		
	public String getCorreoElectronico(){
		return this.correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico){
		this.correoElectronico = correoElectronico;
	}
		
	public String getDireccionCorrespondencia(){
		return this.direccionCorrespondencia;
	}
	
	public void setDireccionCorrespondencia(String direccionCorrespondencia){
		this.direccionCorrespondencia = direccionCorrespondencia;
	}
		
	public String getCiudadCorrespondencia(){
		return this.ciudadCorrespondencia;
	}
	
	public void setCiudadCorrespondencia(String ciudadCorrespondencia){
		this.ciudadCorrespondencia = ciudadCorrespondencia;
	}
		
	public String getTelefonosContacto(){
		return this.telefonosContacto;
	}
	
	public void setTelefonosContacto(String telefonosContacto){
		this.telefonosContacto = telefonosContacto;
	}
		
	public Date getFechaEnvio(){
		return this.fechaEnvio;
	}
	
	public void setFechaEnvio(Date fechaEnvio){
		this.fechaEnvio = fechaEnvio;
	}
		
	public Date getFechaGeneracion(){
		return this.fechaGeneracion;
	}
	
	public void setFechaGeneracion(Date fechaGeneracion){
		this.fechaGeneracion = fechaGeneracion;
	}
		
	public Long getNumeroDeRadicado(){
		return this.numeroDeRadicado;
	}
	
	public void setNumeroDeRadicado(Long numeroDeRadicado){
		this.numeroDeRadicado = numeroDeRadicado;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public String getNumeroGuia(){
		return this.numeroGuia;
	}
	
	public void setNumeroGuia(String numeroGuia){
		this.numeroGuia = numeroGuia;
	}
		

    public List<CorreoRolPersonaCaso> getCorreoRolPersonaCasoList(){
		return this.correoRolPersonaCasoList;
	}
	
	public void setCorreoRolPersonaCasoList(List<CorreoRolPersonaCaso> correoRolPersonaCasoList){
		this.correoRolPersonaCasoList = correoRolPersonaCasoList;
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
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
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
    public PlantillaCarta getPlantillaCarta(){
		return this.plantillaCarta; 
	}
	
	public void setPlantillaCarta(PlantillaCarta plantillaCarta){
		this.plantillaCarta = plantillaCarta;
	}
    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}		

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}



	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CARTA_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCartaPersona);        
        hash = 37 * hash + Objects.hashCode(this.idPlantillaCarta);
        hash = 37 * hash + (this.envioCertimail ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.contenido);
        hash = 37 * hash + Objects.hashCode(this.estadoCarta);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.asunto);
        hash = 37 * hash + Objects.hashCode(this.correoElectronico);
        hash = 37 * hash + Objects.hashCode(this.direccionCorrespondencia);
        hash = 37 * hash + Objects.hashCode(this.ciudadCorrespondencia);
        hash = 37 * hash + Objects.hashCode(this.telefonosContacto);
        hash = 37 * hash + Objects.hashCode(this.fechaEnvio);
        hash = 37 * hash + Objects.hashCode(this.fechaGeneracion);
        hash = 37 * hash + Objects.hashCode(this.numeroDeRadicado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idInvitado);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.numeroGuia);
        hash = 37 * hash + Objects.hashCode(this.fechaDevolucion);  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CartaPersona que se pasa
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
        final CartaPersona other = (CartaPersona) obj;
        
        if (!Objects.equals(this.idCartaPersona, other.idCartaPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idPlantillaCarta, other.idPlantillaCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.envioCertimail, other.envioCertimail)) {
            return false;
        }
        
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoCarta, other.estadoCarta)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        
        if (!Objects.equals(this.correoElectronico, other.correoElectronico)) {
            return false;
        }
        
        if (!Objects.equals(this.direccionCorrespondencia, other.direccionCorrespondencia)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudadCorrespondencia, other.ciudadCorrespondencia)) {
            return false;
        }
        
        if (!Objects.equals(this.telefonosContacto, other.telefonosContacto)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEnvio, other.fechaEnvio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaGeneracion, other.fechaGeneracion)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeRadicado, other.numeroDeRadicado)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDevolucion, other.fechaDevolucion)) {
            return false;
        }        

        return Objects.equals(this.numeroGuia, other.numeroGuia);
                
    }





	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

