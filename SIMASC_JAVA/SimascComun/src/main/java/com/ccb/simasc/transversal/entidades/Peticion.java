package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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
@Table(name="PETICION")
@NamedQuery(name = "Peticion.findAll", query = "SELECT p FROM Peticion p")
public class Peticion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PETICION_PK = "idPeticion";
	public static final String ENTIDAD_PETICION_TIPO = "tipo";
	public static final String ENTIDAD_PETICION_TEXTO = "texto";
	public static final String ENTIDAD_PETICION_FECHA_RESPUESTA = "fechaRespuesta";
	public static final String ENTIDAD_PETICION_RESPUESTA = "respuesta";
	public static final String ENTIDAD_PETICION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PETICION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PETICION_ESTADO_REGISTRO_PETICION = "estadoRegistroPeticion";			
	public static final String ENTIDAD_PETICION_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PETICION_ID_CASO = "idCaso";
	public static final String ENTIDAD_PETICION_FECHA_RADICACION = "fecha_radicacion";
    private static final String[] ATRIBUTOS_ENTIDAD_PETICION
            = {ENTIDAD_PETICION_PK, ENTIDAD_PETICION_FECHA_RESPUESTA, ENTIDAD_PETICION_RESPUESTA, ENTIDAD_PETICION_TEXTO, ENTIDAD_PETICION_ESTADO_REGISTRO, ENTIDAD_PETICION_ID_CASO, ENTIDAD_PETICION_ID_USUARIO_MODIFICACION, ENTIDAD_PETICION_TIPO, ENTIDAD_PETICION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PETICION_FECHA_RADICACION};

	@Id
    @Column(name="id_peticion")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPeticion;
    
	@Column(name="tipo")
	private String tipo;		
    
	@Column(name="texto")
	private String texto;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_respuesta")
	private Date fechaRespuesta;	
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_radicacion")
	private Date fechaRadicacion;		
    
	@Column(name="respuesta")
	private String respuesta;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_caso")
	private Long idCaso;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@OneToMany(mappedBy="peticion")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="peticion", cascade={CascadeType.REMOVE})
    private List<PartePeticion> partePeticionList;
	
	
    public Peticion(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPeticion(){
		return this.idPeticion;
	}
	
	public void setIdPeticion(Long idPeticion){
		this.idPeticion = idPeticion;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public String getTexto(){
		return this.texto;
	}
	
	public void setTexto(String texto){
		this.texto = texto;
	}
		
	public Date getFechaRespuesta(){
		return this.fechaRespuesta;
	}
	
	public void setFechaRespuesta(Date fechaRespuesta){
		this.fechaRespuesta = fechaRespuesta;
	}
		
	public String getRespuesta(){
		return this.respuesta;
	}
	
	public void setRespuesta(String respuesta){
		this.respuesta = respuesta;
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
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		

    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<PartePeticion> getPartePeticionList(){
		return this.partePeticionList;
	}
	
	public void setPartePeticionList(List<PartePeticion> partePeticionList){
		this.partePeticionList = partePeticionList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}	

	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}

	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PETICION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPeticion);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.texto);
        hash = 37 * hash + Objects.hashCode(this.fechaRespuesta);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);   
        hash = 37 * hash + Objects.hashCode(this.respuesta);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);        
                
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Peticion que se pasa
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
        final Peticion other = (Peticion) obj;
        
        if (!Objects.equals(this.idPeticion, other.idPeticion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRespuesta, other.fechaRespuesta)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.respuesta, other.respuesta)) {
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
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }



	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

