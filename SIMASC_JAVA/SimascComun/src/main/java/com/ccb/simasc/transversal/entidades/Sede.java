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
@Table(name="SEDE")
@NamedQuery(name = "Sede.findAll", query = "SELECT p FROM Sede p")
public class Sede implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	@OneToMany(mappedBy="sede", cascade=CascadeType.ALL)
    private List<TelefonoSede> telefonoSedeList;

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SEDE_PK = "idSede";
	public static final String ENTIDAD_SEDE_NOMBRE = "nombre";
	public static final String ENTIDAD_SEDE_VIRTUAL = "virtual";
	public static final String ENTIDAD_SEDE_TIPO_SEDE = "tipoSede";
	public static final String ENTIDAD_SEDE_DIRECCION = "direccion";
	public static final String ENTIDAD_SEDE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SEDE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SEDE_ESTADO_REGISTRO_SEDE = "estadoRegistroSede";			
	public static final String ENTIDAD_SEDE_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SEDE_ID_CENTRO = "idCentro";
    private static final String[] ATRIBUTOS_ENTIDAD_SEDE
            = {ENTIDAD_SEDE_NOMBRE, ENTIDAD_SEDE_PK, ENTIDAD_SEDE_TIPO_SEDE, ENTIDAD_SEDE_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SEDE_ESTADO_REGISTRO, ENTIDAD_SEDE_ID_CENTRO, ENTIDAD_SEDE_DIRECCION, ENTIDAD_SEDE_VIRTUAL, ENTIDAD_SEDE_ID_USUARIO_MODIFICACION};

	@Id
    @Column(name="id_sede")
	private Long idSede;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="virtual")
	private boolean virtual;		
    
	@Column(name="tipo_sede")
	private String tipoSede;		
    
	@Column(name="direccion")
	private String direccion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_centro")
	private Long idCentro;		

	@ManyToOne
	@JoinColumn(name="id_centro", referencedColumnName="id_centro", insertable = false, updatable = false)
    private Centro centro;
		
	@OneToMany(mappedBy="sede")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="sede")
    private List<Caso> casoList;
	@OneToMany(mappedBy="sede")
    private List<PagoCaso> pagoCasoList;
	@OneToMany(mappedBy="sede")
    private List<PersonaSede> personaSedeList;
	@OneToMany(mappedBy="sede")
    private List<Sala> salaList;
	@OneToMany(mappedBy="sede")
    private List<SedeConvenio> sedeConvenioList;
	@OneToMany(mappedBy="sede")
    private List<ServicioSede> servicioSedeList;
	@OneToMany(mappedBy="sede")
    private List<SolicitudServicio> solicitudServicioList;
	@OneToMany(mappedBy="sede")
    private List<TipoServicioSede> tipoServicioSedeList;
	
	
    public Sede(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getVirtual(){
		return this.virtual;
	}
	
	public void setVirtual(boolean virtual){
		this.virtual = virtual;
	}
		
	public String getTipoSede(){
		return this.tipoSede;
	}
	
	public void setTipoSede(String tipoSede){
		this.tipoSede = tipoSede;
	}
		
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
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
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		

    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}
			
    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public List<PagoCaso> getPagoCasoList(){
		return this.pagoCasoList;
	}
	
	public void setPagoCasoList(List<PagoCaso> pagoCasoList){
		this.pagoCasoList = pagoCasoList;
	}
			
    public List<PersonaSede> getPersonaSedeList(){
		return this.personaSedeList;
	}
	
	public void setPersonaSedeList(List<PersonaSede> personaSedeList){
		this.personaSedeList = personaSedeList;
	}
			
    public List<Sala> getSalaList(){
		return this.salaList;
	}
	
	public void setSalaList(List<Sala> salaList){
		this.salaList = salaList;
	}
			
    public List<SedeConvenio> getSedeConvenioList(){
		return this.sedeConvenioList;
	}
	
	public void setSedeConvenioList(List<SedeConvenio> sedeConvenioList){
		this.sedeConvenioList = sedeConvenioList;
	}
			
    public List<ServicioSede> getServicioSedeList(){
		return this.servicioSedeList;
	}
	
	public void setServicioSedeList(List<ServicioSede> servicioSedeList){
		this.servicioSedeList = servicioSedeList;
	}
			
    public List<SolicitudServicio> getSolicitudServicioList(){
		return this.solicitudServicioList;
	}
	
	public void setSolicitudServicioList(List<SolicitudServicio> solicitudServicioList){
		this.solicitudServicioList = solicitudServicioList;
	}
			
    public List<TelefonoSede> getTelefonoSedeList(){
		return this.telefonoSedeList;
	}
	
	public void setTelefonoSedeList(List<TelefonoSede> telefonoSedeList){
		this.telefonoSedeList = telefonoSedeList;
	}
			
    public List<TipoServicioSede> getTipoServicioSedeList(){
		return this.tipoServicioSedeList;
	}
	
	public void setTipoServicioSedeList(List<TipoServicioSede> tipoServicioSedeList){
		this.tipoServicioSedeList = tipoServicioSedeList;
	}
			
    public Centro getCentro(){
		return this.centro; 
	}
	
	public void setCentro(Centro centro){
		this.centro = centro;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SEDE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSede);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.virtual ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoSede);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Sede que se pasa
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
        final Sede other = (Sede) obj;
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.virtual, other.virtual)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoSede, other.tipoSede)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
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
        
        return Objects.equals(this.idCentro, other.idCentro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

