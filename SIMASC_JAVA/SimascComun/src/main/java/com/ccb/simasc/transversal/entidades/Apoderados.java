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
@Table(name="APODERADOS")
@NamedQuery(name = "Apoderados.findAll", query = "SELECT p FROM Apoderados p")
public class Apoderados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_APODERADOS_PK_ID_ROL_APODERADO = "apoderadosPK.idRolApoderado";
			
	public static final String ENTIDAD_APODERADOS_PK_ID_PERSONA_APODERADO = "apoderadosPK.idPersonaApoderado";
			
	public static final String ENTIDAD_APODERADOS_PK_ID_CASO_APODERADO = "apoderadosPK.idCasoApoderado";
			
	public static final String ENTIDAD_APODERADOS_PK_ID_ROL_REPRESENTADO = "apoderadosPK.idRolRepresentado";
			
	public static final String ENTIDAD_APODERADOS_PK_ID_PERSONA_REPRESENTADO = "apoderadosPK.idPersonaRepresentado";
			
	public static final String ENTIDAD_APODERADOS_PK_ID_CASO_REPRESENTADO = "apoderadosPK.idCasoRepresentado";
	public static final String ENTIDAD_APODERADOS_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_APODERADOS_ESTADO_REGISTRO_APODERADOS = "estadoRegistroApoderados";			
	public static final String ENTIDAD_APODERADOS_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_APODERADOS_ID_USUARIO_MODIFICAICON = "idUsuarioModificacion";
    private static final String[] ATRIBUTOS_ENTIDAD_APODERADOS
            = {ENTIDAD_APODERADOS_ID_USUARIO_MODIFICAICON, ENTIDAD_APODERADOS_PK_ID_ROL_APODERADO, ENTIDAD_APODERADOS_PK_ID_ROL_REPRESENTADO, ENTIDAD_APODERADOS_PK_ID_PERSONA_APODERADO, ENTIDAD_APODERADOS_ESTADO_REGISTRO, ENTIDAD_APODERADOS_PK_ID_CASO_APODERADO, ENTIDAD_APODERADOS_PK_ID_PERSONA_REPRESENTADO, ENTIDAD_APODERADOS_PK_ID_CASO_REPRESENTADO, ENTIDAD_APODERADOS_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private ApoderadosPK apoderadosPK;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_representado", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_representado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_representado", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCasoRepresentado;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_apoderado", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_apoderado", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_apoderado", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCasoApoderado;
		
	
	
    public Apoderados(){
		apoderadosPK = new ApoderadosPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ApoderadosPK getApoderadosPK(){
		return this.apoderadosPK;
	}
	
	public void setApoderadosPK(ApoderadosPK apoderadosPK){
		this.apoderadosPK   = apoderadosPK ;
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
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		

    public RolPersonaCaso getRolPersonaCasoApoderado(){
		return this.rolPersonaCasoApoderado; 
	}
	
	public void setRolPersonaCasoApoderado(RolPersonaCaso rolPersonaCasoApoderado){
		this.rolPersonaCasoApoderado= rolPersonaCasoApoderado;
	}
    public RolPersonaCaso getRolPersonaCasoRepresentado(){
		return this.rolPersonaCasoRepresentado; 
	}
	
	public void setRolPersonaCasoRepresentado(RolPersonaCaso rolPersonaCasoRepresentado){
		this.rolPersonaCasoRepresentado = rolPersonaCasoRepresentado;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_APODERADOS) {
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
        
        hash = 37 * hash + Objects.hashCode(this.apoderadosPK);        
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Apoderados que se pasa
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
        final Apoderados other = (Apoderados) obj;
        
        if (!Objects.equals(this.apoderadosPK, other.apoderadosPK)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

