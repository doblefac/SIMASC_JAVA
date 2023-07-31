package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="RELACIONADO_CONVENIO")
@NamedQuery(name = "RelacionadoConvenio.findAll", query = "SELECT p FROM RelacionadoConvenio p")
public class RelacionadoConvenio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_RELACIONADO_CONVENIO_PK_ID_CONVENIO = "relacionadoConvenioPK.idConvenio";
			
	public static final String ENTIDAD_RELACIONADO_CONVENIO_PK_ID_PERSONA = "relacionadoConvenioPK.idPersona";
			
	public static final String ENTIDAD_RELACIONADO_CONVENIO_PK_ID_ROL = "relacionadoConvenioPK.idRol";
	public static final String ENTIDAD_RELACIONADO_CONVENIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_RELACIONADO_CONVENIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO_RELACIONADOCONVENIO = "estadoRegistroRelacionadoConvenio";			
	public static final String ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_RELACIONADO_CONVENIO
            = {ENTIDAD_RELACIONADO_CONVENIO_ID_USUARIO_MODIFICACION, ENTIDAD_RELACIONADO_CONVENIO_PK_ID_CONVENIO, ENTIDAD_RELACIONADO_CONVENIO_PK_ID_PERSONA, ENTIDAD_RELACIONADO_CONVENIO_ESTADO_REGISTRO, ENTIDAD_RELACIONADO_CONVENIO_PK_ID_ROL, ENTIDAD_RELACIONADO_CONVENIO_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private RelacionadoConvenioPK relacionadoConvenioPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_convenio", referencedColumnName="id_convenio", insertable = false, updatable = false)
    private Convenio convenio;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	
	
    public RelacionadoConvenio(){
		relacionadoConvenioPK = new RelacionadoConvenioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public RelacionadoConvenioPK getRelacionadoConvenioPK(){
		return this.relacionadoConvenioPK;
	}
	
	public void setRelacionadoConvenioPK(RelacionadoConvenioPK relacionadoConvenioPK){
		this.relacionadoConvenioPK   = relacionadoConvenioPK ;
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
		

    public Convenio getConvenio(){
		return this.convenio; 
	}
	
	public void setConvenio(Convenio convenio){
		this.convenio = convenio;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_RELACIONADO_CONVENIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.relacionadoConvenioPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad RelacionadoConvenio que se pasa
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
        final RelacionadoConvenio other = (RelacionadoConvenio) obj;
        
        if (!Objects.equals(this.relacionadoConvenioPK, other.relacionadoConvenioPK)) {
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

