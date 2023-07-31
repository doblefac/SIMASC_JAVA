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
@Table(name="PARTE_CONTESTACION")
@NamedQuery(name = "ParteContestacion.findAll", query = "SELECT p FROM ParteContestacion p")
public class ParteContestacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PARTE_CONTESTACION_PK_ID_CONTESTACION_DEMANDA = "parteContestacionPK.idContestacionDemanda";
			
	public static final String ENTIDAD_PARTE_CONTESTACION_PK_ID_ROL = "parteContestacionPK.idRol";
			
	public static final String ENTIDAD_PARTE_CONTESTACION_PK_ID_PERSONA = "parteContestacionPK.idPersona";
			
	public static final String ENTIDAD_PARTE_CONTESTACION_PK_ID_CASO = "parteContestacionPK.idCaso";
	public static final String ENTIDAD_PARTE_CONTESTACION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARTE_CONTESTACION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARTE_CONTESTACION_ESTADO_REGISTRO_PARTECONTESTACION = "estadoRegistroParteContestacion";			
	public static final String ENTIDAD_PARTE_CONTESTACION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARTE_CONTESTACION
            = {ENTIDAD_PARTE_CONTESTACION_PK_ID_CASO, ENTIDAD_PARTE_CONTESTACION_ESTADO_REGISTRO, ENTIDAD_PARTE_CONTESTACION_PK_ID_CONTESTACION_DEMANDA, ENTIDAD_PARTE_CONTESTACION_ID_USUARIO_MODIFICACION, ENTIDAD_PARTE_CONTESTACION_PK_ID_ROL, ENTIDAD_PARTE_CONTESTACION_PK_ID_PERSONA, ENTIDAD_PARTE_CONTESTACION_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private ParteContestacionPK parteContestacionPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_contestacion_demanda", referencedColumnName="id_contestacion_demanda", insertable = false, updatable = false)
    private ContestacionDemanda contestacionDemanda;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	
	
    public ParteContestacion(){
		parteContestacionPK = new ParteContestacionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParteContestacionPK getParteContestacionPK(){
		return this.parteContestacionPK;
	}
	
	public void setParteContestacionPK(ParteContestacionPK parteContestacionPK){
		this.parteContestacionPK   = parteContestacionPK ;
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
		

    public ContestacionDemanda getContestacionDemanda(){
		return this.contestacionDemanda; 
	}
	
	public void setContestacionDemanda(ContestacionDemanda contestacionDemanda){
		this.contestacionDemanda = contestacionDemanda;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PARTE_CONTESTACION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.parteContestacionPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParteContestacion que se pasa
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
        final ParteContestacion other = (ParteContestacion) obj;
        
        if (!Objects.equals(this.parteContestacionPK, other.parteContestacionPK)) {
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

