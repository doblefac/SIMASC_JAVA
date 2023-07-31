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
@Table(name="CLAVE")
@NamedQuery(name = "Clave.findAll", query = "SELECT p FROM Clave p")
public class Clave implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
    public Clave(String usuarioLogin, String clave, boolean claveBloqueada, Date fechaVencimiento, String estadoRegistro){
    	this.clavePK = new ClavePK(usuarioLogin, clave);
    	this.claveBloqueada = claveBloqueada;
    	this.fechaVencimiento = fechaVencimiento;
    	this.estadoRegistro = estadoRegistro;
    	
    }
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_CLAVE_PK_USUARIO_LOGIN = "clavePK.usuarioLogin";
			
	public static final String ENTIDAD_CLAVE_PK_CLAVE = "clavePK.clave";
	public static final String ENTIDAD_CLAVE_CLAVE_BLOQUEADA = "claveBloqueada";
	public static final String ENTIDAD_CLAVE_FECHA_VENCIMIENTO = "fechaVencimiento";
	public static final String ENTIDAD_CLAVE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CLAVE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CLAVE_ESTADO_REGISTRO_CLAVE = "estadoRegistroClave";			
	public static final String ENTIDAD_CLAVE_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_CLAVE
            = {ENTIDAD_CLAVE_ID_USUARIO_MODIFICACION, ENTIDAD_CLAVE_PK_CLAVE, ENTIDAD_CLAVE_ESTADO_REGISTRO, ENTIDAD_CLAVE_PK_USUARIO_LOGIN, ENTIDAD_CLAVE_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CLAVE_CLAVE_BLOQUEADA, ENTIDAD_CLAVE_FECHA_VENCIMIENTO};

	@EmbeddedId
	private ClavePK clavePK;
    
	@Column(name="clave_bloqueada")
	private boolean claveBloqueada;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="usuario_login", referencedColumnName="usuario_login", insertable = false, updatable = false)
    private Usuario usuario;
		
	
	
    public Clave(){
		clavePK = new ClavePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }

	public ClavePK getClavePK(){
		return this.clavePK;
	}
	
	public void setClavePK(ClavePK clavePK){
		this.clavePK   = clavePK ;
	}  
	
	public boolean getClaveBloqueada(){
		return this.claveBloqueada;
	}
	
	public void setClaveBloqueada(boolean claveBloqueada){
		this.claveBloqueada = claveBloqueada;
	}
		
	public Date getFechaVencimiento(){
		return this.fechaVencimiento;
	}
	
	public void setFechaVencimiento(Date fechaVencimiento){
		this.fechaVencimiento = fechaVencimiento;
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
		

    public Usuario getUsuario(){
		return this.usuario; 
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CLAVE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.clavePK);        
        hash = 37 * hash + (this.claveBloqueada ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Clave que se pasa
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
        final Clave other = (Clave) obj;
        
        if (!Objects.equals(this.clavePK, other.clavePK)) {
            return false;
        }
        
        if (!Objects.equals(this.claveBloqueada, other.claveBloqueada)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaVencimiento, other.fechaVencimiento)) {
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

