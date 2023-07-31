package com.ccb.simasc.web.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPreseleccionado;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.negocio.arbitraje.CasoFacade;
import com.ccb.simasc.negocio.arbitraje.EnvioNotificacionFacade;
import com.ccb.simasc.negocio.arbitraje.RepartoFacade;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.utilidades.GestionEventosServicio;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.web.comun.BaseMBean;

/**
 * Bean encargado de realizar sorteos para los casos configurados
 * 
 * @author lvalbuena@asesoftware.com
 *
 */
@ManagedBean(name = "controladorSorteoView")
@ViewScoped
public class ControladorSorteo extends BaseMBean {

	private static final Logger logger = LogManager.getLogger(ControladorSorteo.class.getName());
	// -------------------------------------------------------------------------
	// CONSTANTES
	// -------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	public static final String PANTALLA_PRIMERA = "primera";
	public static final String PANTALLA_SEGUNDA = "segunda";
	public static final String PANTALLA_TERCERA = "tercera";

	// -------------------------------------------------------------------------
	// ATRIBUTOS
	// -------------------------------------------------------------------------

	/**
	 * Bean de negocio encargado de realizar operaciones de sorteo
	 */
	@EJB
	private IRealizacionSorteoFacade sorteoFacade;

	@EJB
	private GestionEventosServicio gestionEventosServicio;

	@EJB
	private CasoFacade casoFacade;

	@EJB
	private RepartoFacade repartofacade;

	@EJB
	private EnvioNotificacionFacade envioNotificacionFacade;

	@EJB
	private ManejadorRolPersonaCaso manejadorPersonaCaso;

	@EJB
	private ManejadorPreseleccionado manejadorPreseleccionado;

	/**
	 * lista de personas habilitadas para el sorteo
	 */
	private List<Persona> personasSorteo;

	/**
	 * lista de personas suplentes habilitadas para el sorteo en preselección
	 * independiente
	 *
	 */
	private List<Persona> personasSorteoPreseleccionadosSuplentes;

	/**
	 * lista de personas suplentes habilitadas para el sorteo en preselección
	 * independiente
	 *
	 */
	private List<Persona> personasSorteoPreseleccionadosPrincipal;

	/**
	 * lista de arbitros seleccionados para el caso
	 */
	private List<Persona> arbitrosSeleccionados;
	/**
	 * lista de arbitros seleccionados para el caso
	 */
	private List<Persona> arbitrosSeleccionadosPreseleccionSuplentes;
	/**
	 * lista de arbitros seleccionados para el caso
	 */
	private List<Persona> arbitrosSeleccionadosPreseleccionPrincipales;

	/**
	 * Representa la audiencia seleccionada para realizar el sorteo
	 */
	private Audiencia audiencia;

	/**
	 * indica si se habilita o no el boton para realizar sorteo
	 */
	private boolean botonSorteoDisabled;

	/**
	 * Lista de audiencias del dia de sorteo
	 */
	private List<Audiencia> audienciasDia;

	/**
	 * Lista de audiencias del dia de sorteo Internacional
	 */
	private List<Audiencia> audienciasDiaInt;

	private List<Audiencia> audienciasDiaMed;
	
	private List<Audiencia> audienciasDiaSecre;
	
	private List<Audiencia> audienciasDiaRecu;
	
	private List<String> tiposSorteoNacional;

	/**
	 * audiencia seleccionada para cancelar
	 */
	private Audiencia audienciaCancelar;

	/**
	 * cantidad de casos sorteados en el dia
	 */
	private int casosSorteados;

	/**
	 * cantidad de casos pendientes por sortear
	 */
	private int casosPorSortear;

	/**
	 * indica el motivo por el cual se cancela el sorteo para el caso seleccionado
	 */
	private String motivoCancelacion;

	/**
	 * indica si se puede o no realizar liberacion de lista
	 */
	private boolean liberoLista;

	/**
	 * lista donde se encuentran las personas a las cuales se les realizo liberacion
	 * de lista
	 */
	private List<PersonaServicioMateria> personasLiberacion;

	/**
	 * indica la pantalla actual en la que se encuentra el usuario
	 */
	private String pantalla;

	/**
	 * Fecha actual
	 */
	private Calendar fechaActual;

	/**
	 * Indica si existen casos a sortear para el dia en curso
	 */
	private boolean fechaConsultaValida;

	/**
	 * id del usuario de sesion activo
	 */
	private String usuarioSesion;

	/**
	 * indica si la persona en session puede o no realizar sorteos
	 */
	private boolean puedeRealizarSorteos;

	/**
	 * Indica el tipo de audiencia sorteo
	 */
	private String tipoAudiencia;
	
