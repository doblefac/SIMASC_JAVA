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
@Table(name="OBLIGACION_PARTE")
@NamedQuery(name = "ObligacionParte.findAll", query = "SELECT p FROM ObligacionParte p")
public class ObligacionParte implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_OBLIGACION_PARTE_PK_TIPO_OBLIGACION = "obligacionPartePK.tipoObligacion";
			
	public static final String ENTIDAD_OBLIGACION_PARTE_PK_ID_RESULTADO_AUDIENCIA = "obligacionPartePK.idResultadoAudiencia";
			
	public static final String ENTIDAD_OBLIGACION_PARTE_PK_ID_CASO = "obligacionPartePK.idCaso";
			
	public static final String ENTIDAD_OBLIGACION_PARTE_PK_ID_ROL = "obligacionPartePK.idRol";
			
	public static final String ENTIDAD_OBLIGACION_PARTE_PK_ID_PERSONA = "obligacionPartePK.idPersona";
	public static final String ENTIDAD_OBLIGACION_PARTE_TIPO_PARTE_RESULTADO = "tipoParteResultado";
	public static final String ENTIDAD_OBLIGACION_PARTE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_OBLIGACION_PARTE_ESTADO_REGISTRO_OBLIGACIONPARTE = "estadoRegistroObligacionParte";			
	public static final String ENTIDAD_OBLIGACION_PARTE_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_OBLIGACION_PARTE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
    private static final String[] ATRIBUTOS_ENTIDAD_OBLIGACION_PARTE
            = {ENTIDAD_OBLIGACION_PARTE_PK_ID_ROL, ENTIDAD_OBLIGACION_PARTE_PK_ID_RESULTADO_AUDIENCIA, ENTIDAD_OBLIGACION_PARTE_PK_ID_CASO, ENTIDAD_OBLIGACION_PARTE_PK_ID_PERSONA, ENTIDAD_OBLIGACION_PARTE_TIPO_PARTE_RESULTADO, ENTIDAD_OBLIGACION_PARTE_PK_TIPO_OBLIGACION, ENTIDAD_OBLIGACION_PARTE_ID_USUARIO_MODIFICACION, ENTIDAD_OBLIGACION_PARTE_FECHA_ULTIMA_MODIFICACION, ENTIDAD_OBLIGACION_PARTE_ESTADO_REGISTRO};

	@EmbeddedId
	private ObligacionPartePK obligacionPartePK;
    
	@Column(name="tipo_parte_resultado")
	private String tipoParteResultado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		

	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_resultado_audiencia", referencedColumnName="id_resultado_audiencia", insertable = false, updatable = false),
	    @JoinColumn(name = "tipo_obligacion", referencedColumnName="tipo_obligacion", insertable = false, updatable = false)	    
	})		
    private Obligacion obligacion;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	
	
    public ObligacionParte(){
		obligacionPartePK = new ObligacionPartePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ObligacionPartePK getObligacionPartePK(){
		return this.obligacionPartePK;
	}
	
	public void setObligacionPartePK(ObligacionPartePK obligacionPartePK){
		this.obligacionPartePK   = obligacionPartePK ;
	}  
	
	public String getTipoParteResultado(){
		return this.tipoParteResultado;
	}
	
	public void setTipoParteResultado(String tipoParteResultado){
		this.tipoParteResultado = tipoParteResultado;
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
		

    public Obligacion getObligacion(){
		return this.obligacion; 
	}
	
	public void setObligacion(Obligacion obligacion){
		this.obligacion = obligacion;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_OBLIGACION_PARTE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.obligacionPartePK);        
        hash = 37 * hash + Objects.hashCode(this.tipoParteResultado);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ObligacionParte que se pasa
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
        final ObligacionParte other = (ObligacionParte) obj;
        
        if (!Objects.equals(this.obligacionPartePK, other.obligacionPartePK)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoParteResultado, other.tipoParteResultado)) {
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

