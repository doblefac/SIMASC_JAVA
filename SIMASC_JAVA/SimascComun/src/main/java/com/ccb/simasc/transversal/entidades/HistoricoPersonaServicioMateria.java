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
@Table(name="HISTORICO_PERSONA_SERVICIO_MATERIA")
@NamedQuery(name = "HistoricoPersonaServicioMateria.findAll", query = "SELECT p FROM HistoricoPersonaServicioMateria p")
public class HistoricoPersonaServicioMateria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_PK = "idHistoricoPersonaServicioMateria";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO = "estadoParaSorteo";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_FECHA_ASIGNACION_ESTADO = "fechaAsignacionEstado";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_PERSONA_SERVICIO_MATERIA = "idPersonaServicioMateria";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO_HISTORICOPERSONASERVICIOMATERIA = "estadoRegistroHistoricoPersonaServicioMateria";			
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MOTIVO_ESTADO_SORTEO = "motivoEstadoSorteo";
    public static final String ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_CASO = "idCaso";
    private static final String[] ATRIBUTOS_ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA
            = {ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_FECHA_ASIGNACION_ESTADO, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_PERSONA_SERVICIO_MATERIA, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_PK, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_OBSERVACIONES, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_USUARIO_MODIFICACION, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MOTIVO_ESTADO_SORTEO, ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA_ID_CASO};

	@Id
    @Column(name="id_historico_persona_servicio_materia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHistoricoPersonaServicioMateria;
    
	@Column(name="estado_para_sorteo")
	private String estadoParaSorteo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_asignacion_estado")
	private Date fechaAsignacionEstado;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_persona_servicio_materia")
	private Long idPersonaServicioMateria;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;	
	
	@Column(name="motivo_estado_sorteo")
	private String motivoEstadoSorteo;	

	@Column(name="id_caso")
	private Long idCaso;	
	

	@ManyToOne
	@JoinColumn(name="id_persona_servicio_materia", referencedColumnName="id_persona_servicio_materia", insertable = false, updatable = false)
    private PersonaServicioMateria personaServicioMateria;
		
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
	
    public HistoricoPersonaServicioMateria(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHistoricoPersonaServicioMateria(){
		return this.idHistoricoPersonaServicioMateria;
	}
	
	public void setIdHistoricoPersonaServicioMateria(Long idHistoricoPersonaServicioMateria){
		this.idHistoricoPersonaServicioMateria = idHistoricoPersonaServicioMateria;
	}
	
	public String getEstadoParaSorteo(){
		return this.estadoParaSorteo;
	}
	
	public void setEstadoParaSorteo(String estadoParaSorteo){
		this.estadoParaSorteo = estadoParaSorteo;
	}
		
	public Date getFechaAsignacionEstado(){
		return this.fechaAsignacionEstado;
	}
	
	public void setFechaAsignacionEstado(Date fechaAsignacionEstado){
		this.fechaAsignacionEstado = fechaAsignacionEstado;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Long getIdPersonaServicioMateria(){
		return this.idPersonaServicioMateria;
	}
	
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria){
		this.idPersonaServicioMateria = idPersonaServicioMateria;
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

	public String getMotivoEstadoSorteo(){
		return this.motivoEstadoSorteo;
	}
	
	public void setMotivoEstadoSorteo(String motivoEstadoSorteo){
		this.motivoEstadoSorteo = motivoEstadoSorteo;
	}

    public PersonaServicioMateria getPersonaServicioMateria(){
		return this.personaServicioMateria; 
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
	
	public void setPersonaServicioMateria(PersonaServicioMateria personaServicioMateria){
		this.personaServicioMateria = personaServicioMateria;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HISTORICO_PERSONA_SERVICIO_MATERIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHistoricoPersonaServicioMateria);        
        hash = 37 * hash + Objects.hashCode(this.estadoParaSorteo);
        hash = 37 * hash + Objects.hashCode(this.fechaAsignacionEstado);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idPersonaServicioMateria);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.motivoEstadoSorteo);
        hash = 37 * hash + Objects.hashCode(this.idCaso);

        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HistoricoPersonaServicioMateria que se pasa
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
        final HistoricoPersonaServicioMateria other = (HistoricoPersonaServicioMateria) obj;
        
        if (!Objects.equals(this.idHistoricoPersonaServicioMateria, other.idHistoricoPersonaServicioMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoParaSorteo, other.estadoParaSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsignacionEstado, other.fechaAsignacionEstado)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaServicioMateria, other.idPersonaServicioMateria)) {
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
        
		if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
		
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);       
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

