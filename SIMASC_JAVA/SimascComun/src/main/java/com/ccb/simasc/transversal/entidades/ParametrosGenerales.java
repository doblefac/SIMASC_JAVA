package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PARAMETROS_GENERALES")
@NamedQueries({
	@NamedQuery(name = "ParametrosGenerales.findAll", query = "SELECT p FROM ParametrosGenerales p"),
	@NamedQuery(name = "ParametrosGenerales.findByNombre", query = "SELECT p FROM ParametrosGenerales p WHERE p.nombre = :nombre")})
public class ParametrosGenerales implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@Transient
	private String tipoValor;
	
	@Transient
	private String modificado;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PARAMETROS_GENERALES_PK = "codigo";
	public static final String ENTIDAD_PARAMETROS_GENERALES_EDITABLE = "editable";
	public static final String ENTIDAD_PARAMETROS_GENERALES_TIPO = "tipo";
	public static final String ENTIDAD_PARAMETROS_GENERALES_NOMBRE = "nombre";
	public static final String ENTIDAD_PARAMETROS_GENERALES_VALOR_NUMERICO = "valorNumerico";
	public static final String ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO = "valorTexto";
	public static final String ENTIDAD_PARAMETROS_GENERALES_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_PARAMETROS_GENERALES_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETROS_GENERALES_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO_PARAMETROSGENERALES = "estadoRegistroParametrosGenerales";			
	public static final String ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETROS_GENERALES
            = {ENTIDAD_PARAMETROS_GENERALES_VALOR_NUMERICO, ENTIDAD_PARAMETROS_GENERALES_NOMBRE, ENTIDAD_PARAMETROS_GENERALES_VALOR_TEXTO, ENTIDAD_PARAMETROS_GENERALES_ID_USUARIO_MODIFICACION, ENTIDAD_PARAMETROS_GENERALES_EDITABLE, ENTIDAD_PARAMETROS_GENERALES_ESTADO_REGISTRO, ENTIDAD_PARAMETROS_GENERALES_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETROS_GENERALES_TIPO, ENTIDAD_PARAMETROS_GENERALES_PK, ENTIDAD_PARAMETROS_GENERALES_DESCRIPCION};

	@Id
    @Column(name="codigo")
	private String codigo;
    
	@Column(name="editable")
	private boolean editable;		
    
	@Column(name="tipo")
	private String tipo;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="valor_numerico")
	private Long valorNumerico;		
    
	@Column(name="valor_texto")
	private String valorTexto;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;	
	
	

	
	
    public ParametrosGenerales(){
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
	
	public boolean getEditable(){
		return this.editable;
	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Long getValorNumerico(){
		return this.valorNumerico;
	}
	
	public void setValorNumerico(Long valorNumerico){
		this.valorNumerico = valorNumerico;
	}
		
	public String getValorTexto(){
		return this.valorTexto;
	}
	
	public void setValorTexto(String valorTexto){
		this.valorTexto = valorTexto;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETROS_GENERALES) {
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
        hash = 37 * hash + (this.editable ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.valorNumerico);
        hash = 37 * hash + Objects.hashCode(this.valorTexto);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrosGenerales que se pasa
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
        final ParametrosGenerales other = (ParametrosGenerales) obj;
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.editable, other.editable)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.valorNumerico, other.valorNumerico)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTexto, other.valorTexto)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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
    /**
	 * @return the tipoValor
	 */
	public String getTipoValor() {
		return tipoValor;
	}



	/**
	 * @param tipoValor the tipoValor to set
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}



	/**
	 * @return the modificado
	 */
	public String getModificado() {
		return modificado;
	}



	/**
	 * @param modificado the modificado to set
	 */
	public void setModificado(String modificado) {
		this.modificado = modificado;
	}
	
	
	// protected region metodos adicionales end

} 

