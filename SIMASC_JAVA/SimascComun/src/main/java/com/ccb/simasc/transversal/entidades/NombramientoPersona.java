package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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

import com.fasterxml.jackson.annotation.JsonIgnore;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="NOMBRAMIENTO_PERSONA")
@NamedQuery(name = "NombramientoPersona.findAll", query = "SELECT p FROM NombramientoPersona p")
public class NombramientoPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_PK = "idNombramientoPersonas";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_ID_CASO = "idCaso";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_METODO_DE_NOMBRAMIENTO = "metodoDeNombramiento";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_CANT_FUNCIONARIOS_PRINCIPALES = "cantFuncionariosPrincipales";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_CANT_FUNCIONARIOS_SUPLENTES = "cantFuncionariosSuplentes";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_MANEJO_DE_SUPLENCIA = "manejoDeSuplencia";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO_NOMBRAMIENTOPERSONA = "estadoRegistroNombramientoPersona";			
	public static final String ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_NOMBRAMIENTO_PERSONA
            = {ENTIDAD_NOMBRAMIENTO_PERSONA_METODO_DE_NOMBRAMIENTO, ENTIDAD_NOMBRAMIENTO_PERSONA_ID_CASO, ENTIDAD_NOMBRAMIENTO_PERSONA_ESTADO_REGISTRO, ENTIDAD_NOMBRAMIENTO_PERSONA_CANT_FUNCIONARIOS_SUPLENTES, ENTIDAD_NOMBRAMIENTO_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_NOMBRAMIENTO_PERSONA_CANT_FUNCIONARIOS_PRINCIPALES, ENTIDAD_NOMBRAMIENTO_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_NOMBRAMIENTO_PERSONA_ID_PERSONA, ENTIDAD_NOMBRAMIENTO_PERSONA_PK, ENTIDAD_NOMBRAMIENTO_PERSONA_MANEJO_DE_SUPLENCIA};

	@Id
    @Column(name="id_nombramiento_personas")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idNombramientoPersonas;
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="metodo_de_nombramiento")
	private String metodoDeNombramiento;		
    
	@Column(name="cant_funcionarios_principales")
	private Integer cantFuncionariosPrincipales;		
    
	@Column(name="cant_funcionarios_suplentes")
	private Integer cantFuncionariosSuplentes;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="manejo_de_suplencia")
	private String manejoDeSuplencia;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public NombramientoPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdNombramientoPersonas(){
		return this.idNombramientoPersonas;
	}
	
	public void setIdNombramientoPersonas(Long idNombramientoPersonas){
		this.idNombramientoPersonas = idNombramientoPersonas;
	}
	
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getMetodoDeNombramiento(){
		return this.metodoDeNombramiento;
	}
	
	public void setMetodoDeNombramiento(String metodoDeNombramiento){
		this.metodoDeNombramiento = metodoDeNombramiento;
	}
		
	public Integer getCantFuncionariosPrincipales(){
		return this.cantFuncionariosPrincipales;
	}
	
	public void setCantFuncionariosPrincipales(Integer cantFuncionariosPrincipales){
		this.cantFuncionariosPrincipales = cantFuncionariosPrincipales;
	}
		
	public Integer getCantFuncionariosSuplentes(){
		return this.cantFuncionariosSuplentes;
	}
	
	public void setCantFuncionariosSuplentes(Integer cantFuncionariosSuplentes){
		this.cantFuncionariosSuplentes = cantFuncionariosSuplentes;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getManejoDeSuplencia(){
		return this.manejoDeSuplencia;
	}
	
	public void setManejoDeSuplencia(String manejoDeSuplencia){
		this.manejoDeSuplencia = manejoDeSuplencia;
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
		

    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_NOMBRAMIENTO_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idNombramientoPersonas);        
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.metodoDeNombramiento);
        hash = 37 * hash + Objects.hashCode(this.cantFuncionariosPrincipales);
        hash = 37 * hash + Objects.hashCode(this.cantFuncionariosSuplentes);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.manejoDeSuplencia);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad NombramientoPersona que se pasa
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
        final NombramientoPersona other = (NombramientoPersona) obj;
        
        if (!Objects.equals(this.idNombramientoPersonas, other.idNombramientoPersonas)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.metodoDeNombramiento, other.metodoDeNombramiento)) {
            return false;
        }
        
        if (!Objects.equals(this.cantFuncionariosPrincipales, other.cantFuncionariosPrincipales)) {
            return false;
        }
        
        if (!Objects.equals(this.cantFuncionariosSuplentes, other.cantFuncionariosSuplentes)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.manejoDeSuplencia, other.manejoDeSuplencia)) {
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
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

