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
import javax.persistence.Transient;

import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TURNO_JORNADA")
@NamedQuery(name = "TurnoJornada.findAll", query = "SELECT p FROM TurnoJornada p")
public class TurnoJornada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Transient
	private String intervalo;
	@Transient
	private Long consecutivo;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TURNO_JORNADA_PK = "idTurnoJornada";
	public static final String ENTIDAD_TURNO_JORNADA_HORA_INICIO = "horaInicio";
	public static final String ENTIDAD_TURNO_JORNADA_HORA_FIN = "horaFin";
	public static final String ENTIDAD_TURNO_JORNADA_LIMITE_AUDIENCIAS = "limiteAudiencias";
	public static final String ENTIDAD_TURNO_JORNADA_ID_CONVENIO = "idConvenio";
	public static final String ENTIDAD_TURNO_JORNADA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TURNO_JORNADA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TURNO_JORNADA_ESTADO_REGISTRO_TURNOJORNADA = "estadoRegistroTurnoJornada";			
	public static final String ENTIDAD_TURNO_JORNADA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_TURNO_JORNADA
            = {ENTIDAD_TURNO_JORNADA_ID_CONVENIO, ENTIDAD_TURNO_JORNADA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TURNO_JORNADA_PK, ENTIDAD_TURNO_JORNADA_ESTADO_REGISTRO, ENTIDAD_TURNO_JORNADA_HORA_INICIO, ENTIDAD_TURNO_JORNADA_HORA_FIN, ENTIDAD_TURNO_JORNADA_LIMITE_AUDIENCIAS, ENTIDAD_TURNO_JORNADA_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_turno_jornada")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTurnoJornada;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_inicio")
	private Date horaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_fin")
	private Date horaFin;		
    
	@Column(name="limite_audiencias")
	private Integer limiteAudiencias;		
    
	@Column(name="id_convenio")
	private Long idConvenio;		
       
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_convenio", referencedColumnName="id_convenio", insertable = false, updatable = false)
    private Convenio convenio;
		
	@OneToMany(mappedBy="turnoJornada")
    private List<AudienciaTurnoJornada> audienciaTurnoJornadaList;
	
	
    public TurnoJornada(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTurnoJornada(){
		return this.idTurnoJornada;
	}
	
	public void setIdTurnoJornada(Long idTurnoJornada){
		this.idTurnoJornada = idTurnoJornada;
	}
	
	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Date getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Date horaFin){
		this.horaFin = horaFin;
	}
		
	public Integer getLimiteAudiencias(){
		return this.limiteAudiencias;
	}
	
	public void setLimiteAudiencias(Integer limiteAudiencias){
		this.limiteAudiencias = limiteAudiencias;
	}
		
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
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
		

    public List<AudienciaTurnoJornada> getAudienciaTurnoJornadaList(){
		return this.audienciaTurnoJornadaList;
	}
	
	public void setAudienciaTurnoJornadaList(List<AudienciaTurnoJornada> audienciaTurnoJornadaList){
		this.audienciaTurnoJornadaList = audienciaTurnoJornadaList;
	}
			
    public Convenio getConvenio(){
		return this.convenio; 
	}
	
	public void setConvenio(Convenio convenio){
		this.convenio = convenio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TURNO_JORNADA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTurnoJornada);        
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.limiteAudiencias);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TurnoJornada que se pasa
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
        final TurnoJornada other = (TurnoJornada) obj;
        
        if (!Objects.equals(this.idTurnoJornada, other.idTurnoJornada)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteAudiencias, other.limiteAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
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


	/**
	 * @return the intervalo
	 */
	public String getIntervalo() {
		return intervalo;
	}


	/**
	 * @param intervalo the intervalo to set
	 */
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}


	/**
	 * @return the consecutivo
	 */
	public Long getConsecutivo() {
		return consecutivo;
	}


	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	// protected region metodos adicionales end

} 

