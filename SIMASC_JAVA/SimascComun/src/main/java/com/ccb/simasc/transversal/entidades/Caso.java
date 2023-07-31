package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CASO")
@NamedQuery(name = "Caso.findAll", query = "SELECT p FROM Caso p")
public class Caso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Se utiliza para realizar el sorteo
	 */
	@Transient
	private NombramientoPersona nombramientoSorteo;
	
	/**
	 * Se almacena la informacion de parametros de sorteo
	 * para el servicio del caso
	 */
	@Transient
	private ParametroServicioSorteo parametroServSorteo;
	
	/**
	 * Indica si para el sorteo del caso se 
	 * realizo liberacion de lista
	 */
	@Transient
	private boolean liberoLista;
	
	/**
	 * Si el sorteo para el caso libero lista
	 * indica la hora de liberacion
	 */
	@Transient
	private Date horaLiberacion;
	
	/**
	 * Listado de personas participantes del sorteo para el caso
	 */
	@Transient
	private List<Persona> participantesSorteo;
	
	/**
	 * 
	 */
	@Transient
	private String textoTipoCuantia;	


	/**
	 * Listado de personas participantes del sorteo a 
	 * las cuales se les realizo liberacion de lista
	 */
	@Transient
	private List<Persona> participantesSorteoLiberacion;
	
	/**
	 * valida si el valor de las pretenciones es numerico
	 * para mostrarlo en formato moneda
	 */
	@Transient
	private String valorPretencionesValidacion;


	@OneToMany(mappedBy="caso", cascade={CascadeType.MERGE})
    private List<Documento> documentoList;


	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CASO_PK = "idCaso";
	public static final String ENTIDAD_CASO_NOMBRE = "nombre";
	public static final String ENTIDAD_CASO_FECHA_RADICACION = "fechaRadicacion";
	public static final String ENTIDAD_CASO_ESTADO_CASO = "estadoCaso";
	public static final String ENTIDAD_CASO_FECHA_ESTADO = "fechaEstado";
	public static final String ENTIDAD_CASO_ETAPA = "etapa";
	public static final String ENTIDAD_CASO_AMPARO_DE_POBREZA = "amparoDePobreza";
	public static final String ENTIDAD_CASO_PENDIENTE_DE_PAGO = "pendienteDePago";
	public static final String ENTIDAD_CASO_TIPO_CUANTIA = "tipoCuantia";
	public static final String ENTIDAD_CASO_CANT_FUNCIONARIOS_PRINCIPALES = "cantFuncionariosPrincipales";
	public static final String ENTIDAD_CASO_VALOR_PRETENSIONES = "valorPretensiones";
	public static final String ENTIDAD_CASO_TIPO_RADICACION = "tipoRadicacion";
	public static final String ENTIDAD_CASO_PRESELECCION = "preseleccion";
	public static final String ENTIDAD_CASO_ID_SERVICIO = "idServicio";
	public static final String ENTIDAD_CASO_ID_MATERIA = "idMateria";
	public static final String ENTIDAD_CASO_TIPO_PACTO = "tipoPacto";
	public static final String ENTIDAD_CASO_DESCRIPCION_PACTO = "descripcionPacto";
	public static final String ENTIDAD_CASO_DOCUMENTO_PACTO = "documentoPacto";
	public static final String ENTIDAD_CASO_TIPO_FALLO = "tipoFallo";
	public static final String ENTIDAD_CASO_TERMINOS_DE_PROCESO = "terminosDeProceso";
	public static final String ENTIDAD_CASO_COMPETENCIA_CCB = "competenciaCcb";
	public static final String ENTIDAD_CASO_TIPO_COMPETENCIA_CCB = "tipoCompetenciaCcb";
	public static final String ENTIDAD_CASO_MOTIVO_DE_NO_COMPETENCIA = "motivoDeNoCompetencia";
	public static final String ENTIDAD_CASO_HECHOS = "hechos";
	public static final String ENTIDAD_CASO_PRETENSIONES = "pretensiones";
	public static final String ENTIDAD_CASO_TIPO_TRAMITE = "tipoTramite";
	public static final String ENTIDAD_CASO_DOCUMENTOS_NO_RECIBIDOS = "documentosNoRecibidos";
	public static final String ENTIDAD_CASO_DURACION = "duracion";
	public static final String ENTIDAD_CASO_RESULTADO = "resultado";
	public static final String ENTIDAD_CASO_ID_CASO_ANTERIOR = "idCasoAnterior";
	public static final String ENTIDAD_CASO_MONEDA = "moneda";
	public static final String ENTIDAD_CASO_DIAS_PARA_PROFERIR_LAUDO = "diasParaProferirLaudo";
	public static final String ENTIDAD_CASO_INICIO_DE_CONFLICTO = "inicioDeConflicto";
	public static final String ENTIDAD_CASO_PARTE_QUE_SOLICITA_SERVICIO = "parteQueSolicitaServicio";
	public static final String ENTIDAD_CASO_OBSERVACIONES = "observaciones";
	public static final String ENTIDAD_CASO_PAGADO = "pagado";
	public static final String ENTIDAD_CASO_MOTIVO_CIERRE = "motivoCierre";
	public static final String ENTIDAD_CASO_FECHA_DE_COBRO = "fechaDeCobro";
	public static final String ENTIDAD_CASO_VALOR_DEVUELTO_AL_CLIENTE = "valorDevueltoAlCliente";
	public static final String ENTIDAD_CASO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_CASO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_CASO_ESTADO_REGISTRO_CASO = "estadoRegistroCaso";			
	public static final String ENTIDAD_CASO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_CASO_ID_SEDE = "idSede";
	public static final String ENTIDAD_CASO_ID_CONVENIO = "idConvenio";
	public static final String ENTIDAD_CASO_ID_AREA_ASUNTO_CLASIFICACION = "idAreaAsuntoClasificacion";
	public static final String ENTIDAD_CASO_ID_LUGAR_DEL_CONFLICTO = "idLugarDelConflicto";
	public static final String ENTIDAD_CASO_ID_SOLICITUD_SERVICIO = "idSolicitudServicio";
	public static final String ENTIDAD_CASO_PERIODICIDAD_TERMINOS = "periodicidadTerminos";
	public static final String ENTIDAD_CASO_TIPO_PERIODICIDAD_TERMINOS = "tipoPeriodicidadTerminos";
	public static final String ENTIDAD_CASO_IDIOMA = "idioma";
	public static final String ENTIDAD_CASO_LEY_APLICABLE = "leyAplicable";
	public static final String ENTIDAD_CASO_TIPO_PRESELECCION = "tipoPreseleccion";
	public static final String ENTIDAD_CASO_QUIEN_PRESELECCIONA = "quienPreselecciona";
	public static final String ENTIDAD_CASO_TIPO_PERITAJE = "tipoPeritaje";
	public static final String ENTIDAD_CASO_RESTAURA_ESTADO_SORTEABILIDAD = "restauraEstadoSorteabilidad";
	
    private static final String[] ATRIBUTOS_ENTIDAD_CASO
            = {ENTIDAD_CASO_PENDIENTE_DE_PAGO, ENTIDAD_CASO_MONEDA, ENTIDAD_CASO_ID_LUGAR_DEL_CONFLICTO, ENTIDAD_CASO_NOMBRE, ENTIDAD_CASO_TIPO_PACTO, ENTIDAD_CASO_RESULTADO, ENTIDAD_CASO_ID_AREA_ASUNTO_CLASIFICACION, ENTIDAD_CASO_DOCUMENTOS_NO_RECIBIDOS, ENTIDAD_CASO_OBSERVACIONES, ENTIDAD_CASO_PK, ENTIDAD_CASO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_CASO_ID_CASO_ANTERIOR, ENTIDAD_CASO_ESTADO_REGISTRO, ENTIDAD_CASO_MOTIVO_DE_NO_COMPETENCIA, ENTIDAD_CASO_FECHA_ESTADO, ENTIDAD_CASO_TIPO_CUANTIA, ENTIDAD_CASO_DESCRIPCION_PACTO, ENTIDAD_CASO_TIPO_COMPETENCIA_CCB, ENTIDAD_CASO_DIAS_PARA_PROFERIR_LAUDO, ENTIDAD_CASO_VALOR_PRETENSIONES, ENTIDAD_CASO_ID_MATERIA, ENTIDAD_CASO_DOCUMENTO_PACTO, ENTIDAD_CASO_FECHA_DE_COBRO, ENTIDAD_CASO_TIPO_RADICACION, ENTIDAD_CASO_VALOR_DEVUELTO_AL_CLIENTE, ENTIDAD_CASO_HECHOS, ENTIDAD_CASO_PAGADO, ENTIDAD_CASO_ID_SERVICIO, ENTIDAD_CASO_ID_SEDE, ENTIDAD_CASO_TIPO_FALLO, ENTIDAD_CASO_ID_CONVENIO, ENTIDAD_CASO_ETAPA, ENTIDAD_CASO_TERMINOS_DE_PROCESO, ENTIDAD_CASO_COMPETENCIA_CCB, ENTIDAD_CASO_ID_SOLICITUD_SERVICIO, ENTIDAD_CASO_INICIO_DE_CONFLICTO, ENTIDAD_CASO_PARTE_QUE_SOLICITA_SERVICIO, ENTIDAD_CASO_FECHA_RADICACION, ENTIDAD_CASO_PRETENSIONES, ENTIDAD_CASO_DURACION, ENTIDAD_CASO_PERIODICIDAD_TERMINOS, ENTIDAD_CASO_AMPARO_DE_POBREZA, ENTIDAD_CASO_ESTADO_CASO, ENTIDAD_CASO_MOTIVO_CIERRE, ENTIDAD_CASO_PRESELECCION, ENTIDAD_CASO_CANT_FUNCIONARIOS_PRINCIPALES, ENTIDAD_CASO_TIPO_PERIODICIDAD_TERMINOS, ENTIDAD_CASO_TIPO_TRAMITE, ENTIDAD_CASO_ID_USUARIO_MODIFICACION,ENTIDAD_CASO_IDIOMA,ENTIDAD_CASO_LEY_APLICABLE,ENTIDAD_CASO_QUIEN_PRESELECCIONA,ENTIDAD_CASO_TIPO_PERITAJE,ENTIDAD_CASO_RESTAURA_ESTADO_SORTEABILIDAD};

	@Id
    @Column(name="id_caso")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idCaso;
    
	@Column(name="nombre")
	private String nombre;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_radicacion")
	private Date fechaRadicacion;		
    
	@Column(name="estado_caso")
	private String estadoCaso;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_estado")
	private Date fechaEstado;		
    
	@Column(name="etapa")
	private String etapa;		
    
	@Column(name="amparo_de_pobreza")
	private boolean amparoDePobreza;		
    
	@Column(name="pendiente_de_pago")
	private boolean pendienteDePago;		
    
	@Column(name="tipo_cuantia")
	private String tipoCuantia;		
    
	@Column(name="cant_funcionarios_principales")
	private Integer cantFuncionariosPrincipales;		
    
	@Column(name="valor_pretensiones")
	private String valorPretensiones;		
    
	@Column(name="tipo_radicacion")
	private String tipoRadicacion;		
    
	@Column(name="preseleccion")
	private boolean preseleccion;		
    
	@Column(name="id_servicio")
	private Long idServicio;		
    
	@Column(name="id_materia")
	private Long idMateria;		
    
	@Column(name="tipo_pacto")
	private String tipoPacto;		
    
	@Column(name="descripcion_pacto")
	private String descripcionPacto;		
    
	@Column(name="documento_pacto")
	private String documentoPacto;		
    
	@Column(name="tipo_fallo")
	private String tipoFallo;		
    
	@Column(name="terminos_de_proceso")
	private String terminosDeProceso;		
    
	@Column(name="competencia_ccb")
	private boolean competenciaCcb;		
    
	@Column(name="tipo_competencia_ccb")
	private String tipoCompetenciaCcb;		
    
	@Column(name="motivo_de_no_competencia")
	private String motivoDeNoCompetencia;		
    
	@Column(name="hechos")
	private String hechos;		
    
	@Column(name="pretensiones")
	private String pretensiones;		
    
	@Column(name="tipo_tramite")
	private String tipoTramite;		
    
	@Column(name="documentos_no_recibidos")
	private String documentosNoRecibidos;		
    
	@Column(name="duracion")
	private String duracion;		
    
	@Column(name="resultado")
	private String resultado;		
    
	@Column(name="id_caso_anterior")
	private Long idCasoAnterior;		
    
	@Column(name="moneda")
	private String moneda;		
    
	@Column(name="dias_para_proferir_laudo")
	private Integer diasParaProferirLaudo;		
    
	@Column(name="inicio_de_conflicto")
	private String inicioDeConflicto;		
    
	@Column(name="parte_que_solicita_servicio")
	private String parteQueSolicitaServicio;		
    
	@Column(name="observaciones")
	private String observaciones;		
    
	@Column(name="pagado")
	private boolean pagado;		
    
	@Column(name="motivo_cierre")
	private String motivoCierre;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_cobro")
	private Date fechaDeCobro;		
    
	@Column(name="valor_devuelto_al_cliente")
	private BigDecimal valorDevueltoAlCliente;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_sede")
	private Long idSede;		
    
	@Column(name="id_convenio")
	private Long idConvenio;		
    
	@Column(name="id_area_asunto_clasificacion")
	private Long idAreaAsuntoClasificacion;		
    
	@Column(name="id_lugar_del_conflicto")
	private String idLugarDelConflicto;		
    
	@Column(name="id_solicitud_servicio")
	private Long idSolicitudServicio;		
    
	@Column(name="periodicidad_terminos")
	private String periodicidadTerminos;		
    
	@Column(name="tipo_periodicidad_terminos")
	private String tipoPeriodicidadTerminos;	
	
	@Column(name="idioma")
	private String idioma;
	
	@Column(name="ley_aplicable")
	private String leyAplicable;
	
	@Column(name="tipo_preseleccion")
	private String tipoPreseleccion;
	
	@Column(name="quien_preselecciona")
	private String quienPreselecciona;

	@Column(name="tipo_peritaje")
	private String tipoPeritaje;
	
	@Column(name="medidas_cautelares")
	private boolean medidasCautelares;
	
	@Column(name="pago_mediacion")
	private BigDecimal pagoMediacion;
	
	@Column(name = "restaura_estado_sorteabilidad")
	private boolean restauraEstadoSorteabilidad;
	
	@Column(name="arbitraje_consumo")
	private boolean arbitrajeConsumo;
	
	@Column(name="concede_amparo")
	private boolean concedeAmparo;
	
	@Column(name="tipo_conflicto")
	private String tipoConflicto;
	
	@Column(name="entero_servicio")
	private String enteroServicio;
	
	@Column(name="reglas_procedimiento")
	private String reglasProcedimiento;

	@ManyToOne
	@JoinColumn(name="id_area_asunto_clasificacion", referencedColumnName="id_area_asunto_clasificacion", insertable = false, updatable = false)
    private AreaAsuntoClasificacion areaAsuntoClasificacion;
		
	@ManyToOne
	@JoinColumn(name="id_convenio", referencedColumnName="id_convenio", insertable = false, updatable = false)
    private Convenio convenio;
		
	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "id_servicio", referencedColumnName="id_servicio", insertable = false, updatable = false),
	    @JoinColumn(name = "id_materia", referencedColumnName="id_materia", insertable = false, updatable = false)	    
	})		
    private ServicioMateria servicioMateria;
		
	@ManyToOne
	@JoinColumn(name="id_solicitud_servicio", referencedColumnName="id_solicitud_servicio", insertable = false, updatable = false)
    private SolicitudServicio solicitudServicio;
		
	@ManyToOne
	@JoinColumn(name="id_lugar_del_conflicto", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="caso")
    private List<Agendamiento> agendamientoList;
	@OneToMany(mappedBy="caso")
    private List<Audiencia> audienciaList;
	@OneToMany(mappedBy="caso")
    private List<Carpeta> carpetaList;
	@OneToMany(mappedBy="caso")
    private List<ContestacionDemanda> contestacionDemandaList;
	@OneToMany(mappedBy="caso")
    private List<Evento> eventoList;
	@OneToMany(mappedBy="caso")
    private List<FacturacionCaso> facturacionCasoList;
	@OneToMany(mappedBy="caso")
    private List<FechaCaso> fechaCasoList;
	@OneToMany(mappedBy="caso")
    private List<GastoTribunal> gastoTribunalList;
	@OneToMany(mappedBy="caso")
    private List<HistoricoExpediente> historicoExpedienteList;	
	@OneToMany(mappedBy="caso")
    private List<HonorariosFijados> honorariosFijadosList;
	@OneToMany(mappedBy="caso")
    private List<Laudo> laudoList;
	@OneToMany(mappedBy="caso")
    private List<NombramientoPersona> nombramientoPersonaList;
	@OneToMany(mappedBy="caso")
    private List<PagoCaso> pagoCasoList;
	@OneToMany(mappedBy="caso")
    private List<PagoHonorarios> pagoHonorariosList;
	@OneToMany(mappedBy="caso")
    private List<Peticion> peticionList;
	@OneToMany(mappedBy="caso")
    private List<Preseleccionado> preseleccionadoList;
	@OneToMany(mappedBy="caso")
    private List<Reliquidacion> reliquidacionList;
	@OneToMany(mappedBy="caso")
    private List<RolPersonaCaso> rolPersonaCasoList;
	@OneToMany(mappedBy="caso")
    private List<SolicitudProrroga> solicitudProrrogaList;
	@OneToMany(mappedBy="caso")
    private List<Sorteo> sorteoList;
	@OneToMany(mappedBy="caso")
    private List<Suspension> suspensionList;
	
	
    public Caso(){
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
	
	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	public Date getFechaRadicacion(){
		return this.fechaRadicacion;
	}
	
	public void setFechaRadicacion(Date fechaRadicacion){
		this.fechaRadicacion = fechaRadicacion;
	}
		
	public String getEstadoCaso(){
		return this.estadoCaso;
	}
	
	public void setEstadoCaso(String estadoCaso){
		this.estadoCaso = estadoCaso;
	}
		
	public Date getFechaEstado(){
		return this.fechaEstado;
	}
	
	public void setFechaEstado(Date fechaEstado){
		this.fechaEstado = fechaEstado;
	}
		
	public String getEtapa(){
		return this.etapa;
	}
	
	public void setEtapa(String etapa){
		this.etapa = etapa;
	}
		
	public boolean getAmparoDePobreza(){
		return this.amparoDePobreza;
	}
	
	public void setAmparoDePobreza(boolean amparoDePobreza){
		this.amparoDePobreza = amparoDePobreza;
	}
		
	public boolean getPendienteDePago(){
		return this.pendienteDePago;
	}
	
	public void setPendienteDePago(boolean pendienteDePago){
		this.pendienteDePago = pendienteDePago;
	}
		
	public String getTipoCuantia(){
		return this.tipoCuantia;
	}
	
	public void setTipoCuantia(String tipoCuantia){
		this.tipoCuantia = tipoCuantia;
	}
		
	public Integer getCantFuncionariosPrincipales(){
		return this.cantFuncionariosPrincipales;
	}
	
	public void setCantFuncionariosPrincipales(Integer cantFuncionariosPrincipales){
		this.cantFuncionariosPrincipales = cantFuncionariosPrincipales;
	}
		
	public String getValorPretensiones(){
		return this.valorPretensiones;
	}
	
	public void setValorPretensiones(String valorPretensiones){
		this.valorPretensiones = valorPretensiones;
	}
		
	public String getTipoRadicacion(){
		return this.tipoRadicacion;
	}
	
	public void setTipoRadicacion(String tipoRadicacion){
		this.tipoRadicacion = tipoRadicacion;
	}
		
	public boolean getPreseleccion(){
		return this.preseleccion;
	}
	
	public void setPreseleccion(boolean preseleccion){
		this.preseleccion = preseleccion;
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
		
	public String getTipoPacto(){
		return this.tipoPacto;
	}
	
	public void setTipoPacto(String tipoPacto){
		this.tipoPacto = tipoPacto;
	}
		
	public String getDescripcionPacto(){
		return this.descripcionPacto;
	}
	
	public void setDescripcionPacto(String descripcionPacto){
		this.descripcionPacto = descripcionPacto;
	}
		
	public String getDocumentoPacto(){
		return this.documentoPacto;
	}
	
	public void setDocumentoPacto(String documentoPacto){
		this.documentoPacto = documentoPacto;
	}
		
	public String getTipoFallo(){
		return this.tipoFallo;
	}
	
	public void setTipoFallo(String tipoFallo){
		this.tipoFallo = tipoFallo;
	}
		
	public String getTerminosDeProceso(){
		return this.terminosDeProceso;
	}
	
	public void setTerminosDeProceso(String terminosDeProceso){
		this.terminosDeProceso = terminosDeProceso;
	}
		
	public boolean getCompetenciaCcb(){
		return this.competenciaCcb;
	}
	
	public void setCompetenciaCcb(boolean competenciaCcb){
		this.competenciaCcb = competenciaCcb;
	}
		
	public String getTipoCompetenciaCcb(){
		return this.tipoCompetenciaCcb;
	}
	
	public void setTipoCompetenciaCcb(String tipoCompetenciaCcb){
		this.tipoCompetenciaCcb = tipoCompetenciaCcb;
	}
		
	public String getMotivoDeNoCompetencia(){
		return this.motivoDeNoCompetencia;
	}
	
	public void setMotivoDeNoCompetencia(String motivoDeNoCompetencia){
		this.motivoDeNoCompetencia = motivoDeNoCompetencia;
	}
		
	public String getHechos(){
		return this.hechos;
	}
	
	public void setHechos(String hechos){
		this.hechos = hechos;
	}
		
	public String getPretensiones(){
		return this.pretensiones;
	}
	
	public void setPretensiones(String pretensiones){
		this.pretensiones = pretensiones;
	}
		
	public String getTipoTramite(){
		return this.tipoTramite;
	}
	
	public void setTipoTramite(String tipoTramite){
		this.tipoTramite = tipoTramite;
	}
		
	public String getDocumentosNoRecibidos(){
		return this.documentosNoRecibidos;
	}
	
	public void setDocumentosNoRecibidos(String documentosNoRecibidos){
		this.documentosNoRecibidos = documentosNoRecibidos;
	}
		
	public String getDuracion(){
		return this.duracion;
	}
	
	public void setDuracion(String duracion){
		this.duracion = duracion;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
		
	public Long getIdCasoAnterior(){
		return this.idCasoAnterior;
	}
	
	public void setIdCasoAnterior(Long idCasoAnterior){
		this.idCasoAnterior = idCasoAnterior;
	}
		
	public String getMoneda(){
		return this.moneda;
	}
	
	public void setMoneda(String moneda){
		this.moneda = moneda;
	}
		
	public Integer getDiasParaProferirLaudo(){
		return this.diasParaProferirLaudo;
	}
	
	public void setDiasParaProferirLaudo(Integer diasParaProferirLaudo){
		this.diasParaProferirLaudo = diasParaProferirLaudo;
	}
		
	public String getInicioDeConflicto(){
		return this.inicioDeConflicto;
	}
	
	public void setInicioDeConflicto(String inicioDeConflicto){
		this.inicioDeConflicto = inicioDeConflicto;
	}
		
	public String getParteQueSolicitaServicio(){
		return this.parteQueSolicitaServicio;
	}
	
	public void setParteQueSolicitaServicio(String parteQueSolicitaServicio){
		this.parteQueSolicitaServicio = parteQueSolicitaServicio;
	}
		
	public String getObservaciones(){
		return this.observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
		
	public boolean getPagado(){
		return this.pagado;
	}
	
	public void setPagado(boolean pagado){
		this.pagado = pagado;
	}
		
	public String getMotivoCierre(){
		return this.motivoCierre;
	}
	
	public void setMotivoCierre(String motivoCierre){
		this.motivoCierre = motivoCierre;
	}
		
	public Date getFechaDeCobro(){
		return this.fechaDeCobro;
	}
	
	public void setFechaDeCobro(Date fechaDeCobro){
		this.fechaDeCobro = fechaDeCobro;
	}
		
	public BigDecimal getValorDevueltoAlCliente(){
		return this.valorDevueltoAlCliente;
	}
	
	public void setValorDevueltoAlCliente(BigDecimal valorDevueltoAlCliente){
		this.valorDevueltoAlCliente = valorDevueltoAlCliente;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		
	public Long getIdConvenio(){
		return this.idConvenio;
	}
	
	public void setIdConvenio(Long idConvenio){
		this.idConvenio = idConvenio;
	}
		
	public Long getIdAreaAsuntoClasificacion(){
		return this.idAreaAsuntoClasificacion;
	}
	
	public void setIdAreaAsuntoClasificacion(Long idAreaAsuntoClasificacion){
		this.idAreaAsuntoClasificacion = idAreaAsuntoClasificacion;
	}
		
	public String getIdLugarDelConflicto(){
		return this.idLugarDelConflicto;
	}
	
	public void setIdLugarDelConflicto(String idLugarDelConflicto){
		this.idLugarDelConflicto = idLugarDelConflicto;
	}
		
	public Long getIdSolicitudServicio(){
		return this.idSolicitudServicio;
	}
	
	public void setIdSolicitudServicio(Long idSolicitudServicio){
		this.idSolicitudServicio = idSolicitudServicio;
	}
	
	public String getPeriodicidadTerminos(){
		return this.periodicidadTerminos;
	}
	
	public void setPeriodicidadTerminos(String periodicidadTerminos){
		this.periodicidadTerminos = periodicidadTerminos;
	}
		
	public String getTipoPeriodicidadTerminos(){
		return this.tipoPeriodicidadTerminos;
	}
	
	public void setTipoPeriodicidadTerminos(String tipoPeriodicidadTerminos){
		this.tipoPeriodicidadTerminos = tipoPeriodicidadTerminos;
	}		

    public List<Agendamiento> getAgendamientoList(){
		return this.agendamientoList;
	}
	
	public void setAgendamientoList(List<Agendamiento> agendamientoList){
		this.agendamientoList = agendamientoList;
	}
			
    public List<Audiencia> getAudienciaList(){
		return this.audienciaList;
	}
	
	public void setAudienciaList(List<Audiencia> audienciaList){
		this.audienciaList = audienciaList;
	}
			
    public List<Carpeta> getCarpetaList(){
		return this.carpetaList;
	}
	
	public void setCarpetaList(List<Carpeta> carpetaList){
		this.carpetaList = carpetaList;
	}
			
    public List<ContestacionDemanda> getContestacionDemandaList(){
		return this.contestacionDemandaList;
	}
	
	public void setContestacionDemandaList(List<ContestacionDemanda> contestacionDemandaList){
		this.contestacionDemandaList = contestacionDemandaList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}
			
    public List<Evento> getEventoList(){
		return this.eventoList;
	}
	
	public void setEventoList(List<Evento> eventoList){
		this.eventoList = eventoList;
	}
			
    public List<FacturacionCaso> getFacturacionCasoList(){
		return this.facturacionCasoList;
	}
	
	public void setFacturacionCasoList(List<FacturacionCaso> facturacionCasoList){
		this.facturacionCasoList = facturacionCasoList;
	}
			
    public List<FechaCaso> getFechaCasoList(){
		return this.fechaCasoList;
	}
	
	public void setFechaCasoList(List<FechaCaso> fechaCasoList){
		this.fechaCasoList = fechaCasoList;
	}
			
    public List<GastoTribunal> getGastoTribunalList(){
		return this.gastoTribunalList;
	}
	
	public void setGastoTribunalList(List<GastoTribunal> gastoTribunalList){
		this.gastoTribunalList = gastoTribunalList;
	}
	
    public List<HistoricoExpediente> getHistoricoExpedienteList(){
		return this.historicoExpedienteList;
	}
	
	public void setHistoricoExpedienteList(List<HistoricoExpediente> historicoExpedienteList){
		this.historicoExpedienteList = historicoExpedienteList;
	}		
			
    public List<HonorariosFijados> getHonorariosFijadosList(){
		return this.honorariosFijadosList;
	}
	
	public void setHonorariosFijadosList(List<HonorariosFijados> honorariosFijadosList){
		this.honorariosFijadosList = honorariosFijadosList;
	}
			
    public List<Laudo> getLaudoList(){
		return this.laudoList;
	}
	
	public void setLaudoList(List<Laudo> laudoList){
		this.laudoList = laudoList;
	}
			
    public List<NombramientoPersona> getNombramientoPersonaList(){
		return this.nombramientoPersonaList;
	}
	
	public void setNombramientoPersonaList(List<NombramientoPersona> nombramientoPersonaList){
		this.nombramientoPersonaList = nombramientoPersonaList;
	}
			
    public List<PagoCaso> getPagoCasoList(){
		return this.pagoCasoList;
	}
	
	public void setPagoCasoList(List<PagoCaso> pagoCasoList){
		this.pagoCasoList = pagoCasoList;
	}
			
    public List<PagoHonorarios> getPagoHonorariosList(){
		return this.pagoHonorariosList;
	}
	
	public void setPagoHonorariosList(List<PagoHonorarios> pagoHonorariosList){
		this.pagoHonorariosList = pagoHonorariosList;
	}
	
    public List<Peticion> getPeticionList(){
		return this.peticionList;
	}
	
	public void setPeticionList(List<Peticion> peticionList){
		this.peticionList = peticionList;
	}
			
    public List<Preseleccionado> getPreseleccionadoList(){
		return this.preseleccionadoList;
	}
	
	public void setPreseleccionadoList(List<Preseleccionado> preseleccionadoList){
		this.preseleccionadoList = preseleccionadoList;
	}
			
    public List<Reliquidacion> getReliquidacionList(){
		return this.reliquidacionList;
	}
	
	public void setReliquidacionList(List<Reliquidacion> reliquidacionList){
		this.reliquidacionList = reliquidacionList;
	}
			
    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
			
    public List<SolicitudProrroga> getSolicitudProrrogaList(){
		return this.solicitudProrrogaList;
	}
	
	public void setSolicitudProrrogaList(List<SolicitudProrroga> solicitudProrrogaList){
		this.solicitudProrrogaList = solicitudProrrogaList;
	}
			
    public List<Sorteo> getSorteoList(){
		return this.sorteoList;
	}
	
	public void setSorteoList(List<Sorteo> sorteoList){
		this.sorteoList = sorteoList;
	}
			
    public List<Suspension> getSuspensionList(){
		return this.suspensionList;
	}
	
	public void setSuspensionList(List<Suspension> suspensionList){
		this.suspensionList = suspensionList;
	}
			
    public AreaAsuntoClasificacion getAreaAsuntoClasificacion(){
		return this.areaAsuntoClasificacion; 
	}
	
	public void setAreaAsuntoClasificacion(AreaAsuntoClasificacion areaAsuntoClasificacion){
		this.areaAsuntoClasificacion = areaAsuntoClasificacion;
	}
    public Convenio getConvenio(){
		return this.convenio; 
	}
	
	public void setConvenio(Convenio convenio){
		this.convenio = convenio;
	}
    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}
    public ServicioMateria getServicioMateria(){
		return this.servicioMateria; 
	}
	
	public void setServicioMateria(ServicioMateria servicioMateria){
		this.servicioMateria = servicioMateria;
	}
    public SolicitudServicio getSolicitudServicio(){
		return this.solicitudServicio; 
	}
	
	public void setSolicitudServicio(SolicitudServicio solicitudServicio){
		this.solicitudServicio = solicitudServicio;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}
	

	public boolean isMedidasCautelares() {
		return medidasCautelares;
	}


	public void setMedidasCautelares(boolean medidasCautelares) {
		this.medidasCautelares = medidasCautelares;
	}


	public BigDecimal getPagoMediacion() {
		return pagoMediacion;
	}


	public void setPagoMediacion(BigDecimal pagoMediacion) {
		this.pagoMediacion = pagoMediacion;
	}
	
	public boolean isArbitrajeConsumo() {
		return arbitrajeConsumo;
	}
	
	public String getReglasProcedimiento() {
		return reglasProcedimiento;
	}

	public void setReglasProcedimiento(String reglasProcedimiento) {
		this.reglasProcedimiento = reglasProcedimiento;
	}

	public void setArbitrajeConsumo(boolean arbitrajeConsumo) {
		this.arbitrajeConsumo = arbitrajeConsumo;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CASO) {
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
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.fechaRadicacion);
        hash = 37 * hash + Objects.hashCode(this.estadoCaso);
        hash = 37 * hash + Objects.hashCode(this.fechaEstado);
        hash = 37 * hash + Objects.hashCode(this.etapa);
        hash = 37 * hash + (this.amparoDePobreza ? 0 : 1);
        hash = 37 * hash + (this.pendienteDePago ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoCuantia);
        hash = 37 * hash + Objects.hashCode(this.cantFuncionariosPrincipales);
        hash = 37 * hash + Objects.hashCode(this.valorPretensiones);
        hash = 37 * hash + Objects.hashCode(this.tipoRadicacion);
        hash = 37 * hash + (this.preseleccion ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idServicio);
        hash = 37 * hash + Objects.hashCode(this.idMateria);
        hash = 37 * hash + Objects.hashCode(this.tipoPacto);
        hash = 37 * hash + Objects.hashCode(this.descripcionPacto);
        hash = 37 * hash + Objects.hashCode(this.documentoPacto);
        hash = 37 * hash + Objects.hashCode(this.tipoFallo);
        hash = 37 * hash + Objects.hashCode(this.terminosDeProceso);
        hash = 37 * hash + (this.competenciaCcb ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.tipoCompetenciaCcb);
        hash = 37 * hash + Objects.hashCode(this.motivoDeNoCompetencia);
        hash = 37 * hash + Objects.hashCode(this.hechos);
        hash = 37 * hash + Objects.hashCode(this.pretensiones);
        hash = 37 * hash + Objects.hashCode(this.tipoTramite);
        hash = 37 * hash + Objects.hashCode(this.documentosNoRecibidos);
        hash = 37 * hash + Objects.hashCode(this.duracion);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.idCasoAnterior);
        hash = 37 * hash + Objects.hashCode(this.moneda);
        hash = 37 * hash + Objects.hashCode(this.diasParaProferirLaudo);
        hash = 37 * hash + Objects.hashCode(this.inicioDeConflicto);
        hash = 37 * hash + Objects.hashCode(this.parteQueSolicitaServicio);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + (this.pagado ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.motivoCierre);
        hash = 37 * hash + Objects.hashCode(this.fechaDeCobro);
        hash = 37 * hash + Objects.hashCode(this.valorDevueltoAlCliente);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        hash = 37 * hash + Objects.hashCode(this.idConvenio);
        hash = 37 * hash + Objects.hashCode(this.idAreaAsuntoClasificacion);
        hash = 37 * hash + Objects.hashCode(this.idLugarDelConflicto);
        hash = 37 * hash + Objects.hashCode(this.idSolicitudServicio);
        hash = 37 * hash + Objects.hashCode(this.periodicidadTerminos);
        hash = 37 * hash + Objects.hashCode(this.tipoPeriodicidadTerminos);
        hash = 37 * hash + Objects.hashCode(this.leyAplicable);
        hash = 37 * hash + Objects.hashCode(this.idioma);
        hash = 37 * hash + Objects.hashCode(this.tipoPreseleccion);
        hash = 37 * hash + Objects.hashCode(this.quienPreselecciona);
        hash = 37 * hash + Objects.hashCode(this.tipoPeritaje);
        hash = 37 * hash + Objects.hashCode(this.restauraEstadoSorteabilidad);
        hash = 37 * hash + Objects.hashCode(this.arbitrajeConsumo);  
        hash = 37 * hash + Objects.hashCode(this.concedeAmparo);
        hash = 37 * hash + Objects.hashCode(this.tipoConflicto);
        hash = 37 * hash + Objects.hashCode(this.enteroServicio);
        hash = 37 * hash + Objects.hashCode(this.reglasProcedimiento); 
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Caso que se pasa
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
        final Caso other = (Caso) obj;
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaRadicacion, other.fechaRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoCaso, other.estadoCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaEstado, other.fechaEstado)) {
            return false;
        }
        
        if (!Objects.equals(this.etapa, other.etapa)) {
            return false;
        }
        
        if (!Objects.equals(this.amparoDePobreza, other.amparoDePobreza)) {
            return false;
        }
        
        if (!Objects.equals(this.pendienteDePago, other.pendienteDePago)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCuantia, other.tipoCuantia)) {
            return false;
        }
        
        if (!Objects.equals(this.cantFuncionariosPrincipales, other.cantFuncionariosPrincipales)) {
            return false;
        }
        
        if (!Objects.equals(this.valorPretensiones, other.valorPretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoRadicacion, other.tipoRadicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.preseleccion, other.preseleccion)) {
            return false;
        }
        
        if (!Objects.equals(this.idServicio, other.idServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPacto, other.tipoPacto)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcionPacto, other.descripcionPacto)) {
            return false;
        }
        
        if (!Objects.equals(this.documentoPacto, other.documentoPacto)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoFallo, other.tipoFallo)) {
            return false;
        }
        
        if (!Objects.equals(this.terminosDeProceso, other.terminosDeProceso)) {
            return false;
        }
        
        if (!Objects.equals(this.competenciaCcb, other.competenciaCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCompetenciaCcb, other.tipoCompetenciaCcb)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoDeNoCompetencia, other.motivoDeNoCompetencia)) {
            return false;
        }
        
        if (!Objects.equals(this.hechos, other.hechos)) {
            return false;
        }
        
        if (!Objects.equals(this.pretensiones, other.pretensiones)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTramite, other.tipoTramite)) {
            return false;
        }
        
        if (!Objects.equals(this.documentosNoRecibidos, other.documentosNoRecibidos)) {
            return false;
        }
        
        if (!Objects.equals(this.duracion, other.duracion)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCasoAnterior, other.idCasoAnterior)) {
            return false;
        }
        
        if (!Objects.equals(this.moneda, other.moneda)) {
            return false;
        }
        
        if (!Objects.equals(this.diasParaProferirLaudo, other.diasParaProferirLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.inicioDeConflicto, other.inicioDeConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.parteQueSolicitaServicio, other.parteQueSolicitaServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        
        if (!Objects.equals(this.pagado, other.pagado)) {
            return false;
        }
        
        if (!Objects.equals(this.motivoCierre, other.motivoCierre)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeCobro, other.fechaDeCobro)) {
            return false;
        }
        
        if (!Objects.equals(this.valorDevueltoAlCliente, other.valorDevueltoAlCliente)) {
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
        
        if (!Objects.equals(this.idSede, other.idSede)) {
            return false;
        }
        
        if (!Objects.equals(this.idConvenio, other.idConvenio)) {
            return false;
        }
        
        if (!Objects.equals(this.idAreaAsuntoClasificacion, other.idAreaAsuntoClasificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.idLugarDelConflicto, other.idLugarDelConflicto)) {
            return false;
        }
        
        if (!Objects.equals(this.idSolicitudServicio, other.idSolicitudServicio)) {
            return false;
        }
        
        if (!Objects.equals(this.periodicidadTerminos, other.periodicidadTerminos)) {
        	return false;
        }
        
        if (!Objects.equals(this.idioma, other.idioma)) {
        	return false;
        }
        
        if (!Objects.equals(this.leyAplicable, other.leyAplicable)) {
        	return false;
        }
        if (!Objects.equals(this.tipoPreseleccion, other.tipoPreseleccion)) {
        	return false;
        }
        if (!Objects.equals(this.quienPreselecciona, other.quienPreselecciona)) {
        	return false;
        }
        if (!Objects.equals(this.tipoPeritaje, other.tipoPeritaje)) {
            return false;
        }
        if (!Objects.equals(this.restauraEstadoSorteabilidad, other.restauraEstadoSorteabilidad)) {
            return false;
        }
        if (!Objects.equals(this.arbitrajeConsumo, other.arbitrajeConsumo)) {
            return false;
        }
        if (!Objects.equals(this.concedeAmparo, other.concedeAmparo)) {
            return false;
        }
        if (!Objects.equals(this.tipoConflicto, other.tipoConflicto)) {
            return false;
        }
        if (!Objects.equals(this.enteroServicio, other.enteroServicio)) {
            return false;
        }
        if (!Objects.equals(this.reglasProcedimiento, other.reglasProcedimiento)) {
            return false;
        }
        
        return Objects.equals(this.tipoPeriodicidadTerminos, other.tipoPeriodicidadTerminos);              
                   
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	/**
	 * Devuelve verdadero si el caso se encuentra en estado cancelado
	 * 
	 * @return
	 */
	public boolean enEstadoCancelado() {
		boolean cancelado = false;
		if (this.estadoCaso.equals(UtilDominios.ESTADO_CASO_CANCELADO)) {
			cancelado = true;
		}

		return cancelado;
	}

	/**
	 * Devuelve verdadero si el caso se encuentra en estado sorteado
	 * 
	 * @return
	 */
	public boolean enEstadoSorteado() {
		boolean sorteado = false;
		if (this.estadoCaso.equals(UtilDominios.ESTADO_CASO_SORTEADO)) {
			sorteado = true;
		}

		return sorteado;
	}

	/**
	 * Devuelve el sorteo del caso que tenga el sorteoId de lo contrario
	 * devuelve null.
	 * 
	 * @param sorteoId
	 * @return Un sorteo o null
	 */
	public Sorteo getSorteo(Long sorteoId) {
		Sorteo busqueda = null;
		for (Sorteo sorteo : this.getSorteoList()) {
			if (sorteo.getIdSorteo().equals(sorteoId)) {
				busqueda = sorteo;
				break;
			}
		}
		return busqueda;
	}

	/**
	 * Devuelve la audiencia del dia que se pasa como parametro y que está en
	 * estado pendiente de ejecución.
	 * 
	 * @param fecha
	 * @return Una audiencia o null
	 */
	public Audiencia getAudienciaSorteoDelDia(Date fecha) {
		Audiencia busqueda = null;
		for (Audiencia audiencia : this.getAudienciaList()) {
			if (audiencia.getTipoAudiencia().equals(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION)
					&& UtilOperaciones.validarFechasDelMismoDia(audiencia.getHoraInicio(), fecha)) {
				busqueda = audiencia;
			}
		}

		return busqueda;

	}

	/**
	 * Devuelve la sorteo del dia que se pasa como parametro.
	 * 
	 * @param fecha
	 * @return Una sorteo o null
	 */
	public Sorteo getSorteoDelDia(Date fecha) {
		Sorteo busqueda = null;
		for (Sorteo sorteo : this.getSorteoList()) {
			if (UtilOperaciones.validarFechasDelMismoDia(sorteo.getFechaRealizacion(), fecha)) {
				busqueda = sorteo;
			}
		}

		return busqueda;

	}
	
	/**
	 * Calcula el tipo de cuantia a partir valor calculado cuantia (valor pretensiones / salario minimo) y
	 * el limite de cuantia para clasificarla como A o B1
	 * @param salarioMinimoLegal
	 * @return Si el tipo de cuantia es A o B
	 */
	public String getTipoCuantia(BigDecimal salarioMinimoLegal){
		BigDecimal valorCuantia = getValorCuantia(salarioMinimoLegal);
		return valorCuantia.compareTo(UtilConstantes.SEPARADOR_TIPO_CUANTIA) >= 0 ? UtilConstantes.TIPO_CUANTIA_A : UtilConstantes.TIPO_CUANTIA_B;
	}
	
	/**
	 * Calcula el valor de la cuantia a partir del valor de las pretensiones y del salario mínimo con la formula:
	 * valorCuantia = valorPretensiones/salarioMinimo
	 * @param salarioMinimoLegal
	 * @return
	 */
	public BigDecimal getValorCuantia(BigDecimal salarioMinimoLegal){
		//return new BigDecimal(this.getValorPretensiones()).divide(salarioMinimoLegal,2,RoundingMode.HALF_UP);
		return new BigDecimal("0");
	}
	
	/**
	 * Devuelve el pago inicial del caso
	 * @return
	 */
	public PagoCaso obtenerPagoInicial(){
		
		PagoCaso pagoInicial = null;
		
		if (this.getPagoCasoList() != null) {
			for (PagoCaso pago : this.getPagoCasoList()) {
				if (pagoInicial == null || pagoInicial.getFechaPago().after(pago.getFechaPago())) {
					pagoInicial = pago;
				}
			}
		}

		return pagoInicial;
	}
	
	/**
	 * @return the nombramientoSorteo
	 */
	public NombramientoPersona getNombramientoSorteo() {
		return nombramientoSorteo;
	}
	
	/**
	 * @param nombramientoSorteo the nombramientoSorteo to set
	 */
	public void setNombramientoSorteo(NombramientoPersona nombramientoSorteo) {
		this.nombramientoSorteo = nombramientoSorteo;
	}
	
	/**
	 * 
	 * @return
	 */
	public ParametroServicioSorteo getParametroServSorteo() {
		return parametroServSorteo;
	}

	/**
	 * 
	 * @param parametroServSorteo
	 */
	public void setParametroServSorteo(ParametroServicioSorteo parametroServSorteo) {
		this.parametroServSorteo = parametroServSorteo;
	}
	
	/**
	 * @return the liberoLista
	 */
	public boolean isLiberoLista() {
		return liberoLista;
	}


	/**
	 * @param liberoLista the liberoLista to set
	 */
	public void setLiberoLista(boolean liberoLista) {
		this.liberoLista = liberoLista;
	}
	

	/**
	 * @return the horaLiberacion
	 */
	public Date getHoraLiberacion() {
		return horaLiberacion;
	}


	/**
	 * @param horaLiberacion the horaLiberacion to set
	 */
	public void setHoraLiberacion(Date horaLiberacion) {
		this.horaLiberacion = horaLiberacion;
	}
	
	/**
	 * @return the participantesSorteo
	 */
	public List<Persona> getParticipantesSorteo() {
		return participantesSorteo;
	}


	/**
	 * @param participantesSorteo the participantesSorteo to set
	 */
	public void setParticipantesSorteo(List<Persona> participantesSorteo) {
		this.participantesSorteo = participantesSorteo;
	}
	
	/**
	 * @return the participantesSorteoLiberacion
	 */
	public List<Persona> getParticipantesSorteoLiberacion() {
		return participantesSorteoLiberacion;
	}


	/**
	 * @param participantesSorteoLiberacion the participantesSorteoLiberacion to set
	 */
	public void setParticipantesSorteoLiberacion(List<Persona> participantesSorteoLiberacion) {
		this.participantesSorteoLiberacion = participantesSorteoLiberacion;
	}


	/**
	 * esta modificando la varibale de clase.OJO
	 * @return the textoTipoCuantia
	 */
	public String getTextoTipoCuantia() {
		this.tipoCuantia = this.tipoCuantia != null ? 
				this.tipoCuantia : UtilConstantes.TIPO_CUANTIA_INDERTERMINADA;
		
		switch (this.tipoCuantia) {
		case UtilDominios.TIPO_CUANTIA_MAYOR:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_MAYOR;
			break;
		case UtilDominios.TIPO_CUANTIA_MENOR:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_MENOR;
			break;
		case UtilDominios.TIPO_CUANTIA_INDETERMINADO:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_INDERTERMINADA;
			break;
		case UtilDominios.TIPO_CUANTIA_CON_CUANTIA:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_CON_CUANTIA;
			break;
		case UtilDominios.TIPO_CUANTIA_INDETERMINADO_ABREVIADO:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_INDETERMINADO_ABREVIADO;
			break;
		default:
			textoTipoCuantia = UtilConstantes.TIPO_CUANTIA_INDERTERMINADA;
			break;
		}
		return textoTipoCuantia;
	}


	/**
	 * @param textoTipoCuantia the textoTipoCuantia to set
	 */
	public void setTextoTipoCuantia(String textoTipoCuantia) {
		this.textoTipoCuantia = textoTipoCuantia;
	}
	
	public String getParticipanteCaso(String rol){
		String participantes="";
		for(RolPersonaCaso rpc:rolPersonaCasoList){
			if(rpc.getRol().getNombre().equals(rol))
				participantes += rpc.getPersona().getNombreCompleto() + ", ";
		}
		return participantes.length()>0?participantes.substring(0, participantes.length()-2):"";
	}
	
	
	public String getValorPretencionesValidacion() {
		if (this.valorPretensiones == null) {
			this.valorPretensiones = "";
		}
		return this.valorPretensiones;
	}


	public void setValorPretencionesValidacion(String valorPretencionesValidacion) {
		this.valorPretencionesValidacion = valorPretencionesValidacion;
	}


	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}


	public String getLeyAplicable() {
		return leyAplicable;
	}


	public void setLeyAplicable(String leyAplicable) {
		this.leyAplicable = leyAplicable;
	}
	
	public String getTipoPreseleccion() {
		return tipoPreseleccion;
	}



	public void setTipoPreseleccion(String tipoPreseleccion) {
		this.tipoPreseleccion = tipoPreseleccion;
	}


	public String getQuienPreselecciona() {
		return quienPreselecciona;
	}


	public void setQuienPreselecciona(String quienPreselecciona) {
		this.quienPreselecciona = quienPreselecciona;
	}


	public String getTipoPeritaje() {
		return tipoPeritaje;
	}


	public void setTipoPeritaje(String tipoPeritaje) {
		this.tipoPeritaje = tipoPeritaje;
	}


	public boolean isRestauraEstadoSorteabilidad() {
		return restauraEstadoSorteabilidad;
	}


	public void setRestauraEstadoSorteabilidad(boolean restauraEstadoSorteabilidad) {
		this.restauraEstadoSorteabilidad = restauraEstadoSorteabilidad;
	}


	public boolean isConcedeAmparo() {
		return concedeAmparo;
	}


	public void setConcedeAmparo(boolean concedeAmparo) {
		this.concedeAmparo = concedeAmparo;
	}


	public String getTipoConflicto() {
		return tipoConflicto;
	}


	public void setTipoConflicto(String tipoConflicto) {
		this.tipoConflicto = tipoConflicto;
	}


	public String getEnteroServicio() {
		return enteroServicio;
	}


	public void setEnteroServicio(String enteroServicio) {
		this.enteroServicio = enteroServicio;
	}
	
	
	
	
	// protected region metodos adicionales end

} 

