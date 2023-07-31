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

import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="SORTEO")
@NamedQuery(name = "Sorteo.findAll", query = "SELECT p FROM Sorteo p")
public class Sorteo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SORTEO_PK = "idSorteo";
	public static final String ENTIDAD_SORTEO_FECHA_REALIZACION = "fechaRealizacion";
	public static final String ENTIDAD_SORTEO_ESTADO = "estado";
	public static final String ENTIDAD_SORTEO_MATERIA_CASO = "materiaCaso";
	public static final String ENTIDAD_SORTEO_SERVICIO_CASO = "servicioCaso";
	public static final String ENTIDAD_SORTEO_TIPO_CUANTIA = "tipoCuantia";
	public static final String ENTIDAD_SORTEO_LIBERO_LISTA = "liberoLista";
	public static final String ENTIDAD_SORTEO_HORA_LIBERACION = "horaLiberacion";
	public static final String ENTIDAD_SORTEO_ALEATORIOS_SELECCIONADOS = "aleatoriosSeleccionados";
	public static final String ENTIDAD_SORTEO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SORTEO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SORTEO_ESTADO_REGISTRO_SORTEO = "estadoRegistroSorteo";			
	public static final String ENTIDAD_SORTEO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SORTEO_ID_CASO = "idCaso";
	public static final String ENTIDAD_SORTEO_TIPO_PRESELECCION = "tipoPreseleccion";
	public static final String ENTIDAD_SORTEO_QUIEN_PRESELECCIONA ="quienPreselecciona";
    private static final String[] ATRIBUTOS_ENTIDAD_SORTEO
            = {ENTIDAD_SORTEO_ESTADO, ENTIDAD_SORTEO_ESTADO_REGISTRO, ENTIDAD_SORTEO_HORA_LIBERACION, ENTIDAD_SORTEO_PK, ENTIDAD_SORTEO_SERVICIO_CASO, ENTIDAD_SORTEO_ID_CASO, ENTIDAD_SORTEO_TIPO_CUANTIA, ENTIDAD_SORTEO_MATERIA_CASO, ENTIDAD_SORTEO_FECHA_REALIZACION, ENTIDAD_SORTEO_LIBERO_LISTA, ENTIDAD_SORTEO_ALEATORIOS_SELECCIONADOS, ENTIDAD_SORTEO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SORTEO_ID_USUARIO_MODIFICACION,ENTIDAD_SORTEO_TIPO_PRESELECCION,ENTIDAD_SORTEO_QUIEN_PRESELECCIONA};

	@Id
    @Column(name="id_sorteo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idSorteo;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_realizacion")
	private Date fechaRealizacion;		
    
	@Column(name="estado")
	private String estado;		
    
	@Column(name="materia_caso")
	private Long materiaCaso;		
    
	@Column(name="servicio_caso")
	private Long servicioCaso;		
    
	@Column(name="tipo_cuantia")
	private String tipoCuantia;		
    
	@Column(name="libero_lista")
	private boolean liberoLista;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_liberacion")
	private Date horaLiberacion;		
    
	@Column(name="aleatorios_seleccionados")
	private String aleatoriosSeleccionados;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_caso")
	private Long idCaso;		
	
	@Column(name="preseleccion")
	private boolean preseleccion;	

	@Column(name="tipo_preseleccion")
	private String tipoPreseleccion;	
	
	@Column(name="quien_preselecciona")
	private String quienPreselecciona;	
	
	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@OneToMany(mappedBy="sorteo")
    private List<Audiencia> audienciaList;
	@OneToMany(mappedBy="sorteo")
    private List<Elegible> elegibleList;
	@OneToMany(mappedBy="sorteo")
    private List<RolPersonaCaso> rolPersonaCasoList;
	
	
    public Sorteo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSorteo(){
		return this.idSorteo;
	}
	
	public void setIdSorteo(Long idSorteo){
		this.idSorteo = idSorteo;
	}
	
	public Date getFechaRealizacion(){
		return this.fechaRealizacion;
	}
	
	public void setFechaRealizacion(Date fechaRealizacion){
		this.fechaRealizacion = fechaRealizacion;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Long getMateriaCaso(){
		return this.materiaCaso;
	}
	
	public void setMateriaCaso(Long materiaCaso){
		this.materiaCaso = materiaCaso;
	}
		
	public Long getServicioCaso(){
		return this.servicioCaso;
	}
	
	public void setServicioCaso(Long servicioCaso){
		this.servicioCaso = servicioCaso;
	}
		
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public boolean getLiberoLista(){
		return this.liberoLista;
	}
	
	public void setLiberoLista(boolean liberoLista){
		this.liberoLista = liberoLista;
	}
		
	public Date getHoraLiberacion(){
		return this.horaLiberacion;
	}
	
	public void setHoraLiberacion(Date horaLiberacion){
		this.horaLiberacion = horaLiberacion;
	}
		
	public String getAleatoriosSeleccionados(){
		return this.aleatoriosSeleccionados;
	}
	
	public void setAleatoriosSeleccionados(String aleatoriosSeleccionados){
		this.aleatoriosSeleccionados = aleatoriosSeleccionados;
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
		

    public List<Audiencia> getAudienciaList(){
		return this.audienciaList;
	}
	
	public void setAudienciaList(List<Audiencia> audienciaList){
		this.audienciaList = audienciaList;
	}
			
    public List<Elegible> getElegibleList(){
		return this.elegibleList;
	}
	
	public void setElegibleList(List<Elegible> elegibleList){
		this.elegibleList = elegibleList;
	}
			
    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}

	public boolean isPreseleccion() {
		return preseleccion;
	}
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}
	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}


	public void setPreseleccion(boolean preseleccion) {
		this.preseleccion = preseleccion;
	}

	

	public String getQuienPreselecciona() {
		return quienPreselecciona;
	}


	public void setQuienPreselecciona(String quienPreselecciona) {
		this.quienPreselecciona = quienPreselecciona;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SORTEO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSorteo);        
        hash = 37 * hash + Objects.hashCode(this.fechaRealizacion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.materiaCaso);
        hash = 37 * hash + Objects.hashCode(this.servicioCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + (this.liberoLista ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.horaLiberacion);
        hash = 37 * hash + Objects.hashCode(this.aleatoriosSeleccionados);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
        hash = 37 * hash + Objects.hashCode(this.quienPreselecciona);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Sorteo que se pasa
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
        final Sorteo other = (Sorteo) obj;
        
        if (!Objects.equals(this.idSorteo, other.idSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRealizacion, other.fechaRealizacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.materiaCaso, other.materiaCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.servicioCaso, other.servicioCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.liberoLista, other.liberoLista)) {
            return false;
        }
        
        if (!Objects.equals(this.horaLiberacion, other.horaLiberacion)) {
            return false;
        }
        
        if (!Objects.equals(this.aleatoriosSeleccionados, other.aleatoriosSeleccionados)) {
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
        if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
        	return false;
        }
        if (!Objects.equals(this.quienPreselecciona, other.quienPreselecciona)) {
            return false;
        }
        
        return Objects.equals(this.idCaso, other.idCaso);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    /**
     * Devuelve verdadero si el estado se encuentra en estado realizado
     * @return
     */
    public boolean enEstadoRealizado(){
    	boolean realizado = false;
    	if(this.estado.equals(UtilDominios.ESTADO_SORTEO_REALIZADO)){
    		realizado = true;
    	}
    	
    	return realizado;
    }
	// protected region metodos adicionales end

} 

