package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="RELIQUIDACION")
@NamedQuery(name = "Reliquidacion.findAll", query = "SELECT p FROM Reliquidacion p")
public class Reliquidacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_RELIQUIDACION_PK = "idReliquidacion";
	public static final String ENTIDAD_RELIQUIDACION_TIPO = "tipo";
	public static final String ENTIDAD_RELIQUIDACION_FECHA = "fecha";
	public static final String ENTIDAD_RELIQUIDACION_MOTIVO = "motivo";
	public static final String ENTIDAD_RELIQUIDACION_VALOR = "valor";
	public static final String ENTIDAD_RELIQUIDACION_ORDEN_DE_PAGO = "ordenDePago";
	public static final String ENTIDAD_RELIQUIDACION_PAGADA = "pagada";
	public static final String ENTIDAD_RELIQUIDACION_NUEVA_CUANTIA = "nuevaCuantia";
	public static final String ENTIDAD_RELIQUIDACION_ID_CASO = "idCaso";
	public static final String ENTIDAD_RELIQUIDACION_FECHA_PAGO = "fechaPago";
	public static final String ENTIDAD_RELIQUIDACION_NUMERO_RECIBO = "numeroRecibo";
	public static final String ENTIDAD_RELIQUIDACION_PORCENTAJE_DEVOLUCION= "porcentajeDevolucion";
	public static final String ENTIDAD_RELIQUIDACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_RELIQUIDACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_RELIQUIDACION_ESTADO_REGISTRO_RELIQUIDACION = "estadoRegistroReliquidacion";			
	public static final String ENTIDAD_RELIQUIDACION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_RELIQUIDACION
            = {ENTIDAD_RELIQUIDACION_FECHA, ENTIDAD_RELIQUIDACION_ORDEN_DE_PAGO, ENTIDAD_RELIQUIDACION_PAGADA, ENTIDAD_RELIQUIDACION_NUEVA_CUANTIA, ENTIDAD_RELIQUIDACION_FECHA_PAGO, ENTIDAD_RELIQUIDACION_ID_USUARIO_MODIFICACION, ENTIDAD_RELIQUIDACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_RELIQUIDACION_NUMERO_RECIBO, ENTIDAD_RELIQUIDACION_TIPO, ENTIDAD_RELIQUIDACION_ESTADO_REGISTRO, ENTIDAD_RELIQUIDACION_PK, ENTIDAD_RELIQUIDACION_VALOR, ENTIDAD_RELIQUIDACION_MOTIVO, ENTIDAD_RELIQUIDACION_ID_CASO, ENTIDAD_RELIQUIDACION_PORCENTAJE_DEVOLUCION};

	@Id
    @Column(name="id_reliquidacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idReliquidacion;
    
	@Column(name="tipo")
	private String tipo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="motivo")
	private String motivo;		
    
	@Column(name="valor")
	private BigDecimal valor;		
    
	@Column(name="orden_de_pago")
	private String ordenDePago;		
    
	@Column(name="pagada")
	private boolean pagada;		
    
	@Column(name="nueva_cuantia")
	private Long nuevaCuantia;		
    
	@Column(name="id_caso")
	private Long idCaso;	
	
	@Column(name="porcentaje_devolucion")
	private Long porcentajeDevolucion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_pago")
	private Date fechaPago;		
    
	@Column(name="numero_recibo")
	private String numeroRecibo;	
	
    
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
		
	@OneToMany(mappedBy="reliquidacion")
    private List<DetalleReliquidacion> detalleReliquidacionList;
	
	
    public Reliquidacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdReliquidacion(){
		return this.idReliquidacion;
	}
	
	public void setIdReliquidacion(Long idReliquidacion){
		this.idReliquidacion = idReliquidacion;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
		
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
	}
		
	public String getOrdenDePago(){
		return this.ordenDePago;
	}
	
	public void setOrdenDePago(String ordenDePago){
		this.ordenDePago = ordenDePago;
	}
		
	public boolean getPagada(){
		return this.pagada;
	}
	
	public void setPagada(boolean pagada){
		this.pagada = pagada;
	}
		
	public Long getNuevaCuantia(){
		return this.nuevaCuantia;
	}
	
	public void setNuevaCuantia(Long nuevaCuantia){
		this.nuevaCuantia = nuevaCuantia;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
	
	public Long getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	public void setPorcentajeDevolucion(Long porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getNumeroRecibo(){
		return this.numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo){
		this.numeroRecibo = numeroRecibo;
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
		

    public List<DetalleReliquidacion> getDetalleReliquidacionList(){
		return this.detalleReliquidacionList;
	}
	
	public void setDetalleReliquidacionList(List<DetalleReliquidacion> detalleReliquidacionList){
		this.detalleReliquidacionList = detalleReliquidacionList;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_RELIQUIDACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idReliquidacion);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.ordenDePago);
        hash = 37 * hash + (this.pagada ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.nuevaCuantia);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.porcentajeDevolucion);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Reliquidacion que se pasa
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
        final Reliquidacion other = (Reliquidacion) obj;
        
        if (!Objects.equals(this.idReliquidacion, other.idReliquidacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.ordenDePago, other.ordenDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.pagada, other.pagada)) {
            return false;
        }
        
        if (!Objects.equals(this.nuevaCuantia, other.nuevaCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroRecibo, other.numeroRecibo)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        } 
        
        if (!Objects.equals(this.porcentajeDevolucion, other.porcentajeDevolucion)) {
            return false;
        } 
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

