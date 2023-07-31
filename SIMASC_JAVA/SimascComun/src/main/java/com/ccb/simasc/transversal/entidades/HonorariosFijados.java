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
@Table(name="HONORARIOS_FIJADOS")
@NamedQuery(name = "HonorariosFijados.findAll", query = "SELECT p FROM HonorariosFijados p")
public class HonorariosFijados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_HONORARIOS_FIJADOS_PK = "idHonorariosFijados";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_VALOR_FIJADO_PRETENSIONES = "valorFijadoPretensiones";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_FECHA_LIMITE_DE_PAGO = "fechaLimiteDePago";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_FECHA_FIJACION = "fechaFijacion";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_MONEDA = "moneda";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_ESTADO_REGISTRO_HONORARIOSFIJADOS = "estadoRegistroHonorariosFijados";			
	public static final String ENTIDAD_HONORARIOS_FIJADOS_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_HONORARIOS_FIJADOS_ID_CASO = "idCaso";
    private static final String[] ATRIBUTOS_ENTIDAD_HONORARIOS_FIJADOS
            = {ENTIDAD_HONORARIOS_FIJADOS_PK, ENTIDAD_HONORARIOS_FIJADOS_FECHA_LIMITE_DE_PAGO, ENTIDAD_HONORARIOS_FIJADOS_ID_USUARIO_MODIFICACION, ENTIDAD_HONORARIOS_FIJADOS_VALOR_FIJADO_PRETENSIONES, ENTIDAD_HONORARIOS_FIJADOS_FECHA_FIJACION, ENTIDAD_HONORARIOS_FIJADOS_MONEDA, ENTIDAD_HONORARIOS_FIJADOS_ID_CASO, ENTIDAD_HONORARIOS_FIJADOS_FECHA_ULTIMA_MODIFICACION, ENTIDAD_HONORARIOS_FIJADOS_ESTADO_REGISTRO};

	@Id
    @Column(name="id_honorarios_fijados")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idHonorariosFijados;
    
	@Column(name="valor_fijado_pretensiones")
	private BigDecimal valorFijadoPretensiones;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_limite_de_pago")
	private Date fechaLimiteDePago;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_fijacion")
	private Date fechaFijacion;		
    
	@Column(name="moneda")
	private String moneda;		
    
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
		
	@OneToMany(mappedBy="honorariosFijados")
    private List<ValorHonorariosActor> valorHonorariosActorList;
	
	
    public HonorariosFijados(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdHonorariosFijados(){
		return this.idHonorariosFijados;
	}
	
	public void setIdHonorariosFijados(Long idHonorariosFijados){
		this.idHonorariosFijados = idHonorariosFijados;
	}
	
	public BigDecimal getValorFijadoPretensiones(){
		return this.valorFijadoPretensiones;
	}
	
	public void setValorFijadoPretensiones(BigDecimal valorFijadoPretensiones){
		this.valorFijadoPretensiones = valorFijadoPretensiones;
	}
		
	public Date getFechaLimiteDePago(){
		return this.fechaLimiteDePago;
	}
	
	public void setFechaLimiteDePago(Date fechaLimiteDePago){
		this.fechaLimiteDePago = fechaLimiteDePago;
	}
		
	public Date getFechaFijacion(){
		return this.fechaFijacion;
	}
	
	public void setFechaFijacion(Date fechaFijacion){
		this.fechaFijacion = fechaFijacion;
	}
		
	public String getMoneda(){
		return this.moneda;
	}
	
	public void setMoneda(String moneda){
		this.moneda = moneda;
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
		

    public List<ValorHonorariosActor> getValorHonorariosActorList(){
		return this.valorHonorariosActorList;
	}
	
	public void setValorHonorariosActorList(List<ValorHonorariosActor> valorHonorariosActorList){
		this.valorHonorariosActorList = valorHonorariosActorList;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_HONORARIOS_FIJADOS) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idHonorariosFijados);        
        hash = 37 * hash + Objects.hashCode(this.valorFijadoPretensiones);
        hash = 37 * hash + Objects.hashCode(this.fechaLimiteDePago);
        hash = 37 * hash + Objects.hashCode(this.fechaFijacion);
        hash = 37 * hash + Objects.hashCode(this.moneda);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HonorariosFijados que se pasa
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
        final HonorariosFijados other = (HonorariosFijados) obj;
        
        if (!Objects.equals(this.idHonorariosFijados, other.idHonorariosFijados)) {
            return false;
        }
        
        if (!Objects.equals(this.valorFijadoPretensiones, other.valorFijadoPretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaLimiteDePago, other.fechaLimiteDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFijacion, other.fechaFijacion)) {
            return false;
        }
        
        if (!Objects.equals(this.moneda, other.moneda)) {
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
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

