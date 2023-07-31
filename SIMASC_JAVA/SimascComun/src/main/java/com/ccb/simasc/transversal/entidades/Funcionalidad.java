package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="FUNCIONALIDAD")
@NamedQuery(name = "Funcionalidad.findAll", query = "SELECT p FROM Funcionalidad p")
public class Funcionalidad implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@Transient
	private boolean acceso;
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_FUNCIONALIDAD_PK = "nombre";
	public static final String ENTIDAD_FUNCIONALIDAD_TITULO = "titulo";
	public static final String ENTIDAD_FUNCIONALIDAD_URL = "url";
	public static final String ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD = "nombreTipoFuncionalidad";
	public static final String ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD = "aplicacionTipoFuncionalidad";
	public static final String ENTIDAD_FUNCIONALIDAD_NOMBRE_FUNCIONALIDAD_PADRE = "nombreFuncionalidadPadre";
	public static final String ENTIDAD_FUNCIONALIDAD_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_FUNCIONALIDAD_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO_FUNCIONALIDAD = "estadoRegistroFuncionalidad";			
	public static final String ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_FUNCIONALIDAD
            = {ENTIDAD_FUNCIONALIDAD_ID_USUARIO_MODIFICACION, ENTIDAD_FUNCIONALIDAD_APLICACION_TIPO_FUNCIONALIDAD, ENTIDAD_FUNCIONALIDAD_URL, ENTIDAD_FUNCIONALIDAD_TITULO, ENTIDAD_FUNCIONALIDAD_PK, ENTIDAD_FUNCIONALIDAD_FECHA_ULTIMA_MODIFICACION, ENTIDAD_FUNCIONALIDAD_NOMBRE_TIPO_FUNCIONALIDAD, ENTIDAD_FUNCIONALIDAD_NOMBRE_FUNCIONALIDAD_PADRE, ENTIDAD_FUNCIONALIDAD_ESTADO_REGISTRO};

	@Id
    @Column(name="nombre")
	private String nombre;
    
	@Column(name="titulo")
	private String titulo;		
    
	@Column(name="url")
	private String url;		
    
	@Column(name="nombre_tipo_funcionalidad")
	private String nombreTipoFuncionalidad;		
    
	@Column(name="aplicacion_tipo_funcionalidad")
	private String aplicacionTipoFuncionalidad;		
    
	@Column(name="nombre_funcionalidad_padre")
	private String nombreFuncionalidadPadre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="nombre_funcionalidad_padre", referencedColumnName="nombre", insertable = false, updatable = false)
    private Funcionalidad funcionalidad;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "nombre_tipo_funcionalidad", referencedColumnName="nombre", insertable = false, updatable = false),
	    @JoinColumn(name = "aplicacion_tipo_funcionalidad", referencedColumnName="aplicacion", insertable = false, updatable = false)	    
	})		
    private TipoFuncionalidad tipoFuncionalidad;
		
	@OneToMany(mappedBy="funcionalidad")
    private List<Funcionalidad> funcionalidadList;
	@OneToMany(mappedBy="funcionalidad")
    private List<FuncionalidadRol> funcionalidadRolList;
	
	
    public Funcionalidad(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
		
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
		
	public String getNombreTipoFuncionalidad(){
		return this.nombreTipoFuncionalidad;
	}
	
	public void setNombreTipoFuncionalidad(String nombreTipoFuncionalidad){
		this.nombreTipoFuncionalidad = nombreTipoFuncionalidad;
	}
		
	public String getAplicacionTipoFuncionalidad(){
		return this.aplicacionTipoFuncionalidad;
	}
	
	public void setAplicacionTipoFuncionalidad(String aplicacionTipoFuncionalidad){
		this.aplicacionTipoFuncionalidad = aplicacionTipoFuncionalidad;
	}
		
	public String getNombreFuncionalidadPadre(){
		return this.nombreFuncionalidadPadre;
	}
	
	public void setNombreFuncionalidadPadre(String nombreFuncionalidadPadre){
		this.nombreFuncionalidadPadre = nombreFuncionalidadPadre;
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
		

    public List<Funcionalidad> getFuncionalidadList(){
		return this.funcionalidadList;
	}
	
	public void setFuncionalidadList(List<Funcionalidad> funcionalidadList){
		this.funcionalidadList = funcionalidadList;
	}
			
    public List<FuncionalidadRol> getFuncionalidadRolList(){
		return this.funcionalidadRolList;
	}
	
	public void setFuncionalidadRolList(List<FuncionalidadRol> funcionalidadRolList){
		this.funcionalidadRolList = funcionalidadRolList;
	}
			
    public Funcionalidad getFuncionalidad(){
		return this.funcionalidad; 
	}
	
	public void setFuncionalidad(Funcionalidad funcionalidad){
		this.funcionalidad = funcionalidad;
	}
    public TipoFuncionalidad getTipoFuncionalidad(){
		return this.tipoFuncionalidad; 
	}
	
	public void setTipoFuncionalidad(TipoFuncionalidad tipoFuncionalidad){
		this.tipoFuncionalidad = tipoFuncionalidad;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_FUNCIONALIDAD) {
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
        
        hash = 37 * hash + Objects.hashCode(this.nombre);        
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.url);
        hash = 37 * hash + Objects.hashCode(this.nombreTipoFuncionalidad);
        hash = 37 * hash + Objects.hashCode(this.aplicacionTipoFuncionalidad);
        hash = 37 * hash + Objects.hashCode(this.nombreFuncionalidadPadre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Funcionalidad que se pasa
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
        final Funcionalidad other = (Funcionalidad) obj;
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreTipoFuncionalidad, other.nombreTipoFuncionalidad)) {
            return false;
        }
        
        if (!Objects.equals(this.aplicacionTipoFuncionalidad, other.aplicacionTipoFuncionalidad)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreFuncionalidadPadre, other.nombreFuncionalidadPadre)) {
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


	public boolean isAcceso() {
		return acceso;
	}


	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}
	    
    
    
    
	// protected region metodos adicionales end

} 

