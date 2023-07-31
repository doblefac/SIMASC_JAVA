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
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.math.BigDecimal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="EVALUACION_CONCILIADOR")
@NamedQuery(name = "EvaluacionConciliador.findAll", query = "SELECT p FROM EvaluacionConciliador p")
public class EvaluacionConciliador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_PK = "idEvaluacionConciliador";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_TOTAL_EVALUACION = "totalEvaluacion";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_DESDE = "periodoDesde";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_HASTA = "periodoHasta";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_ESTADO_REGISTRO_EVALUACIONCONCILIADOR = "estadoRegistroEvaluacionConciliador";			
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_EVALUACION_CONCILIADOR_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_EVALUACION_CONCILIADOR
            = {ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_DESDE, ENTIDAD_EVALUACION_CONCILIADOR_PERIODO_HASTA, ENTIDAD_EVALUACION_CONCILIADOR_PK, ENTIDAD_EVALUACION_CONCILIADOR_ID_PERSONA, ENTIDAD_EVALUACION_CONCILIADOR_ESTADO_REGISTRO, ENTIDAD_EVALUACION_CONCILIADOR_TOTAL_EVALUACION, ENTIDAD_EVALUACION_CONCILIADOR_FECHA_ULTIMA_MODIFICACION, ENTIDAD_EVALUACION_CONCILIADOR_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_evaluacion_conciliador")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idEvaluacionConciliador;
    
	@Column(name="total_evaluacion")
	private BigDecimal totalEvaluacion;		   
		
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="periodo_desde")
	private Date periodoDesde;		
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="periodo_hasta")
	private Date periodoHasta;	
    
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
		
	@OneToMany(mappedBy="evaluacionConciliador")
    private List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList;
	
	
    public EvaluacionConciliador(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdEvaluacionConciliador(){
		return this.idEvaluacionConciliador;
	}
	
	public void setIdEvaluacionConciliador(Long idEvaluacionConciliador){
		this.idEvaluacionConciliador = idEvaluacionConciliador;
	}
	
	public BigDecimal getTotalEvaluacion(){
		return this.totalEvaluacion;
	}
	
	public void setTotalEvaluacion(BigDecimal totalEvaluacion){
		this.totalEvaluacion = totalEvaluacion;
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
		

    public List<DetalleEvaluacionConciliador> getDetalleEvaluacionConciliadorList(){
		return this.detalleEvaluacionConciliadorList;
	}
	
	public void setDetalleEvaluacionConciliadorList(List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList){
		this.detalleEvaluacionConciliadorList = detalleEvaluacionConciliadorList;
	}
			
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	public Date getPeriodoDesde() {
		return periodoDesde;
	}

	public void setPeriodoDesde(Date periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	public Date getPeriodoHasta() {
		return periodoHasta;
	}

	public void setPeriodoHasta(Date periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_EVALUACION_CONCILIADOR) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idEvaluacionConciliador);        
        hash = 37 * hash + Objects.hashCode(this.totalEvaluacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.periodoDesde);
        hash = 37 * hash + Objects.hashCode(this.periodoHasta);        
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad EvaluacionConciliador que se pasa
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
        final EvaluacionConciliador other = (EvaluacionConciliador) obj;
        
        if (!Objects.equals(this.idEvaluacionConciliador, other.idEvaluacionConciliador)) {
            return false;
        }
        
        if (!Objects.equals(this.totalEvaluacion, other.totalEvaluacion)) {
            return false;
        }
        
        if (!Objects.equals(this.periodoDesde, other.periodoDesde)) {
            return false;
        }
        
        if (!Objects.equals(this.periodoHasta, other.periodoHasta)) {
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

