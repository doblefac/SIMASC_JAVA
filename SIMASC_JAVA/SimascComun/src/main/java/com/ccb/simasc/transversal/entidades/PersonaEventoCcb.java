package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
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
@Table(name="PERSONA_EVENTO_CCB")
@NamedQuery(name = "PersonaEventoCcb.findAll", query = "SELECT p FROM PersonaEventoCcb p")
public class PersonaEventoCcb implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_PK_ID_EVENTO_CCB = "personaEventoCcbPK.idEventoCcb";
			
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_PK_ID_PERSONA = "personaEventoCcbPK.idPersona";
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_ESTADO = "estado";
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_ESTADO_REGISTRO_PERSONAEVENTOCCB = "estadoRegistroPersonaEventoCcb";			
	public static final String ENTIDAD_PERSONA_EVENTO_CCB_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PERSONA_EVENTO_CCB
            = {ENTIDAD_PERSONA_EVENTO_CCB_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PERSONA_EVENTO_CCB_ID_USUARIO_MODIFICACION, ENTIDAD_PERSONA_EVENTO_CCB_ESTADO, ENTIDAD_PERSONA_EVENTO_CCB_PK_ID_EVENTO_CCB, ENTIDAD_PERSONA_EVENTO_CCB_PK_ID_PERSONA, ENTIDAD_PERSONA_EVENTO_CCB_ESTADO_REGISTRO};

	@EmbeddedId
	private PersonaEventoCcbPK personaEventoCcbPK;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_evento_ccb", referencedColumnName="id_evento_ccb", insertable = false, updatable = false)
    private EventoCcb eventoCcb;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public PersonaEventoCcb(){
		personaEventoCcbPK = new PersonaEventoCcbPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public PersonaEventoCcbPK getPersonaEventoCcbPK(){
		return this.personaEventoCcbPK;
	}
	
	public void setPersonaEventoCcbPK(PersonaEventoCcbPK personaEventoCcbPK){
		this.personaEventoCcbPK   = personaEventoCcbPK ;
	}  
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
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
		

    public EventoCcb getEventoCcb(){
		return this.eventoCcb; 
	}
	
	public void setEventoCcb(EventoCcb eventoCcb){
		this.eventoCcb = eventoCcb;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PERSONA_EVENTO_CCB) {
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
        
        hash = 37 * hash + Objects.hashCode(this.personaEventoCcbPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PersonaEventoCcb que se pasa
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
        final PersonaEventoCcb other = (PersonaEventoCcb) obj;
        
        if (!Objects.equals(this.personaEventoCcbPK, other.personaEventoCcbPK)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
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

