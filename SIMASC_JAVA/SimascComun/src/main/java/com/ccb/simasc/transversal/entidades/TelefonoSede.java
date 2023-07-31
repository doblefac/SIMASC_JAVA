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
@Table(name="TELEFONO_SEDE")
@NamedQuery(name = "TelefonoSede.findAll", query = "SELECT p FROM TelefonoSede p")
public class TelefonoSede implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_TELEFONO_SEDE_PK_NUMERO = "telefonoSedePK.numero";
			
	public static final String ENTIDAD_TELEFONO_SEDE_PK_TIPO = "telefonoSedePK.tipo";
			
	public static final String ENTIDAD_TELEFONO_SEDE_PK_EXTENSION = "telefonoSedePK.extension";
	public static final String ENTIDAD_TELEFONO_SEDE_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TELEFONO_SEDE_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TELEFONO_SEDE_ESTADO_REGISTRO_TELEFONOSEDE = "estadoRegistroTelefonoSede";			
	public static final String ENTIDAD_TELEFONO_SEDE_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_TELEFONO_SEDE_ID_SEDE = "idSede";
    private static final String[] ATRIBUTOS_ENTIDAD_TELEFONO_SEDE
            = {ENTIDAD_TELEFONO_SEDE_PK_EXTENSION, ENTIDAD_TELEFONO_SEDE_PK_TIPO, ENTIDAD_TELEFONO_SEDE_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TELEFONO_SEDE_ESTADO_REGISTRO, ENTIDAD_TELEFONO_SEDE_ID_USUARIO_MODIFICACION, ENTIDAD_TELEFONO_SEDE_ID_SEDE, ENTIDAD_TELEFONO_SEDE_PK_NUMERO};

	@EmbeddedId
	private TelefonoSedePK telefonoSedePK;
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_sede")
	private Long idSede;		

	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	
	
    public TelefonoSede(){
		telefonoSedePK = new TelefonoSedePK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public TelefonoSedePK getTelefonoSedePK(){
		return this.telefonoSedePK;
	}
	
	public void setTelefonoSedePK(TelefonoSedePK telefonoSedePK){
		this.telefonoSedePK   = telefonoSedePK ;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		

    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TELEFONO_SEDE) {
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
        
        hash = 37 * hash + Objects.hashCode(this.telefonoSedePK);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TelefonoSede que se pasa
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
        final TelefonoSede other = (TelefonoSede) obj;
        
        if (!Objects.equals(this.telefonoSedePK, other.telefonoSedePK)) {
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
        
        return Objects.equals(this.idSede, other.idSede);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

