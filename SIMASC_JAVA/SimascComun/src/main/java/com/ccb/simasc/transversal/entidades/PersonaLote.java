/**
 * 25/02/2019
 * @author jnmartinez
 */
package com.ccb.simasc.transversal.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author jnmartinez
 *
 */
@Entity
@Table(name="PERSONA_LOTE")
@NamedQuery(name = "PersonaLote.findAll", query = "SELECT p FROM PersonaLote p")
public class PersonaLote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Definicion de atributos de la entidad (Exceptuando relaciones)
	
	public static String ENTIDAD_PERSONA_LOTE_PK_ID_LOTE = "personaLotePK.idLote";
	public static String ENTIDAD_PERSONA_LOTE_PK_ID_PERSONA = "personaLotePK.idPersona";
	public static String ENTIDAD_PERSONA_LOTE_PK_ID_CASO = "personaLotePK.idCaso";
	public static String ENTIDAD_PERSONA_LOTE_NOMBRE_PERSONA = "nombrePersona";
	public static String ENTIDAD_PERSONA_LOTE_ROL = "rol";
	public static String ENTIDAD_PERSONA_LOTE_CORREO = "correo";
	public static String ENTIDAD_PERSONA_LOTE_ID_AUDIENCIA = "idAudiencia";
	private static final String[] ATRIBUTOS_ENTIDAD_PARTE_DE_LA_RECUSACION
    = {ENTIDAD_PERSONA_LOTE_ID_AUDIENCIA, ENTIDAD_PERSONA_LOTE_CORREO, ENTIDAD_PERSONA_LOTE_ROL, ENTIDAD_PERSONA_LOTE_NOMBRE_PERSONA, ENTIDAD_PERSONA_LOTE_PK_ID_CASO, ENTIDAD_PERSONA_LOTE_PK_ID_PERSONA, ENTIDAD_PERSONA_LOTE_PK_ID_LOTE};

	@EmbeddedId
	private PersonaLotePK personaLotePK;
	
	@Column(name="nombre_persona")
	private String nombrePersona;
	
	@Column(name="rol")
	private String rol;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="id_audiencia")
	private Long idAudiencia;
	
	@ManyToOne
	@JoinColumn(name ="id_lote", referencedColumnName="id_lote", insertable = false, updatable = false)
	private LoteGenerado loteGenerado;
	
	@ManyToOne
	@JoinColumn(name ="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
	private Audiencia audiencia;
	
	@ManyToOne
	@JoinColumn(name ="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name ="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
	private Caso caso;
	
	public PersonaLote() {
		personaLotePK = new PersonaLotePK();
	}
	
	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARTE_DE_LA_RECUSACION) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
	public PersonaLotePK getPersonaLotePK() {
		return personaLotePK;
	}

	public void setPersonaLotePK(PersonaLotePK personaLotePK) {
		this.personaLotePK = personaLotePK;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Long getIdAudiencia() {
		return idAudiencia;
	}

	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}

	public LoteGenerado getLoteGenerado() {
		return loteGenerado;
	}

	public void setLoteGenerado(LoteGenerado loteGenerado) {
		this.loteGenerado = loteGenerado;
	}

	public Audiencia getAudiencia() {
		return audiencia;
	}

	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	
	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.personaLotePK);
        hash = 37 * hash + Objects.hashCode(this.nombrePersona);
        hash = 37 * hash + Objects.hashCode(this.rol);
        hash = 37 * hash + Objects.hashCode(this.correo);
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteDeLaRecusacion que se pasa
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
        final PersonaLote other = (PersonaLote) obj;
        
        if (!Objects.equals(this.personaLotePK, other.personaLotePK)) {
            return false;
        }
        if (!Objects.equals(this.nombrePersona, other.nombrePersona)) {
            return false;
        }
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        
        return Objects.equals(this.idAudiencia, other.idAudiencia);
                
    }
	
	
}
