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
@Table(name="RECUSACION")
@NamedQuery(name = "Recusacion.findAll", query = "SELECT p FROM Recusacion p")
public class Recusacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@ManyToOne
	@JoinColumn(name="id_doc_confirmacion", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoConfirmacion;
		
	@ManyToOne
	@JoinColumn(name="id_doc_recusacion", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoRecusacion;
		
	@ManyToOne
	@JoinColumn(name="id_doc_respuesta", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoRespuesta;
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_RECUSACION_PK = "idRecusacion";
	public static final String ENTIDAD_RECUSACION_ID_ROL_ARBITRO = "idRolArbitro";
	public static final String ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO = "idPersonaArbitro";
	public static final String ENTIDAD_RECUSACION_ID_CASO_ARBITRO = "idCasoArbitro";
	public static final String ENTIDAD_RECUSACION_ESTADO = "estado";
	public static final String ENTIDAD_RECUSACION_FECHA_RECUSACION = "fechaRecusacion";
	public static final String ENTIDAD_RECUSACION_MOTIVO_RECUSACION = "motivoRecusacion";
	public static final String ENTIDAD_RECUSACION_ACEPTA_RECUSACION = "aceptaRecusacion";
	public static final String ENTIDAD_RECUSACION_FECHA_RESPUESTA_ARBITRO = "fechaRespuestaArbitro";
	public static final String ENTIDAD_RECUSACION_TIPO_DE_CONFIRMACION = "tipoDeConfirmacion";
	public static final String ENTIDAD_RECUSACION_FECHA_CONFIRMACION = "fechaConfirmacion";
	public static final String ENTIDAD_RECUSACION_JUZGADO_CONFIRMACION = "juzgadoConfirmacion";
	public static final String ENTIDAD_RECUSACION_CONFIRMACION_NOMBRAMIENTO = "confirmacionNombramiento";
	public static final String ENTIDAD_RECUSACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_RECUSACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_RECUSACION_ESTADO_REGISTRO_RECUSACION = "estadoRegistroRecusacion";			
	public static final String ENTIDAD_RECUSACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_RECUSACION_ID_DOC_RECUSACION = "idDocRecusacion";
	public static final String ENTIDAD_RECUSACION_ID_DOC_RESPUESTA = "idDocRespuesta";
	public static final String ENTIDAD_RECUSACION_ID_DOC_CONFIRMACION = "idDocConfirmacion";
    private static final String[] ATRIBUTOS_ENTIDAD_RECUSACION
            = {ENTIDAD_RECUSACION_FECHA_RECUSACION, ENTIDAD_RECUSACION_PK, ENTIDAD_RECUSACION_ACEPTA_RECUSACION, ENTIDAD_RECUSACION_ESTADO, ENTIDAD_RECUSACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_RECUSACION_FECHA_CONFIRMACION, ENTIDAD_RECUSACION_MOTIVO_RECUSACION, ENTIDAD_RECUSACION_ID_DOC_RECUSACION, ENTIDAD_RECUSACION_ID_DOC_RESPUESTA, ENTIDAD_RECUSACION_TIPO_DE_CONFIRMACION, ENTIDAD_RECUSACION_ID_USUARIO_MODIFICACION, ENTIDAD_RECUSACION_ID_ROL_ARBITRO, ENTIDAD_RECUSACION_ID_CASO_ARBITRO, ENTIDAD_RECUSACION_ID_PERSONA_ARBITRO, ENTIDAD_RECUSACION_CONFIRMACION_NOMBRAMIENTO, ENTIDAD_RECUSACION_FECHA_RESPUESTA_ARBITRO, ENTIDAD_RECUSACION_JUZGADO_CONFIRMACION, ENTIDAD_RECUSACION_ESTADO_REGISTRO, ENTIDAD_RECUSACION_ID_DOC_CONFIRMACION};

	@Id
    @Column(name="id_recusacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idRecusacion;
    
	@Column(name="id_rol_arbitro")
	private Long idRolArbitro;		
    
	@Column(name="id_persona_arbitro")
	private Long idPersonaArbitro;		
    
	@Column(name="id_caso_arbitro")
	private Long idCasoArbitro;		
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_recusacion")
	private Date fechaRecusacion;		
    
	@Column(name="motivo_recusacion")
	private String motivoRecusacion;		
    
	@Column(name="acepta_recusacion")
	private boolean aceptaRecusacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_respuesta_arbitro")
	private Date fechaRespuestaArbitro;		
    
	@Column(name="tipo_de_confirmacion")
	private String tipoDeConfirmacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_confirmacion")
	private Date fechaConfirmacion;		
    
	@Column(name="juzgado_confirmacion")
	private String juzgadoConfirmacion;		
    
	@Column(name="confirmacion_nombramiento")
	private boolean confirmacionNombramiento;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_doc_recusacion")
	private Long idDocRecusacion;		
    
	@Column(name="id_doc_respuesta")
	private Long idDocRespuesta;		
    
	@Column(name="id_doc_confirmacion")
	private Long idDocConfirmacion;		
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_arbitro", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_arbitro", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_arbitro", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@OneToMany(mappedBy="recusacion")
    private List<ParteDeLaRecusacion> parteDeLaRecusacionList;
	
	
    public Recusacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdRecusacion(){
		return this.idRecusacion;
	}
	
	public void setIdRecusacion(Long idRecusacion){
		this.idRecusacion = idRecusacion;
	}
	
	public Long getIdRolArbitro(){
		return this.idRolArbitro;
	}
	
	public void setIdRolArbitro(Long idRolArbitro){
		this.idRolArbitro = idRolArbitro;
	}
		
	public Long getIdPersonaArbitro(){
		return this.idPersonaArbitro;
	}
	
	public void setIdPersonaArbitro(Long idPersonaArbitro){
		this.idPersonaArbitro = idPersonaArbitro;
	}
		
	public Long getIdCasoArbitro(){
		return this.idCasoArbitro;
	}
	
	public void setIdCasoArbitro(Long idCasoArbitro){
		this.idCasoArbitro = idCasoArbitro;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFechaRecusacion(){
		return this.fechaRecusacion;
	}
	
	public void setFechaRecusacion(Date fechaRecusacion){
		this.fechaRecusacion = fechaRecusacion;
	}
		
	public String getMotivoRecusacion(){
		return this.motivoRecusacion;
	}
	
	public void setMotivoRecusacion(String motivoRecusacion){
		this.motivoRecusacion = motivoRecusacion;
	}
		
	public boolean getAceptaRecusacion(){
		return this.aceptaRecusacion;
	}
	
	public void setAceptaRecusacion(boolean aceptaRecusacion){
		this.aceptaRecusacion = aceptaRecusacion;
	}
		
	public Date getFechaRespuestaArbitro(){
		return this.fechaRespuestaArbitro;
	}
	
	public void setFechaRespuestaArbitro(Date fechaRespuestaArbitro){
		this.fechaRespuestaArbitro = fechaRespuestaArbitro;
	}
		
	public String getTipoDeConfirmacion(){
		return this.tipoDeConfirmacion;
	}
	
	public void setTipoDeConfirmacion(String tipoDeConfirmacion){
		this.tipoDeConfirmacion = tipoDeConfirmacion;
	}
		
	public Date getFechaConfirmacion(){
		return this.fechaConfirmacion;
	}
	
	public void setFechaConfirmacion(Date fechaConfirmacion){
		this.fechaConfirmacion = fechaConfirmacion;
	}
		
	public String getJuzgadoConfirmacion(){
		return this.juzgadoConfirmacion;
	}
	
	public void setJuzgadoConfirmacion(String juzgadoConfirmacion){
		this.juzgadoConfirmacion = juzgadoConfirmacion;
	}
		
	public boolean getConfirmacionNombramiento(){
		return this.confirmacionNombramiento;
	}
	
	public void setConfirmacionNombramiento(boolean confirmacionNombramiento){
		this.confirmacionNombramiento = confirmacionNombramiento;
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
		
	public Long getIdDocRecusacion(){
		return this.idDocRecusacion;
	}
	
	public void setIdDocRecusacion(Long idDocRecusacion){
		this.idDocRecusacion = idDocRecusacion;
	}
		
	public Long getIdDocRespuesta(){
		return this.idDocRespuesta;
	}
	
	public void setIdDocRespuesta(Long idDocRespuesta){
		this.idDocRespuesta = idDocRespuesta;
	}
		
	public Long getIdDocConfirmacion(){
		return this.idDocConfirmacion;
	}
	
	public void setIdDocConfirmacion(Long idDocConfirmacion){
		this.idDocConfirmacion = idDocConfirmacion;
	}
		

    public List<ParteDeLaRecusacion> getParteDeLaRecusacionList(){
		return this.parteDeLaRecusacionList;
	}
	
	public void setParteDeLaRecusacionList(List<ParteDeLaRecusacion> parteDeLaRecusacionList){
		this.parteDeLaRecusacionList = parteDeLaRecusacionList;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_RECUSACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idRecusacion);        
        hash = 37 * hash + Objects.hashCode(this.idRolArbitro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaArbitro);
        hash = 37 * hash + Objects.hashCode(this.idCasoArbitro);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fechaRecusacion);
        hash = 37 * hash + Objects.hashCode(this.motivoRecusacion);
        hash = 37 * hash + (this.aceptaRecusacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaRespuestaArbitro);
        hash = 37 * hash + Objects.hashCode(this.tipoDeConfirmacion);
        hash = 37 * hash + Objects.hashCode(this.fechaConfirmacion);
        hash = 37 * hash + Objects.hashCode(this.juzgadoConfirmacion);
        hash = 37 * hash + (this.confirmacionNombramiento ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocRecusacion);
        hash = 37 * hash + Objects.hashCode(this.idDocRespuesta);
        hash = 37 * hash + Objects.hashCode(this.idDocConfirmacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Recusacion que se pasa
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
        final Recusacion other = (Recusacion) obj;
        
        if (!Objects.equals(this.idRecusacion, other.idRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolArbitro, other.idRolArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaArbitro, other.idPersonaArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoArbitro, other.idCasoArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRecusacion, other.fechaRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoRecusacion, other.motivoRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aceptaRecusacion, other.aceptaRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRespuestaArbitro, other.fechaRespuestaArbitro)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeConfirmacion, other.tipoDeConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaConfirmacion, other.fechaConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.juzgadoConfirmacion, other.juzgadoConfirmacion)) {
            return false;
        }
        
        if (!Objects.equals(this.confirmacionNombramiento, other.confirmacionNombramiento)) {
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
        
        if (!Objects.equals(this.idDocRecusacion, other.idDocRecusacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocRespuesta, other.idDocRespuesta)) {
            return false;
        }
        
        return Objects.equals(this.idDocConfirmacion, other.idDocConfirmacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	public Documento getDocumentoConfirmacion() {
		return documentoConfirmacion;
	}


	public void setDocumentoConfirmacion(Documento documentoConfirmacion) {
		this.documentoConfirmacion = documentoConfirmacion;
	}


	public Documento getDocumentoRecusacion() {
		return documentoRecusacion;
	}


	public void setDocumentoRecusacion(Documento documentoRecusacion) {
		this.documentoRecusacion = documentoRecusacion;
	}


	public Documento getDocumentoRespuesta() {
		return documentoRespuesta;
	}


	public void setDocumentoRespuesta(Documento documentoRespuesta) {
		this.documentoRespuesta = documentoRespuesta;
	}
	// protected region metodos adicionales end

} 

