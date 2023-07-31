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
@Table(name="TRANSCRIPCION")
@NamedQuery(name = "Transcripcion.findAll", query = "SELECT p FROM Transcripcion p")
public class Transcripcion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	@ManyToOne
	@JoinColumn(name="id_doc_generado", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoGenerado;
		
	@ManyToOne
	@JoinColumn(name="id_doc_audio_fuente", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoAudioFuente;
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TRANSCRIPCION_PK = "idTranscripcion";
	public static final String ENTIDAD_TRANSCRIPCION_ESTADO = "estado";
	public static final String ENTIDAD_TRANSCRIPCION_TIEMPO_TRANSCRITO = "tiempoTranscrito";
	public static final String ENTIDAD_TRANSCRIPCION_TIEMPO_INICIAL = "tiempoInicial";
	public static final String ENTIDAD_TRANSCRIPCION_TIEMPO_FINAL = "tiempoFinal";
	public static final String ENTIDAD_TRANSCRIPCION_FECHA_INICIO_TRANSCRIPCION = "fechaInicioTranscripcion";
	public static final String ENTIDAD_TRANSCRIPCION_FECHA_ENTREGA_TRANSCRIPCION = "fechaEntregaTranscripcion";
	public static final String ENTIDAD_TRANSCRIPCION_FECHA_PREVISTA_DE_ENTREGA = "fechaPrevistaDeEntrega";
	public static final String ENTIDAD_TRANSCRIPCION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TRANSCRIPCION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TRANSCRIPCION_ESTADO_REGISTRO_TRANSCRIPCION = "estadoRegistroTranscripcion";			
	public static final String ENTIDAD_TRANSCRIPCION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_TRANSCRIPCION_ID_DOC_GENERADO = "idDocGenerado";
	public static final String ENTIDAD_TRANSCRIPCION_ID_DOC_AUDIO_FUENTE = "idDocAudioFuente";
	public static final String ENTIDAD_TRANSCRIPCION_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_TRANSCRIPCION
            = {ENTIDAD_TRANSCRIPCION_TIEMPO_TRANSCRITO, ENTIDAD_TRANSCRIPCION_FECHA_ENTREGA_TRANSCRIPCION, ENTIDAD_TRANSCRIPCION_ID_DOC_GENERADO, ENTIDAD_TRANSCRIPCION_TIEMPO_FINAL, ENTIDAD_TRANSCRIPCION_ESTADO, ENTIDAD_TRANSCRIPCION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TRANSCRIPCION_ID_DOC_AUDIO_FUENTE, ENTIDAD_TRANSCRIPCION_ID_PERSONA, ENTIDAD_TRANSCRIPCION_PK, ENTIDAD_TRANSCRIPCION_FECHA_PREVISTA_DE_ENTREGA, ENTIDAD_TRANSCRIPCION_ESTADO_REGISTRO, ENTIDAD_TRANSCRIPCION_ID_USUARIO_MODIFICACION, ENTIDAD_TRANSCRIPCION_TIEMPO_INICIAL, ENTIDAD_TRANSCRIPCION_FECHA_INICIO_TRANSCRIPCION};

	@Id
    @Column(name="id_transcripcion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTranscripcion;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="tiempo_transcrito")
	private Long tiempoTranscrito;		
    
	@Column(name="tiempo_inicial")
	private Long tiempoInicial;		
    
	@Column(name="tiempo_final")
	private Long tiempoFinal;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_transcripcion")
	private Date fechaInicioTranscripcion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_entrega_transcripcion")
	private Date fechaEntregaTranscripcion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_prevista_de_entrega")
	private Date fechaPrevistaDeEntrega;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_doc_generado")
	private Long idDocGenerado;		
    
	@Column(name="id_doc_audio_fuente")
	private Long idDocAudioFuente;		
    
	@Column(name="id_persona")
	private Long idPersona;		
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@OneToMany(mappedBy="transcripcion")
    private List<RegistroTranscripcion> registroTranscripcionList;
	
	
    public Transcripcion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTranscripcion(){
		return this.idTranscripcion;
	}
	
	public void setIdTranscripcion(Long idTranscripcion){
		this.idTranscripcion = idTranscripcion;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getTiempoTranscrito(){
		return this.tiempoTranscrito;
	}
	
	public void setTiempoTranscrito(Long tiempoTranscrito){
		this.tiempoTranscrito = tiempoTranscrito;
	}
		
	public Long getTiempoInicial(){
		return this.tiempoInicial;
	}
	
	public void setTiempoInicial(Long tiempoInicial){
		this.tiempoInicial = tiempoInicial;
	}
		
	public Long getTiempoFinal(){
		return this.tiempoFinal;
	}
	
	public void setTiempoFinal(Long tiempoFinal){
		this.tiempoFinal = tiempoFinal;
	}
		
	public Date getFechaInicioTranscripcion(){
		return this.fechaInicioTranscripcion;
	}
	
	public void setFechaInicioTranscripcion(Date fechaInicioTranscripcion){
		this.fechaInicioTranscripcion = fechaInicioTranscripcion;
	}
		
	public Date getFechaEntregaTranscripcion(){
		return this.fechaEntregaTranscripcion;
	}
	
	public void setFechaEntregaTranscripcion(Date fechaEntregaTranscripcion){
		this.fechaEntregaTranscripcion = fechaEntregaTranscripcion;
	}
		
	public Date getFechaPrevistaDeEntrega(){
		return this.fechaPrevistaDeEntrega;
	}
	
	public void setFechaPrevistaDeEntrega(Date fechaPrevistaDeEntrega){
		this.fechaPrevistaDeEntrega = fechaPrevistaDeEntrega;
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
		
	public Long getIdDocGenerado(){
		return this.idDocGenerado;
	}
	
	public void setIdDocGenerado(Long idDocGenerado){
		this.idDocGenerado = idDocGenerado;
	}
		
	public Long getIdDocAudioFuente(){
		return this.idDocAudioFuente;
	}
	
	public void setIdDocAudioFuente(Long idDocAudioFuente){
		this.idDocAudioFuente = idDocAudioFuente;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		

    public List<RegistroTranscripcion> getRegistroTranscripcionList(){
		return this.registroTranscripcionList;
	}
	
	public void setRegistroTranscripcionList(List<RegistroTranscripcion> registroTranscripcionList){
		this.registroTranscripcionList = registroTranscripcionList;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TRANSCRIPCION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTranscripcion);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.tiempoTranscrito);
        hash = 37 * hash + Objects.hashCode(this.tiempoInicial);
        hash = 37 * hash + Objects.hashCode(this.tiempoFinal);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaEntregaTranscripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaPrevistaDeEntrega);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocGenerado);
        hash = 37 * hash + Objects.hashCode(this.idDocAudioFuente);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Transcripcion que se pasa
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
        final Transcripcion other = (Transcripcion) obj;
        
        if (!Objects.equals(this.idTranscripcion, other.idTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoTranscrito, other.tiempoTranscrito)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoInicial, other.tiempoInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.tiempoFinal, other.tiempoFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioTranscripcion, other.fechaInicioTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEntregaTranscripcion, other.fechaEntregaTranscripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPrevistaDeEntrega, other.fechaPrevistaDeEntrega)) {
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
        
        if (!Objects.equals(this.idDocGenerado, other.idDocGenerado)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocAudioFuente, other.idDocAudioFuente)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

    public Documento getDocumentoGenerado() {
		return documentoGenerado;
	}


	public void setDocumentoGenerado(Documento documentoGenerado) {
		this.documentoGenerado = documentoGenerado;
	}


	public Documento getDocumentoAudioFuente() {
		return documentoAudioFuente;
	}


	public void setDocumentoAudioFuente(Documento documentoAudioFuente) {
		this.documentoAudioFuente = documentoAudioFuente;
	}
	
	// protected region metodos adicionales end

} 

