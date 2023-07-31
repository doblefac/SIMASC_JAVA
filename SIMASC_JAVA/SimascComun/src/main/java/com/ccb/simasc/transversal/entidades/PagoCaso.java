package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="PAGO_CASO")
@NamedQuery(name = "PagoCaso.findAll", query = "SELECT p FROM PagoCaso p")
public class PagoCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PAGO_CASO_PK = "numeroRecibo";
	public static final String ENTIDAD_PAGO_CASO_VALOR = "valor";
	public static final String ENTIDAD_PAGO_CASO_FECHA_PAGO = "fechaPago";
	public static final String ENTIDAD_PAGO_CASO_NOMBRE_PERSONA = "nombrePersona";
	public static final String ENTIDAD_PAGO_CASO_TIPO_DE_DOCUMENTO = "tipoDeDocumento";
	public static final String ENTIDAD_PAGO_CASO_NUMERO_DE_DOCUMENTO = "numeroDeDocumento";
	public static final String ENTIDAD_PAGO_CASO_ESTADO = "estado";
	public static final String ENTIDAD_PAGO_CASO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_PAGO_CASO_TIPO_CUANTIA = "tipoCuantia";
	public static final String ENTIDAD_PAGO_CASO_VALOR_CUANTIA = "valorCuantia";
	public static final String ENTIDAD_PAGO_CASO_ID_ORDEN_DE_PAGO = "idOrdenDePago";
	public static final String ENTIDAD_PAGO_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PAGO_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PAGO_CASO_ESTADO_REGISTRO_PAGOCASO = "estadoRegistroPagoCaso";			
	public static final String ENTIDAD_PAGO_CASO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PAGO_CASO_ID_SEDE = "idSede";
	public static final String ENTIDAD_PAGO_CASO_ID_CASO = "idCaso";
	public static final String ENTIDAD_PAGO_CASO_ID_SERVICIO = "idServicio";
    private static final String[] ATRIBUTOS_ENTIDAD_PAGO_CASO
            = {ENTIDAD_PAGO_CASO_ID_SERVICIO, ENTIDAD_PAGO_CASO_TIPO_DE_DOCUMENTO, ENTIDAD_PAGO_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_PAGO_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PAGO_CASO_PK, ENTIDAD_PAGO_CASO_VALOR, ENTIDAD_PAGO_CASO_ID_ORDEN_DE_PAGO, ENTIDAD_PAGO_CASO_ID_CASO, ENTIDAD_PAGO_CASO_TIPO_CUANTIA, ENTIDAD_PAGO_CASO_NUMERO_DE_DOCUMENTO, ENTIDAD_PAGO_CASO_ID_SEDE, ENTIDAD_PAGO_CASO_ESTADO_REGISTRO, ENTIDAD_PAGO_CASO_ESTADO, ENTIDAD_PAGO_CASO_DESCRIPCION, ENTIDAD_PAGO_CASO_NOMBRE_PERSONA, ENTIDAD_PAGO_CASO_VALOR_CUANTIA, ENTIDAD_PAGO_CASO_FECHA_PAGO};

	@Id
    @Column(name="numero_recibo")
	private String numeroRecibo;
    
	@Column(name="valor")
	private Long valor;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_pago")
	private Date fechaPago;		
    
	@Column(name="nombre_persona")
	private String nombrePersona;		
    
	@Column(name="tipo_de_documento")
	private String tipoDeDocumento;		
    
	@Column(name="numero_de_documento")
	private String numeroDeDocumento;		
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="tipo_cuantia")
	private String tipoCuantia;		
    
	@Column(name="valor_cuantia")
	private Long valorCuantia;		
    
	@Column(name="id_orden_de_pago")
	private Long idOrdenDePago;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_sede")
	private Long idSede;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_servicio")
	private Long idServicio;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	@OneToMany(mappedBy="pagoCaso")
    private List<DetallePagoCaso> detallePagoCasoList;
	
	
    public PagoCaso(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getNumeroRecibo(){
		return this.numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo){
		this.numeroRecibo = numeroRecibo;
	}
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getNombrePersona(){
		return this.nombrePersona;
	}
	
	public void setNombrePersona(String nombrePersona){
		this.nombrePersona = nombrePersona;
	}
		
	public String getTipoDeDocumento(){
		return this.tipoDeDocumento;
	}
	
	public void setTipoDeDocumento(String tipoDeDocumento){
		this.tipoDeDocumento = tipoDeDocumento;
	}
		
	public String getNumeroDeDocumento(){
		return this.numeroDeDocumento;
	}
	
	public void setNumeroDeDocumento(String numeroDeDocumento){
		this.numeroDeDocumento = numeroDeDocumento;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public Long getValorCuantia(){
		return this.valorCuantia;
	}
	
	public void setValorCuantia(Long valorCuantia){
		this.valorCuantia = valorCuantia;
	}
		
	public Long getIdOrdenDePago(){
		return this.idOrdenDePago;
	}
	
	public void setIdOrdenDePago(Long idOrdenDePago){
		this.idOrdenDePago = idOrdenDePago;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		

    public List<DetallePagoCaso> getDetallePagoCasoList(){
		return this.detallePagoCasoList;
	}
	
	public void setDetallePagoCasoList(List<DetallePagoCaso> detallePagoCasoList){
		this.detallePagoCasoList = detallePagoCasoList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}
    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PAGO_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.numeroRecibo);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.nombrePersona);
        hash = 37 * hash + Objects.hashCode(this.tipoDeDocumento);
        hash = 37 * hash + Objects.hashCode(this.numeroDeDocumento);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + Objects.hashCode(this.valorCuantia);
        hash = 37 * hash + Objects.hashCode(this.idOrdenDePago);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PagoCaso que se pasa
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
        final PagoCaso other = (PagoCaso) obj;
        
        if (!Objects.equals(this.numeroRecibo, other.numeroRecibo)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.nombrePersona, other.nombrePersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeDocumento, other.tipoDeDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeDocumento, other.numeroDeDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.valorCuantia, other.valorCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.idOrdenDePago, other.idOrdenDePago)) {
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
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

