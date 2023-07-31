package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="ZONA_GEOGRAFICA")
@NamedQuery(name = "ZonaGeografica.findAll", query = "SELECT p FROM ZonaGeografica p")
public class ZonaGeografica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ZONA_GEOGRAFICA_PK = "idZonaGeografica";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_NOMBRE = "nombre";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_ID_ZONA_GEOGRAFICA_PADRE = "idZonaGeograficaPadre";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_ESTADO_REGISTRO_ZONAGEOGRAFICA = "estadoRegistroZonaGeografica";			
	public static final String ENTIDAD_ZONA_GEOGRAFICA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_ZONA_GEOGRAFICA_ID_TIPO_ZONA_GEOGRAFICA = "idTipoZonaGeografica";
    private static final String[] ATRIBUTOS_ENTIDAD_ZONA_GEOGRAFICA
            = {ENTIDAD_ZONA_GEOGRAFICA_NOMBRE, ENTIDAD_ZONA_GEOGRAFICA_ID_USUARIO_MODIFICACION, ENTIDAD_ZONA_GEOGRAFICA_ESTADO_REGISTRO, ENTIDAD_ZONA_GEOGRAFICA_PK, ENTIDAD_ZONA_GEOGRAFICA_ID_ZONA_GEOGRAFICA_PADRE, ENTIDAD_ZONA_GEOGRAFICA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_ZONA_GEOGRAFICA_ID_TIPO_ZONA_GEOGRAFICA};

	@Id
    @Column(name="id_zona_geografica")
	private String idZonaGeografica;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="id_zona_geografica_padre")
	private String idZonaGeograficaPadre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_tipo_zona_geografica")
	private Integer idTipoZonaGeografica;		

	@ManyToOne
	@JoinColumn(name="id_tipo_zona_geografica", referencedColumnName="id_tipo_zona_geografica", insertable = false, updatable = false)
    private TipoZonaGeografica tipoZonaGeografica;
		
	@ManyToOne
	@JoinColumn(name="id_zona_geografica_padre", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="zonaGeografica")
    private List<Caso> casoList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<Centro> centroList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<Convenio> convenioList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<Persona> personaList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<SolicitudServicio> solicitudServicioList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<Ubicacion> ubicacionList;
	@OneToMany(mappedBy="zonaGeografica")
    private List<ZonaGeografica> zonaGeograficaList;
	
	
    public ZonaGeografica(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getIdZonaGeograficaPadre(){
		return this.idZonaGeograficaPadre;
	}
	
	public void setIdZonaGeograficaPadre(String idZonaGeograficaPadre){
		this.idZonaGeograficaPadre = idZonaGeograficaPadre;
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
		
	public Integer getIdTipoZonaGeografica(){
		return this.idTipoZonaGeografica;
	}
	
	public void setIdTipoZonaGeografica(Integer idTipoZonaGeografica){
		this.idTipoZonaGeografica = idTipoZonaGeografica;
	}
		

    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public List<Centro> getCentroList(){
		return this.centroList;
	}
	
	public void setCentroList(List<Centro> centroList){
		this.centroList = centroList;
	}
			
    public List<Convenio> getConvenioList(){
		return this.convenioList;
	}
	
	public void setConvenioList(List<Convenio> convenioList){
		this.convenioList = convenioList;
	}
			
    public List<Persona> getPersonaList(){
		return this.personaList;
	}
	
	public void setPersonaList(List<Persona> personaList){
		this.personaList = personaList;
	}
			
    public List<SolicitudServicio> getSolicitudServicioList(){
		return this.solicitudServicioList;
	}
	
	public void setSolicitudServicioList(List<SolicitudServicio> solicitudServicioList){
		this.solicitudServicioList = solicitudServicioList;
	}
			
    public List<Ubicacion> getUbicacionList(){
		return this.ubicacionList;
	}
	
	public void setUbicacionList(List<Ubicacion> ubicacionList){
		this.ubicacionList = ubicacionList;
	}
			
    public List<ZonaGeografica> getZonaGeograficaList(){
		return this.zonaGeograficaList;
	}
	
	public void setZonaGeograficaList(List<ZonaGeografica> zonaGeograficaList){
		this.zonaGeograficaList = zonaGeograficaList;
	}
			
    public TipoZonaGeografica getTipoZonaGeografica(){
		return this.tipoZonaGeografica; 
	}
	
	public void setTipoZonaGeografica(TipoZonaGeografica tipoZonaGeografica){
		this.tipoZonaGeografica = tipoZonaGeografica;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_ZONA_GEOGRAFICA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeograficaPadre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeografica);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ZonaGeografica que se pasa
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
        final ZonaGeografica other = (ZonaGeografica) obj;
        
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.idZonaGeograficaPadre, other.idZonaGeograficaPadre)) {
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
        
        return Objects.equals(this.idTipoZonaGeografica, other.idTipoZonaGeografica);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

