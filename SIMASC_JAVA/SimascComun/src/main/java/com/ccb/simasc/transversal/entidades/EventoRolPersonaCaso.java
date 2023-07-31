package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
@Table(name="EVENTO_ROL_PERSONA_CASO")
@NamedQuery(name = "EventoRolPersonaCaso.findAll", query = "SELECT p FROM EventoRolPersonaCaso p")
public class EventoRolPersonaCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_PK = "idEventoRolPersonaCaso";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO = "estadoAsignado";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_FECHA_DE_ASIGNACION = "fechaDeAsignacion";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO_EVENTOROLPERSONACASO = "estadoRegistroEventoRolPersonaCaso";			
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_ROL = "idRol";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_CASO = "idCaso";
	public static final String ENTIDAD_EVENTO_ROL_PERSONA_CASO_MOTIVO_INACTIVACION = "motivoInactivacion";
    private static final String[] ATRIBUTOS_ENTIDAD_EVENTO_ROL_PERSONA_CASO
            = {ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_ASIGNADO, ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_ROL, ENTIDAD_EVENTO_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO, ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_PERSONA, ENTIDAD_EVENTO_ROL_PERSONA_CASO_ID_CASO, ENTIDAD_EVENTO_ROL_PERSONA_CASO_PK, ENTIDAD_EVENTO_ROL_PERSONA_CASO_MOTIVO_INACTIVACION, ENTIDAD_EVENTO_ROL_PERSONA_CASO_FECHA_DE_ASIGNACION};

	@Id
    @Column(name="id_evento_rol_persona_caso")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idEventoRolPersonaCaso;
    
	@Column(name="estado_asignado")
	private String estadoAsignado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_asignacion")
	private Date fechaDeAsignacion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_rol")
	private Long idRol;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="motivo_inactivacion")
	private String motivoInactivacion;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	
	
    public EventoRolPersonaCaso(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdEventoRolPersonaCaso(){
		return this.idEventoRolPersonaCaso;
	}
	
	public void setIdEventoRolPersonaCaso(Long idEventoRolPersonaCaso){
		this.idEventoRolPersonaCaso = idEventoRolPersonaCaso;
	}
	
	public String getEstadoAsignado(){
		return this.estadoAsignado;
	}
	
	public void setEstadoAsignado(String estadoAsignado){
		this.estadoAsignado = estadoAsignado;
	}
		
	public Date getFechaDeAsignacion(){
		return this.fechaDeAsignacion;
	}
	
	public void setFechaDeAsignacion(Date fechaDeAsignacion){
		this.fechaDeAsignacion = fechaDeAsignacion;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getMotivoInactivacion(){
		return this.motivoInactivacion;
	}
	
	public void setMotivoInactivacion(String motivoInactivacion){
		this.motivoInactivacion = motivoInactivacion;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_EVENTO_ROL_PERSONA_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idEventoRolPersonaCaso);        
        hash = 37 * hash + Objects.hashCode(this.estadoAsignado);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAsignacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.motivoInactivacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EventoRolPersonaCaso que se pasa
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
        final EventoRolPersonaCaso other = (EventoRolPersonaCaso) obj;
        
        if (!Objects.equals(this.idEventoRolPersonaCaso, other.idEventoRolPersonaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoAsignado, other.estadoAsignado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAsignacion, other.fechaDeAsignacion)) {
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
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        return Objects.equals(this.motivoInactivacion, other.motivoInactivacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

