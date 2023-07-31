package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="CLASIFICADOR_DOMINIO")
@NamedQuery(name = "ClasificadorDominio.findAll", query = "SELECT p FROM ClasificadorDominio p")
public class ClasificadorDominio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
	    @JoinColumn(name = "codigo_clasificado", referencedColumnName="codigo", insertable = false, updatable = false),
	    @JoinColumn(name = "dominio_clasificado", referencedColumnName="dominio", insertable = false, updatable = false)	    
	})		
    private Dominio dominioClasificado;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
	    @JoinColumn(name = "codigo_clasificador", referencedColumnName="codigo", insertable = false, updatable = false),
	    @JoinColumn(name = "dominio_clasificador", referencedColumnName="dominio", insertable = false, updatable = false)	    
	})		
    private Dominio dominioClasificador;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_PK_CODIGO_CLASIFICADO = "clasificadorDominioPK.codigoClasificado";
			
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_PK_DOMINIO_CLASIFICADO = "clasificadorDominioPK.dominioClasificado";
			
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_PK_CODIGO_CLASIFICADOR = "clasificadorDominioPK.codigoClasificador";
			
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_PK_DOMINIO_CLASIFICADOR = "clasificadorDominioPK.dominioClasificador";
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_ESTADO_REGISTRO_CLASIFICADORDOMINIO = "estadoRegistroClasificadorDominio";			
	public static final String ENTIDAD_CLASIFICADOR_DOMINIO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_CLASIFICADOR_DOMINIO
            = {ENTIDAD_CLASIFICADOR_DOMINIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CLASIFICADOR_DOMINIO_PK_CODIGO_CLASIFICADOR, ENTIDAD_CLASIFICADOR_DOMINIO_ID_USUARIO_MODIFICACION, ENTIDAD_CLASIFICADOR_DOMINIO_ESTADO_REGISTRO, ENTIDAD_CLASIFICADOR_DOMINIO_PK_DOMINIO_CLASIFICADO, ENTIDAD_CLASIFICADOR_DOMINIO_PK_CODIGO_CLASIFICADO, ENTIDAD_CLASIFICADOR_DOMINIO_PK_DOMINIO_CLASIFICADOR};

	@EmbeddedId
	private ClasificadorDominioPK clasificadorDominioPK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
	
	
    public ClasificadorDominio(){
		clasificadorDominioPK = new ClasificadorDominioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ClasificadorDominioPK getClasificadorDominioPK(){
		return this.clasificadorDominioPK;
	}
	
	public void setClasificadorDominioPK(ClasificadorDominioPK clasificadorDominioPK){
		this.clasificadorDominioPK   = clasificadorDominioPK ;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_CLASIFICADOR_DOMINIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.clasificadorDominioPK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ClasificadorDominio que se pasa
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
        final ClasificadorDominio other = (ClasificadorDominio) obj;
        
        if (!Objects.equals(this.clasificadorDominioPK, other.clasificadorDominioPK)) {
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


	public Dominio getDominioClasificado() {
		return dominioClasificado;
	}


	public void setDominioClasificado(Dominio dominioClasificado) {
		this.dominioClasificado = dominioClasificado;
	}


	public Dominio getDominioClasificador() {
		return dominioClasificador;
	}


	public void setDominioClasificador(Dominio dominioClasificador) {
		this.dominioClasificador = dominioClasificador;
	}
	// protected region metodos adicionales end

} 

