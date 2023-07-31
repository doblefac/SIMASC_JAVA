package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="DOCUMENTO")
@NamedQuery(name = "Documento.findAll", query = "SELECT p FROM Documento p")
public class Documento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	/**
	 * indica si ell documento es para creacion.
	 */
	@Transient
	private boolean nuevo;
	
	@OneToMany(mappedBy="documentoRecusacion")
    private List<Recusacion> recusacionList;
	
	@OneToMany(mappedBy="documentoAudioFuente")
    private List<Transcripcion> transcripcionDocFuenteList;

	@OneToMany(mappedBy="documentoContestacion")
    private List<ContestacionDemanda> contestacionDemandaList;
	
	//Persona propietaria del documento personal (Ej. cédula, tarjeta profesional, etc)
	@ManyToOne
	@JoinColumn(name="id_persona_documento", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona personaDocumento;
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DOCUMENTO_PK = "idDocumento";
	public static final String ENTIDAD_DOCUMENTO_NOMBRE = "nombre";
	public static final String ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO = "tipoDocumento";
	public static final String ENTIDAD_DOCUMENTO_PUBLICADO = "publicado";
	public static final String ENTIDAD_DOCUMENTO_TIPO_ARCHIVO = "tipoArchivo";
	public static final String ENTIDAD_DOCUMENTO_ESTADO_DIGITALIZACION = "estadoDigitalizacion";
	public static final String ENTIDAD_DOCUMENTO_FECHA_ASIGNACION = "fechaAsignacion";
	public static final String ENTIDAD_DOCUMENTO_FECHA_DIGITALIZACION = "fechaDigitalizacion";
	public static final String ENTIDAD_DOCUMENTO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_DOCUMENTO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_DOCUMENTO_NUMERO_FOLIOS = "numeroFolios";
	public static final String ENTIDAD_DOCUMENTO_RADICADO = "radicado";
	public static final String ENTIDAD_DOCUMENTO_FECHA_RADICACION = "fechaRadicacion";
	public static final String ENTIDAD_DOCUMENTO_CODIGO_GESTOR_DOCUMENTAL = "codigoGestorDocumental";
	public static final String ENTIDAD_DOCUMENTO_URL = "url";
	public static final String ENTIDAD_DOCUMENTO_DURACION = "duracion";
	public static final String ENTIDAD_DOCUMENTO_FECHA_DE_GRABACION = "fechaDeGrabacion";
	public static final String ENTIDAD_DOCUMENTO_NUMERO_DE_PISTA = "numeroDePista";
	public static final String ENTIDAD_DOCUMENTO_VERSION = "version";
	public static final String ENTIDAD_DOCUMENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DOCUMENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DOCUMENTO_ESTADO_REGISTRO_DOCUMENTO = "estadoRegistroDocumento";			
	public static final String ENTIDAD_DOCUMENTO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_DOCUMENTO_ID_CASO = "idCaso";
	public static final String ENTIDAD_DOCUMENTO_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_DOCUMENTO_ID_DIGITALIZADOR = "idDigitalizador";
	public static final String ENTIDAD_DOCUMENTO_ID_CARPETA = "idCarpeta";
	public static final String ENTIDAD_DOCUMENTO_ID_ROL_REMITENTE = "idRolRemitente";
	public static final String ENTIDAD_DOCUMENTO_ID_PERSONA_REMITENTE = "idPersonaRemitente";
	public static final String ENTIDAD_DOCUMENTO_ID_CASO_REMITENTE = "idCasoRemitente";
	public static final String ENTIDAD_DOCUMENTO_ID_PERSONA_DOCUMENTO = "idPersonaDocumento";
	public static final String ENTIDAD_DOCUMENTO_ID_SOLICITUD_SERVICIO = "idSolicitudServicio";
	public static final String ENTIDAD_DOCUMENTO_ID_PETICION = "idPeticion";
	public static final String ENTIDAD_DOCUMENTO_ID_EVENTO_CCB = "idEventoCcb";
	public static final String ENTIDAD_DOCUMENTO_FECHA_CARGUE = "fechaCargue";
	public static final String ENTIDAD_DOCUMENTO_ESTADO = "estado";
	private static final String[] ATRIBUTOS_ENTIDAD_DOCUMENTO
     	= {ENTIDAD_DOCUMENTO_URL, ENTIDAD_DOCUMENTO_ESTADO_REGISTRO, ENTIDAD_DOCUMENTO_ID_ROL_REMITENTE, ENTIDAD_DOCUMENTO_ID_SOLICITUD_SERVICIO, ENTIDAD_DOCUMENTO_PK, ENTIDAD_DOCUMENTO_FECHA_DE_GRABACION, ENTIDAD_DOCUMENTO_PUBLICADO, ENTIDAD_DOCUMENTO_DURACION, ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO, ENTIDAD_DOCUMENTO_RADICADO, ENTIDAD_DOCUMENTO_FECHA_DIGITALIZACION, ENTIDAD_DOCUMENTO_CODIGO_GESTOR_DOCUMENTAL, ENTIDAD_DOCUMENTO_ID_PETICION, ENTIDAD_DOCUMENTO_ID_EVENTO_CCB, ENTIDAD_DOCUMENTO_ID_CARPETA, ENTIDAD_DOCUMENTO_TIPO_ARCHIVO, ENTIDAD_DOCUMENTO_ID_DIGITALIZADOR, ENTIDAD_DOCUMENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DOCUMENTO_OBSERVACIONES, ENTIDAD_DOCUMENTO_VERSION, ENTIDAD_DOCUMENTO_ESTADO_DIGITALIZACION, ENTIDAD_DOCUMENTO_FECHA_RADICACION, ENTIDAD_DOCUMENTO_NOMBRE, ENTIDAD_DOCUMENTO_ID_USUARIO_MODIFICACION, ENTIDAD_DOCUMENTO_ID_CASO_REMITENTE, ENTIDAD_DOCUMENTO_NUMERO_FOLIOS, ENTIDAD_DOCUMENTO_ID_PERSONA_REMITENTE, ENTIDAD_DOCUMENTO_ID_AUDIENCIA, ENTIDAD_DOCUMENTO_ID_PERSONA_DOCUMENTO, ENTIDAD_DOCUMENTO_FECHA_ASIGNACION, ENTIDAD_DOCUMENTO_DESCRIPCION, ENTIDAD_DOCUMENTO_NUMERO_DE_PISTA, ENTIDAD_DOCUMENTO_ID_CASO, ENTIDAD_DOCUMENTO_FECHA_CARGUE, ENTIDAD_DOCUMENTO_ESTADO};

	@Id
    @Column(name="id_documento")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDocumento;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="tipo_documento")
	private String tipoDocumento;		
    
	@Column(name="publicado")
	private boolean publicado;		
    
	@Column(name="tipo_archivo")
	private String tipoArchivo;		
    
	@Column(name="estado_digitalizacion")
	private String estadoDigitalizacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_asignacion")
	private Date fechaAsignacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_digitalizacion")
	private Date fechaDigitalizacion;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="numero_folios")
	private Long numeroFolios;		
    
	@Column(name="radicado")
	private boolean radicado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_radicacion")
	private Date fechaRadicacion;		
    
	@Column(name="codigo_gestor_documental")
	private Long codigoGestorDocumental;		
    
	@Column(name="url")
	private String url;		
    
	@Column(name="duracion")
	private Integer duracion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_grabacion")
	private Date fechaDeGrabacion;		
    
	@Column(name="numero_de_pista")
	private Integer numeroDePista;		
    
	@Column(name="version")
	private Integer version;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="id_digitalizador")
	private Long idDigitalizador;		
    
	@Column(name="id_carpeta")
	private Long idCarpeta;		
    
	@Column(name="id_rol_remitente")
	private Long idRolRemitente;		
    
	@Column(name="id_persona_remitente")
	private Long idPersonaRemitente;		
    
	@Column(name="id_caso_remitente")
	private Long idCasoRemitente;		
    
	@Column(name="id_persona_documento")
	private Long idPersonaDocumento;		
    
	@Column(name="id_solicitud_servicio")
	private Long idSolicitudServicio;		
	
	@Column(name="id_peticion")
	private Long idPeticion;
	
	@Column(name="id_evento_ccb")
	private Long idEventoCcb;
	    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_cargue")
	private Date fechaCargue;
	
	@Column(name="estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_carpeta", referencedColumnName="id_carpeta", insertable = false, updatable = false)
    private Carpeta carpeta;
		
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
	
	@ManyToOne
	@JoinColumn(name="id_evento_ccb", referencedColumnName="id_evento_ccb", insertable = false, updatable = false)
    private EventoCcb eventoCcb;
		
	@ManyToOne
	@JoinColumn(name="id_digitalizador", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="id_peticion", referencedColumnName="id_peticion", insertable = false, updatable = false)
    private Peticion peticion;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_remitente", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_remitente", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_remitente", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@ManyToOne
	@JoinColumn(name="id_solicitud_servicio", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;
		
	@OneToMany(mappedBy="documento")
    private List<Acuse> acuseList;
	@OneToMany(mappedBy="documento")
    private List<Adjunto> adjuntoList;
	@OneToMany(mappedBy="documento")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="documento")
    private List<ContratoConvenio> contratoConvenioList;
	@OneToMany(mappedBy="documento")
    private List<Convenio> convenioList;
	@OneToMany(mappedBy="documento")
    private List<DesarrolloProfesional> desarrolloProfesionalList;
	@OneToMany(mappedBy="documento")
    private List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList;
	@OneToMany(mappedBy="documento")
    private List<EntregaDocumento> entregaDocumentoList;
	@OneToMany(mappedBy="documento")
    private List<GastoTribunal> gastoTribunalList;
	@OneToMany(mappedBy="documento")
    private List<Laudo> laudoList;
	@OneToMany(mappedBy="documento")
    private List<NotificacionDocumento> notificacionDocumentoList;
	@OneToMany(mappedBy="documento")
    private List<Obligacion> obligacionList;
	@OneToMany(mappedBy="documento")
    private List<Pronunciamiento> pronunciamientoList;
	@OneToMany(mappedBy="documento")
    private List<RecursoLaudo> recursoLaudoList;
	@OneToMany(mappedBy="documento")
    private List<ResultadoAudiencia> resultadoAudienciaList;
	@OneToMany(mappedBy="documento")
    private List<SolicitudPrestador> solicitudPrestadorList;;
	@OneToMany(mappedBy="documento")
    private List<SolicitudProrroga> solicitudProrrogaList;
	@OneToMany(mappedBy="documento")
    private List<Inasistencia> inasistenciaList;
	@OneToMany(mappedBy="documento")
    private List<FirmaDocumento> firmaDocumentoList;	
	
	@OneToMany(mappedBy="documento")
    private List<CorrerTraslado> correrTrasladoList;
	
    public Documento(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getTipoDocumento(){
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
		
	public boolean getPublicado(){
		return this.publicado;
	}
	
	public void setPublicado(boolean publicado){
		this.publicado = publicado;
	}
		
	public String getTipoArchivo(){
		return this.tipoArchivo;
	}
	
	public void setTipoArchivo(String tipoArchivo){
		this.tipoArchivo = tipoArchivo;
	}
		
	public String getEstadoDigitalizacion(){
		return this.estadoDigitalizacion;
	}
	
	public void setEstadoDigitalizacion(String estadoDigitalizacion){
		this.estadoDigitalizacion = estadoDigitalizacion;
	}
		
	public Date getFechaAsignacion(){
		return this.fechaAsignacion;
	}
	
	public void setFechaAsignacion(Date fechaAsignacion){
		this.fechaAsignacion = fechaAsignacion;
	}
		
	public Date getFechaDigitalizacion(){
		return this.fechaDigitalizacion;
	}
	
	public void setFechaDigitalizacion(Date fechaDigitalizacion){
		this.fechaDigitalizacion = fechaDigitalizacion;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getNumeroFolios(){
		return this.numeroFolios;
	}
	
	public void setNumeroFolios(Long numeroFolios){
		this.numeroFolios = numeroFolios;
	}
		
	public boolean getRadicado(){
		return this.radicado;
	}
	
	public void setRadicado(boolean radicado){
		this.radicado = radicado;
	}
		
	public Date getFechaRadicacion(){
		return this.fechaRadicacion;
	}
	
	public void setFechaRadicacion(Date fechaRadicacion){
		this.fechaRadicacion = fechaRadicacion;
	}
		
	public Long getCodigoGestorDocumental(){
		return this.codigoGestorDocumental;
	}
	
	public void setCodigoGestorDocumental(Long codigoGestorDocumental){
		this.codigoGestorDocumental = codigoGestorDocumental;
	}
		
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
		
	public Integer getDuracion(){
		return this.duracion;
	}
	
	public void setDuracion(Integer duracion){
		this.duracion = duracion;
	}
		
	public Date getFechaDeGrabacion(){
		return this.fechaDeGrabacion;
	}
	
	public void setFechaDeGrabacion(Date fechaDeGrabacion){
		this.fechaDeGrabacion = fechaDeGrabacion;
	}
		
	public Integer getNumeroDePista(){
		return this.numeroDePista;
	}
	
	public void setNumeroDePista(Integer numeroDePista){
		this.numeroDePista = numeroDePista;
	}
		
	public Integer getVersion(){
		return this.version;
	}
	
	public void setVersion(Integer version){
		this.version = version;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdDigitalizador(){
		return this.idDigitalizador;
	}
	
	public void setIdDigitalizador(Long idDigitalizador){
		this.idDigitalizador = idDigitalizador;
	}
		
	public Long getIdCarpeta(){
		return this.idCarpeta;
	}
	
	public void setIdCarpeta(Long idCarpeta){
		this.idCarpeta = idCarpeta;
	}
		
	public Long getIdRolRemitente(){
		return this.idRolRemitente;
	}
	
	public void setIdRolRemitente(Long idRolRemitente){
		this.idRolRemitente = idRolRemitente;
	}
		
	public Long getIdPersonaRemitente(){
		return this.idPersonaRemitente;
	}
	
	public void setIdPersonaRemitente(Long idPersonaRemitente){
		this.idPersonaRemitente = idPersonaRemitente;
	}
		
	public Long getIdCasoRemitente(){
		return this.idCasoRemitente;
	}
	
	public void setIdCasoRemitente(Long idCasoRemitente){
		this.idCasoRemitente = idCasoRemitente;
	}
		
	public Long getIdPersonaDocumento(){
		return this.idPersonaDocumento;
	}
	
	public void setIdPersonaDocumento(Long idPersonaDocumento){
		this.idPersonaDocumento = idPersonaDocumento;
	}	
	
		
	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
	
	public Long getIdPeticion(){
		return this.idPeticion;
	}
	
	public void setIdPeticion(Long idPeticion){
		this.idPeticion = idPeticion;
	}	
	
	public Long getIdEventoCcb(){
		return this.idEventoCcb;
	}
	
	public void setIdEventoCcb(Long idEventoCcb){
		this.idEventoCcb = idEventoCcb;
	}
	
	public Date getFechaCargue(){
		return this.fechaCargue;
	}
	
	public void setFechaCargue(Date fechaCargue){
		this.fechaCargue = fechaCargue;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
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
			
    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}			
 
			
    public List<ContratoConvenio> getContratoConvenioList(){
		return this.contratoConvenioList;
	}
	
	public void setContratoConvenioList(List<ContratoConvenio> contratoConvenioList){
		this.contratoConvenioList = contratoConvenioList;
	}
			
    public List<Convenio> getConvenioList(){
		return this.convenioList;
	}
	
	public void setConvenioList(List<Convenio> convenioList){
		this.convenioList = convenioList;
	}
			
    public List<DesarrolloProfesional> getDesarrolloProfesionalList(){
		return this.desarrolloProfesionalList;
	}
	
	public void setDesarrolloProfesionalList(List<DesarrolloProfesional> desarrolloProfesionalList){
		this.desarrolloProfesionalList = desarrolloProfesionalList;
	}
			
    public List<DevolucionDocumentoResultado> getDevolucionDocumentoResultadoList(){
		return this.devolucionDocumentoResultadoList;
	}
	
	public void setDevolucionDocumentoResultadoList(List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList){
		this.devolucionDocumentoResultadoList = devolucionDocumentoResultadoList;
	}
			
    public List<EntregaDocumento> getEntregaDocumentoList(){
		return this.entregaDocumentoList;
	}
	
	public void setEntregaDocumentoList(List<EntregaDocumento> entregaDocumentoList){
		this.entregaDocumentoList = entregaDocumentoList;
	}
			
    public List<GastoTribunal> getGastoTribunalList(){
		return this.gastoTribunalList;
	}
	
	public void setGastoTribunalList(List<GastoTribunal> gastoTribunalList){
		this.gastoTribunalList = gastoTribunalList;
	}
			
    public List<Laudo> getLaudoList(){
		return this.laudoList;
	}
	
	public void setLaudoList(List<Laudo> laudoList){
		this.laudoList = laudoList;
	}
			
    public List<NotificacionDocumento> getNotificacionDocumentoList(){
		return this.notificacionDocumentoList;
	}
	
	public void setNotificacionDocumentoList(List<NotificacionDocumento> notificacionDocumentoList){
		this.notificacionDocumentoList = notificacionDocumentoList;
	}
			
    public List<Obligacion> getObligacionList(){
		return this.obligacionList;
	}
	
	public void setObligacionList(List<Obligacion> obligacionList){
		this.obligacionList = obligacionList;
	}
			
    public List<Pronunciamiento> getPronunciamientoList(){
		return this.pronunciamientoList;
	}
	
	public void setPronunciamientoList(List<Pronunciamiento> pronunciamientoList){
		this.pronunciamientoList = pronunciamientoList;
	}
			
    public List<RecursoLaudo> getRecursoLaudoList(){
		return this.recursoLaudoList;
	}
	
	public void setRecursoLaudoList(List<RecursoLaudo> recursoLaudoList){
		this.recursoLaudoList = recursoLaudoList;
	}
			
   		
    public List<ResultadoAudiencia> getResultadoAudienciaList(){
		return this.resultadoAudienciaList;
	}
	
	public void setResultadoAudienciaList(List<ResultadoAudiencia> resultadoAudienciaList){
		this.resultadoAudienciaList = resultadoAudienciaList;
	}	
			
    public List<SolicitudProrroga> getSolicitudProrrogaList(){
		return this.solicitudProrrogaList;
	}
	
	public void setSolicitudProrrogaList(List<SolicitudProrroga> solicitudProrrogaList){
		this.solicitudProrrogaList = solicitudProrrogaList;
	}
			
  		
    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public Carpeta getCarpeta(){
		return this.carpeta; 
	}
	
	public void setCarpeta(Carpeta carpeta){
		this.carpeta = carpeta;
	}
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
	
	public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
	
    public Peticion getPeticion(){
		return this.peticion; 
	}
	
	public void setPeticion(Peticion peticion){
		this.peticion = peticion;
	}
    
	public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}
    public SolicitudServicio getSolicitudServicio(){
		return this.solicitudServicio; 
	}
	
	public void setSolicitudServicio(SolicitudServicio solicitudServicio){
		this.solicitudServicio = solicitudServicio;
	}
	
	public EventoCcb getEventoCcb() {
		return eventoCcb;
	}


	public void setEventoCcb(EventoCcb eventoCcb) {
		this.eventoCcb = eventoCcb;
	}


	public List<SolicitudPrestador> getSolicitudPrestadorList() {
		return solicitudPrestadorList;
	}


	public void setSolicitudPrestadorList(List<SolicitudPrestador> solicitudPrestadorList) {
		this.solicitudPrestadorList = solicitudPrestadorList;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DOCUMENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idDocumento);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 37 * hash + (this.publicado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoArchivo);
        hash = 37 * hash + Objects.hashCode(this.estadoDigitalizacion);
        hash = 37 * hash + Objects.hashCode(this.fechaAsignacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDigitalizacion);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.numeroFolios);
        hash = 37 * hash + (this.radicado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);
        hash = 37 * hash + Objects.hashCode(this.codigoGestorDocumental);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.duracion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeGrabacion);
        hash = 37 * hash + Objects.hashCode(this.numeroDePista);
        hash = 37 * hash + Objects.hashCode(this.version);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idDigitalizador);
        hash = 37 * hash + Objects.hashCode(this.idCarpeta);
        hash = 37 * hash + Objects.hashCode(this.idRolRemitente);
        hash = 37 * hash + Objects.hashCode(this.idPersonaRemitente);
        hash = 37 * hash + Objects.hashCode(this.idCasoRemitente);
        hash = 37 * hash + Objects.hashCode(this.idPersonaDocumento);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);
        hash = 37 * hash + Objects.hashCode(this.idPeticion);
        hash = 37 * hash + Objects.hashCode(this.idEventoCcb);
        hash = 37 * hash + Objects.hashCode(this.fechaCargue);
        hash = 37 * hash + Objects.hashCode(this.estado);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Documento que se pasa
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
        final Documento other = (Documento) obj;
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.publicado, other.publicado)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoArchivo, other.tipoArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoDigitalizacion, other.estadoDigitalizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsignacion, other.fechaAsignacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDigitalizacion, other.fechaDigitalizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroFolios, other.numeroFolios)) {
            return false;
        }
        
        if (!Objects.equals(this.radicado, other.radicado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoGestorDocumental, other.codigoGestorDocumental)) {
            return false;
        }
        
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        
        if (!Objects.equals(this.duracion, other.duracion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeGrabacion, other.fechaDeGrabacion)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDePista, other.numeroDePista)) {
            return false;
        }
        
        if (!Objects.equals(this.version, other.version)) {
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
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idDigitalizador, other.idDigitalizador)) {
            return false;
        }
        
        if (!Objects.equals(this.idCarpeta, other.idCarpeta)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolRemitente, other.idRolRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaRemitente, other.idPersonaRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoRemitente, other.idCasoRemitente)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaDocumento, other.idPersonaDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idPeticion, other.idPeticion)) {
            return false;
        }
        
        if (!Objects.equals(this.idEventoCcb, other.idEventoCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCargue, other.fechaCargue)) {
        	return false;
        }
        
        return Objects.equals(this.estado, other.estado);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
    /**
	 * @return the nuevo
	 */
	public boolean isNuevo() {
		return nuevo;
	}


	/**
	 * @param nuevo the nuevo to set
	 */
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}


	public List<Recusacion> getRecusacionList() {
		return recusacionList;
	}


	public void setRecusacionList(List<Recusacion> recusacionList) {
		this.recusacionList = recusacionList;
	}


	public List<Transcripcion> getTranscripcionDocFuenteList() {
		return transcripcionDocFuenteList;
	}


	public void setTranscripcionDocFuenteList(List<Transcripcion> transcripcionDocFuenteList) {
		this.transcripcionDocFuenteList = transcripcionDocFuenteList;
	}
	
    public List<ContestacionDemanda> getContestacionDemandaList(){
		return this.contestacionDemandaList;
	}
	
	public void setContestacionDemandaList(List<ContestacionDemanda> contestacionDemandaList){
		this.contestacionDemandaList = contestacionDemandaList;
	}


	/**
	 * @return the personaDocumento
	 */
	public Persona getPersonaDocumento() {
		return personaDocumento;
	}

	/**
	 * @param personaDocumento the personaDocumento to set
	 */
	public void setPersonaDocumento(Persona personaDocumento) {
		this.personaDocumento = personaDocumento;
	}

	public List<Inasistencia> getInasistenciaList() {
		return inasistenciaList;
	}

	public void setInasistenciaList(List<Inasistencia> inasistenciaList) {
		this.inasistenciaList = inasistenciaList;
	}


	public List<FirmaDocumento> getFirmaDocumentoList() {
		return firmaDocumentoList;
	}

	public void setFirmaDocumentoList(List<FirmaDocumento> firmaDocumentoList) {
		this.firmaDocumentoList = firmaDocumentoList;
	}


	public List<CorrerTraslado> getCorrerTrasladoList() {
		return correrTrasladoList;
	}


	public void setCorrerTrasladoList(List<CorrerTraslado> correrTrasladoList) {
		this.correrTrasladoList = correrTrasladoList;
	}
	
	
	// protected region metodos adicionales end

} 

