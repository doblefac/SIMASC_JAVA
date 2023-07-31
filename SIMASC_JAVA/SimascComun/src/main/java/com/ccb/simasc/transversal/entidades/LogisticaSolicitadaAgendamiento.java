package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name="LOGISTICA_SOLICITADA_AGENDAMIENTO")
@NamedQuery(name = "LogisticaSolicitadaAgendamiento.findAll", query = "SELECT p FROM LogisticaSolicitadaAgendamiento p")
public class LogisticaSolicitadaAgendamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_PK_ID_AGENDAMIENTO = "logisticaSolicitadaAgendamientoPK.idAgendamiento";
			
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_PK_CODIGO_LOGISTICA = "logisticaSolicitadaAgendamientoPK.codigoLogistica";
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_CANTIDAD = "cantidad";
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_ESTADO_REGISTRO_LOGISTICASOLICITADAAGENDAMIENTO = "estadoRegistroLogisticaSolicitadaAgendamiento";			
	public static final String ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO
            = {ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_PK_ID_AGENDAMIENTO, ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_PK_CODIGO_LOGISTICA, ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_ESTADO_REGISTRO, ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_CANTIDAD, ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO_ID_USUARIO_MODIFICACION};

	@EmbeddedId
	private LogisticaSolicitadaAgendamientoPK logisticaSolicitadaAgendamientoPK;
    
	@Column(name="cantidad")
	private Integer cantidad;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_agendamiento", referencedColumnName="id_agendamiento", insertable = false, updatable = false)
    private Agendamiento agendamiento;
		
	@ManyToOne
	@JoinColumn(name="codigo_logistica", referencedColumnName="codigo_logistica", insertable = false, updatable = false)
    private Logistica logistica;
		
	
	
    public LogisticaSolicitadaAgendamiento(){
		logisticaSolicitadaAgendamientoPK = new LogisticaSolicitadaAgendamientoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public LogisticaSolicitadaAgendamientoPK getLogisticaSolicitadaAgendamientoPK(){
		return this.logisticaSolicitadaAgendamientoPK;
	}
	
	public void setLogisticaSolicitadaAgendamientoPK(LogisticaSolicitadaAgendamientoPK logisticaSolicitadaAgendamientoPK){
		this.logisticaSolicitadaAgendamientoPK   = logisticaSolicitadaAgendamientoPK ;
	}  
	
	public Integer getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
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
		

    public Agendamiento getAgendamiento(){
		return this.agendamiento; 
	}
	
	public void setAgendamiento(Agendamiento agendamiento){
		this.agendamiento = agendamiento;
	}
    public Logistica getLogistica(){
		return this.logistica; 
	}
	
	public void setLogistica(Logistica logistica){
		this.logistica = logistica;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_LOGISTICA_SOLICITADA_AGENDAMIENTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.logisticaSolicitadaAgendamientoPK);        
        hash = 37 * hash + Objects.hashCode(this.cantidad);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LogisticaSolicitadaAgendamiento que se pasa
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
        final LogisticaSolicitadaAgendamiento other = (LogisticaSolicitadaAgendamiento) obj;
        
        if (!Objects.equals(this.logisticaSolicitadaAgendamientoPK, other.logisticaSolicitadaAgendamientoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidad, other.cantidad)) {
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

