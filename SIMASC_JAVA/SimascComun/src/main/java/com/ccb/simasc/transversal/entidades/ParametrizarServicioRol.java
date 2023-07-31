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
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PARAMETRIZAR_SERVICIO_ROL")
@NamedQuery(name = "ParametrizarServicioRol.findAll", query = "SELECT p FROM ParametrizarServicioRol p")
public class ParametrizarServicioRol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_PK_ID_SERVICIO = "parametrizarServicioRolPK.idServicio";
			
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_PK_ID_ROL = "parametrizarServicioRolPK.idRol";
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_ESTADO_REGISTRO_PARAMETRIZARSERVICIOROL = "estadoRegistroParametrizarServicioRol";			
	public static final String ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETRIZAR_SERVICIO_ROL
            = {ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_PK_ID_SERVICIO, ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_PK_ID_ROL, ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_ID_USUARIO_MODIFICACION, ENTIDAD_PARAMETRIZAR_SERVICIO_ROL_ESTADO_REGISTRO};

	@EmbeddedId
	private ParametrizarServicioRolPK parametrizarServicioRolPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	
	
    public ParametrizarServicioRol(){
		parametrizarServicioRolPK = new ParametrizarServicioRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParametrizarServicioRolPK getParametrizarServicioRolPK(){
		return this.parametrizarServicioRolPK;
	}
	
	public void setParametrizarServicioRolPK(ParametrizarServicioRolPK parametrizarServicioRolPK){
		this.parametrizarServicioRolPK   = parametrizarServicioRolPK ;
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
		

    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}
    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETRIZAR_SERVICIO_ROL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.parametrizarServicioRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametrizarServicioRol que se pasa
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
        final ParametrizarServicioRol other = (ParametrizarServicioRol) obj;
        
        if (!Objects.equals(this.parametrizarServicioRolPK, other.parametrizarServicioRolPK)) {
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

