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
@Table(name="HISTORICO_ESTADO_PERSONA_ROL")
@NamedQuery(name = "HistoricoEstadoPersonaRol.findAll", query = "SELECT p FROM HistoricoEstadoPersonaRol p")
public class HistoricoEstadoPersonaRol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_PK = "idHistoricoEstadoPersonaRol";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_ROL = "idRol";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_MOTIVO = "idMotivo";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA = "fecha";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ESTADO_REGISTRO_HISTORICOESTADOPERSONAROL = "estadoRegistroHistoricoEstadoPersonaRol";			
	public static final String ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL
            = {ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_PK, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_PERSONA, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_ROL
    	, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_MOTIVO, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_USUARIO_MODIFICACION
    	, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ESTADO_REGISTRO};

	@Id
    @Column(name="id_historico_estado_persona_rol")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHistoricoEstadoPersonaRol;
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_rol")
	private Long idRol;		
    
	@Column(name="id_motivo")
	private String idMotivo;		
    
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
	
	@Column(name="id_servicio")
	private Long idServicio;	

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private EstadoPersonaRol estadoPersonaRol;
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})	
    private Rol rol;
	
    public HistoricoEstadoPersonaRol(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHistoricoEstadoPersonaRol(){
		return this.idHistoricoEstadoPersonaRol;
	}
	
	public void setIdHistoricoEstadoPersonaRol(Long idHistoricoEstadoPersonaRol){
		this.idHistoricoEstadoPersonaRol = idHistoricoEstadoPersonaRol;
	}
	
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public String getIdMotivo(){
		return this.idMotivo;
	}
	
	public void setIdMotivo(String idMotivo){
		this.idMotivo = idMotivo;
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
		

    public EstadoPersonaRol getEstadoPersonaRol(){
		return this.estadoPersonaRol; 
	}
	
	public void setEstadoPersonaRol(EstadoPersonaRol estadoPersonaRol){
		this.estadoPersonaRol = estadoPersonaRol;
	}

    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}
	
	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoPersonaRol);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idMotivo);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoEstadoPersonaRol que se pasa
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
        final HistoricoEstadoPersonaRol other = (HistoricoEstadoPersonaRol) obj;
        
        if (!Objects.equals(this.idHistoricoEstadoPersonaRol, other.idHistoricoEstadoPersonaRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idMotivo, other.idMotivo)) {
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
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

