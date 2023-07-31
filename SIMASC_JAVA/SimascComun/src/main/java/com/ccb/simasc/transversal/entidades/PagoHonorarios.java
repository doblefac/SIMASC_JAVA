package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="PAGO_HONORARIOS")
@NamedQuery(name = "PagoHonorarios.findAll", query = "SELECT p FROM PagoHonorarios p")
public class PagoHonorarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PAGO_HONORARIOS_PK = "idPagoHonorarios";
	public static final String ENTIDAD_PAGO_HONORARIOS_VALOR_PAGADO = "valorPagado";
	public static final String ENTIDAD_PAGO_HONORARIOS_FECHA_PAGO = "fechaPago";
	public static final String ENTIDAD_PAGO_HONORARIOS_PARTE_QUE_PAGA = "parteQuePaga";
	public static final String ENTIDAD_PAGO_HONORARIOS_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PAGO_HONORARIOS_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO_PAGOHONORARIOS = "estadoRegistroPagoHonorarios";			
	public static final String ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PAGO_HONORARIOS_ID_CASO = "idCaso";
    private static final String[] ATRIBUTOS_ENTIDAD_PAGO_HONORARIOS
            = {ENTIDAD_PAGO_HONORARIOS_ID_CASO, ENTIDAD_PAGO_HONORARIOS_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PAGO_HONORARIOS_ESTADO_REGISTRO, ENTIDAD_PAGO_HONORARIOS_ID_USUARIO_MODIFICACION, ENTIDAD_PAGO_HONORARIOS_FECHA_PAGO, ENTIDAD_PAGO_HONORARIOS_PK, ENTIDAD_PAGO_HONORARIOS_VALOR_PAGADO, ENTIDAD_PAGO_HONORARIOS_PARTE_QUE_PAGA};

	@Id
    @Column(name="id_pago_honorarios")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPagoHonorarios;
    
	@Column(name="valor_pagado")
	private BigDecimal valorPagado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_pago")
	private Date fechaPago;		
    
	@Column(name="parte_que_paga")
	private String parteQuePaga;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_caso")
	private Long idCaso;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	
	
    public PagoHonorarios(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPagoHonorarios(){
		return this.idPagoHonorarios;
	}
	
	public void setIdPagoHonorarios(Long idPagoHonorarios){
		this.idPagoHonorarios = idPagoHonorarios;
	}
	
	public BigDecimal getValorPagado(){
		return this.valorPagado;
	}
	
	public void setValorPagado(BigDecimal valorPagado){
		this.valorPagado = valorPagado;
	}
		
	public Date getFechaPago(){
		return this.fechaPago;
	}
	
	public void setFechaPago(Date fechaPago){
		this.fechaPago = fechaPago;
	}
		
	public String getParteQuePaga(){
		return this.parteQuePaga;
	}
	
	public void setParteQuePaga(String parteQuePaga){
		this.parteQuePaga = parteQuePaga;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PAGO_HONORARIOS) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPagoHonorarios);        
        hash = 37 * hash + Objects.hashCode(this.valorPagado);
        hash = 37 * hash + Objects.hashCode(this.fechaPago);
        hash = 37 * hash + Objects.hashCode(this.parteQuePaga);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PagoHonorarios que se pasa
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
        final PagoHonorarios other = (PagoHonorarios) obj;
        
        if (!Objects.equals(this.idPagoHonorarios, other.idPagoHonorarios)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPagado, other.valorPagado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQuePaga, other.parteQuePaga)) {
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
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

