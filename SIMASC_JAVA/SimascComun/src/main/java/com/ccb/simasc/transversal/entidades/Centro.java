package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

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

import com.ccb.simasc.transversal.dto.CentroDTO;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CENTRO")
@NamedQuery(name = "Centro.findAll", query = "SELECT p FROM Centro p")
public class Centro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CENTRO_PK = "idCentro";
	public static final String ENTIDAD_CENTRO_NOMBRE = "nombre";
	public static final String ENTIDAD_CENTRO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CENTRO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CENTRO_ESTADO_REGISTRO_CENTRO = "estadoRegistroCentro";			
	public static final String ENTIDAD_CENTRO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CENTRO_ID_CIUDAD = "idCiudad";
	public static final String ENTIDAD_CENTRO_DIRECCION = "direccion";
	public static final String ENTIDAD_CENTRO_DIRECTOR = "director";
	public static final String ENTIDAD_CENTRO_FIRMA_DIRECTOR = "firmaDirector";
	public static final String ENTIDAD_CENTRO_RESOLUCION = "resolucion";
	
    private static final String[] ATRIBUTOS_ENTIDAD_CENTRO
            = {ENTIDAD_CENTRO_NOMBRE, ENTIDAD_CENTRO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CENTRO_ID_USUARIO_MODIFICACION, ENTIDAD_CENTRO_ID_CIUDAD, ENTIDAD_CENTRO_ESTADO_REGISTRO, ENTIDAD_CENTRO_PK, ENTIDAD_CENTRO_DIRECCION, ENTIDAD_CENTRO_DIRECTOR, ENTIDAD_CENTRO_FIRMA_DIRECTOR, ENTIDAD_CENTRO_RESOLUCION };

	@Id
    @Column(name="id_centro")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCentro;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_ciudad")
	private String idCiudad;		
	
	@Column(name="direccion")
	private String direccion;	
	
	@Column(name="director")
	private String director;	
	
	@Column(name="firma_director")
	private String firmaDirector;	
	
	@Column(name="resolucion")
	private String resolucion;	

	@ManyToOne
	@JoinColumn(name="id_ciudad", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="centro")
    private List<Convenio> convenioList;
	@OneToMany(mappedBy="centro")
    private List<RolPersona> rolPersonaList;
	@OneToMany(mappedBy="centro")
    private List<Sede> sedeList;
	@OneToMany(mappedBy="centro")
    private List<ServicioMateriaCentro> servicioMateriaCentroList;
	
	
    public Centro(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	public String getIdCiudad(){
		return this.idCiudad;
	}
	
	public void setIdCiudad(String idCiudad){
		this.idCiudad = idCiudad;
	}
		

    public List<Convenio> getConvenioList(){
		return this.convenioList;
	}
	
	public void setConvenioList(List<Convenio> convenioList){
		this.convenioList = convenioList;
	}
			
    public List<RolPersona> getRolPersonaList(){
		return this.rolPersonaList;
	}
	
	public void setRolPersonaList(List<RolPersona> rolPersonaList){
		this.rolPersonaList = rolPersonaList;
	}
			
    public List<Sede> getSedeList(){
		return this.sedeList;
	}
	
	public void setSedeList(List<Sede> sedeList){
		this.sedeList = sedeList;
	}
			
    public List<ServicioMateriaCentro> getServicioMateriaCentroList(){
		return this.servicioMateriaCentroList;
	}
	
	public void setServicioMateriaCentroList(List<ServicioMateriaCentro> servicioMateriaCentroList){
		this.servicioMateriaCentroList = servicioMateriaCentroList;
	}
			
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getFirmaDirector() {
		return firmaDirector;
	}

	public void setFirmaDirector(String firma_director) {
		this.firmaDirector = firma_director;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CENTRO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCentro);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCiudad);
        hash = 37 * hash + Objects.hashCode(this.director);
        hash = 37 * hash + Objects.hashCode(this.firmaDirector);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.resolucion);              
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Centro que se pasa
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
        final Centro other = (Centro) obj;
        
        if (!Objects.equals(this.idCentro, other.idCentro)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        
        if (!Objects.equals(this.firmaDirector, other.firmaDirector)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }        
        
        if (!Objects.equals(this.resolucion, other.resolucion)) {
            return false;
        }      

        return Objects.equals(this.idCiudad, other.idCiudad);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	/**
	 * Metodo encargado de convertir Entidad Centro a DTO Centro DTO
	 * 
	 * @param centro
	 * @return CentroDTO
	 */
    public CentroDTO convertirEntidadToCentroDto(Centro centro){
    	CentroDTO dto = new CentroDTO();
    	dto.setIdCentro(centro.getIdCentro());
    	dto.setNombre(centro.getNombre());
    	dto.setIdUsuarioModificacion(centro.getIdUsuarioModificacion());
    	dto.setFechaUltimaModificacion(centro.getFechaUltimaModificacion());
    	dto.setEstadoRegistro(centro.getEstadoRegistro());
    	dto.setDirector(centro.getDirector());
    	dto.setDireccion(centro.getDireccion());
    	dto.setResolucion(centro.getResolucion());
    	dto.setFirmaDirector(centro.getFirmaDirector());
    	dto.setIdCiudad(centro.getIdCiudad() != null ? centro.getIdCiudad() : null);
    	return dto;
    }
	// protected region metodos adicionales end






} 

