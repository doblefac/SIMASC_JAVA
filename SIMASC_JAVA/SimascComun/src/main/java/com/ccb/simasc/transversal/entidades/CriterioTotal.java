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
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CRITERIO_TOTAL")
@NamedQuery(name = "CriterioTotal.findAll", query = "SELECT p FROM CriterioTotal p")
public class CriterioTotal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_CRITERIO_TOTAL_PK_CODIGO_CRITERIO = "criterioTotalPK.codigoCriterio";
			
	public static final String ENTIDAD_CRITERIO_TOTAL_PK_CODIGO_TOTAL = "criterioTotalPK.codigoTotal";
	public static final String ENTIDAD_CRITERIO_TOTAL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CRITERIO_TOTAL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CRITERIO_TOTAL_ESTADO_REGISTRO_CRITERIOTOTAL = "estadoRegistroCriterioTotal";			
	public static final String ENTIDAD_CRITERIO_TOTAL_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_CRITERIO_TOTAL
            = {ENTIDAD_CRITERIO_TOTAL_ESTADO_REGISTRO, ENTIDAD_CRITERIO_TOTAL_PK_CODIGO_CRITERIO, ENTIDAD_CRITERIO_TOTAL_PK_CODIGO_TOTAL, ENTIDAD_CRITERIO_TOTAL_ID_USUARIO_MODIFICACION, ENTIDAD_CRITERIO_TOTAL_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private CriterioTotalPK criterioTotalPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@OneToMany(mappedBy="criterioTotal")
    private List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList;
	
	
    public CriterioTotal(){
		criterioTotalPK = new CriterioTotalPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public CriterioTotalPK getCriterioTotalPK(){
		return this.criterioTotalPK;
	}
	
	public void setCriterioTotalPK(CriterioTotalPK criterioTotalPK){
		this.criterioTotalPK   = criterioTotalPK ;
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
		

    public List<DetalleEvaluacionConciliador> getDetalleEvaluacionConciliadorList(){
		return this.detalleEvaluacionConciliadorList;
	}
	
	public void setDetalleEvaluacionConciliadorList(List<DetalleEvaluacionConciliador> detalleEvaluacionConciliadorList){
		this.detalleEvaluacionConciliadorList = detalleEvaluacionConciliadorList;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CRITERIO_TOTAL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.criterioTotalPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CriterioTotal que se pasa
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
        final CriterioTotal other = (CriterioTotal) obj;
        
        if (!Objects.equals(this.criterioTotalPK, other.criterioTotalPK)) {
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

