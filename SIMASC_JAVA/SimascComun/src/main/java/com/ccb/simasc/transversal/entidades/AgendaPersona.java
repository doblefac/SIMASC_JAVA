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
@Table(name="AGENDA_PERSONA")
@NamedQuery(name = "AgendaPersona.findAll", query = "SELECT p FROM AgendaPersona p")
public class AgendaPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AGENDA_PERSONA_PK = "idAgendaPersona";
	public static final String ENTIDAD_AGENDA_PERSONA_ESTADO = "estado";
	public static final String ENTIDAD_AGENDA_PERSONA_TIPO_ACTIVIDAD_AGENDA = "tipoActividadAgenda";
	public static final String ENTIDAD_AGENDA_PERSONA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_AGENDA_PERSONA_FECHA_INICIO = "fechaInicio";
	public static final String ENTIDAD_AGENDA_PERSONA_FECHA_FIN = "fechaFin";
	public static final String ENTIDAD_AGENDA_PERSONA_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_AGENDA_PERSONA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_AGENDA_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AGENDA_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AGENDA_PERSONA_ESTADO_REGISTRO_AGENDAPERSONA = "estadoRegistroAgendaPersona";			
	public static final String ENTIDAD_AGENDA_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_AGENDA_PERSONA_CUMPLIO_HORARIO = "cumplioHorario";
	public static final String ENTIDAD_AGENDA_PERSONA_JUSTIFICACION_VALIDA = "justificacionValida";
	
	
	
    private static final String[] ATRIBUTOS_ENTIDAD_AGENDA_PERSONA
            = {ENTIDAD_AGENDA_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_AGENDA_PERSONA_ESTADO, ENTIDAD_AGENDA_PERSONA_OBSERVACIONES, ENTIDAD_AGENDA_PERSONA_PK, ENTIDAD_AGENDA_PERSONA_FECHA_FIN, ENTIDAD_AGENDA_PERSONA_ESTADO_REGISTRO, ENTIDAD_AGENDA_PERSONA_ID_PERSONA, ENTIDAD_AGENDA_PERSONA_FECHA_INICIO, ENTIDAD_AGENDA_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_AGENDA_PERSONA_TIPO_ACTIVIDAD_AGENDA,ENTIDAD_AGENDA_PERSONA_CUMPLIO_HORARIO,ENTIDAD_AGENDA_PERSONA_JUSTIFICACION_VALIDA, ENTIDAD_AGENDA_PERSONA_ID_AUDIENCIA};

	@Id
    @Column(name="id_agenda_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAgendaPersona;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="tipo_actividad_agenda")
	private String tipoActividadAgenda;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;		
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;	
	
	@Column(name="cumplio_horario")
	private Boolean cumplioHorario;	
	
	@Column(name="justificacion_valida")
	private Boolean justificacionValida;	

    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public AgendaPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAgendaPersona(){
		return this.idAgendaPersona;
	}
	
	public void setIdAgendaPersona(Long idAgendaPersona){
		this.idAgendaPersona = idAgendaPersona;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getTipoActividadAgenda(){
		return this.tipoActividadAgenda;
	}
	
	public void setTipoActividadAgenda(String tipoActividadAgenda){
		this.tipoActividadAgenda = tipoActividadAgenda;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
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
		

    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
	
	public Boolean getCumplioHorario() {
		return cumplioHorario;
	}

	public void setCumplioHorario(Boolean cumpliohorario) {
		this.cumplioHorario = cumpliohorario;
	}

	public Boolean getJustificacionValida() {
		return justificacionValida;
	}

	public void setJustificacionValida(Boolean justificacionValida) {
		this.justificacionValida = justificacionValida;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AGENDA_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAgendaPersona);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.tipoActividadAgenda);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.cumplioHorario);
        hash = 37 * hash + Objects.hashCode(this.justificacionValida);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AgendaPersona que se pasa
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
        final AgendaPersona other = (AgendaPersona) obj;
        
        if (!Objects.equals(this.idAgendaPersona, other.idAgendaPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActividadAgenda, other.tipoActividadAgenda)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
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
        
        if (!Objects.equals(this.cumplioHorario, other.cumplioHorario)) {
            return false;
        }
        
        if (!Objects.equals(this.justificacionValida, other.justificacionValida)) {
            return false;
        }        

        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }






	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

