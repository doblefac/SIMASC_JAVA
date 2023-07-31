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
@Table(name="HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO")
@NamedQuery(name = "HistoricoEstadoPersonaTipoServicio.findAll", query = "SELECT p FROM HistoricoEstadoPersonaTipoServicio p")
public class HistoricoEstadoPersonaTipoServicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_PK = "idHistoricoEstadoPersonaTs";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO = "estado";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_FECHA = "fecha";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO_HISTORICOESTADOPERSONATIPOSERVICIO = "estadoRegistroHistoricoEstadoPersonaTipoServicio";			
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO
            = {ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_PK, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ID_PERSONA, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ID_USUARIO_MODIFICACION, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_TIPO_SERVICIO, ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO_FECHA};

	@Id
    @Column(name="id_historico_estado_persona_ts")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHistoricoEstadoPersonaTs;
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="tipo_servicio")
	private String tipoServicio;		
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "tipo_servicio", referencedColumnName="tipo_servicio", insertable = false, updatable = false)	    
	})		
    private EstadoPersonaTipoServicio estadoPersonaTipoServicio;
		
	
	
    public HistoricoEstadoPersonaTipoServicio(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHistoricoEstadoPersonaTs(){
		return this.idHistoricoEstadoPersonaTs;
	}
	
	public void setIdHistoricoEstadoPersonaTs(Long idHistoricoEstadoPersonaTs){
		this.idHistoricoEstadoPersonaTs = idHistoricoEstadoPersonaTs;
	}
	
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
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
		

    public EstadoPersonaTipoServicio getEstadoPersonaTipoServicio(){
		return this.estadoPersonaTipoServicio; 
	}
	
	public void setEstadoPersonaTipoServicio(EstadoPersonaTipoServicio estadoPersonaTipoServicio){
		this.estadoPersonaTipoServicio = estadoPersonaTipoServicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_PERSONA_TIPO_SERVICIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoPersonaTs);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoEstadoPersonaTipoServicio que se pasa
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
        final HistoricoEstadoPersonaTipoServicio other = (HistoricoEstadoPersonaTipoServicio) obj;
        
        if (!Objects.equals(this.idHistoricoEstadoPersonaTs, other.idHistoricoEstadoPersonaTs)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

