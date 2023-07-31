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
@Table(name="SERVICIO_MATERIA_CENTRO")
@NamedQuery(name = "ServicioMateriaCentro.findAll", query = "SELECT p FROM ServicioMateriaCentro p")
public class ServicioMateriaCentro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_CENTRO = "servicioMateriaCentroPK.idCentro";
			
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_SERVICIO = "servicioMateriaCentroPK.idServicio";
			
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_MATERIA = "servicioMateriaCentroPK.idMateria";
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_ESTADO_REGISTRO_SERVICIOMATERIACENTRO = "estadoRegistroServicioMateriaCentro";			
	public static final String ENTIDAD_SERVICIO_MATERIA_CENTRO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_SERVICIO_MATERIA_CENTRO
            = {ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_MATERIA, ENTIDAD_SERVICIO_MATERIA_CENTRO_ID_USUARIO_MODIFICACION, ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_CENTRO, ENTIDAD_SERVICIO_MATERIA_CENTRO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SERVICIO_MATERIA_CENTRO_ESTADO_REGISTRO, ENTIDAD_SERVICIO_MATERIA_CENTRO_PK_ID_SERVICIO};

	@EmbeddedId
	private ServicioMateriaCentroPK servicioMateriaCentroPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_centro", referencedColumnName="id_centro", insertable = false, updatable = false)
    private Centro centro;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)	    
	})		
    private ServicioMateria servicioMateria;
		
	
	
    public ServicioMateriaCentro(){
		servicioMateriaCentroPK = new ServicioMateriaCentroPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ServicioMateriaCentroPK getServicioMateriaCentroPK(){
		return this.servicioMateriaCentroPK;
	}
	
	public void setServicioMateriaCentroPK(ServicioMateriaCentroPK servicioMateriaCentroPK){
		this.servicioMateriaCentroPK   = servicioMateriaCentroPK ;
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
		

    public Centro getCentro(){
		return this.centro; 
	}
	
	public void setCentro(Centro centro){
		this.centro = centro;
	}
    public ServicioMateria getServicioMateria(){
		return this.servicioMateria; 
	}
	
	public void setServicioMateria(ServicioMateria servicioMateria){
		this.servicioMateria = servicioMateria;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SERVICIO_MATERIA_CENTRO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.servicioMateriaCentroPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ServicioMateriaCentro que se pasa
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
        final ServicioMateriaCentro other = (ServicioMateriaCentro) obj;
        
        if (!Objects.equals(this.servicioMateriaCentroPK, other.servicioMateriaCentroPK)) {
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

