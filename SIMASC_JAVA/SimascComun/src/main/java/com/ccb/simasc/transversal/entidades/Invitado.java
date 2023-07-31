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

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="INVITADO")
@NamedQuery(name = "Invitado.findAll", query = "SELECT p FROM Invitado p")
public class Invitado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_INVITADO_PK = "idInvitado";
	public static final String ENTIDAD_INVITADO_ID_AUDIENCIA = "idAudiencia";
	public static final String ENTIDAD_INVITADO_NOMBRE = "nombre";
	public static final String ENTIDAD_INVITADO_CORREO = "correo";
	public static final String ENTIDAD_INVITADO_CIUDAD = "ciudad";
	public static final String ENTIDAD_INVITADO_DIRECCION = "direccion";
	public static final String ENTIDAD_INVITADO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_INVITADO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_INVITADO_ESTADO_REGISTRO_INVITADO = "estadoRegistroInvitado";			
	public static final String ENTIDAD_INVITADO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_INVITADO
            = {ENTIDAD_INVITADO_NOMBRE, ENTIDAD_INVITADO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_INVITADO_ID_USUARIO_MODIFICACION, ENTIDAD_INVITADO_ID_AUDIENCIA, ENTIDAD_INVITADO_DIRECCION, ENTIDAD_INVITADO_ESTADO_REGISTRO, ENTIDAD_INVITADO_CIUDAD, ENTIDAD_INVITADO_PK, ENTIDAD_INVITADO_CORREO};

	@Id
    @Column(name="id_invitado")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idInvitado;
    
	@Column(name="id_audiencia")
	private Long idAudiencia;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="correo")
	private String correo;		
    
	@Column(name="ciudad")
	private String ciudad;		
    
	@Column(name="direccion")
	private String direccion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
		
	@OneToMany(mappedBy="invitado")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="invitado")
    private List<CorreoRolPersonaCaso> correoRolPersonaCasoList;
	
	
    public Invitado(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdInvitado(){
		return this.idInvitado;
	}
	
	public void setIdInvitado(Long idInvitado){
		this.idInvitado = idInvitado;
	}
	
	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public String getCorreo(){
		return this.correo;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}
		
	public String getCiudad(){
		return this.ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
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
		

    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}
			
    public List<CorreoRolPersonaCaso> getCorreoRolPersonaCasoList(){
		return this.correoRolPersonaCasoList;
	}
	
	public void setCorreoRolPersonaCasoList(List<CorreoRolPersonaCaso> correoRolPersonaCasoList){
		this.correoRolPersonaCasoList = correoRolPersonaCasoList;
	}
			
    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_INVITADO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idInvitado);        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.correo);
        hash = 37 * hash + Objects.hashCode(this.ciudad);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Invitado que se pasa
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
        final Invitado other = (Invitado) obj;
        
        if (!Objects.equals(this.idInvitado, other.idInvitado)) {
            return false;
        }
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudad, other.ciudad)) {
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
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

