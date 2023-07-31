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
import javax.persistence.JoinColumns;
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
@Table(name="PARTE_DE_LA_RECUSACION")
@NamedQuery(name = "ParteDeLaRecusacion.findAll", query = "SELECT p FROM ParteDeLaRecusacion p")
public class ParteDeLaRecusacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_ROL_PARTE = "parteDeLaRecusacionPK.idRolParte";
			
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_PERSONA_PARTE = "parteDeLaRecusacionPK.idPersonaParte";
			
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_CASO_PARTE = "parteDeLaRecusacionPK.idCasoParte";
			
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_RECUSACION = "parteDeLaRecusacionPK.idRecusacion";
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_ESTADO_REGISTRO_PARTEDELARECUSACION = "estadoRegistroParteDeLaRecusacion";			
	public static final String ENTIDAD_PARTE_DE_LA_RECUSACION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARTE_DE_LA_RECUSACION
            = {ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_CASO_PARTE, ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_ROL_PARTE, ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_PERSONA_PARTE, ENTIDAD_PARTE_DE_LA_RECUSACION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARTE_DE_LA_RECUSACION_ESTADO_REGISTRO, ENTIDAD_PARTE_DE_LA_RECUSACION_ID_USUARIO_MODIFICACION, ENTIDAD_PARTE_DE_LA_RECUSACION_PK_ID_RECUSACION};

	@EmbeddedId
	private ParteDeLaRecusacionPK parteDeLaRecusacionPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_recusacion", referencedColumnName="id_recusacion", insertable = false, updatable = false)
    private Recusacion recusacion;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_parte", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_parte", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_parte", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	
	
    public ParteDeLaRecusacion(){
		parteDeLaRecusacionPK = new ParteDeLaRecusacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParteDeLaRecusacionPK getParteDeLaRecusacionPK(){
		return this.parteDeLaRecusacionPK;
	}
	
	public void setParteDeLaRecusacionPK(ParteDeLaRecusacionPK parteDeLaRecusacionPK){
		this.parteDeLaRecusacionPK   = parteDeLaRecusacionPK ;
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
		

    public Recusacion getRecusacion(){
		return this.recusacion; 
	}
	
	public void setRecusacion(Recusacion recusacion){
		this.recusacion = recusacion;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PARTE_DE_LA_RECUSACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.parteDeLaRecusacionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteDeLaRecusacion que se pasa
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
        final ParteDeLaRecusacion other = (ParteDeLaRecusacion) obj;
        
        if (!Objects.equals(this.parteDeLaRecusacionPK, other.parteDeLaRecusacionPK)) {
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

