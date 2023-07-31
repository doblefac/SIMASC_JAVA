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

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="LOTE_GENERADO")
@NamedQuery(name = "LoteGenerado.findAll", query = "SELECT p FROM LoteGenerado p")
public class LoteGenerado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AGENDAMIENTO_PK = "idAgendamiento";
	public static final String ENTIDAD_AGENDAMIENTO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_AGENDAMIENTO_NOMBRE_DOCUMENTO = "nombreDocumento";
	public static final String ENTIDAD_AGENDAMIENTO_ESTADO_GENERACION = "estadoGeneracion";
	public static final String ENTIDAD_AGENDAMIENTO_FECHA_GENERACION = "estadoGeneracion";
    private static final String[] ATRIBUTOS_ENTIDAD_AGENDAMIENTO
            = {ENTIDAD_AGENDAMIENTO_PK,ENTIDAD_AGENDAMIENTO_NOMBRE_DOCUMENTO,ENTIDAD_AGENDAMIENTO_ID_PERSONA,ENTIDAD_AGENDAMIENTO_FECHA_GENERACION,ENTIDAD_AGENDAMIENTO_ESTADO_GENERACION};

	@Id
    @Column(name="id_lote")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idLote;
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="nombre_documento")
	private String nombreDocumento;		
    
	@Column(name="estado_generacion")
	private String estadoGeneracion;		
	
	@Column(name="fecha_generacion")
	private Date fechaGeneracion;		
	

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
	
	@OneToMany(mappedBy="loteGenerado")
	private List<PersonaLote> personaLoteList;
		
	
	
	
    public LoteGenerado(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdLote(){
		return this.idLote;
	}
	
	public void setIdLote(Long idLote){
		this.idLote = idLote;
	}
	
	
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}


	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}


	/**
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}


	/**
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}


	/**
	 * @return the estadoGeneracion
	 */
	public String getEstadoGeneracion() {
		return estadoGeneracion;
	}


	/**
	 * @param estadoGeneracion the estadoGeneracion to set
	 */
	public void setEstadoGeneracion(String estadoGeneracion) {
		this.estadoGeneracion = estadoGeneracion;
	}


	/**
	 * @return the fechaGeneracion
	 */
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}


	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	
	

	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public List<PersonaLote> getPersonaLoteList() {
		return personaLoteList;
	}


	public void setPersonaLoteList(List<PersonaLote> personaLoteList) {
		this.personaLoteList = personaLoteList;
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
        
        hash = 37 * hash + Objects.hashCode(this.idLote);        
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.nombreDocumento);
        hash = 37 * hash + Objects.hashCode(this.fechaGeneracion);
        hash = 37 * hash + Objects.hashCode(this.estadoGeneracion);
       
        
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
        final LoteGenerado other = (LoteGenerado) obj;
        
        if (!Objects.equals(this.idLote, other.idLote)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreDocumento, other.nombreDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoGeneracion, other.estadoGeneracion)) {
            return false;
        }
        
        return Objects.equals(this.fechaGeneracion, other.fechaGeneracion);
                
    }


	
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

