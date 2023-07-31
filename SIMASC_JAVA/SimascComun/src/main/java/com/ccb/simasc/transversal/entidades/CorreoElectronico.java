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

import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CORREO_ELECTRONICO")
@NamedQuery(name = "CorreoElectronico.findAll", query = "SELECT p FROM CorreoElectronico p")
public class CorreoElectronico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CORREO_ELECTRONICO_PK = "idCorreo";
	public static final String ENTIDAD_CORREO_ELECTRONICO_DIRECCION = "direccion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_TIPO = "tipo";
	public static final String ENTIDAD_CORREO_ELECTRONICO_ID_UBICACION = "idUbicacion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO_CORREOELECTRONICO = "estadoRegistroCorreoElectronico";			
	public static final String ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_CORREO_ELECTRONICO
            = {ENTIDAD_CORREO_ELECTRONICO_PK, ENTIDAD_CORREO_ELECTRONICO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CORREO_ELECTRONICO_ID_UBICACION, ENTIDAD_CORREO_ELECTRONICO_ID_USUARIO_MODIFICACION, ENTIDAD_CORREO_ELECTRONICO_TIPO, ENTIDAD_CORREO_ELECTRONICO_ID_PERSONA, ENTIDAD_CORREO_ELECTRONICO_DIRECCION, ENTIDAD_CORREO_ELECTRONICO_ESTADO_REGISTRO};

	@Id
    @Column(name="id_correo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCorreo;
    
	@Column(name="direccion")
	private String direccion;	
	
	@Column(name="direccion_anterior")
	private String direccionAnterior;
    
	@Column(name="tipo")
	private String tipo;		
    
	@Column(name="id_ubicacion")
	private Long idUbicacion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_persona")
	private Long idPersona;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_ubicacion", referencedColumnName="id_ubicacion", insertable = false, updatable = false)
    private Ubicacion ubicacion;
	
	@OneToMany(mappedBy="correoElectronico")
	private List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList;
	
	@OneToMany(mappedBy="correoElectronico")
    private List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList;
	
	
    public CorreoElectronico(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCorreo(){
		return this.idCorreo;
	}
	
	public void setIdCorreo(Long idCorreo){
		this.idCorreo = idCorreo;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	public String getDireccionAnterior() {
		return direccionAnterior;
	}

	public void setDireccionAnterior(String direccionAnterior) {
		this.direccionAnterior = direccionAnterior;
	}

	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
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
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		

    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
	
    public Ubicacion getUbicacion(){
		return this.ubicacion; 
	}
	
	public void setUbicacion(Ubicacion ubicacion){
		this.ubicacion = ubicacion;
	}	

	public List<CorreoElectronicoRolPersonaCaso> getCorreoElectronicoRolPersonaCasoList() {
		return correoElectronicoRolPersonaCasoList;
	}

	public void setCorreoElectronicoRolPersonaCasoList(List<CorreoElectronicoRolPersonaCaso> correoElectronicoRolPersonaCasoList) {
		this.correoElectronicoRolPersonaCasoList = correoElectronicoRolPersonaCasoList;
	}	
	
	public List<CorreoElectronicoPersonaSolicitud> getCorreoElectronicoPersonaSolicitudList(){
		return this.correoElectronicoPersonaSolicitudList;
	}
	
	public void setCorreoElectronicoPersonaSolicitudList(List<CorreoElectronicoPersonaSolicitud> correoElectronicoPersonaSolicitudList){
		this.correoElectronicoPersonaSolicitudList = correoElectronicoPersonaSolicitudList;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CORREO_ELECTRONICO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCorreo);        
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CorreoElectronico que se pasa
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
        final CorreoElectronico other = (CorreoElectronico) obj;
        
        if (!Objects.equals(this.idCorreo, other.idCorreo)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
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
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
    /**
	 * Método encargado de convertir Entidad CorreoElectronico a DTO
	 * CorreoElectronicoDTO
	 * 
	 * @param electronico
	 * @return CorreoElectronicoDTO
	 */
    public CorreoElectronicoDTO convertirEntidadToCorreoElectronicoDto(CorreoElectronico electronico) {
		CorreoElectronicoDTO correoElectronicoDTO = new CorreoElectronicoDTO();
		correoElectronicoDTO.setDireccion(electronico.getDireccion());
		correoElectronicoDTO.setDireccionAnterior(electronico.getDireccionAnterior());
		correoElectronicoDTO.setTipo(electronico.getTipo());
		correoElectronicoDTO.setIdUsuarioModificacion(electronico.getIdUsuarioModificacion());
		correoElectronicoDTO.setFechaUltimaModificacion(electronico.getFechaUltimaModificacion());
		correoElectronicoDTO.setEstadoRegistro(electronico.getEstadoRegistro());
		correoElectronicoDTO.setIdCorreo(electronico.getIdCorreo());
		correoElectronicoDTO.setIdUbicacion(electronico.getIdUbicacion() != null ? electronico.getIdUbicacion() : null);
		correoElectronicoDTO.setIdPersona(electronico.getIdPersona() != null ? electronico.getIdPersona() : null);
		return correoElectronicoDTO;
	}
    
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

