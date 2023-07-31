package com.ccb.simasc.comun.fachada.implementacion;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorDiaFestivo;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorElegible;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorPreseleccionado;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.DiaSorteo;
import com.ccb.simasc.transversal.entidades.Elegible;
import com.ccb.simasc.transversal.entidades.ElegiblePK;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Preseleccionado;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 *
 * @author dpachon Clase que engloba toda la funcionalidad de negocio en lo
 *         referente a sorteos
 */
@Stateless
@LocalBean
public class RealizacionSorteoFacade implements Serializable, IRealizacionSorteoFacade {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(RealizacionSorteoFacade.class.getName());

	@EJB
	private ManejadorCaso manejadorCaso;

	@EJB
	private ManejadorSorteo manejadorSorteo;

	@EJB
	private ManejadorDiaFestivo manejadorDiaFestivo;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorElegible manejadorElegible;

	@EJB
	private ManejadorPreseleccionado manejadorPreseleccionado;

	@EJB
	ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;

	@EJB
	ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorAudiencia manejadorAudiencia;

	@EJB
	ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;

	@EJB
	ManejadorEvento manejadorEvento;

	/**
	 * Recurso que provee los servicios de parametros generales
	 */
	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;

	@PersistenceContext
	private transient EntityManager em;

	@EJB
	private ManejadorMateria manejadorMateria;
	
