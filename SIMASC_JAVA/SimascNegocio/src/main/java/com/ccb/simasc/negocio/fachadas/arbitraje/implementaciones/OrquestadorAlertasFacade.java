package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorAlertasPendientes;
import com.ccb.simasc.integracion.manejadores.ManejadorArchivo;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCartaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorDevolucionDocumentoResultado;
import com.ccb.simasc.integracion.manejadores.ManejadorDocumento;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorElegible;
import com.ccb.simasc.integracion.manejadores.ManejadorEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorHistoricoEstadoPersonaRol;
import com.ccb.simasc.integracion.manejadores.ManejadorPagoHonorarios;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPeticion;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorPronunciamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSedeConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorSolicitudServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorSuspension;
import com.ccb.simasc.integracion.manejadores.ManejadorTranscripcion;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILogAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPagoCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametroDeServicioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IPersonaRolAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IProgramacionAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ISolicitudServicioFacade;
import com.ccb.simasc.transversal.dto.AlertaDTO;
import com.ccb.simasc.transversal.dto.ParametroDeServicioDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.PronunciamientoDTO;
import com.ccb.simasc.transversal.dto.SorteoLiberacionListaDTO;
import com.ccb.simasc.transversal.dto.alertas.AlertaEmeritoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.InfoRegistroPronunciamientoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Alerta;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.entidades.EstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.HistoricoEstadoPersonaRol;
import com.ccb.simasc.transversal.entidades.PagoCaso;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.ProgramacionAlerta;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.SolicitudServicio;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;

@Stateless
@LocalBean
public class OrquestadorAlertasFacade implements IOrquestadorAlertasFacade {
	private static final String PREFIJOMETODOS = "alerta";
	private static final String MENSAJE_SIN_SORTEO = "Actualmente no existen sorteos programados.";
	private static final Logger LOGGER = Logger.getLogger(OrquestadorAlertasFacade.class.getName());
	private static final String ABRE_TR = "<tr>";
	private static final String CIERRA_TR = "</tr>";
	private static final String ABRE_TD = "<td>";
	private static final String CIERRA_TD = "</td>";
	private static final String SALTO_LINEA = "<br />";
	
	@EJB
	private ManejadorAlertasPendientes manejadorAlertaPendiente;

	@EJB
	private ManejadorAlerta manejadorAlerta;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorSorteo manejadorSorteo;

	@EJB
	private ICorreoRolPersonaCasoFacade correoRolPersonaCasoFacade;

	@EJB
	private IAlertaFacade alertaFacade;

	@EJB
	private IProgramacionAlertaFacade programacionAlertaFacade;

	@EJB
	private INotificacionFacade notificacionFacade;

	@EJB
	private IRolPersonaCasoFacade rolPersonaCasoFacade;

	@EJB
	private IPersonaFacade personaFacade;

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;

	@EJB
	private ManejadorEstadoPersonaRol manejadorEstadoPersonaRol;

	@EJB
	private ManejadorHistoricoEstadoPersonaRol manejadorHistoricoEstadoPersonaRol;

	@EJB
	private PronunciamientoFacade pronunciamientoFacade;

	@EJB
	private ManejadorDominio manejadorDominio;

	@EJB
	private ManejadorSedeConvenio manejadorSedeConvenio;

	@EJB
	private ManejadorConvenio manejadorConvenio;

	@EJB
	private ManejadorCartaPersona manejadorCartaPersona;

	@EJB
	private ILogAlertasFacade logAlertasFacade;

	@EJB
	private ManejadorDocumento manejadorDocumento;

	@EJB
	private ManejadorDevolucionDocumentoResultado manejadorDevolucionDocumentoResultado;

	@EJB
	private ManejadorSuspension manejadorSuspension;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorRolPersona manejadorRolPersona;

	@EJB
	private ManejadorPeticion manejadorPeticion;

	@EJB
	private ManejadorTranscripcion manejadorTranscripcion;

	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	private ManejadorPronunciamiento manejadorPronunciamiento;

	@EJB
	private ManejadorEvento manejadorEvento;

	@EJB
	private ManejadorPagoHonorarios manejadorPagoHonorarios;

	@EJB
	private ManejadorArchivo manejadorArchivo;

	@EJB
	private ManejadorSolicitudServicio manejadorSolicitudServicio;

	@EJB
	private ICasoFacade casoFacade;

	@EJB
	private ISolicitudServicioFacade solicitudServicioFacade;

	@EJB
	private IParametroDeServicioFacade parametroDeServicioFacade;

	@EJB
	private IPersonaRolAlertaFacade personaRolAlertaFacade;

	@EJB
	private IPagoCasoFacade pagoCasoFacade;
	
	@EJB
	private ManejadorParametroDeServicio manejadorParametroDeServicio;
	
	@EJB
	private ManejadorElegible manejadorElegible;

	/*
	 * -------------------------------------------------------------------------
	 * --------------- -------------- SECCION DE METODOS DE CONSULTA Y EJECUCION DE
	 * LAS ALERTAS
	 * -------------------------------------------------------------------------
	 * ---------------
	 */

	/**
	 * Metodo encargaddo de consultar y ejecutar las alartas tipo parametrizadas,
	 * activas y pendientes de ejecucion
	 */
	@Override
	public void ejecutarAlertasParametrizadas() {
		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
		filtroAlerta.setTipoAlerta(UtilDominios.TIPO_ALERTA_PARAMETRIZADA);
		filtroAlerta.setEstadoEjecucion(UtilDominios.ESTADO_EJECUCION_ALERTA_PENDIENTE);
		filtroAlerta.setHoraEjecucion(new Date());
		List<Alerta> alertasActivas = // manejadorAlerta.//consultarAlertasPorEstadoTipoServicio(filtroAlerta);
				manejadorAlertaPendiente.consultarAlertasEnEjecucion(filtroAlerta);
		LOGGER.info("Alertas Activas " + alertasActivas.size());
		for (Alerta alertaBasicaFor : alertasActivas) {
			this.ejecucionMetodoAlertasParametrizadas(alertaBasicaFor);
		}
	}

