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
@Table(name="HISTORICO_EXPEDIENTE")
@NamedQuery(name = "HistoricoExpediente.findAll", query = "SELECT p FROM HistoricoExpediente p")
public class HistoricoExpediente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_PK = "idHistoricoExpediente";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_FECHA = "fecha";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_ESTADO_REGISTRO_HISTORICOEXPEDIENTE = "estadoRegistroHistoricoExpediente";			
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_ID_CASO = "idCaso";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_HISTORICO_EXPEDIENTE_TIPO_ENTREGA = "tipoEntrega";
    private static final String[] ATRIBUTOS_ENTIDAD_HISTORICO_EXPEDIENTE
            = {ENTIDAD_HISTORICO_EXPEDIENTE_ID_CASO, ENTIDAD_HISTORICO_EXPEDIENTE_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HISTORICO_EXPEDIENTE_ID_USUARIO_MODIFICACION, ENTIDAD_HISTORICO_EXPEDIENTE_ID_PERSONA, ENTIDAD_HISTORICO_EXPEDIENTE_FECHA, ENTIDAD_HISTORICO_EXPEDIENTE_OBSERVACIONES, ENTIDAD_HISTORICO_EXPEDIENTE_PK, ENTIDAD_HISTORICO_EXPEDIENTE_ESTADO_REGISTRO, ENTIDAD_HISTORICO_EXPEDIENTE_TIPO_ENTREGA};

	@Id
    @Column(name="id_historico_expediente")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHistoricoExpediente;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_persona")
	private Long idPersona;	
	
	@Column(name="tipo_entrega")
	private String tipoEntrega;

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public HistoricoExpediente(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHistoricoExpediente(){
		return this.idHistoricoExpediente;
	}
	
	public void setIdHistoricoExpediente(Long idHistoricoExpediente){
		this.idHistoricoExpediente = idHistoricoExpediente;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
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
	
	public String getTipoEntrega(){
		return this.tipoEntrega;
	}
	
	public void setTipoEntrega(String tipoEntrega){
		this.tipoEntrega = tipoEntrega;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HISTORICO_EXPEDIENTE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoExpediente);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoEntrega);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoExpediente que se pasa
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
        final HistoricoExpediente other = (HistoricoExpediente) obj;
        
        if (!Objects.equals(this.idHistoricoExpediente, other.idHistoricoExpediente)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.tipoEntrega, other.tipoEntrega)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