	public List<Audiencia> obtenerAudienciasSorteoDelDiaYSigDiaHabil(String tipo) {
		Calendar fechaActual = Calendar.getInstance();
		List<Audiencia> audienciasDia = new ArrayList<>();
//		obtenemos la hora limite establecida para la consulta de sorteos del día habíl siguiente		
		long horaDeConsulta = (long) manejadorParametrosGenerales.obtenerValorParametroPorCodigoTipo(
				UtilDominios.CODIGO_PARAMETRO_GENERAL_CONSULTA_ARBITROS_DISPONIBLES,
				UtilDominios.TIPO_PARAMETRO_GENERAL_SORTEO_PENDIENTE,
				ParametrosGenerales.ENTIDAD_PARAMETROS_GENERALES_VALOR_NUMERICO);
		if (horaDeConsulta == 0)
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR342.toString()));

		List<Audiencia> audiencias = this.consultarAudienciasSorteoDelDia(fechaActual.getTime());
		for (Iterator iter = audiencias.iterator(); iter.hasNext();) {
			Audiencia audiencia = (Audiencia) iter.next();
			Calendar fechaAudiencia = Calendar.getInstance();
			fechaAudiencia.setTime(audiencia.getHoraInicio());
//			se consulta si es un sorteo del día actual 

			if (audiencia.getTipoAudiencia()
					.equals(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_INTERNACIONAL_DE_DESIGNACION)) {
				audiencia.getCaso().setNombre(UtilConstantes.NOMBRE_CASO_ARBITRAJE_INTERNACIONAL);
			}

			if ((fechaActual.get(Calendar.DAY_OF_MONTH) == fechaAudiencia.get(Calendar.DAY_OF_MONTH) 
					|| fechaActual.get(Calendar.HOUR_OF_DAY) >= ((Number) horaDeConsulta).intValue())
					&& audiencia.getTipoSorteo().equals(tipo)) {
				audienciasDia.add(audiencia);
			} 
		}
		return audienciasDia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.comun.fachada.implementacion.ISorteoFacade#
	 * consultarCasosDelDia(java.util.Date)
	 */
	@Override
	public List<Audiencia> consultarAudienciasSorteoDelDia(Date fecha) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(fecha);
		startCal.add(Calendar.DAY_OF_MONTH, 1);
		int diasNohabiles = consultarDiasNoHabilesPorFecha(startCal.getTime());
		startCal.add(Calendar.DAY_OF_MONTH, diasNohabiles);
		Date siguienteFechaHabil = startCal.getTime();
		return manejadorCaso.consultarAudienciasSorteoDelDia(fecha, siguienteFechaHabil);
	}

	/**
	 * Metodo que devuelve la cuenta de los dias no habiles desde una fecha pasada
	 * hasta el proximo dia habil
	 * 
	 * @param fecha
	 * @return
	 */
	public int consultarDiasNoHabilesPorFecha(Date fecha) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(fecha);
		int anio = startCal.get(Calendar.YEAR);
		List<Date> listadoDiasFestivos = manejadorDiaFestivo.obtenerFestivosAnio(anio);
		int diasNoHabiles = 0;

		boolean isWorkDay = false;
		while (!isWorkDay) {
			if (startCal.get(Calendar.YEAR) != anio) {
				anio = startCal.get(Calendar.YEAR);
				listadoDiasFestivos = manejadorDiaFestivo.obtenerFestivosAnio(anio);
			}
			if ((startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
					&& (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
				isWorkDay = true;
				for (Iterator<Date> iter = listadoDiasFestivos.iterator(); iter.hasNext();) {
					Date diaFestivo = (Date) iter.next();
					Date date = UtilOperaciones.truncDate(startCal.getTime());
					if (date.compareTo(UtilOperaciones.truncDate(diaFestivo)) == 0) {
						isWorkDay = false;
						break;
					}
				}
				if (!isWorkDay) {
					diasNoHabiles++;
				}
			} else {
				isWorkDay = false;
				if(startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
					diasNoHabiles++;
				}				
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return diasNoHabiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ccb.simasc.comun.fachada.implementacion.ISorteoFacade#cancelarSorteoCaso(
	 * com.ccb.simasc.transversal.entidades.Caso, java.util.Date)
	 */
	@Override
	public void cancelarSorteoCaso(Audiencia audiencia) throws SimascException {
		manejadorAudiencia.cancelarAudiencia(audiencia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.comun.fachada.implementacion.ISorteoFacade#
	 * consultarParametrosSorteo()
	 */
	@Override
	public ParametroSorteo consultarParametrosSorteo() {
		return manejadorSorteo.consultarParametrosSorteo();
	}

	/**
	 * valida que los arbitros preseleccionados no sea menor a la requerida por el
	 * caso
	 * 
	 * @param personas
	 * @param audiencia
	 * @param tipoPreseleccionados ** si es preseleccion independiente (PRI , SUP)
	 *                             de lo contrario NULL
	 * @return true si la cantidad es suficiente, false de los contrario
	 */
	@Override
	public boolean validarCantidadMinArb(List<Persona> personas, Audiencia audiencia, String tipoPreseleccionados) {
		boolean valido = true;
		Integer totalRequeridos = Integer.valueOf(0);
		if (tipoPreseleccionados == null) {
			totalRequeridos = audiencia.getCantidadPrincipales() + audiencia.getCantidadSuplentes();
		} else if (tipoPreseleccionados.equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL)) {
			totalRequeridos = audiencia.getCantidadPrincipales();
		} else if (tipoPreseleccionados.equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE)) {
			totalRequeridos = audiencia.getCantidadSuplentes();
		}

		int totalParticipantes = 0;
		for (Persona persona : personas) {
			if (!persona.isMarcaAzulSorteo()) {
				totalParticipantes++;
			}
		}
		if (totalParticipantes < totalRequeridos) {
			valido = false;
		}
		return valido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade#
	 * getConformarListaFuncionarioSorteo(com.ccb.simasc.transversal.entidades.Caso)
	 */
	public List<Persona> getConformarListaFuncionarioSorteo(Caso caso, boolean simulacion, String tipoSorteo) {
		// se consultan las personas que pueden hacer parte del sorteo
		return manejadorPersonaServicioMateria.consultaPersonasParaSorteo(caso, simulacion, tipoSorteo);
	}

	/**
	 * Realiza la simulacion de liberacion de lista para realizar el sorteo del caso
	 * enviado por parametro.
	 * 
	 * @param caso caso al cual se le va a simular la liberacion de lista
	 * @return true si la liberacion permite sorteo, false de lo contrario
	 */
	@Override
	public boolean simulariberacionLista(Caso caso, String tipoSorteo) {
		boolean permiteSorteo = false;

		// se obtienen la cantidad de arbitros que requiere el caso
		NombramientoPersona nombramiento = caso.getNombramientoSorteo();
		Integer totalSolicitados = nombramiento.getCantFuncionariosPrincipales()
				+ nombramiento.getCantFuncionariosSuplentes();

		// consulta las personas simulando la liberacion de lista
		List<Persona> personas = this.getConformarListaFuncionarioSorteo(caso, true, tipoSorteo);

		int conLiberacion = 0;
		for (Persona persona : personas) {
			if (!persona.isMarcaAzulSorteo()) {
				conLiberacion++;
			}
		}
		if (conLiberacion >= totalSolicitados) {
			permiteSorteo = true;
		}
		return permiteSorteo;
	}

	/**
	 * Realiza la liberacion de lista para el caso enviado
	 * 
	 * @param caso
	 */
	@Override
	public List<PersonaServicioMateria> liberarListaSorteo(Caso caso, String usuarioSesion, String tipoSorteo) {
		// consulta las personas simulando la liberacion de lista
		List<Persona> personas = this.getConformarListaFuncionarioSorteo(caso, true, tipoSorteo);
		return manejadorSorteo.liberarListaSorteo(caso, personas, usuarioSesion, tipoSorteo);		
	}

	/**
	 * Metodo encargado de generar los indices aleatorios
	 * 
	 * @param tamano
	 * @param semilla
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private List<Integer> obtenerIndicesAleatorios(int tamano) throws NoSuchAlgorithmException {
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i < tamano; i++) {
			lista.add(i);
		}
		Random rndm = new Random();
		rndm.setSeed(this.generarSemilla());
		Collections.shuffle(lista, rndm);
		return lista;
	}

	/**
	 * Genera aleatoriamente la semilla para ordenar de manera aleatoria los indices
	 * para el sorteo
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private Integer generarSemilla() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance(UtilConstantes.ALGORITMO_SEMILLA);
		return random.nextInt(UtilConstantes.VALOR_SEMILLA_SORTEO);
	}

	/**
	 * De la lista de personas para sorteo se descartan los que ya hacen parte del
	 * caso, los marcados en azul
	 * 
	 * @param personasSorteo
	 * @return
	 */
	private List<Persona> eliminarMarcadosAzul(List<Persona> personasSorteo) {
		List<Persona> personasFiltro = new ArrayList<>();
		for (Persona persona : personasSorteo) {
			if (!persona.isMarcaAzulSorteo()) {
				personasFiltro.add(persona);
			}
		}
		return personasFiltro;
	}

	/**
	 * Realiza el sorteo de las personas teniendo en cuenta la cantidad de
	 * principales y suplentes
	 * 
	 * @param personasSorteo  lista de personas que se van a sortear
	 * @param cantPrincipales cantidad de arbitros principales a sortear
	 * @param cantSuplentes   cantidad de arbitros suplentes a sortear
	 * @return lista de personas que salieron favorecidas para el sorteo
	 * @throws NoSuchAlgorithmException 
	 */
	@Override
	public List<Persona> realizarSorteo(List<Persona> personasSorteo, int cantPrincipales, int cantSuplentes) throws NoSuchAlgorithmException {
		HashMap<Integer, Persona> funcionariosASeleccionar = new HashMap<>();
		List<Persona> funcionariosSeleccionados = new ArrayList<>();

		List<Persona> personasFiltro = this.eliminarMarcadosAzul(personasSorteo);
		List<Integer> indices = obtenerIndicesAleatorios(personasFiltro.size());

		int cont = 0;
		for (Persona persona : personasFiltro) {
			funcionariosASeleccionar.put(indices.get(cont), persona);
			// asocia el numero asignado a los participantes
			personasSorteo.get(personasSorteo.indexOf(persona)).setAleatorioAsignado(indices.get(cont).longValue());
			cont++;
		}
		int ordenPrin = 1;
		int ordenSupl = 1;
		while (cantPrincipales + cantSuplentes > 0) {
			Integer selector = SecureRandom.getInstance(UtilConstantes.ALGORITMO_SEMILLA).nextInt(indices.size());
			Persona funcionario = funcionariosASeleccionar.get(selector);
			if (funcionario != null) {
				if (cantPrincipales > 0) {
					funcionario.setNombradoComoPos(ordenPrin++);
					funcionario.setNombradoComo(UtilConstantes.NOMBRAMIENTO_PRINCIPAL);
					cantPrincipales--;
				} else {
					funcionario.setNombradoComoPos(ordenSupl++);
					funcionario.setNombradoComo(UtilConstantes.NOMBRAMIENTO_SUPLENTE);
					cantSuplentes--;
				}
				funcionario.setAleatorioAsignado(selector.longValue());
				funcionariosSeleccionados.add(funcionario);
				funcionariosASeleccionar.remove(selector);
			}
		}
		return funcionariosSeleccionados;
	}

	/***
	 * Consulta las personas preseleccionadas para el sorteo del caso enviado
	 * 
	 * @param caso
	 * @return lista de personas preseleccionadas
	 */
	public List<Persona> obtenerPreseleccionados(Caso caso, String tipoPreseleccion) {
		List<Persona> preseleccionados = new ArrayList<>();
		List<Preseleccionado> preseleccionList = manejadorPreseleccionado
				.consultarPreseleccionadosCaso(caso.getIdCaso());
		Long idMateria;

		for (Preseleccionado pre : preseleccionList) {
			Persona persona = pre.getPersona();
			try {
				if (caso.getQuienPreselecciona() != null && caso.getQuienPreselecciona()
						.equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)) {

					if (caso.getParametroServSorteo().getSorteoConMateria()) {

						idMateria = obtenerIdMateriaBloquearPreseleccion(pre.getMateriasPreseleccion(), caso,
								persona);

					} else {
					// materia SIN MATERIA
						Materia materia = manejadorMateria.consultaSinMateria();
						idMateria = materia.getIdMateria();
					}
					PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
							.consultarPersonaServicioMateria(caso.getIdServicio(), idMateria,
									persona.getIdPersona());

					persona.setIdPersonaServicioMateria(personaServicioMateria.getIdPersonaServicioMateria());

					persona.setPersonaServicioMateria(personaServicioMateria);
				}
			} catch (SimascNegocioPruebaException e) {
				LOGGER.error(e.getMessage());
			}
			if (tipoPreseleccion == null) {
				preseleccionados.add(pre.getPersona());
			} else if (tipoPreseleccion.equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_PRINCIPAL)) {
				if (!pre.getTipoPreseleccion().equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE)) {
					preseleccionados.add(pre.getPersona());
				}
			} else if (tipoPreseleccion.equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE)) {
				if (pre.getTipoPreseleccion().equals(UtilDominios.TIPO_PRESELECCIONADO_PRESELECCION_SUPLENTE)) {
					preseleccionados.add(pre.getPersona());
				}
			}

		}
		manejadorPersonaServicioMateria.marcarPersonasDelCaso(caso, preseleccionados);
		return preseleccionados;
	}

	/**
	 * Retorna la audiencia a partir de su id
	 * 
	 * @param idAudiencia
	 * @return
	 */
	public Audiencia obtenerAudiencia(Long idAudiencia) {
		Audiencia audiencia = manejadorAudiencia.buscar(idAudiencia);
		// carga lazy del caso por cada audiencia
		audiencia.getCaso().getRolPersonaCasoList();
		audiencia.getCaso().getNombramientoPersonaList();
		return audiencia;
	}

	/**
	 * Registra el resultado del sorteo y actualiza las tablas correspondientes
	 * 
	 * @param audiencia
	 * @param arbitrosSeleccionados
	 */
	@Override
	public void registrarResultSorteo(Audiencia audiencia, List<Persona> arbitrosSeleccionados, String usuarioSesion) {
		Date fechaActual = new Date();

		ParametroServicioSorteo parametroServicioSorteo = manejadorParametroServicioSorteo.buscar(Long.valueOf(audiencia.getTipoSorteo()));

		Sorteo sorteo = new Sorteo();
		sorteo.setFechaRealizacion(fechaActual);
		sorteo.setEstado(UtilDominios.ESTADO_SORTEO_REALIZADO);
		sorteo.setMateriaCaso(audiencia.getCaso().getIdMateria());
		sorteo.setServicioCaso(audiencia.getCaso().getIdServicio());
		sorteo.setTipoCuantia(audiencia.getCaso().getTipoCuantia());
		sorteo.setLiberoLista(audiencia.getCaso().isLiberoLista());
		sorteo.setHoraLiberacion(audiencia.getCaso().getHoraLiberacion());
		sorteo.setIdUsuarioModificacion(usuarioSesion);
		sorteo.setFechaUltimaModificacion(fechaActual);
		sorteo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		sorteo.setIdCaso(audiencia.getIdCaso());
		sorteo.setCaso(audiencia.getCaso());
		sorteo.setAleatoriosSeleccionados(this.obteneraleatoriosSeleccionados(arbitrosSeleccionados));
		sorteo.setPreseleccion(audiencia.getCaso().getPreseleccion());
		sorteo.setTipoPreseleccion(audiencia.getCaso().getTipoPreseleccion());
		sorteo.setQuienPreselecciona(audiencia.getCaso().getQuienPreselecciona());
		Sorteo sorteoSave = manejadorSorteo.registrarSorteo(sorteo);

		// se actualiza la audiencia
		audiencia.setEstado(UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		audiencia.setIdSorteo(sorteoSave.getIdSorteo());
		audiencia.setFechaUltimaModificacion(fechaActual);
		audiencia.setIdUsuarioModificacion(usuarioSesion);
		manejadorAudiencia.actualizar(audiencia);
		
		// se registran los arbitros en ROL_PERSONA_CASO
		for (Persona persona : arbitrosSeleccionados) {
			boolean esPrincipal = UtilConstantes.NOMBRAMIENTO_PRINCIPAL.equalsIgnoreCase(persona.getNombradoComo());

			RolPersonaCaso rolPersoCaso = new RolPersonaCaso();
			rolPersoCaso.setPersona(persona);
			rolPersoCaso.setRol(parametroServicioSorteo.getRol());
			rolPersoCaso.setCaso(audiencia.getCaso());

			String estado = UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO;
			String tipoNombramiento = UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE;
			String tipoNombramientoLabel = UtilConstantes.NOMBRAMIENTO_SUPLENTE;
			if (esPrincipal) {
				estado = UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR;
				tipoNombramiento = UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL;
				tipoNombramientoLabel = UtilConstantes.NOMBRAMIENTO_PRINCIPAL;
			}
			rolPersoCaso.setEstado(estado);
			rolPersoCaso.setTipoNombramiento(tipoNombramiento);

			rolPersoCaso.setOrdenDeAsignacion(persona.getNombradoComoPos());
			rolPersoCaso.setMetodoNombramiento(UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
			rolPersoCaso.setIdUsuarioModificacion(usuarioSesion);
			rolPersoCaso.setFechaUltimaModificacion(fechaActual);
			rolPersoCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			rolPersoCaso.setIdSorteo(sorteoSave.getIdSorteo());
			rolPersoCaso.setTipoSuplencia(audiencia.getCaso().getNombramientoSorteo() != null
					? audiencia.getCaso().getNombramientoSorteo().getManejoDeSuplencia()
					: null);
			manejadorRolPersonaCaso.crear(rolPersoCaso);

			// se generan los eventos
			EventoRolPersonaCaso eventoRolPersonaCaso = new EventoRolPersonaCaso();
			eventoRolPersonaCaso.setEstadoAsignado(estado);
			eventoRolPersonaCaso.setFechaDeAsignacion(fechaActual);
			eventoRolPersonaCaso.setIdUsuarioModificacion(usuarioSesion);
			eventoRolPersonaCaso.setFechaUltimaModificacion(fechaActual);
			eventoRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			eventoRolPersonaCaso.setIdRol(parametroServicioSorteo.getIdRol());
			eventoRolPersonaCaso.setIdPersona(persona.getIdPersona());
			eventoRolPersonaCaso.setIdCaso(audiencia.getCaso().getIdCaso());
			manejadorEventoRolPersonaCaso.crear(eventoRolPersonaCaso);

			String observaciones = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO028.toString()),
					persona.getNombreCompleto(), tipoNombramientoLabel);

			Evento evento = new Evento();
			evento.setTipoEvento(UtilDominios.TIPO_EVENTO_DESIGNACION_ARBITRO);
			evento.setFechaEvento(fechaActual);
			evento.setObservaciones(observaciones);
			evento.setCaso(audiencia.getCaso());
			evento.setIdCaso(audiencia.getCaso().getIdCaso());
			evento.setIdUsuarioModificacion(usuarioSesion);
			evento.setFechaUltimaModificacion(fechaActual);
			evento.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			manejadorEvento.crear(evento);

			// los casos con participantes preseleccionados no se realiza inactivacion
			if ((esPrincipal || (!esPrincipal && parametroServicioSorteo.getBloqueaSuplente())) && (!audiencia.getCaso().getPreseleccion()
					|| (audiencia.getCaso().getPreseleccion() && audiencia.getCaso().getQuienPreselecciona() != null
							&& audiencia.getCaso().getQuienPreselecciona()
									.equals(UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA)))) {
				manejadorPersonaServicioMateria.inactivarParaSorteo(persona, audiencia.getCaso().getIdCaso(),
						fechaActual, usuarioSesion, tipoNombramiento);
			}
		}

		// se almacenan los elegibles
		for (Persona participante : audiencia.getCaso().getParticipantesSorteo()) {
			this.registrarElegibleSorteo(participante, sorteoSave, fechaActual, false, usuarioSesion);
		}
		if (audiencia.getCaso().isLiberoLista()) {
			for (Persona participante : audiencia.getCaso().getParticipantesSorteoLiberacion()) {
				this.registrarElegibleSorteo(participante, sorteoSave, fechaActual, true, usuarioSesion);
			}
		}
	}

	/**
	 * obtiene y ordena los numeros aleatorios de los arbitros seleccionados
	 * 
	 * @param arbitrosSeleccionados
	 * @return numeros separados por (;)
	 */
	private String obteneraleatoriosSeleccionados(List<Persona> arbitrosSeleccionados) {
		String aleatorios = "";
		boolean inicia = true;
		for (Persona arbitro : arbitrosSeleccionados) {
			if (!inicia)
				aleatorios += UtilConstantes.SEPARADOR_ALEATORIOS_SORTEO;
			aleatorios += arbitro.getAleatorioAsignado();
			inicia = false;
		}
		return aleatorios;
	}

	/**
	 * Registra las personas participantes del sorteo en la tabla elegibles
	 * 
	 * @param participante
	 * @param sorteo
	 * @param fecha
	 * @param liberacion
	 */
	private void registrarElegibleSorteo(Persona participante, Sorteo sorteo, Date fecha, boolean liberacion,
			String usuarioSesion) {
		Elegible elegible = new Elegible();
		ElegiblePK elegiblePK = new ElegiblePK(sorteo.getIdSorteo(), participante.getIdPersona());
		elegible.setElegiblePK(elegiblePK);
		elegible.setSorteo(sorteo);
		elegible.setElegidoPorLiberacionLista(liberacion);
		elegible.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		elegible.setFechaDeSeleccion(fecha);
		elegible.setFechaUltimaModificacion(fecha);
		elegible.setIdUsuarioModificacion(usuarioSesion);
		elegible.setPersona(participante);
		elegible.setAleatorioAsignado(participante.getAleatorioAsignado());
		manejadorElegible.crear(elegible);

	}

	/**
	 * Valida si el dia enviado esta parametrizado para realizar sorteo.
	 * 
	 * @return true si el dia esta habilitado, false de lo contrario
	 */
	public boolean validarDiaHabilitadoParaSorteo(Calendar fecha) {
		boolean valida = false;
		ParametroSorteo paramSorteo = this.consultarParametrosSorteo();

		int diaCalendar = fecha.get(Calendar.DAY_OF_WEEK);
		String diaDominio = UtilSimasc.convertirDiaCalendarADiaDominio(diaCalendar);

		if (paramSorteo.getDiaSorteoList() != null) {
			for (DiaSorteo diaSorteo : paramSorteo.getDiaSorteoList()) {
				if (UtilDominios.ESTADO_REGISTRO_ACTIVO.equalsIgnoreCase(diaSorteo.getEstadoRegistro())) {
					if (diaSorteo.getCodigo().equals(diaDominio)) {
						valida = true;
						break;
					}
				}
			}
		}
		return valida;
	}

	/**
	 * Obtiene el nombramiento persona para realizar sorteo NPC
	 * 
	 * @param caso
	 * @return
	 */
	@Override
	public NombramientoPersona obtenerNombramientoPersonaCaso(Caso caso) {
		return manejadorCaso.obtenerNombramientoPersonaCaso(caso);
	}

	private Long obtenerIdMateriaBloquearPreseleccion(String materiasPreseleccionCaso, Caso caso, Persona persona)
			throws SimascNegocioPruebaException {

		Long idMateria = null;
		List<String> materiasPreseleccion = Arrays.asList(materiasPreseleccionCaso.split(","));

		if (materiasPreseleccion.contains(caso.getIdMateria().toString())) {

			PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
					.consultarPersonaServicioMateriaSinExcepcion(caso.getIdServicio(), caso.getIdMateria(),
							persona.getIdPersona());

			if (personaServicioMateria!= null && personaServicioMateria.getEstadoParaSorteo().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
				return caso.getIdMateria();
			}
		}

		for (String materiaPreseleccion : materiasPreseleccion) {

			PersonaServicioMateria personaServicioMateria = manejadorPersonaServicioMateria
					.consultarPersonaServicioMateriaSinExcepcion(caso.getIdServicio(), Long.parseLong(materiaPreseleccion),
							persona.getIdPersona());

			if (personaServicioMateria != null
					&& personaServicioMateria.getEstadoParaSorteo().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {

				idMateria = Long.parseLong(materiaPreseleccion);
				break;
			}

		}

		return idMateria;
	}
}
