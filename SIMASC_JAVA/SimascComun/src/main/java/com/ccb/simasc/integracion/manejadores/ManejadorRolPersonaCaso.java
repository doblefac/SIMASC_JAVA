package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta sección sus modificaciones

import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaComienzoDelDia;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.ArbitrosDisponiblesSorteoDTO;
import com.ccb.simasc.transversal.dto.CasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.PartesSeguimientoDTO;
import com.ccb.simasc.transversal.dto.PendientePronunciamientoDTO;
import com.ccb.simasc.transversal.dto.PersonaCasoListDTO;
import com.ccb.simasc.transversal.dto.PersonaDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.cartas.LotesCartasDTO;
import com.ccb.simasc.transversal.dto.formularios.CasoAsignadoDTO;
import com.ccb.simasc.transversal.dto.formularios.LotesCartasFiltrosDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Evento;
import com.ccb.simasc.transversal.entidades.EventoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad RolPersonaCaso.
 * 
 * @author jsoto
 */
@Stateless
@LocalBean
public class ManejadorRolPersonaCaso extends ManejadorCrud<RolPersonaCaso, RolPersonaCasoPK> {

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	// Atributos
	private static final Logger logger = LogManager.getLogger(ManejadorCrud.class.getName());

	private static final String FECHA_INICIO = "fechaInicio";
	private static final String FECHA_FIN = "fechaFin";

	// protected region atributos end

	public ManejadorRolPersonaCaso() {
		super(RolPersonaCaso.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	/**
	 * Metodo encargado de consultar las personas asociadas al caso en
	 * especifico con servicio y materia particulares retornando un listado de
	 * personas
	 * 
	 * @param idMateria
	 * @param idServicio
	 * @return List<Persona>
	 */
	public List<Persona> consultarPersonasCasoServicioMateria(Long idCaso, Long idMateria, Long idServicio,
			String lista) {
		Query q = mp.getEntityManager().createNativeQuery("SELECT pe.* FROM caso ca "
				+ "INNER JOIN rol_persona_caso rpc ON ca.id_caso = rpc.id_caso "
				+ "INNER JOIN rol_persona rp ON rpc.id_persona = rp.id_persona "
				+ "INNER JOIN rol r ON rp.id_rol = r.id_rol "
				+ "INNER JOIN lista li ON rp.id_lista = li.id_lista "
				+ "INNER JOIN PERSONA pe ON rpc.id_persona = pe.id_persona " + "WHERE ca.id_materia = ?1 "
				+ "AND r.nombre = ?5 " + "AND ca.id_servicio = ?2 " + "AND ca.id_caso = ?3 " + "AND li.nombre = ?4",
				Persona.class);
		q.setParameter(1, idMateria);
		q.setParameter(2, idServicio);
		q.setParameter(3, idCaso);
		q.setParameter(4, lista);
		q.setParameter(5, UtilDominios.ROL_PERSONA_ARBITRO);
		return q.getResultList();
	}

	// /**
	// * Consulta que trae todas las entidades RolPersona de un Rol determinado
	// *
	// * @param rol
	// * @return
	// */
	// public List<RolPersona> consultarPersonaConMenosCasos(Rol rol) {
	// StringBuilder query = new StringBuilder("SELECT r FROM RolPersona r WHERE
	// r.rolPersonaPK.idRol = :");
	// query.append(Rol.ENTIDAD_ROL_PK)
	// .append(" AND r.persona.estadoRegistro")
	// List<RolPersona> rolPersonas = em
	// .createQuery("idRol", RolPersona.class)
	// .setParameter("idRol", rol.getIdRol()).getResultList();
	// return rolPersonas;
	// }

	/**
	 * Consulta que retorna todos los casos que tiene una persona dependiendo de
	 * su rol
	 * 
	 * @param rol
	 * @param persona
	 * @return
	 */
	public List<Caso> consultarPersonaCasosPorRol(Rol rol, Persona persona) {
		Query q = mp.getEntityManager().createNativeQuery("SELECT ca.* FROM persona pe "
				+ "INNER JOIN rol_persona_caso rpc ON pe.id_persona = rpc.id_persona "
				+ "INNER JOIN CASO ca ON rpc.id_caso = ca.id_caso " + "WHERE rpc.id_rol = ?1 "
				+ "AND rpc.id_persona = ?2", Caso.class);
		q.setParameter(1, rol.getIdRol());
		q.setParameter(2, persona.getIdPersona());
		return q.getResultList();
	}

	/**
	 * Consulta los casos a los que está asignado el arbitro que se pasa como
	 * parámetro. Los ordena por código.
	 * 
	 * @param idArbitro
	 *            id la persona con rol arbitro
	 * @return
	 */
	public List<RolPersonaCaso> consultarAsignacionesCasosArbitro(Long idArbitro) {

		String estadoRegistroRolPersonaCaso = RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO;
		String estadoRegistroCaso = Caso.ENTIDAD_CASO_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersonaCaso);
		jpqlQuery.append(" AND rpc.caso.estadoCaso<>:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_ESTADO_CASO);
		jpqlQuery.append(" AND rpc.caso.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroCaso);
		jpqlQuery.append(" ORDER BY rpc.caso.idCaso ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idArbitro);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(estadoRegistroRolPersonaCaso, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_CASO, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(estadoRegistroCaso, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Consulta la persona por el rol y el id del caso
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 */

	public List<Persona> consultarPersonasPorRolCaso(Long idCaso, String nombreRol) {

		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rpc.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();

	}

	/**
	 * Consulta la persona por el rol y el id del caso
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @return
	 */

	public List<RolPersonaCaso> consultarArbitrosPorRolCaso(Long idCaso, String nombreRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);

		return query.getResultList();

	}

	public RolPersonaCaso consultarPersonaPorRolCasoPrestador(Long idCaso, String nombreRol) {
		return consultarPersonaPorRolCasoPorEstado(idCaso, nombreRol, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
	}

	public RolPersonaCaso consultarPersonaPorRolCaso(Long idCaso, String nombreRol) {
		return consultarPersonaPorRolCasoPorEstado(idCaso, nombreRol, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
	}

	/**
	 * Utilizar cuando solo puede haber una persona con el rol en el caso
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estado
	 * @return
	 */
	private RolPersonaCaso consultarPersonaPorRolCasoPorEstado(Long idCaso, String nombreRol, String estado) {
		RolPersonaCaso resultado = new RolPersonaCaso();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
			jpqlQuery.append(" WHERE rpc.rol.nombre=:");
			jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
			jpqlQuery.append(" AND rpc.caso.idCaso=:");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.estadoRegistro=:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" AND rpc.estado=:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

			jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estado);
			resultado = (RolPersonaCaso) query.getSingleResult();
		} catch (NonUniqueResultException e) {
			String mensajeError = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR200.toString()));
			throw new SimascException(mensajeError);
		} catch (NoResultException e) {
			resultado = null;
		}
		return resultado;

	}

	/**
	 * Consulta las personas asignadas al caso cuyo rol permite preselección
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<RolPersonaCaso> consultarPersonasAsignadasCasoParaPreseleccion(Long idCaso) {

		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.preseleccion=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_PRESELECCION);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rpc.persona.estadoPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_PRESELECCION, true);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();

	}

	/**
	 * Guarda el rol de una persona en un caso
	 * 
	 * @param rolPersonaCaso
	 * @return
	 */
	public RolPersonaCaso crearRolPersonaCaso(RolPersonaCaso rolPersonaCaso) {
		RolPersonaCasoPK pk = new RolPersonaCasoPK(rolPersonaCaso.getPersona().getIdPersona(),
				rolPersonaCaso.getCaso().getIdCaso(), rolPersonaCaso.getRol().getIdRol());
		rolPersonaCaso.setRolPersonaCasoPK(pk);
		return (RolPersonaCaso) mp.updateObject(rolPersonaCaso);

	}

	@Override
	public void crear(RolPersonaCaso rolPersonaCaso) {
		RolPersonaCasoPK pk = (rolPersonaCaso.getRolPersonaCasoPK() == null
				|| (rolPersonaCaso.getRolPersonaCasoPK().getIdPersona() == null
						|| rolPersonaCaso.getRolPersonaCasoPK().getIdRol() == null
						|| rolPersonaCaso.getRolPersonaCasoPK().getIdCaso() == null))
								? new RolPersonaCasoPK(rolPersonaCaso.getPersona().getIdPersona(),
										rolPersonaCaso.getCaso().getIdCaso(), rolPersonaCaso.getRol().getIdRol())
								: rolPersonaCaso.getRolPersonaCasoPK();
		rolPersonaCaso.setRolPersonaCasoPK(pk);
		mp.create(rolPersonaCaso);

	}

	public Boolean validaPersonaExisteCaso(Long idPersona, Long idCaso, Long idRol) {
		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.idRol=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_PK);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" AND rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return !query.getResultList().isEmpty();
	}

	public Boolean validaRolPersonaExistioCaso(Long idPersona, Long idCaso, Long idRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.idRol=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_PK);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);

