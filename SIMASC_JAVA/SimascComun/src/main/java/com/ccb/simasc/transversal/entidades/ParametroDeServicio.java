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
@Table(name="PARAMETRO_DE_SERVICIO")
@NamedQuery(name = "ParametroDeServicio.findAll", query = "SELECT p FROM ParametroDeServicio p")
public class ParametroDeServicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_TIPO_PARAMETRO = "tipoParametro";
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE = "parametroDeServicioPK.nombre";
			
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_PK_TIPO_PARAMETRO = "parametroDeServicioPK.tipoParametro";
			
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_PK_ID_SERVICIO = "parametroDeServicioPK.idServicio";
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_VALOR = "valor";
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO_PARAMETRODESERVICIO = "estadoRegistroParametroDeServicio";			
	public static final String ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETRO_DE_SERVICIO
            = {ENTIDAD_PARAMETRO_DE_SERVICIO_DESCRIPCION, ENTIDAD_PARAMETRO_DE_SERVICIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETRO_DE_SERVICIO_ESTADO_REGISTRO, ENTIDAD_PARAMETRO_DE_SERVICIO_PK_NOMBRE, ENTIDAD_PARAMETRO_DE_SERVICIO_PK_TIPO_PARAMETRO, ENTIDAD_PARAMETRO_DE_SERVICIO_PK_ID_SERVICIO, ENTIDAD_PARAMETRO_DE_SERVICIO_VALOR, ENTIDAD_PARAMETRO_DE_SERVICIO_ID_USUARIO_MODIFICACION};

	@EmbeddedId
	private ParametroDeServicioPK parametroDeServicioPK;
    
	@Column(name="valor")
	private String valor;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	
	
    public ParametroDeServicio(){
		parametroDeServicioPK = new ParametroDeServicioPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public ParametroDeServicioPK getParametroDeServicioPK(){
		return this.parametroDeServicioPK;
	}
	
	public void setParametroDeServicioPK(ParametroDeServicioPK parametroDeServicioPK){
		this.parametroDeServicioPK   = parametroDeServicioPK ;
	}  
	
	public String getValor(){
		return this.valor;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
		
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETRO_DE_SERVICIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.parametroDeServicioPK);        
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroDeServicio que se pasa
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
        final ParametroDeServicio other = (ParametroDeServicio) obj;
        
        if (!Objects.equals(this.parametroDeServicioPK, other.parametroDeServicioPK)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
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

