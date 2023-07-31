package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="DISTRIBUCION_TARIFA")
@NamedQuery(name = "DistribucionTarifa.findAll", query = "SELECT p FROM DistribucionTarifa p")
public class DistribucionTarifa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_PK = "idDistribucionTarifa";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_CANTIDAD_ARBITROS = "cantidadArbitros";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_TIPO_ACTOR = "tipoActor";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_VALOR_PORCENTUAL = "valorPorcentual";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_ESTADO_REGISTRO_DISTRIBUCIONTARIFA = "estadoRegistroDistribucionTarifa";			
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_DISTRIBUCION_TARIFA_ID_SERVICIO = "idServicio";
    private static final String[] ATRIBUTOS_ENTIDAD_DISTRIBUCION_TARIFA
            = {ENTIDAD_DISTRIBUCION_TARIFA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DISTRIBUCION_TARIFA_CANTIDAD_ARBITROS, ENTIDAD_DISTRIBUCION_TARIFA_TIPO_ACTOR, ENTIDAD_DISTRIBUCION_TARIFA_ESTADO_REGISTRO, ENTIDAD_DISTRIBUCION_TARIFA_PK, ENTIDAD_DISTRIBUCION_TARIFA_ID_USUARIO_MODIFICACION, ENTIDAD_DISTRIBUCION_TARIFA_VALOR_PORCENTUAL, ENTIDAD_DISTRIBUCION_TARIFA_ESTADO_REGISTRO };

	@Id
    @Column(name="id_distribucion_tarifa")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idDistribucionTarifa;
    
	@Column(name="cantidad_arbitros")
	private String cantidadArbitros;		
    
	@Column(name="tipo_actor")
	private String tipoActor;		
    
	@Column(name="valor_porcentual")
	private BigDecimal valorPorcentual;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;	
	
	@Column(name="id_servicio")
	private String idServicio;	

	
	
    public DistribucionTarifa(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdDistribucionTarifa(){
		return this.idDistribucionTarifa;
	}
	
	public void setIdDistribucionTarifa(Long idDistribucionTarifa){
		this.idDistribucionTarifa = idDistribucionTarifa;
	}
	
	public String getCantidadArbitros(){
		return this.cantidadArbitros;
	}
	
	public void setCantidadArbitros(String cantidadArbitros){
		this.cantidadArbitros = cantidadArbitros;
	}
		
	public String getTipoActor(){
		return this.tipoActor;
	}
	
	public void setTipoActor(String tipoActor){
		this.tipoActor = tipoActor;
	}
		
	public BigDecimal getValorPorcentual(){
		return this.valorPorcentual;
	}
	
	public void setValorPorcentual(BigDecimal valorPorcentual){
		this.valorPorcentual = valorPorcentual;
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
		
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DISTRIBUCION_TARIFA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idDistribucionTarifa);        
        hash = 37 * hash + Objects.hashCode(this.cantidadArbitros);
        hash = 37 * hash + Objects.hashCode(this.tipoActor);
        hash = 37 * hash + Objects.hashCode(this.valorPorcentual);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DistribucionTarifa que se pasa
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
        final DistribucionTarifa other = (DistribucionTarifa) obj;
        
        if (!Objects.equals(this.idDistribucionTarifa, other.idDistribucionTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadArbitros, other.cantidadArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActor, other.tipoActor)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPorcentual, other.valorPorcentual)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

