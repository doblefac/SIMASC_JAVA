package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
@Table(name="FECHAS_CASO")
@NamedQuery(name = "FechasCaso.findAll", query = "SELECT p FROM FechasCaso p")
public class FechasCaso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_FECHAS_CASO_PK = "idCaso";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_DEMANDA = "fechaDeDemanda";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_DESIGNACION_DE_ARBITROS = "fechaDeDesignacionDeArbitros";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_INSTALACION = "fechaDeInstalacion";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_ADMISION_DE_DEMANDA = "fechaDeAdmisionDeDemanda";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_NOTIFICACION_DEL_DEMANDADO = "fechaDeNotificacionDelDemandado";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_CONTESTACION_DE_DEMANDA = "fechaDeContestacionDeDemanda";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_DEMANDA_DE_RECONVENCION = "fechaDeDemandaDeReconvencion";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_LLAMAMIENTO_EN_GARANTIA = "fechaDeLlamamientoEnGarantia";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_TRASLADO_A_EXCEPCIONES_DE_MERITO = "fechaDeTrasladoAExcepcionesDeMerito";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_DE_CONCILIACION = "fechaDeAudienciaDeConciliacion";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_FIJACION_DE_GASTOS_Y_HONORARIOS = "fechaDeFijacionDeGastosYHonorarios";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_PRIMERA_DE_TRAMITE = "fechaDeAudienciaPrimeraDeTramite";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIAS_DE_PRUEBAS = "fechaDeAudienciasDePruebas";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_CIERRE_DE_ETAPA_APROBATORIA = "fechaDeCierreDeEtapaAprobatoria";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIAS_DE_ALEGATOS = "fechaDeAudienciasDeAlegatos";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_DE_LAUDO = "fechaDeAudienciaDeLaudo";
	public static final String ENTIDAD_FECHAS_CASO_F_AUD_ACLARACIONES_COMPLEMENTACIONES_CORRECCIONES_LAUDO = "fAudAclaracionesComplementacionesCorreccionesLaudo";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_TRAMITE_RECURSO_ANULACION = "fechaDeTramiteRecursoAnulacion";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_TRIBUNAL_CONSIGNA_AL_CAC = "fechaTribunalConsignaAlCac";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_DE_CIERRE = "fechaDeCierre";
	public static final String ENTIDAD_FECHAS_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_FECHAS_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_FECHAS_CASO_ESTADO_REGISTRO_FECHASCASO = "estadoRegistroFechasCaso";			
	public static final String ENTIDAD_FECHAS_CASO_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_FECHAS_CASO
            = {ENTIDAD_FECHAS_CASO_FECHA_DE_LLAMAMIENTO_EN_GARANTIA, ENTIDAD_FECHAS_CASO_F_AUD_ACLARACIONES_COMPLEMENTACIONES_CORRECCIONES_LAUDO, ENTIDAD_FECHAS_CASO_FECHA_DE_TRAMITE_RECURSO_ANULACION, ENTIDAD_FECHAS_CASO_FECHA_DE_TRASLADO_A_EXCEPCIONES_DE_MERITO, ENTIDAD_FECHAS_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIAS_DE_ALEGATOS, ENTIDAD_FECHAS_CASO_FECHA_DE_ADMISION_DE_DEMANDA, ENTIDAD_FECHAS_CASO_FECHA_DE_FIJACION_DE_GASTOS_Y_HONORARIOS, ENTIDAD_FECHAS_CASO_ESTADO_REGISTRO, ENTIDAD_FECHAS_CASO_FECHA_TRIBUNAL_CONSIGNA_AL_CAC, ENTIDAD_FECHAS_CASO_FECHA_DE_CONTESTACION_DE_DEMANDA, ENTIDAD_FECHAS_CASO_FECHA_DE_DEMANDA_DE_RECONVENCION, ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIAS_DE_PRUEBAS, ENTIDAD_FECHAS_CASO_FECHA_DE_INSTALACION, ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_DE_LAUDO, ENTIDAD_FECHAS_CASO_FECHA_DE_DESIGNACION_DE_ARBITROS, ENTIDAD_FECHAS_CASO_FECHA_DE_CIERRE, ENTIDAD_FECHAS_CASO_FECHA_DE_DEMANDA, ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_PRIMERA_DE_TRAMITE, ENTIDAD_FECHAS_CASO_ID_USUARIO_MODIFICACION, ENTIDAD_FECHAS_CASO_FECHA_DE_AUDIENCIA_DE_CONCILIACION, ENTIDAD_FECHAS_CASO_FECHA_DE_CIERRE_DE_ETAPA_APROBATORIA, ENTIDAD_FECHAS_CASO_FECHA_DE_NOTIFICACION_DEL_DEMANDADO, ENTIDAD_FECHAS_CASO_PK};

	@Id
    @Column(name="id_caso")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCaso;

    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_demanda")
	private Date fechaDeDemanda;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_designacion_de_arbitros")
	private Date fechaDeDesignacionDeArbitros;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_instalacion")
	private Date fechaDeInstalacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_admision_de_demanda")
	private Date fechaDeAdmisionDeDemanda;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_notificacion_del_demandado")
	private Date fechaDeNotificacionDelDemandado;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_contestacion_de_demanda")
	private Date fechaDeContestacionDeDemanda;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_demanda_de_reconvencion")
	private Date fechaDeDemandaDeReconvencion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_llamamiento_en_garantia")
	private Date fechaDeLlamamientoEnGarantia;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_traslado_a_excepciones_de_merito")
	private Date fechaDeTrasladoAExcepcionesDeMerito;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_audiencia_de_conciliacion")
	private Date fechaDeAudienciaDeConciliacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_fijacion_de_gastos_y_honorarios")
	private Date fechaDeFijacionDeGastosYHonorarios;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_audiencia_primera_de_tramite")
	private Date fechaDeAudienciaPrimeraDeTramite;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_audiencias_de_pruebas")
	private Date fechaDeAudienciasDePruebas;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_cierre_de_etapa_aprobatoria")
	private Date fechaDeCierreDeEtapaAprobatoria;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_audiencias_de_alegatos")
	private Date fechaDeAudienciasDeAlegatos;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_audiencia_de_laudo")
	private Date fechaDeAudienciaDeLaudo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="f_aud_aclaraciones_complementaciones_correcciones_laudo")
	private Date fAudAclaracionesComplementacionesCorreccionesLaudo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_tramite_recurso_anulacion")
	private Date fechaDeTramiteRecursoAnulacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_tribunal_consigna_al_CAC")
	private Date fechaTribunalConsignaAlCac;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_cierre")
	private Date fechaDeCierre;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	
	
    public FechasCaso(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
	
	public Date getFechaDeDemanda(){
		return this.fechaDeDemanda;
	}
	
	public void setFechaDeDemanda(Date fechaDeDemanda){
		this.fechaDeDemanda = fechaDeDemanda;
	}
		
	public Date getFechaDeDesignacionDeArbitros(){
		return this.fechaDeDesignacionDeArbitros;
	}
	
	public void setFechaDeDesignacionDeArbitros(Date fechaDeDesignacionDeArbitros){
		this.fechaDeDesignacionDeArbitros = fechaDeDesignacionDeArbitros;
	}
		
	public Date getFechaDeInstalacion(){
		return this.fechaDeInstalacion;
	}
	
	public void setFechaDeInstalacion(Date fechaDeInstalacion){
		this.fechaDeInstalacion = fechaDeInstalacion;
	}
		
	public Date getFechaDeAdmisionDeDemanda(){
		return this.fechaDeAdmisionDeDemanda;
	}
	
	public void setFechaDeAdmisionDeDemanda(Date fechaDeAdmisionDeDemanda){
		this.fechaDeAdmisionDeDemanda = fechaDeAdmisionDeDemanda;
	}
		
	public Date getFechaDeNotificacionDelDemandado(){
		return this.fechaDeNotificacionDelDemandado;
	}
	
	public void setFechaDeNotificacionDelDemandado(Date fechaDeNotificacionDelDemandado){
		this.fechaDeNotificacionDelDemandado = fechaDeNotificacionDelDemandado;
	}
		
	public Date getFechaDeContestacionDeDemanda(){
		return this.fechaDeContestacionDeDemanda;
	}
	
	public void setFechaDeContestacionDeDemanda(Date fechaDeContestacionDeDemanda){
		this.fechaDeContestacionDeDemanda = fechaDeContestacionDeDemanda;
	}
		
	public Date getFechaDeDemandaDeReconvencion(){
		return this.fechaDeDemandaDeReconvencion;
	}
	
	public void setFechaDeDemandaDeReconvencion(Date fechaDeDemandaDeReconvencion){
		this.fechaDeDemandaDeReconvencion = fechaDeDemandaDeReconvencion;
	}
		
	public Date getFechaDeLlamamientoEnGarantia(){
		return this.fechaDeLlamamientoEnGarantia;
	}
	
	public void setFechaDeLlamamientoEnGarantia(Date fechaDeLlamamientoEnGarantia){
		this.fechaDeLlamamientoEnGarantia = fechaDeLlamamientoEnGarantia;
	}
		
	public Date getFechaDeTrasladoAExcepcionesDeMerito(){
		return this.fechaDeTrasladoAExcepcionesDeMerito;
	}
	
	public void setFechaDeTrasladoAExcepcionesDeMerito(Date fechaDeTrasladoAExcepcionesDeMerito){
		this.fechaDeTrasladoAExcepcionesDeMerito = fechaDeTrasladoAExcepcionesDeMerito;
	}
		
	public Date getFechaDeAudienciaDeConciliacion(){
		return this.fechaDeAudienciaDeConciliacion;
	}
	
	public void setFechaDeAudienciaDeConciliacion(Date fechaDeAudienciaDeConciliacion){
		this.fechaDeAudienciaDeConciliacion = fechaDeAudienciaDeConciliacion;
	}
		
	public Date getFechaDeFijacionDeGastosYHonorarios(){
		return this.fechaDeFijacionDeGastosYHonorarios;
	}
	
	public void setFechaDeFijacionDeGastosYHonorarios(Date fechaDeFijacionDeGastosYHonorarios){
		this.fechaDeFijacionDeGastosYHonorarios = fechaDeFijacionDeGastosYHonorarios;
	}
		
	public Date getFechaDeAudienciaPrimeraDeTramite(){
		return this.fechaDeAudienciaPrimeraDeTramite;
	}
	
	public void setFechaDeAudienciaPrimeraDeTramite(Date fechaDeAudienciaPrimeraDeTramite){
		this.fechaDeAudienciaPrimeraDeTramite = fechaDeAudienciaPrimeraDeTramite;
	}
		
	public Date getFechaDeAudienciasDePruebas(){
		return this.fechaDeAudienciasDePruebas;
	}
	
	public void setFechaDeAudienciasDePruebas(Date fechaDeAudienciasDePruebas){
		this.fechaDeAudienciasDePruebas = fechaDeAudienciasDePruebas;
	}
		
	public Date getFechaDeCierreDeEtapaAprobatoria(){
		return this.fechaDeCierreDeEtapaAprobatoria;
	}
	
	public void setFechaDeCierreDeEtapaAprobatoria(Date fechaDeCierreDeEtapaAprobatoria){
		this.fechaDeCierreDeEtapaAprobatoria = fechaDeCierreDeEtapaAprobatoria;
	}
		
	public Date getFechaDeAudienciasDeAlegatos(){
		return this.fechaDeAudienciasDeAlegatos;
	}
	
	public void setFechaDeAudienciasDeAlegatos(Date fechaDeAudienciasDeAlegatos){
		this.fechaDeAudienciasDeAlegatos = fechaDeAudienciasDeAlegatos;
	}
		
	public Date getFechaDeAudienciaDeLaudo(){
		return this.fechaDeAudienciaDeLaudo;
	}
	
	public void setFechaDeAudienciaDeLaudo(Date fechaDeAudienciaDeLaudo){
		this.fechaDeAudienciaDeLaudo = fechaDeAudienciaDeLaudo;
	}
		
	public Date getFAudAclaracionesComplementacionesCorreccionesLaudo(){
		return this.fAudAclaracionesComplementacionesCorreccionesLaudo;
	}
	
	public void setFAudAclaracionesComplementacionesCorreccionesLaudo(Date fAudAclaracionesComplementacionesCorreccionesLaudo){
		this.fAudAclaracionesComplementacionesCorreccionesLaudo = fAudAclaracionesComplementacionesCorreccionesLaudo;
	}
		
	public Date getFechaDeTramiteRecursoAnulacion(){
		return this.fechaDeTramiteRecursoAnulacion;
	}
	
	public void setFechaDeTramiteRecursoAnulacion(Date fechaDeTramiteRecursoAnulacion){
		this.fechaDeTramiteRecursoAnulacion = fechaDeTramiteRecursoAnulacion;
	}
		
	public Date getFechaTribunalConsignaAlCac(){
		return this.fechaTribunalConsignaAlCac;
	}
	
	public void setFechaTribunalConsignaAlCac(Date fechaTribunalConsignaAlCac){
		this.fechaTribunalConsignaAlCac = fechaTribunalConsignaAlCac;
	}
		
	public Date getFechaDeCierre(){
		return this.fechaDeCierre;
	}
	
	public void setFechaDeCierre(Date fechaDeCierre){
		this.fechaDeCierre = fechaDeCierre;
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
		

    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_FECHAS_CASO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idCaso);        
        hash = 37 * hash + Objects.hashCode(this.fechaDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeDesignacionDeArbitros);
        hash = 37 * hash + Objects.hashCode(this.fechaDeInstalacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAdmisionDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeNotificacionDelDemandado);
        hash = 37 * hash + Objects.hashCode(this.fechaDeContestacionDeDemanda);
        hash = 37 * hash + Objects.hashCode(this.fechaDeDemandaDeReconvencion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeLlamamientoEnGarantia);
        hash = 37 * hash + Objects.hashCode(this.fechaDeTrasladoAExcepcionesDeMerito);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaDeConciliacion);
        hash = 37 * hash + Objects.hashCode(this.fechaDeFijacionDeGastosYHonorarios);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaPrimeraDeTramite);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciasDePruebas);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCierreDeEtapaAprobatoria);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciasDeAlegatos);
        hash = 37 * hash + Objects.hashCode(this.fechaDeAudienciaDeLaudo);
        hash = 37 * hash + Objects.hashCode(this.fAudAclaracionesComplementacionesCorreccionesLaudo);
        hash = 37 * hash + Objects.hashCode(this.fechaDeTramiteRecursoAnulacion);
        hash = 37 * hash + Objects.hashCode(this.fechaTribunalConsignaAlCac);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCierre);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FechasCaso que se pasa
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
        final FechasCaso other = (FechasCaso) obj;
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDemanda, other.fechaDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDesignacionDeArbitros, other.fechaDeDesignacionDeArbitros)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeInstalacion, other.fechaDeInstalacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAdmisionDeDemanda, other.fechaDeAdmisionDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeNotificacionDelDemandado, other.fechaDeNotificacionDelDemandado)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeContestacionDeDemanda, other.fechaDeContestacionDeDemanda)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeDemandaDeReconvencion, other.fechaDeDemandaDeReconvencion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeLlamamientoEnGarantia, other.fechaDeLlamamientoEnGarantia)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeTrasladoAExcepcionesDeMerito, other.fechaDeTrasladoAExcepcionesDeMerito)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaDeConciliacion, other.fechaDeAudienciaDeConciliacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeFijacionDeGastosYHonorarios, other.fechaDeFijacionDeGastosYHonorarios)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaPrimeraDeTramite, other.fechaDeAudienciaPrimeraDeTramite)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciasDePruebas, other.fechaDeAudienciasDePruebas)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCierreDeEtapaAprobatoria, other.fechaDeCierreDeEtapaAprobatoria)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciasDeAlegatos, other.fechaDeAudienciasDeAlegatos)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeAudienciaDeLaudo, other.fechaDeAudienciaDeLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fAudAclaracionesComplementacionesCorreccionesLaudo, other.fAudAclaracionesComplementacionesCorreccionesLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeTramiteRecursoAnulacion, other.fechaDeTramiteRecursoAnulacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaTribunalConsignaAlCac, other.fechaTribunalConsignaAlCac)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCierre, other.fechaDeCierre)) {
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
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

