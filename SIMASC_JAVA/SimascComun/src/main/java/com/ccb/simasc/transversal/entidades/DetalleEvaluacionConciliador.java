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
import javax.persistence.JoinColumns;
import javax.persistence.Temporal;
import java.math.BigDecimal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="DETALLE_EVALUACION_CONCILIADOR")
@NamedQuery(name = "DetalleEvaluacionConciliador.findAll", query = "SELECT p FROM DetalleEvaluacionConciliador p")
public class DetalleEvaluacionConciliador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_ID_EVALUACION_CONCILIADOR = "detalleEvaluacionConciliadorPK.idEvaluacionConciliador";
			
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_CODIGO_CRITERIO = "detalleEvaluacionConciliadorPK.codigoCriterio";
			
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_CODIGO_TOTAL = "detalleEvaluacionConciliadorPK.codigoTotal";
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_VALOR = "valor";
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_ESTADO_REGISTRO_DETALLEEVALUACIONCONCILIADOR = "estadoRegistroDetalleEvaluacionConciliador";			
	public static final String ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_DETALLE_EVALUACION_CONCILIADOR
            = {ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_CODIGO_TOTAL, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_CODIGO_CRITERIO, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_PK_ID_EVALUACION_CONCILIADOR, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_ID_USUARIO_MODIFICACION, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_VALOR, ENTIDAD_DETALLE_EVALUACION_CONCILIADOR_ESTADO_REGISTRO};

	@EmbeddedId
	private DetalleEvaluacionConciliadorPK detalleEvaluacionConciliadorPK;
    
	@Column(name="valor")
	private BigDecimal valor;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "codigo_criterio", referencedColumnName="codigo_criterio", insertable = false, updatable = false),
	    @JoinColumn(name = "codigo_total", referencedColumnName="codigo_total", insertable = false, updatable = false)	    
	})		
    private CriterioTotal criterioTotal;
		
	@ManyToOne
	@JoinColumn(name="id_evaluacion_conciliador", referencedColumnName="id_evaluacion_conciliador", insertable = false, updatable = false)
    private EvaluacionConciliador evaluacionConciliador;
		
	
	
    public DetalleEvaluacionConciliador(){
		detalleEvaluacionConciliadorPK = new DetalleEvaluacionConciliadorPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DetalleEvaluacionConciliadorPK getDetalleEvaluacionConciliadorPK(){
		return this.detalleEvaluacionConciliadorPK;
	}
	
	public void setDetalleEvaluacionConciliadorPK(DetalleEvaluacionConciliadorPK detalleEvaluacionConciliadorPK){
		this.detalleEvaluacionConciliadorPK   = detalleEvaluacionConciliadorPK ;
	}  
	
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
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
		

    public CriterioTotal getCriterioTotal(){
		return this.criterioTotal; 
	}
	
	public void setCriterioTotal(CriterioTotal criterioTotal){
		this.criterioTotal = criterioTotal;
	}
    public EvaluacionConciliador getEvaluacionConciliador(){
		return this.evaluacionConciliador; 
	}
	
	public void setEvaluacionConciliador(EvaluacionConciliador evaluacionConciliador){
		this.evaluacionConciliador = evaluacionConciliador;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DETALLE_EVALUACION_CONCILIADOR) {
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
        
        hash = 37 * hash + Objects.hashCode(this.detalleEvaluacionConciliadorPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetalleEvaluacionConciliador que se pasa
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
        final DetalleEvaluacionConciliador other = (DetalleEvaluacionConciliador) obj;
        
        if (!Objects.equals(this.detalleEvaluacionConciliadorPK, other.detalleEvaluacionConciliadorPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
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

