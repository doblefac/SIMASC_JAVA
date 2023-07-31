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

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TARIFA_CONTRATO")
@NamedQuery(name = "TarifaContrato.findAll", query = "SELECT p FROM TarifaContrato p")
public class TarifaContrato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TARIFA_CONTRATO_PK = "idTarifaContrato";
	public static final String ENTIDAD_TARIFA_CONTRATO_TIPO_TARIFA = "tipoTarifa";
	public static final String ENTIDAD_TARIFA_CONTRATO_MINIMO_CASOS = "minimoCasos";
	public static final String ENTIDAD_TARIFA_CONTRATO_MAXIMO_CASOS = "maximoCasos";
	public static final String ENTIDAD_TARIFA_CONTRATO_CUANTIA_MINIMA = "cuantiaMinima";
	public static final String ENTIDAD_TARIFA_CONTRATO_CUANTIA_MAXIMA = "cuantiaMaxima";
	public static final String ENTIDAD_TARIFA_CONTRATO_RESULTADO = "resultado";
	public static final String ENTIDAD_TARIFA_CONTRATO_PORCENTAJE = "porcentaje";
	public static final String ENTIDAD_TARIFA_CONTRATO_VALOR = "valor";
	public static final String ENTIDAD_TARIFA_CONTRATO_CODIGO_CONTRATO = "codigoContrato";
	public static final String ENTIDAD_TARIFA_CONTRATO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TARIFA_CONTRATO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO_TARIFACONTRATO = "estadoRegistroTarifaContrato";			
	public static final String ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_TARIFA_CONTRATO
            = {ENTIDAD_TARIFA_CONTRATO_RESULTADO, ENTIDAD_TARIFA_CONTRATO_PK, ENTIDAD_TARIFA_CONTRATO_ESTADO_REGISTRO, ENTIDAD_TARIFA_CONTRATO_VALOR, ENTIDAD_TARIFA_CONTRATO_MINIMO_CASOS, ENTIDAD_TARIFA_CONTRATO_CODIGO_CONTRATO, ENTIDAD_TARIFA_CONTRATO_PORCENTAJE, ENTIDAD_TARIFA_CONTRATO_CUANTIA_MAXIMA, ENTIDAD_TARIFA_CONTRATO_TIPO_TARIFA, ENTIDAD_TARIFA_CONTRATO_CUANTIA_MINIMA, ENTIDAD_TARIFA_CONTRATO_ID_USUARIO_MODIFICACION, ENTIDAD_TARIFA_CONTRATO_MAXIMO_CASOS, ENTIDAD_TARIFA_CONTRATO_FECHA_ULTIMA_MODIFICACION};

	@Id
    @Column(name="id_tarifa_contrato")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTarifaContrato;
    
	@Column(name="tipo_tarifa")
	private String tipoTarifa;		
    
	@Column(name="minimo_casos")
	private Integer minimoCasos;		
    
	@Column(name="maximo_casos")
	private Integer maximoCasos;		
    
	@Column(name="cuantia_minima")
	private Double cuantiaMinima;		
    
	@Column(name="cuantia_maxima")
	private Double cuantiaMaxima;		
    
	@Column(name="resultado")
	private String resultado;		
    
	@Column(name="porcentaje")
	private Double porcentaje;		
    
	@Column(name="valor")
	private Double valor;		
    
	@Column(name="codigo_contrato")
	private String codigoContrato;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="codigo_contrato", referencedColumnName="codigo_contrato", insertable = false, updatable = false)
    private ContratoConvenio contratoConvenio;
		
	
	
    public TarifaContrato(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTarifaContrato(){
		return this.idTarifaContrato;
	}
	
	public void setIdTarifaContrato(Long idTarifaContrato){
		this.idTarifaContrato = idTarifaContrato;
	}
	
	public String getTipoTarifa(){
		return this.tipoTarifa;
	}
	
	public void setTipoTarifa(String tipoTarifa){
		this.tipoTarifa = tipoTarifa;
	}
		
	public Integer getMinimoCasos(){
		return this.minimoCasos;
	}
	
	public void setMinimoCasos(Integer minimoCasos){
		this.minimoCasos = minimoCasos;
	}
		
	public Integer getMaximoCasos(){
		return this.maximoCasos;
	}
	
	public void setMaximoCasos(Integer maximoCasos){
		this.maximoCasos = maximoCasos;
	}
		
	public Double getCuantiaMinima(){
		return this.cuantiaMinima;
	}
	
	public void setCuantiaMinima(Double cuantiaMinima){
		this.cuantiaMinima = cuantiaMinima;
	}
		
	public Double getCuantiaMaxima(){
		return this.cuantiaMaxima;
	}
	
	public void setCuantiaMaxima(Double cuantiaMaxima){
		this.cuantiaMaxima = cuantiaMaxima;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
		
	public Double getPorcentaje(){
		return this.porcentaje;
	}
	
	public void setPorcentaje(Double porcentaje){
		this.porcentaje = porcentaje;
	}
		
	public Double getValor(){
		return this.valor;
	}
	
	public void setValor(Double valor){
		this.valor = valor;
	}
		
	public String getCodigoContrato(){
		return this.codigoContrato;
	}
	
	public void setCodigoContrato(String codigoContrato){
		this.codigoContrato = codigoContrato;
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
		

    public ContratoConvenio getContratoConvenio(){
		return this.contratoConvenio; 
	}
	
	public void setContratoConvenio(ContratoConvenio contratoConvenio){
		this.contratoConvenio = contratoConvenio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TARIFA_CONTRATO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTarifaContrato);        
        hash = 37 * hash + Objects.hashCode(this.tipoTarifa);
        hash = 37 * hash + Objects.hashCode(this.minimoCasos);
        hash = 37 * hash + Objects.hashCode(this.maximoCasos);
        hash = 37 * hash + Objects.hashCode(this.cuantiaMinima);
        hash = 37 * hash + Objects.hashCode(this.cuantiaMaxima);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.porcentaje);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.codigoContrato);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TarifaContrato que se pasa
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
        final TarifaContrato other = (TarifaContrato) obj;
        
        if (!Objects.equals(this.idTarifaContrato, other.idTarifaContrato)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTarifa, other.tipoTarifa)) {
            return false;
        }
        
        if (!Objects.equals(this.minimoCasos, other.minimoCasos)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoCasos, other.maximoCasos)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantiaMinima, other.cuantiaMinima)) {
            return false;
        }
        
        if (!Objects.equals(this.cuantiaMaxima, other.cuantiaMaxima)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoContrato, other.codigoContrato)) {
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

