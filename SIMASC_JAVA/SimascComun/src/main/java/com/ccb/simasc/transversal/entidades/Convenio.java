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
import javax.persistence.JoinColumns;
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
@Table(name="CONVENIO")
@NamedQuery(name = "Convenio.findAll", query = "SELECT p FROM Convenio p")
public class Convenio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CONVENIO_PK = "idConvenio";
	public static final String ENTIDAD_CONVENIO_TIPO_CONVENIO = "tipoConvenio";
	public static final String ENTIDAD_CONVENIO_NOMBRE = "nombre";
	public static final String ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA = "fechaInicioVigencia";
	public static final String ENTIDAD_CONVENIO_FECHA_FIN_VIGENCIA = "fechaFinVigencia";
	public static final String ENTIDAD_CONVENIO_LIMITE_INFERIOR_DIAS_PROGRAMACION_AUDIENCIAS = "limiteInferiorDiasProgramacionAudiencias";
	public static final String ENTIDAD_CONVENIO_LIMITE_SUPERIOR_DIAS_PROGRAMACION_AUDIENCIAS = "limiteSuperiorDiasProgramacionAudiencias";
	public static final String ENTIDAD_CONVENIO_REQUIERE_SUPLENTE = "requiereSuplente";
	public static final String ENTIDAD_CONVENIO_DURACION_AUDIENCIAS = "duracionAudiencias";
	public static final String ENTIDAD_CONVENIO_NUMERO_DE_AUDIENCIAS_RECOBRO = "numeroDeAudienciasRecobro";
	public static final String ENTIDAD_CONVENIO_LUGAR = "lugar";
	public static final String ENTIDAD_CONVENIO_DIRECCION = "direccion";
	public static final String ENTIDAD_CONVENIO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CONVENIO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CONVENIO_ESTADO_REGISTRO_CONVENIO = "estadoRegistroConvenio";			
	public static final String ENTIDAD_CONVENIO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CONVENIO_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_CONVENIO_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_CONVENIO_ID_ZONA_GEOGRAFICA = "idZonaGeografica";
	public static final String ENTIDAD_CONVENIO_ID_CENTRO = "idCentro";
	public static final String ENTIDAD_CONVENIO_ID_PERSONA = "idPersona";
	public static final String ENTIDAD_CONVENIO_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_CONVENIO
            = {ENTIDAD_CONVENIO_LUGAR, ENTIDAD_CONVENIO_DIRECCION, ENTIDAD_CONVENIO_FECHA_FIN_VIGENCIA, ENTIDAD_CONVENIO_ID_DOCUMENTO, ENTIDAD_CONVENIO_DURACION_AUDIENCIAS, ENTIDAD_CONVENIO_NUMERO_DE_AUDIENCIAS_RECOBRO, ENTIDAD_CONVENIO_NOMBRE, ENTIDAD_CONVENIO_ID_SERVICIO, ENTIDAD_CONVENIO_ID_MATERIA, ENTIDAD_CONVENIO_LIMITE_SUPERIOR_DIAS_PROGRAMACION_AUDIENCIAS, ENTIDAD_CONVENIO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CONVENIO_ID_USUARIO_MODIFICACION, ENTIDAD_CONVENIO_ID_PERSONA, ENTIDAD_CONVENIO_REQUIERE_SUPLENTE, ENTIDAD_CONVENIO_ID_ZONA_GEOGRAFICA, ENTIDAD_CONVENIO_TIPO_CONVENIO, ENTIDAD_CONVENIO_FECHA_INICIO_VIGENCIA, ENTIDAD_CONVENIO_ESTADO_REGISTRO, ENTIDAD_CONVENIO_ID_CENTRO, ENTIDAD_CONVENIO_PK, ENTIDAD_CONVENIO_LIMITE_INFERIOR_DIAS_PROGRAMACION_AUDIENCIAS};

	@Id
    @Column(name="id_convenio")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idConvenio;
    
	@Column(name="tipo_convenio")
	private String tipoConvenio;		
    
	@Column(name="nombre")
	private String nombre;		
    
	@Column(name="fecha_inicio_vigencia")
	private Date fechaInicioVigencia;		
    
	@Column(name="fecha_fin_vigencia")
	private Date fechaFinVigencia;		
    
	@Column(name="limite_inferior_dias_programacion_audiencias")
	private Integer limiteInferiorDiasProgramacionAudiencias;		
    
	@Column(name="limite_superior_dias_programacion_audiencias")
	private Integer limiteSuperiorDiasProgramacionAudiencias;		
    
	@Column(name="requiere_suplente")
	private boolean requiereSuplente;		
    
	@Column(name="duracion_audiencias")
	private String duracionAudiencias;		
    
	@Column(name="numero_de_audiencias_recobro")
	private Integer numeroDeAudienciasRecobro;		
    
	@Column(name="lugar")
	private String lugar;		
	
	@Column(name="direccion")
	private String direccion;	
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_materia")
	private Long idMateria;		
    
	@Column(name="id_zona_geografica")
	private String idZonaGeografica;		
    
	@Column(name="id_centro")
	private Long idCentro;		
    
	@Column(name="id_persona")
	private Long idPersona;		
    
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_centro", referencedColumnName="id_centro", insertable = false, updatable = false)
    private Centro centro;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)	    
	})		
    private ServicioMateria servicioMateria;
		
	@ManyToOne
	@JoinColumn(name="id_zona_geografica", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="convenio")
    private List<Caso> casoList;
	@OneToMany(mappedBy="convenio")
    private List<ContratoConvenio> contratoConvenioList;
	@OneToMany(mappedBy="convenio")
    private List<ConvenioCentro> convenioCentroList;
	@OneToMany(mappedBy="convenio")
    private List<PlantillaCarta> plantillaCartaList;
	@OneToMany(mappedBy="convenio")
    private List<RelacionadoConvenio> relacionadoConvenioList;
	@OneToMany(mappedBy="convenio")
    private List<SedeConvenio> sedeConvenioList;
	@OneToMany(mappedBy="convenio")
    private List<TurnoJornada> turnoJornadaList;
	
	
    public Convenio(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
	
	public String getTipoConvenio(){
		return this.tipoConvenio;
	}
	
	public void setTipoConvenio(String tipoConvenio){
		this.tipoConvenio = tipoConvenio;
	}
		
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Date getFechaInicioVigencia(){
		return this.fechaInicioVigencia;
	}
	
	public void setFechaInicioVigencia(Date fechaInicioVigencia){
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
		
	public Date getFechaFinVigencia(){
		return this.fechaFinVigencia;
	}
	
	public void setFechaFinVigencia(Date fechaFinVigencia){
		this.fechaFinVigencia = fechaFinVigencia;
	}
		
	public Integer getLimiteInferiorDiasProgramacionAudiencias(){
		return this.limiteInferiorDiasProgramacionAudiencias;
	}
	
	public void setLimiteInferiorDiasProgramacionAudiencias(Integer limiteInferiorDiasProgramacionAudiencias){
		this.limiteInferiorDiasProgramacionAudiencias = limiteInferiorDiasProgramacionAudiencias;
	}
		
	public Integer getLimiteSuperiorDiasProgramacionAudiencias(){
		return this.limiteSuperiorDiasProgramacionAudiencias;
	}
	
	public void setLimiteSuperiorDiasProgramacionAudiencias(Integer limiteSuperiorDiasProgramacionAudiencias){
		this.limiteSuperiorDiasProgramacionAudiencias = limiteSuperiorDiasProgramacionAudiencias;
	}
		
	public boolean getRequiereSuplente(){
		return this.requiereSuplente;
	}
	
	public void setRequiereSuplente(boolean requiereSuplente){
		this.requiereSuplente = requiereSuplente;
	}
		
	public String getDuracionAudiencias(){
		return this.duracionAudiencias;
	}
	
	public void setDuracionAudiencias(String duracionAudiencias){
		this.duracionAudiencias = duracionAudiencias;
	}
		
	public Integer getNumeroDeAudienciasRecobro(){
		return this.numeroDeAudienciasRecobro;
	}
	
	public void setNumeroDeAudienciasRecobro(Integer numeroDeAudienciasRecobro){
		this.numeroDeAudienciasRecobro = numeroDeAudienciasRecobro;
	}
		
	public String getLugar(){
		return this.lugar;
	}
	
	public void setLugar(String lugar){
		this.lugar = lugar;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		
	public Long getIdServicio(){
		return this.idServicio;
	}
	
	public void setIdServicio(Long idServicio){
		this.idServicio = idServicio;
	}
		
	public Long getIdMateria(){
		return this.idMateria;
	}
	
	public void setIdMateria(Long idMateria){
		this.idMateria = idMateria;
	}
		
	public String getIdZonaGeografica(){
		return this.idZonaGeografica;
	}
	
	public void setIdZonaGeografica(String idZonaGeografica){
		this.idZonaGeografica = idZonaGeografica;
	}
		
	public Long getIdCentro(){
		return this.idCentro;
	}
	
	public void setIdCentro(Long idCentro){
		this.idCentro = idCentro;
	}
		
	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public List<Caso> getCasoList(){
		return this.casoList;
	}
	
	public void setCasoList(List<Caso> casoList){
		this.casoList = casoList;
	}
			
    public List<ContratoConvenio> getContratoConvenioList(){
		return this.contratoConvenioList;
	}
	
	public void setContratoConvenioList(List<ContratoConvenio> contratoConvenioList){
		this.contratoConvenioList = contratoConvenioList;
	}
			
    public List<ConvenioCentro> getConvenioCentroList(){
		return this.convenioCentroList;
	}
	
	public void setConvenioCentroList(List<ConvenioCentro> convenioCentroList){
		this.convenioCentroList = convenioCentroList;
	}
			
    public List<PlantillaCarta> getPlantillaCartaList(){
		return this.plantillaCartaList;
	}
	
	public void setPlantillaCartaList(List<PlantillaCarta> plantillaCartaList){
		this.plantillaCartaList = plantillaCartaList;
	}
			
    public List<RelacionadoConvenio> getRelacionadoConvenioList(){
		return this.relacionadoConvenioList;
	}
	
	public void setRelacionadoConvenioList(List<RelacionadoConvenio> relacionadoConvenioList){
		this.relacionadoConvenioList = relacionadoConvenioList;
	}
			
    public List<SedeConvenio> getSedeConvenioList(){
		return this.sedeConvenioList;
	}
	
	public void setSedeConvenioList(List<SedeConvenio> sedeConvenioList){
		this.sedeConvenioList = sedeConvenioList;
	}
			
    public List<TurnoJornada> getTurnoJornadaList(){
		return this.turnoJornadaList;
	}
	
	public void setTurnoJornadaList(List<TurnoJornada> turnoJornadaList){
		this.turnoJornadaList = turnoJornadaList;
	}
			
    public Centro getCentro(){
		return this.centro; 
	}
	
	public void setCentro(Centro centro){
		this.centro = centro;
	}
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public ServicioMateria getServicioMateria(){
		return this.servicioMateria; 
	}
	
	public void setServicioMateria(ServicioMateria servicioMateria){
		this.servicioMateria = servicioMateria;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CONVENIO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idConvenio);        
        hash = 37 * hash + Objects.hashCode(this.tipoConvenio);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioVigencia);
        hash = 37 * hash + Objects.hashCode(this.fechaFinVigencia);
        hash = 37 * hash + Objects.hashCode(this.limiteInferiorDiasProgramacionAudiencias);
        hash = 37 * hash + Objects.hashCode(this.limiteSuperiorDiasProgramacionAudiencias);
        hash = 37 * hash + (this.requiereSuplente ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.duracionAudiencias);
        hash = 37 * hash + Objects.hashCode(this.numeroDeAudienciasRecobro);
        hash = 37 * hash + Objects.hashCode(this.lugar);
        hash = 37 * hash + Objects.hashCode(this.direccion);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.idZonaGeografica);
        hash = 37 * hash + Objects.hashCode(this.idCentro);
        hash = 37 * hash + Objects.hashCode(this.idPersona);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Convenio que se pasa
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
        final Convenio other = (Convenio) obj;
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoConvenio, other.tipoConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioVigencia, other.fechaInicioVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinVigencia, other.fechaFinVigencia)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteInferiorDiasProgramacionAudiencias, other.limiteInferiorDiasProgramacionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.limiteSuperiorDiasProgramacionAudiencias, other.limiteSuperiorDiasProgramacionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.requiereSuplente, other.requiereSuplente)) {
            return false;
        }
        
        if (!Objects.equals(this.duracionAudiencias, other.duracionAudiencias)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDeAudienciasRecobro, other.numeroDeAudienciasRecobro)) {
            return false;
        }
        
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        
        if (!Objects.equals(this.direccion, other.direccion)) {
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
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.idZonaGeografica, other.idZonaGeografica)) {
            return false;
        }
        
        if (!Objects.equals(this.idCentro, other.idCentro)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }


	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

