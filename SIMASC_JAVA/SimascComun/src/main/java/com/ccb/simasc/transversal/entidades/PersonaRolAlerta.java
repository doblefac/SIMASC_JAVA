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
@Table(name="PERSONA_ROL_ALERTA")
@NamedQuery(name = "PersonaRolAlerta.findAll", query = "SELECT p FROM PersonaRolAlerta p")
public class PersonaRolAlerta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_PK = "idPersonaRolAlerta";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ESTADO_REGISTRO_PERSONAROLALERTA = "estadoRegistroPersonaRolAlerta";			
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ID_ALERTA = "idAlerta";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_PERSONA_ROL_ALERTA_ID_ROL = "idRol";
    private static final String[] ATRIBUTOS_ENTIDAD_PERSONA_ROL_ALERTA
            = {ENTIDAD_PERSONA_ROL_ALERTA_ESTADO_REGISTRO, ENTIDAD_PERSONA_ROL_ALERTA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PERSONA_ROL_ALERTA_ID_PERSONA, ENTIDAD_PERSONA_ROL_ALERTA_ID_USUARIO_MODIFICACION, ENTIDAD_PERSONA_ROL_ALERTA_ID_ALERTA, ENTIDAD_PERSONA_ROL_ALERTA_PK, ENTIDAD_PERSONA_ROL_ALERTA_ID_ROL};

	@Id
    @Column(name="id_persona_rol_alerta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPersonaRolAlerta;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_alerta")
	private Long idAlerta;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_rol")
	private Long idRol;		

	@ManyToOne
	@JoinColumn(name="id_alerta", referencedColumnName="id_alerta", insertable = false, updatable = false)
    private Alerta alerta;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	
	
    public PersonaRolAlerta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPersonaRolAlerta(){
		return this.idPersonaRolAlerta;
	}
	
	public void setIdPersonaRolAlerta(Long idPersonaRolAlerta){
		this.idPersonaRolAlerta = idPersonaRolAlerta;
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
		
	public Long getIdAlerta(){
		return this.idAlerta;
	}
	
	public void setIdAlerta(Long idAlerta){
		this.idAlerta = idAlerta;
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
		

    public Alerta getAlerta(){
		return this.alerta; 
	}
	
	public void setAlerta(Alerta alerta){
		this.alerta = alerta;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PERSONA_ROL_ALERTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPersonaRolAlerta);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAlerta);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaRolAlerta que se pasa
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
        final PersonaRolAlerta other = (PersonaRolAlerta) obj;
        
        if (!Objects.equals(this.idPersonaRolAlerta, other.idPersonaRolAlerta)) {
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
        
        if (!Objects.equals(this.idAlerta, other.idAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

