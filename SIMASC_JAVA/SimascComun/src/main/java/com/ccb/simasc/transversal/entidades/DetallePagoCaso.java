package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
@Table(name="DETALLE_PAGO_CASO")
@NamedQuery(name = "DetallePagoCaso.findAll", query = "SELECT p FROM DetallePagoCaso p")
public class DetallePagoCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_DETALLE_PAGO_CASO_PK_NUMERO_RECIBO = "detallePagoCasoPK.numeroRecibo";
			
	public static final String ENTIDAD_DETALLE_PAGO_CASO_PK_CODIGO_ITEM = "detallePagoCasoPK.codigoItem";
	public static final String ENTIDAD_DETALLE_PAGO_CASO_VALOR = "valor";
	public static final String ENTIDAD_DETALLE_PAGO_CASO_SERVICIO_CAJA = "servicioCaja";
	public static final String ENTIDAD_DETALLE_PAGO_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DETALLE_PAGO_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DETALLE_PAGO_CASO_ESTADO_REGISTRO_DETALLEPAGOCASO = "estadoRegistroDetallePagoCaso";			
	public static final String ENTIDAD_DETALLE_PAGO_CASO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_DETALLE_PAGO_CASO
            = {ENTIDAD_DETALLE_PAGO_CASO_PK_NUMERO_RECIBO, ENTIDAD_DETALLE_PAGO_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_DETALLE_PAGO_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DETALLE_PAGO_CASO_ESTADO_REGISTRO, ENTIDAD_DETALLE_PAGO_CASO_PK_CODIGO_ITEM, ENTIDAD_DETALLE_PAGO_CASO_VALOR, ENTIDAD_DETALLE_PAGO_CASO_SERVICIO_CAJA};

	@EmbeddedId
	private DetallePagoCasoPK detallePagoCasoPK;
    
	@Column(name="valor")
	private Long valor;		
    
	@Column(name="servicio_caja")
	private String servicioCaja;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="numero_recibo", referencedColumnName="numero_recibo", insertable = false, updatable = false)
    private PagoCaso pagoCaso;
		
	
	
    public DetallePagoCaso(){
		detallePagoCasoPK = new DetallePagoCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public DetallePagoCasoPK getDetallePagoCasoPK(){
		return this.detallePagoCasoPK;
	}
	
	public void setDetallePagoCasoPK(DetallePagoCasoPK detallePagoCasoPK){
		this.detallePagoCasoPK   = detallePagoCasoPK ;
	}  
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public String getServicioCaja(){
		return this.servicioCaja;
	}
	
	public void setServicioCaja(String servicioCaja){
		this.servicioCaja = servicioCaja;
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
		

    public PagoCaso getPagoCaso(){
		return this.pagoCaso; 
	}
	
	public void setPagoCaso(PagoCaso pagoCaso){
		this.pagoCaso = pagoCaso;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DETALLE_PAGO_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.detallePagoCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.servicioCaja);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DetallePagoCaso que se pasa
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
        final DetallePagoCaso other = (DetallePagoCaso) obj;
        
        if (!Objects.equals(this.detallePagoCasoPK, other.detallePagoCasoPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.servicioCaja, other.servicioCaja)) {
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

