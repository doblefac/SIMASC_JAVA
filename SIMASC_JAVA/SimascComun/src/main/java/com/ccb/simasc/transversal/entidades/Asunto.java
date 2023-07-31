package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
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
@Table(name="ASUNTO")
@NamedQuery(name = "Asunto.findAll", query = "SELECT p FROM Asunto p")
public class Asunto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ASUNTO_PK = "idAsunto";
	public static final String ENTIDAD_ASUNTO_NOMBRE = "nombre";
	public static final String ENTIDAD_ASUNTO_ID_AREA = "idArea";
	public static final String ENTIDAD_ASUNTO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ASUNTO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ASUNTO_ESTADO_REGISTRO_ASUNTO = "estadoRegistroAsunto";			
	public static final String ENTIDAD_ASUNTO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ASUNTO_DESCRIPCION = "descripcion";
    private static final String[] ATRIBUTOS_ENTIDAD_ASUNTO
            = {ENTIDAD_ASUNTO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ASUNTO_ID_USUARIO_MODIFICACION, ENTIDAD_ASUNTO_ESTADO_REGISTRO, ENTIDAD_ASUNTO_NOMBRE, ENTIDAD_ASUNTO_DESCRIPCION, ENTIDAD_ASUNTO_ID_AREA, ENTIDAD_ASUNTO_PK};

	@Id
    @Column(name="id_asunto")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAsunto;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="id_area")
	private Long idArea;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="descripcion")
	private String descripcion;		

	@ManyToOne
	@JoinColumn(name="id_area", referencedColumnName="id_area", insertable = false, updatable = false)
    private Area area;
		
	@OneToMany(mappedBy="asunto")
    private List<AreaAsuntoClasificacion> areaAsuntoClasificacionList;
	@OneToMany(mappedBy="asunto")
    private List<Clasificacion> clasificacionList;
	
	
    public Asunto(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Long getIdArea(){
		return this.idArea;
	}
	
	public void setIdArea(Long idArea){
		this.idArea = idArea;
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
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		

    public List<AreaAsuntoClasificacion> getAreaAsuntoClasificacionList(){
		return this.areaAsuntoClasificacionList;
	}
	
	public void setAreaAsuntoClasificacionList(List<AreaAsuntoClasificacion> areaAsuntoClasificacionList){
		this.areaAsuntoClasificacionList = areaAsuntoClasificacionList;
	}
			
    public List<Clasificacion> getClasificacionList(){
		return this.clasificacionList;
	}
	
	public void setClasificacionList(List<Clasificacion> clasificacionList){
		this.clasificacionList = clasificacionList;
	}
			
    public Area getArea(){
		return this.area; 
	}
	
	public void setArea(Area area){
		this.area = area;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ASUNTO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAsunto);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idArea);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Asunto que se pasa
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
        final Asunto other = (Asunto) obj;
        
        if (!Objects.equals(this.idAsunto, other.idAsunto)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.idArea, other.idArea)) {
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
        
        return Objects.equals(this.descripcion, other.descripcion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

