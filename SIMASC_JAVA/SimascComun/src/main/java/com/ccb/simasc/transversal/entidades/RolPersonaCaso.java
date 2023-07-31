package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.Transient;

import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ROL_PERSONA_CASO")
@NamedQuery(name = "RolPersonaCaso.findAll", query = "SELECT p FROM RolPersonaCaso p")
public class RolPersonaCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@OneToMany(mappedBy="arbitroPrincipalReemplazado")
    private List<RolPersonaCaso> arbitroSuplenteReemplazoList;
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_principal_reemplazado", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_principal_reemplazado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_principal_reemplazado", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso arbitroPrincipalReemplazado;
	
	@Transient
	private String tipoNombramientoLabel;
	
	@Transient
	private String nombramientoPorSorteo;
	
	@Transient
	private Long idServicio;
	
	@OneToMany(mappedBy="rolPersonaCasoRepresentado")
    private List<Apoderados> representadosList;
	@OneToMany(mappedBy="rolPersonaCasoApoderado")
    private List<Apoderados> apoderadosList;	
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
				


	public static final String ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA = "rolPersonaCasoPK.idPersona";
			
	public static final String ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO = "rolPersonaCasoPK.idCaso";
			
	public static final String ENTIDAD_ROL_PERSONA_CASO_PK_ID_ROL = "rolPersonaCasoPK.idRol";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ESTADO = "estado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_MOTIVO_INACTIVACION = "motivoInactivacion";
	public static final String ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO = "tipoNombramiento";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ORDEN_DE_ASIGNACION = "ordenDeAsignacion";
	public static final String ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO = "metodoNombramiento";
	public static final String ENTIDAD_ROL_PERSONA_CASO_TIPO_SUPLENCIA = "tipoSuplencia";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ES_PRESIDENTE = "esPresidente";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO_ROLPERSONACASO = "estadoRegistroRolPersonaCaso";			
	public static final String ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_ROL_APODERADO = "idRolApoderado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_APODERADO = "idPersonaApoderado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_CASO_APODERADO = "idCasoApoderado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_SORTEO = "idSorteo";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_PRONUNCIAMIENTO = "idPronunciamiento";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_ROL_PRINCIPAL_REEMPLAZADO = "idRolPrincipalReemplazado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO = "idPersonaPrincipalReemplazado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_CASO_PRINCIPAL_REEMPLAZADO = "idCasoPrincipalReemplazado";
	public static final String ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO = "idPersonaTercero";
    private static final String[] ATRIBUTOS_ENTIDAD_ROL_PERSONA_CASO
            = {ENTIDAD_ROL_PERSONA_CASO_MOTIVO_INACTIVACION, ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO, ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, ENTIDAD_ROL_PERSONA_CASO_ID_ROL_APODERADO, ENTIDAD_ROL_PERSONA_CASO_TIPO_SUPLENCIA, ENTIDAD_ROL_PERSONA_CASO_ID_PRONUNCIAMIENTO, ENTIDAD_ROL_PERSONA_CASO_ID_ROL_PRINCIPAL_REEMPLAZADO, ENTIDAD_ROL_PERSONA_CASO_PK_ID_PERSONA, ENTIDAD_ROL_PERSONA_CASO_ESTADO, ENTIDAD_ROL_PERSONA_CASO_ID_CASO_APODERADO, ENTIDAD_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_APODERADO, ENTIDAD_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO, ENTIDAD_ROL_PERSONA_CASO_ORDEN_DE_ASIGNACION, ENTIDAD_ROL_PERSONA_CASO_PK_ID_CASO, ENTIDAD_ROL_PERSONA_CASO_ID_CASO_PRINCIPAL_REEMPLAZADO, ENTIDAD_ROL_PERSONA_CASO_PK_ID_ROL, ENTIDAD_ROL_PERSONA_CASO_ES_PRESIDENTE, ENTIDAD_ROL_PERSONA_CASO_ID_SORTEO, ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO};

	@EmbeddedId
	private RolPersonaCasoPK rolPersonaCasoPK;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="motivo_inactivacion")
	private String motivoInactivacion;		
    
	@Column(name="tipo_nombramiento")
	private String tipoNombramiento;		
    
	@Column(name="orden_de_asignacion")
	private Integer ordenDeAsignacion;		
    
	@Column(name="metodo_nombramiento")
	private String metodoNombramiento;		
    
	@Column(name="tipo_suplencia")
	private String tipoSuplencia;		
    
	@Column(name="es_presidente")
	private boolean esPresidente;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_rol_apoderado")
	private Long idRolApoderado;		
    
	@Column(name="id_persona_apoderado")
	private Long idPersonaApoderado;		
    
	@Column(name="id_caso_apoderado")
	private Long idCasoApoderado;		
    
	@Column(name="id_sorteo")
	private Long idSorteo;		
    
	@Column(name="id_pronunciamiento")
	private Long idPronunciamiento;		
    
	@Column(name="id_rol_principal_reemplazado")
	private Long idRolPrincipalReemplazado;		
    
	@Column(name="id_persona_principal_reemplazado")
	private Long idPersonaPrincipalReemplazado;		
    
	@Column(name="id_caso_principal_reemplazado")
	private Long idCasoPrincipalReemplazado;		
    
	@Column(name="id_persona_tercero")
	private Long idPersonaTercero;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	public Long getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}


	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_persona_tercero", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona tercero;
		
	@ManyToOne
	@JoinColumn(name="id_pronunciamiento", referencedColumnName="id_pronunciamiento", insertable = false, updatable = false)
    private Pronunciamiento pronunciamiento;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_apoderado", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_apoderado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_apoderado", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@ManyToOne
	@JoinColumn(name="id_sorteo", referencedColumnName="id_sorteo", insertable = false, updatable = false)
    private Sorteo sorteo;	
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<Audiencia> audienciaList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<CorreoRolPersonaCaso> correoRolPersonaCasoList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<EventoRolPersonaCaso> eventoRolPersonaCasoList;
	@OneToMany(mappedBy="rolPersonaCaso")
	private List<FirmaDocumento> firmaDocumentoList;
	@OneToMany(mappedBy="rolPersonaCaso")
	private List<Inasistencia> inasistenciaList;		
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<Llamada> llamadaList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<ObligacionParte> obligacionParteList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<ParteContestacion> parteContestacionList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<ParteDeLaRecusacion> parteDeLaRecusacionList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<PersonaSuspension> personaSuspensionList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<Recusacion> recusacionList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<RolPersonaCaso> rolPersonaCasoList;
	@OneToMany(mappedBy="rolPersonaCaso")
	private List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList;
	@OneToMany(mappedBy="rolPersonaCaso")
	private List<UbicacionRolPersonaCaso> ubicacionRolPersonaCasoList;
	@OneToMany(mappedBy="rolPersonaCaso")
    private List<PartePeticion> partePeticionList;
	
	
    public RolPersonaCaso(){
		rolPersonaCasoPK = new RolPersonaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RolPersonaCasoPK getRolPersonaCasoPK(){
		return this.rolPersonaCasoPK;
	}
	
	public void setRolPersonaCasoPK(RolPersonaCasoPK rolPersonaCasoPK){
		this.rolPersonaCasoPK   = rolPersonaCasoPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getMotivoInactivacion(){
		return this.motivoInactivacion;
	}
	
	public void setMotivoInactivacion(String motivoInactivacion){
		this.motivoInactivacion = motivoInactivacion;
	}
		
	public String getTipoNombramiento(){
		return this.tipoNombramiento;
	}
	
	public void setTipoNombramiento(String tipoNombramiento){
		this.tipoNombramiento = tipoNombramiento;
	}
		
	public Integer getOrdenDeAsignacion(){
		return this.ordenDeAsignacion;
	}
	
	public void setOrdenDeAsignacion(Integer ordenDeAsignacion){
		this.ordenDeAsignacion = ordenDeAsignacion;
	}
		
	public String getMetodoNombramiento(){
		return this.metodoNombramiento;
	}
	
	public void setMetodoNombramiento(String metodoNombramiento){
		this.metodoNombramiento = metodoNombramiento;
	}
		
	public String getTipoSuplencia(){
		return this.tipoSuplencia;
	}
	
	public void setTipoSuplencia(String tipoSuplencia){
		this.tipoSuplencia = tipoSuplencia;
	}
		
	public boolean getEsPresidente(){
		return this.esPresidente;
	}
	
	public void setEsPresidente(boolean esPresidente){
		this.esPresidente = esPresidente;
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
		
	public Long getIdRolApoderado(){
		return this.idRolApoderado;
	}
	
	public void setIdRolApoderado(Long idRolApoderado){
		this.idRolApoderado = idRolApoderado;
	}
		
	public Long getIdPersonaApoderado(){
		return this.idPersonaApoderado;
	}
	
	public void setIdPersonaApoderado(Long idPersonaApoderado){
		this.idPersonaApoderado = idPersonaApoderado;
	}
		
	public Long getIdCasoApoderado(){
		return this.idCasoApoderado;
	}
	
	public void setIdCasoApoderado(Long idCasoApoderado){
		this.idCasoApoderado = idCasoApoderado;
	}
		
	public Long getIdSorteo(){
		return this.idSorteo;
	}
	
	public void setIdSorteo(Long idSorteo){
		this.idSorteo = idSorteo;
	}
		
	public Long getIdPronunciamiento(){
		return this.idPronunciamiento;
	}
	
	public void setIdPronunciamiento(Long idPronunciamiento){
		this.idPronunciamiento = idPronunciamiento;
	}
		
	public Long getIdRolPrincipalReemplazado(){
		return this.idRolPrincipalReemplazado;
	}
	
	public void setIdRolPrincipalReemplazado(Long idRolPrincipalReemplazado){
		this.idRolPrincipalReemplazado = idRolPrincipalReemplazado;
	}
		
	public Long getIdPersonaPrincipalReemplazado(){
		return this.idPersonaPrincipalReemplazado;
	}
	
	public void setIdPersonaPrincipalReemplazado(Long idPersonaPrincipalReemplazado){
		this.idPersonaPrincipalReemplazado = idPersonaPrincipalReemplazado;
	}
		
	public Long getIdCasoPrincipalReemplazado(){
		return this.idCasoPrincipalReemplazado;
	}
	
	public void setIdCasoPrincipalReemplazado(Long idCasoPrincipalReemplazado){
		this.idCasoPrincipalReemplazado = idCasoPrincipalReemplazado;
	}
		
	public Long getIdPersonaTercero(){
		return this.idPersonaTercero;
	}
	
	public void setIdPersonaTercero(Long idPersonaTercero){
		this.idPersonaTercero = idPersonaTercero;
	}
		

    public List<Audiencia> getAudienciaList(){
		return this.audienciaList;
	}
	
	public void setAudienciaList(List<Audiencia> audienciaList){
		this.audienciaList = audienciaList;
	}
			
    public List<CorreoRolPersonaCaso> getCorreoRolPersonaCasoList(){
		return this.correoRolPersonaCasoList;
	}
	
	public void setCorreoRolPersonaCasoList(List<CorreoRolPersonaCaso> correoRolPersonaCasoList){
		this.correoRolPersonaCasoList = correoRolPersonaCasoList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<EventoRolPersonaCaso> getEventoRolPersonaCasoList(){
		return this.eventoRolPersonaCasoList;
	}
	
	public void setEventoRolPersonaCasoList(List<EventoRolPersonaCaso> eventoRolPersonaCasoList){
		this.eventoRolPersonaCasoList = eventoRolPersonaCasoList;
	}
			
    public List<Llamada> getLlamadaList(){
		return this.llamadaList;
	}
	
	public void setLlamadaList(List<Llamada> llamadaList){
		this.llamadaList = llamadaList;
	}
			
    public List<ParteContestacion> getParteContestacionList(){
		return this.parteContestacionList;
	}
	
	public void setParteContestacionList(List<ParteContestacion> parteContestacionList){
		this.parteContestacionList = parteContestacionList;
	}
			
    public List<ParteDeLaRecusacion> getParteDeLaRecusacionList(){
		return this.parteDeLaRecusacionList;
	}
	
	public void setParteDeLaRecusacionList(List<ParteDeLaRecusacion> parteDeLaRecusacionList){
		this.parteDeLaRecusacionList = parteDeLaRecusacionList;
	}
	
    public List<PartePeticion> getPartePeticionList(){
		return this.partePeticionList;
	}
	
	public void setPartePeticionList(List<PartePeticion> partePeticionList){
		this.partePeticionList = partePeticionList;
	}
			
    public List<PersonaSuspension> getPersonaSuspensionList(){
		return this.personaSuspensionList;
	}
	
	public void setPersonaSuspensionList(List<PersonaSuspension> personaSuspensionList){
		this.personaSuspensionList = personaSuspensionList;
	}
			
    public List<Recusacion> getRecusacionList(){
		return this.recusacionList;
	}
	
	public void setRecusacionList(List<Recusacion> recusacionList){
		this.recusacionList = recusacionList;
	}
			
    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
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
    public Pronunciamiento getPronunciamiento(){
		return this.pronunciamiento; 
	}
	
	public void setPronunciamiento(Pronunciamiento pronunciamiento){
		this.pronunciamiento = pronunciamiento;
	}
    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}
    public Sorteo getSorteo(){
		return this.sorteo; 
	}
	
	public void setSorteo(Sorteo sorteo){
		this.sorteo = sorteo;
	}
	
	public List<CorreoElectronicoRolPersonaCaso> getCorreoElectronicoRolPersonaCasoList() {
		return correoElectronicoRolPersonaCasoList;
	}

	public void setCorreoElectronicoRolPersonaCasoList(
			List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList) {
		this.correoElectronicoRolPersonaCasoList = correoElectronicoRolPersonaCasoList;
	}

	public List<UbicacionRolPersonaCaso> getUbicacionRolPersonaCasoList() {
		return ubicacionRolPersonaCasoList;
	}

	public void setUbicacionRolPersonaCasoList(List<UbicacionRolPersonaCaso> ubicacionRolPersonaCasoList) {
		this.ubicacionRolPersonaCasoList = ubicacionRolPersonaCasoList;
	}

	public List<FirmaDocumento> getFirmaDocumentoList() {
		return firmaDocumentoList;
	}

	public void setFirmaDocumentoList(List<FirmaDocumento> firmaDocumentoList) {
		this.firmaDocumentoList = firmaDocumentoList;
	}

	public List<Inasistencia> getInasistenciaList() {
		return inasistenciaList;
	}

	public void setInasistenciaList(List<Inasistencia> inasistenciaList) {
		this.inasistenciaList = inasistenciaList;
	}

	public List<ObligacionParte> getObligacionParteList() {
		return obligacionParteList;
	}

	public void setObligacionParteList(List<ObligacionParte> obligacionParteList) {
		this.obligacionParteList = obligacionParteList;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ROL_PERSONA_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.rolPersonaCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.motivoInactivacion);
        hash = 37 * hash + Objects.hashCode(this.tipoNombramiento);
        hash = 37 * hash + Objects.hashCode(this.ordenDeAsignacion);
        hash = 37 * hash + Objects.hashCode(this.metodoNombramiento);
        hash = 37 * hash + Objects.hashCode(this.tipoSuplencia);
        hash = 37 * hash + (this.esPresidente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRolApoderado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaApoderado);
        hash = 37 * hash + Objects.hashCode(this.idCasoApoderado);
        hash = 37 * hash + Objects.hashCode(this.idSorteo);
        hash = 37 * hash + Objects.hashCode(this.idPronunciamiento);
        hash = 37 * hash + Objects.hashCode(this.idRolPrincipalReemplazado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaPrincipalReemplazado);
        hash = 37 * hash + Objects.hashCode(this.idCasoPrincipalReemplazado);
        hash = 37 * hash + Objects.hashCode(this.idPersonaTercero);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RolPersonaCaso que se pasa
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
        final RolPersonaCaso other = (RolPersonaCaso) obj;
        
        if (!Objects.equals(this.rolPersonaCasoPK, other.rolPersonaCasoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoInactivacion, other.motivoInactivacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoNombramiento, other.tipoNombramiento)) {
            return false;
        }
        
        if (!Objects.equals(this.ordenDeAsignacion, other.ordenDeAsignacion)) {
            return false;
        }
        
        if (!Objects.equals(this.metodoNombramiento, other.metodoNombramiento)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoSuplencia, other.tipoSuplencia)) {
            return false;
        }
        
        if (!Objects.equals(this.esPresidente, other.esPresidente)) {
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
        
        if (!Objects.equals(this.idRolApoderado, other.idRolApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaApoderado, other.idPersonaApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoApoderado, other.idCasoApoderado)) {
            return false;
        }
        
        if (!Objects.equals(this.idSorteo, other.idSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idPronunciamiento, other.idPronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolPrincipalReemplazado, other.idRolPrincipalReemplazado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaPrincipalReemplazado, other.idPersonaPrincipalReemplazado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoPrincipalReemplazado, other.idCasoPrincipalReemplazado)) {
            return false;
        }
        
        return Objects.equals(this.idPersonaTercero, other.idPersonaTercero);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
    
    public String getTipoNombramientoLabel() {   
    	if (this.tipoNombramiento != null) {
    		switch (this.tipoNombramiento) {
    		case UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL:
    			tipoNombramientoLabel = UtilConstantes.NOMBRAMIENTO_PRINCIPAL; 
    			break;
    		case UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE:
    			tipoNombramientoLabel = UtilConstantes.NOMBRAMIENTO_SUPLENTE;
    			break;
    		default:
    			tipoNombramientoLabel = "";
    			break;
    		}
    	}
		return tipoNombramientoLabel;
	}


	public void setTipoNombramientoString(String tipoNombramientoString) {
		this.tipoNombramientoLabel = tipoNombramientoString;
	}

	public List<RolPersonaCaso> getArbitroSuplenteReemplazoList() {
		return arbitroSuplenteReemplazoList;
	}


	public void setArbitroSuplenteReemplazoList(List<RolPersonaCaso> arbitroSuplenteReemplazoList) {
		this.arbitroSuplenteReemplazoList = arbitroSuplenteReemplazoList;
	}	


	public RolPersonaCaso getArbitroPrincipalReemplazado() {
		return arbitroPrincipalReemplazado;
	}

	public void setArbitroPrincipalReemplazado(RolPersonaCaso arbitroPrincipalReemplazado) {
		this.arbitroPrincipalReemplazado = arbitroPrincipalReemplazado;
	}
    
    /**
     * trae la fecha de cuando se designo el arbitro primer evento del rol_persona_caso
     * @return
     */
    public EventoRolPersonaCaso traerEventoAsignacionPorAceptar(){
    	EventoRolPersonaCaso eventoRol = null;
    	
    	if(this.getEventoRolPersonaCasoList()!=null){
    		List<EventoRolPersonaCaso> listaEventos =  consultarEventosActivos();   	
        	Collections.sort(listaEventos, new Comparator<EventoRolPersonaCaso>() {
        		@Override
        		public int compare(EventoRolPersonaCaso evento1, EventoRolPersonaCaso evento2 ){
        			return (evento1.getFechaDeAsignacion().compareTo(evento2.getFechaDeAsignacion()));
        		}
    		}); 	
        	
        	if(listaEventos.size()>0)
        		eventoRol = listaEventos.get(0);
    	}    	
   
		return eventoRol;
    	
    }
    
    /**
     * trae la fecha de cuando se designo el ya sea principal o suplente
     * @return
     */
    public EventoRolPersonaCaso traerEventoDeDesignacion(){
    	EventoRolPersonaCaso eventoRol = null;
    	
    	if(this.getEventoRolPersonaCasoList()!=null){
    		List<EventoRolPersonaCaso> listaEventos =  consultarEventosActivos();   	
        	Collections.sort(listaEventos, new Comparator<EventoRolPersonaCaso>() {
        		@Override
        		public int compare(EventoRolPersonaCaso evento1, EventoRolPersonaCaso evento2 ){
        			return (evento2.getFechaDeAsignacion().compareTo(evento1.getFechaDeAsignacion()));
        		}
    		}); 	
        	
			for (EventoRolPersonaCaso eventoRolPersonaCasoFor : listaEventos) {
				if ((UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL.equals(tipoNombramiento)
						&& UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR
								.equals(eventoRolPersonaCasoFor.getEstadoAsignado()))
						|| (UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE.equals(tipoNombramiento)
								&& UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO
										.equals(eventoRolPersonaCasoFor.getEstadoAsignado()))) {
					return eventoRolPersonaCasoFor;
				}
			}
        	
    	}    	
   
		return eventoRol;
    	
    }
    
    /**
     * Consulta los estados de la persona cuyo estado de registro es activos
     * @return
     */
    public List<EventoRolPersonaCaso> consultarEventosActivos(){
    	
    	List<EventoRolPersonaCaso> eventosActivos = new ArrayList<>();
    	if(this.getEventoRolPersonaCasoList()!=null){
    		for(EventoRolPersonaCaso erpc : this.getEventoRolPersonaCasoList()){
        		if(erpc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)){
        			eventosActivos.add(erpc);
        		}
        	}
    	}    	
    	
    	return eventosActivos;
    }
    
    /**
     * Devuelve la última fecha de comunicación del pronunciamiento o nulo si no aplica
     * 
     * @return
     */
    public Date consultarFechaDeComunicacion(){

    	Date fechaComunicacion = null;
    	EventoRolPersonaCaso eventoComunicacion = consultarEventoDeComunicacion();
    	if(eventoComunicacion!=null){
    		fechaComunicacion = eventoComunicacion.getFechaDeAsignacion();
    	}
    	return fechaComunicacion;
    }
    
    /**
     * Consulta el evento asociado al momento que se le comunico al �?rbitro su designación como �?rbitro principal para el caso.
     * Devuelve nulo si no hay un evento de comunicación.
     * 
     * @return
     */
    public EventoRolPersonaCaso consultarEventoDeComunicacion(){    	
    	
    	//Consulta los eventos de comunicación de la designación
    	List<EventoRolPersonaCaso> eventosComunicacion = new ArrayList<>();
    	if(this.getEventoRolPersonaCasoList()!=null){
    		for(EventoRolPersonaCaso erpc : this.getEventoRolPersonaCasoList()){
        		if(erpc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO) 
        					&& erpc.getEstadoAsignado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO)){
        			eventosComunicacion.add(erpc);
        		}
        	}
    	}    	
    	
    	EventoRolPersonaCaso eventoComunicacion = null;
    	
    	if(eventosComunicacion.size()==1){
    		eventoComunicacion = eventosComunicacion.get(0);
    	}else if(eventosComunicacion.size()>1){
    		//Trae la ultima fecha de comunicacion registrada en RolPersonaCaso
        	//1. Ordena de meno
    		Collections.sort(eventosComunicacion, new Comparator<EventoRolPersonaCaso>() {
        		@Override
        		public int compare(EventoRolPersonaCaso evento1, EventoRolPersonaCaso evento2 ){
        			return (evento1.getFechaDeAsignacion().compareTo(evento2.getFechaDeAsignacion()));
        		}
    		});
    		//2. Obtiene el útlimo registro
    		eventoComunicacion = eventosComunicacion.get(eventosComunicacion.size()-1);    		        	
    	}    	
    	
    	return eventoComunicacion;
    }
    
    /**
     * Devuelve la úlltima fecha de designación del �?rbitro o nulo sino aplica (si el �?bitro es suplente)
     * 
     * @return
     */
    public Date consultarFechaDeAsignacion() throws SimascException{    	
    	
    	Date fechaAsignacion = null;
    	EventoRolPersonaCaso eventoAsignacion = consultarEventoAsignacion();
    	if(eventoAsignacion!=null){
    		fechaAsignacion = eventoAsignacion.getFechaDeAsignacion();
    	}
    	return fechaAsignacion;    
    	
    }
    
    
    
    /**
     * Consulta el EventoRolPersonaCaso asociado a la asignación como �?rbitro principal (cuando se le asigna el estado 'Por aceptar'
     * @return
     * @throws SimascException
     */
    public EventoRolPersonaCaso consultarEventoAsignacion() throws SimascException{
    	//Consulta los eventos de comunicación de la designación
    	List<EventoRolPersonaCaso> eventosComunicacion = new ArrayList<>();
    	if(this.getEventoRolPersonaCasoList()!=null){
    		for(EventoRolPersonaCaso erpc : this.getEventoRolPersonaCasoList()){
    			//quitamos hora a las fechas
    			erpc.setFechaDeAsignacion(UtilOperaciones.removerHora(erpc.getFechaDeAsignacion()));
        		if(erpc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO) 
        					&& erpc.getEstadoAsignado().equals(UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR)){
        			eventosComunicacion.add(erpc);
        		}
        	}
    	}   
    	//ordenamos los eventos por fecha
    	Collections.sort(eventosComunicacion, new Comparator<EventoRolPersonaCaso>() {
    	     public int compare(EventoRolPersonaCaso o1, EventoRolPersonaCaso o2) {
    	         if (o1.getFechaDeAsignacion() == null || o2.getFechaDeAsignacion() == null)
    	           return 0;
    	         return -1*( o1.getFechaDeAsignacion().compareTo(o2.getFechaDeAsignacion()));
    	     }
    	});
    	//recorremos la lista para validar si hay mas de un evento en la misma fecha
    	int index = 1;
    	for(EventoRolPersonaCaso erpc : eventosComunicacion){
			//quitamos hora a las fechas
    		if(index < eventosComunicacion.size() && erpc.getFechaDeAsignacion().equals(eventosComunicacion.get(index).getFechaDeAsignacion())){
    			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO053.toString()));
    		}
    		index++;
    	}
    	if (eventosComunicacion.size()==0){
    		throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR223.toString()));
    	}
    	
    	return eventosComunicacion.get(0);
    	
    }
    
    
    public EventoRolPersonaCaso traerUtilmaAsignacionPorEstado(String estado){
    	EventoRolPersonaCaso eventoRol = null;
    	
    	if(this.getEventoRolPersonaCasoList()!=null){
    		List<EventoRolPersonaCaso> listaEventos =  consultarEventosPorEstado(estado);   	
        	Collections.sort(listaEventos, new Comparator<EventoRolPersonaCaso>() {
        		@Override
        		public int compare(EventoRolPersonaCaso evento1, EventoRolPersonaCaso evento2 ){
        			return (evento2.getFechaDeAsignacion().compareTo(evento1.getFechaDeAsignacion()));
        		}
    		}); 	
        	
        	if(listaEventos.size()>0){
        		eventoRol = listaEventos.get(0);
        	}
        	
    	}    	
   
		return eventoRol;
    	
    }
    
    
    public List<EventoRolPersonaCaso> consultarEventosPorEstado(String estado){
    	
    	List<EventoRolPersonaCaso> eventosPorEstado = new ArrayList<>();
    	if(this.getEventoRolPersonaCasoList()!=null){
    		for(EventoRolPersonaCaso erpc : this.getEventoRolPersonaCasoList()){
        		if(erpc.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO) && estado.equals(erpc.getEstadoAsignado())){
        			eventosPorEstado.add(erpc);
        		}
        	}
    	}    	
    	
    	return eventosPorEstado;
    }


	public Persona getTercero() {
		return tercero;
	}
	
	
	public void setTercero(Persona tercero) {
		this.tercero = tercero;
	}
 
	public String getNombramientoSorteo() {
		return nombramientoPorSorteo;
	}

	public void setNombramientoSorteo(String nombramientoSorteo) {
		this.nombramientoPorSorteo = nombramientoSorteo;
	}

	/**
	 * Método encargado de obtener el nombramiento de un árbitro al momento que
	 * fue designado por sorteo público
	 * 
	 * @return
	 */
	public String obtenerNombramientoPorSorteo() {
		if (this.sorteo != null) {
			Date fechaRealizacion = UtilOperaciones.obtenerFechaComienzoDelDia(this.sorteo.getFechaRealizacion());

			if (this.getEventoRolPersonaCasoList() != null && !this.eventoRolPersonaCasoList.isEmpty()) {
				Collections.sort(this.getEventoRolPersonaCasoList(), new Comparator<EventoRolPersonaCaso>() {
					@Override
					public int compare(EventoRolPersonaCaso evento1, EventoRolPersonaCaso evento2) {
						return (evento1.getIdEventoRolPersonaCaso().compareTo(evento2.getIdEventoRolPersonaCaso()));
					}
				});

				for (EventoRolPersonaCaso eventoRolPersonaCaso : this.getEventoRolPersonaCasoList()) {
					Date fechaDeAsignacion = UtilOperaciones
							.obtenerFechaComienzoDelDia(eventoRolPersonaCaso.getFechaDeAsignacion());

					if (fechaRealizacion.compareTo(fechaDeAsignacion) == 0) {
						switch (eventoRolPersonaCaso.getEstadoAsignado()) {
						case UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR:
							nombramientoPorSorteo = UtilConstantes.NOMBRAMIENTO_PRINCIPAL;
							break;
						case UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO:
							nombramientoPorSorteo = UtilConstantes.NOMBRAMIENTO_SUPLENTE;
							break;
						default:
							nombramientoPorSorteo = "";
							break;
						}
						break;
					}else {
						nombramientoPorSorteo = "";
					}
				}
			} else {
				nombramientoPorSorteo = "";
			}
		}
		return nombramientoPorSorteo;
	}
	
	/**
	 * @return the representadosList
	 */
	public List<Apoderados> getRepresentadosList() {
		return representadosList;
	}


	/**
	 * @param representadosList the representadosList to set
	 */
	public void setRepresentadosList(List<Apoderados> representadosList) {
		this.representadosList = representadosList;
	}
    
	// protected region metodos adicionales end

} 

