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
@Table(name="CLASIFICACION")
@NamedQuery(name = "Clasificacion.findAll", query = "SELECT p FROM Clasificacion p")
public class Clasificacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CLASIFICACION_PK = "idClasificacion";
	public static final String ENTIDAD_CLASIFICACION_NOMBRE = "nombre";
	public static final String ENTIDAD_CLASIFICACION_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_CLASIFICACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CLASIFICACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CLASIFICACION_ESTADO_REGISTRO_CLASIFICACION = "estadoRegistroClasificacion";			
	public static final String ENTIDAD_CLASIFICACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CLASIFICACION_ID_ASUNTO = "idAsunto";
    private static final String[] ATRIBUTOS_ENTIDAD_CLASIFICACION
            = {ENTIDAD_CLASIFICACION_DESCRIPCION, ENTIDAD_CLASIFICACION_ID_USUARIO_MODIFICACION, ENTIDAD_CLASIFICACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CLASIFICACION_PK, ENTIDAD_CLASIFICACION_ESTADO_REGISTRO, ENTIDAD_CLASIFICACION_NOMBRE, ENTIDAD_CLASIFICACION_ID_ASUNTO};

	@Id
    @Column(name="id_clasificacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idClasificacion;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_asunto")
	private Long idAsunto;		

	@ManyToOne
	@JoinColumn(name="id_asunto", referencedColumnName="id_asunto", insertable = false, updatable = false)
    private Asunto asunto;
		
	@OneToMany(mappedBy="clasificacion")
    private List<AreaAsuntoClasificacion> areaAsuntoClasificacionList;
	
	
    public Clasificacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdClasificacion(){
		return this.idClasificacion;
	}
	
	public void setIdClasificacion(Long idClasificacion){
		this.idClasificacion = idClasificacion;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
	}
		

    public List<AreaAsuntoClasificacion> getAreaAsuntoClasificacionList(){
		return this.areaAsuntoClasificacionList;
	}
	
	public void setAreaAsuntoClasificacionList(List<AreaAsuntoClasificacion> areaAsuntoClasificacionList){
		this.areaAsuntoClasificacionList = areaAsuntoClasificacionList;
	}
			
    public Asunto getAsunto(){
		return this.asunto; 
	}
	
	public void setAsunto(Asunto asunto){
		this.asunto = asunto;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CLASIFICACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idClasificacion);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idAsunto);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Clasificacion que se pasa
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
        final Clasificacion other = (Clasificacion) obj;
        
        if (!Objects.equals(this.idClasificacion, other.idClasificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idAsunto, other.idAsunto);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

