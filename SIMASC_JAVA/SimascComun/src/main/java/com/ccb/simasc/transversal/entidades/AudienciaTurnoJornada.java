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
@Table(name="AUDIENCIA_TURNO_JORNADA")
@NamedQuery(name = "AudienciaTurnoJornada.findAll", query = "SELECT p FROM AudienciaTurnoJornada p")
public class AudienciaTurnoJornada implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_PK_ID_AUDIENCIA = "audienciaTurnoJornadaPK.idAudiencia";
			
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_PK_ID_TURNO_JORNADA = "audienciaTurnoJornadaPK.idTurnoJornada";
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_ESTADO_REGISTRO_AUDIENCIATURNOJORNADA = "estadoRegistroAudienciaTurnoJornada";			
	public static final String ENTIDAD_AUDIENCIA_TURNO_JORNADA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_AUDIENCIA_TURNO_JORNADA
            = {ENTIDAD_AUDIENCIA_TURNO_JORNADA_PK_ID_AUDIENCIA, ENTIDAD_AUDIENCIA_TURNO_JORNADA_PK_ID_TURNO_JORNADA, ENTIDAD_AUDIENCIA_TURNO_JORNADA_ID_USUARIO_MODIFICACION, ENTIDAD_AUDIENCIA_TURNO_JORNADA_ESTADO_REGISTRO, ENTIDAD_AUDIENCIA_TURNO_JORNADA_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private AudienciaTurnoJornadaPK audienciaTurnoJornadaPK;
    
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
	@JoinColumn(name="id_turno_jornada", referencedColumnName="id_turno_jornada", insertable = false, updatable = false)
    private TurnoJornada turnoJornada;
		
	
	
    public AudienciaTurnoJornada(){
		audienciaTurnoJornadaPK = new AudienciaTurnoJornadaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public AudienciaTurnoJornadaPK getAudienciaTurnoJornadaPK(){
		return this.audienciaTurnoJornadaPK;
	}
	
	public void setAudienciaTurnoJornadaPK(AudienciaTurnoJornadaPK audienciaTurnoJornadaPK){
		this.audienciaTurnoJornadaPK   = audienciaTurnoJornadaPK ;
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
		

    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public TurnoJornada getTurnoJornada(){
		return this.turnoJornada; 
	}
	
	public void setTurnoJornada(TurnoJornada turnoJornada){
		this.turnoJornada = turnoJornada;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AUDIENCIA_TURNO_JORNADA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.audienciaTurnoJornadaPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AudienciaTurnoJornada que se pasa
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
        final AudienciaTurnoJornada other = (AudienciaTurnoJornada) obj;
        
        if (!Objects.equals(this.audienciaTurnoJornadaPK, other.audienciaTurnoJornadaPK)) {
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

