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
@Table(name="AUTORIZACION_TRATAMIENTO_DATOS")
@NamedQuery(name = "AutorizacionTratamientoDatos.findAll", query = "SELECT p FROM AutorizacionTratamientoDatos p")
public class AutorizacionTratamientoDatos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_PK = "idAutorizacionTratamientoDatos";
	public static final String ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_NUMERO_IDENTIFICACION = "numeroIdentificacion";
	public static final String ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_TIPO_IDENTIFICACION = "tipoIdentificacion";
	public static final String ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_INDICADOR_ACEPTACION = "indicadorAceptacion";
	public static final String ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_FECHA_ACEPTACION = "fechaAceptacion";
    private static final String[] ATRIBUTOS_ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS
            = {ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_FECHA_ACEPTACION, ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_TIPO_IDENTIFICACION, ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_PK, ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_NUMERO_IDENTIFICACION, ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS_INDICADOR_ACEPTACION};

	@Id
    @Column(name="id_autorizacion_tratamiento_datos")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAutorizacionTratamientoDatos;
    
	@Column(name="numero_identificacion")
	private String numeroIdentificacion;		
    
	@Column(name="tipo_identificacion")
	private String tipoIdentificacion;		
    
	@Column(name="indicador_aceptacion")
	private boolean indicadorAceptacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_aceptacion")
	private Date fechaAceptacion;		

	@OneToMany(mappedBy="autorizacionTratamientoDatos")
    private List<SolicitudServicio> solicitudServicioList;
	
	
    public AutorizacionTratamientoDatos(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAutorizacionTratamientoDatos(){
		return this.idAutorizacionTratamientoDatos;
	}
	
	public void setIdAutorizacionTratamientoDatos(Long idAutorizacionTratamientoDatos){
		this.idAutorizacionTratamientoDatos = idAutorizacionTratamientoDatos;
	}
	
	public String getNumeroIdentificacion(){
		return this.numeroIdentificacion;
	}
	
	public void setNumeroIdentificacion(String numeroIdentificacion){
		this.numeroIdentificacion = numeroIdentificacion;
	}
		
	public String getTipoIdentificacion(){
		return this.tipoIdentificacion;
	}
	
	public void setTipoIdentificacion(String tipoIdentificacion){
		this.tipoIdentificacion = tipoIdentificacion;
	}
		
	public boolean getIndicadorAceptacion(){
		return this.indicadorAceptacion;
	}
	
	public void setIndicadorAceptacion(boolean indicadorAceptacion){
		this.indicadorAceptacion = indicadorAceptacion;
	}
		
	public Date getFechaAceptacion(){
		return this.fechaAceptacion;
	}
	
	public void setFechaAceptacion(Date fechaAceptacion){
		this.fechaAceptacion = fechaAceptacion;
	}
		

    public List<SolicitudServicio> getSolicitudServicioList(){
		return this.solicitudServicioList;
	}
	
	public void setSolicitudServicioList(List<SolicitudServicio> solicitudServicioList){
		this.solicitudServicioList = solicitudServicioList;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AUTORIZACION_TRATAMIENTO_DATOS) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAutorizacionTratamientoDatos);        
        hash = 37 * hash + Objects.hashCode(this.numeroIdentificacion);
        hash = 37 * hash + Objects.hashCode(this.tipoIdentificacion);
        hash = 37 * hash + (this.indicadorAceptacion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaAceptacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AutorizacionTratamientoDatos que se pasa
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
        final AutorizacionTratamientoDatos other = (AutorizacionTratamientoDatos) obj;
        
        if (!Objects.equals(this.idAutorizacionTratamientoDatos, other.idAutorizacionTratamientoDatos)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroIdentificacion, other.numeroIdentificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoIdentificacion, other.tipoIdentificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.indicadorAceptacion, other.indicadorAceptacion)) {
            return false;
        }
        
        return Objects.equals(this.fechaAceptacion, other.fechaAceptacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

