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
@Table(name="ASESORIA")
@NamedQuery(name = "Asesoria.findAll", query = "SELECT p FROM Asesoria p")
public class Asesoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ASESORIA_PK = "idAsesoria";
	public static final String ENTIDAD_ASESORIA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_ASESORIA_TIPO_ASESORIA = "tipoAsesoria";
	public static final String ENTIDAD_ASESORIA_FECHA_ASESORIA = "fechaAsesoria";
	public static final String ENTIDAD_ASESORIA_CUANTIA = "cuantia";
	public static final String ENTIDAD_ASESORIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ASESORIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ASESORIA_ESTADO_REGISTRO_ASESORIA = "estadoRegistroAsesoria";			
	public static final String ENTIDAD_ASESORIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ASESORIA_ID_PERSONA_ASESORA = "idPersonaAsesora";
	public static final String ENTIDAD_ASESORIA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_ASESORIA_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_ASESORIA_ID_SERVICIO = "idServicio";
    private static final String[] ATRIBUTOS_ENTIDAD_ASESORIA
            = {ENTIDAD_ASESORIA_ID_MATERIA, ENTIDAD_ASESORIA_PK, ENTIDAD_ASESORIA_ID_USUARIO_MODIFICACION, ENTIDAD_ASESORIA_TIPO_ASESORIA, ENTIDAD_ASESORIA_OBSERVACIONES, ENTIDAD_ASESORIA_ID_SERVICIO, ENTIDAD_ASESORIA_FECHA_ASESORIA, ENTIDAD_ASESORIA_CUANTIA, ENTIDAD_ASESORIA_ID_PERSONA_ASESORA, ENTIDAD_ASESORIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ASESORIA_ID_PERSONA, ENTIDAD_ASESORIA_ESTADO_REGISTRO};

	
	@Id
    @Column(name="id_asesoria")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAsesoria;
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="tipo_asesoria")
	private String tipoAsesoria;		
	
	@Column(name="fecha_asesoria")
	private Date fechaAsesoria;	
    
	@Column(name="cuantia")
	private Long cuantia;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona_asesora")
	private Long idPersonaAsesora;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_materia")
	private Long idMateria;		
	
	@Column(name="id_servicio")
	private Long idServicio;	

	@ManyToOne
	@JoinColumn(name="id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)
    private Materia materia;
		
	@ManyToOne
	@JoinColumn(name="id_persona_asesora", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona personaAsesora;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
	private Servicio servicio;
		
	
	
    public Asesoria(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAsesoria(){
		return this.idAsesoria;
	}
	
	public void setIdAsesoria(Long idAsesoria){
		this.idAsesoria = idAsesoria;
	}
	
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public String getTipoAsesoria(){
		return this.tipoAsesoria;
	}
	
	public void setTipoAsesoria(String tipoAsesoria){
		this.tipoAsesoria = tipoAsesoria;
	}
	
	public Date getFechaAsesoria() {
		return fechaAsesoria;
	}

	public void setFechaAsesoria(Date fechaAsesoria) {
		this.fechaAsesoria = fechaAsesoria;
	}
		
	public Long getCuantia(){
		return this.cuantia;
	}
	
	public void setCuantia(Long cuantia){
		this.cuantia = cuantia;
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
		
	public Long getIdPersonaAsesora(){
		return this.idPersonaAsesora;
	}
	
	public void setIdPersonaAsesora(Long idPersonaAsesora){
		this.idPersonaAsesora = idPersonaAsesora;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		

    public Materia getMateria(){
		return this.materia; 
	}
	
	public void setMateria(Materia materia){
		this.materia = materia;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public Persona getPersonaAsesora(){
		return this.personaAsesora; 
	}
	
	public void setPersonaAsesora(Persona persona){
		this.personaAsesora = persona;
	}
	
    public Servicio getServicio(){
    	return this.servicio; 
    }

    public void setServicio(Servicio servicio){
    	this.servicio = servicio;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_ASESORIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAsesoria);        
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.tipoAsesoria);
        hash = 37 * hash + Objects.hashCode(this.fechaAsesoria);
        hash = 37 * hash + Objects.hashCode(this.cuantia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersonaAsesora);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Asesoria que se pasa
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
        final Asesoria other = (Asesoria) obj;
        
        if (!Objects.equals(this.idAsesoria, other.idAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAsesoria, other.tipoAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaAsesoria, other.fechaAsesoria)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantia, other.cuantia)) {
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
        
        if (!Objects.equals(this.idPersonaAsesora, other.idPersonaAsesora)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.idMateria, other.idMateria);
                
    }





	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

