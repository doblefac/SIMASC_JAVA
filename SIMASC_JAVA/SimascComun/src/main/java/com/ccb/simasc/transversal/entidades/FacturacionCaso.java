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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.util.Date;
import java.math.BigDecimal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="FACTURACION_CASO")
@NamedQuery(name = "FacturacionCaso.findAll", query = "SELECT p FROM FacturacionCaso p")
public class FacturacionCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_FACTURACION_CASO_PK = "idFacturacionCaso";
	public static final String ENTIDAD_FACTURACION_CASO_FECHA_DE_FACTURACION = "fechaDeFacturacion";
	public static final String ENTIDAD_FACTURACION_CASO_VALOR = "valor";
	public static final String ENTIDAD_FACTURACION_CASO_APROBADO = "aprobado";
	public static final String ENTIDAD_FACTURACION_CASO_VALOR_COBROS_ADICIONALES = "valorCobrosAdicionales";
	public static final String ENTIDAD_FACTURACION_CASO_VALOR_TOTAL = "valorTotal";
	public static final String ENTIDAD_FACTURACION_CASO_FECHA_DE_APROBACION = "fechaDeAprobacion";
	public static final String ENTIDAD_FACTURACION_CASO_COBRADO = "cobrado";
	public static final String ENTIDAD_FACTURACION_CASO_FECHA_DE_COBRO = "fechaDeCobro";
	public static final String ENTIDAD_FACTURACION_CASO_ID_CASO = "idCaso";
	public static final String ENTIDAD_FACTURACION_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_FACTURACION_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_FACTURACION_CASO_ESTADO_REGISTRO_FACTURACIONCASO = "estadoRegistroFacturacionCaso";			
	public static final String ENTIDAD_FACTURACION_CASO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_FACTURACION_CASO
            = {ENTIDAD_FACTURACION_CASO_FECHA_DE_FACTURACION, ENTIDAD_FACTURACION_CASO_ESTADO_REGISTRO, ENTIDAD_FACTURACION_CASO_COBRADO, ENTIDAD_FACTURACION_CASO_VALOR_TOTAL, ENTIDAD_FACTURACION_CASO_FECHA_DE_APROBACION, ENTIDAD_FACTURACION_CASO_ID_CASO, ENTIDAD_FACTURACION_CASO_FECHA_DE_COBRO, ENTIDAD_FACTURACION_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_FACTURACION_CASO_APROBADO, ENTIDAD_FACTURACION_CASO_VALOR_COBROS_ADICIONALES, ENTIDAD_FACTURACION_CASO_PK, ENTIDAD_FACTURACION_CASO_VALOR, ENTIDAD_FACTURACION_CASO_FECHA_ULTIMA_MODIFICACION};

	@Id
    @Column(name="id_facturacion_caso")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idFacturacionCaso;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_facturacion")
	private Date fechaDeFacturacion;		
    
	@Column(name="valor")
	private BigDecimal valor;		
    
	@Column(name="aprobado")
	private boolean aprobado;		
    
	@Column(name="valor_cobros_adicionales")
	private BigDecimal valorCobrosAdicionales;		
    
	@Column(name="valor_total")
	private BigDecimal valorTotal;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_aprobacion")
	private Date fechaDeAprobacion;		
    
	@Column(name="cobrado")
	private boolean cobrado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_cobro")
	private Date fechaDeCobro;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	
	
    public FacturacionCaso(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdFacturacionCaso(){
		return this.idFacturacionCaso;
	}
	
	public void setIdFacturacionCaso(Long idFacturacionCaso){
		this.idFacturacionCaso = idFacturacionCaso;
	}
	
	public Date getFechaDeFacturacion(){
		return this.fechaDeFacturacion;
	}
	
	public void setFechaDeFacturacion(Date fechaDeFacturacion){
		this.fechaDeFacturacion = fechaDeFacturacion;
	}
		
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
	}
		
	public boolean getAprobado(){
		return this.aprobado;
	}
	
	public void setAprobado(boolean aprobado){
		this.aprobado = aprobado;
	}
		
	public BigDecimal getValorCobrosAdicionales(){
		return this.valorCobrosAdicionales;
	}
	
	public void setValorCobrosAdicionales(BigDecimal valorCobrosAdicionales){
		this.valorCobrosAdicionales = valorCobrosAdicionales;
	}
		
	public BigDecimal getValorTotal(){
		return this.valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal){
		this.valorTotal = valorTotal;
	}
		
	public Date getFechaDeAprobacion(){
		return this.fechaDeAprobacion;
	}
	
	public void setFechaDeAprobacion(Date fechaDeAprobacion){
		this.fechaDeAprobacion = fechaDeAprobacion;
	}
		
	public boolean getCobrado(){
		return this.cobrado;
	}
	
	public void setCobrado(boolean cobrado){
		this.cobrado = cobrado;
	}
		
	public Date getFechaDeCobro(){
		return this.fechaDeCobro;
	}
	
	public void setFechaDeCobro(Date fechaDeCobro){
		this.fechaDeCobro = fechaDeCobro;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_FACTURACION_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idFacturacionCaso);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeFacturacion);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + (this.aprobado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.valorCobrosAdicionales);
        hash = 37 * hash + Objects.hashCode(this.valorTotal);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAprobacion);
        hash = 37 * hash + (this.cobrado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCobro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FacturacionCaso que se pasa
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
        final FacturacionCaso other = (FacturacionCaso) obj;
        
        if (!Objects.equals(this.idFacturacionCaso, other.idFacturacionCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeFacturacion, other.fechaDeFacturacion)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.aprobado, other.aprobado)) {
            return false;
        }
        
        if (!Objects.equals(this.valorCobrosAdicionales, other.valorCobrosAdicionales)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotal, other.valorTotal)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAprobacion, other.fechaDeAprobacion)) {
            return false;
        }
        
        if (!Objects.equals(this.cobrado, other.cobrado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCobro, other.fechaDeCobro)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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

