package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

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
@Table(name="AGENDAMIENTO")
@NamedQuery(name = "Agendamiento.findAll", query = "SELECT p FROM Agendamiento p")
public class Agendamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AGENDAMIENTO_PK = "idAgendamiento";
	public static final String ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_AGENDAMIENTO_ID_SALA = "idSala";
	public static final String ENTIDAD_AGENDAMIENTO_HORA_INICIO = "horaInicio";
	public static final String ENTIDAD_AGENDAMIENTO_HORA_FIN = "horaFin";
	public static final String ENTIDAD_AGENDAMIENTO_TIPO_REUNION = "tipoReunion";
	public static final String ENTIDAD_AGENDAMIENTO_RESPONSABLE = "responsable";
	public static final String ENTIDAD_AGENDAMIENTO_CANTIDAD_DE_ASISTENTES = "cantidadDeAsistentes";
	public static final String ENTIDAD_AGENDAMIENTO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_AGENDAMIENTO_ID_CASO = "idCaso";
	public static final String ENTIDAD_AGENDAMIENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AGENDAMIENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO_AGENDAMIENTO = "estadoRegistroAgendamiento";			
	public static final String ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_AGENDAMIENTO
            = {ENTIDAD_AGENDAMIENTO_TIPO_REUNION, ENTIDAD_AGENDAMIENTO_ID_AUDIENCIA, ENTIDAD_AGENDAMIENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_AGENDAMIENTO_ID_USUARIO_MODIFICACION, ENTIDAD_AGENDAMIENTO_ID_SALA, ENTIDAD_AGENDAMIENTO_CANTIDAD_DE_ASISTENTES, ENTIDAD_AGENDAMIENTO_HORA_FIN, ENTIDAD_AGENDAMIENTO_DESCRIPCION, ENTIDAD_AGENDAMIENTO_RESPONSABLE, ENTIDAD_AGENDAMIENTO_ID_CASO, ENTIDAD_AGENDAMIENTO_PK, ENTIDAD_AGENDAMIENTO_HORA_INICIO, ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO};

	@Id
    @Column(name="id_agendamiento")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAgendamiento;
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="id_sala")
	private Long idSala;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_inicio")
	private Date horaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_fin")
	private Date horaFin;		
    
	@Column(name="tipo_reunion")
	private String tipoReunion;		
    
	@Column(name="responsable")
	private String responsable;		
    
	@Column(name="cantidad_de_asistentes")
	private Integer cantidadDeAsistentes;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_sala", referencedColumnName="id_sala", insertable = false, updatable = false)
    private Sala sala;
		
	@OneToMany(mappedBy="agendamiento")
    private List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList;
	@OneToMany(mappedBy="agendamiento")
    private List<LogisticaSolicitadaAgendamiento> logisticaSolicitadaAgendamientoList;
	
	
    public Agendamiento(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAgendamiento(){
		return this.idAgendamiento;
	}
	
	public void setIdAgendamiento(Long idAgendamiento){
		this.idAgendamiento = idAgendamiento;
	}
	
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public Long getIdSala(){
		return this.idSala;
	}
	
	public void setIdSala(Long idSala){
		this.idSala = idSala;
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
		
	public String getTipoReunion(){
		return this.tipoReunion;
	}
	
	public void setTipoReunion(String tipoReunion){
		this.tipoReunion = tipoReunion;
	}
		
	public String getResponsable(){
		return this.responsable;
	}
	
	public void setResponsable(String responsable){
		this.responsable = responsable;
	}
		
	public Integer getCantidadDeAsistentes(){
		return this.cantidadDeAsistentes;
	}
	
	public void setCantidadDeAsistentes(Integer cantidadDeAsistentes){
		this.cantidadDeAsistentes = cantidadDeAsistentes;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
		

    public List<InfraestructuraSolicitadaAgendamiento> getInfraestructuraSolicitadaAgendamientoList(){
		return this.infraestructuraSolicitadaAgendamientoList;
	}
	
	public void setInfraestructuraSolicitadaAgendamientoList(List<InfraestructuraSolicitadaAgendamiento> infraestructuraSolicitadaAgendamientoList){
		this.infraestructuraSolicitadaAgendamientoList = infraestructuraSolicitadaAgendamientoList;
	}
			
    public List<LogisticaSolicitadaAgendamiento> getLogisticaSolicitadaAgendamientoList(){
		return this.logisticaSolicitadaAgendamientoList;
	}
	
	public void setLogisticaSolicitadaAgendamientoList(List<LogisticaSolicitadaAgendamiento> logisticaSolicitadaAgendamientoList){
		this.logisticaSolicitadaAgendamientoList = logisticaSolicitadaAgendamientoList;
	}
			
    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public Sala getSala(){
		return this.sala; 
	}
	
	public void setSala(Sala sala){
		this.sala = sala;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AGENDAMIENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAgendamiento);        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.idSala);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoReunion);
        hash = 37 * hash + Objects.hashCode(this.responsable);
        hash = 37 * hash + Objects.hashCode(this.cantidadDeAsistentes);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Agendamiento que se pasa
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
        final Agendamiento other = (Agendamiento) obj;
        
        if (!Objects.equals(this.idAgendamiento, other.idAgendamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoReunion, other.tipoReunion)) {
            return false;
        }
        
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadDeAsistentes, other.cantidadDeAsistentes)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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

