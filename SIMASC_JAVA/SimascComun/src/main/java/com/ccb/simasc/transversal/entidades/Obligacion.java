package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import java.math.BigDecimal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="OBLIGACION")
@NamedQuery(name = "Obligacion.findAll", query = "SELECT p FROM Obligacion p")
public class Obligacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_OBLIGACION_PK_TIPO_OBLIGACION = "obligacionPK.tipoObligacion";
			
	public static final String ENTIDAD_OBLIGACION_PK_ID_RESULTADO_AUDIENCIA = "obligacionPK.idResultadoAudiencia";
	public static final String ENTIDAD_OBLIGACION_VALOR_TOTAL_ACUERDO = "valorTotalAcuerdo";
	public static final String ENTIDAD_OBLIGACION_FORMA_DE_PAGO = "formaDePago";
	public static final String ENTIDAD_OBLIGACION_NOMBRE_BANCO = "nombreBanco";
	public static final String ENTIDAD_OBLIGACION_NUMERO_CUENTA = "numeroCuenta";
	public static final String ENTIDAD_OBLIGACION_COMPROMISO = "compromiso";
	public static final String ENTIDAD_OBLIGACION_FECHA_COMPROMISO = "fechaCompromiso";
	public static final String ENTIDAD_OBLIGACION_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_OBLIGACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_OBLIGACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_OBLIGACION_ESTADO_REGISTRO_OBLIGACION = "estadoRegistroObligacion";			
	public static final String ENTIDAD_OBLIGACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_OBLIGACION_ID_DOCUMENTO = "idDocumento";
	public static final String ENTIDAD_OBLIGACION_DIRECCION = "direccion";
    private static final String[] ATRIBUTOS_ENTIDAD_OBLIGACION
            = {ENTIDAD_OBLIGACION_NOMBRE_BANCO, ENTIDAD_OBLIGACION_FORMA_DE_PAGO, ENTIDAD_OBLIGACION_VALOR_TOTAL_ACUERDO, ENTIDAD_OBLIGACION_COMPROMISO, ENTIDAD_OBLIGACION_PK_ID_RESULTADO_AUDIENCIA, ENTIDAD_OBLIGACION_OBSERVACIONES, ENTIDAD_OBLIGACION_ID_USUARIO_MODIFICACION, ENTIDAD_OBLIGACION_ID_DOCUMENTO, ENTIDAD_OBLIGACION_ESTADO_REGISTRO, ENTIDAD_OBLIGACION_NUMERO_CUENTA, ENTIDAD_OBLIGACION_PK_TIPO_OBLIGACION, ENTIDAD_OBLIGACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_OBLIGACION_FECHA_COMPROMISO, ENTIDAD_OBLIGACION_DIRECCION};

	@EmbeddedId
	private ObligacionPK obligacionPK;
    
	@Column(name="valor_total_acuerdo")
	private BigDecimal valorTotalAcuerdo;		
    
	@Column(name="forma_de_pago")
	private String formaDePago;		
    
	@Column(name="nombre_banco")
	private String nombreBanco;		
    
	@Column(name="numero_cuenta")
	private String numeroCuenta;		
    
	@Column(name="compromiso")
	private String compromiso;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_compromiso")
	private Date fechaCompromiso;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		
    
	@Column(name="direccion")
	private String direccion;		

	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_resultado_audiencia", referencedColumnName="id_resultado_audiencia", insertable = false, updatable = false)
    private ResultadoAudiencia resultadoAudiencia;
		
	@OneToMany(mappedBy="obligacion", cascade={CascadeType.REMOVE})
    private List<Cuota> cuotaList;
	@OneToMany(mappedBy="obligacion", cascade={CascadeType.REMOVE})
    private List<ObligacionParte> obligacionParteList;
	
	
    public Obligacion(){
		obligacionPK = new ObligacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ObligacionPK getObligacionPK(){
		return this.obligacionPK;
	}
	
	public void setObligacionPK(ObligacionPK obligacionPK){
		this.obligacionPK   = obligacionPK ;
	}  
	
	public BigDecimal getValorTotalAcuerdo(){
		return this.valorTotalAcuerdo;
	}
	
	public void setValorTotalAcuerdo(BigDecimal valorTotalAcuerdo){
		this.valorTotalAcuerdo = valorTotalAcuerdo;
	}
		
	public String getFormaDePago(){
		return this.formaDePago;
	}
	
	public void setFormaDePago(String formaDePago){
		this.formaDePago = formaDePago;
	}
		
	public String getNombreBanco(){
		return this.nombreBanco;
	}
	
	public void setNombreBanco(String nombreBanco){
		this.nombreBanco = nombreBanco;
	}
		
	public String getNumeroCuenta(){
		return this.numeroCuenta;
	}
	
	public void setNumeroCuenta(String numeroCuenta){
		this.numeroCuenta = numeroCuenta;
	}
		
	public String getCompromiso(){
		return this.compromiso;
	}
	
	public void setCompromiso(String compromiso){
		this.compromiso = compromiso;
	}
		
	public Date getFechaCompromiso(){
		return this.fechaCompromiso;
	}
	
	public void setFechaCompromiso(Date fechaCompromiso){
		this.fechaCompromiso = fechaCompromiso;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
		

    public List<Cuota> getCuotaList(){
		return this.cuotaList;
	}
	
	public void setCuotaList(List<Cuota> cuotaList){
		this.cuotaList = cuotaList;
	}
			
    public List<ObligacionParte> getObligacionParteList(){
		return this.obligacionParteList;
	}
	
	public void setObligacionParteList(List<ObligacionParte> obligacionParteList){
		this.obligacionParteList = obligacionParteList;
	}
			
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
    public ResultadoAudiencia getResultadoAudiencia(){
		return this.resultadoAudiencia; 
	}
	
	public void setResultadoAudiencia(ResultadoAudiencia resultadoAudiencia){
		this.resultadoAudiencia = resultadoAudiencia;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_OBLIGACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.obligacionPK);        
        hash = 37 * hash + Objects.hashCode(this.valorTotalAcuerdo);
        hash = 37 * hash + Objects.hashCode(this.formaDePago);
        hash = 37 * hash + Objects.hashCode(this.nombreBanco);
        hash = 37 * hash + Objects.hashCode(this.numeroCuenta);
        hash = 37 * hash + Objects.hashCode(this.compromiso);
        hash = 37 * hash + Objects.hashCode(this.fechaCompromiso);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Obligacion que se pasa
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
        final Obligacion other = (Obligacion) obj;
        
        if (!Objects.equals(this.obligacionPK, other.obligacionPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotalAcuerdo, other.valorTotalAcuerdo)) {
            return false;
        }
        
        if (!Objects.equals(this.formaDePago, other.formaDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreBanco, other.nombreBanco)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        
        if (!Objects.equals(this.compromiso, other.compromiso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCompromiso, other.fechaCompromiso)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.direccion, other.direccion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

