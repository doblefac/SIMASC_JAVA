package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="UBICACION_ROL_PERSONA_CASO")
@NamedQuery(name = "UbicacionRolPersonaCaso.findAll", query = "SELECT p FROM UbicacionRolPersonaCaso p")
public class UbicacionRolPersonaCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_ROL = "ubicacionRolPersonaCasoPK.idRol";
			
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA = "ubicacionRolPersonaCasoPK.idPersona";
			
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_CASO = "ubicacionRolPersonaCasoPK.idCaso";
			
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_UBICACION = "ubicacionRolPersonaCasoPK.idUbicacion";
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_ESTADO_REGISTRO_UBICACIONROLPERSONACASO = "estadoRegistroUbicacionRolPersonaCaso";			
	public static final String ENTIDAD_UBICACION_ROL_PERSONA_CASO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_UBICACION_ROL_PERSONA_CASO
            = {ENTIDAD_UBICACION_ROL_PERSONA_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_UBICACION, ENTIDAD_UBICACION_ROL_PERSONA_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_CASO, ENTIDAD_UBICACION_ROL_PERSONA_CASO_ESTADO_REGISTRO, ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_ROL, ENTIDAD_UBICACION_ROL_PERSONA_CASO_PK_ID_PERSONA};

	@EmbeddedId
	private UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@ManyToOne
	@JoinColumn(name="id_ubicacion", referencedColumnName="id_ubicacion", insertable = false, updatable = false)
    private Ubicacion ubicacion;
		
	
	
    public UbicacionRolPersonaCaso(){
		ubicacionRolPersonaCasoPK = new UbicacionRolPersonaCasoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public UbicacionRolPersonaCasoPK getUbicacionRolPersonaCasoPK(){
		return this.ubicacionRolPersonaCasoPK;
	}
	
	public void setUbicacionRolPersonaCasoPK(UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK){
		this.ubicacionRolPersonaCasoPK   = ubicacionRolPersonaCasoPK ;
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
		

    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_UBICACION_ROL_PERSONA_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.ubicacionRolPersonaCasoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UbicacionRolPersonaCaso que se pasa
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
        final UbicacionRolPersonaCaso other = (UbicacionRolPersonaCaso) obj;
        
        if (!Objects.equals(this.ubicacionRolPersonaCasoPK, other.ubicacionRolPersonaCasoPK)) {
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

