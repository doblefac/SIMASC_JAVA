package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="PUBLICACION")
@NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p")
public class Publicacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PUBLICACION_PK = "idPublicacion";
	public static final String ENTIDAD_PUBLICACION_TIPO_PUBLICACION = "tipoPublicacion";
	public static final String ENTIDAD_PUBLICACION_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_PUBLICACION_TITULO = "titulo";
	public static final String ENTIDAD_PUBLICACION_EDITORIAL = "editorial";
	public static final String ENTIDAD_PUBLICACION_CIUDAD = "ciudad";
	public static final String ENTIDAD_PUBLICACION_ANIO = "anio";
	public static final String ENTIDAD_PUBLICACION_NUMERO_PAGINAS = "numeroPaginas";
	public static final String ENTIDAD_PUBLICACION_NOMBRE_REVISTA = "nombreRevista";
	public static final String ENTIDAD_PUBLICACION_REGISTRO_ISBN = "registroIsbn";
	public static final String ENTIDAD_PUBLICACION_NUMERO_REVISTA = "numeroRevista";
	public static final String ENTIDAD_PUBLICACION_NUMERO_PAGINA = "numeroPagina";
	public static final String ENTIDAD_PUBLICACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PUBLICACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PUBLICACION_ESTADO_REGISTRO_PUBLICACION = "estadoRegistroPublicacion";			
	public static final String ENTIDAD_PUBLICACION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PUBLICACION
            = {ENTIDAD_PUBLICACION_ID_PERSONA, ENTIDAD_PUBLICACION_NOMBRE_REVISTA, ENTIDAD_PUBLICACION_TIPO_PUBLICACION, ENTIDAD_PUBLICACION_NUMERO_REVISTA, ENTIDAD_PUBLICACION_CIUDAD, ENTIDAD_PUBLICACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PUBLICACION_REGISTRO_ISBN, ENTIDAD_PUBLICACION_PK, ENTIDAD_PUBLICACION_TITULO, ENTIDAD_PUBLICACION_NUMERO_PAGINA, ENTIDAD_PUBLICACION_ANIO, ENTIDAD_PUBLICACION_NUMERO_PAGINAS, ENTIDAD_PUBLICACION_ESTADO_REGISTRO, ENTIDAD_PUBLICACION_EDITORIAL, ENTIDAD_PUBLICACION_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_publicacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPublicacion;
    
	@Column(name="tipo_publicacion")
	private String tipoPublicacion;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="titulo")
	private String titulo;		
    
	@Column(name="editorial")
	private String editorial;		
    
	@Column(name="ciudad")
	private String ciudad;		
    
	@Column(name="anio")
	private Integer anio;		
    
	@Column(name="numero_paginas")
	private Integer numeroPaginas;		
    
	@Column(name="nombre_revista")
	private String nombreRevista;		
    
	@Column(name="registro_isbn")
	private String registroIsbn;		
    
	@Column(name="numero_revista")
	private Integer numeroRevista;		
    
	@Column(name="numero_pagina")
	private String numeroPagina;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	
	
    public Publicacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPublicacion(){
		return this.idPublicacion;
	}
	
	public void setIdPublicacion(Long idPublicacion){
		this.idPublicacion = idPublicacion;
	}
	
	public String getTipoPublicacion(){
		return this.tipoPublicacion;
	}
	
	public void setTipoPublicacion(String tipoPublicacion){
		this.tipoPublicacion = tipoPublicacion;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
		
	public String getEditorial(){
		return this.editorial;
	}
	
	public void setEditorial(String editorial){
		this.editorial = editorial;
	}
		
	public String getCiudad(){
		return this.ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}
		
	public Integer getAnio(){
		return this.anio;
	}
	
	public void setAnio(Integer anio){
		this.anio = anio;
	}
		
	public Integer getNumeroPaginas(){
		return this.numeroPaginas;
	}
	
	public void setNumeroPaginas(Integer numeroPaginas){
		this.numeroPaginas = numeroPaginas;
	}
		
	public String getNombreRevista(){
		return this.nombreRevista;
	}
	
	public void setNombreRevista(String nombreRevista){
		this.nombreRevista = nombreRevista;
	}
		
	public String getRegistroIsbn(){
		return this.registroIsbn;
	}
	
	public void setRegistroIsbn(String registroIsbn){
		this.registroIsbn = registroIsbn;
	}
		
	public Integer getNumeroRevista(){
		return this.numeroRevista;
	}
	
	public void setNumeroRevista(Integer numeroRevista){
		this.numeroRevista = numeroRevista;
	}
		
	public String getNumeroPagina(){
		return this.numeroPagina;
	}
	
	public void setNumeroPagina(String numeroPagina){
		this.numeroPagina = numeroPagina;
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
		

    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PUBLICACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPublicacion);        
        hash = 37 * hash + Objects.hashCode(this.tipoPublicacion);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.editorial);
        hash = 37 * hash + Objects.hashCode(this.ciudad);
        hash = 37 * hash + Objects.hashCode(this.anio);
        hash = 37 * hash + Objects.hashCode(this.numeroPaginas);
        hash = 37 * hash + Objects.hashCode(this.nombreRevista);
        hash = 37 * hash + Objects.hashCode(this.registroIsbn);
        hash = 37 * hash + Objects.hashCode(this.numeroRevista);
        hash = 37 * hash + Objects.hashCode(this.numeroPagina);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Publicacion que se pasa
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
        final Publicacion other = (Publicacion) obj;
        
        if (!Objects.equals(this.idPublicacion, other.idPublicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPublicacion, other.tipoPublicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        
        if (!Objects.equals(this.anio, other.anio)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroPaginas, other.numeroPaginas)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreRevista, other.nombreRevista)) {
            return false;
        }
        
        if (!Objects.equals(this.registroIsbn, other.registroIsbn)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroRevista, other.numeroRevista)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroPagina, other.numeroPagina)) {
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

