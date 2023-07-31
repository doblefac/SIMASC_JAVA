package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

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
@Table(name="CARPETA")
@NamedQuery(name = "Carpeta.findAll", query = "SELECT p FROM Carpeta p")
public class Carpeta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CARPETA_PK = "idCarpeta";
	public static final String ENTIDAD_CARPETA_ES_CUADERNO = "esCuaderno";
	public static final String ENTIDAD_CARPETA_PATH = "path";
	public static final String ENTIDAD_CARPETA_NOMBRE = "nombre";
	public static final String ENTIDAD_CARPETA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CARPETA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CARPETA_ESTADO_REGISTRO_CARPETA = "estadoRegistroCarpeta";			
	public static final String ENTIDAD_CARPETA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CARPETA_ID_CARPETA_CONTENEDORA = "idCarpetaContenedora";
	public static final String ENTIDAD_CARPETA_ID_CASO = "idCaso";
    private static final String[] ATRIBUTOS_ENTIDAD_CARPETA
            = {ENTIDAD_CARPETA_PATH, ENTIDAD_CARPETA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CARPETA_ESTADO_REGISTRO, ENTIDAD_CARPETA_ID_CARPETA_CONTENEDORA, ENTIDAD_CARPETA_ID_USUARIO_MODIFICACION, ENTIDAD_CARPETA_PK, ENTIDAD_CARPETA_NOMBRE, ENTIDAD_CARPETA_ES_CUADERNO, ENTIDAD_CARPETA_ID_CASO};

	@Id
    @Column(name="id_carpeta")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCarpeta;
    
	@Column(name="es_cuaderno")
	private boolean esCuaderno;		
    
	@Column(name="path")
	private String path;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_carpeta_contenedora")
	private Long idCarpetaContenedora;		
    
	@Column(name="id_caso")
	private Long idCaso;	
	
	@Column(name="id_cuaderno")
	private Long idCuaderno;		

	@ManyToOne
	@JoinColumn(name="id_carpeta_contenedora", referencedColumnName="id_carpeta", insertable = false, updatable = false)
    private Carpeta carpeta;
	
	@ManyToOne
	@JoinColumn(name="id_cuaderno", referencedColumnName="id_cuaderno", insertable = false, updatable = false)
    private Cuaderno cuaderno;
		
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@OneToMany(mappedBy="carpeta")
    private List<Carpeta> carpetaList;
	@OneToMany(mappedBy="carpeta")
    private List<Documento> documentoList;
	
	
    public Carpeta(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCarpeta(){
		return this.idCarpeta;
	}
	
	public void setIdCarpeta(Long idCarpeta){
		this.idCarpeta = idCarpeta;
	}
	
	public boolean getEsCuaderno(){
		return this.esCuaderno;
	}
	
	public void setEsCuaderno(boolean esCuaderno){
		this.esCuaderno = esCuaderno;
	}
		
	public String getPath(){
		return this.path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	public Long getIdCarpetaContenedora(){
		return this.idCarpetaContenedora;
	}
	
	public void setIdCarpetaContenedora(Long idCarpetaContenedora){
		this.idCarpetaContenedora = idCarpetaContenedora;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		

    public List<Carpeta> getCarpetaList(){
		return this.carpetaList;
	}
	
	public void setCarpetaList(List<Carpeta> carpetaList){
		this.carpetaList = carpetaList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public Carpeta getCarpeta(){
		return this.carpeta; 
	}
	
	public void setCarpeta(Carpeta carpeta){
		this.carpeta = carpeta;
	}
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
	
	public Long getIdCuaderno() {
		return idCuaderno;
	}


	public void setIdCuaderno(Long idCuaderno) {
		this.idCuaderno = idCuaderno;
	}


	public Cuaderno getCuaderno() {
		return cuaderno;
	}


	public void setCuaderno(Cuaderno cuaderno) {
		this.cuaderno = cuaderno;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CARPETA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCarpeta);        
        hash = 37 * hash + (this.esCuaderno ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.path);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCarpetaContenedora);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Carpeta que se pasa
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
        final Carpeta other = (Carpeta) obj;
        
        if (!Objects.equals(this.idCarpeta, other.idCarpeta)) {
            return false;
        }
        
        if (!Objects.equals(this.esCuaderno, other.esCuaderno)) {
            return false;
        }
        
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        
        if (!Objects.equals(this.idCarpetaContenedora, other.idCarpetaContenedora)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

