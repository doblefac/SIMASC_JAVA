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
import javax.persistence.Transient;

import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="PERSONA")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@Transient
	private String nombreCompleto;

	/**
	 * Indica somo fue nombrado en un sorteo:
	 * principal
	 * suplente
	 */
	@Transient
	private String nombradoComo;
	
	/**
	 * Indica la posicion de como
	 * fue nombrado para arbitro
	 */
	@Transient
	private int nombradoComoPos;		

	/**
	 * Indica que no puede hacer parte del sorteo a realizar
	 */
	@Transient
	private boolean marcaAzulSorteo;
	
	/**
	 * Indica que numero aleatorio se le fue asignado al momento de realizar
	 * el sorteo
	 */
	@Transient
	private Long aleatorioAsignado;
		

	@Transient
	private Long idPersonaServicioMateria;
	
	@Transient
	private List<Funcionalidad> funcionalidadesList;	

	@OneToMany(mappedBy="personaQueRecibe")
    private List<CorreoRolPersonaCaso> correosRecibidosList;
	
	@OneToMany(mappedBy="persona")
    private List<CorreoRolPersonaCaso> correosEnviadosList;
	
	//Define las personas que fueron seleccionados por la persona
	@OneToMany(mappedBy="tercero")
    private List<RolPersonaCaso> rolPersonaCasoSeleccionadosList;
	
	@ManyToOne
	@JoinColumn(name = "id_persona_juridica", referencedColumnName = "id_persona", insertable = false, updatable = false)
	private Persona personaJuridica;

	@OneToMany(mappedBy = "personaJuridica")
	private List<Persona> personasNaturalesList;
	
	@OneToMany(mappedBy="persona")
    private List<Documento> documentosPersonalesList;
	
	@OneToMany(mappedBy="persona")
    private List<Asesoria> asesoriaSolicitadaList;
	
	@OneToMany(mappedBy="personaAsesora")
    private List<Asesoria> asesoriaPrestadaList;
	
	@OneToMany(mappedBy="persona")
	private List<PersonaLote> personaLoteList;
	
	@OneToMany(mappedBy="persona")
	private List<LoteGenerado> loteGeneradoList;
	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PERSONA_PK = "idPersona";
	public static final String ENTIDAD_PERSONA_TIPO_PERSONA = "tipoPersona";
	public static final String ENTIDAD_PERSONA_TIPO_DOCUMENTO = "tipoDocumento";
	public static final String ENTIDAD_PERSONA_NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String ENTIDAD_PERSONA_PRIMER_NOMBRE_O_RAZON_SOCIAL = "primerNombreORazonSocial";
	public static final String ENTIDAD_PERSONA_SEGUNDO_NOMBRE = "segundoNombre";
	public static final String ENTIDAD_PERSONA_PRIMER_APELLIDO = "primerApellido";
	public static final String ENTIDAD_PERSONA_SEGUNDO_APELLIDO = "segundoApellido";
	public static final String ENTIDAD_PERSONA_TIPO_FUNCIONARIO = "tipoFuncionario";
	public static final String ENTIDAD_PERSONA_NUMERO_TARJETA_PROFESIONAL = "numeroTarjetaProfesional";
	public static final String ENTIDAD_PERSONA_CIUDAD_DEL_DOCUMENTO = "ciudadDelDocumento";
	public static final String ENTIDAD_PERSONA_FECHA_DE_NACIMIENTO = "fechaDeNacimiento";
	public static final String ENTIDAD_PERSONA_ESCOLARIDAD = "escolaridad";
	public static final String ENTIDAD_PERSONA_ESTRATO = "estrato";
	public static final String ENTIDAD_PERSONA_SEXO = "sexo";
	public static final String ENTIDAD_PERSONA_INSTITUCION_EDUCATIVA = "institucionEducativa";
	public static final String ENTIDAD_PERSONA_FECHA_DE_GRADO = "fechaDeGrado";
	public static final String ENTIDAD_PERSONA_ENTIDAD_EXPIDE_TARJETA_PROFESIONAL = "entidadExpideTarjetaProfesional";
	public static final String ENTIDAD_PERSONA_TIPO_DE_EMPRESA = "tipoDeEmpresa";
	public static final String ENTIDAD_PERSONA_TIPO_DE_ENTIDAD_PUBLICA = "tipoDeEntidadPublica";
	public static final String ENTIDAD_PERSONA_REPRESENTANTE_LEGAL = "representanteLegal";
	public static final String ENTIDAD_PERSONA_SECTOR_DE_LA_EMPRESA = "sectorDeLaEmpresa";
	public static final String ENTIDAD_PERSONA_PAGINA_WEB = "paginaWeb";
	public static final String ENTIDAD_PERSONA_PREFERENCIAS_DE_REFRIGERIO = "preferenciasDeRefrigerio";
	public static final String ENTIDAD_PERSONA_RESUMEN_HOJA_VIDA = "resumenHojaVida";
	public static final String ENTIDAD_PERSONA_AUTORIZA_PUBLICACION_DATOS = "autorizaPublicacionDatos";
	public static final String ENTIDAD_PERSONA_ESTADO_PERSONA = "estadoPersona";
	public static final String ENTIDAD_PERSONA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_PERSONA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_PERSONA_ESTADO_REGISTRO_PERSONA = "estadoRegistroPersona";			
	public static final String ENTIDAD_PERSONA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_PERSONA_ID_PROFESION = "idProfesion";
	public static final String ENTIDAD_PERSONA_ID_PAIS_ORIGEN = "idPaisOrigen";
	public static final String ENTIDAD_PERSONA_ID_PERSONA_JURIDICA = "idPersonaJuridica";
	public static final String ENTIDAD_PERSONA_REGISTRO_CONCILIADOR = "registroConciliador";
	public static final String ENTIDAD_PERSONA_ID_POLITICA = "idPolitica";
    private static final String[] ATRIBUTOS_ENTIDAD_PERSONA
            = {ENTIDAD_PERSONA_NUMERO_DOCUMENTO, ENTIDAD_PERSONA_SEGUNDO_NOMBRE, ENTIDAD_PERSONA_ID_PROFESION, ENTIDAD_PERSONA_RESUMEN_HOJA_VIDA, ENTIDAD_PERSONA_ID_PERSONA_JURIDICA, ENTIDAD_PERSONA_AUTORIZA_PUBLICACION_DATOS, ENTIDAD_PERSONA_ID_PAIS_ORIGEN, ENTIDAD_PERSONA_TIPO_PERSONA, ENTIDAD_PERSONA_FECHA_DE_NACIMIENTO, ENTIDAD_PERSONA_ESTADO_REGISTRO, ENTIDAD_PERSONA_PRIMER_NOMBRE_O_RAZON_SOCIAL, ENTIDAD_PERSONA_TIPO_DE_EMPRESA, ENTIDAD_PERSONA_NUMERO_TARJETA_PROFESIONAL, ENTIDAD_PERSONA_ESCOLARIDAD, ENTIDAD_PERSONA_TIPO_DOCUMENTO, ENTIDAD_PERSONA_CIUDAD_DEL_DOCUMENTO, ENTIDAD_PERSONA_REGISTRO_CONCILIADOR, ENTIDAD_PERSONA_PRIMER_APELLIDO, ENTIDAD_PERSONA_REPRESENTANTE_LEGAL, ENTIDAD_PERSONA_ESTADO_PERSONA, ENTIDAD_PERSONA_TIPO_DE_ENTIDAD_PUBLICA, ENTIDAD_PERSONA_INSTITUCION_EDUCATIVA, ENTIDAD_PERSONA_SEGUNDO_APELLIDO, ENTIDAD_PERSONA_ESTRATO, ENTIDAD_PERSONA_PAGINA_WEB, ENTIDAD_PERSONA_PK, ENTIDAD_PERSONA_SECTOR_DE_LA_EMPRESA, ENTIDAD_PERSONA_ID_USUARIO_MODIFICACION, ENTIDAD_PERSONA_SEXO, ENTIDAD_PERSONA_PREFERENCIAS_DE_REFRIGERIO, ENTIDAD_PERSONA_TIPO_FUNCIONARIO, ENTIDAD_PERSONA_FECHA_DE_GRADO, ENTIDAD_PERSONA_ENTIDAD_EXPIDE_TARJETA_PROFESIONAL, ENTIDAD_PERSONA_FECHA_ULTIMA_MODIFICACION,ENTIDAD_PERSONA_ID_POLITICA};


	@Id
    @Column(name="id_persona")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idPersona;
    
	@Column(name="tipo_persona")
	private String tipoPersona;		
    
	@Column(name="tipo_documento")
	private String tipoDocumento;		
    
	@Column(name="numero_documento")
	private String numeroDocumento;		
    
	@Column(name="primer_nombre_o_razon_social")
	private String primerNombreORazonSocial;		
    
	@Column(name="segundo_nombre")
	private String segundoNombre;		
    
	@Column(name="primer_apellido")
	private String primerApellido;		
    
	@Column(name="segundo_apellido")
	private String segundoApellido;		
    
	@Column(name="tipo_funcionario")
	private String tipoFuncionario;		
    
	@Column(name="numero_tarjeta_profesional")
	private String numeroTarjetaProfesional;		
    
	@Column(name="ciudad_del_documento")
	private String ciudadDelDocumento;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_nacimiento")
	private Date fechaDeNacimiento;		
    
	@Column(name="escolaridad")
	private String escolaridad;		
    
	@Column(name="estrato")
	private String estrato;		
    
	@Column(name="sexo")
	private String sexo;		
    
	@Column(name="institucion_educativa")
	private String institucionEducativa;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_de_grado")
	private Date fechaDeGrado;		
    
	@Column(name="entidad_expide_tarjeta_profesional")
	private String entidadExpideTarjetaProfesional;		
    
	@Column(name="tipo_de_empresa")
	private String tipoDeEmpresa;		
    
	@Column(name="tipo_de_entidad_publica")
	private String tipoDeEntidadPublica;		
    
	@Column(name="representante_legal")
	private String representanteLegal;		
    
	@Column(name="sector_de_la_empresa")
	private String sectorDeLaEmpresa;		
    
	@Column(name="pagina_web")
	private String paginaWeb;		
    
	@Column(name="preferencias_de_refrigerio")
	private String preferenciasDeRefrigerio;		
    
	@Column(name="resumen_hoja_vida")
	private String resumenHojaVida;		
    
	@Column(name="autoriza_publicacion_datos")
	private boolean autorizaPublicacionDatos;		
    
	@Column(name="estado_persona")
	private String estadoPersona;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_profesion")
	private Long idProfesion;		
    
	@Column(name="id_pais_origen")
	private String idPaisOrigen;		
	
	@Column(name="id_politica")
	private String idPolitica;
    
	@Column(name="id_persona_juridica")
	private Long idPersonaJuridica;		
	
	@Column(name="registro_conciliador")
	private String registroConciliador;	
	
	@Column(name="tiene_empleo")
	private String tieneEmpleo;
	
	@Column(name="tipo_empleo")
	private String tipoEmpleo;	
	
	@Column(name="sector_economico")
	private String sectorEconomico;	
	
	@Column(name="estado_civil")
	private String estadoCivil;	
	
	@Column(name="numero_personas_acargo")
	private String numeroPersonasAcargo;	
	
	@Column(name="tiene_sociedad_conyugal")
	private String tieneSociedadConyugal;	
	
	@Column(name="sociedad_conyugal_vigente")
	private String sociedadConyugalVigente;	
	
	@Column(name="ocupacion")
	private String ocupacion;	
	
	@Column(name="actividad_economica")
	private String actividadEconomica;	
	
	@Column(name="nombre_negocio")
	private String nombreNegocio;	
	
	@Column(name="nit_empresa")
	private String nitEmpresa;	
	
	@Column(name="nombre_empresa")
	private String nombreEmpresa;	
	
	@Column(name="otra_actividad_economica")
	private String otraActividadEconomica;	
	
	@Column(name="otra_nombre_negocio")
	private String otraNombreNegocio;
	
	@ManyToOne
	@JoinColumn(name="id_persona_juridica", referencedColumnName="id_persona", insertable = false, updatable = false)
    private Persona persona;
		
	@ManyToOne
	@JoinColumn(name="id_profesion", referencedColumnName="id_profesion", insertable = false, updatable = false)
    private Profesion profesion;
		
	@ManyToOne
	@JoinColumn(name="id_pais_origen", referencedColumnName="id_zona_geografica", insertable = false, updatable = false)
    private ZonaGeografica zonaGeografica;
		
	@OneToMany(mappedBy="persona")
    private List<AgendaPersona> agendaPersonaList;	
	@OneToMany(mappedBy="persona")
    private List<Asesoria> asesoradoList;
	@OneToMany(mappedBy="personaAsesora")
    private List<Asesoria> asesoradorList;
	@OneToMany(mappedBy="persona")
    private List<CartaPersona> cartaPersonaList;
	@OneToMany(mappedBy="persona")
    private List<Convenio> convenioList;
	@OneToMany(mappedBy="persona")
    private List<CorreoElectronico> correoElectronicoList;	
	@OneToMany(mappedBy="persona")
    private List<CorreoRolPersonaCaso> correoRolPersonaCasoList;
	@OneToMany(mappedBy="persona")
    private List<DesarrolloProfesional> desarrolloProfesionalList;
	@OneToMany(mappedBy="persona")
    private List<Documento> documentoList;
	@OneToMany(mappedBy="persona")
    private List<Elegible> elegibleList;
	@OneToMany(mappedBy="persona")
    private List<EntregaDocumento> entregaDocumentoList;
	@OneToMany(mappedBy="persona")
    private List<EstadoPersonaTipoServicio> estadoPersonaTipoServicioList;
	@OneToMany(mappedBy="persona")
    private List<EvaluacionConciliador> evaluacionConciliadorList;
	@OneToMany(mappedBy="persona")
    private List<HistoricoExpediente> historicoExpedienteList;
	@OneToMany(mappedBy="persona")
    private List<Idioma> idiomaList;
	@OneToMany(mappedBy="persona")
    private List<Membresia> membresiaList;
	@OneToMany(mappedBy="persona")
    private List<NombramientoPersona> nombramientoPersonaList;
	@OneToMany(mappedBy="persona")
    private List<Notificacion> notificacionList;
	@OneToMany(mappedBy="persona")
    private List<Persona> personaList;
	@OneToMany(mappedBy="persona")
    private List<PersonaEventoCcb> personaEventoCcbList;
	@OneToMany(mappedBy="persona")
    private List<PersonaRolAlerta> personaRolAlertaList;
	@OneToMany(mappedBy="persona")
    private List<PersonaSede> personaSedeList;
	@OneToMany(mappedBy="persona")
    private List<PersonaServicioMateria> personaServicioMateriaList;
	@OneToMany(mappedBy="persona")
    private List<PersonaSolicitud> personaSolicitudList;
	@OneToMany(mappedBy="persona")
    private List<Preseleccionado> preseleccionadoList;
	@OneToMany(mappedBy="persona")
    private List<Publicacion> publicacionList;
	@OneToMany(mappedBy="persona")
    private List<RelacionadoConvenio> relacionadoConvenioList;
	@OneToMany(mappedBy="persona")
    private List<RequisitoPersona> requisitoPersonaList;
	@OneToMany(mappedBy="persona")
    private List<RolPersona> rolPersonaList;
	@OneToMany(mappedBy="persona")
    private List<RolPersonaCaso> rolPersonaCasoList;
	@OneToMany(mappedBy="persona")
    private List<Telefono> telefonoList;
	@OneToMany(mappedBy="persona")
    private List<Transcripcion> transcripcionList;
	@OneToMany(mappedBy="persona")
    private List<Ubicacion> ubicacionList;
	@OneToMany(mappedBy="persona")
    private List<Usuario> usuarioList;
	@OneToMany(mappedBy="persona")
    private List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList;
	@OneToMany(mappedBy="persona")
    private List<DisponibilidadPersona> disponibilidadPersonaList;
	
	@Transient
	private PersonaServicioMateria personaServicioMateria;	
	
	@Transient
	private boolean designadoPreviamente;
	
    public Persona(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones
		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdPersona(){
		return this.idPersona;
	}
	
	public void setIdPersona(Long idPersona){
		this.idPersona = idPersona;
	}
	
	public String getTipoPersona(){
		return this.tipoPersona;
	}
	
	public void setTipoPersona(String tipoPersona){
		this.tipoPersona = tipoPersona;
	}
		
	public String getTipoDocumento(){
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
		
	public String getNumeroDocumento(){
		return this.numeroDocumento;
	}
	
	public void setNumeroDocumento(String numeroDocumento){
		this.numeroDocumento = numeroDocumento;
	}
		
	public String getPrimerNombreORazonSocial(){
		return this.primerNombreORazonSocial;
	}
	
	public void setPrimerNombreORazonSocial(String primerNombreORazonSocial){
		this.primerNombreORazonSocial = primerNombreORazonSocial;
	}
		
	public String getSegundoNombre(){
		return this.segundoNombre;
	}
	
	public void setSegundoNombre(String segundoNombre){
		this.segundoNombre = segundoNombre;
	}
		
	public String getPrimerApellido(){
		return this.primerApellido;
	}
	
	public void setPrimerApellido(String primerApellido){
		this.primerApellido = primerApellido;
	}
		
	public String getSegundoApellido(){
		return this.segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido){
		this.segundoApellido = segundoApellido;
	}
		
	public String getTipoFuncionario(){
		return this.tipoFuncionario;
	}
	
	public void setTipoFuncionario(String tipoFuncionario){
		this.tipoFuncionario = tipoFuncionario;
	}
		
	public String getNumeroTarjetaProfesional(){
		return this.numeroTarjetaProfesional;
	}
	
	public void setNumeroTarjetaProfesional(String numeroTarjetaProfesional){
		this.numeroTarjetaProfesional = numeroTarjetaProfesional;
	}
		
	public String getCiudadDelDocumento(){
		return this.ciudadDelDocumento;
	}
	
	public void setCiudadDelDocumento(String ciudadDelDocumento){
		this.ciudadDelDocumento = ciudadDelDocumento;
	}
		
	public Date getFechaDeNacimiento(){
		return this.fechaDeNacimiento;
	}
	
	public void setFechaDeNacimiento(Date fechaDeNacimiento){
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
		
	public String getEscolaridad(){
		return this.escolaridad;
	}
	
	public void setEscolaridad(String escolaridad){
		this.escolaridad = escolaridad;
	}
		
	public String getEstrato(){
		return this.estrato;
	}
	
	public void setEstrato(String estrato){
		this.estrato = estrato;
	}
		
	public String getSexo(){
		return this.sexo;
	}
	
	public void setSexo(String sexo){
		this.sexo = sexo;
	}
		
	public String getInstitucionEducativa(){
		return this.institucionEducativa;
	}
	
	public void setInstitucionEducativa(String institucionEducativa){
		this.institucionEducativa = institucionEducativa;
	}
		
	public Date getFechaDeGrado(){
		return this.fechaDeGrado;
	}
	
	public void setFechaDeGrado(Date fechaDeGrado){
		this.fechaDeGrado = fechaDeGrado;
	}
		
	public String getEntidadExpideTarjetaProfesional(){
		return this.entidadExpideTarjetaProfesional;
	}
	
	public void setEntidadExpideTarjetaProfesional(String entidadExpideTarjetaProfesional){
		this.entidadExpideTarjetaProfesional = entidadExpideTarjetaProfesional;
	}
		
	public String getTipoDeEmpresa(){
		return this.tipoDeEmpresa;
	}
	
	public void setTipoDeEmpresa(String tipoDeEmpresa){
		this.tipoDeEmpresa = tipoDeEmpresa;
	}
		
	public String getTipoDeEntidadPublica(){
		return this.tipoDeEntidadPublica;
	}
	
	public void setTipoDeEntidadPublica(String tipoDeEntidadPublica){
		this.tipoDeEntidadPublica = tipoDeEntidadPublica;
	}
		
	public String getRepresentanteLegal(){
		return this.representanteLegal;
	}
	
	public void setRepresentanteLegal(String representanteLegal){
		this.representanteLegal = representanteLegal;
	}
		
	public String getSectorDeLaEmpresa(){
		return this.sectorDeLaEmpresa;
	}
	
	public void setSectorDeLaEmpresa(String sectorDeLaEmpresa){
		this.sectorDeLaEmpresa = sectorDeLaEmpresa;
	}
		
	public String getPaginaWeb(){
		return this.paginaWeb;
	}
	
	public void setPaginaWeb(String paginaWeb){
		this.paginaWeb = paginaWeb;
	}
		
	public String getPreferenciasDeRefrigerio(){
		return this.preferenciasDeRefrigerio;
	}
	
	public void setPreferenciasDeRefrigerio(String preferenciasDeRefrigerio){
		this.preferenciasDeRefrigerio = preferenciasDeRefrigerio;
	}
		
	public String getResumenHojaVida(){
		return this.resumenHojaVida;
	}
	
	public void setResumenHojaVida(String resumenHojaVida){
		this.resumenHojaVida = resumenHojaVida;
	}
		
	public boolean getAutorizaPublicacionDatos(){
		return this.autorizaPublicacionDatos;
	}
	
	public void setAutorizaPublicacionDatos(boolean autorizaPublicacionDatos){
		this.autorizaPublicacionDatos = autorizaPublicacionDatos;
	}
		
	public String getEstadoPersona(){
		return this.estadoPersona;
	}
	
	public void setEstadoPersona(String estadoPersona){
		this.estadoPersona = estadoPersona;
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
		
	public Long getIdProfesion(){
		return this.idProfesion;
	}
	
	public void setIdProfesion(Long idProfesion){
		this.idProfesion = idProfesion;
	}
		
	public String getIdPaisOrigen(){
		return this.idPaisOrigen;
	}
	
	public void setIdPaisOrigen(String idPaisOrigen){
		this.idPaisOrigen = idPaisOrigen;
	}
		
	public Long getIdPersonaJuridica(){
		return this.idPersonaJuridica;
	}
	
	public void setIdPersonaJuridica(Long idPersonaJuridica){
		this.idPersonaJuridica = idPersonaJuridica;
	}
		

    public List<AgendaPersona> getAgendaPersonaList(){
		return this.agendaPersonaList;
	}
	
	public void setAgendaPersonaList(List<AgendaPersona> agendaPersonaList){
		this.agendaPersonaList = agendaPersonaList;
	}
			
    public List<CartaPersona> getCartaPersonaList(){
		return this.cartaPersonaList;
	}
	
	public void setCartaPersonaList(List<CartaPersona> cartaPersonaList){
		this.cartaPersonaList = cartaPersonaList;
	}
			
    public List<Convenio> getConvenioList(){
		return this.convenioList;
	}
	
	public void setConvenioList(List<Convenio> convenioList){
		this.convenioList = convenioList;
	}
			
    public List<CorreoElectronico> getCorreoElectronicoList(){
		return this.correoElectronicoList;
	}
	
	public void setCorreoElectronicoList(List<CorreoElectronico> correoElectronicoList){
		this.correoElectronicoList = correoElectronicoList;
	}
			
    public List<CorreoRolPersonaCaso> getCorreoRolPersonaCasoList(){
		return this.correoRolPersonaCasoList;
	}
	
	public void setCorreoRolPersonaCasoList(List<CorreoRolPersonaCaso> correoRolPersonaCasoList){
		this.correoRolPersonaCasoList = correoRolPersonaCasoList;
	}		
			
    public List<DesarrolloProfesional> getDesarrolloProfesionalList(){
		return this.desarrolloProfesionalList;
	}
	
	public void setDesarrolloProfesionalList(List<DesarrolloProfesional> desarrolloProfesionalList){
		this.desarrolloProfesionalList = desarrolloProfesionalList;
	}
			
    public List<Documento> getDocumentoList(){
		return this.documentoList;
	}
	
	public void setDocumentoList(List<Documento> documentoList){
		this.documentoList = documentoList;
	}		
			
    public List<Elegible> getElegibleList(){
		return this.elegibleList;
	}
	
	public void setElegibleList(List<Elegible> elegibleList){
		this.elegibleList = elegibleList;
	}
			
    public List<EntregaDocumento> getEntregaDocumentoList(){
		return this.entregaDocumentoList;
	}
	
	public void setEntregaDocumentoList(List<EntregaDocumento> entregaDocumentoList){
		this.entregaDocumentoList = entregaDocumentoList;
	}
			
    public List<EstadoPersonaTipoServicio> getEstadoPersonaTipoServicioList(){
		return this.estadoPersonaTipoServicioList;
	}
	
	public void setEstadoPersonaTipoServicioList(List<EstadoPersonaTipoServicio> estadoPersonaTipoServicioList){
		this.estadoPersonaTipoServicioList = estadoPersonaTipoServicioList;
	}
	
    public List<EvaluacionConciliador> getEvaluacionConciliadorList(){
		return this.evaluacionConciliadorList;
	}
	
	public void setEvaluacionConciliadorList(List<EvaluacionConciliador> evaluacionConciliadorList){
		this.evaluacionConciliadorList = evaluacionConciliadorList;
	}
	
    public List<HistoricoExpediente> getHistoricoExpedienteList(){
		return this.historicoExpedienteList;
	}
	
	public void setHistoricoExpedienteList(List<HistoricoExpediente> historicoExpedienteList){
		this.historicoExpedienteList = historicoExpedienteList;
	}
			
    public List<Idioma> getIdiomaList(){
		return this.idiomaList;
	}
	
	public void setIdiomaList(List<Idioma> idiomaList){
		this.idiomaList = idiomaList;
	}
			
    public List<Membresia> getMembresiaList(){
		return this.membresiaList;
	}
	
	public void setMembresiaList(List<Membresia> membresiaList){
		this.membresiaList = membresiaList;
	}
			
    public List<NombramientoPersona> getNombramientoPersonaList(){
		return this.nombramientoPersonaList;
	}
	
	public void setNombramientoPersonaList(List<NombramientoPersona> nombramientoPersonaList){
		this.nombramientoPersonaList = nombramientoPersonaList;
	}
			
    public List<Notificacion> getNotificacionList(){
		return this.notificacionList;
	}
	
	public void setNotificacionList(List<Notificacion> notificacionList){
		this.notificacionList = notificacionList;
	}
			
    public List<Persona> getPersonaList(){
		return this.personaList;
	}
    
    public List<PersonaEventoCcb> getPersonaEventoCcbList(){
		return this.personaEventoCcbList;
	}
	
	public void setPersonaEventoCcbList(List<PersonaEventoCcb> personaEventoCcbList){
		this.personaEventoCcbList = personaEventoCcbList;
	}
	
	public void setPersonaList(List<Persona> personaList){
		this.personaList = personaList;
	}
			
    public List<PersonaRolAlerta> getPersonaRolAlertaList(){
		return this.personaRolAlertaList;
	}
	
	public void setPersonaRolAlertaList(List<PersonaRolAlerta> personaRolAlertaList){
		this.personaRolAlertaList = personaRolAlertaList;
	}
			
    public List<PersonaSede> getPersonaSedeList(){
		return this.personaSedeList;
	}
	
	public void setPersonaSedeList(List<PersonaSede> personaSedeList){
		this.personaSedeList = personaSedeList;
	}
			
    public List<PersonaServicioMateria> getPersonaServicioMateriaList(){
		return this.personaServicioMateriaList;
	}
	
	public void setPersonaServicioMateriaList(List<PersonaServicioMateria> personaServicioMateriaList){
		this.personaServicioMateriaList = personaServicioMateriaList;
	}
			
    public List<PersonaSolicitud> getPersonaSolicitudList(){
		return this.personaSolicitudList;
	}
	
	public void setPersonaSolicitudList(List<PersonaSolicitud> personaSolicitudList){
		this.personaSolicitudList = personaSolicitudList;
	}
			
    public List<Preseleccionado> getPreseleccionadoList(){
		return this.preseleccionadoList;
	}
	
	public void setPreseleccionadoList(List<Preseleccionado> preseleccionadoList){
		this.preseleccionadoList = preseleccionadoList;
	}
			
    public List<Publicacion> getPublicacionList(){
		return this.publicacionList;
	}
	
	public void setPublicacionList(List<Publicacion> publicacionList){
		this.publicacionList = publicacionList;
	}
			
    public List<RelacionadoConvenio> getRelacionadoConvenioList(){
		return this.relacionadoConvenioList;
	}
	
	public void setRelacionadoConvenioList(List<RelacionadoConvenio> relacionadoConvenioList){
		this.relacionadoConvenioList = relacionadoConvenioList;
	}
			
    public List<RequisitoPersona> getRequisitoPersonaList(){
		return this.requisitoPersonaList;
	}
	
	public void setRequisitoPersonaList(List<RequisitoPersona> requisitoPersonaList){
		this.requisitoPersonaList = requisitoPersonaList;
	}
			
    public List<RolPersona> getRolPersonaList(){
		return this.rolPersonaList;
	}
	
	public void setRolPersonaList(List<RolPersona> rolPersonaList){
		this.rolPersonaList = rolPersonaList;
	}
			
    public List<RolPersonaCaso> getRolPersonaCasoList(){
		return this.rolPersonaCasoList;
	}
	
	public void setRolPersonaCasoList(List<RolPersonaCaso> rolPersonaCasoList){
		this.rolPersonaCasoList = rolPersonaCasoList;
	}
			
    public List<Telefono> getTelefonoList(){
		return this.telefonoList;
	}
	
	public void setTelefonoList(List<Telefono> telefonoList){
		this.telefonoList = telefonoList;
	}
			
    public List<Transcripcion> getTranscripcionList(){
		return this.transcripcionList;
	}
	
	public void setTranscripcionList(List<Transcripcion> transcripcionList){
		this.transcripcionList = transcripcionList;
	}
			
    public List<Ubicacion> getUbicacionList(){
		return this.ubicacionList;
	}
	
	public void setUbicacionList(List<Ubicacion> ubicacionList){
		this.ubicacionList = ubicacionList;
	}
			
    public List<Usuario> getUsuarioList(){
		return this.usuarioList;
	}
	
	public void setUsuarioList(List<Usuario> usuarioList){
		this.usuarioList = usuarioList;
	}
			
    public Persona getPersona(){
		return this.persona; 
	}
	
	public void setPersona(Persona persona){
		this.persona = persona;
	}
    public Profesion getProfesion(){
		return this.profesion; 
	}
	
	public void setProfesion(Profesion profesion){
		this.profesion = profesion;
	}
    public ZonaGeografica getZonaGeografica(){
		return this.zonaGeografica; 
	}
	
	public void setZonaGeografica(ZonaGeografica zonaGeografica){
		this.zonaGeografica = zonaGeografica;
	}
	
	public void setNombreCompleto(String nombreCompleto){
		this.nombreCompleto=nombreCompleto;
	}
	
	/**
	 * @return the asesoriaSolicitadaList
	 */
	public List<Asesoria> getAsesoriaSolicitadaList() {
		return asesoriaSolicitadaList;
	}


	/**
	 * @param asesoriaSolicitadaList the asesoriaSolicitadaList to set
	 */
	public void setAsesoriaSolicitadaList(List<Asesoria> asesoriaSolicitadaList) {
		this.asesoriaSolicitadaList = asesoriaSolicitadaList;
	}


	/**
	 * @return the asesoriaPrestadaList
	 */
	public List<Asesoria> getAsesoriaPrestadaList() {
		return asesoriaPrestadaList;
	}


	/**
	 * @param asesoriaPrestadaList the asesoriaPrestadaList to set
	 */
	public void setAsesoriaPrestadaList(List<Asesoria> asesoriaPrestadaList) {
		this.asesoriaPrestadaList = asesoriaPrestadaList;
	}


	/**
	 * @return the correosEnviadosList
	 */
	public List<CorreoRolPersonaCaso> getCorreosEnviadosList() {
		return correosEnviadosList;
	}


	/**
	 * @param correosEnviadosList the correosEnviadosList to set
	 */
	public void setCorreosEnviadosList(List<CorreoRolPersonaCaso> correosEnviadosList) {
		this.correosEnviadosList = correosEnviadosList;
	}

	public List<Asesoria> getAsesoradoList() {
		return asesoradoList;
	}

	public void setAsesoradoList(List<Asesoria> asesoradoList) {
		this.asesoradoList = asesoradoList;
	}

	public List<Asesoria> getAsesoradorList() {
		return asesoradorList;
	}

	public void setAsesoradorList(List<Asesoria> asesoradorList) {
		this.asesoradorList = asesoradorList;
	}

	public List<DevolucionDocumentoResultado> getDevolucionDocumentoResultadoList() {
		return devolucionDocumentoResultadoList;
	}

	public void setDevolucionDocumentoResultadoList(List<DevolucionDocumentoResultado> devolucionDocumentoResultadoList) {
		this.devolucionDocumentoResultadoList = devolucionDocumentoResultadoList;
	}

	public List<DisponibilidadPersona> getDisponibilidadPersonaList() {
		return disponibilidadPersonaList;
	}

	public void setDisponibilidadPersonaList(List<DisponibilidadPersona> disponibilidadPersonaList) {
		this.disponibilidadPersonaList = disponibilidadPersonaList;
	}


	public List<PersonaLote> getPersonaLoteList() {
		return personaLoteList;
	}


	public void setPersonaLoteList(List<PersonaLote> personaLoteList) {
		this.personaLoteList = personaLoteList;
	}
	
	public boolean isDesignadoPreviamente() {
		return designadoPreviamente;
	}

	public void setDesignadoPreviamente(boolean designadoPreviamente) {
		this.designadoPreviamente = designadoPreviamente;
	}
	
	public String getOtraActividadEconomica() {
		return otraActividadEconomica;
	}

	public void setOtraActividadEconomica(String otraActividadEconomica) {
		this.otraActividadEconomica = otraActividadEconomica;
	}

	public String getOtraNombreNegocio() {
		return otraNombreNegocio;
	}
	
	public void setOtraNombreNegocio(String otraNombreNegocio) {
		this.otraNombreNegocio = otraNombreNegocio;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PERSONA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idPersona);        
        hash = 37 * hash + Objects.hashCode(this.tipoPersona);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumento);
        hash = 37 * hash + Objects.hashCode(this.primerNombreORazonSocial);
        hash = 37 * hash + Objects.hashCode(this.segundoNombre);
        hash = 37 * hash + Objects.hashCode(this.primerApellido);
        hash = 37 * hash + Objects.hashCode(this.segundoApellido);
        hash = 37 * hash + Objects.hashCode(this.tipoFuncionario);
        hash = 37 * hash + Objects.hashCode(this.numeroTarjetaProfesional);
        hash = 37 * hash + Objects.hashCode(this.ciudadDelDocumento);
        hash = 37 * hash + Objects.hashCode(this.fechaDeNacimiento);
        hash = 37 * hash + Objects.hashCode(this.escolaridad);
        hash = 37 * hash + Objects.hashCode(this.estrato);
        hash = 37 * hash + Objects.hashCode(this.sexo);
        hash = 37 * hash + Objects.hashCode(this.institucionEducativa);
        hash = 37 * hash + Objects.hashCode(this.fechaDeGrado);
        hash = 37 * hash + Objects.hashCode(this.entidadExpideTarjetaProfesional);
        hash = 37 * hash + Objects.hashCode(this.tipoDeEmpresa);
        hash = 37 * hash + Objects.hashCode(this.tipoDeEntidadPublica);
        hash = 37 * hash + Objects.hashCode(this.representanteLegal);
        hash = 37 * hash + Objects.hashCode(this.sectorDeLaEmpresa);
        hash = 37 * hash + Objects.hashCode(this.paginaWeb);
        hash = 37 * hash + Objects.hashCode(this.preferenciasDeRefrigerio);
        hash = 37 * hash + Objects.hashCode(this.resumenHojaVida);
        hash = 37 * hash + (this.autorizaPublicacionDatos ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.estadoPersona);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idProfesion);
        hash = 37 * hash + Objects.hashCode(this.idPaisOrigen);
        hash = 37 * hash + Objects.hashCode(this.idPersonaJuridica);
        hash = 37 * hash + Objects.hashCode(this.registroConciliador);
        hash = 37 * hash + Objects.hashCode(this.idPolitica);
        hash = 37 * hash + Objects.hashCode(this.otraActividadEconomica);
        hash = 37 * hash + Objects.hashCode(this.otraNombreNegocio);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Persona que se pasa
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
        final Persona other = (Persona) obj;
        
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoPersona, other.tipoPersona)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumento, other.numeroDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.primerNombreORazonSocial, other.primerNombreORazonSocial)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoNombre, other.segundoNombre)) {
            return false;
        }
        
        if (!Objects.equals(this.primerApellido, other.primerApellido)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoApellido, other.segundoApellido)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoFuncionario, other.tipoFuncionario)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroTarjetaProfesional, other.numeroTarjetaProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudadDelDocumento, other.ciudadDelDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeNacimiento, other.fechaDeNacimiento)) {
            return false;
        }
        
        if (!Objects.equals(this.escolaridad, other.escolaridad)) {
            return false;
        }
        
        if (!Objects.equals(this.estrato, other.estrato)) {
            return false;
        }
        
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        
        if (!Objects.equals(this.institucionEducativa, other.institucionEducativa)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDeGrado, other.fechaDeGrado)) {
            return false;
        }
        
        if (!Objects.equals(this.entidadExpideTarjetaProfesional, other.entidadExpideTarjetaProfesional)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeEmpresa, other.tipoDeEmpresa)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDeEntidadPublica, other.tipoDeEntidadPublica)) {
            return false;
        }
        
        if (!Objects.equals(this.representanteLegal, other.representanteLegal)) {
            return false;
        }
        
        if (!Objects.equals(this.sectorDeLaEmpresa, other.sectorDeLaEmpresa)) {
            return false;
        }
        
        if (!Objects.equals(this.paginaWeb, other.paginaWeb)) {
            return false;
        }
        
        if (!Objects.equals(this.preferenciasDeRefrigerio, other.preferenciasDeRefrigerio)) {
            return false;
        }
        
        if (!Objects.equals(this.resumenHojaVida, other.resumenHojaVida)) {
            return false;
        }
        
        if (!Objects.equals(this.autorizaPublicacionDatos, other.autorizaPublicacionDatos)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoPersona, other.estadoPersona)) {
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
        
        if (!Objects.equals(this.idProfesion, other.idProfesion)) {
            return false;
        }
        
        if (!Objects.equals(this.idPaisOrigen, other.idPaisOrigen)) {
            return false;
        }        
        
        if (!Objects.equals(this.registroConciliador, other.registroConciliador)) {
        	return false;
        }
        if (!Objects.equals(this.idPolitica, other.idPolitica)) {
            return false;
        }
        if (!Objects.equals(this.otraActividadEconomica, other.otraActividadEconomica)) {
            return false;
        }
        if (!Objects.equals(this.otraNombreNegocio, other.otraNombreNegocio)) {
            return false;
        }
        
        return Objects.equals(this.idPersonaJuridica, other.idPersonaJuridica);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
    /**
     * Devuelve el nombre completo: primerNombre + segundoNombre + primerApellido + segundoApellido
     * Omite los valores nulos
     * @return
     */   
    public String getNombreCompleto(){
    	StringBuilder sb = new StringBuilder();
    	if(this.getPrimerNombreORazonSocial()!=null){
    		sb.append(this.getPrimerNombreORazonSocial());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getSegundoNombre()!=null){
    		sb.append(this.getSegundoNombre());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getPrimerApellido()!=null){
    		sb.append(this.getPrimerApellido());
        	sb.append(UtilConstantes.CARACTER_ESPACIO);
    	}
    	
    	if(this.getSegundoApellido()!=null){
    		sb.append(this.getSegundoApellido());
    	}    	
    	
    	return sb.toString();
    }
    
    /**
     * Valida si el usuario esta disponible para sorteo en el servicio-materia
     * Se encuentra disponible si el estado en la asignación al servicio-materia es activo
     * @param servicioMateria
     * @return
     */
	public boolean validarDisponibilidadSorteo(ServicioMateria servicioMateria) {
		PersonaServicioMateria psmTemp = null;
		boolean disponible = false;

		for (PersonaServicioMateria psm : this.getPersonaServicioMateriaList()) {
			if (psm.getServicioMateria().equals(servicioMateria)) {
				psmTemp = psm;
			}
		}
		if (psmTemp != null && psmTemp.getEstadoParaSorteo() != null
				&& psmTemp.getEstadoParaSorteo().equals(UtilDominios.ESTADO_PERSONA_ACTIVO)) {
			disponible = true;
		}

		return disponible;
	}
    
    /**
     * Devuelve la lista asociada al rol arbitro de la persona, si 
     * tienea asignación al rol devuelve null
     * 
     * @return
     */
    public Lista obtenerListaRolArbitro(){
    	Lista listaArbitro = null;
    	if(this.rolPersonaList != null)
	    	for(RolPersona rolPersona : this.rolPersonaList)
	    		if(rolPersona.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_ARBITRO))
	    			listaArbitro = rolPersona.getLista();
    	return listaArbitro;
    }
    
    /**
   	 * @return the marcaAzulSorteo
   	 */
   	public boolean isMarcaAzulSorteo() {
   		return marcaAzulSorteo;
   	}


   	/**
   	 * @param marcaAzulSorteo the marcaAzulSorteo to set
   	 */
   	public void setMarcaAzulSorteo(boolean marcaAzulSorteo) {
   		this.marcaAzulSorteo = marcaAzulSorteo;
   	}
   	
	/**
	 * @return the nombradoComo
	 */
	public String getNombradoComo() {
		return nombradoComo;
	}


	/**
	 * @param nombradoComo the nombradoComo to set
	 */
	public void setNombradoComo(String nombradoComo) {
		this.nombradoComo = nombradoComo;
	}
	
	/**
	 * @return the nombradoComoPos
	 */
	public int getNombradoComoPos() {
		return nombradoComoPos;
	}


	/**
	 * @param nombradoComoPos the nombradoComoPos to set
	 */
	public void setNombradoComoPos(int nombradoComoPos) {
		this.nombradoComoPos = nombradoComoPos;
	}
	
	public Long getAleatorioAsignado() {
		return aleatorioAsignado;
	}


	public void setAleatorioAsignado(Long aleatorioAsignado) {
		this.aleatorioAsignado = aleatorioAsignado;
	}
	
	
	/**
	 * @return the idPersonaServicioMateria
	 */
	public Long getIdPersonaServicioMateria() {
		return idPersonaServicioMateria;
	}


	/**
	 * @param idPersonaServicioMateria the idPersonaServicioMateria to set
	 */
	public void setIdPersonaServicioMateria(Long idPersonaServicioMateria) {
		this.idPersonaServicioMateria = idPersonaServicioMateria;
	}


	public List<CorreoRolPersonaCaso> getCorreosRecibidosList() {
		return correosRecibidosList;
	}


	public void setCorreosRecibidosList(List<CorreoRolPersonaCaso> correosRecibidosList) {
		this.correosRecibidosList = correosRecibidosList;
	}


	public List<RolPersonaCaso> getRolPersonaCasoSeleccionadosList() {
		return rolPersonaCasoSeleccionadosList;
	}


	public void setRolPersonaCasoSeleccionadosList(List<RolPersonaCaso> rolPersonaCasoSeleccionadosList) {
		this.rolPersonaCasoSeleccionadosList = rolPersonaCasoSeleccionadosList;
	}
	
	public List<Funcionalidad> getFuncionalidadesList() {
		return funcionalidadesList;
	}


	public void setFuncionalidadesList(List<Funcionalidad> funcionalidadesList) {
		this.funcionalidadesList = funcionalidadesList;
	}
	
	public Persona getPersonaJuridica(){
		return this.personaJuridica; 
	}
	
	public void setPersonaJuridica(Persona persona){
		this.personaJuridica = persona;
	}
	
	public List<Persona> getPersonasNaturalesList(){
		return this.personasNaturalesList;
	}
	
	public void setPersonasNaturalesList(List<Persona> personasNaturalesList){
		this.personasNaturalesList = personasNaturalesList;
	}
	
	public List<Documento> getDocumentosPersonalesList() {
		return documentosPersonalesList;
	}


	public void setDocumentosPersonalesList(List<Documento> documentosPersonalesList) {
		this.documentosPersonalesList = documentosPersonalesList;
	}
	
	public String getRegistroConciliador() {
		return registroConciliador;
	}

	public void setRegistroConciliador(String registroConciliador) {
		this.registroConciliador = registroConciliador;
	}
	
	
	public List<LoteGenerado> getLoteGeneradoList() {
		return loteGeneradoList;
	}


	public void setLoteGeneradoList(List<LoteGenerado> loteGeneradoList) {
		this.loteGeneradoList = loteGeneradoList;
	}

	public String getIdPolitica() {
		return idPolitica;
	}


	public void setIdPolitica(String idPolitica) {
		this.idPolitica = idPolitica;
	}


	public PersonaServicioMateria getPersonaServicioMateria() {
		return personaServicioMateria;
	}


	public void setPersonaServicioMateria(PersonaServicioMateria personaServicioMateria) {
		this.personaServicioMateria = personaServicioMateria;
	}

	public String getTieneEmpleo() {
		return tieneEmpleo;
	}

	public void setTieneEmpleo(String tieneEmpleo) {
		this.tieneEmpleo = tieneEmpleo;
	}

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public String getSectorEconomico() {
		return sectorEconomico;
	}

	public void setSectorEconomico(String sectorEconomico) {
		this.sectorEconomico = sectorEconomico;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNumeroPersonasAcargo() {
		return numeroPersonasAcargo;
	}

	public void setNumeroPersonasAcargo(String numeroPersonasAcargo) {
		this.numeroPersonasAcargo = numeroPersonasAcargo;
	}

	public String getTieneSociedadConyugal() {
		return tieneSociedadConyugal;
	}

	public void setTieneSociedadConyugal(String tieneSociedadConyugal) {
		this.tieneSociedadConyugal = tieneSociedadConyugal;
	}

	public String getSociedadConyugalVigente() {
		return sociedadConyugalVigente;
	}

	public void setSociedadConyugalVigente(String sociedadConyugalVigente) {
		this.sociedadConyugalVigente = sociedadConyugalVigente;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getNombreNegocio() {
		return nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}

	public String getNitEmpresa() {
		return nitEmpresa;
	}

	public void setNitEmpresa(String nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Metodo encargado de convertir Entidad Persona a DTO PersonaBasicaDTO
	 * 
	 * @param persona
	 * @return PersonaBasicaDTO
	 */
	public PersonaBasicaDTO convertirEntidadToPersonaBasicoDTO(Persona persona){
		PersonaBasicaDTO personaBasicaDTO = new PersonaBasicaDTO();
		personaBasicaDTO.setIdPersona(persona.getIdPersona());
		personaBasicaDTO.setNombreCompleto(persona.getNombreCompleto());
		personaBasicaDTO.setTipoDocumento(persona.getTipoDocumento());
		personaBasicaDTO.setNumeroDocumento(persona.getNumeroDocumento());
		personaBasicaDTO.setPrimerNombreORazonSocial(persona.getPrimerNombreORazonSocial());
		personaBasicaDTO.setSegundoNombre(persona.getSegundoNombre() != null ? persona.getSegundoNombre() : null);
		personaBasicaDTO.setPrimerApellido(persona.getPrimerApellido() != null ? persona.getPrimerApellido() : null);
		personaBasicaDTO.setSegundoApellido(persona.getSegundoApellido() != null ? persona.getSegundoApellido() : null);
		return personaBasicaDTO;
	}	
	// protected region metodos adicionales end
} 

