package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="ROL")
@NamedQuery(name = "Rol.findAll", query = "SELECT p FROM Rol p")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@Transient
	private List<Funcionalidad> funcionalidadList;
	
	@OneToMany(mappedBy="rol", cascade = CascadeType.ALL)
    private List<FuncionalidadRol> funcionalidadRolList;
	// protected region atributos end
	
	@OneToMany(mappedBy="rol")
    private List<ParametrizarServicioRol> parametrizarServicioRolList;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ROL_PK = "idRol";
	public static final String ENTIDAD_ROL_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_ROL_NOMBRE = "nombre";
	public static final String ENTIDAD_ROL_PRESELECCION = "preseleccion";
	public static final String ENTIDAD_ROL_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_ROL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ROL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ROL_ESTADO_REGISTRO_ROL = "estadoRegistroRol";			
	public static final String ENTIDAD_ROL_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ROL_APLICA_MAUC= "aplicaMauc";
	private static final String[] ATRIBUTOS_ENTIDAD_ROL = { ENTIDAD_ROL_PK, ENTIDAD_ROL_ID_USUARIO_MODIFICACION,
			ENTIDAD_ROL_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ROL_NOMBRE, ENTIDAD_ROL_FECHA_CREACION,
			ENTIDAD_ROL_PRESELECCION, ENTIDAD_ROL_TIPO_SERVICIO, ENTIDAD_ROL_ESTADO_REGISTRO,ENTIDAD_ROL_APLICA_MAUC };

	@Id
    @Column(name="id_rol")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idRol;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="preseleccion")
	private boolean preseleccion;
	
	@Column(name="tipo_servicio")
	private String tipoServicio;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;
	
	@Column(name="aplica_mauc")
	private boolean aplicaMauc;

	@OneToMany(mappedBy="rol", cascade = CascadeType.ALL)
    private List<AgrupamientoRol> agrupamientoRolList;
	@OneToMany(mappedBy="rol")
    private List<ParametroServicioSorteo> parametroServicioSorteoList;
	@OneToMany(mappedBy="rol")
    private List<PersonaRolAlerta> personaRolAlertaList;
	@OneToMany(mappedBy="rol")
    private List<PersonaSolicitud> personaSolicitudList;
	@OneToMany(mappedBy="rol")
    private List<RelacionadoConvenio> relacionadoConvenioList;
	@OneToMany(mappedBy="rol")
    private List<RolPersona> rolPersonaList;
	@OneToMany(mappedBy="rol")
    private List<RolPersonaCaso> rolPersonaCasoList;
	@OneToMany(mappedBy="rol")
    private List<TipoDeServicioRol> tipoDeServicioRolList;
	
	
    public Rol(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
	
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getPreseleccion(){
		return this.preseleccion;
	}
	
	public void setPreseleccion(boolean preseleccion){
		this.preseleccion = preseleccion;
	}
	
	public String getTipoServicio() {
		return tipoServicio;
	}
    
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
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
		

    public List<AgrupamientoRol> getAgrupamientoRolList(){
		return this.agrupamientoRolList;
	}
	
	public void setAgrupamientoRolList(List<AgrupamientoRol> agrupamientoRolList){
		this.agrupamientoRolList = agrupamientoRolList;
	}
			
    public List<FuncionalidadRol> getFuncionalidadRolList(){
		return this.funcionalidadRolList;
	}
	
	public void setFuncionalidadRolList(List<FuncionalidadRol> funcionalidadRolList){
		this.funcionalidadRolList = funcionalidadRolList;
	}
			
    public List<ParametroServicioSorteo> getParametroServicioSorteoList(){
		return this.parametroServicioSorteoList;
	}
	
	public void setParametroServicioSorteoList(List<ParametroServicioSorteo> parametroServicioSorteoList){
		this.parametroServicioSorteoList = parametroServicioSorteoList;
	}
			
    public List<PersonaRolAlerta> getPersonaRolAlertaList(){
		return this.personaRolAlertaList;
	}
	
	public void setPersonaRolAlertaList(List<PersonaRolAlerta> personaRolAlertaList){
		this.personaRolAlertaList = personaRolAlertaList;
	}
			
    public List<PersonaSolicitud> getPersonaSolicitudList(){
		return this.personaSolicitudList;
	}
	
	public void setPersonaSolicitudList(List<PersonaSolicitud> personaSolicitudList){
		this.personaSolicitudList = personaSolicitudList;
	}
			
    public List<RelacionadoConvenio> getRelacionadoConvenioList(){
		return this.relacionadoConvenioList;
	}
	
	public void setRelacionadoConvenioList(List<RelacionadoConvenio> relacionadoConvenioList){
		this.relacionadoConvenioList = relacionadoConvenioList;
	}
			
    public List<RolPersona> getRolPersonaList(){
		return this.rolPersonaList;
	}
	
	public void setRolPersonaList(List<RolPersona> rolPersonaList){
		this.rolPersonaList = rolPersonaList;
	}
			
    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
			
    public List<TipoDeServicioRol> getTipoDeServicioRolList(){
		return this.tipoDeServicioRolList;
	}
	
	public void setTipoDeServicioRolList(List<TipoDeServicioRol> tipoDeServicioRolList){
		this.tipoDeServicioRolList = tipoDeServicioRolList;
	}
	
	public boolean getAplicaMauc() {
		return aplicaMauc;
	}


	public void setAplicaMauc(boolean aplicaMauc) {
		this.aplicaMauc = aplicaMauc;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ROL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idRol);        
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.preseleccion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + (this.aplicaMauc ? 0 : 1);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Rol que se pasa
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
        final Rol other = (Rol) obj;
        
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.preseleccion, other.preseleccion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
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
	// Escriba en esta sección sus modificaciones
	public List<Funcionalidad> getFuncionalidadList() {
		return funcionalidadList;
	}


	public void setFuncionalidadList(List<Funcionalidad> funcionalidadList) {
		this.funcionalidadList = funcionalidadList;
	}


	/**
	 * @return the parametrizarServicioRolList
	 */
	public List<ParametrizarServicioRol> getParametrizarServicioRolList() {
		return parametrizarServicioRolList;
	}


	/**
	 * @param parametrizarServicioRolList the parametrizarServicioRolList to set
	 */
	public void setParametrizarServicioRolList(List<ParametrizarServicioRol> parametrizarServicioRolList) {
		this.parametrizarServicioRolList = parametrizarServicioRolList;
	}


	
	
	// protected region metodos adicionales end
	

} 

