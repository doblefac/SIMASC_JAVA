package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="HOMOLOGACION_SISTEMA_EXTERNO")
@NamedQuery(name = "HomologacionSistemaExterno.findAll", query = "SELECT p FROM HomologacionSistemaExterno p")
public class HomologacionSistemaExterno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_CODIGO_SIMASC = "homologacionSistemaExternoPK.codigoSimasc";
			
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_DOMINIO_SIMASC = "homologacionSistemaExternoPK.dominioSimasc";
			
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_CODIGO_SISTEMA_EXTERNO = "homologacionSistemaExternoPK.codigoSistemaExterno";
			
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_SISTEMA_EXTERNO = "homologacionSistemaExternoPK.sistemaExterno";
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_ESTADO_REGISTRO_HOMOLOGACIONSISTEMAEXTERNO = "estadoRegistroHomologacionSistemaExterno";			
	public static final String ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO
            = {ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_DOMINIO_SIMASC, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_CODIGO_SIMASC, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_ID_USUARIO_MODIFICACION, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_ESTADO_REGISTRO, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_CODIGO_SISTEMA_EXTERNO, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_PK_SISTEMA_EXTERNO, ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO_FECHA_ULTIMA_MODIFICACION};

	@EmbeddedId
	private HomologacionSistemaExternoPK homologacionSistemaExternoPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	
	
    public HomologacionSistemaExterno(){
		homologacionSistemaExternoPK = new HomologacionSistemaExternoPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public HomologacionSistemaExternoPK getHomologacionSistemaExternoPK(){
		return this.homologacionSistemaExternoPK;
	}
	
	public void setHomologacionSistemaExternoPK(HomologacionSistemaExternoPK homologacionSistemaExternoPK){
		this.homologacionSistemaExternoPK   = homologacionSistemaExternoPK ;
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
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_HOMOLOGACION_SISTEMA_EXTERNO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.homologacionSistemaExternoPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad HomologacionSistemaExterno que se pasa
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
        final HomologacionSistemaExterno other = (HomologacionSistemaExterno) obj;
        
        if (!Objects.equals(this.homologacionSistemaExternoPK, other.homologacionSistemaExternoPK)) {
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


	@Override
	public String toString() {
		return "HomologacionSistemaExterno [homologacionSistemaExternoPK=" + homologacionSistemaExternoPK
				+ ", idUsuarioModificacion=" + idUsuarioModificacion + ", fechaUltimaModificacion="
				+ fechaUltimaModificacion + ", estadoRegistro=" + estadoRegistro + "]";
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end
    
} 

