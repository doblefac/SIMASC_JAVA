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
import javax.persistence.Transient;

import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="AUDIENCIA")
@NamedQuery(name = "Audiencia.findAll", query = "SELECT p FROM Audiencia p")
public class Audiencia implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@Transient
	private boolean audienciaRealizada;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_AUDIENCIA_PK = "idAudiencia";
	public static final String ENTIDAD_AUDIENCIA_VIRTUAL = "virtual";
	public static final String ENTIDAD_AUDIENCIA_ESTADO = "estado";
	public static final String ENTIDAD_AUDIENCIA_HORA_INICIO = "horaInicio";
	public static final String ENTIDAD_AUDIENCIA_HORA_FIN = "horaFin";
	public static final String ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA = "tipoAudiencia";
	public static final String ENTIDAD_AUDIENCIA_CANTIDAD_ASISTENTES = "cantidadAsistentes";
	public static final String ENTIDAD_AUDIENCIA_CANTIDAD_PRINCIPALES = "cantidadPrincipales";
	public static final String ENTIDAD_AUDIENCIA_CANTIDAD_SUPLENTES = "cantidadSuplentes";
	public static final String ENTIDAD_AUDIENCIA_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_AUDIENCIA_RESULTADO = "resultado";
	public static final String ENTIDAD_AUDIENCIA_CONSECUTIVO = "consecutivo";
	public static final String ENTIDAD_AUDIENCIA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_AUDIENCIA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_AUDIENCIA_ESTADO_REGISTRO_AUDIENCIA = "estadoRegistroAudiencia";			
	public static final String ENTIDAD_AUDIENCIA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_AUDIENCIA_ID_SORTEO = "idSorteo";
	public static final String ENTIDAD_AUDIENCIA_ID_CASO = "idCaso";
	public static final String ENTIDAD_AUDIENCIA_ID_ROL_SECRETARIO = "idRolSecretario";
	public static final String ENTIDAD_AUDIENCIA_ID_PERSONA_SECRETARIO = "idPersonaSecretario";
	public static final String ENTIDAD_AUDIENCIA_ID_CASO_SECRETARIO = "idCasoSecretario";
    private static final String[] ATRIBUTOS_ENTIDAD_AUDIENCIA
            = {ENTIDAD_AUDIENCIA_ID_USUARIO_MODIFICACION, ENTIDAD_AUDIENCIA_PK, ENTIDAD_AUDIENCIA_RESULTADO, ENTIDAD_AUDIENCIA_CONSECUTIVO, ENTIDAD_AUDIENCIA_ID_SORTEO, ENTIDAD_AUDIENCIA_HORA_INICIO, ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA, ENTIDAD_AUDIENCIA_ID_CASO, ENTIDAD_AUDIENCIA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_AUDIENCIA_ID_ROL_SECRETARIO, ENTIDAD_AUDIENCIA_VIRTUAL, ENTIDAD_AUDIENCIA_ID_PERSONA_SECRETARIO, ENTIDAD_AUDIENCIA_HORA_FIN, ENTIDAD_AUDIENCIA_ESTADO, ENTIDAD_AUDIENCIA_OBSERVACIONES, ENTIDAD_AUDIENCIA_ID_CASO_SECRETARIO, ENTIDAD_AUDIENCIA_CANTIDAD_ASISTENTES, ENTIDAD_AUDIENCIA_CANTIDAD_PRINCIPALES, ENTIDAD_AUDIENCIA_CANTIDAD_SUPLENTES};

	@Id
    @Column(name="id_audiencia")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idAudiencia;
    
	@Column(name="virtual")
	private boolean virtual;		
    
	@Column(name="estado")
	private String estado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_inicio")
	private Date horaInicio;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="hora_fin")
	private Date horaFin;		
    
	@Column(name="tipo_audiencia")
	private String tipoAudiencia;		
   
	@Column(name="cantidad_asistentes")
	private Integer cantidadAsistentes;	
	
	@Column(name="cantidad_principales")
	private Integer cantidadPrincipales;	
	
	@Column(name="cantidad_suplentes")
	private Integer cantidadSuplentes;	
	
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="resultado")
	private String resultado;		
	
	@Column(name="consecutivo")
	private Long consecutivo;	
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_sorteo")
	private Long idSorteo;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_rol_secretario")
	private Long idRolSecretario;		
    
	@Column(name="id_persona_secretario")
	private Long idPersonaSecretario;		
    
	@Column(name="id_caso_secretario")
	private Long idCasoSecretario;	
	
	@Column(name="tipo_sorteo")
	private String tipoSorteo;	

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_rol_secretario", referencedColumnName="id_rol", insertable = false, updatable = false),
	    @JoinColumn(name = "id_persona_secretario", referencedColumnName="id_persona", insertable = false, updatable = false),
	    @JoinColumn(name = "id_caso_secretario", referencedColumnName="id_caso", insertable = false, updatable = false)	    
	})		
    private RolPersonaCaso rolPersonaCaso;
		
	@ManyToOne
	@JoinColumn(name="id_sorteo", referencedColumnName="id_sorteo", insertable = false, updatable = false)
    private Sorteo sorteo;
		
	@OneToMany(mappedBy="audiencia")
    private List<AgendaPersona> agendaPersonaList;
	@OneToMany(mappedBy="audiencia")
    private List<Agendamiento> agendamientoList;
	@OneToMany(mappedBy="audiencia")
    private List<AudienciaTurnoJornada> audienciaTurnoJornadaList;
	@OneToMany(mappedBy="audiencia")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="audiencia")
    private List<CorreoRolPersonaCaso> correoRolPersonaCasoList;
	@OneToMany(mappedBy="audiencia")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="audiencia")
    private List<Inasistencia> inasistenciaList;
	@OneToMany(mappedBy="audiencia")
    private List<Invitado> invitadoList;
	@OneToMany(mappedBy="audiencia")
    private List<ResultadoAudiencia> resultadoAudienciaList;
	@OneToMany(mappedBy="audiencia")
	private List<PersonaLote> personaLoteList;
	
	
    public Audiencia(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdAudiencia(){
		return this.idAudiencia;
	}
	
	public void setIdAudiencia(Long idAudiencia){
		this.idAudiencia = idAudiencia;
	}
	
	public boolean getVirtual(){
		return this.virtual;
	}
	
	public void setVirtual(boolean virtual){
		this.virtual = virtual;
	}
		
	public String getEstado(){
		return this.estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	public Date getHoraInicio(){
		return this.horaInicio;
	}
	
	public void setHoraInicio(Date horaInicio){
		this.horaInicio = horaInicio;
	}
		
	public Date getHoraFin(){
		return this.horaFin;
	}
	
	public void setHoraFin(Date horaFin){
		this.horaFin = horaFin;
	}
		
	public String getTipoAudiencia(){
		return this.tipoAudiencia;
	}
	
	public void setTipoAudiencia(String tipoAudiencia){
		this.tipoAudiencia = tipoAudiencia;
	}
		
	public Integer getCantidadAsistentes(){
		return this.cantidadAsistentes;
	}
	
	public void setCantidadAsistentes(Integer cantidadAsistentes){
		this.cantidadAsistentes = cantidadAsistentes;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
	
	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
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
		
	public Long getIdSorteo(){
		return this.idSorteo;
	}
	
	public void setIdSorteo(Long idSorteo){
		this.idSorteo = idSorteo;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public Long getIdRolSecretario(){
		return this.idRolSecretario;
	}
	
	public void setIdRolSecretario(Long idRolSecretario){
		this.idRolSecretario = idRolSecretario;
	}
		
	public Integer getCantidadPrincipales() {
		return cantidadPrincipales;
	}


	public void setCantidadPrincipales(Integer cantidadPrincipales) {
		this.cantidadPrincipales = cantidadPrincipales;
	}

	public Integer getCantidadSuplentes() {
		return cantidadSuplentes;
	}


	public void setCantidadSuplentes(Integer cantidadSuplentes) {
		this.cantidadSuplentes = cantidadSuplentes;
	}


	public Long getIdPersonaSecretario(){
		return this.idPersonaSecretario;
	}
	
	public void setIdPersonaSecretario(Long idPersonaSecretario){
		this.idPersonaSecretario = idPersonaSecretario;
	}
		
	public Long getIdCasoSecretario(){
		return this.idCasoSecretario;
	}
	
	public void setIdCasoSecretario(Long idCasoSecretario){
		this.idCasoSecretario = idCasoSecretario;
	}
		

    public List<AgendaPersona> getAgendaPersonaList(){
		return this.agendaPersonaList;
	}
	
	public void setAgendaPersonaList(List<AgendaPersona> agendaPersonaList){
		this.agendaPersonaList = agendaPersonaList;
	}
			
    public List<Agendamiento> getAgendamientoList(){
		return this.agendamientoList;
	}
	
	public void setAgendamientoList(List<Agendamiento> agendamientoList){
		this.agendamientoList = agendamientoList;
	}
			
    public List<AudienciaTurnoJornada> getAudienciaTurnoJornadaList(){
		return this.audienciaTurnoJornadaList;
	}
	
	public void setAudienciaTurnoJornadaList(List<AudienciaTurnoJornada> audienciaTurnoJornadaList){
		this.audienciaTurnoJornadaList = audienciaTurnoJornadaList;
	}
			
    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}
			
    public List<CorreoRolPersonaCaso> getCorreoRolPersonaCasoList(){
		return this.correoRolPersonaCasoList;
	}
	
	public void setCorreoRolPersonaCasoList(List<CorreoRolPersonaCaso> correoRolPersonaCasoList){
		this.correoRolPersonaCasoList = correoRolPersonaCasoList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<Inasistencia> getInasistenciaList(){
		return this.inasistenciaList;
	}
	
	public void setInasistenciaList(List<Inasistencia> inasistenciaList){
		this.inasistenciaList = inasistenciaList;
	}
			
    public List<Invitado> getInvitadoList(){
		return this.invitadoList;
	}
	
	public void setInvitadoList(List<Invitado> invitadoList){
		this.invitadoList = invitadoList;
	}
			
    public List<ResultadoAudiencia> getResultadoAudienciaList(){
		return this.resultadoAudienciaList;
	}
	
	public void setResultadoAudienciaList(List<ResultadoAudiencia> resultadoAudienciaList){
		this.resultadoAudienciaList = resultadoAudienciaList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public RolPersonaCaso getRolPersonaCaso(){
		return this.rolPersonaCaso; 
	}
	
	public void setRolPersonaCaso(RolPersonaCaso rolPersonaCaso){
		this.rolPersonaCaso = rolPersonaCaso;
	}
    public Sorteo getSorteo(){
		return this.sorteo; 
	}
	
	public void setSorteo(Sorteo sorteo){
		this.sorteo = sorteo;
	}

	public List<PersonaLote> getPersonaLoteList() {
		return personaLoteList;
	}


	public void setPersonaLoteList(List<PersonaLote> personaLoteList) {
		this.personaLoteList = personaLoteList;
	}


	public String getTipoSorteo() {
		return tipoSorteo;
	}


	public void setTipoSorteo(String tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_AUDIENCIA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idAudiencia);        
        hash = 37 * hash + (this.virtual ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.tipoAudiencia);
        hash = 37 * hash + Objects.hashCode(this.cantidadAsistentes);
        hash = 37 * hash + Objects.hashCode(this.cantidadSuplentes);
        hash = 37 * hash + Objects.hashCode(this.cantidadPrincipales);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.consecutivo);        
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSorteo);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idRolSecretario);
        hash = 37 * hash + Objects.hashCode(this.idPersonaSecretario);
        hash = 37 * hash + Objects.hashCode(this.idCasoSecretario);
        hash = 37 * hash + Objects.hashCode(this.tipoSorteo);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Audiencia que se pasa
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
        final Audiencia other = (Audiencia) obj;
        
        if (!Objects.equals(this.idAudiencia, other.idAudiencia)) {
            return false;
        }
        
        if (!Objects.equals(this.virtual, other.virtual)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoAudiencia, other.tipoAudiencia)) {
            return false;
        }

        
        if (!Objects.equals(this.cantidadAsistentes, other.cantidadAsistentes)) {
        	return false;
        }
        if (!Objects.equals(this.cantidadPrincipales, other.cantidadPrincipales)) {
        	return false;
        }
        if (!Objects.equals(this.cantidadSuplentes, other.cantidadSuplentes)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.consecutivo, other.consecutivo)) {
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
        
        if (!Objects.equals(this.idSorteo, other.idSorteo)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idRolSecretario, other.idRolSecretario)) {
            return false;
        }
        
        if (!Objects.equals(this.idPersonaSecretario, other.idPersonaSecretario)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoSorteo, other.tipoSorteo)) {
            return false;
        }
        return Objects.equals(this.idCasoSecretario, other.idCasoSecretario);
        
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    /**
     * Devuelve verdadero si la audiencia se encuentra en estado cancelado
     * @return
     */
    public boolean enEstadoCancelado(){
    	boolean cancelado = false;
    	if(this.estado.equals(UtilDominios.ESTADO_AUDIENCIA_CANCELADA)){
    		cancelado = true;
    	}    	
    	return cancelado;
    }
    /**
     * Devuelve verdadero si la audiencia se encuentra en estado aplazada
     * @return
     */
    public boolean enEstadoAplazado(){
    	boolean aplazada = false;
    	if(this.estado.equals(UtilDominios.ESTADO_AUDIENCIA_APLAZADA)){
    		aplazada = true;
    	}    	
    	return aplazada;
    }
        
    
    /**
	 * @return the audienciaRealizada
	 */
	public boolean isAudienciaRealizada() {		
		audienciaRealizada = false;
    	if(UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equals(this.estado)) {
    		audienciaRealizada = true;
    	}
		return audienciaRealizada;
	}
    
    
    
    
    /**
     * Devuelve la sala asociada al agendamiento si este existe y si se encuentra
     * una sala asociada a este, de lo contrario devuelve nulo.
     * @return La sala asociada a la audiencia
     */
    public Sala obtenerSala(){
    	Sala sala = null;    	
    	List<Agendamiento> agendamientos = this.getAgendamientoList();
    	if(agendamientos!=null){
    		if(agendamientos.size()>1){
    			Agendamiento agendamiento = agendamientos.get(0);
    			if(agendamiento!=null){
    				sala = agendamiento.getSala();
    			}
    		}
    	}
    	
    	return sala;
    }
	// protected region metodos adicionales end




} 

