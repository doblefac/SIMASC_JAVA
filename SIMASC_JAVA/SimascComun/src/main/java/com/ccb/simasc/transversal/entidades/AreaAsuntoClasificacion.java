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
@Table(name="AREA_ASUNTO_CLASIFICACION")
@NamedQuery(name = "AreaAsuntoClasificacion.findAll", query = "SELECT p FROM AreaAsuntoClasificacion p")
public class AreaAsuntoClasificacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_PK = "idAreaAsuntoClasificacion";
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_ASUNTO = "idAsunto";
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_ESTADO_REGISTRO_AREAASUNTOCLASIFICACION = "estadoRegistroAreaAsuntoClasificacion";			
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_CLASIFICACION = "idClasificacion";
    private static final String[] ATRIBUTOS_ENTIDAD_AREA_ASUNTO_CLASIFICACION
            = {ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_CLASIFICACION, ENTIDAD_AREA_ASUNTO_CLASIFICACION_PK, ENTIDAD_AREA_ASUNTO_CLASIFICACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_ASUNTO, ENTIDAD_AREA_ASUNTO_CLASIFICACION_ID_USUARIO_MODIFICACION, ENTIDAD_AREA_ASUNTO_CLASIFICACION_ESTADO_REGISTRO};

	@Id
    @Column(name="id_area_asunto_clasificacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAreaAsuntoClasificacion;
    
	@Column(name="id_asunto")
	private Long idAsunto;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_clasificacion")
	private Long idClasificacion;		

	@ManyToOne
	@JoinColumn(name="id_asunto", referencedColumnName="id_asunto", insertable = false, updatable = false)
    private Asunto asunto;
		
	@ManyToOne
	@JoinColumn(name="id_clasificacion", referencedColumnName="id_clasificacion", insertable = false, updatable = false)
    private Clasificacion clasificacion;
		
	@OneToMany(mappedBy="areaAsuntoClasificacion")
    private List<Caso> casoList;
	
	
    public AreaAsuntoClasificacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAreaAsuntoClasificacion(){
		return this.idAreaAsuntoClasificacion;
	}
	
	public void setIdAreaAsuntoClasificacion(Long idAreaAsuntoClasificacion){
		this.idAreaAsuntoClasificacion = idAreaAsuntoClasificacion;
	}
	
	public Long getIdAsunto(){
		return this.idAsunto;
	}
	
	public void setIdAsunto(Long idAsunto){
		this.idAsunto = idAsunto;
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
		
	public Long getIdClasificacion(){
		return this.idClasificacion;
	}
	
	public void setIdClasificacion(Long idClasificacion){
		this.idClasificacion = idClasificacion;
	}
		

    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public Asunto getAsunto(){
		return this.asunto; 
	}
	
	public void setAsunto(Asunto asunto){
		this.asunto = asunto;
	}
    public Clasificacion getClasificacion(){
		return this.clasificacion; 
	}
	
	public void setClasificacion(Clasificacion clasificacion){
		this.clasificacion = clasificacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AREA_ASUNTO_CLASIFICACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAreaAsuntoClasificacion);        
        hash = 37 * hash + Objects.hashCode(this.idAsunto);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idClasificacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad AreaAsuntoClasificacion que se pasa
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
        final AreaAsuntoClasificacion other = (AreaAsuntoClasificacion) obj;
        
        if (!Objects.equals(this.idAreaAsuntoClasificacion, other.idAreaAsuntoClasificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idAsunto, other.idAsunto)) {
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
        
        return Objects.equals(this.idClasificacion, other.idClasificacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

