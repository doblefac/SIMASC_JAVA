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
@Table(name="CONTRATO_CONVENIO")
@NamedQuery(name = "ContratoConvenio.findAll", query = "SELECT p FROM ContratoConvenio p")
public class ContratoConvenio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CONTRATO_CONVENIO_PK = "codigoContrato";
	public static final String ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO = "idConvenio";
	public static final String ENTIDAD_CONTRATO_CONVENIO_FECHA_INICIO = "fechaInicio";
	public static final String ENTIDAD_CONTRATO_CONVENIO_FECHA_FIN = "fechaFin";
	public static final String ENTIDAD_CONTRATO_CONVENIO_MAXIMO_AUDIENCIAS = "maximoAudiencias";
	public static final String ENTIDAD_CONTRATO_CONVENIO_DIAS_CANCELACION = "diasCancelacion";
	public static final String ENTIDAD_CONTRATO_CONVENIO_INCREMENTO_AUDIENCIA_ADICIONAL = "incrementoAudienciaAdicional";
	public static final String ENTIDAD_CONTRATO_CONVENIO_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_CONTRATO_CONVENIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CONTRATO_CONVENIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO_CONTRATOCONVENIO = "estadoRegistroContratoConvenio";			
	public static final String ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CONTRATO_CONVENIO_TIPO_COMBINACION = "tipoCombinacion";
	
    private static final String[] ATRIBUTOS_ENTIDAD_CONTRATO_CONVENIO
            = {ENTIDAD_CONTRATO_CONVENIO_FECHA_FIN, ENTIDAD_CONTRATO_CONVENIO_DIAS_CANCELACION, ENTIDAD_CONTRATO_CONVENIO_PK, ENTIDAD_CONTRATO_CONVENIO_MAXIMO_AUDIENCIAS, ENTIDAD_CONTRATO_CONVENIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CONTRATO_CONVENIO_INCREMENTO_AUDIENCIA_ADICIONAL, ENTIDAD_CONTRATO_CONVENIO_ESTADO_REGISTRO, ENTIDAD_CONTRATO_CONVENIO_ID_CONVENIO, ENTIDAD_CONTRATO_CONVENIO_FECHA_INICIO, ENTIDAD_CONTRATO_CONVENIO_ID_USUARIO_MODIFICACION, ENTIDAD_CONTRATO_CONVENIO_ID_DOCUMENTO,ENTIDAD_CONTRATO_CONVENIO_TIPO_COMBINACION };

	@Id
    @Column(name="codigo_contrato")
	private String codigoContrato;
    
	@Column(name="id_convenio")
	private Long idConvenio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;		
    
	@Column(name="maximo_audiencias")
	private Integer maximoAudiencias;		
    
	@Column(name="dias_cancelacion")
	private Integer diasCancelacion;		
    
	@Column(name="incremento_audiencia_adicional")
	private BigDecimal incrementoAudienciaAdicional;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;	
	
	@Column(name="tipo_combinacion")
	private String tipoCombinacion;		
	

	@ManyToOne
	@JoinColumn(name="id_convenio", referencedColumnName="id_convenio", insertable = false, updatable = false)
    private Convenio convenio;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@OneToMany(mappedBy="contratoConvenio")
    private List<TarifaContrato> tarifaContratoList;
	
	
    public ContratoConvenio(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getCodigoContrato(){
		return this.codigoContrato;
	}
	
	public void setCodigoContrato(String codigoContrato){
		this.codigoContrato = codigoContrato;
	}
	
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio = fechaInicio;
	}
		
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public void setFechaFin(Date fechaFin){
		this.fechaFin = fechaFin;
	}
		
	public Integer getMaximoAudiencias(){
		return this.maximoAudiencias;
	}
	
	public void setMaximoAudiencias(Integer maximoAudiencias){
		this.maximoAudiencias = maximoAudiencias;
	}
		
	public Integer getDiasCancelacion(){
		return this.diasCancelacion;
	}
	
	public void setDiasCancelacion(Integer diasCancelacion){
		this.diasCancelacion = diasCancelacion;
	}
		
	public BigDecimal getIncrementoAudienciaAdicional(){
		return this.incrementoAudienciaAdicional;
	}
	
	public void setIncrementoAudienciaAdicional(BigDecimal incrementoAudienciaAdicional){
		this.incrementoAudienciaAdicional = incrementoAudienciaAdicional;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
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
		

    public List<TarifaContrato> getTarifaContratoList(){
		return this.tarifaContratoList;
	}
	
	public void setTarifaContratoList(List<TarifaContrato> tarifaContratoList){
		this.tarifaContratoList = tarifaContratoList;
	}
			
    public Convenio getConvenio(){
		return this.convenio; 
	}
	
	public void setConvenio(Convenio convenio){
		this.convenio = convenio;
	}
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
	
	public String getTipoCombinacion() {
		return tipoCombinacion;
	}
	
	public void setTipoCombinacion(String tipoCombinacion) {
		this.tipoCombinacion = tipoCombinacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CONTRATO_CONVENIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.codigoContrato);        
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.fechaInicio);
        hash = 37 * hash + Objects.hashCode(this.fechaFin);
        hash = 37 * hash + Objects.hashCode(this.maximoAudiencias);
        hash = 37 * hash + Objects.hashCode(this.diasCancelacion);
        hash = 37 * hash + Objects.hashCode(this.incrementoAudienciaAdicional);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipoCombinacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ContratoConvenio que se pasa
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
        final ContratoConvenio other = (ContratoConvenio) obj;
        
        if (!Objects.equals(this.codigoContrato, other.codigoContrato)) {
            return false;
        }
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.maximoAudiencias, other.maximoAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.diasCancelacion, other.diasCancelacion)) {
            return false;
        }
        
        if (!Objects.equals(this.incrementoAudienciaAdicional, other.incrementoAudienciaAdicional)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCombinacion, other.tipoCombinacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

