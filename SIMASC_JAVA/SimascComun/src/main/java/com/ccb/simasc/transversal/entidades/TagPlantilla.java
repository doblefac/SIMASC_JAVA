package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.Temporal;
import java.util.Date;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TAG_PLANTILLA")
@NamedQuery(name = "TagPlantilla.findAll", query = "SELECT p FROM TagPlantilla p")
public class TagPlantilla implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TAG_PLANTILLA_PK = "idTagPlantilla";
	public static final String ENTIDAD_TAG_PLANTILLA_CODIGO = "codigo";
	public static final String ENTIDAD_TAG_PLANTILLA_NOMBRE = "nombre";
	public static final String ENTIDAD_TAG_PLANTILLA_CONSULTA = "consulta";
	public static final String ENTIDAD_TAG_PLANTILLA_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_TAG_PLANTILLA_DESCRIPCION = "descripcion";
	public static final String ENTIDAD_TAG_PLANTILLA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_TAG_PLANTILLA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_TAG_PLANTILLA_ESTADO_REGISTRO_TAGPLANTILLA = "estadoRegistroTagPlantilla";			
	public static final String ENTIDAD_TAG_PLANTILLA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_TAG_PLANTILLA_TIPO_CONSULTA = "tipoConsulta";

	private static final String[] ATRIBUTOS_ENTIDAD_TAG_PLANTILLA
            = {ENTIDAD_TAG_PLANTILLA_DESCRIPCION, ENTIDAD_TAG_PLANTILLA_CODIGO, ENTIDAD_TAG_PLANTILLA_NOMBRE, ENTIDAD_TAG_PLANTILLA_TIPO_SERVICIO, ENTIDAD_TAG_PLANTILLA_PK, ENTIDAD_TAG_PLANTILLA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_TAG_PLANTILLA_CONSULTA, ENTIDAD_TAG_PLANTILLA_ID_USUARIO_MODIFICACION, ENTIDAD_TAG_PLANTILLA_ESTADO_REGISTRO, ENTIDAD_TAG_PLANTILLA_TIPO_CONSULTA};

	@Id
    @Column(name="id_tag_plantilla")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idTagPlantilla;
    
	@Column(name="codigo")
	private String codigo;		
    
	@Column(name="consulta")
	private String consulta;	
	
	@Column(name="nombre")
	private String nombre;	
    
	@Column(name="tipo_servicio")
	private String tipoServicio;		
    
	@Column(name="descripcion")
	private String descripcion;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@OneToMany(mappedBy="tagPlantilla")
    private List<ValorPlantillaCarta> valorPlantillaCartaList;
	
	@Column(name="tipo_consulta")
	private String tipoConsulta;	
	
	
    public TagPlantilla(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdTagPlantilla(){
		return this.idTagPlantilla;
	}
	
	public void setIdTagPlantilla(Long idTagPlantilla){
		this.idTagPlantilla = idTagPlantilla;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public String getConsulta(){
		return this.consulta;
	}
	
	public void setConsulta(String consulta){
		this.consulta = consulta;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
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
		

    public List<ValorPlantillaCarta> getValorPlantillaCartaList(){
		return this.valorPlantillaCartaList;
	}
	
	public void setValorPlantillaCartaList(List<ValorPlantillaCarta> valorPlantillaCartaList){
		this.valorPlantillaCartaList = valorPlantillaCartaList;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
			

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TAG_PLANTILLA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idTagPlantilla);        
        hash = 37 * hash + Objects.hashCode(this.codigo);
        hash = 37 * hash + Objects.hashCode(this.nombre);        
        hash = 37 * hash + Objects.hashCode(this.consulta);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.tipoConsulta);       
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TagPlantilla que se pasa
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
        final TagPlantilla other = (TagPlantilla) obj;
        
        if (!Objects.equals(this.idTagPlantilla, other.idTagPlantilla)) {
            return false;
        }
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
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
        
        if (!Objects.equals(this.tipoConsulta, other.tipoConsulta)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }





	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