	/**
	 * Metodo encargado de ejecutar las alertas que tienen un registro de
	 * programacion pendiente
	 */
	@Override
	public void ejecutarAlertasProgramadasNegocio() {
		List<ProgramacionAlerta> programacion = manejadorAlertaPendiente.consultarProgramacionesEnEjecucion();
		LOGGER.info("alertas programadas " + programacion.size());
		for (ProgramacionAlerta programacionFor : programacion) {
			this.ejecucionAlertasProgramacion(programacionFor);
		}
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------- -------------- SECCION DE METODOS DE INVOCACION REFLECTIVA
	 * -------------------------------------------------------------------------
	 * ---------------
	 */

	/**
	 * Metodo encargado de ejecutar las programaciones activas de las alertas de
	 * negocio, Llama cada una de las calses a utilizar
	 * 
	 * @param alertaEjecutar
	 */
	private void ejecucionAlertasProgramacion(ProgramacionAlerta programacion) {
		String mensajeError = null;
		if (programacion.getAlerta() == null || programacion.getAlerta().getCodigo() == null) {
			LOGGER.info("Error con la estructura de la alerta idProgramacionAlerta:"
					+ programacion.getIdProgramacionAlerta());
		} else {
			LOGGER.info("Ejecucion alerta " + programacion.getAlerta().getCodigo() + "id: "
					+ programacion.getIdProgramacionAlerta());
			String metodoEjecutar = this.PREFIJOMETODOS + programacion.getAlerta().getCodigo();
			Class[] argumentos = new Class[1];
			argumentos[0] = ProgramacionAlerta.class;
			try {

				Method metodoLlamado = this.getClass().getMethod(metodoEjecutar, argumentos);
				metodoLlamado.invoke(this, programacion);
			} catch (NoSuchMethodException | SecurityException e) {
				mensajeError = "El metodo no se encuentra o esta protegido" + programacion.getAlerta().getCodigo();
				this.persistirMensajesErrorAlerta(mensajeError, programacion.getIdAlerta(),
						programacion.getIdProgramacionAlerta(), UtilDominios.ESTADO_NOTIFICACION_METODO_NO_ENCONTRADO);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				mensajeError = "La forma de invocar el metodo no es correcta " + programacion.getAlerta().getCodigo();
				LOGGER.throwing("OrquestadorAlertasFacade", "ejecucionAlertasProgramacion", e.getCause());
				this.persistirMensajesErrorAlerta(mensajeError, programacion.getIdAlerta(),
						programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_FALLO_EJECUCION);

			}

		}

	}

	/**
	 * Metodo encargado de ejecutar las programaciones activas de las alertas de
	 * negocio,
	 * 
	 * @param alertaEjecutar
	 */
	private void ejecucionMetodoAlertasParametrizadas(Alerta alertaEjecutar) {
		LOGGER.info("Ejecucion alerta " + alertaEjecutar.getCodigo());
		String metodoEjecutar = PREFIJOMETODOS + alertaEjecutar.getCodigo();
		Class[] argumentos = new Class[1];
		argumentos[0] = Alerta.class;
		String mensajeError = null;
		try {
			Method metodoLlamado = this.getClass().getMethod(metodoEjecutar, argumentos);
			metodoLlamado.invoke(this, alertaEjecutar);
		} catch (NoSuchMethodException | SecurityException e) {
			mensajeError = "El metodo no se encuentra o esta protegido " + alertaEjecutar.getCodigo();
			this.persistirMensajesErrorAlerta(mensajeError, alertaEjecutar.getIdAlerta(), null,
					UtilDominios.ESTADO_NOTIFICACION_METODO_NO_ENCONTRADO);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			mensajeError = "La forma de invocar el metodo no es correcta " + alertaEjecutar.getCodigo();			
			LOGGER.throwing("OrquestadorAlertasFacade", "ejecucionMetodoAlertasParametrizadas", e.getCause());
			this.persistirMensajesErrorAlerta(mensajeError, alertaEjecutar.getIdAlerta(), null,
					UtilDominios.ESTADO_NOTIFICACION_FALLO_EJECUCION);

		}

	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------- -------------- SECCION AUXILIARES DE CONSULTA
	 * -------------------------------------------------------------------------
	 * ---------------
	 */

	/**
	 * Metodo encargado de llamar la nueva transaccion
	 * 
	 * @param mensaje
	 * @param idAlerta
	 * @param programacion
	 * @param codigo
	 */
	private void persistirMensajesErrorAlerta(String mensaje, Long idAlerta, Long idProgramacion, String codigoLog) {
		try {
			logAlertasFacade.generarLogAlerta(mensaje, codigoLog, idAlerta, idProgramacion,
					UtilDominios.ESTADO_EJECUCION_ALERTA_FALLO_EJECUCION);			
		} catch (Exception e1) {
			LOGGER.info("Error de ejeucion de segunda transaccion 2");
		}
	}

	/**
	 * Metodo encargado de llamar la nueva transaccion
	 * 
	 * @param mensaje
	 * @param idAlerta
	 * @param programacion
	 * @param codigo
	 */
	private void persistirMensajesEjecucionAlerta(String mensaje, Long idAlerta, Long idProgramacion,
			String codigoLog) {
		try {
			logAlertasFacade.generarLogAlerta(mensaje, codigoLog, idAlerta, idProgramacion, codigoLog);			
		} catch (Exception e1) {
			LOGGER.info("Error de ejeucion de segunda transaccion 3");
		}
	}

	private void envioConNotificacion(List<PersonaBasicaDTO> notificados, String textoCorreo, Alerta alerta) {
		ParametrosEnvioCorreoDTO parametrosEnvio = null;
		if (!notificados.isEmpty()) {
			parametrosEnvio = new ParametrosEnvioCorreoDTO();
			parametrosEnvio.setAsunto(alerta.getAsunto() != null ? alerta.getAsunto() : "Alerta sin asunto");
			parametrosEnvio.setPersonasEnvio(null);
			List<String> cuerpoCorreo = new ArrayList<>();
			cuerpoCorreo.add(textoCorreo);
			parametrosEnvio.setCuerpoCorreo(cuerpoCorreo);

			for (PersonaBasicaDTO notificadoFor : notificados) {
				List<PersonaBasicaDTO> personaBasica = new ArrayList<>();
				personaBasica.add(notificadoFor);
				parametrosEnvio.setPersonasEnvio(personaBasica);
				Boolean envioCorrecto = correoRolPersonaCasoFacade.enviarCorreoAlerta(parametrosEnvio);

				notificacionFacade.generarLogAlerta(textoCorreo,
						envioCorrecto ? UtilDominios.ESTADO_NOTIFICACION_ENVIADA
								: UtilDominios.ESTADO_NOTIFICACION_NO_ENVIADA,
						alerta.getIdAlerta(), notificadoFor.getIdPersona(), new Date());
			}

		} else {
			notificacionFacade.generarLogAlerta(textoCorreo, UtilDominios.ESTADO_NOTIFICACION_FALLO_SIN_REMITENTE,
					alerta.getIdAlerta(), null, new Date());

		}

	}

	/**
	 * Finaliza el proceso de envio de la alerta
	 * 
	 * @param idCaso
	 * @param idCentro
	 * @param alerta
	 * @param idPersonas
	 * @param filtros
	 */
	private void gestionarAlertasProcesoNotificacion(Long idCaso, Long idCentro, Long idConvenio, Alerta alerta,
			List<Long> idPersonas, Map<String, String> filtros) {
		String textoAlerta = filtros != null ? alertaFacade.reemplazarTextoAlertas(alerta.getTexto(), filtros)
				: alerta.getTexto();
		List<Long> centros = new ArrayList<>();
		if (idCentro != null) {
			centros.add(idCentro);
		}
		List<PersonaBasicaDTO> notificados = alertaFacade.consultarPersonasNotificar(idCaso, alerta.getIdAlerta(),
				centros, idConvenio, idPersonas);
		this.envioConNotificacion(notificados, textoAlerta, alerta);

	}

	/*
	 * -------------------------------------------------------------------------
	 * -------------- SECCION DE METODOS DE EJECUCION DE ALERTAS ALERTAS TIPO:
	 * PARAMETRIZADAS
	 * -------------------------------------------------------------------------
	 * ---------------
	 */

	/**
	 * Metodo de ejecucion de alerta de los casos sin asignacion de conciliador
	 * 
	 * @param alerta
	 * 
	 */

	public void alertaCSINCON(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinAsignacion = manejadorCaso.alertaCasosSinConciliador(alerta.getValor(),
				alerta.getTipoPeriodicidad());
		for (InfoBasicaAlertasDTO casoFor : casosSinAsignacion) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Metodo que ejecuta la alerta id: 11 Generación de constancia de inasistencia
	 * 
	 * @param alerta
	 */
	@SuppressWarnings("unused")
	public void alertaGENINA(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;

		List<InfoBasicaAlertasDTO> casoPendienteDocImpo = manejadorAudiencia.alertaResultadoPendienteDocmento(
				UtilDominios.RESULTADO_AUDIENCIA_INASISTENCIA, alerta.getTipoPeriodicidad(), alerta.getValor(),
				encabezadoTabla, UtilConstantes.PARAM_ALERTA_TABLA_CIERRE,
				UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CONRADD);

		if (casoPendienteDocImpo != null) {
			for (InfoBasicaAlertasDTO caso : casoPendienteDocImpo) {
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_TABLA, caso.getTabla());
				List<Long> personas = new ArrayList<>();
				personas.add(caso.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(caso.getIdCaso(), null, null, alerta, personas, filtros);
			}
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id: 12 Radicación de acta o constancia de
	 * imposibilidad
	 * 
	 * @param alerta
	 */
	@SuppressWarnings("unused")
	public void alertaRADIMP(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;

		List<InfoBasicaAlertasDTO> casoPendienteDocumentoImpo = manejadorAudiencia.alertaResultadoPendienteDocmento(
				UtilDominios.RESULTADO_AUDIENCIA_IMPOSIBILIDAD_DE_ACUERDO, alerta.getTipoPeriodicidad(),
				alerta.getValor(), encabezadoTabla, UtilConstantes.PARAM_ALERTA_TABLA_CIERRE,
				UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CONRADD);

		if (casoPendienteDocumentoImpo != null) {
			for (InfoBasicaAlertasDTO casoPendienteDoc : casoPendienteDocumentoImpo) {
				Map<String, String> filtrosAlertRADIMP = new HashMap<>();
				filtrosAlertRADIMP.put(UtilConstantes.PARAM_ALERTA_TABLA, casoPendienteDoc.getTabla());
				List<Long> personasNotificar = new ArrayList<>();
				personasNotificar.add(casoPendienteDoc.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(casoPendienteDoc.getIdCaso(), null, null, alerta, personasNotificar, filtrosAlertRADIMP);
			}
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id: 13: Radicación de constancia de inasistencia
	 * 
	 * @param alerta
	 */
	@SuppressWarnings("unused")
	public void alertaRADINA(Alerta alerta) {
		this.alertaGENINA(alerta);
	}

	/**
	 * Metodo que ejecuta la alerta id:131 : Asignacion de casos a
	 * auxiliares-seguimiento a casos.
	 * 
	 * @param alerta
	 */
	public void alertaACA(Alerta alerta) {
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		if (alerta.getValor() != null) {
			if (dayOfMonth == alerta.getValor().intValue()) {

				List<InfoBasicaAlertasDTO> casosAsignados = rolPersonaCasoFacade.crearAsignacionCasosAuxiliaresADM();
				if (casosAsignados != null) {
					for (InfoBasicaAlertasDTO caso : casosAsignados) {
						Map<String, String> filtros = new HashMap<>();
						filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_AUXILIAR, caso.getNombrePersona());
						filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, caso.getIdCaso().toString());
						filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, caso.getNombreCaso());
						List<Long> personas = new ArrayList<Long>();
						personas.add(caso.getIdPersona());
						this.gestionarAlertasProcesoNotificacion(caso.getIdCaso(), caso.getIdCentro(), null, alerta,
								personas, filtros);
					}
				}

			}
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Metodo que ejecuta la alerta id:8 : Cartas Pendientes por Impresion
	 * 
	 * @param alerta
	 */
	public void alertaPENIMP(Alerta alerta) {
		List<InfoBasicaAlertasDTO> cartasPendientes = manejadorCartaPersona.alertaCartasPendientesImpresion();

		if ((cartasPendientes != null) && (!cartasPendientes.isEmpty())) {
			for (InfoBasicaAlertasDTO cartaPendiente : cartasPendientes) {
				Map<String, String> filtros = new HashMap<>();

				filtros.put(UtilConstantes.PARAM_ALERTA_CANTIDAD_CARTAS, cartaPendiente.getNumeroCartas().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, cartaPendiente.getNombreCentro());
				this.gestionarAlertasProcesoNotificacion(null, cartaPendiente.getIdCentro(), null, alerta, null,
						filtros);
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id:10 : Cerrar Audiencia
	 * 
	 * @param alerta
	 */
	public void alertaCERAU(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;

		List<InfoBasicaAlertasDTO> audienciasPendientes = manejadorAudiencia.alertaCerrarAudiencia(encabezadoTabla,
				UtilConstantes.PARAM_ALERTA_TABLA_CIERRE, UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CERAU,
				alerta.getTipoPeriodicidad());

		if (audienciasPendientes != null) {
			for (InfoBasicaAlertasDTO caso : audienciasPendientes) {
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_TABLA, caso.getTabla());
				List<Long> personas = new ArrayList<>();
				personas.add(caso.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(caso.getIdCaso(), null, null, alerta, personas, filtros);
			}
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id:9 : Actualizar estado de cartas
	 * 
	 * @param alerta
	 */
	public void alertaAESTCAR(Alerta alerta) {
		List<InfoBasicaAlertasDTO> cartasEnviadas = manejadorCartaPersona
				.alertaActualizarEstadoCarta(alerta.getTipoPeriodicidad(), alerta.getValor());
		StringBuilder bldNuevo = new StringBuilder();
		Map<String, String> filtros = new HashMap<>();
		bldNuevo.append(SALTO_LINEA);
		bldNuevo.append(SALTO_LINEA);
		bldNuevo.append("<table border=1>");
		bldNuevo.append(ABRE_TR);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Código del Caso");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Id Carta");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Fecha de Envío");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Nombre del Destinatario");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Dirección");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Sede que genera carta");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(ABRE_TD);
		bldNuevo.append("Número de días de retraso");
		bldNuevo.append(CIERRA_TD);
		bldNuevo.append(CIERRA_TR);
		if (!cartasEnviadas.isEmpty()) {
			for (InfoBasicaAlertasDTO cartaEnviada : cartasEnviadas) {

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				bldNuevo.append(ABRE_TR);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getIdCaso());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getIdCartaPersona().toString());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(df.format(cartaEnviada.getFechaEnvio()));
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getNombrePersona());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getDireccion());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getNombreSede());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(cartaEnviada.getDiasHabiles());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(CIERRA_TR);

			}
			bldNuevo.append("</table>");
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_CARTAS, bldNuevo.toString());
			this.gestionarAlertasProcesoNotificacion(null, cartasEnviadas.get(0).getIdCentro(), null, alerta, null,
					filtros);
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id:16 : Vencimiento del término legal para
	 * cierre de caso entre 80 y 90 Días
	 * 
	 * @param alerta
	 */
	public void alertaVENCIE(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinCerrar = manejadorCaso.consultarCasosSinCerrarVencimientoLegalAlerta();
		this.alertaCasoSinCerrar(casosSinCerrar, alerta);
	}
	
	public void alertaCasoSinCerrar(List<InfoBasicaAlertasDTO> casosSinCerrar, Alerta alerta) {
		if (!casosSinCerrar.isEmpty()) {
			for (InfoBasicaAlertasDTO casoSinCerrar : casosSinCerrar) {
				Map<String, String> filtros = new HashMap<>();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoSinCerrar.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoSinCerrar.getNombreCaso());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, casoSinCerrar.getNombreCentro());
				filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_RADICACION,
						df.format(casoSinCerrar.getFechaRadicacionCaso()));
				filtros.put(UtilConstantes.PARAM_ALERTA_TIEMPO_CIERRE,
						casoSinCerrar.getDiasTranscurridosCaso().toString());
				this.gestionarAlertasProcesoNotificacion(casoSinCerrar.getIdCaso(), casoSinCerrar.getIdCentro(), null,
						alerta, null, filtros);
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}
	

	/**
	 * Metodo que ejecuta la alerta id:17 : Vencimiento del término legal para
	 * cierre de caso mayor a 90 Días
	 * 
	 * @param alerta
	 */
	public void alertaVENCI90(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinCerrar = manejadorCaso.consultarCasosSinCerrar90DiasVencimientoLegalAlerta();
		this.alertaVENICICasosSinCerrar(casosSinCerrar, alerta);
	}
	
	public void alertaVENICICasosSinCerrar(List<InfoBasicaAlertasDTO> casosSinCerrar, Alerta alerta) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Long idCentro = -1L;
		Map<String, String> filtros = new HashMap<>();
		StringBuilder bldNuevo = new StringBuilder();
		int tamanio = 0;
		if (!casosSinCerrar.isEmpty()) {

			for (InfoBasicaAlertasDTO casoSinCerrar : casosSinCerrar) {

				tamanio++;

				if (!idCentro.equals(casoSinCerrar.getIdCentro()) && !idCentro.equals(-1L)) {

					bldNuevo.append("</table>");
					filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_CIERRES, bldNuevo.toString());
					this.gestionarAlertasProcesoNotificacion(null, idCentro, null, alerta, null, filtros);
				}

				if (!idCentro.equals(casoSinCerrar.getIdCentro())) {

					bldNuevo = new StringBuilder();
					filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, casoSinCerrar.getNombreCentro());
					bldNuevo.append(SALTO_LINEA);
					bldNuevo.append(SALTO_LINEA);
					bldNuevo.append("<table border=1>");
					bldNuevo.append(ABRE_TR);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Código del Caso");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Fecha de Radicación");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Nombre del Caso");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Conciliador");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Sede");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(ABRE_TD);
					bldNuevo.append("Número de días transcurridos desde la radicación");
					bldNuevo.append(CIERRA_TD);
					bldNuevo.append(CIERRA_TR);

					idCentro = casoSinCerrar.getIdCentro() != null ? casoSinCerrar.getIdCentro() : -1;
				}
				bldNuevo.append(ABRE_TR);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(casoSinCerrar.getIdCaso());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(df.format(casoSinCerrar.getFechaRadicacionCaso()));
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(casoSinCerrar.getNombreCaso());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(casoSinCerrar.getNombrePersona());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(casoSinCerrar.getNombreSede());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(ABRE_TD);
				bldNuevo.append(casoSinCerrar.getDiasTranscurridosCaso());
				bldNuevo.append(CIERRA_TD);
				bldNuevo.append(CIERRA_TR);

				if (tamanio == casosSinCerrar.size()) {
					bldNuevo.append("</table>");
					filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_CIERRES, bldNuevo.toString());
					this.gestionarAlertasProcesoNotificacion(null, idCentro, null, alerta, null, filtros);
				}
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id:17 : Vencimiento del término legal para
	 * cierre de caso mayor a 90 Días
	 * 
	 * @param alerta
	 */
	public void alertaVEN90MED(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinCerrar = manejadorCaso
				.consultarCasosSinCerrar90DiasVencimientoLegalAlertaMed();

		this.alertaVENICICasosSinCerrar(casosSinCerrar, alerta);
	}

		/**
	 * Metodo que ejecuta la alerta id:17 : Vencimiento del término legal para
	 * cierre de caso de insolvencia
	 * 
	 * @param alerta
	 */
	public void alertaVENINSOL(Alerta alerta) {
		List<ParametroDeServicio> parametro = manejadorParametroDeServicio.consultarParametrosDeServicio(
			UtilDominios.DIAS_ENVIO_ALERTA_CIERRE_CASO, UtilConstantes.ID_SERVICIO_INSOLVENCIA);

		List<InfoBasicaAlertasDTO> casosSinCerrar = manejadorCaso
				.consultarCasosSinCerrarVencimientoLegalAlertaInsolvencia(parametro.get(0).getValor());

		this.alertaCasoSinCerrar(casosSinCerrar, alerta);	
	}

	/**
	 * Metodo que ejecuta la alerta id:50 : Lista de casos de convenio radicados en
	 * el dia por el apoderado.
	 * 
	 * @param alerta
	 */
	public void alertaCONRADD(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;
		List<InfoBasicaAlertasDTO> casosConvenio = manejadorCaso.consultarCasosConvenioRadicadosPorApoderadoAlerta(
				encabezadoTabla, UtilConstantes.PARAM_ALERTA_TABLA_CIERRE,
				UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_CONRADD, new Date());
		if (casosConvenio != null) {
			for (InfoBasicaAlertasDTO caso : casosConvenio) {
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_TABLA, caso.getTabla());
				List<Long> personas = new ArrayList<>();
				personas.add(caso.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(caso.getIdCaso(), null, null, alerta, personas, filtros);
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta id:49 : Lista de casos de convenio radicados en
	 * el dia por los apoderados.
	 * 
	 * @param alerta
	 */
	public void alertaRADAPOS(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;
		List<InfoBasicaAlertasDTO> casosConvenio = manejadorCaso.consultarCasosConvenioRadicadosPorApoderadosAlerta(
				encabezadoTabla, UtilConstantes.PARAM_ALERTA_TABLA_CIERRE,
				UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_RADAPOS, new Date());
		if (casosConvenio != null) {
			for (InfoBasicaAlertasDTO caso : casosConvenio) {
				List<Long> centros = manejadorSedeConvenio.consultarCentrosPorConvenio(caso.getIdConvenio());
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CONVENIO, caso.getNombreConvenio());
				filtros.put(UtilConstantes.PARAM_ALERTA_TABLA, caso.getTabla());
				String textoAlerta = alertaFacade.reemplazarTextoAlertas(alerta.getTexto(), filtros);
				List<PersonaBasicaDTO> notificados = alertaFacade.consultarPersonasNotificar(caso.getIdCaso(),
						alerta.getIdAlerta(), centros, caso.getIdConvenio(), null);
				this.envioConNotificacion(notificados, textoAlerta, alerta);
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta: Finalizacion de contrato de convenio.
	 * 
	 * @param alerta
	 */
	public void alertaFINCONT(Alerta alerta) {
		List<InfoBasicaAlertasDTO> conveniosPorFinalizar = manejadorConvenio
				.listarConveniosFinalizacionContrato(alerta.getValor(), alerta.getTipoPeriodicidad());
		this.infoalertContratos(conveniosPorFinalizar, alerta);
	}
	
	public void infoalertContratos(List<InfoBasicaAlertasDTO> conveniosPorFinalizar, Alerta alerta) {
		for (InfoBasicaAlertasDTO convenio : conveniosPorFinalizar) {
			List<Long> centros = manejadorSedeConvenio.consultarCentrosPorConvenio(convenio.getIdConvenio());
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_ANALISTA_ASIGNADO, convenio.getNombrePersona());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CONVENIO, convenio.getNombreConvenio());
			List<Long> personas = new ArrayList<>();
			personas.add(convenio.getIdPersona());
			String textoAlerta = alertaFacade.reemplazarTextoAlertas(alerta.getTexto(), filtros);
			List<PersonaBasicaDTO> notificados = alertaFacade.consultarPersonasNotificar(null, alerta.getIdAlerta(),
					centros, convenio.getIdConvenio(), personas);
			this.envioConNotificacion(notificados, textoAlerta, alerta);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta: FINCONT: Membresia por vencer.
	 * 
	 * @param alerta
	 */
	public void alertaVENMEMB(Alerta alerta) {
		DominioPK dominioPk = new DominioPK();
		dominioPk.setCodigo(UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		dominioPk.setDominio(UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		Dominio domEncabezadoTabla = manejadorDominio.buscar(dominioPk);
		ParametrosGenerales parametroDiaMinimo = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_MINIMO_DIA_EJECUCION_ALERTA_VENMEMB);
		ParametrosGenerales parametroDiaMaximo = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_MAXIMO_DIA_EJECUCION_ALERTA_VENMEMB);
		String encabezadoTabla = domEncabezadoTabla != null && domEncabezadoTabla.getDescripcion() != null
				? domEncabezadoTabla.getDescripcion()
				: UtilConstantes.PARAM_ALERTA_TABLA_ENCABEZADO;
		Long diaMinimo = parametroDiaMinimo != null && parametroDiaMinimo.getValorNumerico() != null
				? parametroDiaMinimo.getValorNumerico()
				: UtilConstantes.PARAM_ALERTA_VENMEMB_DIA_MINIMO_EJECUCION;
		Long diaMaximo = parametroDiaMaximo != null && parametroDiaMaximo.getValorNumerico() != null
				? parametroDiaMaximo.getValorNumerico()
				: UtilConstantes.PARAM_ALERTA_VENMEMB_DIA_MAXIMO_EJECUCION;

		List<InfoBasicaAlertasDTO> conciliadoresPorCentro = manejadorRolPersona
				.consultarConciliadoresVencimientoMembresia(encabezadoTabla, UtilConstantes.PARAM_ALERTA_TABLA_CIERRE,
						UtilConstantes.PARAM_ALERTA_TABLA_TEXTO_ENCABEZADO_VENMEMB, alerta.getTipoPeriodicidad(),
						diaMinimo, diaMaximo);
		for (InfoBasicaAlertasDTO conciliadorPorCentro : conciliadoresPorCentro) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, conciliadorPorCentro.getNombreCentro());
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA, conciliadorPorCentro.getTabla());
			this.gestionarAlertasProcesoNotificacion(null, conciliadorPorCentro.getIdCentro(), null, alerta, null,
					filtros);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo que ejecuta la alerta: (ANUEVCO) Alerta de creacion de nuevo contrato
	 * de convenio.
	 * 
	 * @param alerta
	 */
	public void alertaANUEVCO(Alerta alerta) {
		List<InfoBasicaAlertasDTO> conveniosFinalizados = manejadorConvenio
				.listarConveniosSinNuevoContrato(alerta.getValor(), alerta.getTipoAlerta());
		this.infoalertContratos(conveniosFinalizados, alerta);
	}

	/**
	 * Alerta de "Tramite inactivo", codigo de alerta: TRAINA
	 * 
	 * @param alerta, entidad Alerta
	 */
	public void alertaTRAINA(Alerta alerta) {
		LOGGER.info("Alerta de tramite inactivo");
		List<InfoBasicaAlertasDTO> casosSinTramitar = manejadorSuspension.alertaTramiteInactivo();
		for (InfoBasicaAlertasDTO casoFor : casosSinTramitar) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Alerta de "Tramite próximo a activarse", codigo de alerta: TRAACT
	 * 
	 * @param alerta, entidad Alerta
	 */
	public void alertaTRAACT(Alerta alerta) {
		LOGGER.info("Alerta de tramite proximo a activarse");
		List<InfoBasicaAlertasDTO> casosProximosAActivarse = manejadorSuspension
				.alertaTramiteProximoActivarse(alerta.getValor());
		for (InfoBasicaAlertasDTO casoFor : casosProximosAActivarse) {
			Map<String, String> filtros = new HashMap<>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_FIN_SUSPENSION, df.format(casoFor.getFecha()));
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Alerta de "Vencimiento para programar designación", codigo de alerta: VENDESG
	 * 
	 * @param alerta, entidad Alerta
	 */
	public void alertaVENDESG(Alerta alerta) {
		LOGGER.info("Alerta de Vencimiento para programar designación");
		List<InfoBasicaAlertasDTO> casosProximosAActivarse = manejadorAudiencia
				.alertaVencimientoProgramarDesignacion(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoFor : casosProximosAActivarse) {
			Map<String, String> filtros = new HashMap<>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_RADICACION, df.format(casoFor.getFechaRadicacionCaso()));
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * ALERTA peticiones especiales C20
	 * 
	 * @param alerta
	 */
	public void alertaATEPET(Alerta alerta) {
		List<InfoBasicaAlertasDTO> peticiones = manejadorPeticion.alertaPeticionesEspeciales();
		for (InfoBasicaAlertasDTO peticionFor : peticiones) {
			List<Long> personas = null;
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, peticionFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, peticionFor.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_TIPO_PETICION, peticionFor.getTipoPeticion());
			if (peticionFor.getValorParametro() != null) {
				personas = manejadorAlerta.consultarNotificadosPeticion(peticionFor.getIdCentro(),
						peticionFor.getValorParametro());
			}
			this.gestionarAlertasProcesoNotificacion(peticionFor.getIdCaso(), peticionFor.getIdCentro(), null, alerta,
					personas, filtros);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Metodo encargado de la ejecucion de la alerta de proximos sorteos A9
	 * 
	 * @param alerta
	 */
	public void alertaPROXSOR(Alerta alerta) {
		String proximosSorteos = manejadorCaso.alertaProximosSorteos();
		Map<String, String> filtros = new HashMap<>();
		filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_SORTEOS,
				proximosSorteos != null ? SALTO_LINEA + SALTO_LINEA + proximosSorteos : SALTO_LINEA + SALTO_LINEA + MENSAJE_SIN_SORTEO);
		this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, filtros);
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Alerta Registrar Minutos Diarios de Transcripcion, codigo de alerta: REGMINDI
	 */
	public void alertaREGMINDI(Alerta alerta) {

		List<InfoBasicaAlertasDTO> idPersonasRegistroPendiente = manejadorTranscripcion
				.alertaTranscripcionesRegistrarMinutosDiarios();
		List<Long> idPersonaNotificacion = new ArrayList<>();
		for (InfoBasicaAlertasDTO idPersonaRegistroPendiente : idPersonasRegistroPendiente) {
			idPersonaNotificacion.add(idPersonaRegistroPendiente.getIdPersona());
		}
		if (!idPersonaNotificacion.isEmpty()) {

			this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, idPersonaNotificacion, null);
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de la ejecucion de la alerta A-10-1 Comunicar designación
	 * COMDES
	 * 
	 * @param alerta
	 */
	public void alertaCOMDES(Alerta alerta) {
		List<InfoBasicaAlertasDTO> personasSinComunicar = manejadorEventoRolPersonaCaso.alertaComunicarDesignacion();
		for (InfoBasicaAlertasDTO personaSinComunicar : personasSinComunicar) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_SIN_COMUNICACION, personaSinComunicar.getTabla());
			gestionarAlertasProcesoNotificacion(null, null, null, alerta,
					Arrays.asList(personaSinComunicar.getIdPersona()), filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de la ejecucion de la alerta A-10-2 Actualizar
	 * Pronunciamiento COMDESP
	 * 
	 * @param alerta
	 */
	public void alertaCOMDESP(Alerta alerta) {
		List<InfoBasicaAlertasDTO> personasSinPronunciar = manejadorPronunciamiento.alertaActualizarPronunciamiento();
		for (InfoBasicaAlertasDTO personaSinPronunciar : personasSinPronunciar) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_SIN_ACTUALIZACION, personaSinPronunciar.getTabla());
			gestionarAlertasProcesoNotificacion(null, null, null, alerta,
					Arrays.asList(personaSinPronunciar.getIdPersona()), filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta A-11, consulta de la alerta Trámite sin movimiento, TSINMOV
	 * 
	 * @param alerta
	 */
	public void alertaTSINMOV(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinMovimiento = manejadorEvento
				.alertaTramiteSinMovimiento(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoSinMovimiento : casosSinMovimiento) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_NUMERO_DIAS, alerta.getValor().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_CASOS, casoSinMovimiento.getTabla());
			gestionarAlertasProcesoNotificacion(null, casoSinMovimiento.getIdCentro(), null, alerta, null, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta C-24, Cumplimiento de porcentaje de casos para seguimiento: PRCUMP
	 * 
	 * @param alerta
	 */
	public void alertaPRCUMP(Alerta alerta) {
		Boolean cumplimiento = manejadorCaso.alertaCumplimientoPorcentajeSeguimientoCasos();
		if (!cumplimiento) {
			gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, null);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta A-16, Registrar resultado de audiencia, REGRAUD
	 * 
	 * @param alerta
	 */
	public void alertaREGRAUD(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosAudienciaPendiente = manejadorAudiencia
				.alertaRegistrarResultadoAudiencia(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoAudienciaPendiente : casosAudienciaPendiente) {
			Map<String, String> filtros = new HashMap<>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			filtros.put(UtilConstantes.PARAM_ALERTA_TIPO_AUDIENCIA, casoAudienciaPendiente.getTipoAudiencia());
			filtros.put(UtilConstantes.PARAM_ALERTA_PARAMETROS_FECHA_AUDIENCIA,
					df.format(casoAudienciaPendiente.getFechaAudiencia()));
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoAudienciaPendiente.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoAudienciaPendiente.getNombreCaso());
			gestionarAlertasProcesoNotificacion(casoAudienciaPendiente.getIdCaso(),
					casoAudienciaPendiente.getIdCentro(), null, alerta, null, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Alerta A-19, Rendir cuentas RENDCUE
	 * 
	 * @param alerta
	 */
	public void alertaRENDCUE(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinGastosTribunal = manejadorAudiencia
				.alertaRendircuentas(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoSinGastosTribunal : casosSinGastosTribunal) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoSinGastosTribunal.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoSinGastosTribunal.getNombreCaso());
			gestionarAlertasProcesoNotificacion(casoSinGastosTribunal.getIdCaso(), casoSinGastosTribunal.getIdCentro(),
					null, alerta, Arrays.asList(casoSinGastosTribunal.getIdPersona()), filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta A-7, Fijación de audiencia de instalacion, FIJINST
	 * 
	 * @param alerta
	 */
	public void alertaFIJINST(Alerta alerta) {
		 List<InfoBasicaAlertasDTO> casosSinAudiencia = manejadorAudiencia.alertaFijacionAudienciaInstalacion(alerta.getTipoPeriodicidad(),
				alerta.getValor());

		if (!casosSinAudiencia.isEmpty()) {
			for (InfoBasicaAlertasDTO casoSinAudiencia : casosSinAudiencia) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoSinAudiencia.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoSinAudiencia.getNombreCaso());
				filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_RADICACION,
						df.format(casoSinAudiencia.getFechaRadicacionCaso()));

				gestionarAlertasProcesoNotificacion(casoSinAudiencia.getIdCaso(), casoSinAudiencia.getIdCentro(), null,
						alerta, null, filtros);
			}
		}

		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * A13 Metodo de ejecucion de alerta No Pronunciamiento del arbitro
	 * 
	 * @param alerta
	 * 
	 */

	public void alertaNPROARB(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosNoAceptados = manejadorRolPersonaCaso
				.alertaNoPronunciamientoArbitro(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoFor : casosNoAceptados) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_DESIGNACION, SALTO_LINEA + SALTO_LINEA + casoFor.getTabla());
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * A14 No Pronunciamiento del Secretario de tribunal
	 * 
	 * @param alerta
	 * 
	 */

	public void alertaNPROSEC(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosNoAceptados = manejadorRolPersonaCaso
				.alertaNoPronunciamientoSecretario(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoFor : casosNoAceptados) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoFor.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoFor.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_SECRETARIO, casoFor.getNombrePersona());
			this.gestionarAlertasProcesoNotificacion(casoFor.getIdCaso(), casoFor.getIdCentro(), null, alerta, null,
					filtros);

		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}

	}

	/**
	 * Arbitraje Alerta A8 nombrar suplente como principal
	 * 
	 * @param alerta
	 */
	public void alertaSUPARB(Alerta alerta) {
		List<InfoBasicaAlertasDTO> arbitros = manejadorRolPersonaCaso
				.alertaNombrarSuplenteArbitraje(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO arbitroFor : arbitros) {
			InfoRegistroPronunciamientoDTO infoPronunciamiento = new InfoRegistroPronunciamientoDTO();
			infoPronunciamiento.setFechaComunicacion(new Date());
			infoPronunciamiento.setPronunciamiento(new PronunciamientoDTO());
			infoPronunciamiento.getPronunciamiento()
					.setPronunciamiento(UtilDominios.TIPO_PRONUNCIAMIENTO_NO_SE_PRONUNCIA);
			infoPronunciamiento.getPronunciamiento().setFecha(new Date());
			infoPronunciamiento.setFechaDesignacion(arbitroFor.getFechaDesignacion());
			pronunciamientoFacade.guardarPronunciamiento(infoPronunciamiento, arbitroFor.getIdPersona(),
					arbitroFor.getIdCaso());
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Arbitraje Alerta A24
	 * 
	 * @param alerta
	 */
	public void alertaALACAUD(Alerta alerta) {

		List<InfoBasicaAlertasDTO> audienciasNoCerradas = manejadorAudiencia
				.alertaCierreDeAudiencias(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO audienciaNoCerrada : audienciasNoCerradas) {

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, audienciaNoCerrada.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, audienciaNoCerrada.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_PARAMETROS_FECHA_AUDIENCIA,
					df.format(audienciaNoCerrada.getFechaAudiencia()));

			this.gestionarAlertasProcesoNotificacion(audienciaNoCerrada.getIdCaso(), null, null, alerta, null, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta A-20, Aviso de 3 días sobre fecha de vencimiento de pago, codigos:
	 * VENPAGO
	 * 
	 * @param alerta
	 */
	public void alertaVENPAGO(Alerta alerta) {
		this.infoAlertaVENPAGO(alerta);		
	}
	
	public void infoAlertaVENPAGO(Alerta alerta) {
		List<InfoBasicaAlertasDTO> casosSinPagoHonorarios = manejadorPagoHonorarios
				.alertaAvisoVencimientoPagoHonorarios(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoSinPagoHonorarios : casosSinPagoHonorarios) {

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_PLAZO_CONSIGNACION,
					df.format(casoSinPagoHonorarios.getFecha()));

			List<Long> idPersonas = new ArrayList<>();
			List<InfoBasicaAlertasDTO> partesNotificar = manejadorPagoHonorarios
					.consultaPartesAlertaPagoHonorarios(casoSinPagoHonorarios.getIdCaso(), null);
			for (InfoBasicaAlertasDTO parteNotificar : partesNotificar) {
				if (parteNotificar != null && parteNotificar.getIdPersona() != null) {
					idPersonas.add(parteNotificar.getIdPersona());
				}
			}

			gestionarAlertasProcesoNotificacion(casoSinPagoHonorarios.getIdCaso(), casoSinPagoHonorarios.getIdCentro(),
					null, alerta, idPersonas, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * alerta A-21, Aviso de 1 día sobre fecha de vencimiento de pago, codigos:
	 * VENPAGF
	 * 
	 * @param alerta
	 */
	public void alertaVENPAGF(Alerta alerta) {
		this.infoAlertaVENPAGO(alerta);
	}

	/**
	 * Alerta (A-22) Aviso de no pago de la contraparte de los honorarios, codigo:
	 * PAGCONT
	 * 
	 * @param alerta
	 */
	public void alertaPAGCONT(Alerta alerta) {

		List<InfoBasicaAlertasDTO> casosSinPagoHonorarios = manejadorPagoHonorarios
				.alertaNoPagoHonorariosContraparte(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoSinPagoHonorarios : casosSinPagoHonorarios) {
			List<InfoBasicaAlertasDTO> partesNotificar = manejadorPagoHonorarios
					.consultaPartesAlertaPagoHonorarios(casoSinPagoHonorarios.getIdCaso(), alerta.getCodigo());

			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_ROL_NO_PAGO, casoSinPagoHonorarios.getNombreRol().toLowerCase());

			List<Long> idPersonas = new ArrayList<>();
			for (InfoBasicaAlertasDTO parteNotificar : partesNotificar) {
				if (parteNotificar != null && parteNotificar.getIdPersona() != null) {
					idPersonas.add(parteNotificar.getIdPersona());
				}
			}

			gestionarAlertasProcesoNotificacion(casoSinPagoHonorarios.getIdCaso(), casoSinPagoHonorarios.getIdCentro(),
					null, alerta, idPersonas, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Alerta (A-12) Vencimiento de término legal para cerrar caso (laudo)
	 * 
	 * @param alerta
	 */
	public void alertaVTERCIE(Alerta alerta) {

		List<InfoBasicaAlertasDTO> casosVencimientoDeTerminos = manejadorCaso
				.alertaCasosVencimientoTerminos(alerta.getTipoPeriodicidad(), alerta.getValor());
		for (InfoBasicaAlertasDTO casoVencimientoTerminos : casosVencimientoDeTerminos) {

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoVencimientoTerminos.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoVencimientoTerminos.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_RADICACION,
					df.format(casoVencimientoTerminos.getFechaRadicacionCaso()));
			filtros.put(UtilConstantes.PARAM_ALERTA_FECHA_INSTALACION,
					df.format(casoVencimientoTerminos.getFechaAudiencia()));

			this.gestionarAlertasProcesoNotificacion(casoVencimientoTerminos.getIdCaso(), null, null, alerta, null,
					filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta ARMEME árbitros eméritos.
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaARBEME(Alerta alerta) throws Exception {
		String textoAlerta = alerta.getTexto();
		List<AlertaEmeritoDTO> emeritos = manejadorPersona.consultarArbitrosEmeritos();
		List<PersonaBasicaDTO> notificados = manejadorAlerta.consultarPersonasNotificar(alerta.getIdAlerta());
		try {
			for (AlertaEmeritoDTO p : emeritos) {

				if (rolPersonaCasoFacade.inactivacionSegunMotivo(p.getIdPersona(), UtilDominios.ESTADO_ARBITROS_EMERITO,
						p.getIdRol(), p.getIdServicio())) {
					if(!notificados.isEmpty()){
						this.envioConNotificacion(notificados, p.getNombrePersona() + textoAlerta, alerta);
					}
					// Actualizar fecha de alerta
					logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
							UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
				} else {
					LOGGER.info("alertaARBEME no se cambió el estado de " + p.getNombrePersona() + " en el rol "
							+ p.getIdRol() + " para el servicio " + p.getIdServicio());
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/*
	 * -------------------------------------------------------------------------
	 * --------------- --------------- SECCION DE METODOS DE EJECUCION DE ALERTAS
	 * ALERTAS TIPO: AUTOMATICO
	 * -------------------------------------------------------------------------
	 * ---------------
	 *
	 */

	@Override
	public void alertaNOT_NPC(Long idCaso, Long idPersona, String metodoNombramiento) {
		String codigoAlerta = "NOT_" + metodoNombramiento;
		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
		filtroAlerta.setCodigo(codigoAlerta);
		filtroAlerta.setTipoAlerta(UtilDominios.TIPO_ALERTA_AUTOMATICA);
		List<Alerta> alertas = manejadorAlerta.consultarAlertasPorEstadoTipoServicio(filtroAlerta);

		if (!alertas.isEmpty()) {
			InfoBasicaAlertasDTO infoAlerta = manejadorCaso.alertaAceptacionConciliador(idCaso, metodoNombramiento);
			if (infoAlerta != null) {
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, infoAlerta.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, infoAlerta.getNombreCaso());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, infoAlerta.getNombreCentro());
				filtros.put(UtilConstantes.PARAM_ALERTA_PLAZO_CONTESTACION,
						infoAlerta.getValorParametroNumerico() != null
								? infoAlerta.getValorParametroNumerico().toString()
								: "");
				String textoAlerta = alertaFacade.reemplazarTextoAlertas(alertas.get(0).getTexto(), filtros);
				List<Long> centros = new ArrayList<>();
				centros.add(infoAlerta.getIdCentro());
				List<Long> personas = new ArrayList<>();
				personas.add(idPersona);

				List<PersonaBasicaDTO> notificados = alertaFacade.consultarPersonasNotificar(null,
						alertas.get(0).getIdAlerta(), centros, null, personas);

				this.envioConNotificacion(notificados, textoAlerta, alertas.get(0));
			}
		}

	}

	@Override
	public void alertaSOBASGM(Long idPersona) {

		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
		filtroAlerta.setCodigo(UtilConstantes.COD_ALERTA_SOBRE_ASIGNACION_MIN_TRANSCRIPCION);
		filtroAlerta.setTipoAlerta(UtilDominios.TIPO_ALERTA_AUTOMATICA);
		List<Alerta> alertas = manejadorAlerta.consultarAlertasPorEstadoTipoServicio(filtroAlerta);

		if (!alertas.isEmpty()) {
			List<InfoBasicaAlertasDTO> infoAlerta = manejadorTranscripcion
					.alertaTranscripcionesSobreAsignacion(idPersona);
			for (InfoBasicaAlertasDTO infoBasicaAlertasDTO : infoAlerta) {

				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_AUXILIAR, infoBasicaAlertasDTO.getNombrePersona());
				this.gestionarAlertasProcesoNotificacion(null, null, null, alertas.get(0), null, filtros);

			}

			alertaFacade.actualizarAlertaEjecutada(alertas.get(0).getIdAlerta());
		}

	}

	@Override
	public void alertaFIJAUT(Long idCaso) {

		AlertaDTO filtroAlerta = new AlertaDTO();
		filtroAlerta.setEstado(UtilDominios.ESTADO_ALERTA_ACTIVA);
		filtroAlerta.setCodigo(UtilConstantes.COD_ALERTA_FIJACION_AUDIENCIA_PRIMERA_TRAMITE);
		filtroAlerta.setTipoAlerta(UtilDominios.TIPO_ALERTA_AUTOMATICA);
		List<Alerta> alertas = manejadorAlerta.consultarAlertasPorEstadoTipoServicio(filtroAlerta);

		if (!alertas.isEmpty()) {

			Caso casoConAudienciaPrimeraTramite = manejadorCaso.buscar(idCaso);
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoConAudienciaPrimeraTramite.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoConAudienciaPrimeraTramite.getNombre());
			this.gestionarAlertasProcesoNotificacion(casoConAudienciaPrimeraTramite.getIdCaso(),
					casoConAudienciaPrimeraTramite.getSede().getIdCentro(), null, alertas.get(0), null, filtros);

		}

		alertaFacade.actualizarAlertaEjecutada(alertas.get(0).getIdAlerta());

	}

	/*
	 * -------------------------------------------------------------------------
	 * ---------- --------------- SECCION DE METODOS DE EJECUCION DE ALERTAS ALERTAS
	 * TIPO: NEGOCIO
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------------------
	 * ----------
	 */

	/**
	 * Metodo encargado de ejecutar la alerta de activacion de arbitro
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaACTARB(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio del arbitro");
		Caso caso = manejadorCaso.buscar(programacion.getIdCaso());
		RolPersonaCaso rolPersona = manejadorRolPersonaCaso.consultarPersonaAsignadoCaso(programacion.getIdPersona(),
				programacion.getIdCaso(), null);
		Date fechaHistorico = UtilOperaciones.adicionarMesesFecha(programacion.getFechaEjecucion(), -6);
		List<InformacionFiltro> filtros = new ArrayList<>();
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,
				HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_PERSONA,
				programacion.getIdPersona()));
		filtros.add(new InformacionFiltro(TipoFiltro.EXACTO,
				HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_ID_ROL,
				rolPersona.getRolPersonaCasoPK().getIdRol()));
		filtros.add(new InformacionFiltro(TipoFiltro.MENOR,
				HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA, fechaHistorico.getTime()));
		List<InformacionOrdenamiento> ordenamientos = new ArrayList<>();
		ordenamientos.add(new InformacionOrdenamiento(TipoOrdenamiento.DESCENDENTE,
				HistoricoEstadoPersonaRol.ENTIDAD_HISTORICO_ESTADO_PERSONA_ROL_FECHA));
		List<HistoricoEstadoPersonaRol> hEstadoPersonaRol = manejadorHistoricoEstadoPersonaRol.consultar(filtros,
				ordenamientos, null);

		List<EstadoPersonaRol> estadoPersonaRol = manejadorEstadoPersonaRol.consultarEstadoPersonaFiltros(
				programacion.getIdPersona(), rolPersona.getRolPersonaCasoPK().getIdRol(), null, null);

		try {
			if (estadoPersonaRol != null) {
				if (estadoPersonaRol.get(0).getIdMotivo().equals(UtilDominios.MOTIVO_NO_ACEPTACION)
						|| estadoPersonaRol.get(0).getIdMotivo().equals(UtilDominios.MOTIVO_NO_CONTESTACION)
						|| estadoPersonaRol.get(0).getIdMotivo()
								.equals(UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA)) {
					if (hEstadoPersonaRol != null) {
						if (rolPersonaCasoFacade.inactivacionSegunMotivo(programacion.getIdPersona(),
								hEstadoPersonaRol.get(0).getIdMotivo(), rolPersona.getRolPersonaCasoPK().getIdRol(), caso.getIdServicio())) {
							this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
									programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);
						} else {
							this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
									programacion.getIdProgramacionAlerta(),
									UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
						}

						notificacionFacade.generarLogAlerta(
								"Ha sido actualizado el estado del operador : " + programacion.getIdCaso(),
								UtilDominios.ESTADO_NOTIFICACION_ENVIADA, programacion.getIdAlerta(),
								programacion.getIdPersona(), new Date());
					} else {
						// si no encuentra estado anterior se habilita
						if (rolPersonaCasoFacade.inactivacionSegunMotivo(programacion.getIdPersona(),
								UtilDominios.ESTADO_ARBITROS_HABILITADO, rolPersona.getRolPersonaCasoPK().getIdRol(), caso.getIdServicio())) {
							this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
									programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);
						} else {
							this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
									programacion.getIdProgramacionAlerta(),
									UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
						}
					}
				} else {
					this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
							programacion.getIdProgramacionAlerta(),
							UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
				}
			}

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta de nombramiento suplente de
	 * conciliador
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaCSUPLEN(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio del suplente");
		String[] estadosList = { UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR };
		RolPersonaCaso rolPersonaConciliador = manejadorRolPersonaCaso.consultarPersonaAsignadoCaso(
				programacion.getIdPersona(), programacion.getIdCaso(), Arrays.asList(estadosList));
		if (rolPersonaConciliador != null) {
			pronunciamientoFacade.alertaNombramientoSuplente(programacion.getIdCaso(), programacion.getIdPersona(),
					rolPersonaConciliador.getRolPersonaCasoPK().getIdRol());

			notificacionFacade.generarLogAlerta("Nombramiento suplente idCaso: " + programacion.getIdCaso(),
					UtilDominios.ESTADO_NOTIFICACION_ENVIADA, programacion.getIdAlerta(), programacion.getIdPersona(),
					new Date());

			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}

	}

	/**
	 * Metodo encargado de ejecutar la alerta de primer recordatorio para aceptacion
	 * del caso
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	public void alertaRECACE1(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio primer recordatorio aceptacion");

		String nombreDominio = "";

		InfoBasicaAlertasDTO conciliadorPendiente = manejadorRolPersonaCaso
				.alertaRecordatorioAceptacionCaso(programacion.getIdCaso(), programacion.getIdPersona());

		RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultaRolPersonaId(programacion.getIdPersona(),
				programacion.getIdCaso(), null);

		if (rolPersonaCaso != null && rolPersonaCaso.getRol() != null && rolPersonaCaso.getRol().getNombre() != null) {
			nombreDominio = manejadorDominio.consultarNombreRol(rolPersonaCaso.getRol().getNombre());
		}

		if (conciliadorPendiente != null) {
			Map<String, String> filtros = new HashMap<String, String>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, conciliadorPendiente.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, conciliadorPendiente.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, conciliadorPendiente.getNombreCentro());
			filtros.put(UtilConstantes.PARAM_ALERTA_PARAMETROS_HORAS_ACEPTACION,
					conciliadorPendiente.getValorParametroNumerico() != null
							? Long.toString((conciliadorPendiente.getValorParametroNumerico() - 1L))
							: "0");

			if (nombreDominio != null && !nombreDominio.isEmpty()) {
				filtros.put(UtilConstantes.PARAM_ALERTA_ROL, nombreDominio);
			}

			List<Long> personas = new ArrayList<>();
			personas.add(programacion.getIdPersona());
			this.gestionarAlertasProcesoNotificacion(conciliadorPendiente.getIdCaso(),
					conciliadorPendiente.getIdCentro(), null, programacion.getAlerta(), personas, filtros);
			this.persistirMensajesEjecucionAlerta("Alerta ejecutada correctamente", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}

	}

	/**
	 * Metodo encargado de ejecutar la alerta del ultimo recordatorio para
	 * aceptacion del caso
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void alertaRECACEU(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio ultimo recordatorio aceptacion del caso");

		String nombreDominio = "";
		InfoBasicaAlertasDTO conciliadorPendiente = manejadorRolPersonaCaso
				.alertaRecordatorioAceptacionCaso(programacion.getIdCaso(), programacion.getIdPersona());

		RolPersonaCaso rolPersonaCaso = manejadorRolPersonaCaso.consultaRolPersonaId(programacion.getIdPersona(),
				programacion.getIdCaso(), null);

		if (rolPersonaCaso != null && rolPersonaCaso.getRol() != null && rolPersonaCaso.getRol().getNombre() != null) {
			nombreDominio = manejadorDominio.consultarNombreRol(rolPersonaCaso.getRol().getNombre());
		}

		if (conciliadorPendiente != null) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, conciliadorPendiente.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, conciliadorPendiente.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, conciliadorPendiente.getNombreCentro());
			if (nombreDominio != null && !nombreDominio.isEmpty()) {
				filtros.put(UtilConstantes.PARAM_ALERTA_ROL, nombreDominio);
			}
			List<Long> personas = new ArrayList<>();
			personas.add(programacion.getIdPersona());

			this.gestionarAlertasProcesoNotificacion(conciliadorPendiente.getIdCaso(),
					conciliadorPendiente.getIdCentro(), null, programacion.getAlerta(), personas, filtros);
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}

	}

	public void alertaPROAUC(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio programacion de audiencia");
		InfoBasicaAlertasDTO casoSinAudiencia = manejadorAudiencia
				.audienciasPendienteCasoConciliador(programacion.getIdPersona(), programacion.getIdCaso());

		if (casoSinAudiencia != null) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, programacion.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoSinAudiencia.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, casoSinAudiencia.getNombreCentro());
			gestionarAlertasProcesoNotificacion(casoSinAudiencia.getIdCaso(), casoSinAudiencia.getIdCentro(), null,
					programacion.getAlerta(), Arrays.asList(programacion.getIdPersona()), filtros);
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta de realizacion de control de legalidad
	 * al caso
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaCONTLEG(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio de realizacion de control de legalidad al caso 2");
		InfoBasicaAlertasDTO casoControlLegalidadPendiente = manejadorRolPersonaCaso
				.alertaControlLegalidad(programacion.getIdCaso(), programacion.getIdPersona());

		if (casoControlLegalidadPendiente != null) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoControlLegalidadPendiente.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoControlLegalidadPendiente.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CENTRO, casoControlLegalidadPendiente.getNombreCentro());
			List<Long> personas = new ArrayList<>();
			personas.add(programacion.getIdPersona());

			this.gestionarAlertasProcesoNotificacion(casoControlLegalidadPendiente.getIdCaso(),
					casoControlLegalidadPendiente.getIdCentro(), null, programacion.getAlerta(), personas, filtros);
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta de documentos sin digitalizar DOCNODI
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaDOCNODI(ProgramacionAlerta programacion) throws Exception {

		List<InfoBasicaAlertasDTO> documentosPendientesCaso = manejadorDocumento.alertaDocumentosSinDigitalizar(
				programacion.getIdCaso(), programacion.getIdPersona(), programacion.getIdDocumento());

		if (!documentosPendientesCaso.isEmpty()) {
			for (InfoBasicaAlertasDTO documentoPendienteDigitalizador : documentosPendientesCaso) {
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO,
						documentoPendienteDigitalizador.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, documentoPendienteDigitalizador.getNombreCaso());
				List<Long> personas = new ArrayList<>();
				personas.add(programacion.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(documentoPendienteDigitalizador.getIdCaso(),
						documentoPendienteDigitalizador.getIdCentro(), null, programacion.getAlerta(), personas,
						filtros);
				this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
						programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

			}

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta de corrección de actas o constancias
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaCORRACT(ProgramacionAlerta programacion) throws Exception {
		
		List<InfoBasicaAlertasDTO> correccionActaConstancia = manejadorDevolucionDocumentoResultado
				.alertaCorreccionActaConstancia(programacion.getIdCaso(), programacion.getIdPersona());

		if (correccionActaConstancia != null) {
			for (InfoBasicaAlertasDTO casoActaConstancia : correccionActaConstancia) {				
				Map<String, String> filtros = new HashMap<>();
				filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoActaConstancia.getIdCaso().toString());
				filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoActaConstancia.getNombreCaso());
				List<Long> personas = new ArrayList<>();
				personas.add(programacion.getIdPersona());
				this.gestionarAlertasProcesoNotificacion(casoActaConstancia.getIdCaso(),
						casoActaConstancia.getIdCentro(), null, programacion.getAlerta(), personas, filtros);

			}
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta SINTRA1 sin tramitar por el abogado.
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaSINTRA1(ProgramacionAlerta programacion) {
		this.infoAlertaSINTRA(programacion);
	}
	
	public void infoAlertaSINTRA(ProgramacionAlerta programacion) {
		
		InfoBasicaAlertasDTO casoSinTramitar = manejadorRolPersonaCaso
				.consultarSiExisteTramiteCasoAbogado(programacion.getIdCaso(), programacion.getIdPersona());
		ParametrosGenerales parametroTiempoEstudio = manejadorParametrosGenerales
				.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_TIEMPO_ESTUDIO_CASO_ARBITRAJE);
		Alerta alerta = manejadorAlerta.buscar(programacion.getIdAlerta());
		Long tiempoEstudioCaso = parametroTiempoEstudio != null && parametroTiempoEstudio.getValorNumerico() != null
				? parametroTiempoEstudio.getValorNumerico()
				: UtilConstantes.PARAM_ALERTA_TIEMPO_ESTUDIO_CASO_ARBITRAJE;
		Long tiempoLimiteEstudio = tiempoEstudioCaso - alerta.getValor();
		
		if (casoSinTramitar != null) {
			Map<String, String> filtros = new HashMap<>();
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_ABOGADO, casoSinTramitar.getNombreAbogado());
			filtros.put(UtilConstantes.PARAM_ALERTA_NUMERO_HORAS, tiempoLimiteEstudio.toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, casoSinTramitar.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, casoSinTramitar.getNombreCaso());
			List<Long> personas = new ArrayList<>();
			personas.add(programacion.getIdPersona());

			this.gestionarAlertasProcesoNotificacion(casoSinTramitar.getIdCaso(), casoSinTramitar.getIdCentro(), null,
					programacion.getAlerta(), personas, filtros);
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
	}

	/**
	 * Metodo encargado de ejecutar la alerta SINTRA2 sin tramitar por el abogado.
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	public void alertaSINTRA2(ProgramacionAlerta programacion){
		this.infoAlertaSINTRA(programacion);
	}

	public void alertaMVAR() {
		manejadorArchivo.moverArchivos();
	}

	/**
	 * Metodo encargado de la ejecucion de la alerta de proximos sorteos A9 Para
	 * 
	 * @param alerta
	 */
	public void alertaPROXSORA(Alerta alerta) {
		String proximosSorteos = manejadorCaso.alertaProximosSorteos();
		Map<String, String> filtros = new HashMap<>();
		filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_SORTEOS,
				proximosSorteos != null ? SALTO_LINEA + SALTO_LINEA + proximosSorteos : SALTO_LINEA + SALTO_LINEA + MENSAJE_SIN_SORTEO);
		if (proximosSorteos != null) {
			this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de la ejecucion de la alerta de notificacion de vencimiento
	 * de la orden de pago para casos de mediacion
	 * 
	 * @param programacionAlerta
	 */
	public void alertaNOTMED(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio vencimiento orden de pago alertaNOTMED");

		Map<String, String> filtros = new HashMap<>();

		if (manejadorSolicitudServicio.validarPagoSolicitudServicio(programacion.getIdCaso())) {

			SolicitudServicio solicitudServicio = solicitudServicioFacade
					.consultarSolicitudPorOrden(programacion.getIdCaso());

			List<String> nombreParametros = new ArrayList<>();
			nombreParametros.add(UtilConstantes.ENLACE);

			List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, solicitudServicio.getIdServicio(),
							UtilConstantes.ENLACE);

			filtros.put(UtilConstantes.PARAM_ENLACE, parametroDeServicioList.get(0).getValor());
			filtros.put(UtilConstantes.PARAM_ALERTA_NUMERO_ORDEN, programacion.getIdCaso().toString());
			gestionarAlertasProcesoNotificacion(null, null, null, programacion.getAlerta(),
					Arrays.asList(programacion.getIdPersona()), filtros);
		}

		this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(), programacion.getIdProgramacionAlerta(),
				UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

	}

	public void alertaACTCASO(ProgramacionAlerta programacion) throws Exception {

		casoFacade.actualizarEstdoSuspension(programacion.getIdCaso());

		this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(), programacion.getIdProgramacionAlerta(),
				UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

	}

	public void alertaNOTVENAR(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio vencimiento pronunciamiento arbitro");

		InfoRegistroPronunciamientoDTO infoRegistroPronunciamientoDTO = pronunciamientoFacade
				.consultarPronunciamiento(programacion.getIdPersona(), programacion.getIdCaso());

		if (infoRegistroPronunciamientoDTO.getPronunciamiento() == null) {

			Map<String, String> filtros = new HashMap<>();

			Caso caso = casoFacade.buscar(programacion.getIdCaso());

			filtros.put(UtilConstantes.PARAM_CODIGO_CASO, programacion.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_NOMBRE_CASO, caso.getNombre());

			gestionarAlertasProcesoNotificacion(null, null, null, programacion.getAlerta(),
					Arrays.asList(programacion.getIdPersona()), filtros);
		}

		this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(), programacion.getIdProgramacionAlerta(),
				UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

	}

	/**
	 * Metodo encargado de la ejecucion de la alerta de resultadoa del sorteo
	 * 
	 * @param alerta
	 */
	public void alertaRESSOR(Alerta alerta) {
		String resultadoSorteos = manejadorSorteo.alertaResultadoSorteo();
		Map<String, String> filtros = new HashMap<>();
		filtros.put(UtilConstantes.PARAM_FECHA_ACTUAL, new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_SORTEOS,
				resultadoSorteos != null ? " <br/> <br/> " + resultadoSorteos : " <br/> <br/> " + MENSAJE_SIN_SORTEO);
		if (resultadoSorteos != null) {
			this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, filtros);
		}
		try {
			logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
					UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	public void alertaNOTVENPA(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio vencimiento orden de pago alertaNOTVENPA");

		Map<String, String> filtros = new HashMap<>();

		PagoCaso pagoCaso = pagoCasoFacade.obtenerPagoCasoPorCaso(programacion.getIdCaso());

		if (pagoCaso == null) {

			Caso caso = casoFacade.buscar(programacion.getIdCaso());
					
			List<Long> personasNotificar = personaRolAlertaFacade.consultaPersonasNotificarPorIdAlerta(programacion.getIdAlerta());
												
			if (programacion.getIdPersona() != null) {
				personasNotificar.add(programacion.getIdPersona());
			}					
			
			List<String> nombreParametros = new ArrayList<>();
			nombreParametros.add(UtilConstantes.ENLACE);

			List<ParametroDeServicioDTO> parametroDeServicioList = parametroDeServicioFacade
					.consultarParametroDeServicio(nombreParametros, caso.getIdServicio(), UtilConstantes.ENLACE);

			filtros.put(UtilConstantes.PARAM_ENLACE, parametroDeServicioList.get(0).getValor());
			gestionarAlertasProcesoNotificacion(null, null, null, programacion.getAlerta(),
					personasNotificar, filtros);
		}

		this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(), programacion.getIdProgramacionAlerta(),
				UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

	}
	
	public void alertaNOTVENCC(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio vencimiento orden de pago alertaNOTVENCC");
		int dias = 0;
		
		Caso caso = casoFacade.buscar(programacion.getIdCaso());
		Date fechaAceptacionConciliador = null;
		for(RolPersonaCaso personas : caso.getRolPersonaCasoList()) {
			if(personas.getRol().getNombre().equals(UtilDominios.ROL_PERSONA_CONCILIADOR)) {
				fechaAceptacionConciliador = personas.getPronunciamiento().getFecha();
			}
		}
		
		List<ParametroDeServicio> parametro = manejadorParametroDeServicio.consultarParametrosDeServicio(
				UtilDominios.DIAS_LIMITE_PARA_CIERRE_DE_CASO, UtilConstantes.ID_SERVICIO_INSOLVENCIA);
		List<Evento> eventos = manejadorEvento.consultarEventosCaso(caso.getIdCaso());
		for(Evento evento : eventos) {
			if(evento.getObservaciones().equals(UtilConstantes.ESTADO_CASO_ADMITIDO)){
				dias = (int) (UtilOperaciones.adicionarDiasFecha(evento.getFechaEvento(),
						Integer.parseInt(parametro.get(0).getValor())).getTime() - new Date().getTime());
			}
		}
		
		
		if(!caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_SUSPENDIDO) 
				&& !caso.getEstadoCaso().equals(UtilDominios.ESTADO_CASO_CERRADO)) {
			
			Map<String, String> filtros = new HashMap<>();
			filtros.put("[CODIGO_CASO]", caso.getIdCaso().toString());
			filtros.put("[NOMBRE_CASO]", caso.getNombre());
			filtros.put("[NOMBRE_CENTRO]", caso.getSede().getNombre());
			filtros.put("[FECHA_ACEPTACION_CONCILIADOR]", ""+fechaAceptacionConciliador);
			filtros.put("[TIEMPO_CIERRE]", ""+dias);
			
		}
		
	}	
		
	public void alertaLIBLIST(Alerta alerta) {
		
		List<SorteoLiberacionListaDTO> resultadoLiberacionLista = manejadorSorteo.consultarSorteosRealizadosHoyConLiberacionLista();
		
		for (SorteoLiberacionListaDTO sorteoLiberacionListaDTO : resultadoLiberacionLista) {
			
			Map<String, String> filtros = new HashMap<>();
			String tablaPersonasLiberadas = generarTablaPersonasLiberadasPorSorteo(sorteoLiberacionListaDTO.getIdSorteo());
			String tipoLista = sorteoLiberacionListaDTO.getTipoLista().equals("No aplica") ? "" : sorteoLiberacionListaDTO.getTipoLista();
			
			filtros.put(UtilConstantes.PARAM_ALERTA_TIPO_LISTA, tipoLista);
			filtros.put(UtilConstantes.PARAM_ALERTA_TIPO_SERVICIO, sorteoLiberacionListaDTO.getServicio());
			filtros.put(UtilConstantes.PARAM_ALERTA_TIPO_MATERIA, sorteoLiberacionListaDTO.getMateria());
			filtros.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, sorteoLiberacionListaDTO.getIdCaso().toString());
			filtros.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, sorteoLiberacionListaDTO.getNombreCaso());
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_OPERADORES_LIBERADOS, tablaPersonasLiberadas);
			
			this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, filtros);
			
			try {
				logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
						UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		}		
	}
	
	public String generarTablaPersonasLiberadasPorSorteo(long idSorteo) {
		
		List<String> personasLiberadas = manejadorElegible.consultarPersonasLiberadasPorSorteo(idSorteo);
		StringBuilder tabla = new StringBuilder();
		tabla.append("<html><body><table>" + 
						"<tr style='background-color: #CEEFDF'>" + 
							"<td height='59' align='center'><p style='font:20px/25px Arial, Helvetica, sans-serif; color:##053F2D;'>Operadores Liberados</p></td>" );
		tabla.append(CIERRA_TR);
		
		for (String personaLiberada : personasLiberadas) {
			tabla.append(ABRE_TR);
			tabla.append(ABRE_TD);
			tabla.append(personaLiberada);
			tabla.append(CIERRA_TD);
			tabla.append(CIERRA_TR);
		}		
		tabla.append("</table></body></html>");
		return tabla.toString();
	}
	
	/**
	 * Metodo encargado de ejecutar la alerta de primer recordatorio para aceptacion
	 * del caso EQUIDAD
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	public void alertaRECACE1E(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio primer recordatorio aceptacion EQUIDAD");		
		String nombreDominioE = "";		
		InfoBasicaAlertasDTO conciliadorPendienteE = manejadorRolPersonaCaso
				.alertaRecordatorioAceptacionCaso(programacion.getIdCaso(), programacion.getIdPersona());		
		RolPersonaCaso rolPersonaCasoE = manejadorRolPersonaCaso.consultaRolPersonaId(programacion.getIdPersona(),
				programacion.getIdCaso(), null);

		if (rolPersonaCasoE != null && rolPersonaCasoE.getRol() != null && rolPersonaCasoE.getRol().getNombre() != null) {
			nombreDominioE = manejadorDominio.consultarNombreRol(rolPersonaCasoE.getRol().getNombre());			
		}

		if (conciliadorPendienteE != null) {			
			Map<String, String> filtrosE = new HashMap<String, String>();
			filtrosE.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, conciliadorPendienteE.getIdCaso().toString());
			filtrosE.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, conciliadorPendienteE.getNombreCaso());
			
			if (nombreDominioE != null && !nombreDominioE.isEmpty()) {
				filtrosE.put(UtilConstantes.PARAM_ALERTA_ROL, nombreDominioE);
			}

			List<Long> personasE = new ArrayList<>();
			personasE.add(programacion.getIdPersona());			
			this.gestionarAlertasProcesoNotificacion(conciliadorPendienteE.getIdCaso(),
					conciliadorPendienteE.getIdCentro(), null, programacion.getAlerta(), personasE, filtrosE);			
			this.persistirMensajesEjecucionAlerta("Alerta ejecutada correctamente", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}		

	}

	/**
	 * Metodo encargado de ejecutar la alerta del ultimo recordatorio para
	 * aceptacion del caso EQUIDAD
	 * 
	 * @param programacion
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void alertaRECACEUE(ProgramacionAlerta programacion) throws Exception {
		LOGGER.info("Alerta de negocio ultimo recordatorio aceptacion del caso EQUIDAD");		

		String nombreDominioEq = "";		
		InfoBasicaAlertasDTO conciliadorPendienteEq = manejadorRolPersonaCaso
				.alertaRecordatorioAceptacionCaso(programacion.getIdCaso(), programacion.getIdPersona());		
		RolPersonaCaso rolPersonaCasoEq = manejadorRolPersonaCaso.consultaRolPersonaId(programacion.getIdPersona(),
				programacion.getIdCaso(), null);
		 
		if (rolPersonaCasoEq != null && rolPersonaCasoEq.getRol() != null && rolPersonaCasoEq.getRol().getNombre() != null) {
			nombreDominioEq = manejadorDominio.consultarNombreRol(rolPersonaCasoEq.getRol().getNombre());
		}

		if (conciliadorPendienteEq != null) {			
			Map<String, String> filtrosEq = new HashMap<>();
			filtrosEq.put(UtilConstantes.PARAM_ALERTA_CODIGO_CASO, conciliadorPendienteEq.getIdCaso().toString());
			filtrosEq.put(UtilConstantes.PARAM_ALERTA_NOMBRE_CASO, conciliadorPendienteEq.getNombreCaso());			
			List<Long> personasEq = new ArrayList<>();
			personasEq.add(programacion.getIdPersona());			
			this.gestionarAlertasProcesoNotificacion(conciliadorPendienteEq.getIdCaso(),
					conciliadorPendienteEq.getIdCentro(), null, programacion.getAlerta(), personasEq, filtrosEq);			
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_EJECUTADA);

		} else {
			this.persistirMensajesEjecucionAlerta("", programacion.getIdAlerta(),
					programacion.getIdProgramacionAlerta(), UtilDominios.PROGRAMACION_ALERTA_CONDICION_NO_CUMPLIDA);
		}
		

	}
	
	/**
	 * Metodo que ejecuta la alerta id:67 : Notificacion rechazos conciliadores equidad
	 * 
	 * @param alerta
	 */
	public void alertaRECONEQ(Alerta alerta) {		
			Map<String, String> filtros = new HashMap<>();
			String tablaConciliadoresRechazo = generarTablaConciliadoresEquidadRechazo();						
			filtros.put(UtilConstantes.PARAM_ALERTA_TABLA_CON_EQUIDAD_REC, tablaConciliadoresRechazo);			
			this.gestionarAlertasProcesoNotificacion(null, null, null, alerta, null, filtros);			
			try {
				logAlertasFacade.generarLogAlerta("", null, alerta.getIdAlerta(), null,
						UtilDominios.ESTADO_EJECUCION_ALERTA_EJECUTADA);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}				
	}
	
	public String generarTablaConciliadoresEquidadRechazo() {
		
		List<String> conciliadores = manejadorRolPersonaCaso.listaConciliadoresEquidadRechazo();
		StringBuilder tablaC = new StringBuilder();
		tablaC.append("<html><body><table>" + 
						"<tr style='background-color: #CEEFDF'>" + 
							"<td height='59' align='center'><p style='font:20px/25px Arial, Helvetica, sans-serif; color:##053F2D;'>Conciliadores</p></td>"); 
							tablaC.append(CIERRA_TR);
		for (String p : conciliadores) {			
			tablaC.append(ABRE_TR);
			tablaC.append(ABRE_TD);
			tablaC.append(p);
			tablaC.append(CIERRA_TD);
			tablaC.append(CIERRA_TR);
		}		
		tablaC.append("</table></body></html>");		
		return tablaC.toString();
	}
}
