package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TIPO_ZONA_GEOGRAFICA")
@NamedQuery(name = "TipoZonaGeografica.findAll", query = "SELECT p FROM TipoZonaGeografica p")
public class TipoZonaGeografica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_PK = "idTipoZonaGeografica";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_NOMBRE = "nombre";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_OPCIONAL = "opcional";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_ESTADO_REGISTRO_TIPOZONAGEOGRAFICA = "estadoRegistroTipoZonaGeografica";			
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_TIPO_ZONA_GEOGRAFICA_ID_TIPO_ZONA_GEOGRAFICA_PADRE = "idTipoZonaGeograficaPadre";
    private static final String[] ATRIBUTOS_ENTIDAD_TIPO_ZONA_GEOGRAFICA
            = {ENTIDAD_TIPO_ZONA_GEOGRAFICA_NOMBRE, ENTIDAD_TIPO_ZONA_GEOGRAFICA_OPCIONAL, ENTIDAD_TIPO_ZONA_GEOGRAFICA_ID_USUARIO_MODIFICACION, ENTIDAD_TIPO_ZONA_GEOGRAFICA_ID_TIPO_ZONA_GEOGRAFICA_PADRE, ENTIDAD_TIPO_ZONA_GEOGRAFICA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TIPO_ZONA_GEOGRAFICA_PK, ENTIDAD_TIPO_ZONA_GEOGRAFICA_ESTADO_REGISTRO};

	@Id
    @Column(name="id_tipo_zona_geografica")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer idTipoZonaGeografica;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="opcional")
	private boolean opcional;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_tipo_zona_geografica_padre")
	private Integer idTipoZonaGeograficaPadre;		

	@ManyToOne
	@JoinColumn(name="id_tipo_zona_geografica_padre", referencedColumnName="id_tipo_zona_geografica", insertable = false, updatable = false)
    private TipoZonaGeografica tipoZonaGeografica;
		
	@OneToMany(mappedBy="tipoZonaGeografica")
    private List<TipoZonaGeografica> tipoZonaGeograficaList;
	@OneToMany(mappedBy="tipoZonaGeografica")
    private List<ZonaGeografica> zonaGeograficaList;
	
	
    public TipoZonaGeografica(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Integer getIdTipoZonaGeografica(){
		return this.idTipoZonaGeografica;
	}
	
	public void setIdTipoZonaGeografica(Integer idTipoZonaGeografica){
		this.idTipoZonaGeografica = idTipoZonaGeografica;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public boolean getOpcional(){
		return this.opcional;
	}
	
	public void setOpcional(boolean opcional){
		this.opcional = opcional;
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
		
	public Integer getIdTipoZonaGeograficaPadre(){
		return this.idTipoZonaGeograficaPadre;
	}
	
	public void setIdTipoZonaGeograficaPadre(Integer idTipoZonaGeograficaPadre){
		this.idTipoZonaGeograficaPadre = idTipoZonaGeograficaPadre;
	}
		

    public List<TipoZonaGeografica> getTipoZonaGeograficaList(){
		return this.tipoZonaGeograficaList;
	}
	
	public void setTipoZonaGeograficaList(List<TipoZonaGeografica> tipoZonaGeograficaList){
		this.tipoZonaGeograficaList = tipoZonaGeograficaList;
	}
			
    public List<ZonaGeografica> getZonaGeograficaList(){
		return this.zonaGeograficaList;
	}
	
	public void setZonaGeograficaList(List<ZonaGeografica> zonaGeograficaList){
		this.zonaGeograficaList = zonaGeograficaList;
	}
			
    public TipoZonaGeografica getTipoZonaGeografica(){
		return this.tipoZonaGeografica; 
	}
	
	public void setTipoZonaGeografica(TipoZonaGeografica tipoZonaGeografica){
		this.tipoZonaGeografica = tipoZonaGeografica;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TIPO_ZONA_GEOGRAFICA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeografica);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + (this.opcional ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idTipoZonaGeograficaPadre);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TipoZonaGeografica que se pasa
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
        final TipoZonaGeografica other = (TipoZonaGeografica) obj;
        
        if (!Objects.equals(this.idTipoZonaGeografica, other.idTipoZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.opcional, other.opcional)) {
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
        
        return Objects.equals(this.idTipoZonaGeograficaPadre, other.idTipoZonaGeograficaPadre);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

