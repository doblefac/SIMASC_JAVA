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
@Table(name="PARAMETRIZACION_TARIFAS")
@NamedQuery(name = "ParametrizacionTarifas.findAll", query = "SELECT p FROM ParametrizacionTarifas p")
public class ParametrizacionTarifas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_PK = "idParametrizacionTarifas";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_CANTIDAD_ARBITROS = "cantidadArbitros";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_TARIFA = "tipoTarifa";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_MONEDA = "moneda";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_RANGO = "tipoValorRango";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_RANGO_INFERIOR = "rangoInferior";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_RANGO_SUPERIOR = "rangoSuperior";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_MINIMO = "tipoValorMinimo";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_VALOR_MINIMO = "valorMinimo";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_MAXIMO = "tipoValorMaximo";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_VALOR_MAXIMO = "valorMaximo";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_ACTOR = "tipoActor";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_ESTADO_REGISTRO_PARAMETRIZACIONTARIFAS = "estadoRegistroParametrizacionTarifas";			
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PARAMETRIZACION_TARIFAS_ID_SERVICIO = "idServicio";
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETRIZACION_TARIFAS
            = {ENTIDAD_PARAMETRIZACION_TARIFAS_VALOR_MINIMO, ENTIDAD_PARAMETRIZACION_TARIFAS_ID_USUARIO_MODIFICACION, ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_RANGO, ENTIDAD_PARAMETRIZACION_TARIFAS_ESTADO_REGISTRO, ENTIDAD_PARAMETRIZACION_TARIFAS_ID_SERVICIO, ENTIDAD_PARAMETRIZACION_TARIFAS_CANTIDAD_ARBITROS, ENTIDAD_PARAMETRIZACION_TARIFAS_VALOR_MAXIMO, ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_TARIFA, ENTIDAD_PARAMETRIZACION_TARIFAS_MONEDA, ENTIDAD_PARAMETRIZACION_TARIFAS_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETRIZACION_TARIFAS_PK, ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_MINIMO, ENTIDAD_PARAMETRIZACION_TARIFAS_RANGO_SUPERIOR, ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_VALOR_MAXIMO, ENTIDAD_PARAMETRIZACION_TARIFAS_RANGO_INFERIOR, ENTIDAD_PARAMETRIZACION_TARIFAS_TIPO_ACTOR};

	@Id
    @Column(name="id_parametrizacion_tarifas")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idParametrizacionTarifas;
    
	@Column(name="cantidad_arbitros")
	private String cantidadArbitros;		
    
	@Column(name="tipo_tarifa")
	private String tipoTarifa;		
    
	@Column(name="moneda")
	private String moneda;		
    
	@Column(name="tipo_valor_rango")
	private String tipoValorRango;		
    
	@Column(name="rango_inferior")
	private BigDecimal rangoInferior;		
    
	@Column(name="rango_superior")
	private BigDecimal rangoSuperior;		
    
	@Column(name="tipo_valor_minimo")
	private String tipoValorMinimo;		
    
	@Column(name="valor_minimo")
	private BigDecimal valorMinimo;		
    
	@Column(name="tipo_valor_maximo")
	private String tipoValorMaximo;		
    
	@Column(name="valor_maximo")
	private BigDecimal valorMaximo;		
    
	@Column(name="tipo_actor")
	private String tipoActor;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_servicio")
	private Long idServicio;	
	
	@Column(name="materia_consumo")
	private boolean materiaConsumo;

	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	
	
    public ParametrizacionTarifas(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdParametrizacionTarifas(){
		return this.idParametrizacionTarifas;
	}
	
	public void setIdParametrizacionTarifas(Long idParametrizacionTarifas){
		this.idParametrizacionTarifas = idParametrizacionTarifas;
	}
	
	public String getCantidadArbitros(){
		return this.cantidadArbitros;
	}
	
	public void setCantidadArbitros(String cantidadArbitros){
		this.cantidadArbitros = cantidadArbitros;
	}
		
	public String getTipoTarifa(){
		return this.tipoTarifa;
	}
	
	public void setTipoTarifa(String tipoTarifa){
		this.tipoTarifa = tipoTarifa;
	}
		
	public String getMoneda(){
		return this.moneda;
	}
	
	public void setMoneda(String moneda){
		this.moneda = moneda;
	}
		
	public String getTipoValorRango(){
		return this.tipoValorRango;
	}
	
	public void setTipoValorRango(String tipoValorRango){
		this.tipoValorRango = tipoValorRango;
	}
		
	public BigDecimal getRangoInferior(){
		return this.rangoInferior;
	}
	
	public void setRangoInferior(BigDecimal rangoInferior){
		this.rangoInferior = rangoInferior;
	}
		
	public BigDecimal getRangoSuperior(){
		return this.rangoSuperior;
	}
	
	public void setRangoSuperior(BigDecimal rangoSuperior){
		this.rangoSuperior = rangoSuperior;
	}
		
	public String getTipoValorMinimo(){
		return this.tipoValorMinimo;
	}
	
	public void setTipoValorMinimo(String tipoValorMinimo){
		this.tipoValorMinimo = tipoValorMinimo;
	}
		
	public BigDecimal getValorMinimo(){
		return this.valorMinimo;
	}
	
	public void setValorMinimo(BigDecimal valorMinimo){
		this.valorMinimo = valorMinimo;
	}
		
	public String getTipoValorMaximo(){
		return this.tipoValorMaximo;
	}
	
	public void setTipoValorMaximo(String tipoValorMaximo){
		this.tipoValorMaximo = tipoValorMaximo;
	}
		
	public BigDecimal getValorMaximo(){
		return this.valorMaximo;
	}
	
	public void setValorMaximo(BigDecimal valorMaximo){
		this.valorMaximo = valorMaximo;
	}
		
	public String getTipoActor(){
		return this.tipoActor;
	}
	
	public void setTipoActor(String tipoActor){
		this.tipoActor = tipoActor;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		

    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}
	
	

	public boolean isMateriaConsumo() {
		return materiaConsumo;
	}


	public void setMateriaConsumo(boolean materiaConsumo) {
		this.materiaConsumo = materiaConsumo;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETRIZACION_TARIFAS) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idParametrizacionTarifas);        
        hash = 37 * hash + Objects.hashCode(this.cantidadArbitros);
        hash = 37 * hash + Objects.hashCode(this.tipoTarifa);
        hash = 37 * hash + Objects.hashCode(this.moneda);
        hash = 37 * hash + Objects.hashCode(this.tipoValorRango);
        hash = 37 * hash + Objects.hashCode(this.rangoInferior);
        hash = 37 * hash + Objects.hashCode(this.rangoSuperior);
        hash = 37 * hash + Objects.hashCode(this.tipoValorMinimo);
        hash = 37 * hash + Objects.hashCode(this.valorMinimo);
        hash = 37 * hash + Objects.hashCode(this.tipoValorMaximo);
        hash = 37 * hash + Objects.hashCode(this.valorMaximo);
        hash = 37 * hash + Objects.hashCode(this.tipoActor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.materiaConsumo);
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrizacionTarifas que se pasa
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
        final ParametrizacionTarifas other = (ParametrizacionTarifas) obj;
        
        if (!Objects.equals(this.idParametrizacionTarifas, other.idParametrizacionTarifas)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadArbitros, other.cantidadArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTarifa, other.tipoTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.moneda, other.moneda)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorRango, other.tipoValorRango)) {
            return false;
        }
        
        if (!Objects.equals(this.rangoInferior, other.rangoInferior)) {
            return false;
        }
        
        if (!Objects.equals(this.rangoSuperior, other.rangoSuperior)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorMinimo, other.tipoValorMinimo)) {
            return false;
        }
        
        if (!Objects.equals(this.valorMinimo, other.valorMinimo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoValorMaximo, other.tipoValorMaximo)) {
            return false;
        }
        
        if (!Objects.equals(this.valorMaximo, other.valorMaximo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoActor, other.tipoActor)) {
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
        
        if (!Objects.equals(this.materiaConsumo, other.materiaConsumo)) {
            return false;
        }
        
        return Objects.equals(this.idServicio, other.idServicio);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