	/**
	 * 
	 */
	private boolean vistaNombreCaso;
	private boolean vistaMateria;
	private boolean vistaCuantia;
	private boolean vistaFechaSolicitud;
	

	// -------------------------------------------------------------------------
	// METODOS
	// -------------------------------------------------------------------------

	/**
	 * Constructor del MBean
	 */
	public ControladorSorteo() {

	}

	/**
	 * Metodo de inicio de modulo
	 */
	public void preRenderComp() {
		this.postConstruct();
	}

	/**
	 * limpia imformacion cuando se cambie de pantalla
	 */
	private void limpiar() {
		this.arbitrosSeleccionados = new ArrayList<Persona>();
		this.personasSorteo = new ArrayList<Persona>();
		this.personasLiberacion = null;
		this.liberoLista = false;
		this.botonSorteoDisabled = false;
	}

	/**
	 * inicia la informacion para la realizacion de sorteo
	 */
	@PostConstruct
	public void postConstruct() {
		tiposSorteoNacional = new ArrayList<>();
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_ARBITROS_SOCIALES);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_AMIGABLES_COMPONEDORES);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_PERITOS);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_ARBITROS);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_ARBITROS_PARA_RECUPERACION);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_AMIGABLES);
		tiposSorteoNacional.add(UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_PERITOS);
		usuarioSesion = this.getUserNameSession();
		this.tipoAudiencia = getTipoSorteo();
		audiencia = null;
		fechaConsultaValida = true;
		fechaActual = Calendar.getInstance();
		botonSorteoDisabled = false;
		this.limpiar();
		this.consultarAudienciasSorteo();
		actualizarIndicadores();
		this.puedeRealizarSorteos = this.tienePermisosSorteo();
		validaColumna(this.tipoAudiencia);
		pantalla = PANTALLA_PRIMERA;
	}

	private String getTipoSorteo() {
			String key = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");
			if (key != null) {
				if (key.equals("1")) {
					return UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES;
				} else if (key.equals("2")) {
					return UtilDominios.TIPO_DE_SORTEO_ARBITROS_SOCIALES;
				} else if (key.equals("4")) {
					return UtilDominios.TIPO_DE_SORTEO_AMIGABLES_COMPONEDORES;
				} else if (key.equals("5")) {
					return UtilDominios.TIPO_DE_SORTEO_PERITOS;
				} else if (key.equals("7")) {
					return UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_ARBITROS;
				} else if (key.equals("11")) {
					return UtilDominios.TIPO_DE_SORTEO_ARBITROS_PARA_RECUPERACION;
				} else if (key.equals("15")) {
					return UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_AMIGABLES;
				} else if (key.equals("16")) {
					return UtilDominios.TIPO_DE_SORTEO_NOMINACION_DE_PERITOS;
				} else if (key.equals("8")) {
					return UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES;
				} else if (key.equals("3")) {
					return UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES;
				}else if (key.equals("18")) {
					return UtilDominios.TIPO_DE_SORTEO_SECRETARIOS;
				}else if (key.equals("19")){
					return UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION;
				}else {
					if (audiencia != null) {
						return audiencia.getTipoSorteo();
					} else {
						return "";
					}
				}
			}else if (this.tipoAudiencia != null) {
				return this.tipoAudiencia;				
			}else {
				if (audiencia != null) {
					return audiencia.getTipoSorteo();
				} else {
					return "";
				}
			}
	}

	/**
	 * Consulta las audiencias las cuales se encuentran disponibles para realizar
	 * sorteo
	 */
	private void consultarAudienciasSorteo() {
		if (this.getTipoAudiencia() == null) {
			if(!this.tiposSorteoNacional.contains(this.getTipoSorteo())) {
				audienciasDia = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(this.getTipoSorteo());
			}else {
				audienciasDia = obtenerAudienciasSorteoNacionalesDia();
			}
			if (audienciasDia.size() <= 0) {
				fechaConsultaValida = false;
			}
		} else {
			audienciasDia = obtenerAudienciasSorteoNacionalesDia();
			audienciasDiaInt = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(
					UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES);
			audienciasDiaMed = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(
					UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES);
			audienciasDiaSecre = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(
					UtilDominios.TIPO_DE_SORTEO_SECRETARIOS);
			audienciasDiaRecu = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(
					UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION);
			if(this.getTipoSorteo() != null) {
				if (this.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
					audienciasDia = audienciasDiaInt;
				}else if (this.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
					audienciasDia = audienciasDiaMed;
				} else if (this.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
					audienciasDia = audienciasDiaSecre;
				} else if (this.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
					audienciasDia = audienciasDiaRecu;
				}
				if (audienciasDia.size() <= 0) {
					fechaConsultaValida = false;
				}
			}else{
				if (audiencia.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
					audienciasDia = audienciasDiaInt;
				}else if (audiencia.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
					audienciasDia = audienciasDiaMed;
				} else if (audiencia.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
					audienciasDia = audienciasDiaSecre;
				} else if (audiencia.getTipoSorteo().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
					audienciasDia = audienciasDiaRecu;
				}
				if (audienciasDia.size() <= 0) {
					fechaConsultaValida = false;
				}
			}
		}
	}
	
	public List<Audiencia> obtenerAudienciasSorteoNacionalesDia(){
		List<Audiencia> audienciasDia = new ArrayList<>();		
		for(String tipoSorteo : this.tiposSorteoNacional) {
			List<Audiencia> audSort = sorteoFacade.obtenerAudienciasSorteoDelDiaYSigDiaHabil(tipoSorteo);
			if(!audSort.isEmpty() && audSort != null) {
				audienciasDia.addAll(audSort);
			}
		}		
		return audienciasDia;		
	}
	
	
	public void validaColumna(String tipoSorteo) {
		this.vistaNombreCaso = true;
		this.vistaMateria = true;
		this.vistaCuantia = true;
		this.vistaFechaSolicitud = true;
		
		if(tipoSorteo.equals("3")) {
			this.vistaNombreCaso = false;
			this.vistaMateria = false;
			this.vistaCuantia = false;
			this.vistaFechaSolicitud = false;
		}else if(!this.tiposSorteoNacional.contains(tipoSorteo)){
			this.vistaMateria = false;
			this.vistaCuantia = false;
		}
	}

	/**
	 * Verifica si el número de casos es mayor a cero y si no es así despliega un
	 * mensaje en pantalla.
	 */
	public void validarNumeroCasos() {
		if (audienciasDia.size() == 0 && this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO022.toString())));
		}
		if (audienciasDia.size() == 0 && this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO022.toString())));
		}
		if (audienciasDia.size() == 0 && this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO022.toString())));
		}
		if (audienciasDia.size() == 0 && this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO022.toString())));
		}
		if (audienciasDia.size() == 0 && this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO022.toString())));
		}
	}

	/**
	 * Actualiza los valores de los casos sorteados y de los casos por sortear
	 */
	private void actualizarIndicadores() {
		//casosSorteados = calcularCasosSorteados();
		//casosPorSortear = calcularCasosParaSorteo();
		this.calcularCasos();
	}

	/**
	 * Consulta los parametros de servicio y se los carga al caso seleccionado
	 */
	private void cargarParamServSorteo() {
		ParametroSorteo parametrosSort = sorteoFacade.consultarParametrosSorteo();
		for (ParametroServicioSorteo paramServicio : parametrosSort.getParametroServicioSorteoList()) {
			if (paramServicio.getIdServicio() == this.audiencia.getCaso().getIdServicio()) {
				this.audiencia.getCaso().setParametroServSorteo(paramServicio);
				break;
			}
		}
	}

	/**
	 * Redirecciona al detalle del caso si el caso esta disponible para sorteo, de
	 * lo contrario, despliega un mensaje al usuario indicando que el sorteo ha sido
	 * cancelado
	 * 
	 * @param caso
	 */
	public void redireccionarADetalleCaso(Audiencia audiencia) {
		this.limpiar();
		// se consulta la udiencia directamente en la db por si se registraron cambios.
		// SEGURIDAD
		this.audiencia = this.sorteoFacade.obtenerAudiencia(audiencia.getIdAudiencia());

		if (!this.audiencia.isAudienciaRealizada()) {
			// YA NO APLICA
			this.audiencia.getCaso()
					.setNombramientoSorteo(this.sorteoFacade.obtenerNombramientoPersonaCaso(audiencia.getCaso()));
			this.cargarParamServSorteo();
			this.buscarCasoConformacionLista();
			this.permisosSorteo();
			pantalla = PANTALLA_SEGUNDA;
		} else {
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR126.toString());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
		}
	}

	/**
	 * Valida si cuenta con permisos para realizar el sorteo, si no es asi muestra
	 * mensaje
	 */
	private void permisosSorteo() {
		if (!this.puedeRealizarSorteos) {
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR127.toString());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
		}
	}

	/**
	 * Devuelve verdadero si la audiencia de sorteo del día para el caso del
	 * parámetro está en estado cancelado o realizado. Esto implica que no pueda ser
	 * cancelada.
	 * 
	 * @param caso
	 * @return
	 */
	public boolean validarCancelacionAudiencia(Caso caso) {
		Audiencia audiencia = caso.getAudienciaSorteoDelDia(fechaActual.getTime());
		return audiencia.enEstadoCancelado() || audiencia.isAudienciaRealizada();
	}

	/**
	 * Calcula el número de casos del día que ya han sido sorteados
	 * 
	 * @return
	 */
	/*private int calcularCasosSorteados() {
		int conteoCasos = 0;
		// Sorteo tipo audiencia publica
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES)) {
			for (Audiencia audiencia : audienciasDia) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo audiencia publica internacional
		if (this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
			for (Audiencia audiencia : audienciasDiaInt) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}

		// Sorteo tipo mediadores
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
			for (Audiencia audiencia : audienciasDiaMed) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo Secretarios
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
			for (Audiencia audiencia : audienciasDiaSecre) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo Arbitros de Recusación
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
			for (Audiencia audiencia : audienciasDiaRecu) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		return conteoCasos;
	}*/

	/**
	 * Calcula el número de casos del día que están pendientes de sorteo
	 * 
	 * @return
	 */
	/*private int calcularCasosParaSorteo() {
		int conteoCasos = 0;
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES)) {
			for (Audiencia audiencia : audienciasDia) {
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo audiencia publica internacional
		if (this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
			for (Audiencia audiencia : audienciasDiaInt) {
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo mediadores
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
			for (Audiencia audiencia : audienciasDiaMed) {
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo Secretarios
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
			for (Audiencia audiencia : audienciasDiaSecre) {
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}
		// Sorteo tipo Arbitros de Recusación
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
			for (Audiencia audiencia : audienciasDiaRecu) {
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
			}
		}

		return conteoCasos;

	}*/
	
	/**
	 * Calcula el número de casos del día que ya han sido sorteados y pendientes
	 * 
	 * @return
	 */
	private void calcularCasos() {
		int conteoCasos = 0;
		int conteoCasosPendientes = 0;
		// Sorteo tipo audiencia publica
		if (this.getTipoAudiencia() != null
				&& this.tiposSorteoNacional.contains(this.getTipoAudiencia())) {
			for (Audiencia audiencia : audienciasDia) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasosPendientes++;
				}
			}
		}
		// Sorteo tipo audiencia publica internacional
		if (this.getTipoAudiencia() != null && this.getTipoAudiencia()
				.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_INTERNACIONALES)) {
			for (Audiencia audiencia : audienciasDiaInt) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasosPendientes++;
				}
			}
		}

		// Sorteo tipo mediadores
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_PUBLICO_MEDIADORES)) {
			for (Audiencia audiencia : audienciasDiaMed) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasosPendientes++;
				}
			}
		}
		// Sorteo tipo Secretarios
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_SECRETARIOS)) {
			for (Audiencia audiencia : audienciasDiaSecre) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasosPendientes++;
				}
			}
		}
		// Sorteo tipo Arbitros de Recusación
		if (this.getTipoAudiencia() != null
				&& this.getTipoAudiencia().equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_RECUSACION)) {
			for (Audiencia audiencia : audienciasDiaRecu) {
				if (UtilDominios.ESTADO_AUDIENCIA_REALIZADA.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasos++;
				}
				if (UtilDominios.ESTADO_AUDIENCIA_PENDIENTE.equalsIgnoreCase(audiencia.getEstado())) {
					conteoCasosPendientes++;
				}
			}
		}
		
		
		this.casosSorteados = conteoCasos;
		this.casosPorSortear = conteoCasosPendientes;
	}

	/**
	 * Cancela el sorteo publico de designación del caso seleccionado
	 */
	public void cancelarSorteoCaso() {
		String msg = audienciaCancelar.getObservaciones() + ";" + motivoCancelacion;
		audienciaCancelar.setObservaciones(msg);
		sorteoFacade.cancelarSorteoCaso(audienciaCancelar);
		gestionEventosServicio.registrarEvento(audienciaCancelar.getCaso(), UtilDominios.ESTADO_CASO_CANCELADO,
				motivoCancelacion, usuarioSesion);
		this.consultarAudienciasSorteo();
		this.actualizarIndicadores();
	}

	/**
	 * Consulta las personas preseleccionadas para realizar el sorteo
	 */
	private void cargaPreseleccionados() {
		personasSorteo = sorteoFacade.obtenerPreseleccionados(this.audiencia.getCaso(), null);
		if (this.audiencia.getCaso().getTipoPreseleccion().equals(UtilDominios.TIPO_PRESELECCION_PRESELECCION_UNICA)) {
			if (!sorteoFacade.validarCantidadMinArb(personasSorteo, this.audiencia, null)) {
				this.botonSorteoDisabled = true;
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR123.toString());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
			}
		} else {
			// principales
			personasSorteoPreseleccionadosPrincipal = sorteoFacade.obtenerPreseleccionados(this.audiencia.getCaso(),
					UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL);
			if (!sorteoFacade.validarCantidadMinArb(personasSorteoPreseleccionadosPrincipal, this.audiencia,
					UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL)) {
				this.botonSorteoDisabled = true;
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR123.toString());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
			}
			// suplentes
			personasSorteoPreseleccionadosSuplentes = sorteoFacade.obtenerPreseleccionados(this.audiencia.getCaso(),
					UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE);
			if (!sorteoFacade.validarCantidadMinArb(personasSorteoPreseleccionadosSuplentes, this.audiencia,
					UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE)) {
				this.botonSorteoDisabled = true;
				String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR123.toString());
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
			}
		}
		this.ordenarPersonasSorteo();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(this.audiencia.getHoraInicio());
		if (fechaActual.get(Calendar.DAY_OF_MONTH) != startCal.get(Calendar.DAY_OF_MONTH)) {
			this.botonSorteoDisabled = true;
		}

	}

	/**
	 * realiza la conformacion de lista de funcionarios para realizar sorteo
	 * teniendo en cuenta los parametros de sorteo
	 */
	@SuppressWarnings("static-access")
	private void conformarListaFuncionarioSorteo() {
		personasSorteo = sorteoFacade.getConformarListaFuncionarioSorteo(this.audiencia.getCaso(), false, this.audiencia.getTipoSorteo());
		this.ordenarPersonasSorteo();

		if (!sorteoFacade.validarCantidadMinArb(personasSorteo, this.audiencia, null)) {

			// simula la liberacion de lista
			if (sorteoFacade.simulariberacionLista(this.audiencia.getCaso(), this.audiencia.getTipoSorteo())) {
				this.botonSorteoDisabled = false;
				this.liberoLista = true;
				// se muestra mensaje al usuario de liberacion de lista
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_INFORMATIVO,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR121.toString())));
			} else {
				this.botonSorteoDisabled = true;
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
						MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR122.toString())));
			}
		}
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(this.audiencia.getHoraInicio());
		if (fechaActual.get(Calendar.DAY_OF_MONTH) != startCal.get(Calendar.DAY_OF_MONTH)) {
			this.botonSorteoDisabled = true;
		}
	}

	/**
	 * Ordena de forma alfabetica a las personas que van a participar en el sorteo
	 */
	private void ordenarPersonasSorteo() {
		Collections.sort(personasSorteo, new Comparator<Persona>() {
			@Override
			public int compare(Persona p1, Persona p2) {
				return p1.getPrimerNombreORazonSocial().compareTo(p2.getPrimerNombreORazonSocial());
			}
		});
		if (personasSorteoPreseleccionadosPrincipal != null) {
			Collections.sort(personasSorteoPreseleccionadosPrincipal, new Comparator<Persona>() {
				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getPrimerNombreORazonSocial().compareTo(p2.getPrimerNombreORazonSocial());
				}
			});
		}
		if (personasSorteoPreseleccionadosSuplentes != null) {
			Collections.sort(personasSorteoPreseleccionadosSuplentes, new Comparator<Persona>() {
				@Override
				public int compare(Persona p1, Persona p2) {
					return p1.getPrimerNombreORazonSocial().compareTo(p2.getPrimerNombreORazonSocial());
				}
			});
		}
	}

	/**
	 * Metodo encargado de de buscar y calcular los arbitros para un sorteo teniendo
	 * en cuenta si el caso maneja por preseleccion o no
	 * 
	 */
	public void buscarCasoConformacionLista() {
		this.liberoLista = false;

		if (this.audiencia.getCaso().getPreseleccion()) {
			this.cargaPreseleccionados();
		} else {
			this.conformarListaFuncionarioSorteo();
		}
	}

	/**
	 * 
	 * @param personaDTOs
	 * @return
	 */
	public Long funcionariosHabilitadosSorteo(List<PersonaDTO> personaDTOs) {
		int cantidad = 0;
		for (PersonaDTO it : personaDTOs) {
			if (!it.isEsMediador()) {
				cantidad++;
			}
		}
		return (long) cantidad;
	}

	/**
	 * Realiza el sorteo para el caso con las personas seleccionadas
	 */
	public void sortear() {
		// se consulta la udiencia directamente en la db por si se registraron cambios.
		// SEGURIDAD
		Audiencia audienciaVal = this.sorteoFacade.obtenerAudiencia(this.audiencia.getIdAudiencia());
		if (!audienciaVal.isAudienciaRealizada()) {
			if (this.liberoLista) {
				this.sorteoConLiberacionDeLista();
			} else {
				this.audiencia.getCaso().setParticipantesSorteo(personasSorteo);

				try {
					if (this.getAudiencia().getCaso().getPreseleccion() && this.getAudiencia().getCaso()
							.getTipoPreseleccion().equals(UtilDominios.TIPO_PRESELECCION_PRESELECCION_INDEPENDIENTE)) {
						this.arbitrosSeleccionadosPreseleccionPrincipales = sorteoFacade.realizarSorteo(
								personasSorteoPreseleccionadosPrincipal, this.audiencia.getCantidadPrincipales(), 0);
						this.arbitrosSeleccionadosPreseleccionSuplentes = sorteoFacade.realizarSorteo(
								personasSorteoPreseleccionadosSuplentes, 0, this.audiencia.getCantidadSuplentes());
						this.arbitrosSeleccionados.addAll(this.arbitrosSeleccionadosPreseleccionPrincipales);
						this.arbitrosSeleccionados.addAll(this.arbitrosSeleccionadosPreseleccionSuplentes);
					} else {
						this.arbitrosSeleccionados = sorteoFacade.realizarSorteo(personasSorteo,
								this.audiencia.getCantidadPrincipales(), this.audiencia.getCantidadSuplentes());
					}

				} catch (Exception e) {
					logger.error(e.getMessage());
					String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
					pantalla = PANTALLA_PRIMERA;
				}
				// Preseleccion Independiente

			}
			sorteoFacade.registrarResultSorteo(audiencia, this.arbitrosSeleccionados, usuarioSesion);

			if (this.getAudiencia().getCaso().getPreseleccion() &&
				this.getAudiencia().getCaso().getQuienPreselecciona() != null && this.getAudiencia().getCaso()
					.getQuienPreselecciona().equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)) {

				for (Persona arbitro : arbitrosSeleccionados) {

					Preseleccionado preseleccionado = manejadorPreseleccionado
							.buscarPreseleccionado(arbitro.getIdPersona(), this.audiencia.getIdCaso());
					preseleccionado.setIdMateria(arbitro.getPersonaServicioMateria().getIdMateria());

					manejadorPreseleccionado.actualizar(preseleccionado);
				}

			}

			if (audienciaVal.getCaso() != null && audienciaVal.getCaso().getIdServicio()
					.equals(UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)) {
				Caso caso = audienciaVal.getCaso();
				caso.setEstadoCaso(UtilDominios.ESTADO_CASO_CONCILIADOR_DESIGNADO);
				casoFacade.actualizarCasos(caso);
			}

			this.actualizarIndicadores();
			pantalla = PANTALLA_TERCERA;

		} else {
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR126.toString());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
			pantalla = PANTALLA_PRIMERA;
		}
	}

	/**
	 * El sorteo se debe realizar con liberacion de lista, primero se sortean los
	 * las personas preseleccionadas y despues los arbitros faltantes se toman de la
	 * lista de liberacion
	 */
	private void sorteoConLiberacionDeLista() {
		this.audiencia.getCaso().setLiberoLista(true);

		int cantFuncionariosPrincipales = this.audiencia.getCantidadPrincipales();
		int cantFuncionariosSuplentes = this.audiencia.getCantidadSuplentes();

		// se realiza sorteo con las personas calculadas inicialmente
		// se realiza calculo de cuantos principales y suplentes se necesitan para este
		// sorteo
		int cantPrin = 0;
		int cantSup = 0;
		int i = 1;
		for (Persona persona : personasSorteo) {
			if (!persona.isMarcaAzulSorteo()) {
				if (i <= cantFuncionariosPrincipales) {
					cantPrin++;
				} else {
					cantSup++;
				}
				i++;
			}
		}
		this.audiencia.getCaso().setParticipantesSorteo(personasSorteo);

		try {
			this.arbitrosSeleccionados = sorteoFacade.realizarSorteo(personasSorteo, cantPrin, cantSup);

			// se obtienen las personas liberadas
			personasLiberacion = sorteoFacade.liberarListaSorteo(this.audiencia.getCaso(), usuarioSesion, this.audiencia.getTipoSorteo());
			this.audiencia.getCaso().setHoraLiberacion(new Date());

			List<Persona> personasLiberadas = new ArrayList<Persona>();
			for (PersonaServicioMateria psm : personasLiberacion) {
				personasLiberadas.add(psm.getPersona());
			}

			// se realiza el sorteo con las personas liberadas
			int faltantesPrincipales = cantFuncionariosPrincipales - cantPrin;
			int faltantesSuplentes = cantFuncionariosSuplentes - cantSup;

			this.audiencia.getCaso().setParticipantesSorteoLiberacion(personasLiberadas);
			List<Persona> personasRestantes = sorteoFacade.realizarSorteo(personasLiberadas, faltantesPrincipales,
					faltantesSuplentes);

			// se agregan los arbitros restantes
			for (Persona persona : personasRestantes) {
				if (UtilConstantes.NOMBRAMIENTO_PRINCIPAL.equals(persona.getNombradoComo())) {
					persona.setNombradoComoPos(++cantPrin);
				} else {
					persona.setNombradoComoPos(++cantSup);
				}
				this.arbitrosSeleccionados.add(persona);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			String msg = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR010.toString());
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA, msg));
			pantalla = PANTALLA_PRIMERA;
		}

	}

	/**
	 * Metodo para ir a la pantalla inicial del sorteo
	 */
	public void pantallaInicial() {
		if(this.audiencia != null) {
			this.tipoAudiencia = ""+this.audiencia.getTipoSorteo();
		}
		pantalla = PANTALLA_PRIMERA;
	}

	public boolean isBotonSorteoDisabled() {
		if (!this.puedeRealizarSorteos) {
			botonSorteoDisabled = true;
		}
		return botonSorteoDisabled;
	}

	public void setBotonSorteoDisabled(boolean botonSorteoDisabled) {
		this.botonSorteoDisabled = botonSorteoDisabled;
	}

	/**
	 * @return the casosSorteados
	 */
	public int getCasosSorteados() {
		return casosSorteados;
	}

	/**
	 * @param casosSorteados the casosSorteados to set
	 */
	public void setCasosSorteados(int casosSorteados) {
		this.casosSorteados = casosSorteados;
	}

	/**
	 * @return the casosPorSortear
	 */
	public int getCasosPorSortear() {
		return casosPorSortear;
	}

	/**
	 * @param casosPorSortear the casosPorSortear to set
	 */
	public void setCasosPorSortear(int casosPorSortear) {
		this.casosPorSortear = casosPorSortear;
	}

	/**
	 * @return the motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	/**
	 * @param motivoCancelacion the motivoCancelacion to set
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	/**
	 * @return the pantalla
	 */
	public String getPantalla() {
		return pantalla;
	}

	/**
	 * @param pantalla the pantalla to set
	 */
	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}

	/**
	 * @return the fechaActual
	 */
	public Calendar getFechaActual() {
		return fechaActual;
	}

	/**
	 * @param fechaActual the fechaActual to set
	 */
	public void setFechaActual(Calendar fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * @return the fechaConsultaValida
	 */
	public boolean isFechaConsultaValida() {
		return fechaConsultaValida;
	}

	/**
	 * @param fechaConsultaValida the fechaConsultaValida to set
	 */
	public void setFechaConsultaValida(boolean fechaConsultaValida) {
		this.fechaConsultaValida = fechaConsultaValida;
	}

	/**
	 * @return the audienciasDia
	 */
	public List<Audiencia> getAudienciasDia() {
		return audienciasDia;
	}

	/**
	 * @param audienciasDia the audienciasDia to set
	 */
	public void setAudienciasDia(List<Audiencia> audienciasDia) {
		this.audienciasDia = audienciasDia;
	}

	/**
	 * @return the personasSorteo
	 */
	public List<Persona> getPersonasSorteo() {
		return personasSorteo;
	}

	/**
	 * @param personasSorteo the personasSorteo to set
	 */
	public void setPersonasSorteo(List<Persona> personasSorteo) {
		this.personasSorteo = personasSorteo;
	}

	/**
	 * @return the audienciaCancelar
	 */
	public Audiencia getAudienciaCancelar() {
		return audienciaCancelar;
	}

	/**
	 * @param audienciaCancelar the audienciaCancelar to set
	 */
	public void setAudienciaCancelar(Audiencia audienciaCancelar) {
		this.audienciaCancelar = audienciaCancelar;
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
	 * @return the arbitrosSeleccionados
	 */
	public List<Persona> getArbitrosSeleccionados() {
		return arbitrosSeleccionados;
	}

	/**
	 * @param arbitrosSeleccionados the arbitrosSeleccionados to set
	 */
	public void setArbitrosSeleccionados(List<Persona> arbitrosSeleccionados) {
		this.arbitrosSeleccionados = arbitrosSeleccionados;
	}

	/**
	 * @return the audiencia
	 */
	public Audiencia getAudiencia() {
		return audiencia;
	}

	/**
	 * @param audiencia the audiencia to set
	 */
	public void setAudiencia(Audiencia audiencia) {
		this.audiencia = audiencia;
	}

	/**
	 * Mensaje para los casos sorteados
	 * 
	 * @return
	 */
	public String getMensajeSorteo() {
		return MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO071.toString());
	}

	/**
	 * @return the usuarioSesion
	 */
	public String getUsuarioSesion() {
		return usuarioSesion;
	}

	/**
	 * @param usuarioSesion the usuarioSesion to set
	 */
	public void setUsuarioSesion(String usuarioSesion) {
		this.usuarioSesion = usuarioSesion;
	}

	/**
	 * Indica si es preselección independiente
	 * 
	 * @return true si es preseleccion independiente de lo contrario false
	 */

	public boolean getPreseleccionIndependiente() {
		return this.getAudiencia().getCaso().getPreseleccion() && this.getAudiencia().getCaso().getTipoPreseleccion()
				.equals(UtilDominios.TIPO_PRESELECCION_PRESELECCION_INDEPENDIENTE);
	}

	public List<Persona> getPersonasSorteoPreseleccionadosSuplentes() {
		return personasSorteoPreseleccionadosSuplentes;
	}

	public void setPersonasSorteoPreseleccionadosSuplentes(List<Persona> personasSorteoPreseleccionadosSuplentes) {
		this.personasSorteoPreseleccionadosSuplentes = personasSorteoPreseleccionadosSuplentes;
	}

	public List<Persona> getArbitrosSeleccionadosPreseleccionSuplentes() {
		return arbitrosSeleccionadosPreseleccionSuplentes;
	}

	public void setArbitrosSeleccionadosPreseleccionSuplentes(
			List<Persona> arbitrosSeleccionadosPreseleccionSuplentes) {
		this.arbitrosSeleccionadosPreseleccionSuplentes = arbitrosSeleccionadosPreseleccionSuplentes;
	}

	public List<Persona> getArbitrosSeleccionadosPreseleccionPrincipales() {
		return arbitrosSeleccionadosPreseleccionPrincipales;
	}

	public void setArbitrosSeleccionadosPreseleccionPrincipales(
			List<Persona> arbitrosSeleccionadosPreseleccionPrincipales) {
		this.arbitrosSeleccionadosPreseleccionPrincipales = arbitrosSeleccionadosPreseleccionPrincipales;
	}

	public List<Persona> getPersonasSorteoPreseleccionadosPrincipal() {
		return personasSorteoPreseleccionadosPrincipal;
	}

	public void setPersonasSorteoPreseleccionadosPrincipal(List<Persona> personasSorteoPreseleccionadosPrincipal) {
		this.personasSorteoPreseleccionadosPrincipal = personasSorteoPreseleccionadosPrincipal;
	}

	public String getTipoAudiencia() {
		return tipoAudiencia;
	}

	public String getTipoPeritaje() {
		if (this.getAudiencia().getCaso() != null && this.getAudiencia().getCaso().getTipoPeritaje() != null) {
			return this.getAudiencia().getCaso().getTipoPeritaje().equals(UtilDominios.TIPO_PERITAJE_VINCULANTE)
					? "Vinculante"
					: "No vinculante";
		} else {
			return "";
		}
	}

	public boolean getEsPeritaje() {
		if (this.getAudiencia().getCaso() != null) {
			return this.getAudiencia().getCaso().getIdServicio().equals(UtilConstantes.ID_SERVICIO_PERITAJE);
		} else {
			return false;
		}
	}

	public void setTipoAudiencia(String tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

	public String getQuienPreselecciona() {
		return this.getAudiencia().getCaso().getQuienPreselecciona()
				.equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA) ? " CCB " : " Partes ";
	}

	public boolean isVistaNombreCaso() {
		return vistaNombreCaso;
	}

	public void setVistaNombreCaso(boolean vistaNombreCaso) {
		this.vistaNombreCaso = vistaNombreCaso;
	}

	public boolean isVistaMateria() {
		return vistaMateria;
	}

	public void setVistaMateria(boolean vistaMateria) {
		this.vistaMateria = vistaMateria;
	}

	public boolean isVistaCuantia() {
		return vistaCuantia;
	}

	public void setVistaCuantia(boolean vistaCuantia) {
		this.vistaCuantia = vistaCuantia;
	}

	/**
	 * @return the vistaFechaRadicacion
	 */
	public boolean isVistaFechaSolicitud() {
		return vistaFechaSolicitud;
	}

	/**
	 * @param vistaFechaRadicacion the vistaFechaRadicacion to set
	 */
	public void setVistaFechaSolicitud(boolean vistaFechaSolicitud) {
		this.vistaFechaSolicitud = vistaFechaSolicitud;
	}
	
	
	
}
