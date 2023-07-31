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
@Table(name="INASISTENCIA")
@NamedQuery(name = "Inasistencia.findAll", query = "SELECT p FROM Inasistencia p")
public class Inasistencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_INASISTENCIA_PK_ID_AUDIENCIA = "inasistenciaPK.idAudiencia";
			
	public static final String ENTIDAD_INASISTENCIA_PK_ID_ROL = "inasistenciaPK.idRol";
			
	public static final String ENTIDAD_INASISTENCIA_PK_ID_PERSONA = "inasistenciaPK.idPersona";
			
	public static final String ENTIDAD_INASISTENCIA_PK_ID_CASO = "inasistenciaPK.idCaso";
	public static final String ENTIDAD_INASISTENCIA_PRESENTA_EXCUSA = "presentaExcusa";
	public static final String ENTIDAD_INASISTENCIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_INASISTENCIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_INASISTENCIA_ESTADO_REGISTRO_INASISTENCIA = "estadoRegistroInasistencia";			
	public static final String ENTIDAD_INASISTENCIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_INASISTENCIA_TIPO = "tipo";
    private static final String[] ATRIBUTOS_ENTIDAD_INASISTENCIA
            = {ENTIDAD_INASISTENCIA_PK_ID_CASO, ENTIDAD_INASISTENCIA_PK_ID_ROL, ENTIDAD_INASISTENCIA_ID_USUARIO_MODIFICACION, ENTIDAD_INASISTENCIA_TIPO, ENTIDAD_INASISTENCIA_PK_ID_PERSONA, ENTIDAD_INASISTENCIA_ESTADO_REGISTRO, ENTIDAD_INASISTENCIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_INASISTENCIA_PRESENTA_EXCUSA, ENTIDAD_INASISTENCIA_PK_ID_AUDIENCIA};

	@EmbeddedId
	private InasistenciaPK inasistenciaPK;
    
	@Column(name="presenta_excusa")
	private boolean presentaExcusa;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="tipo")
	private String tipo;		
	
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_audiencia", referencedColumnName="id_audiencia", insertable = false, updatable = false)
    private Audiencia audiencia;
	
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	
	
    public Inasistencia(){
		inasistenciaPK = new InasistenciaPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public InasistenciaPK getInasistenciaPK(){
		return this.inasistenciaPK;
	}
	
	public void setInasistenciaPK(InasistenciaPK inasistenciaPK){
		this.inasistenciaPK   = inasistenciaPK ;
	}  
	
	public boolean getPresentaExcusa(){
		return this.presentaExcusa;
	}
	
	public void setPresentaExcusa(boolean presentaExcusa){
		this.presentaExcusa = presentaExcusa;
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
		
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		

    public Audiencia getAudiencia(){
		return this.audiencia; 
	}
	
	public void setAudiencia(Audiencia audiencia){
		this.audiencia = audiencia;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}
	
	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_INASISTENCIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.inasistenciaPK);        
        hash = 37 * hash + (this.presentaExcusa ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Inasistencia que se pasa
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
        final Inasistencia other = (Inasistencia) obj;
        
        if (!Objects.equals(this.inasistenciaPK, other.inasistenciaPK)) {
            return false;
        }
        
        if (!Objects.equals(this.presentaExcusa, other.presentaExcusa)) {
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
        
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        
        return Objects.equals(this.tipo, other.tipo);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

