package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CUOTA")
@NamedQuery(name = "Cuota.findAll", query = "SELECT p FROM Cuota p")
public class Cuota implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_CUOTA_PK_NUMERO_CUOTA = "cuotaPK.numeroCuota";
			
	public static final String ENTIDAD_CUOTA_PK_ID_RESULTADO_AUDIENCIA = "cuotaPK.idResultadoAudiencia";
			
	public static final String ENTIDAD_CUOTA_PK_TIPO_OBLIGACION = "cuotaPK.tipoObligacion";
	public static final String ENTIDAD_CUOTA_FECHA_PREVISTA = "fechaPrevista";
	public static final String ENTIDAD_CUOTA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CUOTA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CUOTA_ESTADO_REGISTRO_CUOTA = "estadoRegistroCuota";			
	public static final String ENTIDAD_CUOTA_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_CUOTA
            = {ENTIDAD_CUOTA_ESTADO_REGISTRO, ENTIDAD_CUOTA_PK_ID_RESULTADO_AUDIENCIA, ENTIDAD_CUOTA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CUOTA_ID_USUARIO_MODIFICACION, ENTIDAD_CUOTA_PK_TIPO_OBLIGACION, ENTIDAD_CUOTA_FECHA_PREVISTA, ENTIDAD_CUOTA_PK_NUMERO_CUOTA};

	@EmbeddedId
	private CuotaPK cuotaPK;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_prevista")
	private Date fechaPrevista;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_resultado_audiencia", referencedColumnName="id_resultado_audiencia", insertable = false, updatable = false),
	    @JoinColumn(name = "tipo_obligacion", referencedColumnName="tipo_obligacion", insertable = false, updatable = false)	    
	})		
    private Obligacion obligacion;
		
	
	
    public Cuota(){
		cuotaPK = new CuotaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CuotaPK getCuotaPK(){
		return this.cuotaPK;
	}
	
	public void setCuotaPK(CuotaPK cuotaPK){
		this.cuotaPK   = cuotaPK ;
	}  
	
	public Date getFechaPrevista(){
		return this.fechaPrevista;
	}
	
	public void setFechaPrevista(Date fechaPrevista){
		this.fechaPrevista = fechaPrevista;
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
		

    public Obligacion getObligacion(){
		return this.obligacion; 
	}
	
	public void setObligacion(Obligacion obligacion){
		this.obligacion = obligacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CUOTA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.cuotaPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaPrevista);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Cuota que se pasa
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
        final Cuota other = (Cuota) obj;
        
        if (!Objects.equals(this.cuotaPK, other.cuotaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaPrevista, other.fechaPrevista)) {
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

