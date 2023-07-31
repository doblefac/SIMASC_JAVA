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
@Table(name="CONTESTACION_DEMANDA")
@NamedQuery(name = "ContestacionDemanda.findAll", query = "SELECT p FROM ContestacionDemanda p")
public class ContestacionDemanda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@ManyToOne
	@JoinColumn(name="id_documento_contestacion", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoContestacion;
		
	@ManyToOne
	@JoinColumn(name="id_documento_dem_reconvencion", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoDemReconvencion;
		
	@ManyToOne
	@JoinColumn(name="id_documento_excepciones", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documentoExcepciones;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CONTESTACION_DEMANDA_PK = "idContestacionDemanda";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_CONTESTACION = "idDocumentoContestacion";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_EXCEPCIONES = "idDocumentoExcepciones";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_DEM_RECONVENCION = "idDocumentoDemReconvencion";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_CASO = "idCaso";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO_CONTESTACIONDEMANDA = "estadoRegistroContestacionDemanda";			
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CONTESTACION_DEMANDA_ID_CONTESTACION_DEM_RECONVENCION = "idContestacionDemReconvencion";
    private static final String[] ATRIBUTOS_ENTIDAD_CONTESTACION_DEMANDA
            = {ENTIDAD_CONTESTACION_DEMANDA_ID_USUARIO_MODIFICACION, ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_CONTESTACION, ENTIDAD_CONTESTACION_DEMANDA_ID_CASO, ENTIDAD_CONTESTACION_DEMANDA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CONTESTACION_DEMANDA_ID_CONTESTACION_DEM_RECONVENCION, ENTIDAD_CONTESTACION_DEMANDA_ESTADO_REGISTRO, ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_EXCEPCIONES, ENTIDAD_CONTESTACION_DEMANDA_ID_DOCUMENTO_DEM_RECONVENCION, ENTIDAD_CONTESTACION_DEMANDA_PK};

	@Id
    @Column(name="id_contestacion_demanda")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idContestacionDemanda;
    
	@Column(name="id_documento_contestacion")
	private Long idDocumentoContestacion;		
    
	@Column(name="id_documento_excepciones")
	private Long idDocumentoExcepciones;		
    
	@Column(name="id_documento_dem_reconvencion")
	private Long idDocumentoDemReconvencion;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_contestacion_dem_reconvencion")
	private Long idContestacionDemReconvencion;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_contestacion_dem_reconvencion", referencedColumnName="id_contestacion_demanda", insertable = false, updatable = false)
    private ContestacionDemanda contestacionDemanda;
		
	@OneToMany(mappedBy="contestacionDemanda")
    private List<ContestacionDemanda> contestacionDemandaList;
	@OneToMany(mappedBy="contestacionDemanda")
    private List<ParteContestacion> parteContestacionList;
	
	
    public ContestacionDemanda(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdContestacionDemanda(){
		return this.idContestacionDemanda;
	}
	
	public void setIdContestacionDemanda(Long idContestacionDemanda){
		this.idContestacionDemanda = idContestacionDemanda;
	}
	
	public Long getIdDocumentoContestacion(){
		return this.idDocumentoContestacion;
	}
	
	public void setIdDocumentoContestacion(Long idDocumentoContestacion){
		this.idDocumentoContestacion = idDocumentoContestacion;
	}
		
	public Long getIdDocumentoExcepciones(){
		return this.idDocumentoExcepciones;
	}
	
	public void setIdDocumentoExcepciones(Long idDocumentoExcepciones){
		this.idDocumentoExcepciones = idDocumentoExcepciones;
	}
		
	public Long getIdDocumentoDemReconvencion(){
		return this.idDocumentoDemReconvencion;
	}
	
	public void setIdDocumentoDemReconvencion(Long idDocumentoDemReconvencion){
		this.idDocumentoDemReconvencion = idDocumentoDemReconvencion;
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
		
	public Long getIdContestacionDemReconvencion(){
		return this.idContestacionDemReconvencion;
	}
	
	public void setIdContestacionDemReconvencion(Long idContestacionDemReconvencion){
		this.idContestacionDemReconvencion = idContestacionDemReconvencion;
	}
		

    public List<ContestacionDemanda> getContestacionDemandaList(){
		return this.contestacionDemandaList;
	}
	
	public void setContestacionDemandaList(List<ContestacionDemanda> contestacionDemandaList){
		this.contestacionDemandaList = contestacionDemandaList;
	}
			
    public List<ParteContestacion> getParteContestacionList(){
		return this.parteContestacionList;
	}
	
	public void setParteContestacionList(List<ParteContestacion> parteContestacionList){
		this.parteContestacionList = parteContestacionList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public ContestacionDemanda getContestacionDemanda(){
		return this.contestacionDemanda; 
	}
	
	public void setContestacionDemanda(ContestacionDemanda contestacionDemanda){
		this.contestacionDemanda = contestacionDemanda;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CONTESTACION_DEMANDA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idContestacionDemanda);        
        hash = 37 * hash + Objects.hashCode(this.idDocumentoContestacion);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoExcepciones);
        hash = 37 * hash + Objects.hashCode(this.idDocumentoDemReconvencion);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idContestacionDemReconvencion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ContestacionDemanda que se pasa
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
        final ContestacionDemanda other = (ContestacionDemanda) obj;
        
        if (!Objects.equals(this.idContestacionDemanda, other.idContestacionDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoContestacion, other.idDocumentoContestacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoExcepciones, other.idDocumentoExcepciones)) {
            return false;
        }
        
        if (!Objects.equals(this.idDocumentoDemReconvencion, other.idDocumentoDemReconvencion)) {
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
        
        return Objects.equals(this.idContestacionDemReconvencion, other.idContestacionDemReconvencion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

    

	public Documento getDocumentoContestacion() {
		return documentoContestacion;
	}


	public void setDocumentoContestacion(Documento documentoContestacion) {
		this.documentoContestacion = documentoContestacion;
	}


	public Documento getDocumentoDemReconvencion() {
		return documentoDemReconvencion;
	}


	public void setDocumentoDemReconvencion(Documento documentoDemReconvencion) {
		this.documentoDemReconvencion = documentoDemReconvencion;
	}


	public Documento getDocumentoExcepciones() {
		return documentoExcepciones;
	}


	public void setDocumentoExcepciones(Documento documentoExcepciones) {
		this.documentoExcepciones = documentoExcepciones;
	}
	
	// protected region metodos adicionales end

} 

