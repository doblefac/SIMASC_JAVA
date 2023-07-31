package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="DEVOLUCION_DOCUMENTO_RESULTADO")
@NamedQuery(name = "DevolucionDocumentoResultado.findAll", query = "SELECT p FROM DevolucionDocumentoResultado p")
public class DevolucionDocumentoResultado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_PK = "idDevolucionDocumentoResultado";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA = "fecha";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_CORRIGE = "corrige";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA_CORRECCION = "fechaCorreccion";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_OBSERVACIONES_RESPUESTA = "observacionesRespuesta";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ESTADO_REGISTRO_DEVOLUCIONDOCUMENTORESULTADO = "estadoRegistroDevolucionDocumentoResultado";			
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO
            = {ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_PERSONA, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_OBSERVACIONES, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_DOCUMENTO, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ESTADO_REGISTRO, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_CORRIGE, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_ID_USUARIO_MODIFICACION, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_PK, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA_CORRECCION, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_OBSERVACIONES_RESPUESTA, ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO_FECHA};

	@Id
    @Column(name="id_devolucion_documento_resultado")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDevolucionDocumentoResultado;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="corrige")
	private boolean corrige;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="fecha_correccion")
	private Date fechaCorreccion;		
    
	@Column(name="observaciones_respuesta")
	private String observacionesRespuesta;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona")
	private Long idPersona;    
		
    
	@Column(name="id_documento")
	private Long idDocumento;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@OneToMany(mappedBy="devolucionDocumentoResultado")
    private List<MotivoDevolucion> motivoDevolucionList;
	
	
    public DevolucionDocumentoResultado(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdDevolucionDocumentoResultado(){
		return this.idDevolucionDocumentoResultado;
	}
	
	public void setIdDevolucionDocumentoResultado(Long idDevolucionDocumentoResultado){
		this.idDevolucionDocumentoResultado = idDevolucionDocumentoResultado;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public boolean getCorrige(){
		return this.corrige;
	}
	
	public void setCorrige(boolean corrige){
		this.corrige = corrige;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Date getFechaCorreccion(){
		return this.fechaCorreccion;
	}
	
	public void setFechaCorreccion(Date fechaCorreccion){
		this.fechaCorreccion = fechaCorreccion;
	}
		
	public String getObservacionesRespuesta(){
		return this.observacionesRespuesta;
	}
	
	public void setObservacionesRespuesta(String observacionesRespuesta){
		this.observacionesRespuesta = observacionesRespuesta;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		

		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public List<MotivoDevolucion> getMotivoDevolucionList(){
		return this.motivoDevolucionList;
	}
	
	public void setMotivoDevolucionList(List<MotivoDevolucion> motivoDevolucionList){
		this.motivoDevolucionList = motivoDevolucionList;
	}			

    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_DEVOLUCION_DOCUMENTO_RESULTADO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idDevolucionDocumentoResultado);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + (this.corrige ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.fechaCorreccion);
        hash = 37 * hash + Objects.hashCode(this.observacionesRespuesta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DevolucionDocumentoResultado que se pasa
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
        final DevolucionDocumentoResultado other = (DevolucionDocumentoResultado) obj;
        
        if (!Objects.equals(this.idDevolucionDocumentoResultado, other.idDevolucionDocumentoResultado)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.corrige, other.corrige)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCorreccion, other.fechaCorreccion)) {
            return false;
        }
        
        if (!Objects.equals(this.observacionesRespuesta, other.observacionesRespuesta)) {
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
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

