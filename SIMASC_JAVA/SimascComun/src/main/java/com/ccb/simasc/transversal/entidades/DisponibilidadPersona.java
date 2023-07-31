package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.sql.Time;
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
@Table(name="DISPONIBILIDAD_PERSONA")
@NamedQuery(name = "DisponibilidadPersona.findAll", query = "SELECT p FROM DisponibilidadPersona p")
public class DisponibilidadPersona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_PK = "idDisponibildadPersona";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_CODIGO = "codigo";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_HORA_INICIO = "horaInicio";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_HORA_FIN = "horaFin";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_ESTADO_REGISTRO_DISPONIBILIDADPERSONA = "estadoRegistroDisponibilidadPersona";			
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_DISPONIBILIDAD_PERSONA_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_DISPONIBILIDAD_PERSONA
            = {ENTIDAD_DISPONIBILIDAD_PERSONA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DISPONIBILIDAD_PERSONA_PK, ENTIDAD_DISPONIBILIDAD_PERSONA_HORA_FIN, ENTIDAD_DISPONIBILIDAD_PERSONA_ESTADO_REGISTRO, ENTIDAD_DISPONIBILIDAD_PERSONA_HORA_INICIO, ENTIDAD_DISPONIBILIDAD_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_DISPONIBILIDAD_PERSONA_CODIGO, ENTIDAD_DISPONIBILIDAD_PERSONA_ID_PERSONA};

	@Id
    @Column(name="id_disponibildad_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDisponibildadPersona;
    
	@Column(name="codigo")
	private String codigo;		
    
	@Column(name="hora_inicio")
	private Time horaInicio;		
    
	@Column(name="hora_fin")
	private Time horaFin;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona")
	private Long idPersona;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public DisponibilidadPersona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdDisponibildadPersona(){
		return this.idDisponibildadPersona;
	}
	
	public void setIdDisponibildadPersona(Long idDisponibildadPersona){
		this.idDisponibildadPersona = idDisponibildadPersona;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
		
	public Time getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Time horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Time getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Time horaFin){
		this.horaFin = horaFin;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_DISPONIBILIDAD_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idDisponibildadPersona);        
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DisponibilidadPersona que se pasa
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
        final DisponibilidadPersona other = (DisponibilidadPersona) obj;
        
        if (!Objects.equals(this.idDisponibildadPersona, other.idDisponibildadPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
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
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

