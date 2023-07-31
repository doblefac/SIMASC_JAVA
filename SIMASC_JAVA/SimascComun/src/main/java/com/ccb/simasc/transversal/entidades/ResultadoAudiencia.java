package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
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
@Table(name="RESULTADO_AUDIENCIA")
@NamedQuery(name = "ResultadoAudiencia.findAll", query = "SELECT p FROM ResultadoAudiencia p")
public class ResultadoAudiencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_PK = "idResultadoAudiencia";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_TIPO_RESULTADO_AUDIENCIA = "tipoResultadoAudiencia";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ESTADO = "estado";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ESTADO_REGISTRO_RESULTADOAUDIENCIA = "estadoRegistroResultadoAudiencia";			
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_RESULTADO_AUDIENCIA_ID_UBICACION = "idUbicacion";
    private static final String[] ATRIBUTOS_ENTIDAD_RESULTADO_AUDIENCIA
            = {ENTIDAD_RESULTADO_AUDIENCIA_ESTADO, ENTIDAD_RESULTADO_AUDIENCIA_ID_AUDIENCIA, ENTIDAD_RESULTADO_AUDIENCIA_TIPO_RESULTADO_AUDIENCIA, ENTIDAD_RESULTADO_AUDIENCIA_ESTADO_REGISTRO, ENTIDAD_RESULTADO_AUDIENCIA_ID_DOCUMENTO, ENTIDAD_RESULTADO_AUDIENCIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_RESULTADO_AUDIENCIA_PK, ENTIDAD_RESULTADO_AUDIENCIA_OBSERVACIONES, ENTIDAD_RESULTADO_AUDIENCIA_ID_USUARIO_MODIFICACION, ENTIDAD_RESULTADO_AUDIENCIA_ID_UBICACION};

	@Id
    @Column(name="id_resultado_audiencia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idResultadoAudiencia;
    
	@Column(name="tipo_resultado_audiencia")
	private String tipoResultadoAudiencia;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="id_ubicacion")
	private Long idUbicacion;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_ubicacion", referencedColumnName="id_ubicacion", insertable = false, updatable = false)
    private Ubicacion ubicacion;

	@OneToMany(mappedBy="resultadoAudiencia", cascade={CascadeType.REMOVE})
    private List<Obligacion> obligacionList;
	
	
    public ResultadoAudiencia(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdResultadoAudiencia(){
		return this.idResultadoAudiencia;
	}
	
	public void setIdResultadoAudiencia(Long idResultadoAudiencia){
		this.idResultadoAudiencia = idResultadoAudiencia;
	}
	
	public String getTipoResultadoAudiencia(){
		return this.tipoResultadoAudiencia;
	}
	
	public void setTipoResultadoAudiencia(String tipoResultadoAudiencia){
		this.tipoResultadoAudiencia = tipoResultadoAudiencia;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
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
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
			
    public List<Obligacion> getObligacionList(){
		return this.obligacionList;
	}
	
	public void setObligacionList(List<Obligacion> obligacionList){
		this.obligacionList = obligacionList;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_RESULTADO_AUDIENCIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idResultadoAudiencia);        
        hash = 37 * hash + Objects.hashCode(this.tipoResultadoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ResultadoAudiencia que se pasa
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
        final ResultadoAudiencia other = (ResultadoAudiencia) obj;
        
        if (!Objects.equals(this.idResultadoAudiencia, other.idResultadoAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoResultadoAudiencia, other.tipoResultadoAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
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
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.idUbicacion, other.idUbicacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

