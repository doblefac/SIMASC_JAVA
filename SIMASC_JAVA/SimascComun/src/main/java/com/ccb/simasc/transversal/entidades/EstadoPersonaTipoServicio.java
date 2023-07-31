package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="ESTADO_PERSONA_TIPO_SERVICIO")
@NamedQuery(name = "EstadoPersonaTipoServicio.findAll", query = "SELECT p FROM EstadoPersonaTipoServicio p")
public class EstadoPersonaTipoServicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@Transient
	private Date fechaAsignacion;

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_PK_ID_PERSONA = "estadoPersonaTipoServicioPK.idPersona";
			
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_PK_TIPO_SERVICIO = "estadoPersonaTipoServicioPK.tipoServicio";
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO = "estado";
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO_ESTADOPERSONATIPOSERVICIO = "estadoRegistroEstadoPersonaTipoServicio";			
	public static final String ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO
            = {ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_PK_TIPO_SERVICIO, ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO, ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ID_USUARIO_MODIFICACION, ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_PK_ID_PERSONA, ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO_ESTADO_REGISTRO};

	@EmbeddedId
	private EstadoPersonaTipoServicioPK estadoPersonaTipoServicioPK;
    
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
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@OneToMany(mappedBy="estadoPersonaTipoServicio")
    private List<HistoricoEstadoPersonaTipoServicio> historicoEstadoPersonaTipoServicioList;
	
	
    public EstadoPersonaTipoServicio(){
		estadoPersonaTipoServicioPK = new EstadoPersonaTipoServicioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public EstadoPersonaTipoServicioPK getEstadoPersonaTipoServicioPK(){
		return this.estadoPersonaTipoServicioPK;
	}
	
	public void setEstadoPersonaTipoServicioPK(EstadoPersonaTipoServicioPK estadoPersonaTipoServicioPK){
		this.estadoPersonaTipoServicioPK   = estadoPersonaTipoServicioPK ;
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
		

    public List<HistoricoEstadoPersonaTipoServicio> getHistoricoEstadoPersonaTipoServicioList(){
		return this.historicoEstadoPersonaTipoServicioList;
	}
	
	public void setHistoricoEstadoPersonaTipoServicioList(List<HistoricoEstadoPersonaTipoServicio> historicoEstadoPersonaTipoServicioList){
		this.historicoEstadoPersonaTipoServicioList = historicoEstadoPersonaTipoServicioList;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_ESTADO_PERSONA_TIPO_SERVICIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.estadoPersonaTipoServicioPK);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EstadoPersonaTipoServicio que se pasa
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
        final EstadoPersonaTipoServicio other = (EstadoPersonaTipoServicio) obj;
        
        if (!Objects.equals(this.estadoPersonaTipoServicioPK, other.estadoPersonaTipoServicioPK)) {
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


	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}


	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
    
	// protected region metodos adicionales end

} 

