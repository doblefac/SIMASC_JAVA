package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="GASTO_TRIBUNAL")
@NamedQuery(name = "GastoTribunal.findAll", query = "SELECT p FROM GastoTribunal p")
public class GastoTribunal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_GASTO_TRIBUNAL_PK = "idGastoTribunal";
	public static final String ENTIDAD_GASTO_TRIBUNAL_VALOR = "valor";
	public static final String ENTIDAD_GASTO_TRIBUNAL_FECHA = "fecha";
	public static final String ENTIDAD_GASTO_TRIBUNAL_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_GASTO_TRIBUNAL_ID_CASO = "idCaso";
	public static final String ENTIDAD_GASTO_TRIBUNAL_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_GASTO_TRIBUNAL_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_GASTO_TRIBUNAL_ESTADO_REGISTRO_GASTOTRIBUNAL = "estadoRegistroGastoTribunal";			
	public static final String ENTIDAD_GASTO_TRIBUNAL_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_GASTO_TRIBUNAL_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_GASTO_TRIBUNAL
            = {ENTIDAD_GASTO_TRIBUNAL_DESCRIPCION, ENTIDAD_GASTO_TRIBUNAL_FECHA, ENTIDAD_GASTO_TRIBUNAL_ID_USUARIO_MODIFICACION, ENTIDAD_GASTO_TRIBUNAL_PK, ENTIDAD_GASTO_TRIBUNAL_ESTADO_REGISTRO, ENTIDAD_GASTO_TRIBUNAL_ID_CASO, ENTIDAD_GASTO_TRIBUNAL_ID_DOCUMENTO, ENTIDAD_GASTO_TRIBUNAL_VALOR, ENTIDAD_GASTO_TRIBUNAL_FECHA_ULTIMA_MODIFICACION};

	@Id
    @Column(name="id_gasto_tribunal")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idGastoTribunal;
    
	@Column(name="valor")
	private Long valor;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	
	
    public GastoTribunal(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdGastoTribunal(){
		return this.idGastoTribunal;
	}
	
	public void setIdGastoTribunal(Long idGastoTribunal){
		this.idGastoTribunal = idGastoTribunal;
	}
	
	public Long getValor(){
		return this.valor;
	}
	
	public void setValor(Long valor){
		this.valor = valor;
	}
		
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
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
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_GASTO_TRIBUNAL) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idGastoTribunal);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad GastoTribunal que se pasa
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
        final GastoTribunal other = (GastoTribunal) obj;
        
        if (!Objects.equals(this.idGastoTribunal, other.idGastoTribunal)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
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
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

