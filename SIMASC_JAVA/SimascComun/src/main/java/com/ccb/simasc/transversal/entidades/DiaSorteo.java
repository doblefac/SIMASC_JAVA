package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="DIA_SORTEO")
@NamedQuery(name = "DiaSorteo.findAll", query = "SELECT p FROM DiaSorteo p")
public class DiaSorteo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_DIA_SORTEO_PK = "codigo";
	public static final String ENTIDAD_DIA_SORTEO_DOMINIO = "dominio";
	public static final String ENTIDAD_DIA_SORTEO_ID_PARAMETROS_SORTEO = "idParametrosSorteo";
	public static final String ENTIDAD_DIA_SORTEO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_DIA_SORTEO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_DIA_SORTEO_ESTADO_REGISTRO_DIASORTEO = "estadoRegistroDiaSorteo";			
	public static final String ENTIDAD_DIA_SORTEO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_DIA_SORTEO
            = {ENTIDAD_DIA_SORTEO_ID_PARAMETROS_SORTEO, ENTIDAD_DIA_SORTEO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_DIA_SORTEO_DOMINIO, ENTIDAD_DIA_SORTEO_ESTADO_REGISTRO, ENTIDAD_DIA_SORTEO_ID_USUARIO_MODIFICACION, ENTIDAD_DIA_SORTEO_PK};

	@Id
    @Column(name="codigo")
	private String codigo;
    
	@Column(name="dominio")
	private String dominio;		
    
	@Column(name="id_parametros_sorteo")
	private Long idParametrosSorteo;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_parametros_sorteo", referencedColumnName="id_parametros_sorteo", insertable = false, updatable = false)
    private ParametroSorteo parametroSorteo;
		
	
	
    public DiaSorteo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getDominio(){
		return this.dominio;
	}
	
	public void setDominio(String dominio){
		this.dominio = dominio;
	}
		
	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
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
		

    public ParametroSorteo getParametroSorteo(){
		return this.parametroSorteo; 
	}
	
	public void setParametroSorteo(ParametroSorteo parametroSorteo){
		this.parametroSorteo = parametroSorteo;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_DIA_SORTEO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.codigo);        
        hash = 37 * hash + Objects.hashCode(this.dominio);
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DiaSorteo que se pasa
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
        final DiaSorteo other = (DiaSorteo) obj;
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.dominio, other.dominio)) {
            return false;
        }
        
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
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

