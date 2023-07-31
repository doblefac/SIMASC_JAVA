package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="UBICACION")
@NamedQuery(name = "Ubicacion.findAll", query = "SELECT p FROM Ubicacion p")
public class Ubicacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_UBICACION_PK = "idUbicacion";
	public static final String ENTIDAD_UBICACION_DIRECCION = "direccion";
	public static final String ENTIDAD_UBICACION_LATITUD = "latitud";
	public static final String ENTIDAD_UBICACION_LONGITUD = "longitud";
	public static final String ENTIDAD_UBICACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_UBICACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_UBICACION_ESTADO_REGISTRO_UBICACION = "estadoRegistroUbicacion";			
	public static final String ENTIDAD_UBICACION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_UBICACION_ID_ZONA_GEOGRAFICA = "idZonaGeografica";
	public static final String ENTIDAD_UBICACION_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_UBICACION_TIPO = "tipo";
    private static final String[] ATRIBUTOS_ENTIDAD_UBICACION
            = {ENTIDAD_UBICACION_LONGITUD, ENTIDAD_UBICACION_DIRECCION, ENTIDAD_UBICACION_ID_ZONA_GEOGRAFICA, ENTIDAD_UBICACION_ID_PERSONA, ENTIDAD_UBICACION_LATITUD, ENTIDAD_UBICACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_UBICACION_ID_USUARIO_MODIFICACION, ENTIDAD_UBICACION_ESTADO_REGISTRO, ENTIDAD_UBICACION_TIPO, ENTIDAD_UBICACION_PK};

	@Id
    @Column(name="id_ubicacion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idUbicacion;
    
	@Column(name="direccion")
	private String direccion;		
    
	@Column(name="latitud")
	private BigDecimal latitud;		
    
	@Column(name="longitud")
	private BigDecimal longitud;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_zona_geografica")
	private String idZonaGeografica;		
    
	@Column(name="id_persona")
	private Long idPersona;		
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="localidad")
	private String localidad;
	
	@Column(name="barrio")
	private String barrio;

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_zona_geografica", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="ubicacion")
    private List<CorreoElectronico> correoElectronicoList;
	@OneToMany(mappedBy="ubicacion")
    private List<ResultadoAudiencia> resultadoAudienciaList;
	@OneToMany(mappedBy="ubicacion")
    private List<Telefono> telefonoList;
	
	@OneToMany(mappedBy="ubicacion")
    private List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList;
	
	
    public Ubicacion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
		
	public BigDecimal getLatitud(){
		return this.latitud;
	}
	
	public void setLatitud(BigDecimal latitud){
		this.latitud = latitud;
	}
		
	public BigDecimal getLongitud(){
		return this.longitud;
	}
	
	public void setLongitud(BigDecimal longitud){
		this.longitud = longitud;
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
		
	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}		

    public List<CorreoElectronico> getCorreoElectronicoList(){
		return this.correoElectronicoList;
	}
	
	public void setCorreoElectronicoList(List<CorreoElectronico> correoElectronicoList){
		this.correoElectronicoList = correoElectronicoList;
	}
			
    public List<ResultadoAudiencia> getResultadoAudienciaList(){
		return this.resultadoAudienciaList;
	}
	
	public void setResultadoAudienciaList(List<ResultadoAudiencia> resultadoAudienciaList){
		this.resultadoAudienciaList = resultadoAudienciaList;
	}
			
    public List<Telefono> getTelefonoList(){
		return this.telefonoList;
	}
	
	public void setTelefonoList(List<Telefono> telefonoList){
		this.telefonoList = telefonoList;
	}
			
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getBarrio() {
		return barrio;
	}


	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}


	public List<UbicacionPersonaSolicitud> getUbicacionPersonaSolicitudList(){
		return this.ubicacionPersonaSolicitudList;
	}
	
	public void setUbicacionPersonaSolicitudList(List<UbicacionPersonaSolicitud> ubicacionPersonaSolicitudList){
		this.ubicacionPersonaSolicitudList = ubicacionPersonaSolicitudList;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_UBICACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);        
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.latitud);
        hash = 37 * hash + Objects.hashCode(this.longitud);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.localidad);
        hash = 37 * hash + Objects.hashCode(this.barrio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Ubicacion que se pasa
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
        final Ubicacion other = (Ubicacion) obj;
        
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        
        if (!Objects.equals(this.latitud, other.latitud)) {
            return false;
        }
        
        if (!Objects.equals(this.longitud, other.longitud)) {
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
        
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        
        if (!Objects.equals(this.barrio, other.barrio)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

