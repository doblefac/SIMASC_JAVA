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
@Table(name="USUARIO")
@NamedQuery(name = "Usuario.findAll", query = "SELECT p FROM Usuario p")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_USUARIO_PK = "usuarioLogin";
	public static final String ENTIDAD_USUARIO_ESTADO = "estado";
	public static final String ENTIDAD_USUARIO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_USUARIO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_USUARIO_ULTIMO_ACCESO = "ultimoAcceso";
	public static final String ENTIDAD_USUARIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_USUARIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_USUARIO_APLICA_MAUC= "aplicaMauc";

    private static final String[] ATRIBUTOS_ENTIDAD_USUARIO
            = {ENTIDAD_USUARIO_ESTADO, ENTIDAD_USUARIO_ID_PERSONA, ENTIDAD_USUARIO_OBSERVACIONES, ENTIDAD_USUARIO_PK, ENTIDAD_USUARIO_ID_USUARIO_MODIFICACION, ENTIDAD_USUARIO_ULTIMO_ACCESO, ENTIDAD_USUARIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_USUARIO_APLICA_MAUC};

	@Id
    @Column(name="usuario_login")
	private String usuarioLogin;
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="ultimo_acceso")
	private Date ultimoAcceso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;	
	
	@Column(name="aplica_mauc")
	private boolean aplicaMauc;	
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
	
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@OneToMany(mappedBy="usuario")
    private List<Clave> claveList;
	@OneToMany(mappedBy="usuario")
    private List<Llamada> llamadaList;
	
	
    public Usuario(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }
	public String getUsuarioLogin(){
		return this.usuarioLogin;
	}
	
	public void setUsuarioLogin(String usuarioLogin){
		this.usuarioLogin = usuarioLogin;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public Date getUltimoAcceso(){
		return this.ultimoAcceso;
	}
	
	public void setUltimoAcceso(Date ultimoAcceso){
		this.ultimoAcceso = ultimoAcceso;
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
		

    public List<Clave> getClaveList(){
		return this.claveList;
	}
	
	public void setClaveList(List<Clave> claveList){
		this.claveList = claveList;
	}
			
    public List<Llamada> getLlamadaList(){
		return this.llamadaList;
	}
	
	public void setLlamadaList(List<Llamada> llamadaList){
		this.llamadaList = llamadaList;
	}
			
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_USUARIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.usuarioLogin);        
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.ultimoAcceso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.aplicaMauc);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Usuario que se pasa
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
        final Usuario other = (Usuario) obj;
        
        if (!Objects.equals(this.usuarioLogin, other.usuarioLogin)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.ultimoAcceso, other.ultimoAcceso)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aplicaMauc, other.aplicaMauc)) {
            return false;
        }
        
        return Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    
    public Usuario(String usuarioLogin, String estado, Long idPersona) {
		super();
		this.usuarioLogin = usuarioLogin;
		this.estado = estado;
		this.idPersona = idPersona;
	}

	// protected region metodos adicionales end

} 

