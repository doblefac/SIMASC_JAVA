package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
import javax.persistence.JoinColumns;
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
@Table(name="PERSONA_SERVICIO_MATERIA")
@NamedQuery(name = "PersonaServicioMateria.findAll", query = "SELECT p FROM PersonaServicioMateria p")
public class PersonaServicioMateria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_PK = "idPersonaServicioMateria";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO = "estadoParaSorteo";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_INICIO_VIGENCIA = "fechaInicioVigencia";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_FIN_DE_VIGENCIA = "fechaFinDeVigencia";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_CANTIDAD_CASOS_ASIGNADOS = "cantidadCasosAsignados";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO_PERSONASERVICIOMATERIA = "estadoRegistroPersonaServicioMateria";			
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_ESPECIALIDAD = "idEspecialidad";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA = "idMateria";	
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_MOTIVO_ESTADO_SORTEO = "motivoEstadoSorteo";	
	public static final String ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_CASO = "idCaso";	
    private static final String[] ATRIBUTOS_ENTIDAD_PERSONA_SERVICIO_MATERIA
            = {ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA, ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_INICIO_VIGENCIA, ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_FIN_DE_VIGENCIA, ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION, ENTIDAD_PERSONA_SERVICIO_MATERIA_PK, ENTIDAD_PERSONA_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA, ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO, ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO, ENTIDAD_PERSONA_SERVICIO_MATERIA_CANTIDAD_CASOS_ASIGNADOS, ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO, ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_ESPECIALIDAD, ENTIDAD_PERSONA_SERVICIO_MATERIA_MOTIVO_ESTADO_SORTEO, ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_CASO};

	@Id
    @Column(name="id_persona_servicio_materia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPersonaServicioMateria;
    
	@Column(name="estado_para_sorteo")
	private String estadoParaSorteo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio_vigencia")
	private Date fechaInicioVigencia;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin_de_vigencia")
	private Date fechaFinDeVigencia;		
    
	@Column(name="cantidad_casos_asignados")
	private Long cantidadCasosAsignados;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_especialidad")
	private Long idEspecialidad;		
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_materia")
	private Long idMateria;		

	@Column(name="motivo_estado_sorteo")
	private String motivoEstadoSorteo;		
	
	@Column(name="id_caso")
	private Long idCaso;

	@ManyToOne
	@JoinColumn(name="id_especialidad", referencedColumnName="id_especialidad", insertable = false, updatable = false)
    private Especialidad especialidad;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)	    
	})		
    private ServicioMateria servicioMateria;
	
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;

	@OneToMany(mappedBy="personaServicioMateria")
    private List<HistoricoPersonaServicioMateria> historicoPersonaServicioMateriaList;
	
	
    public PersonaServicioMateria(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPersonaServicioMateria(){
		return this.idPersonaServicioMateria;
	}
	
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria){
		this.idPersonaServicioMateria = idPersonaServicioMateria;
	}
	
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
		
	public Date getFechaInicioVigencia(){
		return this.fechaInicioVigencia;
	}
	
	public void setFechaInicioVigencia(Date fechaInicioVigencia){
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
		
	public Date getFechaFinDeVigencia(){
		return this.fechaFinDeVigencia;
	}
	
	public void setFechaFinDeVigencia(Date fechaFinDeVigencia){
		this.fechaFinDeVigencia = fechaFinDeVigencia;
	}
		
	public Long getCantidadCasosAsignados(){
		return this.cantidadCasosAsignados;
	}
	
	public void setCantidadCasosAsignados(Long cantidadCasosAsignados){
		this.cantidadCasosAsignados = cantidadCasosAsignados;
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
		
	public Long getIdEspecialidad(){
		return this.idEspecialidad;
	}
	
	public void setIdEspecialidad(Long idEspecialidad){
		this.idEspecialidad = idEspecialidad;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
	
	public String getMotivoEstadoSorteo(){
		return this.motivoEstadoSorteo;
	}
	
	public void setMotivoEstadoSorteo(String motivoEstadoSorteo){
		this.motivoEstadoSorteo = motivoEstadoSorteo;
	}

	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}

    public List<HistoricoPersonaServicioMateria> getHistoricoPersonaServicioMateriaList(){
		return this.historicoPersonaServicioMateriaList;
	}
	
	public void setHistoricoPersonaServicioMateriaList(List<HistoricoPersonaServicioMateria> historicoPersonaServicioMateriaList){
		this.historicoPersonaServicioMateriaList = historicoPersonaServicioMateriaList;
	}
			
    public Especialidad getEspecialidad(){
		return this.especialidad; 
	}
	
	public void setEspecialidad(Especialidad especialidad){
		this.especialidad = especialidad;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public ServicioMateria getServicioMateria(){
		return this.servicioMateria; 
	}
	
	public void setServicioMateria(ServicioMateria servicioMateria){
		this.servicioMateria = servicioMateria;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PERSONA_SERVICIO_MATERIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPersonaServicioMateria);        
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinDeVigencia);
        hash = 37 * hash + Objects.hashCode(this.cantidadCasosAsignados);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idEspecialidad);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.motivoEstadoSorteo);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaServicioMateria que se pasa
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
        final PersonaServicioMateria other = (PersonaServicioMateria) obj;
        
        if (!Objects.equals(this.idPersonaServicioMateria, other.idPersonaServicioMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinDeVigencia, other.fechaFinDeVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadCasosAsignados, other.cantidadCasosAsignados)) {
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
        
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoEstadoSorteo, other.motivoEstadoSorteo)) {
            return false;
        }
		
		if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }

        return Objects.equals(this.idMateria, other.idMateria);        		
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