		return !query.getResultList().isEmpty();
	}

	public Boolean validaRolExisteCaso(Long idCaso, String idRol) {
		String estadoRegistroRolPersona = RolPersona.ENTIDAD_ROL_PERSONA_ESTADO_REGISTRO;
		String estadoRegistroPersona = Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO + "1";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroRolPersona);
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append(estadoRegistroPersona);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, idRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);

		query.setParameter(estadoRegistroRolPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(estadoRegistroPersona, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return !query.getResultList().isEmpty();
	}

	/**
	 * metodo que obtiene si la persona se encuentra en el caso activo
	 * @param idPersona
	 * @param idCaso
	 * @param rolesExclusion listado de roles que se excluira en la busqueda,
	 * 	es decir si la persona se encuentra activa con alguno de los roles no se tendra en cuenta en la consulta
	 * 	Si se envia una lista vacia se excluira el rol que se encuentre en la tabla persona_servicio_sorteo con el servicio del caso
	 * @return
	 */
	public boolean obtenerRolesPersonaCaso(Long idPersona, Long idCaso, List<String> rolesExclusion) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" , ParametroServicioSorteo pss ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.rol.estadoRegistro=:");
		jpqlQuery.append("estadoRol");
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append("estadoPersona");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append("estadoRegistro");
		if(rolesExclusion != null && !rolesExclusion.isEmpty())
			jpqlQuery.append(" AND rpc.rol.nombre not in :roles");
		if(rolesExclusion != null && rolesExclusion.isEmpty())
			jpqlQuery.append(" AND rpc.rol.idRol = pss.idRol and pss.idServicio = rpc.caso.idServicio ");
		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter("estadoRol", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoPersona", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(rolesExclusion != null && !rolesExclusion.isEmpty())
			query.setParameter("roles", rolesExclusion);
		return query.getResultList().isEmpty();
	}

	public List<RolPersonaCaso> obtenerPersonasAsociadasACaso(Long idCaso) {
		Query query = mp.getEntityManager().createQuery("SELECT rpc FROM RolPersonaCaso rpc WHERE rpc.caso.idCaso =: idCaso",
				RolPersonaCaso.class);

		query.setParameter("idCaso", idCaso);

		return query.getResultList();
	}

	/**
	 * Eliminar a un persona con un rol de un caso
	 * 
	 * @param RolPersonaCaso
	 *            rolPersonaCaso
	 */
	public void eliminarRolPersonaCaso(RolPersonaCaso rolPersonaCaso) {

		RolPersonaCasoPK rolPersonaCasoPK = new RolPersonaCasoPK(rolPersonaCaso.getPersona().getIdPersona(),
				rolPersonaCaso.getCaso().getIdCaso(), rolPersonaCaso.getRol().getIdRol());
		rolPersonaCaso.setRolPersonaCasoPK(rolPersonaCasoPK);
		mp.delete(rolPersonaCaso);

	}

	/**
	 * Consulta los arbitros designados en el momento, o que se encuentran a la
	 * espera de ser confirmados o rechazados del nombramiento
	 * 
	 * @param idCaso
	 * @param metodoNombramiento
	 * @param tipoDesignacion
	 * @return
	 */
	public int consultarNumeroArbitrosNombrados(Long idCaso, String metodoNombramiento, String tipoDesignacion,
			Long idterceroAuto) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT COUNT(rpc) nombrados ");
		jpqlQuery.append(" FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.metodoNombramiento =:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.tipoNombramiento =:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.estado in (" + "'" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "','"
				+ UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "','" + UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO
				+ "')");
		jpqlQuery.append(" AND rpc.rol.nombre = :").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		if (!UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento) && idterceroAuto != null) {
			jpqlQuery.append(" AND rpc.idPersonaTercero=:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO);
		}

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO, metodoNombramiento);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, tipoDesignacion);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, UtilDominios.ROL_PERSONA_ARBITRO);
		if (!UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(metodoNombramiento) && idterceroAuto != null) {
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO, idterceroAuto);
		}

		List<Object> resultadoQuery = query.getResultList();

		if (resultadoQuery == null || resultadoQuery.isEmpty())
			return 0;

		return Integer.valueOf(resultadoQuery.get(0).toString());
	}

	/**
	 * Consulta los arbitros designados en el momento, o que se encuentran a la
	 * espera de ser confirmados o rechazados del nombramiento
	 * 
	 * @param idCaso
	 * @param metodoNombramiento:_
	 *            principal o suplente
	 * @param tipoDesignacion
	 * @return
	 */
	public int consultarNumeroArbitrosNombrados(Long idCaso, String tipoDesignacion) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT COUNT(rpc) nombrados ");
		jpqlQuery.append(" FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.tipoNombramiento =:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.estado in (" + "'" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "','"
				+ UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "','" + UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO
				+ "')");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, tipoDesignacion);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<Object> resultadoQuery = query.getResultList();

		if (resultadoQuery == null || resultadoQuery.isEmpty())
			return 0;

		return Integer.valueOf(resultadoQuery.get(0).toString());
	}

	/**
	 * Consulta rolPersonaCaso por id
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @param estado
	 * @return RolPersonaCaso
	 */
	public RolPersonaCaso consultaRolPersonaId(Long idPersona, Long idCaso, String estado) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.persona.idPersona=:").append(Persona.ENTIDAD_PERSONA_PK);
		if (estado != null)
			jpqlQuery.append(" AND rpc.estado=:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" AND rpc.caso.idCaso=:").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if (estado != null)
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estado);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		RolPersonaCaso rolPersonaCaso;
		
		try {
			rolPersonaCaso = (RolPersonaCaso) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			rolPersonaCaso = null;
		}
		return rolPersonaCaso;		
	}

	/**
	 * @author cbenavides metodo que trae el rol persona caso asociado a un id
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public RolPersonaCaso consultarRolPersonaCaso(Long idPersona, Long idCaso, String rolPersona) {
		StringBuilder jpqlQuery = new StringBuilder();
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		try {
			jpqlQuery.append("SELECT rpc ");
			jpqlQuery.append(" FROM RolPersonaCaso rpc ");
			jpqlQuery.append(" WHERE rpc.caso.idCaso =: ").append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.persona.idPersona =: ").append(Persona.ENTIDAD_PERSONA_PK);
			jpqlQuery.append(" AND rpc.estadoRegistro =: ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

			if (rolPersona != null) {
				jpqlQuery.append(" AND rpc.rol.nombre =: ").append(Rol.ENTIDAD_ROL_NOMBRE);
			}
			Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			if (rolPersona != null) {
				query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, rolPersona);
			}
			rolPersonaCaso = (RolPersonaCaso) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR260.toString()));
		} catch (Exception e) {
			return null;
		}
		return rolPersonaCaso;
	}

	/**
	 * consulta el arbittro suplente a un arbitro principal
	 * 
	 * @param rolPersonaCasoArbitro
	 * @param eventoRolPersonaCaso
	 * @return
	 */
	public EventoRolPersonaCaso asignacionArbitroSuplente(RolPersonaCaso rolPersonaCasoArbitro,
			EventoRolPersonaCaso eventoRolPersonaCaso) {
		EventoRolPersonaCaso rolPersonaCasoSuplente;
		StringBuilder jpqlQuery = new StringBuilder();
		StringBuilder nombramiento = new StringBuilder(" ");

		if (rolPersonaCasoArbitro.getTipoSuplencia() == null) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR204.toString()));
		} else {
			if (rolPersonaCasoArbitro.getTipoSuplencia().equals(UtilDominios.TIPO_MANEJO_SUPLENCIA_PERSONAL)) {
				nombramiento.append(" AND rpc.ordenDeAsignacion = ");
				nombramiento.append(rolPersonaCasoArbitro.getOrdenDeAsignacion());
			}
		}

		try {
			Date fechaInicio = obtenerFechaComienzoDelDia(eventoRolPersonaCaso.getFechaDeAsignacion());
			Date fechaFinal = obtenerFechaFinDelDia(eventoRolPersonaCaso.getFechaDeAsignacion());
			jpqlQuery.append("SELECT erpc ");
			jpqlQuery.append(" FROM EventoRolPersonaCaso erpc JOIN erpc.rolPersonaCaso rpc");
			jpqlQuery.append(" WHERE ");
			jpqlQuery.append("erpc.fechaDeAsignacion>=:").append(FECHA_INICIO);
			jpqlQuery.append(" AND erpc.fechaDeAsignacion<=:").append(FECHA_FIN);
			jpqlQuery.append(" AND rpc.caso.idCaso =:").append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.tipoNombramiento =:")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
			jpqlQuery.append(" AND rpc.metodoNombramiento =:")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO);
			jpqlQuery.append(" AND rpc.tipoSuplencia =:")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_SUPLENCIA);
			jpqlQuery.append(" AND rpc.estado =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
			jpqlQuery.append(" AND rpc.estadoRegistro =: ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(" AND erpc.estadoRegistro =: ")
					.append(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO);
			jpqlQuery.append(nombramiento.toString());
			jpqlQuery.append(" ORDER BY rpc.ordenDeAsignacion");
			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(FECHA_INICIO, fechaInicio);
			query.setParameter(FECHA_FIN, fechaFinal);
			query.setParameter(Caso.ENTIDAD_CASO_PK, rolPersonaCasoArbitro.getCaso().getIdCaso());
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO,
					UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO,
					rolPersonaCasoArbitro.getMetodoNombramiento());
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_SUPLENCIA,
					rolPersonaCasoArbitro.getTipoSuplencia());
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
					UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(EventoRolPersonaCaso.ENTIDAD_EVENTO_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
			List<EventoRolPersonaCaso> resultado = query.getResultList();

			if (resultado != null && !resultado.isEmpty()) {
				rolPersonaCasoSuplente = resultado.get(0);
			} else {
				rolPersonaCasoSuplente = null;
			}

		} catch (Exception e) {
			rolPersonaCasoSuplente = null;
		}

		return rolPersonaCasoSuplente;

	}

	/**
	 * Consulta la asignación de un arbitro en el caso
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return rolPersonaCaso
	 * @throws SimascException
	 */
	public RolPersonaCaso consultarArbitroAsignadoAlCaso(Long idPersona, Long idCaso) {
		List<RolPersonaCaso> rolPersonaCasoList;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro = :");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.rol.nombre IN :rolesArbitros");
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("rolesArbitros", Arrays.asList(UtilConstantes.ROLES_ARBITROS));

		rolPersonaCasoList = (List<RolPersonaCaso>) query.getResultList();
		if (rolPersonaCasoList.isEmpty()) {
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR052.toString()),
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
			throw new SimascException(mensajeError);
		}
		return rolPersonaCasoList.get(0);
	}

	/**
	 * Consulta que recibe los filtros de id persona y id caso para retornar un
	 * solo RolPersonaCaso activo
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public RolPersonaCaso consultarPersonaAsignadaCaso(Long idPersona, Long idCaso) {
		List<RolPersonaCaso> rolPersonaCasoList;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro = :");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rolPersonaCasoList = query.getResultList();
		if (rolPersonaCasoList.isEmpty()) {
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR052.toString()),
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
			throw new SimascException(mensajeError);
		}
		return rolPersonaCasoList.get(0);
	}

	/**
	 * Método que válida la asignación de una persona a un caso
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 * @throws SimascException
	 */
	public RolPersonaCaso estaPersonaAsignadaCaso(Long idPersona, Long idCaso) {
		List<RolPersonaCaso> rolPersonaCasoList;
		RolPersonaCaso rolPersonaCaso = null;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro = :");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		rolPersonaCasoList = (List<RolPersonaCaso>) query.getResultList();

		if (!rolPersonaCasoList.isEmpty()) {
			rolPersonaCaso = rolPersonaCasoList.get(0);
		}

		return rolPersonaCaso;
	}

	public List<Persona> consultarArbitrosPrincipales(Long idCaso, String nombreRol) {

		List<String> listaRol = new ArrayList<>();
		listaRol.add(nombreRol);

		return consultarArbitrosPrincipales(idCaso, listaRol);

	}

	public List<Persona> consultarArbitrosPrincipales(Long idCaso, List<String> nombresRoles) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.rol.estadoRegistro=:");
		jpqlQuery.append("estadoRol");
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append("estadoPersona");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append("estadoRegistro");
		jpqlQuery.append(" AND rpc.tipoNombramiento=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter("estadoRol", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoPersona", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO,
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		return query.getResultList();
	}

	/**
	 * Metodo que actualiza el estado de todas las personas activas asociadas a
	 * un caso
	 * 
	 * @param idCaso
	 * @param estado
	 */
	public void actualizarEstadoPersonasCaso(Long idCaso, String estado, String motivo) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("UPDATE RolPersonaCaso rpc ");
		jpqlQuery.append("set rpc.estado =: estado, ");
		jpqlQuery.append("rpc.motivoInactivacion =: motivo, ");
		jpqlQuery.append("rpc.idUsuarioModificacion =: idUsuarioModificacion, ");
		jpqlQuery.append("rpc.fechaUltimaModificacion =: fecha ");
		jpqlQuery.append("WHERE rpc.caso.idCaso =: idCaso ");
		jpqlQuery.append("AND rpc.estadoRegistro =: estadoRegistro ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("estado", estado);
		query.setParameter("motivo", motivo);
		query.setParameter("idUsuarioModificacion", UtilConstantes.USUARIO_DEFECTO_SIMASC);
		query.setParameter("fecha", new Date());
		query.setParameter("idCaso", idCaso);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);

		query.executeUpdate();
	}

	public List<Object[]> consultarDetallesArbitros(Long idCaso, Boolean conMotivoInactivacion, Boolean conSecretarios) {
		
		String[] roles = conSecretarios ? UtilConstantes.ROLES_OPERADORES : UtilConstantes.ROLES_ARBITROS;
		StringBuilder nativeQuery = new StringBuilder();
		
		
		nativeQuery.append("SELECT rpc.* ");

		nativeQuery.append("FROM Rol_Persona_Caso rpc ");
		nativeQuery.append("INNER JOIN rol rol ");
		nativeQuery.append("ON rol.id_rol = rpc.id_rol ");
		nativeQuery.append("WHERE rpc.id_Caso = ?1 ");		
		nativeQuery.append("  AND rol.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(Arrays.asList(roles)));		
		nativeQuery.append("  AND rpc.estado_Registro = ?2 ");
		nativeQuery.append("  AND ( rpc.estado <> ?3");
		if( conMotivoInactivacion )
				nativeQuery.append(" OR rpc.motivo_Inactivacion is not null ");
		nativeQuery.append(" ) ");
		nativeQuery.append(" ORDER BY rpc.tipo_Nombramiento,");
		nativeQuery.append(" ( CASE WHEN rpc.estado = ?4 THEN 0 ");
				nativeQuery.append(" WHEN rpc.estado = ?5 THEN 1");
				nativeQuery.append(" WHEN rpc.estado = ?6 THEN 2"); //los rechazados o no se pronunciaron
				nativeQuery.append(" WHEN rpc.estado = ?7 THEN 3");
				nativeQuery.append(" WHEN rpc.estado = ?8 THEN 4");
				nativeQuery.append(" WHEN rpc.estado = ?3 THEN 5");
				nativeQuery.append(" ELSE 6");
			nativeQuery.append(" END ),");
		nativeQuery.append(" rpc.orden_De_Asignacion");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), RolPersonaCaso.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECUSADO);
		query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(8, UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		
		List<RolPersonaCaso> resultadoQuery = query.getResultList();
		List<Object[]> resultado = new ArrayList<>();
		for (RolPersonaCaso objeto : resultadoQuery) {
			Object[] campo = new Object[6];
			campo[0] = objeto;
			campo[3] = objeto.getPronunciamiento();
			campo[4] = (objeto.getRecusacionList() != null && !objeto.getRecusacionList().isEmpty() )? objeto.getRecusacionList().get(0) : null;
			resultado.add(campo);
		}

		return resultado;

	}

	private List<EventoRolPersonaCaso> consultarEventoCaso(Long idCaso, Long idPersona, List<String> tiposEvento) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT e");
		jpqlQuery.append(" FROM EventoRolPersonaCaso e");
		jpqlQuery.append(" WHERE e.idCaso =:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND e.idPersona =:");
		jpqlQuery.append("idPersona");
		jpqlQuery.append(" AND e.estadoAsignado IN :");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_TIPO_EVENTO);
		jpqlQuery.append(" AND e.estadoRegistro=:");
		jpqlQuery.append(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter("idPersona", idPersona);
		query.setParameter(Evento.ENTIDAD_EVENTO_TIPO_EVENTO, tiposEvento);
		query.setParameter(Evento.ENTIDAD_EVENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<Evento> eventos = query.getResultList();
		if (eventos.isEmpty())
			return null;
		return query.getResultList();
	}

	/**
	 * Metodo que actualiza el estado a las personas pasadas como parametro
	 * 
	 * @param idCaso
	 * @param estado
	 */
	public void actualizarEstadoPersonasCaso(List<Long> idPersonas, Long idCaso, String estado, String motivo) {
		for (Long idPersona : idPersonas) {
			RolPersonaCaso rolPersonaCaso = consultarRolPersonaCaso(idPersona, idCaso, null);
			if (rolPersonaCaso != null
					&& UtilDominios.ESTADO_REGISTRO_ACTIVO.equals(rolPersonaCaso.getEstadoRegistro())) {
				rolPersonaCaso.setEstado(estado);
				rolPersonaCaso.setMotivoInactivacion(motivo);
				rolPersonaCaso.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
				rolPersonaCaso.setFechaUltimaModificacion(new Date());
				actualizar(rolPersonaCaso);
			}
		}

	}

	public RolPersonaCaso consultarPersonasPorRolCasoInactivo(Long idPersonas, Long idCaso, String nombreRol) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.persona.idPersona =:");
		jpqlQuery.append("listaIdPersonas");
		jpqlQuery.append(" AND rpc.estadoRegistro =:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter("listaIdPersonas", idPersonas);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_INACTIVO);

		return (RolPersonaCaso) query.getSingleResult();

	}

	public List<RolPersonaCaso> consultarPersonasPorRolCaso(List<Long> idPersonas, Long idCaso) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre IN :rolesArbitro ");
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.persona.idPersona in :");
		jpqlQuery.append("listaIdPersonas");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter("rolesArbitro", Arrays.asList(UtilConstantes.ROLES_OPERADORES));
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter("listaIdPersonas", idPersonas);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();

	}

	/**
	 * Consulta las personas asignadas al caso y con el rol que se pasa como
	 * parámetro
	 * 
	 * @param idPersonas
	 * @param idCaso
	 * @param nombreRol
	 * @return List<RolPersonaCaso> Las personas asignadas al caso.
	 */
	public List<RolPersonaCaso> consultarPersonasAsignadasCasoPorRol(Long idCaso, String nombreRol) {
		return consultarPersonasAsignadasCasoPorRol(idCaso, Arrays.asList(nombreRol));
	}

	/**
	 * Consulta las personas asignadas al caso que cumplen los roles que se
	 * pasan como parámetro
	 * 
	 * @param idPersonas
	 * @param idCaso
	 * @param nombresRoles
	 * @return List<RolPersonaCaso> Las personas asignadas al caso.
	 */
	public List<RolPersonaCaso> consultarPersonasAsignadasCasoPorRol(Long idCaso, List<String> nombresRoles) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return query.getResultList();
	}

	/**
	 * Consulta las personas asignadas al caso que cumplen con los roles que se
	 * pasan como parámetro.
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso
	 *            : Identificador del caso.
	 * @param nombresRoles:
	 *            Lista de roles de las personas.
	 * @param sinIdentificacion:
	 *            bandera que indica si se filtran las personas que no tienen
	 *            identificacion.
	 * @return List<RolPersonaCaso> Las personas asignadas al caso.
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarPersonasAsignadasCasoPorRol(Long idCaso, List<String> nombresRoles,
			boolean sinIndentificacion) {
								
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		
		if (sinIndentificacion) {
			jpqlQuery.append(" AND rpc.persona.numeroDocumento IS NULL ");
		}

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return (List<RolPersonaCaso>) query.getResultList();
		
	}

	/**
	 * Consulta el abogado asignado al caso (Solo puede haber uno asignado al
	 * caso)
	 * 
	 * @param idCaso
	 * @return El abogado asignado al caso
	 */
	public RolPersonaCaso consultarAbogadoDelCaso(Long idCaso) {
		List<RolPersonaCaso> resultados = consultarPersonasAsignadasCasoPorRol(idCaso,
				UtilDominios.ROL_PERSONA_ABOGADO);
		RolPersonaCaso abogado = null;

		if (!resultados.isEmpty()) {
			abogado = resultados.get(0);
		}
		return abogado;
	}

	/**
	 * Consulta el secretario asignado al caso (Solo puede haber uno asignado al
	 * caso)
	 * 
	 * @param idCaso
	 * @return RolPersonaCaso
	 */
	public RolPersonaCaso consultarSecretarioDelCaso(Long idCaso) {
		List<RolPersonaCaso> resultados = consultarPersonasAsignadasCasoPorRol(idCaso,
				UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		RolPersonaCaso secretario = null;
		if (!resultados.isEmpty()) {
			secretario = resultados.get(0);
		}
		return secretario;
	}

	/**
	 * Consulta las partes demandadas en el caso que se pasa como parámetro
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<RolPersonaCaso> consultarDemandadosCaso(Long idCaso) {

		return consultarPersonasAsignadasCasoPorRol(idCaso,
				Arrays.asList(UtilDominios.ROL_PERSONA_PARTE_DEMANDADA, UtilDominios.ROL_PERSONA_DEUDOR_RECUPERACION));
	}

	/**
	 * Consulta las partes demandantes en el caso que se pasa como parámetro
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<RolPersonaCaso> consultarDemandantesCaso(Long idCaso) {

		return consultarPersonasAsignadasCasoPorRol(idCaso, Arrays.asList(UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE,
				UtilDominios.ROL_PERSONA_ACREEDOR_RECUPERACION));
	}

	/**
	 * Consulta los arbitros preseleccionados al caso
	 * 
	 * @param idCaso
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarArbitrosDesignados(Long idCaso, String nombreRol) {
		List<String> lstTiposNombramiento = Arrays.asList(UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE,
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre=:");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado <>:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" AND rpc.tipoNombramiento IN : ");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);

		jpqlQuery.append(" ORDER BY rpc.tipoNombramiento ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, lstTiposNombramiento);

		return query.getResultList();

	}

	/**
	 * Consulta los arbitros asignados al caso que se pasa como parametro. Si se
	 * pasa el tipoNombramiento, el metodoNombramiento o el estado solo se
	 * devuelven los que correspondan a estos valores (Estos valores son
	 * opcionales)
	 * 
	 * @param idCaso
	 * @param tipoNombramiento
	 *            Valor del dominio de TIPO_NOMBRAMIENTO
	 * @param metodosNombramiento
	 *            Lista con los valores del dominio de METODOS_NOMBRAMIENTO que
	 *            deben tener los arbitros para ser consultados
	 * @param estado
	 *            Lista de valores del dominio de ESTADO_ROL_PERSONA_CASO que
	 *            deben tener los arbitros para ser consultados
	 * 
	 * @return Los arbitros asignados al caso
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarArbitros(Long idCaso, String tipoNombramiento,
			List<String> metodosNombramiento, List<String> estados) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=").append("'ACT'");
		if (estados != null && !estados.isEmpty()) {
			jpqlQuery.append(" AND rpc.estado in :");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		}
		if (tipoNombramiento != null) {
			jpqlQuery.append(" AND rpc.tipoNombramiento=:");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		}
		if (metodosNombramiento != null && !metodosNombramiento.isEmpty()) {
			jpqlQuery.append(" AND rpc.metodoNombramiento in :");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO);
		}

		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ASC ");

		Query query = mp.createQuery(jpqlQuery.toString());

		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, Arrays.asList(UtilConstantes.ROLES_ARBITROS));
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		if (estados != null && !estados.isEmpty()) {
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		}
		if (tipoNombramiento != null) {
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, tipoNombramiento);
		}
		if (metodosNombramiento != null && !metodosNombramiento.isEmpty()) {
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO, metodosNombramiento);
		}

		return query.getResultList();

	}
	
	/**
	 * Consulta los árbitros asignados al caso que se pasa como parametro
	 * 
	 * @param idCaso
	 * @return
	 */
	public List<RolPersonaCaso> consultarArbitrosPrincipalesCaso(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idRol IN ( ");
		jpqlQuery.append(" SELECT pss.idRol FROM ParametroServicioSorteo pss ");
		jpqlQuery.append("  WHERE pss.estadoRegistro = :")
				.append(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO);
		jpqlQuery.append(" ) ");
		jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idCaso = :").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro = :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, Arrays.asList(
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR));

		return query.getResultList();
	}

	/**
	 * Consulta si la persona esta asignada a un caso 
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public RolPersonaCaso consultarPersonaAsignadoCaso(Long idPersona, Long idCaso, List<String> estados) {
		RolPersonaCaso rolPersonaCaso;
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idCaso = :");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idPersona = :");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro = :");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		if(estados !=null && !estados.isEmpty()){
			jpqlQuery.append(" AND rpc.estado IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		}

		Query query = mp.createQuery(jpqlQuery.toString());

		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(estados !=null && !estados.isEmpty()){
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		}
		
		try{
			rolPersonaCaso = (RolPersonaCaso) query.getSingleResult();
		}catch(NoResultException | NonUniqueResultException e){
			rolPersonaCaso = null;
		}

		return rolPersonaCaso;
	}

	/**
	 * Consulta una persona en rol persona caso que tenga registrado el id de
	 * una persona reemplazada
	 * 
	 * @param idPersona
	 * @param idCaso
	 * @return RolPersonaCaso
	 */
	public RolPersonaCaso consultarPersonaReemplazoCaso(Long idPersona, Long idCaso, String nombreRol) {
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
			jpqlQuery.append(" WHERE rpc.idPersonaPrincipalReemplazado =: ");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO);
			jpqlQuery.append(" AND rpc.caso.idCaso = :");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);

			if (nombreRol != null && !nombreRol.isEmpty()) {
				jpqlQuery.append(" AND rpc.rol.nombre =: ");
				jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
			}

			Query query = mp.createQuery(jpqlQuery.toString());
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO, idPersona);
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);

			if (nombreRol != null && !nombreRol.isEmpty()) {
				query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
			}

			rolPersonaCaso = (RolPersonaCaso) query.getSingleResult();
		} catch (NoResultException e) {
			// Si no se encuentra resultado fue porque no hubo suplentes que lo
			// reemplazaron
			String mensajeError = String.format(
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR052.toString()),
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO191.toString()));
			logger.info(mensajeError);
			rolPersonaCaso = null;
		} catch (Exception e) {
			logger.error(e);
		}
		return rolPersonaCaso;
	}

	public void actualizarRolPersonaCaso(RolPersonaCaso rolPersonaCaso) {
		mp.getEntityManager().setFlushMode(FlushModeType.COMMIT);
		actualizar(rolPersonaCaso);
		mp.getEntityManager().flush();
	}

	public RolPersonaCaso obtenerRolDePersona(CasosAsignadosDTO casosAsignadosDTO) {
		RolPersonaCaso personaCaso = new RolPersonaCaso();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc WHERE").append(" rpc.persona.idPersona =: ")
					.append(Persona.ENTIDAD_PERSONA_PK).append(" AND rpc.caso.idCaso =: ").append(Caso.ENTIDAD_CASO_PK)
					.append(" AND rpc.estadoRegistro =: ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO)
					.append(" AND rpc.persona.estadoRegistro =: ")
					.append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO)
					.append(" AND rpc.caso.estadoRegistro =: ")
					.append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);

			Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, casosAsignadosDTO.getIdPersona());
			query.setParameter(Caso.ENTIDAD_CASO_PK, casosAsignadosDTO.getIdCaso());
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

			personaCaso = (RolPersonaCaso) query.getSingleResult();

		} catch (NoResultException e) {
			String mensajeError = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR153.toString()));
			throw new SimascException(mensajeError);
		} catch (Exception e) {
			logger.error(e);
		}

		return personaCaso;
	}

	/**
	 * Permite a una persona natural o jurídica consultar si se encuentra
	 * vinculado a un caso
	 * 
	 * @param tipoDoc
	 * @param identificacion
	 * @param nombre
	 * @return la lista RolPersonaCaso donde se encontro a la persona
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultaVinculacionPersonaCaso(String tipoDoc, String identificacion, String nombre) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT * FROM ROL_PERSONA_CASO RPC ");
		jpqlQuery.append("INNER JOIN PERSONA p ");
		jpqlQuery.append("ON RPC.ID_PERSONA=P.ID_PERSONA ");
		boolean tipo = false;
		if (tipoDoc != null && identificacion != null) {
			tipo = true;
			jpqlQuery.append(" WHERE p.TIPO_DOCUMENTO = ?1");
			jpqlQuery.append(" AND p.NUMERO_DOCUMENTO = ?2");
		} else if (UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION.equals(tipoDoc)
				|| UtilDominios.TIPO_DOCUMENTO_PERSONA_POR_VERIFICAR.equals(tipoDoc)) {
			tipo = true;
			jpqlQuery.append(" WHERE p.TIPO_DOCUMENTO = ?1");

		}
		if (nombre != null) {
			String filtro = tipo ? " AND" : " WHERE";
			jpqlQuery.append(filtro
					+ " replace(CONCAT(p.primer_nombre_o_razon_social, coalesce(p.segundo_nombre,''), coalesce(p.primer_apellido,''), coalesce(p.segundo_apellido,'')),' ','') LIKE ?3");
		}
		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), RolPersonaCaso.class);
		if (tipoDoc != null && identificacion != null) {
			query.setParameter(1, tipoDoc);
			query.setParameter(2, identificacion);
		} else if (UtilDominios.TIPO_DOCUMENTO_PERSONA_SIN_IDENTIFICACION.equals(tipoDoc)
				|| UtilDominios.TIPO_DOCUMENTO_PERSONA_POR_VERIFICAR.equals(tipoDoc)) {
			query.setParameter(1, tipoDoc);
		}
		if (nombre != null) {
			query.setParameter(3, '%' + nombre.replace(" ", "").trim() + '%');
		}

		return query.getResultList();
	}

	/**
	 * Consulta arbitros externos
	 * 
	 * @param idCaso
	 * @param nombresRoles
	 * @return
	 */
	public List<Persona> consultarArbitrosExternos(Long idCaso, List<String> nombresRoles) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc.persona FROM RolPersonaCaso rpc");
		jpqlQuery.append(" LEFT JOIN rpc.persona.personaServicioMateriaList psm ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.rol.estadoRegistro=:");
		jpqlQuery.append("estadoRol");
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append("estadoPersona");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append("estadoRegistro");
		jpqlQuery.append(" AND rpc.tipoNombramiento=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append(" AND psm.estadoRegistro=:");
		jpqlQuery.append("estadoRegistroPSM");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter("estadoRol", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoPersona", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO,
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter("estadoRegistroPSM", null);

		return query.getResultList();
	}

	/**
	 * Consulta arbitros registrados en ministerio
	 * 
	 * @param idCaso
	 * @param nombresRoles
	 * @return
	 */
	public List<Persona> consultarArbitrosMinisterio(Long idCaso, List<String> nombresRoles) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT distinct (rpc.persona) FROM RolPersonaCaso rpc");
		jpqlQuery.append(" INNER JOIN rpc.persona.personaServicioMateriaList psm ");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.rol.nombre in :");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.rol.estadoRegistro=:");
		jpqlQuery.append("estadoRol");
		jpqlQuery.append(" AND rpc.persona.estadoRegistro=:");
		jpqlQuery.append("estadoPersona");
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append("estadoRegistro");
		jpqlQuery.append(" AND rpc.tipoNombramiento=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND rpc.estado=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter("estadoRol", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoPersona", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO,
				UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO,
				UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		return query.getResultList();
	}

	/**
	 * consulta los arbitros para el reporte Reporte de casos sorteados
	 * 
	 * @param idCaso
	 * @param idSorteo
	 * @return
	 */
	public List<RolPersonaCaso> obtenerArbitrosSorteoCaso(Long idCaso, Long idSorteo) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append("INNER JOIN rpc.rol rl ");
		jpqlQuery.append("WHERE rpc.caso.idCaso= :idCaso ");
		jpqlQuery.append("AND rpc.estadoRegistro = '" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		jpqlQuery.append("AND rpc.metodoNombramiento = '" + UtilDominios.NOMBRAMIENTO_POR_LA_CCB + "' ");
		jpqlQuery.append("AND rpc.idSorteo = :idSorteo ");
		jpqlQuery.append("AND rpc.estado <> '" + UtilDominios.ESTADO_ROL_PERSONA_CASO_NO_APLICA + "' ");
		jpqlQuery.append("ORDER BY rpc.tipoNombramiento, rpc.ordenDeAsignacion ");

		TypedQuery<RolPersonaCaso> tipoQuey = mp.getEntityManager().createQuery(jpqlQuery.toString(), RolPersonaCaso.class);
		tipoQuey.setParameter("idCaso", idCaso);
		tipoQuey.setParameter("idSorteo", idSorteo);

		return tipoQuey.getResultList();
	}

	public List<RolPersonaCaso> consultarApoderadosPorIdPersonaEnCaso(Long idPersona, Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append("WHERE rpc.idPersonaApoderado = :idApoderado ");
		jpqlQuery.append("AND  rpc.rolPersonaCasoPK.idCaso = :idCaso");

		Query tipoQuey = mp.getEntityManager().createQuery(jpqlQuery.toString());
		tipoQuey.setParameter("idApoderado", idPersona);
		tipoQuey.setParameter("idCaso", idCaso);

		return tipoQuey.getResultList();
	}

	/**
	 * Método encargado de consultar las personas de un caso por rol y por una
	 * lista de estados
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estados
	 * @return
	 */
	public List<Persona> consultarArbitrosCasoPorEstados(Long idCaso, List<String> estados) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc.persona ");
		jpqlQuery.append("  FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.caso.idCaso = :").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append("   AND rpc.rol.nombre IN :").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append("   AND rpc.estado IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append("   AND rpc.estadoRegistro = :")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.rol.estadoRegistro = :").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.persona.estadoPersona = :").append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append("   AND rpc.persona.estadoRegistro = :").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.caso.estadoRegistro = :").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, Arrays.asList(UtilConstantes.ROLES_ARBITROS));
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Método encargado de consultar las personas de un caso por rol y por una
	 * lista de estados
	 * 
	 * @param idCaso
	 * @param nombreRol
	 * @param estados
	 * @return
	 */
	public List<RolPersonaCaso> consultarArbitrosCasoPorEstadosRPC(Long idCaso, List<String> estados) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc ");
		jpqlQuery.append("  FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.caso.idCaso = :").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append("   AND rpc.rol.nombre IN :").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append("   AND rpc.estado IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append("   AND rpc.estadoRegistro = :")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.rol.estadoRegistro = :").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.persona.estadoPersona = :").append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append("   AND rpc.persona.estadoRegistro = :").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.caso.estadoRegistro = :").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, Arrays.asList(UtilConstantes.ROLES_ARBITROS));
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Consulta las personas asignadas a un caso dados unos roles especificos
	 * 
	 * @param idCaso
	 * @param roles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> consultarPartesDelCaso(Long idCaso, List<String> roles) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc.persona ");
		jpqlQuery.append("  FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.caso.idCaso = :").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append("   AND rpc.rol.nombre IN :").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append("   AND rpc.estado NOT IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append("   AND rpc.estadoRegistro = :")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.rol.estadoRegistro = :").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.persona.estadoPersona = :").append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append("   AND rpc.persona.estadoRegistro = :").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.caso.estadoRegistro = :").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(
				"   AND (rpc.persona.correoElectronicoList IS NOT NULL OR rpc.persona.ubicacionList IS NOT NULL) ");
		jpqlQuery.append(" ORDER BY rpc.rol.nombre ");

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, roles);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, Arrays
				.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO));
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Consulta las personas asignadas a un caso dados unos roles especificos
	 * 
	 * @param idCaso
	 * @param roles
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolPersonaCaso> consultarPartesDelCasoRPC(Long idCaso, List<String> roles) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT rpc ");
		jpqlQuery.append("  FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" WHERE rpc.caso.idCaso = :").append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append("   AND rpc.rol.nombre IN :").append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append("   AND rpc.estado NOT IN :").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		jpqlQuery.append("   AND rpc.estadoRegistro = :")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.rol.estadoRegistro = :").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.persona.estadoPersona = :").append(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA);
		jpqlQuery.append("   AND rpc.persona.estadoRegistro = :").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append("   AND rpc.caso.estadoRegistro = :").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(
				"   AND (rpc.persona.correoElectronicoList IS NOT NULL OR rpc.persona.ubicacionList IS NOT NULL) ");
		jpqlQuery.append(" ORDER BY rpc.rol.nombre ");

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, roles);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, Arrays
				.asList(UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO));
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_PERSONA, UtilDominios.ESTADO_PERSONA_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}

	/**
	 * Consulta la lista de RolPersonaCaso de una persona en un caso dado el
	 * numero del documento de la persona el nombre del rol y el caso
	 * 
	 * @param numeroCedula
	 * @param nombreRol
	 * @param idCaso
	 * @return
	 */
	public List<RolPersonaCaso> consultarListaRolPersonaCaso(String numeroDocumento, String nombreRol, Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append("WHERE rpc.persona.numeroDocumento = ?1 ");
		jpqlQuery.append("AND rpc.rol.nombre = ?2 ");
		jpqlQuery.append("AND rpc.caso.idCaso = ?3 ");
		jpqlQuery.append("AND rpc.estadoRegistro = ?4 ");

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), RolPersonaCaso.class);
		query.setParameter(1, numeroDocumento);
		query.setParameter(2, nombreRol);
		query.setParameter(3, idCaso);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList();
	}
	
	/**
	 * Consulta el rol persona caso activo por idCaso, nombreRoles y estados
	 * 
	 * @param idCaso
	 * @param nombresRoles
	 * @param estados
	 * @return
	 * @throws SimascException
	 */
	public List<RolPersonaCaso> consultarPersonasPorRolEstado(Long idCaso, List<String> nombresRoles,
            List<String> estados, boolean consultarActivosInactivos) {
            StringBuilder jpqlQuery = new StringBuilder();
            jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
            jpqlQuery.append(" WHERE rpc.rol.nombre IN : ");
            jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
            jpqlQuery.append(" AND rpc.caso.idCaso=: ");
            jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
            if(!consultarActivosInactivos){
                jpqlQuery.append(" AND rpc.estadoRegistro =: ");
                jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
            }             
            jpqlQuery.append(" AND rpc.estado IN : ");
            jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
            
            Query query = mp.createQuery(jpqlQuery.toString());
            query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
            query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
            if(!consultarActivosInactivos)
                query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
                                               UtilDominios.ESTADO_REGISTRO_ACTIVO);                           
            query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
            
            return query.getResultList();
    }

	/**
	 * Consulta los rol persona caso por caso, metodo de nombramiento y persona tercero 
	 * @param idCaso
	 * @param metodoNombramiento
	 * @param idPersonaTercero
	 * @return
	 */
	public List<RolPersonaCaso> consultarPrestadoresPorMetodoNombramientoTercero( Long idCaso, String metodoNombramiento, Long idPersonaTercero ){
		  StringBuilder jpqlQuery = new StringBuilder();
          jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
          jpqlQuery.append(" WHERE rpc.rolPersonaCasoPK.idCaso =:").append(Caso.ENTIDAD_CASO_PK);
          jpqlQuery.append(" AND rpc.metodoNombramiento =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO);
          if( idPersonaTercero != null)
          	jpqlQuery.append(" AND rpc.idPersonaTercero =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO);
          jpqlQuery.append(" AND rpc.estadoRegistro =:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
          jpqlQuery.append(" AND rpc.estado in (" + "'" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "','"
  				+ UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "','" + UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO
  				+ "')");
          Query query = mp.createQuery(jpqlQuery.toString());
          query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
          query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_METODO_NOMBRAMIENTO, metodoNombramiento);
          if( idPersonaTercero != null )
              query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_TERCERO, idPersonaTercero);
          query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
          
          return query.getResultList();
	}
	
	public List<Long> casosNoHabilitacion(Date fechaLiberacion, Long idServicio, Long idMateria, 
			Long idPersona, Long idCaso, Boolean conMateria, Long idRol){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT rpc.id_caso FROM ROL_PERSONA_CASO rpc  ");
		nativeQuery.append(" INNER JOIN CASO ca ON ca.id_caso = rpc.id_caso ");
		nativeQuery.append(" INNER JOIN SORTEO sor ON sor.id_sorteo = rpc.id_sorteo ");
		nativeQuery.append(" AND (sor.preseleccion = 0 or (sor.preseleccion = 1 and sor.quien_preselecciona = ?13 )) ");
		nativeQuery.append(" AND ca.id_servicio = ?12 ");
		nativeQuery.append(" WHERE rpc.estado IN ( ?9 , ?10 ) ");
		nativeQuery.append(" AND rpc.metodo_nombramiento = ?8 ");
		nativeQuery.append(" AND ca.estado_caso NOT IN ( ?7 ) ");
		nativeQuery.append(" AND rpc.id_rol = ?6 ");
		nativeQuery.append(" AND rpc.tipo_nombramiento = ?3 ");
		nativeQuery.append(" AND ca.id_caso <> ?4 ");
		nativeQuery.append(" AND ca.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" AND sor.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.id_persona = ?2 ");
		nativeQuery.append(" AND sor.fecha_realizacion >= ?5   ");
		nativeQuery.append(" AND ca.id_caso not in (select acl.id_caso from ARBITRO_CASO_LIBERACION acl where acl.id_persona = rpc.id_persona) ");
		
		if(conMateria){
			nativeQuery.append(" AND ca.id_materia = ?11  ");
		}
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Long.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idPersona);
		query.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(4, idCaso);
		query.setParameter(5, fechaLiberacion);
		query.setParameter(6, idRol);
		query.setParameter(7, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(8, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(12, idServicio);
		query.setParameter(13, UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA);
		if(conMateria){
			query.setParameter(11, idMateria);
		}
		
		return query.getResultList();
	}
	
	public List<PersonaBasicaDTO> consultarArbitrosDisponiblesSorteo(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("SELECT concat(ltrim(isnull(per.primer_nombre_o_razon_social, '') + ' '),");
		nativeQuery.append("			ltrim(isnull(per.segundo_nombre, '') + ' '),");
		nativeQuery.append("			ltrim(isnull(per.primer_apellido, '') + ' '),");
		nativeQuery.append("			ltrim(isnull(per.segundo_apellido, ''))) as nombreCompleto, ");
		nativeQuery.append("		rpc.id_persona as idPersona ");
		nativeQuery.append("FROM PERSONA per INNER JOIN ROL_PERSONA rp ON per.id_persona = rp.id_persona ");
		nativeQuery.append("INNER JOIN LISTA ls ON ls.id_lista = rp.id_lista ");
		nativeQuery.append("INNER JOIN PERSONA_SERVICIO_MATERIA psm ON per.id_persona = psm.id_persona ");
		nativeQuery.append("INNER JOIN MATERIA ma ON psm.id_materia = ma.id_materia ");
		nativeQuery.append("INNER JOIN CASO c on ma.id_materia = c.id_materia and psm.id_servicio = c.id_servicio ");
		nativeQuery.append("INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON rp.id_rol = pss.id_rol and pss.id_servicio = c.id_servicio ");
		nativeQuery.append("INNER JOIN ESTADO_PERSONA_TIPO_SERVICIO ep on per.id_persona = ep.id_persona AND ep.estado = ?1 ");
		nativeQuery.append("LEFT JOIN ROL_PERSONA_CASO rpc on pss.id_rol = rpc.id_rol and c.id_caso = rpc.id_caso and per.id_persona = rpc.id_persona ");
		nativeQuery.append("WHERE rp.fecha_fin_vigencia is null ");
		nativeQuery.append("AND psm.fecha_fin_de_vigencia is null ");
		nativeQuery.append("AND rp.estado_registro = ?2 ");
		nativeQuery.append("AND per.estado_registro = ?2 ");
		nativeQuery.append("AND per.estado_persona = ?2 ");
		nativeQuery.append("AND psm.estado_para_sorteo = ?2 ");
		nativeQuery.append("AND c.id_caso = ?3 ");
		nativeQuery.append("AND ls.nombre = case c.tipo_cuantia when ?4 THEN 'B' ELSE 'A' END ");
		nativeQuery.append("order by nombreCompleto");
		Query q = getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaBasicaDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(3, idCaso);
		q.setParameter(4, UtilDominios.TIPO_CUANTIA_MENOR);
		
		return q.getResultList();
	}
	
	
	

	/**
	 * consulta la lista de casos pendientes por pronunciamiento del conciliador
	 * 
	 * @param idPersona
	 * @return
	 */
	public List<PendientePronunciamientoDTO> casosPendientesPronunciamentoConciliador(Long idPersona, String rol) {
		List<PendientePronunciamientoDTO> casosPendientes = null;
		
		Long idServicio = UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION ;
		String tipoServicio = UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS;
		
		if(rol != null && rol.equals(UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD)) {
			idServicio=  UtilConstantes.ID_SERVICIO_EQUIDAD;
			tipoServicio = UtilDominios.TIPO_SERVICIO_CONVIVENCIA;
		}

		String sedeAudiencia = "(SELECT se.nombre FROM SEDE se "
				+ " left JOIN SALA sa ON sa.id_sede = se.id_sede AND sa.estado_registro = ?1 "
				+ " left JOIN AGENDAMIENTO ag ON ag.id_sala = sa.id_sala AND ag.estado_registro = ?1 "
				+ " WHERE id_audiencia = au.id_audiencia " + " AND se.estado_registro = ?1) AS sede, ";

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT ca.id_caso AS idCaso, ");
		jpqlQuery.append(" ca.nombre AS nombreCaso, ");
		jpqlQuery.append("CASE WHEN ca.id_servicio = ?8 ");
		jpqlQuery.append("THEN s.nombre ");
		jpqlQuery.append("ELSE co.nombre END AS convenioNombre, ");
		jpqlQuery.append(" ca.fecha_radicacion AS fechaRadicacion, ");
		jpqlQuery.append(" au.hora_inicio AS fechaAudiencia, ");
		jpqlQuery.append(sedeAudiencia);
		jpqlQuery.append(" ca.hechos AS hechos, ");
		jpqlQuery.append(" (SELECT nombre FROM SERVICIO WHERE id_servicio = ca.id_servicio) AS servicio, ");
		jpqlQuery.append(" ca.id_servicio AS idServicio, ");
		jpqlQuery.append(" rpc.id_rol AS idRol, ");
		jpqlQuery.append(" rpc.id_persona AS idPersona, ");
		jpqlQuery.append(" (SELECT nombre FROM MATERIA ma WHERE ma.id_materia = ca.id_materia) AS materia, ");
		jpqlQuery.append(" ca.pretensiones AS pretensiones, ");
		jpqlQuery.append(" ca.valor_pretensiones AS cuantia ");

		jpqlQuery.append(" FROM ROL_PERSONA_CASO  rpc ");		
		jpqlQuery.append(" LEFT JOIN CASO ca ON rpc.id_caso = ca.id_caso ");
		jpqlQuery.append(" INNER JOIN SERVICIO s ON ca.id_servicio = s.id_servicio ");
		jpqlQuery.append(" LEFT JOIN CONVENIO co ON co.id_convenio = ca.id_convenio AND co.estado_registro = ?1 ");
		// jpqlQuery.append(
		// " LEFT JOIN EVENTO e ON ca.id_caso = e.id_caso AND e.estado_registro
		// = ?1 AND e.tipo_evento = ?2 ");
		jpqlQuery.append(
				" LEFT JOIN AUDIENCIA au ON au.estado_registro = ?1 AND au.estado = ?3 and au.id_caso = ca.id_caso ");

		jpqlQuery.append(" WHERE ca.estado_registro = ?1 ");
		jpqlQuery.append(" AND rpc.estado_registro = ?1 ");
		jpqlQuery.append(" AND rpc.estado = ?4 ");
		jpqlQuery.append(" AND rpc.tipo_nombramiento = ?7 ");
		jpqlQuery.append(" AND rpc.id_rol IN (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ?6 ) ");
		jpqlQuery.append(" AND rpc.id_persona = ?5 ");
		jpqlQuery.append(" ORDER BY ca.id_servicio , ca.id_caso ");

		Query q = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), PendientePronunciamientoDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		// q.setParameter(2, UtilDominios.TIPO_EVENTO_RADICACION_DE_CASO);
		q.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		q.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(5, idPersona);
		q.setParameter(6, tipoServicio);
		q.setParameter(7, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		q.setParameter(8, idServicio);		
		casosPendientes = q.getResultList();
		return casosPendientes;
	}

	/**
	 * Consulta el rol persona caso activo por idCaso, nombreRoles y estados
	 * 
	 * @param idCaso
	 * @param nombresRoles
	 * @param estados
	 * @return
	 * @throws SimascException
	 */
	public List<RolPersonaCaso> consultarPersonasoPorRoleEstado(Long idCaso, List<String> nombresRoles,
			List<String> estados, boolean consultarActivosInactivos) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.nombre IN : ");
		jpqlQuery.append(Rol.ENTIDAD_ROL_NOMBRE);
		jpqlQuery.append(" AND rpc.caso.idCaso=: ");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		if (!consultarActivosInactivos) {
			jpqlQuery.append(" AND rpc.estadoRegistro =: ");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		}
		jpqlQuery.append(" AND rpc.estado IN : ");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombresRoles);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		if (!consultarActivosInactivos)
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);

		return query.getResultList();
	}

	public List<RolPersonaCaso> consultaConciliadoresCasoEstadoNombramiento(Long idCaso, List<String> estados,
			String tipoNombramiento) {
		
		List<String> tiposServicio = new ArrayList<String>();
		tiposServicio.add(UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		tiposServicio.add(UtilDominios.TIPO_SERVICIO_CONVIVENCIA);
		
		final String TIPO_SERVICIO = "TIPO_SERVICIO";
		StringBuilder jpqlQuery = new StringBuilder();
		
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE rpc.rol.idRol IN ");
		jpqlQuery.append(
				" ( SELECT tsr.tipoDeServicioRolPK.idRol FROM TipoDeServicioRol tsr WHERE tsr.tipoDeServicioRolPK.tipoServicio IN :");
		jpqlQuery.append(TIPO_SERVICIO);
		jpqlQuery.append(" ) AND rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.estadoRegistro=:");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rpc.estado IN :");
		jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		if (tipoNombramiento != null) {
			jpqlQuery.append(" AND rpc.tipoNombramiento = :");
			jpqlQuery.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		}

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(TIPO_SERVICIO, tiposServicio);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, tipoNombramiento);

		return query.getResultList();

	}

	public List<RolPersonaCaso> consultarPartesCaso(Long idCaso) {
		return consultarPartesCasoPersona(idCaso, null);
	}
	
	public List<RolPersonaCaso> consultarPartesCasoPersona(Long idCaso, Long idPersona) {
		final String DOMINIO_CLASIFICADOR = "DOMINIO_CLASIFICADOR";
		final String CODIGO_CLASIFICADOR = "CODIGO_CLASIFICADOR";
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append("WHERE rpc.rol.nombre IN  ");
		jpqlQuery.append("( SELECT cd.clasificadorDominioPK.codigoClasificado FROM ClasificadorDominio cd WHERE ");
		jpqlQuery.append(" cd.clasificadorDominioPK.dominioClasificador = :");
		jpqlQuery.append(DOMINIO_CLASIFICADOR);
		jpqlQuery.append(" AND cd.clasificadorDominioPK.codigoClasificador =:");
		jpqlQuery.append(CODIGO_CLASIFICADOR);
		jpqlQuery.append(" ) AND rpc.rolPersonaCasoPK.idCaso = : ").append(Caso.ENTIDAD_CASO_PK);
		if(idPersona!=null){
			jpqlQuery.append(" AND rpc.rolPersonaCasoPK.idPersona = : ").append(Persona.ENTIDAD_PERSONA_PK);
		}
		jpqlQuery.append(" AND rpc.estadoRegistro =: ").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial,rpc.persona.numeroDocumento");
		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(DOMINIO_CLASIFICADOR, UtilDominios.DOMINIO_AGRUPADOR_ROL_PERSONA);
		query.setParameter(CODIGO_CLASIFICADOR, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idPersona!=null){
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);	
		}
		
		return query.getResultList();
	}

	/**
	 * Consulta las personas asignadas al caso cuyo rol está habilitado para el
	 * envio de cartas de acuerdo al servicio asociado al caso.
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idServicios:
	 *            Lista de idServicios.
	 * @return List<RolPersonaCaso> Las personas asignadas al caso.
	 */
	@SuppressWarnings("unchecked")
	public List<PersonaCasoListDTO> consultarPersonasAsignadasCaso(Long idCaso, List<Long> idServicios) {

		String nombrePersona = "(SELECT CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', "
				+ "p.primer_apellido, ' ', p.segundo_apellido) "
				+ "FROM PERSONA p  WHERE p.id_persona = rpc.id_persona AND p.estado_registro = ?1 ) AS nombrePersona, ";

		String nombreRol = "(SELECT r.nombre FROM ROL r WHERE r.id_rol = rpc.id_rol  "
				+ "AND r.estado_registro =?1 ) AS nombreRol, ";

		String tieneCorreo = "(SELECT CASE WHEN COUNT (c.id_correo) >= 1 THEN CAST (1 AS BIT) "
				+ "ELSE CAST (0 AS BIT) END  FROM CORREO_ELECTRONICO c "
				+ "WHERE c.id_persona = rpc.id_persona AND c.estado_registro = ?1 ) AS tieneCorreo  ";

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT DISTINCT(rpc.id_persona) AS idPersona, ");
		nativeQuery.append(nombrePersona);
		nativeQuery.append(nombreRol);
		nativeQuery.append(tieneCorreo);
		nativeQuery.append("FROM ROL_PERSONA_CASO rpc ");
		nativeQuery.append("INNER JOIN AGRUPAMIENTO_ROL ar ");
		nativeQuery.append("ON ar.id_rol = rpc.id_rol AND ar.tipo_agrupamiento = ?2 ");
		nativeQuery.append("AND ar.id_servicio ").append(UtilConsultasSQL.clausulaInSQLSNumeros(idServicios));
		nativeQuery.append("AND ar.estado_registro= ?1 ");
		nativeQuery.append("WHERE rpc.id_caso = ?3 AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" ORDER BY nombrePersona");

		Query q = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), PersonaCasoListDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, UtilDominios.AGRUPADOR_ROL_PERSONA_ENVIO_CARTAS);
		q.setParameter(3, idCaso);

		return (List<PersonaCasoListDTO>) q.getResultList();

	}

	/**
	 * Consulta el lote de cartas a generar CON-F-074
	 * 
	 * @param filtros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LotesCartasDTO> lotesCartasByFiltros(LotesCartasFiltrosDTO filtros) {
		String partes = null;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct c.id_caso codigoCaso, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',"
				+ "rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as nombreParte, ");
		nativeQuery.append(" (select nombre from dominio d where d.codigo=r.nombre and dominio=?1 ) rol, ");
		
		if(filtros.isEnvioCorreo()){			
			nativeQuery.append(" ce.direccion correo, ");
		}else{
			nativeQuery.append(" (select top 1 ce.direccion  from CORREO_ELECTRONICO ce where ce.id_persona=p.id_persona"
					+ " and ce.tipo=?15 and ce.estado_registro=?2) as correo, ");		
		}
				
		nativeQuery.append(" 'false' as selected, ");
		nativeQuery.append(" ?13 as enviarCorreo, ");
		nativeQuery.append(" a.id_audiencia as idAudiencia ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join rol r on rpc.id_rol=r.id_rol");
		nativeQuery.append(" inner join caso c on c.id_caso=rpc.id_caso and c.estado_registro=?2 and estado_caso<>?3 ");
		if (UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA.equals(filtros.getTipoCaso())
				|| UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(filtros.getTipoCaso()))
			nativeQuery.append(" inner join convenio co on co.id_convenio = c.id_convenio and co.estado_registro = 'ACT' ");
		
		nativeQuery.append(" inner join servicio se on se.id_servicio = c.id_servicio and se.tipo = ?14 ");
		nativeQuery.append(
				" inner join persona p on rpc.id_persona=p.id_persona and p.estado_persona=?4 and p.estado_registro=?2 ");
		
		if(filtros.isEnvioCorreo()){		
			nativeQuery.append(" inner join correo_electronico ce on ce.id_persona=p.id_persona and ce.estado_registro=?2 ");
			nativeQuery.append(" and (ce.tipo = ?15 or (ce.tipo <> ?15 and exists ");
			nativeQuery.append(" (select 1 from CORREO_ELECTRONICO_ROL_PERSONA_CASO cep where cep.id_caso = c.id_caso and cep.id_correo = ce.id_correo))) ");
		}
		
		nativeQuery
				.append(" inner join AUDIENCIA a on a.id_caso=c.id_caso and a.estado_registro= ?2 and a.estado= ?11 and a.hora_inicio > GETDATE() ");
		if (UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO.equals(filtros.getTipoCaso())
				|| UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(filtros.getTipoCaso())) {
			nativeQuery.append(" inner join Agendamiento ag on ag.id_audiencia=a.id_audiencia and ag.estado_registro=?2 ");
			nativeQuery.append(" inner join sala sa on sa.id_sala=ag.id_sala and sa.estado_registro=?2 ");
			nativeQuery.append(" inner join sede s on s.id_sede=sa.id_sede and s.estado_registro=?2");			
		}
		nativeQuery.append(" where rpc.estado_registro=?2 ");
		nativeQuery.append(" and cast(fecha_radicacion as Date) between cast(?6 as Date) and cast(?7 as Date) ");
		nativeQuery.append(" and c.id_servicio = ?12 ");

		if (Long.valueOf(filtros.getIdCaso()) != null && Long.valueOf(filtros.getIdCaso()) > 0)
			nativeQuery.append(" and c.id_caso =?9 ");

		List<Long> idCentros = new ArrayList<>();
		for (CentroDTO centro : filtros.getCentros()) {
			idCentros.add(centro.getIdCentro());
		}
		String centrosIn = UtilConsultasSQL.clausulaInSQLSNumeros(idCentros);
		if (UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA.equals(filtros.getTipoCaso())) {
			nativeQuery.append(" and co.id_centro ").append(centrosIn);
		} else {
			if (Long.valueOf(filtros.getIdSede()) != null && Long.valueOf(filtros.getIdSede()) > 0)
				nativeQuery.append(" and s.id_sede=?10 ");	
			nativeQuery.append(" and s.id_centro ").append(centrosIn);
		}
		
		List<String> filtrosPartes = new ArrayList<>();
		boolean empresaConvenio = false;
		if (filtros.getTipoPartes().isEmpty()) {
			filtrosPartes = Arrays.asList(UtilDominios.ROL_PERSONA_CONVOCANTE, UtilDominios.ROL_PERSONA_CONVOCADO, UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
			empresaConvenio = true;
		} else {
			for (String fparte : filtros.getTipoPartes()) {
				if (fparte.equalsIgnoreCase(UtilDominios.FILTRO_EMPRESA_CONVENIO))
					empresaConvenio = true;
				else
					filtrosPartes.add(fparte);
			}			
		}
		if (!filtrosPartes.isEmpty() || empresaConvenio)
			nativeQuery.append(" and (");
		
		if (!filtrosPartes.isEmpty()) {
			partes = UtilConsultasSQL.clausulaInSQLStrings(filtrosPartes);
			nativeQuery.append(" r.nombre " + partes + " ");
		}
		if ((UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA.equals(filtros.getTipoCaso())
				|| UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(filtros.getTipoCaso())) && empresaConvenio) {
			if (!filtrosPartes.isEmpty())
				nativeQuery.append(" or ");
			nativeQuery.append(" co.id_persona = p.id_persona ");
		}
		if (!filtrosPartes.isEmpty() || empresaConvenio)
			nativeQuery.append(") ");

		nativeQuery.append(" order by c.id_caso ");

		Query q = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), LotesCartasDTO.class);
		q.setParameter(1, UtilDominios.DOMINIO_ROL_PERSONA);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(3, UtilDominios.ESTADO_CASO_REGISTRADO);
		q.setParameter(4, UtilDominios.ESTADO_PERSONA_ACTIVO);
		q.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		q.setParameter(11, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		q.setParameter(14, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		q.setParameter(6, filtros.getFechaInicio());
		q.setParameter(7, filtros.getFechaFinal());
		q.setParameter(12, filtros.getTipoCaso());
		if (Long.valueOf(filtros.getIdCaso()) != null && Long.valueOf(filtros.getIdCaso()) > 0)
			q.setParameter(9, filtros.getIdCaso());
		if (Long.valueOf(filtros.getIdSede()) != null && Long.valueOf(filtros.getIdSede()) > 0)
			q.setParameter(10, filtros.getIdSede());
		if (filtros.isEnvioCorreo())
			q.setParameter(13, UtilConstantes.TRUE);
		else
			q.setParameter(13, UtilConstantes.FALSE);
		
		q.setParameter(15, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			
		return q.getResultList();
	}

	/**
	 * Valida si una persona existe en un caso
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idPersona:
	 *            Identificador de la persona.
	 * @aram idRol: 
	 *            Identificador del rol de la persona.
	 * @return Boolean
	 */
	public Boolean validarPersonaCaso(Long idPersona, Long idCaso, Long idRol, boolean activo) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc");
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" rpc.caso.idCaso=:");
		jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		jpqlQuery.append(" AND rpc.persona.idPersona=:");
		jpqlQuery.append(Persona.ENTIDAD_PERSONA_PK);

		if (idRol != null) {
			jpqlQuery.append(" AND rpc.rol.idRol=:");
			jpqlQuery.append(Rol.ENTIDAD_ROL_PK);
		}

		if (activo) {
			jpqlQuery.append(" AND rpc.estadoRegistro=: ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		}
		
		jpqlQuery.append(" AND rpc.estado <>:").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
		query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		
		if (idRol != null)
			query.setParameter(Rol.ENTIDAD_ROL_PK, idRol);
		if (activo)
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return !query.getResultList().isEmpty();
	}

	/**
	 * Método que obtiene la cantidad de casos que se encuentran asignados a una
	 * persona por rol para casos de convenio o jornada que se encuentran
	 * pendientes de asignacion de audiencia
	 * 
	 * @param idConvenio
	 * @return
	 */
	public List<Object[]> obtenerListadoCasosPersona(Long idConvenio, String rol) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select id_persona, count(*) ");
		nativeQuery.append("from ROL_PERSONA_CASO ");
		nativeQuery.append("where id_rol = (select id_rol from rol where nombre = ?2) ");
		nativeQuery.append("and estado <> ").append("'INA'");
		nativeQuery.append(" and estado_registro = ").append("'ACT'");
		nativeQuery.append(" and id_caso in (select distinct c.id_caso ");
		nativeQuery.append(" from caso c left join audiencia a on a.id_caso is null ");
		nativeQuery.append(" where c.id_convenio = ?1 ");
		nativeQuery.append(" and c.estado_registro = ").append("'ACT'");
		nativeQuery.append(" ) ");
		nativeQuery.append(" group by id_persona ");
		nativeQuery.append(" order by 2 desc ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, idConvenio);
		query.setParameter(2, rol);

		return query.getResultList();
	}

	/**
	 * Metodo que busca las personas de un caso, dependiendo su rol y el centro
	 * 
	 * @param List<String>
	 *            centros, centros que tiene el usuario en seccion.
	 * @param List<String>
	 *            nombreRol, nombre de los roles que se desea buscar.
	 * @return List<Persona>
	 */
	public List<Persona> consultarPersonaCasoPorRolCentro(List<String> centros, List<String> nombreRol) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT DISTINCT (rpc.persona) FROM RolPersonaCaso rpc, ");
		jpqlQuery.append(" Rol rol, ");
		jpqlQuery.append(" Caso c, RolPersona rp, ");
		jpqlQuery.append(" Sede sede , Convenio convenio");
		jpqlQuery.append(" WHERE rpc.rol.idRol = rol.idRol ");
		jpqlQuery.append(" AND rpc.caso.idCaso = c.idCaso ");
		jpqlQuery.append(" AND c.sede.idSede = sede.idSede ");
		jpqlQuery.append(" and rpc.rolPersonaCasoPK.idPersona = rp.idPersona and rp.rol.nombre =: rolApoderadoConvenio");
		jpqlQuery.append(" AND rol.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(nombreRol));
		jpqlQuery.append(" AND sede.idCentro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(centros));
		jpqlQuery.append(" AND rpc.estadoRegistro = : ")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND rol.estadoRegistro = : ").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append(" AND convenio.idConvenio = c.idConvenio ");
		jpqlQuery.append(" AND c.estadoRegistro = : ").append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND sede.estadoRegistro = : ").append(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY rpc.persona.primerNombreORazonSocial ");

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), Persona.class);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter("rolApoderadoConvenio", UtilDominios.ROL_PERSONA_APODERADO_CONVENIO);
		query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Sede.ENTIDAD_SEDE_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PartesSeguimientoDTO> consultarPartesSeguimiento(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select concat(rtrim(p.primer_nombre_o_razon_social),' ',rtrim(p.segundo_nombre),' ',");
		nativeQuery.append(" rtrim(p.primer_apellido),' ',rtrim(p.segundo_apellido)) as nombreParte, ");
		nativeQuery.append(" cel.id_telefono as idCelular, ");
		nativeQuery.append(" cel.numero as celular, ");
		nativeQuery.append(" tel.id_telefono as idTelefono, ");
		nativeQuery.append(" tel.numero as telefono, ");
		nativeQuery.append(" (select nombre from dominio d where d.codigo=r.nombre and dominio=?1 ) as tipoParte, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(" rpc.id_rol as idRol");
		nativeQuery.append(" from ROL_PERSONA_CASO rpc ");
		nativeQuery.append(
				" inner join persona p on p.id_persona=rpc.id_persona and rpc.estado_registro=p.estado_registro ");
		nativeQuery.append(
				" inner join rol r on rpc.id_rol=r.id_rol and r.estado_registro=rpc.estado_registro and  r.nombre in (?2,?3,?4,?5) ");
		nativeQuery.append(" left join (select id_telefono,id_persona,numero from TELEFONO where tipo_telefono=?6 ");
		nativeQuery.append(" and estado_registro=?7 and id_persona is not null union ");
		nativeQuery.append(" select t.id_telefono,u.id_persona,t.numero from telefono t ");
		nativeQuery.append(
				" inner join ubicacion u on t.id_ubicacion=u.id_ubicacion and t.estado_registro=u.estado_registro ");
		nativeQuery.append(" where t.estado_registro=?7 and t.tipo_telefono=?6 and   u.id_persona is not null ");
		nativeQuery.append(" ) cel on cel.id_persona=p.id_persona ");
		nativeQuery.append(" left join (select id_telefono,id_persona,numero from TELEFONO where tipo_telefono=?8 ");
		nativeQuery.append(" and estado_registro=?7 and id_persona is not null union ");
		nativeQuery.append(" select t.id_telefono,u.id_persona,t.numero from telefono t ");
		nativeQuery.append(
				" inner join ubicacion u on t.id_ubicacion=u.id_ubicacion and t.estado_registro=u.estado_registro ");
		nativeQuery.append(" where t.estado_registro=?7 and t.tipo_telefono=?8 and   u.id_persona is not null ");
		nativeQuery.append(" ) tel on tel.id_persona=p.id_persona ");
		nativeQuery.append(" where rpc.estado_registro=?7 ");
		nativeQuery.append(" and rpc.id_caso = ?9 ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), PartesSeguimientoDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(2, UtilDominios.ROL_PERSONA_CONVOCADO);
		query.setParameter(3, UtilDominios.ROL_PERSONA_CONVOCANTE);
		query.setParameter(4, UtilDominios.ROL_PERSONA_APODERADO_CONVOCADO);
		query.setParameter(5, UtilDominios.ROL_PERSONA_APODERADO_CONVOCANTE);
		query.setParameter(6, UtilDominios.TIPO_TELEFONO_CELULAR);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.TIPO_TELEFONO_FIJO);
		query.setParameter(9, idCaso);
		return query.getResultList();
	}

	/**
	 * query que consulta los registros que tenga una persona en RolPersonaCaso
	 * por nombre del rol, estado de nombramiento y tipo de nombramiento
	 * 
	 * @param idPersona
	 * @param nombreRol
	 * @param estado
	 * @param tipoNombramiento
	 * @return List<RolPersonaCaso>
	 */
	public List<RolPersonaCaso> consultarCasosPersonaRolEstadoTipo(Long idPersona, List<String> nombreRol,
			List<String> estados, List<String> tipoNombramientos) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT rpc FROM RolPersonaCaso rpc ");
		jpqlQuery.append(" JOIN rpc.rol r ");
		jpqlQuery.append(" JOIN rpc.persona p ");
		jpqlQuery.append(" WHERE rpc.estadoRegistro = : ")
				.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);
		if(idPersona != null)
			jpqlQuery.append(" AND p.idPersona = : ").append(Persona.ENTIDAD_PERSONA_PK);
		if (nombreRol != null && !nombreRol.isEmpty())
			jpqlQuery.append(" AND r.nombre in : ").append(Rol.ENTIDAD_ROL_NOMBRE);
		if (estados != null && !estados.isEmpty())
			jpqlQuery.append(" AND rpc.estado in : ").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO);
		if (tipoNombramientos != null && !tipoNombramientos.isEmpty())
			jpqlQuery.append(" AND rpc.tipoNombramiento in : ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO);
		jpqlQuery.append(" AND p.estadoRegistro = : ").append(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO);
		jpqlQuery.append(" AND r.estadoRegistro = : ").append(Rol.ENTIDAD_ROL_ESTADO_REGISTRO);
		jpqlQuery.append(" ORDER BY rpc.caso.idCaso DESC");

		Query query = mp.getEntityManager().createQuery(jpqlQuery.toString(), RolPersonaCaso.class);
		query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
				UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Rol.ENTIDAD_ROL_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Persona.ENTIDAD_PERSONA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if(idPersona != null)
			query.setParameter(Persona.ENTIDAD_PERSONA_PK, idPersona);
		if (nombreRol != null && !nombreRol.isEmpty())
			query.setParameter(Rol.ENTIDAD_ROL_NOMBRE, nombreRol);
		if (estados != null && !estados.isEmpty())
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO, estados);
		if (tipoNombramientos != null && !tipoNombramientos.isEmpty())
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_TIPO_NOMBRAMIENTO, tipoNombramientos);
		return query.getResultList();
	}
	
	/**
	 * Método que retorna la cantidad de casos que acepto la persona como conciliador en el periodo 
	 * comprendido en las fechas recibidas por parametro
	 * @param idPersona
	 * @param periodoDesde
	 * @param periodoHasta
	 * @return
	 */
	public BigDecimal cantidadCasosAceptadosPorConciliador(Long idPersona,
			Date periodoDesde, Date periodoHasta) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select count(distinct e.id_caso) from rol_persona_caso rpc ");
		nativeQuery.append(" inner join EVENTO_ROL_PERSONA_CASO e on e.id_caso = rpc.id_caso ");
		nativeQuery.append(" and estado_asignado = ?2 and e.id_persona = rpc.id_persona ");
		nativeQuery.append(" and rpc.id_rol = e.id_rol  ");
		nativeQuery.append(" and fecha_de_asignacion between ?5 and ?6 ");
		nativeQuery.append(" inner join TIPO_DE_SERVICIO_ROL t on e.id_rol = t.id_rol and tipo_servicio = ?3 and t.estado_registro = ?1 ");
		nativeQuery.append(" where rpc.estado_registro = ?1 ");
		nativeQuery.append(" and rpc.estado = ?2 ");
		nativeQuery.append(" and rpc.id_persona = ?4 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(4, idPersona);
		query.setParameter(5, periodoDesde);
		query.setParameter(6, periodoHasta);
		
		return (BigDecimal) query.getSingleResult();
	}
	
	public List<CasoAsignadoDTO> consultarVinculacionClienteCaso(PersonaDTO cliente){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" Select distinct s.nombre as nombreServicio, ");
		nativeQuery.append(" c.id_caso as codigoCaso, ");
		nativeQuery.append(" c.nombre as nombreCaso, ");
		nativeQuery.append(" c.fecha_radicacion as fechaRadicacion, ");
		nativeQuery.append(" d.nombre as estadoCaso ");
		nativeQuery.append(" from caso c  ");
		nativeQuery.append(" inner join ROL_PERSONA_CASO rpc on rpc.id_caso=c.id_caso  ");
		nativeQuery.append(" INNER JOIN ROL R ON R.ID_ROL = rpc.id_rol ");
		nativeQuery.append(" INNER JOIN CLASIFICADOR_DOMINIO CD ON CD.codigo_clasificado=R.nombre ");
		nativeQuery.append(" inner join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append(" inner join SERVICIO s on  c.id_servicio=s.id_servicio ");
		nativeQuery.append(" inner join dominio d on d.codigo=c.estado_caso ");
		nativeQuery.append(" where d.dominio= ?1 ");
		nativeQuery.append(" AND CD.CODIGO_CLASIFICADOR= ?2 ");
		
		if(cliente.getNumeroDocumento()!=null){
			nativeQuery.append(" and p.numero_documento = ?3 ");	
		}
		if(cliente.getTipoDocumento()!=null){
			nativeQuery.append(" and p.tipo_documento = ?4 ");	
		}
		
		if(cliente.getPrimerNombreORazonSocial()!=null){
			String nombres  = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like ?6 ) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like  ?6) or "+  
					"(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like ?6) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like ?6)  )";
			nativeQuery.append(" AND ").append(nombres);
		}
		
		nativeQuery.append(" and s.estado_registro=?5 ");
		nativeQuery.append(" and c.estado_registro=?5 ");
		nativeQuery.append(" and rpc.estado_registro=?5 ");
		nativeQuery.append(" and r.estado_registro=?5 ");
		nativeQuery.append(" and p.estado_registro=?5 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), CasoAsignadoDTO.class);
		query.setParameter(1, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(2, UtilDominios.AGRUPADOR_ROL_PERSONA_PARTES_APLICACION);
		if(cliente.getNumeroDocumento()!=null){
			query.setParameter(3, cliente.getNumeroDocumento());	
		}
		if(cliente.getTipoDocumento()!=null){
			query.setParameter(4, cliente.getTipoDocumento());	
		}
		
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, cliente.getPrimerNombreORazonSocial());
					
		return query.getResultList();
	}
	
	
	/**
	 * Metodo que consulta la alerta de recordatorio de aceptacion de casos
	 * @param idCaso
	 * @param idPersona
	 * @return
	 */
	public InfoBasicaAlertasDTO alertaRecordatorioAceptacionCaso( Long idCaso , Long idPersona ){		
		InfoBasicaAlertasDTO infoAlertas;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.nombre AS nombreCaso, ");
		nativeQuery.append(" ce.id_centro AS idCentro, ");
		nativeQuery.append(" ce.nombre AS nombreCentro , ");
		nativeQuery.append(" CONVERT(INT, pg.valor) AS valorParametroNumerico ");
		nativeQuery.append(" FROM ROL_PERSONA_CASO rpc  ");
		nativeQuery.append(" INNER JOIN CASO ca  ON rpc.id_caso = ca.id_caso AND ca.estado_caso <> ?10 AND ca.estado_registro = ?1 ");
		nativeQuery.append(" LEFT JOIN  PARAMETRO_DE_SERVICIO pg ON pg.tipo_parametro = ?11 ");
		nativeQuery.append(" AND pg.nombre = ( CASE WHEN rpc.metodo_nombramiento = ?5 THEN ?7  ");
		nativeQuery.append(" WHEN rpc.metodo_nombramiento = ?6 THEN ?8 END ) AND pg.estado_registro = ?1 and pg.id_servicio = ca.id_servicio , ");
		nativeQuery.append(" CENTRO ce  ");
		nativeQuery.append(" WHERE rpc.estado = ?4 ");

		nativeQuery.append(" AND ce.id_centro =  ( CASE WHEN ca.id_servicio = ?9 THEN  (SELECT cv.id_centro FROM CASO c INNER JOIN CONVENIO cv "); 
		nativeQuery.append(" ON ca.id_convenio = cv.id_convenio AND ca.id_caso = c.id_caso  AND  cv.estado_registro = ?1 )  ");
		nativeQuery.append(" ELSE  (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE ca.id_caso = cas.id_caso AND se.estado_registro = ?1 ) END ) ");
		nativeQuery.append(" AND rpc.estado_registro = ?1 ");
		nativeQuery.append(" AND rpc.id_persona = ?3 ");
		nativeQuery.append(" AND rpc.id_caso = ?2 ");		
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(5, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(6, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);		
		query.setParameter(7, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB);
		query.setParameter(8, UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_PARTES);
		query.setParameter(9, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(10, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(11, UtilDominios.PARAMETRO_DE_SERVICIO_ACEPTACION_CASO);
		try {			
			infoAlertas = (InfoBasicaAlertasDTO) query.getSingleResult();
		} catch (NonUniqueResultException  | NoResultException e) {			
			infoAlertas = null;
		}

		return infoAlertas;
	}

	/**
	 * Metodo que consulta la alerta de caso pendiente por realizacion de control de legalidad
	 * @param idCaso
	 * @param idPersona
	 * @return InfoBasicaAlertasDTO
	 */
	public InfoBasicaAlertasDTO alertaControlLegalidad( Long idCaso , Long idPersona ){
		InfoBasicaAlertasDTO infoAlertas;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select distinct ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.nombre AS nombreCaso, ");
		nativeQuery.append(" s.id_centro AS idCentro ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join caso ca ");
		nativeQuery.append(" on ca.id_caso = rpc.id_caso ");
		nativeQuery.append(" inner join audiencia a ");
		nativeQuery.append(" on a.id_caso = ca.id_caso ");
		nativeQuery.append(" inner join resultado_audiencia ra ");
		nativeQuery.append(" on ra.id_audiencia = a.id_audiencia ");
		nativeQuery.append(" inner join rol r ");
		nativeQuery.append(" on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" inner join sede s ");
		nativeQuery.append(" on s.id_sede = ca.id_sede ");
		nativeQuery.append(" where r.nombre = ?2 ");
		nativeQuery.append(" and ra.estado = ?3 ");
		nativeQuery.append(" and ca.estado_caso = ?4 ");
		nativeQuery.append(" and rpc.estado = ?5 ");
		nativeQuery.append(" and rpc.id_persona = ?6 ");
		nativeQuery.append(" and ca.id_caso = ?7 ");
		nativeQuery.append(" and rpc.estado_registro = ?1 ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and a.estado_registro = ?1 ");
		nativeQuery.append(" and ra.estado_registro = ?1 ");
		nativeQuery.append(" and r.estado_registro = ?1 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION);
		query.setParameter(3, UtilDominios.ESTADO_RESULTADO_POR_VALIDAR);
		query.setParameter(4, UtilDominios.ESTADO_CASO_ASIGNADO_CONTROL_DE_LEGALIDAD);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		query.setParameter(6, idPersona);		
		query.setParameter(7, idCaso);

		try {
			infoAlertas = (InfoBasicaAlertasDTO) query.getSingleResult();
		} catch (NonUniqueResultException  | NoResultException e) {
			infoAlertas = null;
		}
		return infoAlertas;
	}
	
	/**
	 * Metodo que consulta la alerta sin tramitar por el abogado.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param idPersona:
	 *            Identificador de la persona.
	 * @return InfoBasicaAlertasDTO
	 */
	public InfoBasicaAlertasDTO consultarSiExisteTramiteCasoAbogado(Long idCaso, Long idPersona) {
		InfoBasicaAlertasDTO infoAlertas;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select ca.id_caso AS idCaso, ");
		nativeQuery.append(" ca.nombre AS nombreCaso, ");
		nativeQuery.append(" s.id_centro AS idCentro, ");
		nativeQuery.append(" CONCAT(p.primer_nombre_o_razon_social,' ',p.segundo_nombre,' ',p.primer_apellido,' ',p.segundo_apellido) as nombreAbogado ");
		nativeQuery.append(" from rol_persona_caso rpc ");
		nativeQuery.append(" inner join caso ca ");
		nativeQuery.append(" on ca.id_caso = rpc.id_caso ");
		nativeQuery.append(" inner join rol r ");
		nativeQuery.append(" on r.id_rol = rpc.id_rol ");
		nativeQuery.append(" inner join sede s ");
		nativeQuery.append(" on s.id_sede = ca.id_sede ");
		nativeQuery.append(" inner join persona p "); 
		nativeQuery.append(" on rpc.id_persona = p.id_persona "); 
		nativeQuery.append(" where r.nombre = ?2 ");
		nativeQuery.append(" and rpc.estado IN (?3, ?4) ");
		nativeQuery.append(" and rpc.id_persona = ?5 ");
		nativeQuery.append(" and ca.tipo_pacto IS NULL ");
		nativeQuery.append(" and ca.descripcion_pacto IS NULL ");
		nativeQuery.append(" and ca.id_caso = ?6 ");
		nativeQuery.append(" and rpc.estado_registro = ?1 ");
		nativeQuery.append(" and ca.estado_registro = ?1 ");
		nativeQuery.append(" and r.estado_registro = ?1 ");
		nativeQuery.append(" and s.estado_registro = ?1 ");
		nativeQuery.append(" and ca.estado_caso not in (?7, ?8, ?9) ");

		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_ABOGADO);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, idPersona);
		query.setParameter(6, idCaso);
		query.setParameter(7, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_SUSPENDIDO);
		query.setParameter(9, UtilDominios.ESTADO_CASO_SUSPENDIDO_POR_REQUERIMIENTO);
		
		try {
			infoAlertas = (InfoBasicaAlertasDTO) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			infoAlertas = null;
		}
		return infoAlertas;
	}
	
	/**
	 *Alerta A8 Nombrar suplente como principal consulta 
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> alertaNombrarSuplenteArbitraje(String tipoPeriodicidad, Long valor){
		List<String> casosCierre = Arrays.asList(UtilDominios.ESTADO_CASO_CERRADO,UtilDominios.ESTADO_CASO_CANCELADO);
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("  WITH consulta AS ( ");
		nativeQuery.append("  SELECT rpc.id_persona AS tpidPersona, rpc.id_caso AS tpidCaso, erpc.fecha_de_asignacion AS tpfechaAsignacion,  ");
		nativeQuery.append("  DATEDIFF(dd,erpc.fecha_de_asignacion,GETDATE() ) AS tpdiasCalenadario, ");
		nativeQuery.append("  dbo.diasHabilesEntreDosFechas(erpc.fecha_de_asignacion,GETDATE()) AS tpdiasHabiles ");
		nativeQuery.append("  FROM ROL_PERSONA_CASO rpc ");
		nativeQuery.append("  INNER JOIN CASO ca ON ca.id_caso = rpc.id_caso AND ca.estado_caso NOT ").append(UtilConsultasSQL.clausulaInSQLStrings(casosCierre));
		nativeQuery.append("  INNER JOIN SERVICIO se ON se.id_servicio = ca.id_servicio ");
		nativeQuery.append("  CROSS APPLY (SELECT TOP 1 * FROM EVENTO_ROL_PERSONA_CASO erpc WHERE rpc.id_rol = erpc.id_rol AND rpc.id_persona = erpc.id_persona ");
		nativeQuery.append("  AND rpc.id_caso = erpc.id_caso AND erpc.estado_asignado = ?6 order by fecha_de_asignacion desc) erpc ");
		nativeQuery.append("  WHERE rpc.id_rol IN (SELECT id_rol FROM PARAMETRO_SERVICIO_SORTEO WHERE estado_registro = ?1 ) ");
		nativeQuery.append("  AND rpc.estado = ?2 ");
		nativeQuery.append("  AND rpc.tipo_nombramiento = ?3 ");
		nativeQuery.append("  AND ca.estado_registro = ?1 ");
		nativeQuery.append("  AND rpc.estado_registro = ?1 ");
		nativeQuery.append("  AND erpc.estado_registro = ?1  ");
		nativeQuery.append("  AND se.tipo in (?5, ?7) )  ");

		nativeQuery.append("  SELECT co.tpidPersona AS idPersona, co.tpidCaso AS idCaso, ");
		nativeQuery.append("  co.tpfechaAsignacion AS fechaDesignacion FROM  consulta co ");
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){			
			nativeQuery.append(	"WHERE co.tpdiasHabiles = ISNULL ( ?4 ,5);");
		}else {
			nativeQuery.append("  WHERE co.tpdiasCalenadario = ISNULL ( ?4 ,5); ");		
		}
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(4, valor);
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
		query.setParameter(7, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		return query.getResultList();
		
	}
	
	/**
	 *Alerta A13 Alerta no pronunciamiento del arbitro 
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> alertaNoPronunciamientoArbitro(String tipoPeriodicidad, Long valor){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("  DECLARE @TablaEncabezado varchar(max);  ");
		nativeQuery.append("  Set @TablaEncabezado = CONCAT( ISNULL ( (SELECT descripcion FROM DOMINIO WHERE dominio = ?5 AND codigo = ?6 ), ");
		nativeQuery.append("  '<html><body><table>  '), '<tr><td  width= 100px> <b>Nombre de arbitro</b></td> ");
		nativeQuery.append("   <td> <b>Fecha de designación</b></td> <td><b>Fecha de comunicación</b></td></tr>' ) ; ");

		nativeQuery.append("  WITH consulta AS ( ");
		nativeQuery.append("  SELECT pe.id_persona AS idPersona, ");
		nativeQuery.append("  ca.id_caso AS idCaso, ");
		nativeQuery.append("  ca.nombre AS nombreCaso, ");
		nativeQuery.append("  sed.id_centro AS idCentro,");
		nativeQuery.append("  concat(rtrim(pe.primer_nombre_o_razon_social), rtrim(' '+pe.segundo_nombre), rtrim(' '+pe.primer_apellido), rtrim(' '+pe.segundo_apellido)) AS nombrePersona, ");
		nativeQuery.append("  erpc.fecha_de_asignacion fechaAsig, ");
		nativeQuery.append("  erpcom.fecha_de_asignacion fechaCom, ");
		nativeQuery.append("  DATEDIFF(dd,erpcom.fecha_de_asignacion,GETDATE()) diasCalendario, ");
		nativeQuery.append("  dbo.diasHabilesEntreDosFechas(erpcom.fecha_de_asignacion,GETDATE()) diasHabiles ");
		nativeQuery.append("  FROM ROL_PERSONA_CASO rpc ");
		nativeQuery.append("  INNER JOIN CASO ca ON ca.id_caso = rpc.id_caso AND ca.estado_registro = ?1 ");
		nativeQuery.append("  AND rpc.id_rol IN (SELECT id_rol FROM PARAMETRO_SERVICIO_SORTEO WHERE estado_registro = ?1 ) ");
		nativeQuery.append("  INNER JOIN SERVICIO se ON se.id_servicio = ca.id_servicio AND se.estado_registro = ?1 ");
		nativeQuery.append("  INNER JOIN EVENTO_ROL_PERSONA_CASO erpc ON rpc.id_caso = erpc.id_caso ");
		nativeQuery.append("  AND rpc.id_persona = erpc.id_persona AND rpc.id_rol = erpc.id_rol AND erpc.estado_registro = ?1 ");
		nativeQuery.append("  INNER JOIN EVENTO_ROL_PERSONA_CASO erpcom on rpc.id_caso = erpcom.id_caso ");
		nativeQuery.append("  AND rpc.id_persona = erpcom.id_persona AND rpc.id_rol = erpcom.id_rol AND erpcom.estado_registro = ?1 ");
		nativeQuery.append("  INNER JOIN PERSONA pe ON pe.id_persona = rpc.id_persona AND pe.estado_registro = ?1 ");
		nativeQuery.append("  LEFT JOIN SEDE sed ON sed.id_sede = ca.id_sede AND sed.estado_registro = ?1 ");
		nativeQuery.append("  WHERE rpc.estado_registro = ?1 ");
		nativeQuery.append("  AND rpc.estado = ?2 ");
		nativeQuery.append("  and erpcom.estado_asignado = ?7 ");
		nativeQuery.append("  AND erpc.estado_asignado = ?2 ");
		nativeQuery.append("  and ca.estado_caso <> ?8 ");
		nativeQuery.append("  AND se.tipo in (?3, ?9) ) ");

		nativeQuery.append("  SELECT DISTINCT co.idCaso, co.nombreCaso, co.idCentro, ");
		nativeQuery.append("  ( SELECT  @TablaEncabezado +  CONVERT(NVARCHAR(MAX), (SELECT ");
		nativeQuery.append("  cs.nombrePersona AS td, ");
		nativeQuery.append("  FORMAT(cs.fechaAsig, 'dd/MM/yyyy')  AS td, ");
		nativeQuery.append("  FORMAT(cs.fechaCom, 'dd/MM/yyyy') AS td ");
		nativeQuery.append("  FROM   consulta  cs ");
		nativeQuery.append("  WHERE cs.idCaso =co.idCaso "); 
		
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){			
			nativeQuery.append(	" AND cs.diasHabiles = ISNULL ( ?4 , 3) ");
		}else {
			nativeQuery.append("  AND cs.diasCalendario = ISNULL ( ?4 , 3)  ");		
		}
		
		
		nativeQuery.append("  FOR XML RAW('tr'), ELEMENTS, TYPE) ) + '</table></body></html>'   ) AS tabla ");
		nativeQuery.append("  FROM consulta co  ");
		
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){			
			nativeQuery.append(	" WHERE co.diasHabiles = ISNULL ( ?4 ,5) ");
		}else {
			nativeQuery.append("  WHERE co.diasCalendario = ISNULL ( ?4 ,5)  ");		
		}
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(4, valor);
		query.setParameter(5, UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		query.setParameter(6, UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(9, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		return query.getResultList();
		
	}
	
	
	/**
	 *Alerta A14 No Pronunciamiento del Secretario de tribunal
	 * @return
	 */
	public List<InfoBasicaAlertasDTO> alertaNoPronunciamientoSecretario(String tipoPeriodicidad, Long valor){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(	" WITH consulta AS (	 SELECT pe.id_persona AS idPersona,  ");
		nativeQuery.append(	" ca.id_caso AS idCaso,  ");
		nativeQuery.append(	" ca.nombre AS nombreCaso,  ");
		nativeQuery.append(	" sed.id_centro AS idCentro,  ");
		nativeQuery.append(	" concat(rtrim(pe.primer_nombre_o_razon_social), rtrim(' '+pe.segundo_nombre), rtrim(' '+pe.primer_apellido), rtrim(' '+pe.segundo_apellido)) AS nombrePersona,  ");
		nativeQuery.append(	" erpc.fecha_de_asignacion fechaAsig,  ");
		nativeQuery.append(	" DATEDIFF(dd,erpcom.fecha_de_asignacion,GETDATE()) diasCalendario,  ");
		nativeQuery.append(	" dbo.diasHabilesEntreDosFechas(erpcom.fecha_de_asignacion,GETDATE()) diasHabiles  ");
		nativeQuery.append(	" FROM ROL_PERSONA_CASO rpc  ");
		nativeQuery.append(	" INNER JOIN CASO ca ON ca.id_caso = rpc.id_caso AND ca.estado_registro = ?1  ");
		nativeQuery.append(	" INNER JOIN ROL r ON r.id_rol = rpc.id_rol AND r.nombre = ?5  AND r.estado_registro = ?1  ");
		nativeQuery.append(	" INNER JOIN SERVICIO se ON se.id_servicio = ca.id_servicio AND se.estado_registro = ?1  ");
		nativeQuery.append(	" INNER JOIN EVENTO_ROL_PERSONA_CASO erpc ON rpc.id_caso = erpc.id_caso  ");
		nativeQuery.append(	" AND rpc.id_persona = erpc.id_persona AND rpc.id_rol = erpc.id_rol AND erpc.estado_registro = ?1 ");
		nativeQuery.append("  INNER JOIN EVENTO_ROL_PERSONA_CASO erpcom on rpc.id_caso = erpcom.id_caso ");
		nativeQuery.append("  AND rpc.id_persona = erpcom.id_persona AND rpc.id_rol = erpcom.id_rol AND erpcom.estado_registro = ?1 ");
		nativeQuery.append(	" INNER JOIN PERSONA pe ON pe.id_persona = rpc.id_persona AND pe.estado_registro = ?1  ");
		nativeQuery.append(	" INNER JOIN SEDE sed ON sed.id_sede = ca.id_sede AND sed.estado_registro = ?1 ");
		nativeQuery.append(	" WHERE rpc.estado_registro = ?1  ");
		nativeQuery.append(	" AND rpc.estado = ?2  ");
		nativeQuery.append(	" AND erpc.estado_asignado = ?2  ");
		nativeQuery.append("  and erpcom.estado_asignado = ?7 ");
		nativeQuery.append("  and ca.estado_caso <> ?8 ");
		nativeQuery.append(	" AND se.tipo = ?3 )  ");

		nativeQuery.append(	" SELECT co.idCaso, co.nombreCaso, co.nombrePersona, co.idCentro FROM consulta co  ");
		
		
		if(UtilDominios.TIPO_PERIODICIDAD_HABIL.equals(tipoPeriodicidad)){			
			nativeQuery.append(	" WHERE co.diasHabiles = ISNULL ( ?4 ,5);");
		}else {
			nativeQuery.append("  WHERE co.diasCalendario = ISNULL ( ?4 ,5); ");		
		}		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(4, valor);
		query.setParameter(5, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);		
		query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
		query.setParameter(8, UtilDominios.ESTADO_CASO_CERRADO);
		return query.getResultList();
		
	}

	public List<Long> consultarRolesCasoPersona(Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select distinct id_rol as idRol from rol_persona_caso where id_persona = ?1");
		nativeQuery.append(" and estado_registro = ?2");
		
		Query q = getEntityManager().createNativeQuery(nativeQuery.toString(), Long.class);
		q.setParameter(1, idPersona);
		q.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		return q.getResultList();
	}
	
	public boolean existeArbitroNombramientoOrden(RolPersonaCaso rpc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from rol_persona_caso rpc inner join rol r on r.id_rol = rpc.id_rol and r.nombre = ?5 and r.estado_registro = ?1 ");
		sql.append(" where rpc.tipo_nombramiento = ?2 ");
		sql.append(" and rpc.orden_de_asignacion = ?3 ");
		sql.append(" and rpc.id_caso = ?4 ");
		sql.append(" and rpc.estado_registro = ?1 ");
		sql.append(" and rpc.metodo_nombramiento = ?6 ");
		if (!UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(rpc.getTipoNombramiento())
				&& rpc.getIdPersonaTercero() != null)
			sql.append(" and rpc.id_persona_tercero = ?7 ");
		sql.append(" and rpc.estado in (?8, ?9) ");
		
		Query query = getEntityManager().createNativeQuery(sql.toString(), Long.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, rpc.getTipoNombramiento());
		query.setParameter(3, rpc.getOrdenDeAsignacion());
		query.setParameter(4, rpc.getRolPersonaCasoPK().getIdCaso());
		query.setParameter(5, UtilDominios.ROL_PERSONA_ARBITRO);
		query.setParameter(6, rpc.getMetodoNombramiento());
		if (!UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES.equals(rpc.getTipoNombramiento())
				&& rpc.getIdPersonaTercero() != null)
			query.setParameter(7, rpc.getIdPersonaTercero());
		query.setParameter(8, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		
		return !query.getResultList().isEmpty();
	}
	
	/**
	 * consulta los arbitros para el reporte Reporte de casos sorteados
	 * 
	 * @param idCaso
	 * @param idSorteo
	 * @return
	 */
	public List<ArbitrosDisponiblesSorteoDTO> obtenerArbitrosDisponiblesSorteoCaso(Long idCaso, Long idSorteo) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) as nombre, ");
		nativeQuery.append("case when aleatorio_asignado is null then 'Si' else 'No' end as nombradoPreviamente, ");
		nativeQuery.append("aleatorio_asignado as numeroAleatorioDesignado, ");
		nativeQuery.append("(select case when rpc.id_persona_principal_reemplazado is not null then 'Suplente' ");
		nativeQuery.append("else ");
		nativeQuery.append("(select nombre from dominio d where dominio = 'tipo_nombramiento' and codigo = rpc.tipo_nombramiento) ");
		nativeQuery.append("end from ROL_PERSONA_CASO rpc where rpc.id_caso = s.id_caso and rpc.id_sorteo = s.id_sorteo and rpc.id_persona=p.id_persona) as designadoSorteo, ");
		nativeQuery.append("case when e.elegido_por_liberacion_lista = 1 then 'Si' else 'No' end as liberado ");
		nativeQuery.append("from persona p ");
		nativeQuery.append("inner join ELEGIBLE e ");
		nativeQuery.append("on p.id_persona = e.id_persona ");
		nativeQuery.append("inner join sorteo s ");
		nativeQuery.append("on e.id_sorteo = s.id_sorteo ");
		nativeQuery.append("where s.id_sorteo = ?1 and s.id_caso = ?2 ");
		nativeQuery.append("order by nombre ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), ArbitrosDisponiblesSorteoDTO.class);

		query.setParameter(1, idSorteo);
		query.setParameter(2, idCaso);

		return query.getResultList();
	}
	
	
	public List<RolPersonaCaso> consultarPorIdCasoyIdRol(Long idCaso, int idRol){
		
		StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("select rpc.* ");
		nativeQuery.append("from ROL_PERSONA_CASO rpc ");
		nativeQuery.append("where rpc.id_caso = ?1 ");
		nativeQuery.append("and rpc.id_rol = ?2 ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), RolPersonaCaso.class);

		query.setParameter(1, idCaso);
		query.setParameter(2, idRol);

		return query.getResultList();
	}
	
	public List<RolPersonaCaso> consultarPorIdCasoyRol(Long idCaso, String rol){
  		StringBuilder nativeQuery = new StringBuilder();
  
  		nativeQuery.append("select rpc.* ");
  		nativeQuery.append("from ROL_PERSONA_CASO rpc ");
  		nativeQuery.append("inner join ROL r on r.id_rol = rpc.id_rol ");
  		nativeQuery.append("where rpc.id_caso = ?1 ");
  		nativeQuery.append("and r.nombre = ?2 ");
  
  		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), RolPersonaCaso.class);
  
  		query.setParameter(1, idCaso);
  		query.setParameter(2, rol);
  
  		return query.getResultList();
  	}
  	
	public Integer consultarNumeroCasosAbiertosAceptados(Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();

		jpqlQuery.append("SELECT     COUNT(*) ");
		jpqlQuery.append("FROM       CASO c ");
		jpqlQuery.append("           INNER JOIN ROL_PERSONA_CASO rpc ");
		jpqlQuery.append("           ON         c.id_caso = rpc.id_caso ");
		jpqlQuery.append("           INNER JOIN PERSONA p ");
		jpqlQuery.append("           ON         rpc.id_persona = p.id_persona ");
		jpqlQuery.append("           INNER JOIN EVENTO_ROL_PERSONA_CASO e ");
		jpqlQuery.append("           ON         rpc.id_caso       = e.id_caso ");
		jpqlQuery.append("           AND        rpc.id_persona    = e.id_persona ");		
		jpqlQuery.append("           INNER JOIN ESTADO_PERSONA_ROL epr ");
		jpqlQuery.append("           ON         rpc.id_persona = epr.id_persona ");
		jpqlQuery.append("           AND        rpc.id_rol     = epr.id_rol ");
		jpqlQuery.append("           INNER JOIN PARAMETRO_DE_SERVICIO ps ");
		jpqlQuery.append("           ON         convert(varchar(10), e.fecha_de_asignacion, 111) >= ps.valor ");
		jpqlQuery.append("WHERE      rpc.id_rol                = ?1 ");
		jpqlQuery.append("AND        c.estado_caso            <> ?2 ");
		jpqlQuery.append("AND        c.id_servicio             = ?3 ");
		jpqlQuery.append("AND        rpc.estado                = ?4 ");
		jpqlQuery.append("AND        EXISTS ");
		jpqlQuery.append("           (SELECT 1 ");
		jpqlQuery.append("           FROM    ROL_PERSONA rp ");
		jpqlQuery.append("           WHERE   rp.id_persona               = rpc.id_persona ");
		jpqlQuery.append("           AND     rp.id_rol                   = ?1 ");
		jpqlQuery.append("           AND     rp.fecha_fin_vigencia IS NULL ");
		jpqlQuery.append("           ) ");
		jpqlQuery.append("AND        epr.id_motivo  = ?7 ");
		jpqlQuery.append("AND        e.estado_asignado = ?6 ");
		jpqlQuery.append("AND        ps.nombre = ?8 ");
		jpqlQuery.append("AND        rpc.id_persona         = ?9");

		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), Long.class);
		query.setParameter(1, UtilConstantes.ID_ROL_ARBITRO);
		query.setParameter(2, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(3, UtilConstantes.ID_SERVICIO_ARBITRAJE);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(7, UtilDominios.ESTADO_ARBITROS_HABILITADO);
		query.setParameter(8, UtilConstantes.FECHA_APROBACION);
		query.setParameter(9, idPersona);

		List<Object> resultadoQuery = query.getResultList();

		if (resultadoQuery == null || resultadoQuery.isEmpty())
			return 0;

		return Integer.valueOf(resultadoQuery.get(0).toString());
	}
	
	public boolean ultimoCasoDesignadoSuplente(Long idPersona, Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		Long idUltimoCaso;

		jpqlQuery.append("SELECT     erpc.id_caso ");
		jpqlQuery.append("FROM       EVENTO_ROL_PERSONA_CASO erpc ");
		jpqlQuery.append("           inner join ROL_PERSONA_CASO rpc ");
		jpqlQuery.append("           ON         erpc.id_persona = rpc.id_persona ");
		jpqlQuery.append("           AND        erpc.id_caso = rpc.id_caso ");		
		jpqlQuery.append("           inner join CASO c ");
		jpqlQuery.append("           on rpc.id_caso = c.id_caso ");
		jpqlQuery.append("           inner join SORTEO s ");
		jpqlQuery.append("           on s.id_sorteo = rpc.id_sorteo ");
		jpqlQuery.append("           and s.id_caso = c.id_caso ");
		jpqlQuery.append("WHERE      erpc.id_persona         = ?1 ");
		jpqlQuery.append("AND        estado_asignado         = ?2 ");
		jpqlQuery.append("AND        rpc.tipo_nombramiento   = ?3 ");
		jpqlQuery.append("AND        rpc.metodo_nombramiento = ?4 ");
		jpqlQuery.append("AND        (s.preseleccion = 0 or (s.preseleccion = 1 and s.quien_preselecciona = ?6 )) ");
		jpqlQuery.append("AND        s.materia_caso = (select top 1 materia_caso from SORTEO where id_caso = ?5 order by fecha_realizacion desc) ");
		jpqlQuery.append("order by   fecha_de_asignacion desc ");

		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), Long.class);
		query.setParameter(1, idPersona);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		query.setParameter(3, UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE);
		query.setParameter(4, UtilDominios.METODO_NOMBRAMIENTO_SORTEO);
		query.setParameter(5, idCaso);
		query.setParameter(6, UtilDominios.PRESELECCIONADO_POR_LA_CAMARA_DE_COMERCIO_BOGOTA);

		List<Object> resultadoQuery = query.getResultList();

		if (resultadoQuery == null || resultadoQuery.isEmpty())
			return false;

		idUltimoCaso =  Long.valueOf(resultadoQuery.get(0).toString());
		
		if(idUltimoCaso.equals(idCaso)){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/**
	 * @author aacevedo metodo que consulta si ya existe un id_persona_principal_reemplazado para el caso, validacion evita doble suplente
	 * @param idPersona
	 * @param idCaso
	 * @return
	 */
	public RolPersonaCaso consultarPersonaPrincipalRemplazoCaso(Long idPersona, Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		RolPersonaCaso rolPersonaCaso = new RolPersonaCaso();
		try {
			jpqlQuery.append("SELECT rpc ");
			jpqlQuery.append(" FROM RolPersonaCaso rpc ");
			jpqlQuery.append(" WHERE rpc.caso.idCaso =: ").append(Caso.ENTIDAD_CASO_PK);
			jpqlQuery.append(" AND rpc.idPersonaPrincipalReemplazado =: ").append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO);
			jpqlQuery.append(" AND rpc.estadoRegistro =: ")
					.append(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO);

			Query query = mp.getEntityManager().createQuery(jpqlQuery.toString());
			query.setParameter(Caso.ENTIDAD_CASO_PK, idCaso);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ID_PERSONA_PRINCIPAL_REEMPLAZADO, idPersona);
			query.setParameter(RolPersonaCaso.ENTIDAD_ROL_PERSONA_CASO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO);				
			rolPersonaCaso = (RolPersonaCaso) query.getSingleResult();
			logger.info("Resultado idPersonaPrincipalRemplazo: "+ rolPersonaCaso.getIdPersonaPrincipalReemplazado());

		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR260.toString()));
		} catch (Exception e) {
			return null;
		}
		return rolPersonaCaso;
	}
	
	/**
	 * @author aacevedo metodo que consulta si ya existe un id_persona para un caso creado los ultimos 10 minutos
	 * @param idPersona
	 * @param idRol
	 * @return integer
	 */
	public Integer consultarPersonaRolCasoDoble(Long idPersona) {
		StringBuilder jpqlQuery = new StringBuilder();			
		jpqlQuery.append("SELECT COUNT(*) ");
		jpqlQuery.append(" FROM ROL_PERSONA_CASO rpc, CASO c ");
		jpqlQuery.append(" WHERE rpc.id_caso  = c.id_caso  ");
		jpqlQuery.append(" and rpc.id_persona  = ?1  ");		
		jpqlQuery.append(" AND rpc.estado_registro = ?2 ");
		jpqlQuery.append(" AND DATEDIFF(minute, c.fecha_radicacion , SYSDATETIME()) <= 10 ");				
		
		Query query = mp.getEntityManager().createNativeQuery(jpqlQuery.toString(), Long.class);
		query.setParameter(1, idPersona);		
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);					
		List<Object> resultadoQuery = query.getResultList();
			
		
		if (resultadoQuery == null || resultadoQuery.isEmpty())
			return 0;
		return Integer.valueOf(resultadoQuery.get(0).toString());
	}
	
	public String obtenerNombreRol(Long idCaso, Long idPersona) {
				
    	StringBuilder string = new StringBuilder();     	
    	string.append("select top 1 d.nombre from ROL_PERSONA_CASO rpc ");
    	string.append("inner join rol r on r.id_rol = rpc.id_rol ");
    	string.append("inner join DOMINIO d on d.codigo = r.nombre ");
    	string.append("where rpc.estado_registro = ?1 ");
    	string.append("and r.estado_registro = ?1 ");
    	string.append("and id_persona = ?2 ");
    	string.append("and id_caso = ?3 ");
    	string.append("and d.dominio = ?4 ");
    	    	
    	Query query = getEntityManager().createNativeQuery(string.toString());
    	query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	query.setParameter(2, idPersona);
    	query.setParameter(3, idCaso);
    	query.setParameter(4, UtilDominios.DOMINIO_ROL_PERSONA);
    	
    	try { 
    		return (String) query.getSingleResult();			
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
	
	public List<String> listaConciliadoresEquidadRechazo() {		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT CONCAT(p.primer_nombre_o_razon_social,' ',p.segundo_nombre,' ',p.primer_apellido,' ',p.segundo_apellido) as nombreCompleto ");
		nativeQuery.append("FROM PERSONA p WHERE P.id_persona  IN ( ");
		nativeQuery.append("SELECT erpc.id_persona " ); 
		nativeQuery.append(" from EVENTO_ROL_PERSONA_CASO erpc ");	
		nativeQuery.append("where erpc.id_rol = (SELECT r.id_rol  FROM ROL r  WHERE r.nombre = ?1 ) ");
		nativeQuery.append("and estado_asignado = ?2 ");
		nativeQuery.append("and dbo.diasHabilesEntreDosFechas(erpc.fecha_ultima_modificacion, GETDATE())<= 15 ");
		nativeQuery.append("GROUP by erpc.id_persona ");
		nativeQuery.append("HAVING COUNT(*) > 1 ) ");
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1, UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
    	return (List<String>) query.getResultList();
    }

	// protected region metodos adicionales end

}
