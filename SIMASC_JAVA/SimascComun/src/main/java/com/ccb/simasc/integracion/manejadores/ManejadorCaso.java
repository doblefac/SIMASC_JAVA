package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaComienzoDelDia;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CasosControlLegalidadDTO;
import com.ccb.simasc.transversal.dto.CasosNoAsignadosDTO;
import com.ccb.simasc.transversal.dto.CasosSinCerrarDTO;
import com.ccb.simasc.transversal.dto.InfoCasoPDFDTO;
import com.ccb.simasc.transversal.dto.InformacionCasoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoIncompletoDTO;
import com.ccb.simasc.transversal.dto.formularios.DatosReversarResultadoDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Materia;
import com.ccb.simasc.transversal.entidades.NombramientoPersona;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.ServicioMateriaPK;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
import com.ccb.simasc.transversal.utilidades.UtilParamServicio;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Caso.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorCaso extends ManejadorCrud<Caso, Long> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	private static final String FECHA_INICIO_DIA_SORTEO = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO114.toString());
	private static final String FECHA_FIN_DIA_SORTEO = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO115.toString());
    private static final String FECHA_INICIO_DIA_SORTEO_SIG_DIA_HABIL = MensajesSimasc.getInstancia()
    		.getMensajePorKey(MensajesEnum.INFO122.toString());
    private static final String FECHA_FIN_DIA_SORTEO_SIG_DIA_HABIL = MensajesSimasc.getInstancia()
    		.getMensajePorKey(MensajesEnum.INFO123.toString());
	private static final String FILTRO_CASO_ASIGNADO_NO_VALIDO_PARAMETROS_NULOS = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO116.toString());
	private static final String FILTRO_CASO_ASIGNADO_NO_VALIDO_TIPO_SERVICIO_NULO = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO121.toString());
	private static final String FILTRO_CASO_ASIGNADO_NO_VALIDO_FECHA_FALTANTE = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO117.toString());
	private static final String FILTRO_CASO_ASIGNADO_NO_VALIDO_FECHAS_INCOMPATIBLES = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO037.toString());
	private static final String NO_EXISTEN_RESULTADOS = MensajesSimasc.getInstancia()
			.getMensajePorKey(MensajesEnum.INFO034.toString());

	@PersistenceContext
	private EntityManager em;

	// protected region atributos end

	public ManejadorCaso() {
		super(Caso.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Registra una caso en la bd
	 * 
	 * @param caso
	 * @return
	 */
	public Caso crearCaso(Caso caso) {
		return (Caso) mp.updateObject(caso);

	}

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	public List<Audiencia> consultarAudienciasSorteoDelDia(Date fecha) {
		return this.consultarAudienciasSorteoDelDia(fecha, null);
	}
	
	/**
	 * Consulta las audiencias para sorteo de público de designación del día
	 * 
	 * @param fecha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Audiencia> consultarAudienciasSorteoDelDia(Date fecha, Date fechaSiguienteHabil ) {

		List<Audiencia> audiencias = new ArrayList<Audiencia>();

		Date fechaInicio = obtenerFechaComienzoDelDia(fecha);
		Date fechaFinal = obtenerFechaFinDelDia(fecha);
        Date fechaInicioSigDiaHab = new Date();
        Date fechaFinalSigDiaHab = new Date();
        if (fechaSiguienteHabil != null) {
            fechaInicioSigDiaHab = UtilOperaciones.obtenerFechaComienzoDelDia(fechaSiguienteHabil);
            fechaFinalSigDiaHab = UtilOperaciones.obtenerFechaFinDelDia(fechaSiguienteHabil);
        }

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT a FROM Audiencia a");
		jpqlQuery.append(" WHERE a.horaInicio >=: " + FECHA_INICIO_DIA_SORTEO);
		jpqlQuery.append(" AND a.horaInicio <=:" + FECHA_FIN_DIA_SORTEO);
		jpqlQuery.append(" AND a.estado IN :" + Audiencia.ENTIDAD_AUDIENCIA_ESTADO);
		jpqlQuery.append(" AND a.tipoAudiencia IN :" + Audiencia.ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA);
		jpqlQuery.append(" AND a.estadoRegistro =: " + Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO);
        jpqlQuery.append(" OR  (a.horaInicio >=: " + FECHA_INICIO_DIA_SORTEO_SIG_DIA_HABIL);
        jpqlQuery.append(" AND a.horaInicio <=:" + FECHA_FIN_DIA_SORTEO_SIG_DIA_HABIL);
        jpqlQuery.append(" AND a.estado IN :estado");
        jpqlQuery.append(" AND a.tipoAudiencia IN :tipoAudiencia");
        jpqlQuery.append(" AND a.estadoRegistro =: estadoRegistro)");

		List<String> estadosFiltro = new ArrayList<String>();
		estadosFiltro.add(UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		estadosFiltro.add(UtilDominios.ESTADO_AUDIENCIA_REALIZADA);

        List<String> tipoFiltro = new ArrayList<String>();
        tipoFiltro.add(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
        tipoFiltro.add(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_INTERNACIONAL_DE_DESIGNACION);
        tipoFiltro.add(UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_MEDIADORES);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(FECHA_INICIO_DIA_SORTEO, fechaInicio, TemporalType.TIMESTAMP);
		query.setParameter(FECHA_FIN_DIA_SORTEO, fechaFinal, TemporalType.TIMESTAMP);
        query.setParameter(FECHA_INICIO_DIA_SORTEO_SIG_DIA_HABIL, fechaInicioSigDiaHab, TemporalType.TIMESTAMP);
        query.setParameter(FECHA_FIN_DIA_SORTEO_SIG_DIA_HABIL, fechaFinalSigDiaHab, TemporalType.TIMESTAMP);
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO, estadosFiltro);
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA, tipoFiltro);
		query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<Audiencia> audienciasResult = query.getResultList();

		for (Audiencia audiencia : audienciasResult) {
			// carga lazy del caso por cada audiencia
			audiencia.getCaso().getRolPersonaCasoList();
			audiencia.getCaso().getNombramientoPersonaList();
			audiencias.add(audiencia);
		}

		return audiencias;
	}

	/**
	 * Obtiene el nombramiento persona para realizar sorteo NPC
	 * 
	 * @param caso
	 * @return
	 */
	public NombramientoPersona obtenerNombramientoPersonaCaso(Caso caso) {
		NombramientoPersona nombramientoPersona = new NombramientoPersona();
		for (NombramientoPersona nombramiento : caso.getNombramientoPersonaList()) {
			if (UtilDominios.NOMBRAMIENTO_POR_LA_CCB.equalsIgnoreCase(nombramiento.getMetodoDeNombramiento())
					&& UtilDominios.ESTADO_REGISTRO_ACTIVO.equalsIgnoreCase(nombramiento.getEstadoRegistro())) {
				nombramientoPersona = nombramiento;
				break;
			}
		}
		return nombramientoPersona;
	}

	public List<Caso> consultarCasosPorFiltros(String etapaP, Date fechaInicial, Date fechaFin, String tipoCuantia,
			Long idMateria, Long idServicio) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Caso c ");
		jpqlQuery.append(" LEFT JOIN FETCH c.audienciaList ");
		jpqlQuery.append(" LEFT JOIN FETCH c.eventoList ");
		jpqlQuery.append(" LEFT JOIN FETCH c.rolPersonaCasoList ");
		jpqlQuery.append(" WHERE c.fechaRadicacion>=:");
		jpqlQuery.append(" fechaInicial");
		jpqlQuery.append(" AND c.fechaRadicacion<=:");
		jpqlQuery.append(" fechaFin");
		if (null != tipoCuantia) {
			jpqlQuery.append(" AND c.tipoCuantia=:");
			jpqlQuery.append(" tipoCuantia");
		}
		if (null != idMateria) {
			jpqlQuery.append(" AND c.idMateria=:");
			jpqlQuery.append(" idMateria");
		}
		if (null != etapaP) {
			jpqlQuery.append(" AND c.etapa=:");
			jpqlQuery.append(" etapa");
		}
		if (null != idServicio) {
			jpqlQuery.append(" AND c.idServicio=:");
			jpqlQuery.append(" idServicio");
		}
		jpqlQuery.append(" ORDER BY c.idCaso ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFin", fechaFin);
		if (null != tipoCuantia) {
			query.setParameter("tipoCuantia", tipoCuantia);
		}
		if (null != idMateria) {
			query.setParameter("idMateria", idMateria);
		}
		if (null != idServicio) {
			query.setParameter("idServicio", idServicio);
		}
		if (null != etapaP) {
			query.setParameter("etapa", etapaP);
		}
		return query.getResultList();
	}

	/**
	 * Devuelve la última fecha de cierre para los casos del arbitro que
	 * correspondan con el servicioMateria.
	 * 
	 * @param idArbitro
	 * @param servicioMateria
	 * @return
	 */
	public Date consultarFechaDisponibilidadArbitro(Long idArbitro, ServicioMateriaPK servicioMateriaPK) {
		String estadoRegistroRolPersonaCaso = RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO;
		String estadoRegistroCaso = Caso.ENTIDAD_CASO_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT flc.fecha FROM Caso c JOIN c.rolPersonaCasoList rpc");
		jpqlQuery.append(" LEFT JOIN c.fechaCasoList flc ");
		jpqlQuery.append(" WHERE rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersonaCaso);
		jpqlQuery.append(" AND c.estadoCaso<>:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_ESTADO_CASO);
		jpqlQuery.append(" AND c.servicioMateria.servicioMateriaPK.idServicio=:");
		jpqlQuery.append(Servicio.ENTIDAD_SERVICIO_PK);
		jpqlQuery.append(" AND c.servicioMateria.servicioMateriaPK.idMateria=:");
		jpqlQuery.append(Materia.ENTIDAD_MATERIA_PK);
		jpqlQuery.append(" AND c.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroCaso);
		jpqlQuery.append(" AND (flc.fechaCasoPK.tipoFecha = :fechaLimiteCierre) ");
		jpqlQuery.append(" ORDER BY flc.fecha DESC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idArbitro);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(estadoRegistroRolPersonaCaso, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_CASO, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(Servicio.ENTIDAD_SERVICIO_PK, servicioMateriaPK.getIdServicio());
		query.setParameter(Materia.ENTIDAD_MATERIA_PK, servicioMateriaPK.getIdMateria());
		query.setParameter(estadoRegistroCaso, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("fechaLimiteCierre", UtilDominios.ESTADO_TIPO_FECHA_CASO_LIMITE_CIERRE);

		query.setMaxResults(1);
		query.setFirstResult(0);

		List resultado = query.getResultList();
		Date fechaDisponibilidad = null;
		if (resultado != null && resultado.size() == 1) {
			fechaDisponibilidad = (Date) resultado.get(0);
		}
		return fechaDisponibilidad;
	}

	public List<Caso> consultarCasoPorPatron(String patron) {
		Long patronId;
		try {
			patronId = Long.parseLong(patron);
		} catch (NumberFormatException e) {
			patronId = -1l;
		}

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Caso c WHERE c.nombre like :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_NOMBRE);
		jpqlQuery.append(" OR c.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_NOMBRE, "%" + patron + "%");
		query.setParameter(Caso.ENTIDAD_CASO_PK, patronId);
		query.setMaxResults(10);

		return query.getResultList();
	}

	public Caso buscarCasoActivo(Long id) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Caso c WHERE c.estadoRegistro = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND c.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_PK, id);

		return (Caso) query.getSingleResult();
	}

	/**
	 * TRANSF009 Listar casos asignados Consulta los ids de los casos asignados
	 * al funcionario que se especifica en el DTO y aplicando los filtros que se
	 * pasan como parámetro. Si el rol del usuario es Conciliador, Arbitro,
	 * Secretario de tribunal o apoderado de algún caso solo se le dev.  
	 * 
	 * @param filtros
	 * @return
	 */
	public List<CasoAsignadoDTO> consultarCasosAsignados(FiltroCasosAsignadosDTO filtros,
			boolean noPrestadorDeServicioOApoderado, boolean esParte) {
		
		if (!filtros.tieneIdPersonaUsuario()) {
			throw new SimascException(FILTRO_CASO_ASIGNADO_NO_VALIDO_PARAMETROS_NULOS);
		}

		if (!filtros.tieneTipoServicio()) {
			throw new SimascException(FILTRO_CASO_ASIGNADO_NO_VALIDO_TIPO_SERVICIO_NULO);
		}

		if (filtros.tieneFechaDesde() && filtros.tieneFechaHasta()
				&& filtros.getFechaHasta().before(filtros.getFechaDesde())) {
			throw new SimascException(FILTRO_CASO_ASIGNADO_NO_VALIDO_FECHAS_INCOMPATIBLES);
		}

		if ((filtros.tieneFechaDesde() && !filtros.tieneFechaHasta())
				|| (!filtros.tieneFechaDesde() && filtros.tieneFechaHasta())) {
			throw new SimascException(FILTRO_CASO_ASIGNADO_NO_VALIDO_FECHA_FALTANTE);
		}

		// Si el código del caso es ingresado se deben ignorar las fechas
		if (filtros.tieneCodigoCaso() || esParte) {
			filtros.setFechaDesde(null);
			filtros.setFechaHasta(null);
		}

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso, ");
		nativeQuery.append("  c.nombre, ");
		nativeQuery.append("  c.fecha_radicacion, ");
		nativeQuery.append("  est.nombre, ");
		nativeQuery.append("  c.fecha_estado, ");
		nativeQuery.append("  sede.nombre, ");
		nativeQuery.append("  res.nombre, ");
		nativeQuery.append("  ser.nombre, ");
		nativeQuery.append("  c.etapa, ");
		nativeQuery.append("  c.id_servicio, ");
		nativeQuery.append("  ser.tipo ");
		nativeQuery.append("FROM CASO AS c ");
		nativeQuery.append(" LEFT JOIN  AUDIENCIA aud ");
		nativeQuery.append("  ON c.id_caso=aud.id_caso ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO func ");
		nativeQuery.append("  ON c.id_caso=func.id_caso ");
		nativeQuery.append(" LEFT JOIN ROL rolFunc ");
		nativeQuery.append("  ON func.id_rol=rolFunc.id_rol ");
		nativeQuery.append(" LEFT JOIN SEDE sede ");
		nativeQuery.append("  ON sede.id_sede=c.id_sede ");
		nativeQuery.append("  AND sede.estado_registro = ?31 ");
		nativeQuery.append(" INNER JOIN DOMINIO est ");
		nativeQuery.append("  ON est.codigo=c.estado_caso AND est.dominio= ?19");
		nativeQuery.append(" LEFT JOIN DOMINIO res ");
		nativeQuery.append("  ON res.codigo=c.resultado AND (res.dominio= ?20");
		nativeQuery.append(" OR res.dominio= ?21 )");
		if (filtros.tieneIdConvenio()) {
			nativeQuery.append(" LEFT JOIN CONVENIO con ");
			nativeQuery.append("  ON c.id_convenio=con.id_convenio ");
		}
		if (filtros.tieneApoderado()) {
			nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO apod ");
			nativeQuery.append("  ON c.id_caso=apod.id_caso ");
			nativeQuery.append(" LEFT JOIN ROL rolApod ");
			nativeQuery.append("  ON rolApod.id_rol=apod.id_rol ");
			nativeQuery.append(" LEFT JOIN PERSONA perApod ");
			nativeQuery.append("  ON apod.id_persona             =perApod.id_persona ");
		}
		if (filtros.tieneNumeroIdentificacionParte()) {
			nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO parte ");
			nativeQuery.append("  ON c.id_caso=parte.id_caso ");
			nativeQuery.append(" LEFT JOIN ROL rolParte ");
			nativeQuery.append("  ON rolParte.id_rol=parte.id_rol ");
			nativeQuery.append(" LEFT JOIN PERSONA perParte ");
			nativeQuery.append("  ON parte.id_persona             =perParte.id_persona ");
		}
		if (filtros.tieneIdAbogado()) {
			nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO abogado ");
			nativeQuery.append("  ON c.id_caso=abogado.id_caso ");
		}
		nativeQuery.append(" INNER JOIN SERVICIO ser ");
		nativeQuery.append("  ON c.id_servicio=ser.id_servicio ");
		if (filtros.tieneIdMateria()) {
			nativeQuery.append(" INNER JOIN MATERIA mat ");
			nativeQuery.append("  ON c.id_materia=mat.id_materia ");
		}
		nativeQuery.append(" WHERE ");
		nativeQuery.append("  c.estado_registro  =?31 ");;
		// Esta validación solo aplica si la persona es Arbitro, Conciliador,
		// Secretario de tribunal o Apoderado
		// Si es cualquiera de estos roles solo se devuelven los casos en los
		// que está asignado
		// De lo contrario (No prestador de servicio (secretarias, abogados,
		// Analistas de conciliación, responsables de convenios, auxiliar
		// administrativo,
		// coordinador del servicio, coordinador CAC, dirección CAC) no se tiene
		// en cuenta la asignación
		if (!noPrestadorDeServicioOApoderado) {
			nativeQuery.append("    AND func.id_persona = ?1");
			nativeQuery.append("  	AND func.estado_registro       =?31 ");
			nativeQuery.append("	AND	( ");
			nativeQuery.append("			rolFunc.nombre = ?16 ");

			// Validación para saber si la persona que esta consultando es un
			// arbitro
			boolean personaEsArbitro = false;
			for (String rol : UtilConstantes.ROLES_ARBITROS) {
				if (rol.equals(filtros.getNombreRol())) {
					personaEsArbitro = true;
					break;
				}
				
			}
			
			Boolean esConciliador = false;
			for (String rol : UtilConstantes.ROLES_CONCILIADOR) {
				if (rol.equals(filtros.getNombreRol())) {
					esConciliador = true;
					break;
				}
				
			}
		
			//Validación para saber si la persona que esta consultando es un conciliador
			if(esConciliador){
				nativeQuery.append("            AND func.estado       = ?22");
				nativeQuery.append("            AND func.tipo_nombramiento    =  ?23");				
			}

			// Validacion que aplica unicamente para los arbitros (Audiencia de
			// instalacion pendiente de ejecución o realizada)
			else if (personaEsArbitro) {
				nativeQuery.append("            AND func.estado       in (?34 ,?26)");
				nativeQuery.append(" AND c.estado_caso not in (?32, ?33) ");
			}

			nativeQuery.append("           	)");
			nativeQuery.append("  	AND rolFunc.estado_registro   =?31 ");
		} else if (esParte) {
			nativeQuery.append("    AND func.id_persona = ?1");
			nativeQuery.append("  	AND func.estado_registro       =?31 ");
			nativeQuery.append("	AND	 ");
			nativeQuery.append("			rolFunc.nombre IN ");
			nativeQuery.append(" ( SELECT cd.codigo_clasificado FROM CLASIFICADOR_DOMINIO cd ");
			nativeQuery.append(" WHERE cd.dominio_clasificador = ?17 ");
			nativeQuery.append(" AND cd.codigo_clasificador = ?18 ) ");
			nativeQuery.append(" AND c.estado_caso not in (?32, ?33) ");
		} else if (filtros.tieneIdPrestadorDeServicio()) {
			nativeQuery.append(" AND func.id_persona=?1");
			nativeQuery.append(" AND func.estado_registro       =?31 ");
			nativeQuery.append(" AND func.estado       = ?26");;
		}
		if (!esParte && filtros.getTipoServicio() != null) {
			nativeQuery.append(" AND ser.tipo=?2");
		}

		nativeQuery.append(" AND ser.estado_registro=?31 ");

		if (filtros.tieneEstadoCaso()) {
			nativeQuery.append(" AND c.estado_caso=?3");
		}
		// Fecha de radicacion del caso
		if (filtros.tieneFechaDesde() && filtros.tieneFechaHasta()) {			 
			nativeQuery.append(" AND CAST(c.fecha_radicacion AS DATE) <=?4");
			nativeQuery.append(" AND CAST(c.fecha_radicacion AS DATE) >=?5");
		}
		// Nombre del caso
		if (filtros.tieneNombreCaso()) {
			nativeQuery.append(" AND c.nombre LIKE ?6 ");
		}
		// Codigo del caso
		if (filtros.tieneCodigoCaso()) {
			nativeQuery.append(" AND c.id_caso = ?7");
		}
		// Convenio del caso
		if (filtros.tieneIdConvenio()) {
			nativeQuery.append(" AND con.id_convenio    = ?8");
			nativeQuery.append(" AND con.estado_registro=?31 ");
		}
		// Apoderado del caso
		if (filtros.tieneApoderado()) {
			nativeQuery.append("  	AND ( (rolApod.nombre   = ?27");
			nativeQuery.append("  		OR rolApod.nombre      = ?28 )");
			nativeQuery.append(
					"  		AND ( CONCAT(perApod.primer_nombre_o_razon_social, perApod.segundo_nombre, perApod.primer_apellido, perApod.segundo_apellido) ) LIKE ?9");
			nativeQuery.append("  		AND rolApod.estado_registro=?31 ");
			nativeQuery.append("  		AND perApod.estado_registro    = ?31 )");
			nativeQuery.append("  	AND apod.estado_registro   =?31 ");
			nativeQuery.append("  	AND rolApod.estado_registro   =?31 ");
		}
		// Parte del caso
		if (filtros.tieneNumeroIdentificacionParte()) {
			nativeQuery.append("   AND ( ( rolParte.nombre     = ?29");
			nativeQuery.append("  		OR rolParte.nombre          = ?30 )");
			nativeQuery.append("  		AND perParte.numero_documento LIKE ?10");
			nativeQuery.append("  		AND rolParte.estado_registro=?31 ");
			nativeQuery.append(" 		AND perParte.estado_registro    = ?31 )");
			nativeQuery.append("   AND parte.estado_registro   =?31 ");
			nativeQuery.append("   AND rolParte.estado_registro   =?31 ");
		}
		// Abogado del caso
		if (filtros.tieneIdAbogado()) {
			nativeQuery.append("   AND abogado.id_persona   =?11");
			nativeQuery.append("   AND abogado.estado_registro   =?31 ");
		}
		if (filtros.tieneIdServicio()) {
			nativeQuery.append("  	AND ser.id_servicio   =?12");
		}
		if (filtros.tieneIdMateria()) {
			nativeQuery.append("   AND mat.id_materia   =?13");
			nativeQuery.append("   AND mat.estado_registro   =?31 ");
		}
		if (filtros.tieneCuantia()) {
			nativeQuery.append("   AND c.tipo_cuantia   =?14");
		}
		if (filtros.tieneIdSede()) {
			nativeQuery.append("   AND sede.id_sede   =?15");
		}
		
		if(filtros.isArbitrajeConsumo()) {
			nativeQuery.append("   AND c.arbitraje_consumo   = 1");
		}

		nativeQuery.append(" GROUP BY c.id_caso, ");
		nativeQuery.append("  c.nombre, ");
		nativeQuery.append("  c.fecha_radicacion, ");
		nativeQuery.append("  est.nombre, ");
		nativeQuery.append("  c.fecha_estado, ");
		nativeQuery.append("  sede.nombre, ");
		nativeQuery.append("  res.nombre, ");
		nativeQuery.append("  ser.nombre, ");
		nativeQuery.append("  c.etapa, ");
		nativeQuery.append("  c.id_servicio, ");
		nativeQuery.append("  ser.tipo ");
		nativeQuery.append(" ORDER BY c.id_caso desc");

		Query query = em.createNativeQuery(nativeQuery.toString());
		if (!noPrestadorDeServicioOApoderado) {
			query.setParameter(1, filtros.getIdPersonaUsuario());
			query.setParameter(16, filtros.getNombreRol());
		} else if (esParte) {
			query.setParameter(1, filtros.getIdPersonaUsuario());
		} else if (filtros.tieneIdPrestadorDeServicio()) {
			query.setParameter(1, filtros.getIdPrestadorDeServicio());
		}

		if (esParte) {
			query.setParameter(17, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA);
			query.setParameter(18, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		} else if (filtros.getTipoServicio() != null) {
			query.setParameter(2, filtros.getTipoServicio());
		}

		if (filtros.tieneEstadoCaso()) {
			query.setParameter(3, filtros.getEstadoCaso());
		}
		if (filtros.tieneFechaDesde() && filtros.tieneFechaHasta()) {
			DateFormat formateador= new SimpleDateFormat("yyyy-MM-dd");
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(filtros.getFechaHasta());
			Calendar fin = Calendar.getInstance();
			fin.setTime(filtros.getFechaDesde());
			query.setParameter(4, formateador.format(UtilOperaciones.setearFinDelDia(inicio).getTime()));
			query.setParameter(5, formateador.format(UtilOperaciones.setearInicioDelDia(fin).getTime()));					
		}
		if (filtros.tieneNombreCaso()) {
			query.setParameter(6,'%'+filtros.getNombreCaso()+'%' );
		}
		if (filtros.tieneCodigoCaso()) {
			query.setParameter(7, filtros.getCodigoCaso());
		}
		if (filtros.tieneIdConvenio()) {
			query.setParameter(8, filtros.getIdConvenio());
		}
		if (filtros.tieneApoderado()) {
			query.setParameter(9, '%'+filtros.getApoderado().replaceAll("\\s", "")+'%');
		}
		if (filtros.tieneNumeroIdentificacionParte()) {
			query.setParameter(10, '%'+filtros.getNumeroIdentificacionParte()+'%');
		}
		if (filtros.tieneIdAbogado()) {
			query.setParameter(11, filtros.getIdAbogado());
		}
		if (filtros.tieneIdServicio()) {
			query.setParameter(12, filtros.getIdServicio());
		}
		if (filtros.tieneIdMateria()) {
			query.setParameter(13, filtros.getIdMateria());
		}
		if (filtros.tieneCuantia()) {
			query.setParameter(14, filtros.getCuantia());
		}
		if (filtros.tieneIdSede()) {
			query.setParameter(15, filtros.getIdSede());
		}
		
		query.setParameter(19, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(20, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(21, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		query.setParameter(22, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(23, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(24, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(25, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(26, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(27, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(28, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(29, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(30, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(31, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(32, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(33, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(34, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		
		return convertirResultadoACasosAsignadosDTO(query.getResultList());
	}
	
	//dgomezb
	
	/**
	 * SIMASC-CU-Arbitraje - Continuar Radicación de Caso 
	 * Lista los casos que quedaron en estado en creacion.
	 * @return
	 */
	public List<CasoIncompletoDTO> consultarCasosIncompletos() {
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso as codigoCaso,");
		nativeQuery.append(" c.id_servicio as idServicio, ");
		nativeQuery.append(" c.tipo_cuantia as tipoCuantia, ");
		nativeQuery.append(" c.valor_pretensiones as valorPretensiones, ");
		nativeQuery.append(" c.id_materia as idMateria, ");
		nativeQuery.append(" c.id_sede as idSede, ");
		nativeQuery.append(" c.amparo_de_pobreza as amparoDePobreza, ");
		nativeQuery.append(" c.pendiente_de_pago as pendienteDePago, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" c.fecha_radicacion as fechaRadicacion, ");
		nativeQuery.append(" 'EN CREACION' as estadoCaso, ");
		nativeQuery.append(" c.fecha_estado as fechaUltimoEstado, ");
		nativeQuery.append(" sede.nombre as sede, ");
		nativeQuery.append(" ser.nombre as nombreServicio, ");
		nativeQuery.append(" c.etapa ");
		nativeQuery.append("FROM CASO AS c WITH (NOLOCK)");
		nativeQuery.append(" LEFT JOIN  AUDIENCIA aud WITH (NOLOCK) ON c.id_caso=aud.id_caso ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO func WITH (NOLOCK) ON c.id_caso=func.id_caso ");
		nativeQuery.append(" LEFT JOIN ROL rolFunc WITH (NOLOCK) ON func.id_rol=rolFunc.id_rol ");
		nativeQuery.append(" INNER JOIN SEDE sede WITH (NOLOCK)  ON sede.id_sede=c.id_sede ");
		nativeQuery.append(" INNER JOIN SERVICIO ser WITH (NOLOCK)  ON c.id_servicio=ser.id_servicio ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" c.estado_caso = ?1 ");
		nativeQuery.append(" AND sede.estado_registro = ?2 ");
		nativeQuery.append(" AND ser.estado_registro = ?2 ");
		nativeQuery.append(" GROUP BY c.id_caso, c.id_servicio, c.tipo_cuantia, c.valor_pretensiones, ");
		nativeQuery.append(" c.amparo_de_pobreza, c.pendiente_de_pago, c.id_materia, c.id_sede, c.nombre, ");
		nativeQuery.append(" c.fecha_radicacion, c.fecha_estado, sede.nombre, ser.nombre, c.etapa ");
		nativeQuery.append(" ORDER BY c.id_caso desc ");
		Query query = em.createNativeQuery(nativeQuery.toString(), CasoIncompletoDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ENCREACION);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	private List<CasoAsignadoDTO> convertirResultadoACasosAsignadosDTO(List<Object[]> registros) {
		List<CasoAsignadoDTO> casos = new ArrayList<>();
		for (Object[] registro : registros) {
			casos.add(convertirRegistroACasoAsignadoDTO(registro));
		}

		return casos;
	}

	private CasoAsignadoDTO convertirRegistroACasoAsignadoDTO(Object[] registro) {
		CasoAsignadoDTO caso = new CasoAsignadoDTO();
		Object reg;
		caso.setCodigoCaso(((reg = registro[0]) == null ? null : ((BigDecimal) reg).longValue()));
		caso.setNombreCaso(((reg = registro[1]) == null ? null : (String) reg));
		caso.setFechaRadicacion(((reg = registro[2]) == null ? null : (Date) reg));
		caso.setEstadoCaso(((reg = registro[3]) == null ? null : (String) reg));
		caso.setFechaUltimoEstado(((reg = registro[4]) == null ? null : (Date) reg));
		caso.setSede(((reg = registro[5]) == null ? null : (String) reg));
		caso.setResultado(((reg = registro[6]) == null ? null : (String) reg));
		caso.setNombreServicio(((reg = registro[7]) == null ? null : (String) reg));
		caso.setEtapa(((reg = registro[8]) == null ? null : (String) reg));
		caso.setIdServicio(((reg = registro[9]) == null ? null : ((BigDecimal) reg).longValue()));
		caso.setTipoServicio(((reg = registro[10]) == null ? null : ((String) reg)));

		return caso;
	}

	public List<CasoAsignadoDTO> consultarCasosAsignadosDigitalizador(FiltroCasosAsignadosDTO filtros) {

		if (!filtros.tieneIdPersonaUsuario())
			throw new SimascException(FILTRO_CASO_ASIGNADO_NO_VALIDO_PARAMETROS_NULOS);

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT distinct c.id_caso as codigoCaso, c.nombre as nombreCaso, c.fecha_radicacion as fechaRadicacion, est.nombre as estadoCaso ");
		nativeQuery.append(", c.fecha_estado fechaUltimoEstado, sede.nombre as sede, res.nombre as resultado, ser.nombre as nombreServicio, c.etapa as etapa ");
		nativeQuery.append(" FROM  DOCUMENTO d ");
		nativeQuery.append("INNER JOIN CASO AS c ON c.id_caso = d.id_caso ");
		nativeQuery.append("INNER JOIN DOMINIO est on est.codigo = c.estado_caso and est.dominio = ?1 ");
		nativeQuery.append("INNER JOIN SEDE sede on sede.id_sede = c.id_sede ");
		nativeQuery.append("INNER JOIN SERVICIO ser ON c.id_servicio=ser.id_servicio ");
		nativeQuery.append("LEFT JOIN DOMINIO res on res.codigo = c.resultado and res.dominio = ?2 ");
		nativeQuery.append("WHERE d.estado_registro = ?3 ");
		nativeQuery.append("and c.estado_registro = ?3 ");
		nativeQuery.append("and sede.estado_registro = ?3 ");
		nativeQuery.append("and ser.estado_registro = ?3 ");
		nativeQuery.append("and d.id_digitalizador = ?8 ");

		// Fecha de radicacion del caso
		if (filtros.tieneFechaDesde() & filtros.tieneFechaHasta()) {
			nativeQuery.append(" AND c.fecha_radicacion<=?4");
			nativeQuery.append(" AND c.fecha_radicacion>=?5");
		}
		// Nombre del caso
		if (filtros.tieneNombreCaso()) {
			nativeQuery.append("  	AND c.nombre LIKE ?6 ");
		}
		// Codigo del caso
		if (filtros.tieneCodigoCaso()) {
			nativeQuery.append("  	AND c.id_caso = ?7");
		}
		nativeQuery.append(" ORDER BY c.id_caso desc ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), CasoAsignadoDTO.class);

		query.setParameter(1, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(2, UtilDominios.DOMINIO_RESULTADO_CASO);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, filtros.getIdPersonaUsuario());
		
		if (filtros.tieneFechaDesde() & filtros.tieneFechaHasta()) {
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(filtros.getFechaHasta());
			Calendar fin = Calendar.getInstance();
			fin.setTime(filtros.getFechaDesde());
			query.setParameter(4, UtilOperaciones.setearFinDelDia(inicio));
			query.setParameter(5, UtilOperaciones.setearInicioDelDia(fin));
		}
		if (filtros.tieneNombreCaso()) {
			query.setParameter(6, '%'+filtros.getNombreCaso()+'%' );
		}
		if (filtros.tieneCodigoCaso()) {
			query.setParameter(7, filtros.getCodigoCaso());
		}


		return query.getResultList();
	}



	/**
	 * Devuelve una lista de los casos con los ids que se pasan como parametro
	 * 
	 * @param casos
	 * @return Una lista con la información para desplegar en el reporte
	 */
	public List<Caso> consultarCasosPorId(List<Long> idCasos) {

		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT c FROM Caso c WHERE c.idCaso ")
				.append(UtilConsultasSQL.clausulaInSQLSNumeros(idCasos));

		Query query = mp.createQuery(jpqlQuery.toString());

		return query.getResultList();

	}

	public List<CasoDTO> obtenerCasosPorEtapa(String etapa) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT id_caso as idCaso, nombre FROM Caso c WHERE c.etapa = ?1 ");
		jpqlQuery.append(" and c.estado_registro = ?2");

		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), CasoDTO.class);
		query.setParameter(1, etapa);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	public Caso actualizarCaso(Caso caso) {
		return (Caso) mp.updateObject(caso);

	}
	

	/**
	 * Se obtienen los casos cerrados o registrados realizando filtros por
	 * idCentro, estados del caso (REGISTRADO, CERRADO), tipo de eventos (REGISTRO),.
	 * 
	 * @param resultadoCaso
	 * @param idCentro
	 * @param estadosCaso
	 * @return List<Caso>: Lista de casos.
	 */
	@SuppressWarnings("unchecked")
	public List<CasoDTO> obtenerCasosCerrados(String resultadoCaso, Long idCentro, List<String> estadosCasos,
			List<String> tipoEventos, List<String> roles) {
		StringBuilder queryNative = new StringBuilder();
		queryNative.append(" SELECT c.id_caso AS idCaso, ");
		queryNative.append(" c.nombre AS nombre ");
		queryNative.append(" FROM CASO c ");
		
		queryNative.append(" INNER JOIN SERVICIO s ");
		queryNative.append(" ON  s.id_servicio = c.id_servicio ");
		queryNative.append(" AND s.tipo = ?6 ");
		queryNative.append(" AND s.estado_registro = ?1 ");
		if (estadosCasos != null)
			queryNative.append(" AND c.estado_caso ").append(UtilConsultasSQL.clausulaInSQLStrings(estadosCasos));

		if (tipoEventos != null) {
			queryNative.append(" CROSS APPLY (SELECT TOP 1 e.* FROM EVENTO e  WHERE e.id_caso = c.id_caso ");
			queryNative.append(" AND e.tipo_evento ").append(UtilConsultasSQL.clausulaInSQLStrings(tipoEventos));
			queryNative.append(" AND e.fecha_evento >= DATEADD(mm, DATEDIFF(mm,0, GETDATE())-1,0) ");
			queryNative.append(" AND e.fecha_evento <= Convert(date,dateadd(d,1,GETDATE())) ");
			queryNative.append(" AND e.estado_registro = ?1 order by e.fecha_evento DESC ) ev ");
		}

		if (roles != null) {
			queryNative.append(" LEFT JOIN ROL_PERSONA_CASO rpc ");
			queryNative.append(" ON  rpc.id_caso = c.id_caso ");
			queryNative.append(" AND rpc.estado_registro = ?1 ");
			queryNative.append(" AND rpc.estado = ?2 ");
			queryNative.append(" AND rpc.id_rol = (select id_rol from rol where nombre ")
					   .append(UtilConsultasSQL.clausulaInSQLStrings(roles))
					   .append(" and estado_registro = ?1) ");
		}
		
		queryNative.append(" WHERE c.estado_registro = ?1 ");

		if (resultadoCaso != null)
			queryNative.append(" AND c.resultado = ?3 ");

		if (roles != null)
			queryNative.append(" AND rpc.id_caso IS NULL ");

		if (idCentro != null) {
			queryNative.append(" AND ( CASE WHEN c.id_servicio = ?4 THEN  (SELECT cv.id_centro FROM CASO ca "
					+ "INNER JOIN CONVENIO cv ON ca.id_convenio = cv.id_convenio AND ca.id_caso = c.id_caso  AND  ca.estado_registro = ?1 ) ");
			queryNative.append(" ELSE  (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se "
					+ "ON se.id_sede = cas.id_sede WHERE c.id_caso = cas.id_caso AND cas.estado_registro = ?1 ) END ) = ?5 ");
		}

		

		Query query = em.createNativeQuery(queryNative.toString(), CasoDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		query.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(6, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);

		if (resultadoCaso != null)
			query.setParameter(3, resultadoCaso);

		if (idCentro != null) {
			query.setParameter(5, idCentro);
		}

		return query.getResultList();
	}

	/**
	 * Devuelve el nombre del caso
	 * 
	 * @param idCaso
	 * @return nombre caso
	 */
	@SuppressWarnings("unchecked")
	public String consultarNombreCaso(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT c.nombre ");
		jpqlQuery.append(" FROM Caso c ");
		jpqlQuery.append(" WHERE c.id_Caso = ?1 ");

		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), String.class);
		query.setParameter(1, idCaso);

		return (String) query.getSingleResult();
	}

	public List<Caso> casoActivosConSupensiones() {
		List<Caso> casosSupensiones = null;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT DISTINCT(C)  FROM Caso C,Suspension S ");
		jpqlQuery.append(" WHERE C.estadoCaso NOT IN ('CER','CAN')");
		jpqlQuery.append(" AND C.idCaso = S.idCaso");
		jpqlQuery.append(" AND C.estadoRegistro = 'ACT'");
		jpqlQuery.append(" AND S.estadoRegistro = 'ACT'");
		Query query = mp.createQuery(jpqlQuery.toString());

		casosSupensiones = query.getResultList();

		return casosSupensiones;

	}

	/**
	 * Otiene el caso por id y tipo de servicio
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 */
	public Caso consultarCasoPorIdTipoServicio(Long idCaso, String tipoServicio) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c FROM Caso c ");
		jpqlQuery.append("JOIN c.servicioMateria sm ");
		jpqlQuery.append("JOIN sm.servicio s ");
		jpqlQuery.append("WHERE c.idCaso = ?1 ");
		jpqlQuery.append("AND s.tipo = ?2 ");

		Query query = em.createQuery(jpqlQuery.toString(), Caso.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, tipoServicio);

		return (Caso) query.getSingleResult();
	}

	/**
	 * Valida si se puede realizar cambio de estado de un caso, basado en la
	 * parametrizacion de estados
	 * 
	 * @param estadoInicial
	 *            estado inicial del caso
	 * @param estadoFinal
	 *            estado final del caso
	 * @return true si el cambio es permitido, false de lo contrario.
	 */
	public boolean validarCambioEstadoCaso(String estadoInicial, String estadoFinal) {
		boolean valido = true;
		
		return valido;
	}

	/**
	 * Permite obtener los casos pendientes por asignar a un conciliador.
	 * 
	 * @author aperez
	 * @param idCentros:
	 *            Lista de centros que tiene asociado el usuario.
	 * @return List<CasosNoAsignadosDTO>: Lista de los casos no asignados para
	 *         reparto.
	 */
	@SuppressWarnings("unchecked")
	public List<CasosNoAsignadosDTO> listarCasosNoAsignadosPorReparto(List<Long> idCentros) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso AS idCaso, ");
		nativeQuery.append("c.nombre  AS nombreCaso, ");
		nativeQuery.append("CASE WHEN c.id_servicio = ?4 ");
		nativeQuery.append("THEN s.nombre ");		
		nativeQuery.append("ELSE co.nombre END AS nombreConvenio, ");
		nativeQuery.append("c.fecha_radicacion AS fechaRadicacion, ");
		nativeQuery.append("s.id_servicio AS idServicio ");
		nativeQuery.append("FROM CASO c ");

		nativeQuery.append("INNER JOIN SERVICIO s ON c.id_servicio= s.id_servicio AND s.tipo= ?1 ");
		nativeQuery.append("AND s.estado_registro=?2 ");

		nativeQuery.append("LEFT JOIN CONVENIO co ON c.id_convenio = co.id_convenio ");
		nativeQuery.append("AND co.estado_registro=?2 ");

		nativeQuery.append("INNER JOIN SEDE se ON c.id_sede = se.id_sede ");
		nativeQuery.append("AND se.estado_registro=?2 ");
		nativeQuery.append("AND se.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idCentros));

		nativeQuery.append("WHERE c.estado_caso=?3 ");
		nativeQuery.append("AND c.estado_registro=?2 ");
		nativeQuery.append("ORDER BY c.id_caso DESC	 ");

		Query q = em.createNativeQuery(nativeQuery.toString(), CasosNoAsignadosDTO.class);

		q.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(3, UtilDominios.ESTADO_CASO_PENDIENTE_POR_DESIGNACION);
		q.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION);

		return q.getResultList();
	}

	public InfoCasoPDFDTO infoCaso(long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	c.id_caso as idCaso, ");
		nativeQuery.append(" hechos as hechos, ");
		nativeQuery.append(" pretensiones as pretensiones, ");
		nativeQuery.append(" fecha_radicacion as fechaRadicacion, ");
		nativeQuery
				.append(" (select nombre from DOMINIO where codigo=c.tipo_cuantia and dominio= ?1) as tipoCuantia, ");
		nativeQuery.append(" valor_pretensiones as valorPretensiones, ");
		nativeQuery.append(" isnull(c.inicio_de_conflicto,5) inicioConflicto, ");
		nativeQuery.append(" (select nombre from materia where id_materia=c.id_materia) AsuntoConciliacion, ");
		nativeQuery.append(" (select nombre from sede where id_sede=c.id_sede) sede, ");
		nativeQuery.append(" (select nombre from centro where id_centro = (select id_centro from SEDE s2 where id_sede = c.id_sede)) centro ");
		nativeQuery.append(" from caso  c ");
		nativeQuery.append(" where c.id_caso= ?2 ");

		Query q = em.createNativeQuery(nativeQuery.toString(), InfoCasoPDFDTO.class);
		q.setParameter(1, UtilDominios.DOMINIO_TIPO_CUANTIA_CONCILIACION);
		q.setParameter(2, idCaso);
		return (InfoCasoPDFDTO) q.getSingleResult();

	}

	/**
	 * Obtiene la informacion del caso para la modificacion de parametros para
	 * reparto del caso (CONF-103).
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param isTramiteOrdinario:
	 *            Bandera que permite saber si el servicio es de tramite
	 *            ordinario.
	 * @return InformacionCasoDTO: DTO que contiene informacion del caso.
	 */
	@SuppressWarnings("unchecked")
	public InformacionCasoDTO obtenerInformacionCasoPorIdCaso(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();

		String nombreConciliador = "(SELECT CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) "
				+ "FROM PERSONA p, PERSONA_SOLICITUD ps, TIPO_DE_SERVICIO_ROL tsr  WHERE p.id_persona = ps.id_persona "
				+ "AND ps.id_rol = tsr.id_rol AND tsr.tipo_servicio=?3 AND c.id_solicitud_servicio = ps.id_solicitud_servicio "
				+ "AND ps.estado_registro =?1  AND tsr.estado_registro=?1 AND p.estado_registro = ?1 ) AS nombreConciliador, ";
		
		nativeQuery.append("SELECT c.id_caso AS idCaso, ");
		nativeQuery.append("c.nombre AS nombreCaso, ");
		nativeQuery.append("c.tipo_cuantia AS tipoCuantia, ");
		nativeQuery.append("c.valor_pretensiones AS cuantia, ");
		nativeQuery.append("c.id_servicio AS tipoServicio, ");
		nativeQuery.append("c.id_convenio AS idConvenio, ");
		nativeQuery.append("co.duracion_audiencias AS duracionAudiencias, ");
		nativeQuery.append("co.nombre AS nombreConvenio, ");
		nativeQuery.append("c.id_materia AS idMateria, ");
		nativeQuery.append("(SELECT nombre FROM MATERIA m WHERE c.id_materia = m.id_materia AND m.estado_registro = ?1) "
						+ "AS nombreMateria, ");
		nativeQuery.append("(SELECT e.observaciones FROM EVENTO e WHERE e.fecha_evento = "
				+ "(SELECT MAX(fecha_evento) FROM evento WHERE tipo_evento = ?2 AND e.id_caso = c.id_caso )) AS motivoNoReparto, ");
		nativeQuery.append(" (select fecha_inicio_audiencia from SOLICITUD_SERVICIO WHERE id_solicitud_servicio = c.id_solicitud_servicio) AS fechaAudienciaSolicitud, ");
		nativeQuery.append(nombreConciliador);
		nativeQuery.append("s.id_centro AS idCentro, ");
		nativeQuery.append("s.nombre AS nombreSede, ");
		nativeQuery.append("au.hora_inicio as horaInicioAudiencia, ");
		nativeQuery.append("au.hora_fin as horaFinAudiencia, ");
		nativeQuery.append("ser.nombre as nombreServicio ");
		nativeQuery.append("FROM CASO c ");
		nativeQuery.append("INNER JOIN SERVICIO ser ");
		nativeQuery.append("ON ser.id_servicio = c.id_servicio  ");
		nativeQuery.append("LEFT JOIN CONVENIO co ");
		nativeQuery.append("ON c.id_convenio = co.id_convenio AND co.estado_registro= ?1 ");
		nativeQuery.append("LEFT JOIN AUDIENCIA au ");
		nativeQuery.append("ON au.id_caso = c.id_caso AND au.estado = ?5 AND au.estado_registro = ?1 ");
		nativeQuery.append("INNER JOIN SEDE s ");
		nativeQuery.append("ON s.id_sede = c.id_sede AND s.estado_registro= ?1 ");
		nativeQuery.append("WHERE c.estado_registro = ?1 ");
		nativeQuery.append("AND c.id_caso = ?4 ");

		Query q = em.createNativeQuery(nativeQuery.toString(), InformacionCasoDTO.class);
		q.setParameter(2, UtilDominios.TIPO_EVENTO_REPARTO_NO_EXITOSO);
		q.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, idCaso);
		q.setParameter(5, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);

		List<InformacionCasoDTO> informacionCaso = q.getResultList();
		if (informacionCaso.isEmpty())
			throw new SimascException(NO_EXISTEN_RESULTADOS);
		else
			return informacionCaso.get(0);
	}
	
	/**
	 * Método que obtiene los casos asociados a un convenio que no cuenta con audiencias
	 * @param idConvenio
	 * @return
	 */
	public List<CasoDTO> consultarCasosConvenioAudienciaPendiente(Long idConvenio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select c.id_caso idCaso, c.nombre ");
		nativeQuery.append("from caso c left join audiencia a on c.id_caso = a.id_caso ");
		nativeQuery.append("and a.estado_registro = ?2 ");
		nativeQuery.append("where c.id_convenio = ?1 ");
		nativeQuery.append("and c.estado_registro = ?2 ");
		nativeQuery.append(" and a.id_caso is null ");
		nativeQuery.append(" order by c.nombre");
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), CasoDTO.class);
		query.setParameter(1, idConvenio);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}
	
	/**
	 * Método que obtiene los codigos de los casos a los que se encuentre asociandoa
	 * @param idPersona
	 * @param rol
	 * @return
	 */
	public List<Object> obtenerCasosPersonaPorRol(Long idPersona, String rol, Long idConvenio) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select distinct c.id_caso from rol_persona_caso rpc "); 
		nativeQuery.append(" inner join caso c on c.id_caso = rpc.id_caso and c.id_convenio = ?4 and c.estado_registro = ?5 ");
		nativeQuery.append(" where rpc.id_persona = ?1 and rpc.id_rol = (select id_rol from rol where nombre = ?2) ");
		nativeQuery.append(" and rpc.estado_registro = ?5 ");
		nativeQuery.append(" and rpc.estado <> ?3");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idPersona);
		query.setParameter(2, rol);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(4, idConvenio);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}
	

	
	
	public Long consultarDiferenciaFechaCaso(long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select	DATEDIFF ( day,c.fecha_radicacion, getdate() )  ");
		nativeQuery.append(" from caso c ");	
		nativeQuery.append(" where c.id_caso= ?1 ");
		nativeQuery.append(" and c.estado_registro= ?2 ");


		Query q = em.createNativeQuery(nativeQuery.toString(), Long.class);
		q.setParameter(1, idCaso);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return (Long) q.getSingleResult();

	}
	
	/**
	 * Método para obtener los datos del caso del cual se reversará el resultado
	 * @param idCaso
	 * @return
	 */
	public List<DatosReversarResultadoDTO> consultarDatoCasoReversarJornada(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select c.nombre as nombreCaso ");
		nativeQuery.append(", (select nombre from dominio where dominio = ?1 and codigo = c.resultado) as resultado ");
		nativeQuery.append(", concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) as conciliador ");
		nativeQuery.append("from caso c  ");
		nativeQuery.append("inner join rol_persona_caso rpc2 on c.id_caso = rpc2.id_caso and rpc2.estado <> ?2 and rpc2.tipo_nombramiento = ?3 and rpc2.estado_registro = ?4 ");
		nativeQuery.append("inner join PERSONA p on rpc2.id_persona = p.id_persona and p.estado_registro = ?4 ");
		nativeQuery.append("inner join tipo_de_servicio_rol tsr on tsr.id_rol = rpc2.id_rol and tsr.tipo_servicio = ?5 and tsr.estado_registro = ?4 ");
		nativeQuery.append("inner join convenio co on co.id_convenio = c.id_convenio and co.tipo_convenio = ?6 and co.estado_registro = ?4 ");
		nativeQuery.append("where c.id_caso = ?7 ");
		nativeQuery.append("and c.resultado is not null");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), DatosReversarResultadoDTO.class);
		q.setParameter(1, UtilDominios.DOMINIO_RESULTADO_CASO_CONCILIACION);
		q.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		q.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		q.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(6, UtilDominios.TIPO_CONVENIO_JORNADA);
		q.setParameter(7, idCaso);
		
		return q.getResultList();
	}
	
	/**
	 * Consulta los casos sin cerrar asignados al conciliador autenticado
	 * CON-F-102
	 * 
	 * @author cagonzalez
	 * @param idConciliador
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CasosSinCerrarDTO> consultarCasosSinCerrarConciliacion(Long idConciliador) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT cs.id_caso AS idCaso, cs.nombre AS nombre, cs.fecha_radicacion AS fechaRadicacion FROM CASO cs ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO r on ");
		nativeQuery.append(" cs.id_caso=r.id_caso  ");
		nativeQuery.append(" and r.id_rol IN ");
		nativeQuery.append(" (select ts.ID_ROL from TIPO_DE_SERVICIO_ROL ts ");
		nativeQuery.append(" where tipo_servicio=?1 ");
		nativeQuery.append(" and ts.estado_registro=?3 ) ");		
		nativeQuery.append(" and r.id_persona=?2 ");
		nativeQuery.append(" and r.estado_registro=?3 ");
		nativeQuery.append(" where cs.ESTADO_CASO NOT IN (?4) ");
		nativeQuery.append(" and (exists (select c.* from caso c inner join SOLICITUD_PRORROGA s on ");
		nativeQuery.append(" c.id_caso=s.id_caso ");
		nativeQuery.append(" where (Cast(getDate() as DATE)  between ");
		nativeQuery.append(" Cast(dateadd(d,-5,(select top 1 fecha_prorroga from SOLICITUD_PRORROGA order by fecha_solicitud desc)) AS DATE) ");
		nativeQuery.append("  and Cast(getDate() as DATE) ");
		nativeQuery.append(" or ((select top 1 Cast(fecha_solicitud as DATE) from SOLICITUD_PRORROGA order by fecha_solicitud desc) =  Cast(getDate() as DATE) )) ");
		nativeQuery.append(" and c.estado_registro=?3 ");
		nativeQuery.append(" and s.estado_registro=?3 ");
		nativeQuery.append(" AND c.id_caso=cs.id_caso  ");
		nativeQuery.append(" AND GETDATE() >= s.fecha_solicitud ");
		nativeQuery.append(" AND c.estado_caso NOT IN (?4) ");
		nativeQuery.append(" AND (DATEDIFF ( day,c.fecha_radicacion, getdate() )) > 80 )   ");
		nativeQuery.append(" OR (DATEDIFF ( day,cs.fecha_radicacion, getdate() )) BETWEEN 81 AND 90) ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), CasosSinCerrarDTO.class);
		
		query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(2, idConciliador);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(4, UtilDominios.ESTADO_CASO_REGISTRADO);
		
		return query.getResultList();
	}
	
	/**
	 * Método que obtiene la cantidad de casos cerrados en el periodo indicado por resultado
	 * o todos los que tengan resultado para la persona con rol de conciliador
	 * @param resultadoCaso si viene nulo se obtienen todos los casos cerrados en los que la persona se encuentra como conciliador
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	public BigDecimal cantidadCasosConciliadorPorEstado(String resultadoCaso, Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select count(*) from caso c ");
		nativeQuery.append(" inner join rol_persona_caso rpc on c.id_caso = rpc.id_caso and rpc.estado_registro = ?1 and rpc.estado = ?2 ");
		nativeQuery.append(" inner join TIPO_DE_SERVICIO_ROL t on rpc.id_rol = t.id_rol and tipo_servicio = ?3 and t.estado_registro = ?1 ");
		nativeQuery.append(" where rpc.id_persona = ?4 ");
		nativeQuery.append(" and c.estado_registro = ?1 ");
		if(resultadoCaso != null)
			nativeQuery.append(" and c.resultado = ?5 ");
		else
			nativeQuery.append(" and c.resultado is not null ");
		nativeQuery.append(" and c.estado_caso = ?6 ");
		nativeQuery.append(" and c.fecha_estado between CAST(?7 as date) and CAST(?8 as date) ");
		Query query = em.createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, idPersona);
		if(resultadoCaso != null)
			query.setParameter(5, resultadoCaso);
		query.setParameter(6, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(7, periodoDesde);
		query.setParameter(8, periodoHasta);
		return (BigDecimal) query.getSingleResult();
	}
	
	/**
	 * Permite consultar los casos pendientes por control de legalidad.
	 * (CONF-111/CONF-126)
	 * 
	 * @param reasignarAnalista:
	 *            Indica que pertenece a la consulta del caso CONF-111
	 * @return List<CasosControlLegalidadDTO>
	 */
	public List<CasosControlLegalidadDTO> consultarCasosParaControlLegalidad(boolean reasignarAnalista, String rol, Long idPersona) {

		String rolAnalista = UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION;
		String rolConciliador= UtilDominios.ROL_PERSONA_CONCILIADOR;
		String estadoCasoAsignado= UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD;
		
		if (rol != null && rol.equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD)) {
			
			rolAnalista = UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD;
			rolConciliador= UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD;
			estadoCasoAsignado= UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_CALIDAD;
			
		}
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso as idCaso, ");
		nativeQuery.append("c.nombre as caso, ");
		nativeQuery.append(
				"(select stuff((select ', ' + concat (pcon.primer_nombre_o_razon_social, ' ', pcon.segundo_nombre, ' ', pcon.primer_apellido, ' ', pcon.segundo_apellido)  for xml path('')), 1, 1, '')) as conciliador, ");
		nativeQuery.append("a.hora_inicio AS fechaUltimaAudiencia, ");
		nativeQuery.append("c.resultado as resultadoCaso, ");
		nativeQuery.append("c.id_servicio as idServicio, ");

		if (reasignarAnalista) {
			nativeQuery.append(
					"(select stuff((select ', ' + concat (pacon.primer_nombre_o_razon_social, ' ', pacon.segundo_nombre, ' ', pacon.primer_apellido, ' ', pacon.segundo_apellido)  for xml path('')), 1, 1, '')) as analistaConciliador, ");
			nativeQuery.append("pacon.id_persona as idAnalistaConciliador, ");
			nativeQuery.append("dbo.fechaLimiteEstudioCaso(erpc.fecha_de_asignacion) AS fechaLimiteEstudioCaso ");
		} else {
			nativeQuery.append("c.fecha_radicacion AS fechaRadicacionCaso, ");
			nativeQuery.append("a.resultado AS resultadoUltimaAudiencia ");
		}
		nativeQuery.append("FROM CASO c ");
		nativeQuery.append("INNER JOIN ROL_PERSONA_CASO con ON con.id_caso= c.id_caso ");
		nativeQuery.append("AND con.estado_registro = ?1 ");
		nativeQuery.append("AND con.estado = ?2 ");
		nativeQuery.append("AND con.tipo_nombramiento = ?3 ");				
		nativeQuery.append("INNER JOIN PARAMETRO_SERVICIO_SORTEO pss on con.id_rol = pss.id_rol ");
		nativeQuery.append("and pss.id_servicio = c.id_servicio ");
		nativeQuery.append("INNER JOIN PERSONA pcon ON pcon.id_persona = con.id_persona ");
		nativeQuery.append("AND pcon.estado_registro = ?1 ");
		nativeQuery.append("LEFT JOIN ROL_PERSONA_CASO acon ON acon.id_caso= c.id_caso ");
		nativeQuery.append("AND acon.estado_registro = ?1 ");
		nativeQuery.append("AND acon.estado = ?5 ");
		nativeQuery.append("AND acon.id_rol = (select id_rol from rol where nombre = ?6 and estado_registro = ?1) ");
		if(reasignarAnalista){
			nativeQuery.append("LEFT JOIN PERSONA pacon ON pacon.id_persona = acon.id_persona ");
			nativeQuery.append("AND pacon.estado_registro = ?1 ");

			nativeQuery.append("LEFT JOIN EVENTO_ROL_PERSONA_CASO erpc ON erpc.id_caso = c.id_caso ");
			nativeQuery.append("AND erpc.estado_asignado = ?5 AND erpc.fecha_de_asignacion = "
					+ " (SELECT MIN(fecha_de_asignacion) FROM EVENTO_ROL_PERSONA_CASO WHERE id_caso = c.id_caso  AND estado_asignado = ?5 ) ");
		}

		nativeQuery.append("INNER JOIN AUDIENCIA a ON a.id_caso = c.id_caso ");
	
		nativeQuery
				.append("AND a.consecutivo = (SELECT MAX(consecutivo) FROM AUDIENCIA WHERE id_caso = c.id_caso AND estado = ?7 "
						+ "AND a.estado_registro = ?1 ) ");

		nativeQuery.append("WHERE c.estado_caso = ?8 ");
		nativeQuery.append("AND c.estado_registro = ?1 ");
		if(!reasignarAnalista)
			nativeQuery.append("AND acon.id_persona = ?9 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), CasosControlLegalidadDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(4, rolConciliador);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		query.setParameter(6, rolAnalista);
		query.setParameter(7, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(8, estadoCasoAsignado);
		query.setParameter(9, idPersona);

		return query.getResultList();
	}
	
	/**
	 * Método que obtiene la cantidad de casos cerrados a tiempo o con solicitud de prorroga 
	 * en los que la persona se encuentra
	 * como conciliador para el periodo recibibdo
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param cierreOportuno indica si se debe obtener la cantidad de casos cerrados a tiempo o 
	 * 						 aquellos que cuentan con una solicitud de prorroga
	 * @return
	 */
	public BigDecimal cantidadCasosNoCerradosConSolicitud(Long idPersona,
			Date periodoDesde, Date periodoHasta, boolean cierreOportuno) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select COUNT(distinct C.id_caso) from caso c ");
		nativeQuery.append(" inner join rol_persona_caso rpc on c.id_caso = rpc.id_caso and rpc.estado_registro = ?1 and rpc.estado = ?2 ");
		nativeQuery.append(" inner join TIPO_DE_SERVICIO_ROL t on rpc.id_rol = t.id_rol and tipo_servicio = ?3 and t.estado_registro = ?1 ");
		nativeQuery.append(" where c.estado_registro = ?1 ");
		nativeQuery.append(" AND RPC.id_persona = ?4 ");
		nativeQuery.append(" AND DATEADD(D,(select VALOR_NUMERICO from PARAMETROS_GENERALES where codigo = ?5 AND TIPO = ?6 ),C.fecha_radicacion) ");
		if(cierreOportuno) {
			nativeQuery.append(" >= C.fecha_estado ");
			nativeQuery.append(" and c.fecha_estado between ?7 and ?8 ");
			nativeQuery.append(" and c.estado_caso = ?9");
		} else {
			nativeQuery.append(" between ?7 and ?8 ");
			nativeQuery.append(" AND EXISTS (SELECT 1 FROM SOLICITUD_PRORROGA WHERE ID_CASO = C.id_caso AND estado_registro = ?1 AND fecha_prorroga > ?8) ");
			nativeQuery.append(" and c.estado_caso <> ?9 ");			
		}

		Query query = em.createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, idPersona);
		query.setParameter(5, UtilConstantes.DIAS_CIERRE_CASO);
		query.setParameter(6, UtilConstantes.TIPO_PARAMETRO_CIERRE_CASO);
		query.setParameter(7, periodoDesde);
		query.setParameter(8, periodoHasta);
		query.setParameter(9, UtilDominios.ESTADO_CASO_REGISTRADO);
		
		return (BigDecimal) query.getSingleResult();
	}
	
	/**
	 * Consulta que retorna los casos que se radicaron el dia anterior y no tienen conciliador 
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> alertaCasosSinConciliador( Long valor, String tipoPeriodicidad){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.nombre AS nombreCaso, ");
		nativeQuery.append(" sed.id_centro AS idCentro ");
		nativeQuery.append(" FROM CASO ca  ");
		nativeQuery.append(" INNER JOIN SERVICIO se ON se.id_servicio = ca.id_servicio AND se.estado_registro = ?1 AND se.tipo = ?3 ");
		nativeQuery.append(" LEFT JOIN SEDE sed ON ca.id_sede = sed.id_sede AND sed.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN ROL_PERSONA_CASO rpc ON ca.id_caso = rpc.id_caso AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?3 ) ");
		nativeQuery.append(" AND rpc.tipo_nombramiento = ?4 AND rpc.estado IN ( ?5 ,?6 ) ");
		nativeQuery.append(" WHERE rpc.id_caso IS NULL ");
		nativeQuery.append(" AND ca.estado_registro = ?1 "); 
		nativeQuery.append(" AND DATEDIFF ( dd , ca.fecha_radicacion , GETDATE() ) < 100 "); 
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){
			nativeQuery.append(" AND dbo.diasHabilesEntreDosFechas(ca.fecha_radicacion, GETDATE()) >= ?2");		
			
		}else{
			nativeQuery.append(" AND DATEDIFF ( dd , au.hora_inicio , GETDATE() ) >= ?2 ");
		}		
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, valor != null ? valor : 1);
		q.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(4, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		q.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		
		
		return q.getResultList();
	}
	
	/**
	 * Metodo que consulta la data de las alertas de plazo de contestatcion
	 * @param idCaso
	 * @param metodoNombramiento
	 * @return
	 */
	public InfoBasicaAlertasDTO alertaAceptacionConciliador( Long idCaso , String metodoNombramiento ){
		InfoBasicaAlertasDTO infoAlertas;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT c.id_caso AS idCaso, ");
		nativeQuery.append(" c.nombre AS nombreCaso,  ");
		nativeQuery.append(" ce.nombre AS nombreCentro, ");
		nativeQuery.append(" pg.valor_numerico AS valorParametroNumerico, ");
		nativeQuery.append(" ce.id_centro AS idCentro ");
		nativeQuery.append(" FROM CASO c ");
		nativeQuery.append(" LEFT JOIN SEDE se ON se.id_sede = c.id_sede AND se.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN CENTRO ce ON ce.id_centro = se.id_centro  AND ce.estado_registro = ?1, ");
		nativeQuery.append(" PARAMETROS_GENERALES pg ");
		nativeQuery.append(" WHERE c.id_caso = ?2 ");
		nativeQuery.append(" AND c.estado_registro = ?1 ");
		nativeQuery.append(" AND pg.tipo  = ?3 ");
		nativeQuery.append(" AND pg.codigo = ( SELECT  CASE WHEN ?4 = ?5 THEN ?7 "); 
		nativeQuery.append(" WHEN ?4  = ?6  THEN ?8 END ) ");
		
		
		Query query = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, UtilDominios.PARAMETRO_GENERAL_PLAZO_ACEPTACION_CONCILIADOR);
		query.setParameter(4, metodoNombramiento);		
		query.setParameter(5, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(6, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);		
		query.setParameter(7, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		query.setParameter(8, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		try {
			infoAlertas = (InfoBasicaAlertasDTO) query.getSingleResult();
		} catch (NonUniqueResultException  | NoResultException e) {
			infoAlertas = null;
		}

		return infoAlertas;
	}
	
	/**
	 * Metodo que consulta los casos de convenio radicados en el dia por el
	 * apoderado.
	 * 
	 * @param tablaEncabezado:
	 *            Estilos encabezado de la tabla de casos.
	 * @param tablaCierre:
	 *            Cierre de elmentos html de la tabla de casos.
	 * @param tablaTextoEncabezado:
	 *            Texto del encabezado de la tabla.
	 * @param fechaVigencia:
	 *            fecha de consulta (El dia en que se ejecuta la alerta).
	 * @return List<InfoBasicaAlertasDTO>: Lista de datos de la alerta.
	 */
	public List<InfoBasicaAlertasDTO> consultarCasosConvenioRadicadosPorApoderadoAlerta(String tablaEncabezado,
			String tablaCierre, String tablaTextoEncabezado, Date fechaVigencia) {

		Date fechaFinDia = null;
		Date fechaInicioDia = null;
		if (fechaVigencia != null) {
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaVigencia);
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaVigencia);
		}
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max), ");
		nativeQuery.append(" @TablaCierre varchar(max), ");
		nativeQuery.append(" @TablaTextoEncabezado varchar(max) ");
		nativeQuery.append(" Set @TablaCierre = ?1 ");
		nativeQuery.append(" Set @TablaEncabezado = ?2 ");
		nativeQuery.append(" Set @TablaTextoEncabezado = ?3 ");

		nativeQuery.append(" (SELECT * FROM (SELECT DISTINCT p.id_persona AS idPersona, ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombrePersona, ");
		nativeQuery.append(" @TablaEncabezado + @TablaTextoEncabezado + (SELECT ");
		nativeQuery.append(" DISTINCT c.id_caso AS td, ");
		nativeQuery.append(" ISNULL(c.nombre, '') AS td ");

		nativeQuery.append(" FROM CASO c ");

		nativeQuery.append(" INNER JOIN EVENTO e ");
		nativeQuery.append(" ON e.id_caso = c.id_caso ");
		nativeQuery.append(" AND e.tipo_evento = ?4 ");
		nativeQuery.append(" AND e.estado_registro = ?7 ");
		
		if (fechaVigencia != null) {
			nativeQuery.append(" AND e.fecha_evento BETWEEN ?5 AND ?6 ");
		}

		nativeQuery.append(" INNER JOIN USUARIO u ");
		nativeQuery.append(" ON u.usuario_login = e.id_usuario_modificacion ");
		nativeQuery.append(" AND u.estado = ?7 ");

		nativeQuery.append(" INNER JOIN RELACIONADO_CONVENIO rc ");
		nativeQuery.append(" ON rc.id_persona = u.id_persona ");
		nativeQuery.append(" AND rc.estado_registro = ?7 ");

		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rc.id_rol ");
		nativeQuery.append(" AND r.nombre IN (?8, ?9) ");
		nativeQuery.append(" AND r.estado_registro = ?7 ");

		nativeQuery.append(" WHERE c.estado_registro = ?7 ");
		nativeQuery.append(" AND p.id_persona = rc.id_persona ");
		nativeQuery.append(" AND p.estado_registro = ?7 ");
		nativeQuery.append(" AND c.id_servicio = ?10 ");

		nativeQuery.append(" FOR xml RAW ('tr'), ELEMENTS) + @TablaCierre AS tabla  ");
		nativeQuery.append(" FROM PERSONA p ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp ");
		nativeQuery.append(" ON rp.id_persona = p.id_persona ");
		nativeQuery.append(" AND rp.estado_registro = ?7 ");
		nativeQuery.append(" AND (rp.fecha_fin_vigencia IS NULL OR rp.fecha_fin_vigencia > GETDATE()) ");
		nativeQuery.append(" AND rp.fecha_inicio_vigencia IS NOT NULL ");
		nativeQuery.append(" AND rp.fecha_inicio_vigencia < GETDATE() ");
		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rp.id_rol ");
		nativeQuery.append(" AND r.nombre IN (?8, ?9) ");
		nativeQuery.append(" AND r.estado_registro = ?7 ");
		nativeQuery.append(" INNER JOIN RELACIONADO_CONVENIO rc ");
		nativeQuery.append(" ON rc.id_persona = p.id_persona ");
		nativeQuery.append(" AND rc.id_rol = r.id_rol ");
		nativeQuery.append(" AND rc.estado_registro = ?7) AS t ");
		nativeQuery.append(" WHERE t.tabla IS NOT NULL)  ");

		Query query = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, tablaCierre);
		query.setParameter(2, tablaEncabezado);
		query.setParameter(3, tablaTextoEncabezado);

		query.setParameter(4, UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO);
		if (fechaVigencia != null) {
			query.setParameter(5, fechaInicioDia);
			query.setParameter(6, fechaFinDia);
		}

		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		query.setParameter(8, UtilDominios.ROL_PERSONA_APODERADO_CONVENIO);
		query.setParameter(9, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		query.setParameter(10, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);

		return query.getResultList();

	}

	/**
	 * Metodo que permite consultar los casos de convenios radicados en el dia
	 * por los apoderados.
	 * 
	 * @param tablaEncabezado:
	 *            Estilos encabezado de la tabla de casos.
	 * @param tablaCierre:
	 *            Cierre de elmentos html de la tabla de casos.
	 * @param tablaTextoEncabezado:
	 *            Texto del encabezado de la tabla.
	 * @param fechaVigencia:
	 *            fecha de consulta (El dia en que se ejecuta la alerta).
	 * @return List<InfoBasicaAlertasDTO>: Lista de datos de la alerta.
	 */
	public List<InfoBasicaAlertasDTO> consultarCasosConvenioRadicadosPorApoderadosAlerta(String tablaEncabezado,
			String tablaCierre, String tablaTextoEncabezado, Date fechaVigencia) {
		Date fechaFinDia = null;
		Date fechaInicioDia = null;
		if (fechaVigencia != null) {
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaVigencia);
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaVigencia);
		}
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max), ");
		nativeQuery.append(" @TablaCierre varchar(max), ");
		nativeQuery.append(" @TablaTextoEncabezado varchar(max) ");
		nativeQuery.append(" Set @TablaCierre = ?1 ");
		nativeQuery.append(" Set @TablaEncabezado = ?2 ");
		nativeQuery.append(" Set @TablaTextoEncabezado = ?3 ");

		nativeQuery.append(" (SELECT * FROM (SELECT DISTINCT con.id_convenio AS idConvenio, ");
		nativeQuery.append(" con.nombre AS nombreConvenio, @TablaEncabezado + @TablaTextoEncabezado + ");
		nativeQuery.append(" (SELECT DISTINCT c.id_caso AS td, ");
		nativeQuery.append(" ISNULL(c.nombre, '') AS td, ");
		nativeQuery.append(
				" CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS td ");

		nativeQuery.append(" FROM CASO c ");

		nativeQuery.append(" INNER JOIN EVENTO e ");
		nativeQuery.append(" ON e.id_caso = c.id_caso ");
		nativeQuery.append(" AND e.tipo_evento = ?4 ");
		nativeQuery.append(" AND e.estado_registro = ?7 ");
		if (fechaVigencia != null) {
			nativeQuery.append(" AND e.fecha_evento BETWEEN ?5 AND ?6 ");
		}

		nativeQuery.append(" INNER JOIN USUARIO u ");
		nativeQuery.append(" ON u.usuario_login = e.id_usuario_modificacion ");
		nativeQuery.append(" AND u.estado = ?7 ");

		nativeQuery.append(" INNER JOIN PERSONA p ");
		nativeQuery.append(" ON p.id_persona = u.id_persona ");
		nativeQuery.append(" AND p.estado_registro = ?7 ");

		nativeQuery.append(" INNER JOIN RELACIONADO_CONVENIO rc ");
		nativeQuery.append(" ON p.id_persona = rc.id_persona ");
		nativeQuery.append(" AND rc.estado_registro = ?7 ");

		nativeQuery.append(" INNER JOIN ROL r ");
		nativeQuery.append(" ON r.id_rol = rc.id_rol ");
		nativeQuery.append(" AND r.nombre IN (?8, ?9) ");
		nativeQuery.append(" AND r.estado_registro = ?7 ");

		nativeQuery.append(" WHERE c.estado_registro = ?7 ");
		nativeQuery.append(" AND con.id_convenio = rc.id_convenio ");
		nativeQuery.append(" AND con.estado_registro = ?7 ");
		nativeQuery.append(" AND con.id_convenio = c.id_convenio ");
		nativeQuery.append(" AND c.id_servicio = ?10 ");

		nativeQuery.append(" FOR xml RAW ('tr'), ELEMENTS) + @TablaCierre AS tabla  ");
		nativeQuery.append(" FROM CONVENIO con) AS t  ");
		nativeQuery.append(" WHERE t.tabla IS NOT NULL)  ");

		Query query = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, tablaCierre);
		query.setParameter(2, tablaEncabezado);
		query.setParameter(3, tablaTextoEncabezado);
		query.setParameter(4, UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO);

		if (fechaVigencia != null) {
			query.setParameter(5, fechaInicioDia);
			query.setParameter(6, fechaFinDia);
		}

		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.ROL_PERSONA_APODERADO_CONVENIO);
		query.setParameter(9, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		query.setParameter(10, UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO);

		return query.getResultList();
	}
	
	
	/**
	 * Método que consula los casos que estan entre 80 y 90 dias desde su fecha de radicacion
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> consultarCasosSinCerrarVencimientoLegalAlerta() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as nombreCaso, c.fecha_radicacion as fechaRadicacionCaso, ce.id_centro as idCentro, ");
		nativeQuery.append(" (select DATEDIFF (day,c.fecha_radicacion, getdate()) - ISNULL((SELECT top 1 DATEDIFF(day, e.fecha_evento, ");
		nativeQuery.append(" (select top 1 ev.fecha_evento from evento ev where estado_registro = 'ACT' AND tipo_evento = ?1 AND EV.id_caso = c.id_caso)) ");
		nativeQuery.append(" from evento e where tipo_evento= ?2 and e.id_caso = c.id_caso and e.estado_registro= ?3 order by e.fecha_evento asc), 0)) as diasTranscurridosCaso, ");
		nativeQuery.append(" ce.nombre as nombreCentro ");
		nativeQuery.append(" from caso c , centro ce, servicio s ");
		nativeQuery.append(" where ");
		nativeQuery.append(" ce.id_centro = (case when c.id_servicio = ?4 then (select co.id_centro from convenio co where co.id_convenio = c.id_convenio and co.estado_registro = ?3) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?3) end) ");
		nativeQuery.append(" and not exists (select * from solicitud_prorroga sp where sp.id_caso = c.id_caso) ");
		nativeQuery.append(" and DATEDIFF (day,c.fecha_radicacion, getdate()) between 80 and 90 ");
		nativeQuery.append(" and c.estado_registro = ?3 ");
		nativeQuery.append(" and c.id_servicio = s.id_servicio ");
		nativeQuery.append(" and s.tipo = ?5 ");
		nativeQuery.append(" and c.estado_caso not in ( ?6 , ?7, ?8 ) ");
		nativeQuery.append(" and not exists (select * from AUDIENCIA a where a.id_caso = c.id_caso and a.hora_inicio >= GETDATE() and estado = ?9) ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		q.setParameter(1, UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
		q.setParameter(2, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		q.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(6, UtilDominios.ESTADO_CASO_REGISTRADO);
		q.setParameter(7, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD);
		q.setParameter(8, UtilDominios.ESTADO_CASO_CANCELADO);
		q.setParameter(9, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		
		return q.getResultList();
	}
	
	
	/**
	 * Método que consula los casos que tienen mas de 90 dias desde su fecha de radicacion
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> consultarCasosSinCerrar90DiasVencimientoLegalAlerta() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select a.*, DATEDIFF ( day,fechaRadicacionCaso, getdate()) - ISNULL(DATEDIFF(day, e.fecha_evento, ev.fecha_evento),0) as diasTranscurridosCaso ");
		nativeQuery.append(" from ( ");
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as NombreCaso, c.fecha_radicacion as fechaRadicacionCaso, ce.nombre as nombreCentro, ce.id_centro as idCentro, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) AS nombrePersona, ");
		nativeQuery.append(" se.nombre as nombreSede ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" left join solicitud_prorroga sp on sp.id_caso = c.id_caso ");
		nativeQuery.append(" left join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso AND rpc.estado_registro=?3 and rpc.estado IN (?5, ?6) ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol and r.nombre = ?7 ");
		nativeQuery.append(" inner join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" LEFT JOIN SEDE se ON se.id_sede = c.id_sede, ");
		nativeQuery.append(" centro ce ");
		nativeQuery.append(" where ");
		nativeQuery.append(" c.estado_caso not in (?8, ?9) ");
		nativeQuery.append(" and sp.id_caso is null ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?4 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?3) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?3 ) end ) ");
		nativeQuery.append(" AND C.ESTADO_REGISTRO=?3 ");
		nativeQuery.append(" ) a ");
		nativeQuery.append(" outer apply (select fecha_evento from evento  where idCaso = id_caso and estado_registro=?3 AND tipo_evento=?1 group by fecha_evento having max(fecha_evento) = fecha_evento) ev ");
		nativeQuery.append(" outer apply (select fecha_evento from evento  where idCaso = id_caso and estado_registro=?3 AND tipo_evento=?2 group by fecha_evento having min(fecha_evento) = fecha_evento) e ");
		nativeQuery.append(" where DATEDIFF ( day,fechaRadicacionCaso, getdate()) - ISNULL(DATEDIFF(day, e.fecha_evento, ev.fecha_evento),0) > (select valor_numerico from parametros_generales where codigo = ?10) ");
		nativeQuery.append(" order by idCentro, fechaRadicacionCaso ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		q.setParameter(1, UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
		q.setParameter(2, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		q.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		q.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(7, UtilDominios.ROL_PERSONA_CONCILIADOR);
		q.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);
		q.setParameter(9, UtilDominios.ESTADO_CASO_CERRADO);
		q.setParameter(10, UtilParamGenerales.CODIGO_CIERRE_CASO_NUMERO_DIAS);
		
	
		return q.getResultList();
	}
	
	public List<InfoBasicaAlertasDTO> consultarCasosSinCerrar90DiasVencimientoLegalAlertaMed() {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select a.*, DATEDIFF ( day,fechaRadicacionCaso, getdate()) - ISNULL(DATEDIFF(day, e.fecha_evento, ev.fecha_evento),0) as diasTranscurridosCaso ");
		nativeQuery.append(" from ( ");
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as NombreCaso, c.fecha_radicacion as fechaRadicacionCaso, ce.nombre as nombreCentro, ce.id_centro as idCentro, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) AS nombrePersona, ");
		nativeQuery.append(" se.nombre as nombreSede ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" left join solicitud_prorroga sp on sp.id_caso = c.id_caso ");
		nativeQuery.append(" left join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso AND rpc.estado_registro=?3 and rpc.estado IN (?5, ?6) ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO pss on r.id_rol = pss.id_rol and c.id_servicio = pss.id_servicio ");
		nativeQuery.append(" inner join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" LEFT JOIN SEDE se ON se.id_sede = c.id_sede, ");
		nativeQuery.append(" centro ce ");
		nativeQuery.append(" where ");
		nativeQuery.append(" c.id_servicio = ?11 ");
		nativeQuery.append(" and c.estado_caso not in (?8, ?9) ");
		nativeQuery.append(" and sp.id_caso is null ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?4 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?3) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?3 ) end ) ");
		nativeQuery.append(" AND C.ESTADO_REGISTRO=?3 ");
		nativeQuery.append(" ) a ");
		nativeQuery.append(" outer apply (select fecha_evento from evento  where idCaso = id_caso and estado_registro=?3 AND tipo_evento=?1 group by fecha_evento having max(fecha_evento) = fecha_evento) ev ");
		nativeQuery.append(" outer apply (select fecha_evento from evento  where idCaso = id_caso and estado_registro=?3 AND tipo_evento=?2 group by fecha_evento having min(fecha_evento) = fecha_evento) e ");
		nativeQuery.append(" where DATEDIFF ( day,fechaRadicacionCaso, getdate()) - ISNULL(DATEDIFF(day, e.fecha_evento, ev.fecha_evento),0) > (select valor from PARAMETRO_DE_SERVICIO where tipo_parametro = ?10 and id_servicio= ?11) ");
		nativeQuery.append(" order by idCentro, fechaRadicacionCaso ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		q.setParameter(1, UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
		q.setParameter(2, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		q.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		q.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);
		q.setParameter(9, UtilDominios.ESTADO_CASO_CERRADO);
		q.setParameter(10, UtilParamServicio.TIPO_PARAMETRO_CIERRE_CASO);
		q.setParameter(11, UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION);
		
	
		return q.getResultList();
	}
	
	public List<InfoBasicaAlertasDTO> consultarCasosSinCerrarVencimientoLegalAlertaInsolvencia(String dias) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select a.*, DATEDIFF ( day,fechaRadicacionCaso, getdate()) as diasTranscurridosCaso ");
		nativeQuery.append(" from ( ");
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as NombreCaso, c.fecha_radicacion as fechaRadicacionCaso, ce.nombre as nombreCentro, ce.id_centro as idCentro, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) AS nombrePersona, ");
		nativeQuery.append(" se.nombre as nombreSede ");
		nativeQuery.append(" from caso c ");
		nativeQuery.append(" left join solicitud_prorroga sp on sp.id_caso = c.id_caso ");
		nativeQuery.append(" left join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso AND rpc.estado_registro=?3 and rpc.estado IN (?5, ?6) ");
		nativeQuery.append(" inner join rol r on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" inner join PARAMETRO_SERVICIO_SORTEO pss on r.id_rol = pss.id_rol and c.id_servicio = pss.id_servicio ");
		nativeQuery.append(" inner join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" LEFT JOIN SEDE se ON se.id_sede = c.id_sede, ");
		nativeQuery.append(" centro ce ");
		nativeQuery.append(" where ");
		nativeQuery.append(" c.id_servicio = ?11 ");
		nativeQuery.append(" and c.estado_caso not in (?8, ?9, ?10, ?12) ");
		nativeQuery.append(" and sp.id_caso is null ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?4 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?3) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?3 ) end ) ");
		nativeQuery.append(" AND C.ESTADO_REGISTRO=?3 ");
		nativeQuery.append(" ) a ");
		nativeQuery.append(" where DATEDIFF ( day,fechaRadicacionCaso, getdate()) in ( ");
		nativeQuery.append(dias);
		nativeQuery.append(")");
		nativeQuery.append(" order by idCentro, fechaRadicacionCaso ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		q.setParameter(1, UtilDominios.TIPO_EVENTO_CASO_REABIERTO);
		q.setParameter(2, UtilDominios.TIPO_EVENTO_CASO_REGISTRADO);
		q.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		q.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		q.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(8, UtilDominios.ESTADO_CASO_INADMITIDO);
		q.setParameter(9, UtilDominios.ESTADO_CASO_DESISTIMIENTO);
		q.setParameter(10, UtilDominios.ESTADO_CASO_DEVOLUCION);
		q.setParameter(12, UtilDominios.ESTADO_CASO_RECHAZADO);
		q.setParameter(11, UtilConstantes.ID_SERVICIO_INSOLVENCIA);
		
	
		return q.getResultList();
	}
	
	/**
	 * Alerta A9 proximos sorteos
	 * Método que consula los proximos sorteos
	 * @return
	 */
	public String alertaProximosSorteos() {
		String proximosSorteos;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DECLARE @TablaEncabezado varchar(max);  ");
		nativeQuery.append(" Set @TablaEncabezado = CONCAT( ISNULL ( (SELECT descripcion FROM DOMINIO WHERE dominio = ?5 AND codigo = ?6 ), ");
		nativeQuery.append(" '<html><body><table> '), ");
		nativeQuery.append(" '<tr><td><b>Código del caso</b></td> <td><b>Nombre caso</b></td><td><b>Tipo de caso</b></td><td><b>Especialidad</b></td><td><b>Tipo de cuantía</b></td><td><b>Conformación previa de las listas por las partes o en virtud del pacto arbitral</b></td><td><b>Árbitros sorteables</b></td></tr>' ); ");
		nativeQuery.append(" SELECT @TablaEncabezado + + REPLACE(REPLACE(CONVERT(NVARCHAR(MAX), ");
		nativeQuery.append(" (SELECT ca.id_caso AS td, ");
		nativeQuery.append(" ca.nombre AS td, ");
		nativeQuery.append(" se.nombre as td, ");
		nativeQuery.append(" ma.nombre as td, ");
		nativeQuery.append(" dc.nombre as td, ");
		nativeQuery.append(" case ca.preseleccion when 1 then 'Si' else 'No' end as td, ");
		nativeQuery.append(" (select valor_texto from parametros_generales where codigo = 'URL_PROXSOR') as td ");
		nativeQuery.append(" FROM AUDIENCIA au  ");
		nativeQuery.append(" INNER JOIN CASO ca ON ca.estado_registro = ?1 AND ca.id_caso = au.id_caso ");
		nativeQuery.append(" INNER JOIN SERVICIO se ON se.id_servicio = ca.id_servicio AND se.estado_registro = ?1 AND se.tipo = ?4 ");
		nativeQuery.append(" INNER JOIN SEDE sed ON sed.id_sede  = ca.id_sede AND sed.estado_registro = ?1 ");
		nativeQuery.append(" INNER JOIN MATERIA ma on ca.id_materia = ma.id_materia ");
		nativeQuery.append(" INNER JOIN DOMINIO dc on ca.tipo_cuantia = dc.codigo and dc.dominio in (?7, ?8) ");
		nativeQuery.append(" WHERE au.tipo_audiencia = ?2 ");
		nativeQuery.append(" AND au.estado_registro = ?1 ");
		nativeQuery.append(" AND au.estado = ?3 ");
		nativeQuery.append(" AND  CAST (au.hora_inicio AS DATE ) = CAST( DATEADD (dd,1,GETDATE()) AS DATE) "); 
		nativeQuery.append(" FOR XML RAW('tr'), ELEMENTS, TYPE) ),'&lt;','<'),'&gt;','>') + '</table></body></html>' ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(5, UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		query.setParameter(6, UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		query.setParameter(7, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(8, UtilDominios.DOMINIO_TIPO_CUANTIA_ARBITRAJE);
		try {
			proximosSorteos = (String) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			proximosSorteos = null;
		}		
		
		return proximosSorteos;
		
	}
	
	
	/**
	 * Alerta C24 Cumplimiento de Porcentaje de Casos para seguimiento 
	 * @return Boolean (Si es true esta cumpliendo con el 10%)
	 */
	public Boolean alertaCumplimientoPorcentajeSeguimientoCasos() {
		Boolean cumplimientoPorcentaje;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" Declare @calculoPorcentajeCasos decimal(19,2),  ");
		nativeQuery.append(" @numeroCasosTotal decimal(19,2), ");
		nativeQuery.append(" @numeroCasos decimal (19,2) ");
		nativeQuery.append(" set @numeroCasosTotal = (SELECT count(*) FROM CASO ca  ");
		nativeQuery.append(" INNER JOIN SERVICIO se ");
		nativeQuery.append(" ON se.id_servicio = ca.id_servicio ");
		nativeQuery.append(" AND se.tipo = ?1 AND se.estado_registro = ?2 ");
		nativeQuery.append(" CROSS APPLY (select top 1 aud.* FROM AUDIENCIA aud ");
		nativeQuery.append(" where aud.id_caso = ca.id_caso ");
		nativeQuery.append(" AND aud.estado_registro = ?2 order by aud.hora_inicio desc) AS ultimaAudienciaCaso ");
		nativeQuery.append(" INNER JOIN RESULTADO_AUDIENCIA rau ");
		nativeQuery.append(" ON rau.id_audiencia = ultimaAudienciaCaso.id_audiencia ");
		nativeQuery.append(" AND rau.estado_registro = ?2 ");
		nativeQuery.append(" cross apply (select top 1 ob.* from OBLIGACION ob ");
		nativeQuery.append(" where ob.id_resultado_audiencia = rau.id_resultado_audiencia ");
		nativeQuery.append(" AND MONTH(ob.fecha_compromiso) = MONTH(GETDATE()) ");
		nativeQuery.append(" AND YEAR(ob.fecha_compromiso) = YEAR(GETDATE()) ");
		nativeQuery.append(" AND ob.estado_registro = ?2 order by ob.fecha_compromiso asc) as obligacionCaso ");
		nativeQuery.append(" WHERE ca.resultado = ?3 AND ca.estado_registro = ?2 )  ");
		nativeQuery.append(" Set @calculoPorcentajeCasos=(@numeroCasosTotal*10)/100 ");
		nativeQuery.append(" Set @numeroCasos = (SELECT count(*) ");
		nativeQuery.append(" FROM  CASO c ");
		nativeQuery.append(" INNER JOIN SERVICIO s ");
		nativeQuery.append(" ON s.id_servicio = c.id_servicio ");
		nativeQuery.append(" AND s.tipo = ?1 ");
		nativeQuery.append(" AND s.estado_registro = ?2 ");
		nativeQuery.append(" INNER JOIN LLAMADA ll ");
		nativeQuery.append(" ON ll.id_caso = c.id_caso ");
		nativeQuery.append(" AND ll.tipo_llamada = ?4 ");
		nativeQuery.append(" AND MONTH(ll.fecha) = MONTH(GETDATE()) ");
		nativeQuery.append(" AND YEAR(ll.fecha) = YEAR(GETDATE()) ");
		nativeQuery.append(" AND ll.estado_registro = ?2 ");
		nativeQuery.append(" cross apply (select top 1 a.* from AUDIENCIA a ");
		nativeQuery.append(" where a.id_caso = c.id_caso ");
		nativeQuery.append(" AND a.estado_registro = ?2 order by hora_inicio desc) as ultimaAudiencia ");
		nativeQuery.append(" INNER JOIN RESULTADO_AUDIENCIA ra ");
		nativeQuery.append(" ON ra.id_audiencia = ultimaAudiencia.id_audiencia ");
		nativeQuery.append(" AND ra.estado_registro = ?2 ");
		nativeQuery.append(" cross apply (select top 1 o.* from OBLIGACION o ");
		nativeQuery.append(" where o.id_resultado_audiencia = ra.id_resultado_audiencia ");
		nativeQuery.append(" AND MONTH(o.fecha_compromiso) = MONTH(GETDATE()) ");
		nativeQuery.append(" AND YEAR(o.fecha_compromiso) = YEAR(GETDATE()) ");
		nativeQuery.append(" AND o.estado_registro = ?2 order by o.fecha_compromiso asc) as obligacion ");
		nativeQuery.append(" WHERE c.resultado = ?3 ");
		nativeQuery.append(" AND c.estado_registro = ?2 ) ");
		nativeQuery.append(" (select (case when @numeroCasos < @calculoPorcentajeCasos	 ");
		nativeQuery.append(" then 'false' ");
		nativeQuery.append(" else 'true' end) as cumplimientoPorcentajeCasos) ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), String.class);
		
		query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.RESULTADO_CASO_CONCILIACION_ACUERDO);
		query.setParameter(4, UtilDominios.LLAMADA_SEGUIMIENTO_CASO);
		
		try {
			String cumplimiento = (String) query.getSingleResult();
			if(cumplimiento.equals("false")){
				cumplimientoPorcentaje=false;
			}else{
				cumplimientoPorcentaje=true;
			}
		} catch (NonUniqueResultException | NoResultException e) {
			cumplimientoPorcentaje = null;
		}		
		
		return cumplimientoPorcentaje;
		
	}
	
	
	/**
	 * Alerta C24 Cumplimiento de Porcentaje de Casos para seguimiento 
	 * @return Boolean (Si es true esta cumpliendo con el 10%)
	 */
	public List<InfoBasicaAlertasDTO> alertaCasosVencimientoTerminos(String tipoPeriodicidad, Long dias) {
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" with consulta as(  ");
		nativeQuery.append(" SELECT c.id_caso as idCaso, c.nombre as nombreCaso, c.fecha_radicacion as fechaRadicacion,  ");
		nativeQuery.append(" (SELECT top 1 aud.hora_inicio from AUDIENCIA aud where aud.id_caso=c.id_caso and aud.estado_registro=?1 and aud.estado=?2 and aud.tipo_audiencia=?3 ");
		nativeQuery.append(" order by aud.hora_inicio desc) as fechaInstalacion, ");
		nativeQuery.append(" SUM(CASE WHEN s.fecha_final IS NULL THEN  0 ");
		nativeQuery.append(" ELSE dbo.diasHabilesEntreDosFechas(s.fecha_inicial, s.fecha_final) END) AS diasSuspension, ");
		nativeQuery.append(" fc.fecha as fechaInicioConteo, ");
		nativeQuery.append(" DATEADD(MONTH, (SELECT CASE WHEN ps.valor >= 30 THEN ((ps.valor + 30 - 1) / 30) ELSE 1 END FROM PARAMETRO_DE_SERVICIO ps WHERE ps.estado_registro = ?1 AND ps.nombre = ?13 AND ps.tipo_parametro = ?4 AND ps.id_servicio = c.id_servicio),fc.fecha) AS fechaInicioMasMeses, ");
		nativeQuery.append(" dbo.SumarDiasHabiles(DATEADD(MONTH, (SELECT CASE WHEN ps.valor >= 30 THEN ((ps.valor + 30 - 1) / 30) ELSE 1 END FROM PARAMETRO_DE_SERVICIO ps WHERE ps.estado_registro = ?1 AND ps.nombre = ?13 AND ps.tipo_parametro = ?4 AND ps.id_servicio = c.id_servicio),fc.fecha), ");
		nativeQuery.append(" SUM(CASE WHEN s.fecha_final IS NULL THEN  0 ");
		nativeQuery.append(" ELSE dbo.diasHabilesEntreDosFechas(s.fecha_inicial, s.fecha_final) END)) as fechaLimiteCierreCaso ");
		nativeQuery.append(" from CASO c ");
		nativeQuery.append(" LEFT JOIN SUSPENSION S ON S.id_caso=C.id_caso ");
		nativeQuery.append(" AND s.estado_registro=?1 ");
		nativeQuery.append(" AND s.tipo in (?7,?8,?9) ");
		nativeQuery.append(" INNER JOIN FECHA_CASO fc on fc.id_caso=c.id_caso ");
		nativeQuery.append(" INNER JOIN SERVICIO sv on sv.id_servicio=c.id_servicio ");
		nativeQuery.append(" where c.etapa=?5 ");
		nativeQuery.append(" and fc.tipo_fecha=?6 ");
		nativeQuery.append(" and c.estado_registro=?1 ");
		nativeQuery.append(" and fc.estado_registro=?1 ");
		nativeQuery.append(" AND c.estado_caso not in (?10,?11) ");
		nativeQuery.append(" AND sv.tipo=?12 ");		
		nativeQuery.append(" group by c.id_caso, c.nombre, fc.fecha, c.fecha_radicacion) ");
		nativeQuery.append(" select consulta.idCaso as idCaso, consulta.nombreCaso as nombreCaso,  ");
		nativeQuery.append(" consulta.fechaRadicacion as fechaRadicacionCaso, consulta.fechaInstalacion as fechaAudiencia ");
		nativeQuery.append(" from consulta ");
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){			
			nativeQuery.append(" where dbo.diasHabilesEntreDosFechas(getdate(),consulta.fechaLimiteCierreCaso)=?14 ");			
		}else{	
			nativeQuery.append(" where DATEDIFF(dd,getdate(),consulta.fechaLimiteCierreCaso)=?14 ");
		}	
				
		Query query = em.createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(3, UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION);
		query.setParameter(4, UtilDominios.DIAS_LIMITE_PARA_CIERRE_DE_CASO);
		query.setParameter(5, UtilDominios.ETAPA_CASO_ARBITRAL);
		query.setParameter(6, UtilDominios.ESTADO_TIPO_FECHA_CASO_INICIO_CONTEO_TERMINOS);
		query.setParameter(7, UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		query.setParameter(8, UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		query.setParameter(9, UtilDominios.TIPO_SUSPENSION_PRORROGA);
		query.setParameter(10, UtilDominios.ESTADO_CASO_CANCELADO);
		query.setParameter(11, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(12, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(13, UtilConstantes.DIAS_LIMITE_PARA_CIERRE_DE_CASO);
		query.setParameter(14, dias);
		
		return query.getResultList();
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Caso validarNombramientoArbitros(Long idCaso) {
		
		Caso caso = null;
	
		try{
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select * from caso c ");
		nativeQuery.append(" where c.cant_funcionarios_principales = (select count(*) from ROL_PERSONA_CASO rpc ");
		nativeQuery.append(" where rpc.estado_registro=?1 and rpc.estado = ?2 and rpc.tipo_nombramiento=?3  ");
		nativeQuery.append(" AND rpc.id_caso=c.id_caso) ");
		nativeQuery.append(" and c.estado_registro=?1 ");
		nativeQuery.append(" and c.id_caso = ?4 ");
		Query query = em.createNativeQuery(nativeQuery.toString(), Caso.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(4, idCaso);
		
		caso = (Caso) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			caso = null;
		}
		
		return caso;
	}
	
	public Caso obtenerDatosBasicosCaso(Long idCaso) {
		Caso caso;
		StringBuilder sql = new StringBuilder();
		sql.append(" select id_caso, id_servicio, id_convenio, fecha_radicacion from caso (nolock) where id_caso = ?1 and estado_registro = ?2 ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), Caso.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		try {
			caso = (Caso) query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			caso = null;
		}
		return caso;
	}
	
	public List<Caso> obtenerCasosActivosArbitro(Long idPersona, Long idMateria){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT c.id_caso, ");
		nativeQuery.append(" c.id_servicio, ");
		nativeQuery.append(" c.tipo_cuantia, ");
		nativeQuery.append(" c.valor_pretensiones, ");
		nativeQuery.append(" c.id_materia, ");
		nativeQuery.append(" c.id_sede, ");				
		nativeQuery.append(" c.nombre, ");
		nativeQuery.append(" c.fecha_radicacion, ");				
		nativeQuery.append(" c.etapa ");
		nativeQuery.append("FROM CASO c WITH (NOLOCK)");
		nativeQuery.append(" INNER JOIN rol_persona_caso r WITH (NOLOCK) ON c.id_caso = r.id_caso ");
		nativeQuery.append(" INNER JOIN rol_persona rp (NOLOCK) ON r.id_rol = rp.id_rol AND r.id_persona = rp.id_persona");
		nativeQuery.append(" INNER JOIN ESTADO_PERSONA_ROL epr WITH (NOLOCK) ON r.id_persona = epr.id_persona AND r.id_rol = epr.id_rol ");
		nativeQuery.append(" INNER JOIN sorteo s WITH (NOLOCK) ON s.id_sorteo = r.id_sorteo ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" c.id_servicio in (?3, ?11) ");
		nativeQuery.append(" AND r.id_rol = ?4 ");
		nativeQuery.append(" AND r.tipo_nombramiento = ?5 ");
		nativeQuery.append(" AND c.estado_caso <> ?6 ");
		nativeQuery.append(" AND r.estado in (?7, ?8) ");
		nativeQuery.append(" AND r.motivo_inactivacion is null ");
		nativeQuery.append(" AND rp.id_lista is not null ");
		nativeQuery.append(" AND rp.fecha_fin_vigencia is null ");
		nativeQuery.append(" AND epr.id_motivo <> ?9 ");
		nativeQuery.append(" AND r.id_persona = ?1 ");
		nativeQuery.append(" AND c.id_materia = ?2 ");
		nativeQuery.append(" AND (s.preseleccion = 0 or (s.preseleccion = 1 and s.quien_preselecciona = ?10 )) ");
		nativeQuery.append(" AND c.id_caso not in (select acl.id_caso from ARBITRO_CASO_LIBERACION acl where acl.id_persona = r.id_persona) ");
		nativeQuery.append(" ORDER BY s.fecha_realizacion desc ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), Caso.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, idMateria);
		query.setParameter(3, UtilConstantes.ID_SERVICIO_ARBITRAJE);
		query.setParameter(4, UtilConstantes.ID_ROL_ARBITRO);
		query.setParameter(5, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(6, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(8, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(9, UtilDominios.ESTADO_ARBITROS_TEMPORAL_SORTEO);
		query.setParameter(10, UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA);
		query.setParameter(11, UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO);

		return query.getResultList();
	}
	
	public void eliminarDatosPreseleccion(Long idCaso){
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("update caso set tipo_preseleccion = null, quien_preselecciona = null ");
		nativeQuery.append("where id_caso= ?1");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idCaso);
		query.executeUpdate();
	}
	// protected region metodos adicionales end

}
