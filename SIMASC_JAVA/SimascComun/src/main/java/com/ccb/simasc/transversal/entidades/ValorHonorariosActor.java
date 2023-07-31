package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="VALOR_HONORARIOS_ACTOR")
@NamedQuery(name = "ValorHonorariosActor.findAll", query = "SELECT p FROM ValorHonorariosActor p")
public class ValorHonorariosActor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_PK_ID_HONORARIOS_FIJADOS = "valorHonorariosActorPK.idHonorariosFijados";
			
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_PK_TIPO_ACTOR = "valorHonorariosActorPK.tipoActor";
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_VALOR = "valor";
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_ESTADO_REGISTRO_VALORHONORARIOSACTOR = "estadoRegistroValorHonorariosActor";			
	public static final String ENTIDAD_VALOR_HONORARIOS_ACTOR_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_VALOR_HONORARIOS_ACTOR
            = {ENTIDAD_VALOR_HONORARIOS_ACTOR_PK_TIPO_ACTOR, ENTIDAD_VALOR_HONORARIOS_ACTOR_VALOR, ENTIDAD_VALOR_HONORARIOS_ACTOR_ESTADO_REGISTRO, ENTIDAD_VALOR_HONORARIOS_ACTOR_PK_ID_HONORARIOS_FIJADOS, ENTIDAD_VALOR_HONORARIOS_ACTOR_ID_USUARIO_MODIFICACION, ENTIDAD_VALOR_HONORARIOS_ACTOR_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private ValorHonorariosActorPK valorHonorariosActorPK;
    
	@Column(name="valor")
	private BigDecimal valor;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_honorarios_fijados", referencedColumnName="id_honorarios_fijados", insertable = false, updatable = false)
    private HonorariosFijados honorariosFijados;
		
	
	
    public ValorHonorariosActor(){
		valorHonorariosActorPK = new ValorHonorariosActorPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ValorHonorariosActorPK getValorHonorariosActorPK(){
		return this.valorHonorariosActorPK;
	}
	
	public void setValorHonorariosActorPK(ValorHonorariosActorPK valorHonorariosActorPK){
		this.valorHonorariosActorPK   = valorHonorariosActorPK ;
	}  
	
	public BigDecimal getValor(){
		return this.valor;
	}
	
	public void setValor(BigDecimal valor){
		this.valor = valor;
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
		

    public HonorariosFijados getHonorariosFijados(){
		return this.honorariosFijados; 
	}
	
	public void setHonorariosFijados(HonorariosFijados honorariosFijados){
		this.honorariosFijados = honorariosFijados;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_VALOR_HONORARIOS_ACTOR) {
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
        
        hash = 37 * hash + Objects.hashCode(this.valorHonorariosActorPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorHonorariosActor que se pasa
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
        final ValorHonorariosActor other = (ValorHonorariosActor) obj;
        
        if (!Objects.equals(this.valorHonorariosActorPK, other.valorHonorariosActorPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
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

