package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaComienzoDelDia;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

// Escriba en esta sección sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.PersonaServicioMateriaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.MotivoHabilitacionArbitro;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad PersonaServicioMateria.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorPersonaServicioMateria extends ManejadorCrud<PersonaServicioMateria, Long> {
	private static final String CONSULTA_PERSONA_SERVICIO_MATERIA = "SELECT pe.* FROM PERSONA_SERVICIO_MATERIA psm INNER JOIN persona pe ON psm.id_persona = pe.id_persona INNER JOIN rol_persona rp ON pe.id_persona = rp.id_persona INNER JOIN lista li ON rp.id_lista = li.id_lista WHERE ";
	private static final String CRITERIO_AND = " AND ";
	private static final String CRITERIO_MATERIA = " psm.id_materia = ?1 ";
	private static final String CRITERIO_SERVICIO = " psm.id_servicio = ?2 ";
	private static final String CRITERIO_LISTA = " li.nombre = ?4 ";
	private static final String CRITERIO_ESTADO_SORTEO = " psm.estado_para_sorteo = ?3 ";
	private static final String CRITERIO_FECHA_VIGENCIA = " psm.fecha_fin_de_vigencia is null or > psm.fecha_fin_de_vigencia GETDATE() ";
	

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private transient EntityManager em;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorMateria manejadorMateria;

	@EJB
	private ManejadorHistoricoPersonaServicioMateria historicoPersSerMateria;
	
	@EJB
	private ManejadorMotivoHabilitacionArbitro  motivoHabilitacionArbitro;

	@EJB
    private ManejadorParametrosGenerales manejadorParametrosGenerales;

	@EJB
    private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo ;

	// protected region atributos end

	public ManejadorPersonaServicioMateria() {
		super(PersonaServicioMateria.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Actualiza en base de datos el registro de Persona Servicio Materia
	 * 
	 * @param personaServicioMateria
	 * @return
	 */
	public PersonaServicioMateria actualizarPersonaServicioMateria(PersonaServicioMateria personaServicioMateria) {
		PersonaServicioMateria personaServicioMateria2 = new PersonaServicioMateria();
		personaServicioMateria2 = em.merge(personaServicioMateria);
		return personaServicioMateria2;
	}

	/**
	 * retorna las personas habilitadas para realizar sorteo, para el caso y
	 * teniendo en cuenta los parametros de sorteo
	 * 
	 * @param casoId
	 * @param paramServSorteo
	 * @param simulacion      indica si se va a simular la liberacion de lista
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultaPersonasParaSorteo(Caso caso, boolean simulacion, String tipoSorteo) {
		
		ParametroServicioSorteo pServicioSorteo = manejadorParametroServicioSorteo.buscar(Long.valueOf(tipoSorteo));
		Boolean sorteoConLista = pServicioSorteo.getSorteoConLista();
		Boolean sorteoConMateria = pServicioSorteo.getSorteoConMateria();
		List<Persona> personas = new ArrayList<Persona>();
		String  tipoCuantia = caso.getTipoCuantia();
		String lista = null;
		if(caso.getIdServicio().equals(UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO)){
			if(tipoSorteo.equals(UtilDominios.TIPO_DE_SORTEO_ARBITROS_NACIONALES)){
				if(caso.getTipoCuantia().equalsIgnoreCase(UtilDominios.TIPO_CUANTIA_ARBITRAJE_ABREVIADO_INDETERMINADO)){
					tipoCuantia = UtilDominios.TIPO_CUANTIA_INDETERMINADO;
				}else{
					tipoCuantia = this.tipoCuantiaPorPretensiones(caso.getValorPretensiones());
				}
			}else{
				sorteoConLista = false;
			}			
		}
		if (Boolean.TRUE.equals(sorteoConLista)) {
			lista = UtilSimasc.obtenerListaPorTipoCuantia(tipoCuantia);
		}

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT per.id_persona as idPersona, ");
		jpqlQuery.append("psm.id_persona_servicio_materia as idPersonaServicioMateria, ");
		jpqlQuery.append("   (SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END ");
		jpqlQuery.append("   FROM ROL_PERSONA_CASO rpc ");
		jpqlQuery.append("  INNER JOIN CASO cc ON rpc.id_caso = cc.id_caso ");
		jpqlQuery.append("   WHERE rpc.id_persona  = per.id_persona ");
		jpqlQuery.append("   AND cc.id_caso = ?7 ");
		jpqlQuery.append("   AND rpc.estado_registro = ?1) as designadoPreviamente ");
		jpqlQuery.append("FROM PERSONA per ");
		jpqlQuery.append("INNER JOIN ROL_PERSONA rp ON per.id_persona = rp.id_persona ");
		jpqlQuery.append("AND rp.fecha_fin_vigencia is null ");
		jpqlQuery.append("AND rp.estado_registro = ?1 ");
		if (Boolean.TRUE.equals(sorteoConLista)) {
			jpqlQuery.append("INNER JOIN LISTA ls ON ls.id_lista = rp.id_lista ");
		}
		jpqlQuery.append("INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON rp.id_rol = pss.id_rol ");
		jpqlQuery.append("INNER JOIN SERVICIO serv ON serv.id_servicio = pss.id_servicio ");
		jpqlQuery.append("INNER JOIN PERSONA_SERVICIO_MATERIA psm ON per.id_persona = psm.id_persona ");
		jpqlQuery.append("AND psm.id_servicio = pss.id_servicio ");
		jpqlQuery.append("INNER JOIN ESTADO_PERSONA_ROL epr ON per.id_persona = epr.id_persona ");
		jpqlQuery.append("AND epr.id_rol = rp.id_rol ");
		jpqlQuery.append("AND epr.id_servicio = psm.id_servicio ");
		jpqlQuery.append("AND epr.id_motivo = ?2 ");
		jpqlQuery.append("AND psm.fecha_fin_de_vigencia is null ");
		if (!simulacion) {
			jpqlQuery.append("AND psm.estado_para_sorteo = ?3 ");
		}
		if (Boolean.TRUE.equals(sorteoConMateria)) {
			jpqlQuery.append("INNER JOIN CASO c ON c.id_materia = psm.id_materia ");
		
		} else {
			jpqlQuery.append("AND psm.id_materia = ?5 ");
		}
		jpqlQuery.append("WHERE pss.id_parametros_servicios_sorteo = ?4 ");
		if (Boolean.TRUE.equals(sorteoConMateria)) {
			jpqlQuery.append("AND c.id_caso = ?7 ");
		
		}
		if (Boolean.TRUE.equals(sorteoConLista)) {
			jpqlQuery.append("AND ls.nombre = ?6 ");
		}		

		Query q = em.createNativeQuery(jpqlQuery.toString(), PersonaDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		q.setParameter(3, UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
		q.setParameter(4, Long.valueOf(tipoSorteo));
		if (!Boolean.TRUE.equals(sorteoConMateria)) {
			Materia materia = manejadorMateria.consultaSinMateria();
			q.setParameter(5, materia.getIdMateria());
		}
		if (Boolean.TRUE.equals(sorteoConLista)) {
			q.setParameter(6, lista);
		}
		q.setParameter(7, caso.getIdCaso());
		List<PersonaDTO> personasDto = q.getResultList();

		for (PersonaDTO persDto : personasDto) {
			Persona persona = manejadorPersona.buscar(persDto.getIdPersona());
			persona.setIdPersonaServicioMateria(persDto.getIdPersonaServicioMateria());
			persona.setDesignadoPreviamente(persDto.isDesignadoPreviamente());
			personas.add(persona);			
		}
		this.marcarPersonasDelCaso(caso, personas);
		return personas;
	}

	/**
	 * Marca la personas que se encuentran en el caso enviado
	 * 
	 * @param caso
	 * @param personas
	 */
	public void marcarPersonasDelCaso(Caso caso, List<Persona> personas) {
		List<MotivoHabilitacionArbitro> motivosHabilitacion = motivoHabilitacionArbitro.findAll();
		for (Persona persona : personas) {
			for (RolPersonaCaso rolPerCaso : caso.getRolPersonaCasoList()) {
				if (rolPerCaso.getPersona().getIdPersona().longValue() == persona.getIdPersona().longValue()) {
					
					boolean aplicaMotivoInactivacion = false;
					for(MotivoHabilitacionArbitro motivos : motivosHabilitacion) {
						if(rolPerCaso.getMotivoInactivacion() != null && rolPerCaso.getMotivoInactivacion().equalsIgnoreCase(
								motivos.getCodigoInactivacion().getDominioPK().getCodigo())) {
							aplicaMotivoInactivacion = true;
						}
					}				
					if(!aplicaMotivoInactivacion) {
						persona.setMarcaAzulSorteo(true);
						break;
					}
				}
			}
		}
	}

	/**
	 * ADM-C-022
	 * 
	 * @param idMateria
	 * @param idServicio
	 * @param lista
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaServicioMateria> consultarPersonaServicioMateriaInscritaPorServicio(Long idServicio,
			Long idMateria, Long idPersona) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT psm FROM PersonaServicioMateria psm " + "WHERE psm.idServicio =: idServicio "
				+ "AND psm.estadoRegistro = 'ACT' ");
		if (idMateria != null) {
			sb.append("AND psm.idMateria =: idMateria ");
		}
		if (idPersona != null) {
			sb.append("AND psm.idPersona =: idPersona");
		}
		Query q = em.createQuery(sb.toString(), PersonaServicioMateria.class);
		q.setParameter("idServicio", idServicio);
		if (idPersona != null) {
			q.setParameter("idPersona", idPersona);
		}
		if (idMateria != null) {
			q.setParameter("idMateria", idMateria);
		}
		return q.getResultList();
	}

	/**
	 * Metodo encargado de consultar las personas asociadas a un servicio, una
	 * materia, a una lista (A/B) y que se encuentren activas retornando un listado
	 * de personas
	 * 
	 * @param idMateria
	 * @param idServicio
	 * @param lista
	 * @return List<Persona>
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPersonasServicioMateria(Long idMateria, Long idServicio, String lista) {
		StringBuilder queryBase = new StringBuilder();
		queryBase.append(CONSULTA_PERSONA_SERVICIO_MATERIA);
		queryBase.append(CRITERIO_MATERIA);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_SERVICIO);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_ESTADO_SORTEO);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_LISTA);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_FECHA_VIGENCIA);
		
		Query q = em.createNativeQuery(queryBase.toString(), Persona.class);
		q.setParameter(1, idMateria);
		q.setParameter(2, idServicio);
		q.setParameter(3, UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
		q.setParameter(4, lista);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarPersonasServicioMateriaSinEstado(Long idMateria, Long idServicio, String lista) {
		StringBuilder queryBase = new StringBuilder();
		queryBase.append(CONSULTA_PERSONA_SERVICIO_MATERIA);
		queryBase.append(CRITERIO_MATERIA);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_SERVICIO);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_LISTA);
		
		Query q = em.createNativeQuery(queryBase.toString(), Persona.class);
		q.setParameter(1, idMateria);
		q.setParameter(2, idServicio);
		q.setParameter(4, lista);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarPersonasServicioMateriaPorServicio(Long idMateria, Long idServicio, String lista) {
		StringBuilder queryBase = new StringBuilder();
		queryBase.append(CONSULTA_PERSONA_SERVICIO_MATERIA);		
		queryBase.append(CRITERIO_SERVICIO);
		queryBase.append(CRITERIO_AND);
		queryBase.append(CRITERIO_ESTADO_SORTEO);
		
		if (idMateria != null) {
			queryBase.append(CRITERIO_AND);
			queryBase.append(CRITERIO_MATERIA);		
		}
		if (null != lista) {
			queryBase.append(CRITERIO_AND);
			queryBase.append(CRITERIO_LISTA);
		}
		Query q = em.createNativeQuery(queryBase.toString(), Persona.class);
		q.setParameter(1, idMateria);
		q.setParameter(2, idServicio);
		q.setParameter(3, UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
		q.setParameter(4, lista);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public PersonaServicioMateria consultarPersonaServicioMateriaPorServicioMateriaPersona(Long idServicio,
			Long idMateria, Long idPersona) throws SimascException {
		List<PersonaServicioMateria> personaServicioMateriaList = new ArrayList<PersonaServicioMateria>();
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT psm FROM PersonaServicioMateria psm ");
		jpqlQuery.append("WHERE psm.idServicio =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO);
		jpqlQuery.append(" AND psm.idMateria =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA);
		jpqlQuery.append(" AND psm.idPersona =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA);

		Query q = em.createQuery(jpqlQuery.toString());
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO, idServicio);
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA, idMateria);
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA, idPersona);
		personaServicioMateriaList = (List<PersonaServicioMateria>) q.getResultList();
		if (personaServicioMateriaList.isEmpty()) {
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR052.toString()),
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
			throw new SimascException(mensajeError);
		}
		return personaServicioMateriaList.get(0);
	}

	/**
	 * Metodo encargado de consultar las personas asociadas a un servicio y materia
	 * en reparto.
	 * 
	 * @author aperez.
	 * 
	 * @param idServicio: Identificador del servicio del caso.
	 * @param idMateria:  Materia asociada del caso.
	 * @param idPersona:  Persona prestador del servicio.
	 * @return PersonaServicioMateria: Entidad de tipo PersonaServicioMateria.
	 * @throws SimascException
	 */	
	public PersonaServicioMateria consultarPersonaServicioMateria(Long idServicio, Long idMateria, Long idPersona)
			throws SimascNegocioPruebaException {		
	
		PersonaServicioMateria personaServicioMateria = consultarPersonaServicioMateriaSinExcepcion(idServicio, idMateria, idPersona);
		
		if (personaServicioMateria == null) {
			String mensajeError = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR482.toString()));
			throw new SimascNegocioPruebaException(mensajeError);
		}

		return personaServicioMateria;
	}

	@SuppressWarnings("unchecked")
	public PersonaServicioMateria consultarPersonaServicioMateriaSinExcepcion(Long idServicio, Long idMateria,
			Long idPersona) {
		
		Date fechaActual = new Date();
		Date fechaConsultaIni = obtenerFechaComienzoDelDia(fechaActual);
		Date fechaConsultaFin = obtenerFechaFinDelDia(fechaActual);

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT psm FROM PersonaServicioMateria psm ");
		jpqlQuery.append("WHERE psm.idServicio =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO);
		jpqlQuery.append(" AND psm.idMateria =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA);
		jpqlQuery.append(" AND psm.idPersona =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA);
		jpqlQuery.append(" AND ((psm.fechaFinDeVigencia IS NULL) OR :fechaConsultaIni <= psm.fechaFinDeVigencia)");
		jpqlQuery.append(" AND :fechaConsultaFin >= psm.fechaInicioVigencia");
		jpqlQuery.append(" AND psm.estadoRegistro =:");
		jpqlQuery.append(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO);

		Query q = em.createQuery(jpqlQuery.toString());
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_SERVICIO, idServicio);
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA, idMateria);
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA, idPersona);
		q.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_REGISTRO,
				UtilDominios.ESTADO_FUNC_EXTERNO_ACTIVO);
		q.setParameter("fechaConsultaIni", fechaConsultaIni);
		q.setParameter("fechaConsultaFin", fechaConsultaFin);

		List<PersonaServicioMateria> personaServicioMateriaList = (List<PersonaServicioMateria>) q.getResultList();
		if (personaServicioMateriaList.isEmpty()) {
			return null;
		}
		return personaServicioMateriaList.get(0);
	}

	/**
	 * 
	 */
	public PersonaServicioMateria consultarPersonaServicioMateriaVigenteActualmente(Long idServicio, Long idMateria,
			Long idPersona) {
		return this.consultarPersonaServMateriaPorFechaReciente(idServicio, idMateria, idPersona, new Date());
	}
	
	@SuppressWarnings("unchecked")
	public PersonaServicioMateria consultarPersonaServMateriaPorFechaReciente(Long idServicio, Long idMateria,
			Long idPersona, Date fechaConsulta) throws SimascException {
		List<PersonaServicioMateria> personaServicioMateria = new ArrayList<PersonaServicioMateria>();
		StringBuilder jpqlQuery = new StringBuilder();
		Date fechaConsultaIni = obtenerFechaComienzoDelDia(fechaConsulta);
		Date fechaConsultaFin = obtenerFechaFinDelDia(fechaConsulta);

		jpqlQuery.append("SELECT psm FROM PersonaServicioMateria psm ");
		jpqlQuery.append(" WHERE psm.idServicio =: ").append(Servicio.ENTIDAD_SERVICIO_PK);
		jpqlQuery.append(" AND psm.idMateria =: ").append(Materia.ENTIDAD_MATERIA_PK);
		jpqlQuery.append(" AND psm.idPersona =: ").append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND ((psm.fechaFinDeVigencia IS NULL) OR :fechaConsultaFin <= psm.fechaFinDeVigencia) ");
		jpqlQuery.append(" AND :fechaConsultaIni >= psm.fechaInicioVigencia");

		Query query = em.createQuery(jpqlQuery.toString());
		query.setParameter(Servicio.ENTIDAD_SERVICIO_PK, idServicio);
		query.setParameter(Materia.ENTIDAD_MATERIA_PK, idMateria);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter("fechaConsultaIni", fechaConsultaIni);
		query.setParameter("fechaConsultaFin", fechaConsultaFin);

		query.setFirstResult(0);
		query.setMaxResults(1);
		personaServicioMateria = (List<PersonaServicioMateria>) query.getResultList();
		if (personaServicioMateria.size() == 0)
			throw new SimascException("El árbitro no está prestando el servicio actualmente para esta materia");
		return personaServicioMateria.get(0);
	}


	/**
	 * Activa la persona para realizar sorteo
	 * 
	 * @param persona
	 */
	public void activarParaSorteo(PersonaServicioMateriaDTO psm) {
		PersonaServicioMateria obj = new PersonaServicioMateria();
		obj.setIdPersonaServicioMateria(psm.getIdPersonaServicioMateria());
		obj.setEstadoParaSorteo(psm.getEstadoParaSorteo());
		obj.setIdUsuarioModificacion(psm.getIdUsuarioModificacion());
		obj.setFechaUltimaModificacion(psm.getFechaUltimaModificacion());
		obj.setMotivoEstadoSorteo(null);
		obj.setIdCaso(null);
		this.actualizar(obj);
		historicoPersSerMateria.registroHistoricoSorteo(obj, psm.getObservacion(), psm.getIdUsuarioModificacion());
	}

	/*
	 * Inactiva la persona para realizar sorteo
	 * 
	 * @param persona
	 */
	public void inactivarParaSorteo(Persona persona, Long idCaso, Date fechaActual, String usuarioSesion,
			String motivoEstadoSorteo) {
		PersonaServicioMateria psm = this.buscar(persona.getIdPersonaServicioMateria());

		String motivoEstadoDescripcion;
		if(motivoEstadoSorteo.equals(UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL)){
			motivoEstadoDescripcion = UtilConstantes.NOMBRAMIENTO_PRINCIPAL;
		}else{
			motivoEstadoDescripcion = UtilConstantes.NOMBRAMIENTO_SUPLENTE;
		}
		List<String> parametrosMensaje = new ArrayList<String>();
		parametrosMensaje.add(motivoEstadoDescripcion);
		parametrosMensaje.add(idCaso.toString());
				
		String observaciones = String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO029.toString()),
				parametrosMensaje.toArray());
		
		psm.setEstadoParaSorteo(UtilDominios.ESTADO_PERSONA_SORTEO_INACTIVO);
		psm.setFechaUltimaModificacion(fechaActual);
		psm.setIdUsuarioModificacion(usuarioSesion);
		psm.setMotivoEstadoSorteo(motivoEstadoSorteo);
		psm.setIdCaso(idCaso);
		this.actualizar(psm);
		historicoPersSerMateria.registroHistoricoSorteo(psm, observaciones, usuarioSesion);
	}

	/**
	 * Obtiene valor para el campo cantidad_casos_asignados
	 * 
	 * @param parametros
	 */
	public Long obtenerCantidadCasosAsignados(PersonaServicioMateria parametros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				" select ISNULL(Min(cantidad_casos_asignados),0) AS numMinCasos from PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append(" inner join ROL_PERSONA rp on psm.id_persona = rp.id_persona ");
		nativeQuery.append(" inner join ESTADO_PERSONA_ROL epr on psm.id_persona = epr.id_persona and rp.id_rol = epr.id_rol ");		
		nativeQuery.append(" where psm.id_servicio = ?1 and id_materia = ?2 and psm.estado_registro = ?4 ");
		nativeQuery.append(" and rp.fecha_fin_vigencia is null and epr.id_motivo = ?5 ");
		
		if(parametros.getIdPersona() != null) {
			nativeQuery.append(" and id_centro = (select top 1 id_centro ");
			nativeQuery.append(" from ROL_PERSONA rpc where rpc.id_persona = ?3 and estado_registro = ?4 ");
			nativeQuery.append(" and rpc.fecha_fin_vigencia is null order by id_centro ) ");
		}

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, parametros.getIdServicio());
		query.setParameter(2, parametros.getIdMateria());
		
		if(parametros.getIdPersona() != null) {
			query.setParameter(3, parametros.getIdPersona());
		}
		
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.ESTADO_CONCILIADOR_ACTIVO);

		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	@SuppressWarnings("unchecked")
	public PersonaServicioMateria consultaPersonServicioMateriaByIdServicioAndNombreRol(String nombreRol, Long idServicio) {
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT TOP 1 psm.* from PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ON psm.id_persona = rp.id_persona ");
		nativeQuery.append(" INNER JOIN ROL r ON r.id_rol = rp.id_rol ");
		nativeQuery.append(" WHERE r.nombre = ?1 ");
		nativeQuery.append(" AND rp.estado_registro = ?2 ");
		nativeQuery.append(" AND psm.id_servicio = ?3 ");
		nativeQuery.append(" AND rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia > GETDATE() ");
		nativeQuery.append(" ORDER BY psm.cantidad_casos_asignados, psm.id_persona ");

		Query query = em.createNativeQuery(nativeQuery.toString(),PersonaServicioMateria.class);
		query.setParameter(1, nombreRol);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, idServicio);		

		List<PersonaServicioMateria> personaServicioMateria = query.getResultList();
		if(personaServicioMateria != null && !personaServicioMateria.isEmpty()) {
			return personaServicioMateria.get(0);
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonaServicioMateria> consultaPersonServicioMateriaByIdPersona(Long idPersona) {
		
		StringBuilder nativeQueryByPersona = new StringBuilder();
		nativeQueryByPersona.append(" SELECT psm.* from PERSONA_SERVICIO_MATERIA psm ");
		nativeQueryByPersona.append(" WHERE psm.id_persona = ?1 ");
		nativeQueryByPersona.append(" AND psm.estado_registro = ?2 ");

		Query queryByPersona = em.createNativeQuery(nativeQueryByPersona.toString(),PersonaServicioMateria.class);
		queryByPersona.setParameter(1, idPersona);
		queryByPersona.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return queryByPersona.getResultList();	
	}

	@SuppressWarnings("unchecked")
	public PersonaServicioMateria consultaPersonServicioMateriaByIdPersonaIdCaso(Long idPersona, Long idCaso) {
		
		StringBuilder nativeQueryByPersona = new StringBuilder();
		nativeQueryByPersona.append(" SELECT TOP 1 psm.* from PERSONA_SERVICIO_MATERIA psm ");
		nativeQueryByPersona.append(" WHERE psm.id_persona = ?1 ");
		nativeQueryByPersona.append(" AND psm.id_caso = ?2 ");
		nativeQueryByPersona.append(" AND psm.estado_registro = ?3 ");

		Query queryByPersona = em.createNativeQuery(nativeQueryByPersona.toString(),PersonaServicioMateria.class);
		queryByPersona.setParameter(1, idPersona);
		queryByPersona.setParameter(2, idCaso);
		queryByPersona.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<PersonaServicioMateria> personaServicioMateria = queryByPersona.getResultList();
		if(personaServicioMateria != null && !personaServicioMateria.isEmpty()) {
			return personaServicioMateria.get(0);
		}
		return null;	
	}

	@SuppressWarnings("unchecked")
	public String tipoCuantiaPorPretensiones(String valorPrentensiones) {
		try {
			Long valorPretenciones = Long.valueOf(valorPrentensiones.trim().replace(".", ""));
					
			ParametrosGenerales parametrosGenerales = manejadorParametrosGenerales.buscar(UtilConstantes.SMLMV);
			Long cuantiaCuatrocientosSMLV = parametrosGenerales.getValorNumerico() * 400;
			
			if(valorPretenciones >= cuantiaCuatrocientosSMLV){
				return UtilDominios.TIPO_CUANTIA_MAYOR;
			}else{
				return UtilDominios.TIPO_CUANTIA_MENOR;
			}	

		} catch (Exception e) {
			return null;
		}

	}
}
