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
@Table(name="TELEFONO")
@NamedQuery(name = "Telefono.findAll", query = "SELECT p FROM Telefono p")
public class Telefono implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TELEFONO_PK = "idTelefono";
	public static final String ENTIDAD_TELEFONO_NUMERO = "numero";
	public static final String ENTIDAD_TELEFONO_TIPO_TELEFONO = "tipoTelefono";
	public static final String ENTIDAD_TELEFONO_EXTENSION = "extension";
	public static final String ENTIDAD_TELEFONO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TELEFONO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TELEFONO_ESTADO_REGISTRO_TELEFONO = "estadoRegistroTelefono";			
	public static final String ENTIDAD_TELEFONO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_TELEFONO_ID_UBICACION = "idUbicacion";
	public static final String ENTIDAD_TELEFONO_ID_PERSONA = "idPersona";
    private static final String[] ATRIBUTOS_ENTIDAD_TELEFONO
            = {ENTIDAD_TELEFONO_EXTENSION, ENTIDAD_TELEFONO_PK, ENTIDAD_TELEFONO_NUMERO, ENTIDAD_TELEFONO_TIPO_TELEFONO, ENTIDAD_TELEFONO_ID_PERSONA, ENTIDAD_TELEFONO_ID_UBICACION, ENTIDAD_TELEFONO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TELEFONO_ID_USUARIO_MODIFICACION, ENTIDAD_TELEFONO_ESTADO_REGISTRO};

	@Id
    @Column(name="id_telefono")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTelefono;
    
	@Column(name="numero")
	private String numero;		
    
	@Column(name="tipo_telefono")
	private String tipoTelefono;		
    
	@Column(name="extension")
	private String extension;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_ubicacion")
	private Long idUbicacion;		
    
	@Column(name="id_persona")
	private Long idPersona;		

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_ubicacion", referencedColumnName="id_ubicacion", insertable = false, updatable = false)
    private Ubicacion ubicacion;
		
	@OneToMany(mappedBy="telefono")
    private List<Llamada> llamadaList;
	
	
    public Telefono(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTelefono(){
		return this.idTelefono;
	}
	
	public void setIdTelefono(Long idTelefono){
		this.idTelefono = idTelefono;
	}
	
	public String getNumero(){
		return this.numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
		
	public String getTipoTelefono(){
		return this.tipoTelefono;
	}
	
	public void setTipoTelefono(String tipoTelefono){
		this.tipoTelefono = tipoTelefono;
	}
		
	public String getExtension(){
		return this.extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
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
		
	public Long getIdUbicacion(){
		return this.idUbicacion;
	}
	
	public void setIdUbicacion(Long idUbicacion){
		this.idUbicacion = idUbicacion;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
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
    public Ubicacion getUbicacion(){
		return this.ubicacion; 
	}
	
	public void setUbicacion(Ubicacion ubicacion){
		this.ubicacion = ubicacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TELEFONO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTelefono);        
        hash = 37 * hash + Objects.hashCode(this.numero);
        hash = 37 * hash + Objects.hashCode(this.tipoTelefono);
        hash = 37 * hash + Objects.hashCode(this.extension);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUbicacion);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Telefono que se pasa
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
        final Telefono other = (Telefono) obj;
        
        if (!Objects.equals(this.idTelefono, other.idTelefono)) {
            return false;
        }
        
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTelefono, other.tipoTelefono)) {
            return false;
        }
        
        if (!Objects.equals(this.extension, other.extension)) {
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
        
        if (!Objects.equals(this.idUbicacion, other.idUbicacion)) {
            return false;
        }
        
        return Objects.equals(this.idPersona, other.idPersona);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

