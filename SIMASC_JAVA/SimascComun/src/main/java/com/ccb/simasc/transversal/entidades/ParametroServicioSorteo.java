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
@Table(name="PARAMETRO_SERVICIO_SORTEO")
@NamedQuery(name = "ParametroServicioSorteo.findAll", query = "SELECT p FROM ParametroServicioSorteo p")
public class ParametroServicioSorteo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_PK = "idParametrosServiciosSorteo";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_PARAMETROS_SORTEO = "idParametrosSorteo";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_LISTA = "sorteoConLista";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_MATERIA = "sorteoConMateria";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_CANTIDAD_DE_MATERIAS = "cantidadDeMaterias";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO_PARAMETROSERVICIOSORTEO = "estadoRegistroParametroServicioSorteo";			
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_ROL = "idRol";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_BLOQUEA_SUPLENTE = "bloqueaSuplente";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_AUDIENCIA_LIBERA_SUPLENTE = "audiencia_libera_suplente";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_LIBERA_ARBITROS_CIERRE = "liberaArbitrosCierre";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_NO_PRONUNCIAMIENTO = "suspendeNoPronunciamiento";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_RECHAZO = "suspendeRechazo";
	public static final String ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_EXTEMPORANEO = "suspendeExtemporaneo";
	
    private static final String[] ATRIBUTOS_ENTIDAD_PARAMETRO_SERVICIO_SORTEO
            = {ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_SERVICIO, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO, 
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_ROL, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_CANTIDAD_DE_MATERIAS, 
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_USUARIO_MODIFICACION, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_PK, 
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ID_PARAMETROS_SORTEO, 
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_LISTA, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SORTEO_CON_MATERIA,
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_BLOQUEA_SUPLENTE, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_AUDIENCIA_LIBERA_SUPLENTE,
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_LIBERA_ARBITROS_CIERRE, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_NO_PRONUNCIAMIENTO,
            		ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_RECHAZO, ENTIDAD_PARAMETRO_SERVICIO_SORTEO_SUSPENDE_EXTEMPORANEO};

	@Id
    @Column(name="id_parametros_servicios_sorteo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idParametrosServiciosSorteo;
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_parametros_sorteo")
	private Long idParametrosSorteo;		
    
	@Column(name="sorteo_con_lista")
	private boolean sorteoConLista;		
    
	@Column(name="sorteo_con_materia")
	private boolean sorteoConMateria;		
    
	@Column(name="cantidad_de_materias")
	private Integer cantidadDeMaterias;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_rol")
	private Long idRol;		
	
	@Column(name="bloquea_suplente")
	private boolean bloqueaSuplente;		

	@Column(name="audiencia_libera_suplente")
	private String audienciaLiberaSuplente;		

	@Column(name="libera_arbitros_cierre")
	private boolean liberaArbitrosCierre;		

	@Column(name="suspende_no_pronunciamiento")
	private boolean suspendeNoPronunciamiento;		

	@Column(name="suspende_rechazo")
	private boolean suspendeRechazo;		

	@Column(name="suspende_extemporaneo")
	private boolean suspendeExtemporaneo;		

	@ManyToOne
	@JoinColumn(name="id_parametros_sorteo", referencedColumnName="id_parametros_sorteo", insertable = false, updatable = false)
    private ParametroSorteo parametroSorteo;
		
	@ManyToOne
	@JoinColumn(name="id_rol", referencedColumnName="id_rol", insertable = false, updatable = false)
    private Rol rol;
		
	@ManyToOne
	@JoinColumn(name="id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false)
    private Servicio servicio;
		
	
	
    public ParametroServicioSorteo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdParametrosServiciosSorteo(){
		return this.idParametrosServiciosSorteo;
	}
	
	public void setIdParametrosServiciosSorteo(Long idParametrosServiciosSorteo){
		this.idParametrosServiciosSorteo = idParametrosServiciosSorteo;
	}
	
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdParametrosSorteo(){
		return this.idParametrosSorteo;
	}
	
	public void setIdParametrosSorteo(Long idParametrosSorteo){
		this.idParametrosSorteo = idParametrosSorteo;
	}
		
	public boolean getSorteoConLista(){
		return this.sorteoConLista;
	}
	
	public void setSorteoConLista(boolean sorteoConLista){
		this.sorteoConLista = sorteoConLista;
	}
		
	public boolean getSorteoConMateria(){
		return this.sorteoConMateria;
	}
	
	public void setSorteoConMateria(boolean sorteoConMateria){
		this.sorteoConMateria = sorteoConMateria;
	}
		
	public Integer getCantidadDeMaterias(){
		return this.cantidadDeMaterias;
	}
	
	public void setCantidadDeMaterias(Integer cantidadDeMaterias){
		this.cantidadDeMaterias = cantidadDeMaterias;
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
		
	public Long getIdRol(){
		return this.idRol;
	}
	
	public void setIdRol(Long idRol){
		this.idRol = idRol;
	}
		

    public ParametroSorteo getParametroSorteo(){
		return this.parametroSorteo; 
	}
	
	public void setParametroSorteo(ParametroSorteo parametroSorteo){
		this.parametroSorteo = parametroSorteo;
	}
    public Rol getRol(){
		return this.rol; 
	}
	
	public void setRol(Rol rol){
		this.rol = rol;
	}
    public Servicio getServicio(){
		return this.servicio; 
	}
	
	public void setServicio(Servicio servicio){
		this.servicio = servicio;
	}

    public boolean getBloqueaSuplente(){
		return this.bloqueaSuplente; 
	}
	
	public void setBloqueaSuplente(boolean bloqueaSuplente){
		this.bloqueaSuplente = bloqueaSuplente;
	}

    public String getAudienciaLiberaSuplente(){
		return this.audienciaLiberaSuplente; 
	}
	
	public void setAudienciaLiberaSuplente(String audienciaLiberaSuplente){
		this.audienciaLiberaSuplente = audienciaLiberaSuplente;
	}
	
    public boolean getLiberaArbitrosCierre(){
		return this.liberaArbitrosCierre; 
	}
	
	public void setliberaArbitrosCierre(boolean liberaArbitrosCierre){
		this.liberaArbitrosCierre = liberaArbitrosCierre;
	}
	
    public boolean getSuspendeNoPronunciamiento(){
		return this.suspendeNoPronunciamiento; 
	}
	
	public void setSuspendeNoPronunciamiento(boolean suspendeNoPronunciamiento){
		this.suspendeNoPronunciamiento = suspendeNoPronunciamiento;
	}

    public boolean getSuspendeRechazo(){
		return this.suspendeRechazo; 
	}
	
	public void setSuspendeRechazo(boolean suspendeRechazo){
		this.suspendeRechazo = suspendeRechazo;
	}
	
    public boolean getSuspendeExtemporaneo(){
		return this.suspendeExtemporaneo; 
	}
	
	public void setSuspendeExtemporaneo(boolean suspendeExtemporaneo){
		this.suspendeExtemporaneo = suspendeExtemporaneo;
	}

	
	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PARAMETRO_SERVICIO_SORTEO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idParametrosServiciosSorteo);        
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idParametrosSorteo);
        hash = 37 * hash + (this.sorteoConLista ? 0 : 1);
        hash = 37 * hash + (this.sorteoConMateria ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.cantidadDeMaterias);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idRol);
        hash = 37 * hash + Objects.hashCode(this.bloqueaSuplente);
        hash = 37 * hash + Objects.hashCode(this.audienciaLiberaSuplente);
        hash = 37 * hash + Objects.hashCode(this.liberaArbitrosCierre);
        hash = 37 * hash + Objects.hashCode(this.suspendeNoPronunciamiento);
        hash = 37 * hash + Objects.hashCode(this.suspendeRechazo);
        hash = 37 * hash + Objects.hashCode(this.suspendeExtemporaneo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroServicioSorteo que se pasa
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
        final ParametroServicioSorteo other = (ParametroServicioSorteo) obj;
        
        if (!Objects.equals(this.idParametrosServiciosSorteo, other.idParametrosServiciosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idParametrosSorteo, other.idParametrosSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.sorteoConLista, other.sorteoConLista)) {
            return false;
        }
        
        if (!Objects.equals(this.sorteoConMateria, other.sorteoConMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.cantidadDeMaterias, other.cantidadDeMaterias)) {
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
        
        if (!Objects.equals(this.bloqueaSuplente, other.bloqueaSuplente)) {
            return false;
        }
        
        if (!Objects.equals(this.audienciaLiberaSuplente, other.audienciaLiberaSuplente)) {
            return false;
        }
        
        if (!Objects.equals(this.liberaArbitrosCierre, other.liberaArbitrosCierre)) {
            return false;
        }
        
        if (!Objects.equals(this.suspendeNoPronunciamiento, other.suspendeNoPronunciamiento)) {
            return false;
        }
        
        if (!Objects.equals(this.suspendeRechazo, other.suspendeRechazo)) {
            return false;
        }
        
        if (!Objects.equals(this.suspendeExtemporaneo, other.suspendeExtemporaneo)) {
            return false;
        }
        
        return Objects.equals(this.idRol, other.idRol);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

