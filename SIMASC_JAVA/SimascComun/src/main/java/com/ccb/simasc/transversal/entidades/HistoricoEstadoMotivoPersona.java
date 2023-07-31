package com.ccb.simasc.transversal.entidades;

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

@Entity
@Table(name="HISTORICO_ESTADO_MOTIVO_PERSONA")
@NamedQuery(name = "HistoricoEstadoMotivoPersona.findAll", query = "SELECT p FROM HistoricoEstadoMotivoPersona p")
public class HistoricoEstadoMotivoPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_PK = "idHistoricoEstadoMotivoPersona";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_ROL = "idRol";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_MOTIVO = "idMotivo";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ESTADO_PARA_SORTEO = "estadoParaSorteo";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_FECHA = "fecha";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ESTADO_REGISTRO_HISTORICOESTADOMOTIVOPERSONA = "estadoRegistroHistoricoEstadoMotivoPersona";			
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_MOTIVO_ESTADO_SORTEO = "motivoEstadoSorteo";
    private static final String[] ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA
            = {ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_PK, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_PERSONA, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_ROL
    	, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_MOTIVO, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ESTADO_PARA_SORTEO, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_MATERIA
    	, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_FECHA, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ID_USUARIO_MODIFICACION
    	, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_ESTADO_REGISTRO, 
    	ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA_MOTIVO_ESTADO_SORTEO};

	@Id
    @Column(name="id_historico_estado_motivo_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHistoricoEstadoMotivoPersona;
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_rol")
	private Long idRol;		
    
	@Column(name="id_motivo")
	private String idMotivo;		
    
	@Column(name="estado_para_sorteo")
	private String estadoParaSorteo;		
    
	@Column(name="id_materia")
	private Long idMateria;		
    
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
	
	@Column(name="motivo_estado_sorteo")
	private String motivoEstadoSorteo;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})		
    private EstadoPersonaRol estadoPersonaRol;
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)	    
	})	
    private Rol rol;
	
    public HistoricoEstadoMotivoPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHistoricoEstadoMotivoPersona(){
		return this.idHistoricoEstadoMotivoPersona;
	}
	
	public void setIdHistoricoEstadoMotivoPersona(Long idHistoricoEstadoMotivoPersona){
		this.idHistoricoEstadoMotivoPersona = idHistoricoEstadoMotivoPersona;
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

	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
	
	public Long getIdMateria(){
		return this.idMateria;
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
	
	public String getmotivoEstadoSorteo(){
		return this.motivoEstadoSorteo;
	}
	
	public void setMotivoEstadoSorteo(String motivoEstadoSorteo){
		this.motivoEstadoSorteo = motivoEstadoSorteo;
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
	
	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HISTORICO_ESTADO_MOTIVO_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoEstadoMotivoPersona);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idMotivo);
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.motivoEstadoSorteo);
        
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
        final HistoricoEstadoMotivoPersona other = (HistoricoEstadoMotivoPersona) obj;
        
        if (!Objects.equals(this.idHistoricoEstadoMotivoPersona, other.idHistoricoEstadoMotivoPersona)) {
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
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }

        if (!Objects.equals(this.idMateria, other.idMateria)) {
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
        
        if (!Objects.equals(this.motivoEstadoSorteo, other.motivoEstadoSorteo)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
}
