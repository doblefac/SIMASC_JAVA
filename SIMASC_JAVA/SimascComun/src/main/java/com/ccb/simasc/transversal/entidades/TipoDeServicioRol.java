package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
@Table(name="TIPO_DE_SERVICIO_ROL")
@NamedQuery(name = "TipoDeServicioRol.findAll", query = "SELECT p FROM TipoDeServicioRol p")
public class TipoDeServicioRol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL = "tipoDeServicioRolPK.idRol";
			
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_TIPO_SERVICIO = "tipoDeServicioRolPK.tipoServicio";
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_ID_USUARIO_MODIFICAICON = "idUsuarioModificaicon";
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO_TIPODESERVICIOROL = "estadoRegistroTipoDeServicioRol";			
	public static final String ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_TIPO_DE_SERVICIO_ROL
            = {ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_TIPO_SERVICIO, ENTIDAD_TIPO_DE_SERVICIO_ROL_ID_USUARIO_MODIFICAICON, ENTIDAD_TIPO_DE_SERVICIO_ROL_PK_ID_ROL, ENTIDAD_TIPO_DE_SERVICIO_ROL_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TIPO_DE_SERVICIO_ROL_ESTADO_REGISTRO};

	@EmbeddedId
	private TipoDeServicioRolPK tipoDeServicioRolPK;
    
	@Column(name="id_usuario_modificaicon")
	private String idUsuarioModificaicon;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	
	
    public TipoDeServicioRol(){
		tipoDeServicioRolPK = new TipoDeServicioRolPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TipoDeServicioRolPK getTipoDeServicioRolPK(){
		return this.tipoDeServicioRolPK;
	}
	
	public void setTipoDeServicioRolPK(TipoDeServicioRolPK tipoDeServicioRolPK){
		this.tipoDeServicioRolPK   = tipoDeServicioRolPK ;
	}  
	
	public String getIdUsuarioModificaicon(){
		return this.idUsuarioModificaicon;
	}
	
	public void setIdUsuarioModificaicon(String idUsuarioModificaicon){
		this.idUsuarioModificaicon = idUsuarioModificaicon;
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

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TIPO_DE_SERVICIO_ROL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.tipoDeServicioRolPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificaicon);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoDeServicioRol que se pasa
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
        final TipoDeServicioRol other = (TipoDeServicioRol) obj;
        
        if (!Objects.equals(this.tipoDeServicioRolPK, other.tipoDeServicioRolPK)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificaicon, other.idUsuarioModificaicon)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

